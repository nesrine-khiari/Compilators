

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;






public class Main {
    static ArrayList<String> tab= new ArrayList<String>();
    public static void main(String[] args) {
    	
        Scanner anaLex=new Scanner("test.txt");
		System.out.println("***********************Analyse lexical*************************");
	
      


        UniteLexicale ul;
        do {
            ul=anaLex.lexemeSuivant();
            tab.add(ul.toString());
            
           
        } while(ul.getCategorie()!=Categorie.EOF);


for( int i=0; i<tab.size();i++)
       	
       	System.out.println(" " + 
       			tab.get(i));

	   System.out.println("***********************Analyse Syntaxique*************************");	 
    	 
		 Parsernew test22 = new Parsernew();
    	
        
        test22.analyzeSLnew();
        
    }
}

