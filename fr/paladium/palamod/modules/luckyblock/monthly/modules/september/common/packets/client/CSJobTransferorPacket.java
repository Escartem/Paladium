package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemJobTransferor;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CSJobTransferorPacket implements IMessage {
  private JobType firstJob;
  
  private JobType secondJob;
  
  public CSJobTransferorPacket() {}
  
  public CSJobTransferorPacket(JobType firstJob, JobType secondJob) {
    this.firstJob = firstJob;
    this.secondJob = secondJob;
  }
  
  public void fromBytes(ByteBuf buf) {
    try {
      this.firstJob = JobType.values()[buf.readInt()];
      this.secondJob = JobType.values()[buf.readInt()];
    } catch (Exception e) {
      System.err.println("Failed to read packet: " + e.getMessage());
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.firstJob.ordinal());
    buf.writeInt(this.secondJob.ordinal());
  }
  
  public static class Handler implements IMessageHandler<CSJobTransferorPacket, IMessage> {
    public IMessage onMessage(CSJobTransferorPacket message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      World world = player.field_70170_p;
      ItemStack heldItem = player.func_70694_bm();
      if (heldItem == null || heldItem.func_77973_b() == null || !(heldItem.func_77973_b() instanceof ItemJobTransferor))
        return null; 
      ItemJobTransferor item = (ItemJobTransferor)heldItem.func_77973_b();
      item.apply(player, heldItem, message.firstJob, message.secondJob);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSJobTransferorPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */