package fr.paladium.palashop.client.ui.impl.pb;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.client.ui.impl.pb.node.ShopTebexPackageNode;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.common.tebex.TebexManager;
import fr.paladium.palashop.common.tebex.dto.TebexPackage;
import fr.paladium.palashop.common.tebex.network.BBPacketGetTebex;
import fr.paladium.palashop.server.config.tebex.TebexConfig;
import fr.paladium.palashop.server.config.tebex.TebexPackageConfig;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

@UIDataPopup(active = true)
@UIData(backgroundColor = "#17181BF2", zlevel = 1000.0D)
public class UIShopPBPopup extends UIShopBase {
  private static final StringSignal tebexTokenSignal = new StringSignal();
  
  private static final ListSignal<Map.Entry<TebexPackage, TebexPackageConfig>> tebexPackageListSignal = new ListSignal();
  
  private final long required;
  
  public long getRequired() {
    return this.required;
  }
  
  public UIShopPBPopup() {
    this(0L);
  }
  
  public UIShopPBPopup(long required) {
    this.required = required;
  }
  
  public void init() {
    (new BBPacketGetTebex()).subscribe(result -> {
          if (result == null || result.getToken() == null || result.getToken().isEmpty() || result.getPackages() == null || result.getPackages().isEmpty()) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger la page d'achat", "palashop")).send();
            ZUI.close((UI)this);
            return;
          } 
          tebexTokenSignal.set(result.getToken());
          Map.Entry[] arrayOfEntry = new Map.Entry[result.getPackages().size()];
          for (int i = 0; i < result.getPackages().size(); i++) {
            int index = i;
            TebexPackageConfig tebexPackageConfig = result.getPackages().get(index);
            TebexManager.getPackageById(result.getToken(), tebexPackageConfig.getPackageId()).thenAccept(()).exceptionally(());
          } 
        }).send();
    TextNode.create(54.0D, 66.0D)
      .text(Text.create("Acheter des PB", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 60.0F, Color.WHITE)))
      .attach((UI)this);
    FlexNode.horizontal(564.0D, 90.0D, 37.0D)
      .align(Align.CENTER)
      .body(flex -> {
          TextNode.create(0.0D, 0.0D).text(Text.create("Il vous manque", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F, Color.WHITE))).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(" " + String.format("%,d", new Object[] { Long.valueOf(this.required) }).replace(",", ".").replace(" ", ".") + " ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 30.0F, Color.WHITE))).attach(flex);
          ImageNode.create(0.0D, 0.0D).resource(ResourceConstant.ITEM_PB).height(flex.getHeight() * 0.699999988079071D).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(" pour effectuer votre achat", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F, Color.WHITE))).attach(flex);
        }).visible(node -> (this.required > 0L))
      .attach((UI)this);
    ContainerNode.create(55.0D, 174.0D, 1810.0D, 855.0D)
      .onMount(container -> GridNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight()).margin(14.0D).body(()).attach(container))
      
      .skeleton(container -> GridNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight()).margin(14.0D).body(()))
      
      .wait((ISignal)tebexTokenSignal)
      .wait((ISignal)tebexPackageListSignal)
      .attach((UI)this);
    ImageNode.create(1840.0D, 89.0D, 20.0D, 20.0D)
      .resource(ResourceConstant.ICON_CLOSE)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close((UI)this))
      
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\pb\UIShopPBPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */