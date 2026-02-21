package fr.paladium.palamixins.mixin.server.entity;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.server.CriticalHitEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityPlayerMP.class})
public abstract class MixinEntityPlayerMP {
  @Inject(method = {"onCriticalHit"}, at = {@At("HEAD")}, cancellable = true)
  public void onCriticalHit(Entity entity, CallbackInfo ci) {
    EntityPlayerMP player = (EntityPlayerMP)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new CriticalHitEvent(player, entity, CriticalHitEvent.CriticalType.CRITICAL)))
      ci.cancel(); 
  }
  
  @Inject(method = {"onEnchantmentCritical"}, at = {@At("HEAD")}, cancellable = true)
  public void onEnchantmentCritical(Entity entity, CallbackInfo ci) {
    EntityPlayerMP player = (EntityPlayerMP)this;
    if (MinecraftForge.EVENT_BUS.post((Event)new CriticalHitEvent(player, entity, CriticalHitEvent.CriticalType.ENCHANTMENT)))
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\server\entity\MixinEntityPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */