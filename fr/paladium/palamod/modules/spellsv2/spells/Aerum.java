package fr.paladium.palamod.modules.spellsv2.spells;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.utils.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Aerum implements Spell {
  public void perform(EntityPlayerMP player, int tier) {
    int y = (int)player.field_70163_u;
    for (int i = 250; i > player.field_70163_u; i--) {
      if (player.field_70170_p.func_147439_a((int)player.field_70165_t, i, (int)player.field_70161_v) != Blocks.field_150350_a) {
        y = i;
        break;
      } 
    } 
    try {
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      if (pl != null && pl.isOnline()) {
        World bukkitWorld = pl.getWorld();
        Location location = new Location(bukkitWorld, (int)player.field_70165_t, (y + 4), (int)player.field_70161_v);
        pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
      } 
    } catch (Exception e) {
      player.func_70634_a((int)player.field_70165_t, (y + 4), (int)player.field_70161_v);
    } 
    EventUtils.spawnParticle(player.field_70170_p, "portal", (int)player.field_70165_t, (y + 4), (int)player.field_70161_v, 100, 0.10000000149011612D);
    EventUtils.spawnParticle(player.field_70170_p, "portal", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 100, 0.10000000149011612D);
    player.field_70170_p.func_72956_a((Entity)player, "mob.endermen.idle", 1.0F, 1.0F);
  }
  
  public int getId() {
    return 0;
  }
  
  public String getName() {
    return "Aerum";
  }
  
  public int getMaxTiers() {
    return 1;
  }
  
  public int getCost() {
    return 3;
  }
  
  public int getCooldown() {
    return 10;
  }
  
  public int getLevel() {
    return 50;
  }
  
  public String[] getDescription() {
    return new String[] { "Permet de se téléporter à la couche la plus haute au dessus de soit (un peu comme un /top)." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\spells\Aerum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */