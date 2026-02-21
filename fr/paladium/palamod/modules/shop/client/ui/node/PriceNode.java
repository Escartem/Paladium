package fr.paladium.palamod.modules.shop.client.ui.node;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.shop.client.ui.signal.ShopItemSignal;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.format.FormatUtils;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;

public class PriceNode extends ContainerNode {
  private ShopItemSignal shopItem = null;
  
  private IntegerSignal quantity = null;
  
  private String text = "";
  
  private Color textColor = Color.WHITE;
  
  private Color sellTextColor = new Color(94, 212, 42);
  
  private Color buyTextColor = new Color(255, 57, 57);
  
  private Color rectColor = new Color(50, 50, 50);
  
  private boolean buy = false;
  
  protected PriceNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    onInit(node -> {
          node.clearChildren();
          if (this.shopItem == null)
            return; 
          ShopItem item = (ShopItem)this.shopItem.getOrDefault();
          TextNode.create(0.0D, 0.0D).text(Text.create(this.text, TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 16.0F, this.buy ? this.buyTextColor : this.sellTextColor))).attach((Node)this);
          RectNode.create(0.0D, 32.0D, getWidth(), 68.0D).border(this.rectColor, 5.0D, false).color(this.rectColor).body(()).attach((Node)this);
        });
  }
  
  private String getBuyPrice(ShopItem item) {
    if (item == null)
      return "???"; 
    if (item.canBuy()) {
      double totalPrice = item.getBuyPrice() * ((Integer)this.quantity.getOrDefault()).intValue();
      return (totalPrice < 100.0D) ? String.format("%.2f", new Object[] { Double.valueOf(totalPrice) }) : FormatUtils.formatNumber((long)totalPrice);
    } 
    return "INDISPONIBLE";
  }
  
  private String getSellPrice(ShopItem item) {
    if (item == null)
      return "???"; 
    if (item.canSell()) {
      double totalPrice = item.getSellPrice() * ((Integer)this.quantity.getOrDefault()).intValue();
      return (totalPrice < 100.0D) ? String.format("%.2f", new Object[] { Double.valueOf(totalPrice) }) : FormatUtils.formatNumber((long)totalPrice);
    } 
    return "INDISPONIBLE";
  }
  
  public static PriceNode create(double x, double y, double width, double height) {
    return new PriceNode(x, y, width, height);
  }
  
  public PriceNode shopItem(ShopItemSignal shopItem) {
    this.shopItem = shopItem;
    return this;
  }
  
  public PriceNode quantity(IntegerSignal quantitySignal) {
    this.quantity = quantitySignal;
    return this;
  }
  
  public PriceNode textColor(Color color) {
    this.textColor = color;
    return this;
  }
  
  public PriceNode text(String text) {
    this.text = text;
    return this;
  }
  
  public PriceNode buyOrSell(boolean buy) {
    this.buy = buy;
    return this;
  }
  
  public PriceNode sellTextColor(Color color) {
    this.sellTextColor = color;
    return this;
  }
  
  public PriceNode buyTextColor(Color color) {
    this.buyTextColor = color;
    return this;
  }
  
  public PriceNode rectColor(Color color) {
    this.rectColor = color;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\node\PriceNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */