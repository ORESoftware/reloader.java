package org.ores.reload;

import java.util.Arrays;

@SuppressWarnings("UnusedDeclaration")
public class User implements Runnable {
  
  public void hobby() {
    
    System.out.println(Arrays.asList(1,2,3));
    playFootball();
  }
  
  public void playFootball() {
    System.out.println("Play ZZZZZZZZ");
    System.out.println("Play TTT 222");
  }
  
  @Override
  public void run() {
    System.out.println("Running run!");
     this.hobby();
  }
}