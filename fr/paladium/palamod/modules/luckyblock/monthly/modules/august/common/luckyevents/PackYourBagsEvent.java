package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.PackYourBagsRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PackYourBagsEvent extends ALuckyEvent implements IHeliosLuckyEventWidget, ISchematic {
  public static final String EVENT_NAME = "Fais tes valises";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 700;
  
  private static final String TEXTURE_PATH = "august/pack_your_bags";
  
  public static final String MANSION_SCHEMATIC_PATH = "lbaout_maison.schematic";
  
  public static final String CAMPING_SCHEMATIC_PATH = "lbaout_camping.schematic";
  
  private static PackYourBagsEvent INSTANCE;
  
  public PackYourBagsEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), TimeUnit.MINUTES
        .toMillis(1L), System.currentTimeMillis());
    World world = player.field_70170_p;
    DoubleLocation spawnLocation = new DoubleLocation(x, y, z);
    Schematic schematic = null;
    try {
      schematic = SchematicUtils.paste((EntityPlayer)player, SchematicUtils.load("lbaout_maison.schematic"), world, spawnLocation, false);
    } catch (Exception e) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
      e.printStackTrace();
    } 
    Timer timer = new Timer();
    timer.schedule((TimerTask)new PackYourBagsRunnable(player, world, spawnLocation, schematic), 0L, 1000L);
  }
  
  public String getName() {
    return "Fais tes valises";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "august/pack_your_bags";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Récupère les 5 objets et rend toi dans la voiture de tes parents" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\PackYourBagsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */