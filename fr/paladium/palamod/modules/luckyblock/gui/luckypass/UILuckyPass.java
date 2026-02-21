package fr.paladium.palamod.modules.luckyblock.gui.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.Timeline;
import fr.paladium.lib.aurelienribon.tweenengine.Tween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquation;
import fr.paladium.lib.aurelienribon.tweenengine.TweenEquations;
import fr.paladium.palaforgeutils.lib.box.BoundingBox;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.luckypass.node.LuckyPassEventNode;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketGetLuckyPass;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketWinLuckyPass;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class UILuckyPass extends UI {
  private static final ResourceLocation FOREGROUND_TEXTURE = new ResourceLocation("palamod", "textures/gui/LuckyPass/minecraft/foreground.png");
  
  private static final ResourceLocation UNKNOWN_TEXTURE = new ResourceLocation("palamod", "textures/gui/LuckyPass/minecraft/unknown.png");
  
  private static final ResourceLocation EMPTY_TEXTURE = new ResourceLocation("palamod", "textures/gui/LuckyPass/minecraft/empty.png");
  
  private boolean started;
  
  private boolean success;
  
  private final List<ANode>[] nodes = (List<ANode>[])new List[3];
  
  public double x;
  
  public double y;
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.started) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    PlayerLuckyPassProperties eep = PlayerLuckyPassProperties.get((EntityPlayer)this.field_146297_k.field_71439_g);
    if (eep == null) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    for (int i = 0; i < this.nodes.length; i++)
      this.nodes[i] = new ArrayList<>(); 
    double baseRatio = 1.7777777777777777D;
    double ratio = this.field_146297_k.field_71443_c / this.field_146297_k.field_71440_d;
    if (ratio < 1.7777777777777777D || Math.abs(ratio - 1.7777777777777777D) < 0.005D) {
      this.x = 0.0D;
      this.y = (this.field_146297_k.field_71440_d - height(100.0D)) / 2.0D;
    } else {
      this.x = (this.field_146297_k.field_71443_c - width(100.0D)) / 2.0D;
      this.y = 0.0D;
    } 
    MinecraftTitleNodeLabel titleNodeLabel = new MinecraftTitleNodeLabel(this.x + width(50.0F), this.y + height(16.5F), "LUCKY PASS");
    titleNodeLabel.setX(titleNodeLabel.defaultX - (GuiUtils.getStringWidth(this.field_146297_k, titleNodeLabel.getText(), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), titleNodeLabel.getFontSize()) / 2.0F));
    addNode((ANode)titleNodeLabel);
    ANode openNode;
    addNode(openNode = (new MinecraftTextCallToActionNode(this.x + width(50.0F) - width(7.055F), this.y + height(76.0F), width(14.11F), "LANCER")).setCallback(n -> {
            n.setEnabled(false);
            SoundUtils.CLICK.playClientSound(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70163_u, this.field_146297_k.field_71439_g.field_70161_v, 1.0F, 1.0F);
            PalaMod.getNetHandler().sendToServer((IMessage)new PacketGetLuckyPass());
          }));
    long now = TimeUtils.nowZoned().getDayOfYear();
    long date = eep.getDate();
    if (now == date && !ForgeEnv.isIDE()) {
      openNode.setEnabled(false);
      openNode.addHover("§fRevenez demain pour gagner vos LuckyBlock !");
    } 
    int freeSlots = 0;
    for (int j = 0; j < this.field_146297_k.field_71439_g.field_71071_by.field_70462_a.length; j++) {
      if (this.field_146297_k.field_71439_g.field_71071_by.field_70462_a[j] == null || (this.field_146297_k.field_71439_g.field_71071_by.field_70462_a[j]).field_77994_a <= 0)
        freeSlots++; 
    } 
    if (freeSlots < 3) {
      openNode.setEnabled(false);
      openNode.addHover("§fVous devez avoir au moins 3 emplacements vides dans votre inventaire !");
    } 
    double xCenter = this.x + width(50.0F);
    double size = width(6.5F);
    double xOffset = width(14.0F);
    double yOffset = height(23.0F);
    double left = xCenter - size / 2.0D - xOffset;
    double top = this.y + height(21.0F);
    BoundingBox box = BoundingBox.create(0.0D, this.y + height(27.85F), this.field_146294_l, height(44.277F));
    for (int k = 0; k < 3; k++) {
      for (int m = 0; m < 3; m++) {
        LuckyPassEventNode node = (new LuckyPassEventNode(left + xOffset * k, top + yOffset * m, size, UNKNOWN_TEXTURE, true)).setScissor(box);
        addNode((ANode)node);
        this.nodes[k].add(node);
      } 
    } 
  }
  
  public void setData(int[] wins) {
    this.started = true;
    start(wins);
  }
  
  private void start(int[] wins) {
    PlayerLuckyPassProperties eep = PlayerLuckyPassProperties.get((EntityPlayer)this.field_146297_k.field_71439_g);
    double xCenter = this.x + width(50.0F);
    double size = width(6.5F);
    double xOffset = width(14.0F);
    double yOffset = height(23.0F);
    double left = xCenter - size / 2.0D - xOffset;
    double top = this.y + height(21.0F);
    Random random = this.field_146297_k.field_71441_e.field_73012_v;
    BoundingBox box = BoundingBox.create(0.0D, this.y + height(27.85F), this.field_146294_l, height(44.277F));
    int i;
    for (i = 0; i < 3; i++) {
      for (int k = 0; k < 25; k++) {
        LuckyPassEventNode node = null;
        if (random.nextBoolean()) {
          node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * (k + 1), size, EMPTY_TEXTURE, false)).setScissor(box);
        } else {
          node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * (k + 1), size, LuckyType.Util.getRandomType(), false)).setScissor(box);
        } 
        addNode((ANode)node);
        this.nodes[i].add(node);
      } 
    } 
    for (i = 0; i < 3; i++) {
      int value = wins[i];
      LuckyPassEventNode node = null;
      if (value == -1) {
        node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * 26.0D, size, EMPTY_TEXTURE, false)).setScissor(box);
      } else {
        node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * 26.0D, size, LuckyType.values()[value], false)).setScissor(box);
      } 
      addNode((ANode)node);
      this.nodes[i].add(node);
    } 
    for (i = 0; i < 3; i++) {
      LuckyPassEventNode node = null;
      if (random.nextBoolean()) {
        node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * 27.0D, size, EMPTY_TEXTURE, false)).setScissor(box);
      } else {
        node = (new LuckyPassEventNode(left + xOffset * i, top - yOffset * 27.0D, size, LuckyType.Util.getRandomType(), false)).setScissor(box);
      } 
      addNode((ANode)node);
      this.nodes[i].add(node);
    } 
    (new Thread(() -> {
          try {
            for (int i = 0; i < 3; i++) {
              Thread.sleep(100L);
              List<ANode> list = this.nodes[i];
              for (ANode node : list) {
                Timeline timeline = Timeline.createSequence().beginSequence().push(Tween.to(node, 2, 5000.0F).target((float)(node.defaultY + (list.size() - 3) * yOffset)).ease((TweenEquation)TweenEquations.easeInOutQuart));
                timeline.start(node.getTweenManager());
                if (i == 2 && node == list.get(list.size() - 1))
                  timeline.addCallback(8, ()); 
              } 
              Thread.sleep(600L);
            } 
          } catch (Exception exception) {}
        }"UILuckyPass/Animation")).start();
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    BackgroundHelper.createMinecraft(width(54.27F), height(70.0F), true);
    GuiUtils.drawImageTransparent(this.x + width(50.0F) - width(23.59375F), this.y + height(50.0F) - height(22.6385F), FOREGROUND_TEXTURE, width(47.1875F), height(45.277F));
  }
  
  public void func_146281_b() {
    super.func_146281_b();
    if (this.started && !this.success)
      PalaMod.getNetHandler().sendToServer((IMessage)new PacketWinLuckyPass()); 
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\luckypass\UILuckyPass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */