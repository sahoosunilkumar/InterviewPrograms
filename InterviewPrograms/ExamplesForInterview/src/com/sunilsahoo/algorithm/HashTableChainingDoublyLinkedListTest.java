package com.sunilsahoo.algorithm;
/*
 *    Java Program to Implement Hash Tables Chaining with Doubly Linked Lists 
 *    
 *    This is a Java Program to implement hash tables chaining with Doubly Linked Lists. A hash table (also hash map) is a data structure used to implement an associative array, a structure that can map keys to values. A hash table uses a hash function to compute an index into an array of buckets or slots, from which the correct value can be found. In order to prevent collision, hash tables are chained with another data structure ( Doubly Linked List in this case ).
Here is the source code of the Java program to implement hash tables chaining with Doubly Linked Lists. The Java program is successfully compiled and run on a Windows system. The program output is also shown below.


 */
 
import java.util.Scanner;
 
/* Node for doubly linked list */
class DLLNode
{
    DLLNode next, prev;
    int data;
 
    /* Constructor */
    public DLLNode(int x)
    {
        data = x;
        next = null;
        prev = null;
    }
}
 
/* Class HashTableChainingDoublyLinkedList */
class HashTableChainingDoublyLinkedList
{
    private DLLNode[] table;
    private int size ;
 
    /* Constructor */
    public HashTableChainingDoublyLinkedList(int tableSize)
    {
        table = new DLLNode[ nextPrime(tableSize) ];
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
        table = new DLLNode[l];
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
        DLLNode nptr = new DLLNode(val);
        DLLNode start = table[pos];                
        if (table[pos] == null)
            table[pos] = nptr;            
        else
        {
            nptr.next = start;
            start.prev = nptr;
            table[pos] = nptr;
        }    
    }
    /* Function to remove an element */
    public void remove(int val)
    {
        try
        {
            int pos = myhash(val);    
            DLLNode start = table[pos];
            DLLNode end = start;
            if (start.data == val)
            {
                size--;
                if (start.next == null)
                {
                    table[pos] = null;
                    return;
                }                
                start = start.next;
                start.prev = null;
                table[pos] = start;
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
            end.next.next.prev = end;
            end.next = end.next.next;
 
            table[pos] = start;
        }
        catch (Exception e)
        {
            System.out.println("\nElement not found\n");
        }
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
    /* Function to print hash table */
    public void printHashTable ()
    {
        System.out.println();
        for (int i = 0; i < table.length; i++)
        {
            System.out.print ("Bucket " + i + ":  ");            
 
            DLLNode start = table[i];
            while(start != null)
            {
                System.out.print(start.data +" ");
                start = start.next;
            }
            System.out.println();
        }
    }    
 
}
 
/* Class HashTableChainingDoublyLinkedListTest */
public class HashTableChainingDoublyLinkedListTest
{ 
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        /* Make object of HashTableChainingDoublyLinkedList */
        HashTableChainingDoublyLinkedList htcdll = new HashTableChainingDoublyLinkedList(scan.nextInt() );
 
        char ch;
        /*  Perform HashTableChainingDoublyLinkedList operations  */
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
                htcdll.insert( scan.nextInt() ); 
                break;                          
            case 2 :                  
                System.out.println("Enter integer element to delete");
                htcdll.remove( scan.nextInt() ); 
                break; 
            case 3 : 
                htcdll.makeEmpty();
                System.out.println("Hash Table Cleared\n");
                break;
            case 4 : 
                System.out.println("Size = "+ htcdll.getSize() );
                break; 
            case 5 : 
                System.out.println("Empty status = "+ htcdll.isEmpty() );
                break;        
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            } 
            /* Display hash table */ 
            htcdll.printHashTable();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}

/*
 * 
 * 
 * Hash Table Test
 
 
Enter size
5
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
99
 
Bucket 0:
Bucket 1:
Bucket 2:
Bucket 3:
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
23
 
Bucket 0:
Bucket 1:
Bucket 2:
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
36
 
Bucket 0:
Bucket 1:  36
Bucket 2:
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
47
 
Bucket 0:
Bucket 1:  36
Bucket 2:  47
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
80
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  47
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
37
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  37 47
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
92
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  92 37 47
Bucket 3:  23
Bucket 4:  99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
1
Enter integer element to insert
49
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  92 37 47
Bucket 3:  23
Bucket 4:  49 99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
2
Enter integer element to delete
37
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  92 47
Bucket 3:  23
Bucket 4:  49 99
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
2
Enter integer element to delete
99
 
Bucket 0:  80
Bucket 1:  36
Bucket 2:  92 47
Bucket 3:  23
Bucket 4:  49
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
2
Enter integer element to delete
80
 
Bucket 0:
Bucket 1:  36
Bucket 2:  92 47
Bucket 3:  23
Bucket 4:  49
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
3
Hash Table Cleared
 
 
Bucket 0:
Bucket 1:
Bucket 2:
Bucket 3:
Bucket 4:
 
Do you want to continue (Type y or n)
 
y
 
Hash Table Operations
 
1. insert
2. remove
3. clear
4. size
5. check empty
5
Empty status = true
 
Bucket 0:
Bucket 1:
Bucket 2:
Bucket 3:
Bucket 4:
 
Do you want to continue (Type y or n)
 
n
*/
