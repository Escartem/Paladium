package fr.paladium.palamod.modules.luckyblock.gui.halloween;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class UIHalloweenTradeClose extends UI {
  public UIHalloweenTradeClose() {
    super(null, "palamod:halloween_close");
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((new AClickableNode((width(50.0F) - width(6.795F)), height(67.59F), width(13.59F), height(5.55F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            GuiUtils.drawRoundedRect(this.x, this.y, new Color(48 + (int)animationValue(207.0F), 217 + (int)animationValue(38.0F), 110 + (int)animationValue(145.0F)), this.width, this.height, width(2.0F));
            UIHalloweenTradeClose.this.drawFullyCenteredString("compris".toUpperCase(), this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(207.0F), 255 - (int)animationValue(38.0F), 255 - (int)animationValue(145.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 70);
          }
        }).setCallback(node -> this.field_146297_k.func_147108_a(null)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    drawBackground();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\UIHalloweenTradeClose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */