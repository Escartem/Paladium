package fr.paladium.palamod.mixins.client.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Item.class})
public class IMixinItem {
  @Inject(method = {"getColorFromItemStack"}, at = {@At("HEAD")}, cancellable = true)
  public void isColored(ItemStack p_82790_1_, int p_82790_2_, CallbackInfoReturnable<Integer> ci) {
    if (p_82790_1_.func_77942_o() && 
      p_82790_1_.func_77978_p().func_74764_b("palamod_colored")) {
      ci.setReturnValue(Integer.valueOf(p_82790_1_.func_77978_p().func_74762_e("palamod_colored")));
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\item\IMixinItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */