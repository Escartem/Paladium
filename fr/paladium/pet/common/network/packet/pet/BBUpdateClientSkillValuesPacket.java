package fr.paladium.pet.common.network.packet.pet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSide;
import fr.paladium.pet.PetLogger;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.HashMap;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBUpdateClientSkillValuesPacket extends ForgePacket {
  @PacketData(PacketSide.SERVER)
  private HashMap<String, Double> data;
  
  public BBUpdateClientSkillValuesPacket() {}
  
  public BBUpdateClientSkillValuesPacket(PetPlayer pet) {
    this.data = new HashMap<>();
    if (pet == null)
      return; 
    SkillConfig config = SkillConfig.get();
    for (SkillData value : pet.getSkillData().getSkills().values()) {
      if (value.getSkillId().equals("no_skill"))
        continue; 
      Optional<Skill> result = config.findSkillById(value.getSkillId());
      if (!result.isPresent())
        continue; 
      Skill skill = result.get();
      this.data.put(skill
          .getId(), 
          Double.valueOf(skill.getPersonalValue(pet)));
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBUpdateClientSkillValuesPacket(PetPlayer.get((EntityPlayer)player)), player);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    PetClientProxy.getInstance().setSkillValues(this.data);
    PetLogger.info("Skill values updated" + this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\BBUpdateClientSkillValuesPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */