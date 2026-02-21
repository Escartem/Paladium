package fr.paladium.palaforgeutils.lib.sidedaction;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.sidedaction.network.server.CSExecuteServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionList;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerComponent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.NonNull;

public final class ServerActionHook {
  private static final Map<String, SidedActionList> REFLECTION_CACHE = new HashMap<>();
  
  private static final Cache<String, SidedActionExecution<?>> EXECUTION_CACHE = CacheBuilder.newBuilder().expireAfterWrite(30L, TimeUnit.SECONDS).build();
  
  private static ServerActionContext context;
  
  @NonNull
  public static <T> CompletableFuture<T> useServer(@NonNull Consumer<ServerActionContext> consumer, Object... args) {
    if (consumer == null)
      throw new NullPointerException("consumer is marked non-null but is null"); 
    CompletableFuture<T> future = new CompletableFuture<>();
    StackTraceElement current = Thread.currentThread().getStackTrace()[2];
    String className = current.getClassName();
    String methodName = current.getMethodName();
    SidedActionEntry entry = register(className, methodName, args);
    if (entry == null)
      return future; 
    if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
      if (entry.hasServerCache()) {
        SidedActionCacheResult result = entry.getValidCacheResult(entry.getServerTimeout());
        if (result != null)
          return CompletableFuture.completedFuture(null); 
      } 
      consumer.accept(getContext());
      entry.setCacheResult(new SidedActionCacheResult(null));
      return CompletableFuture.completedFuture(null);
    } 
    if (entry.hasClientCache()) {
      SidedActionCacheResult result = entry.getValidCacheResult(entry.getClientTimeout());
      if (result != null)
        return CompletableFuture.completedFuture(null); 
    } 
    send(className, methodName, args, future);
    return future;
  }
  
  @NonNull
  public static <T> CompletableFuture<T> useServer(@NonNull Function<ServerActionContext, CompletableFuture<T>> supplier, Object... args) {
    if (supplier == null)
      throw new NullPointerException("supplier is marked non-null but is null"); 
    CompletableFuture<T> future = new CompletableFuture<>();
    StackTraceElement current = Thread.currentThread().getStackTrace()[2];
    String className = current.getClassName();
    String methodName = current.getMethodName();
    SidedActionEntry entry = register(className, methodName, args);
    if (entry == null)
      return future; 
    if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
      if (entry.isSecure() && !getContext().isActive()) {
        future.completeExceptionally(new RuntimeException("[ServerAction] Cannot execute secure action without valid context (are you trying to execute server side?)."));
        return future;
      } 
      if (entry.hasServerCache()) {
        SidedActionCacheResult result = entry.getValidCacheResult(entry.getServerTimeout());
        if (result != null)
          return CompletableFuture.completedFuture((T)result.getResult()); 
      } 
      ((CompletableFuture)supplier.apply(getContext())).thenAccept(r -> {
            entry.setCacheResult(new SidedActionCacheResult(r));
            future.complete(r);
          }).exceptionally(ex -> {
            ex.printStackTrace();
            future.completeExceptionally(ex);
            return null;
          });
      return future;
    } 
    if (entry.hasClientCache()) {
      SidedActionCacheResult result = entry.getValidCacheResult(entry.getClientTimeout());
      if (result != null)
        return CompletableFuture.completedFuture((T)result.getResult()); 
    } 
    send(className, methodName, args, future);
    return future;
  }
  
  @NonNull
  public static SidedActionEntry register(@NonNull String className, @NonNull String methodName, Object[] args) {
    if (className == null)
      throw new NullPointerException("className is marked non-null but is null"); 
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    if (REFLECTION_CACHE.containsKey(className)) {
      SidedActionList list = REFLECTION_CACHE.get(className);
      SidedActionEntry entry = list.get(methodName);
      if (entry != null)
        return entry; 
    } 
    try {
      Class<?> clazz = Class.forName(className, false, Thread.currentThread().getContextClassLoader());
      Method method = null;
      Method[] arrayOfMethod;
      int i;
      byte b;
      for (arrayOfMethod = clazz.getDeclaredMethods(), i = arrayOfMethod.length, b = 0; b < i; ) {
        Method m = arrayOfMethod[b];
        if ((!m.isAnnotationPresent((Class)ServerAction.class) && !clazz.isAnnotationPresent((Class)ServerComponent.class)) || !m.getName().equals(methodName)) {
          b++;
          continue;
        } 
        method = m;
      } 
      if (method == null)
        throw new RuntimeException("[ServerAction] Cannot find method " + methodName + " in class " + className + ". Method should be public static with @ServerAction annotation."); 
      if (!method.isAccessible())
        method.setAccessible(true); 
      if (!Modifier.isStatic(method.getModifiers()))
        throw new RuntimeException("[ServerAction] Cannot execute method " + methodName + " in class " + className + ". Method should be public static."); 
      if (args == null && method.getParameterCount() > 0)
        throw new RuntimeException("[ServerAction] Cannot execute method " + methodName + " in class " + className + ". Method should have no parameters."); 
      if (args != null && args.length != method.getParameterCount())
        throw new RuntimeException("[ServerAction] Cannot execute method " + methodName + " in class " + className + ". Method should have " + args.length + " parameters."); 
      SidedActionList list = REFLECTION_CACHE.computeIfAbsent(className, k -> new SidedActionList());
      boolean component = !method.isAnnotationPresent((Class)ServerAction.class);
      SidedActionCache cache = component ? ((ServerComponent)clazz.<ServerComponent>getAnnotation(ServerComponent.class)).cache() : ((ServerAction)method.<ServerAction>getAnnotation(ServerAction.class)).cache();
      boolean secure = component ? ((ServerComponent)clazz.<ServerComponent>getAnnotation(ServerComponent.class)).secure() : ((ServerAction)method.<ServerAction>getAnnotation(ServerAction.class)).secure();
      return list.add(methodName, method, secure, cache);
    } catch (Exception e) {
      if (ForgeEnv.isIDE())
        throw new RuntimeException("[ServerAction] Cannot fetch method " + methodName + " in class " + className + ". Method should be public static.", e); 
      return null;
    } 
  }
  
  private static <T> void send(@NonNull String className, @NonNull String methodName, Object[] args, @NonNull CompletableFuture<T> future) {
    if (className == null)
      throw new NullPointerException("className is marked non-null but is null"); 
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    if (future == null)
      throw new NullPointerException("future is marked non-null but is null"); 
    String uuid = UUIDUtils.randomStringUUID();
    while (EXECUTION_CACHE.asMap().containsKey(uuid))
      uuid = UUIDUtils.randomStringUUID(); 
    EXECUTION_CACHE.put(uuid, new SidedActionExecution(uuid, className, methodName, args, future));
    PalaForgeUtilsMod.proxy.getNetwork().sendToServer((IMessage)new CSExecuteServerAction(uuid, className, methodName, args));
  }
  
  public static <T> SidedActionExecution<T> getAndRemoveExecution(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    SidedActionExecution<T> execution = (SidedActionExecution<T>)EXECUTION_CACHE.getIfPresent(uuid);
    if (execution != null)
      EXECUTION_CACHE.invalidate(uuid); 
    return execution;
  }
  
  public static ServerActionContext getContext() {
    if (context == null)
      context = new ServerActionContext(); 
    return context;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\ServerActionHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */