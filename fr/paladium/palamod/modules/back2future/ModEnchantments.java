package fr.paladium.palamod.modules.back2future;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.back2future.enchantment.FrostWalker;
import fr.paladium.palamod.modules.back2future.enchantment.Omniscient;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ModEnchantments {
  public static Enchantment frostWalker;
  
  public static Enchantment omniscient;
  
  public static void init() {
    if (Back2Future.enableFrostWalker)
      frostWalker = (Enchantment)new FrostWalker(); 
    omniscient = (Enchantment)new Omniscient();
  }
  
  public static void onLivingUpdate(EntityLivingBase entity) {
    if (entity.field_70170_p.field_72995_K || !Back2Future.enableFrostWalker)
      return; 
    ItemStack boots = entity.func_71124_b(1);
    int level = 0;
    if ((level = EnchantmentHelper.func_77506_a(frostWalker.field_77352_x, boots)) > 0 && 
      entity.field_70122_E) {
      int x = (int)entity.field_70165_t;
      int y = (int)entity.field_70163_u;
      int z = (int)entity.field_70161_v;
      if (!WorldGuardUtils.checkFlag(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, DefaultFlag.BLOCK_BREAK, true))
        return; 
      int radius = 1 + level;
      for (int i = -radius; i <= radius; i++) {
        for (int j = -radius; j <= radius; j++) {
          Block block = entity.field_70170_p.func_147439_a(x + i, y - 1, z + j);
          if ((block == Blocks.field_150355_j || block == Blocks.field_150358_i) && !(block instanceof net.minecraft.block.BlockDynamicLiquid) && entity.field_70170_p.func_72805_g(x + i, y - 1, z + j) == 0)
            entity.field_70170_p.func_147449_b(x + i, y - 1, z + j, ModBlocks.frosted_ice); 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\ModEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */