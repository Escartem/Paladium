package fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks;

import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTank extends ItemBlock {
  public ItemBlockTank(Block block) {
    super(block);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    super.func_77624_a(stack, p_77624_2_, list, p_77624_4_);
    list.add("§4Contenu: §c" + ((stack.func_77942_o() && stack.func_77978_p().func_74764_b("liquid") && stack
        .func_77978_p().func_74779_i("liquid").length() > 0) ? 
        I18n.func_135052_a(stack.func_77978_p().func_74779_i("liquid") + ".name", new Object[0]) : "Vide"));
    list.add("§4Niveau: §c" + ((stack
        .func_77942_o() && stack.func_77978_p().func_74764_b("liquidLevel")) ? 
        (String)Integer.valueOf(stack.func_77978_p().func_74762_e("liquidLevel")) : "Vide"));
    list.add("§4Niveau max: §c" + ((BlockTank)this.field_150939_a).getTank().getMaxSeve());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\itemblocks\ItemBlockTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */