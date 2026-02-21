package fr.paladium.palamod.modules.ajobs.common.registerer.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DropCauldronRequirement extends Requirement<Void, ItemStack> {
  public DropCauldronRequirement(int level, JobType type, String description, int value) {
    super(level, type, description, value, null);
  }
  
  protected int validate(EntityPlayer player, ItemStack object) {
    if (object == null || object.func_77973_b() == null || object.field_77994_a <= 0)
      return 0; 
    return object.field_77994_a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\requirement\DropCauldronRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */