package fr.paladium.palamod.modules.pillage.common.effects.blocks.tnt;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class WitherTNT implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    int count = (world.field_73012_v.nextFloat() < 0.1D) ? 2 : 1;
    for (int i = 0; i < count; i++)
      spawnWither(world, x, y, z); 
  }
  
  private void spawnWither(World world, double x, double y, double z) {
    if (world.field_73013_u != EnumDifficulty.PEACEFUL) {
      EntityWither entitywither = new EntityWither(world);
      entitywither.func_70012_b(x, y, z, 0.0F, 0.0F);
      entitywither.func_82206_m();
      if (!world.field_72995_K) {
        List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, entitywither.field_70121_D
            .func_72314_b(50.0D, 50.0D, 50.0D));
        players.forEach(player -> {
              player.func_71029_a((StatBase)AchievementList.field_150963_I);
              StatsEep eep = StatsEep.get((Entity)player);
              eep.increaseWitherSpawned();
            });
      } 
      world.func_72838_d((Entity)entitywither);
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 1), new Object[] { "ZZZ", "XYX", "XXX", Character.valueOf('Z'), new ItemStack(Items.field_151144_bL, 1, 1), 
          
          Character.valueOf('X'), Blocks.field_150335_W, Character.valueOf('Y'), ItemsRegister.PALADIUM_CORE });
    return this;
  }
  
  public String getName() {
    return "wither_tnt";
  }
  
  public String getDescription() {
    return "TNT qui invoque un wither avec 1 chance sur 100 d’en faire pop un deuxième";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.WITHER_TNT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\tnt\WitherTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */