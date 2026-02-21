package fr.paladium.palaspawner.common.item;

import com.google.common.base.Strings;
import fr.paladium.palaspawner.common.tile.Tier;
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
  
  public String func_77653_i(ItemStack stack) {
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null)
      return "§fEmpty Spawner"; 
    String entity = compound.func_74779_i("entityId");
    if (Strings.isNullOrEmpty(entity))
      return "§fEmpty Spawner"; 
    return "§fSpawner §7(" + entity + ")";
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lores, boolean flag) {
    if (!item.func_77942_o()) {
      lores.add("§cNe contient aucune âme");
      return;
    } 
    NBTTagCompound compound = item.func_77978_p();
    String entity = compound.func_74779_i("entityId");
    if (Strings.isNullOrEmpty(entity)) {
      lores.add("§cNe contient aucune âme");
      return;
    } 
    int count = compound.func_74762_e("souls");
    int tier = Tier.getTier(count).ordinal();
    lores.add(((tier == 0) ? "§7" : ((tier == 1) ? "§e" : ((tier == 2) ? "§a" : ((tier == 3) ? "§2" : "§6")))) + "Tier " + tier);
    lores.add("§e" + count + " âmes de " + entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\item\ItemEmptySpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */