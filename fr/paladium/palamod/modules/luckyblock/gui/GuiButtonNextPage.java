package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
class GuiButtonNextPage extends GuiButton {
  private final boolean nextPage;
  
  public GuiButtonNextPage(int par1, int par2, int par3, boolean par4) {
    super(par1, par2, par3, 23, 13, "");
    this.nextPage = par4;
  }
  
  public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
    if (this.field_146125_m) {
      boolean var4 = (par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      par1Minecraft.func_110434_K().func_110577_a(new ResourceLocation("textures/gui/book.png"));
      int var5 = 0;
      int var6 = 192;
      if (var4)
        var5 += 23; 
      if (!this.nextPage)
        var6 += 13; 
      func_73729_b(this.field_146128_h, this.field_146129_i, var5, var6, 23, 13);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiButtonNextPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */