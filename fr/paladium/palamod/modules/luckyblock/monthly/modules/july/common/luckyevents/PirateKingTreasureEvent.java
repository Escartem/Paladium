package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.TreasureChestManager;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class PirateKingTreasureEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le trésor du roi des pirates";
  
  private static final String TEXTURE_PATH = "july/pirate_king_treasure";
  
  private static final int RARITY = 500;
  
  private static final boolean IS_BAD = false;
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/pirate_king.png";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(10L);
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityNPC pirate = new EntityNPC(world, "Roi des pirates", "palamod:textures/entity/npc/pirate_king.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)pirate);
  }
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    PlayerInteractEvent.Action action = event.action;
    if (action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
      return; 
    DoubleLocation location = new DoubleLocation(event.x, event.y, event.z);
    if (TreasureChestManager.getInstance().openChest((EntityPlayerMP)player, location))
      event.setCanceled(true); 
  }
  
  public String getName() {
    return "Le trésor du roi des pirates";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "july/pirate_king_treasure";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\PirateKingTreasureEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */