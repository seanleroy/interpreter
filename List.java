
/**
 *  Interface for ADT List
 *  @author Kieran Herley
 */
import java.util.Iterator;
import java.util.ListIterator;

public interface List<EltType>
                 extends java.lang.Iterable<EltType>
           
{
  /**
   * Return the number of elements in this sequence
   * @return size of list
   */
   public int size();
   
   /**
   * Return true if this list is empty, false otherwise.
   * @return emptiness flag
   */
   public boolean isEmpty();
   
   /**
   * Return the element at specified index. Illegal if
   * no such index exists.
   * @param inx-- the index of required element
   * @return the corresponding element
   */
   public EltType get(int inx);
   
   /**
   * Replace the element at specified index with newElt.
   * Return the old element at that index. Illegal if no
   * such index exists.
   * @param inx-- the index of repalcement location
   * @param newElt-- the new element for that location
   * @return the old element at the location
   */
   public EltType set(int inx, EltType newElt);
   
   /**
   * Add element newElt at the end of the list.
   * @param newElt-- the element to be added
   */
   public void add(EltType newElt);
   
   /**
   * Add element newElt to the list at index inx. Illegal
   * if inx is negative or greater than current list size.
   * @param inx-- index of the  insertion location
   * @param newElt-- the element to be inserted
   */
   public void add(int inx, EltType newElt);
   
   /**
   * Remove the element at the specified index from the
   * list and return it. Illegal if no such index exists.
   * @param inx-- the index of the removal location
   * @return the element at that location
   */
   public EltType remove(int inx);
    
   /**
   * Return an iterator of the elements of this list.
   * @return list elements (as Iterator)
   */
   public Iterator<EltType> iterator();
   
   /**
   * Return an iterator of the elements of this list.
   * @return list elements (as Iterator)
   */
   public ListIterator<EltType> listIterator();
   
}