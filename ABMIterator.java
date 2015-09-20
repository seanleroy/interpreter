
/**
*   Implmentation for Iterator of entries
*   abstraction for ArrayBasedMap
*   @author Kieran Herley
*/
import java.util.Iterator;

public class ABMIterator<KeyType, ValueType>
       implements Iterator<Entry<KeyType, ValueType>>
{
   /**
   *  Create an iterator (of keys) for map m.
   */
   public ABMIterator(ArrayBasedMap<KeyType, ValueType> m)
   {  cursor = 0;
      mp = m;
   }
 
   /**
   *   Return true if this iterator has one or more
   *   elements in front of the cursor.
   *   @return boolean non-emptiness indication
   */
   public boolean hasNext()
   {  return (cursor < mp.numEntries);
   }
   
   /**
   *   Remove and return the next element (in front of
   *   cursor) fro
   m this iterator. Illegal if the list is 
   *   empty.
   *   @return a list elemnt
   */
   public Entry<KeyType, ValueType> next()
   {  return mp.entries[cursor++];
   }
   
   /**
   *  Remove from underlying collection the element
   *  most recently returned by the iterator. Optional
   *  operation.
   */
   public void remove()
   {  mp.remove(mp.entries[cursor--].getKey());
   }
   
   /* the cursor-th value is the next to be returned */
   private int cursor;
   
   /* the map whose vaklues this iterator operates upon */
   private ArrayBasedMap<KeyType, ValueType> mp;
}
