package fr.paladium.palamod.modules.ajobs.common.items;

import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;

public class ItemRootSeeds extends ItemSeeds {
  public ItemRootSeeds(String unlocalized) {
    super(BlocksRegistry.ROOT_SEEDS_BLOCK, (Block)BlocksRegister.connectedDirtTilled);
    func_77655_b(unlocalized);
    func_111206_d("palajobs:" + unlocalized);
    func_77637_a((CreativeTabs)JobsTab.getInstance());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\items\ItemRootSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */