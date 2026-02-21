package fr.paladium.pet.common.network.packet.skill;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.roll.SkillRollData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import fr.paladium.pet.server.skill.skill.SkillType;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSActiveSpellPacket extends ForgePacket {
  @PacketData
  private int slot;
  
  public CSActiveSpellPacket(int slot) {
    this.slot = slot;
  }
  
  public CSActiveSpellPacket() {}
  
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has()) {
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.notification(player);
      return;
    } 
    SkillRollData rollData = pet.getSkillFromRoll(this.slot);
    if (rollData == null) {
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.notification(player);
      return;
    } 
    SkillData data = pet.getSkillData().get(rollData.getSkillId());
    SkillConfig config = SkillConfig.get();
    Optional<Skill> result = config.findSkillById(data.getSkillId());
    if (!result.isPresent()) {
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.notification(player);
      return;
    } 
    Skill skill = result.get();
    if (skill.getType() != SkillType.ACTIVE) {
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_ACTIVE_SKILL_AT_SLOT.notification(player);
      return;
    } 
    skill.handle(player, pet, data);
    pet.sync();
  }
  
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\CSActiveSpellPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */