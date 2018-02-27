package library_project;

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
    private String category;
    private String genre;
    private String langue;
    private String album;
    private String origine;
    private String societe;
    private String typeSociete;
    private String personne;
    private String profession;
    private String statut;
    private String support;
    private String console;
    
    public Oeuvre(int Id, String Titre, int Annee, String Commentaire, int Note, String Category,String Genre,String Langue,
            String Album,String Origine,String Societe,String TypeSociete,String Personne,String Profession,
            String Statut,String Support,String Console)
    {
        this.id = Id;
        this.titre = Titre;
        this.annee = Annee;
        this.commentaire = Commentaire;
        this.note = Note;
        this.category = Category;
        this.genre = Genre;
        this.langue = Langue;
        this.album = Album;
        this.origine = Origine;
        this.societe = Societe;
        this.typeSociete = TypeSociete;
        this.personne = Personne;
        this.profession = Profession;
        this.statut = Statut;
        this.support = Support;
        this.console = Console;
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
    
    public String getAlbum()
    {
        return album;
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
    
    public String getConsole()
    {
        return console;
    }



  
    
    
}
