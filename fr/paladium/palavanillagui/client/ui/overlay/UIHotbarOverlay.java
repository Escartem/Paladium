package fr.paladium.palavanillagui.client.ui.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palavanillagui.client.ui.util.container.JumpBarContainer;
import fr.paladium.palavanillagui.client.ui.util.container.WaterBreathingContainer;
import fr.paladium.palavanillagui.client.ui.util.container.XpBarContainer;
import fr.paladium.palavanillagui.client.ui.util.node.ArmorNode;
import fr.paladium.palavanillagui.client.ui.util.node.FoodNode;
import fr.paladium.palavanillagui.client.ui.util.node.HeartNode;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeHooks;

@UIData(background = false, anchorY = Align.END)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = false, type = RenderGameOverlayEvent.ElementType.HOTBAR))
@UIDataGuiscale(active = true, limited = false)
public class UIHotbarOverlay extends UI {
  private static final ResourceLocation SLOT = ResourceUtils.get("palavanillagui", "textures/gui/hotbar/slot.png");
  
  private static final ResourceLocation SLOT_SELECTED = ResourceUtils.get("palavanillagui", "textures/gui/hotbar/slot_selected.png");
  
  private static final Color SELECTED_BORDER = new Color(51, 8, 5);
  
  private final MapSignal<RenderGameOverlayEvent.ElementType, Boolean> eventSignal = new MapSignal(new HashMap<>());
  
  public MapSignal<RenderGameOverlayEvent.ElementType, Boolean> getEventSignal() {
    return this.eventSignal;
  }
  
  private final StringSignal toolHightlightSignal = new StringSignal("");
  
  private final DoubleSignal toolHightlightOffsetSignal = new DoubleSignal(0.0D);
  
  private final StringSignal textDisplayedSignal = new StringSignal("");
  
  private final BooleanSignal creativeModeSignal = new BooleanSignal((Minecraft.func_71410_x()).field_71442_b.func_78758_h());
  
  private final BooleanSignal playerRidingSignal = new BooleanSignal((Minecraft.func_71410_x()).field_71439_g.func_70115_ae());
  
  private final DoubleSignal ridingMaxHealthSignal = new DoubleSignal(0.0D);
  
  private final DoubleSignal ridingHealthSignal = new DoubleSignal(0.0D);
  
  private final TextInfo toolHightlightInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F).color(new Color(1.0F, 1.0F, 1.0F, 0.0F));
  
  private final TextInfo textDisplayedInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F).color(new Color(1.0F, 1.0F, 1.0F, 0.0F));
  
  private final DoubleSignal healthSignal = new DoubleSignal((Minecraft.func_71410_x()).field_71439_g.func_110143_aJ());
  
  private final DoubleSignal maxHealthSignal = new DoubleSignal((Minecraft.func_71410_x()).field_71439_g.func_110138_aP());
  
  private final IntegerSignal foodSignal = new IntegerSignal((Minecraft.func_71410_x()).field_71439_g.func_71024_bL().func_75116_a());
  
  private final IntegerSignal armorSignal = new IntegerSignal(ForgeHooks.getTotalArmorValue((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
  
  private final DoubleSignal absorptionSignal = new DoubleSignal((Minecraft.func_71410_x()).field_71439_g.func_110139_bj());
  
  private final BooleanSignal hasPoisonSignal = new BooleanSignal((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76436_u));
  
  private final BooleanSignal hasWitherSignal = new BooleanSignal((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_82731_v));
  
  private final BooleanSignal hasHungerSignal = new BooleanSignal((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76438_s));
  
  private final ListSignal<ItemStack> hotBarSignal = new ListSignal(getHotbarItems());
  
  private final IntegerSignal itemSelectedSignal = new IntegerSignal(0);
  
  private TweenAnimator tweenHightlightAnimator;
  
  private TweenAnimator tweenTextAnimator;
  
  private boolean damaged = false;
  
  public UIHotbarOverlay() {
    if (this.eventSignal.size() == 0)
      for (RenderGameOverlayEvent.ElementType type : RenderGameOverlayEvent.ElementType.values()) {
        this.eventSignal.silent();
        this.eventSignal.put(type, Boolean.valueOf(false));
      }  
  }
  
  public void init() {
    ContainerNode.create(642.0D, 922.0D, 636.0D, 158.0D)
      .body(t -> {
          ((TextNode)TextNode.create(t.dw(2.0D), 0.0D).anchorX(Align.CENTER).onInit(())).watch((Signal)this.toolHightlightSignal).watch((Signal)this.creativeModeSignal).watch((Signal)this.toolHightlightOffsetSignal).attach(t);
          ((TextNode)TextNode.create(t.getWidth() / 2.0D, -150.0D).anchorX(Align.CENTER).onInit(())).watch((Signal)this.textDisplayedSignal).attach(t);
          ContainerNode.create(0.0D, 0.0D, 282.0D, 48.0D).body(()).watch((Signal)this.healthSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.absorptionSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.maxHealthSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.hasPoisonSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.hasWitherSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          ContainerNode.create(0.0D, 0.0D, 282.0D, 24.0D).body(()).watch((Signal)this.armorSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.absorptionSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.maxHealthSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          XpBarContainer.create(0.0D, 55.0D, t.getWidth(), 16.0D).body(()).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.playerRidingSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          JumpBarContainer.create(0.0D, 55.0D, t.getWidth(), 15.0D).body(()).watch((Signal)this.playerRidingSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          WaterBreathingContainer.create(t.getWidth() - 201.0D, 0.0D, 201.0D, 19.0D).body(()).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          ContainerNode.create(t.getWidth() - 282.0D, 22.0D, 282.0D, 24.0D).body(()).watch((Signal)this.foodSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.hasHungerSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.playerRidingSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          ContainerNode.create(t.getWidth() - 282.0D, 0.0D, 282.0D, 48.0D).body(()).watch((Signal)this.creativeModeSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.playerRidingSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.ridingMaxHealthSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.ridingHealthSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
          ContainerNode.create(0.0D, 82.0D, t.getWidth(), 75.0D).body(()).watch((Signal)this.hotBarSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.itemSelectedSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).watch((Signal)this.eventSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(t);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    ItemStack current = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.func_70448_g();
    Integer selectedCurrent = Integer.valueOf((Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70461_c);
    boolean isCreative = (Minecraft.func_71410_x()).field_71442_b.func_78758_h();
    if (((Boolean)this.creativeModeSignal.getOrDefault()).booleanValue() != isCreative)
      this.creativeModeSignal.set(Boolean.valueOf(isCreative)); 
    if (hotbarHasChanged())
      this.hotBarSignal.set(getHotbarItems()); 
    if (this.itemSelectedSignal.getOrDefault() != selectedCurrent)
      this.itemSelectedSignal.set(selectedCurrent); 
    if (((Boolean)this.playerRidingSignal.getOrDefault()).booleanValue() != (Minecraft.func_71410_x()).field_71439_g.func_70115_ae())
      this.playerRidingSignal.set(Boolean.valueOf((Minecraft.func_71410_x()).field_71439_g.func_70115_ae())); 
    if (((Boolean)this.playerRidingSignal.getOrDefault()).booleanValue() && 
      (Minecraft.func_71410_x()).field_71439_g.field_70154_o instanceof EntityLivingBase) {
      EntityLivingBase mount = (EntityLivingBase)(Minecraft.func_71410_x()).field_71439_g.field_70154_o;
      if (((Double)this.ridingHealthSignal.getOrDefault()).doubleValue() != mount.func_110143_aJ())
        this.ridingHealthSignal.set(Double.valueOf(mount.func_110143_aJ())); 
      if (((Double)this.ridingMaxHealthSignal.getOrDefault()).doubleValue() != mount.func_110138_aP())
        this.ridingMaxHealthSignal.set(Double.valueOf(mount.func_110138_aP())); 
    } 
    if (Math.ceil((Minecraft.func_71410_x()).field_71439_g.func_110143_aJ()) != ((Double)this.healthSignal.getOrDefault()).doubleValue()) {
      if (Math.ceil((Minecraft.func_71410_x()).field_71439_g.func_110143_aJ()) < ((Double)this.healthSignal.getOrDefault()).doubleValue()) {
        this.damaged = true;
      } else {
        this.damaged = false;
      } 
      this.healthSignal.set(Double.valueOf(Math.ceil((Minecraft.func_71410_x()).field_71439_g.func_110143_aJ())));
    } 
    if ((Minecraft.func_71410_x()).field_71439_g.func_110138_aP() != ((Double)this.maxHealthSignal.getOrDefault()).doubleValue())
      this.maxHealthSignal.set(Double.valueOf((Minecraft.func_71410_x()).field_71439_g.func_110138_aP())); 
    if ((Minecraft.func_71410_x()).field_71439_g.func_110139_bj() != ((Double)this.absorptionSignal.getOrDefault()).doubleValue())
      this.absorptionSignal.set(Double.valueOf((Minecraft.func_71410_x()).field_71439_g.func_110139_bj())); 
    if (ForgeHooks.getTotalArmorValue((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g) != ((Integer)this.armorSignal.getOrDefault()).intValue())
      this.armorSignal.set(Integer.valueOf(ForgeHooks.getTotalArmorValue((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g))); 
    if ((Minecraft.func_71410_x()).field_71439_g.func_71024_bL().func_75116_a() != ((Integer)this.foodSignal.getOrDefault()).intValue())
      this.foodSignal.set(Integer.valueOf((Minecraft.func_71410_x()).field_71439_g.func_71024_bL().func_75116_a())); 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76436_u) != ((Boolean)this.hasPoisonSignal.getOrDefault()).booleanValue())
      this.hasPoisonSignal.set(Boolean.valueOf((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76436_u))); 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_82731_v) != ((Boolean)this.hasWitherSignal.getOrDefault()).booleanValue())
      this.hasWitherSignal.set(Boolean.valueOf((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_82731_v))); 
    if ((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76438_s) != ((Boolean)this.hasHungerSignal.getOrDefault()).booleanValue())
      this.hasHungerSignal.set(Boolean.valueOf((Minecraft.func_71410_x()).field_71439_g.func_70644_a(Potion.field_76438_s))); 
    updateTween(this.tweenHightlightAnimator, () -> (this.toolHightlightInfo.getColor()).a = this.tweenHightlightAnimator.getValue());
    updateTween(this.tweenTextAnimator, () -> (this.textDisplayedInfo.getColor()).a = this.tweenTextAnimator.getValue());
    if (current == null || current.func_77973_b() == null) {
      if (!((String)this.toolHightlightSignal.getOrDefault()).isEmpty())
        this.toolHightlightSignal.set(""); 
      return;
    } 
    if (!((String)this.toolHightlightSignal.getOrDefault()).equalsIgnoreCase(current.func_82833_r())) {
      this.toolHightlightSignal.set(current.func_82833_r());
      this.tweenHightlightAnimator = TweenAnimator.create(1.0F).sequence(2000.0F, 0.0F).start();
    } 
  }
  
  private void updateTween(TweenAnimator animator, Runnable callback) {
    if (animator != null && animator.getTimeline().isStarted() && !animator.getTimeline().isFinished()) {
      animator.update();
      if (callback != null)
        callback.run(); 
    } 
  }
  
  public void setTextDisplayed(String textToDisplay) {
    this.textDisplayedSignal.set(textToDisplay);
    this.tweenTextAnimator = TweenAnimator.create(1.0F).sequence(3050.0F, 0.0F).start();
    this.tweenTextAnimator.setCallback(cb -> this.textDisplayedSignal.set(""));
  }
  
  public void setToolHightlightOffset(double offset) {
    this.toolHightlightOffsetSignal.set(Double.valueOf(offset));
  }
  
  private List<ItemStack> getHotbarItems() {
    List<ItemStack> hotbar = new ArrayList<>();
    for (int i = 0; i < 9; i++)
      hotbar.add((Minecraft.func_71410_x()).field_71439_g.field_71071_by.field_70462_a[i]); 
    return hotbar;
  }
  
  private boolean hotbarHasChanged() {
    List<ItemStack> hotbar = getHotbarItems();
    boolean hasChanged = false;
    for (int i = 0; i < hotbar.size(); i++) {
      if (hotbar.get(i) != this.hotBarSignal.get(i)) {
        hasChanged = true;
        break;
      } 
    } 
    return hasChanged;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\overlay\UIHotbarOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */