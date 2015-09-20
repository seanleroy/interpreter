
/**
*   Implementation of Entry abstraction.
*   @author Kieran Herley
*/

public class MapEntry<KeyType, ValueType>
       implements Entry<KeyType, ValueType>
{

   /**
   *   Create new entry with specified key and value.
   *   @param k: the key
   *   @param e: the value
   */
   public MapEntry(KeyType k, ValueType e)
   {  key = k;
      value = e;
   }

   /**
   *   Return key of this entry.
   *   @return the key
   */
   public KeyType getKey()
   {   return key;
   }
   
   /**
   *   Return value of this entry.
   *   @return the value
   */
   public ValueType getValue()
   {   return value;
   }
   
   /**
   *   Set key of this entry to k.
   *   @param k-- the new key 
   */
   public void setKey(KeyType k)
   {   key = k;
   }
   
   /**
   *   Set value of this entry to v.
   *   @return the value
   */
   public void setValue(ValueType v)
   {   value = v;
   }
   
   /* the key of this entry */
   private KeyType key;
   
   /* the value of this entry */
   private ValueType value;
}
