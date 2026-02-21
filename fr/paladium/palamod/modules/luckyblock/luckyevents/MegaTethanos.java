package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class MegaTethanos extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.ENDIUM_GAUNTLET);
    UpgradeHelper.applyUpgrade(stack, 11);
    UpgradeHelper.applyUpgrade(stack, 10);
    UpgradeHelper.applyUpgrade(stack, 9);
    UpgradeHelper.applyUpgrade(stack, 14);
    UpgradeHelper.applyUpgrade(stack, 12);
    UpgradeHelper.applyUpgrade(stack, 13);
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, stack));
  }
  
  public String getName() {
    return "Méga-Inéluctable";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 12500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "mineluctable";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MegaTethanos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */