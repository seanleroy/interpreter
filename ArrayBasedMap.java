
/**
*   Array-based implementation of ADT Map.
*
*   @author Kieran Herley 
*/
import java.util.Iterator;

public class ArrayBasedMap<KeyType, ValueType>
             implements Map<KeyType, ValueType>
{
   /**
   *   Construct an empty  map object with equality
   *   determined by the .equals of KeyType.
   */
   public ArrayBasedMap()
   {  entries = (Entry<KeyType, ValueType>[]) new MapEntry [INIT_CAPACITY];
      capacity = INIT_CAPACITY;
      comp = null;
      numEntries = 0;       
   }

   /**
   *   Construct an empty  map object with equality determined by 
   *   specified comparator.
   *   @param c: the comparator encoding the order
   */
   public ArrayBasedMap(Comparator<KeyType> c)
   {  this();  
      comp = c;      
   }
         
   /**
   *   Return number of items in map.
   *   @return the number of items.
   */   
   public int size()
   {   return numEntries;
   }
   
   /**
   *   Return true if map empty, false otherwise.
   *   @return true or false
   */   
   public boolean isEmpty()
   {   return (numEntries == 0);
   }
    
   /**
     If map contains an entry with key equal to k, return the
     value of the entry, else return null.
     @param k -- the search key
     @return old value/null
   */
   public ValueType get(KeyType k)
   {  int indexWithKey = findEntry(k);
      if (indexWithKey != NO_SUCH_KEY)
         return (entries[indexWithKey]).getValue();
      else
         return null;
   }
 
   /**
     If map does not contain an entry with key equal to k, then add
     entry (k, e) to map nd return null; else, replace with e the
     existing value of the entry with key equal to k and return its
     old value.
     @param k-- the key
     @param e-- the value
     @return null/old value
   */
   public ValueType put(KeyType k, ValueType e)  
   {  Entry<KeyType, ValueType> newEntry = 
         new MapEntry<KeyType, ValueType>(k, e);
      int index = findEntry(k);
      if (index != NO_SUCH_KEY)
      {  ValueType oldVal = entries[index].getValue();
	 entries[index] = newEntry;
	 return oldVal;
      }
      else
      {  expandIfNecessary();          
         entries[numEntries++] = newEntry;
      }
      return null;
   }

   /**
     Remove from the map the entry with key equal to k and return its
     value; if the map has no such entry, return null.
     @param k -- the key
     @return value/null
   */
   public ValueType remove(KeyType k)  
   {  int location = findEntry(k);
      if (location == NO_SUCH_KEY)
         return null;
      else
      {  Entry<KeyType, ValueType> removedEntry = entries[location];
         entries[location] = entries[numEntries-1];
         numEntries--;
         return removedEntry.getValue();   
      }
   }
   
  /**
     Return an iterator containing the entries stored in the map.
     @return iterator of entries
   */
   public Iterator<Entry<KeyType, ValueType>> iterator()
   {  return new ABMIterator<KeyType, ValueType>(this);
   }
   
   
   /*
   *  Return true if keys k1 and k2 are equal
   */
   private boolean isEqualTo(KeyType k1, KeyType k2)
   {  if (comp == null)
         return (k1.equals(k2));
      else
         return (comp.compare(k1, k2) == 0);
   }
   
   /*
   *   Return index bearing the specified key value; 
   *   return -1 is no such key exits.
   *   @param key the key value
   *   @return node with closest key.
   */
   private int findEntry(KeyType key)
   {  for (int i = 0; i < numEntries; i++)
      {  if (isEqualTo(key, entries[i].getKey()))
	 {  return i;
	 }
      }
      return NO_SUCH_KEY;
   }
   
   /*
   *  If the array is full, double its size.
   */
   protected void expandIfNecessary()
   {  if (numEntries == capacity)
      {  int newCapacity = 2*capacity;
         Entry<KeyType, ValueType> [] temp = 
	    (Entry<KeyType, ValueType> []) new MapEntry[newCapacity];
         for (int i = 0; i < capacity; i++)
	 {  temp[i] = entries[i]; 
	 }           
         entries = temp;
         capacity = newCapacity;
      }   
   }
   
   /*
   *   Print the keys of the entries in the map.
   */
   public void print()
   {   
      System.out.println("<");
      for (int i = 0; i < numEntries; i++)
      {   
         System.out.print("(");
         try
         {
            System.out.print(
            ((entries[i]).getKey()).toString()
            );
         }
         catch(Exception e)
         {
            System.out.print("???");
         }
         System.out.print(", ");
         try
         {
            System.out.print(
            ((entries[i]).getValue()).toString()
            );
         }
         catch(Exception e)
         {
            System.out.print("???");
         }
         System.out.println(")");
      }
      System.out.println("numEntries = "+numEntries);
      System.out.println(">");
   }
   
   /*
   *   Issue specified error message and abort.
   *   @param errmsg-- the error message
   */
   private void flagError(String errmsg)
   {   System.out.println("ArrayBasedMap: "+errmsg);
       System.exit(1);
   }
   
   /* the initial capacity of the map */
   protected static final int INIT_CAPACITY = 100;
   
   /* value denoting unsucessful search */
   protected static final int NO_SUCH_KEY = -1;
   
   /* cpmparator that defines equality between keys */
   protected Comparator<KeyType> comp;
   
   /* the entries that make up the map */
   protected Entry<KeyType, ValueType> [] entries;
   
   /* the number of entries currently in the map */
   protected int numEntries;
   
   /* the current capacity of the map */
   protected int capacity ;
      
}
