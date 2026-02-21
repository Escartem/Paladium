package fr.paladium.palamod.modules.luckyblock.luckystats.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.item.UIShopLuckyStatsItemPage;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BBPacketShopLuckyStatsData implements IMessage {
  private PlayerLuckyStats data;
  
  public BBPacketShopLuckyStatsData() {}
  
  public BBPacketShopLuckyStatsData(PlayerLuckyStats data) {
    this.data = data;
  }
  
  public void fromBytes(ByteBuf buf) {
    if (buf.isReadable()) {
      this.data = new PlayerLuckyStats();
      this.data.read(buf);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    if (this.data != null)
      this.data.write(buf); 
  }
  
  public static class Handler implements IMessageHandler<BBPacketShopLuckyStatsData, IMessage> {
    public IMessage onMessage(BBPacketShopLuckyStatsData message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT) {
        UIShopLuckyStatsItemPage ui = (UIShopLuckyStatsItemPage)ZUI.getUI(UIShopLuckyStatsItemPage.class);
        if (ui != null)
          ui.getDataSignal().set(message.data); 
      } else {
        EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
        (new PlayerLuckyStats((EntityPlayer)player)).load(t -> PalaMod.getNetHandler().sendTo(new BBPacketShopLuckyStatsData(t), player), e -> player.func_145747_a((IChatComponent)new ChatComponentText(e.getDefaultError())));
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckystats\common\network\BBPacketShopLuckyStatsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */