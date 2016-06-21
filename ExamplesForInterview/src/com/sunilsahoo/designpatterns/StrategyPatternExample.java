package com.sunilsahoo.designpatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * ## Also known as
Policy
Type : Behavioral
## Intent
Define a family of algorithms, encapsulate each one, and make them
interchangeable. Strategy lets the algorithm vary independently from clients
that use it.

![alt text](./etc/strategy_1.png "Strategy")

## Applicability
Use the Strategy pattern when

* many related classes differ only in their behavior. Strategies provide a way to configure a class either one of many behaviors
* you need different variants of an algorithm. for example, you might define algorithms reflecting different space/time trade-offs. Strategies can be used when these variants are implemented as a class hierarchy of algorithms
* an algorithm uses data that clients shouldn't know about. Use the Strategy pattern to avoid exposing complex, algorithm-specific data structures
* a class defines many behaviors, and these appear as multiple conditional statements in its operations. Instead of many conditionals, move related conditional branches into their own Strategy class


 * 
 * In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under behavior pattern.
In Strategy pattern, we create objects which represent various strategies and a context object whose behavior varies as per its strategy object. The strategy object changes the executing algorithm of the context object.
 * 
 * @author sunilkumarsahoo
 *
 */
public class StrategyPatternExample {
	public static void main(String[] args) {
		 
        Student emp1=new Student("sam","4");
        Student emp2=new Student("amy","2");
        Student emp3=new Student("brad","1");

        ArrayList<Student> list=new ArrayList<Student>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
      
        System.out.println("list Before sorting : \n"+list);
 
        Collections.sort(list,new ComparatorName());
        System.out.println("\nlist after sorting on basis of name(ascending order), "
                  + "using inner class : \n"+ list);
 
        Collections.sort(list,new ComparatorId());
        System.out.println("\nlist after sorting on basis of id(ascending order), "
                  + "using static nested class : \n"+list);
       
    }

}

class Student{
    String name;
    String id;
   
    public Student() {}
   
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id  + '}';
    }
   
}
 
    //static nested class
    class ComparatorId  implements Comparator<Student>{
       @Override
        public int compare(Student obj1, Student obj2) {
           //sort Student on basis of id(ascending order)
           return obj1.id.compareTo(obj2.id);
        }
    }
    class ComparatorName  implements Comparator<Student>{
        @Override
         public int compare(Student obj1, Student obj2) {
            //sort Student on basis of id(ascending order)
            return obj1.name.compareTo(obj2.name);
         }
     }
 

