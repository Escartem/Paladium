package fr.paladium.palamod.modules.back2future.items;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class ChorusFruit extends ItemFood implements IConfigurable {
  public ChorusFruit() {
    super(4, 0.3F, false);
    func_77848_i();
    func_111206_d("chorus_fruit");
    func_77655_b(Utils.getUnlocalisedName("chorus_fruit"));
    func_77637_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    ItemStack result = super.func_77654_b(stack, world, player);
    return result;
  }
  
  private static boolean teleportTo(EntityLivingBase entity, double xx, double yy, double zz) {
    EnderTeleportEvent event = new EnderTeleportEvent(entity, xx, yy, zz, 0.0F);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return false; 
    double d3 = entity.field_70165_t;
    double d4 = entity.field_70163_u;
    double d5 = entity.field_70161_v;
    entity.field_70165_t = event.targetX;
    entity.field_70163_u = event.targetY;
    entity.field_70161_v = event.targetZ;
    boolean flag = false;
    int i = MathHelper.func_76128_c(entity.field_70165_t);
    int j = MathHelper.func_76128_c(entity.field_70163_u);
    int k = MathHelper.func_76128_c(entity.field_70161_v);
    if (entity.field_70170_p.func_72899_e(i, j, k)) {
      boolean flag1 = false;
      while (!flag1 && j > 0) {
        Block block = entity.field_70170_p.func_147439_a(i, j - 1, k);
        if (block.func_149688_o().func_76230_c()) {
          flag1 = true;
          continue;
        } 
        entity.field_70163_u--;
        j--;
      } 
      if (flag1) {
        entity.func_70634_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
        if (entity.field_70170_p.func_72945_a((Entity)entity, entity.field_70121_D).isEmpty() && 
          !entity.field_70170_p.func_72953_d(entity.field_70121_D))
          flag = true; 
      } 
    } 
    if (!flag) {
      entity.func_70107_b(d3, d4, d5);
      return false;
    } 
    short short1 = 128;
    for (int l = 0; l < short1; l++) {
      double d6 = l / (short1 - 1.0D);
      float f = (entity.func_70681_au().nextFloat() - 0.5F) * 0.2F;
      float f1 = (entity.func_70681_au().nextFloat() - 0.5F) * 0.2F;
      float f2 = (entity.func_70681_au().nextFloat() - 0.5F) * 0.2F;
      double d7 = d3 + (entity.field_70165_t - d3) * d6 + (entity.func_70681_au().nextDouble() - 0.5D) * entity.field_70130_N * 2.0D;
      double d8 = d4 + (entity.field_70163_u - d4) * d6 + entity.func_70681_au().nextDouble() * entity.field_70131_O;
      double d9 = d5 + (entity.field_70161_v - d5) * d6 + (entity.func_70681_au().nextDouble() - 0.5D) * entity.field_70130_N * 2.0D;
      entity.field_70170_p.func_72869_a("portal", d7, d8, d9, f, f1, f2);
    } 
    entity.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
    entity.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\ChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */