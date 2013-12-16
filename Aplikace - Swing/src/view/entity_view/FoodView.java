/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.entity_view;

import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.FoodManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lenka
 */
public class FoodView extends JFrame {

    private JTextField nameField, quantityField, minimumField;
    private JButton add, storno;
    private JLabel name, quantity, minimum;
    private FoodManager manager;
    private int status, ID;

    public FoodView(FoodManager manager) {
        status = 0;
        this.manager = manager;
        initComponents();
    }

    public FoodView(FoodManager manager, int ID, String name, String quantity, String minimum) {
        status = 1;
        this.ID = ID;
        this.manager = manager;
        initComponents();
        nameField.setText(name);
        quantityField.setText(quantity);
        minimumField.setText(minimum);
    }

    public FoodView(FoodManager manager, String name, String quantity, String minimum) {
        status = 2;
        this.manager = manager;
        initComponents();
        nameField.setText(name);
        quantityField.setText(quantity);
        minimumField.setText(minimum);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameField = new JTextField();
        quantityField = new JTextField();
        minimumField = new JTextField();

        name = new JLabel("Jmeno");
        quantity = new JLabel("Mnozstvi");
        minimum = new JLabel("Minimalni mnozstvi");

        add = new JButton("Pridat");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!quantityField.getText().equals("")){
                        Integer.parseInt(quantityField.getText());}
                    if (!minimumField.getText().equals("")){
                        Integer.parseInt(minimumField.getText());
                    }
                    if (status == 0) {
                        try {
                            manager.add(nameField.getText(), quantityField.getText(), minimumField.getText());
                        } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (status == 1) {
                            try {
                                manager.update(ID, nameField.getText(), quantityField.getText(), minimumField.getText());
                            } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
                                JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            try {
                                manager.filter(nameField.getText(), quantityField.getText(), minimumField.getText());
                            } catch (UnableToObtainDataFromDatabaseException ex) {
                                JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mnozstvi a minimalni mnozstvi musi byt cisla");
                }
            }
        });
        storno = new JButton("Zrusit");
        storno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.storno();
                } catch (UnableToObtainDataFromDatabaseException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        getContentPane().setLayout(new GridLayout(4, 2));
        getContentPane().add(nameField);
        getContentPane().add(name);
        getContentPane().add(quantityField);
        getContentPane().add(quantity);
        getContentPane().add(minimumField);
        getContentPane().add(minimum);
        getContentPane().add(storno);
        getContentPane().add(add);
        this.setLocationRelativeTo(null);
        pack();
    }
}
