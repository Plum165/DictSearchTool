import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;

public class DictionarySearch {
    static TreeSet<String> dictionary = new TreeSet<>();
    static AVLTree avl = new AVLTree();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        // Main menu loop
        do {
            // Display the menu
            System.out.println("\n=== Dictionary Search Menu ===");
            System.out.println("1. Load Dictionary and Query Files");
            System.out.println("2. Perform AVL Tree Search");
            System.out.println("3. Perform Binary Search");
            System.out.println("4. Display Binary Search Comparison Table");
            System.out.println("5. Comparing the Binary Search");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    loadFiles(input);  // Load the dictionary and query files
                    break;

                case 2:
                    if (avl != null) {
                        System.out.print("Enter the word to search in AVL tree: ");
                        String word = input.nextLine().toLowerCase();
                        int comparisons = avl.search(word);
                        System.out.println("Comparisons for " + word + ": " + comparisons);
                    } else {
                        System.out.println("You need to load files first.");
                    }
                    break;

                case 3:
                    if (!dictionary.isEmpty()) {
                        System.out.print("Enter the word to search with Binary Search: ");
                        String word = input.nextLine().toLowerCase();
                        String[] sortedArray = dictionary.toArray(new String[0]);
                        int comparisons = binarySearch(sortedArray, word);
                        System.out.println("Comparisons for " + word + ": " + comparisons);
                    } else {
                        System.out.println("You need to load files first.");
                    }
                    break;

                case 4:
                    if (!dictionary.isEmpty()) {
                    System.out.print("Enter query file path: ");
                     String queryFile = input.nextLine();
                        displayBinarySearchComparisons(queryFile);
                    } else {
                        System.out.println("You need to load files first.");
                    }
                    break;
                case 5:
                    System.out.println("Word to search:");
                    String testWord = input.nextLine();
                    experimentAVLBinarySearch(testWord);
                    break;

                case 6:
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // Loads dictionary and query files
    private static void loadFiles(Scanner input) {
        System.out.print("Enter dictionary file path: ");
        String dictionaryPath = input.nextLine();
        System.out.print("Enter query file path: ");
        String queryPath = input.nextLine();

        // Build the AVL tree from the dictionary file
        createAVLTrees(dictionaryPath);

        // Perform AVL and binary search experiments
        experimentAVLSearch(queryPath);
        experimentBinarySearch(dictionaryPath, queryPath);
    }

    // Builds an AVL tree from dictionary words
    private static void createAVLTrees(String filename) {
        try (Scanner in = new Scanner(new File(filename))) {
            while (in.hasNext()) {
                String data = in.nextLine().toLowerCase();
                avl.insert(data);           // insert into AVL tree
                dictionary.add(data);       // insert into built-in TreeSet (acts like a binary search tree)
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found: " + filename);
        }
    }
    //just to compare the searches
    private static void experimentAVLBinarySearch(String word)
    {
      int AVLcomparisons = avl.search(word);
      int Binarycomparisons = binarySearch(dictionary.toArray(new String[0]),word);
     
      if (AVLcomparisons > Binarycomparisons)
      {
         System.out.println("AVL Search had the shortest amount of comparisons (" + AVLcomparisons +") for the word: " + word );
       }
       else if (AVLcomparisons < Binarycomparisons)
       {
         System.out.println("Binary Search had the shortest amount of comparisons (" + Binarycomparisons +") for the word: " + word );

       }
       else {System.out.println("Both searches had the shortest amount of comparisons (" + AVLcomparisons +") for the word: " + word );
}
    }

    // Performs AVL search for each query and logs number of comparisons
    private static void experimentAVLSearch(String queryPath) {
        try (Scanner in = new Scanner(new File(queryPath))) {
            System.out.println("\n[AVL Tree Search]");
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                int comparisons = avl.search(word);
                System.out.println(word + ": " + comparisons + " comparisons");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Query file not found: " + queryPath);
        }
    }

    // Performs binary search on sorted array for each query and logs comparisons
    private static void experimentBinarySearch(String dictionaryPath, String queryPath) {
        String[] sortedArray = dictionary.toArray(new String[0]);

        try (Scanner in = new Scanner(new File(queryPath))) {
            System.out.println("\n[Binary Search]");
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                int comparisons = binarySearch(sortedArray, word);
                System.out.println(word + ": " + comparisons + " comparisons");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Query file not found: " + queryPath);
        }
    }

    // Binary search method that also counts comparisons
    private static int binarySearch(String[] array, String target) {
        int comparisons = 0;
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;
            int cmp = target.compareTo(array[mid]);

            if (cmp == 0)
                return comparisons;
            else if (cmp < 0)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return comparisons;
    }

    // Displays comparison counts for all words from the query file
    private static void displayBinarySearchComparisons(String filename) {
        String[] sortedArray = dictionary.toArray(new String[0]);

        try (Scanner in = new Scanner(new File(filename));
         PrintWriter out = new PrintWriter("Comparisons.txt");
         ) {
            System.out.println("\n[Binary Search Comparison Table]");
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                int comparisons = binarySearch(sortedArray, word);
                System.out.println("Word: " + word + " -> Comparisons: " + comparisons);
                out.println("Word: " + word + " -> Comparisons: " + comparisons);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Query file not found.");
        }
    }
}
