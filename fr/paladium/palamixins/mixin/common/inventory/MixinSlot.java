package fr.paladium.palamixins.mixin.common.inventory;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.SlotCanTakeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Slot.class})
public abstract class MixinSlot {
  @Inject(method = {"canTakeStack"}, at = {@At("RETURN")}, cancellable = true)
  public void canTakeStack(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
    Slot slot = (Slot)this;
    SlotCanTakeEvent event = new SlotCanTakeEvent(slot, player, slot.func_75211_c());
    MinecraftForge.EVENT_BUS.post((Event)event);
    cir.setReturnValue(Boolean.valueOf(!event.isCanceled()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\inventory\MixinSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */