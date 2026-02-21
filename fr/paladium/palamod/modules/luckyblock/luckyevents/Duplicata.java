package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Duplicata extends ALuckyEvent {
  private String[] bannedItems = new String[] { "drawer", "lucky", "safechest" };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = player.field_71071_by.func_70448_g();
    if (stack == null || stack.func_77973_b() == null || isBannedItem(stack))
      for (int i = 0; i < 36; i++) {
        ItemStack st = player.field_71071_by.field_70462_a[i];
        if (st != null && st.func_77973_b() != null)
          if (!isBannedItem(st)) {
            ItemStack itemStack = st.func_77946_l();
            itemStack.field_77994_a = 1;
            player.field_71071_by.func_70441_a(itemStack);
            return;
          }  
      }  
    ItemStack newStack = stack.func_77946_l();
    newStack.field_77994_a = 1;
    player.field_71071_by.func_70441_a(stack);
  }
  
  private boolean isBannedItem(ItemStack stack) {
    for (String bannedName : this.bannedItems) {
      if (stack.func_77977_a().toLowerCase().contains(bannedName))
        return true; 
    } 
    return false;
  }
  
  public String getName() {
    return "Duplicata";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 10000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "duplicata";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Duplicata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */