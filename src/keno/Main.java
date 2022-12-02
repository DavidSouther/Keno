/*
 * Main.java
 *
 * Created on May 26, 2007, 5:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keno;

/**
 *
 * @author David Souther
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.GraphicsDevice device = java.awt.GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // boolean fullScreen = device.isFullScreenSupported()
        // && device.isDisplayChangeSupported();
        boolean fullScreen = false;
        // java.awt.DisplayMode oldDisplayMode = device.getDisplayMode();
        // java.awt.DisplayMode newDisplayMode = new java.awt.DisplayMode(800, 600,
        // oldDisplayMode.getBitDepth(), oldDisplayMode.getRefreshRate());
        View v = new View();
        javax.swing.JFrame frame = new javax.swing.JFrame("Keno");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.add(v);
        // frame.setResizable(false);
        // frame.setUndecorated(fullScreen);

        if (fullScreen) {
            try {
                // device.setDisplayMode(newDisplayMode);
                device.setFullScreenWindow(frame);
                frame.validate();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            } /*
               * finally {
               * device.setDisplayMode(oldDisplayMode);
               * device.setFullScreenWindow(null);
               * }
               */
        } else {
            frame.pack();
            frame.setVisible(true);
        }

    }

}
