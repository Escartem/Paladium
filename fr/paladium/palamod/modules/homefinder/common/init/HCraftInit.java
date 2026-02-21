package fr.paladium.palamod.modules.homefinder.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HCraftInit implements HInit {
  public void init() {
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.HOME_FINDER, 1), new Object[] { 
          "XYX", "ZWZ", "UUU", Character.valueOf('X'), Items.field_151156_bN, 
          Character.valueOf('Y'), PPRegisterer.PPItems.GLUE, Character.valueOf('Z'), Blocks.field_150411_aY, Character.valueOf('W'), 
          new ItemStack(Items.field_151144_bL, 1, 4), 
          Character.valueOf('U'), ItemsRegister.COMPRESSED_PALADIUM });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\init\HCraftInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */