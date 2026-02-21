package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WhoAreYou extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (player.field_70170_p.field_73010_i.size() > 1) {
      List<EntityPlayerMP> playerList = new ArrayList(player.field_70170_p.field_73010_i);
      playerList.remove(player);
      final EntityPlayerMP randomPlayer = playerList.get(player.field_70170_p.field_73012_v.nextInt(playerList.size()));
      PlayerUtils.sendMessage((EntityPlayer)randomPlayer, "§cVous allez être téléporté à §e" + player
          .func_70005_c_() + "§c pendant 30 secondes puis revenir à votre position. Have Fun");
      final Player currentPlayer = Bukkit.getPlayer(player.func_110124_au());
      final Player randomPlayerBukkit = Bukkit.getPlayer(randomPlayer.func_110124_au());
      final LuckyTask task = new LuckyTask();
      task
        
        .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
            public void run() {
              try {
                Thread.sleep(5000L);
                Location randomPlayerLocation = randomPlayerBukkit.getLocation();
                UsersManager.getInvincibles().add(randomPlayer);
                randomPlayerBukkit.teleport(currentPlayer.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                for (int i = 0; i < 30; i++) {
                  PlayerUtils.sendMessage((EntityPlayer)randomPlayer, (30 - i) + " secondes restantes...");
                  Thread.sleep(1000L);
                } 
                randomPlayerBukkit.teleport(randomPlayerLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
              } catch (InterruptedException interruptedException) {}
              UsersManager.getInvincibles().remove(randomPlayer);
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
            }
          }0L).getTaskId();
    } else {
      PlayerUtils.sendMessage((EntityPlayer)player, "Aucun autre joueur en ligne... dommage !");
    } 
  }
  
  public String getName() {
    return "T ki ?";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "t_ki";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WhoAreYou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */