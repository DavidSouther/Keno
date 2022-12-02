/*
 * PlayThread.java
 *
 * Created on May 31, 2007, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

/**
 *
 * @author David Souther
 */
public class PlayThread extends Thread{
    
    View control;
    long sleep;
    int[] bonus, nums;

    final int BASE_SPEED = 50;
    
    /** Creates a new instance of PlayThread */
    public PlayThread(View c, int[] bonus, int[] nums, int speed) {
        control = c;
        this.bonus = bonus;
        this.nums = nums;
        sleep = (long)(BASE_SPEED * (6-speed));
    }    
    
    public void run(){
        control.lock();
        for(int i=0; i<5; i++){
            control.setBonus(bonus[i]);
            dowait();
        }
        for(int i=0; i<20; i++){
            control.hit(nums[i]);
            dowait();
        }
        control.lock();
        control.done();
    }
    
    private void dowait(){
        try{
            this.sleep(sleep);
        } catch(Exception e){}
    }
    
}
