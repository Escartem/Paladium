package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.awt.Color;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSmokeBomb extends Item implements ITooltipWiki {
  private int color;
  
  public ItemSmokeBomb(int color, String colorName) {
    this.color = color;
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("smokebomb_" + colorName);
    func_111206_d("palamod:smokebomb");
    func_77625_d(16);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
    return this.color;
  }
  
  public boolean func_77648_a(ItemStack item, final EntityPlayer player, final World world, int x, int y, int z, int side, float px, float py, float pz) {
    if (!world.field_72995_K)
      return false; 
    final Color c = new Color(this.color);
    final LuckyTask task = new LuckyTask();
    task
      
      .id = PaladiumScheduler.INSTANCE.runTaskTimer(new Runnable() {
          long times = 0L;
          
          double offx = 0.0D;
          
          double offy = 0.0D;
          
          Vec3 loc = null;
          
          public void run() {
            this.times++;
            if (this.times > 120L) {
              PaladiumScheduler.INSTANCE.cancelTask(task.id);
              return;
            } 
            int pix = (int)player.field_70165_t;
            int piy = (int)player.field_70163_u;
            int piz = (int)player.field_70161_v;
            if (this.times < 40L) {
              double dy;
              for (dy = piy; dy < piy + 0.3D; dy += 0.1D) {
                double dx;
                for (dx = pix - 0.3D; dx < pix + 0.3D; dx += 0.1D) {
                  this.loc = Vec3.func_72443_a(dx, dy + this.offy, piz);
                  world.func_72869_a("reddust", dx, dy + this.offy, piz, c.getRed() / 255.0D, c.getGreen() / 255.0D, c.getBlue() / 255.0D);
                } 
              } 
              this.offy += 0.04D;
            } else if (this.times < 65L) {
              if (this.times == 40L)
                this.offy = 0.0D; 
              double dx;
              for (dx = -0.5D; dx < 0.5D; dx += 0.1D) {
                double dz;
                for (dz = -0.5D; dz < 0.5D; dz += 0.1D)
                  world.func_72869_a("reddust", this.loc.field_72450_a + this.offx + dx, this.offy + this.loc.field_72448_b, this.loc.field_72449_c + dz, c.getRed() / 255.0D, c.getGreen() / 255.0D, c.getBlue() / 255.0D); 
              } 
              this.offx += 0.6D;
              this.offy += 0.8D;
            } 
          }
        }1L, 1L).getTaskId();
    return true;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List list, boolean flag) {
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSmokeBomb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */