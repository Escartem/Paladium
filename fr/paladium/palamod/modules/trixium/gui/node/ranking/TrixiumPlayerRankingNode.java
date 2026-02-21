package fr.paladium.palamod.modules.trixium.gui.node.ranking;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TrixiumPlayerRankingNode extends AClickableNode {
  private final Color color = new Color(26, 55, 138);
  
  private final DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.FRANCE);
  
  private final int rank;
  
  private final String playerName;
  
  private final ResourceLocation trixiumTexture = new ResourceLocation("palamod", "textures/gui/trixium/trixium.png");
  
  private final ResourceLocation loadingTexture = new ResourceLocation("palamod", "textures/gui/trixium/ranking/none.png");
  
  private final AtomicReference<ResourceLocation> headTexture;
  
  public TrixiumPlayerRankingNode(double x, double y, double width, double height, int rank, String playerName, int trixium) {
    super(x, y, width, height);
    DecimalFormatSymbols symbols = this.formatter.getDecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    this.formatter.setDecimalFormatSymbols(symbols);
    this.rank = rank;
    this.playerName = playerName;
    this.headTexture = downloadImage("http://minotar.net/helm/" + playerName);
    TextNodeLabel playerLabel = new TextNodeLabel(width(22.0F), height / 2.0D - (GuiUtils.getFontHeight(Minecraft.func_71410_x(), Fonts.MONTSERRAT_BOLD.getFont(), 50) / 2), playerName, Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    playerLabel.setTextAlign(TextAlign.LEFT);
    playerLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    playerLabel.setWidth(width(50.0F));
    playerLabel.setSolid(false);
    TextNodeLabel trixiumLabel = new TextNodeLabel(width(88.0F), height / 2.0D - (GuiUtils.getFontHeight(Minecraft.func_71410_x(), Fonts.MONTSERRAT_BOLD.getFont(), 50) / 2), this.formatter.format(trixium), Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    trixiumLabel.setTextAlign(TextAlign.RIGHT);
    trixiumLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    trixiumLabel.setWidth(width(50.0F));
    trixiumLabel.setSolid(false);
    addChild((ANode)playerLabel);
    addChild((ANode)trixiumLabel);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (this.rank % 2 == 0)
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.color); 
    if (this.playerName.equalsIgnoreCase(mc.field_71439_g.func_70005_c_()))
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(72, 131, 232)); 
    GuiUtils.drawCenteredStringWithCustomFont(mc, String.valueOf(this.rank + 1), this.x + width(5.7F), this.y + this.height / 2.0D - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_EXTRABOLD.getFont(), (this.rank >= 999) ? 50 : 70) / 2), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), (this.rank >= 999) ? 50 : 70);
    if (this.headTexture != null && this.headTexture.get() != null) {
      GuiUtils.drawImageTransparent(this.x + width(12.0F), this.y + height(10.0F), this.headTexture.get(), width(6.5F), height(80.0F), false);
    } else {
      GuiUtils.drawImageTransparent(this.x + width(12.0F), this.y + height(10.0F), this.loadingTexture, width(6.5F), height(80.0F));
    } 
    GuiUtils.drawImageTransparent(this.x + width(90.0F), this.y + height(25.0F), this.trixiumTexture, width(4.0F), height(50.0F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\node\ranking\TrixiumPlayerRankingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */