package fr.paladium.palaspawner.common.item.upgrade;

import fr.paladium.palaspawner.common.spawner.upgrade.SpawnerUpgrade;
import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AItemSpawnerUpgrade extends Item {
  public static final String NAME_PATTERN = "spawner_upgrade_%s";
  
  private final SpawnerUpgrade upgrade;
  
  public SpawnerUpgrade getUpgrade() {
    return this.upgrade;
  }
  
  public AItemSpawnerUpgrade(SpawnerUpgrade upgrade) {
    this.upgrade = upgrade;
    this.field_77777_bU = 1;
    func_77637_a((CreativeTabs)SpawnerTabs.getInstance());
    func_77655_b(String.format("spawner_upgrade_%s", new Object[] { upgrade.getId() }));
    func_111206_d("palaspawner:" + String.format("spawner_upgrade_%s", new Object[] { upgrade.getId() }));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\ite\\upgrade\AItemSpawnerUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */