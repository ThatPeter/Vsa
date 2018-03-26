/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3swingapp_2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class Cv3swingapp_2 {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PersonTable03Frame().setVisible(true);
            }
        });
    }
}

class PersonTable03Frame extends JFrame {

    private PersonTable03Model tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addButton;
    private JButton deleteButton;
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    private static String[] columnNames = {"First Name", "Last Name", "Birth Year", "Employed"};
    
    private static List<Person> testPersonsList() {
        em.clear();
        TypedQuery<Person> pq = em.createNamedQuery("Person.findAll", Person.class);
        List<Person> personsList = pq.getResultList();
        return personsList;
    }

    public PersonTable03Frame() {
        super("Person Table");
        
        emf = Persistence.createEntityManagerFactory("Cv3swingapp_2PU");
        em = emf.createEntityManager();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);

        tableModel = new PersonTable03Model(testPersonsList(),  columnNames);

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        addButton = new JButton("ADD");
        deleteButton = new JButton("DELETE");

        deleteButton.setEnabled(false);

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);


        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel selModel = table.getSelectionModel();
        selModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selRows = table.getSelectedRowCount();
                // Delete button is enabled only if there are 1 or more selected rows.
                deleteButton.setEnabled(selRows > 0);
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doAdd();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doDelete();
            }
        });
    }

    private void doAdd() {
        // Creates an empty Person object.
        Person person = new Person();
        tableModel.addPerson(person);
        persist(person);
    }

    private void doDelete() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Person p = tableModel.getPersonInRow(row);
            tableModel.deletePerson(p);
            remove(p);
        }
    }

    public void remove(Object object) {
        em.getTransaction().begin();
        try {
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }
    
    public void persist(Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }
    
    public void merge(Object object) {
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}

class PersonTable03Model extends AbstractTableModel {

    private String[] columnNames;

    // The internal structure of the model is an ArrayList<Person>.
    private List<Person> personsList;

    public PersonTable03Model(List<Person> personsList, String[] columnNames) {
        this.personsList = personsList;
        this.columnNames = columnNames;
    }

    public int getRowCount() {
        return personsList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;   // The default renderer/editor for Integer (Number) aligns to the right!
            case 3:
                return Boolean.class;   // The default renderer/editor for Boolean shows a check box.
        }

        return Object.class;
    }

    public boolean isCellEditable(int row, int column) {
        return true;   // All cells are editable!
    }

    public Object getValueAt(int row, int column) {
        Person person = personsList.get(row);

        switch (column) {
            case 0:
                return person.getFirstName();
            case 1:
                return person.getLastName();
            case 2:
                return person.getBirthYear();   // Auto-boxing: int -> Integer returned as Object.
            case 3:
                return person.isEmployed();     // Auto-boxing: boolean -> Boolean returned as Object.
        }

        return null;
    }

    // setValueAt() is implemented because cells are editable!
    public void setValueAt(Object value, int row, int column) {
        Person person = personsList.get(row);

        switch (column) {
            case 0:
                person.setFirstName((String) value);
                break;
            case 1:
                person.setLastName((String) value);
                break;
            case 2:
                person.setBirthYear((Integer) value);
                break;   // Unboxing: Object -> Integer -> int
            case 3:
                person.setEmployed((Boolean) value);
                break;    // Unboxing: Object -> Boolean -> boolean
        }

        // IMPORTANT!!!
        // This notifies all the listeners that a cell in the model
        // has been updated.
        System.out.println("PERSON CHANGED");
        fireTableCellUpdated(row, column);
        //add merging
        merge(person);
    }

    public void merge(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cv3swingapp_2PU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
    // NOTE: the following are CUSTOM methods, NOT related to TableModel
    // interface or AbstractTableModel class.
    // These methods lets you to interact with the table model in terms
    // of a "record", in this case a Person object.
    public void addPerson(Person person) {
        personsList.add(person);

        int addedRow = personsList.size() - 1;

        // IMPORTANT!!!
        // This notifies all the listeners that a row in the model
        // has been added.
        fireTableRowsInserted(addedRow, addedRow);
    }

    public void deletePerson(Person p) {
        personsList.remove(p);

        // IMPORTANT!!!
        // This notifies all the listeners that a row in the model
        // has been removed.
        fireTableDataChanged();
    }

    public Person getPersonInRow(int row) {
        return personsList.get(row);
    }

    public List<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
        // IMPORTANT!!!
        // This notifies all the listeners that all data in the model
        // has been changed.
        fireTableDataChanged();
    }
}