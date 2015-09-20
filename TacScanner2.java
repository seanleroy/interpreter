/**
   Translator for TAC program. Scans source file and constructs
   TacProgram for execution.
*/

import java.io.*;
import java.util.regex.*;
import java.util.Scanner;

public class TacScanner2
{

  public TacScanner2(String sourceFile)
  {  scanner = null;
     TacProgram2 program = new TacProgram2();
    
     try
     {  scanner = new Scanner(new File(sourceFile));
     }
     catch (Exception e)
     {  System.err.println("Trouble opening "+sourceFile);
        System.exit(1);
     }

     tokens = Pattern.compile(tokenRE);
     interstices = Pattern.compile(intersticesRE);
  }

  /**
     Scan source and construct and return corresponding TAC program.
     @return the TAC program
  */
  public TacProgram2 scan()
  {  //Pattern tokens = Pattern.compile(tokenRE);
     //Pattern interstices = Pattern.compile(intersticesRE);

     TacProgram2 program = new TacProgram2();

     while (scanner.hasNext())
     {  TacInstruction2 currentInst = this.nextInstruction(); 
        if (currentInst != null)
          program.append(currentInst); 
     }
     //program.close();
     return program;
  }

  /*
     Issue specified error message and halt execution.
  */
  private void shriek(String message)
  {  System.err.println("TacScanner: "+message);
     System.exit(1);
  }

  /*
     Read and return next token, skipping over any preceeding comments
     or whitespace, and return it.
     @return spelling of next token
  */
  public String next()
  { 
     scanner.skip(interstices);
     //MatchResult x = scanner.match();
     //System.out.printf("Skipping |%s|\n", x.toString());
    
     String tkn = scanner.findInLine(tokens);
     //System.out.printf("Consumed token \"%s\"\n", tkn); 
     return tkn; 
  }

  /*
    Read next token, skipping over any preceeding comments or whitespace, 
    verify that is is an an identifier and return it.
    @return spelling of identifier
  */
  private String nextId()
  {  String tkn = next();
     if (! true)    // fix this up later
        shriek("Id or label expected, found "+tkn);
     return tkn; 
  }

  /*
    Verify that next token is as expected.
    @param exp-- token expected
  */
  private void match(String exp)
  {  String fnd = next();
     if (! exp.equals(fnd))
        shriek(exp+" expected, found "+fnd);
  }


  /*
    Read next TAC instruction from source and return the corresponding
    instruction object.
    @return TacInstruction2 object for next instruction 
  */
  private TacInstruction2 nextInstruction()
  {  TacInstruction2 inst = null;
     String first, a1, a2, a3, op, label;

     first = next();
     if (first == null)
     {  return null;
     }
     else
     if (first.equals("read"))
     {  a1 = nextId();
        match(";");
        return new TacInstruction2(TacInstruction2.InstructionKind.READ, a1, "", "", "", "");
     }
     else
     if (first.equals("write"))
     {  a1 = nextId();
        match(";");
        return new TacInstruction2(TacInstruction2.InstructionKind.WRITE, a1, "", "", "", "");
     }
     else
     if (first.equals("if"))
     {   match("(");
         a1 = nextId();
         op = next();
         match(FALSE);
         match(")");
         match("goto");
         label = nextId();
         match(";");
         if (op.equals("=="))
            return new TacInstruction2(TacInstruction2.InstructionKind.JUMPFALSE, a1, "", "", "", label);
         else
            return new TacInstruction2(TacInstruction2.InstructionKind.JUMPTRUE, a1, "", "", "",label); 
     }
     else
     if (first.equals("goto"))
     {  label = nextId();
        match(";");
        return new TacInstruction2(TacInstruction2.InstructionKind.GOTO, "", "", "", "", label);
     }
     else
     if (first.equals("halt"))
     {  match(";");
        return new TacInstruction2(TacInstruction2.InstructionKind.HALT, "", "", "", "", "");
     }
     else
     if (Pattern.matches(namesRE, first))
     {  // handle assignment or label
        String nxt = nextId();

        if (nxt.equals(":"))
        {  label = first;
           return new TacInstruction2(TacInstruction2.InstructionKind.LABEL, "", "", "", "", label);
        }
        else
        {  a1 = first;
           a2 = next();
           nxt = next();
           if (nxt.equals(";"))
           {  return new TacInstruction2(TacInstruction2.InstructionKind.TWOADDR, a1, a2, "", "", "");
           }
           else
           {  op = nxt;
              a3 = nextId();
              match(";");
              return new TacInstruction2(TacInstruction2.InstructionKind.THREEADDR, a1, a2, a3, op, "");
           }
        }
     }
     else
     {  shriek("Illegal token "+first);
        return null;
     }
  }

  private boolean isOperator(String op)
  {  return Pattern.matches(operatorsRE, op);
  }

  private boolean isName(String n)
  {  return Pattern.matches(namesRE, n);
  }

  /* Regular expressions to specify TAC tokens */
  private final String wordsRE = "if|goto|read|write|halt";                 // reserved words
  private final String namesRE = "[a-zA-Z][a-zA-Z0-9]*";                    // identifiers and labels
  private final String numbersRE = "[0-9]+";                                // integer literals
  private final String operatorsRE = "\\+|\\-|\\*|\\/|==|!=|<=|<|>=|>";     // arithmetic operators
  private final String symbolsRE = "\\(|\\)|;|:=|:";                        // TAC symbols
  private final String tokenRE = wordsRE+"|"+namesRE+"|"+numbersRE+"|"+     // RE for tokens
                                operatorsRE+"|"+symbolsRE;
  private Pattern tokens ;
  
  private final String intersticesRE =   "(\\s*(#.*)?)*";                   // "inter-token" stuff             
  private Pattern interstices;                    

  /* lexcical scanner for source file */
  private Scanner scanner;

  private static final String TRUE = "1"; // Numerical encoding for true/false
  private static final String FALSE = "0";// (Any non-zero signifies false.)

  public static void main(String args[])
  {  
     if (args.length == 0)
     {  System.out.println("Usage: TacScanner2 filename");
        System.exit(1);
     }
     String tacFilename = args[0]; 

     TacScanner2 sc = new TacScanner2(tacFilename);
     sc.scan();
     
  }

}