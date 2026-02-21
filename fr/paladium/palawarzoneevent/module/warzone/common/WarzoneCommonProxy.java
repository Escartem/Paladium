package fr.paladium.palawarzoneevent.module.warzone.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palawarzoneevent.module.warzone.common.block.WarzoneBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public abstract class WarzoneCommonProxy extends AModProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    WarzoneBlocks.init(new CreativeTabs("warzone") {
          public Item func_78016_d() {
            return Items.field_151040_l;
          }
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\WarzoneCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */