package fr.paladium.palamixins.mixin.common.enchantment;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamixins.event.common.enchantment.EnchantmentKnockbackModifierEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EnchantmentHelper.class})
public class MixinEnchantmentHelper {
  @Overwrite
  public static int func_77507_b(EntityLivingBase attacker, EntityLivingBase target) {
    EnchantmentKnockbackModifierEvent event = new EnchantmentKnockbackModifierEvent(attacker, target, EnchantmentHelper.func_77506_a(Enchantment.field_77337_m.field_77352_x, attacker.func_70694_bm()));
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return 0; 
    return event.getModifier();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\enchantment\MixinEnchantmentHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */