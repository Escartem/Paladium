package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.WellCalibratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CSSatelliteHandlePacket implements IMessage {
  private boolean win;
  
  public CSSatelliteHandlePacket() {}
  
  public CSSatelliteHandlePacket(boolean win) {
    this.win = win;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.win = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.win);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSSatelliteHandlePacket, IMessage> {
    public IMessage onMessage(CSSatelliteHandlePacket message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (!WellCalibratedEvent.perform(player.func_110124_au())) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'a pas de satellite à calibrer." });
        return null;
      } 
      if (!message.win) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi à calibrer le satellite." });
        MonthlyUtils.playSound(player, "soft_fail");
        return null;
      } 
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aLa PSA te remercie pour tes services. Tu obtiens un unclaim finder orange." });
      InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_ORANGE, 1));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\cs\CSSatelliteHandlePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */