package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Vector3d;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;

public class WalkDistanceQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.WALK_DISTANCE;
  
  private static final double MAX_DISTANCE_PER_TICK = 6.0D;
  
  private static final int INCREMENT_REQUIRED_VALUE = 50;
  
  private static final double NO_PROGRESS = 0.0D;
  
  private final Map<UUID, Vector3d> activeMap = new HashMap<>();
  
  private final Map<UUID, Double> distanceMap = new HashMap<>();
  
  public static void trigger(EntityPlayer entityPlayer, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      String travelDistanceProgressMessage = PalapassTranslateEnum.TRAVEL_DISTANCE_PROGRESS.textOrDefault(entityPlayer, "Distance parcourue §c{VALUE}/{QUANTITY}");
      quest.questProgress(entityPlayer, quantity, travelDistanceProgressMessage, false);
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
    UUID uuid = e.player.func_110124_au();
    this.activeMap.remove(uuid);
    this.distanceMap.remove(uuid);
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    double distance = getTraveledDistance(player);
    if (distance <= 0.0D)
      return; 
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    if (playerData == null)
      return; 
    UUID uuid = player.func_110124_au();
    this.distanceMap.put(uuid, Double.valueOf(((Double)this.distanceMap.getOrDefault(uuid, Double.valueOf(0.0D))).doubleValue() + distance));
    if (((Double)this.distanceMap.get(uuid)).doubleValue() >= 50.0D) {
      this.distanceMap.put(uuid, Double.valueOf(0.0D));
      trigger((EntityPlayer)player, 50);
    } 
  }
  
  private double getTraveledDistance(EntityPlayerMP player) {
    UUID uuid = player.func_110124_au();
    Vector3d prevPos = this.activeMap.get(uuid);
    if (prevPos == null) {
      this.activeMap.put(uuid, new Vector3d(player.field_70165_t, player.field_70163_u, player.field_70161_v));
      return 0.0D;
    } 
    double oldX = prevPos.x;
    double oldZ = prevPos.z;
    prevPos.set(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    double newX = player.field_70165_t;
    double newZ = player.field_70161_v;
    double distance = MathHelper.func_76133_a((oldX - newX) * (oldX - newX) + (oldZ - newZ) * (oldZ - newZ));
    if (distance > 6.0D || distance < 0.01D)
      return 0.0D; 
    return distance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\WalkDistanceQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */