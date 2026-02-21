package fr.paladium.palamod.common.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class BaseFood extends ItemFood {
  protected String field_77774_bZ;
  
  public BaseFood(String unlocalizedName, int amount, float saturation, boolean isWolfFood) {
    super(amount, saturation, isWolfFood);
    this.field_77774_bZ = unlocalizedName;
    func_77655_b("food." + this.field_77774_bZ);
    func_111206_d("palamod:food/" + this.field_77774_bZ);
    func_77637_a((CreativeTabs)Registry.FOOD_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */