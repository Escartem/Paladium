package fr.paladium.palawither.common.item.upgrade;

import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.wither.base.IWither;
import lombok.NonNull;

public class ItemFurtiveWitherUpgrade extends ItemWitherUpgrade {
  public ItemFurtiveWitherUpgrade() {
    super("furtive_wither_upgrade", "palawither:upgrade/furtive");
  }
  
  public boolean isValid(@NonNull IWither wither) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    return !(wither instanceof fr.paladium.palawither.common.entity.targetable.IWitherCoordinateTargetable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\ite\\upgrade\ItemFurtiveWitherUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */