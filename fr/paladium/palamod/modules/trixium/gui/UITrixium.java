package fr.paladium.palamod.modules.trixium.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.network.PacketOpenGui;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.gui.node.TrixiumItemNode;
import fr.paladium.palamod.modules.trixium.network.ranking.CSPacketTrixiumRanking;
import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UITrixium extends UI {
  private final Color[] colors = new Color[] { new Color(131, 174, 248), new Color(154, 131, 248) };
  
  private final ResourceLocation trixium = new ResourceLocation("palamod", "textures/gui/trixium/trixium.png");
  
  private final ResourceLocation crown = new ResourceLocation("palamod", "textures/gui/trixium/crown.png");
  
  DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.FRANCE);
  
  private double scrollX;
  
  private boolean scrolling;
  
  private double scroll;
  
  private double scrollTarget;
  
  private double value;
  
  private double valueTarget;
  
  private final double valueFinal;
  
  private int ticks;
  
  private final List<TrixiumReward> rewards;
  
  public UITrixium(int trixium, List<TrixiumReward> rewards) {
    super(null, "palamod:trixium");
    this.valueFinal = trixium;
    this.valueTarget = (UITrixiumCache.value == 0) ? trixium : UITrixiumCache.value;
    this.value = (UITrixiumCache.value == 0) ? trixium : UITrixiumCache.value;
    UITrixiumCache.value = (int)this.valueFinal;
    this.scrollX = 0.0D;
    this.scrolling = false;
    this.scroll = 0.0D;
    this.scrollTarget = 0.0D;
    this.rewards = rewards;
  }
  
  public UITrixium(int trixium, List<TrixiumReward> rewards, double scroll, double scrollTarget) {
    super(null, "palamod:trixium");
    this.valueFinal = trixium;
    this.valueTarget = UITrixiumCache.value;
    this.value = UITrixiumCache.value;
    UITrixiumCache.value = (int)this.valueFinal;
    this.scroll = scroll;
    this.scrollTarget = scrollTarget;
    this.rewards = rewards;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    DecimalFormatSymbols symbols = this.formatter.getDecimalFormatSymbols();
    symbols.setGroupingSeparator(' ');
    this.formatter.setDecimalFormatSymbols(symbols);
    if (this.value > 0.0D && getPositionFromValue((int)this.value) > this.field_146294_l + this.scroll) {
      this.scrollTarget = getPositionFromValue((int)this.value) - width(25.0F);
      this.scroll = getPositionFromValue((int)this.value) - width(25.0F);
      if (this.scroll > getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F) && !this.scrolling) {
        this.scrollTarget = getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F);
        this.scroll = getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F);
      } 
    } 
    for (TrixiumReward reward : this.rewards) {
      if (reward.getState() == TrixiumReward.State.NOT_AVAILABLE && this.value >= reward.getValue() && reward.getCommands() != null && !reward.getCommands().isEmpty())
        reward.setState(TrixiumReward.State.AVAILABLE); 
      addNode((ANode)new TrixiumItemNode(getPositionFromValue(reward.getValue()) - (width(7.656F) / 2.0F), height(45.0F), width(7.656F), width(7.656F), reward));
    } 
    addNode((new AClickableNode(width(40.99F), height(87.96F), width(18.07F), height(7.4F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            Color backgroundColor = new Color(48 + (int)animationValue(207.0F), 217 + (int)animationValue(38.0F), 110 + (int)animationValue(145.0F));
            Color textColor = new Color(255 - (int)animationValue(207.0F), 255 - (int)animationValue(38.0F), 255 - (int)animationValue(145.0F));
            GuiUtils.drawRoundedRect(this.x, this.y, backgroundColor, this.width, this.height, height(10.0F));
            UITrixium.this.drawFullyCenteredString("Ajouter du Trixium", this.x + this.width / 2.0D, this.y + this.height / 2.0D, textColor, Fonts.MONTSERRAT_BOLD.getFont(), 50);
          }
        }).setCallback(node -> {
            UITrixiumCache.value = (int)this.value;
            UITrixiumCache.trixium = (int)this.value;
            UITrixiumCache.trixiumFinal = (int)this.valueFinal;
            UITrixiumCache.rewards = this.rewards;
            UITrixiumCache.scroll = this.scroll;
            UITrixiumCache.scrollTarget = this.scrollTarget;
            PalaMod.getNetwork().sendToServer((IMessage)(new PacketOpenGui()).setInformations((byte)PGuiRegistry.GUI_TRIXIUM));
          }));
    addNode((new AClickableNode(width(78.4375F), height(2.5F), width(14.843F), height(7.4F)) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            GuiUtils.drawRoundedRect(this.x, this.y, Color.WHITE, this.width, this.height, height(10.0F));
            UITrixium.this.drawFullyCenteredString("Classement", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(99, 99, 99), Fonts.MONTSERRAT_BOLD.getFont(), 50);
            double crownX = this.x + width(71.5F);
            double crownY = this.y + height(12.0F);
            double crownSize = width(6.0F);
            GL11.glPushMatrix();
            GL11.glTranslated(crownX, crownY + crownSize, 0.0D);
            GL11.glRotated(15.0D + animationValue(10.0F), 0.0D, 0.0D, 1.0D);
            GL11.glTranslated(-crownX, -(crownY + crownSize), 0.0D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, animationValue(1.0F));
            GuiUtils.drawImageTransparent(crownX, crownY, UITrixium.this.crown, crownSize, crownSize, false);
            GL11.glPopMatrix();
          }
        }).setCallback(node -> PTrixium.getNetwork().sendToServer((IMessage)new CSPacketTrixiumRanking())));
    addNode((new TexturedNodeButton(width(96.04F), height(3.61F), width(1.927F), width(1.927F), "palamod:textures/gui/trixium/close"))
        .setCallback(node -> this.field_146297_k.func_147108_a(null)));
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
    this.scrollX = mouseX;
    this.scrolling = true;
  }
  
  protected void func_146286_b(int mouseX, int mouseY, int buttonID) {
    super.func_146286_b(mouseX, mouseY, buttonID);
    this.scrolling = false;
  }
  
  public void func_146274_d() {
    super.func_146274_d();
    int k = Mouse.getDWheel();
    boolean speed = Keyboard.isKeyDown(29);
    this.scrollTarget += (k < 0) ? (speed ? -width(30.0F) : -120.0D) : ((k > 0) ? (speed ? width(30.0F) : 120.0D) : 0.0D);
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
    if (this.valueFinal > 0.0D && this.ticks > 5 && this.ticks % 20 == 0 && this.valueTarget < this.valueFinal && this.valueFinal <= ((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) {
      boolean foundAnimation = false;
      double lastValue = 0.0D;
      for (TrixiumReward reward : this.rewards) {
        if (this.valueTarget <= lastValue && this.valueFinal >= reward.getValue()) {
          this.valueTarget = reward.getValue();
          foundAnimation = true;
          break;
        } 
        lastValue = reward.getValue();
      } 
      if (!foundAnimation)
        this.valueTarget = this.valueFinal; 
    } else if (this.valueTarget > this.valueFinal || this.valueFinal > ((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) {
      this.valueTarget = this.valueFinal;
    } 
    this.ticks++;
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    if (this.scrolling) {
      this.scrollTarget += this.scrollX - mouseX;
      this.scrollX = mouseX;
    } 
    if (this.scroll < -100.0D && !this.scrolling)
      this.scrollTarget = 0.0D; 
    if (this.scroll > getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F) && !this.scrolling)
      this.scrollTarget = getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F); 
    this.scroll = smoothValue(this.scroll, this.scrollTarget);
    int lastValue = (int)this.value;
    this.value = smoothValue(this.value, this.valueTarget);
    if (this.value != this.valueTarget && getPositionFromValue((int)this.value) > this.field_146294_l + this.scrollTarget)
      if (this.value > ((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) {
        this.scrollTarget = getPositionFromValue(((TrixiumReward)this.rewards.get(this.rewards.size() - 1)).getValue()) - width(75.0F);
      } else {
        this.scrollTarget = getPositionFromValue((int)this.value) - width(25.0F);
      }  
    if (this.value != this.valueTarget && getPositionFromValue((int)this.value) - this.scroll < 0.0D)
      this.scrollTarget = getPositionFromValue((int)this.value) - width(75.0F); 
    boolean playRewardSound = false;
    for (TrixiumReward reward : this.rewards) {
      if (reward.getState() == TrixiumReward.State.NOT_AVAILABLE && this.value >= reward.getValue() && reward.getCommands() != null && !reward.getCommands().isEmpty()) {
        reward.setState(TrixiumReward.State.AVAILABLE);
        if (!playRewardSound)
          this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("fireworks.launch"), 1.0F)); 
        playRewardSound = true;
        continue;
      } 
      if (reward.getState() == TrixiumReward.State.AVAILABLE && this.value < reward.getValue())
        reward.setState(TrixiumReward.State.NOT_AVAILABLE); 
    } 
    if (!playRewardSound && lastValue != (int)this.value)
      this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.orb"), 10.0F)); 
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
  
  public boolean isScrolling() {
    return this.scrolling;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */