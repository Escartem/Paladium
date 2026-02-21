package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class PoisonObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    double range = 3.5D;
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 3.5D, y - 3.5D, z - 3.5D, x + 3.5D, y + 3.5D, z + 3.5D);
    List<EntityLivingBase> entityList = world.func_72872_a(EntityLivingBase.class, aabb);
    int initialValue = 80;
    entityList.forEach(entityLivingBase -> entityLivingBase.func_70690_d(new PotionEffect(Potion.field_76436_u.func_76396_c(), 80, 2, true)));
  }
  
  public ObjectEffect registerRecipes(Block block) {
    ItemStack poison1 = new ItemStack((Item)Items.field_151068_bn, 1, 16388);
    ItemStack poison2 = new ItemStack((Item)Items.field_151068_bn, 1, 16420);
    ItemStack poison3 = new ItemStack((Item)Items.field_151068_bn, 1, 16452);
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('Y'), poison1 });
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('Y'), poison2 });
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('Y'), poison3 });
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN, 
          Character.valueOf('Y'), poison1 });
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN, 
          Character.valueOf('Y'), poison2 });
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('X'), BlocksRegister.HARDENED_OBSIDIAN, 
          Character.valueOf('Y'), poison3 });
    return this;
  }
  
  public String getName() {
    return "poison_obsi";
  }
  
  public String getDescription() {
    return "Donne un effet de Poison 2 pendant 5 secondes";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.POISON_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\PoisonObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */