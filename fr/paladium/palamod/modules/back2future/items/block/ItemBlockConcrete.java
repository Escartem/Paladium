package fr.paladium.palamod.modules.back2future.items.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.back2future.blocks.BlockConcrete;
import fr.paladium.palamod.modules.back2future.blocks.BlockConcretePowder;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockConcrete extends ItemBlock {
  public ItemBlockConcrete(Block p_i45328_1_) {
    super(p_i45328_1_);
  }
  
  public String func_77653_i(ItemStack p_77653_1_) {
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      if (this.field_150939_a instanceof BlockConcretePowder)
        return I18n.func_135052_a("tile.concrete_powder.name", new Object[] { I18n.func_135052_a("color." + ((BlockConcretePowder)this.field_150939_a)
                .getColor().getUnlocalizedName(), new Object[0]) }); 
      if (this.field_150939_a instanceof BlockConcrete)
        return I18n.func_135052_a("tile.concrete.name", new Object[] { I18n.func_135052_a("color." + ((BlockConcrete)this.field_150939_a)
                .getColor().getUnlocalizedName(), new Object[0]) }); 
      return I18n.func_135052_a(this.field_150939_a.func_149739_a(), new Object[0]);
    } 
    return "";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\block\ItemBlockConcrete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */