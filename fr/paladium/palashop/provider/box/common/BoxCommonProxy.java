package fr.paladium.palashop.provider.box.common;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.box.BoxProvider;
import fr.paladium.palashop.provider.box.common.dto.box.BoxData;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.palashop.provider.box.common.item.ItemBoxGift;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxReward;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxStart;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxStop;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxWait;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxKeepAlive;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxNext;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxReward;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxStart;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxStop;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BoxCommonProxy extends ShopProviderProxy {
  @ProviderListener
  public void onModnit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initItem();
    initEntity();
    initNetwork();
    initManager();
  }
  
  private void initItem() {
    RegistryUtils.item(new Item[] { (Item)new ItemBoxGift() });
  }
  
  private void initEntity() {
    EntityRegistry.registerModEntity(EntityBox.class, "EntityBox", 2, PalaShopMod.getInstance(), 100, 1, true);
  }
  
  private void initNetwork() {
    initNetwork(BoxProvider.getInstance().getId());
    PacketUtils.registerPacket(getNetwork(), CSPacketBoxStart.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketBoxStop.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketBoxNext.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketBoxReward.class);
    PacketUtils.registerPacket(getNetwork(), CSPacketBoxKeepAlive.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketBoxStart.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketBoxStop.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketBoxWait.class);
    PacketUtils.registerPacket(getNetwork(), SCPacketBoxReward.class);
  }
  
  private void initManager() {
    BoxManager.register(BoxData.create("paladium", "§6Paladium").item("box_paladium_key", new ResourceLocation("palashop", "boxes/paladium")).assets(new ResourceLocation("palashop", "boxes/paladium")).entitySize(2.5F, 2.0F).overlaySize(6.0F));
    BoxManager.register(BoxData.create("amethyst", "§dAméthyste").item("box_amethyst_key", new ResourceLocation("palashop", "boxes/amethyst")).assets(new ResourceLocation("palashop", "boxes/amethyst")).entitySize(2.5F, 2.0F).overlaySize(6.0F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\BoxCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */