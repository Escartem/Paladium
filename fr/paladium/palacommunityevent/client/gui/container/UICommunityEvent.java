package fr.paladium.palacommunityevent.client.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftHexaNode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.nodes.progressbar.MinecraftProgressBarNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palacommunityevent.client.gui.container.node.CommunityEventBigStepNode;
import fr.paladium.palacommunityevent.client.gui.container.node.CommunityEventStepNode;
import fr.paladium.palacommunityevent.client.gui.container.node.PceMinecraftTitleNodeLabel;
import fr.paladium.palacommunityevent.common.pojo.CommonCommunityStep;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.CommunityStep;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class UICommunityEvent extends UI {
  public CommunityEvent communityEventData;
  
  public int playerProgress;
  
  public int globalProgress;
  
  public UICommunityEvent(CommunityEvent communityEventData, int playerProgress, int globalProgress) {
    super(null, "palacommunityevent:communityevent");
    this.communityEventData = communityEventData;
  }
  
  public UICommunityEvent() {
    super(null, "palacommunityevent:communityevent");
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.communityEventData == null)
      return; 
    addNode((ANode)new PceMinecraftTitleNodeLabel(width(13.0F), height(12.0F), this.communityEventData.name, this.communityEventData.titleColor, this.communityEventData.titleColorShadow));
    TextNodeLabel label;
    addNode((ANode)(label = (new TextNodeLabel(0.0D, height(27.0F), "Progression communautaire", Fonts.PIXEL_NES.getFont(), 150, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(100.0F));
    addNode((ANode)(label = (new TextNodeLabel(0.0D, height(57.0F), "Progression personnelle", Fonts.PIXEL_NES.getFont(), 150, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(100.0F));
    addNode((ANode)new MinecraftCloseNode(width(86.0F), height(12.0F)));
    addNode((new MinecraftHexaNode(width(7.2F), height(43.843F), width(5.9375F), new ResourceLocation("palacommunityevent", "textures/gui/" + this.communityEventData.textureFolder + "/icon.png"))).setBorder(true).setCallback(n -> {
            this.prev = (GuiScreen)this;
            (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/pce deposit " + this.communityEventData.id);
          }));
    double progressBarCommuX = width(20.0F);
    double progressBarCommuY = height(40.0F);
    double progressBarCommuWidth = width(60.0F);
    double progressBarCommuHeight = height(6.0F);
    MinecraftProgressBarNode commonProgress = new MinecraftProgressBarNode(progressBarCommuX, progressBarCommuY, progressBarCommuWidth, progressBarCommuHeight, Color.decode("#" + this.communityEventData.color)) {
        public boolean isHovered(int mouseX, int mouseY) {
          if (UICommunityEvent.this.getNodes().stream().filter(n -> !(n instanceof MinecraftProgressBarNode)).anyMatch(ANode::isHovered))
            return false; 
          return super.isHovered(mouseX, mouseY);
        }
      };
    commonProgress.setMin(0.0F);
    commonProgress.setValue((this.globalProgress <= this.communityEventData.totalItems) ? this.globalProgress : this.communityEventData.totalItems);
    commonProgress.setMax(this.communityEventData.totalItems);
    commonProgress.addHover("§bProgression de la communauté");
    commonProgress.addHover("");
    commonProgress.addHover("§8» §e" + Math.min(this.globalProgress, this.communityEventData.totalItems) + "/" + this.communityEventData.totalItems + " §8[§c" + (int)(Math.min(this.globalProgress, this.communityEventData.totalItems) / this.communityEventData.totalItems * 100.0F) + "%§8]");
    addNode((ANode)commonProgress);
    int i = 0;
    for (CommonCommunityStep step : this.communityEventData.commonSteps) {
      float percentage = step.percentToReach;
      CommunityEventBigStepNode communityEventBigStepNode;
      addNode((ANode)(communityEventBigStepNode = new CommunityEventBigStepNode(progressBarCommuX + progressBarCommuWidth * (percentage / 100.0F) - width(2.75F), progressBarCommuY + progressBarCommuHeight / 2.0D - width(3.5F), width(7.0F), width(7.0F), "palacommunityevent:textures/gui/" + this.communityEventData.textureFolder + "/depositslot", "palacommunityevent:textures/gui/" + this.communityEventData.textureFolder + "/depositslot_hover", this.communityEventData, (CommunityStep)step)));
      communityEventBigStepNode.setCallback(c -> (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/pce claim " + this.communityEventData.id + " " + step.id));
      i++;
    } 
    double progressBarPlayerX = width(20.0F);
    double progressBarPlayerY = height(70.0F);
    MinecraftProgressBarNode playerProgressBar = new MinecraftProgressBarNode(progressBarPlayerX, progressBarPlayerY, progressBarCommuWidth, progressBarCommuHeight, Color.decode("#" + this.communityEventData.color)) {
        public boolean isHovered(int mouseX, int mouseY) {
          if (UICommunityEvent.this.getNodes().stream().filter(n -> !(n instanceof MinecraftProgressBarNode)).anyMatch(ANode::isHovered))
            return false; 
          return super.isHovered(mouseX, mouseY);
        }
      };
    playerProgressBar.setMin(0.0F);
    playerProgressBar.setValue((this.playerProgress <= this.communityEventData.totalItemsPerPlayer) ? this.playerProgress : this.communityEventData.totalItemsPerPlayer);
    playerProgressBar.setMax(this.communityEventData.totalItemsPerPlayer);
    playerProgressBar.addHover("§bVotre progression");
    playerProgressBar.addHover("");
    playerProgressBar.addHover("§8» §e" + Math.min(this.playerProgress, this.communityEventData.totalItemsPerPlayer) + "/" + this.communityEventData.totalItemsPerPlayer + " §8[§c" + (int)(Math.min(this.playerProgress, this.communityEventData.totalItemsPerPlayer) / this.communityEventData.totalItemsPerPlayer * 100.0F) + "%§8]");
    addNode((ANode)playerProgressBar);
    i = 0;
    for (CommunityStep step : this.communityEventData.playerSteps) {
      float percentage = step.percentToReach;
      if (i % 2 == 0) {
        addNode((ANode)new CommunityEventStepNode(progressBarPlayerX + progressBarCommuWidth * (percentage / 100.0F) - width(2.75F), progressBarPlayerY + progressBarCommuHeight / 2.0D - width(2.75F), width(5.5F), width(5.5F), "palacommunityevent:textures/gui/common/slot", "palacommunityevent:textures/gui/common/slot", this.communityEventData, step));
      } else {
        addNode((ANode)new CommunityEventBigStepNode(progressBarPlayerX + progressBarCommuWidth * (percentage / 100.0F) - width(2.75F), progressBarPlayerY + progressBarCommuHeight / 2.0D - width(3.5F), width(7.0F), width(7.0F), "palacommunityevent:textures/gui/" + this.communityEventData.textureFolder + "/depositslot", "palacommunityevent:textures/gui/" + this.communityEventData.textureFolder + "/depositslot", this.communityEventData, step));
      } 
      i++;
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    double w = width(79.16F);
    double h = height(79.16F);
    BackgroundHelper.createMinecraft(w, h);
    if (this.communityEventData == null)
      return; 
    double x = (width(100.0D) - w) / 2.0D;
    double y = (height(100.0D) - h) / 2.0D;
    GuiUtils.drawImageTransparent(x, y, 0.0D, 0.0D, width(78.4F), height(77.65F), w, h, new ResourceLocation("palacommunityevent", "textures/gui/" + this.communityEventData.textureFolder + "/background.png"), Color.white, 0.5F);
  }
  
  public void setCommunityEventData(CommunityEvent communityEventData) {
    this.communityEventData = communityEventData;
    func_73866_w_();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\UICommunityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */