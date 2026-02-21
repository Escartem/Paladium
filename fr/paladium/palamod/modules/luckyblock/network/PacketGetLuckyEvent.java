package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.gui.UILuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.data.PlayerLuckyStats;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.LuckyStatsError;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.util.RandomCollection;
import io.netty.buffer.ByteBuf;
import java.security.SecureRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class PacketGetLuckyEvent implements IMessage {
  private boolean cs;
  
  private int x;
  
  private int y;
  
  private int z;
  
  private LuckyEvents event;
  
  private boolean isNew;
  
  public PacketGetLuckyEvent() {}
  
  public PacketGetLuckyEvent(int x, int y, int z) {
    this.cs = true;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public PacketGetLuckyEvent(LuckyEvents event, boolean isNew) {
    this.cs = false;
    this.event = event;
    this.isNew = isNew;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.cs = buf.readBoolean();
    if (this.cs) {
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
    } else {
      try {
        this.event = LuckyEvents.values()[buf.readInt()];
      } catch (Exception e) {
        this.event = LuckyEvents.REMOVE_MONEY;
      } 
      this.isNew = buf.readBoolean();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.cs);
    if (this.cs) {
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
    } else {
      buf.writeInt(this.event.ordinal());
      buf.writeBoolean(this.isNew);
    } 
  }
  
  public static class Handler implements IMessageHandler<PacketGetLuckyEvent, IMessage> {
    public IMessage onMessage(PacketGetLuckyEvent message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT) {
        try {
          ((UILuckyBlock)(Minecraft.func_71410_x()).field_71462_r).setData(message.event, message.isNew);
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } else {
        EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
        WorldServer world = (WorldServer)player.field_70170_p;
        if (!world.func_72899_e(message.x, message.y, message.z))
          return null; 
        TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(message.x, message.y, message.z);
        if (te != null) {
          LuckyEvents event;
          LuckyType type = te.getType();
          if (te.getEvent() == null) {
            PlayerLuckyPassProperties playerLuckyPassProperties = PlayerLuckyPassProperties.get((EntityPlayer)player);
            event = LuckyEvents.fromEvent((ALuckyEvent)((RandomCollection)(playerLuckyPassProperties.isHasLuckyPass() ? PLuckyBlock.luckypassEvents : PLuckyBlock.events).get(type)).next());
            int tr = 0;
            while (event == null) {
              if (tr >= 10) {
                event = LuckyEvents.CONSOLATION;
                break;
              } 
              event = LuckyEvents.fromEvent((ALuckyEvent)((RandomCollection)(playerLuckyPassProperties.isHasLuckyPass() ? PLuckyBlock.luckypassEvents : PLuckyBlock.events).get(type)).next());
              tr++;
            } 
            int consolationRandom = (new SecureRandom()).nextInt((int)Math.pow(2.0D, 30.0D));
            if (consolationRandom <= PlayerLuckyBlockProperties.get((EntityPlayer)player).getCurrentChance())
              event = LuckyEvents.CONSOLATION; 
            if (event.getEvent().isBad())
              PlayerLuckyBlockProperties.get((EntityPlayer)player).setCurrentChance(PlayerLuckyBlockProperties.get((EntityPlayer)player).getCurrentChance() * 2); 
            te.setEvent(event);
          } else {
            event = te.getEvent();
          } 
          LuckyEvents finalEvent = event;
          (new PlayerLuckyStats((EntityPlayer)player)).load(stats -> PalaMod.getNetHandler().sendTo(new PacketGetLuckyEvent(finalEvent, !stats.getStats().containsKey(finalEvent)), player), error -> PalaMod.getNetHandler().sendTo(new PacketGetLuckyEvent(finalEvent, false), player));
        } else {
          PalaMod.getNetHandler().sendTo(new PacketGetLuckyEvent(LuckyEvents.BOOM, false), player);
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketGetLuckyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */