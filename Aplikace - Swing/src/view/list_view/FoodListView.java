/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.list_view;

import controller.FoodManager;
import Database.entity.Feed;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
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
public class FoodListView extends JFrame{
    private JButton newFood, filterFood, editFood, deleteFood, home;
    private JPanel horniLista;
    private FoodManager manager;
    private JTable foodList;
    private ArrayList<Feed> foods = new ArrayList<>();
    

    public FoodListView(FoodManager manager, ArrayList<Feed> foods) {
        this.foods=foods;
        this.manager=manager;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Krmivo");
        newFood=new JButton("Pridat krmivo");
        newFood.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.newFood();
                dispose();
            }
        });
        editFood=new JButton("Upravit krmivo");
        editFood.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=foodList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(null, "Musi byt vybrano prave jedno krmivo");
                }
                else{
                    Feed food=foods.get(rows[0]);
                    manager.editFood(food.getId(), food.getName(), food.getAmount(), food.getMinimum());
                    dispose();
                }
            }
        });
        filterFood=new JButton("Filtrovat krmivo");
        filterFood.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.filterFood();
                dispose();
            }
        });
        deleteFood=new JButton("Smazat krmivo");
        deleteFood.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=foodList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(null, "Musi byt vybrano prave jedno krmivo");
                }
                else{
                    Feed food=foods.get(rows[0]);
                    try {
                        manager.deleteFood(food);
                        dispose();
                    } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
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
        horniLista.add(editFood);
        horniLista.add(deleteFood);
        horniLista.add(newFood);
        horniLista.add(filterFood);
        horniLista.add(home);
        
        FoodListView.FoodTableModel model = new FoodListView.FoodTableModel(foods);
        foodList = new JTable(model);
        
        getContentPane().add(new JScrollPane(foodList), BorderLayout.CENTER);
        getContentPane().add(horniLista, BorderLayout.PAGE_START);
        pack();
        this.setLocationRelativeTo(null);
    }
    
    public class FoodTableModel extends AbstractTableModel {

        private ArrayList<Feed> list;

        public FoodTableModel(ArrayList<Feed> list) {
            this.list = new ArrayList<>(list);
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Název";
                    break;
                case 1:
                    name = "Množství";
                    break;
                case 2:
                    name = "Minimální množství";
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
            Feed food = list.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = food.getName();
                    break;
                case 1:
                    value = food.getAmount();
                    break;
                case 2:
                    value = food.getMinimum();
                    break;
            }
            return value;
        }            
    }  
}
