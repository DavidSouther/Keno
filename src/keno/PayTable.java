/*
 * PayTable.java
 *
 * Created on May 28, 2007, 11:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

/**
 *
 * @author David Souther
 */
public class PayTable {
    
    private View control;
    private int payout = 0;
    
    /** Creates a new instance of PayTable */
    public PayTable(View c) {
        control = c;
    }
    
    /*
     *  Simple payout table. Number hit^2 * bet.
     */
    public int doPayout(int numHit, int bet, int selected){
        int payout = 0;
        switch(selected){
            case 2:switch(numHit){
                case 2:payout=bet * 11;break;
            };break;
            case 3:switch(numHit){
                case 2:payout=bet * 2;break;
                case 3:payout=bet * 24;break;
            }break;
            case 4:switch(numHit){
                case 2:payout=bet * 1;break;
                case 3:payout=bet * 4;break;
                case 4:payout=bet * 65;break;
            }break;
            case 5:switch(numHit){
                case 3:payout=bet * 3;break;
                case 4:payout=bet * 16;break;
                case 5:payout=bet * 140;break;
            }break;
            case 6:switch(numHit){
                case 3:payout=bet * 2;break;
                case 4:payout=bet * 6;break;
                case 5:payout=bet * 25;break;
                case 6:payout=bet * 212;break;
            }break;
            case 7:switch(numHit){
                case 3:payout=bet * 1;break;
                case 4:payout=bet * 3;break;
                case 5:payout=bet * 11;break;
                case 6:payout=bet * 93;break;
                case 7:payout=bet * 400;break;
            }break;
            case 8:switch(numHit){
                case 4:payout=bet * 2;break;
                case 5:payout=bet * 10;break;
                case 6:payout=bet * 35;break;
                case 7:payout=bet * 260;break;
                case 8:payout=bet * 640;break;
            }break;
            case 9:switch(numHit){
                case 4:payout=bet * 2;break;
                case 5:payout=bet * 4;break;
                case 6:payout=bet * 10;break;
                case 7:payout=bet * 70;break;
                case 8:payout=bet * 412;break;
                case 9:payout=bet * 1200;break;
            }break;
            case 10:switch(numHit){
                case 4:payout=bet * 1;break;
                case 5:payout=bet * 3;break;
                case 6:payout=bet * 8;break;
                case 7:payout=bet * 10;break;
                case 8:payout=bet * 270;break;
                case 9:payout=bet * 580;break;
                case 10:payout=bet * 1200;break;
            }break;
        }
        return payout;
    }
}
