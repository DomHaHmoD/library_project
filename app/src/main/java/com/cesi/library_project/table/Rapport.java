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

/**
 *
 * @author dubs
 */
public class Rapport {
    private Statement state = Connexion.Connect().createStatement();
    String requete;
    ResultSet result;
}

public Rapport() throws SQLException, ClassNotFoundException {
        initComponents();
        findRapports(state);

        public ArrayList<Livre> ListLivres(String ValToSearch) {
        ArrayList<Livre> livresList = new ArrayList<Livre>();

        try {
            
           String livreQuery = "SELECT COUNT(*) FROM oeuvre where note = 5
                                union
                                SELECT COUNT(*) FROM oeuvre where note = 4
                                union
                                SELECT COUNT(*) FROM oeuvre where note = 3
                                union
                                SELECT COUNT(*) FROM oeuvre where note = 2
                                union
                                SELECT COUNT(*) FROM oeuvre where note =  1
                                union
                                SELECT COUNT(*) FROM oeuvre where note = 0";

                                result = state.executeQuery(livreQuery);

            Livre livre;