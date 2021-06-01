package _03_Intro_to_Binary_Trees;

public class BinaryTreeDemo {
    
    public static void main(String[] args) {
        BinaryTree<Integer> ints = new BinaryTree<>();
        ints.insert(50);
        ints.insert(60);
        ints.insert(30);
        ints.insert(5);
        ints.insert(75);
        ints.delete(5);
        ints.print();
    }

}
