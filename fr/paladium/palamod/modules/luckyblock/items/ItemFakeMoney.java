package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class ItemFakeMoney extends Item {
  public ItemFakeMoney() {
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(5);
    func_77655_b("fake_money");
    func_111206_d("customnpcs:npcMoney");
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    if (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)) {
      p_77624_3_.add("Ce sont des billets factices,");
      p_77624_3_.add("prend garde à toi.");
    } 
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemFakeMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */