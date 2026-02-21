package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BaseDeco extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random r = player.field_70170_p.field_73012_v;
    int randomX = r.nextInt(30000) - 15000;
    int randomZ = r.nextInt(30000) - 15000;
    int randomY = player.field_70170_p.func_72976_f(randomX, randomZ) + 13;
    try {
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      if (pl != null && pl.isOnline()) {
        World bukkitWorld = pl.getWorld();
        Location location = new Location(bukkitWorld, randomX, randomY, randomZ);
        pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
      } 
    } catch (Exception e) {
      player.func_70634_a(randomX, randomY, randomZ);
    } 
    EventUtils.spawnStructure(player.field_70170_p, randomX, randomY, randomZ, 18, 18, 18, Blocks.field_150343_Z, player);
    EventUtils.spawnStructure(player.field_70170_p, randomX, randomY, randomZ, 15, 15, 15, Blocks.field_150350_a, player);
  }
  
  public String getName() {
    return "Base déco";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "base_deco";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\BaseDeco.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */