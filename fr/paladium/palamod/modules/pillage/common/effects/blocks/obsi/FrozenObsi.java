package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class FrozenObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    double radius = 3.5D;
    double offset = 3.5D;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - 3.5D, y - 3.5D, z - 3.5D, x + 3.5D, y + 3.5D, z + 3.5D);
    List<?> entities = world.func_72872_a(EntityLivingBase.class, scanAbove);
    for (Object obj : entities) {
      if (obj instanceof EntityLivingBase) {
        EntityLivingBase e = (EntityLivingBase)obj;
        IAttributeInstance attribute = e.func_110148_a(SharedMonsterAttributes.field_111263_d);
        double speed = (attribute == null) ? 0.0D : attribute.func_111126_e();
        if (attribute != null && speed <= 0.0D)
          continue; 
        float yaw = e.field_70177_z;
        float pitch = e.field_70125_A;
        e.field_70159_w = 0.0D;
        e.field_70181_x = 0.0D;
        e.field_70179_y = 0.0D;
        if (attribute == null) {
          e.func_70634_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
        } else {
          attribute.func_111128_a(0.0D);
        } 
        e.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 60, 10));
        if (!(e instanceof net.minecraft.entity.player.EntityPlayer)) {
          long start = System.currentTimeMillis();
          (new Thread(() -> {
                while (System.currentTimeMillis() - start < 4000L) {
                  e.field_70159_w = 0.0D;
                  e.field_70181_x = 0.0D;
                  e.field_70179_y = 0.0D;
                  e.field_70177_z = yaw;
                  e.field_70125_A = pitch;
                  if (attribute == null) {
                    e.func_70634_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
                    continue;
                  } 
                  attribute.func_111128_a(0.0D);
                } 
                if (attribute != null)
                  attribute.func_111128_a(speed); 
              }"Thread/FrozenObsi-" + e
              
              .func_70005_c_() + "-" + e.func_145782_y())).start();
        } 
      } 
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 2), new Object[] { "IOI", "OIO", "IOI", Character.valueOf('I'), Blocks.field_150403_cj, Character.valueOf('O'), Blocks.field_150343_Z });
    return this;
  }
  
  public String getName() {
    return "frozen_obsi";
  }
  
  public String getDescription() {
    return "Obsidienne qui immobilise les entités quand celle-ci est cassée";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.FROZEN_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\FrozenObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */