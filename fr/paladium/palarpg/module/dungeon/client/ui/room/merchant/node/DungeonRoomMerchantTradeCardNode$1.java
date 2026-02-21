package fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.node;

import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

class null implements NodeRenderCallback<DungeonRoomMerchantTradeCardNode> {
  public void apply(DungeonRoomMerchantTradeCardNode node, double mouseX, double mouseY, float partialTicks) {}
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull DungeonRoomMerchantTradeCardNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    DungeonRoomMerchantTradeCardNode.access$000(DungeonRoomMerchantTradeCardNode.this, mouseX, mouseY, partialTicks);
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull DungeonRoomMerchantTradeCardNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    DungeonRoomMerchantTradeCardNode.access$100(DungeonRoomMerchantTradeCardNode.this, mouseX, mouseY, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\room\merchant\node\DungeonRoomMerchantTradeCardNode$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */