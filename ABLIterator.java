
/**
*   Implementation for Iterator abstraction on List ADT
*   @author Kieran Herley
*/
import java.util.Iterator;

public class ABLIterator<EltType>
             implements Iterator<EltType>
{
   /**
   *  Create an iterator for the specified list.
   */
   public ABLIterator(ArrayBasedList<EltType> l)
   {  lst = l;
      cursor = 0;
   }
 
   /**
   *   Return true if this iterator has one or more
   *   elements in front of the cursor.
   *   @return boolean non-emptiness indication
   */
   public boolean hasNext()
   {  return (cursor < lst.numElts);
   }
   
   /**
   *   Remove and return the next element (in front of
   *   cursor) from this iterator. Illegal if the list is 
   *   empty.
   *   @return a list element
   */
   public EltType next()
   {  if (!this.hasNext())
      {  System.out.println("ABLIterator: attempt to access non-existent element ");
         System.exit(1);
      }
      return lst.elements[cursor++];
   }
   
   /**
   *  Remove from underlying collection the element
   *  most recently returned by the iterator. Optional
   *  operation.
   */
   public void remove()
   {  if (cursor == 0)
      {  System.out.println("cannot remove-- no such element");
         System.exit(1);
      }
      lst.remove(cursor-1);
   }
   
   /* the list upon which this iterator operates */
   protected ArrayBasedList<EltType> lst;
   
   /* the index of element immediately in front of cursor */
   protected int cursor;
}
