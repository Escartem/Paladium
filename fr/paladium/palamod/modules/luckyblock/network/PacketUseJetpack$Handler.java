package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Handler implements IMessageHandler<PacketUseJetpack, IMessage> {
  public IMessage onMessage(PacketUseJetpack message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (WorldGuardUtils.isItemEffectBlocked(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, Item.func_150891_b(ItemsRegister.JETPACK)))
      return null; 
    if (PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      (new Notification(Notification.NotificationType.WARNING, "L'item jetpack est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send(player);
      return null;
    } 
    if (player.func_82169_q(2) == null)
      return null; 
    if (player.func_82169_q(2).func_77973_b() == ItemsRegister.JETPACK) {
      if (player.field_70163_u > 256.0D) {
        EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 20, 0.15D);
        return null;
      } 
      if (message.reset) {
        if (player.field_70122_E)
          player.func_82170_o(Potion.field_76429_m.field_76415_H); 
      } else {
        if (player.field_70163_u <= 0.0D)
          return null; 
        if (player.field_71071_by.func_146028_b(Items.field_151152_bP)) {
          player.func_82169_q(2).func_77972_a(1, (EntityLivingBase)player);
          if (player.func_82169_q(2).func_77960_j() >= player.func_82169_q(2)
            .func_77958_k())
            player.func_70062_b(3, null); 
          player.field_71071_by.func_146026_a(Items.field_151152_bP);
          player.field_70181_x = 2.0D;
          player.field_70143_R = 0.0F;
          player.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
          EventUtils.spawnParticle(player.field_70170_p, "smoke", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10, 0.1D);
          EventUtils.spawnParticle(player.field_70170_p, "flame", player.field_70165_t, player.field_70163_u, player.field_70161_v, 10, 0.1D);
          player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 200, 9, true));
        } 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUseJetpack$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */