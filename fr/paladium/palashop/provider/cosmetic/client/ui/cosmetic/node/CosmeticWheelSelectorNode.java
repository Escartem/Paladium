package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.UIShopCosmetic;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render.CosmeticElementThumbnailRendererNode;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketEquipCosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
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
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.CircleNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CosmeticWheelSelectorNode extends Node {
  private CosmeticNavbarElement element;
  
  private BooleanSignal dragging;
  
  private List<Node> slots;
  
  private ListSignal<Integer> equippedCosmeticsSignal;
  
  private boolean empty;
  
  public CosmeticNavbarElement getElement() {
    return this.element;
  }
  
  public BooleanSignal getDragging() {
    return this.dragging;
  }
  
  public List<Node> getSlots() {
    return this.slots;
  }
  
  public ListSignal<Integer> getEquippedCosmeticsSignal() {
    return this.equippedCosmeticsSignal;
  }
  
  public boolean isEmpty() {
    return this.empty;
  }
  
  protected CosmeticWheelSelectorNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static CosmeticWheelSelectorNode create(double x, double y, double width, double height) {
    return new CosmeticWheelSelectorNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    clearChildren();
    this.slots = new LinkedList<>();
    this.equippedCosmeticsSignal = new ListSignal(new ArrayList());
    CosmeticPlayer.OutfitCosmetic outfit = ((CosmeticPlayer)UIShopCosmetic.cosmeticPlayerSignal.getOrDefault()).getOutfit();
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = outfit.get(this.element.getFactory());
    if (!optionalEquippedCosmetic.isPresent()) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger cette catégorie de cosmétique", "palashop")).send();
      return;
    } 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    Color backgroundColor = ColorConstant.BLACK.copy();
    CircleNode.create(0.0D, 0.0D, getWidth())
      .color(backgroundColor)
      .attach(this);
    ImageNode.create(dw(4.0D), dh(4.0D), dw(2.0D), dh(2.0D))
      .resource(Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/wheel/center.png")))
      .attach(this);
    ContainerNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .body(container -> {
          this.slots.clear();
          this.empty = true;
          for (int i = 0; i < (equippedCosmetic.getCosmetics()).length; i++) {
            int index = i;
            Optional<ICosmetic> optionalCosmetic = equippedCosmetic.get(index);
            double[] position = getWheelPosition(index);
            double size = getWidth() * 0.18000000715255737D;
            if (optionalCosmetic.isPresent())
              this.empty = false; 
            Node slotNode = null;
            if (this.equippedCosmeticsSignal.contains(Integer.valueOf(index))) {
              slotNode = ((ImageNode)ImageNode.create(position[0] - size / 2.0D, position[1] - size / 2.0D, size, size).resource(ResourceConstant.ICON_LOADING).color(Color.WHITE).hoveredColor(Color.WHITE).onRender(new NodeRenderCallback<ImageNode>() {
                    public void apply(ImageNode node, double mouseX, double mouseY, float partialTicks) {}
                    
                    @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
                    public void pre(@NonNull ImageNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                      if (node == null)
                        throw new NullPointerException("node is marked non-null but is null"); 
                      if (context == null)
                        throw new NullPointerException("context is marked non-null but is null"); 
                      GL11.glPushMatrix();
                      GL11.glTranslated(node.getX() + node.dw(2.0D), node.getY() + node.dh(2.0D), 0.0D);
                      GL11.glRotated((System.currentTimeMillis() / 2L % 360L), 0.0D, 0.0D, 1.0D);
                      GL11.glScaled(0.5D, 0.5D, 1.0D);
                      GL11.glTranslated(-(node.getX() + node.dw(2.0D)), -(node.getY() + node.dh(2.0D)), 0.0D);
                    }
                    
                    @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
                    public void post(@NonNull ImageNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                      if (node == null)
                        throw new NullPointerException("node is marked non-null but is null"); 
                      if (context == null)
                        throw new NullPointerException("context is marked non-null but is null"); 
                      GL11.glPopMatrix();
                    }
                  })).enabled(()).attach(container);
            } else if (!optionalCosmetic.isPresent()) {
              slotNode = ImageNode.create(position[0] - size / 2.0D, position[1] - size / 2.0D, size, size).resource(this.element.getResource()).color(Color.WHITE.copyAlpha(0.08F)).hoveredColor(Color.WHITE.copyAlpha(0.3F)).enabled(()).attach(container);
            } else {
              slotNode = CosmeticElementThumbnailRendererNode.create(position[0] - size / 2.0D, position[1] - size / 2.0D, size, size).provider(CosmeticProvider.getInstance().getId()).object(optionalCosmetic.get()).onClick(()).enabled(()).attach(container);
            } 
            if (slotNode != null)
              this.slots.add(index, slotNode); 
          } 
        }).watch((Signal)this.equippedCosmeticsSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
    CircleNode.create(0.0D, 0.0D, getWidth())
      .color(Color.TRANSPARENT, backgroundColor.darker(0.7F).copyAlpha(0.7F))
      .visible(node -> (!((Boolean)this.dragging.getOrDefault()).booleanValue() && this.equippedCosmeticsSignal.isEmpty() && this.empty))
      .attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    GL11.glTranslated(0.0D, 0.0D, 1.0D);
    float opacity = hoverValue(1.0F);
    if (((Boolean)this.dragging.getOrDefault()).booleanValue() && this.slots.stream().anyMatch(slot -> slot.isHovered(mouseX, mouseY))) {
      DrawUtils.TEXT.drawText(getX(), getY() + getHeight() * 1.05D, getWidth(), 0.0D, Text.create("Relachez pour équiper", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, (float)dw(20.0D), Color.WHITE.copyAlpha(opacity))).horizontalAlign(Align.CENTER), TextMode.SPLIT);
    } else if (!((Boolean)this.dragging.getOrDefault()).booleanValue() && this.equippedCosmeticsSignal.isEmpty() && this.empty) {
      DrawUtils.TEXT.drawText(getX() + dw(4.0D), getY() + getHeight() * 0.35D, dw(2.0D), 0.0D, Text.create("Faites glisser pour équiper", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, (float)dw(20.0D), Color.WHITE.copyAlpha(opacity))).horizontalAlign(Align.CENTER), TextMode.SPLIT);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, opacity);
      DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - dw(12.0D), getY() + getHeight() * 0.55D, dw(6.0D), dw(6.0D), Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/wheel/mouse.png")));
    } else if (!((Boolean)this.dragging.getOrDefault()).booleanValue() && this.slots.stream().anyMatch(slot -> slot.isHovered(mouseX, mouseY))) {
      DrawUtils.TEXT.drawText(getX(), getY() + getHeight() * 1.05D, getWidth(), 0.0D, Text.create("Cliquez pour déséquiper", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, (float)dw(20.0D), Color.WHITE)).horizontalAlign(Align.CENTER), TextMode.SPLIT);
    } else if (!((Boolean)this.dragging.getOrDefault()).booleanValue()) {
      DrawUtils.TEXT.drawText(getX(), getY() + getHeight() * 1.05D, getWidth(), 0.0D, Text.create("Faites glisser pour équiper", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, (float)dw(20.0D), Color.WHITE)).horizontalAlign(Align.CENTER), TextMode.SPLIT);
    } 
    GL11.glTranslated(0.0D, 0.0D, -1.0D);
  }
  
  public void onSnap(@NonNull ICosmetic cosmetic, @NonNull Node node, Runnable callback) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    int index = this.slots.indexOf(node);
    if (index == -1) {
      if (callback != null)
        callback.run(); 
      return;
    } 
    this.equippedCosmeticsSignal.add(Integer.valueOf(index));
    (new CSPacketEquipCosmetic(cosmetic.getFactory().getId(), cosmetic.getId(), index)).subscribe(result -> {
          this.equippedCosmeticsSignal.remove(Integer.valueOf(index));
          if (callback != null)
            callback.run(); 
        }).send();
  }
  
  private double[] getWheelPosition(int index) {
    if (index < 0 || index >= 4)
      throw new IllegalArgumentException("Invalid index " + index + " for wheel position, must be between 0 and 3"); 
    double x = 0.0D;
    double y = 0.0D;
    if (index == 0) {
      x = dw(2.0D);
      y = dh(5.0D);
    } else if (index == 1) {
      x = dw(5.0D) * 4.0D;
      y = dh(2.0D);
    } else if (index == 2) {
      x = dw(2.0D);
      y = dh(5.0D) * 4.0D;
    } else if (index == 3) {
      x = dw(5.0D);
      y = dh(2.0D);
    } 
    return new double[] { x, y };
  }
  
  @NonNull
  public CosmeticWheelSelectorNode element(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    this.element = element;
    return this;
  }
  
  @NonNull
  public CosmeticWheelSelectorNode dragging(@NonNull BooleanSignal dragging) {
    if (dragging == null)
      throw new NullPointerException("dragging is marked non-null but is null"); 
    this.dragging = dragging;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\node\CosmeticWheelSelectorNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */