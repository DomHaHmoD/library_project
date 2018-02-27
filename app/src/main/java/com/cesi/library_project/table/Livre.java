/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_project;

/**
 *
 * @author dubs
 */
public class Livre {
    private int id;
    private String titre;
    private int annee;
    private String commentaire;
    private int note;
    private String category;
    private String genre;
    private String langue;
    private String origine;
    private String societe;
    private String typeSociete;
    private String personne;
    private String profession;
    private String statut;
    private String support;
    
    public Livre(int Id, String Titre, int Annee, String Commentaire, int Note, String Category,String Genre,String Langue,
            String Origine,String Societe,String TypeSociete,String Personne,String Profession,
            String Statut,String Support)
    {
        this.id = Id;
        this.titre = Titre;
        this.annee = Annee;
        this.commentaire = Commentaire;
        this.note = Note;
        this.category = Category;
        this.genre = Genre;
        this.langue = Langue;   
        this.origine = Origine;
        this.societe = Societe;
        this.typeSociete = TypeSociete;
        this.personne = Personne;
        this.profession = Profession;
        this.statut = Statut;
        this.support = Support;
       
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

    public String getCategory()
    {
        return category;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
    public String getLangue()
    {
        return langue;
    }
   
    public String getOrigine()
    {
        return origine;
    }
    
    public String getSociete()
    {
        return societe;
    }
    
    public String getTypeSociete()
    {
        return typeSociete;
    }
    
    public String getPersonne()
    {
        return personne;
    }
    
    public String getProfession()
    {
        return profession;
    }
    
    public String getStatut()
    {
        return statut;
    }
    
    public String getSupport()
    {
        return support;
    }
   
}
