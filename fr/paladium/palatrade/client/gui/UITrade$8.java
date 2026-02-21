package fr.paladium.palatrade.client.gui;

import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

class null extends TexturedNodeButton {
  null(double x0, double x1, double x2, double x3) {
    super(x0, x1, x2, x3);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    double pbValue = Double.parseDouble(UITrade.access$500(UITrade.this).getText().split(" ")[0]);
    if (pbValue <= 0.0D) {
      setHovers(new ArrayList());
      return;
    } 
    List<String> hovers = new ArrayList<>();
    hovers.add("Vous gagnez 90% de §c" + pbValue + " §rsoit §c" + String.format("%.2f", new Object[] { Double.valueOf(pbValue * 0.9D) }));
    setHovers(hovers);
    super.draw(mc, mouseX, mouseY);
  }
  
  public List<String> getHovers() {
    List<String> hovers = new ArrayList<>();
    return hovers;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\gui\UITrade$8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */