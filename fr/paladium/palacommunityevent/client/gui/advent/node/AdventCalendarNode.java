package fr.paladium.palacommunityevent.client.gui.advent.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.manager.AdventCalendarManager;
import fr.paladium.palacommunityevent.common.network.BBPacketAdventCalendar;
import fr.paladium.palacommunityevent.common.pojo.advent.AdventCalendarType;
import java.time.LocalDateTime;
import java.time.Month;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AdventCalendarNode extends AClickableNode {
  private static final ResourceLocation BACKGROUND = new ResourceLocation("palacommunityevent", "textures/gui/advent/case/background.png");
  
  private final Color primaryColor;
  
  private final Color secondaryColor;
  
  private final int day;
  
  private final int fontSize;
  
  private final LocalDateTime now;
  
  private final AdventCalendarType type;
  
  private final ItemStack[] items;
  
  public AdventCalendarNode(double x, double y, double width, double height, Color primaryColor, Color secondaryColor, int day, int fontSize) {
    super(x, y, width, height);
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.day = day;
    this.fontSize = fontSize;
    this.now = LocalDateTime.now();
    this.type = PalaCommunityEventEEP.get((Entity)(Minecraft.func_71410_x()).field_71439_g).getAdventCalendarType(this.now.getYear(), day);
    this.items = AdventCalendarManager.getItems(this.now.getYear(), day);
    if (this.now.getDayOfMonth() == this.day && this.type == AdventCalendarType.NONE && this.now.getMonth() == Month.DECEMBER && this.now.getDayOfMonth() <= 24)
      setCallback(n -> (new BBPacketAdventCalendar(this.now.getYear(), this.now.getMonthValue(), this.now.getDayOfMonth())).subscribe(()).send()); 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double stroke = (int)this.ui.width(0.25D);
    if (this.type == AdventCalendarType.PICKED_UP) {
      GuiUtils.drawImageTransparent((int)this.x, (int)this.y, BACKGROUND, (int)this.width, (int)this.height);
      GuiUtils.drawFilledBorder((int)(this.x + stroke), (int)(this.y + stroke), (int)(this.x + this.width - stroke), (int)(this.y + this.height - stroke), new Color(50, 50, 50), stroke);
      float itemSize = width(60.0F) / 16.0F;
      GuiUtils.renderScaledItemStackIntoGUI(this.items[0], this.x + width(50.0F) - (8.0F * itemSize), this.y + height(50.0F) - (8.0F * itemSize), itemSize);
      return;
    } 
    GuiUtils.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), this.primaryColor);
    GuiUtils.drawFilledBorder((int)(this.x + stroke), (int)(this.y + stroke), (int)(this.x + this.width - stroke), (int)(this.y + this.height - stroke), this.primaryColor.darker(0.5F), stroke);
    if (this.now.getDayOfMonth() != this.day) {
      double secondaryStroke = (int)height(15.0F);
      GuiUtils.drawRect((int)(this.x + width(50.0F) - secondaryStroke / 2.0D), (int)this.y, (int)(this.x + width(50.0F) + secondaryStroke / 2.0D), (int)(this.y + this.height), this.secondaryColor);
      GuiUtils.drawRect((int)this.x, (int)(this.y + height(50.0F) - secondaryStroke / 2.0D), (int)(this.x + this.width), (int)(this.y + height(50.0F) + secondaryStroke / 2.0D), this.secondaryColor);
      GuiUtils.drawRect((int)this.x, (int)(this.y + height(50.0F) + secondaryStroke / 2.0D), (int)(this.x + this.width), (int)(this.y + height(50.0F) + secondaryStroke / 2.0D + height(2.5F)), this.secondaryColor.darker(0.2F));
    } 
    double textBackgroundSize = width(60.0F);
    GuiUtils.drawRect((int)(this.x + width(50.0F) - textBackgroundSize / 2.0D), (int)(this.y + height(50.0F) - textBackgroundSize / 2.0D), (int)(this.x + width(50.0F) + textBackgroundSize / 2.0D), (int)(this.y + height(50.0F) + textBackgroundSize / 2.0D), new Color(0.0F, 0.0F, 0.0F, 0.2F));
    String text = String.format("%02d", new Object[] { Integer.valueOf(this.day) });
    GuiUtils.drawCenteredStringWithCustomFont(mc, text, (int)(this.x + width(50.0F)), (int)(this.y + height(47.0F) - (GuiUtils.getFontHeight(mc, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize) / 2.0F)), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize, true, Color.BLACK);
    if (this.now.getDayOfMonth() > this.day && this.now.getMonth() == Month.DECEMBER) {
      double lockSize = (int)width(40.0F);
      GuiUtils.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), new Color(28, 28, 28, 200));
      GuiUtils.drawImageTransparent((int)(this.x + width(50.0F) - lockSize / 2.0D), (int)(this.y + height(50.0F) - lockSize / 2.0D), new ResourceLocation("palacommunityevent", "textures/gui/advent/case/lock.png"), lockSize, lockSize);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\advent\node\AdventCalendarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */