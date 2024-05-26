import java.util.LinkedList;
import java.util.Queue;

/**
 * A class representing a Binary Search Tree (BST).
 */
public class BinarySearchTree {
    private Node root;

    /**
     * A private inner class representing a node in the BST.
     */
    private class Node {
        private int value;
        private Node left;
        private Node right;

        /**
         * Constructs a new node with the given value.
         *
         * @param value the value of the node
         */
        private Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Checks if the tree contains a node with the given value.
     *
     * @param value the value to search for
     * @return true if the tree contains the value, false otherwise
     */
    public boolean contains(int value) {
        return contains(root, value);
    }

    /**
     * overLoaded contains method to allow passing in Node
     * @param node is the root Node of this tree
     * @param value is the value to be searched in the Binary Tree
     * @return true if the tree contains the value, false otherwise
     */
    private boolean contains(Node node, int value) {
        if (node == null)
            return false;
        if (value == node.value)
            return true;
        return (value < node.value) ? contains(node.left, value) : contains(node.right, value);
    }

    /**
     * Adds a node with the given value to the tree.
     *
     * @param value the value to add
     */
    public void add(int value) {
        root = add(root, value);
    }

    /**
     * An overloaded add method to accept multiple addition by passing an array of ints
     * @param intArray is the array containing integers to be added
     */
    public void add(int[] intArray) {
        for (int num : intArray) {
            this.add(num);
        }
    }

    /**
     * Overloaded method to Add a node with the given value to the tree
     * @param node is the root Node of the tree
     * @param value is the vale to add
     * @return the root Node containing the value
     */
    private Node add(Node node, int value) {
        if (node == null)
            return new Node(value);
        if (value < node.value)
            node.left = add(node.left, value);
        else
            node.right = add(node.right, value);
        return node;
    }

    /**
     * Returns the preorder traversal of the tree as a string.
     *
     * @return the preorder traversal of the tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString().trim();
    }

    /**
     * Returns a String representation after traversal from the Node
     * @param node is the root Node of a binary tree
     * @param sb is a StringBuilder storing the string
     */
    private void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.value).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    /**
     * Returns the breadth-first traversal of the tree as a string.
     *
     * @return the breadth-first traversal of the tree
     */
    public String bfs() {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            sb.append(node.value).append(" ");
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }

        return sb.toString().trim();
    }

    /**
     * a helper method to reset the tree for testing purposes
     * (it cannot be private since it is being utilized by the JUnit test class)
     */
    public void clear(){
        root = null;
    }


    /**
     * Deletes a node with the given value from the tree.
     *
     * @param value the value to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean delete(int value) {
        return delete(root, value) != null;
    }

    private Node delete(Node node, int value) {
        if (node == null)
            return null;

        if (value < node.value)
            node.left = delete(node.left, value);
        else if (value > node.value)
            node.right = delete(node.right, value);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                node.value = minValue(node.right);
                node.right = delete(node.right, node.value);
            }
        }
        return node;
    }

    private int minValue(Node node) {
        int min = node.value;
        while (node.left != null) {
            min = node.left.value;
            node = node.left;
        }
        return min;
    }

    /**
     * Returns the height of the tree.
     *
     * @return the height of the tree
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Checks if there exists a path in the tree from root to leaf such that it adds up to the given sum.
     *
     * @param sum the sum to check for
     * @return true if such a path exists, false otherwise
     */
    public boolean hasSum(int sum) {
        return hasSum(root, sum);
    }

    /**
     * A method to recursively traverse the tree paths and find the sum
     * @param node is the root
     * @param sum is the target sum
     * @return true is there exists a path which contains the path
     */
    private boolean hasSum(Node node, int sum) {
        if (node == null) // case empty tree
            return false;
        if (node.left == null && node.right == null && sum == node.value ) // base cas: a leaf has been reached
            return true;
        return hasSum(node.left, sum - node.value) || hasSum(node.right, sum - node.value);
    }

    /**
     * Checks if the given array represents a symmetric binary tree.
     *
     * @param tree the array representing the binary tree
     * @return true if the tree is symmetric, false otherwise
     */

    public static boolean isSymmetrical(int[] tree){
        StringBuilder sb;
        if(tree.length == 0 || tree.length == 1)
            return true;

        if( tree.length - 1 != 2 && Math.sqrt(tree.length - 1) % 2 !=0 )
            return false;

        for(int level = 0; level < Math.sqrt(tree.length - 1); level++){
            System.out.println(Math.pow(2, level));
            sb = new StringBuilder();
            for(int itemCount = (int) Math.pow(2, level); itemCount < level + Math.pow(2, level); itemCount++){
                sb.append(tree[itemCount]);
            }
            if (  !isPalindrome(sb.toString()) ) return false; //if a given level doesn't form a palindrome when doing BFS then the tree won't be symmetrical
        }

        return true; // if the loops terminates then it is symmetrical
    }

    private static boolean isPalindrome(String str){
        System.out.println(str);
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            char leftChar = str.charAt(left);
            char rightChar = str.charAt(right);

            // Compare characters
            if (leftChar != rightChar) {
                return false; // Characters don't match, not a palindrome
            }
            // Move pointers
            left++;
            right--;

        }
        return true; // If the loop completes, the string is a palindrome

    }


}





