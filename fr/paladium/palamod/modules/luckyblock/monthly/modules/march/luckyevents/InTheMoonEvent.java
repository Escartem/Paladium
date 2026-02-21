package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCBlackScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.utils.StructureUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class InTheMoonEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Tu es dans la lune";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 130;
  
  private static final String TEXTURE_PATH = "march/in_the_moon";
  
  private static final String SCHEMATIC_NAME = "in_the_moon";
  
  private static final String TELEPORT_MESSAGE = "§eTu as été téléporté dans la lune !";
  
  private static final long BLACK_SCREEN_DURATION = TimeUnit.SECONDS.toMillis(1L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    int structureY = 250;
    DoubleLocation location = new DoubleLocation(x, 250.0D, z);
    Schematic schematic = SchematicUtils.loadSchematic("in_the_moon");
    if (schematic == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de charger la structure." });
      return;
    } 
    if (!StructureUtils.paste((EntityPlayer)player, schematic, location, false)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cIl n'a pas été possible de coller la structure." });
      return;
    } 
    SCBlackScreenPacket sCBlackScreenPacket = new SCBlackScreenPacket(BLACK_SCREEN_DURATION);
    CommonMarch.getInstance().getNetwork().sendTo((IMessage)sCBlackScreenPacket, player);
    (new Thread(() -> {
          try {
            Thread.sleep(BLACK_SCREEN_DURATION);
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
          } catch (InterruptedException interruptedException) {}
        })).start();
  }
  
  public String getName() {
    return "Tu es dans la lune";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "march/in_the_moon";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\InTheMoonEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */