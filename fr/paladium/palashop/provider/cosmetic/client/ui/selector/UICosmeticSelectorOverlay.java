package fr.paladium.palashop.provider.cosmetic.client.ui.selector;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.node.render.CosmeticElementThumbnailRendererNode;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.event.CosmeticExecuteWheelEvent;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketExecuteWheelCosmetic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeRenderCallback;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.CircleNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
@UIDataOverlay(active = true, interaction = @UIDataOverlayInteraction(active = true, cancelClick = true, cancelKeyboard = false, cancelScroll = false), render = @UIDataOverlayRender(post = true))
public class UICosmeticSelectorOverlay extends UI {
  private static final Map<CosmeticNavbarElement, UICosmeticSelectorOverlayCache> CACHE_MAP = new HashMap<>();
  
  private static final double WHEEL_RADIUS = 360.0D;
  
  private static final Color BACKGROUND_COLOR = ColorConstant.BLACK.copy();
  
  private static CosmeticNavbarElement lastElement;
  
  private final CosmeticPlayer cosmeticPlayer = CosmeticPlayer.me();
  
  private final IntegerSignal selectedSignal = new IntegerSignal(0);
  
  private final Map<CosmeticNavbarElement, CosmeticPlayer.EquippedCosmetic> equippedCosmeticMap = new LinkedHashMap<>();
  
  private boolean closing;
  
  private double angle;
  
  private double targetAngle;
  
  private int index;
  
  public UICosmeticSelectorOverlay() {
    CosmeticPlayer.OutfitCosmetic outfit = this.cosmeticPlayer.getOutfit();
    int index = 0;
    for (CosmeticNavbarElement element : CosmeticNavbarManager.getElementList()) {
      Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = outfit.get(element.getFactory());
      if (!optionalEquippedCosmetic.isPresent() || ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getType() != CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.WHEEL)
        continue; 
      this.equippedCosmeticMap.put(element, optionalEquippedCosmetic.get());
      if (lastElement != null && lastElement.equals(element))
        this.selectedSignal.set(Integer.valueOf(index)); 
      index++;
    } 
  }
  
  public void init() {
    this.index = -1;
    if (this.cosmeticPlayer == null) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les cosmétiques", "palashop")).send();
      ZUI.close(this);
      return;
    } 
    if (this.equippedCosmeticMap.isEmpty()) {
      (new Notification(Notification.NotificationType.ERROR, "Aucun cosmétique équipé", "palashop")).send();
      ZUI.close(this);
      return;
    } 
    Resource selectorResource = Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/wheel/selector.png"));
    selectorResource.prepareBind();
    ((CircleNode)CircleNode.create(600.0D, 180.0D, 720.0D)
      .color(BACKGROUND_COLOR)
      .body(circle -> {
          CosmeticNavbarElement element = ((CosmeticNavbarElement[])this.equippedCosmeticMap.keySet().toArray((T[])new CosmeticNavbarElement[0]))[((Integer)this.selectedSignal.getOrDefault()).intValue()];
          CosmeticPlayer.EquippedCosmetic equippedCosmetic = this.equippedCosmeticMap.get(element);
          TextInfo textInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE).shadow(circle.getColor());
          Text text = Text.create(element.getName(), textInfo).modifier(TextModifier.CAPITALIZE).horizontalAlign(Align.CENTER);
          if (this.equippedCosmeticMap.size() > 1) {
            text.add(Text.create("    ", textInfo)).add(Text.create((((Integer)this.selectedSignal.getOrDefault()).intValue() + 1) + "/" + this.equippedCosmeticMap.size(), textInfo.copy().letterSpacing(3.0F)));
            TextNode.create(circle.dw(2.0D), circle.ah(15.0D)).text(Text.create("Utilisez la molette pour choisir le type de cosmétique", textInfo).horizontalAlign(Align.CENTER)).mode(TextMode.SPLIT).width(circle.getWidth() * 0.7D).anchorX(Align.CENTER).attach((Node)circle);
          } 
          TextNode.create(0.0D, -50.0D).text(text).width(circle.getWidth()).attach((Node)circle);
          ImageNode.create(circle.dw(4.0D), circle.dh(4.0D), circle.dw(2.0D), circle.dh(2.0D)).resource(Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/wheel/center.png"))).attach((Node)circle);
          CircleNode.create(circle.dw(2.0D) - circle.dw(10.0D), circle.dh(2.0D) - circle.dw(10.0D), circle.dw(5.0D)).color(circle.getColor()).attach((Node)circle);
          ((ImageNode)ImageNode.create(0.0D, 0.0D, circle.getWidth(), circle.getHeight()).resource(selectorResource).onRender(new NodeRenderCallback<ImageNode>() {
                public void apply(ImageNode node, double mouseX, double mouseY, float partialTicks) {}
                
                @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
                public void pre(@NonNull ImageNode node, @NonNull InternalContext context, double mouseX, double mouseY, float partialTicks) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                  if (context == null)
                    throw new NullPointerException("context is marked non-null but is null"); 
                  GL11.glPushMatrix();
                  GL11.glTranslated(node.getX() + node.dw(2.0D), node.getY() + node.dh(2.0D), 0.0D);
                  GL11.glRotated(-45.0D + UICosmeticSelectorOverlay.this.angle, 0.0D, 0.0D, 1.0D);
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
              })).visible(()).attach((Node)circle);
          CircleNode.create(circle.dw(2.0D) - circle.getWidth() * 0.02199999988079071D / 2.0D, circle.dh(2.0D) - circle.getWidth() * 0.02199999988079071D / 2.0D, circle.getWidth() * 0.02199999988079071D).color(Color.WHITE).visible(()).attach((Node)circle);
          for (int i = 0; i < 4; i++) {
            Optional<ICosmetic> optionalCosmetic = equippedCosmetic.get(i);
            double size = circle.getWidth() * 0.18000000715255737D;
            double x = 0.0D;
            double y = 0.0D;
            if (i == 0) {
              x = circle.dw(2.0D);
              y = circle.dh(5.0D);
            } else if (i == 1) {
              x = circle.dw(5.0D) * 4.0D;
              y = circle.dh(2.0D);
            } else if (i == 2) {
              x = circle.dw(2.0D);
              y = circle.dh(5.0D) * 4.0D;
            } else if (i == 3) {
              x = circle.dw(5.0D);
              y = circle.dh(2.0D);
            } 
            if (optionalCosmetic.isPresent()) {
              ICosmeticClient cosmeticClient = (ICosmeticClient)optionalCosmetic.get();
              Signal<ICosmeticClient> cosmeticSignal = new Signal(cosmeticClient);
              if (!cosmeticClient.isReceived())
                cosmeticClient.onReceived(()); 
              ContainerNode.create(x - size / 2.0D, y - size / 2.0D, size, size).body(()).watch(cosmeticSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((Node)circle);
            } else {
              ImageNode.create(x - size / 2.0D, y - size / 2.0D, size, size).resource(element.getResource()).color(Color.WHITE.copyAlpha(0.08F)).attach((Node)circle);
            } 
          } 
        })).watch((Signal)this.selectedSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    CosmeticNavbarElement element = ((CosmeticNavbarElement[])this.equippedCosmeticMap.keySet().toArray((T[])new CosmeticNavbarElement[0]))[((Integer)this.selectedSignal.getOrDefault()).intValue()];
    if (getCooldown(element) > 0L)
      return; 
    double x = mouseX - 960.0D;
    double y = mouseY - 540.0D;
    double mouseAngle = Math.toDegrees(Math.atan2(y, x)) + 180.0D;
    int oldIndex = this.index;
    if (this.index != -1 || Math.abs(x) > 5.0D || Math.abs(y) > 5.0D)
      if (mouseAngle >= 45.0D && mouseAngle < 135.0D) {
        this.index = 0;
      } else if (mouseAngle >= 135.0D && mouseAngle < 225.0D) {
        this.index = 1;
      } else if (mouseAngle >= 225.0D && mouseAngle < 315.0D) {
        this.index = 2;
      } else if ((mouseAngle >= 315.0D || mouseAngle < 45.0D) && (mouseAngle != 360.0D || this.index != -1)) {
        this.index = 3;
      }  
    if (this.index != -1) {
      int diff = (oldIndex == -1) ? this.index : (this.index - oldIndex);
      if (diff != 0) {
        if (diff > 2) {
          diff = -1;
        } else if (diff < -2) {
          diff = 1;
        } 
        SoundConstant.BUTTON_HOVER.copy().play();
        this.targetAngle += (diff * 90);
        if (oldIndex == -1)
          this.angle = this.targetAngle; 
      } 
    } 
    if (this.angle != this.targetAngle)
      this.angle = lerpByFramerate(this.angle, this.targetAngle, 0.5D, 0.5D, true); 
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    GL11.glClear(1024);
    GL11.glEnable(2960);
    GL11.glStencilFunc(519, 1, 255);
    GL11.glStencilOp(7680, 7680, 7681);
    GL11.glColorMask(false, false, false, false);
    DrawUtils.SHAPE.drawCircle(960.0D, 540.0D, Color.RED, 54.0D);
    GL11.glColorMask(true, true, true, true);
    GL11.glStencilFunc(517, 1, 255);
    GL11.glStencilOp(7680, 7680, 7680);
    DrawUtils.SHAPE.drawCircle(960.0D, 540.0D, Color.WHITE, 56.0D);
    GL11.glDisable(2960);
    GL11.glClear(1024);
    CosmeticNavbarElement element = ((CosmeticNavbarElement[])this.equippedCosmeticMap.keySet().toArray((T[])new CosmeticNavbarElement[0]))[((Integer)this.selectedSignal.getOrDefault()).intValue()];
    long cooldown = getCooldown(element);
    if (cooldown > 0L) {
      DrawUtils.SHAPE.drawCircle(960.0D, 540.0D, BACKGROUND_COLOR.darker(0.85F).copyAlpha(0.85F), 360.0D);
      DrawUtils.TEXT.drawText(595.0D, 180.0D, 720.0D, 720.0D, Text.create(String.format("%.0f", new Object[] { Double.valueOf(Math.ceil(((float)cooldown / 1000.0F))) }), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 50.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER), TextMode.SPLIT);
    } 
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    context.cancel();
    if (lastElement != null && getCooldown(lastElement) > 0L)
      return; 
    if (!clickType.isLeft())
      this.closing = true; 
    ZUI.close(this);
  }
  
  public void mouseScroll(double mouseX, double mouseY, int value, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    context.cancel();
    if (this.equippedCosmeticMap.size() <= 1)
      return; 
    int newValue = (((Integer)this.selectedSignal.getOrDefault()).intValue() + ((value > 0) ? 1 : -1)) % this.equippedCosmeticMap.size();
    if (newValue < 0)
      newValue = this.equippedCosmeticMap.size() - 1; 
    this.selectedSignal.set(Integer.valueOf(newValue));
  }
  
  public boolean close() {
    if (!this.closing && (Minecraft.func_71410_x()).field_71462_r == null && this.index != -1)
      apply(); 
    return true;
  }
  
  private void apply() {
    if (this.index == -1)
      return; 
    try {
      lastElement = ((CosmeticNavbarElement[])this.equippedCosmeticMap.keySet().toArray((T[])new CosmeticNavbarElement[0]))[((Integer)this.selectedSignal.getOrDefault()).intValue()];
      applyLast(this.index);
      SoundConstant.BUTTON_CLICK.copy().play();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static void applyLast() {
    applyLast(-1);
  }
  
  public static void applyLast(int index) {
    if (lastElement == null)
      return; 
    UICosmeticSelectorOverlayCache cache = CACHE_MAP.get(lastElement);
    if (cache != null && getCooldown(lastElement) > 0L) {
      (new Notification(Notification.NotificationType.ERROR, "Vous devez attendre avant de pouvoir utiliser ce cosmétique", "palashop")).send();
      return;
    } 
    if (index == -1 && cache == null)
      return; 
    index = (index == -1) ? cache.getLastIndex() : index;
    if (index == -1)
      return; 
    CosmeticPlayer.OutfitCosmetic outfit = CosmeticPlayer.me().getOutfit();
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = outfit.get(lastElement.getFactory());
    if (!optionalEquippedCosmetic.isPresent() || ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getType() != CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.WHEEL)
      return; 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    Optional<ICosmetic> optionalCosmetic = equippedCosmetic.get(index);
    if (!optionalCosmetic.isPresent())
      return; 
    ICosmetic cosmetic = optionalCosmetic.get();
    CSPacketExecuteWheelCosmetic.HitResult target = new CSPacketExecuteWheelCosmetic.HitResult((Minecraft.func_71410_x()).field_71476_x);
    if (MinecraftForge.EVENT_BUS.post((Event)new CosmeticExecuteWheelEvent.Pre((Entity)(Minecraft.func_71410_x()).field_71439_g, cosmetic, target)))
      return; 
    CACHE_MAP.put(lastElement, new UICosmeticSelectorOverlayCache(index, equippedCosmetic.getWheelCooldown()));
    (new CSPacketExecuteWheelCosmetic(cosmetic.getFactory().getId(), index, target)).send();
    MinecraftForge.EVENT_BUS.post((Event)new CosmeticExecuteWheelEvent.Post((Entity)(Minecraft.func_71410_x()).field_71439_g, cosmetic, target));
  }
  
  public static long getCooldown(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    UICosmeticSelectorOverlayCache cache = CACHE_MAP.get(element);
    if (cache == null)
      return 0L; 
    return cache.getRemainingCooldown();
  }
  
  public static class UICosmeticSelectorOverlayCache {
    private final int lastIndex;
    
    private final long cooldown;
    
    private final long lastUse;
    
    public int getLastIndex() {
      return this.lastIndex;
    }
    
    public long getCooldown() {
      return this.cooldown;
    }
    
    public long getLastUse() {
      return this.lastUse;
    }
    
    public UICosmeticSelectorOverlayCache(int lastIndex, long cooldown) {
      this.lastIndex = lastIndex;
      this.cooldown = cooldown;
      this.lastUse = System.currentTimeMillis();
    }
    
    public long getRemainingCooldown() {
      if (this.cooldown <= 0L)
        return 0L; 
      return Math.max(0L, this.lastUse + this.cooldown - System.currentTimeMillis());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\selector\UICosmeticSelectorOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */