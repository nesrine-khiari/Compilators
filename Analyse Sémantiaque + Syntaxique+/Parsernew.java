import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Parsernew {



public String[] LRGS = {"S'->S", //0
"S->{ Declaration D Corps I }",//1
"D->id : T D",//2
"D->FinDec", //3
"I->lire E I", //4
"I->ecrire E I",//5
"I->id ~~ E I",//6
"I->si E alors I ", //7
"I->tantque E faire I", //8
"I->FinCorps",//9
"T->char",//10
"T->entier",//11
"T->bolean",//12
"T->tableau [ nb ] T",//13
"E->nb",//14
"E->id",//15
"E->( E )",//16
"E->id OPP id",//17
"E->nb OPP nb",//18
"OPP-><=", 
"OPP->>=", 
"OPP-> ><",
"OPP->=",
"OPP->et",
"OPP->ou",
"OPP->+",
"OPP->-"
};


public String[][] tableSLR = { 
    {"etat/VT", "{" , "Declaration","Corps", "}", "id", ":","FinDec", "lire", "ecrire", "~~", "si", "alors", "tantque", "faire", "FinCorps","char", "entier", "bolean", "tableau", "[", "nb", "]", "(", ")", "<=", ">=", "><", "=", "et", "ou", "+", "-", "$", "S'", "S", "D", "I", "T", "E", "OPP"},
    {"0", "s2", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err" ,"err","1", "err", "err", "err","err", "err"},
    {"1",  "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err" , "err", "ACC", "err", "err", "err","err", "err", "err", "err"},
    {"2", "err",   "s3",  "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err","err","err"},
    {"3",  "err",  "err", "err","err", "s5","err","s6", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","err","err","4", "err", "err", "err","err"},
    {"4", "err",  "err", "s7",  "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err","err"},
    {"5", "err",  "err", "err","err", "err","s8", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err"},
    {"6", "err",  "err", "r3", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err","err"},
    {"7",  "err", "err", "err","err","s12","err","err","s10","s11","err","s13","err","s14","err","s14", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err","9","err","err","err"},
    {"8", "err",  "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","s17","s18","s19","s20", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err","err","err","16","err","err"},
    {"9",  "err", "err", "err","s21", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err","err"},
    {"10", "err","err", "err","err", "s24", "err", "err", "err","err", "err",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err","s23","err","s25",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err", "22", "err" },
    {"11", "err", "err", "err","err","s24", "err", "err", "err","err", "err",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err","s23","err","s25",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err",  "err", "err", "err","err", "err", "26", "err" },
    {"12", "err", "err","err", "err", "err", "err", "err","err", "err", "s27", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err" },
    {"13", "err", "err","err", "err", "s24", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","s23","err","s25", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","28","err"},
    {"14", "err", "err","err", "err", "s24", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","s23","err","s25", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","29","err" },
    {"15", "err", "err", "err", "r9",  "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err","err"},
    {"16",  "err","err", "err","err", "s5","err","s6",  "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err", "err", "err","err","30",  "err", "err", "err","err"},
    {"17", "err", "err", "err","err", "r10","err","r10",  "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err", "err", "err","err","err",  "err", "err", "err","err"},
    {"18", "err", "err", "err","err", "r11","err","r11",  "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err", "err", "err","err","err",  "err", "err", "err","err"},
    {"19", "err", "err", "err","err", "r12","err","r12",  "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err",   "err", "err", "err","err", "err", "err", "err","err","err",  "err", "err", "err","err"},
    {"20", "err", "err", "err", "err", "err", "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err","s31", "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err"},
    {"21", "err", "err", "err", "err", "err", "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err", "err","err","r1", "err","err", "err", "err", "err", "err","err"},
    {"22", "err", "err", "err","err","s12","err","err","s10","s11","err","s13","err","s14","err","s15", "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err", "err","32","err","err","err"},
    {"23", "err", "err", "err","err","r14","err","err","r14","r14","err","r14","r14","r14","r14","r14", "err",  "err","err", "err", "err", "err",  "err","err", "r14","s34","s35","s36","s37","s38","s39","s40","s41", "err",  "err","err", "err", "err", "err",  "err","33"},
    {"24", "err", "err", "err","err","r15","err","err","r15","r15","err","r15","r15","r15","r15","r15", "err", "err","err", "err", "err", "err", "err", "err", "r15","s34", "s35", "s36", "s37", "s38", "s39", "s40", "s41", "err", "err","err", "err", "err", "err", "err","42"},
    {"25", "err", "err","err", "err", "s24", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","s23","err","s25", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","43","err"},
    {"26", "err", "err", "err","err","s12","err","err","s10","s11","err","s13","err","s14","err","s15", "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err",  "err","err", "err", "err", "err", "err","44","err","err","err"},
    {"27", "err", "err","err", "err", "s24", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","s23","err","s25", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err","45","err"},
    {"28", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "s46", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"29", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "s47", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"30", "err", "err", "r2", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"}, 
{"31", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "s48", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"32", "err", "err", "err", "r4", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"33", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "s49", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},  
{"34", "err", "err", "err", "err", "r19","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r19", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"35", "err", "err", "err", "err", "r20","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r20", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"36", "err", "err", "err", "err", "r21","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r21", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"37", "err", "err", "err", "err", "r22","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r22", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"38", "err", "err", "err", "err", "r23","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r23", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"39", "err", "err", "err", "err", "r24","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r24", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"40", "err", "err", "err", "err", "r25","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r25", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"41", "err", "err", "err", "err", "r26","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "r26", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"42", "err", "err", "err", "err", "s50", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"43", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","s51", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"44", "err", "err", "err", "r5", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"45", "err", "err", "err", "err", "s12","err", "err", "s10", "s11", "err","s13", "err", "s14", "err", "s15","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "52","err", "err", "err"},
{"46", "err", "err", "err", "err", "s12","err", "err", "s10", "s11", "err","s13", "err", "s14", "err", "s15","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "53","err", "err", "err"},
{"47", "err", "err", "err", "err", "s12","err", "err", "s10", "s11", "err","s13", "err", "s14", "err", "s15","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "54","err", "err", "err"},
{"48", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "s55", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"49", "err", "err", "err", "err", "r18","err", "err", "r18", "r18", "err","r18", "r18", "r18", "r18", "r18","err","err","err","err", "err", "err", "err", "err","r18", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"50", "err", "err", "err", "err", "r17","err", "err", "r17", "r17", "err","r17", "r17", "r17", "r17", "r17","err","err","err","err", "err", "err", "err", "err","r17", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"51", "err", "err", "err", "err", "r16","err", "err", "r16", "r16", "err","r16", "r16", "r16", "r16", "r16","err","err","err","err", "err", "err", "err", "err","r16", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"52", "err", "err", "err", "r6", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"53", "err", "err", "err", "r7", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"54", "err", "err", "err", "r8", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},
{"55", "err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","s17","s18","s19","s20", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","56", "err", "err"},
{"56", "err", "err", "err", "err", "r13","err", "r13", "err", "err", "err","err", "err", "err", "err", "err","err","err","err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err", "err","err", "err", "err", "err","err", "err", "err"},

         };
    
     


   
    
    public Stack<String> stackState = new Stack<>();
    public Stack<String> sem = new Stack<String>();
    public Stack<String> analyse = new Stack<>();
    public HashMap<String,String> tabId=new HashMap<>();
    
    public Stack<String> stackSymbol = new Stack<>();
    String ch[]= {"{", "Declaration",  "id", ":", "entier", "FinDec", "Corps", "lire", "id", "ecrire", "id","FinCorps", "}", "$"};
    public String strInput ;
    
   
    public String action = "";
    
    
    
    int index = 0;

    

    
    public Parsernew() {
    }
    public void analyzeSLnew() {
    	
        action = "";
        index = 0;
       
        analyse.push("0");
        
       
        System.out.println("********pile     Entrée   Action			   ");
        this.AfficherSLR();
    
       while(index<ch.length) 
        
        {
          if( ch[index]=="id"){
                  stackState.add("id");
          }
            String s = analyse.peek();
            
            String act=Action(s,ch[index]);
          
            if (Action(s,ch[index]).charAt(0) == 's') {
                   	
                           
                analyse.push(ch[index]);
                analyse.push(Action(s, ch[index]).substring(1));
               
                              
              
                index++;
                action = "shift";
                
                AfficherSLR();
            }
            // Réduction
            else if (Action(s,ch[index]).charAt(0) == 'r') {
                
                String str = LRGS[Integer.parseInt(Action(s, ch[index]).substring(1))];
                Sementique(str);
                System.out.println("Semantique " + sem);
                int pos= str.indexOf('>');
               
                String tabparties[]= str.split("->");
                
                
                String Partiegauche=tabparties[0];
               
                
                String Partiedroite=tabparties[1];
                 
                
                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;
               
               
                for (int i = 0; i < taillepile; i++) {
                	
                  
                    
                    analyse.pop();
                    
                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
        
                
                analyse.push(Action(sommetpile, Partiegauche));
                
               
                action = "reduce:" + str;
                AfficherSLR();
            } 
            //acceptation
            else if (Action(s,ch[index]) == "ACC")
            	{
            	System.out.println("Analyse syntaxique validé : mot accepté"); 
                String resultatSemantique=sem.pop();
                if (resultatSemantique.equals("vide")){
                        sem.push("vide");
                        System.out.println("Analyse sémantique validé "); 
                        System.out.println("Semantique " + sem);}
                else {
                        System.out.println("Echec de l'analyse sémantique"); 

                }
               
            	break;}
            	
            else
            	//erreur
            	{
            	
            	System.out.println("texterreur"+ Action(s,ch[index])+s+ch[index]+index); 
            	System.out.println("Echec d'analyse : mot n'est pas accepté"); 
                System.out.println("Semantique " + sem);
        	break;
            	}
               
        }
        
    }
  
   
    

    
    public String Action(String s, String a) {
        for (int i = 1; i <57 ; i++)
            if (tableSLR[i][0].equals(s))
                for (int j = 1; j <41; j++)
                    if (tableSLR[0][j].equals(a))
                        return tableSLR[i][j];
        return "err";
    }

    
 


    public void AfficherSLR() {
        //  SLR
    	
    	
    	String ss= "---";
    	String ss1= "---";
    	 int taillepile=analyse.size();
    	int taillepilediv2= taillepile /2;
         for(int i=0;i<taillepilediv2;i++)
     		ss=ss + "---" ;
         int tailleinput=ch.length;
         for(int i=0;i<tailleinput;i++)
     		ss1=ss1 + "---" ;
    	
    	
    	
        
       
        strInput="";
        for(int i=index; i<ch.length;i++)
        	strInput= strInput+ ch[i];
       
        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput+ ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void AfficherSLRnew(String []tt) {
        //  SLR
    	
    	
    	String ss= "-------";
    	String ss1= "-------";
    	 int taillepile=analyse.size();
    	int taillepilediv2= taillepile /2;
         for(int i=0;i<taillepilediv2;i++)
     		ss=ss + "-------" ;
         int tailleinput=tt.length;
         for(int i=0;i<tailleinput;i++)
     		ss1=ss1 + "-------" ;
    	
    	
    	
        
       
        strInput="";
        for(int i=index; i<tt.length;i++)
        	strInput= strInput+ tt[i];
       
        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput+ ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void ouput() {
        
        
        System.out.println("**********Tableau SLR¨********");

        for (int i = 0; i <57 ; i++) {
            for (int j = 0; j <41; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }


    void Sementique(String regle) {
        String temp1 = "";
        String temp2 = "";
        String temp3 = "";

         

                if(regle.equals("S->{ Declaration D Corps I }")){
                        temp1 = sem.pop(); // type de I
                        temp2 = sem.pop(); // type D
                        if (temp1.equals("vide")  && temp2.equals("vide")) {
                                sem.push("vide");
                        } else {
                                sem.push("erreur");
                        }
                        }
                else if(regle.equals("D->id : T D")){
                        temp3 = sem.pop();
                        temp2 = sem.pop();

                        if (temp3.equals("vide")) {
                                
                                      sem.push("vide");
                                }
                             
                        
                        else {
                                sem.push("erreur");
                        }
                        ;}

                else if(regle.equals("D->FinDec")){
                      sem.push("vide");
                      
               }
                        
                        
               
                else if(regle.equals("I->lire E I")){
                        temp1 = sem.pop();
                        temp2 = sem.pop();
                        sem.push(temp1);
                        }
                else if(regle.equals("I->ecrire E I")){
                  temp1 = sem.pop();
                  temp2=sem.pop();
                   sem.push(temp1);
                  }

                else if(regle.equals("I->id ~~ E I")){
                        temp1 = sem.pop();
                        temp2 = sem.pop();
                        temp3=sem.pop();
                        if (temp1.equals("vide") ) {
                                String type1=tabId.get(temp2);
                                String type2=tabId.get(temp3);
                                if(type1.equals(type2)){
                                 sem.push("vide");}
                                else {sem.push("erreur");}
                        }
                        else {
                                sem.push("erreur");
                        }
                        }
                
                else if (regle.equals("I->si E alors I ")){
                        temp1 = sem.pop();
                        temp2 = sem.pop();
                        if (temp2.equals("bolean") && temp1.equals("vide")) {
                                sem.push("vide");
                        } else {
                                sem.push("erreur");
                        }
                        }
                
                else if (regle.equals("I->tantque E faire I")){
                   temp1 = sem.pop();
                   temp2 = sem.pop();
                   if (temp2.equals("bolean") && temp1.equals("vide")) {
                        sem.push("vide");
                   } else {
                        sem.push("erreur");
                }
                  }

                else if (regle.equals("I->FinCorps")){
                     sem.push("vide") ;               
                }

                else if (regle.equals("E->id")){
                        sem.push("vide") ;               
                   }

                else if (regle.equals("T->char")){
                        sem.push("char");
                        }

                else if (regle.equals("T->entier")){
                        sem.push("entier");
                        }
                else if (regle.equals("T->bolean")){
                        sem.push("bolean");
                       }
                
               
                else if (regle.equals("E->id OPP id")){
                   temp1=sem.pop();
                   temp2=sem.pop();
                    String type1=tabId.get(temp1);
                    String type2=tabId.get(temp2);
                    if(type1.equals(type2)){
                            sem.push(type1);
                    }
                    else{
                            sem.push("erreur");
                    }

                        }
                else {
                        sem.push("erreur");
                }

        }
    
    
   
 

    
    
    
    
    

}



