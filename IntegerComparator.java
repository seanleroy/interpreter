
/**
*   Comparator class for Integers.
*   @author Kieran Herley 25/2/99
*/

public class IntegerComparator 
             implements Comparator<Integer>
{
   public IntegerComparator(){}

   /**
   *  Return negative, zero or position integer depending
   *  on whether a < b, a= b or a > b. Illegal is a and
   *  b cannot be compared.
   *  @param a-- first comparee
   *  @param b-- second comparee
   *  @return comparison result as int
   */
   public int compare(Integer a, Integer b)
   {  int aValue = a.intValue();
      int bValue = b.intValue();
      return (aValue - bValue);
   }
    
}
