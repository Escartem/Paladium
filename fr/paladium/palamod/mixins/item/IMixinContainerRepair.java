package fr.paladium.palamod.mixins.item;

import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ContainerRepair.class})
public abstract class IMixinContainerRepair {
  @Redirect(method = {"updateRepairOutput", "updateItemName"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setStackDisplayName(Ljava/lang/String;)Lnet/minecraft/item/ItemStack;"))
  public ItemStack setStackDisplayName(ItemStack item, String string) {
    item.func_151001_c(string);
    item.field_77990_d.func_74775_l("display").func_74757_a("Renamed", true);
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\item\IMixinContainerRepair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */