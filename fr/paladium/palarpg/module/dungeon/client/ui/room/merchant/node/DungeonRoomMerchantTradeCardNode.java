package fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.node;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palarpg.module.dungeon.client.ui.room.merchant.UIDungeonRoomMerchantTrade;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.network.room.merchant.CSPacketRPGDungeonRoomMerchantSelect;
import fr.paladium.palarpg.module.dungeon.common.utils.RomanNumberFormatter;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DungeonRoomMerchantTradeCardNode extends Node {
  private static final Resource GLOW_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/glow.png")).linear().blocking();
  
  private static final Resource BACK_MEPHISTO_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/mephisto/back.png")).linear().blocking();
  
  private static final Resource FRONT_MEPHISTO_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/mephisto/front.png")).linear().blocking();
  
  private static final Resource BACK_MAMMON_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/mammon/back.png")).linear().blocking();
  
  private static final Map<RPGItemRarity, Resource> FRONT_MAMMON_TEXTURE = new HashMap<>();
  
  private final TweenAnimator rotationAnimator;
  
  private int index;
  
  private UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData data;
  
  private boolean active;
  
  static {
    for (RPGItemRarity rarity : RPGItemRarity.values())
      FRONT_MAMMON_TEXTURE.put(rarity, Resource.of(new ResourceLocation("palarpg", "textures/gui/room/merchant/mammon/front/" + rarity.name().toLowerCase() + ".png")).linear().blocking()); 
  }
  
  public TweenAnimator getRotationAnimator() {
    return this.rotationAnimator;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData getData() {
    return this.data;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  protected DungeonRoomMerchantTradeCardNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.rotationAnimator = TweenAnimator.create(180.0F);
    onRender(new NodeRenderCallback<DungeonRoomMerchantTradeCardNode>() {
          public void apply(DungeonRoomMerchantTradeCardNode node, double mouseX, double mouseY, float partialTicks) {}
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
          public void pre(@NonNull DungeonRoomMerchantTradeCardNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            DungeonRoomMerchantTradeCardNode.this.onPreRender(mouseX, mouseY, partialTicks);
          }
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
          public void post(@NonNull DungeonRoomMerchantTradeCardNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            DungeonRoomMerchantTradeCardNode.this.onPostRender(mouseX, mouseY, partialTicks);
          }
        });
  }
  
  @NonNull
  public static DungeonRoomMerchantTradeCardNode create(double x, double y, double width, double height) {
    return new DungeonRoomMerchantTradeCardNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (getTrade() == null)
      return; 
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(isMephisto() ? BACK_MEPHISTO_TEXTURE : BACK_MAMMON_TEXTURE)
      .zlevel(-0.01D)
      .visible(node -> (this.rotationAnimator.getValue() >= 90.0F && this.rotationAnimator.getValue() <= 270.0F))
      .attach(this);
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(isMephisto() ? FRONT_MEPHISTO_TEXTURE : FRONT_MAMMON_TEXTURE.get(((EntityDungeonMerchant.EntityDungeonMerchantMammonTrade)getTrade()).getOutput().getRarity()))
      .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
      .attach(this);
    if (isMephisto()) {
      EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade mephistoTrade = getTrade();
      TextNode.create(dw(2.0D), 75.0D)
        .text(Text.create(mephistoTrade.getOutput().getName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.decode("#F6F9E8"))).horizontalAlign(Align.CENTER))
        .mode(TextMode.SPLIT)
        .width(aw(-25.0D))
        .anchor(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
      TextNode.create(dw(2.0D), 140.0D)
        .text(Text.create("-" + String.format("%.0f", new Object[] { Double.valueOf(mephistoTrade.getInput().getValue() * 100.0D) }) + "%", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 48.0F, Color.decode("#FF3C3C"))))
        .anchorX(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
      TextNode.create(dw(2.0D), 231.0D)
        .text(Text.create(mephistoTrade.getInput().getStat().getFormattedStatName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 21.0F, Color.WHITE)))
        .anchorX(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
      TextNode.create(dw(2.0D), 386.0D)
        .text(Text.create("+" + String.format("%.0f", new Object[] { Double.valueOf(mephistoTrade.getOutput().getValue() * 100.0D) }) + "%", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 48.0F, Color.decode("#00E397"))))
        .anchorX(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
      TextNode.create(dw(2.0D), 478.0D)
        .text(Text.create(mephistoTrade.getOutput().getStat().getFormattedStatName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 21.0F, Color.WHITE)))
        .anchorX(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
    } else {
      EntityDungeonMerchant.EntityDungeonMerchantMammonTrade mammonTrade = getTrade();
      TextNode.create(dw(2.0D), 62.0D)
        .text(Text.create("marché " + RomanNumberFormatter.format(this.index + 1), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.decode("#F6F9E8"))))
        .anchorX(Align.CENTER)
        .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
        .attach(this);
      ItemNode.create(122.0D, 218.0D, 62.0D)
        .item(mammonTrade.getInput().getItem())
        .itemHover(true)
        .attach(this);
      ItemNode.create(341.0D, 403.0D, 62.0D)
        .item(mammonTrade.getOutput().getItem())
        .itemHover(true)
        .anchor(Align.CENTER)
        .attach(this);
    } 
    TextButtonNode.create(dw(2.0D) - 122.0D, ah(-35.0D))
      .text("choisir")
      .width(245.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          this.active = true;
          for (DungeonRoomMerchantTradeCardNode child : getParent().getChildren(DungeonRoomMerchantTradeCardNode.class)) {
            child.enabled(());
            ((TextButtonNode)child.getChildren(TextButtonNode.class).getLast()).visible(());
            if (child != this)
              child.flip(); 
          } 
          (new Thread(())).start();
        }).enabled(node -> isEnabled())
      .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
      .attach(this);
  }
  
  private final void onPreRender(double mouseX, double mouseY, float partialTicks) {
    this.rotationAnimator.update();
    double zlevel = 0.0D;
    for (Node node : getChildren().recursive()) {
      if (node.getZlevel() < 0.0D)
        continue; 
      node.zlevel(zlevel);
      zlevel += 0.01D;
    } 
    double centerX = getX() + dw(2.0D);
    double centerY = getY() + dh(2.0D);
    if (getTrade() != null && (isHovered() || this.active) && this.rotationAnimator.getValue() == 360.0F) {
      if (isMephisto()) {
        RPGItemRarity.LEGENDARY.getColor().copyAlpha(this.active ? 1.0F : hoverValue(1.0F)).bind();
      } else {
        ((EntityDungeonMerchant.EntityDungeonMerchantMammonTrade)getTrade()).getOutput().getRarity().getColor().copyAlpha(this.active ? 1.0F : hoverValue(1.0F)).bind();
      } 
      DrawUtils.RESOURCE.drawImage(getX() - dw(6.0D), getY() - dh(6.0D), w() + dw(3.0D), h() + dh(3.0D), GLOW_TEXTURE);
      Color.reset();
    } 
    GL11.glPushMatrix();
    GL11.glDisable(2884);
    GL11.glTranslated(0.0D, 0.0D, dw(2.0D));
    GL11.glTranslated(centerX, centerY, 0.0D);
    GL11.glRotated((this.rotationAnimator.getValue() % 360.0F), 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(-centerX, -centerY, 0.0D);
  }
  
  private final void onPostRender(double mouseX, double mouseY, float partialTicks) {
    GL11.glEnable(2884);
    GL11.glPopMatrix();
  }
  
  private final boolean isMephisto() {
    return getTrade() instanceof EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade;
  }
  
  private final <T extends EntityDungeonMerchant.IEntityDungeonMerchantTrade> T getTrade() {
    if (this.data == null || (this.data.getTrades()).length <= this.index)
      return null; 
    return (T)this.data.getTrades()[this.index];
  }
  
  @NonNull
  public DungeonRoomMerchantTradeCardNode flip() {
    if (getTrade() == null || !ZUI.isOpen(getUi()))
      return this; 
    if (this.rotationAnimator.getValue() == 180.0F) {
      SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/loot/" + (isMephisto() ? RPGItemRarity.LEGENDARY : ((EntityDungeonMerchant.EntityDungeonMerchantMammonTrade)getTrade()).getOutput().getRarity()).name().toLowerCase() + ".ogg")).volume(3.0F).build(SoundCategory.MASTER).play();
    } else {
      SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/loot/flip.ogg")).volume(1.0F).build(SoundCategory.MASTER).play();
    } 
    this.rotationAnimator.sequence(1000.0F, (this.rotationAnimator.getValue() == 180.0F) ? 360.0F : 180.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> {
          if (this.rotationAnimator.getValue() == 180.0F)
            ZUI.close(getUi()); 
        }).start();
    return this;
  }
  
  @NonNull
  public DungeonRoomMerchantTradeCardNode data(int index, @NonNull UIDungeonRoomMerchantTrade.UIDungeonRoomMerchantTradeData data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    this.index = index;
    this.data = data;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\room\merchant\node\DungeonRoomMerchantTradeCardNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */