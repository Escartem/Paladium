package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemDutchBoat extends ItemBoat {
  public static final String NAME = "dutch_boat";
  
  public static final String DESCRIPTION = "Bateau légèrement plus rapide qu'un bateau en bambou. Il y a volant dans le nom, cela ne doit pas être un hasard...";
  
  public ItemDutchBoat() {
    this.field_77777_bU = 1;
    func_77655_b("dutch_boat");
    func_111206_d("palamod:dutch_boat");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    float f = 1.0F;
    float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
    float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
    double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
    double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f + 1.62D - player.field_70129_M;
    double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
    Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
    float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
    float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
    float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
    float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
    float f7 = f4 * f5;
    float f8 = f3 * f5;
    double d3 = 5.0D;
    Vec3 vec31 = vec3.func_72441_c(f7 * d3, f6 * d3, f8 * d3);
    MovingObjectPosition movingobjectposition = world.func_72901_a(vec3, vec31, true);
    if (movingobjectposition == null)
      return item; 
    Vec3 vec32 = player.func_70676_i(f);
    boolean flag = false;
    float f9 = 1.0F;
    List<?> list = world.func_72839_b((Entity)player, player.field_70121_D.func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b(f9, f9, f9));
    int i;
    for (i = 0; i < list.size(); i++) {
      Entity entity = (Entity)list.get(i);
      if (entity.func_70067_L()) {
        float f10 = entity.func_70111_Y();
        AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f10, f10, f10);
        if (axisalignedbb.func_72318_a(vec3))
          flag = true; 
      } 
    } 
    if (flag)
      return item; 
    if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      i = movingobjectposition.field_72311_b;
      int j = movingobjectposition.field_72312_c;
      int k = movingobjectposition.field_72309_d;
      if (world.func_147439_a(i, j, k) == Blocks.field_150431_aC)
        j--; 
      EntityDutchBoat entityboat = new EntityDutchBoat(world, (i + 0.5F), (j + 1.0F), (k + 0.5F));
      entityboat.field_70177_z = (((MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3) - 1) * 90);
      if (!world.func_72945_a((Entity)entityboat, entityboat.field_70121_D.func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty())
        return item; 
      if (!world.field_72995_K)
        world.func_72838_d((Entity)entityboat); 
      if (!player.field_71075_bZ.field_75098_d)
        item.field_77994_a--; 
    } 
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemDutchBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */