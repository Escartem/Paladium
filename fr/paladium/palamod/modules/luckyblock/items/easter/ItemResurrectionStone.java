package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemResurrectionStone extends Item implements ITooltipInformations {
  public static final String NAME = "resurrection_stone";
  
  public static final float HEAL_AMOUNT = 8.0F;
  
  public static final String RESURRECTION_MESSAGE = "§aTu as ressuscité mais ta pierre de résurrection a été consommée.";
  
  private static final String DESCRIPTION = "Permet au joueur qui la possède et meurt de ressusciter. Il est freeze pendant deux secondes et il réapparaît avec 4 cœurs.";
  
  public ItemResurrectionStone() {
    func_77655_b("resurrection_stone");
    func_111206_d("palamod:resurrection_stone");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet au joueur qui la possède et meurt de ressusciter. Il est freeze pendant deux secondes et il réapparaît avec 4 cœurs.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemResurrectionStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */