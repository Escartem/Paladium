package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PacketWinLuckyPass implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<PacketWinLuckyPass, IMessage> {
    public IMessage onMessage(PacketWinLuckyPass message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      PlayerLuckyPassProperties prop = PlayerLuckyPassProperties.get((EntityPlayer)entityPlayerMP);
      if (!prop.isHasLuckyPass())
        return null; 
      long now = TimeUtils.nowZoned().getDayOfYear();
      long date = prop.getDate();
      if (now != date || ForgeEnv.isIDE()) {
        if (!BukkitUtils.hasPermission(entityPlayerMP.func_110124_au(), "palamod.luckypass"))
          return null; 
        prop.setDate(now);
        prop.playerStartedTracking((EntityPlayer)entityPlayerMP);
        for (int i : prop.getWins()) {
          if (i != -1) {
            ((EntityPlayer)entityPlayerMP).field_70170_p.func_72956_a((Entity)entityPlayerMP, "fireworks.launch", 3.0F, 1.0F);
            EventUtils.spawnParticle(((EntityPlayer)entityPlayerMP).field_70170_p, "fireworksSpark", ((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v, 100, 0.3D);
            ItemStack is = LuckyType.Util.getIconFrom(LuckyType.values()[i]).func_77946_l();
            if (!((EntityPlayer)entityPlayerMP).field_71071_by.func_70441_a(is))
              PlayerUtils.dropItemStack((Entity)entityPlayerMP, ((EntityPlayer)entityPlayerMP).field_70165_t, ((EntityPlayer)entityPlayerMP).field_70163_u, ((EntityPlayer)entityPlayerMP).field_70161_v, is); 
          } 
        } 
      } else {
        entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§7[§aLuckyPass§7] §bRevenez demain pour gagner vos LuckyBlock !"));
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PacketWinLuckyPass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */