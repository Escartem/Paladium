package fr.paladium.palamod.modules.mailbox.common;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public class PMailboxProxyCommon {
  public void preInit() {}
  
  public EntityPlayer getPlayerEntity(MessageContext ctx) {
    return (EntityPlayer)(ctx.getServerHandler()).field_147369_b;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\PMailboxProxyCommon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */