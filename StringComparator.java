/**
*   Comparator class for Strings.
*   @author Kieran Herley August, 2004
*/

public class StringComparator 
   implements Comparator<String>
{

   public StringComparator(){}
   
   /**
   *  Return negative, zero or position integer depending
   *  on whether a < b, a= b or a > b. Illegal is a and
   *  b cannot be compared.
   *  @param a-- first comparee
   *  @param b-- second comparee
   *  @return comparison result as int
   */
   public int compare(String a, String b)
   {  checkIfComparable(a); checkIfComparable(b);
      int compResult = a.compareTo(b);
      return compResult ;
   }
   
   /*
   *  Check that a is of type String; abort otherwise.
   */   
   public boolean checkIfComparable(Object a)
   {  if (a == null)
         return false;
      else
      {  try {String b = (String) a;}
         catch (ClassCastException e)
         {  emit("non-String argument");
	    return false;
	 }
         return true;
      }
   }
   
   private void emit(String errmsg)
   {
      System.out.println("StringComparator: "+errmsg);
      System.exit(1);
   }
   
}
