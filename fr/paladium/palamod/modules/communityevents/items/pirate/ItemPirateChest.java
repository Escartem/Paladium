package fr.paladium.palamod.modules.communityevents.items.pirate;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemPirateChest extends Item {
  public ItemPirateChest() {
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("piratechest");
    func_111206_d("palamod:piratechest");
    func_77625_d(1);
  }
  
  public int getRenderPasses(int metadata) {
    return 2;
  }
  
  public boolean func_77623_v() {
    return true;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§eVous avez dévérouillé un coffre pirate !"));
      List<ItemStack> randomList = new ArrayList<>();
      randomList.add(new ItemStack(ItemsRegister.PALADIUM_INGOT, 32));
      randomList.add(new ItemStack(BlocksRegister.BLOCK_PALADIUM, 6));
      randomList.add(new ItemStack(ItemsRegister.COMPRESSED_PALADIUM, 1));
      randomList.add(new ItemStack((Item)ItemsRegister.STICK_GOD, 1));
      randomList.add(new ItemStack(ItemsRegister.FINDIUM, 16));
      randomList.add(new ItemStack(Blocks.field_150340_R, 32));
      randomList.add(new ItemStack((Item)ItemsRegister.UNCLAIMFINDER_BLEU, 1));
      ItemStack hammerEnchant1 = new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM);
      UpgradeHelper.applyUpgrade(hammerEnchant1, 1);
      UpgradeHelper.applyUpgrade(hammerEnchant1, 1);
      UpgradeHelper.applyUpgrade(hammerEnchant1, 8);
      randomList.add(hammerEnchant1);
      ItemStack hammerEnchant2 = new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM);
      UpgradeHelper.applyUpgrade(hammerEnchant2, 2);
      UpgradeHelper.applyUpgrade(hammerEnchant2, 2);
      UpgradeHelper.applyUpgrade(hammerEnchant2, 2);
      randomList.add(hammerEnchant2);
      int rdm = (new Random()).nextInt(randomList.size());
      item.field_77994_a--;
      InventoryUtils.giveOrDropitems(player, randomList.get(rdm));
    } 
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\items\pirate\ItemPirateChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */