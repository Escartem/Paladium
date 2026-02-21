package fr.paladium.palarpg.module.dungeon.client.ui.chest.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
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
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.EnumMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DungeonChestItemNode extends Node {
  private static final Resource BACK_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/back.png")).linear();
  
  private static final Resource GLOW_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/glow.png")).linear();
  
  private static final Map<RPGItemRarity, Resource> RARITY_TEXTURES = new EnumMap<>(RPGItemRarity.class);
  
  private final TweenAnimator glowAnimator;
  
  private final TweenAnimator rotationAnimator;
  
  private RPGDungeonPlayerData.RPGDungeonItem item;
  
  static {
    for (RPGItemRarity rarity : RPGItemRarity.values())
      RARITY_TEXTURES.put(rarity, Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/" + rarity.name().toLowerCase() + ".png")).linear()); 
  }
  
  public TweenAnimator getGlowAnimator() {
    return this.glowAnimator;
  }
  
  public TweenAnimator getRotationAnimator() {
    return this.rotationAnimator;
  }
  
  public RPGDungeonPlayerData.RPGDungeonItem getItem() {
    return this.item;
  }
  
  protected DungeonChestItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.glowAnimator = TweenAnimator.create(0.0F);
    this.rotationAnimator = TweenAnimator.create(180.0F);
    onRender(new NodeRenderCallback<DungeonChestItemNode>() {
          public void apply(DungeonChestItemNode node, double mouseX, double mouseY, float partialTicks) {}
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
          public void pre(@NonNull DungeonChestItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            DungeonChestItemNode.this.onPreRender(mouseX, mouseY, partialTicks);
          }
          
          @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
          public void post(@NonNull DungeonChestItemNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
            if (node == null)
              throw new NullPointerException("node is marked non-null but is null"); 
            if (context == null)
              throw new NullPointerException("context is marked non-null but is null"); 
            DungeonChestItemNode.this.onPostRender(mouseX, mouseY, partialTicks);
          }
        });
  }
  
  @NonNull
  public static DungeonChestItemNode create(double x, double y, double width, double height) {
    return new DungeonChestItemNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.item == null)
      return; 
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(BACK_TEXTURE)
      .zlevel(-0.01D)
      .visible(node -> (this.rotationAnimator.getValue() >= 90.0F && this.rotationAnimator.getValue() <= 270.0F))
      .attach(this);
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(RARITY_TEXTURES.get(this.item.getRarity()))
      .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
      .attach(this);
    ItemNode.create(dw(2.0D) - 42.0D, 156.0D, 84.0D)
      .item(this.item.getItem())
      .itemHover(true)
      .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
      .attach(this);
    int fontSize = 17;
    Text text = Text.create(this.item.getItem().func_82833_r(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, fontSize, Color.WHITE));
    while (text.getWidth() > getWidth() * 0.81D && fontSize > 1)
      text = Text.create(this.item.getItem().func_82833_r(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, --fontSize, Color.WHITE)); 
    TextNode.create(dw(2.0D), 382.0D)
      .text(text.horizontalAlign(Align.CENTER))
      .width(getWidth() * 0.81D)
      .visible(node -> (this.rotationAnimator.getValue() < 90.0F || this.rotationAnimator.getValue() > 270.0F))
      .anchorX(Align.CENTER)
      .attach(this);
  }
  
  private final void onPreRender(double mouseX, double mouseY, float partialTicks) {
    this.glowAnimator.update();
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
    if (this.item != null && this.glowAnimator.getValue() > 0.0F) {
      this.item.getRarity().getColor().copyAlpha(this.glowAnimator.getValue()).bind();
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
  
  @NonNull
  public DungeonChestItemNode flip() {
    if (this.item == null || !ZUI.isOpen(getUi()))
      return this; 
    if (isVisible())
      SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/loot/" + this.item.getRarity().name().toLowerCase() + ".ogg")).volume(3.0F).build(SoundCategory.MASTER).play(); 
    this.glowAnimator.sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start().setCallback(t1 -> {
          SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/loot/flip.ogg")).volume(1.0F).build(SoundCategory.MASTER).play();
          this.rotationAnimator.sequence(1000.0F, 360.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(()).start();
        }).start();
    return this;
  }
  
  @NonNull
  public DungeonChestItemNode item(@NonNull RPGDungeonPlayerData.RPGDungeonItem item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\chest\node\DungeonChestItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */