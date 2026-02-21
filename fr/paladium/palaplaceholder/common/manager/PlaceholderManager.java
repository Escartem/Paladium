package fr.paladium.palaplaceholder.common.manager;

import fr.paladium.palaplaceholder.common.extension.IPlaceholderExtension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EntityPlayer;

public final class PlaceholderManager {
  private static PlaceholderManager INSTANCE;
  
  private static final Pattern DETECTOR = Pattern.compile("%[a-z]+_[a-z_]+%");
  
  public Map<String, IPlaceholderExtension> getRegisteredExtension() {
    return this.registeredExtension;
  }
  
  private final Map<String, IPlaceholderExtension> registeredExtension = new HashMap<>();
  
  public void registerExtension(Class<? extends IPlaceholderExtension>... clazz) {
    for (Class<? extends IPlaceholderExtension> extensionClazz : clazz) {
      try {
        IPlaceholderExtension extension = extensionClazz.newInstance();
        if (this.registeredExtension.containsKey(extension.getIdentifier())) {
          System.out.println("[PalaPlaceholderAPI] The extension with the identifier \"" + extension.getIdentifier() + "\" is already registered");
        } else {
          extension.init();
          this.registeredExtension.put(extension.getIdentifier(), extension);
        } 
      } catch (InstantiationException|IllegalAccessException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void registerExtension(IPlaceholderExtension... extensions) {
    for (IPlaceholderExtension extension : extensions) {
      if (this.registeredExtension.containsKey(extension.getIdentifier())) {
        System.out.println("[PalaPlaceholderAPI] The extension with the identifier \"" + extension.getIdentifier() + "\" is already registered");
      } else {
        extension.init();
        this.registeredExtension.put(extension.getIdentifier(), extension);
      } 
    } 
  }
  
  public String setPlaceholders(EntityPlayer player, String string) {
    List<String> mapper = new ArrayList<>();
    AtomicReference<String> result = new AtomicReference<>(string);
    String matching = string;
    Matcher matcher = DETECTOR.matcher(matching);
    while (matcher.find()) {
      mapper.add(matcher.group());
      matcher = DETECTOR.matcher(matcher.replaceFirst(""));
    } 
    mapper.forEach(placeholder -> {
          int indexFirstUnderscore = placeholder.indexOf("_");
          String identifier = placeholder.substring(1, indexFirstUnderscore).toLowerCase();
          String key = placeholder.substring(indexFirstUnderscore + 1, placeholder.length() - 1).toLowerCase();
          if (this.registeredExtension.containsKey(identifier)) {
            IPlaceholderExtension placeholderExtension = this.registeredExtension.get(identifier);
            String replacer = placeholderExtension.replace(key, player);
            if (replacer != null)
              result.set(((String)result.get()).replaceFirst(placeholder, replacer)); 
          } 
        });
    return result.get();
  }
  
  public static PlaceholderManager inst() {
    if (INSTANCE == null)
      INSTANCE = new PlaceholderManager(); 
    return INSTANCE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\manager\PlaceholderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */