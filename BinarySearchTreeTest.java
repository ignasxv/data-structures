import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {

    @Test
    void testAddAndContains() {
        BinarySearchTree bst = new BinarySearchTree();
        assertFalse(bst.contains(10)); // Tree is empty

        bst.add(50);
        bst.add(30);
        bst.add(0); //adding zero
        bst.add(-99); //adding negatives
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);
        assertTrue(bst.contains(0));
        assertTrue(bst.contains(-99));
        assertTrue(bst.contains(50)); // Value exists in the tree
        assertTrue(bst.contains(30)); // Value exists in the tree
        assertTrue(bst.contains(80)); // Value exists in the tree
        assertFalse(bst.contains(100)); // Value does not exist in the tree  

    }

    @Test
    void testToString() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(new int[]{});
        assertEquals("", bst.toString(), "An empty tree");
        bst.clear();

        bst.add(new int[]{5});
        assertEquals("5", bst.toString(), "A tree with a single node");
        bst.clear();

        bst.add(new int[]{5, 3});
        assertEquals("5 3", bst.toString(), "A tree with a two nodes");
        bst.clear();

        bst.add(new int[]{1, 2, 3, 4, 5});
        assertEquals("1 2 3 4 5", bst.toString(), "A tree with nodes in ascending order");
        bst.clear();

        bst.add(new int[]{5, 4, 3, 2, 1});
        assertEquals("5 4 3 2 1", bst.toString(), "A tree with nodes in descending order");
        bst.clear();

        bst.add(new int[]{4, 2, 6, 1, 3, 5, 7});
        assertEquals("4 2 1 3 6 5 7", bst.toString(), "A tree with nodes in random order");
        bst.clear();

        bst.add(new int[]{-5, -3, -7, -2, -4});
        assertEquals("-5 -7 -3 -4 -2", bst.toString(), "A tree with nodes containing negative integers");
        bst.clear();

        bst.add(new int[]{2, 1, 3, 2, 3, 1});
        assertEquals("2 1 1 3 2 3", bst.toString(), "A tree with duplicate values");
        bst.clear();
    }

    @Test
    void testBfs() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(new int[]{});
        assertEquals("", bst.bfs(), "An empty tree");
        bst.clear();

        bst.add(new int[]{5});
        assertEquals("5", bst.bfs(), "A tree with a single node");
        bst.clear();

        bst.add(new int[]{5, 3});
        assertEquals("5 3", bst.bfs(), "A tree with two nodes");
        bst.clear();

        bst.add(new int[]{1, 2, 3, 4, 5});
        assertEquals("1 2 3 4 5", bst.bfs(), "A tree with nodes in ascending order");
        bst.clear();

        bst.add(new int[]{5, 4, 3, 2, 1});
        assertEquals("5 4 3 2 1", bst.bfs(), "A tree with nodes in descending order");
        bst.clear();

        bst.add(new int[]{4, 2, 6, 1, 3, 5, 7});
        assertEquals("4 2 6 1 3 5 7", bst.bfs(), "A tree with nodes in random order");
        bst.clear();

        bst.add(new int[]{-5, -3, -7, -2, -4});
        assertEquals("-5 -7 -3 -4 -2", bst.bfs(), "A tree with nodes containing negative integers");
        bst.clear();

        bst.add(new int[]{2, 1, 1, 3, 2, 3, 1});
        assertEquals("2 1 3 1 2 3 1", bst.bfs(), "A tree with duplicate values");
        bst.clear();
    }

    @Test
    void testDelete() {
        BinarySearchTree bst = new BinarySearchTree();
        assertFalse(bst.delete(10)); // Tree is empty, cannot delete

        // Insert nodes into the tree
        bst.add(new int[]{5, 3, 8, 2, 4, 7, 9, 1, 6});

        bst.delete(10);
        assertEquals("5 3 2 1 4 8 7 6 9", bst.toString(), "Deleting a node not present in the tree");

        bst.delete(1);
        assertEquals("5 3 2 4 8 7 6 9", bst.toString(), "Deleting a leaf node");

        bst.delete(8);
        assertEquals("5 3 2 4 9 7 6", bst.toString(), "Deleting a node with one child");

        bst.delete(5);
        assertEquals("6 3 2 4 9 7", bst.toString(), "Deleting a node with two children");

        bst.delete(6);
        assertEquals("7 3 2 4 9", bst.toString(), "Deleting the root node");

        // Delete remaining nodes
        bst.clear();
        assertEquals("", bst.toString(), "Deleting remaining nodes");
    }

    @Test
    void testHeight() {
        BinarySearchTree bst = new BinarySearchTree();
        assertEquals(0, bst.height(), "Height of an empty tree");

        bst.add(new int[]{5});
        assertEquals(1, bst.height(), "Height of a tree with a single node");

        bst.add(new int[]{3, 8});
        assertEquals(2, bst.height(), "Height of a tree with two levels");

        bst.add(new int[]{2, 4, 7, 9});
        assertEquals(3, bst.height(), "Height of a tree with three levels");

        bst.add(new int[]{1, 6});
        assertEquals(4, bst.height(), "Height of a tree with four levels");
    }

    @Test
    void testHasSum() {
        BinarySearchTree bst = new BinarySearchTree();
        assertFalse(bst.hasSum(10)); // Tree is empty, sum does not exist

        bst.add(5);
        assertTrue(bst.hasSum(5)); // Tree has only one node, sum equals the node value

        bst.add(new int[] {-9});
        assertTrue(bst.hasSum(-4), "involving negative sums");
        bst.clear();

        bst.add(new int[] {3, 8, 1, 4, 7, 10});
        assertFalse(bst.hasSum(15)); // Sum exists in the tree
        assertTrue(bst.hasSum(22)); // Sum exists
        assertTrue(bst.hasSum(4)); //sum exists
    }

    @Test
    void testIsSymmetrical() {

        assertTrue(BinarySearchTree.isSymmetrical( new int[] {} )); //an empty tree

        assertTrue(BinarySearchTree.isSymmetrical( new int[] {8} )); //tree containing a single element

        assertFalse(BinarySearchTree.isSymmetrical( new int[] {4, 2} )); //A non-full tree

        assertTrue(BinarySearchTree.isSymmetrical( new int[] {8, 2, 2} )); //A symmetrical with 2 levels


    }
}
