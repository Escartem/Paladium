package fr.paladium.palawither.common.item;

import fr.paladium.palawither.common.CommonProxy;
import lombok.NonNull;
import net.minecraft.item.Item;

public class ItemWitherHead extends Item {
  public ItemWitherHead(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    func_77625_d(16);
    func_77655_b(name);
    func_77637_a(CommonProxy.CREATIVE_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\item\ItemWitherHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */