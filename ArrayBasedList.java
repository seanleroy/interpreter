/**
*  Arrar-based implmentation for ADT List
*  @author Kieran Herley (adapted from GT)
*/
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayBasedList<EltType>
             implements List<EltType>
{
   /**
   * Create an empty list.
   */
   public ArrayBasedList(int initCapacity)
   {  numElts = 0;
      capacity =  initCapacity;
      elements = (EltType[]) (new Object[capacity]);
   }
   
   /**
   * Create an empty list.
   */
   public ArrayBasedList()
   {  this(INIT_CAP);
   }
   /**
   * Return the number of elements in this sequence
   * @return size of list
   */
   public int size()
   {  return numElts;
   }
   
   /**
   * Return true if this list is empty, false otherwise.
   * @return emptiness flag
   */
   public boolean isEmpty()
   {  return (numElts == 0);
   }
   
   /**
   * Return the element at specified index. Illegal if
   * no such index exists.
   * @param inx-- the index of required element
   * @return the corresponding element
   */
   public EltType get(int inx)
   {  if ((inx < 0) || (numElts-1 < inx))
      {  flagError("index out of bounds");
      }
      return elements[inx];
   }
   
   /**
   * Replace the element at specified index with newElt.
   * Return the old element at that index. Illegal if no
   * such index exists.
   * @param inx-- the index of repalcement location
   * @param newElt-- the new element for that location
   * @return the old element at the location
   */
   public EltType set(int inx, EltType newElt)
   {  if ((inx < 0) || (numElts-1 < inx))
      {  flagError("index out of bounds");
      }
      EltType oldElt = elements[inx];
      elements[inx] = newElt;
      return oldElt;
   }
   
   /**
   * Add element newElt at the end of the list.
   * @param newElt-- the element to be added
   */
   public void add(EltType newElt)
   {  expandIfNecessary();
      elements[numElts] = newElt;
      numElts++;
   }
   
   /**
   * Add element newElt to the list at index inx. Illegal
   * if inx is negative or greater than current list size.
   * @param inx-- index of the  insertion location
   * @param newElt-- the element to be inserted
   */
   public void add(int inx, EltType newElt)
   {  checkIndex(inx, size() + 1);
      expandIfNecessary();
      for (int i = numElts-1; i >= inx ; i--)
      {  elements[i+1] = elements[i];
      }
      elements[inx] = newElt;
      numElts++;
   }
   
   /**
   * Remove the element at the specified index from the
   * list and return it. Illegal if no such index exists.
   * @param inx-- the index of the removal location
   * @return the element at that location
   */
   public EltType remove(int inx)
   {  checkIndex(inx, size());
      EltType retElt = elements[inx];
      for (int i = inx; i < numElts -1 ; i++)
      {  elements[i] = elements[i+1];
      }
      numElts--;
      return retElt;
   }
    
   /**
   * Return an iterator of the elements of this list.
   * @return list elements (as Iterator)
   */
   public Iterator<EltType> iterator()
   {  return new ABLIterator<EltType>(this);
   }
   
   /**
   * Return an iterator of the elements of this list.
   * @return list elements (as Iterator)
   */
   public ListIterator<EltType>  listIterator()
   {  return new ABLListIterator<EltType>(this);
   }
   
   /*
   *  Check that inx is within bounds (i.e. < highInx)
   *  and issue error message if not.
   */
   private void checkIndex(int inx, int highInx)
   {  if ((inx < 0) || (inx >= highInx))
      {  flagError("index out of bounds");
      }
   }
   
   /*
   * If the array is full, double its capacity.
   */
   private void expandIfNecessary()
   { if (size() == capacity)   
     {  EltType temp[] = 
	   (EltType[])(new Object[2*capacity]);
        for (int i = 0; i < capacity; i++)
	{  temp[i] = elements[i];
	}
        elements = temp;
        capacity = 2*capacity;
     }
   }
   
   /*
   *   Issue specified error message and abort.
   *   @param errmsg-- the error message
   */
   private void flagError(String errmsg)
   {   System.out.println("ArrayBasedList: "+errmsg);
       System.exit(1);
   }  
   
   /* default capacity of the deque */
   private static final int  INIT_CAP = 10; 
   
   /* maximum current  capacity of the deque */
   private int capacity;                    
   
   /* the deque elemets */
   protected EltType elements[];                      
   
   /* num of elemnts in deque */
   protected int numElts; 
   
}