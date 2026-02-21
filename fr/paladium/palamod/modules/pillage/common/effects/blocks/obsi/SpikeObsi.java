package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpikeObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    BlockSpike blockSpike;
    if (PPillage.debugMode)
      System.out.println("[SpikeObsi] Apply effect at " + x + ", " + y + ", " + z + " caused by " + entity); 
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 2.0D, y - 2.0D, z - 2.0D, x + 2.0D, y + 2.0D, z + 2.0D);
    List<EntityLivingBase> entityList = world.func_72872_a(EntityLivingBase.class, aabb);
    Block blockToPlace = Blocks.field_150343_Z;
    BlockPos pos = new BlockPos(x, y, z);
    world.func_147468_f(pos.getX(), pos.getY(), pos.getZ());
    switch (metadata) {
      case 0:
        blockSpike = BlocksRegister.SPIKE_WOOD;
        break;
      case 1:
        blockSpike = BlocksRegister.SPIKE_IRON;
        break;
      case 2:
        blockSpike = BlocksRegister.SPIKE_GOLD;
        break;
      case 3:
        blockSpike = BlocksRegister.SPIKE_DIAMOND;
        break;
      case 4:
        blockSpike = BlocksRegister.SPIKE_AMETHYST;
        break;
      case 5:
        blockSpike = BlocksRegister.SPIKE_TITANE;
        break;
      case 6:
        blockSpike = BlocksRegister.SPIKE_PALADIUM;
        break;
    } 
    if (!entityList.isEmpty()) {
      EntityLivingBase entityLivingBase = entityList.get(0);
      int heading = MathHelper.func_76128_c((entityLivingBase.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
      switch (heading) {
        case 0:
          world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), (Block)blockSpike, 9, 2);
          break;
        case 1:
          world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), (Block)blockSpike, 4, 2);
          break;
        case 2:
          world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), (Block)blockSpike, 2, 2);
          break;
        case 3:
          world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), (Block)blockSpike, 5, 2);
          break;
      } 
    } else {
      world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), (Block)blockSpike, 9, 2);
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 0), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_WOOD, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 1), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_IRON, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 2), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_GOLD, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 3), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 4), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 5), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_TITANE, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 6), new Object[] { new ItemStack(Blocks.field_150343_Z, 1), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 0), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_WOOD, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 1), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_IRON, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 2), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_GOLD, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 3), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 4), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 5), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_TITANE, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack(block, 1, 6), new Object[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1), new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM, 1) });
    return this;
  }
  
  public String getName() {
    return "spike_obsi";
  }
  
  public String getDescription() {
    return "Remplace l’obsidienne par une spike de son type";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.SPIKE_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\SpikeObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */