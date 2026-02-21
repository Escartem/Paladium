package fr.paladium.pet.common.network.packet.skill;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.client.ui.utils.data.SkillRollSlotData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.roll.SkillRollData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBRequestSkillRollPacket extends ForgePacket {
  @PacketData
  private List<SkillRollSlotData> data;
  
  public BBRequestSkillRollPacket() {}
  
  public BBRequestSkillRollPacket(List<SkillRollSlotData> data) {
    this.data = data;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.data == null || this.data.isEmpty())
      this.data = new ArrayList<>(); 
    PetClientProxy.getInstance().setSkillRollSlots(this.data);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    List<SkillRollData> rolls = pet.getSkillRollData().getRolls(pet);
    List<SkillRollSlotData> skillRollSlots = new ArrayList<>();
    for (int i = 0; i < 6; i++)
      skillRollSlots.add(SkillRollSlotData.none(i)); 
    int index = 0;
    long now = System.currentTimeMillis();
    SkillConfig config = SkillConfig.get();
    for (SkillRollData roll : rolls) {
      if (index >= 6)
        break; 
      Optional<Skill> result = config.findSkillById(roll.getSkillId());
      if (result.isPresent()) {
        Skill skill = result.get();
        int slot = roll.getSlot();
        SkillRollData skillRollData = pet.getSkillFromRoll(slot);
        if (skillRollData == null)
          continue; 
        SkillData skillData = pet.getSkill(skillRollData.getSkillId());
        if (skillData == null)
          continue; 
        boolean usageCooldown = skill.isOnCooldown(skill.getCooldown(now, skillData.getNextUseMillis()));
        skillRollSlots.set(index, SkillRollSlotData.from(roll.getSlot(), skill, usageCooldown));
      } 
      index++;
    } 
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBRequestSkillRollPacket(skillRollSlots), player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\BBRequestSkillRollPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */