package fr.paladium.palamod.modules.shop.client.ui.container;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.modules.shop.PShop;
import fr.paladium.palamod.modules.shop.client.ui.node.PriceNode;
import fr.paladium.palamod.modules.shop.client.ui.node.QuantitySelectorNode;
import fr.paladium.palamod.modules.shop.client.ui.node.ShopSlotNode;
import fr.paladium.palamod.modules.shop.client.ui.signal.ShopItemSignal;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.palamod.modules.shop.network.CSPacketBuyShopItem;
import fr.paladium.palamod.modules.shop.network.CSPacketSellShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ItemShopDisplayContainer extends RectNode {
  private final ShopItemSignal shopItemSignal = new ShopItemSignal(null);
  
  public ShopItemSignal getShopItemSignal() {
    return this.shopItemSignal;
  }
  
  private QuantitySelectorNode quantitySelectorNode = null;
  
  protected ItemShopDisplayContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          ((TextNode)TextNode.create(37.0D, 7.0D).mode(TextMode.OVERFLOW).text(Text.create("", PaladiumText.HEADER.copy().color(Color.WHITE).shadow(new Color(105, 106, 112)).fontSize(50.0F), TextOverflow.ELLIPSIS)).onInit(())).width(680.0D).watch((Signal)this.shopItemSignal).attach((Node)this);
          this.quantitySelectorNode = (QuantitySelectorNode)QuantitySelectorNode.create(43.0D, 415.0D, 241.0D, 105.0D).attach((Node)this);
          ((ShopSlotNode)ShopSlotNode.create(37.0D, 129.0D).resource(ShopSlotNode.getSlotHover()).hoverResource((ResourceLocation)null).onInit(())).watch((Signal)this.shopItemSignal).size(247.0D, 247.0D).attach((Node)this);
          PriceNode.create(339.0D, 130.0D, 405.0D, 95.0D).buyOrSell(true).text("PRIX D'ACHAT").shopItem(this.shopItemSignal).quantity(this.quantitySelectorNode.getQuantitySignal()).watch((Signal)this.shopItemSignal).watch((Signal)this.quantitySelectorNode.getQuantitySignal()).attach((Node)this);
          PriceNode.create(339.0D, 257.0D, 405.0D, 95.0D).buyOrSell(false).text("PRIX DE VENTE").shopItem(this.shopItemSignal).quantity(this.quantitySelectorNode.getQuantitySignal()).watch((Signal)this.shopItemSignal).watch((Signal)this.quantitySelectorNode.getQuantitySignal()).attach((Node)this);
          ((ButtonNode)TextButtonNode.create(368.0D, 463.0D).text("ACHETER").onInit(())).onClick(()).watch((Signal)this.shopItemSignal).attach((Node)this);
          ((ButtonNode)TextButtonNode.create(573.0D, 463.0D).text("VENDRE").color(ButtonNode.GREEN_COLOR).onInit(())).onClick(()).watch((Signal)this.shopItemSignal).hoverLines(()).attach((Node)this);
        });
  }
  
  public static ItemShopDisplayContainer create(double x, double y, double width, double height) {
    return new ItemShopDisplayContainer(x, y, width, height);
  }
  
  public void setSelectedItem(ShopItem shopItem) {
    this.shopItemSignal.set(shopItem);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\clien\\ui\container\ItemShopDisplayContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */