package fr.paladium.palajobs.client.ui.lvltokens.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardRarity;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ButtonJobRewardTokenSelector extends AClickableNode {
  public void setReward(LvlTokenReward reward) {
    this.reward = reward;
  }
  
  public void setStroke(double stroke) {
    this.stroke = stroke;
  }
  
  public void setSelected(boolean selected) {
    this.selected = selected;
  }
  
  public void setScale(float scale) {
    this.scale = scale;
  }
  
  private static final ResourceLocation EPIC = new ResourceLocation("palajobs", "textures/gui/tokens/epic.png");
  
  private static final ResourceLocation RARE = new ResourceLocation("palajobs", "textures/gui/tokens/rare.png");
  
  private static final ResourceLocation COMMON = new ResourceLocation("palajobs", "textures/gui/tokens/common.png");
  
  private static final ResourceLocation MONEY = new ResourceLocation("palajobs", "textures/gui/tokens/money.png");
  
  private static final ResourceLocation XPJOBS = new ResourceLocation("palajobs", "textures/gui/tokens/xpjobs.png");
  
  private LvlTokenReward reward;
  
  private double stroke;
  
  public boolean selected;
  
  public LvlTokenReward getReward() {
    return this.reward;
  }
  
  public double getStroke() {
    return this.stroke;
  }
  
  public boolean isSelected() {
    return this.selected;
  }
  
  private float scale = 1.0F;
  
  public float getScale() {
    return this.scale;
  }
  
  public ButtonJobRewardTokenSelector(LvlTokenReward reward, double x, double y, double width, double height, double stroke) {
    super(x, y, width, height);
    this.stroke = stroke;
    this.reward = reward;
    if (reward.itemStack != null) {
      if (reward.itemStack.func_77973_b() != null) {
        setHovers(reward.itemStack.func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, false));
      } else {
        addHover(reward.name);
      } 
      setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    double drawX = this.x;
    if (drawX < this.ui.width(20.0F))
      drawX = this.ui.width(20.0F); 
    if (drawX > this.ui.width(73.0F))
      drawX = this.ui.width(73.0F); 
    GL11.glPushMatrix();
    GL11.glTranslated(drawX + this.width / 2.0D, this.y + this.height / 2.0D, 0.0D);
    GL11.glScaled(this.scale, this.scale, 1.0D);
    GL11.glTranslated(-(drawX + this.width / 2.0D), -(this.y + this.height / 2.0D), 0.0D);
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Color.decode("#1a1a1f").darker(0.3F));
    Color color = Color.white;
    if (this.reward.rarity == EnumLvlTokenRewardRarity.COMMON) {
      color = Color.decode("#51e249");
    } else if (this.reward.rarity == EnumLvlTokenRewardRarity.EPIC) {
      color = Color.decode("#8e3de3");
    } else if (this.reward.rarity == EnumLvlTokenRewardRarity.RARE) {
      color = Color.decode("#419ce3");
    } 
    if (this.selected) {
      GuiUtils.drawRect(this.x, this.y - this.stroke, this.x + this.width, this.y, color);
      GuiUtils.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + this.stroke, color);
      GuiUtils.drawRect(this.x - this.stroke, this.y, this.x, this.y + this.height, color);
      GuiUtils.drawRect(this.x + this.width, this.y, this.x + this.width + this.stroke * 1.2000000476837158D, this.y + this.height, color);
    } 
    if (!this.selected)
      GuiUtils.drawRect(this.x, this.y + this.height - 6.0D, this.x + this.width, this.y + this.height + this.stroke - 4.0D, color); 
    drawContent(mc, mouseX, mouseY);
    GL11.glPopMatrix();
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), 30);
    ResourceLocation image = COMMON;
    if (this.reward.rarity == EnumLvlTokenRewardRarity.COMMON) {
      image = COMMON;
    } else if (this.reward.rarity == EnumLvlTokenRewardRarity.RARE) {
      image = RARE;
    } else if (this.reward.rarity == EnumLvlTokenRewardRarity.EPIC) {
      image = EPIC;
    } 
    if (this.reward.category == EnumLvlTokenRewardCategory.ITEM) {
      GL11.glPushMatrix();
      if (this.reward.itemStack.func_77973_b() != null)
        GuiUtils.renderScaledItemStackIntoGUI(this.reward.itemStack, this.x + width(35), this.y + height(35), width(2.0F)); 
      GL11.glDisable(2929);
      GL11.glPopMatrix();
    } else {
      ResourceLocation icon = XPJOBS;
      if (this.reward.category == EnumLvlTokenRewardCategory.XP_JOBS) {
        icon = XPJOBS;
      } else if (this.reward.category == EnumLvlTokenRewardCategory.MONEY) {
        icon = MONEY;
      } 
      GuiUtils.drawImageTransparent(this.x + width(35), this.y + height(35), icon, width(30), width(30));
    } 
    GuiUtils.drawImageTransparent(this.x + width(83), this.y + height(2), image, width(15), width(15));
    if (this.selected) {
      Color color = Color.decode("#1a1a1f");
      Color darkerColor = color.darker(1.0F);
      Color darkerColorWithAlpha = new Color(darkerColor.getRed(), darkerColor.getGreen(), darkerColor.getBlue(), 0.9F);
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, darkerColorWithAlpha);
      GuiUtils.drawCenteredStringWithCustomFont(mc, "OBTIENT", this.x + this.width / 2.0D, this.y + this.height / 2.0D - (fontHeight / 2) - 20.0D, Color.WHITE, Fonts.PIXEL_NES.getFont(), 50, true, Color.BLACK);
      String desc = "";
      if (this.reward.category == EnumLvlTokenRewardCategory.XP_JOBS) {
        desc = this.reward.quantity + "xp " + this.reward.job.name();
      } else if (this.reward.category == EnumLvlTokenRewardCategory.MONEY) {
        desc = this.reward.quantity + "$";
      } else if (this.reward.category == EnumLvlTokenRewardCategory.ITEM) {
        if (this.reward.itemStack.func_77973_b() != null) {
          desc = this.reward.quantity + "x " + this.reward.itemStack.func_82833_r();
        } else {
          desc = this.reward.name;
        } 
      } 
      if (desc.length() > 16)
        desc = desc.substring(0, 16) + "..."; 
      GuiUtils.drawCenteredStringWithCustomFont(mc, desc, this.x + this.width / 2.0D, this.y + this.height / 2.0D - (fontHeight / 2) + 10.0D, Color.WHITE, Fonts.PIXEL_NES.getFont(), 50, true, Color.BLACK);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\nodes\ButtonJobRewardTokenSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */