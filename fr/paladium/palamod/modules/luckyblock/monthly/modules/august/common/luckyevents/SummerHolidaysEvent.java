package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets.SCPlanePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.managers.AugustConfigManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class SummerHolidaysEvent extends ALuckyEvent implements ISchematic {
  private static final String EVENT_NAME = "Vacances d'été";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 1400;
  
  private static final String TEXTURE_PATH = "august/summer_holidays";
  
  private static final String SCHEMATIC_PATH = "lbaout_avion.schematic";
  
  private static final String SOUND_NAME = "panic";
  
  private static final String ALERT_MESSAGE = "&eRendez vous sur le serveur &dEvent &epour être téléporté !";
  
  private static final long DURATION_BEFORE_SONG_MILLIS = TimeUnit.SECONDS.toMillis(10L);
  
  private static final long DURATION_BEFORE_TELEPORT = TimeUnit.SECONDS.toMillis(20L);
  
  public static SummerHolidaysEvent INSTANCE;
  
  public SummerHolidaysEvent() {
    INSTANCE = this;
  }
  
  public void perform(final EntityPlayerMP player, int x, int y, int z) {
    final World world = player.field_70170_p;
    final DoubleLocation spawnLocation = new DoubleLocation(x, 150.0D, z, player.field_70177_z, player.field_70125_A);
    Schematic schematic = null;
    try {
      schematic = SchematicUtils.paste((EntityPlayer)player, SchematicUtils.load("lbaout_avion.schematic"), world, spawnLocation, false);
    } catch (Exception e) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
      e.printStackTrace();
    } 
    int posX = spawnLocation.getBlockX();
    int posY = spawnLocation.getBlockY();
    int posZ = spawnLocation.getBlockZ();
    if (EventUtils.canInteract((EntityPlayer)player, posX, posY, posZ)) {
      world.func_147449_b(posX, posY, posZ, (Block)Blocks.field_150349_c);
      spawnLocation.clone().add(0.0D, 1.0D, 0.0D).teleportServer((EntityPlayer)player);
    } 
    final LuckyTask task = new LuckyTask();
    final Schematic finalSchematic = schematic;
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskAsyncLater(new Runnable() {
          public void run() {
            try {
              Thread.sleep(SummerHolidaysEvent.DURATION_BEFORE_SONG_MILLIS);
              MonthlyUtils.playSound(player, "panic");
              PalaMod.network.sendTo((IMessage)new SCPlanePacket(), player);
              Thread.sleep(SummerHolidaysEvent.DURATION_BEFORE_TELEPORT);
              SummerHolidaysEvent.this.alert(player);
              player.field_70143_R = 0.0F;
              TeleportUtils.teleport((EntityPlayer)player, spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ());
              SchematicUtils.clean(finalSchematic, world, spawnLocation);
            } catch (Exception e) {
              e.printStackTrace();
            } 
            PaladiumScheduler.INSTANCE.cancelTask(task.id);
          }
        }0L).getTaskId();
  }
  
  public void alert(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eRendez vous sur le serveur &dEvent &epour être téléporté !" });
    SoundUtils.LEVEL_UP.playSound(player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties != null)
      properties.setSummerTeleportation(true); 
  }
  
  public void teleport(EntityPlayerMP player) {
    AugustConfigManager manager = AugustConfigManager.getInstance();
    manager.teleport((EntityPlayer)player, 
        
        CommonModule.getInstance().getConfig().getServerName(), manager
        .getConfig().getSummerIslandLocations());
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties != null)
      properties.setSummerTeleportation(false); 
  }
  
  public String getName() {
    return "Vacances d'été";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1400;
  }
  
  public String getTexture() {
    return "august/summer_holidays";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerHolidaysEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */