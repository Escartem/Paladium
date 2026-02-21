package fr.paladium.palashop.common.provider;

import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import fr.paladium.palashop.common.provider.dto.DefaultShopProvider;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.common.provider.event.IProviderListener;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.NonNull;

public class ProviderManager {
  private static Map<String, IShopProvider<? extends IShopItem>> providerMap;
  
  private static IShopProvider<? extends IShopItem> defaultProvider;
  
  public static void load() {
    System.out.println("[/] Loading providers");
    try {
      providerMap = new LinkedHashMap<>();
      for (UnmodifiableIterator<ClassPath.ClassInfo> unmodifiableIterator = ClassPath.from(ProviderManager.class.getClassLoader()).getTopLevelClassesRecursive("fr.paladium.palashop.provider").iterator(); unmodifiableIterator.hasNext(); ) {
        ClassPath.ClassInfo info = unmodifiableIterator.next();
        if (!info.getName().endsWith("Provider"))
          continue; 
        Class<?> clazz = Class.forName(info.getName(), false, Thread.currentThread().getContextClassLoader());
        if (!IShopProvider.class.isAssignableFrom(clazz) || (clazz.getDeclaredConstructors()).length == 0 || clazz.getDeclaredConstructors()[0].getParameterCount() != 0)
          continue; 
        Object instance = null;
        if (IProviderListener.class.isAssignableFrom(clazz)) {
          instance = clazz.getDeclaredConstructors()[0].newInstance(new Object[0]);
          ProviderDispatcher.register(instance);
        } 
        try {
          if (instance != null) {
            register((IShopProvider<? extends IShopItem>)instance);
            continue;
          } 
          IShopProvider<? extends IShopItem> provider = (IShopProvider<? extends IShopItem>)clazz.getDeclaredConstructors()[0].newInstance(new Object[0]);
          register(provider);
        } catch (Exception e) {
          System.out.println("Failed to load provider " + info.getName());
          throw new RuntimeException(e);
        } 
      } 
    } catch (Exception e) {
      System.out.println("Failed to load providers");
      throw new RuntimeException(e);
    } 
    if (defaultProvider == null)
      throw new IllegalStateException("No default provider found"); 
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ProviderLoadProviderEvent.pre() });
    providerMap = (Map<String, IShopProvider<? extends IShopItem>>)providerMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(LinkedHashMap::new, (map, entry) -> (IShopProvider)map.put(entry.getKey(), entry.getValue()), Map::putAll);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ProviderLoadProviderEvent.post() });
    System.out.println("[+] Providers loaded (" + providerMap.size() + ")");
  }
  
  public static void register(@NonNull IShopProvider<? extends IShopItem> provider) {
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    providerMap.put(provider.getId(), provider);
    if (provider.getClass().isAnnotationPresent((Class)DefaultShopProvider.class))
      defaultProvider = provider; 
    ProviderDispatcher.register(provider);
  }
  
  @NonNull
  public static List<IShopProvider<?>> getProviders() {
    return new ArrayList<>(providerMap.values());
  }
  
  @NonNull
  public static Optional<IShopProvider<? extends IShopItem>> getProvider(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return Optional.ofNullable(providerMap.get(id));
  }
  
  @NonNull
  public static IShopProvider<? extends IShopItem> getProviderOrDefault(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (providerMap.containsKey(id))
      return providerMap.get(id); 
    return defaultProvider;
  }
  
  @NonNull
  public static IShopProvider<? extends IShopItem> getDefaultProvider() {
    return defaultProvider;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\ProviderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */