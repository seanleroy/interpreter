/**
   Class definition for individual three-address code instruction.

   @author Kieran Herley, Nov 2011
*/

public class TacInstruction2
{

   /**
      Create 
   */
   public TacInstruction2(TacInstruction2.InstructionKind k, 
                         String a1, String a2, String a3, 
                         String o, String l)
   {  kind = k;
      addr1 = a1;
      addr2 = a2;
      addr3 = a3;
      op = o;
      label = l;
   }

   /**
      Return stringification of this instruction.
      @return instruction as a String
   */
   public String toString()
   {  switch (kind)
      {  case THREEADDR : 
              return addr1+" := " +addr2+" "+op+" "+addr3+";"; 
         case  TWOADDR : 
              return addr1+" := "+addr2+";"; 
         case GOTO:
              return "goto "+label+";";
         case JUMPTRUE : 
              return "if ( "+addr1+" != 0) goto "+label+";"; 
         case JUMPFALSE : 
              return "if ( "+addr1+" == 0) goto "+label+";"; 
         case READ : 
              return "read "+addr1+";"; 
         case WRITE : 
              return "write "+addr1+";"; 
         case HALT : 
              return "halt;"; 
         case LABEL : 
              return label+":"; 
      }
      return "";
   }

   /**
      Print stringification of this instruction.
   */
   public void show()
   {  System.out.println(this.toString());
   }

   /**
      Return kind of this instruction.
   */
   public TacInstruction2.InstructionKind getKind()
   {  return kind;
   }

   /**
      Return first address of this instruction, empty if n/a.
   */
   public String getAddr1()
   {  return addr1;
   }

   /**
      Return second address of this instruction, empty if n/a.
   */
   public String getAddr2()
   {  return addr2;
   }

   /**
     Return third address of this instruction, empty if n/a.
   */
   public String getAddr3()
   {  return addr3;
   }

   /**int i = 0; i <  program.size(); i++ 
     Return operation of this instruction, empty if n/a.
   */
   public String getOp()
   {  return op;
   }

   /**
     Return label of this instruction, empty if n/a.
   */
   public String getLabel()
   {  return label;
   }


   /* The different kinds of instructions */
   public enum InstructionKind
   {  THREEADDR,   // x = y + z
      TWOADDR,     // x = y
      GOTO,        // goto l
      JUMPTRUE,    // if (x == 1) goto l
      JUMPFALSE,   // if (x == 0) goto l
      READ,        // read(x)
      WRITE,       // write(x)
      HALT,        // halt
      LABEL        // l:
   }

   // The kind of this instruction
   private TacInstruction2.InstructionKind kind;
   
   // The three addresses of this instruction; empty string if n/a
   private String addr1;
   private String addr2;  
   private String addr3;  

   // The operation of this TAC instruction (empty if n/a)
   private String op;

   // The label of this TAC instruction (empty if n/a)
   private String label;

}