package fr.paladium.palamod.modules.ajobs.server;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.event.OnPlayerPreEarnXp;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palamixins.event.SlotCanTakeEvent;
import fr.paladium.palamod.events.BlockPistonExtendEvent;
import fr.paladium.palamod.events.BlockPistonRetractEvent;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.ajobs.common.items.ItemJobBottle;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import fr.paladium.tutorial.common.JobXpBottleGenerateEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;

public class AJobsEventHandler {
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onPickupVanillaXp(PlayerPickupXpEvent event) {
    if (event.entityPlayer == null || event.entityPlayer.field_70170_p.field_72995_K)
      return; 
    EntityPlayer player = event.entityPlayer;
    int rankValue = ((Integer)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)player), "palamod.vanillaxp.value.", Integer.class).orElse(Integer.valueOf(0))).intValue();
    int bonusValue = ((Integer)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)player), "palamod.vanillaxp.bonus.", Integer.class).orElse(Integer.valueOf(0))).intValue();
    int totalValue = rankValue + bonusValue;
    if (totalValue <= 0)
      return; 
    double multiplier = totalValue / 100.0D;
    int bonus = (int)Math.round(event.orb.field_70530_e * multiplier);
    event.orb.field_70530_e += bonus;
  }
  
  @SubscribeEvent
  public void onPlayerEarnXp(OnPlayerPreEarnXp e) {
    if (e.objectiveType == ObjectiveType.XP_BOTTLER)
      return; 
    EntityPlayer player = e.player;
    boolean hasExpBottler = false;
    if (player.func_70694_bm() != null && EnchantmentHelper.func_77506_a(PMiner.xp_bottler.field_77352_x, player.func_70694_bm()) > 0)
      hasExpBottler = true; 
    if (!hasExpBottler)
      for (ItemStack element : player.field_71071_by.field_70460_b) {
        if (element != null && EnchantmentHelper.func_77506_a(PMiner.xp_bottler.field_77352_x, element) > 0) {
          hasExpBottler = true;
          break;
        } 
      }  
    if (!hasExpBottler)
      return; 
    double xpEarn = e.xpearn;
    if (xpEarn <= 0.0D)
      return; 
    ItemJobBottle itemRef = ItemJobBottle.get(e.jobType);
    if (player.field_71071_by.func_146028_b((Item)itemRef))
      for (ItemStack item : player.field_71071_by.field_70462_a) {
        if (item != null && item.func_77973_b() == itemRef) {
          ItemJobBottle jobBottle = (ItemJobBottle)item.func_77973_b();
          if (jobBottle.getJob() == e.jobType) {
            double currentXp = jobBottle.getXp(item);
            if (currentXp < 1000.0D) {
              xpEarn -= jobBottle.addXp(item, Math.round(xpEarn / item.field_77994_a * Math.pow(10.0D, 2.0D)) / Math.pow(10.0D, 2.0D)) * item.field_77994_a;
              e.setCanceled(true);
              if (xpEarn > 0.0D && xpEarn != e.xpearn)
                JobsPlayer.get((Entity)player).addXp(e.jobType, e.objectiveType, xpEarn, player, e.multiplier); 
              return;
            } 
          } 
        } 
      }  
    if (player.field_71071_by.func_146028_b(Items.field_151069_bo)) {
      ItemStack bottle = null;
      int slot = 0;
      int i = 0;
      for (ItemStack itemStack : player.field_71071_by.field_70462_a) {
        if (itemStack != null && itemStack.func_77973_b() == Items.field_151069_bo && (
          bottle == null || bottle.field_77994_a > itemStack.field_77994_a)) {
          bottle = itemStack;
          slot = i;
        } 
        i++;
      } 
      if (bottle == null || bottle.field_77994_a <= 0)
        return; 
      player.field_71071_by.func_70299_a(slot, null);
      double xp = Math.min(Math.round(xpEarn / bottle.field_77994_a * Math.pow(10.0D, 2.0D)) / Math.pow(10.0D, 2.0D), 1000.0D);
      if (xp <= 0.0D)
        return; 
      ItemStack item = itemRef.create(xp);
      item.field_77994_a = bottle.field_77994_a;
      MinecraftForge.EVENT_BUS.post((Event)new JobXpBottleGenerateEvent(player, item));
      if (!player.field_71071_by.func_70441_a(item))
        ItemUtils.spawnItemAtEntity((Entity)player, item); 
      xpEarn -= xp * bottle.field_77994_a;
      e.setCanceled(true);
      if (xpEarn > 0.0D && xpEarn != e.xpearn)
        JobsPlayer.get((Entity)player).addXp(e.jobType, e.objectiveType, xpEarn, player, e.multiplier); 
    } 
  }
  
  @SubscribeEvent
  public void breakEvent(BlockEvent.HarvestDropsEvent event) {
    if (event.harvester == null || EnchantmentHelper.func_77502_d((EntityLivingBase)event.harvester))
      return; 
    if (event.harvester.func_82165_m(PMiner.HARDNESS.field_76415_H) && event.block instanceof net.minecraft.block.BlockObsidian)
      JobsPlayer.get((Entity)event.harvester).addXp(JobType.MINER, ObjectiveType.FISH, 4.0D, event.harvester); 
  }
  
  @SubscribeEvent
  public void onPistonExtend(BlockPistonExtendEvent event) {
    for (BlockLocation location : event.getBlocks()) {
      PlacedBlockData.setPlaced(location.getWorld(), location.getX(), location.getY(), location.getZ());
      PlacedBlockData.setPlaced(location.getWorld(), location.getX() + (event.getDirection()).offsetX, location.getY() + (event.getDirection()).offsetY, location.getZ() + (event.getDirection()).offsetZ);
    } 
  }
  
  @SubscribeEvent
  public void onPistonRetract(BlockPistonRetractEvent event) {
    PlacedBlockData.setPlaced(event.getWorld(), event.getX() + (event.getDirection()).offsetX, event.getY() + (event.getDirection()).offsetY, event.getZ() + (event.getDirection()).offsetZ);
  }
  
  @SubscribeEvent
  public void onSlotCanTakeStack(SlotCanTakeEvent event) {
    if (event.getSlot() instanceof net.minecraft.inventory.SlotCrafting && event.getItemStack() != null && PalaJobsAPI.inst().isCraftBlackListed(event.getItemStack()).isPresent() && !JobsBridge.canUseCraft(event.getPlayer(), event.getItemStack(), false))
      event.setCanceled(true); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\server\AJobsEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */