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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author dubs
 */
public class Livres_Form extends javax.swing.JFrame {
    
 private Statement state = Connexion.Connect().createStatement();
    String requete;
    ResultSet result;
    DefaultTableModel model;

    public Livres_Form() throws SQLException, ClassNotFoundException {
        initComponents();
        findLivres(state);
    }


    public ArrayList<Livre> ListLivres(String ValToSearch) {
        ArrayList<Livre> livresList = new ArrayList<Livre>();

        try {
            
           String livreQuery = "SELECT * FROM oeuvre "
                   + "LEFT JOIN category ON category.id = oeuvre.category_id "
                   + "LEFT JOIN genre ON genre.id = oeuvre.genre_id "
                   + "LEFT JOIN langue ON langue.id = oeuvre.langue_id "
                   + "LEFT JOIN origine ON origine.id = oeuvre.origine_id "
                   + "LEFT JOIN societe ON societe.id = oeuvre.societe_id "
                   + "LEFT JOIN TravailleComme ON TravailleComme.oeuvre_id = oeuvre.id"
                   + "LEFT JOIN personne ON personne.id = TravailleComme.personne_id "
                   + "LEFT JOIN profession ON profession.id = TravailleComme.profession_id "
                   + "LEFT JOIN statut ON statut.id = oeuvre.statut_id "
                   + "LEFT JOIN support ON support.id = oeuvre.support_id "
                   
                   + "WHERE (`titre`|| `date_parution`|| `note`|| `name_personne` || `name_profession` || `name_societe` ) LIKE '%"+ValToSearch+"%' "
                   + "AND (category_id = 2)";
           
            result = state.executeQuery(livreQuery);

            Livre livre;

            while (result.next()) {
                livre = new Livre(
                        result.getInt("id"),
                        result.getString("titre"),
                        result.getInt("date_parution"),
                        result.getString("commentaire"),
                        result.getInt("note"),
                        result.getString("name_category"),
                        result.getString("name_genre"),
                        result.getString("name_langue"),
                        result.getString("name_origine"),                  
                        result.getString("name_societe"),
                        result.getString("fonction_societe"),
                        result.getString("name_personne"),
                        result.getString("name_profession"),
                        result.getString("name_statut"),
                        result.getString("name_support")
                );
                livresList.add(livre);

            }
            return livresList;


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return livresList;

}


    // Filter Data In Jtable
    private void findLivres(Statement state) {
        ArrayList<Livre> oeuvres = ListLivres(jTextField_Filter.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]
        {"Id", "Titre", "Annee","Commentaire","Note", "Category", "Genre","Langue", "Origine", "Personne", "Profession", "Societe", "Type Societe", "Statut", "Support"});
        Object[] row = new Object[15];

        for (int i = 0; i < oeuvres.size(); i++) {
            row[0] = oeuvres.get(i).getId();
            row[1] = oeuvres.get(i).getTitre();
            row[2] = oeuvres.get(i).getAnnee();
            row[3] = oeuvres.get(i).getCommentaire();
            row[4] = oeuvres.get(i).getNote();
            row[5] = oeuvres.get(i).getCategory();
            row[6] = oeuvres.get(i).getGenre();
            row[7] = oeuvres.get(i).getLangue();
            row[8] = oeuvres.get(i).getOrigine();
            row[9] = oeuvres.get(i).getPersonne();
            row[10] = oeuvres.get(i).getProfession();
            row[11] = oeuvres.get(i).getSociete();
            row[12] = oeuvres.get(i).getTypeSociete();
            row[13] = oeuvres.get(i).getStatut();
            row[14] = oeuvres.get(i).getSupport();
           
            
            model.addRow(row);
        }
           jTable_Display_Livres.setModel(model);

    }


    // Execute The Insert Update And Delete Querys
    public void executeSQlQuery(String query, String message) {

        try {

            if ((state.executeUpdate(query)) == 1) {
                // refresh jtable data
                DefaultTableModel model = (DefaultTableModel) jTable_Display_Livres.getModel();
                model.setRowCount(0);

                JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not " + message);
            }
            findLivres(state);
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }findLivres(state);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Titre = new javax.swing.JTextField();
        jTextField_Annee = new javax.swing.JTextField();
        jTextField_Id = new javax.swing.JTextField();
        jButton_Insert = new javax.swing.JButton();
        jButton_Update = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Livres = new javax.swing.JTable();
        jTextField_Filter = new javax.swing.JTextField();
        jButton_Search = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField_Support = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_Origine = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Langue = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_Note = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_Genre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_Personne = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_Societe = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField_Profession = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_Statut = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField_TypeSociete = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_Category = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_Commentaire = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Id :");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Titre :");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Commentaire :");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Annee :");

        jTextField_Titre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_TitreActionPerformed(evt);
            }
        });

        jTextField_Annee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AnneeActionPerformed(evt);
            }
        });

        jTextField_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_IdActionPerformed(evt);
            }
        });

        jButton_Insert.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Insert.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jButton_Insert.setForeground(new java.awt.Color(0, 102, 0));
        jButton_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton_Insert.setText("INSERT");
        jButton_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });

        jButton_Update.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Update.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jButton_Update.setForeground(new java.awt.Color(0, 102, 204));
        jButton_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upd.png"))); // NOI18N
        jButton_Update.setText("UPDATE");
        jButton_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });

        jButton_Delete.setBackground(new java.awt.Color(255, 255, 255));
        jButton_Delete.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(255, 0, 0));
        jButton_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del.png"))); // NOI18N
        jButton_Delete.setText("DELETE");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jTable_Display_Livres.setBackground(new java.awt.Color(204, 204, 204));
        jTable_Display_Livres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Category", "Titre", "Annee", "Commentaire", "Note", "Genre", "Langue", "Origine", "Personne", "Profession", "Societe", "TypeSociete", "Statut", "Support"
            }
        ));
        jTable_Display_Livres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_LivresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Livres);

        jTextField_Filter.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));
        jTextField_Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FilterActionPerformed(evt);
            }
        });
        jTextField_Filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_FilterKeyReleased(evt);
            }
        });

        jButton_Search.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jButton_Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton_Search.setText("SEARCH");
        jButton_Search.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText("Support :");

        jTextField_Support.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SupportActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Note :");

        jTextField_Origine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_OrigineActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Genre :");

        jTextField_Langue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LangueActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("Personne :");

        jTextField_Note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NoteActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel12.setText("Langue :");

        jTextField_Genre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_GenreActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel13.setText("Societe :");

        jTextField_Personne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_PersonneActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel15.setText("Profession :");

        jTextField_Societe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SocieteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel16.setText("Origine :");

        jTextField_Profession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProfessionActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel17.setText("Statut :");

        jTextField_Statut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_StatutActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel18.setText("TypeSociete :");

        jTextField_TypeSociete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_TypeSocieteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Category :");

        jTextField_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CategoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/dubs/NetBeansProjects/Library_Project_Java_Alex/src/images/Livre.png")); // NOI18N
        jLabel1.setText("LIVRES");

        jTextArea_Commentaire.setColumns(20);
        jTextArea_Commentaire.setRows(5);
        jScrollPane2.setViewportView(jTextArea_Commentaire);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Note, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Genre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Langue, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Origine, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Personne, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Profession, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Societe, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_TypeSociete, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Statut, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Support, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Titre, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Annee, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton_Insert)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Delete)
                                .addGap(1, 1, 1)))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jButton_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Search)
                            .addComponent(jTextField_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Delete)
                            .addComponent(jButton_Update)
                            .addComponent(jButton_Insert))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Titre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Annee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Langue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Origine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Personne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Profession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Societe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_TypeSociete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Statut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Support, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CategoryActionPerformed

    private void jTextField_TypeSocieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_TypeSocieteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_TypeSocieteActionPerformed

    private void jTextField_StatutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_StatutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_StatutActionPerformed

    private void jTextField_ProfessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProfessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProfessionActionPerformed

    private void jTextField_SocieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SocieteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SocieteActionPerformed

    private void jTextField_PersonneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_PersonneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_PersonneActionPerformed

    private void jTextField_GenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_GenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_GenreActionPerformed

    private void jTextField_NoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NoteActionPerformed

    private void jTextField_LangueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_LangueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_LangueActionPerformed

    private void jTextField_OrigineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_OrigineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_OrigineActionPerformed

    private void jTextField_SupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SupportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SupportActionPerformed

    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SearchActionPerformed
        findLivres(state);
    }//GEN-LAST:event_jButton_SearchActionPerformed

    private void jTextField_FilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_FilterKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FilterKeyReleased

    private void jTextField_FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FilterActionPerformed

    private void jTable_Display_LivresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_LivresMouseClicked
        int i = jTable_Display_Livres.getSelectedRow();
        TableModel model = jTable_Display_Livres.getModel();
        jTextField_Id.setText(model.getValueAt(i, 0).toString());    
        jTextField_Titre.setText(model.getValueAt(i, 1).toString());
        jTextField_Annee.setText(model.getValueAt(i, 2).toString());
        jTextArea_Commentaire.setText(model.getValueAt(i, 3).toString());
        jTextField_Note.setText(model.getValueAt(i, 4).toString());
        jTextField_Category.setText(model.getValueAt(i, 5).toString());
        jTextField_Genre.setText(model.getValueAt(i, 6).toString());
        jTextField_Langue.setText(model.getValueAt(i, 7).toString());
        jTextField_Origine.setText(model.getValueAt(i, 8).toString());
        jTextField_Personne.setText(model.getValueAt(i, 9).toString());
        jTextField_Profession.setText(model.getValueAt(i, 10).toString());
        jTextField_Societe.setText(model.getValueAt(i, 11).toString());
        jTextField_TypeSociete.setText(model.getValueAt(i, 12).toString());
        jTextField_Statut.setText(model.getValueAt(i, 13).toString());
        jTextField_Support.setText(model.getValueAt(i, 14).toString());

    }//GEN-LAST:event_jTable_Display_LivresMouseClicked

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        String query = "DELETE FROM `oeuvre` WHERE `id`= '" + jTextField_Id.getText() + "'";
        executeSQlQuery(query, "Deleted");
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateActionPerformed
        String query = "UPDATE `oeuvre`"
                + "SET `titre`= '" + jTextField_Titre.getText() + "',"
                + " `date_parution`= '" + jTextField_Annee.getText() + "',"
                + " `commentaire`= '" +jTextArea_Commentaire.getText() +"',"
                + "`note`= '" + jTextField_Note.getText()  +"',"
                + "' WHERE `id`= '" + jTextField_Id.getText() + "'";
        executeSQlQuery(query, "Updated");
    }//GEN-LAST:event_jButton_UpdateActionPerformed

    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertActionPerformed
        String query = "INSERT INTO `oeuvre`(`titre`,`date_parution`,`commentaire`,`note`) " +
                "VALUES ('" + jTextField_Titre.getText() + "','" + jTextField_Annee.getText() + "'," +
                "'" + jTextArea_Commentaire.getText() + "','" + jTextField_Note.getText() + "')";
        
        String query2 = "INSERT INTO `oeuvre`(`titre`,`date_parution`,`commentaire`,`note`) " +
                "VALUES ('" + jTextField_Titre.getText() + "','" + jTextField_Annee.getText() + "'," +
                "'" + jTextArea_Commentaire.getText() + "','" + jTextField_Note.getText() + "')";
        executeSQlQuery(query, "Inserted");
    }//GEN-LAST:event_jButton_InsertActionPerformed

    private void jTextField_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IdActionPerformed

    private void jTextField_AnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AnneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AnneeActionPerformed

    private void jTextField_TitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_TitreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_TitreActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Insert;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton jButton_Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Display_Livres;
    private javax.swing.JTextArea jTextArea_Commentaire;
    private javax.swing.JTextField jTextField_Annee;
    private javax.swing.JTextField jTextField_Category;
    private javax.swing.JTextField jTextField_Filter;
    private javax.swing.JTextField jTextField_Genre;
    private javax.swing.JTextField jTextField_Id;
    private javax.swing.JTextField jTextField_Langue;
    private javax.swing.JTextField jTextField_Note;
    private javax.swing.JTextField jTextField_Origine;
    private javax.swing.JTextField jTextField_Personne;
    private javax.swing.JTextField jTextField_Profession;
    private javax.swing.JTextField jTextField_Societe;
    private javax.swing.JTextField jTextField_Statut;
    private javax.swing.JTextField jTextField_Support;
    private javax.swing.JTextField jTextField_Titre;
    private javax.swing.JTextField jTextField_TypeSociete;
    // End of variables declaration//GEN-END:variables
}
