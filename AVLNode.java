// Node class for the use of educational purposes
// 19 March 2024
// Moegamat Samsodien 

public class AVLNode
{
   // Instance/global variables
   private String keyword,associatedSentence;
   private double relevanceScore;
   AVLNode leftChild, rightChild;
   int nodeHeight;
//Constructor
   public AVLNode(String keyword, String associatedSentence, double confidenceScore) 
   {
      this.keyword = keyword;
      this.associatedSentence = associatedSentence;
      this.relevanceScore = relevanceScore;
      nodeHeight = 0;
      leftChild = null; 
      rightChild = null;
   }
    // Getter methods for private variables
   public String getKeyword() { return keyword; }
   public String getAssociatedSentence() { return associatedSentence; }
   public double getRelevanceScore() { return relevanceScore; }

   @Override
   //To string 
   public String toString() 
   {
      return keyword + " " + associatedSentence + " (" + relevanceScore + ").";
   }
}