package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.client.ui.popup.UIDungeonWarningPopup;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.stat.server.manager.StatsManager;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import fr.paladium.zephyrui.lib.utils.pair.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class DungeonServerPlayerListener {
  private final Set<String> startingHubPlayers = new HashSet<>();
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    OfflinePlayer offlinePlayer = OfflinePlayer.of((EntityPlayer)player);
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
    if (data == null) {
      FMLServerScheduler.getInstance().add(new Runnable[] { () -> player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cUne erreur est survenue lors du chargement de vos données.") });
      return;
    } 
    Consumer<DungeonWorld> task = world -> {
        data.setJoiningDungeon(null);
        if (ForgeEnv.isIDE() && !world.isMember(offlinePlayer.getUuidString()))
          world.addPlayer(offlinePlayer); 
        if (world.getState() != DungeonWorld.DungeonState.WAITING) {
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
          return;
        } 
        world.teleport(player).updateMaxLevels().sync(player);
        if (data != null && data.getBackpackSize() == 0)
          ZUIServer.open(UIDungeonWarningPopup.class, player, new Object[] { "vous n'avez pas de sac à dos !", "Vous ne possédez pas de §f§lsac à dos de donjon §rdans votre inventaire, vous ne pourrez pas récupérer les récompenses dans les coffres.", Boolean.FALSE }); 
      };
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalWorld.isPresent() || ((DungeonWorld)optionalWorld.get()).getLeader() == null || (!data.isJoiningDungeon(((DungeonWorld)optionalWorld.get()).getUniqueId()) && !ForgeEnv.isIDE())) {
      try {
        DungeonWorld.create().thenAccept(dungeonWorld -> FMLServerScheduler.getInstance().add(new Runnable[] { () })).exceptionally(ex -> {
              throw new RuntimeException(ex);
            });
      } catch (Exception e) {
        e.printStackTrace();
        FMLServerScheduler.getInstance().add(new Runnable[] { () -> player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cUne erreur est survenue lors de la création de votre serveur donjon.") });
        return;
      } 
    } else {
      task.accept(optionalWorld.get());
    } 
  }
  
  @SubscribeEvent
  public void onLeave(PlayerEvent.PlayerLoggedOutEvent event) {
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get(event.player.field_70170_p);
    if (!optionalWorld.isPresent())
      return; 
    ((DungeonWorld)optionalWorld.get()).removePlayer(UUIDUtils.toString((Entity)event.player), false);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onFallAttack(LivingAttackEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld() || event.source != DamageSource.field_76379_h)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onFallDamage(LivingHurtEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld() || event.source != DamageSource.field_76379_h)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onAttack(LivingAttackEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    if (event.source.func_76364_f() instanceof EntityPlayer) {
      event.setCanceled(true);
      return;
    } 
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get(event.entityLiving.field_70170_p);
    if (!optionalWorld.isPresent() || ((DungeonWorld)optionalWorld.get()).getState() == DungeonWorld.DungeonState.STARTED)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDamage(LivingHurtEvent event) {
    if (!(event.entityLiving instanceof EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    if (event.source.func_76364_f() instanceof EntityPlayer) {
      event.setCanceled(true);
      return;
    } 
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get(event.entityLiving.field_70170_p);
    if (!optionalWorld.isPresent() || ((DungeonWorld)optionalWorld.get()).getState() == DungeonWorld.DungeonState.STARTED)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onFood(LivingEvent.LivingUpdateEvent event) {
    if (!(event.entityLiving instanceof EntityPlayer) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayer player = (EntityPlayer)event.entityLiving;
    player.func_71024_bL().func_75122_a(20, 5.0F);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entity instanceof EntityPlayerMP) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalWorld.isPresent())
      return; 
    event.setCanceled(true);
    StatsManager.MAX_HEALTH.applyMaxHealth((EntityLivingBase)player, true);
    DungeonWorld world = optionalWorld.get();
    if (world.getState() != DungeonWorld.DungeonState.STARTED) {
      world.teleport(player).setState(DungeonWorld.DungeonState.WAITING);
      return;
    } 
    world.getPlayer(UUIDUtils.toString((Entity)player)).ifPresent(DungeonPlayer::kill);
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    List<DungeonWorld> toRemove = new ArrayList<>();
    for (DungeonWorld world : DungeonWorld.getAll()) {
      if (world.getOnlinePlayers().isEmpty()) {
        toRemove.add(world);
        continue;
      } 
      if (world.getState() != DungeonWorld.DungeonState.WAITING)
        continue; 
      Optional<EntityPlayer> optionalLeader = world.getLeader().getOnlinePlayer();
      if (!optionalLeader.isPresent())
        continue; 
      EntityPlayerMP player = (EntityPlayerMP)optionalLeader.get();
      String uuid = UUIDUtils.toString((Entity)player);
      Chunk chunk = world.getWorld().func_72938_d(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70161_v));
      boolean foundStart = false;
      for (Object tileEntityObj : chunk.field_150816_i.values()) {
        if (!(tileEntityObj instanceof TileEntityDungeonRoom))
          continue; 
        TileEntityDungeonRoom tileEntity = (TileEntityDungeonRoom)tileEntityObj;
        if (tileEntity.func_145838_q() != BlocksRegister.DUNGEON_ROOM_START_TRIGGER)
          continue; 
        Pair<Integer> application = tileEntity.getApplicationY();
        int tileX = tileEntity.field_145851_c;
        int tileMinY = ((Integer)application.getFirst()).intValue();
        int tileMaxY = ((Integer)application.getSecond()).intValue();
        int tileZ = tileEntity.field_145849_e;
        int playerX = MathHelper.func_76128_c(player.field_70165_t);
        int playerY = MathHelper.func_76128_c(player.field_70163_u);
        int playerZ = MathHelper.func_76128_c(player.field_70161_v);
        if (playerX == tileX && playerY >= tileMinY && playerY <= tileMaxY && playerZ == tileZ) {
          if (!this.startingHubPlayers.contains(uuid)) {
            this.startingHubPlayers.add(uuid);
            if (world.getDungeon() == null) {
              (new SCPacketRPGPlaySound("sounds/dungeon/error.ogg")).send(player);
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §6✘ §6§lVous §6§lne §6§lpouvez §6§lpas §6§ldémarrer."));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §cVous §cdevez §cdéfinir §cvotre §cdonjon §cavant §cde §cpouvoir §cy §caccéder."));
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              player.func_145747_a((IChatComponent)new ChatComponentText(""));
            } else {
              try {
                world.start();
              } catch (Exception e) {
                player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue, §c" + e.getMessage().toLowerCase()));
                e.printStackTrace();
                continue;
              } 
            } 
          } 
          foundStart = true;
          break;
        } 
      } 
      if (!foundStart)
        this.startingHubPlayers.remove(uuid); 
    } 
    toRemove.forEach(DungeonWorld::remove);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerPlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */