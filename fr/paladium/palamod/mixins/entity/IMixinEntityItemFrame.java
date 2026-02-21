package fr.paladium.palamod.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityItemFrame.class})
public class IMixinEntityItemFrame extends EntityHanging {
  public IMixinEntityItemFrame(World world) {
    super(world);
  }
  
  @Inject(method = {"func_146065_b"}, at = {@At("HEAD")}, cancellable = true)
  public void attackEntityFrom(Entity entity, boolean flag, CallbackInfo ci) {
    if (flag && entity == null) {
      ci.cancel();
      return;
    } 
  }
  
  public int func_82329_d() {
    return 9;
  }
  
  public int func_82330_g() {
    return 9;
  }
  
  public void func_110128_b(Entity entity) {}
  
  public void func_70091_d(double x, double y, double z) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\entity\IMixinEntityItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */