package fr.paladium.palajobs.core.packets.server;

import com.google.gson.Gson;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.client.ui.boss.UIBoss;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import fr.paladium.palajobs.core.constant.BossConstants;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class SCPacketOpenBossGui implements IMessage {
  public SCPacketOpenBossGui() {}
  
  public SCPacketOpenBossGui(BossData data) {
    this.data = data;
  }
  
  private static final Gson gson = new Gson();
  
  private BossData data;
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, gson.toJson(this.data));
  }
  
  public void fromBytes(ByteBuf buf) {
    this.data = (BossData)gson.fromJson(ByteBufUtils.readUTF8String(buf), BossData.class);
  }
  
  public static class Handler implements IMessageHandler<SCPacketOpenBossGui, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketOpenBossGui message, MessageContext ctx) {
      (PalaJobs.getClient()).bossItem = (ItemStack)BossConstants.BOSS_ITEM.get(message.data.type);
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIBoss((GuiScreen)new UIJobsHome(), message.data));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketOpenBossGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */