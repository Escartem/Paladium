package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.Cosmetic;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.data.CosmeticProperties;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.dto.KillMessageCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import lombok.NonNull;
import net.minecraft.client.Minecraft;

public class KillMessageCosmeticClient extends Cosmetic<KillMessageCosmetic.KillCosmeticProperties> implements ICosmeticClient {
  public String toString() {
    return "KillMessageCosmeticClient(callbackList=" + getCallbackList() + ")";
  }
  
  private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
  
  private final Queue<Consumer<ICosmeticClient>> callbackList = new ConcurrentLinkedQueue<>();
  
  public Queue<Consumer<ICosmeticClient>> getCallbackList() {
    return this.callbackList;
  }
  
  public KillMessageCosmeticClient(@NonNull String id) {
    super(id);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
  }
  
  @NonNull
  public KillMessageCosmeticClient receive(@NonNull KillMessageCosmetic.KillCosmeticProperties properties) {
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    setProperties((CosmeticProperties)properties);
    while (!this.callbackList.isEmpty())
      ((Consumer<KillMessageCosmeticClient>)this.callbackList.poll()).accept(this); 
    return this;
  }
  
  @NonNull
  public CosmeticFactory getFactory() {
    return (CosmeticFactory)KillMessageCosmeticFactory.getInstance();
  }
  
  @NonNull
  public String getName() {
    return COLOR_PATTERN.matcher(getFormattedMessage((Minecraft.func_71410_x()).field_71439_g.func_70005_c_(), "{victim}")).replaceAll("");
  }
  
  public Resource getThumbnail() {
    return null;
  }
  
  @NonNull
  public <T extends fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement> T onReceived(@NonNull Consumer<T> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    if (isReceived()) {
      callback.accept((T)this);
    } else {
      this.callbackList.add(callback);
    } 
    return (T)this;
  }
  
  @NonNull
  public <T extends fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement> T onLoaded(@NonNull Consumer<T> callback) {
    if (callback == null)
      throw new NullPointerException("callback is marked non-null but is null"); 
    if (isLoaded()) {
      callback.accept((T)this);
    } else {
      this.callbackList.add(callback);
    } 
    return (T)this;
  }
  
  public boolean isReceived() {
    return (getProperties() != null);
  }
  
  public boolean isLoaded() {
    return isReceived();
  }
  
  public String getMessage() {
    if (!isReceived())
      return null; 
    return ((KillMessageCosmetic.KillCosmeticProperties)getProperties()).getMessage();
  }
  
  @NonNull
  public String getFormattedMessage(@NonNull String killer, @NonNull String victim) {
    if (killer == null)
      throw new NullPointerException("killer is marked non-null but is null"); 
    if (victim == null)
      throw new NullPointerException("victim is marked non-null but is null"); 
    String message = getMessage();
    if (message == null)
      return null; 
    return message.replace("{killer}", killer).replace("%killer%", killer).replace("{victim}", victim).replace("%victim%", victim);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\dto\KillMessageCosmeticClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */