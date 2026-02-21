package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AnnounceButtonNode extends AClickableNode {
  private int id;
  
  private ResourceLocation resource;
  
  public void setId(int id) {
    this.id = id;
  }
  
  public void setResource(ResourceLocation resource) {
    this.resource = resource;
  }
  
  public int getId() {
    return this.id;
  }
  
  public ResourceLocation getResource() {
    return this.resource;
  }
  
  public AnnounceButtonNode(int id, ResourceLocation resource, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.id = id;
    this.resource = resource;
  }
  
  public void onHover(int mouseX, int mouseY) {
    super.onHover(mouseX, mouseY);
    double drawHeight = this.height;
    if (this.id == 5)
      drawHeight -= height(35.0F); 
    GuiUtils.drawBorder(this.x, this.y, this.x + this.width, this.y + drawHeight, Color.WHITE, 1.0D);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, this.resource, this.width, this.height, true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\nodes\AnnounceButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */