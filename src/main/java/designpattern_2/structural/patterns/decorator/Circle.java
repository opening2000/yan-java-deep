package designpattern_2.structural.patterns.decorator;


public class Circle implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Shape: Circle");
   }
}
