package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.render.ui.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.dto.CloakCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class CloakCosmeticShopRenderRenderer implements ShopElementRenderer<CosmeticShopItem> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull CosmeticShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (element.getCosmeticFactory() == null || element.getCosmeticId() == null)
      return; 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(element.getCosmeticFactory());
    if (!optionalFactory.isPresent())
      return; 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(element.getCosmeticId());
    if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof CloakCosmeticClient))
      return; 
    CloakCosmeticClient cosmetic = (CloakCosmeticClient)optionalCosmetic.get();
    FakeEntityPlayerMP player = new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    ((CosmeticPlayer.EquippedCosmetic)cosmeticPlayer.getOutfit().get(cosmetic.getFactory().getId()).get()).equip((ICosmetic)cosmetic);
    EntityViewerNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .sizeRange(0.6D, 1.2D)
      .rotationPitchRange(0.0D, 0.0D)
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .rotationYaw(210.0D)
      .pipeLineLevel(0.0D)
      .yOffset(20.0D)
      .effect(MaskNodeEffect::create)
      .attach(container);
  }
  
  public boolean isValid(@NonNull CosmeticShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (element.getCosmeticFactory() != null && element.getCosmeticFactory().equals(CloakCosmeticFactory.ID));
  }
  
  @NonNull
  public Class<CosmeticShopItem> getElementType() {
    return CosmeticShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\rende\\ui\shop\CloakCosmeticShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */