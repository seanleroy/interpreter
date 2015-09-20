/**
 *  Interface for ADT Comparator.
 *  @author Kieran Herley (following GT)
*/
public interface Comparator<KeyType>
{
   /**
   *  Return negative, zero or position integer depending
   *  on whether a < b, a= b or a > b. Illegal if a and
   *  b cannot be compared.
   *  @param a-- first comparee
   *  @param b-- second comparee
   *  @return comparison result as int
   */
   public int compare(KeyType a, KeyType b);
}