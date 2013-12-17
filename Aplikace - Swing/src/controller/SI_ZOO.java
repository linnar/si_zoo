/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.*;
import view.MainWindow;

/**
 *
 * @author Lenka
 */
public class SI_ZOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainWindow(new DBConnector()).setVisible(true);
    }
}
