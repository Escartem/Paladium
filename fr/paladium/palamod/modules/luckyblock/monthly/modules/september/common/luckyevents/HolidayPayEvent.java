package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class HolidayPayEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Pécule de vacances";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "september/holiday_pay";
  
  private static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/journey.png";
  
  private static final String SCHEMATIC_PATH = "lb09_agencevoyage.schematic";
  
  public static final String ISLAND_SCHEMATIC_PATH = "lbaout_camping.schematic";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String NOT_WAITING_MESSAGE = "&cVous n'êtes pas en attente de placer une structure !";
  
  private final HashSet<UUID> waitingPlayers;
  
  public static HolidayPayEvent INSTANCE;
  
  public HashSet<UUID> getWaitingPlayers() {
    return this.waitingPlayers;
  }
  
  public HolidayPayEvent() {
    INSTANCE = this;
    this.waitingPlayers = new HashSet<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    TimedSchematic timed = new TimedSchematic(DURATION, new DoubleLocation((Entity)player), "lb09_agencevoyage.schematic");
    timed.paste(player, false);
    World world = player.func_130014_f_();
    EntityNPC journey = new EntityNPC(world, "Journey", "palamod:textures/entity/npc/journey.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)journey);
  }
  
  public boolean isWaiting(EntityPlayerMP player, UUID uniqueId) {
    if (!this.waitingPlayers.contains(uniqueId)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'êtes pas en attente de placer une structure !" });
      return false;
    } 
    return true;
  }
  
  public String getName() {
    return "Pécule de vacances";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "september/holiday_pay";
  }
  
  public void handle(EntityPlayerMP player) {
    DoubleLocation location = new DoubleLocation(player.field_70165_t, 150.0D, player.field_70161_v, player.field_70177_z, player.field_70125_A);
    TimedSchematic timed = new TimedSchematic(-1L, location, "lbaout_camping.schematic");
    timed.paste(player, false);
    if (timed.getSchematic() == null) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aUtilisez la commande &6/summerisland &apour faire apparaître l'île des vacances !" });
      return;
    } 
    int x = (int)Math.floor(location.getX());
    int y = (int)Math.floor(location.getY());
    int z = (int)Math.floor(location.getZ());
    if (timed.getSchematic() != null && EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      player.func_130014_f_().func_147449_b(x, y, z, (Block)Blocks.field_150349_c);
      TeleportUtils.teleport((EntityPlayer)player, x, (y + 2), z);
    } 
    this.waitingPlayers.remove(player.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\HolidayPayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */