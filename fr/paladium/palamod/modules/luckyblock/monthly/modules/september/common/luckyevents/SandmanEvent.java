package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SandCastleLifeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class SandmanEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le marchand de sable";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "september/sandman";
  
  private static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/sandman.png";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String NOT_WAITING_MESSAGE = "&cVous n'êtes pas en attente d'un chateau de sable !";
  
  public static final String SOUND_NAME = "sandman";
  
  public static SandmanEvent INSTANCE;
  
  private final HashSet<UUID> castleWaitingPlayers;
  
  public SandmanEvent() {
    INSTANCE = this;
    this.castleWaitingPlayers = new HashSet<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityNPC seller = new EntityNPC(world, "Sandman", "palamod:textures/entity/npc/sandman.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)seller);
  }
  
  public boolean isWaiting(EntityPlayerMP player, UUID uniqueId) {
    if (!this.castleWaitingPlayers.contains(uniqueId)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'êtes pas en attente d'un chateau de sable !" });
      return false;
    } 
    return true;
  }
  
  public void addWaitingPlayer(UUID uniqueId) {
    this.castleWaitingPlayers.add(uniqueId);
  }
  
  public void pasteCastle(EntityPlayerMP player) {
    UUID uniqueId = player.func_110124_au();
    TimedSchematic schematic = new TimedSchematic(-1L, new DoubleLocation((Entity)player), "lbaout_chateausable.schematic");
    schematic.paste(player, false);
    if (schematic.getSchematic() == null)
      return; 
    this.castleWaitingPlayers.remove(uniqueId);
  }
  
  public void handle(EntityPlayerMP player) {
    UUID uniqueId = player.func_110124_au();
    SandCastleLifeEvent.INSTANCE.perform(player, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
    this.castleWaitingPlayers.remove(uniqueId);
  }
  
  public String getName() {
    return "Le marchand de sable";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "september/sandman";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\SandmanEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */