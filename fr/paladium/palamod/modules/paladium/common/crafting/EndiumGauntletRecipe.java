package fr.paladium.palamod.modules.paladium.common.crafting;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EndiumGauntletRecipe implements IRecipe {
  public final int recipeWidth;
  
  public final int recipeHeight;
  
  public final ItemStack[] recipeItems;
  
  public EndiumGauntletRecipe(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_) {
    this.recipeWidth = p_i1917_1_;
    this.recipeHeight = p_i1917_2_;
    this.recipeItems = p_i1917_3_;
  }
  
  public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
    for (int i = 0; i <= 3 - this.recipeWidth; i++) {
      for (int j = 0; j <= 3 - this.recipeHeight; j++) {
        if (checkMatch(p_77569_1_, i, j, true))
          return true; 
        if (checkMatch(p_77569_1_, i, j, false))
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        int i1 = k - p_77573_2_;
        int j1 = l - p_77573_3_;
        ItemStack itemstack = null;
        if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
          if (p_77573_4_) {
            itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
          } else {
            itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
          }  
        ItemStack itemstack1 = p_77573_1_.func_70463_b(k, l);
        if (itemstack1 != null || itemstack != null) {
          if ((itemstack1 == null && itemstack != null) || (itemstack1 != null && itemstack == null))
            return false; 
          if (itemstack.func_77973_b() != itemstack1.func_77973_b())
            return false; 
          if (itemstack.func_77960_j() != 32767 && itemstack
            .func_77960_j() != itemstack1.func_77960_j())
            return false; 
        } 
      } 
    } 
    return true;
  }
  
  public ItemStack func_77572_b(InventoryCrafting inventory) {
    ItemStack gauntlet = func_77571_b();
    LegendaryStone incluedStone = (LegendaryStone)inventory.func_70301_a(4).func_77973_b();
    NBTTagCompound tag = gauntlet.func_77978_p();
    if (tag == null) {
      tag = new NBTTagCompound();
      gauntlet.func_77982_d(tag);
    } 
    ArrayList<Integer> integerArray = new ArrayList<>();
    if (tag.func_74764_b("upgradearray"))
      for (int i : tag.func_74759_k("upgradearray"))
        integerArray.add(Integer.valueOf(i));  
    integerArray.add(Integer.valueOf(incluedStone.getEffect().getType()));
    tag.func_74783_a("upgradearray", UpgradeHelper.convertIntegers(integerArray));
    tag.func_74768_a("modifiersammount", tag.func_74762_e("modifiersammount") + 1);
    return gauntlet;
  }
  
  public int func_77570_a() {
    return 9;
  }
  
  public ItemStack func_77571_b() {
    return new ItemStack(ItemsRegister.ENDIUM_GAUNTLET);
  }
  
  public static EndiumGauntletRecipe toRecipe(ItemStack p_92103_1_, Object... p_92103_2_) {
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
    EndiumGauntletRecipe shapedrecipes = new EndiumGauntletRecipe(j, k, aitemstack, p_92103_1_);
    return shapedrecipes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\crafting\EndiumGauntletRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */