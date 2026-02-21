package fr.paladium.palashop.client.ui;

import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.client.ui.node.ShopNavbarNode;
import fr.paladium.palashop.client.ui.store.UIShopStore;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@UIData(backgroundColor = "#17181B")
public abstract class UIShopPage extends UIShopBase {
  private static ShopNavbarNode navbarNode;
  
  private final Resource background;
  
  private final ShopNavbarElement element;
  
  private UIShopStore store;
  
  public UIShopPage(ShopNavbarElement element) {
    this.background = Resource.of(new ResourceLocation("palashop", "textures/ui/background.png"));
    this.element = element;
  }
  
  public Resource getBackground() {
    return this.background;
  }
  
  public ShopNavbarElement getElement() {
    return this.element;
  }
  
  public UIShopStore getStore() {
    return this.store;
  }
  
  public void init() {
    this.store = (UIShopStore)useStore(UIShopStore.class, new Object[0]);
    this.store.init();
    schedule(() -> {
          if (this.store == null)
            return; 
          this.store.init();
        }5000L, 5000L);
    RectNode.create(-2.0D, 0.0D, 2.0D, 1080.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    RectNode.create(1920.0D, 0.0D, 2.0D, 1080.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    RectNode.create(0.0D, -2.0D, 1920.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    RectNode.create(0.0D, 1080.0D, 1920.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach(this);
    if (navbarNode != null) {
      navbarNode.current(this.element).attach(this);
    } else {
      navbarNode = (ShopNavbarNode)ShopNavbarNode.create(0.0D, 0.0D, 1920.0D, 114.0D).current(this.element).attach(this);
    } 
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
    DrawUtils.RESOURCE.drawImage(0.0D, 0.0D, 1920.0D, 1080.0D, this.background);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public boolean close() {
    if (this instanceof IBackableUI) {
      UIShopPage back = ((IBackableUI)this).getBack();
      if (back == null)
        return true; 
      back.reload();
      ZUI.open(back, true);
      SoundConstant.BUTTON_CLICK.copy().play();
      return false;
    } 
    return super.close();
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    super.mousePressed(mouseX, mouseY, clickType, context);
    if (context.isCancelled())
      return; 
    if (clickType.isBack() || clickType.isForward())
      context.cancel(); 
    if (!(this instanceof IBackableUI))
      return; 
    if (clickType.isBack()) {
      UIShopPage back = ((IBackableUI)this).getBack();
      if (back == null)
        return; 
      ZUI.open(back, true);
      SoundConstant.BUTTON_CLICK.copy().play();
    } 
  }
  
  public static interface IBackableUI {
    UIShopPage getBack();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\UIShopPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */