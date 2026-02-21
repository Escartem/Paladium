package fr.paladium.palamod.modules.trixium.gui.node.ranking;

import fr.paladium.factions.client.gui.nodes.EmblemNode;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TrixiumFactionRankingNode extends AClickableNode {
  private final DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.FRANCE);
  
  private final int rank;
  
  private final String factionName;
  
  private final int trixium;
  
  private final ResourceLocation trixiumTexture = new ResourceLocation("palamod", "textures/gui/trixium/trixium.png");
  
  public TrixiumFactionRankingNode(double x, double y, double width, double height, int rank, TrixiumFactionProfile profile) {
    super(x, y, width, height);
    DecimalFormatSymbols symbols = this.formatter.getDecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    this.formatter.setDecimalFormatSymbols(symbols);
    this.rank = rank;
    this.factionName = profile.factionName;
    this.trixium = (int)profile.amountTrixium;
    if (profile.exists) {
      addChild((ANode)new EmblemNode(width(12.0F), height(20.0F), width(10.67F), height(60.0F), profile.backgroundColor, profile.foregroundColor, profile.borderColor, profile.iconColor, profile.iconBorderColor, profile.backgroundId, profile.foregroundId, profile.iconId, null));
    } else {
      addChild((ANode)(new TexturedNodeButton(width(12.0F), height(20.0F), width(10.67F), height(60.0F))).setTexture("palamod:textures/gui/trixium/ranking/none").setHoveredTexture("palamod:textures/gui/trixium/ranking/none"));
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawCenteredStringWithCustomFont(mc, String.valueOf(this.rank + 1), this.x + width(6.0F), this.y + this.height / 2.0D - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_EXTRABOLD.getFont(), (this.rank >= 999) ? 30 : ((this.rank >= 99) ? 70 : 100)) / 2), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), (this.rank >= 999) ? 30 : ((this.rank >= 99) ? 70 : 100));
    GuiUtils.drawStringWithCustomFont(mc, this.factionName, this.x + width(25.0F), this.y + height(23.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 60);
    GuiUtils.drawImageTransparent(this.x + width(25.0F), this.y + height(54.0F), this.trixiumTexture, width(3.4F), height(20.0F));
    GuiUtils.drawStringWithCustomFont(mc, this.formatter.format(this.trixium), this.x + width(29.0F), this.y + height(56.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 60);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\node\ranking\TrixiumFactionRankingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */