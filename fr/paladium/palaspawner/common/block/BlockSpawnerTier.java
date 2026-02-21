package fr.paladium.palaspawner.common.block;

import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSpawnerTier extends Block {
  public static final String NAME_PATTERN = "spawner_structure_%s";
  
  private final Tier tier;
  
  public Tier getTier() {
    return this.tier;
  }
  
  public BlockSpawnerTier(Tier tier) {
    super(Material.field_151576_e);
    this.tier = tier;
    String formattedName = String.format("spawner_structure_%s", new Object[] { tier.name().toLowerCase() });
    func_149663_c(formattedName);
    func_149658_d("palaspawner:" + formattedName);
    func_149647_a((CreativeTabs)SpawnerTabs.getInstance());
    func_149711_c(50.0F);
    func_149752_b(2000.0F);
    func_149672_a(Block.field_149780_i);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\block\BlockSpawnerTier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */