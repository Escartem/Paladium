package fr.paladium.palaforgeutils.lib.tooltip;

import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.debug.UIDataDebug;
import net.minecraft.item.ItemStack;

@UIDataDebug(hotreload = false, profiler = false)
class CustomTooltipFakeUI extends UI {
  private ItemStack itemStack;
  
  private CustomTooltip.CustomTooltipData tooltipData;
  
  private CustomTooltipFakeUI() {}
  
  public void init() {
    if (this.itemStack != null && this.tooltipData != null)
      ((CustomTooltipNode)this.tooltipData.getNode().get()).item(this.itemStack).attach(this); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tooltip\CustomTooltipListener$CustomTooltipFakeUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */