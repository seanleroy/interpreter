/**
   Execution engine for three-address code programs. Minimal
   error detection.
     
   Syntax:
      x := y op z;
      x := y;
      read x;
      write x;
      if ( x == 0 ) goto l;  
      if (x != 0 ) goto l;
      goto l;
      l:
      halt;
      # This is a comment

   Notes:
   1) Symbol := denotes assignment
   2) Instructions terminated by semicolons
   3) Identifiers: alphanumeric strings, beginning with letter; case sensitive
   4) Jump labels: as above; must be unique; no terminating semicolon
   5) Comments: begin with #, extend to end of line


   @author Kieran Herley, Nov 2011
*/

import java.util.*;

public class TacExecutionEngine
{

   /**
      Create this (initially empty) program.
   */
   public TacExecutionEngine(TacProgram2 prog)
   {  program = prog;

      /* Initialize other data structures here */
 
      input = new Scanner(System.in);
   }


   /**
      Execute this program.
   */
   public void execute()
   {  
	/* Insert code to execute program here */
	int programIndex = 0;
	int labelIndex =0;
	
	// run through program to get the labels
	while( labelIndex < program.size() ) {
		currentInstruction = program.get(labelIndex);
		label = currentInstruction.getLabel();
		if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.LABEL ) {
			
			labelInstructions.put( label, labelIndex );
			//currentInstruction.show();
		}
		labelIndex++;
	}
	
	while ( programIndex < program.size() ) {
		
		currentInstruction = program.get(programIndex);
		addrInstruction1 = currentInstruction.getAddr1();
		addrInstruction2 = currentInstruction.getAddr2();
		addrInstruction3 = currentInstruction.getAddr3();
		label = currentInstruction.getLabel();
		operator = currentInstruction.getOp();
		
		if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.READ ) {
		
			currentInstruction.show();
			read();  
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.THREEADDR ) {
		
			//currentInstruction.show();
			threeaddr();
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.TWOADDR ) {
		
			//currentInstruction.show();
			twoaddr();
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.JUMPTRUE ) {
			value1 = ( int ) instructionMap.get( addrInstruction1 );
			// compare value with zero
			if ( isEqualZero(value1) == false ) {
				// jump to label
				programIndex = ( int ) labelInstructions.get( label ) ; 
				currentInstruction.show();
				// print true or false
				System.out.println(isEqualZero(value1));
			}
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.JUMPFALSE ) {
			value1 = ( int ) instructionMap.get( addrInstruction1 );
			if ( isEqualZero(value1) == true ) {
				// jump to label
				programIndex = ( int ) labelInstructions.get( label ) ; 
				currentInstruction.show();
				// print true or false
				System.out.println(isEqualZero(value1));
			}
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.GOTO ) {
			// jump to label
			programIndex = ( int ) labelInstructions.get( label );
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.WRITE ) {
			// write instruction
			currentInstruction.show();
			write();
			
		} else if ( currentInstruction.getKind() == TacInstruction2.InstructionKind.HALT ) {
			currentInstruction.show();
		} 
			programIndex++;
		
	}

   }

   /* LOTS OF LOVELY CODE HERE . . . . */
   
   public boolean isEqualZero( int value1) {
	
	if ( value1 == 0 ) {
		return true;
	} else {
		return false;
	}
   
   }
   
   /**
      Get user input
   */
   public void read() {
	// ask user to enter value
	 System.out.print( "Please enter a value for " + addrInstruction1 + " : " );
	 // read input from user
	 value1 = input.nextInt();
	 // place result in map
	 instructionMap.put( addrInstruction1, value1 ); 
	 System.out.println();
   }
   
   /**
      write instruction to console
   */
   public void write() {
	// search map with key and print value
	if ( isInteger( addrInstruction1 ) == false ) {
	System.out.println( addrInstruction1 + " = " + instructionMap.get( addrInstruction1 ) );
		
	} else {
		System.out.println( "value" + " = " + addrInstruction1 );
	}
	System.out.println();
   }
   
   public void threeaddr() {
   
	if ( isInteger( addrInstruction2 ) == true ) {
		value1 = Integer.parseInt( addrInstruction2 );
	} else {
		value1 = ( int ) instructionMap.get( addrInstruction2 );
	}
	
	if ( isInteger( addrInstruction3 ) == true ) {
		value2 = Integer.parseInt( addrInstruction3 );
	} else {
		value2 =( int ) instructionMap.get( addrInstruction3 );
	}

	 
	operations( operator );
	instructionMap.put( addrInstruction1,  result );
   
   }
   
   /** 
	Two adder instruction
  */
    public void twoaddr() {
	
	if ( isInteger( addrInstruction2 ) == true ) {
		value1 = Integer.parseInt( addrInstruction2 );
		instructionMap.put( addrInstruction1,  value1 );
	} else {
		value1 =( int ) instructionMap.get( addrInstruction2 );
		instructionMap.put( addrInstruction1,  value1 );
	}

   }
   
   /** 
	Check if a string can be converted to an int.
	
	@param str the string to be converted.
  */
   public boolean isInteger( String str ) {
   
	try {
		Integer.parseInt( str );
		return true;
	} catch ( NumberFormatException e ) {
		return false;
	}
   
   }
   
   // various operations and results
   public void operations( final String op ) {
	switch( op ) {
		case PLUS : 
			result =  value1 + value2;
			break;
		case MINUS :
			result = value1 - value2;
			break;
		case MULTIPLY : 
			result = value1 * value2;
			break;
		case DIVIDE :
			result = value1 / value2;
			break;
		case LESSTHAN :
			if ( value1 < value2 ) {
				result = value2;
			} else {
				result = value1;
			}
			break;
		case LESSEQUAL :
			if ( value1 <= value2 ) {
				result = value2;
			} else if ( value1 == value2 ){
				result = -1;
			}
			break;
		case EQUALS :
			if ( value1 > value2 ) {
				result = value2;
			} else if ( value1 == value2 ){
				result = -1;
			}
			break;
		default :
			System.out.println( "Incompatible Operator!" );
	}
	
   }

	/* The instructions comprising this program */
	private TacProgram2 program;

	/* Insert declarations of other data stucrures here */
	Map labelInstructions = new ArrayBasedMap( new StringComparator() );
	Map instructionMap = new ArrayBasedMap<String, Integer>( new StringComparator() );
	private Scanner input;
	private TacInstruction2 currentInstruction;
	private String addrInstruction1;
	private String addrInstruction2;
	private String addrInstruction3;
	private String label;
	private String operator;
	private String line;
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String LESSTHAN = "<";
	private static final String LESSEQUAL = "<=";
	private static final String EQUALS = "==";
	private int value1;
	private int value2;
	private int result;
   
   
	/* Input source */
	

   public static void main(String args[])
   {  System.out.println("TacExecutionEngine v1.0: KTH 11/11/'11");
      System.out.println("Beta version");
      if (args.length < 1 )
      {  System.err.println("Usage: java TacExecutionEngine filename");
         System.exit(1);
      } 
      String sourceFile = args[0];

      /* Scan source file and creat TAC program */
      TacScanner2 scanner = new TacScanner2(sourceFile);
      TacProgram2 prog = scanner.scan();
      System.out.println("*** Scanning complete; execution commencing ***");
	System.out.println();
      /* Create execution environment and execute program */
      TacExecutionEngine engine = new TacExecutionEngine(prog);
      engine.execute();     
      System.out.println();
      System.out.println("*** Execution complete                      ***");
      
      
      
   }




}
