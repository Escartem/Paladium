package fr.paladium.palamod.mixins.client.particle;

import fr.paladium.palamod.client.PaticleAccessor;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityFX.class})
public abstract class IMixinEntityFX extends Entity implements PaticleAccessor {
  public IMixinEntityFX(World p_i1582_1_) {
    super(p_i1582_1_);
  }
  
  @Accessor
  public abstract void setParticleGravity(float paramFloat);
  
  @Accessor
  public abstract void setParticleMaxAge(int paramInt);
  
  @Inject(method = {"<init>(Lnet/minecraft/world/World;DDD)V"}, at = {@At(value = "RETURN", target = "Lnet/minecraft/client/particle/EntityFX;setPosition(DDD)V")})
  public void addSetPrevPos(World p_i1219_1_, double p_i1219_2_, double p_i1219_4_, double p_i1219_6_, CallbackInfo ci) {
    this.field_70169_q = p_i1219_2_;
    this.field_70167_r = p_i1219_4_;
    this.field_70166_s = p_i1219_6_;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\particle\IMixinEntityFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */