package fr.paladium.palashop.common.provider.dto.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import lombok.NonNull;
import net.minecraftforge.common.MinecraftForge;

public class ShopProviderProxy {
  private SimpleNetworkWrapper network;
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  protected void initNetwork(@NonNull String channelName) {
    if (channelName == null)
      throw new NullPointerException("channelName is marked non-null but is null"); 
    this.network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName.toUpperCase());
  }
  
  protected void addPacket(@NonNull Class<? extends ForgePacket>... packets) {
    if (packets == null)
      throw new NullPointerException("packets is marked non-null but is null"); 
    for (Class<? extends ForgePacket> packet : packets)
      PacketUtils.registerPacket(this.network, packet); 
  }
  
  protected void addListener(@NonNull Class<?>... listeners) {
    if (listeners == null)
      throw new NullPointerException("listeners is marked non-null but is null"); 
    for (Class<?> listener : listeners) {
      try {
        Object instanciedListener = listener.newInstance();
        MinecraftForge.EVENT_BUS.register(instanciedListener);
        FMLCommonHandler.instance().bus().register(instanciedListener);
      } catch (InstantiationException|IllegalAccessException e) {
        e.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\dto\impl\ShopProviderProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */