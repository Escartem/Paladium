package fr.paladium.palamixins.mixin.common.entity;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.common.entity.EntityIsInsideOpaqueBlockEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Entity.class})
public class IMixinEntity {
  @Inject(method = {"isEntityInsideOpaqueBlock"}, at = {@At("HEAD")}, cancellable = true)
  public void isEntityInsideOpaqueBlock(CallbackInfoReturnable<Boolean> cir) {
    if (MinecraftForge.EVENT_BUS.post((Event)new EntityIsInsideOpaqueBlockEvent((Entity)this)))
      cir.setReturnValue(Boolean.valueOf(false)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\entity\IMixinEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */