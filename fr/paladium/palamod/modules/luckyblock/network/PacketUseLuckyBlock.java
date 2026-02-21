package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.bigbrother.lib.metrics.MetricCounter;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.metrics.bigbrother.BigBrotherImpl;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class PacketUseLuckyBlock implements IMessage {
  public boolean cancel;
  
  public int x;
  
  public int y;
  
  public int z;
  
  public PacketUseLuckyBlock() {}
  
  public PacketUseLuckyBlock(boolean cancel, int x, int y, int z) {
    this.cancel = cancel;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.cancel = buf.readBoolean();
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.cancel);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<PacketUseLuckyBlock, IMessage> {
    public IMessage onMessage(PacketUseLuckyBlock message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (message.cancel) {
        WorldServer world = (WorldServer)player.field_70170_p;
        if (!world.func_72899_e(message.x, message.y, message.z) || !(world.func_147438_o(message.x, message.y, message.z) instanceof TileEntityLuckyBlock))
          return null; 
        TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(message.x, message.y, message.z);
        if (te == null)
          return null; 
        te.setOpen(false);
        return null;
      } 
      PacketUseLuckyBlock.perform(player, message.x, message.y, message.z);
      return null;
    }
  }
  
  public static void perform(EntityPlayerMP player, int x, int y, int z) {
    WorldServer world = (WorldServer)player.field_70170_p;
    if (!world.func_72899_e(x, y, z) || !(world.func_147438_o(x, y, z) instanceof TileEntityLuckyBlock))
      return; 
    TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(x, y, z);
    if (te == null)
      return; 
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      te.setOpen(false);
      world.func_147468_f(x, y, z);
      return;
    } 
    if (te != null && te.getEvent() != null) {
      if (ConfigManager.hasKey("luckyblock", String.valueOf(LuckyEvents.indexOf(te.getEvent()))) && 
        ConfigManager.getBoolean("luckyblock", String.valueOf(LuckyEvents.indexOf(te.getEvent())))) {
        te.setOpen(false);
        PlayerUtils.sendMessage((EntityPlayer)player, "§8[§6Paladium§8] §cCet évenement est en cours de maintenance");
        te.setEvent(null);
        return;
      } 
      world.func_147468_f(x, y, z);
      world.func_147487_a("fireworksSpark", x + 0.5D, y, z + 0.5D, 500, 0.1D, 1.0D, 0.1D, 0.1D);
      SoundUtils.FIREWORK_BLAST.playSound(player, x, y, z, 1.0F, 1.0F);
      if (!Utils.isDev())
        try {
          BigBrotherImpl.instance.getPlayerSession().increment(FastUUID.toString((Entity)player), "luckyblockOpened", 1.0D);
          MetricCounter metricLuckyblockType = (new MetricCounter()).setName("luckyblock_type").register(BigBrotherImpl.instance.getApi());
          String type = (te.getType() != null) ? te.getType().name().toLowerCase() : "paladium";
          metricLuckyblockType.newInstance(type, 1.0D);
        } catch (Exception e) {
          e.printStackTrace();
        }  
      LuckyEvents.values()[LuckyEvents.indexOf(te.getEvent())].getEvent().perform(player, x, y, z);
      (new PlayerLuckyStats((EntityPlayer)player)).load(t -> {
            if (!t.getStats().containsKey(te.getEvent())) {
              t.addStat(te.getEvent(), Long.valueOf(TimeUtils.now()));
              t.save((), ());
            } 
          }error -> player.func_145747_a((IChatComponent)new ChatComponentText(error.getDefaultError())));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUseLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */