/*
 * KenoNumber.java
 *
 * Created on May 28, 2007, 11:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

/**
 *
 * @author David Souther
 */
public class KenoNumber {
    
    private java.util.Random rand;
    private View control;
    
    /** Creates a new instance of KenoNumber */
    public KenoNumber(View c) {
        control = c;
        rand = new java.util.Random();
    }
    
    /* 
     * Returns an int[] of 20 random numbers.
     *
     */
    
    public int[] getNums(){
        int[] nums = new int[80];
        for(int i=0; i<80; i++)nums[i] = i;
        for(int j=0; j<5; j++)
            for(int i=0; i<80; i++){
                int r = rand.nextInt(80);
                int t = nums[i];
                nums[i] = nums[r];
                nums[r] = t;
            }
        int[] ret = new int[20];
        for(int i=0; i<20; i++)
            ret[i] = nums[i];
        return ret;
    }
    
}
