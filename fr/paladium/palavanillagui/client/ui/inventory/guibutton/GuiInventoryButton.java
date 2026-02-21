package fr.paladium.palavanillagui.client.ui.inventory.guibutton;

import fr.paladium.palavanillagui.client.ui.inventory.UIInventory;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GuiInventoryButton extends GuiButton {
  private final RenderItem itemRenderer = new RenderItem();
  
  public GuiInventoryButton(int x, int y, String label) {
    super(2147483647, x, y, 20, 20, label);
  }
  
  public void func_146112_a(Minecraft mc, int p_146112_2_, int p_146112_3_) {
    if (this.field_146125_m) {
      FontRenderer fontrenderer = mc.field_71466_p;
      mc.func_110434_K().func_110577_a(GuiButton.field_146122_a);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146123_n = (p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g);
      int k = func_146114_a(this.field_146123_n);
      GL11.glEnable(3042);
      OpenGlHelper.func_148821_a(770, 771, 1, 0);
      GL11.glBlendFunc(770, 771);
      func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
      func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
      func_146119_b(mc, p_146112_2_, p_146112_3_);
      this.itemRenderer.func_77015_a(fontrenderer, mc.func_110434_K(), new ItemStack(Item.func_150898_a(Blocks.field_150477_bB)), this.field_146128_h + 2, this.field_146129_i + 1);
    } 
  }
  
  public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
    boolean inWindow = (this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
    if (inWindow)
      ZUI.open((UI)new UIInventory()); 
    return inWindow;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\inventory\guibutton\GuiInventoryButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */