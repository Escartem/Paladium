package fr.paladium.palamod.modules.luckyblock.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class GuiButtonClipboard extends GuiButton {
  private boolean centered;
  
  public GuiButtonClipboard(int par1, int par2, int par3, int par4, int par5, String par6, boolean center) {
    super(par1, par2, par3, par4, par5, par6);
    this.centered = center;
  }
  
  public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
    if (this.field_146124_l) {
      FontRenderer fontrenderer = par1Minecraft.field_71466_p;
      int l = 14737632;
      if (!this.field_146124_l) {
        l = -6250336;
      } else if (this.field_146123_n) {
        l = 16777120;
      } 
      if (!this.centered) {
        float scalediff = 0.8F;
        float widthscaled = (this.field_146128_h + this.field_146120_f / 2 - 54) * 1.0F / scalediff;
        int widthd = (int)widthscaled;
        float heightscaled = (this.field_146129_i + (this.field_146121_g - 8) / 2 + 3) * 1.0F / scalediff;
        int heightd = (int)heightscaled;
        GL11.glPushMatrix();
        GL11.glScalef(scalediff, scalediff, scalediff);
        fontrenderer.func_85187_a(this.field_146126_j, widthd, heightd, 0, false);
        GL11.glPopMatrix();
      } else {
        float scalediff = 0.8F;
        float widthscaled = ((this.field_146128_h + this.field_146120_f / 2) - (this.field_146126_j.length() / 2) * 5.0F * scalediff - 10.0F) * 1.0F / scalediff;
        int widthd = (int)widthscaled;
        float heightscaled = (this.field_146129_i + (this.field_146121_g - 8) / 2 + 2) * 1.0F / scalediff;
        int heightd = (int)heightscaled;
        GL11.glPushMatrix();
        GL11.glScalef(scalediff, scalediff, scalediff);
        fontrenderer.func_85187_a(this.field_146126_j, widthd, heightd, 0, false);
        GL11.glPopMatrix();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiButtonClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */