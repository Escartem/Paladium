package fr.paladium.pet.server.skill.handler;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.skill.ActiveSkillUsedEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.SkillManager;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.concurrent.TimeUnit;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public abstract class ASkillHandler {
  private String id;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof ASkillHandler))
      return false; 
    ASkillHandler other = (ASkillHandler)o;
    if (!other.canEqual(this))
      return false; 
    Object this$id = getId(), other$id = other.getId();
    return !((this$id == null) ? (other$id != null) : !this$id.equals(other$id));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof ASkillHandler;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $id = getId();
    return result * 59 + (($id == null) ? 43 : $id.hashCode());
  }
  
  public String toString() {
    return "ASkillHandler(id=" + getId() + ")";
  }
  
  public String getId() {
    return this.id;
  }
  
  public ASkillHandler(String id) {
    this.id = id;
  }
  
  public Skill getSkill() {
    return SkillConfig.get().getActive(this.id);
  }
  
  public void handle(EntityPlayerMP player, PetPlayer pet, SkillData data) {
    Skill skill = getSkill();
    if (skill == null) {
      PetTranslateEnum.MESSAGE_SKILL_NO_FOUND.message((ICommandSender)player);
      return;
    } 
    String skillName = skill.getName((EntityPlayer)player);
    long now = System.currentTimeMillis();
    long cooldown = skill.getCooldown(now, data.getNextUseMillis());
    if (!SkillManager.getInstance().isBypassed((EntityPlayer)player) && skill.isOnCooldown(cooldown)) {
      PetTranslateEnum.MESSAGE_SKILL_ON_COOLDOWN.message((ICommandSender)player, new Object[] { skillName, DurationConverter.fromMillisToString(cooldown) });
      return;
    } 
    ActiveSkillUsedEvent event = new ActiveSkillUsedEvent((EntityPlayer)player, pet, skill);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return; 
    if (!perform(player, pet)) {
      PetTranslateEnum.MESSAGE_CANT_USE_SKILL.message((ICommandSender)player, new Object[] { skillName });
      return;
    } 
    PetTranslateEnum.MESSAGE_SKILL_USED.message((ICommandSender)player, new Object[] { skillName });
    long cooldownReduction = applyPetSkillCooldownReduction(TimeUnit.MINUTES
        .toMillis(skill.getCooldownInMinutes()), pet);
    data.use(pet, now + cooldownReduction);
    pet.setLastSkillUsage(now);
  }
  
  private long applyPetSkillCooldownReduction(long cooldownMillis, PetPlayer pet) {
    PassiveResponse response = PassiveSkillEnum.BOOSTED_HARVEST.getResponse(pet, SkillConfig.get());
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return cooldownMillis; 
    long bonus = (long)(cooldownMillis * response.getValueAsPercent(value));
    return cooldownMillis - bonus;
  }
  
  public abstract boolean perform(EntityPlayerMP paramEntityPlayerMP, PetPlayer paramPetPlayer);
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\ASkillHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */