package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.selector.SelectorNode;
import fr.paladium.paladiumui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palavanillagui.client.ClientProxy;
import fr.paladium.palavanillagui.client.ui.inventory.UIDirectory;
import fr.paladium.palavanillagui.client.ui.util.node.ShortcutNode;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.selector.SelectorNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

public class ShortcutContainer extends RectNode {
  private final StringSignal orderSignal = new StringSignal("ALPHABETICAL");
  
  private final StringSignal searchSignal = new StringSignal("");
  
  protected ShortcutContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          ContainerNode.create(41.0D, 123.0D, 924.0D, 419.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(893.0D, 0.0D, 419.0D)).onInit(()).watch((Signal)((UIDirectory)getUi()).getEditMode()).watch((Signal)((UIDirectory)getUi()).getListSignal()).watch((Signal)this.orderSignal).watch((Signal)this.searchSignal).wait((ISignal)((UIDirectory)getUi()).getListSignal()).attach((Node)this);
          SelectorNode.create(41.0D, 36.0D, 287.0D).onChange(()).zindex(1).body(()).attach((Node)this);
          SearchTextFieldNode.create(693.0D, 40.0D, 273.0D, 46.13D).placeholder(TTT.format("directory.search.placeholder", new Object[0])).marginLeft(15.0D).onChange(()).attach((Node)this);
        });
  }
  
  public static ShortcutContainer create(double x, double y, double width, double height) {
    return new ShortcutContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\ShortcutContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */