package _06_How_Many_Are_Smaller_Than_Us;

import java.util.Arrays;
import java.util.Random;

import _05_Intro_to_AVL_Trees.AVLTree;

public class HowManyAreSmallerThanUsTest {

    private int[] nums;
    private Random rand;
    
    public HowManyAreSmallerThanUsTest() {
        nums = new int[10];
        rand = new Random();
    }

    public static void main(String[] args) {
        HowManyAreSmallerThanUsTest hmu =  new HowManyAreSmallerThanUsTest();
        hmu.runTest();
    }

    public void runTest() {
        
        AVLTree<Integer> av = new AVLTree<>();
        HowManyAreSmallerThanUs hm = new HowManyAreSmallerThanUs();
        
        for (int i = 0; i < nums.length; i++) {
            int num = getUniqueRandomNum();   
            av.insert(num);
            nums[i] = num;
        }
        
        av.print();
        System.out.println(Arrays.toString(nums));
        int[] results = hm.howManyAreSmallerThanUs(nums, av);
        System.out.println(Arrays.toString(results));
        
    }

    private int getUniqueRandomNum() {
        
        boolean unique = false;
        int num = 0;
        
        while(!unique) {
            
            num = rand.nextInt(20) + 1;
            
            for (int i = 0; i < nums.length; i++) {
                if (num == nums[i]) {
                     break; 
                }
                
                if(i == nums.length-1) {
                    unique = true;
                }
            }
            
            
        }
        
        return num;
        
        
    }

}
