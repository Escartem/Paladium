package fr.paladium.palamod.modules.luckyblock.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerSafeChestLocked;
import fr.paladium.palamod.modules.luckyblock.network.PacketOpenSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import fr.paladium.palamod.modules.luckyblock.ui.utils.container.CodeDisplayContainer;
import fr.paladium.palamod.modules.luckyblock.ui.utils.signal.CodeSignal;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UISafeChestLocked extends UI {
  private static final Resource LOCK_ICON = Resource.of(new ResourceLocation("palamod", "textures/gui/container/safechest/lock_icon.png"));
  
  private final TileEntitySafeChest teSafeChest;
  
  private final CodeSignal codeSignal;
  
  public CodeSignal getCodeSignal() {
    return this.codeSignal;
  }
  
  private final StringSignal labelSignal = new StringSignal("palamod.safechest.label.enter");
  
  private long tick = 0L;
  
  private final int cooldown = 6;
  
  private final double ultra;
  
  public UISafeChestLocked(TileEntitySafeChest tile, EntityPlayer player) {
    super((Container)new ContainerSafeChestLocked(tile, player.field_71071_by));
    this.teSafeChest = tile;
    this.codeSignal = new CodeSignal(tile.getCodeMaxLength());
    this.ultra = (tile.getCodeMaxLength() == 4) ? 180.0D : 0.0D;
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> BackgroundNode.create(1103.0D + this.ultra, 670.0D).body(()).attach(container))
      
      .attach(this);
  }
  
  public void update() {
    if (this.codeSignal.isFilled())
      if (!this.teSafeChest.isCodeInitialized()) {
        getClass();
        if (this.tick >= (20 * 6)) {
          this.teSafeChest.setCode(this.codeSignal.getCode());
          this.tick = 0L;
          PalaMod.getNetwork().sendToServer((IMessage)new PacketOpenSafeChest(this.teSafeChest.field_145851_c, this.teSafeChest.field_145848_d, this.teSafeChest.field_145849_e, this.codeSignal.getCode()));
          return;
        } 
        if (!"palamod.safechest.label.initialized".equals(this.labelSignal.getOrDefault()))
          this.labelSignal.set("palamod.safechest.label.initialized"); 
        this.tick++;
      } else if (!this.teSafeChest.isCorrect(this.codeSignal.getCode())) {
        getClass();
        if (this.tick >= (20 * 6)) {
          this.tick = 0L;
          this.codeSignal.clear();
          this.labelSignal.reset();
          this.labelSignal.publish();
          return;
        } 
        if (!"palamod.safechest.label.incorrect".equals(this.labelSignal.getOrDefault()))
          this.labelSignal.set("palamod.safechest.label.incorrect"); 
        this.tick++;
      } else {
        getClass();
        if (this.tick >= (20 * 6)) {
          this.tick = 0L;
          PalaMod.getNetwork().sendToServer((IMessage)new PacketOpenSafeChest(this.teSafeChest.field_145851_c, this.teSafeChest.field_145848_d, this.teSafeChest.field_145849_e, this.codeSignal.getCode()));
          return;
        } 
        if (!"palamod.safechest.label.correct".equals(this.labelSignal.getOrDefault()))
          this.labelSignal.set("palamod.safechest.label.correct"); 
        this.tick++;
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\ui\UISafeChestLocked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */