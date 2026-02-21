package fr.paladium.palashop.provider.box.client.ui;

import fr.paladium.paladiumui.kit.checkbox.CheckboxNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementThumbnailRendererNode;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.common.shop.network.page.BBPacketGetPages;
import fr.paladium.palashop.provider.box.common.dto.box.BoxClient;
import fr.paladium.palashop.provider.box.common.dto.shop.BoxShopItem;
import fr.paladium.palashop.provider.box.common.network.server.CSPacketBoxStart;
import fr.paladium.palashop.provider.item.dto.ItemShopItem;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.checkbox.CheckboxNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIShopBoxPage extends UIShopPage {
  public static final Resource KEY_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/ui/box/key.png")).linear();
  
  public static final Resource GLOW_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/ui/box/glow.png")).linear();
  
  public static final Resource GIFT_RESOURCE = Resource.of(new ResourceLocation("palashop", "textures/ui/box/gift.png")).linear();
  
  private final BoxClient box;
  
  private final int entityId;
  
  private final BooleanSignal fastSignal;
  
  public UIShopBoxPage(@NonNull String box, Integer entityId) {
    super((ShopNavbarElement)ShopNavbarManager.PAGE_HOME);
    if (box == null)
      throw new NullPointerException("box is marked non-null but is null"); 
    this.box = (BoxClient)PacketSerialUtils.getGson().fromJson(box, BoxClient.class);
    this.entityId = entityId.intValue();
    this.fastSignal = new BooleanSignal(false);
  }
  
  public void init() {
    super.init();
    int tmpKeyCount = 0;
    int tmpFreeSlots = 0;
    for (ItemStack stack : (Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a) {
      if (stack == null || stack.func_77973_b() == null) {
        tmpFreeSlots++;
      } else if (stack.func_77973_b() == this.box.getData().getItem()) {
        tmpKeyCount += stack.field_77994_a;
      } 
    } 
    int keyCount = Math.min(tmpKeyCount, 16);
    int freeSlots = tmpFreeSlots;
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    TextNode.create(42.0D, 159.0D)
      .text(Text.create(Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]").matcher(this.box.getData().getName().toUpperCase() + " BOX").replaceAll(""), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ImageNode.create(471.0D, 282.0D)
      .resource(Resource.of(this.box.getData().getResource().getThumbnail()).linear())
      .anchorX(Align.CENTER)
      .height(300.0D)
      .attach((UI)this);
    FlexNode.horizontal(471.0D, 595.0D, 20.0D)
      .margin(14.0D)
      .align(Align.CENTER)
      .body(flex -> {
          CheckboxNode.create(0.0D, 0.0D, flex.h()).onChange(()).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create("Ouverture rapide", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, (float)flex.h(), Color.WHITE))).attach(flex);
        }).anchorX(Align.CENTER)
      .attach((UI)this);
    FlexNode.horizontal(471.0D, 633.0D, 66.0D)
      .margin(9.0D)
      .body(flex -> {
          ((TextButtonNode)((TextButtonNode)((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 206.0D, flex.h()).text("Ouvrir").onClick(())).onInit(())).enabled(())).attach(flex);
          ((TextButtonNode)((TextButtonNode)((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 206.0D, flex.h()).text("Ouvrir x10").resource(GIFT_RESOURCE, 34.0D, -5.0D, true).onClick(())).onInit(())).enabled(())).attach(flex);
        }).anchorX(Align.CENTER)
      .attach((UI)this);
    RectNode.create(82.0D, 740.0D, 811.0D, 292.0D)
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(16.5F))
      .body(rect -> {
          DoubleSignal widthSignal = new DoubleSignal(0.0D);
          FlexNode.vertical(35.0D, 26.5D, rect.aw(-70.0D)).margin(21.0D).body(()).attach(rect);
          FlexNode.horizontal(rect.aw(-35.0D), 35.0D, 38.0D).margin(6.0D).body(()).onUpdate(()).anchorX(Align.END).attach(rect);
        }).effect(MaskNodeEffect::create)
      .attach((UI)this);
    ContainerNode.create(800.0D, 220.0D, 1120.0D, 860.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(1091.0D, 102.0D, 692.0D))
      .body(container -> {
          TextNode.create(1002.0D - container.getX(), 50.0D).text(Text.create("Contenu de la box", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE))).zindex(1).attach(container);
          List<IShopItem> rewards = (List<IShopItem>)this.box.getRewards().stream().sorted(()).collect(Collectors.toList());
          GridNode.create(1002.0D - container.getX(), 102.0D, 874.0D, 0.0D).margin(24.0D).body(()).attach(container);
          GridNode.create(1002.0D - container.getX(), 102.0D, 874.0D, 0.0D).margin(24.0D).body(()).attach(container);
        }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\clien\\ui\UIShopBoxPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */