
/**
*   Interface for ListIterator abstraction
*   @author Kieran Herley
*/
import java.util.ListIterator;

public class ABLListIterator<EltType>
       extends ABLIterator<EltType>
       implements ListIterator<EltType>      
{
   /**
   *  Create a list iterator for the specified list.
   */
   public ABLListIterator(ArrayBasedList<EltType> l)
   {  super(l);
   }
   
   /**
   *  Insert the specified element into the list
   *  immediately in front of the cursor.
   *  @param elt--the element to be added
   */
   public void add(EltType elt)
   {  int addIndex = this.nextIndex();
      lst.add(addIndex, elt);
   }
 
   /**
   *   Return true if this list iterator has one or
   *   more elements behind the cursor.
   *   @return boolean more-elements-backwards flag
   */
   public boolean hasPrevious()
   {  return (cursor > 0);
   }
     
   /**
   *  Return the index of the element that would be
   *  returned by a call to next. Illegal if no such 
   *  element.
   *   @return index of next list element
   */
   public int nextIndex()
   {  return cursor;
   }
   
   /**
   *   Remove and return the next element (in front of
   *   cursor) from this iterator. Illegal if the list is 
   *   empty.
   *   @return a list element
   */
   public EltType next()
   {  lastReturned = cursor;
      return lst.elements[cursor++];
   }
   
   /**
   *   Return the previous element (behind cursor) in
   *   this list iterator. Illegal if no such element.
   *   @return previous list elemnt
   */
   public EltType previous()
   {  if (!hasPrevious())
      {  flagError("Attempt to move past begining of list");
      }
      cursor--;
      lastReturned = cursor;
      return lst.elements[cursor];
   }
   
   /**
   *  Return the index of the element that would be
   *  returned by a call to previous. Illegal if no such 
   *  element.
   *   @return index of previous list element
   */
   public int previousIndex()
   {  if (!hasPrevious())
      {  flagError("Attempt to move past begining of list");
      }
      return (cursor - 1);
   }
   
   /**
   *  Remove from underlying collection the element
   *  most recently returned by the iterator. Optional
   *  operation. Assume cursor move between removes.
   */
   public void remove()
   { if (lastReturned == NO_SUCH_ELT)
     {  flagError("cannot remove--no such element");
     }
     else
     if (lastReturned != cursor) // last move forward
     {  cursor--;
     }
     lst.remove(lastReturned);
     lastReturned = NO_SUCH_ELT;
   }
   
   /**
   *  Replace the element most recently returned (by 
   *  next or previous) with o. Optional operation.
   */
   public void set(EltType o)
   {  lst.elements[lastReturned] = o;
   }
   
   /*
   *  Print the specified error message and abort.
   */
   private void flagError(String errMsg)
   {  System.out.println("ABLListIterator: "+errMsg);
      System.exit(1);
   }
   
   /* */
   private static final int NO_SUCH_ELT= -1;
   
   /* index of most recently returned element */
   private int lastReturned = NO_SUCH_ELT;
}
