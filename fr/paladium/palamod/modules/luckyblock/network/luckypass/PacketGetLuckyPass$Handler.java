package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.luckyblock.gui.luckypass.UILuckyPass;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<PacketGetLuckyPass, IMessage> {
  public IMessage onMessage(PacketGetLuckyPass message, MessageContext ctx) {
    if (ctx.side == Side.SERVER) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get((EntityPlayer)entityPlayerMP);
      long now = System.currentTimeMillis();
      long last = properties.getWinsDate();
      if (last != 0L && now - last < 3600000L && !ForgeEnv.isIDE())
        return new PacketGetLuckyPass(properties.getWins()); 
      Random r = ((EntityPlayer)entityPlayerMP).field_70170_p.field_73012_v;
      int[] wins = { -1, 0, -1 };
      int p = r.nextInt(100);
      if (p < 10) {
        wins = new int[] { 0, 0, 0 };
      } else if (p < 40) {
        wins = new int[] { 0, 0, -1 };
      } 
      for (int i = 0; i < wins.length; i++) {
        if (wins[i] != -1)
          wins[i] = LuckyType.Util.getRandomType().ordinal(); 
      } 
      properties.setWinsDate(now);
      properties.setWins(wins);
      properties.playerStartedTracking((EntityPlayer)entityPlayerMP);
      return new PacketGetLuckyPass(wins);
    } 
    ((UILuckyPass)(Minecraft.func_71410_x()).field_71462_r).setData(PacketGetLuckyPass.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PacketGetLuckyPass$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */