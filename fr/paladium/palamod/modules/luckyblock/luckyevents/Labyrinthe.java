package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Labyrinthe extends ALuckyEvent {
  public void perform(final EntityPlayerMP player, final int x, int y, final int z) {
    if (!ServerManager.getLabyrinthe().contains(player))
      ServerManager.getLabyrinthe().add(player); 
    y = 100;
    final List<int[]> visited = (List)new ArrayList<>();
    final int min = -8;
    final int max = 8;
    (new Thread(new Runnable() {
          public void run() {
            EventUtils.spawnStructure(player.field_70170_p, x, 102, z, (max - min) * 2, 1, (max - min) * 2, Blocks.field_150343_Z, player);
            for (int i = min; i < max; i++) {
              for (int j = min; j < max; j++) {
                EventUtils.spawnStructure(player.field_70170_p, x + i * 2 + 1, 100, z + j * 2 + 1, 3, 3, 3, Blocks.field_150343_Z, player);
                player.field_70170_p.func_147449_b(x + i * 2 + 1, 100, z + j * 2 + 1, Blocks.field_150350_a);
                player.field_70170_p.func_147449_b(x + i * 2 + 1, 101, z + j * 2 + 1, Blocks.field_150350_a);
              } 
            } 
            visited.add(new int[] { 0, 0 });
            int[] last = { 0, 0 };
            int iteration = 0;
            while (visited.size() < (max - min) * (max - min) && iteration < 10000) {
              List<int[]> neighbors = Labyrinthe.this.getNeighbor(last, min, max);
              int[] neighbor = neighbors.get(player.field_70170_p.field_73012_v.nextInt(neighbors.size()));
              if (!Labyrinthe.this.isVisited(neighbor[0], neighbor[1], visited)) {
                visited.add(neighbor);
                if (neighbor[0] > last[0]) {
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1 - 1, 100, z + neighbor[1] * 2 + 1, Blocks.field_150350_a);
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1 - 1, 101, z + neighbor[1] * 2 + 1, Blocks.field_150350_a);
                } else if (neighbor[0] < last[0]) {
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1 + 1, 100, z + neighbor[1] * 2 + 1, Blocks.field_150350_a);
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1 + 1, 101, z + neighbor[1] * 2 + 1, Blocks.field_150350_a);
                } else if (neighbor[1] > last[1]) {
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1, 100, z + neighbor[1] * 2 + 1 - 1, Blocks.field_150350_a);
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1, 101, z + neighbor[1] * 2 + 1 - 1, Blocks.field_150350_a);
                } else if (neighbor[1] < last[1]) {
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1, 100, z + neighbor[1] * 2 + 1 + 1, Blocks.field_150350_a);
                  player.field_70170_p.func_147449_b(x + neighbor[0] * 2 + 1, 101, z + neighbor[1] * 2 + 1 + 1, Blocks.field_150350_a);
                } 
              } 
              last = neighbor;
              iteration++;
            } 
            try {
              Player pl = Bukkit.getPlayer(player.func_110124_au());
              if (pl != null && pl.isOnline()) {
                World bukkitWorld = pl.getWorld();
                Location location = new Location(bukkitWorld, x, 101.0D, z);
                pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
              } 
            } catch (Exception e) {
              player.func_70634_a(x, 101.0D, z);
            } 
            player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 999999999, 9));
          }
        })).start();
  }
  
  private List<int[]> getNeighbor(int[] pos, int min, int max) {
    List<int[]> neighbors = (List)new ArrayList<>();
    if (pos[0] + 1 < max)
      neighbors.add(new int[] { pos[0] + 1, pos[1] }); 
    if (pos[1] + 1 < max)
      neighbors.add(new int[] { pos[0], pos[1] + 1 }); 
    if (pos[0] - 1 >= min)
      neighbors.add(new int[] { pos[0] - 1, pos[1] }); 
    if (pos[1] - 1 >= min)
      neighbors.add(new int[] { pos[0], pos[1] - 1 }); 
    return neighbors;
  }
  
  private boolean isVisited(int x, int z, List<int[]> datas) {
    boolean visited = false;
    for (int[] data : datas) {
      if (data[0] == x && data[1] == z) {
        visited = true;
        break;
      } 
    } 
    return visited;
  }
  
  public String getName() {
    return "Labyrinthe";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Labyrinthe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */