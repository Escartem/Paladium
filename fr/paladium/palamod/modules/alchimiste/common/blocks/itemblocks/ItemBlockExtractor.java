package fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBlockExtractor extends ItemBlock {
  public ItemBlockExtractor(Block p_i45328_1_) {
    super(p_i45328_1_);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    super.func_77624_a(stack, p_77624_2_, list, p_77624_4_);
    int use = 144;
    int maxUse = 144;
    NBTTagCompound compound = stack.func_77978_p();
    if (compound != null) {
      if (compound.func_74764_b("use"))
        use = compound.func_74762_e("use"); 
      if (compound.func_74764_b("maxUse"))
        maxUse = compound.func_74762_e("maxUse"); 
    } else {
      list.add("§4Utilisations restante: §cNon défini");
      return;
    } 
    list.add("§4Utilisations restante: §c" + use + "/" + maxUse);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\itemblocks\ItemBlockExtractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */