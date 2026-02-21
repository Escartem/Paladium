package fr.paladium.palashop.provider.cosmetic.factory.impl.emote;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.EmoteCosmeticClientProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.EmoteCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.EmoteCosmeticServerProxy;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class EmoteCosmeticFactory extends CosmeticFactory {
  public static String ID = "emote";
  
  private static EmoteCosmeticFactory instance;
  
  private static EmoteCosmeticCommonProxy proxy;
  
  public EmoteCosmeticFactory(@NonNull CosmeticProvider provider) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    super(provider, ID);
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    instance = this;
    proxy = (EmoteCosmeticCommonProxy)provider.createProxy("fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.EmoteCosmeticClientProxy", "fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.EmoteCosmeticServerProxy");
    ProviderDispatcher.register(proxy);
  }
  
  @ProviderListener
  public void onEvent(ProviderEvent event) {
    dispatch(event);
  }
  
  public void dispatch(@NonNull ProviderEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    if (event.isGlobal())
      return; 
    ProviderDispatcher.dispatch(proxy, event);
  }
  
  @NonNull
  public static EmoteCosmeticCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static EmoteCosmeticClientProxy getClient() {
    return (EmoteCosmeticClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static EmoteCosmeticServerProxy getServer() {
    return (EmoteCosmeticServerProxy)proxy;
  }
  
  @NonNull
  public <T extends ICosmetic> List<T> getCosmetics() {
    return proxy.getCosmetics();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return proxy.getCosmetic(id);
  }
  
  public CosmeticPlayer.EquippedCosmetic getEquippedCosmetic() {
    return CosmeticPlayer.EquippedCosmetic.wheel();
  }
  
  @NonNull
  public static EmoteCosmeticFactory getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\EmoteCosmeticFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */