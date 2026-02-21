package fr.paladium.palarpg.module.dungeon.client.ui.chest;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundElementNode;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.ui.chest.node.DungeonChestItemNode;
import fr.paladium.palarpg.module.dungeon.client.ui.chest.node.DungeonChestSlotNode;
import fr.paladium.palarpg.module.dungeon.client.ui.popup.UIDungeonWarningPopup;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.network.chest.CSPacketRPGDungeonChestValidate;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

@UIData(background = false)
public class UIDungeonChest extends UI {
  private final int size;
  
  private final UIDungeonChestData chestData;
  
  private final Set<RPGDungeonPlayerData.RPGDungeonItem> items;
  
  private final Set<RPGDungeonPlayerData.RPGDungeonItem> backpackItems;
  
  private final TweenAnimator itemsAnimator = TweenAnimator.create(-1080.0F);
  
  private final TweenAnimator backpackAnimator = TweenAnimator.create(1080.0F);
  
  private final TweenAnimator backgroundAnimator = TweenAnimator.create(0.0F);
  
  private final ListSignal<RPGDungeonPlayerData.RPGDungeonItem> deletedItems = new ListSignal(new ArrayList());
  
  private boolean closing;
  
  private boolean inspecting;
  
  public UIDungeonChest(@NonNull UIDungeonChestData chestData) {
    if (chestData == null)
      throw new NullPointerException("chestData is marked non-null but is null"); 
    this.chestData = chestData;
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData("dungeon");
    if (data == null) {
      this.size = 0;
      this.items = null;
      this.backpackItems = null;
      return;
    } 
    this.size = data.getBackpackSize();
    this
      
      .items = (Set<RPGDungeonPlayerData.RPGDungeonItem>)Arrays.<RPGDungeonPlayerData.RPGDungeonItem>stream(chestData.getItems()).filter(item -> (item.getItem() != null)).sorted((o1, o2) -> {
          int rarityCompare = o2.getRarity().compareTo((Enum)o1.getRarity());
          if (rarityCompare != 0)
            return rarityCompare; 
          int nameCompare = o1.getItem().func_82833_r().compareToIgnoreCase(o2.getItem().func_82833_r());
          return (nameCompare != 0) ? nameCompare : Integer.compare((o2.getItem()).field_77994_a, (o1.getItem()).field_77994_a);
        }).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    this
      
      .backpackItems = (Set<RPGDungeonPlayerData.RPGDungeonItem>)data.getBackpack().stream().filter(item -> (item.getItem() != null)).sorted((o1, o2) -> {
          int rarityCompare = o2.getRarity().compareTo((Enum)o1.getRarity());
          if (rarityCompare != 0)
            return rarityCompare; 
          int nameCompare = o1.getItem().func_82833_r().compareToIgnoreCase(o2.getItem().func_82833_r());
          return (nameCompare != 0) ? nameCompare : Integer.compare((o2.getItem()).field_77994_a, (o1.getItem()).field_77994_a);
        }).collect(Collectors.toCollection(java.util.LinkedHashSet::new));
  }
  
  public void init() {
    if (this.items == null || this.items.isEmpty()) {
      ZUI.close(this);
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les données du coffre", "paladium")).send();
      return;
    } 
    if (this.backpackItems == null) {
      ZUI.close(this);
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger les données du sac à dos", "paladium")).send();
      return;
    } 
    this.itemsAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.backpackAnimator.sequence(500.0F, 721.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    this.backgroundAnimator.sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
    ContainerNode.create(0.0D, this.backpackAnimator.getValue(), 1920.0D, 1080.0D)
      .body(container -> {
          BackgroundNode.create(108.0D, 80.0D, 1704.0D, 1032.0D).attach(container);
          TextNode.create(150.0D, 82.0D).text(Text.create("backpack ", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE).lineHeight(-7.0F)).add(Text.create("(" + this.size + ")", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 40.0F, Color.WHITE))).verticalAlign(Align.END)).attach(container);
          BackgroundElementNode.create(1102.0D, 112.0D, 407.0D, 52.0D).color(Color.decode("#282828")).shadowColor(Color.decode("#1F1F1F")).shadow(0.0F, 4.0F).cornerSize(5.0D).body(()).attach(container);
          ((TextButtonNode)TextButtonNode.create(1524.0D, 118.0D).text("inspecter").onInit(()).onClick(())).size(245.0D, 42.0D).enabled(()).watch((Signal)this.deletedItems).attach(container);
          ContainerNode.create(108.0D, 200.0D, 1704.0D, 880.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(1677.0D, 70.0D, 775.0D, 3.0D)).body(()).attach(container);
        }).onAnimate((node, animator, value) -> node.y(value))
      .animate(this.backpackAnimator)
      .attach(this);
    ContainerNode.create(0.0D, this.itemsAnimator.getValue(), 1920.0D, 817.0D)
      .body(container -> {
          TextNode.create(container.dw(2.0D), 13.0D).text(Text.create("coffre " + this.chestData.getRarity().getName(), TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 58.0F, Color.WHITE))).anchorX(Align.CENTER).attach(container);
          AtomicBoolean scrollUpdated = new AtomicBoolean(false);
          ContainerNode.create(0.0D, 0.0D, container.w(), container.h()).overflow(OverflowProperty.SCROLL).scrollSpeed(3.0D).body(()).onInit(()).onRender(()).attach(container);
        }).onAnimate((node, animator, value) -> node.y(value))
      .animate(this.itemsAnimator)
      .attach(this);
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    this.backgroundAnimator.update();
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), (new Color(14, 14, 14)).copyAlpha(this.backgroundAnimator.getValue()).toGradient((new Color(14, 14, 14)).copyAlpha(this.backgroundAnimator.getValue() * 0.6F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
  }
  
  public boolean close() {
    if (this.closing)
      return false; 
    if (this.backpackAnimator.getValue() == 721.0F) {
      this.closing = true;
      this.itemsAnimator.sequence(500.0F, -1080.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
      this.backpackAnimator.sequence(500.0F, 1080.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
      this.backgroundAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> ZUI.close(this, true)).start();
    } else if (this.backpackAnimator.getValue() == 0.0F) {
      this.closing = true;
      this.backpackAnimator.sequence(500.0F, 1080.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
      this.backgroundAnimator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> ZUI.close(this, true)).start();
    } 
    return false;
  }
  
  public static class UIDungeonChestData implements IBufSerializable {
    private int chestX;
    
    private int chestY;
    
    private int chestZ;
    
    private RPGDungeonPlayerData.RPGDungeonItem[] items;
    
    private TileEntityDungeonChest.DungeonChestRarity rarity;
    
    public UIDungeonChestData() {}
    
    public UIDungeonChestData(int chestX, int chestY, int chestZ, RPGDungeonPlayerData.RPGDungeonItem[] items, TileEntityDungeonChest.DungeonChestRarity rarity) {
      this.chestX = chestX;
      this.chestY = chestY;
      this.chestZ = chestZ;
      this.items = items;
      this.rarity = rarity;
    }
    
    public int getChestX() {
      return this.chestX;
    }
    
    public int getChestY() {
      return this.chestY;
    }
    
    public int getChestZ() {
      return this.chestZ;
    }
    
    public RPGDungeonPlayerData.RPGDungeonItem[] getItems() {
      return this.items;
    }
    
    public TileEntityDungeonChest.DungeonChestRarity getRarity() {
      return this.rarity;
    }
    
    public void read(ByteBuf buf) {
      this.chestX = buf.readInt();
      this.chestY = buf.readInt();
      this.chestZ = buf.readInt();
      int length = buf.readInt();
      this.items = new RPGDungeonPlayerData.RPGDungeonItem[length];
      for (int i = 0; i < length; i++)
        this.items[i] = new RPGDungeonPlayerData.RPGDungeonItem(UUIDUtils.from(ByteBufUtils.readUTF8String(buf)), ByteBufUtils.readUTF8String(buf), ByteBufUtils.readItemStack(buf), RPGItemRarity.values()[buf.readInt()]); 
      this.rarity = TileEntityDungeonChest.DungeonChestRarity.values()[buf.readInt()];
    }
    
    public void write(ByteBuf buf) {
      buf.writeInt(this.chestX);
      buf.writeInt(this.chestY);
      buf.writeInt(this.chestZ);
      buf.writeInt(this.items.length);
      for (RPGDungeonPlayerData.RPGDungeonItem item : this.items) {
        ByteBufUtils.writeUTF8String(buf, UUIDUtils.toString(item.getUniqueId()));
        ByteBufUtils.writeUTF8String(buf, item.getType());
        ByteBufUtils.writeItemStack(buf, item.getItem());
        buf.writeInt(item.getRarity().ordinal());
      } 
      buf.writeInt(this.rarity.ordinal());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\chest\UIDungeonChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */