/**
*  Interface for ADT Entry.
*  @author Kieran Herley (adapted from  GT)
*/
public interface Entry<KeyType, ValueType>
{
   /**
   *  Return the key component of this entry.
   *  @return key
   */
   public KeyType getKey();
   
   /**
   *  Return the value of this entry.
   *  @return value
   */
   public ValueType getValue();
}