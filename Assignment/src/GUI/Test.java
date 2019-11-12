/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ppunme
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Col1");
        model.addColumn("Col2");

        // Create the first row
        model.insertRow(0, new Object[] { "r1" });

        // Insert a row at position p
        //int p = 1;
        model.insertRow(1, new Object[] { "r3" });

        JFrame f = new JFrame();
        f.setSize(300, 300);
        f.add(new JScrollPane(table));
        f.setVisible(true);
    }
    
}
