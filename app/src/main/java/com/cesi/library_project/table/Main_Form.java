/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author dubs
 */
public class Main_Form extends javax.swing.JFrame {

    private Statement state = Connexion.Connect().createStatement();
    String requete;
    ResultSet result;
    DefaultTableModel model;

    /**
     * Creates new form NewJFrame
     */
    public Main_Form() throws SQLException, ClassNotFoundException{
        initComponents();
        findOeuvres(state);
    }
// get a list of users from mysql database

    public ArrayList<Oeuvre> ListOeuvres(String ValToSearch) {
        ArrayList<Oeuvre> oeuvresList = new ArrayList<Oeuvre>();
    
        try{
            
            String searchQuery = "SELECT * FROM oeuvre "
                + "LEFT JOIN category ON category.id = oeuvre.category_id "
                + "LEFT JOIN genre ON genre.id = oeuvre.genre_id "
                + "LEFT JOIN langue ON langue.id = oeuvre.langue_id "
                + "LEFT JOIN album ON album.id = oeuvre.album_id "
                + "LEFT JOIN origine ON origine.id = oeuvre.origine_id "
                + "LEFT JOIN societe ON societe.id = oeuvre.societe_id "
                + "LEFT JOIN TravailleComme ON TravailleComme.oeuvre_id = oeuvre.id "
                + "LEFT JOIN personne ON personne.id = TravailleComme.personne_id "
                + "LEFT JOIN profession ON profession.id = TravailleComme.profession_id "
                + "LEFT JOIN statut ON statut.id = oeuvre.statut_id "
                + "LEFT JOIN support ON support.id = oeuvre.support_id "
                + "LEFT JOIN typeConsole ON typeConsole.id = oeuvre.typeConsole_id "
                
                + "WHERE (`titre`|| `date_parution`|| `note`) LIKE '%"+ValToSearch+"%'";
               
            
            result = state.executeQuery(searchQuery);
            
            Oeuvre oeuvre;

            while (result.next()) {
                oeuvre = new Oeuvre(
                        result.getInt("id"),
                        result.getString("titre"),
                        result.getInt("date_parution"),
                        result.getString("commentaire"),
                        result.getInt("note"),
                        result.getString("name_category"),
                        result.getString("name_genre"),
                        result.getString("name_langue"),
                        result.getString("titre_album"),
                        result.getString("name_origine"),
                        result.getString("name_societe"),
                        result.getString("fonction_societe"),
                        result.getString("name_personne"),
                        result.getString("name_profession"),
                        result.getString("name_statut"),
                        result.getString("name_support"),
                        result.getString("name_console")
                        
                        
                );oeuvresList.add(oeuvre);
                
            } //return oeuvresList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return oeuvresList;

   
}

    // Filter Data In Jtable
    private void findOeuvres(Statement state) {
        ArrayList<Oeuvre> oeuvres = ListOeuvres(jTextField_MainSearch.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]
        {"Id", "Category", "Titre", "Annee","Commentaire","Note", "Genre","Langue","Album", "Origine", "Personne", "Profession", "Societe", "Type Societe", "Statut", "Support", "Console"});
        Object[] row = new Object[17];

        for (int i = 0; i < oeuvres.size(); i++) {
            row[0] = oeuvres.get(i).getId();
            row[1] = oeuvres.get(i).getCategory();
            row[2] = oeuvres.get(i).getTitre();
            row[3] = oeuvres.get(i).getAnnee();
            row[4] = oeuvres.get(i).getCommentaire();
            row[5] = oeuvres.get(i).getNote();
            row[6] = oeuvres.get(i).getGenre();
            row[7] = oeuvres.get(i).getLangue();
            row[8] = oeuvres.get(i).getAlbum();
            row[9] = oeuvres.get(i).getOrigine();
            row[10] = oeuvres.get(i).getPersonne();
            row[11] = oeuvres.get(i).getProfession();
            row[12] = oeuvres.get(i).getSociete();
            row[13] = oeuvres.get(i).getTypeSociete();
            row[14] = oeuvres.get(i).getStatut();
            row[15] = oeuvres.get(i).getSupport();
            row[16] = oeuvres.get(i).getConsole();
            
            model.addRow(row);
        }
        jTable_MainOeuvres.setModel(model);
        
//model.setData(donnees);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_MainSearch = new javax.swing.JButton();
        jButton_Livres = new javax.swing.JButton();
        jButton_Musiques = new javax.swing.JButton();
        jButton_Films = new javax.swing.JButton();
        jButton_Jeux = new javax.swing.JButton();
        jTextField_MainSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_MainOeuvres = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jButton_MainSearch.setText("MainSearch");
        jButton_MainSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MainSearchActionPerformed(evt);
            }
        });

        jButton_Livres.setText("Livres");
        jButton_Livres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_LivresMouseClicked(evt);
            }
        });
        jButton_Livres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LivresActionPerformed(evt);
            }
        });

        jButton_Musiques.setText("Musiques");
        jButton_Musiques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MusiquesActionPerformed(evt);
            }
        });

        jButton_Films.setText("Films");
        jButton_Films.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FilmsActionPerformed(evt);
            }
        });

        jButton_Jeux.setText("JeuxVideo");
        jButton_Jeux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_JeuxActionPerformed(evt);
            }
        });

        jTextField_MainSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MainSearchActionPerformed(evt);
            }
        });

        jTable_MainOeuvres.setBackground(new java.awt.Color(220, 204, 204));
        jTable_MainOeuvres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Category", "Titre", "Annee", "Commentaire", "Note", "Genre", "Langue", "Album", "Origine", "Personne", "Profession", "Societe", "TypeSociete", "Statut", "Support", "TypeConsole"
            }
        ));
        jTable_MainOeuvres.setSelectionBackground(new java.awt.Color(0, 153, 204));
        jTable_MainOeuvres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_MainOeuvresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_MainOeuvres);
        if (jTable_MainOeuvres.getColumnModel().getColumnCount() > 0) {
            jTable_MainOeuvres.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable_MainOeuvres.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable_MainOeuvres.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable_MainOeuvres.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable_MainOeuvres.getColumnModel().getColumn(5).setPreferredWidth(40);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_Livres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Musiques)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Films)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Jeux)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 414, Short.MAX_VALUE)
                        .addComponent(jButton_MainSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_MainSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_MainSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_MainSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Livres)
                        .addComponent(jButton_Musiques)
                        .addComponent(jButton_Films)
                        .addComponent(jButton_Jeux)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_MainSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MainSearchActionPerformed
       findOeuvres(state);
    }//GEN-LAST:event_jButton_MainSearchActionPerformed

    private void jButton_LivresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LivresActionPerformed
       
    }//GEN-LAST:event_jButton_LivresActionPerformed

    private void jButton_MusiquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MusiquesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_MusiquesActionPerformed

    private void jButton_FilmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FilmsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_FilmsActionPerformed

    private void jButton_JeuxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_JeuxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_JeuxActionPerformed

    private void jTextField_MainSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MainSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MainSearchActionPerformed

    private void jTable_MainOeuvresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_MainOeuvresMouseClicked
        int i = jTable_MainOeuvres.getSelectedRow();
        TableModel model = jTable_MainOeuvres.getModel();
    }//GEN-LAST:event_jTable_MainOeuvresMouseClicked

    private void jButton_LivresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_LivresMouseClicked
        try {
            new Livres_Form().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Main_Form.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_LivresMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Films;
    private javax.swing.JButton jButton_Jeux;
    private javax.swing.JButton jButton_Livres;
    private javax.swing.JButton jButton_MainSearch;
    private javax.swing.JButton jButton_Musiques;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_MainOeuvres;
    private javax.swing.JTextField jTextField_MainSearch;
    // End of variables declaration//GEN-END:variables
}
