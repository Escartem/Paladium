package fr.paladium.palamod.modules.spellsv2.gui.buttons;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.client.utils.HoverUtils;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTexturedButton extends GuiButton {
  public ResourceLocation texture;
  
  public ResourceLocation hoveredTexture;
  
  public ResourceLocation disabledTexture;
  
  private boolean pressed;
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture) {
    super(id, x, y, width, height, "");
    this.texture = texture;
  }
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture, String tooltip) {
    super(id, x, y, width, height, tooltip);
    this.texture = texture;
  }
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture, ResourceLocation hoveredTexture) {
    super(id, x, y, width, height, "");
    this.texture = texture;
    this.hoveredTexture = hoveredTexture;
  }
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture, ResourceLocation hoveredTexture, ResourceLocation disabled) {
    super(id, x, y, width, height, "");
    this.texture = texture;
    this.hoveredTexture = hoveredTexture;
    this.disabledTexture = disabled;
  }
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture, ResourceLocation hoveredTexture, String tooltip) {
    super(id, x, y, width, height, tooltip);
    this.texture = texture;
    this.hoveredTexture = hoveredTexture;
  }
  
  public GuiTexturedButton(int id, int x, int y, int width, int height, ResourceLocation texture, ResourceLocation hoveredTexture, ResourceLocation disabled, String tooltip) {
    super(id, x, y, width, height, tooltip);
    this.texture = texture;
    this.hoveredTexture = hoveredTexture;
    this.disabledTexture = disabled;
  }
  
  public void func_146112_a(Minecraft mc, int x, int y) {
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    if (this.field_146124_l || this.disabledTexture == null) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.field_146123_n = isHover(x, y);
      if (!this.field_146124_l) {
        GuiUtils.greyLoad(true);
        mc.func_110434_K().func_110577_a(this.texture);
        Gui.func_146110_a(this.field_146128_h, this.field_146129_i, 0.0F, 0.0F, this.field_146120_f, this.field_146121_g, this.field_146120_f, this.field_146121_g);
        GuiUtils.greyUnload();
        if (this.field_146123_n && this.field_146126_j.length() > 0)
          HoverUtils.drawHoveringText(Arrays.asList(new String[] { this.field_146126_j }, ), x + 2, y - 2, 99.0F, mc.field_71466_p); 
        return;
      } 
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      if (this.hoveredTexture != null) {
        mc.func_110434_K().func_110577_a(this.field_146123_n ? this.hoveredTexture : this.texture);
      } else {
        mc.func_110434_K().func_110577_a(this.texture);
        if (this.field_146123_n)
          GL11.glColor3f(0.7F, 0.7F, 0.7F); 
      } 
      if (this.pressed)
        GL11.glColor3f(0.5F, 0.5F, 0.5F); 
      Gui.func_146110_a(this.field_146128_h, this.field_146129_i, 0.0F, 0.0F, this.field_146120_f, this.field_146121_g, this.field_146120_f, this.field_146121_g);
      if (this.field_146123_n && this.field_146126_j.length() > 0) {
        GL11.glPushMatrix();
        HoverUtils.drawHoveringText(Arrays.asList(new String[] { this.field_146126_j }, ), x + 2, y - 2, 99.0F, mc.field_71466_p);
        GL11.glPopMatrix();
      } 
    } else {
      this.field_146123_n = isHover(x, y);
      GuiUtils.drawImageTransparent(this.field_146128_h, this.field_146129_i, this.disabledTexture, this.field_146120_f, this.field_146121_g);
      if (this.field_146123_n && this.field_146126_j.length() > 0) {
        GL11.glPushMatrix();
        HoverUtils.drawHoveringText(Arrays.asList(new String[] { this.field_146126_j }, ), x + 2, y - 2, 99.0F, mc.field_71466_p);
        GL11.glPopMatrix();
      } 
    } 
  }
  
  public boolean isHover(int x, int y) {
    return (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);
  }
  
  public boolean func_146116_c(Minecraft mc, int x, int y) {
    if (this.field_146124_l && this.field_146125_m && x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g)
      this.pressed = true; 
    return super.func_146116_c(mc, x, y);
  }
  
  public void func_146118_a(int x, int y) {
    this.pressed = false;
    super.func_146118_a(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\buttons\GuiTexturedButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */