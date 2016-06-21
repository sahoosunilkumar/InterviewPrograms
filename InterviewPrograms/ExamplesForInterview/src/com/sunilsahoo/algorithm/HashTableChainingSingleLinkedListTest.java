package com.sunilsahoo.algorithm;

/*
 *    Java Program to Implement Hash Tables chaining with Singly Linked Lists
 *    
 *    This is a Java Program to implement hash tables chaining with Singly Linked List. A hash table (also hash map) is a data structure used to implement an associative array, a structure that can map keys to values. A hash table uses a hash function to compute an index into an array of buckets or slots, from which the correct value can be found. In order to prevent collision, hash tables are chained with another data structure ( Singly Linked List in this case ).
Here is the source code of the Java program to implement hash tables chaining with Singly Linked List. The Java program is successfully compiled and run on a Windows system. The program output is also shown below.
 */
 
import java.util.Scanner;
 
/* Node for singly linked list */
class SLLNode
{
    SLLNode next;
    int data;
 
    /* Constructor */
    public SLLNode(int x)
    {
        data = x;
        next = null;
    }
}
 
/* Class HashTableChainingSinglyLinkedList */
class HashTableChainingSinglyLinkedList
{
    private SLLNode[] table;
    private int size ;
 
    /* Constructor */
    public HashTableChainingSinglyLinkedList(int tableSize)
    {
        table = new SLLNode[ nextPrime(tableSize) ];
        size = 0;
    }
    /* Function to check if hash table is empty */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /* Function to clear hash table */
    public void makeEmpty()
    {
        int l = table.length;
        table = new SLLNode[l];
        size = 0;
    }
    /* Function to get size */
    public int getSize()
    {
        return size;
    }
    /* Function to insert an element */
    public void insert(int val)
    {
        size++;
        int pos = myhash(val);        
        SLLNode nptr = new SLLNode(val);                
        if (table[pos] == null)
            table[pos] = nptr;            
        else
        {
            nptr.next = table[pos];
            table[pos] = nptr;
        }    
    }
    /* Function to remove an element */
    public void remove(int val)
    {
        int pos = myhash(val);    
        SLLNode start = table[pos];
        SLLNode end = start;
        if (start.data == val)
        {
            size--;
            table[pos] = start.next;
            return;
        }
        while (end.next != null && end.next.data != val)
            end = end.next;
        if (end.next == null)
        {
            System.out.println("\nElement not found\n");
            return;
        }
        size--;
        if (end.next.next == null)
        {
            end.next = null;
            return;
        }
        end.next = end.next.next;
        table[pos] = start;
    }
    /* Function myhash */
    private int myhash(Integer x )
    {
        int hashVal = x.hashCode( );
        hashVal %= table.length;
        if (hashVal < 0)
            hashVal += table.length;
        return hashVal;
    }
    /* Function to generate next prime number >= n */
    private static int nextPrime( int n )
    {
        if (n % 2 == 0)
            n++;
        for ( ; !isPrime( n ); n += 2);
 
        return n;
    }
    /* Function to check if given number is prime */
    private static boolean isPrime( int n )
    {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
    public void printHashTable ()
    {
        System.out.println();
        for (int i = 0; i < table.length; i++)
        {
            System.out.print ("Bucket " + i + ":  ");             
            SLLNode start = table[i];
            while(start != null)
            {
                System.out.print(start.data +" ");
                start = start.next;
            }
            System.out.println();
        }
    }   
}
 
/* Class HashTableChainingSinglyLinkedListTest */
public class HashTableChainingSingleLinkedListTest
{ 
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size"); 
        /* Make object of HashTableChainingSinglyLinkedList */
        HashTableChainingSinglyLinkedList htcsll = new HashTableChainingSinglyLinkedList(scan.nextInt() );
 
        char ch;
        /*  Perform HashTableChainingSinglyLinkedList operations  */
        do    
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. clear");
            System.out.println("4. size"); 
            System.out.println("5. check empty");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                htcsll.insert( scan.nextInt() ); 
                break;                          
            case 2 :                 
                System.out.println("Enter integer element to delete");
                htcsll.remove( scan.nextInt() ); 
                break;                        
            case 3 : 
                htcsll.makeEmpty();
                System.out.println("Hash Table Cleared\n");
                break;
            case 4 : 
                System.out.println("Size = "+ htcsll.getSize() );
                break; 
            case 5 : 
                System.out.println("Empty status = "+ htcsll.isEmpty() );
                break;        
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /* Display hash table */ 
            htcsll.printHashTable();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}
