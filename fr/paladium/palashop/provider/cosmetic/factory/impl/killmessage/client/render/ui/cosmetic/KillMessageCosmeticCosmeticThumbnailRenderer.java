package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.cosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class KillMessageCosmeticCosmeticThumbnailRenderer implements ShopElementRenderer<ICosmeticClient> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull ICosmeticClient element, @NonNull final Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (!(element instanceof KillMessageCosmeticClient))
      return; 
    ((KillMessageCosmeticClient)element).onLoaded(cosmetic -> {
          String message = cosmetic.getFormattedMessage((Minecraft.func_71410_x()).field_71439_g.func_70005_c_(), "{Victime}").replace("§[", "[");
          Text text = Text.create(message, TextInfo.create((IFont)PaladiumFont.MINECRAFT, (float)container.getHeight() * 0.1F, Color.WHITE)).align(Align.CENTER, Align.CENTER);
          double width = text.aw(40.0D);
          double height = text.getHeight() * 2.0D;
          TweenAnimator tweenAnimator = TweenAnimator.create(0.0F);
          ((RectNode)((RectNode)RectNode.create(0.0D, container.dh(2.0D) - height / 2.0D, width, height).color(ColorConstant.BLACK).effect((NodeEffect)RoundedNodeEffect.create((float)(height * 0.10999999940395355D))).onDraw(())).onRender(new NodeRenderCallback<RectNode>() {
                public void apply(RectNode node, double mouseX, double mouseY, float partialTicks) {}
                
                @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
                public void pre(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                  if (context == null)
                    throw new NullPointerException("context is marked non-null but is null"); 
                  UI ui = container.getUi();
                  int finalWidth = (int)ui.getAbsoluteWidth(container.getWidth());
                  int finalHeight = (int)ui.getAbsoluteHeight(container.getHeight());
                  int x = (int)ui.getAbsoluteX(container.getAbsoluteX());
                  int y = (int)(ui.getHeight() - ui.getAbsoluteY(container.getAbsoluteY()) + finalHeight);
                  GL11.glPushAttrib(524288);
                  GL11.glEnable(3089);
                  GL11.glScissor(x, y, finalWidth + 1, finalHeight);
                }
                
                @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
                public void post(@NonNull RectNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                  if (context == null)
                    throw new NullPointerException("context is marked non-null but is null"); 
                  GL11.glDisable(3089);
                  GL11.glPopAttrib();
                }
              })).attach(container);
        });
  }
  
  public boolean isValid(@NonNull ICosmeticClient element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return element.getFactory().getId().equals(KillMessageCosmeticFactory.ID);
  }
  
  @NonNull
  public Class<ICosmeticClient> getElementType() {
    return ICosmeticClient.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\rende\\ui\cosmetic\KillMessageCosmeticCosmeticThumbnailRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */