package fr.paladium.palahologram.common.hologram.element.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palahologram.client.ClientProxy;
import fr.paladium.palahologram.common.hologram.element.HologramElement;
import fr.paladium.palaplaceholder.common.manager.PlaceholderManager;
import fr.paladium.translate.common.controller.TranslateController;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFont;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFontRenderer;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class TextHologramElement extends HologramElement {
  public void setText(String text) {
    this.text = text;
  }
  
  public void setFontSize(float fontSize) {
    this.fontSize = fontSize;
  }
  
  public void setBackground(boolean background) {
    this.background = background;
  }
  
  private String text = "";
  
  public String getText() {
    return this.text;
  }
  
  private float fontSize = 16.0F;
  
  public float getFontSize() {
    return this.fontSize;
  }
  
  private boolean background = true;
  
  public boolean isBackground() {
    return this.background;
  }
  
  public TextHologramElement() {
    this("");
  }
  
  public TextHologramElement(String text) {
    this.text = text;
  }
  
  public TextHologramElement(String text, float fontSize, boolean background) {
    this.text = text;
    this.fontSize = fontSize;
    this.background = background;
  }
  
  public double getHeight() {
    return (MinecraftFontRenderer.getFontHeight() * 0.02666667F * this.fontSize / 16.0F);
  }
  
  public void draw() {
    float scale = 0.02666667F * this.fontSize / 16.0F;
    String transformedText = transformText(this.text);
    double width = TextInfo.create(MinecraftFont.MINECRAFT, MinecraftFontRenderer.getFontHeight(), Color.WHITE).getWidth(transformedText) / 2.0D;
    if (getAlignment() == Align.START) {
      GL11.glTranslated(-1.0D * scale, 0.0D, 0.0D);
    } else if (getAlignment() == Align.CENTER) {
      GL11.glTranslated(-0.5D * scale, 0.0D, 0.0D);
    } 
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glScalef(scale, scale, 0.0F);
    render(transformedText, width);
    GL11.glScalef(1.0F / scale, 1.0F / scale, 0.0F);
    GL11.glRotated(-180.0D, 0.0D, 0.0D, 1.0D);
  }
  
  private void render(String transformedText, double width) {
    if (this.background) {
      float upScale = 1.0F / 0.02666667F * this.fontSize / 16.0F;
      GL11.glDepthMask(false);
      switch (getAlignment()) {
        case START:
          DrawUtils.SHAPE.drawRect(-1.0D, 0.0D, width * 2.0D + 1.0D, getHeight() * upScale, Color.BLACK.copyAlpha(0.25F));
          break;
        case CENTER:
          DrawUtils.SHAPE.drawRect(-width + -1.0D, 0.0D, width * 2.0D + 1.0D, getHeight() * upScale, Color.BLACK.copyAlpha(0.25F));
          break;
        case END:
          DrawUtils.SHAPE.drawRect(-width * 2.0D + -1.0D, 0.0D, width * 2.0D + 1.0D, getHeight() * upScale, Color.BLACK.copyAlpha(0.25F));
          break;
      } 
      GL11.glDepthMask(true);
    } 
    DrawUtils.TEXT.drawText(0.0D, 1.0D, transformedText, TextInfo.create(MinecraftFont.MINECRAFT, MinecraftFontRenderer.getFontHeight(), Color.WHITE), getAlignment(), Align.START);
  }
  
  @SideOnly(Side.CLIENT)
  private String transformText(String text) {
    if (ClientProxy.TRANSLATE_LOADED)
      text = (String)TranslateController.getInstance().getDefaultTranslation().getTranslations().getOrDefault(text, text); 
    if (ClientProxy.PLACEHOLDER_LOADED)
      text = PlaceholderManager.inst().setPlaceholders((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, text); 
    return text.replace("&", "§");
  }
  
  public HologramElement read(NBTTagCompound nbt) {
    super.read(nbt);
    this.background = nbt.func_74767_n("background");
    this.text = nbt.func_74779_i("text");
    this.fontSize = nbt.func_74760_g("fontSize");
    return this;
  }
  
  public void write(NBTTagCompound nbt) {
    super.write(nbt);
    nbt.func_74757_a("background", this.background);
    nbt.func_74778_a("text", this.text);
    nbt.func_74776_a("fontSize", this.fontSize);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\common\hologram\element\impl\TextHologramElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */