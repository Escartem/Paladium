package fr.paladium.pet.common.store.provider.client.render.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.store.provider.server.dto.PetSkinShopItem;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PetSkinShopRenderRenderer implements ShopElementRenderer<PetSkinShopItem> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull PetSkinShopItem element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (element.getPetId() == null || element.getPetId().isEmpty())
      return; 
    FakeEntityPlayerMP player = new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    PetPlayer eep = PetPlayer.get((EntityPlayer)player);
    eep.setCurrentSkin(element.getPetId());
    eep.setUnlockedSkin(element.getPetId());
    EntityViewerNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .sizeRange(0.6D, 1.2D)
      .rotationPitchRange(0.0D, 0.0D)
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .rotationYaw(-30.0D)
      .yOffset(20.0D)
      .pipeLineLevel(0.0D)
      .effect(MaskNodeEffect::create)
      .onUpdate(node -> player.field_70173_aa = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa)
      .attach(container);
  }
  
  public boolean isValid(@NonNull PetSkinShopItem element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return true;
  }
  
  @NonNull
  public Class<PetSkinShopItem> getElementType() {
    return PetSkinShopItem.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\client\render\shop\PetSkinShopRenderRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */