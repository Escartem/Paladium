package fr.paladium.pet.common.store.provider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.impl.AShopProvider;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.server.shop.event.ShopItemApplyProviderEvent;
import fr.paladium.pet.PetLogger;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.pet.PetAdditionalData;
import fr.paladium.pet.common.store.provider.client.PetSkinClientProxy;
import fr.paladium.pet.common.store.provider.common.PetSkinCommonProxy;
import fr.paladium.pet.common.store.provider.server.PetSkinServerProxy;
import fr.paladium.pet.common.store.provider.server.dto.PetSkinShopItem;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.PetServerProxy;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.bson.Document;

@ShopProvider(id = "pet", item = PetSkinShopItem.class)
public class PetSkinShopProvider extends AShopProvider<PetSkinShopItem> {
  public static final String SKINS_TAG = "skins";
  
  private static PetSkinShopProvider instance;
  
  private static PetSkinCommonProxy proxy;
  
  public PetSkinShopProvider() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    instance = this;
    proxy = (PetSkinCommonProxy)createProxy("fr.paladium.pet.common.store.provider.client.PetSkinClientProxy", "fr.paladium.pet.common.store.provider.server.PetSkinServerProxy");
    ProviderDispatcher.register(proxy);
  }
  
  @ProviderListener
  public void onShopApply(ShopItemApplyProviderEvent<PetSkinShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    importData(((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getPlayer());
  }
  
  public HashSet<String> getSkins(EntityPlayer player, PetPlayer pet) {
    UUID uniqueId = player.func_110124_au();
    if (!getPlayerSkinsMap().containsKey(uniqueId))
      return new HashSet<>(); 
    HashSet<String> skins = new HashSet<>((Collection<? extends String>)getPlayerSkinsMap().get(uniqueId));
    if (pet.getUnlockedSkin() != null)
      skins.add(pet.getUnlockedSkin()); 
    return skins;
  }
  
  @SideOnly(Side.SERVER)
  public void importData(EntityPlayer player) {
    getData(UUIDUtils.toString((Entity)player)).thenAccept(data -> {
          if (!data.containsKey("skins"))
            return; 
          List<String> skins = data.getList("skins", String.class, new ArrayList());
          getPlayerSkinsMap().put((Entity)player, new HashSet<>(skins));
        }).exceptionally(e -> {
          e.printStackTrace();
          return null;
        });
  }
  
  @SideOnly(Side.SERVER)
  public void addSkin(EntityPlayer player, String id) {
    PetAdditionalData petData = PetCommonProxy.getInstance().findPet(id);
    if (petData == null)
      return; 
    String uuid = UUIDUtils.toString((Entity)player);
    getData(uuid).thenAccept(data -> {
          List<String> skins = data.getList("skins", String.class, new ArrayList());
          if (skins.contains(petData.getName()))
            return; 
          skins.add(petData.getName());
          getPlayerSkinsMap().put((Entity)player, new HashSet<>(skins));
          data.put("skins", skins);
          setData(uuid, data).thenAccept(()).exceptionally(());
        }).exceptionally(e -> {
          e.printStackTrace();
          PetUtils.sendPrefixedMessage((ICommandSender)player, new String[] { "§7[§c!§7] §e" + getTranslatedSkinName(player, petData.getName()) });
          return null;
        });
  }
  
  @SideOnly(Side.SERVER)
  public void clearSkins(EntityPlayer player) {
    String uuid = UUIDUtils.toString((Entity)player);
    Document data = new Document();
    data.put("skins", new ArrayList());
    getPlayerSkinsMap().put((Entity)player, new HashSet());
    setData(uuid, data).thenAccept(user -> {
          PetUtils.sendPrefixedMessage((ICommandSender)player, new String[] { "§7[§c-§7] §eALL" });
          PetLogger.info("All skins removed from " + player.func_110124_au());
        }).exceptionally(e -> {
          e.printStackTrace();
          PetUtils.sendPrefixedMessage((ICommandSender)player, new String[] { "§7[§c!§7] §eALL" });
          return null;
        });
  }
  
  @SideOnly(Side.SERVER)
  private SessionPlayerMap<HashSet<String>> getPlayerSkinsMap() {
    return PetServerProxy.getInstance().getPlayerSkinsMap();
  }
  
  public String getTranslatedSkinName(String skinId) {
    return getTranslatedSkinName(null, skinId);
  }
  
  public String getTranslatedSkinName(EntityPlayer player, String skinId) {
    PetAdditionalData petData = PetCommonProxy.getInstance().findPet(skinId);
    if (petData == null)
      return skinId; 
    return TTT.format(player, getTTTSkinKey(skinId), new Object[0]);
  }
  
  private String getTTTSkinKey(String skinId) {
    return "shop.pet." + skinId + ".name";
  }
  
  @NonNull
  public static PetSkinCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static PetSkinClientProxy getClient() {
    return (PetSkinClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static PetSkinServerProxy getServer() {
    return (PetSkinServerProxy)proxy;
  }
  
  public static PetSkinShopProvider getInstance() {
    if (instance == null)
      throw new IllegalStateException("PetSkinShopProvider is not initialized"); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\PetSkinShopProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */