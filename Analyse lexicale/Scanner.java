
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Scanner {
    private ArrayList<Character> fluxCaracteres;
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;

    public Scanner() {
        this("");
    }

    public Scanner(String nomFich) {
        BufferedReader f = null;
        int car = 0;
        fluxCaracteres = new ArrayList<Character>();
        indiceCourant = 0;
        eof = false;
        try {
            f = new BufferedReader(new FileReader(nomFich));
        } catch (IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f = new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while ((car = f.read()) != -1)
                fluxCaracteres.add((char) car);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void caractereSuivant() {
        if (indiceCourant < fluxCaracteres.size())
            caractereCourant = fluxCaracteres.get(indiceCourant++);
        else
            eof = true;
    }

    public void reculer() {
        if (indiceCourant > 0)
            indiceCourant--;
    }

    public UniteLexicale lexemeSuivant() {
        caractereSuivant();

        while (eof || Character.isWhitespace(caractereCourant)) {
            if (eof)
                return new UniteLexicale(Categorie.EOF, "");
            caractereSuivant();
        }

        if (Character.isLetter(caractereCourant))
            return getID();

        if (caractereCourant == '\"')
            return getStringLiteral();

        if (Character.isDigit(caractereCourant))
            return getNombre();

        if (caractereCourant == '~')
            return new UniteLexicale(Categorie.OPASS, "~");

        if (caractereCourant == '(')
            return new UniteLexicale(Categorie.OPEN_PAREN, "(");

        if (caractereCourant == ')')
            return new UniteLexicale(Categorie.CLOSE_PAREN, ")");

        if (caractereCourant == ':')
            return new UniteLexicale(Categorie.DOUBLE_POINT, ":");

        if (caractereCourant == '[')
            return new UniteLexicale(Categorie.BEGIN, "[");

        if (caractereCourant == ']')
            return new UniteLexicale(Categorie.END, "]");

        if (caractereCourant == '{')
            return new UniteLexicale(Categorie.OPEN_BRACE, "{");

        if (caractereCourant == '}')
            return new UniteLexicale(Categorie.CLOSE_BRACE, "}");
        if (caractereCourant == ';')
            return new UniteLexicale(Categorie.SEMICOLON, ";");
        if (caractereCourant == ',')
            return new UniteLexicale(Categorie.COMMA, ",");

        if (caractereCourant == '<' || caractereCourant == '>' || caractereCourant == '=' || caractereCourant == '+'
                || caractereCourant == '-' || caractereCourant == '*' || caractereCourant == '/'
                || caractereCourant == '#')
            return getOPP();

        return null;
    }

    public UniteLexicale getID() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();

        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    sb.append(caractereCourant);
                    break;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isLetterOrDigit(caractereCourant)) {
                        sb.append(caractereCourant);
                        etat = 1;
                    } else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    // La fonction VerifMotCle permet t=de vérifier s'il s'agit d'un mot clé ou un
                    // ID
                    if (VerifMotcle(sb.toString())) {
                        return new UniteLexicale(sb.toString(), sb.toString());
                    } // <si,si>
                    else
                        return new UniteLexicale(Categorie.ID, sb.toString());

                case 3:
                    if (VerifMotcle(sb.toString())) {
                        return new UniteLexicale(sb.toString(), sb.toString());
                    } else
                        return new UniteLexicale(Categorie.ID, sb.toString());

            }
        }
    }

    public UniteLexicale getNombre() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    etat = 1;
                    sb.append(caractereCourant);
                    break;
                case 1:
                    caractereSuivant();
                    if (eof)
                        etat = 3;
                    else if (Character.isDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat = 2;
                    break;
                case 2:
                    reculer();
                    return new UniteLexicale(Categorie.INT_LITERAL, sb.toString());
                case 3:
                    return new UniteLexicale(Categorie.INT_LITERAL, sb.toString());
            }
        }

    }

    public UniteLexicale getStringLiteral() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:

                    etat = 1; // Transition to state 1 if the current character is a double quote
                    sb.append(caractereCourant);

                    break;
                case 1:
                    caractereSuivant();
                    if (eof) {
                        etat = 3; // Transition to state 3 if EOF is reached
                    } else if (caractereCourant != '\"') {
                        sb.append(caractereCourant); // Append non-quote characters
                    } else {
                        etat = 2; // Transition to state 2 if a closing quote is found
                    }
                    break;
                case 2:
                    return new UniteLexicale(Categorie.STRING_LITERAL, sb.toString()); // Return the string literal
                case 3:
                    return new UniteLexicale(Categorie.STRING_LITERAL, sb.toString());

            }
        }
    }

    // public UniteLexicale getOPPASS() {
    // int etat = 0;
    // StringBuffer sb = new StringBuffer();
    // while (true) {
    // switch (etat) {
    // case 0:
    // if (eof)
    // break;
    // else if (caractereCourant == '~') {
    // sb.append(caractereCourant);
    // caractereSuivant();
    // etat = 1;

    // } else
    // break;

    // case 1:
    // if (eof)
    // break;
    // else if (caractereCourant == '~') {
    // sb.append(caractereCourant);
    // caractereSuivant();
    // etat = 2;

    // } else
    // break;

    // case 2:
    // if (eof)
    // etat = 3;
    // else
    // etat = 5;
    // case 3:

    // return new UniteLexicale(Categorie.OPASS, sb.toString());
    // case 4:
    // reculer();
    // return new UniteLexicale(Categorie.OPASS, sb.toString());

    // }

    // }
    // }

    public UniteLexicale getOPP() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;

                    } else if (caractereCourant == '>') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;
                    } else if (caractereCourant == '<') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 3;
                    } else if (caractereCourant == '+') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 9;
                    } else if (caractereCourant == '-') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 10;
                    } else if (caractereCourant == '/') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 11;

                    } else if (caractereCourant == '*') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 12;

                    }

                    break;

                case 1:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "EGA");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "EGA");

                case 2:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 4;

                    } else
                        etat = 5;

                    break;

                case 3:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 6;

                    } else if (caractereCourant == '>') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 7;

                    } else
                        etat = 8;

                    break;

                case 4:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "PGE");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "PGE");
                case 5:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "PGQ");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "PGQ");

                case 6:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "PPE");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "PPE");
                case 7:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "DIF");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "DIF");
                case 8:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "PPQ");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "PPQ");

                case 9:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "PLUS");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "PLUS");

                case 10:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "MOINS");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "MOINS");
                case 11:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "DIVIDE");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "DIVIDE");
                case 12:
                    if (eof) {
                        return new UniteLexicale(Categorie.OPP, "MULTIPLY");
                    } else
                        reculer();
                    return new UniteLexicale(Categorie.OPP, "MULTIPLY");

            }

        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return fluxCaracteres.toString();
    }

    public boolean VerifMotcle(String s) {
        return (s.equals("If") || s.equals("Else") || s.equals("Then") || s.equals("While") ||
                s.equals("Do") || s.equals("Input") || s.equals("Output") ||
                s.equals("And") || s.equals("Or") || s.equals("String") ||
                s.equals("Map") || s.equals("Int") || s.equals("List") || s.equals("EndDeclerations") ||
                s.equals("Boolean"));
    }

}
