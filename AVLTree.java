// Moegamat Samsodien
// 19 March 2024
// An AVL Tree with insert and search functionality

public class AVLTree {
    private AVLNode root;
    private int insertCount = 0;
    private int searchCount = 0;

    public AVLTree() {}

    // Insert data into the AVL tree
    public void insert(String data) {
        String keyword = data.substring(0, data.indexOf("\t"));
        String sentence = data.substring(data.indexOf("\t") + 1, data.indexOf("\t", data.indexOf("\t") + 1));
        double score = Double.parseDouble(data.substring(data.lastIndexOf("\t") + 1));
        root = insertRecursive(root, keyword, sentence, score);
    }

    private AVLNode insertRecursive(AVLNode node, String keyword, String sentence, double score) {
        if (node == null) return new AVLNode(keyword, sentence, score);

        insertCount++;
        if (keyword.compareTo(node.getKeyword()) < 0)
            node.leftChild = insertRecursive(node.leftChild, keyword, sentence, score);
        else if (keyword.compareTo(node.getKeyword()) > 0)
            node.rightChild = insertRecursive(node.rightChild, keyword, sentence, score);

        return balance(node);
    }

    // Search for a term in the AVL tree and return the number of comparisons
    public int search(String keyword) {
        searchCount = 0;  // Reset searchCount for each new search
        AVLNode result = searchRecursive(root, keyword);
        return searchCount;  // Return number of comparisons
    }

    private AVLNode searchRecursive(AVLNode node, String keyword) {
        searchCount++;  // Count each comparison
        if (node == null || node.getKeyword().equals(keyword)) return node;

        if (keyword.compareTo(node.getKeyword()) < 0)
            return searchRecursive(node.leftChild, keyword);

        return searchRecursive(node.rightChild, keyword);
    }

    // AVL balancing utilities
    private int height(AVLNode node) {
        return (node != null) ? node.nodeHeight : -1;
    }

    private int balanceFactor(AVLNode node) {
        return height(node.rightChild) - height(node.leftChild);
    }

    private void fixHeight(AVLNode node) {
        node.nodeHeight = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.leftChild;
        y.leftChild = x.rightChild;
        x.rightChild = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private AVLNode balance(AVLNode node) {
        fixHeight(node);
        if (balanceFactor(node) == 2) {
            if (balanceFactor(node.rightChild) < 0)
                node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        if (balanceFactor(node) == -2) {
            if (balanceFactor(node.leftChild) > 0)
                node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }
        return node;
    }
}
