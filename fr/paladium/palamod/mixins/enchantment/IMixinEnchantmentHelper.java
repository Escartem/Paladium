package fr.paladium.palamod.mixins.enchantment;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.common.CommonBridge;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EnchantmentHelper.class})
public class IMixinEnchantmentHelper {
  @Inject(method = {"buildEnchantmentList"}, at = {@At("RETURN")}, cancellable = true)
  private static void onBuildEnchantmentList(Random random, ItemStack itemStack, int enchantmentLevel, CallbackInfoReturnable<List> ci) {
    List<EnchantmentData> enchantmentList = (List<EnchantmentData>)ci.getReturnValue();
    if (enchantmentList != null) {
      enchantmentList.removeIf(data -> {
            Enchantment enchantment = data.field_76302_b;
            return (enchantment == Enchantment.field_151369_A || enchantment == Enchantment.field_151370_z);
          });
      if (enchantmentList.isEmpty())
        enchantmentList.add(new EnchantmentData(Enchantment.field_77347_r, 1)); 
    } 
    ci.setReturnValue(enchantmentList);
  }
  
  @Inject(method = {"getLootingModifier"}, at = {@At("HEAD")}, cancellable = true)
  private static void getLootingModifier(EntityLivingBase entity, CallbackInfoReturnable<Integer> ci) {
    EntityPlayerMP player = (EntityPlayerMP)entity;
    if (entity instanceof EntityPlayerMP && player.field_71071_by.func_70431_c(new ItemStack(BlocksRegister.LILY_OF_THE_VALLEY)) && CommonBridge.isFarmland() && 
      entity.field_70170_p.field_73012_v.nextInt(100) < 4) {
      int level = EnchantmentHelper.func_77506_a(Enchantment.field_77335_o.field_77352_x, entity.func_70694_bm()) + 1;
      ci.setReturnValue(Integer.valueOf(level));
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\enchantment\IMixinEnchantmentHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */