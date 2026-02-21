package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityCustomArrow;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import fr.paladium.palamod.modules.spellsv2.entity.GravityProjectile;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import java.time.Duration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHandlerArrow {
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onLivingHurt(LivingHurtEvent event) {
    try {
      if (event == null || event.source == null || event.entity == null || event.ammount <= 0.0F || event.isCanceled())
        return; 
      DamageSource source = event.source;
      if (source.func_76352_a()) {
        Entity entity = source.func_76364_f();
        if (entity instanceof EntityCustomArrow && event.entity instanceof EntityLivingBase) {
          EntityCustomArrow arrow = (EntityCustomArrow)entity;
          EntityLivingBase base = (EntityLivingBase)event.entity;
          int type = arrow.getType();
          if (event != null && event.entity != null && base != null && arrow != null && arrow.getPlayer() != null) {
            double originX, originY, originZ;
            ServerType serverType = CommonModule.getInstance().getConfig().getServerType();
            if (base instanceof EntityPlayer && (serverType == ServerType.FARMLAND || serverType == ServerType.MINAGE))
              return; 
            switch (type) {
              case 0:
                base.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 120, 1));
                break;
              case 1:
                base.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 180, 0));
                break;
              case 2:
                base.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 2));
                break;
              case 3:
                if (base instanceof net.minecraft.entity.passive.EntityChicken || base instanceof fr.paladium.palamod.modules.back2future.entities.EntityEndermite || base instanceof fr.paladium.palamod.modules.spellsv2.entity.EntityGhost || base instanceof fr.welsymc.guardiangolem.common.entities.EntityGolem) {
                  arrow.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas utiliser une Switch Arrow sur cette entité."));
                  return;
                } 
                if (base instanceof EntitySupremeWither && ((EntitySupremeWither)base).isLoading()) {
                  arrow.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas utiliser une Switch Arrow sur un Wither Suprême en train de charger."));
                  return;
                } 
                if (arrow.getPlayer().getEntityData().func_74764_b("switchArrow")) {
                  Duration duration = Duration.between(TimeUtil.fromLong(arrow.getPlayer().getEntityData().func_74763_f("switchArrow")), TimeUtil.nowZoned());
                  if (TimeUtil.fromLong(arrow.getPlayer().getEntityData().func_74763_f("switchArrow")).plusSeconds(10L).isAfter(TimeUtil.nowZoned())) {
                    arrow.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez attendre §e" + Math.max(0L, 10L - duration.getSeconds()) + "§c secondes avant de pouvoir réutiliser une Switch Arrow."));
                    return;
                  } 
                } 
                originX = (arrow.getOrigin() != null) ? arrow.getOrigin().getX() : (arrow.getPlayer()).field_70165_t;
                originY = (arrow.getOrigin() != null) ? arrow.getOrigin().getY() : (arrow.getPlayer()).field_70163_u;
                originZ = (arrow.getOrigin() != null) ? arrow.getOrigin().getZ() : (arrow.getPlayer()).field_70161_v;
                event.ammount = 0.0F;
                if (!(base instanceof EntityPlayer)) {
                  arrow.getPlayer().getEntityData().func_74772_a("switchArrow", TimeUtil.now());
                  arrow.getPlayer().func_70690_d(new PotionEffect(PRegister_Potions.potionSwitchArrow.getPotionId(), 220, 0));
                  if (base.func_70011_f(originX, originY, originZ) <= 15.0D && base.field_70163_u > 0.0D && (arrow.getPlayer()).field_70163_u > 0.0D) {
                    double targetX = base.field_70165_t;
                    double targetY = base.field_70163_u;
                    double targetZ = base.field_70161_v;
                    TeleportUtils.teleport(arrow.getPlayer(), targetX, targetY, targetZ);
                    base.func_70634_a(originX, originY, originZ);
                    event.ammount = 0.0F;
                    if (arrow.getPlayer() instanceof EntityPlayerMP)
                      SoundUtils.ENDERMAN_TELEPORT.playSound((EntityPlayerMP)arrow.getPlayer(), targetX, targetY, targetZ, 1.0F, 1.0F); 
                    if ((arrow.getPlayer()).field_70170_p instanceof WorldServer) {
                      WorldServer worldServer = (WorldServer)(arrow.getPlayer()).field_70170_p;
                      worldServer.func_73039_n().func_151247_a((Entity)base, (Packet)new S18PacketEntityTeleport((Entity)base));
                      worldServer.func_147487_a("portal", originX, originY, originZ, 50, 0.4D, 0.4D, 0.4D, 0.0D);
                      worldServer.func_147487_a("portal", targetX, targetY, targetZ, 50, 0.4D, 0.4D, 0.4D, 0.0D);
                    } 
                  } else {
                    arrow.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa distance maximale pour utiliser une Switch Arrow est de 15 blocs."));
                  } 
                } else {
                  arrow.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas utiliser une Switch Arrow sur un joueur."));
                } 
                event.setCanceled(true);
                break;
              case 4:
                if (base instanceof EntityPlayer)
                  base.func_70690_d(new PotionEffect(PRegister_Potions.potionFrost.field_76415_H, 400, 0)); 
                break;
            } 
          } 
        } 
      } 
      if (source.func_76352_a()) {
        Entity entity1 = source.func_76364_f();
        if (entity1 instanceof GravityProjectile && event.entity instanceof EntityPlayer) {
          if (MonthlyUtils.hasInvulnerabilityEffect((EntityLivingBase)event.entity))
            return; 
          ((EntityPlayer)event.entity).func_70690_d(new PotionEffect(PRegister_Potions.potionGravity.field_76415_H, 760, ((GravityProjectile)entity1)
                .getTier() - 1));
          ((EntityPlayer)event.entity).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 220));
        } 
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\EventHandlerArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */