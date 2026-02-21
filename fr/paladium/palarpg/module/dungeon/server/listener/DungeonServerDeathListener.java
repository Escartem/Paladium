package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.network.death.SCPacketRPGDungeonDeathUseRespawn;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.server.event.DungeonPlayerDeathEvent;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DungeonServerDeathListener {
  private final Map<String, Long> INVULNERABILITY_MAP = new HashMap<>();
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onDeathWithRespawn(DungeonPlayerDeathEvent.Pre event) {
    Optional<EntityPlayer> optionalPlayer = event.getPlayer().getOnlinePlayer();
    if (!optionalPlayer.isPresent())
      return; 
    EntityPlayer player = optionalPlayer.get();
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      ItemStack stack = player.field_71071_by.field_70462_a[i];
      if (stack != null && stack.func_77973_b() != null && stack.field_77994_a > 0) {
        Optional<IRPGItem> optionalItem = IRPGItem.get(stack);
        if (optionalItem.isPresent()) {
          IRPGItem rpgItem = optionalItem.get();
          Optional<Boolean> tagRespawn = rpgItem.getTag("respawn");
          if (tagRespawn.isPresent() && ((Boolean)tagRespawn.get()).booleanValue()) {
            event.setCanceled(true);
            player.field_71071_by.field_70462_a[i] = null;
            if (player instanceof EntityPlayerMP)
              (new SCPacketRPGDungeonDeathUseRespawn(stack)).send((EntityPlayerMP)player); 
            this.INVULNERABILITY_MAP.put(player.func_70005_c_(), Long.valueOf(System.currentTimeMillis() + 3000L));
            return;
          } 
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onInvulnerabilityDamage(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!this.INVULNERABILITY_MAP.containsKey(player.func_70005_c_()))
      return; 
    long time = ((Long)this.INVULNERABILITY_MAP.get(player.func_70005_c_())).longValue();
    if (System.currentTimeMillis() > time) {
      this.INVULNERABILITY_MAP.remove(player.func_70005_c_());
      return;
    } 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onInvulnerabilityAttack(LivingAttackEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!this.INVULNERABILITY_MAP.containsKey(player.func_70005_c_()))
      return; 
    long time = ((Long)this.INVULNERABILITY_MAP.get(player.func_70005_c_())).longValue();
    if (System.currentTimeMillis() > time) {
      this.INVULNERABILITY_MAP.remove(player.func_70005_c_());
      return;
    } 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onDeathInteraction(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive())
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onDeathDamage(LivingHurtEvent event) {
    if (event.source == null || !(event.source.func_76364_f() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.source.func_76364_f();
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive())
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onDeathAttack(LivingAttackEvent event) {
    if (event.source == null || !(event.source.func_76364_f() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.source.func_76364_f();
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive())
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerDeathListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */