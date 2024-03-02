


public class UniteLexicale {
    private Object categorie;
    private Object lexeme;

    public UniteLexicale(Object categorie, Object lexeme) {
        this.categorie=categorie;
        this.lexeme=lexeme;
    }
    

    public Object getCategorie() {
        return categorie;
    }

    public String toString() {
        return "<"+categorie.toString()+","+lexeme+">";
    }
    
}
