package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.paladium.client.ui.UIScanStick;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class Handler implements IMessageHandler<PacketListPlayer, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketListPlayer message, MessageContext ctx) {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (entityClientPlayerMP.func_70694_bm() != null && entityClientPlayerMP.func_70694_bm().func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemStickModeration && 
      ZUI.isOpen(UIScanStick.class)) {
      UIScanStick ui = (UIScanStick)ZUI.getUI(UIScanStick.class);
      if (message.players == null)
        return null; 
      if (message.players.isEmpty() && ForgeEnv.isDev()) {
        message.players.add("AugmaTV");
        message.players.add("Zeldown");
        message.players.add("SK0R3N");
      } 
      ui.setPlayerList(message.players);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketListPlayer$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */