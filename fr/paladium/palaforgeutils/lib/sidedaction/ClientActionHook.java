package fr.paladium.palaforgeutils.lib.sidedaction;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.sidedaction.network.client.SCExecuteClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionEntry;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionExecution;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.SidedActionList;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCacheResult;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientActionResult;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.client.ClientComponent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;

public final class ClientActionHook {
  private static final Map<String, SidedActionList> REFLECTION_CACHE = new HashMap<>();
  
  private static final Cache<String, SidedActionExecution<?>> EXECUTION_CACHE = CacheBuilder.newBuilder().expireAfterWrite(30L, TimeUnit.SECONDS).build();
  
  @NonNull
  public static <T> ClientActionResult<T> useClient(@NonNull Runnable runnable, Object... args) {
    if (runnable == null)
      throw new NullPointerException("runnable is marked non-null but is null"); 
    StackTraceElement current = Thread.currentThread().getStackTrace()[2];
    String className = current.getClassName();
    String methodName = current.getMethodName();
    ClientActionResult<T> clientResult = new ClientActionResult(className, methodName, args);
    SidedActionEntry entry = register(className, methodName, args);
    if (entry == null)
      return clientResult; 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      if (entry.hasClientCache()) {
        SidedActionCacheResult result = entry.getValidCacheResult(entry.getClientTimeout());
        if (result != null) {
          clientResult.getFuture().complete(null);
          return clientResult;
        } 
      } 
      runnable.run();
      entry.setCacheResult(new SidedActionCacheResult(null));
      clientResult.getFuture().complete(null);
      return clientResult;
    } 
    if (entry.hasServerCache()) {
      SidedActionCacheResult result = entry.getValidCacheResult(entry.getServerTimeout());
      if (result != null)
        clientResult.getFuture().complete(null); 
    } 
    return clientResult;
  }
  
  @NonNull
  public static <T> ClientActionResult<T> useClient(@NonNull Supplier<CompletableFuture<T>> supplier, Object... args) {
    if (supplier == null)
      throw new NullPointerException("supplier is marked non-null but is null"); 
    StackTraceElement current = Thread.currentThread().getStackTrace()[2];
    String className = current.getClassName();
    String methodName = current.getMethodName();
    ClientActionResult<T> clientResult = new ClientActionResult(className, methodName, args);
    SidedActionEntry entry = register(className, methodName, args);
    if (entry == null)
      return clientResult; 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      if (entry.hasClientCache()) {
        SidedActionCacheResult sidedActionCacheResult = entry.getValidCacheResult(entry.getClientTimeout());
        if (sidedActionCacheResult != null) {
          clientResult.getFuture().complete(sidedActionCacheResult.getResult());
          return clientResult;
        } 
      } 
      CompletableFuture<T> result = supplier.get();
      result.thenAccept(r -> {
            entry.setCacheResult(new SidedActionCacheResult(r));
            clientResult.getFuture().complete(r);
          });
      return clientResult;
    } 
    if (entry.hasServerCache()) {
      SidedActionCacheResult result = entry.getValidCacheResult(entry.getServerTimeout());
      if (result != null)
        clientResult.getFuture().complete(result.getResult()); 
    } 
    return clientResult;
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
        if ((!m.isAnnotationPresent((Class)ClientAction.class) && !clazz.isAnnotationPresent((Class)ClientComponent.class)) || !m.getName().equals(methodName)) {
          b++;
          continue;
        } 
        method = m;
      } 
      if (method == null)
        throw new RuntimeException("[ClientAction] Cannot find method " + methodName + " in class " + className + ". Method should be public static with @ClientAction annotation."); 
      if (!method.isAccessible())
        method.setAccessible(true); 
      if (!Modifier.isStatic(method.getModifiers()))
        throw new RuntimeException("[ClientAction] Cannot execute method " + methodName + " in class " + className + ". Method should be public static."); 
      if (args == null && method.getParameterCount() > 0)
        throw new RuntimeException("[ClientAction] Cannot execute method " + methodName + " in class " + className + ". Method should have no parameters."); 
      if (args != null && args.length != method.getParameterCount())
        throw new RuntimeException("[ClientAction] Cannot execute method " + methodName + " in class " + className + ". Method should have " + args.length + " parameters."); 
      SidedActionList list = REFLECTION_CACHE.computeIfAbsent(className, k -> new SidedActionList());
      boolean component = !method.isAnnotationPresent((Class)ClientAction.class);
      SidedActionCache cache = component ? ((ClientComponent)clazz.<ClientComponent>getAnnotation(ClientComponent.class)).cache() : ((ClientAction)method.<ClientAction>getAnnotation(ClientAction.class)).cache();
      return list.add(methodName, method, true, cache);
    } catch (Exception e) {
      if (ForgeEnv.isIDE())
        throw new RuntimeException("[ClientAction] Cannot fetch method " + methodName + " in class " + className + ". Method should be public static.", e); 
      return null;
    } 
  }
  
  public static <T> void send(@NonNull PlayerSelector selector, @NonNull String className, @NonNull String methodName, Object[] args, @NonNull CompletableFuture<T> future) {
    if (selector == null)
      throw new NullPointerException("selector is marked non-null but is null"); 
    if (className == null)
      throw new NullPointerException("className is marked non-null but is null"); 
    if (methodName == null)
      throw new NullPointerException("methodName is marked non-null but is null"); 
    if (future == null)
      throw new NullPointerException("future is marked non-null but is null"); 
    selector.apply(player -> {
          String uuid;
          for (uuid = UUIDUtils.randomStringUUID(); EXECUTION_CACHE.asMap().containsKey(uuid); uuid = UUIDUtils.randomStringUUID());
          EXECUTION_CACHE.put(uuid, new SidedActionExecution(uuid, className, methodName, args, future));
          PalaForgeUtilsMod.proxy.getNetwork().sendTo((IMessage)new SCExecuteClientAction(uuid, className, methodName, args), player);
        });
  }
  
  public static <T> SidedActionExecution<T> getAndRemoveExecution(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    SidedActionExecution<T> execution = (SidedActionExecution<T>)EXECUTION_CACHE.getIfPresent(uuid);
    if (execution != null)
      EXECUTION_CACHE.invalidate(uuid); 
    return execution;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sidedaction\ClientActionHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */