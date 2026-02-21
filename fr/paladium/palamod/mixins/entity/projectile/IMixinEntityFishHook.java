package fr.paladium.palamod.mixins.entity.projectile;

import fr.paladium.palamod.modules.palapass.Palapass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({EntityFishHook.class})
public abstract class IMixinEntityFishHook extends Entity {
  @Shadow
  public EntityPlayer field_146042_b;
  
  public IMixinEntityFishHook(World p_i1582_1_) {
    super(p_i1582_1_);
  }
  
  @Redirect(method = {"func_146034_e"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntityInWorld(Lnet/minecraft/entity/Entity;)Z"))
  private boolean func_146034_e(World world, Entity entity) {
    if (this.field_146042_b instanceof net.minecraft.entity.player.EntityPlayerMP && entity instanceof EntityItem) {
      ItemStack stack = ((EntityItem)entity).func_92059_d();
      Palapass.processFishing(this.field_146042_b, stack);
    } 
    return world.func_72838_d(entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\projectile\IMixinEntityFishHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */