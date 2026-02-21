package fr.paladium.palamod.modules.luckyblock.monthly.tasks;

import fr.paladium.palamod.modules.luckyblock.monthly.managers.SchematicManager;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import java.util.TimerTask;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class SchematicRunnable extends TimerTask {
  private SchematicManager manager;
  
  public SchematicRunnable(SchematicManager manager) {
    this.manager = manager;
  }
  
  public void run() {
    try {
      World world = MinecraftServer.func_71276_C().func_130014_f_();
      long now = System.currentTimeMillis();
      if (world == null)
        return; 
      this.manager.getStructures().forEach(structure -> {
            if (structure.isExpired(now))
              structure.remove(world); 
          });
      this.manager.getStructures().removeIf(structure -> structure.isExpired(now));
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tasks\SchematicRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */