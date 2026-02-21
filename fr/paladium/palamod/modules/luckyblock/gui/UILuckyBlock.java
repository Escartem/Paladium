package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.opengl.modifier.GLCoords;
import fr.paladium.lib.apollon.utils.opengl.modifier.GLRotation;
import fr.paladium.lib.apollon.utils.opengl.modifier.GLScale;
import fr.paladium.lib.apollon.utils.opengl.transformation.GLTransformation;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquation;
import fr.paladium.lib.aurelienribon.tweenengine.equations.Back;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.box.BoundingBox;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.node.LuckyBlockEventNode;
import fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.item.UIShopLuckyStatsItemPage;
import fr.paladium.palamod.modules.luckyblock.network.PacketGetLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class UILuckyBlock extends UI {
  public UILuckyBlock(int tileX, int tileY, int tileZ) {
    this.nodes = (List<LuckyBlockEventNode>[])new List[3];
    this.tileX = tileX;
    this.tileY = tileY;
    this.tileZ = tileZ;
  }
  
  private static final ResourceLocation FOREGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/LuckyBlock/minecraft/foreground.png");
  
  private static final ResourceLocation UNKNOWN_TEXTURE = new ResourceLocation("palamod", "textures/gui/LuckyBlock/minecraft/unknown.png");
  
  private static final Back EASING = new Back() {
      public final float compute(float t) {
        float s = 0.5F;
        if ((t *= 2.0F) < 1.0F)
          return 0.5F * t * t * (((s *= 1.525F) + 1.0F) * t - s); 
        return 0.5F * ((t -= 2.0F) * t * (((s *= 1.525F) + 1.0F) * t + s) + 2.0F);
      }
      
      public String toString() {
        return "Back.Custom.INOUT";
      }
    };
  
  private final int tileX;
  
  private final int tileY;
  
  private final int tileZ;
  
  private TileEntityLuckyBlock tile;
  
  private LuckyEvents event;
  
  private boolean isNew;
  
  private boolean success;
  
  private final List<LuckyBlockEventNode>[] nodes;
  
  private MinecraftTextCallToActionNode node;
  
  public double x;
  
  public double y;
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.event != null) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    for (int i = 0; i < this.nodes.length; i++)
      this.nodes[i] = new ArrayList<>(); 
    TileEntity te = this.field_146297_k.field_71441_e.func_147438_o(this.tileX, this.tileY, this.tileZ);
    if (!(te instanceof TileEntityLuckyBlock)) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    this.tile = (TileEntityLuckyBlock)te;
    double baseRatio = 1.7777777777777777D;
    double ratio = this.field_146297_k.field_71443_c / this.field_146297_k.field_71440_d;
    if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D) {
      this.x = 0.0D;
      this.y = (this.field_146297_k.field_71440_d - height(100.0D)) / 2.0D;
    } else {
      this.x = (this.field_146297_k.field_71443_c - width(100.0D)) / 2.0D;
      this.y = 0.0D;
    } 
    MinecraftTitleNodeLabel titleNodeLabel = new MinecraftTitleNodeLabel(this.x + width(13.69F) + width(27.135F) - width(0.390625F), this.y + height(7.59F), "LUCKY BLOCK");
    titleNodeLabel.setX(titleNodeLabel.defaultX - (GuiUtils.getStringWidth(this.field_146297_k, titleNodeLabel.getText(), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), titleNodeLabel.getFontSize()) / 2.0F));
    addNode((ANode)titleNodeLabel);
    MinecraftSubTitleNodeLabel subTitleNodeLabel = new MinecraftSubTitleNodeLabel(this.x + width(13.69F) + width(27.135F) - width(0.390625F), this.y + height(16.94F), this.tile.getType().getText());
    subTitleNodeLabel.setX(subTitleNodeLabel.defaultX - (GuiUtils.getStringWidth(this.field_146297_k, subTitleNodeLabel.getText(), Fonts.PIXEL_NES.getFont(), subTitleNodeLabel.getFontSize()) / 2.0F));
    addNode((ANode)subTitleNodeLabel);
    LuckyType type = this.tile.getType();
    ANode luckystatsNode;
    addNode(luckystatsNode = (new MinecraftTextCallToActionNode(this.x + width(71.51F) + width(9.66F) - width(6.145F) - width(0.390625F), this.y + height(61.666F), width(12.29F), "LUCKYSTATS")).setCallback(n -> ZUI.open((UI)new UIShopLuckyStatsItemPage(type))).setEnabled(!LuckyType.disabledLuckyType.contains(this.tile.getType())));
    addNode((ANode)(this.node = (MinecraftTextCallToActionNode)(new MinecraftTextCallToActionNode(this.x + width(13.69F) + width(27.135F) - width(0.390625F) - width(7.055F), this.y + height(85.0F), width(14.11F), "OUVRIR")).setCallback(n -> {
            luckystatsNode.setEnabled(false);
            n.setEnabled(false);
            SoundUtils.CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
            PalaMod.getNetHandler().sendToServer((IMessage)new PacketGetLuckyEvent(this.tileX, this.tileY, this.tileZ));
          })));
    double xCenter = this.x + width(13.69F) + width(27.135F) - width(0.390625F);
    double size = width(6.5F);
    double xOffset = width(14.0F);
    double yOffset = height(23.0F);
    double left = xCenter - size / 2.0D - xOffset;
    double top = this.y + height(16.0F);
    BoundingBox box = BoundingBox.create(0.0D, this.y + height(23.0F), this.field_146294_l, height(44.277F));
    for (int j = 0; j < 3; j++) {
      for (int k = 0; k < 3; k++) {
        LuckyBlockEventNode node = (new LuckyBlockEventNode(left + xOffset * j, top + yOffset * k, size, UNKNOWN_TEXTURE, true)).setScissor(box);
        addNode((ANode)node);
        this.nodes[j].add(node);
      } 
    } 
  }
  
  public void setData(LuckyEvents event, boolean isNew) {
    this.isNew = isNew;
    start(event);
  }
  
  private void start(LuckyEvents event) {
    this.tile.setEvent(event);
    this.event = event;
    double xCenter = this.x + width(13.69F) + width(27.135F) - width(0.390625F);
    double size = width(6.5F);
    double xOffset = width(14.0F);
    double yOffset = height(23.0F);
    double left = xCenter - size / 2.0D - xOffset;
    double top = this.y + height(16.0F);
    BoundingBox box = BoundingBox.create(0.0D, this.y + height(23.0F), this.field_146294_l, height(44.277F));
    LuckyEvents[] events = LuckyEvents.valuesByType(this.tile.getType());
    int i;
    for (i = 0; i < 3; i++) {
      for (int k = 0; k < 25; k++) {
        ALuckyEvent randomEvent = events[this.field_146297_k.field_71441_e.field_73012_v.nextInt(events.length)].getEvent();
        ResourceLocation texture = new ResourceLocation("palamod", "textures/gui/LuckyBlock/events/" + randomEvent.getTexture() + ".png");
        LuckyBlockEventNode node = (new LuckyBlockEventNode(left + xOffset * i, top - yOffset * (k + 1), size, texture, false)).setScissor(box);
        addNode((ANode)node);
        this.nodes[i].add(node);
      } 
    } 
    for (i = 0; i < 3; i++) {
      ResourceLocation texture = new ResourceLocation("palamod", "textures/gui/LuckyBlock/events/" + this.event.getEvent().getTexture() + ".png");
      LuckyBlockEventNode node = (new LuckyBlockEventNode(left + xOffset * i, top - yOffset * 26.0D, size, texture, false)).setScissor(box);
      addNode((ANode)node);
      this.nodes[i].add(node);
    } 
    for (i = 0; i < 3; i++) {
      ALuckyEvent randomEvent = events[this.field_146297_k.field_71441_e.field_73012_v.nextInt(events.length)].getEvent();
      ResourceLocation texture = new ResourceLocation("palamod", "textures/gui/LuckyBlock/events/" + randomEvent.getTexture() + ".png");
      LuckyBlockEventNode node = (new LuckyBlockEventNode(left + xOffset * i, top - yOffset * 27.0D, size, texture, false)).setScissor(box);
      addNode((ANode)node);
      this.nodes[i].add(node);
    } 
    (new Thread(() -> {
          try {
            for (int i = 0; i < 3; i++) {
              Thread.sleep(100L);
              List<LuckyBlockEventNode> list = this.nodes[i];
              for (LuckyBlockEventNode node : list) {
                node.getAnimator().sequence(5000.0F, (float)(node.defaultY + (list.size() - 3) * yOffset), (TweenEquation)EASING).start();
                if (i == 2 && node == list.get(list.size() - 1))
                  node.getAnimator().getTimeline().addCallback(8, ()); 
              } 
              Thread.sleep(600L);
            } 
          } catch (Exception exception) {}
        }"UILuckyBlock/Animation")).start();
    this.node.setText("PASSER");
    this.node.setCallback(n -> {
          n.setEnabled(false);
          for (int i = 0; i < 3; i++) {
            for (LuckyBlockEventNode node : this.nodes[i])
              node.getAnimator().setSpeed(5.0F); 
          } 
        });
    this.node.setEnabled(true);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    BackgroundHelper.createMinecraft(this.x + width(13.69F), this.y + height(50.0F) - height(45.32F), width(54.27F), height(90.64F), true);
    BackgroundHelper.createMinecraft(this.x + width(71.51F), this.y + height(50.0F) - height(22.13F), width(19.32F), height(44.26F), true);
    GuiUtils.drawRect(this.x + width(13.69F), this.y + height(71.38F), this.x + width(13.69F) + width(54.27F) - width(0.78125F), this.y + height(71.38F) + height(9.72F), new Color(0.0F, 0.0F, 0.0F, 0.15F));
    GuiUtils.drawImageTransparent(this.x + width(13.69F) + width(27.135F) - width(0.390625F) - width(23.59375F), this.y + height(22.5F), FOREGROUND_TEXTURE, width(47.1875F), height(45.277F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.renderScaledItemStackIntoGUI(LuckyType.Util.getIconFrom(this.tile.getType()), this.x + width(71.51F) + width(9.66F) - width(6.40625F) - width(0.390625F), this.y + height(32.96F), width(12.8125F) / 16.0F);
    String text = (this.event == null) ? "CLIQUEZ POUR OUVRIR LE LUCKY BLOCK" : (this.success ? this.event.getEvent().getName().toUpperCase() : "OUVERTURE EN COURS");
    double textWidth = GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, text, this.x + width(13.69F) + width(27.135F) - width(0.390625F), this.y + height(75.0F), Color.WHITE, Fonts.PIXEL_NES.getFont(), 70);
    if (this.event != null && this.success && this.isNew) {
      double variation = (Math.sin((System.currentTimeMillis() * 2L) * Math.PI / 1500.0D) + 1.0D) / 2.0D;
      GLCoords newCenter = GLCoords.create(this.x + width(13.69F) + width(27.135F) - width(0.390625F) + textWidth / 2.0D + width(1.0F), this.y + height(72.5F));
      GLTransformation.create().rotate(30.0D, GLRotation.PITCH, newCenter).scale(GLScale.create(1.0D + 0.2D * variation, 1.0D + 0.2D * variation, 1.0D), newCenter).apply(() -> {
            GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "NEW", newCenter.getX(), newCenter.getY() + height(0.2F), Color.decode("#B91C0C"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 15);
            GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, "NEW", newCenter.getX(), newCenter.getY(), Color.decode("#EF3926"), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 15);
          });
    } 
  }
  
  public void func_146281_b() {
    super.func_146281_b();
    if (this.event == null) {
      PalaMod.getNetwork().sendToServer((IMessage)new PacketUseLuckyBlock(true, this.tileX, this.tileY, this.tileZ));
    } else {
      (new Notification(Notification.NotificationType.INFO, "Vous avez eu l'évènement " + this.event.getEvent().getName(), "luckyblock")).send();
      PalaMod.getNetwork().sendToServer((IMessage)new PacketUseLuckyBlock(false, this.tileX, this.tileY, this.tileZ));
    } 
  }
  
  public double width(double value) {
    double baseRatio = 1.7777777777777777D;
    double ratio = this.field_146297_k.field_71443_c / this.field_146297_k.field_71440_d;
    if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D)
      return this.field_146297_k.field_71443_c / 100.0D * value; 
    return this.field_146297_k.field_71440_d * 1.7777777777777777D / 100.0D * value;
  }
  
  public float width(float value) {
    float baseRatio = 1.7777778F;
    float ratio = this.field_146297_k.field_71443_c / this.field_146297_k.field_71440_d;
    if (ratio < 1.7777778F || Math.abs(ratio - 1.7777778F) < 0.005F)
      return this.field_146297_k.field_71443_c / 100.0F * value; 
    return this.field_146297_k.field_71440_d * 1.7777778F / 100.0F * value;
  }
  
  public double height(double value) {
    double baseRatio = 1.7777777777777777D;
    return width(value) / 1.7777777777777777D;
  }
  
  public float height(float value) {
    float baseRatio = 1.7777778F;
    return width(value) / 1.7777778F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\UILuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */