package fr.paladium.palamod.modules.mailbox.client;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.mailbox.common.PMailboxProxyCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PMailboxProxyClient extends PMailboxProxyCommon {
  public static PMailboxProxyClient instance;
  
  public void preInit() {
    instance = this;
  }
  
  public EntityPlayer getPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
  }
  
  public static PMailboxProxyClient getInstance() {
    return instance;
  }
  
  public static void setInstance(PMailboxProxyClient instance) {
    PMailboxProxyClient.instance = instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\PMailboxProxyClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */