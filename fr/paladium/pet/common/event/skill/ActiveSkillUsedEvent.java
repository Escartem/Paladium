package fr.paladium.pet.common.event.skill;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.skill.Skill;
import net.minecraft.entity.player.EntityPlayer;

@Cancelable
public class ActiveSkillUsedEvent extends Event {
  private final EntityPlayer player;
  
  private final PetPlayer pet;
  
  private final Skill skill;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public PetPlayer getPet() {
    return this.pet;
  }
  
  public Skill getSkill() {
    return this.skill;
  }
  
  public ActiveSkillUsedEvent(EntityPlayer player, PetPlayer pet, Skill skill) {
    this.player = player;
    this.pet = pet;
    this.skill = skill;
    PetStatChangeEvent.call(player, UpdateStatType.SKILL_USED);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\skill\ActiveSkillUsedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */