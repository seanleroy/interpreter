
/**
*   Implementation of ADT Map based on on
*   ordered array.
*
*   @author Kieran Herley 
*/
import java.util.Iterator;

public class ArrayBasedMap2<KeyType, ValueType>
             extends ArrayBasedMap<KeyType, ValueType>
             implements Map<KeyType, ValueType>
{
 
   /**
   *   Construct an empty  map object with equality determined by 
   *   specified comparator.
   *   @param c: the comparator encoding the order
   */
   public ArrayBasedMap2(Comparator<KeyType> c)
   {  super(c);   
      
      /* Populate array 
      entries[0] = new MapEntry(2, 2);  
      entries[1] = new MapEntry(3, 3);  
      entries[2] = new MapEntry(5, 5);  
      entries[3] = new MapEntry(7, 7);  
      entries[4] = new MapEntry(11, 11);  
      entries[5] = new MapEntry(13, 13);  
      entries[6] = new MapEntry(17, 17);  
      entries[7] = new MapEntry(23, 3); 
      numEntries = 8; 
     */
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
   {  System.out.println("Put "+k);
      Entry<KeyType, ValueType> newEntry = 
         new MapEntry<KeyType, ValueType>(k, e);
      int index = findEntry(k);
      System.out.println("findEntry returns "+index);
      if (index != NO_SUCH_KEY)
      {  ValueType oldVal = entries[index].getValue();
	 entries[index] = newEntry;
	 return oldVal;
      }
      else
      {  expandIfNecessary();          
	 int i = findEntryLocation(k);
         System.out.printf("Shifting slots %d to %d rightwards \n", size()-1, i-1);
	 for (int j = size(); j > i; j--)
	 {  entries[j] = entries[j-1];
	 }
	 entries[i] = newEntry;
	 numEntries++;
      }
      return null;
   }
 
     
   /*
   *   Return index bearing the specified key value; 
   *   return NO_SUCH_KEY if no such key exists.
   *   @param key the key value
   *   @return index of key/NO_SUCH_KEY
   *   ****** RECURSIVE VERSION__ NOT USED *****
   */
   private int findEntryRec(KeyType key)
   {  return findEntryRec(key, 0, size()-1);
   }
   
   /*
   * Return index of specified key within entries[low..high]
   * or return NO_SUCH_KEY if key does not occur within this
   * subarray.
   * ******* RECURSIVE VERSION-- NOT USED ******
   */
   private int findEntryRec(KeyType key, int low, int high)
   {  if (low > high)
      {  return NO_SUCH_KEY;
      }
      else
      {  int mid = (low + high)/2;
         Entry<KeyType, ValueType> e = entries[mid];
	 int compResult = comp.compare(key, e.getKey());
	 if (compResult == 0)
	 {  return mid; 
	 }
	 else
	 if (compResult < 0)
	 {  return findEntryRec(key, low, mid-1);
	 }
	 else
	 {  return findEntryRec(key, mid+1, high);
	 }
      }
   }
   
   /*
   * Return index of specified key within entries[low..high]
   * or return NO_SUCH_KEY if key does not occur within this
   * subarray.
   */
   private int findEntry(KeyType key)
   {  int location = findEntryLocation(key);
      if ( this.isEmpty() || 
           comp.compare(key, entries[location].getKey()) != 0)
         return NO_SUCH_KEY;
      else
         return location;
   }

   /*
    * Return the index of the searchkey key within entries[]
    * or index that *could* accomodate search key if it were
    * to be inserted.
   */
   public int findEntryLocation(KeyType key)
   {  int low = 0;
      int high = this.size()-1;

      // Handle special case where key alrger than all existing keys
      if (comp.compare(key, entries[high].getKey()) > 0)
         return this.size();

      while (low < high)
      {  int mid = (low + high)/2;
         
         Entry<KeyType, ValueType> e = entries[mid];
	 int compResult = comp.compare(key, e.getKey());
	 if (compResult == 0)
	 {  return mid;
	 }
	 else
	 if (compResult < 0)
	 {  high = mid -1;
	 }
	 else
	 {  low = mid + 1;
	 }
      }
      return low;
   }

   
   /*
   *   Issue specified error message and abort.
   *   @param errmsg-- the error message
   */
   private void flagError(String errmsg)
   {   System.out.println("ArrayBasedMap2: "+errmsg);
       System.exit(1);
   }
   
   public static void main(String [] args)
   {  ArrayBasedMap2<Integer, Integer> mp
      = new ArrayBasedMap2<Integer, Integer>(new IntegerComparator());
      
      mp.print();

      System.out.printf("findEntryLocation %d returns %d\n", 
                  2, mp.findEntryLocation(2));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  3, mp.findEntryLocation(3));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  5, mp.findEntryLocation(5));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  7, mp.findEntryLocation(7));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  11, mp.findEntryLocation(11));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  13, mp.findEntryLocation(13));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  17, mp.findEntryLocation(17));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  23, mp.findEntryLocation(23));

      System.out.printf("findEntryLocation %d returns %d\n", 
                  1, mp.findEntryLocation(1));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  4, mp.findEntryLocation(4));
      System.out.printf("findEntryLocation %d returns %d\n", 
                  24, mp.findEntryLocation(24));
 
      
   
   }
         
}
