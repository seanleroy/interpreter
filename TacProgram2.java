/**
   Three-address code program class
   @author Kieran Herley, Nov 2011
*/

import java.util.*;

public class TacProgram2
   extends ArrayList<TacInstruction2>
{

   /**
      Add specified instruction to end of this program.
      @param inst-- instruction to be added
   */
   public void append(TacInstruction2 instr)
   {  this.add(instr);
   }




   public static void main(String args[])
   {  TacProgram2 prog = new TacProgram2();

      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.READ, "x", "", "", "", ""));
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.LABEL, "", "", "", "", "L0"));
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.WRITE, "x", "", "", "", ""));
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.THREEADDR, "x", "x", "1", "-", ""));  
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.WRITE, "x", "", "", "", ""));
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.JUMPTRUE, "x", "", "", "", "L0"));
      prog.add(new TacInstruction2(TacInstruction2.InstructionKind.HALT, "", "", "", "", ""));

   }


}