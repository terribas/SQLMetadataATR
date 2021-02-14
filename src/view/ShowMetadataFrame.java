/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import pojo.Table;

/**
 *
 * @author alejandroterribasruiz
 */
public class ShowMetadataFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShowMetadataFrame
     */
    public ShowMetadataFrame(String dbName, ArrayList<Table> tables) {
        initComponents();
        
        init(dbName, tables);
    }

    private void init(String dbName, ArrayList<Table> tables){
        this.setTitle("TABLES OF " + dbName);
        
        if(tables == null){
            taDetails.setText("Could not retrieve metadata");
            
        }else if(tables.size() == 0){
            taDetails.setText("This database is empty.");
        
        }else{
            //con tables.toString() se incluyen [ , ] y queda feo
            for (int i = 0; i < tables.size(); i++) {
                taDetails.append(tables.get(i).toString());
            }
        
            taDetails.setCaretPosition(0);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        taDetails = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taDetails.setEditable(false);
        taDetails.setColumns(20);
        taDetails.setFont(new java.awt.Font("Courier", 0, 13)); // NOI18N
        taDetails.setRows(5);
        taDetails.setText("\n");
        scrollPane.setViewportView(taDetails);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea taDetails;
    // End of variables declaration//GEN-END:variables
}
