package fr.paladium.pet.client.ui.home.node.assignment.child;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.node.assignment.child.node.FeedButtonNode;
import fr.paladium.pet.client.ui.utils.data.AssignmentClientData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.packet.pet.CSPOpenFeedContainerPacket;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AssignmentNode extends ANode {
  public static final Color GREEN_FINISH_COLOR = new Color(48, 203, 31);
  
  private static final Color PROGRESS_VOID_COLOR = Color.decode("#323232");
  
  private static final Color PROGRESS_VOID_UNDER_COLOR = Color.decode("#1A1A1A");
  
  private static final Color PROGRESS_COLOR = Color.decode("#5ED42A");
  
  private static final Color PROGRESS_UNDER_COLOR = Color.decode("#3B8818");
  
  public static final ResourceLocation UNION_LOGO = new ResourceLocation("palapet", "textures/ui/home/union_logo.png");
  
  private final Minecraft mc;
  
  private final AssignmentClientData data;
  
  public Minecraft getMc() {
    return this.mc;
  }
  
  public AssignmentClientData getData() {
    return this.data;
  }
  
  public AssignmentNode(AssignmentClientData data, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.data = data;
    this.mc = Minecraft.func_71410_x();
    double nodeWidth = width(30.0F);
    double nodeHeight = height(15.0F);
    ANode feedNode = (new FeedButtonNode((width(100.0F) - nodeWidth) / 2.0D, height(100.0F) - nodeHeight / 2.0D, nodeWidth, nodeHeight)).setCallback(callback -> PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)new CSPOpenFeedContainerPacket()));
    if (!this.data.isFinish() && this.data.getType().equals(AssignmentType.ITEM))
      addChild(feedNode); 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, UIPetHome.BACKGROUND_COLOR);
    double logoPosX = this.x + width(4.0F);
    double logoPosY = this.y + height(20.0F);
    drawLogo(logoPosX, logoPosY);
    double titlePosX = logoPosX + width(23.0F);
    double titlePosY = logoPosY;
    int titleFontSize = 1;
    TextNodeLabel title = new TextNodeLabel(titlePosX, logoPosY, this.data.getName().toUpperCase(), Fonts.PIXEL_NES.getFont(), titleFontSize, Color.WHITE);
    title.setTextOverflow(TextOverflow.ELLIPSIS);
    title.setWidth(width(70.0F));
    title.draw(mc, mouseY, mouseY);
    drawDescription(titlePosX, titlePosY + height(15.0F));
    double progressBarX = titlePosX;
    double progressBarY = titlePosY + height(50.0F);
    double progressbarHeight = height(7.0F);
    double progressbarWidth = width(50.0F);
    drawProgressBar(progressBarX, progressBarY, progressbarHeight, progressbarWidth);
    if (this.data.isFinish()) {
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(GREEN_FINISH_COLOR.r, GREEN_FINISH_COLOR.g, GREEN_FINISH_COLOR.b, 0.3F));
      double logoWidth = width(18.888F);
      double logoHeight = width(18.888F);
      GuiUtils.drawImageTransparent(this.x + width(40.0F), this.y + height(30.0F), UNION_LOGO, logoWidth, logoHeight);
    } 
  }
  
  public void drawFinish(Minecraft mc, int mouseX, int mouseY) {
    if (this.data.isFinish()) {
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(GREEN_FINISH_COLOR.r, GREEN_FINISH_COLOR.g, GREEN_FINISH_COLOR.b, 0.3F));
      double logoWidth = width(18.888F);
      double logoHeight = width(18.888F);
      GuiUtils.drawImageTransparent(this.x + width(40.0F), this.y + height(30.0F), UNION_LOGO, logoWidth, logoHeight);
    } 
  }
  
  private void drawLogo(double posX, double posY) {
    double logoWidth = width(18.888F);
    double logoHeight = width(18.888F);
    GuiUtils.drawImageTransparent(posX, posY, this.data.getLogo(), logoWidth, logoHeight, false);
  }
  
  private void drawDescription(double posX, double posY) {
    int fontSize = 1;
    FontObj font = Fonts.MONTSERRAT_REGULAR.getFont();
    List<String> lines = GuiUtils.getSplittedString(this.mc, this.data.getDescription(), font, fontSize, width(70.0F));
    int lineHeight = GuiUtils.getFontHeight(this.mc, font, fontSize + 10);
    int lineCount = Math.min(lines.size(), 3);
    for (int i = 0; i < lineCount; i++) {
      String text = lines.get(i);
      if (i == lineCount - 1 && lineCount != lines.size())
        text = text + TextOverflow.ELLIPSIS.getOverflow(); 
      GuiUtils.drawStringWithCustomFont(this.mc, text, posX, posY, Color.WHITE, font, fontSize);
      posY += lineHeight;
    } 
  }
  
  private void drawProgressBar(double posX, double posY, double barHeight, double barWidth) {
    GuiUtils.drawRect(posX, posY, posX + barWidth, posY + barHeight, PROGRESS_VOID_COLOR);
    double underHeight = barHeight * 0.2D;
    GuiUtils.drawRect(posX, posY + barHeight - underHeight, posX + barWidth, posY + barHeight, PROGRESS_VOID_UNDER_COLOR);
    double progress = this.data.getProgress() / this.data.getMaxProgress();
    double progressWidth = progress * barWidth;
    GuiUtils.drawRect(posX, posY, posX + progressWidth, posY + barHeight, PROGRESS_COLOR);
    GuiUtils.drawRect(posX, posY + barHeight - underHeight, posX + progressWidth, posY + barHeight, PROGRESS_UNDER_COLOR);
    String progressText = getProgressText();
    int fontSize = -35;
    FontObj font = Fonts.MONTSERRAT_EXTRABOLD.getFont();
    GuiUtils.drawStringWithCustomFont(this.mc, progressText, posX + barWidth + 
        
        width(1.0F), posY + height(1.0F), Color.WHITE, font, fontSize);
  }
  
  private String getProgressText() {
    double minute = 60.0D;
    AssignmentType type = this.data.getType();
    if (type.isTimedType() && this.data.getMaxProgress() > minute) {
      double currentMinutes = this.data.getProgress() / minute;
      double maxMinutes = this.data.getMaxProgress() / minute;
      return formatNumber(currentMinutes) + "/" + formatNumber(maxMinutes);
    } 
    return formatNumber(this.data.getProgress()) + "/" + formatNumber(this.data.getMaxProgress());
  }
  
  private String formatNumber(double number) {
    return (number % 1.0D == 0.0D) ? String.format("%d", new Object[] { Integer.valueOf((int)number) }) : String.format("%.1f", new Object[] { Double.valueOf(number) });
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


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\assignment\child\AssignmentNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */