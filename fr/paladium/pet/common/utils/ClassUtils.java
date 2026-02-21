package fr.paladium.pet.common.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
  public static List<Class<?>> getClasses(String packageName) throws Exception {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace('.', '/');
    List<Class<?>> classes = new ArrayList<>();
    URL resource = classLoader.getResource(path);
    if (resource == null)
      throw new IllegalArgumentException("Package " + packageName + " not found"); 
    String resourcePath = resource.getPath();
    File directory = new File(resourcePath);
    if (directory.exists()) {
      String[] files = directory.list();
      for (String file : files) {
        if (file.endsWith(".class")) {
          String className = packageName + '.' + file.substring(0, file.length() - 6);
          classes.add(Class.forName(className));
        } 
      } 
    } 
    return classes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\ClassUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */