package fr.paladium.palamod.mixins.block;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.events.EntityPortalEnterEvent;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockEndPortal.class})
public abstract class IMixinBlockEndPortal {
  @Inject(method = {"onEntityCollidedWithBlock"}, at = {@At("HEAD")}, cancellable = true)
  private void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity, CallbackInfo ci) {
    if (entity.field_70154_o == null && entity.field_70153_n == null && !world.field_72995_K) {
      EntityPortalEnterEvent event = new EntityPortalEnterEvent(world, entity, x, y, z);
      MinecraftForge.EVENT_BUS.post((Event)event);
      if (event.isCanceled())
        ci.cancel(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlockEndPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */