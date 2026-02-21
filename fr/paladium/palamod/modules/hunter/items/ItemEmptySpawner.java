package fr.paladium.palamod.modules.hunter.items;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemEmptySpawner extends ItemBlock {
  public ItemEmptySpawner(Block block) {
    super(block);
    func_77625_d(1);
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lores, boolean flag) {
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("soul")) {
        NBTTagCompound soul = item.func_77978_p().func_74775_l("soul");
        int count = soul.func_74762_e("count");
        int tier = getTier(count);
        lores.add(((tier == 0) ? "§7" : ((tier == 1) ? "§e" : ((tier == 2) ? "§a" : ((tier == 3) ? "§2" : "§6")))) + "Tier " + 
            getTier(count));
        lores.add("§e" + count + " âmes de " + soul.func_74779_i("entity"));
      } else {
        lores.add("§cNe contient aucune âme");
      } 
    } else {
      lores.add("§cNe contient aucune âme");
    } 
    super.func_77624_a(item, player, lores, flag);
  }
  
  public int getTier(int count) {
    return (int)((Math.ceil((count / 50)) > 4.0D) ? 4.0D : Math.ceil((count / 50)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemEmptySpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */