package fr.paladium.palawither.common.item.upgrade;

import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.wither.base.IWither;
import lombok.NonNull;

public class ItemResistanceWitherUpgrade extends ItemWitherUpgrade {
  public ItemResistanceWitherUpgrade() {
    super("resistance_wither_upgrade", "palawither:upgrade/resistance");
  }
  
  public boolean isValid(@NonNull IWither wither) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    return !(wither instanceof fr.paladium.palawither.common.entity.targetable.IWitherCoordinateTargetable);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\ite\\upgrade\ItemResistanceWitherUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */