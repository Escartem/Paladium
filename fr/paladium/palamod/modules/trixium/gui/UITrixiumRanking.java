package fr.paladium.palamod.modules.trixium.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.factions.client.gui.nodes.EmblemNode;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.ScrollNodeButton;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.buttons.utils.DynamicScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.gui.node.ranking.TrixiumFactionRankingNode;
import fr.paladium.palamod.modules.trixium.gui.node.ranking.TrixiumPlayerRankingNode;
import fr.paladium.palamod.modules.trixium.network.ranking.page.CSPacketTrixiumRankingFactionPage;
import fr.paladium.palamod.modules.trixium.network.ranking.page.CSPacketTrixiumRankingPlayerPage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UITrixiumRanking extends UI {
  private final Color[] colors = new Color[] { new Color(131, 174, 248), new Color(154, 131, 248) };
  
  private final ResourceLocation trixium = new ResourceLocation("palamod", "textures/gui/trixium/trixium.png");
  
  DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.FRANCE);
  
  private DynamicScrollableArea playersArea;
  
  private DynamicScrollableArea factionsArea;
  
  private final List<TrixiumProfile> players;
  
  private final List<TrixiumFactionProfile> factions;
  
  private final TrixiumProfile playerProfile;
  
  private final TrixiumFactionProfile factionProfile;
  
  public DynamicScrollableArea getPlayersArea() {
    return this.playersArea;
  }
  
  public DynamicScrollableArea getFactionsArea() {
    return this.factionsArea;
  }
  
  public UITrixiumRanking(List<TrixiumProfile> players, List<TrixiumFactionProfile> factions, TrixiumProfile playerProfile, TrixiumFactionProfile factionProfile) {
    super(null, "palamod:trixium");
    this.players = players;
    this.factions = factions;
    this.playerProfile = playerProfile;
    this.factionProfile = factionProfile;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    DecimalFormatSymbols symbols = this.formatter.getDecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    this.formatter.setDecimalFormatSymbols(symbols);
    TextNodeLabel playerLabel = new TextNodeLabel(width(40.5F), height(32.31F), this.playerProfile.playerName, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 150, Color.WHITE);
    playerLabel.setTextAlign(TextAlign.CENTER);
    playerLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    playerLabel.setWidth(width(20.0F));
    addNode((ANode)playerLabel);
    TextNodeLabel playerPositionInfoLabel = new TextNodeLabel(width(47.5F), height(41.2F), "Position", Fonts.MONTSERRAT_MEDIUM.getFont(), 50, Color.WHITE);
    playerPositionInfoLabel.setTextAlign(TextAlign.RIGHT);
    addNode((ANode)playerPositionInfoLabel);
    TextNodeLabel playerTrixiumInfoLabel = new TextNodeLabel(width(47.5F), height(47.4F), "Trixium", Fonts.MONTSERRAT_MEDIUM.getFont(), 50, Color.WHITE);
    playerTrixiumInfoLabel.setTextAlign(TextAlign.RIGHT);
    addNode((ANode)playerTrixiumInfoLabel);
    TextNodeLabel playerPositionLabel = new TextNodeLabel(width(58.25F), height(41.2F), (this.playerProfile.rank == -1) ? "non classé" : ((this.playerProfile.rank + 1) + " " + ((this.playerProfile.rank == 0) ? "er" : "ème")), Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    playerPositionLabel.setTextAlign(TextAlign.RIGHT);
    playerPositionLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    playerPositionLabel.setWidth(width(10.0F));
    addNode((ANode)playerPositionLabel);
    TextNodeLabel playerTrixiumLabel = new TextNodeLabel(width(56.5F), height(47.4F), this.formatter.format(this.playerProfile.amountTrixium), Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    playerTrixiumLabel.setTextAlign(TextAlign.RIGHT);
    playerTrixiumLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    playerTrixiumLabel.setWidth(width(8.0F));
    addNode((ANode)playerTrixiumLabel);
    TextNodeLabel factionLabel = new TextNodeLabel(width(40.5F), height(73.240005F), (this.factionProfile == null) ? "Pas de faction" : this.factionProfile.factionName, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 150, Color.WHITE);
    factionLabel.setTextAlign(TextAlign.CENTER);
    factionLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    factionLabel.setWidth(width(20.0F));
    addNode((ANode)factionLabel);
    TextNodeLabel factionPositionInfoLabel = new TextNodeLabel(width(47.5F), height(82.130005F), "Position", Fonts.MONTSERRAT_MEDIUM.getFont(), 50, Color.WHITE);
    factionPositionInfoLabel.setTextAlign(TextAlign.RIGHT);
    addNode((ANode)factionPositionInfoLabel);
    TextNodeLabel factionTrixiumInfoLabel = new TextNodeLabel(width(47.5F), height(88.33F), "Trixium", Fonts.MONTSERRAT_MEDIUM.getFont(), 50, Color.WHITE);
    factionTrixiumInfoLabel.setTextAlign(TextAlign.RIGHT);
    addNode((ANode)factionTrixiumInfoLabel);
    if (this.factionProfile != null) {
      addNode((ANode)new EmblemNode(width(47.6F), height(60.5F), width(5.67F), width(5.67F), this.factionProfile.backgroundColor, this.factionProfile.foregroundColor, this.factionProfile.borderColor, this.factionProfile.iconColor, this.factionProfile.iconBorderColor, this.factionProfile.backgroundId, this.factionProfile.foregroundId, this.factionProfile.iconId, null));
    } else {
      addNode((ANode)(new TexturedNodeButton(width(47.6F), height(60.5F), width(5.67F), width(5.67F))).setTexture("palamod:textures/gui/trixium/ranking/none").setHoveredTexture("palamod:textures/gui/trixium/ranking/none"));
    } 
    TextNodeLabel factionPositionLabel = new TextNodeLabel(width(58.25F), height(82.130005F), (this.factionProfile == null) ? "-" : ((this.factionProfile.rank == -1) ? "non classé" : ((this.factionProfile.rank + 1) + " " + ((this.factionProfile.rank == 0) ? "er" : "ème"))), Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    factionPositionLabel.setTextAlign(TextAlign.RIGHT);
    factionPositionLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    factionPositionLabel.setWidth(width(10.0F));
    addNode((ANode)factionPositionLabel);
    TextNodeLabel factionTrixiumLabel = new TextNodeLabel(width(56.5F), height(88.33F), (this.factionProfile == null) ? "-" : this.formatter.format(this.factionProfile.amountTrixium), Fonts.MONTSERRAT_BOLD.getFont(), 50, Color.WHITE);
    factionTrixiumLabel.setTextAlign(TextAlign.RIGHT);
    factionTrixiumLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    factionTrixiumLabel.setWidth(width(8.0F));
    addNode((ANode)factionTrixiumLabel);
    this
      
      .playersArea = (DynamicScrollableArea)(new DynamicScrollableArea(width(2.86F), height(27.59F), width(36.760002F), height(92.59F), new ScrollArea(width(35.05F), height(27.59F), height(65.0F), width(0.625F), height(15.27F)), 5, 20) {
        public void update(int index) {
          PTrixium.getNetwork().sendToServer((IMessage)new CSPacketTrixiumRankingPlayerPage((int)Math.ceil((index / 20.0F))));
        }
      }).setScrollSpeed(50).setScrollTexture("palamod:textures/gui/trixium/ranking/scroll").setScrollHoverTexture("palamod:textures/gui/trixium/ranking/scroll_hover");
    int i;
    for (i = 0; i < this.players.size(); i++)
      addNode((new TrixiumPlayerRankingNode(width(2.86F), height(27.59F + 5.0F * i), width(33.9F), height(5.0F), i, ((TrixiumProfile)this.players.get(i)).playerName, (int)((TrixiumProfile)this.players.get(i)).amountTrixium)).setArea((ScrollableArea)this.playersArea)); 
    addScrollableArea((ScrollableArea)this.playersArea);
    this
      
      .factionsArea = (DynamicScrollableArea)(new DynamicScrollableArea(width(64.06F), height(27.59F), width(97.96F), height(95.37F), new ScrollArea(width(96.25F), height(27.59F), height(65.0F), width(0.625F), height(15.27F)), 10, 20) {
        public void update(int index) {
          PTrixium.getNetwork().sendToServer((IMessage)new CSPacketTrixiumRankingFactionPage((int)Math.ceil((index / 20.0F))));
        }
      }).setScrollSpeed(50).setScrollTexture("palamod:textures/gui/trixium/ranking/scroll").setScrollHoverTexture("palamod:textures/gui/trixium/ranking/scroll_hover");
    for (i = 0; i < this.factions.size(); i++)
      addNode((new TrixiumFactionRankingNode(width(64.06F), height(27.59F + 11.38F * i), width(33.9F), height(11.38F), i, this.factions.get(i))).setArea((ScrollableArea)this.factionsArea)); 
    addScrollableArea((ScrollableArea)this.factionsArea);
    getNodes().stream().filter(ScrollNodeButton.class::isInstance).forEach(node -> node.setZindex(100));
    addNode((new AClickableNode(width(78.4375F), height(2.5F), width(14.843F), height(7.4F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            GuiUtils.drawRoundedRect(this.x, this.y, Color.WHITE, this.width, this.height, height(10.0F));
            UITrixiumRanking.this.drawFullyCenteredString("Déposer du trixium", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(99, 99, 99), Fonts.MONTSERRAT_BOLD.getFont(), 50);
            double crownX = this.x + width(88.5F);
            double crownY = this.y + height(12.0F);
            double crownSize = width(6.0F);
            GL11.glPushMatrix();
            GL11.glTranslated(crownX, crownY + crownSize, 0.0D);
            GL11.glRotated(15.0D + animationValue(10.0F), 0.0D, 0.0D, 1.0D);
            GL11.glTranslated(-crownX, -(crownY + crownSize), 0.0D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, animationValue(1.0F));
            GuiUtils.drawImageTransparent(crownX, crownY, UITrixiumRanking.this.trixium, crownSize, crownSize, false);
            GL11.glPopMatrix();
          }
        }).setCallback(node -> this.field_146297_k.field_71439_g.func_71165_d("/trixium")));
    addNode((new TexturedNodeButton(width(96.04F), height(3.61F), width(1.927F), width(1.927F), "palamod:textures/gui/trixium/close"))
        .setCallback(node -> this.field_146297_k.func_147108_a(null)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawGradientRect(0.0D, 0.0D, this.colors, this.field_146294_l, this.field_146295_m, new Vector2d(0.0D, this.field_146295_m), new Vector2d(this.field_146294_l, 0.0D), this.field_146295_m);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, getBackground(), this.field_146294_l, this.field_146295_m, false);
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, height(12.13F), new Color(85, 77, 184));
    GuiUtils.drawImageTransparent(width(2.864F), (height(6.065F) - width(1.795F)), this.trixium, width(3.59F), width(3.59F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "TRIXIUM", width(7.7F), height(4.5F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 180);
    GuiUtils.drawRoundedRect(width(2.86F), height(17.03F), new Color(85, 77, 184), width(33.9F), height(78.33F), width(1.0F));
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "Classement individuel", width(19.81F), height(20.27F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 120);
    GuiUtils.drawRoundedRect(width(35.05F), height(27.59F), new Color(15, 4, 113), width(0.625F), height(65.0F), width(0.35F));
    GuiUtils.drawRoundedRect(width(64.06F), height(17.03F), new Color(85, 77, 184), width(33.9F), height(78.33F), width(1.0F));
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "Classement faction", width(81.01F), height(20.27F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 120);
    GuiUtils.drawRoundedRect(width(96.25F), height(27.59F), new Color(15, 4, 113), width(0.625F), height(65.0F), width(0.35F));
    GuiUtils.drawRoundedRect(width(38.75F), height(17.03F), new Color(85, 77, 184), width(23.33F), height(37.4F), width(1.0F));
    GuiUtils.drawRect(width(38.75F), height(39.537F), width(62.08F), height(44.537F), new Color(34, 39, 138));
    GuiUtils.drawRect(width(38.75F), height(45.83F), width(62.08F), height(50.83F), new Color(34, 39, 138));
    GuiUtils.renderPlayerHead(this.field_146297_k.field_71439_g.func_70005_c_(), (width(50.0F) - 8.0F * width(0.25F)), height(21.01F), width(0.25F));
    GuiUtils.drawImageTransparent(width(57.0F), height(47.1F), this.trixium, width(1.25F), width(1.25F));
    GuiUtils.drawRoundedRect(width(38.75F), height(57.96F), new Color(85, 77, 184), width(23.33F), height(37.4F), width(1.0F));
    GuiUtils.drawRect(width(38.75F), height(80.466995F), width(62.08F), height(85.466995F), new Color(34, 39, 138));
    GuiUtils.drawRect(width(38.75F), height(86.76F), width(62.08F), height(91.76F), new Color(34, 39, 138));
    GuiUtils.drawImageTransparent(width(57.0F), height(88.03F), this.trixium, width(1.25F), width(1.25F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawRoundedRect(width(35.05F), height(27.59F), new Color(15, 4, 113), width(0.625F), height(65.0F), width(0.35F));
    GuiUtils.drawRoundedRect(width(96.25F), height(27.59F), new Color(15, 4, 113), width(0.625F), height(65.0F), width(0.35F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixiumRanking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */