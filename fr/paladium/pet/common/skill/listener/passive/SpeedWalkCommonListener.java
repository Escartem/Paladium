package fr.paladium.pet.common.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;

public class SpeedWalkCommonListener {
  public static final double MIN_SPEED = 0.1D;
  
  public static final double DEFAULT_MULTIPLIER = 1.0D;
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerTickClient(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.END || !event.player.field_70122_E || CommonModule.getInstance().getCombatTag().inFight())
      return; 
    double speedMultiplier = 1.0D;
    double boostValue = 1.0D;
    boolean isMoving = (event.player.field_70701_bs != 0.0F || event.player.field_70702_br != 0.0F);
    PassiveSkillEnum skill = PassiveSkillEnum.SPEED_WALK;
    double value = PetClientProxy.getInstance().getSkillValue(skill.getId());
    boostValue = PetUtils.getValueAsPercent(value);
    speedMultiplier += boostValue;
    if (isMoving) {
      double currentSpeed = Math.sqrt(event.player.field_70159_w * event.player.field_70159_w + event.player.field_70179_y * event.player.field_70179_y);
      if (currentSpeed < 0.1D * speedMultiplier) {
        event.player.field_70159_w *= speedMultiplier;
        event.player.field_70179_y *= speedMultiplier;
      } 
      return;
    } 
    event.player.field_70159_w /= speedMultiplier;
    event.player.field_70179_y /= speedMultiplier;
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerTickServer(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.END || !event.player.field_70122_E)
      return; 
    if (Server.is(new ServerType[] { ServerType.FACTION }))
      try {
        if (CombatTag.getInstance().getManager().isInCombat(Bukkit.getPlayer(event.player.func_110124_au())))
          return; 
      } catch (Exception|NoClassDefFoundError exception) {} 
    EntityPlayer player = event.player;
    double speedMultiplier = 1.0D;
    double boostValue = 1.0D;
    boolean isMoving = (event.player.field_70701_bs != 0.0F || event.player.field_70702_br != 0.0F);
    PassiveSkillEnum skill = PassiveSkillEnum.SPEED_WALK;
    PetPlayer pet = PetPlayer.get(player);
    PassiveResponse response = skill.getResponse(pet);
    double value = response.getPersonalValue(pet);
    boostValue = PetUtils.getValueAsPercent(value);
    speedMultiplier += boostValue;
    if (isMoving) {
      double currentSpeed = Math.sqrt(event.player.field_70159_w * event.player.field_70159_w + event.player.field_70179_y * event.player.field_70179_y);
      if (currentSpeed < 0.1D * speedMultiplier) {
        event.player.field_70159_w *= speedMultiplier;
        event.player.field_70179_y *= speedMultiplier;
      } 
      return;
    } 
    event.player.field_70159_w /= speedMultiplier;
    event.player.field_70179_y /= speedMultiplier;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\skill\listener\passive\SpeedWalkCommonListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */