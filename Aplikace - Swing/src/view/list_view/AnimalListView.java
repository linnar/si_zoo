/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.list_view;

import Database.entity.Animal;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.AnimalManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenka
 */
public class AnimalListView extends JFrame{
    private JButton newAnimal, filterAnimal, editAnimal, deleteAnimal, home;
    private JPanel horniLista;
    private AnimalManager manager;
    private JTable animalList;
    private ArrayList<Animal> animals = new ArrayList<>();
    

    public AnimalListView(AnimalManager manager, ArrayList<Animal> animals) {
        this.animals=animals;
        this.manager=manager;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Zvirata");
        newAnimal=new JButton("Pridat zvire");
        newAnimal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.newAnimal();
                dispose();
            }
        });
        editAnimal=new JButton("Upravit zvire");
        editAnimal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=animalList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(null, "Musi byt vybrano prave jedno zvire");
                }
                else{
                    Animal animal=animals.get(rows[0]);
                    manager.editAnimal(animal.getId(), animal.getChip(), animal.getName(), animal.getBirth() , animal.getSpecie());
                    dispose();
                }
            }
        });
        filterAnimal=new JButton("Filtrovat zvirata");
        filterAnimal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.filterAnimal();
                dispose();
            }
        });
        deleteAnimal=new JButton("Smazat zvire");
        deleteAnimal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=animalList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(null, "Musi byt vybrano prave jedno zviíre");
                }
                else{
                    Animal animal=animals.get(rows[0]);
                    try {
                        manager.deleteAnimal(animal);
                        dispose();
                    } catch (UnableToObtainDataFromDatabaseException | UnableToCloseDatabaseCorrectlyException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                    } 
                }
            }
        });
        home=new JButton("Domu");
        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.home();
                dispose();
            }
        });
        
        
        getContentPane().setLayout(new BorderLayout());
        
        horniLista=new JPanel();
        horniLista.setLayout(new FlowLayout());
        horniLista.add(editAnimal);
        horniLista.add(deleteAnimal);
        horniLista.add(newAnimal);
        horniLista.add(filterAnimal);
        horniLista.add(home);
        
        AnimalListView.AnimalTableModel model = new AnimalListView.AnimalTableModel(animals);
        animalList = new JTable(model);
        
        getContentPane().add(new JScrollPane(animalList), BorderLayout.CENTER);
        getContentPane().add(horniLista, BorderLayout.PAGE_START);
        pack();
        this.setLocationRelativeTo(null);
    }
    
    public class AnimalTableModel extends AbstractTableModel {

        private ArrayList<Animal> list;

        public AnimalTableModel(ArrayList<Animal> list) {
            this.list = new ArrayList<>(list);
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Číslo čipu";
                    break;
                case 1:
                    name = "Jméno";
                    break;
                case 2:
                    name = "Datum narození";
                    break;
                case 3:
                    name = "Rasa";
                    break;
            }
            return name;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class type = String.class;
            switch (columnIndex) {
                case 0:
                case 1:
                    type = Integer.class;
                    break;
            }
            return type;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Animal animal = list.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = animal.getChip();
                    break;
                case 1:
                    value = animal.getName();
                    break;
                case 2:
                    value = animal.getBirth();
                    break;
                case 3:
                    value = animal.getSpecie();
                    break;
            }
            return value;
        }            
    }  
}
