package fr.paladium.palarpg.module.dungeon.common.block.template;

import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class ABlockDungeon extends Block {
  protected ABlockDungeon(@NonNull String name, @NonNull String texture) {
    super(Material.field_151576_e);
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
    func_149711_c(-1.0F);
    func_149752_b(6000000.0F);
    func_149647_a(null);
    func_149658_d("palarpg:dungeon/" + texture);
    func_149663_c(name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\template\ABlockDungeon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */