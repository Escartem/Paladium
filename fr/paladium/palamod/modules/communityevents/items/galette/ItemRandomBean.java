package fr.paladium.palamod.modules.communityevents.items.galette;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemRandomBean extends Item {
  public ItemRandomBean() {
    func_111206_d("palamod:random_beans");
    func_77655_b("random_beans");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§eVous avez obtenu une feve !"));
      List<ItemStack> randomList = new ArrayList<>();
      randomList.add(new ItemStack(BlocksRegister.FEVE_ARTY, 1));
      randomList.add(new ItemStack(BlocksRegister.FEVE_DANCAROK, 1));
      randomList.add(new ItemStack(BlocksRegister.FEVE_TEDAROK, 1));
      randomList.add(new ItemStack(BlocksRegister.FEVE_RAVIROK, 1));
      int rdm = world.field_73012_v.nextInt(randomList.size());
      item.field_77994_a--;
      InventoryUtils.giveOrDropitems(player, randomList.get(rdm));
    } 
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\items\galette\ItemRandomBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */