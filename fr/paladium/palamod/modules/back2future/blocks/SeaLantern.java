package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class SeaLantern extends Block implements IConfigurable {
  public SeaLantern() {
    super(Material.field_151592_s);
    func_149711_c(0.3F);
    func_149715_a(1.0F);
    func_149672_a(field_149778_k);
    func_149658_d("sea_lantern");
    func_149663_c(Utils.getUnlocalisedName("sea_lantern"));
    func_149647_a(Back2Future.enablePrismarine ? Back2Future.creativeTab : null);
  }
  
  public int func_149745_a(Random random) {
    return 2 + random.nextInt(2);
  }
  
  public int func_149679_a(int fortune, Random random) {
    return MathHelper.func_76125_a(func_149745_a(random) + random.nextInt(fortune + 1), 1, 5);
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return ModItems.prismarine_crystals;
  }
  
  public MapColor func_149728_f(int meta) {
    return MapColor.field_151677_p;
  }
  
  protected boolean func_149700_E() {
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enablePrismarine;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\SeaLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */