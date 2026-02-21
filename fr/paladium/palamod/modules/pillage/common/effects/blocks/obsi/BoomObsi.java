package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.utils.PPillageUtils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BoomObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity placer) {
    if (PPillage.debugMode)
      System.out.println("[BoomObsi] Apply effect at " + x + ", " + y + ", " + z + " caused by " + placer); 
    double range = 1.5D;
    PPillageUtils.createExplosion(world, x, y, z, 0.0F, true);
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 1.5D, y - 1.5D, z - 1.5D, x + 1.5D, y + 1.5D, z + 1.5D);
    List<EntityLivingBase> entityList = world.func_72872_a(EntityLivingBase.class, aabb);
    entityList.forEach(entity -> {
          if (entity instanceof net.minecraft.entity.player.EntityPlayer)
            return; 
          if (PPillage.debugMode)
            System.out.println("[BoomObsi] Apply effect to " + entity.func_70005_c_()); 
          entity.func_70097_a(DamageSource.field_76377_j, 17.5F);
        });
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack(Items.field_151016_H, 1), new ItemStack(Blocks.field_150343_Z, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1), new Object[] { new ItemStack(Items.field_151016_H, 1), new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) });
    return this;
  }
  
  public String getName() {
    return "boom_obsi";
  }
  
  public String getDescription() {
    return "Génère une explosion qui affecte les entités aux alentours";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.BOOM_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\BoomObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */