package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palawither.common.entity.targetable.IWitherCoordinateTargetable;
import fr.paladium.palawither.common.utils.WitherUtils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class AntiAggroObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    double range = 3.5D;
    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 3.5D, y - 3.5D, z - 3.5D, x + 3.5D, y + 3.5D, z + 3.5D);
    List<EntityLivingBase> entityList = world.func_72872_a(EntityLivingBase.class, aabb);
    entityList.forEach(entityLivingBase -> {
          if (WitherUtils.isWither((Entity)entityLivingBase)) {
            EntityMob mob = (EntityMob)entityLivingBase;
            mob.func_70624_b(null);
            mob.func_70784_b(null);
            mob.func_70604_c(null);
            if (mob instanceof IWitherCoordinateTargetable)
              ((IWitherCoordinateTargetable)mob).setTargetLocation(null); 
          } 
        });
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 8), new Object[] { "XXX", "XNX", "XXX", Character.valueOf('X'), Blocks.field_150343_Z, Character.valueOf('N'), Items.field_151156_bN });
    return this;
  }
  
  public String getName() {
    return "anti_aggro_obsi";
  }
  
  public String getDescription() {
    return "Tout wither qui casse cette obsidienne perd l'aggro qu'il a.";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.ANTI_AGGRO_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\AntiAggroObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */