package fr.paladium.palamod.mixins.entity.passive;

import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntitySheep.class})
public abstract class IMixinEntitySheep extends EntityAnimal {
  public IMixinEntitySheep(World p_i1683_1_) {
    super(p_i1683_1_);
  }
  
  @Inject(method = {"interact"}, at = {@At("HEAD")})
  public void interact(EntityPlayer player, CallbackInfoReturnable<Boolean> ci) {
    ItemStack itemstack = player.field_71071_by.func_70448_g();
    if (itemstack != null && func_70877_b(itemstack) && func_70874_b() == 0 && !func_70880_s())
      JobsBridge.performEntityFeedQuest(player, EntitySheep.class); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\passive\IMixinEntitySheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */