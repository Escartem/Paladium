package fr.paladium.palamod.mixins.item;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockBridge;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemTool.class})
public class IMixinItemTool extends Item {
  public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
    int amount = 2;
    if (LuckyBlockBridge.damageTotem(attacker, amount))
      return true; 
    stack.func_77972_a(amount, attacker);
    return true;
  }
  
  public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
    int amount = 1;
    if (block.func_149712_f(world, x, y, z) != 0.0D) {
      if (LuckyBlockBridge.damageTotem(entity, amount))
        return true; 
      stack.func_77972_a(amount, entity);
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\item\IMixinItemTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */