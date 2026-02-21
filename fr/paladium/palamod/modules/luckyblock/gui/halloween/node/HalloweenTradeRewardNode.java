package fr.paladium.palamod.modules.luckyblock.gui.halloween.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class HalloweenTradeRewardNode extends AClickableNode {
  private AtomicReference<ResourceLocation> texture;
  
  public HalloweenTradeRewardNode(double x, double y, double width, double height, String url) {
    super(x, y, width, height);
    this.texture = downloadImage(url);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRoundedRect(this.x, this.y, new Color(210, 81, 33), this.width, this.height, this.ui.width(0.4F));
    if (this.texture != null && this.texture.get() != null)
      GuiUtils.drawImageTransparent(this.x + width(20.0F), this.y + height(20.0F), this.texture.get(), width(60.0F), height(60.0F)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\node\HalloweenTradeRewardNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */