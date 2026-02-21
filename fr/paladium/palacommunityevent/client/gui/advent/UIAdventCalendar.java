package fr.paladium.palacommunityevent.client.gui.advent;

import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.notification.MinecraftNotification;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palacommunityevent.client.gui.advent.node.AdventCalendarNode;
import fr.paladium.palacommunityevent.common.manager.AdventCalendarManager;
import java.time.LocalDateTime;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UIAdventCalendar extends UI {
  private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation("palacommunityevent", "textures/gui/advent/background_overlay.png");
  
  public void func_73866_w_() {
    LocalDateTime now = LocalDateTime.now();
    int year = now.getYear();
    if (!AdventCalendarManager.isValid(year)) {
      Apollon.instance().getNotificationManager().addNotification((ANotification)new MinecraftNotification("Santa Claus", "Il semble y avoir une erreur dans la configuration du calendrier de l'avent " + year, "paladium"));
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    super.func_73866_w_();
    addNode((ANode)new MinecraftCloseNode(width(82.0F), height(13.0F)));
    Color[][] colors = { 
        { Color.decode("#5C16D2"), Color.decode("#E8C51A") }, { Color.decode("#CE67FF"), Color.decode("#3F58E3") }, { Color.decode("#419CE3"), Color.decode("#5C16D2") }, { Color.decode("#E0481A"), Color.decode("#E8C51A") }, { Color.decode("#78D23D"), Color.decode("#21E292") }, { Color.decode("#5C16D2"), Color.decode("#E8C51A") }, { Color.decode("#78D23D"), Color.decode("#BB37F9") }, { Color.decode("#901140"), Color.decode("#E8C51A") }, { Color.decode("#FFCC4A"), Color.decode("#E11958") }, { Color.decode("#8822E2"), Color.decode("#E8C51A") }, 
        { Color.decode("#8C3799"), Color.decode("#E8C51A") }, { Color.decode("#901140"), Color.decode("#E8C51A") }, { Color.decode("#BB37F9"), Color.decode("#E8C51A") }, { Color.decode("#3F58E3"), Color.decode("#C82222") }, { Color.decode("#5C16D2"), Color.decode("#E8C51A") }, { Color.decode("#FFCC4A"), Color.decode("#78D23D") }, { Color.decode("#419CE3"), Color.decode("#E8C51A") }, { Color.decode("#5C16D2"), Color.decode("#419CE3") }, { Color.decode("#4165E3"), Color.decode("#E8C51A") }, { Color.decode("#78D23D"), Color.decode("#E8C51A") }, 
        { Color.decode("#5C16D2"), Color.decode("#419CE3") }, { Color.decode("#21E292"), Color.decode("#E8C51A") }, { Color.decode("#D2963D"), Color.decode("#E8C51A") }, { Color.decode("#E11958"), Color.decode("#E8C51A") } };
    double smallSize = width(7.2F);
    double offset = (height(59.16F) - smallSize * 4.0D) / 3.0D;
    int index = 1;
    int i;
    for (i = 0; i < 12; i++) {
      addNode((ANode)new AdventCalendarNode((width(50.0F) - width(8.695F)) - (smallSize + offset) * 3.0D + (offset + smallSize) * (i % 3), height(25.18F) + (offset + smallSize) * (i / 3), smallSize, smallSize, colors[index - 1][0], colors[index - 1][1], index, 150));
      index += (i % 3 == 2) ? 4 : 1;
    } 
    index = 24;
    addNode((ANode)new AdventCalendarNode((width(50.0F) - width(8.695F)), height(25.18F), width(17.39F), height(59.16F), colors[23][0], colors[23][1], index, 500));
    index = 4;
    for (i = 0; i < 11; i++) {
      addNode((ANode)new AdventCalendarNode((width(50.0F) + width(8.695F)) + offset + (offset + smallSize) * (i % 3), height(25.18F) + (offset + smallSize) * (i / 3), smallSize, smallSize, colors[index - 1][0], colors[index - 1][1], index, 150));
      index += (i % 3 == 2) ? 4 : 1;
    } 
    addNode((ANode)(new TexturedNodeButton((width(50.0F) + width(8.695F)) + offset + (offset + smallSize) * 2.0D, height(25.18F) + (offset + smallSize) * 3.0D, smallSize, smallSize)).setTexture("palacommunityevent:textures/gui/advent/case/template"));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    drawBackground((width(50.0F) - width(70.0F) / 2.0F), (height(50.0F) - height(76.38F) / 2.0F), width(70.0F), height(76.38F));
    String title = "calendrier de l'avent".toUpperCase();
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, title, width(50.0F), (height(14.0F) + height(0.37F)), Color.decode("#28600F"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 230);
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, title, width(50.0F), height(14.0F), Color.decode("#46A41D"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 230);
  }
  
  private void drawBackground(double x, double y, double width, double height) {
    double innerBorderRadius = width(0.52D);
    double outsideRadius = width(0.78125D);
    double outsideBorderRadius = width(1.04167D);
    Color backgroundColor = new Color(204, 54, 55);
    Color innerBorderColor = new Color(1.0F, 1.0F, 1.0F, 0.5F);
    double innerWidth = width - outsideRadius;
    double innerHeight = height - outsideRadius;
    GuiUtils.drawRect(x - outsideRadius, y - outsideRadius, x + innerWidth + outsideRadius, y + innerHeight + outsideRadius, backgroundColor);
    GuiUtils.drawBorder(x, y, x + innerWidth, y + innerHeight, innerBorderColor, innerBorderRadius);
    GuiUtils.drawBorder(x - outsideRadius, y - outsideRadius, x + innerWidth + outsideRadius, y + innerHeight + outsideRadius, backgroundColor, outsideBorderRadius);
    GL11.glPushMatrix();
    GL11.glEnable(3089);
    GuiUtils.scissor(this.field_146297_k, x, y, width - outsideRadius, height - outsideRadius);
    (new Color(204, 54, 55, 50)).brighter(0.5F).bind();
    GuiUtils.drawImageTransparent(x, y, OVERLAY_TEXTURE, width, height);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3089);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\advent\UIAdventCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */