package fr.paladium.pet.client.ui.home.node.assignment;

import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.node.assignment.child.AssignmentNode;
import fr.paladium.pet.client.ui.utils.data.AssignmentClientData;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AssignmentGlobalNode extends ANode {
  private static final ResourceLocation LOGO = new ResourceLocation("palapet", "textures/ui/home/assignment_logo.png");
  
  private final HomeData data;
  
  private final String title;
  
  private final String actualisationText;
  
  private FlexNode flexNode;
  
  public AssignmentGlobalNode(HomeData data, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.data = data;
    this.title = PetTranslateEnum.GUI_NODE_ASSIGNMENT_TITLE.text();
    this.actualisationText = PetTranslateEnum.GUI_NODE_ASSIGNMENT_REMAINING.text();
  }
  
  private void initFlexNode(double startX, double startY, double nodeWidth, double nodeHeight) {
    if (this.flexNode != null)
      return; 
    FlexNode flexNode = FlexNode.horizontal(startX, startY, nodeHeight).setMargin(width(0.7F));
    int i = 0;
    for (AssignmentClientData assignment : this.data.getAssignments()) {
      AssignmentNode node = new AssignmentNode(assignment, 0.0D, 0.0D, nodeWidth, nodeHeight);
      flexNode.addChild((ANode)node);
    } 
    this.flexNode = flexNode;
    addChild((ANode)flexNode);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double logoX = this.ui.width(1.979F);
    double logoY = this.ui.width(1.979F);
    GuiUtils.drawImageTransparent(this.x, this.y, LOGO, logoX, logoY);
    double textX = this.x + logoX + width(1.094F);
    double textY = this.y + this.ui.height(0.75F);
    int fontSize = 140;
    FontObj font = Fonts.MONTSERRAT_EXTRABOLD.getFont();
    GuiUtils.drawStringWithCustomFont(mc, this.title, textX, textY, Color.WHITE, font, fontSize);
    Calendar now = Calendar.getInstance();
    Calendar midnight = Calendar.getInstance();
    midnight.set(11, 23);
    midnight.set(12, 59);
    midnight.set(13, 59);
    midnight.set(14, 999);
    long remainingTime = midnight.getTimeInMillis() - now.getTimeInMillis();
    FontObj remainingFont = Fonts.MONTSERRAT_SEMIBOLD.getFont();
    int remainingFontSize = 1;
    String duration = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(remainingTime)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60L), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60L) });
    String remainingString = this.actualisationText + duration;
    double textWidth = GuiUtils.getStringWidth(mc, remainingString, remainingFont, remainingFontSize);
    double remainingX = this.x + width(100.0F) - textWidth;
    double remainingY = textY + height(6.0F);
    GuiUtils.drawStringWithCustomFont(mc, remainingString
        .toUpperCase(), remainingX, remainingY, Color.WHITE, remainingFont, remainingFontSize);
    double barX = textX + GuiUtils.getStringWidth(mc, this.title, font, fontSize) + width(1.0F);
    double barY = textY + (GuiUtils.getFontHeight(mc, font, fontSize) / 2.0F);
    double barHeight = height(0.5F);
    double barEndX = Math.floor(remainingX - width(1.0F));
    GuiUtils.drawRect(barX, barY, barEndX, barY + barHeight, Color.WHITE);
    double flexNodeX = 0.0D;
    double flexNodeY = logoY + height(6.0F);
    double flexNodeWidth = width(24.5F);
    double flexNodeHeight = flexNodeY + height(47.0F);
    initFlexNode(flexNodeX, flexNodeY, flexNodeWidth, flexNodeHeight);
  }
  
  public boolean onClick(int i, int i1, int i2) {
    return false;
  }
  
  public void onRelease(int i, int i1, int i2) {}
  
  public void onKeyTyped(char c, int i) {}
  
  public void onHover(int i, int i1) {}
  
  public void onHoverOut(int i, int i1) {}
  
  public void fixedUpdate() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\assignment\AssignmentGlobalNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */