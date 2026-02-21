package fr.paladium.palamod.modules.packetreducer.client;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.packetreducer.common.PPacketReducerProxyCommon;
import java.util.HashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PPacketReducerProxyClient extends PPacketReducerProxyCommon {
  private final HashSet<Integer> entitiesOnScreen = new HashSet<>();
  
  public static PPacketReducerProxyClient instance;
  
  public HashSet<Integer> getEntitiesOnScreen() {
    return this.entitiesOnScreen;
  }
  
  public void preInit() {
    instance = this;
  }
  
  public EntityPlayer getPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
  }
  
  public static PPacketReducerProxyClient getInstance() {
    return instance;
  }
  
  public static void setInstance(PPacketReducerProxyClient instance) {
    PPacketReducerProxyClient.instance = instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\client\PPacketReducerProxyClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */