package org.ores.reload;

import javax.xml.stream.Location;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Reloader implements Runnable {
  
  private Thread current;
  
  public void run() {
    ClassLoader c = this.getClass().getClassLoader();
  }
  
  
  public void reee(){
  
    try {
      String className = "org.ores.reload.User";
      ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
      MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
      Class<?> myObjectClass = classLoader.loadClass(className);
  
      Object object1 = (Object) myObjectClass.newInstance();
  
      Object object2 = (Object) myObjectClass.newInstance();
  
      //create new class loader so classes can be reloaded.
      classLoader = new MyClassLoader(parentClassLoader);
      myObjectClass = classLoader.loadClass(className);
  
      object1 = (Object) myObjectClass.newInstance();
      object2 = (Object) myObjectClass.newInstance();
  
      Method run = myObjectClass.getMethod("run");
      run.invoke(object2);
    }
    catch(Exception e){
      e.printStackTrace(System.out);
    }
  }
  
  
  public void reload() {

//    Class<?> myClass = Reloader.class;
    
    try {
  
  
      String className = "org.ores.reload.User";
      
      Class<?> myClass = Class.forName(className);
      System.out.printf("my class is Class@%x%n", myClass.hashCode());
      System.out.println("reloading");
      
//      URL loc = myClass.getProtectionDomain().getCodeSource().getLocation();
//      System.out.println(loc);
//      URL[] urls = {loc};
//      ClassLoader delegateParent = myClass.getClassLoader().getParent();
//
//      URLClassLoader cl = new URLClassLoader(urls, delegateParent);
//      Class<?> reloaded = cl.loadClass(myClass.getName());
  
      Class<?> reloaded = new MyClassloader().loadClass(className);
      var v = reloaded.newInstance();
  
      Method run = reloaded.getMethod("run");
      run.invoke(v);
      
      System.out.printf("reloaded my class: Class@%x%n", reloaded.hashCode());
      System.out.println("Different classes: " + (myClass != reloaded));
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public Reloader start() {
    if (this.current != null) {
      this.current.destroy();
    }
    this.current = new Thread(this);
    this.current.start();
    return this;
  }
  
  
  public static void main(String[] args) {
    
    var s = new Reloader().start();
    
    while (true) {
      
      s.reload();
      
//      s.reee();
      try {
        Thread.sleep(2000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
  }
}
