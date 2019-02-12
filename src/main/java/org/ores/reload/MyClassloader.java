package org.ores.reload;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassloader extends ClassLoader {
  
  
  public static void main(String[] args) throws Exception {
    do {
      Object foo = new MyClassloader().loadClass("MyFoo").newInstance();
      System.out.println("LOADED: " + foo); // Overload MyFoo#toString() for effect
      System.out.println("Press <ENTER> when MyFoo.class has changed");
      System.in.read();
    } while (true);
  }
  
  @Override
  public Class<?> loadClass(String s) {
    
//    System.out.println("Current class");
//    System.out.println(s);
    
    try {
      if (!s.startsWith("org.ores")) {
        
        return this.getParent().loadClass(s);
      }
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
    
    System.out.println("reloading class:");
    System.out.println(s);
    return findClass(s);
  }
  
  @Override
  public Class<?> findClass(String s) {
    try {
      byte[] bytes = loadClassData(s);
      return this.defineClass(s, bytes, 0, bytes.length);
    } catch (IOException ioe) {
//      try {
//        return super.loadClass(s);
//      } catch (ClassNotFoundException ignore) { }
      ioe.printStackTrace(System.out);
      return null;
    }
  }
  
  private byte[] loadClassData(String className) throws IOException {
//    File f = new File("/home/oleg/codes/oresoftware/reloader.java/src/main/java/" + className.replaceAll("\\.", "/") + ".class");
    
    System.out.println("Class name!!!");
    System.out.println(className);
    
    File f = new File("/home/oleg/codes/oresoftware/reloader.java/target/classes/org/ores/reload/User.class");
    int size = (int) f.length();
    byte buff[] = new byte[size];
    FileInputStream fis = new FileInputStream(f);
    DataInputStream dis = new DataInputStream(fis);
    dis.readFully(buff);
    dis.close();
    return buff;
  }
}
