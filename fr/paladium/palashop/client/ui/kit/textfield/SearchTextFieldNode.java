package fr.paladium.palashop.client.ui.kit.textfield;

import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

public class SearchTextFieldNode extends TextFieldNode {
  protected SearchTextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    marginRight(39.0D);
  }
  
  @NonNull
  public static SearchTextFieldNode create(double x, double y, double width) {
    return new SearchTextFieldNode(x, y, width, 49.0D);
  }
  
  @NonNull
  public static SearchTextFieldNode create(double x, double y, double width, double height) {
    return new SearchTextFieldNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    clearChildren();
    TextButtonNode.create(aw(-10.0D) - 29.0D, 10.0D, 29.0D, 29.0D)
      .resourceHeight(10.0D)
      .resource(ResourceConstant.ICON_SEARCH)
      .onClick((node, mouseX, mouseY, clickType) -> keyPressed(' ', 28, InternalContext.create()))
      .attach((Node)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\textfield\SearchTextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */