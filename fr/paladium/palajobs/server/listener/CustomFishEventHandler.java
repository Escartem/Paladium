package fr.paladium.palajobs.server.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palajobs.api.event.OnPlayerFish;
import fr.paladium.palajobs.core.entity.EntityCustomFishHook;
import fr.paladium.palajobs.core.jobs.requirement.FishingRequirement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.FishingObjective;
import fr.paladium.palajobs.core.quest.types.FishingQuest;
import fr.paladium.palapass.common.quest.misc.FishingQuest;
import fr.paladium.worldguardflagplus.WorldGuardFlagPlus;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class CustomFishEventHandler {
  @SubscribeEvent
  public void onUseFishingRod(PlayerInteractEvent e) {
    if (e.action != PlayerInteractEvent.Action.RIGHT_CLICK_AIR && e.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
      return; 
    EntityPlayer player = e.entityPlayer;
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    ItemStack item = player.field_71071_by.func_70448_g();
    if (item == null || !(item.func_77973_b() instanceof net.minecraft.item.ItemFishingRod) || (e.face == 0 && e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
      return; 
    if (WorldGuardUtils.checkFlag(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, WorldGuardFlagPlus.USE_FISHING_ROD, true)) {
      EntityFishHook fishEntity = player.field_71104_cf;
      boolean isFishing = (fishEntity != null);
      if (!isFishing) {
        world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (world.field_73012_v.nextFloat() * 0.4F + 0.8F));
        if (!world.field_72995_K)
          world.func_72838_d((Entity)new EntityCustomFishHook(world, player)); 
      } else {
        fishEntity.func_70106_y();
      } 
      player.func_71038_i();
    } 
    e.setResult(Event.Result.DENY);
    e.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onPlayerFish(OnPlayerFish event) {
    EntityPlayer player = event.player;
    ItemStack reward = event.reward;
    if (player.field_70170_p.field_72995_K)
      return; 
    FishingObjective.performCheck(player, reward);
    FishingQuest.performCheck(player, reward);
    JobsPlayer.get((Entity)player).getRequirements(FishingRequirement.class).forEach(optional -> optional.ifPresent(()));
    FishingQuest.trigger(player, reward, reward.field_77994_a);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\listener\CustomFishEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */