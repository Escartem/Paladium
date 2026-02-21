package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render;

import fr.paladium.palashop.client.ui.kit.render.ShopElementRendererNode;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityViewerNode;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class CosmeticElementRenderRendererNode extends ShopElementRendererNode {
  protected CosmeticElementRenderRendererNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    type("cosmetic_render");
    orElse(node -> {
          FakeEntityPlayerMP player = new FakeEntityPlayerMP(CosmeticPlayer.me().getPlayer());
          EntityViewerNode.create(0.0D, 0.0D, getWidth(), getHeight()).sizeRange(0.5D, 0.9D).rotationPitchRange(0.0D, 0.0D).entity((EntityLivingBase)player).size(0.6D).yOffset(40.0D).rotationYaw(-30.0D).pipeLineLevel(0.0D).effect(MaskNodeEffect::create).onUpdate(()).attach((Node)this);
        });
  }
  
  @NonNull
  public static CosmeticElementRenderRendererNode create(double x, double y, double width, double height) {
    return new CosmeticElementRenderRendererNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\node\render\CosmeticElementRenderRendererNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */