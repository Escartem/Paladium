package fr.paladium.palamod.modules.luckyblock.items.halloween;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPumpkinLayer extends ItemBlockWithMetadata {
  public ItemPumpkinLayer(Block p_i45326_1_) {
    super(p_i45326_1_, p_i45326_1_);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (p_77648_1_.field_77994_a == 0)
      return false; 
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
    if (block == BlocksRegister.PUMKINLAYER) {
      int i1 = p_77648_3_.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);
      int j1 = i1 & 0x7;
      if (j1 <= 6 && p_77648_3_
        .func_72855_b(this.field_150939_a
          .func_149668_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_)) && p_77648_3_
        .func_72921_c(p_77648_4_, p_77648_5_, p_77648_6_, j1 + 1 | i1 & 0xFFFFFFF8, 2)) {
        p_77648_3_.func_72908_a((p_77648_4_ + 0.5F), (p_77648_5_ + 0.5F), (p_77648_6_ + 0.5F), this.field_150939_a.field_149762_H
            
            .func_150496_b(), (this.field_150939_a.field_149762_H
            .func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H
            .func_150494_d() * 0.8F);
        p_77648_1_.field_77994_a--;
        return true;
      } 
    } 
    return super.func_77648_a(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemPumpkinLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */