package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palaschematic.PalaSchematic;
import fr.paladium.palaschematic.utils.Schematic;
import java.io.File;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class AscenseurEmotionnel extends ALuckyEvent {
  public static final String SCHEMATIC_PATH = "Ascenseur.schematic";
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final int posX = (int)player.field_70165_t;
    final int posZ = (int)player.field_70161_v;
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              File schematicFile = new File(PalaMod.conf, "Ascenseur.schematic");
              Schematic schematic = PalaSchematic.get().getSchematicManager().load(schematicFile);
              PalaSchematic.get().getSchematicManager().paste(schematic, new Location(Bukkit.getWorlds().get(0), posX, 200.0D, posZ));
              EventUtils.spawnStructure(player.field_70170_p, posX, 200, posZ, 1, 1, 1, Blocks.field_150348_b, player);
              EventUtils.teleportPlayer(player, posX, 201.0D, posZ);
              PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("elevator_music"), player);
              Thread.sleep(10645L);
              String playerId = FastUUID.toString((Entity)player);
              UsersManager.getCancelFallDamagePlayer().add(playerId);
              EventUtils.spawnStructure(player.field_70170_p, posX, 200, posZ, 1, 1, 1, Blocks.field_150350_a, player);
              Thread.sleep(30000L);
              UsersManager.getCancelFallDamagePlayer().remove(playerId);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public String getName() {
    return "Ascenseur émotionnel";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "june/ascenseur_emotionnel";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\AscenseurEmotionnel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */