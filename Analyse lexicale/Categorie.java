
public enum Categorie{
    EOF,
    $,
    NUL,
    ID,
    NOMBRE,
    OPPAff,
    OPP,
    POINT,
    DPOINT,
    PARENOUV,
    PARENFERM,
    CROCHOUV,
    CROCHFERM,
    DEBUT,
    FIN,
    
    ;

    


    public String toString() {
        return this.name().toLowerCase();
    }


public static Categorie toCategorie(String s) {
    for(Categorie c:Categorie.values())
        if(c.toString().equalsIgnoreCase(s))
            return c;
    return null;
}



}