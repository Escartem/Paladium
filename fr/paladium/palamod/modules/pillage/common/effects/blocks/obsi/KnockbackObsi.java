package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class KnockbackObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    double radius = 3.5D;
    double offset = 3.5D;
    float str = 2.0F;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(x - 3.5D, y - 3.5D, z - 3.5D, x + 3.5D, y + 3.5D, z + 3.5D);
    List<?> entities = world.func_72872_a(EntityLivingBase.class, scanAbove);
    for (Object obj : entities) {
      if (obj instanceof EntityLivingBase) {
        EntityLivingBase e = (EntityLivingBase)obj;
        e.field_70160_al = true;
        e.field_70159_w = (e.field_70165_t - x > 0.0D) ? Math.min(e.field_70159_w + 2.0D, 2.0D) : Math.max(e.field_70159_w - 2.0D, -2.0D);
        e.field_70181_x = Math.min(e.field_70181_x + 2.0D, 2.0D);
        e.field_70179_y = (e.field_70161_v - z > 0.0D) ? Math.min(e.field_70179_y + 2.0D, 2.0D) : Math.max(e.field_70179_y - 2.0D, -2.0D);
        if (e instanceof EntityPlayerMP) {
          EntityPlayerMP p = (EntityPlayerMP)e;
          p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)e));
        } 
      } 
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe(new ItemStack(block, 4), new Object[] { "OOO", "OKO", "OOO", Character.valueOf('K'), ItemsRegister.ORB_KNOCKBACK, Character.valueOf('O'), Blocks.field_150343_Z });
    return this;
  }
  
  public String getName() {
    return "knockback_obsi";
  }
  
  public String getDescription() {
    return "Obsidienne qui repousse les joueurs quand celle-ci est cassée";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.KNOCKBACK_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\KnockbackObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */