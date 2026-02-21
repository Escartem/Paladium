package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Reflexe extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (player.field_71071_by.func_146028_b((Item)ItemsRegister.HANGGLIDER))
      player.field_71071_by.func_146026_a((Item)ItemsRegister.HANGGLIDER); 
    player.field_71071_by.func_70441_a(new ItemStack(Items.field_151131_as));
    (new Thread(() -> {
          try {
            for (int oy = 0; oy < 2500; oy++) {
              Thread.sleep(2L);
              try {
                Player pl = Bukkit.getPlayer(player.func_110124_au());
                if (pl != null && pl.isOnline()) {
                  World bukkitWorld = pl.getWorld();
                  Location location = new Location(bukkitWorld, x, (y + oy / 5), z);
                  pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
                } 
              } catch (Exception e) {
                player.func_70634_a(x, (y + oy / 5), z);
              } 
              EventUtils.spawnParticle(player.field_70170_p, "fireworksSpark", x, (y + oy / 5), z, 50, 0.0D);
            } 
          } catch (Exception exception) {}
        })).start();
  }
  
  public String getName() {
    return "Reflexe";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Reflexe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */