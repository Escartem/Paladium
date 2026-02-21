package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.KillAnimationCosmeticClientProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.KillAnimationCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.KillAnimationCosmeticServerProxy;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class KillAnimationCosmeticFactory extends CosmeticFactory {
  public static String ID = "kill_animation";
  
  private static KillAnimationCosmeticFactory instance;
  
  private static KillAnimationCosmeticCommonProxy proxy;
  
  public KillAnimationCosmeticFactory(@NonNull CosmeticProvider provider) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    super(provider, ID);
    if (provider == null)
      throw new NullPointerException("provider is marked non-null but is null"); 
    instance = this;
    proxy = (KillAnimationCosmeticCommonProxy)provider.createProxy("fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.KillAnimationCosmeticClientProxy", "fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.KillAnimationCosmeticServerProxy");
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
  public static KillAnimationCosmeticCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static KillAnimationCosmeticClientProxy getClient() {
    return (KillAnimationCosmeticClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static KillAnimationCosmeticServerProxy getServer() {
    return (KillAnimationCosmeticServerProxy)proxy;
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
    return CosmeticPlayer.EquippedCosmetic.unique(1);
  }
  
  @NonNull
  public static KillAnimationCosmeticFactory getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\KillAnimationCosmeticFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */