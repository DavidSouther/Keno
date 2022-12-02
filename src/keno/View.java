/*
 * View.java
 *
 * Created on May 26, 2007, 5:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author David Souther
 */
public class View extends JPanel{
    
    private JFrame frame;
    
    private JPanel kenoBoard;
    private JButton playButton;
    private StatsPanel stats;
    private MiddlePanel midPanel;
    private KenoButton[] buttons;
    
    private KenoNumber numbers;
    private PayTable table;
    
    private int numSelected = 0,
                numHit = 0,
                numBonus = 0,
                payout = 0,
                plays = 0,
                credits = 0,
                bet = 0;
    private java.util.ArrayList<Integer> choices;
    private boolean played = false, locked = false;
    
    private PlayThread player;
    
    /** Creates a new instance of View */
    public View() {
        this.setLayout(new java.awt.BorderLayout());
        
        choices = new java.util.ArrayList<Integer>(10);
        
        kenoBoard = new JPanel();
        //kenoBoard.setLayout(new java.awt.GridLayout(3, 1));
        kenoBoard.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        kenoBoard.setPreferredSize(new java.awt.Dimension(650, 600));
        buttons = new KenoButton[80];
        
        JPanel boardPanel1 = new JPanel();
        boardPanel1.setLayout(new java.awt.GridLayout(4, 10));
        boardPanel1.setPreferredSize(new java.awt.Dimension(650, 260));
        for(int i=0; i<40; i++){
            buttons[i] = new KenoButton(i, this);
            boardPanel1.add(buttons[i]);
        }
        
        JPanel boardPanel2 = new JPanel();
        boardPanel2.setPreferredSize(new java.awt.Dimension(650, 260));
        boardPanel2.setLayout(new java.awt.GridLayout(4, 10));
        for(int i=40; i<80; i++){
            buttons[i] = new KenoButton(i, this);
            boardPanel2.add(buttons[i]);
        }
       
        midPanel = new MiddlePanel(this);
        
        boardPanel1.setBackground(java.awt.Color.BLUE);
        midPanel.setBackground(java.awt.Color.GREEN);
        boardPanel2.setBackground(java.awt.Color.RED);
        
        kenoBoard.add(boardPanel1);
        kenoBoard.add(midPanel);
        kenoBoard.add(boardPanel2);
        
        this.add(kenoBoard, java.awt.BorderLayout.CENTER);
        
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                play();
            }
        });
        
        this.add(playButton, java.awt.BorderLayout.PAGE_END);
        
        stats = new StatsPanel(this);
        this.add(stats, java.awt.BorderLayout.LINE_END);
        
        addCredits(100);
        
        numbers = new KenoNumber(this);
        table = new PayTable(this);
        
        System.out.println("M\tH\tB\tP\tG");
    }
    
    public void addCredits(int n){
        if(locked) return;
        credits += n;
        stats.setCredits(credits);
    }
    
    public boolean select(int n){
        if(locked) return false;
        if(played == true)
            reset();
        if(numSelected >= 10){
            return false;
        } else {
            choices.add(n);
            midPanel.setMarked(++numSelected);
            return true;
        }
    }
    
    public boolean deselect(int n){
        if(locked) return false;
        if(played==true)
            reset();
        if(choices.remove((Integer)n))
            numSelected--;
        midPanel.setMarked(numSelected);
        return true;
    }
    
    public void play(){
        if(locked) return;
        if(played == true)
            reset();
        bet = stats.getBet();
        if(bet<=0)return;
        if((credits - bet) <0) return;
        
        played = true;
        
        credits -= bet;
        stats.setCredits(credits);
        stats.setPayout(0);
        
        int[] bonus = getNums();
        for(int i=0; i<5; i++){
            while(buttons[bonus[i]].isSelected()){
                bonus[i]++;
                if(bonus[i]>79)bonus[i]=0;
            }
        }
        int[] nums = numbers.getNums();
       
        PlayThread p = new PlayThread(this, bonus, nums, stats.getSpeed());
        p.start();
    }
    
    public void done(){
        payout = table.doPayout(numHit, bet, numSelected);
        if(numBonus > 1)
            payout *= (numBonus - 1) * 2;
        
        credits += payout;
        
        plays++;
        
        stats.setPayout(payout);
        stats.setGames(plays);
        stats.setCredits(credits);
        
        System.out.printf("%d\t%d\t%d\t%d\t%d\n", (Integer) numSelected,(Integer) numHit,(Integer) numBonus,(Integer)payout, (Integer) plays);
        
//        if(numHit >= (numSelected * .8) || payout >= 200)/
//            JOptionPane.showMessageDialog(this.getParent(), "Holy shit! You hit!\nYou won " 
//                    + payout + " on " + numHit + " hits with " + numBonus + 
//                    " bonus squares.");
    }
    
    public void setBonus(int n){
        this.buttons[n].bonus();
    }
    
    public void hit(int n){
        buttons[n].hit();
        if(buttons[n].isSelected()){
            numHit++;
            midPanel.setHit(numHit);
        }
        if(buttons[n].isBonus()){
            numBonus++;
            stats.setBonus(numBonus);
        }
    }
    
    public void reset(){
        if(locked) return;
        for(int i=0; i<80; i++)
            buttons[i].reset();
       
        played = false;
        numHit = 0;
        numBonus = 0;
        midPanel.setHit(numHit);
        stats.setBonus(numBonus);
        stats.setPayout(0);
    }
    
    public void random(){
        if(locked) return;
        clear();
        int[] nums = getNums();
        for(int i=0; i<8; i++)
            buttons[nums[i]].select();
    }
    
    public void clear(){
        if(locked) return;
        for(int i=0; i<80; i++)
            if(buttons[i].isSelected())
                buttons[i].deselect();
    }
    
    public void lock(){
        locked = !locked;
    }
    
    
    private int[] getNums(){
        java.util.Random rand = new java.util.Random();
        int[] nums = new int[80];
        for(int i=0; i<80; i++)nums[i] = i;
        for(int j=0; j<5; j++)
            for(int i=0; i<80; i++){
                int r = rand.nextInt(80);
                int t = nums[i];
                nums[i] = nums[r];
                nums[r] = t;
            };
        return nums;
    }
}
