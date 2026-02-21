package fr.paladium.palamod.mixins.world;

import fr.paladium.palamod.common.ExplosionSound;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Explosion.class})
public abstract class IMixinExplosion implements ExplosionSound {
  private boolean hasSound = true;
  
  public void setHasSound(boolean hasSound) {
    this.hasSound = hasSound;
  }
  
  public boolean isHasSound() {
    return this.hasSound;
  }
  
  @Redirect(method = {"doExplosionB"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSoundEffect(DDDLjava/lang/String;FF)V"))
  private void playSoundEffectProxy(World world, double p_72908_1_, double p_72908_3_, double p_72908_5_, String p_72908_7_, float p_72908_8_, float p_72908_9_) {
    if (this.hasSound)
      world.func_72908_a(p_72908_1_, p_72908_3_, p_72908_5_, p_72908_7_, p_72908_8_, p_72908_9_); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\world\IMixinExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */