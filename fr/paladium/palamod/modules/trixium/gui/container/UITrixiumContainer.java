package fr.paladium.palamod.modules.trixium.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.trixium.gui.UITrixium;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumCache;
import fr.paladium.palamod.modules.trixium.gui.container.slot.UITrixiumBaseSlot;
import fr.paladium.palamod.modules.trixium.gui.container.slot.UITrixiumSlot;
import fr.paladium.palamod.modules.trixium.gui.node.TrixiumItemNode;
import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class UITrixiumContainer extends UIContainer {
  private final Color[] colors = new Color[] { new Color(131, 174, 248), new Color(154, 131, 248) };
  
  private final ResourceLocation trixium = new ResourceLocation("palamod", "textures/gui/trixium/trixium.png");
  
  DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.FRANCE);
  
  private double scroll;
  
  private double scrollTarget;
  
  private final double value;
  
  private final List<TrixiumReward> rewards;
  
  public UITrixiumContainer(int value, List<TrixiumReward> rewards, double scroll, double scrollTarget) {
    super(null, "palamod:trixium", new ContainerTrixium((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.value = value;
    this.scroll = scroll;
    this.scrollTarget = scrollTarget;
    this.rewards = rewards;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new UITrixiumSlot(new TrixiumInventory(), 0, width(46.04F), height(32.77F), width(7.916D)));
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)new UITrixiumBaseSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, j + i * 9 + 9, (width(29.167F) + width(4.74F) * j), (height(53.61F) + height(8.42F) * i), width(3.96D))); 
    } 
    for (i = 0; i < 9; i++)
      addNode((ANode)new UITrixiumBaseSlot((IInventory)this.field_146297_k.field_71439_g.field_71071_by, i, (width(29.167F) + width(4.74F) * i), height(82.5F), width(3.96D))); 
    DecimalFormatSymbols symbols = this.formatter.getDecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    this.formatter.setDecimalFormatSymbols(symbols);
    for (TrixiumReward reward : this.rewards) {
      if (reward.getState() == TrixiumReward.State.NOT_AVAILABLE && this.value >= reward.getValue())
        reward.setState(TrixiumReward.State.AVAILABLE); 
      addNode((ANode)new TrixiumItemNode(getPositionFromValue(reward.getValue()) - (width(7.656F) / 2.0F), height(45.0F), width(7.656F), width(7.656F), reward));
    } 
    addNode((ANode)new AClickableNode(width(40.99F), height(87.96F), width(18.07F), height(7.4F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            Color backgroundColor = new Color(48, 217, 110);
            Color textColor = new Color(255, 255, 255);
            GuiUtils.drawRoundedRect(this.x, this.y, backgroundColor, this.width, this.height, height(10.0F));
            UITrixiumContainer.this.drawFullyCenteredString("Ajouter du Trixium", this.x + this.width / 2.0D, this.y + this.height / 2.0D, textColor, Fonts.MONTSERRAT_BOLD.getFont(), 50);
          }
        });
    addNode((ANode)new AClickableNode(width(78.4375F), height(2.5F), width(14.843F), height(7.4F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            GuiUtils.drawRoundedRect(this.x, this.y, Color.WHITE, this.width, this.height, height(10.0F));
            UITrixiumContainer.this.drawFullyCenteredString("Classement", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(99, 99, 99), Fonts.MONTSERRAT_BOLD.getFont(), 50);
          }
        });
    addNode((new TexturedNodeButton(width(96.04F), height(3.61F), width(1.927F), width(1.927F), "palamod:textures/gui/trixium/close") {
          public boolean isHovered(int mouseX, int mouseY) {
            return false;
          }
        }).setZindex(100));
    addNode((new TexturedNodeButton(width(69.53F), height(17.31F), width(3.6979F), width(3.6979F), "palamod:textures/gui/trixium/close"))
        .setCallback(node -> {
            UITrixiumCache.scroll = this.scroll;
            UITrixiumCache.scrollTarget = this.scrollTarget;
            this.field_146297_k.func_147108_a((GuiScreen)new UITrixium(UITrixiumCache.trixiumFinal, UITrixiumCache.rewards, UITrixiumCache.scroll, UITrixiumCache.scrollTarget));
          }).setZindex(100));
    addNode((ANode)new AClickableNode(0.0D, 0.0D, this.field_146294_l, this.field_146295_m) {
          public void draw(Minecraft arg0, int arg1, int arg2) {
            GuiUtils.drawRoundedRect(width(25.2F), height(14.9F), new Color(8, 8, 104, 240), width(49.58F), height(78.05F), width(0.7F));
          }
        });
    addNode((ANode)new AClickableNode(0.0D, 0.0D, this.field_146294_l, this.field_146295_m) {
          public void draw(Minecraft arg0, int arg1, int arg2) {
            GuiUtils.drawCenteredStringWithCustomFont(UITrixiumContainer.this.field_146297_k, "déposer votre trixium".toUpperCase(), this.width / 2.0D, height(24.44F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 120);
          }
        });
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    if (this.scroll < -100.0D)
      this.scrollTarget = 0.0D; 
    if (this.scroll > getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F))
      this.scrollTarget = getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F); 
    this.scroll = smoothValue(this.scroll, this.scrollTarget);
    getNodes().stream().filter(TrixiumItemNode.class::isInstance).forEach(node -> node.setX(node.getDefaultX() - this.scroll));
    GuiUtils.drawGradientRect(0.0D, 0.0D, this.colors, this.field_146294_l, this.field_146295_m, new Vector2d(0.0D, this.field_146295_m), new Vector2d(this.field_146294_l, 0.0D), this.field_146295_m);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, getBackground(), this.field_146294_l, this.field_146295_m, false);
    GuiUtils.drawRect(0.0D, 0.0D, this.field_146294_l, height(12.13F), new Color(85, 77, 184));
    GuiUtils.drawImageTransparent(width(2.864F), (height(6.065F) - width(1.795F)), this.trixium, width(3.59F), width(3.59F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "TRIXIUM", width(7.7F), height(4.5F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 180);
    GuiUtils.drawRect(0.0D, height(24.537F), this.field_146294_l, height(77.497F), new Color(85, 77, 184));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "Récompenses", width(2.864F), height(29.63F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 100);
    GuiUtils.drawRect(0.0D, height(51.66F), this.field_146294_l, height(52.86F), new Color(109, 115, 212));
    GuiUtils.drawRect(0.0D, height(51.66F), getPositionFromValue((int)this.value) - this.scroll, height(52.86F), new Color(36, 219, 169));
    GuiUtils.drawRoundedRect(width(80.78F), height(27.87F), new Color(108, 114, 212), width(17.1875F), height(7.5F), width(0.5F));
    GuiUtils.drawImageTransparent(width(81.927F), (height(27.87F) + height(7.5F) / 2.0F - width(1.0675F)), this.trixium, width(2.135F), width(2.135F));
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, this.formatter.format((int)this.value), width(84.79F), height(30.5F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 100);
  }
  
  protected void func_73869_a(char c, int keyCode) {
    if (keyCode == 1) {
      UITrixiumCache.scroll = this.scroll;
      UITrixiumCache.scrollTarget = this.scrollTarget;
      this.field_146297_k.func_147108_a((GuiScreen)new UITrixium(UITrixiumCache.trixiumFinal, UITrixiumCache.rewards, UITrixiumCache.scroll, UITrixiumCache.scrollTarget));
      return;
    } 
    super.func_73869_a(c, keyCode);
  }
  
  public void func_146281_b() {}
  
  private double getPositionFromValue(int value) {
    if (value <= 0)
      return 0.0D; 
    int lastValue = 0;
    int i = 0;
    for (TrixiumReward reward : this.rewards) {
      if (value > lastValue && value <= reward.getValue())
        return (width(30.0F) * i + (value - lastValue) / (reward.getValue() - lastValue) * width(30.0F)); 
      lastValue = reward.getValue();
      i++;
    } 
    return (width(30.0F) * i + (value - lastValue) / 100000.0F * width(30.0F));
  }
  
  private double smoothValue(double value, double target) {
    int fps = Integer.parseInt(this.field_146297_k.field_71426_K.split(" fps")[0]);
    double diff = target - value;
    double absDiff = Math.abs(diff);
    double offset = 0.2D / (((fps == 0) ? true : fps) / 60.0F) * absDiff / 3.0D;
    if (absDiff > 0.2D) {
      value += (diff > 0.0D) ? offset : -offset;
    } else {
      value = target;
    } 
    return value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\UITrixiumContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */