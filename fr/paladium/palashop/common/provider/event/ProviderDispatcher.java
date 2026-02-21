package fr.paladium.palashop.common.provider.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.NonNull;

public class ProviderDispatcher {
  private static final Map<Class<?>, Object> INSTANCE_TABLE = new ConcurrentHashMap<>();
  
  private static final Map<Class<?>, Map<String, Method>> ASM_TABLE = new ConcurrentHashMap<>();
  
  public static void register(@NonNull Object instance) {
    if (instance == null)
      throw new NullPointerException("instance is marked non-null but is null"); 
    Map<String, Method> listeners = new HashMap<>();
    for (Method method : instance.getClass().getMethods()) {
      if (method.isAnnotationPresent((Class)ProviderListener.class) && method.getParameterCount() == 1) {
        Class<?> parameter = method.getParameterTypes()[0];
        if (!ProviderEvent.class.isAssignableFrom(parameter)) {
          System.out.println("Invalid parameter type for listener method " + method.getName() + " in class " + instance.getClass().getName());
        } else {
          method.setAccessible(true);
          listeners.put(parameter.getName(), method);
        } 
      } 
    } 
    if (listeners.isEmpty())
      return; 
    INSTANCE_TABLE.put(instance.getClass(), instance);
    ASM_TABLE.put(instance.getClass(), listeners);
  }
  
  public static boolean dispatchGlobal(@NonNull ProviderEvent<?>... events) {
    if (events == null)
      throw new NullPointerException("events is marked non-null but is null"); 
    AtomicBoolean canceled = new AtomicBoolean(false);
    for (ProviderEvent<?> event : events) {
      event.setGlobal(true);
      ASM_TABLE.keySet().forEach(clazz -> {
            if (dispatch(clazz, event))
              canceled.set(true); 
          });
    } 
    return canceled.get();
  }
  
  public static boolean dispatch(@NonNull Object instance, @NonNull ProviderEvent<?> event) {
    if (instance == null)
      throw new NullPointerException("instance is marked non-null but is null"); 
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    Class<?> clazz = instance.getClass();
    if (!ASM_TABLE.containsKey(clazz))
      return false; 
    String eventName = event.getClass().getName();
    Map<String, Method> listeners = ASM_TABLE.get(clazz);
    listeners.forEach((name, method) -> {
          if (name.equals(eventName) || name.equals(ProviderEvent.class.getName()))
            try {
              method.invoke(instance, new Object[] { event });
            } catch (Exception e) {
              System.out.println("Error while dispatching event " + eventName + " to method " + method.getName() + " in class " + clazz.getName());
              e.printStackTrace();
            }  
        });
    return event.isCanceled();
  }
  
  public static boolean dispatch(@NonNull Class<?> clazz, @NonNull ProviderEvent<?> event) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    if (!ASM_TABLE.containsKey(clazz))
      return false; 
    String eventName = event.getClass().getName();
    Object instance = INSTANCE_TABLE.get(clazz);
    Map<String, Method> listeners = ASM_TABLE.get(clazz);
    listeners.forEach((name, method) -> {
          if (name.equals(eventName) || name.equals(ProviderEvent.class.getName()))
            try {
              method.invoke(instance, new Object[] { event });
            } catch (Exception e) {
              System.out.println("Error while dispatching event " + eventName + " to method " + method.getName() + " in class " + clazz.getName());
              e.printStackTrace();
            }  
        });
    return event.isCanceled();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\ProviderDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */