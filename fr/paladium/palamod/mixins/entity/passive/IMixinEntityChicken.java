package fr.paladium.palamod.mixins.entity.passive;

import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({EntityChicken.class})
public abstract class IMixinEntityChicken extends EntityAnimal {
  public IMixinEntityChicken(World p_i1683_1_) {
    super(p_i1683_1_);
  }
  
  public boolean func_70085_c(EntityPlayer p_70085_1_) {
    ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
    if (itemstack != null && func_70877_b(itemstack) && func_70874_b() == 0 && !func_70880_s()) {
      JobsBridge.performEntityFeedQuest(p_70085_1_, EntityChicken.class);
      if (!p_70085_1_.field_71075_bZ.field_75098_d) {
        itemstack.field_77994_a--;
        if (itemstack.field_77994_a <= 0)
          p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null); 
      } 
      func_146082_f(p_70085_1_);
      return true;
    } 
    return super.func_70085_c(p_70085_1_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\passive\IMixinEntityChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */