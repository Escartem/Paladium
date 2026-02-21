package fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
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

public class EffectTNT implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity placer) {
    int range = 4;
    world.func_72876_a(placer, x, y, z, range, true);
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - range, y - range, z - range, x + range, y + range, z + range);
    List<EntityLivingBase> entityList = world.func_72872_a(EntityLivingBase.class, aabb);
    entityList.forEach(entity -> {
          entity.func_70690_d(new PotionEffect(Potion.field_76440_q.func_76396_c(), 60, 1, true));
          entity.func_70690_d(new PotionEffect(Potion.field_76436_u.func_76396_c(), 80, 1, true));
        });
  }
  
  public ObjectEffect registerRecipes(Block block) {
    ItemStack weakness1 = new ItemStack((Item)Items.field_151068_bn, 1, 16424);
    ItemStack weakness2 = new ItemStack((Item)Items.field_151068_bn, 1, 16456);
    ItemStack poison1 = new ItemStack((Item)Items.field_151068_bn, 1, 16388);
    ItemStack poison2 = new ItemStack((Item)Items.field_151068_bn, 1, 16420);
    ItemStack poison3 = new ItemStack((Item)Items.field_151068_bn, 1, 16452);
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison1, Character.valueOf('A'), 
          weakness1 });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison1, Character.valueOf('A'), 
          weakness2 });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison2, Character.valueOf('A'), 
          weakness1 });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison2, Character.valueOf('A'), 
          weakness2 });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison3, Character.valueOf('A'), 
          weakness1 });
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { 
          "XXX", "ZYA", "XXX", Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('Z'), poison3, Character.valueOf('A'), 
          weakness2 });
    return this;
  }
  
  public String getName() {
    return "effect_tnt";
  }
  
  public String getDescription() {
    return "TNT qui inflige des effets négatifs";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.EFFECT_TNT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\tnt\EffectTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */