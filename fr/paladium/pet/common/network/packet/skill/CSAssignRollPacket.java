package fr.paladium.pet.common.network.packet.skill;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSAssignRollPacket extends ForgePacket {
  public static final String REMOVE_ID = "remove";
  
  @PacketData
  private int slot;
  
  @PacketData
  private String skillId;
  
  public CSAssignRollPacket(int slot, String skillId) {
    this.slot = slot;
    this.skillId = skillId;
  }
  
  public CSAssignRollPacket() {}
  
  public void processServer(EntityPlayerMP player) {
    if (this.slot < 0 || this.slot > 5)
      return; 
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has()) {
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_NO_FAMILIAR.notification(player);
      return;
    } 
    if (this.skillId.equals("remove")) {
      if (pet.getSkillRollData().removeSkillRoll(this.slot)) {
        PetTranslateEnum.MESSAGE_SKILL_ROLL_REMOVED.message((ICommandSender)player, new Object[] { Integer.valueOf(this.slot + 1) });
        PetTranslateEnum.MESSAGE_SKILL_ROLL_REMOVED.notification(player, new Object[] { Integer.valueOf(this.slot + 1) });
      } 
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
    if (!pet.isSkillSelected(skill)) {
      PetTranslateEnum.MESSAGE_SKILL_NOT_SELECTED.message((ICommandSender)player);
      PetTranslateEnum.MESSAGE_SKILL_NOT_SELECTED.notification(player);
      return;
    } 
    pet.getSkillRollData().setSkillRoll(this.slot, skill);
    PetTranslateEnum.MESSAGE_SKILL_ROLL_SET_SUCCESS.message((ICommandSender)player, new Object[] { Integer.valueOf(this.slot + 1), skill.getName((EntityPlayer)player) });
    PetTranslateEnum.MESSAGE_SKILL_ROLL_SET_SUCCESS.notification(player, new Object[] { Integer.valueOf(this.slot + 1), skill.getName((EntityPlayer)player) });
  }
  
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\CSAssignRollPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */