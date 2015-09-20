/**
 *  Interface for ADT Map
 *  @author Kieran Herley (following GT)
*/
import java.util.Iterator;

public interface Map<KeyType, ValueType>
//       extends java.lang.Iterable<Entry<KeyType, ValueType>>
{

  /**
  *  Return number of entries in this map.
  *  @return no. of entries
  */
  public int size();
  
  /**
  *  Return true if this map is empty, false otherwise.
  *  @param boolean emptiness flag
  */
  public boolean isEmpty();
  
  /**
  *  If map contains an entry with key equal to k, return the
  *  value of the entry, else return null.
  *  @param k -- the search key
  *  @return old value/null
  */
  public ValueType get(KeyType k);
  
  /**
     If map does not contain an entry with key equal to k, then add
     entry (k, e) to map nd return null; else, replace with e the
     existing value of the entry with key equal to k and return its
     old value.
     @param k-- the key
     @param e-- the value
     @return null/old value
  */
  public ValueType put(KeyType k, ValueType e);
  
  /**
     Remove from the map the entry with key equal to k and return its
     value; if the map has no such entry, return null.
     @param k -- the key
     @return value/null
  */
  public ValueType remove(KeyType k);
  
  /**
     Return an iterator containing the entries stored in the map.
     @return iterator of entries
  */
  public Iterator<Entry<KeyType, ValueType>> iterator();
  
}