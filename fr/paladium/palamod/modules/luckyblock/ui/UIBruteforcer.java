package fr.paladium.palamod.modules.luckyblock.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerBruteforcer;
import fr.paladium.palamod.modules.luckyblock.network.PacketBruteforcerStart;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@UIData(background = false, container = true)
public class UIBruteforcer extends UI {
  private static final Resource FINDIUM_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/container/bruteforcer/findium_placeholder.png"));
  
  private final TileEntityBruteforcer tile;
  
  private final FloatSignal progress = new FloatSignal(0.0F);
  
  private final BooleanSignal isStarted = new BooleanSignal(true);
  
  private final StringSignal code;
  
  private final Random rdm = new Random();
  
  private int codeTestTicks = 0;
  
  public UIBruteforcer(TileEntityBruteforcer tile) {
    super((Container)new ContainerBruteforcer(tile, (Minecraft.func_71410_x()).field_71439_g.field_71071_by));
    this.tile = tile;
    this.code = new StringSignal("xxx");
    if (this.tile.started) {
      this.code.set(getRandomCode());
    } else if (this.tile.found) {
      this.code.set(this.tile.validChest.code);
    } else {
      this.code.set((this.tile.getValidSafeChest().getCodeMaxLength() == 3) ? "xxx" : "xxxx");
    } 
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 785.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("bruteforcer", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(body.getWidth() / 2.0D - 270.0D + 60.0D, 380.0D, 472.0D, 35.0D).color(new Color(50, 50, 50), new Color(239, 159, 38)).onInit(())).watch((Signal)this.progress).attach(body);
          ((ProgressNode)ProgressNode.create(body.getWidth() / 2.0D - 270.0D + 60.0D, 415.0D, 472.0D, 5.0D).color(new Color(26, 26, 26), new Color(221, 106, 0)).onInit(())).watch((Signal)this.progress).attach(body);
          RectNode.create(176.0D, 138.0D, 408.0D, 167.0D).color(Color.BLACK.copyAlpha(0.15F)).attach(body);
          ((TextNode)TextNode.create(body.getWidth() / 2.0D, 140.0D).text(Text.create(this.code.getOrDefault(), PaladiumText.TITLE.copy().fontSize(109.0F))).onInit(())).anchorX(Align.CENTER).watch((Signal)this.code).attach(body);
          TextButtonNode.create(body.getWidth() / 2.0D - 76.0D, 285.0D).text("Lancer").onInit(()).onClick(()).size(152.0D, 42.0D).watch((Signal)this.isStarted).attach(body);
        }).attach(this);
  }
  
  public void update() {
    super.update();
    if (this.tile.started && this.tile.validChest != null && !this.tile.found) {
      if (this.codeTestTicks >= 30) {
        this.code.set(getRandomCode());
        this.codeTestTicks = 0;
      } 
      this.codeTestTicks++;
    } 
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.progress.getOrDefault()).floatValue() != this.tile.getFindiumProgress())
      this.progress.set(Float.valueOf(this.tile.getFindiumProgress())); 
    if (this.tile.found && this.tile.validChest != null && this.code.getOrDefault() != this.tile.validChest.code)
      this.code.set(this.tile.validChest.code); 
    if (((Boolean)this.isStarted.getOrDefault()).booleanValue() != this.tile.started)
      this.isStarted.set(Boolean.valueOf(this.tile.started)); 
  }
  
  private String getRandomCode() {
    StringBuilder code = new StringBuilder("xxx");
    do {
      code.setLength(0);
      for (int i = 0; i < this.tile.validChest.getCodeMaxLength(); i++)
        code.append(this.rdm.nextInt(10)); 
    } while (code.toString() == this.tile.validChest.code);
    return code.toString();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\ui\UIBruteforcer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */