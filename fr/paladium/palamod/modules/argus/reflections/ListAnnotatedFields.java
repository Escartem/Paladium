package fr.paladium.palamod.modules.argus.reflections;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.collect.Sets;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.ReflectionsException;
import org.reflections.scanners.MethodAnnotationsScanner;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ListAnnotatedFields {
  public static Set<Method> getMethodsAnnotatedWith(Reflections r, Class<? extends Annotation> annotation) {
    Iterable<String> methods = r.getStore().get(MethodAnnotationsScanner.class.getSimpleName(), new String[] { annotation.getName() });
    return getMethodsFromDescriptors(methods, r.getConfiguration().getClassLoaders());
  }
  
  public static Set<Method> getMethodsFromDescriptors(Iterable<String> annotatedWith, ClassLoader... classLoaders) {
    Set<Method> result = Sets.newHashSet();
    for (String annotated : annotatedWith) {
      if (!isConstructor(annotated)) {
        Method member = (Method)getMemberFromDescriptor(annotated, classLoaders);
        if (member != null)
          result.add(member); 
      } 
    } 
    return result;
  }
  
  public static MyHashSet<String> nh(Reflections rf, MyHashSet<String> a) {
    try {
      Arrays.<Class<? extends Annotation>>asList(FieldsRef._a).forEach(q -> a.p((Collection)rf.getFieldsAnnotatedWith(q).stream().map(()).collect(Collectors.toList())));
    } catch (Exception exception) {}
    return a;
  }
  
  public static boolean isEmpty(String s) {
    return (s == null || s.length() == 0);
  }
  
  public static boolean isEmpty(Object[] objects) {
    return (objects == null || objects.length == 0);
  }
  
  public static Member getMemberFromDescriptor(String descriptor, ClassLoader... classLoaders) throws ReflectionsException {
    int p0 = descriptor.lastIndexOf('(');
    String memberKey = (p0 != -1) ? descriptor.substring(0, p0) : descriptor;
    String methodParameters = (p0 != -1) ? descriptor.substring(p0 + 1, descriptor.lastIndexOf(')')) : "";
    int p1 = Math.max(memberKey.lastIndexOf('.'), memberKey.lastIndexOf("$"));
    String className = memberKey.substring(memberKey.lastIndexOf(' ') + 1, p1);
    String memberName = memberKey.substring(p1 + 1);
    Class<?>[] parameterTypes = null;
    if (!isEmpty(methodParameters)) {
      String[] parameterNames = methodParameters.split(",");
      List<Class<?>> result = new ArrayList<>(parameterNames.length);
      for (String name : parameterNames)
        result.add(ReflectionUtils.forName(name.trim(), classLoaders)); 
      parameterTypes = (Class[])result.<Class<?>[]>toArray((Class<?>[][])new Class[result.size()]);
    } 
    Class<?> aClass = ReflectionUtils.forName(className, classLoaders);
    while (aClass != null) {
      try {
        if (!descriptor.contains("("))
          return aClass.isInterface() ? aClass.getField(memberName) : aClass
            .getDeclaredField(memberName); 
        if (isConstructor(descriptor))
          return aClass.isInterface() ? aClass.getConstructor(parameterTypes) : aClass
            .getDeclaredConstructor(parameterTypes); 
        return aClass.isInterface() ? aClass.getMethod(memberName, parameterTypes) : aClass
          .getDeclaredMethod(memberName, parameterTypes);
      } catch (Exception e) {
        aClass = aClass.getSuperclass();
      } 
    } 
    return null;
  }
  
  public static boolean isConstructor(String fqn) {
    return fqn.contains((new Object() {
          int t;
          
          public String toString() {
            byte[] buf = new byte[5];
            this.t = -2007737787;
            buf[0] = (byte)(this.t >>> 6);
            this.t = 863475622;
            buf[1] = (byte)(this.t >>> 19);
            this.t = -1160578774;
            buf[2] = (byte)(this.t >>> 17);
            this.t = 976358002;
            buf[3] = (byte)(this.t >>> 23);
            this.t = -1443426497;
            buf[4] = (byte)(this.t >>> 19);
            return new String(buf);
          }
        }).toString());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\reflections\ListAnnotatedFields.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */