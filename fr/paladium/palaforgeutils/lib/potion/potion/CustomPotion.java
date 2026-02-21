package fr.paladium.palaforgeutils.lib.potion.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class CustomPotion extends Potion {
  private APotion potion;
  
  public CustomPotion(int id, APotion potion) {
    super(id, potion.isBad(), potion.getColor().getRGB());
    this.potion = potion;
    func_76390_b("potion." + potion.getId() + ".name");
  }
  
  public boolean func_76400_d() {
    return false;
  }
  
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
    if (this.potion.getIcon() == null && this.potion.getIconItem() == null)
      return; 
    if (this.potion.getIcon() != null) {
      drawImageTransparent((x + 6), (y + 7), this.potion.getIcon(), 18.0D, 18.0D);
    } else if (this.potion.getIconItem() != null) {
      drawItemStack(this.potion.getIconItem(), (x + 6), (y + 7));
    } 
  }
  
  private void drawImageTransparent(double x, double y, ResourceLocation image, double width, double height) {
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(image);
    GL11.glColor4d(255.0D, 255.0D, 255.0D, 255.0D);
    Tessellator tess = Tessellator.field_78398_a;
    GL11.glEnable(2832);
    GL11.glEnable(3042);
    GL14.glBlendEquation(32774);
    GL11.glBlendFunc(770, 771);
    tess.func_78369_a(255.0F, 255.0F, 255.0F, 255.0F);
    tess.func_78382_b();
    tess.func_78374_a(x, y + height, 0.0D, 0.0D, 1.0D);
    tess.func_78374_a(x + width, y + height, 0.0D, 1.0D, 1.0D);
    tess.func_78374_a(x + width, y, 0.0D, 1.0D, 0.0D);
    tess.func_78374_a(x, y, 0.0D, 0.0D, 0.0D);
    tess.func_78381_a();
    GL11.glDisable(2832);
    GL11.glDisable(3042);
  }
  
  private void drawItemStack(ItemStack itemstack, double posX, double posY) {
    if (itemstack == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, 0.0D);
    RenderItem itemRenderer = new RenderItem();
    itemRenderer.field_77024_a = true;
    itemRenderer.func_82406_b((Minecraft.func_71410_x()).field_71466_p, Minecraft.func_71410_x().func_110434_K(), itemstack, 0, 0);
    GL11.glDisable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\potion\CustomPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */