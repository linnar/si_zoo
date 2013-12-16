/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.entity_view;

import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.AnimalManager;
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
public class AnimalView extends JFrame{

    private JTextField chipNumberField, nameField, dateOfBirthField, specieField;
    private JButton add, storno;
    private JLabel chipNumber, name, dateOfBirth, specie;
    private AnimalManager manager;
    private int status, ID; 
    
    public AnimalView(AnimalManager manager) {
        status=0;
        this.manager=manager;
        initComponents();
    }
    
    public AnimalView(AnimalManager manager,  int ID, String chipNumber,String name,String dateOfBirth,String specie) {
        status=1;
        this.ID=ID;
        this.manager=manager;
        initComponents();
        chipNumberField.setText(chipNumber);
        nameField.setText(name);
        dateOfBirthField.setText(dateOfBirth);
        specieField.setText(specie);
    }
    
    public AnimalView(AnimalManager manager,  String chipNumber,String name,String dateOfBirth,String specie) {
        status=2;
        this.manager=manager;
        initComponents();
        chipNumberField.setText(chipNumber);
        nameField.setText(name);
        dateOfBirthField.setText(dateOfBirth);
        specieField.setText(specie);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        chipNumberField=new JTextField();
        nameField=new JTextField();
        dateOfBirthField=new JTextField();
        specieField=new JTextField();
        
        chipNumber=new JLabel("Cislo cipu");
        name=new JLabel("Jmeno");
        dateOfBirth=new JLabel("Datum narozeni");
        specie=new JLabel("Druh");
        
        add=new JButton("Pridat");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status==0){
                    try {
                        manager.add(chipNumberField.getText(), nameField.getText(), dateOfBirthField.getText(), specieField.getText());
                    } catch (UnableToCloseDatabaseCorrectlyException |UnableToObtainDataFromDatabaseException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    if(status==1){
                        try {
                            manager.update(ID,chipNumberField.getText(), nameField.getText(),  dateOfBirthField.getText(), specieField.getText());
                        } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        try {
                            manager.filter(chipNumberField.getText(), nameField.getText(), dateOfBirthField.getText(), specieField.getText());
                        } catch (UnableToObtainDataFromDatabaseException ex) {
                            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                dispose();
            }

        });
        storno=new JButton("Zrusit");
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
                
        getContentPane().setLayout(new GridLayout(5,2));
        getContentPane().add(chipNumberField);
        getContentPane().add(chipNumber);
        getContentPane().add(nameField);
        getContentPane().add(name);
        getContentPane().add(dateOfBirthField);
        getContentPane().add(dateOfBirth);
        getContentPane().add(specieField);
        getContentPane().add(specie);
        getContentPane().add(storno);
        getContentPane().add(add);
        this.setLocationRelativeTo(null);
        pack();
    }
}
