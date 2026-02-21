package fr.paladium.palashop.client.ui.impl.buy.offer;

import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementRenderRendererNode;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeInitCallback;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

class null implements NodeInitCallback<ShopElementRenderRendererNode> {
  public void apply(@NonNull ShopElementRenderRendererNode node) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull ShopElementRenderRendererNode node, @NonNull InternalContext context) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    node.object(((ConditionalBuyableObject)((ShopOffer)((ConditionalBuyableObject)UIShopBuyDefaultOffer.access$100(UIShopBuyDefaultOffer.this).getOrDefault()).getObject().getItem()).getConditionalShopItems().get(((Integer)UIShopBuyDefaultOffer.access$000(UIShopBuyDefaultOffer.this).getOrDefault()).intValue())).getObject().getItem());
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull ShopElementRenderRendererNode node, @NonNull InternalContext context) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\buy\offer\UIShopBuyDefaultOffer$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */