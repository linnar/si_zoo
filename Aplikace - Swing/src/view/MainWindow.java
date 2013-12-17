/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Database.DBConnector;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Lenka
 */
public class MainWindow extends JFrame {
    
    private JButton staff, animal, food;
    private DBConnector connector;

    public MainWindow(DBConnector con) {
        this.connector = con;
        initComponents();
    }
    
    private void initComponents() {
        try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("ISYZ");
        staff=new JButton("Zamestnanci");
        this.staff.setBorder(new EmptyBorder(10,10,10,10));
        animal=new JButton("Zvirata");
        this.animal.setBorder(new EmptyBorder(3,3,3,3));
        food=new JButton("Krmivo");
        this.food.setBorder(new EmptyBorder(3,3,3,3));
        getContentPane().setLayout(new GridLayout(3,1,4,4));
        getContentPane().add(staff);
        staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StaffManager(connector);
                    dispose();
                } catch (UnableToObtainDataFromDatabaseException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Doslo k chybe pri navazovani spojeni s databazi.", "", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        animal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AnimalManager(connector);
                    dispose();
                } catch (UnableToObtainDataFromDatabaseException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Doslo k chybe pri navazovani spojeni s databazi.", "", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        food.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new FoodManager(connector);
                    dispose();
                } catch (UnableToObtainDataFromDatabaseException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        getContentPane().add(animal);
        getContentPane().add(food);
        pack();
        this.setSize(200, this.getHeight());
        this.setLocationRelativeTo(null);
    }
}
