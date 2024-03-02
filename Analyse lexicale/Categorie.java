public enum Categorie {
    EOF, // End of file
    ID, // Identifier
    // $,
    INT,
    OPP, //All operators
    OPASS, //Assign
    OPEN_PAREN, // '('
    CLOSE_PAREN, // ')'
    OPEN_BRACE, // '{'
    CLOSE_BRACE, // '}'
    COMMA, // ','
    BEGIN, // ','
    END, // ','
    SEMICOLON,
    DOUBLE_POINT; // ';'
  
    

    @Override
    public String toString() {
        return name().toLowerCase(); // Converts enum name to lowercase
    }

    public static Categorie fromString(String text) {
        for (Categorie b : Categorie.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}

 // ASSIGN, // Assignment operator '='
    // PLUS, // '+'
    // MINUS, // '-'
    // MULTIPLY, // '*'
    // DIVIDE, // '/'
    // EQUALS, // '=='
    // NOT_EQUALS, // '!='
    // LESS_THAN, // '<'
    // GREATER_THAN, // '>'
    // LESS_THAN_EQUALS, // '<='
    // GREATER_THAN_EQUALS, // '>='


    