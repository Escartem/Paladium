package fr.paladium.palamod.modules.miner.command;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.miner.PMiner;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class CommandNether extends CommandBase {
  private static final int NEEDED_MINER_LEVEL = 3;
  
  private final Map<EntityPlayer, Long> cooldown = new HashMap<>();
  
  public String func_71517_b() {
    return "nether";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/nether";
  }
  
  public void func_71515_b(ICommandSender sender, String[] p_71515_2_) {
    if (sender instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)sender;
      if (this.cooldown.containsKey(player)) {
        long cd = ((Long)this.cooldown.get(player)).longValue();
        Duration duration = Duration.between(TimeUtil.nowZoned(), TimeUtil.fromLong(cd).plusMinutes(10L));
        if (duration.getSeconds() <= 0L) {
          this.cooldown.remove(player);
          player.func_145747_a((IChatComponent)new ChatComponentText("§aVous pouvez désormais executer la commande §e/nether"));
          return;
        } 
        player.func_145747_a((IChatComponent)new ChatComponentText("§cVeuillez attendre §e" + ((duration.getSeconds() >= 60L) ? ((int)(duration.getSeconds() / 60L) + " minutes.") : (duration.getSeconds() + " secondes."))));
        return;
      } 
      if (PConfigs.server_minage_enabled && JobsPlayer.get((Entity)player).getLevel(JobType.MINER) >= 3 && player.field_70170_p.field_73011_w.field_76574_g != -1 && !PMiner.proxy.isMinerDimension()) {
        WorldServer nether = null;
        for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
          if (world.field_73011_w.field_76574_g == -1) {
            nether = world;
            break;
          } 
        } 
        if (nether != null) {
          int x = nether.field_73012_v.nextInt(10000) - 5000;
          int z = nether.field_73012_v.nextInt(10000) - 5000;
          player.func_70634_a(x, player.field_70170_p.func_72976_f(x, z), z);
          player.func_71027_c(-1);
          this.cooldown.put(player, Long.valueOf(TimeUtil.now()));
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§cImpossible de charger le Nether."));
        } 
      } else {
        player.func_145747_a((IChatComponent)new ChatComponentText("§cPour effectuer la commande vous devez :"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Être sur un serveur minage §8[" + (PConfigs.server_minage_enabled ? "§a✔" : "§c✘") + "§8]"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Ne pas être dans une dimension mineur §8[" + (!PMiner.proxy.isMinerDimension() ? "§a✔" : "§c✘") + "§8]"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Être au moins au niveau 3 Mineur §8[" + ((JobsPlayer.get((Entity)player).getLevel(JobType.MINER) >= 3) ? "§a✔" : "§c✘") + "§8]"));
        player.func_145747_a((IChatComponent)new ChatComponentText("§8» §7Ne pas être dans le Nether §8[" + ((player.field_70170_p.field_73011_w.field_76574_g != -1) ? "§a✔" : "§c✘") + "§8]"));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\command\CommandNether.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */