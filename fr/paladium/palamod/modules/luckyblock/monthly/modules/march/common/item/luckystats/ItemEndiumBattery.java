package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemEndiumBattery extends Item implements ITooltipInformations {
  private final String DESCRIPTION = "Peut être combiné avec un clique droit pour réparer et améliorer ces objets : pistolet laser et moon boots.";
  
  public ItemEndiumBattery() {
    func_77655_b("endium_battery");
    func_111206_d("palamod:endium_battery");
    func_77637_a(PLuckyBlock.TAB);
    this.field_77777_bU = 1;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Peut être combiné avec un clique droit pour réparer et améliorer ces objets : pistolet laser et moon boots.");
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return stack; 
    int countValid = 0;
    int validSlot = -1;
    int i = 0;
    for (ItemStack is : player.field_71071_by.field_70462_a) {
      if (is != null && (is.func_77973_b() == ItemsRegister.MOON_BOOTS || is.func_77973_b() == ItemsRegister.LASER_GUN)) {
        countValid++;
        validSlot = i;
      } 
      i++;
    } 
    if (countValid > 1) {
      MonthlyUtils.sendMessage(player, new String[] { "§eVous devez avoir §cUN SEUL §eitem améliorable dans votre inventaire." });
      return stack;
    } 
    if (countValid == 0) {
      MonthlyUtils.sendMessage(player, new String[] { "§cVous n'avez aucun item à améliorer dans votre inventaire." });
      return stack;
    } 
    if (validSlot != -1 && player.field_71071_by.field_70462_a[validSlot] != null) {
      ItemStack targetItem = player.field_71071_by.field_70462_a[validSlot];
      if (targetItem.func_77973_b() == ItemsRegister.MOON_BOOTS || targetItem.func_77973_b() == ItemsRegister.LASER_GUN) {
        MonthlyUtils.decrementCurrentItem(player, stack);
        NBTTagCompound nbt = new NBTTagCompound();
        if (targetItem.func_77942_o())
          nbt = targetItem.func_77978_p(); 
        nbt.func_74757_a("endium", true);
        targetItem.func_77982_d(nbt);
        targetItem.func_77964_b(0);
        MonthlyUtils.sendMessage(player, new String[] { "§d" + targetItem.func_82833_r() + " §eAmélioration en Endium: §aConfirmée" });
      } 
    } 
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemEndiumBattery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */