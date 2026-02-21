package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class SuperCraftingManager {
  private static final SuperCraftingManager instance = new SuperCraftingManager();
  
  private List recipes = new ArrayList();
  
  private static final String __OBFID = "CL_00000090";
  
  public ShapedRecipes selected = null;
  
  public ShapedRecipes getSelected() {
    return this.selected;
  }
  
  public void setSelected(ShapedRecipes selected) {
    this.selected = selected;
  }
  
  public static final SuperCraftingManager getInstance() {
    return instance;
  }
  
  private SuperCraftingManager() {
    addRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_FURNACE, 1), new Object[] { "X#X", "XXX", 
          Character.valueOf('X'), Blocks.field_150344_f, Character.valueOf('#'), BlocksRegister.PALADIUM_FURNACE });
    addRecipe(new ItemStack(ItemsRegister.MARIO_MUSHROOM, 1), new Object[] { 
          "PMP", "RWR", "PPP", 
          Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('R'), ItemsRegister.PALADIUM_CORE, 
          Character.valueOf('M'), Blocks.field_150338_P, Character.valueOf('W'), 
          Blocks.field_150337_Q });
    addRecipe(new ItemStack(ItemsRegister.BLAZE_AND_STEEL, 1), new Object[] { "PFP", "FSF", "PFP", 
          Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM, Character.valueOf('F'), Items.field_151065_br, 
          Character.valueOf('S'), Items.field_151033_d });
    addRecipe(new ItemStack(ItemsRegister.ROLLER, 1), new Object[] { "J  ", "JEJ", "R R", 
          Character.valueOf('K'), BlocksRegister.BLOCK_FINDIUM, Character.valueOf('E'), ItemsRegister.ORB_SPEED, 
          Character.valueOf('R'), ItemsRegister.COMPRESSED_PALADIUM });
    addRecipe(new ItemStack(ItemsRegister.GRAPPIN, 1), new Object[] { 
          "SP ", "PRF", " FF", 
          Character.valueOf('S'), BlocksRegister.BLOCK_TITANE, Character.valueOf('P'), BlocksRegister.BLOCK_PALADIUM, 
          Character.valueOf('R'), ItemsRegister.PALADIUM_CORE, Character.valueOf('F'), 
          Items.field_151007_F });
    addRecipe(new ItemStack(BlocksRegister.SAFE_CHEST, 1), new Object[] { 
          "ERE", "BCB", "EBC", 
          Character.valueOf('E'), BlocksRegister.ANVIL_TITANE, Character.valueOf('R'), Items.field_151137_ax, 
          Character.valueOf('B'), BlocksRegister.BLOCK_TITANE, Character.valueOf('C'), 
          BlocksRegister.TITANE_CHEST });
    Collections.sort(this.recipes, new Comparator() {
          private static final String __OBFID = "CL_00000091";
          
          public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
            return (p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes) ? 1 : ((p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes) ? -1 : (
              
              (p_compare_2_.func_77570_a() < p_compare_1_.func_77570_a()) ? -1 : (
              (p_compare_2_.func_77570_a() > p_compare_1_.func_77570_a()) ? 1 : 0)));
          }
          
          public int compare(Object p_compare_1_, Object p_compare_2_) {
            return compare((IRecipe)p_compare_1_, (IRecipe)p_compare_2_);
          }
        });
  }
  
  public ShapedRecipes addRecipe(ItemStack p_92103_1_, Object... p_92103_2_) {
    String s = "";
    int i = 0;
    int j = 0;
    int k = 0;
    if (p_92103_2_[i] instanceof String[]) {
      String[] astring = (String[])p_92103_2_[i++];
      for (int l = 0; l < astring.length; l++) {
        String s1 = astring[l];
        k++;
        j = s1.length();
        s = s + s1;
      } 
    } else {
      while (p_92103_2_[i] instanceof String) {
        String s2 = (String)p_92103_2_[i++];
        k++;
        j = s2.length();
        s = s + s2;
      } 
    } 
    HashMap<Object, Object> hashmap;
    for (hashmap = new HashMap<>(); i < p_92103_2_.length; i += 2) {
      Character character = (Character)p_92103_2_[i];
      ItemStack itemstack1 = null;
      if (p_92103_2_[i + 1] instanceof Item) {
        itemstack1 = new ItemStack((Item)p_92103_2_[i + 1]);
      } else if (p_92103_2_[i + 1] instanceof Block) {
        itemstack1 = new ItemStack((Block)p_92103_2_[i + 1], 1, 32767);
      } else if (p_92103_2_[i + 1] instanceof ItemStack) {
        itemstack1 = (ItemStack)p_92103_2_[i + 1];
      } 
      hashmap.put(character, itemstack1);
    } 
    ItemStack[] aitemstack = new ItemStack[j * k];
    for (int i1 = 0; i1 < j * k; i1++) {
      char c0 = s.charAt(i1);
      if (hashmap.containsKey(Character.valueOf(c0))) {
        aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).func_77946_l();
      } else {
        aitemstack[i1] = null;
      } 
    } 
    ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, p_92103_1_);
    this.recipes.add(shapedrecipes);
    return shapedrecipes;
  }
  
  public void addShapelessRecipe(ItemStack p_77596_1_, Object... p_77596_2_) {
    ArrayList<ItemStack> arraylist = new ArrayList();
    Object[] aobject = p_77596_2_;
    int i = p_77596_2_.length;
    for (int j = 0; j < i; j++) {
      Object object1 = aobject[j];
      if (object1 instanceof ItemStack) {
        arraylist.add(((ItemStack)object1).func_77946_l());
      } else if (object1 instanceof Item) {
        arraylist.add(new ItemStack((Item)object1));
      } else {
        if (!(object1 instanceof Block))
          throw new RuntimeException("Invalid shapeless recipy!"); 
        arraylist.add(new ItemStack((Block)object1));
      } 
    } 
    this.recipes.add(new ShapelessRecipes(p_77596_1_, arraylist));
  }
  
  public ItemStack findMatchingRecipe(InventoryCrafting p_82787_1_, World p_82787_2_) {
    int i = 0;
    ItemStack itemstack = null;
    ItemStack itemstack1 = null;
    int j;
    for (j = 0; j < p_82787_1_.func_70302_i_(); j++) {
      ItemStack itemstack2 = p_82787_1_.func_70301_a(j);
      if (itemstack2 != null) {
        if (i == 0)
          itemstack = itemstack2; 
        if (i == 1)
          itemstack1 = itemstack2; 
        i++;
      } 
    } 
    if (i == 2 && itemstack.func_77973_b() == itemstack1.func_77973_b() && itemstack.field_77994_a == 1 && itemstack1.field_77994_a == 1 && itemstack
      .func_77973_b().isRepairable()) {
      Item item = itemstack.func_77973_b();
      int j1 = item.func_77612_l() - itemstack.func_77952_i();
      int k = item.func_77612_l() - itemstack1.func_77952_i();
      int l = j1 + k + item.func_77612_l() * 5 / 100;
      int i1 = item.func_77612_l() - l;
      if (i1 < 0)
        i1 = 0; 
      return new ItemStack(itemstack.func_77973_b(), 1, i1);
    } 
    for (j = 0; j < this.recipes.size(); j++) {
      IRecipe irecipe = this.recipes.get(j);
      if (irecipe.func_77569_a(p_82787_1_, p_82787_2_))
        return irecipe.func_77572_b(p_82787_1_); 
    } 
    return null;
  }
  
  public List getRecipeList() {
    return this.recipes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtable\SuperCraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */