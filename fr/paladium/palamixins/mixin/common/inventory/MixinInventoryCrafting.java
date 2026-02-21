package fr.paladium.palamixins.mixin.common.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({InventoryCrafting.class})
public interface MixinInventoryCrafting {
  @Accessor("eventHandler")
  Container getContainer();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\inventory\MixinInventoryCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */