/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.list_view;

import Database.entity.Staff;
import Vyjimky.UnableToCloseDatabaseCorrectlyException;
import Vyjimky.UnableToObtainDataFromDatabaseException;
import controller.StaffManager;
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
public class StaffListView extends JFrame{
    private JButton newStaff, filterStaff, editStaff, deleteStaff, home;
    private JPanel horniLista;
    private StaffManager manager;
    private JTable staffList;
    private ArrayList<Staff> staffs = new ArrayList<>();
    

    public StaffListView(StaffManager manager, ArrayList<Staff> staffs) {
        this.staffs=staffs;
        this.manager=manager;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Zamestnanci");
        newStaff=new JButton("Pridat zamestnance");
        newStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.newStaff();
                dispose();
            }
        });
        editStaff=new JButton("Upravit zamestnance");
        editStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=staffList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(new JFrame(), "Musi byt vybran prave jeden zamestnanec.", "", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Staff staff=staffs.get(rows[0]);
                    manager.editStaff(staff.getId(), staff.getPersonalId(), staff.getName(), staff.getLastname(), staff.getBirth() , staff.getContact());
                    dispose();
                }
            }
        });
        deleteStaff=new JButton("Smazat zamestnance");
        deleteStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int[] rows=staffList.getSelectedRows();
                if (rows.length>1 || rows.length==0){
                    JOptionPane.showMessageDialog(new JFrame(), "Musi byt vybran prave jeden zamestnanec.", "", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Staff staff=staffs.get(rows[0]);
                    try {
                        manager.deleteStaff(staff.getId());
                        dispose();
                    } catch (UnableToCloseDatabaseCorrectlyException | UnableToObtainDataFromDatabaseException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba pri komunikaci s databazi.", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        }});
        filterStaff=new JButton("Filtrovat zamestnance");
        filterStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                manager.filterStaff();
                dispose();
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
        horniLista.add(newStaff);
        horniLista.add(editStaff);
        horniLista.add(filterStaff);
        horniLista.add(deleteStaff);
        horniLista.add(home);
        
        StaffTableModel model = new StaffTableModel(staffs);
        staffList = new JTable(model);
        
        getContentPane().add(new JScrollPane(staffList), BorderLayout.CENTER);
        getContentPane().add(horniLista, BorderLayout.PAGE_START);
        pack();
        this.setLocationRelativeTo(null);
    }
    
    public class StaffTableModel extends AbstractTableModel {

        private ArrayList<Staff> list;

        public StaffTableModel(ArrayList<Staff> list) {
            this.list = new ArrayList<>(list);
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public String getColumnName(int column) {
            String name = "??";
            switch (column) {
                case 0:
                    name = "Osobní číslo";
                    break;
                case 1:
                    name = "Jméno";
                    break;
                case 2:
                    name = "Příjmení";
                    break;
                case 3:
                    name = "Datum narození";
                    break;
                case 4:
                    name = "Kontakt";
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
            Staff staff = list.get(rowIndex);
            Object value = null;
            switch (columnIndex) {
                case 0:
                    value = staff.getPersonalId();
                    break;
                case 1:
                    value = staff.getName();
                    break;
                case 2:
                    value = staff.getLastname();
                    break;
                case 3:
                    value = staff.getBirth();
                    break;
                case 4:
                    value = staff.getContact();
                    break;
            }
            return value;
        }            
    }  
}
