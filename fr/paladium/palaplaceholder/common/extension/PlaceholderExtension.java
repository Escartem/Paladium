package fr.paladium.palaplaceholder.common.extension;

import fr.paladium.palaplaceholder.common.extension.placeholder.Placeholder;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PlaceholderExtension implements IPlaceholderExtension {
  private final Map<String, Placeholder> placeholderMapper = new HashMap<>();
  
  public Map<String, Placeholder> getPlaceholderMapper() {
    return this.placeholderMapper;
  }
  
  public void registerPlaceholder(Placeholder... placeholders) {
    for (Placeholder placeholder : placeholders) {
      if (this.placeholderMapper.containsKey(placeholder.getKey())) {
        System.out.println("[PalaPlaceholderAPI] The placeholder with the key \"" + placeholder.getKey() + "\" is already registered");
      } else {
        this.placeholderMapper.put(placeholder.getKey(), placeholder);
      } 
    } 
  }
  
  public String replace(String key, EntityPlayer player) {
    if (this.placeholderMapper.containsKey(key)) {
      Placeholder placeholder = this.placeholderMapper.get(key);
      return placeholder.apply(player);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\common\extension\PlaceholderExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */