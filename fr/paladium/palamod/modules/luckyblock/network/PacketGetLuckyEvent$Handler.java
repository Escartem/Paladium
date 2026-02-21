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
import java.security.SecureRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class Handler implements IMessageHandler<PacketGetLuckyEvent, IMessage> {
  public IMessage onMessage(PacketGetLuckyEvent message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT) {
      try {
        ((UILuckyBlock)(Minecraft.func_71410_x()).field_71462_r).setData(PacketGetLuckyEvent.access$000(message), PacketGetLuckyEvent.access$100(message));
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } else {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      WorldServer world = (WorldServer)player.field_70170_p;
      if (!world.func_72899_e(PacketGetLuckyEvent.access$200(message), PacketGetLuckyEvent.access$300(message), PacketGetLuckyEvent.access$400(message)))
        return null; 
      TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(PacketGetLuckyEvent.access$200(message), PacketGetLuckyEvent.access$300(message), PacketGetLuckyEvent.access$400(message));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketGetLuckyEvent$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */