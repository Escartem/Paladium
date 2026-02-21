package fr.paladium.palaautomation.common.tile.util;

import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaautomation.common.tile.impl.TileEntityReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapelessRecipes;

public class AutoCrafterRecipe {
  private static final List<ShapelessRecipes> SHAPELESS_RECIPES = new ArrayList<>();
  
  private final List<TileEntityReceiver> receivers;
  
  private final HashMap<Integer, PipeItemData> inputItems;
  
  private final HashMap<Integer, TileEntityReceiver> inputReceivers;
  
  private final List<TileEntityReceiver> craftedReceivers;
  
  private PipeItemData outputItem;
  
  public List<TileEntityReceiver> getReceivers() {
    return this.receivers;
  }
  
  public HashMap<Integer, PipeItemData> getInputItems() {
    return this.inputItems;
  }
  
  public HashMap<Integer, TileEntityReceiver> getInputReceivers() {
    return this.inputReceivers;
  }
  
  public List<TileEntityReceiver> getCraftedReceivers() {
    return this.craftedReceivers;
  }
  
  public PipeItemData getOutputItem() {
    return this.outputItem;
  }
  
  public AutoCrafterRecipe(TileEntityCrafter crafter, List<TileEntityReceiver> fromReceivers) {
    if (SHAPELESS_RECIPES.isEmpty())
      loadShapelessRecipes(); 
    this.outputItem = null;
    this.receivers = fromReceivers;
    this.inputReceivers = new HashMap<>();
    this.craftedReceivers = new ArrayList<>();
    this.inputItems = new HashMap<>();
    InventoryCrafting crafting = new InventoryCrafting(new Container() {
          public boolean func_75145_c(EntityPlayer player) {
            return false;
          }
        },  3, 3);
    List<ItemStack> itemsForShapeless = new ArrayList<>();
    for (int i = 0; i < fromReceivers.size(); i++) {
      this.inputItems.put(Integer.valueOf(i), null);
      TileEntityReceiver receiver = fromReceivers.get(i);
      this.inputReceivers.put(Integer.valueOf(i), receiver);
      if (receiver != null) {
        PipeItemData itemData = receiver.getItemData();
        if (itemData != null) {
          ItemStack itemStack = itemData.toSingleItemStack();
          if (itemStack != null) {
            this.inputItems.put(Integer.valueOf(i), itemData);
            crafting.func_70299_a(i, itemStack);
            itemsForShapeless.add(itemStack);
            this.craftedReceivers.add(receiver);
          } 
        } 
      } 
    } 
    ItemStack recipe = CraftingManager.func_77594_a().func_82787_a(crafting, crafter.func_145831_w());
    if (recipe == null)
      recipe = findShapelessRecipe(itemsForShapeless); 
    if (recipe == null)
      return; 
    if (!crafter.canCraft(recipe))
      return; 
    this.outputItem = PipeItemData.of(recipe);
  }
  
  private ItemStack findShapelessRecipe(List<ItemStack> inputItems) {
    for (ShapelessRecipes recipe : SHAPELESS_RECIPES) {
      if (matchesShapeless(recipe, inputItems))
        return recipe.func_77571_b(); 
    } 
    return null;
  }
  
  private boolean matchesShapeless(ShapelessRecipes recipe, List<ItemStack> inputItems) {
    List<ItemStack> required = new ArrayList<>(recipe.field_77579_b);
    for (ItemStack input : inputItems) {
      Iterator<ItemStack> iterator = required.iterator();
      while (iterator.hasNext()) {
        ItemStack req = iterator.next();
        if (ItemStack.func_77989_b(input, req) && input.field_77994_a >= req.field_77994_a)
          iterator.remove(); 
      } 
    } 
    return required.isEmpty();
  }
  
  private List<TileEntityReceiver> permute(List<TileEntityReceiver> receivers) {
    if (receivers.size() < 9)
      return receivers; 
    return receivers;
  }
  
  public void decrementReceivers() {
    for (TileEntityReceiver receiver : this.craftedReceivers) {
      receiver.decrement();
      receiver.onPipeItemDataChanged();
    } 
  }
  
  public String toString() {
    return "AutoCrafterRecipe{inputItems=" + this.inputItems + ", outputItem=" + this.outputItem + '}';
  }
  
  public static void loadShapelessRecipes() {
    SHAPELESS_RECIPES.clear();
    List<?> recipeList = CraftingManager.func_77594_a().func_77592_b();
    for (Object recipeObj : recipeList) {
      if (recipeObj instanceof ShapelessRecipes)
        SHAPELESS_RECIPES.add((ShapelessRecipes)recipeObj); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\til\\util\AutoCrafterRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */