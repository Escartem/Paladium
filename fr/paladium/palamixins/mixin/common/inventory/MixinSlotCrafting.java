package fr.paladium.palamixins.mixin.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({SlotCrafting.class})
public interface MixinSlotCrafting {
  @Accessor("thePlayer")
  EntityPlayer getPlayer();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\inventory\MixinSlotCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */