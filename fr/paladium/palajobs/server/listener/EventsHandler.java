package fr.paladium.palajobs.server.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palajobs.api.event.OnJobBossDeathEvent;
import fr.paladium.palajobs.api.event.wither.PlayerSummonWitherEvent;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.constant.BossConstants;
import fr.paladium.palajobs.core.entity.boss.AEntityJobBoss;
import fr.paladium.palajobs.core.jobs.requirement.BreakBlockRequirement;
import fr.paladium.palajobs.core.jobs.requirement.EntityKillRequirement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.boss.BossState;
import fr.paladium.palajobs.core.pojo.boss.JobBossEep;
import fr.paladium.palajobs.core.pojo.objectives.types.ArrowKillObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.BreakBlockObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillObjective;
import fr.paladium.palajobs.core.pojo.objectives.types.WitherSpawnObjective;
import fr.paladium.palajobs.core.quest.types.BlockBreakQuest;
import fr.paladium.palajobs.core.quest.types.EntityKillQuest;
import fr.paladium.palajobs.core.quest.types.ItemUseQuest;
import fr.paladium.palajobs.core.utils.ChatUtils;
import fr.paladium.palajobs.core.utils.MultiplierUtils;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.EnchantmentUtils;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palawither.common.utils.WitherUtils;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.translate.common.texttotranslate.utils.TTTChat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsHandler {
  private final Map<UUID, Float> bossDamage = new HashMap<>();
  
  private float bossTotalDamage;
  
  private JobsManager jobsManager;
  
  @SubscribeEvent
  public void onJoinBoss(PlayerEvent.PlayerLoggedInEvent e) {
    EntityPlayer player = e.player;
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    if (JobsManager.getInstance().getApi() == null || JobsManager.getInstance().getBossData() == null || jobsPlayer == null || jobsPlayer.getBossValue() == null || jobsPlayer.getBossValue().isEmpty())
      return; 
    String currentBoss = (JobsManager.getInstance().getBossData()).uuid.toString();
    Set<String> toRemove = new HashSet<>();
    for (Map.Entry<String, Map.Entry<JobType, Integer>> entry : (Iterable<Map.Entry<String, Map.Entry<JobType, Integer>>>)jobsPlayer.getBossValue().entrySet()) {
      if (((String)entry.getKey()).equals(currentBoss))
        continue; 
      float percentage = ((Integer)((Map.Entry)entry.getValue()).getValue()).intValue() / ((Integer)BossConstants.BOSS_QUANTITY.get(((Map.Entry)entry.getValue()).getKey())).intValue();
      jobsPlayer.addXp((JobType)((Map.Entry)entry.getValue()).getKey(), ObjectiveType.ENTITY_KILL_SPECIAL, (500000.0F * percentage), player, 0.0D);
      toRemove.add(entry.getKey());
      TTTChat.sendMessage(player, "message.jobs.boss.participate", new Object[] { String.format("%.2f", new Object[] { Float.valueOf(500000.0F * percentage) }), ((JobType)((Map.Entry)entry.getValue()).getKey()).getPrefix() + TTT.format(player, ((JobType)((Map.Entry)entry.getValue()).getKey()).getName(), new Object[0]) });
    } 
    toRemove.forEach(jobsPlayer.getBossValue()::remove);
  }
  
  @SubscribeEvent
  public void onKillBoss(LivingDeathEvent e) {
    if (e.entity instanceof AEntityJobBoss && !e.entity.field_70170_p.field_72995_K && 
      JobsManager.getInstance().getApi() != null && JobsManager.getInstance().getBossData() != null && (JobsManager.getInstance().getBossData()).state == BossState.SPAWNED) {
      JobsManager.getInstance().getApi().finish().enqueue(new Callback<String>() {
            public void onResponse(Call<String> call, Response<String> response) {
              JobsManager.getInstance().fetchBossDataAsync();
            }
            
            public void onFailure(Call<String> call, Throwable error) {
              error.printStackTrace();
            }
          });
      Optional<Map.Entry<UUID, Float>> optional = this.bossDamage.entrySet().stream().sorted(Collections.reverseOrder((Comparator)Map.Entry.comparingByValue())).findFirst();
      if (!optional.isPresent()) {
        TTTChat.broadcast("message.jobs.boss.kill.error", new Object[0]);
        this.bossDamage.clear();
        this.bossTotalDamage = 0.0F;
        return;
      } 
      int xp = 10000 * this.bossDamage.size();
      Map.Entry<UUID, Float> entry = optional.get();
      EntityPlayer winner = e.entity.field_70170_p.func_152378_a(entry.getKey());
      this.bossDamage.forEach((uuid, damage) -> {
            EntityPlayer player = e.entity.field_70170_p.func_152378_a(uuid);
            if (player != null) {
              float p = damage.floatValue() / this.bossTotalDamage;
              JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
              if (jobsPlayer != null)
                jobsPlayer.addXp(((AEntityJobBoss)e.entity).type, ObjectiveType.ENTITY_KILL_SPECIAL, (xp * p), player, 0.0D); 
              TTTChat.sendMessage(player, "message.jobs.boss.kill", new Object[] { String.format("%.2f", new Object[] { Float.valueOf(p * 100.0F) }), Float.valueOf(xp * p), TTT.format(player, ((AEntityJobBoss)e.entity).type.getName(), new Object[0]) });
            } 
          });
      if (winner == null) {
        TTTChat.broadcast("message.jobs.boss.kill.error", new Object[0]);
        this.bossDamage.clear();
        this.bossTotalDamage = 0.0F;
        return;
      } 
      JobBossEep eep = JobBossEep.get((Entity)winner);
      eep.getBossStat(((AEntityJobBoss)e.entity).type.getName()).addKill();
      float lastWinPercentage = ((Float)entry.getValue()).floatValue() / this.bossTotalDamage;
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("§fFélicitation à §c" + winner.func_70005_c_());
      ChatUtils.broadcastCenteredMessage("§fQui a vaincu le boss métier");
      ChatUtils.broadcastCenteredMessage("§fAvec §c" + String.format("%.2f", new Object[] { Float.valueOf(lastWinPercentage * 100.0F) }) + "% §fde dégâts");
      ChatUtils.broadcastCenteredMessage("");
      ChatUtils.broadcastCenteredMessage("§8§m---------------------------------");
      ChatUtils.broadcastCenteredMessage("");
      MinecraftForge.EVENT_BUS.post((Event)new OnJobBossDeathEvent((AEntityJobBoss)e.entity, this.bossDamage, this.bossTotalDamage, winner));
      this.bossDamage.clear();
      this.bossTotalDamage = 0.0F;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onDamageBoss(LivingHurtEvent e) {
    if (e.entity instanceof AEntityJobBoss && !e.entity.field_70170_p.field_72995_K) {
      Entity sourceOfDomage = e.source.func_76364_f();
      EntityPlayer tmpDamager = null;
      if (sourceOfDomage instanceof EntityPlayer) {
        tmpDamager = (EntityPlayer)e.source.func_76364_f();
      } else if (sourceOfDomage instanceof EntityThrowable && ((EntityThrowable)sourceOfDomage).func_85052_h() instanceof EntityPlayer) {
        tmpDamager = (EntityPlayer)((EntityThrowable)sourceOfDomage).func_85052_h();
      } 
      EntityPlayer damager = tmpDamager;
      if (damager == null)
        return; 
      this.bossTotalDamage += e.ammount;
      float playerDamage = ((Float)this.bossDamage.getOrDefault(damager.func_110124_au(), Float.valueOf(0.0F))).floatValue();
      playerDamage += e.ammount;
      this.bossDamage.put(damager.func_110124_au(), Float.valueOf(playerDamage));
      JobBossEep eep = JobBossEep.get((Entity)damager);
      eep.getBossStat(((AEntityJobBoss)e.entity).type.getName()).addDamage(e.ammount);
    } 
  }
  
  @SubscribeEvent
  public void onDamageEntity(AttackEntityEvent event) {
    EntityPlayer player = event.entityPlayer;
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    ItemStack stack = player.func_71045_bC();
    if (stack == null)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (getJobsManager().canUseUseItem(player, data, stack) != null) {
      PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour utiliser ceci." });
      event.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onInteract(PlayerUseItemEvent.Start event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    ItemStack stack = event.item;
    if (stack == null)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (getJobsManager().canUseUseItem(player, data, stack) != null) {
      PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour utiliser ceci." });
      event.setCanceled(true);
      return;
    } 
    ItemUseQuest.performCheck(player, stack);
  }
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    ItemStack stack = player.func_71045_bC();
    if (stack == null)
      return; 
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (stack.func_77973_b() instanceof net.minecraft.item.ItemFishingRod && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.face == 0)
      return; 
    if (getJobsManager().canUseUseItem(player, data, stack) != null) {
      PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour utiliser ceci." });
      event.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onKillBow(LivingDeathEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K || !event.source.func_76352_a() || !(event.source.func_76346_g() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
    ArrowKillObjective.performCheck(player, event.entityLiving.getClass());
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onKillEntity(LivingDeathEvent event) {
    if (event.entityLiving.field_70170_p.field_72995_K || event.isCanceled() || !(event.source.func_76346_g() instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
    EntityKillObjective.performCheck(player, event.entityLiving.getClass());
    EntityKillQuest.performCheck(player, event.entityLiving.getClass());
    JobsPlayer.get((Entity)player).getRequirements(EntityKillRequirement.class).forEach(optional -> optional.ifPresent(()));
  }
  
  @SubscribeEvent
  public void onBlockPlace(BlockEvent.PlaceEvent event) {
    EntityPlayer player = event.player;
    if ((player.func_130014_f_()).field_72995_K || event.isCanceled() || event.block == null || new ItemStack(event.block) == null)
      return; 
    Block block = event.block;
    JobsPlayer data = JobsPlayer.get((Entity)player);
    if (getJobsManager().canUseUseItem(player, data, new ItemStack(event.block)) != null) {
      PlayerUtils.sendMessage(player, new String[] { "§7[§aJobs§7]§r §cVous n'avez pas le niveau requis pour utiliser ceci." });
      event.setCanceled(true);
      return;
    } 
    boolean include = true;
    if (block instanceof IPlantable)
      include = false; 
    if (block instanceof net.minecraft.block.IGrowable)
      include = false; 
    if (block.equals(Blocks.field_150440_ba))
      include = true; 
    if (include)
      PlacedBlockData.setPlaced(event.world, event.x, event.y, event.z); 
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onBlockBreak(BlockEvent.BreakEvent event) {
    EntityPlayer player = event.getPlayer();
    if ((player.func_130014_f_()).field_72995_K || event.isCanceled() || event.getResult() == Event.Result.DENY)
      return; 
    World world = event.world;
    Block block = event.block;
    if (EnchantmentUtils.containsEnchantment(player.func_71045_bC(), Enchantment.field_77348_q))
      return; 
    Location location = new Location(player.field_70170_p, event.x, event.y, event.z);
    ItemStack stack = new ItemStack(event.block);
    int blockdData = world.func_72805_g(event.x, event.y, event.z);
    boolean include = true;
    if (block instanceof IPlantable) {
      IPlantable plantable = (IPlantable)block;
      blockdData = plantable.getPlantMetadata((IBlockAccess)world, event.x, event.y, event.z);
      include = false;
      if ((block == Blocks.field_150459_bM || block == Blocks.field_150469_bN || block == Blocks.field_150464_aj) && 
        blockdData < 7)
        return; 
    } 
    if (block instanceof net.minecraft.block.IGrowable) {
      include = false;
      if ((block.func_149739_a().contains("orangeblue") || block.func_149739_a().contains("kiwano")) && 
        blockdData < 9)
        return; 
      if ((block.func_149739_a().contains("chervil") || block.func_149739_a().contains("eggplant")) && 
        blockdData < 7)
        return; 
    } 
    if (block == Blocks.field_150464_aj)
      stack = new ItemStack(Items.field_151014_N, 1, event.blockMetadata); 
    if (block == Blocks.field_150439_ay)
      stack = new ItemStack(Blocks.field_150450_ax); 
    if (player.field_70154_o == null) {
      BreakBlockObjective.performCheck(player, location, stack, include);
      BlockBreakQuest.performCheck(player, location, stack, include);
      BreakBlockRequirement.BreakBlockRequirementData data = new BreakBlockRequirement.BreakBlockRequirementData(stack, location, include);
      JobsPlayer.get((Entity)player).getRequirements(BreakBlockRequirement.class).forEach(optional -> optional.ifPresent(()));
    } 
  }
  
  @SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    Entity entity = event.entity;
    if (entity.field_70170_p.field_72995_K || event.isCanceled())
      return; 
    Optional<IWither> optionalWither = WitherUtils.getWither(entity);
    if (!optionalWither.isPresent())
      return; 
    IWither wither = optionalWither.get();
    World world = entity.field_70170_p;
    EntityPlayer player = world.func_72890_a(entity, 10.0D);
    if (player == null)
      return; 
    WitherSpawnObjective.performCheck(player);
    MinecraftForge.EVENT_BUS.post((Event)PlayerSummonWitherEvent.of((EntityPlayerMP)player, wither, new DoubleLocation((Entity)player)));
  }
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    if (!e.player.field_70170_p.field_72995_K && e.player instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)e.player;
      JobsManager.getInstance().checkDailyQuests(player);
      JobsPlayer jobPlayer = JobsPlayer.get((Entity)e.player);
      if (jobPlayer != null)
        MultiplierUtils.getMultiplierForPlayer((EntityPlayer)player, jobPlayer); 
    } 
  }
  
  public JobsManager getJobsManager() {
    if (this.jobsManager == null)
      this.jobsManager = JobsManager.getInstance(); 
    return this.jobsManager;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\listener\EventsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */