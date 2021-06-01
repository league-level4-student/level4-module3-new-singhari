package _03_Intro_to_Binary_Trees;

import _04_Morse_Code.MorseCode;
import _05_Intro_to_AVL_Trees.AVLNode;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;
    private boolean morseCode = false;

    static final int SPACING = 5;

    /*
     * Each of these methods are implemented by calling a recursive method.
     * 
     * Note: The morseCode part will be used in the next recipe. This flag is
     * set to perform unique operations when comparing values in morseCode.
     */
    public void insert(T value) {
        root = recursiveInsert(root, value);

        if (morseCode) {
            ((MorseCode) value).setPosition(0);
        }
    }

    public Node<T> search(T value) {

        Node<T> nodeQuery = recursiveSearch(root, value);

        if (nodeQuery == null) {

            System.out.println("NOT FOUND. RETURN VALUE IS NULL");
            return null;

        } else {

            if (morseCode) {
                System.out.println("FOUND VALUE: "
                        + ((MorseCode) nodeQuery.getValue()).getDecoded());
                ((MorseCode) value).setPosition(0);
            } else {
                System.out.println("FOUND VALUE: " + nodeQuery.getValue());
            }
            return nodeQuery;
        }

    }

    public void delete(T value) {
        root = recursiveDelete(root, value);

        if (morseCode) {
            ((MorseCode) value).setPosition(0);
        }
    }

    public void print() {
        recursivePrint(root, 0);
    }

    public Node<T> getRoot() {
        return this.root;
    }

    /*
     * This method searches the references until it finds a null node at the
     * right spot to place the new value.
     * 
     * As described in the demo it will go down the left branch if the value
     * being inserted is smaller or down a right branch if the value being
     * inserted is larger.
     * 
     * No duplicate values are allowed.
     */
    private Node<T> recursiveInsert(Node<T> current, T value) {

        if (current == null) {
            return new Node<T>(value);
        }

        if (value.compareTo(current.getValue()) < 0) {
            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }
            current.setLeft(recursiveInsert(current.getLeft(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }
            current.setRight(recursiveInsert(current.getRight(), value));
        } else {
            return current;
        }

        return current;
    }

    /*
     * This method progresses through the tree in a similar manner to insert,
     * but stops whenever it finds a null meaning the data does not exist, or
     * finds the data it is looking for. The data it returns take the form of a
     * Node.
     */
    protected Node<T> recursiveSearch(Node<T> current, T value) {

        if (current == null) {
            return null;
        } else if (value.compareTo(current.getValue()) == 0) {
            return current;
        } else if (value.compareTo(current.getValue()) < 0) {
            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }
            return recursiveSearch(current.getLeft(), value);
        } else {

            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }
            return recursiveSearch(current.getRight(), value);
        }

    }

    /*
     * Deletion is a little bit more complicated.
     * 
     * The data is still searched through in the usual manner, but if a parent
     * node(a node that contains references to other nodes) is deleted then the
     * references must be updated and the best candidate from the child nodes is
     * selected to be the new parent.
     */

    protected Node<T> recursiveDelete(Node<T> current, T value) {
        if (current == null)
            return current;

        if (value.compareTo(current.getValue()) < 0) {
            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }
            current.setLeft(recursiveDelete(current.getLeft(), value));
        } else if (value.compareTo(current.getValue()) > 0) {
            if (morseCode) {
                MorseCode mcValue = (MorseCode) value;
                mcValue.setPosition(mcValue.getPosition() + 1);
            }

            current.setRight(recursiveDelete(current.getRight(), value));
        } else {

            if ((current.getLeft() == null) || (current.getRight() == null)) {
                Node<T> temp = null;
                if (temp == current.getLeft())
                    temp = current.getRight();
                else
                    temp = current.getLeft();

                if (temp == null) {
                    temp = current;
                    current = null;
                } else
                    current = temp;
            } else {

                Node<T> temp = findSmallestNode(current.getRight());

                current.setValue(temp.getValue());

                current.setRight(
                        recursiveDelete(current.getRight(), temp.getValue()));
            }
        }

        return current;

    }

    /*
     * This method prints out the BinaryTree starting with the root at the far
     * left on the console and the bottom leaves at the far right of the
     * console. 
     * 
     * Each Node at the same level should be even with each other.
     */

    protected void recursivePrint(Node<T> current, int space) {
        if (current == null)
            return;

        space += SPACING;

        recursivePrint(current.getRight(), space);

        System.out.print("\n");
        for (int i = SPACING; i < space; i++)
            System.out.print(" ");

        if (morseCode)
            System.out.print(
                    ((((MorseCode) current.getValue()).getDecoded()) + "\n"));
        else
            System.out.print(current.getValue() + "\n");

        recursivePrint(current.getLeft(), space);
    }

    protected Node<T> findSmallestNode(Node<T> current) {

        if (current.getLeft() == null) {
            return current;
        } else {
            return findSmallestNode(current.getLeft());
        }

    }

    public void setMorseCode(boolean morseCode) {
        this.morseCode = morseCode;
    }

}