package com.cesi.library_project.tableau;
/**
 *
 * @author dubs
 */
public class Oeuvre {
    
    private int id;
    private String titre;
    private int annee;
    private String commentaire;
    private int note;
    
    public Oeuvre(int Id, String Titre, int Annee, String Commentaire, int Note)
    {
        this.id = Id;
        this.titre = Titre;
        this.annee = Annee;
        this.commentaire = Commentaire;
        this.note = Note;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getTitre()
    {
        return titre;
    }
    
    public int getAnnee()
    {
        return annee;
    }
    
    public String getCommentaire()
    {
        return commentaire;
    }
    
    public int getNote()
    {
        return note;
    }
}
