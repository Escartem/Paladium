package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportYOne extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!player.field_70170_p.field_72995_K) {
      for (int i = 6; i < 8; i++) {
        if (!player.field_70170_p.func_147437_c((int)player.field_70165_t, i, (int)player.field_70161_v) && !player.field_70170_p.field_72995_K && 
          
          EventUtils.canInteract((EntityPlayer)player, (int)player.field_70165_t, i, (int)player.field_70161_v)) {
          Block block = player.field_70170_p.func_147439_a(x, i, z);
          PlayerUtils.dropItemStack((Entity)player, player.field_70165_t, i, player.field_70161_v, new ItemStack(block));
          player.field_70170_p.func_147468_f((int)player.field_70165_t, i, (int)player.field_70161_v);
        } 
      } 
      if (player.field_70170_p.func_147437_c((int)player.field_70165_t, 7, (int)player.field_70161_v))
        try {
          Player pl = Bukkit.getPlayer(player.func_110124_au());
          if (pl == null || !pl.isOnline())
            return; 
          World bukkitWorld = pl.getWorld();
          Location spawnLocation = new Location(bukkitWorld, (int)player.field_70165_t, 7.0D, (int)player.field_70161_v);
          pl.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } catch (Exception e) {
          player.func_70634_a((int)player.field_70165_t, 7.0D, (int)player.field_70161_v);
        }  
    } 
  }
  
  public String getName() {
    return "20 000 lieu sous terre";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "teleportyone";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\TeleportYOne.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */