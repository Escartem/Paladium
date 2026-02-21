package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.PhantomBoatRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palaschematic.PalaSchematic;
import fr.paladium.palaschematic.utils.BlockData;
import fr.paladium.palaschematic.utils.BlockPos;
import fr.paladium.palaschematic.utils.Schematic;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PhantomBoatEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public static final Map<UUID, PhantomBoatRunnable> RUNNABLE_MAP = new HashMap<>();
  
  private static final String EVENT_NAME = "Bateau fantôme";
  
  private static final String TEXTURE_PATH = "july/phamtom_boat";
  
  private static final int RARITY = 650;
  
  private static final boolean IS_BAD = false;
  
  private static final String SCHEMATIC_PATH = "phantom_boat.schematic";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Schematic schematic = null;
    Location defaultLocation = new Location(player.field_70170_p, x, y, z);
    try {
      File schematicFile = new File(PalaMod.conf, "phantom_boat.schematic");
      schematic = PalaSchematic.get().getSchematicManager().load(schematicFile);
      for (BlockData data : schematic.getBlocks()) {
        BlockPos pos = data.getPos();
        Location blockLocation = defaultLocation.clone(pos.getX(), pos.getY(), pos.getZ());
        if (!EventUtils.canInteract((EntityPlayer)player, blockLocation)) {
          schematic = null;
          return;
        } 
      } 
      PalaSchematic.get().getSchematicManager().paste(schematic, new Location(
            
            Bukkit.getWorlds().get(0), x, y, z));
    } catch (Exception e) {
      e.printStackTrace();
    } 
    startTask((EntityPlayer)player, defaultLocation, schematic);
    MonthlyUtils.startHeliosEvent(getClass(), player);
  }
  
  public static PhantomBoatRunnable getBySkeleton(EntitySkeleton skeleton) {
    for (PhantomBoatRunnable runnable : RUNNABLE_MAP.values()) {
      if (runnable.contains(skeleton.func_110124_au()))
        return runnable; 
    } 
    return null;
  }
  
  public void startTask(EntityPlayer player, Location defaultLocation, Schematic schematic) {
    Timer timer = new Timer();
    PhantomBoatRunnable runnable = get(player);
    if (runnable != null)
      runnable.stop(false); 
    runnable = new PhantomBoatRunnable(player, defaultLocation, schematic);
    timer.scheduleAtFixedRate((TimerTask)runnable, 0L, 1000L);
    RUNNABLE_MAP.put(player.func_110124_au(), runnable);
  }
  
  public void stopTask(EntityPlayer player) {
    PhantomBoatRunnable runnable = get(player);
    if (runnable != null) {
      runnable.stop(true);
      RUNNABLE_MAP.remove(player.func_110124_au());
    } 
  }
  
  public PhantomBoatRunnable get(EntityPlayer player) {
    return RUNNABLE_MAP.get(player.func_110124_au());
  }
  
  public String getName() {
    return "Bateau fantôme";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 650;
  }
  
  public String getTexture() {
    return "july/phamtom_boat";
  }
  
  public boolean isTimerType() {
    return false;
  }
  
  public String[] getDescription() {
    return new String[] { "Tue tous les squelettes pour obtenir une récompense" };
  }
  
  public String getAction() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\PhantomBoatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */