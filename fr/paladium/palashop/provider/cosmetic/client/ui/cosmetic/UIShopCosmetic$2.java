package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.mouse.NodeMousePressedCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.Optional;
import lombok.NonNull;

class null implements NodeMousePressedCallback<RectNode> {
  public void apply(RectNode node, double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
  public void pre(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
  }
  
  @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
  public void post(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (!node.isEnabled())
      return; 
    CosmeticPlayer.OutfitCosmetic outfit = ((CosmeticPlayer)UIShopCosmetic.cosmeticPlayerSignal.getOrDefault()).getOutfit();
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = outfit.get(loadedCosmetic.getFactory().getId());
    if (!optionalEquippedCosmetic.isPresent())
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    if (equippedCosmetic.getType() == CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.UNIQUE) {
      boolean equipped = equippedCosmetic.has((ICosmetic)loadedCosmetic);
      UIShopCosmetic.access$000(UIShopCosmetic.this).add(loadedCosmetic);
      if (equipped) {
        (new CSPacketUnEquipCosmetic(loadedCosmetic.getFactory().getId(), loadedCosmetic.getId(), 0)).subscribe(result -> UIShopCosmetic.access$000(UIShopCosmetic.this).remove(loadedCosmetic)).send();
      } else {
        (new CSPacketEquipCosmetic(loadedCosmetic.getFactory().getId(), loadedCosmetic.getId(), 0)).subscribe(result -> UIShopCosmetic.access$000(UIShopCosmetic.this).remove(loadedCosmetic)).send();
      } 
      context.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\UIShopCosmetic$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */