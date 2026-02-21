package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palavanillagui.common.utils.TextureUtils;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class PotionEffectContainer extends Node {
  protected static final ResourceLocation INVENTORY = new ResourceLocation("textures/gui/container/inventory.png");
  
  private final Color backgroundColor = new Color(30, 30, 35);
  
  private final Color borderGrayColor = new Color(64, 64, 77);
  
  protected PotionEffectContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    List<PotionEffect> potions = new ArrayList<>((Minecraft.func_71410_x()).field_71439_g.func_70651_bq());
    if (potions.isEmpty())
      return; 
    int initialGap = 30;
    int height = 122;
    int gap = 30;
    int offset = 0;
    int maxHeight = potions.size() * 122 + (potions.size() - 1) * gap;
    while (maxHeight > getHeight()) {
      gap--;
      maxHeight = potions.size() * 122 + (potions.size() - 1) * gap;
    } 
    for (int i = 0; i < potions.size(); i++) {
      PotionEffect effect = potions.get(i);
      Potion potion = Potion.field_76425_a[effect.func_76456_a()];
      offset = gap * i + 122 * i;
      DrawUtils.SHAPE.drawRect(getX(), getY() + offset, getWidth(), 122.0D, this.backgroundColor);
      DrawUtils.SHAPE.drawBorder(getX() + 10.0D, getY() + 10.0D + offset, getX() + getWidth() - 10.0D, getY() + offset + 122.0D - 10.0D, this.borderGrayColor, 10.0D);
      DrawUtils.SHAPE.drawBorder(getX(), getY() + offset, getX() + getWidth(), getY() + offset + 122.0D, this.backgroundColor, 10.0D);
      GL11.glPushMatrix();
      GL11.glTranslated(-350.0D, -315.0D + 152.0D * i + ((gap != 30 && i > 0) ? (-(30 - gap) * i) : 0.0D), 0.0D);
      GL11.glScaled(3.5D, 3.5D, 0.0D);
      if (potion.func_76400_d()) {
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL14.glBlendEquation(32774);
        GL11.glBlendFunc(770, 771);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(INVENTORY);
        int iconIndex = potion.func_76392_e();
        TextureUtils.drawTexturedModalRect((int)getX() + 6, (int)getY() + 7, 0 + iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        GL11.glEnable(3042);
      } 
      potion.renderInventoryEffect((int)getX(), (int)getY(), effect, Minecraft.func_71410_x());
      GL11.glPopMatrix();
      if (potion.shouldRenderInvText(effect)) {
        String potionName = I18n.func_135052_a(potion.func_76393_a(), new Object[0]);
        if (effect.func_76458_c() > 0 && effect.func_76458_c() < 4)
          potionName = potionName + " " + I18n.func_135052_a("enchantment.level." + (effect.func_76458_c() + 1), new Object[0]); 
        TextInfo potionNameInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE);
        DrawUtils.TEXT.drawText(getX() + 100.0D, getY() + offset + 35.0D, getWidth() - 115.0D, potionNameInfo.getHeight(), potionName, potionNameInfo, Align.START, Align.START, TextOverflow.DOT, TextMode.NORMAL);
        DrawUtils.TEXT.drawText(getX() + 100.0D, getY() + offset + 60.0D, Potion.func_76389_a(effect), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 20.0F).color(new Color(64, 64, 78)), Align.START, Align.START);
      } 
    } 
  }
  
  public static PotionEffectContainer create(double x, double y, double width, double height) {
    return new PotionEffectContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\PotionEffectContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */