package fr.paladium.pet.common.network.packet.skill;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.skill.AssignSkillEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.SkillManager;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import java.util.HashMap;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class CSAssignSkillPacket extends ForgePacket {
  @PacketData
  private int slot;
  
  @PacketData
  private String skillId;
  
  public CSAssignSkillPacket(int slot, String skillId) {
    this.slot = slot;
    this.skillId = skillId;
  }
  
  public CSAssignSkillPacket() {}
  
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has()) {
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.notification(player);
      return;
    } 
    long now = System.currentTimeMillis();
    SkillData data = pet.getSkill(this.slot);
    if (data == null || this.skillId == null) {
      PetTranslateEnum.MESSAGE_NO_SLOT.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_SLOT.notification(player);
      return;
    } 
    long changeCooldown = data.getChangeCooldown(player, now);
    if (!SkillManager.getInstance().isBypassed((EntityPlayer)player) && !data.canChangeSlot(changeCooldown)) {
      PetTranslateEnum.MESSAGE_CHANGE_SKILL_COOLDOWN.message((ICommandSender)player, new Object[] { DurationConverter.fromMillisToString(changeCooldown) });
      PetTranslateEnum.MESSAGE_CHANGE_SKILL_COOLDOWN.notification(player, new Object[] { DurationConverter.fromMillisToString(changeCooldown) });
      return;
    } 
    SkillConfig config = SkillConfig.get();
    Optional<Skill> result = config.findSkillById(this.skillId);
    if (!result.isPresent()) {
      PetTranslateEnum.MESSAGE_NO_SKILL.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_SKILL.notification(player);
      return;
    } 
    Skill skill = result.get();
    if (skill.getType() == SkillType.ACTIVE) {
      Optional<Skill> oldSkill = config.findSkillById(data.getSkillId());
      if (!oldSkill.isPresent() || ((Skill)oldSkill.get()).getType() != SkillType.ACTIVE) {
        HashMap<SkillType, Integer> slots = config.getSkillCount(pet);
        int activeCount = ((Integer)slots.getOrDefault(SkillType.ACTIVE, Integer.valueOf(0))).intValue();
        if (activeCount >= config.getMaxActiveSkills()) {
          PetTranslateEnum.MESSAGE_MAX_ACTIVE_SKILLS.message((ICommandSender)player, new Object[] { Integer.valueOf(config.getMaxActiveSkills()) });
          PetTranslateEnum.MESSAGE_MAX_ACTIVE_SKILLS.notification(player, new Object[] { Integer.valueOf(config.getMaxActiveSkills()) });
          return;
        } 
      } 
    } 
    int level = pet.getLevel();
    if (!skill.hasRequiredLevel(level)) {
      PetTranslateEnum.MESSAGE_SKILL_REQUIRED_LEVEL.message((ICommandSender)player, new Object[] { Integer.valueOf(skill.getRequiredLevel()) });
      PetTranslateEnum.MESSAGE_SKILL_REQUIRED_LEVEL.notification(player, new Object[] { Integer.valueOf(skill.getRequiredLevel()) });
      return;
    } 
    if (pet.isSkillSelected(skill)) {
      PetTranslateEnum.MESSAGE_SKILL_ALREADY_SELECTED.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_SKILL_ALREADY_SELECTED.notification(player);
      return;
    } 
    if (SkillManager.getInstance().isBypassed((EntityPlayer)player)) {
      data.changeSlotBypass(skill);
    } else {
      data.changeSlot(skill, pet.getNextSkillUsage(skill));
    } 
    PetTranslateEnum.MESSAGE_CHANGE_SKILL.message((ICommandSender)player, new Object[] { Integer.valueOf(this.slot + 1), skill.getName((EntityPlayer)player) });
    PetTranslateEnum.MESSAGE_CHANGE_SKILL.notification(player, new Object[] { Integer.valueOf(this.slot + 1), skill.getName((EntityPlayer)player) });
    AssignSkillEvent event = new AssignSkillEvent((EntityPlayer)player, pet, skill);
    MinecraftForge.EVENT_BUS.post((Event)event);
  }
  
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\CSAssignSkillPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */