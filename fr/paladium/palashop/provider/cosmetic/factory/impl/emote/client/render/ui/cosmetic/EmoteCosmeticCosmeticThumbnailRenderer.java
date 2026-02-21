package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.cosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.utils.player.FakeEntityPlayerMP;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import fr.paladium.zephyrui.lib.ui.node.property.position.PositionProperty;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;

public class EmoteCosmeticCosmeticThumbnailRenderer implements ShopElementRenderer<ICosmeticClient> {
  @SideOnly(Side.CLIENT)
  public void render(@NonNull ICosmeticClient element, @NonNull Node container) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (container == null)
      throw new NullPointerException("container is marked non-null but is null"); 
    if (!(element instanceof EmoteCosmeticClient))
      return; 
    EmoteCosmeticClient cosmetic = (EmoteCosmeticClient)element;
    FakeEntityPlayerMP player = new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    ((CosmeticPlayer.EquippedCosmetic)cosmeticPlayer.getOutfit().get(cosmetic.getFactory().getId()).get()).equip((ICosmetic)cosmetic);
    cosmetic.onLoaded(loadedCosmetic -> {
          ((LindwormAnimatable)loadedCosmetic.getEmoteModel().getOrCreateAnimatable((Entity)player)).playAnimation(loadedCosmetic.getEmoteModel().getAnimationFile().getFirstAnimation(), true);
          if (loadedCosmetic.hasEffectModel() && loadedCosmetic.getEffectModel().isAnimated()) {
            String effectAnimationName = loadedCosmetic.getEffectModel().getAnimationFile().getFirstAnimation();
            if (effectAnimationName != null)
              ((LindwormAnimatable)loadedCosmetic.getEffectModel().getOrCreateAnimatable((Entity)player)).playAnimation(effectAnimationName, true); 
          } 
        });
    ((EntityNode)EntityNode.create(0.0D, 0.0D, container.getWidth(), container.getHeight())
      .entity((EntityLivingBase)player)
      .size(0.8D)
      .rotationYaw(-30.0D)
      .yOffset(10.0D)
      .pipeLineLevel(0.0D)
      .onRender(new NodeRenderCallback<EntityNode>() {
          public void apply(EntityNode node, double mouseX, double mouseY, float partialTicks) {}
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
          public void pre(@NonNull EntityNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            if (node.getParent().getParent().getPosition() == PositionProperty.RELATIVE) {
              node.getUi().startMask(node.getX(), node.getY(), node.getWidth(), node.getHeight());
            } else {
              node.entity((EntityLivingBase)new FakeEntityPlayerMP((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
            } 
          }
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
          public void post(@NonNull EntityNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            if (node.getParent().getParent().getPosition() == PositionProperty.RELATIVE)
              node.getUi().stopMask(); 
          }
        })).attach(container);
  }
  
  public boolean isValid(@NonNull ICosmeticClient element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return element.getFactory().getId().equals(EmoteCosmeticFactory.ID);
  }
  
  @NonNull
  public Class<ICosmeticClient> getElementType() {
    return ICosmeticClient.class;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\rende\\ui\cosmetic\EmoteCosmeticCosmeticThumbnailRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */