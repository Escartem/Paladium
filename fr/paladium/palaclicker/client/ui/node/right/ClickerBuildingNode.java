package fr.paladium.palaclicker.client.ui.node.right;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.common.network.packet.building.CSPacketClickerBuildingBuy;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class ClickerBuildingNode extends Node {
  public static final ResourceLocation UNKOWN_TEXTURE = new ResourceLocation("palaclicker", "textures/gui/icons/unknown.png");
  
  public static final Color BORDER_COLOR = new Color(51, 8, 5);
  
  public static final Color[] COLORS = new Color[] { new Color(239, 57, 38), new Color(249, 110, 95), new Color(186, 35, 19), new Color(170, 39, 25), new Color(146, 19, 12) };
  
  public static final Color[] DISABLED_COLORS = new Color[] { new Color(67, 67, 80), new Color(117, 117, 135), new Color(59, 59, 71), new Color(42, 42, 51), new Color(34, 34, 39) };
  
  private ClickerBuilding building;
  
  private int count;
  
  private boolean unlocked;
  
  private boolean available;
  
  public ClickerBuilding getBuilding() {
    return this.building;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public boolean isUnlocked() {
    return this.unlocked;
  }
  
  public boolean isAvailable() {
    return this.available;
  }
  
  protected ClickerBuildingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    hoverDuration(100L);
    onClick((node, mouseX, mouseY, clickType) -> {
          if (this.building == null || !this.available)
            return; 
          Minecraft mc = Minecraft.func_71410_x();
          EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
          double playerX = ((EntityPlayer)entityClientPlayerMP).field_70165_t;
          double playerY = ((EntityPlayer)entityClientPlayerMP).field_70163_u;
          double playerZ = ((EntityPlayer)entityClientPlayerMP).field_70161_v;
          SoundUtils.BUTTON_CLICK.playClientSound(playerX, playerY, playerZ, 0.1F, 1.0F);
          getHoverAnimator().setValue(0.0F);
          hovered(false);
          ((UIClicker)getUi()).getUpgradePageSignal().set(Boolean.valueOf(false));
          ((UIClicker)getUi()).getUpgradeHotbarSignal().publish();
          (new CSPacketClickerBuildingBuy(this.building.getId())).send();
        });
  }
  
  public static ClickerBuildingNode create(double x, double y, double width, double height) {
    return new ClickerBuildingNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    if (this.building == null)
      return; 
    clearChildren();
    ContainerNode.create(0.0D, 0.0D, getWidth(), getDefaultHeight())
      .body(container -> {
          if (!this.unlocked) {
            ImageNode.create(19.0D, 15.0D).resource(UNKOWN_TEXTURE).linear(false).width(50.0D).aspectRatio(1.0D).attach(container);
          } else if (this.building.getImage() != null && !this.building.getImage().isEmpty()) {
            ImageNode.create(19.0D, 15.0D).resource(this.building.getImage()).linear(false).width(50.0D).aspectRatio(1.0D).attach(container);
          } else if (this.building.getItem() != null && !this.building.getItem().isEmpty()) {
            ItemNode.create(19.0D, 15.0D, 50.0D).item(this.building.getItemStack()).stackSize(false).attach(container);
          } else {
            RectNode.create(19.0D, 15.0D, 50.0D, 50.0D).wait((ISignal)new BooleanSignal()).attach(container);
          } 
          TextNode.create(93.0D, 20.0D).text(Text.create(this.unlocked ? this.building.getName() : "???", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F).color(Color.WHITE)).overflow(TextOverflow.ELLIPSIS)).mode(TextMode.OVERFLOW).width(200.0D).attach(container);
          FlexNode.horizontal(93.0D, 46.0D, 15.0D).margin(5.0D).body(()).attach(container);
          TextNode.create(aw(-20.0D), -6.0D).text(Text.create(String.format("%02d", new Object[] { Integer.valueOf(this.count) }), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 42.0F).color(Color.WHITE).shadow().shadow(0.0F, 4.0F))).anchorX(Align.END).attach(container);
        }).attach(this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.available && this.unlocked) {
      float brightness = hoverValue(0.2F);
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), COLORS[0].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), 5.0D, COLORS[1].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY(), 5.0D, getHeight(), COLORS[2].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX() + aw(-5.0D), getY(), 5.0D, getHeight(), COLORS[2].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-12.0D), getWidth(), 5.0D, COLORS[3].brighter(brightness));
      DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-7.0D), getWidth(), 7.0D, COLORS[4].brighter(brightness));
    } else {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), DISABLED_COLORS[0]);
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), 5.0D, DISABLED_COLORS[1]);
      DrawUtils.SHAPE.drawRect(getX(), getY(), 5.0D, getHeight(), DISABLED_COLORS[2]);
      DrawUtils.SHAPE.drawRect(getX() + aw(-5.0D), getY(), 5.0D, getHeight(), DISABLED_COLORS[2]);
      DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-12.0D), getWidth(), 5.0D, DISABLED_COLORS[3]);
      DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-7.0D), getWidth(), 7.0D, DISABLED_COLORS[4]);
    } 
    if (isHovered() && this.unlocked)
      ((UIClicker)getUi()).getBuildingInfoNode().data(this).visible(node -> true).enabled(node -> true).init(getUi()); 
    DrawUtils.SHAPE.drawFilledBorder(getX() + 2.0D, ((int)getY() + 2), getX() + getWidth() - 2.0D, getY() + getHeight() - 2.0D, BORDER_COLOR, 2.0D);
  }
  
  public ClickerBuildingNode data(ClickerBuilding building, int count, boolean unlocked) {
    this.building = building;
    this.count = count;
    this.unlocked = unlocked;
    return this;
  }
  
  public ClickerBuildingNode available(boolean available) {
    this.available = available;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\right\ClickerBuildingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */