package fr.paladium.palamod.modules.hunter.events;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

@SideOnly(Side.SERVER)
public class EventsManagerServer {
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerSneak(LivingEvent.LivingUpdateEvent e) {
    if (e.entity instanceof net.minecraft.entity.player.EntityPlayer) {
      EntityLivingBase entity = e.entityLiving;
      World world = entity.field_70170_p;
      BlockPos pos = (new BlockPos((Entity)entity)).down();
      if (entity.func_70093_af() && !world.field_72995_K && world.func_147439_a(pos.getX(), pos.getY(), pos.getZ()) == BlocksRegister.ELEVATOR_BLOCK)
        for (int i = pos.getY() - 1; i >= 0; i--) {
          if (world.func_147439_a(pos.getX(), i, pos.getZ()) == BlocksRegister.ELEVATOR_BLOCK) {
            if (world.func_147439_a(pos.getX(), i + 1, pos.getZ()) != Blocks.field_150350_a || world.func_147439_a(pos.getX(), i + 2, pos.getZ()) != Blocks.field_150350_a)
              return; 
            entity.func_70095_a(false);
            BlockPos ps = new BlockPos(entity.field_70165_t, (i + 1), entity.field_70161_v);
            float yaw = entity.field_70177_z;
            float pitch = entity.field_70125_A;
            try {
              Player pl = Bukkit.getPlayer(entity.func_110124_au());
              if (pl != null && pl.isOnline()) {
                World bukkitWorld = pl.getWorld();
                Location location = new Location(bukkitWorld, ps.getX() + 0.5D, ps.getY(), ps.getZ() + 0.5D, yaw, pitch);
                pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
              } 
            } catch (Exception ex) {
              entity.func_70634_a(entity.field_70165_t, (i + 1), entity.field_70161_v);
            } 
            entity.field_70125_A = pitch;
            entity.field_70177_z = yaw;
            return;
          } 
        }  
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onEnderpearl(PlayerInteractEvent e) {
    if (e.entityPlayer.func_70694_bm() != null && (
      e.entityPlayer.func_70694_bm().func_77973_b() == Items.field_151079_bi || e.entityPlayer.func_70694_bm().func_77973_b() == ItemsRegister.STICK_TELEPORT) && 
      WorldGuardUtils.getStates(e.entityPlayer.field_70170_p, e.entityPlayer.field_70165_t, e.entityPlayer.field_70163_u, e.entityPlayer.field_70161_v, DefaultFlag.BLOCKED_CMDS) != null)
      for (Object f : WorldGuardUtils.getStates(e.entityPlayer.field_70170_p, e.entityPlayer.field_70165_t, e.entityPlayer.field_70163_u, e.entityPlayer.field_70161_v, DefaultFlag.BLOCKED_CMDS)) {
        if ("/enderpearl".equalsIgnoreCase(f.toString())) {
          e.setCanceled(true);
          return;
        } 
      }  
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerJump(LivingEvent.LivingJumpEvent e) {
    EntityLivingBase entity = e.entityLiving;
    World world = entity.field_70170_p;
    BlockPos pos = (new BlockPos((Entity)entity)).down();
    if (!world.field_72995_K && world.func_147439_a(pos.getX(), pos.getY(), pos.getZ()) == BlocksRegister.ELEVATOR_BLOCK)
      for (int i = pos.getY() + 1; i < 256; i++) {
        if (world.func_147439_a(pos.getX(), i, pos.getZ()) == BlocksRegister.ELEVATOR_BLOCK) {
          if (world.func_147439_a(pos.getX(), i + 1, pos.getZ()) != Blocks.field_150350_a || world.func_147439_a(pos.getX(), i + 2, pos.getZ()) != Blocks.field_150350_a)
            return; 
          BlockPos ps = new BlockPos(entity.field_70165_t, (i + 1), entity.field_70161_v);
          float yaw = entity.field_70177_z;
          float pitch = entity.field_70125_A;
          try {
            Player pl = Bukkit.getPlayer(entity.func_110124_au());
            if (pl != null && pl.isOnline()) {
              World bukkitWorld = pl.getWorld();
              Location location = new Location(bukkitWorld, ps.getX() + 0.5D, ps.getY(), ps.getZ() + 0.5D, yaw, pitch);
              pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            } 
          } catch (Exception ex) {
            entity.func_70634_a(ps.getX() + 0.5D, (i + 1), ps.getZ() + 0.5D);
          } 
          entity.field_70125_A = pitch;
          entity.field_70177_z = yaw;
          break;
        } 
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\events\EventsManagerServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */