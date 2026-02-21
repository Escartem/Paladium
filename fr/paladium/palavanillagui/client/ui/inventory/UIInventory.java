package fr.paladium.palavanillagui.client.ui.inventory;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.BackButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palavanillagui.client.ui.util.IBypassAble;
import fr.paladium.palavanillagui.client.ui.util.container.PotionEffectContainer;
import fr.paladium.palavanillagui.client.ui.util.node.InventoryShortcutListNode;
import fr.paladium.palavanillagui.client.ui.util.node.InventorySlotNode;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.entity.EntityNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UIInventory extends UI implements IBypassAble {
  private static final Resource ARMOR_BADGE = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/armor_badge.png"));
  
  private static final Resource CRAFT_BADGE = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/craft_badge.png"));
  
  private static final Resource BACK_FLIPPED = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/back_flipped.png"));
  
  private static final Resource HELMET_PLACEHOLDER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/helmet_placeholder.png"));
  
  private static final Resource CHESTPLATE_PLACEHOLDER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/chestplate_placeholder.png"));
  
  private static final Resource LEGGINGS_PLACEHOLDER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/leggings_placeholder.png"));
  
  private static final Resource BOOTS_PLACEHOLDER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/boots_placeholder.png"));
  
  private static final Map<Integer, Resource> ARMOR_PLACEHOLDER = new HashMap<Integer, Resource>() {
    
    };
  
  private InventoryShortcutListNode list;
  
  private boolean bypass = false;
  
  public UIInventory() {
    super((Minecraft.func_71410_x()).field_71439_g.field_71069_bz);
  }
  
  public void init() {
    ContainerNode.create(0.0D, -3.0D, 1920.0D, 1080.0D)
      .body(background -> {
          PotionEffectContainer.create(143.0D, 127.0D, 353.0D, 826.0D).attach(background);
          BackgroundNode.create(826.0D, 826.0D).body(()).attach(background);
        }).attach(this);
    int defaultX = 500;
    int defaultY = 300;
    int x = 500;
    int y = 300;
    for (int i = 0; i < (Minecraft.func_71410_x()).field_71439_g.field_71069_bz.field_75151_b.size(); i++) {
      Slot slot = (Minecraft.func_71410_x()).field_71439_g.field_71069_bz.field_75151_b.get(i);
      if (i == 0) {
        x = 1092;
        y = 371;
      } 
      if (i == 1) {
        x = 922;
        y = 341;
      } 
      if (i > 1 && i < 3)
        x += 60; 
      if (i == 3) {
        x = 922;
        y = 401;
      } 
      if (i > 3 && i < 5)
        x += 60; 
      if (i == 5) {
        x = 615;
        y = 340;
      } 
      if (i > 5 && i < 9)
        y += 60; 
      if (i == 9) {
        x = 615;
        y = 620;
      } 
      if (i > 9 && i < 36) {
        x += 60;
        if (i == 18 || i == 27) {
          x = 615;
          y += 60;
        } 
      } 
      if (i == 36) {
        x = 615;
        y = 844;
      } 
      if (i > 36 && i < 45)
        x += 60; 
      InventorySlotNode.create(x, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).placeholder(ARMOR_PLACEHOLDER.get(Integer.valueOf(i))).x(x).attach(this);
    } 
  }
  
  public void reloadList() {
    this.list.reload();
  }
  
  public void keyPressed(char c, int keyCode, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.isCancelled())
      return; 
    if (keyCode == (Minecraft.func_71410_x()).field_71474_y.field_151445_Q.func_151463_i() && getUi().isOnTop()) {
      context.cancel(() -> {
            if (Keyboard.isKeyDown(56)) {
              this.bypass = true;
              Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiInventory((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
              return;
            } 
            ZUI.close(this);
          });
      return;
    } 
    super.keyPressed(c, keyCode, context);
  }
  
  public boolean isBypass() {
    return this.bypass;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\inventory\UIInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */