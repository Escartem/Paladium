package fr.paladium.palamod.modules.communityevents.items.galette;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGalette extends Item {
  private Block blockGalette;
  
  public ItemGalette() {
    func_111206_d("palamod:galette");
    func_77655_b("galette");
    func_77637_a(PLuckyBlock.TAB);
    this.blockGalette = BlocksRegister.GALETTE_CAKE;
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
    if (block == Blocks.field_150431_aC && (p_77648_3_.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_) & 0x7) < 1) {
      p_77648_7_ = 1;
    } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I) {
      if (p_77648_7_ == 0)
        p_77648_5_--; 
      if (p_77648_7_ == 1)
        p_77648_5_++; 
      if (p_77648_7_ == 2)
        p_77648_6_--; 
      if (p_77648_7_ == 3)
        p_77648_6_++; 
      if (p_77648_7_ == 4)
        p_77648_4_--; 
      if (p_77648_7_ == 5)
        p_77648_4_++; 
    } 
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    if (p_77648_1_.field_77994_a == 0)
      return false; 
    if (p_77648_3_.func_147472_a(this.blockGalette, p_77648_4_, p_77648_5_, p_77648_6_, false, p_77648_7_, (Entity)null, p_77648_1_)) {
      int i1 = this.blockGalette.func_149660_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, 0);
      if (p_77648_3_.func_147465_d(p_77648_4_, p_77648_5_, p_77648_6_, this.blockGalette, i1, 3)) {
        if (p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_) == this.blockGalette) {
          this.blockGalette.func_149689_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, (EntityLivingBase)p_77648_2_, p_77648_1_);
          this.blockGalette.func_149714_e(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1);
        } 
        p_77648_3_.func_72908_a((p_77648_4_ + 0.5F), (p_77648_5_ + 0.5F), (p_77648_6_ + 0.5F), this.blockGalette.field_149762_H.func_150496_b(), (this.blockGalette.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.blockGalette.field_149762_H.func_150494_d() * 0.8F);
        p_77648_1_.field_77994_a--;
      } 
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\items\galette\ItemGalette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */