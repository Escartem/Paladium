package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.SummerSalesUI;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

public class SCSummerSalePacket implements IMessage {
  public static final int SUMMER_ID = 0;
  
  public static final int FORTUNE_ID = 1;
  
  private List<ItemStack> rewards;
  
  private ItemStack reward;
  
  private int id;
  
  public SCSummerSalePacket() {
    this.rewards = new ArrayList<>();
    this.reward = null;
    this.id = 0;
  }
  
  public SCSummerSalePacket(List<ItemStack> rewards, ItemStack reward, int id) {
    this.rewards = rewards;
    this.reward = reward;
    this.id = id;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
    int size = buf.readInt();
    for (int i = 0; i < size; i++)
      this.rewards.add(ByteBufUtils.readItemStack(buf)); 
    this.reward = ByteBufUtils.readItemStack(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
    buf.writeInt(this.rewards.size());
    for (ItemStack reward : this.rewards)
      ByteBufUtils.writeItemStack(buf, reward); 
    ByteBufUtils.writeItemStack(buf, this.reward);
  }
  
  public static class Handler implements IMessageHandler<SCSummerSalePacket, IMessage> {
    @SideOnly(Side.CLIENT)
    public void openUI(List<ItemStack> rewards, ItemStack reward, int id) {
      if ((Minecraft.func_71410_x()).field_71462_r != null)
        return; 
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new SummerSalesUI(rewards, reward, id));
    }
    
    public IMessage onMessage(SCSummerSalePacket message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT)
        return null; 
      openUI(message.rewards, message.reward, message.id);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCSummerSalePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */