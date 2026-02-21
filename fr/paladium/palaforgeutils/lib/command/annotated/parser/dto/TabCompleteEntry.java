package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameterTabComplete;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.packet.SCPacketCommandTabComplete;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public final class TabCompleteEntry {
  public TabCompleteEntry() {}
  
  private static final Cache<String, Method> METHOD_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private static final Cache<String, CompletableFuture<List<String>>> CALLBACK_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private String[] options;
  
  private String methodRaw;
  
  private String methodName;
  
  private String methodClass;
  
  private Side side;
  
  private transient Object instance;
  
  private transient Method cachedMethod;
  
  public String[] getOptions() {
    return this.options;
  }
  
  public String getMethodRaw() {
    return this.methodRaw;
  }
  
  public String getMethodName() {
    return this.methodName;
  }
  
  public String getMethodClass() {
    return this.methodClass;
  }
  
  public Side getSide() {
    return this.side;
  }
  
  public Object getInstance() {
    return this.instance;
  }
  
  public Method getCachedMethod() {
    return this.cachedMethod;
  }
  
  public TabCompleteEntry(@NonNull CommandParameterTabComplete annotation, @NonNull Object instance) throws IllegalArgumentException {
    if (annotation == null)
      throw new NullPointerException("annotation is marked non-null but is null"); 
    if (instance == null)
      throw new NullPointerException("instance is marked non-null but is null"); 
    this.options = annotation.value();
    this.side = annotation.side();
    this.instance = instance;
    if (annotation.method() != null && !annotation.method().isEmpty()) {
      this.methodRaw = annotation.method();
      this.methodName = this.methodRaw;
      this.methodClass = instance.getClass().getName();
      String[] methodParts = this.methodRaw.split("::");
      if (methodParts.length == 2) {
        if (!"this".equalsIgnoreCase(methodParts[0]) && !"self".equalsIgnoreCase(methodParts[0]))
          this.methodClass = methodParts[0]; 
        this.methodName = methodParts[1];
      } else if (methodParts.length > 2) {
        throw new IllegalArgumentException("The method " + this.methodRaw + " is not valid for tab-complete in @CommandParameterTabComplete.");
      } 
      if (this.side == Side.SERVER)
        this.cachedMethod = getMethod(this.methodRaw, this.methodName, this.methodClass); 
    } 
    if (this.side == null)
      throw new IllegalArgumentException("The side cannot be null in @CommandParameterTabComplete."); 
  }
  
  public boolean isEmpty() {
    return ((this.methodName == null || this.methodName.isEmpty()) && (this.options == null || this.options.length == 0));
  }
  
  @NonNull
  public CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (isEmpty() || !context.isPlayer())
      return CompletableFuture.completedFuture(null); 
    if (this.options != null && this.options.length > 0)
      return CompletableFuture.completedFuture(Arrays.asList(this.options)); 
    if (this.methodName != null && !this.methodName.isEmpty())
      if (this.side == Side.SERVER && this.cachedMethod != null) {
        try {
          Object result = this.cachedMethod.invoke(this.instance, new Object[] { context });
          if (result != null) {
            if (result instanceof String[])
              return CompletableFuture.completedFuture(Arrays.asList((String[])result)); 
            if (result instanceof List)
              return CompletableFuture.completedFuture((List<String>)result); 
            if (result instanceof CompletableFuture)
              return (CompletableFuture<List<String>>)result; 
          } 
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } else if (this.side == Side.CLIENT) {
        String uuid = UUIDUtils.randomStringUUID();
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        CALLBACK_CACHE.put(uuid, future);
        PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)new SCPacketCommandTabComplete(uuid, this.methodRaw, this.methodName, this.methodClass, context.getCommand(), context.getArgs()), context.getPlayer());
        return future;
      }  
    return CompletableFuture.completedFuture(new ArrayList<>());
  }
  
  public static Method getMethod(@NonNull String methodRaw, @NonNull String methodName, @NonNull String methodClass) throws IllegalArgumentException {
    if (methodRaw == null)
      throw new NullPointerException("methodRaw is marked non-null but is null"); 
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    if (methodClass == null)
      throw new NullPointerException("methodClass is marked non-null but is null"); 
    Method cachedMethod = (Method)METHOD_CACHE.getIfPresent(methodRaw);
    if (cachedMethod != null)
      return cachedMethod; 
    Class<?> clazz = null;
    try {
      clazz = Class.forName(methodClass);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("The class " + methodClass + " cannot be found for tab-complete method " + methodRaw + " in @CommandParameterTabComplete.");
    } 
    List<Method> candidates = new ArrayList<>();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.getName().equals(methodName)) {
        candidates.add(method);
        Class<?>[] params = method.getParameterTypes();
        if (params.length == 1 && CommandContext.class.isAssignableFrom(params[0])) {
          Class<?> returnType = method.getReturnType();
          if (returnType == String[].class || returnType == List.class || returnType == ArrayList.class || returnType == CompletableFuture.class) {
            cachedMethod = method;
            break;
          } 
        } 
      } 
    } 
    if (cachedMethod == null)
      throw new IllegalArgumentException("The method " + methodRaw + " cannot be found for tab-complete in @CommandParameterTabComplete. Found " + candidates.size() + " candidate(s) with the name " + methodName + " in class " + clazz.getName() + ", make sure it has a valid signature."); 
    cachedMethod.setAccessible(true);
    METHOD_CACHE.put(methodRaw, cachedMethod);
    return cachedMethod;
  }
  
  public static CompletableFuture<List<String>> getAndRemoveCallback(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<List<String>> future = (CompletableFuture<List<String>>)CALLBACK_CACHE.getIfPresent(uuid);
    if (future != null) {
      CALLBACK_CACHE.invalidate(uuid);
      return future;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\TabCompleteEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */