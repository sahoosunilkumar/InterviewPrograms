package com.sunilsahoo.collection;

/**
 * We next consider a queue-like data structure that supports insertion and
 * deletion at both the front and the back of the queue. Such a structure is
 * called a double- ended queue, or deque, which is usually pronounced “deck” to
 * avoid confusion with the dequeue method of the regular queue ADT, which is
 * pronounced like the abbreviation “D.Q.” The deque abstract data type is more
 * general than both the stack and the queue ADTs. The extra generality can be
 * useful in some applications. For example, we described a restaurant using a
 * queue to maintain a waitlist. Occasionally, the first person might be removed
 * from the queue only to find that a table was not available; typically, the
 * restaurant will reinsert the person at the first position in the queue. It
 * may also be that a customer at the end of the queue may grow impatient and
 * leave the restaurant. (We will need an even more general data structure if we
 * want to model customers leaving the queue from other positions.)
 * 
 * 
 * Our Deque ADT Interface java.util.Deque throws exceptions returns special
 * value first( ) getFirst( ) peekFirst( ) last( ) getLast( ) peekLast( )
 * addFirst(e) addFirst(e) offerFirst(e) addLast(e) addLast(e) offerLast(e)
 * removeFirst( ) removeFirst( ) pollFirst( ) removeLast( ) removeLast( )
 * pollLast( ) size( ) size( ) isEmpty( ) isEmpty( )
 * 
 * @author sunilkumarsahoo
 *
 */
public class CustomDoubleEndedQueueExample {

}
