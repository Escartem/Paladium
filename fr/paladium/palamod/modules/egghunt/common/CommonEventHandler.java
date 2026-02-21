package fr.paladium.palamod.modules.egghunt.common;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import retrofit2.Response;

public class CommonEventHandler {
  public static BlockPos lastValidPosition;
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    ModuleEggHunt.getInstance().setActive(false);
    ModuleEggHunt.getInstance().setCanXp(false);
  }
  
  @SubscribeEvent
  public void onLivingDeath(LivingDeathEvent e) {
    if (e.entityLiving.field_70170_p.field_72995_K || PEggHunt.status == null || PEggHunt.status.getEggOwner() == null || !(e.entityLiving instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entityLiving;
    if (!PEggHunt.data.isActive() || !PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_()))
      return; 
    EntityPlayer tmpKiller = null;
    if (e.source.func_76364_f() instanceof EntityPlayer) {
      tmpKiller = (EntityPlayer)e.source.func_76364_f();
    } else if (e.source.func_76364_f() instanceof EntityThrowable && ((EntityThrowable)e.source.func_76364_f()).func_85052_h() instanceof EntityPlayer) {
      tmpKiller = (EntityPlayer)((EntityThrowable)e.source.func_76364_f()).func_85052_h();
    } 
    EntityPlayer killer = tmpKiller;
    if (killer != null) {
      player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous venez de vous faire voler l'oeuf de dragon par §e" + killer.func_70005_c_()));
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(killer);
              PEggHunt.setOwner(playerInput, false, ());
            } catch (IOException e1) {
              killer.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cL'oeuf que transportait ce joueur semble venir d'une autre contrée, vous ne pouvez donc rien en faire."));
              e1.printStackTrace();
            } 
          }0L);
      return;
    } 
    PEggHunt.dropEgg(player, false);
  }
  
  @SubscribeEvent
  public void onLivingHurt(LivingHurtEvent e) {
    if (e.entityLiving.field_70170_p.field_72995_K || PEggHunt.status == null || PEggHunt.status.getEggOwner() == null || !(e.entityLiving instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entityLiving;
    if (player == null || !PEggHunt.data.isActive() || !PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_()))
      return; 
    EntityPlayer tmpDamager = null;
    if (e.source.func_76364_f() instanceof EntityPlayer) {
      tmpDamager = (EntityPlayer)e.source.func_76364_f();
    } else if (e.source.func_76364_f() instanceof EntityThrowable && ((EntityThrowable)e.source.func_76364_f()).func_85052_h() instanceof EntityPlayer) {
      tmpDamager = (EntityPlayer)((EntityThrowable)e.source.func_76364_f()).func_85052_h();
    } 
    EntityPlayer damager = tmpDamager;
    if (damager != null && !damager.func_110124_au().equals(player.func_110124_au())) {
      player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous venez de vous faire voler l'oeuf de dragon par §e" + damager.func_70005_c_()));
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(damager);
              PEggHunt.setOwner(playerInput, false, ());
            } catch (IOException e1) {
              damager.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cL'oeuf que transportait ce joueur semble venir d'une autre contrée, vous ne pouvez donc rien en faire."));
              e1.printStackTrace();
            } 
          }0L);
    } 
  }
  
  @SubscribeEvent
  public void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent e) {
    if (e.player.field_70170_p.field_72995_K || PEggHunt.status == null || PEggHunt.status.getEggOwner() == null)
      return; 
    if (!PEggHunt.data.isActive() || !PEggHunt.status.getEggOwner().equalsIgnoreCase(e.player.func_70005_c_()))
      return; 
    PEggHunt.dropEgg(e.player, false);
  }
  
  @SubscribeEvent
  public void onPlayerMove(TickEvent.PlayerTickEvent e) {
    if (e.player.field_70170_p.field_72995_K || PEggHunt.status == null || PEggHunt.status.getEggOwner() == null || e.phase != TickEvent.Phase.END)
      return; 
    if (!PEggHunt.data.isActive() || !PEggHunt.status.getEggOwner().equalsIgnoreCase(e.player.func_70005_c_()))
      return; 
    World world = e.player.field_70170_p;
    BlockPos pos = new BlockPos((Entity)e.player);
    if (lastValidPosition != null && pos.distance(lastValidPosition) > 100) {
      try {
        Player player = Bukkit.getPlayer(e.player.func_110124_au());
        if (player != null && player.isOnline()) {
          World bukkitWorld = player.getWorld();
          Location location = new Location(bukkitWorld, lastValidPosition.getX(), lastValidPosition.getY(), lastValidPosition.getZ());
          player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } 
      } catch (Exception e1) {
        e.player.func_70634_a(lastValidPosition.getX(), lastValidPosition.getY(), lastValidPosition.getZ());
      } 
      e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous ne pouvez pas vous téléporter avec cet oeuf."));
    } 
    if (!PEggHunt.isValidLocation(world, pos, e.player, true)) {
      if (lastValidPosition == null || !PEggHunt.isValidLocation(world, lastValidPosition, e.player, true)) {
        PEggHunt.dropEgg(e.player, true);
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous ne pouvez pas"));
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§8[§c/§8] Être à plus de 5000 blocs du spawn"));
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§8[§c/§8] Être dans une zone protégée"));
      } else {
        try {
          Player player = Bukkit.getPlayer(e.player.func_110124_au());
          if (player != null && player.isOnline()) {
            World bukkitWorld = player.getWorld();
            Location location = new Location(bukkitWorld, lastValidPosition.getX(), lastValidPosition.getY(), lastValidPosition.getZ());
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
          } 
        } catch (Exception e1) {
          e.player.func_70634_a(lastValidPosition.getX(), lastValidPosition.getY(), lastValidPosition.getZ());
        } 
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cVous ne pouvez pas"));
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§8[§c/§8] Être à plus de 5000 blocs du spawn"));
        e.player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§8[§c/§8] Être dans une zone protégée"));
      } 
      return;
    } 
    lastValidPosition = pos;
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onCommand(CommandEvent e) {
    if (!(e.sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.sender;
    if (player.field_70170_p.field_72995_K || PEggHunt.status == null || PEggHunt.status.getEggOwner() == null)
      return; 
    if (!PEggHunt.data.isActive() || !PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_()))
      return; 
    if (e.command.func_71517_b().contains("home") || e.command.func_71517_b().contains("tpa") || e.command.func_71517_b().contains("tpaccept") || e.command.func_71517_b().contains("spawn"))
      e.setCanceled(true); 
  }
  
  public static String _a = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = -1663298696;
        buf[0] = (byte)(this.t >>> 7);
        this.t = 302658141;
        buf[1] = (byte)(this.t >>> 11);
        this.t = 1059555940;
        buf[2] = (byte)(this.t >>> 6);
        this.t = -1951380898;
        buf[3] = (byte)(this.t >>> 8);
        this.t = -1277558441;
        buf[4] = (byte)(this.t >>> 11);
        this.t = -1308280333;
        buf[5] = (byte)(this.t >>> 10);
        this.t = 1671849670;
        buf[6] = (byte)(this.t >>> 24);
        this.t = 1296593109;
        buf[7] = (byte)(this.t >>> 18);
        this.t = 859527595;
        buf[8] = (byte)(this.t >>> 23);
        this.t = 557831463;
        buf[9] = (byte)(this.t >>> 6);
        this.t = -1502978651;
        buf[10] = (byte)(this.t >>> 16);
        this.t = 523342523;
        buf[11] = (byte)(this.t >>> 3);
        this.t = -1133585188;
        buf[12] = (byte)(this.t >>> 9);
        this.t = -1787450726;
        buf[13] = (byte)(this.t >>> 12);
        this.t = -1916223384;
        buf[14] = (byte)(this.t >>> 18);
        this.t = -2038346876;
        buf[15] = (byte)(this.t >>> 21);
        return new String(buf);
      }
    }).toString();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\CommonEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */