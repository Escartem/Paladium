package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ContainerSuperCraftingTable;
import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.SuperCraftingManager;
import fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable.ui.node.SuperCraftingSlotItemNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class UISuperCraftingTableContainer extends UIContainer {
  private static InventoryPlayer inventory;
  
  public static InventoryCrafting craftMatrix;
  
  private static ContainerSuperCraftingTable containerSuperCraftingTable;
  
  private final World worldObj;
  
  private final int posX;
  
  private final int posY;
  
  private final int posZ;
  
  public static IInventory craftResult = (IInventory)new InventoryCraftResult();
  
  public UISuperCraftingTableContainer(GuiScreen prev, InventoryPlayer inventory2, World world, int x, int y, int z) {
    super(prev, "supercraftingtable_slot", (Container)(containerSuperCraftingTable = new ContainerSuperCraftingTable(inventory = (Minecraft.func_71410_x()).field_71439_g.field_71071_by, world, x, y, z)));
    this.worldObj = world;
    this.posX = x;
    this.posY = y;
    this.posZ = z;
    craftResult = containerSuperCraftingTable.getCraftResult();
    craftMatrix = containerSuperCraftingTable.getCraftMatrix();
    SuperCraftingManager.getInstance().setSelected(null);
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)(new SlotNodeSuperCraftingTable(craftResult, 0, width(25.35F), height(77.0F), height(9.3F), inventory.field_70458_d, (IInventory)craftMatrix, this.worldObj, this.posX, this.posY, this.posZ))
        .setTexture(SuperCraftingTableResources.SLOT_OUTPUT.getTexture()));
    int l;
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 3; i1++)
        addNode((ANode)(new SlotNode((IInventory)craftMatrix, i1 + l * 3, (width(23.0F) + i1 * width(3.4F)), (
              height(54.9F) + l * height(6.0F)), height(5.6F), height(5.6F)))
            .setTexture(SuperCraftingTableResources.SLOT.getTexture())); 
    } 
    for (l = 0; l < 3; l++) {
      for (int i1 = 0; i1 < 9; i1++)
        addNode((ANode)(new SlotNode((IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by, i1 + l * 9 + 9, (
              width(42.5F) + i1 * width(3.0F)), (height(59.3F) + l * height(5.5F)), height(5.0F), height(5.0F)))
            .setTexture(SuperCraftingTableResources.SLOT.getTexture())); 
    } 
    for (l = 0; l < 9; l++)
      addNode((ANode)(new SlotNode((IInventory)(Minecraft.func_71410_x()).field_71439_g.field_71071_by, l, (width(42.5F) + l * width(3.0F)), (
            height(60.8F) + height(15.5F)), height(5.0F), height(5.0F)))
          .setTexture(SuperCraftingTableResources.SLOT.getTexture())); 
    ScrollArea scrollArea = new ScrollArea(width(76.2F), height(27.0F), height(30.0F), width(1.6F), height(4.0F));
    ScrollableArea area = new ScrollableArea(width(37.5F), height(28.3F), width(72.9F), height(53.4F), scrollArea);
    area.setScrollTexture(SuperCraftingTableResources.SLOT.getTexture())
      .setScrollHoverTexture(SuperCraftingTableResources.SLOT.getTexture()).setScrollSpeed(50);
    int i = 0;
    for (Object recipe : SuperCraftingManager.getInstance().getRecipeList()) {
      ShapedRecipes r = (ShapedRecipes)recipe;
      SuperCraftingSlotItemNode slot = new SuperCraftingSlotItemNode(width(38.0F), (height(30.0F) + i * height(10.0F)), width(34.0F), height(9.0F), r);
      slot.setCallback(c -> {
            if (SuperCraftingManager.getInstance().getSelected() != null && r.func_77571_b().func_77973_b().equals(SuperCraftingManager.getInstance().getSelected().func_77571_b().func_77973_b())) {
              SuperCraftingManager.getInstance().setSelected(null);
            } else {
              SuperCraftingManager.getInstance().setSelected(r);
            } 
          });
      slot.setArea(area);
      addNode((ANode)slot);
      i++;
    } 
    addScrollableArea(area);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    super.preDraw(mouseX, mouseY, ticks);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation(SuperCraftingTableResources.BACKGROUND.getTexture() + ".png"), this.field_146294_l, this.field_146295_m);
    if (SuperCraftingManager.getInstance().getSelected() != null) {
      ShapedRecipes recipe = SuperCraftingManager.getInstance().getSelected();
      ((SlotNode)getSlots().get(0)).setPlaceholder(recipe.func_77571_b());
      int i = 0;
      for (ItemStack is : recipe.field_77574_d) {
        ((SlotNode)getSlots().get(i + 1)).setPlaceholder(is);
        i++;
      } 
    } 
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation(SuperCraftingTableResources.FOREGROUND.getTexture() + ".png"), this.field_146294_l, this.field_146295_m);
  }
  
  public void func_146281_b() {
    super.func_146281_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtabl\\ui\UISuperCraftingTableContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */