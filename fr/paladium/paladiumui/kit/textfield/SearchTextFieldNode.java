package fr.paladium.paladiumui.kit.textfield;

import fr.paladium.paladiumui.kit.button.impl.SearchButtonNode;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

public class SearchTextFieldNode extends TextFieldNode {
  private final SearchButtonNode searchButton;
  
  protected SearchTextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    marginRight(height - 1.0D);
    double searchButtonWidth = 0.8260869565217391D * height;
    this.searchButton = (SearchButtonNode)SearchButtonNode.create(width - searchButtonWidth - 5.0D, 0.0D).iconSize(searchButtonWidth / 2.0D).width(searchButtonWidth).height(height).attach((Node)this).onClick((node, mouseX, mouseY, clickType) -> keyPressed(' ', 28, InternalContext.create()));
  }
  
  @NonNull
  public static SearchTextFieldNode create(double x, double y, double width) {
    return new SearchTextFieldNode(x, y, width, 46.0D);
  }
  
  @NonNull
  public static SearchTextFieldNode create(double x, double y, double width, double height) {
    return new SearchTextFieldNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double searchButtonWidth = 0.8260869565217391D * getHeight();
    this.searchButton.height(getHeight()).width(searchButtonWidth).x(getWidth() - searchButtonWidth - 5.0D);
    marginRight(getHeight() - 1.0D);
    super.draw(mouseX, mouseY, ticks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\textfield\SearchTextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */