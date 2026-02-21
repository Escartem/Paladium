package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.JudgeData;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class DevilsAdvocateEvent extends ALuckyEvent implements ISchematic {
  private static final String EVENT_NAME = "L'avocat du diable";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 500;
  
  private static final String TEXTURE_PATH = "september/devils_advocate";
  
  public static final String DEVIL_SKIN_PATH = "palamod:textures/entity/npc/devil.png";
  
  public static final String JUDGE_SKIN_PATH = "palamod:textures/entity/npc/judge.png";
  
  public static final String SCHEMATIC_PATH = "lb09_tribunal.schematic";
  
  private static final String WARNING_MESSAGE = "&eVeuillez défendre le &cdiable &econtre le &cjugement &e!";
  
  public static final long EXPIRATION_MILLIS = TimeUnit.MINUTES.toMillis(10L);
  
  public static DevilsAdvocateEvent INSTANCE;
  
  private final Map<UUID, JudgeData> judges;
  
  public DevilsAdvocateEvent() {
    INSTANCE = this;
    this.judges = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    DoubleLocation location = new DoubleLocation((Entity)player);
    TimedSchematic timed = new TimedSchematic(EXPIRATION_MILLIS, location, "lb09_tribunal.schematic");
    Schematic schematic = timed.paste(player, true);
    World world = player.field_70170_p;
    EntityNPC devil = new EntityNPC(world, "Diable", "palamod:textures/entity/npc/devil.png", (x + 1), (y - 2), z, EXPIRATION_MILLIS, EXPIRATION_MILLIS, true);
    EntityNPC judge = new EntityNPC(world, "Juge", "palamod:textures/entity/npc/judge.png", (x - 1), (y - 2), z, EXPIRATION_MILLIS, EXPIRATION_MILLIS, true);
    world.func_72838_d((Entity)devil);
    world.func_72838_d((Entity)judge);
    JudgeData judgeData = new JudgeData((Entity)judge, (Entity)devil, timed.getUniqueId());
    this.judges.put(player.func_110124_au(), judgeData);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVeuillez défendre le &cdiable &econtre le &cjugement &e!" });
  }
  
  public JudgeData get(UUID uniqueId, Entity entity) {
    JudgeData data = this.judges.get(uniqueId);
    if (!data.isValid(entity))
      return null; 
    return data;
  }
  
  public void remove(UUID uniqueId) {
    this.judges.remove(uniqueId);
  }
  
  public String getName() {
    return "L'avocat du diable";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "september/devils_advocate";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\DevilsAdvocateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */