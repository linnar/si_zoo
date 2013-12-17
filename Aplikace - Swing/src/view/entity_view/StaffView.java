/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.entity_view;

import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.StaffManager;
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
public class StaffView extends JFrame {

    private JTextField personalNumberField, nameField, lastNameField, dateOfBirthField, contactField;
    private JButton add, storno;
    private JLabel personalNumber, name, lastName, dateOfBirth, contact;
    private StaffManager manager;
    private int status, ID;

    public StaffView(StaffManager manager) {
        status = 0;
        this.manager = manager;
        initComponents();
    }

    public StaffView(StaffManager manager, int ID, String personalNumber, String name, String lastName, String dateOfBirth, String contact) {
        status = 1;
        this.ID = ID;
        this.manager = manager;
        initComponents();
        personalNumberField.setText(personalNumber);
        nameField.setText(name);
        lastNameField.setText(lastName);
        dateOfBirthField.setText(dateOfBirth);
        contactField.setText(contact);
    }

    public StaffView(StaffManager manager, String personalNumber, String name, String lastName, String dateOfBirth, String contact) {
        status = 2;
        this.manager = manager;
        initComponents();
        personalNumberField.setText(personalNumber);
        nameField.setText(name);
        lastNameField.setText(lastName);
        dateOfBirthField.setText(dateOfBirth);
        contactField.setText(contact);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        personalNumberField = new JTextField();
        nameField = new JTextField();
        lastNameField = new JTextField();
        dateOfBirthField = new JTextField();
        contactField = new JTextField();

        personalNumber = new JLabel("Osobni cislo");
        name = new JLabel("Jmeno");
        lastName = new JLabel("Prijmeni");
        dateOfBirth = new JLabel("Datum narozeni");
        contact = new JLabel("Kontakt");

        add = new JButton("Pridat");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status == 0) {
                    try {
                        manager.add(personalNumberField.getText(), nameField.getText(), lastNameField.getText(), dateOfBirthField.getText(), contactField.getText());
                    } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (status == 1) {
                        try {
                            manager.update(ID, personalNumberField.getText(), nameField.getText(), lastNameField.getText(), dateOfBirthField.getText(), contactField.getText());
                        } catch (UnableToObtainDataFromDatabaseException | UnableToCloseDatabaseCorrectlyException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            manager.filter(-1, personalNumberField.getText(), nameField.getText(), lastNameField.getText(), dateOfBirthField.getText(), contactField.getText());
                        } catch (UnableToObtainDataFromDatabaseException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                dispose();
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

        getContentPane().setLayout(new GridLayout(6, 2));
        getContentPane().add(personalNumberField);
        getContentPane().add(personalNumber);
        getContentPane().add(nameField);
        getContentPane().add(name);
        getContentPane().add(lastNameField);
        getContentPane().add(lastName);
        getContentPane().add(dateOfBirthField);
        getContentPane().add(dateOfBirth);
        getContentPane().add(contactField);
        getContentPane().add(contact);
        getContentPane().add(storno);
        getContentPane().add(add);

        pack();
        this.setLocationRelativeTo(null);
    }
}
