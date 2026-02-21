package fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpongeTNT implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    int posX = MathHelper.func_76128_c(x);
    int posY = MathHelper.func_76128_c(y);
    int posZ = MathHelper.func_76128_c(z);
    int range = 2;
    for (int i = -range; i <= range; i++) {
      for (int j = -range; j <= range; j++) {
        for (int k = -range; k <= range; k++) {
          Block block = world.func_147439_a(posX + i, posY + j, posZ + k);
          if (block.func_149688_o().func_76224_d())
            world.func_147468_f(posX + i, posY + j, posZ + k); 
        } 
      } 
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { "XXX", "ZYZ", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.EXTRAPOLATED_BUCKET, 
          Character.valueOf('Z'), PPRegisterer.PPBlocks.COMPRESSED_SPONGE });
    return this;
  }
  
  public String getName() {
    return "sponge_tnt";
  }
  
  public String getDescription() {
    return "TNT qui absorbe l'eau dans un rayon de 4*4*4";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.SPONGE_TNT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\tnt\SpongeTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */