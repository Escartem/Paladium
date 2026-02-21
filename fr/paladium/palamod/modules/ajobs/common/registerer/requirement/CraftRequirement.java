package fr.paladium.palamod.modules.ajobs.common.registerer.requirement;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.jobs.Requirement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftRequirement extends Requirement<ItemStack, ItemStack> {
  public CraftRequirement(int level, JobType type, String description, int value, ItemStack target) {
    super(level, type, description, value, target);
  }
  
  protected int validate(EntityPlayer player, ItemStack object) {
    if (object == null || object.func_77973_b() == null || object.field_77994_a <= 0 || !((ItemStack)getTarget()).func_77969_a(object))
      return 0; 
    return object.field_77994_a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\requirement\CraftRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */