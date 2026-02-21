package fr.paladium.palamod.modules.miner.dimminer.server.listener;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palajobs.core.utils.PlacedBlockData;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.manager.DimMinerManager;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerOverlay;
import fr.paladium.palamod.modules.miner.dimminer.common.network.SCPacketDimMinerStats;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ServerDimMinerEventHandler {
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onTrack(PlayerEvent.StartTracking e) {
    if (!PMiner.proxy.isMinerDimension() || !(e.target instanceof EntityPlayerMP) || PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)e.entityPlayer), "paladium.dimminer.bypass"))
      return; 
    try {
      World world = e.target.field_70170_p;
      EntityTracker tracker = ((WorldServer)world).func_73039_n();
      ConcurrentHashMap<Integer, Object> trackedEntityIDs = (ConcurrentHashMap<Integer, Object>)ObfuscationReflectionHelper.getPrivateValue(EntityTracker.class, tracker, new String[] { "trackedEntityIDs", "field_72794_c" });
      EntityTrackerEntry entry = (EntityTrackerEntry)trackedEntityIDs.get(Integer.valueOf(e.target.func_145782_y()));
      if (entry != null)
        entry.func_73123_c((EntityPlayerMP)e.entityPlayer); 
    } catch (Exception|NoClassDefFoundError ex) {
      ex.printStackTrace();
    } 
  }
  
  @SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent e) {
    if (!PMiner.proxy.isMinerDimension() || !(e.entity instanceof net.minecraft.entity.monster.EntityMob))
      return; 
    e.setCanceled(true);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    DimMinerPlayer data = DimMinerPlayer.get((Entity)player);
    if (data == null)
      return; 
    if (ForgeEnv.isIDE() && PMiner.proxy.isMinerDimension())
      PMiner.network.sendTo((IMessage)new SCPacketDimMinerStats(), player); 
    if (data.isExpired() && data.getPortalData() != null && data.getPortalData().isSameServer()) {
      PMiner.network.sendTo((IMessage)new SCPacketDimMinerStats(), player);
      World world = player.field_70170_p;
      for (DoubleLocation location : data.getPortalData().getBlocks()) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        if (world.func_147439_a(x, y, z) == BlocksRegister.WITHERED_OBSIDIAN && world.field_73012_v.nextInt(3) == 0)
          world.func_147468_f(x, y, z); 
      } 
      data.reset();
      data.sync();
    } 
    if (!PMiner.proxy.isMinerDimension())
      return; 
    if (ForgeEnv.isIDE() && !data.isActive())
      data.create(); 
    boolean bypass = PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "paladium.dimminer.bypass");
    if (!data.isActive() && !bypass) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous n'êtes pas autorisé à vous connecter dans cette dimension."));
      player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cVous n'êtes pas autorisé à vous connecter dans cette dimension.");
      return;
    } 
    if (data.isActive()) {
      DimMinerManager.DimMinerSection section = DimMinerManager.generateNextSection();
      DoubleLocation teleport = section.findSafeTeleportLocation(player.field_70170_p);
      int tryCount = 0;
      while (teleport == null) {
        if (tryCount > 10) {
          player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cErreur lors de la téléportation, veuillez réessayer."));
          player.field_71135_a.func_147360_c("§8[§6Paladium§8] §cErreur lors de la téléportation, veuillez réessayer.");
          return;
        } 
        section = DimMinerManager.generateNextSection();
        teleport = section.findSafeTeleportLocation(player.field_70170_p);
        tryCount++;
      } 
      data.setSection(section);
      int portalX = teleport.getBlockX();
      int portalY = teleport.getBlockY() - 1;
      int portalZ = teleport.getBlockZ();
      int ox;
      for (ox = -3; ox <= 2; ox++) {
        for (int oz = -2; oz <= 2; oz++)
          player.field_70170_p.func_147449_b(portalX + ox, portalY, portalZ + oz, BlocksRegister.WITHERED_OBSIDIAN); 
      } 
      for (ox = -1; ox <= 1; ox++)
        player.field_70170_p.func_147449_b(portalX + ox, portalY + 1, portalZ + 2, BlocksRegister.WITHERED_OBSIDIAN); 
      for (ox = -1; ox <= 1; ox++)
        player.field_70170_p.func_147449_b(portalX + ox, portalY + 5, portalZ + 2, BlocksRegister.WITHERED_OBSIDIAN); 
      int oy;
      for (oy = 0; oy <= 5; oy++)
        player.field_70170_p.func_147449_b(portalX - 2, portalY + oy, portalZ + 2, BlocksRegister.WITHERED_OBSIDIAN); 
      for (oy = 0; oy <= 5; oy++)
        player.field_70170_p.func_147449_b(portalX + 1, portalY + oy, portalZ + 2, BlocksRegister.WITHERED_OBSIDIAN); 
      player.field_70170_p.func_147449_b(portalX, portalY + 2, portalZ + 2, (Block)Blocks.field_150480_ab);
      teleport.teleportServer((EntityPlayer)player);
    } 
    PMiner.network.sendTo((IMessage)new SCPacketDimMinerOverlay(), player);
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onPlace(BlockEvent.PlaceEvent event) {
    if (!PMiner.proxy.isMinerDimension() || event.player.field_71075_bZ.field_75098_d)
      return; 
    DimMinerPlayer data = DimMinerPlayer.get((Entity)event.player);
    if (!data.isActive()) {
      event.setCanceled(true);
      event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa dimension est entrain de se fermer."));
      return;
    } 
    if (event.block.hasTileEntity(event.blockMetadata) && event.world.func_147438_o(event.x, event.y, event.z) instanceof net.minecraft.inventory.IInventory) {
      event.setCanceled(true);
      event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas poser de bloc contenant des items dans cette dimension."));
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onBreak(BlockEvent.BreakEvent event) {
    if (!PMiner.proxy.isMinerDimension() || (event.getPlayer()).field_71075_bZ.field_75098_d)
      return; 
    DimMinerPlayer data = DimMinerPlayer.get((Entity)event.getPlayer());
    if (!data.isActive()) {
      event.setCanceled(true);
      event.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa dimension est entrain de se fermer."));
      return;
    } 
    long points = DimMinerPlayer.getPoints(event.block);
    if (points > 0L && data.getPoints() < points) {
      event.setCanceled(true);
      event.getPlayer().func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous n'avez pas assez de points pour casser ce bloc."));
      return;
    } 
    if (event.getResult() != Event.Result.DENY && !event.isCanceled() && !PlacedBlockData.isPlaced(event.world, event.x, event.y, event.z))
      data.breakBlock(event.block); 
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    DimMinerPlayer data = DimMinerPlayer.get((Entity)event.player);
    if (data != null)
      data.onTick(); 
    boolean isMinerDimension = PMiner.proxy.isMinerDimension();
    if (!isMinerDimension)
      return; 
    boolean active = (data != null && data.isActive());
    if (active && event.player.field_70173_aa % 1200 == 0)
      data.incrementTimePlayed(); 
    boolean bypass = PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)event.player), "paladium.dimminer.bypass");
    if (active && !ForgeEnv.isIDE() && !bypass) {
      DimMinerManager.DimMinerSection section = data.getSection();
      if (section != null) {
        double posX = event.player.field_70165_t;
        double posY = event.player.field_70163_u;
        double posZ = event.player.field_70161_v;
        float yaw = event.player.field_70177_z;
        float pitch = event.player.field_70125_A;
        if (posX < section.getMinX()) {
          TeleportUtils.teleport(event.player, (section.getMinX() + 1), posY, posZ, yaw, pitch);
          event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous avez atteint la limite de la dimension."));
        } else if (posX > section.getMaxX()) {
          TeleportUtils.teleport(event.player, (section.getMaxX() - 1), posY, posZ, yaw, pitch);
          event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous avez atteint la limite de la dimension."));
        } else if (posZ < section.getMinZ()) {
          TeleportUtils.teleport(event.player, posX, posY, (section.getMinZ() + 1), yaw, pitch);
          event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous avez atteint la limite de la dimension."));
        } else if (posZ > section.getMaxZ()) {
          TeleportUtils.teleport(event.player, posX, posY, (section.getMaxZ() - 1), yaw, pitch);
          event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous avez atteint la limite de la dimension."));
        } 
      } 
    } 
    if (!active && !data.isExpired() && !bypass) {
      data.setExpired(true);
      event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVotre dimension mineur va s'effondrer dans 30 secondes."));
      (new Thread(() -> {
            try {
              Thread.sleep(30000L);
              FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
            } catch (Exception exception) {}
          }"DimMiner/Close-" + UUIDUtils.toString((Entity)event.player))).start();
    } 
  }
  
  @SubscribeEvent
  public void onFall(LivingFallEvent event) {
    if (!PMiner.proxy.isMinerDimension())
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onFallDamage(LivingHurtEvent event) {
    if (!PMiner.proxy.isMinerDimension() || event.source != DamageSource.field_76379_h)
      return; 
    event.ammount = 0.0F;
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\server\listener\ServerDimMinerEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */