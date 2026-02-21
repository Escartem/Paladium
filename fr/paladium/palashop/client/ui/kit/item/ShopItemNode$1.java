package fr.paladium.palashop.client.ui.kit.item;

import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

class null implements NodeRenderCallback<ShopItemNode> {
  public void apply(ShopItemNode node, double mouseX, double mouseY, float partialTicks) {}
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull ShopItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    ShopItemNode.access$000(ShopItemNode.this, mouseX, mouseY, partialTicks);
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull ShopItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    ShopItemNode.access$100(ShopItemNode.this, mouseX, mouseY, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\item\ShopItemNode$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */