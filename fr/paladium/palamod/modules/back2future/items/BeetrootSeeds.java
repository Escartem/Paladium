package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class BeetrootSeeds extends ItemSeeds implements IConfigurable {
  public BeetrootSeeds() {
    super(ModBlocks.beetroot, Blocks.field_150458_ak);
    func_111206_d("beetroot_seeds");
    func_77655_b(Utils.getUnlocalisedName("beetroot_seeds"));
    func_77637_a(Back2Future.enableBeetroot ? Back2Future.creativeTab : null);
    if (Back2Future.enableBeetroot) {
      ChestGenHooks.addItem("mineshaftCorridor", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("pyramidJungleChest", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("strongholdCorridor", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("strongholdCrossing", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
      ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(new ItemStack((Item)this), 1, 2, 5));
    } 
  }
  
  public boolean isEnabled() {
    return Back2Future.enableBeetroot;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\BeetrootSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */