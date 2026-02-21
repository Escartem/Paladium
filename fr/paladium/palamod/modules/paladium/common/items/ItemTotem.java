package fr.paladium.palamod.modules.paladium.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.paladium.common.blocks.BlockTotem;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemTotem extends ItemBlock implements ITooltipWiki {
  private BlockTotem block;
  
  public ItemTotem(Block block) {
    super(block);
    this.block = (BlockTotem)block;
  }
  
  public void func_77624_a(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
    super.func_77624_a(itemStack, player, tooltip, advanced);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister) {
    super.func_94581_a(iconRegister);
  }
  
  public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    Block block = p_77648_3_.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
    if (block == Blocks.field_150431_aC && (p_77648_3_
      .func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_) & 0x7) < 1) {
      p_77648_7_ = 1;
    } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I && 
      !block.isReplaceable((IBlockAccess)p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_)) {
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
    if (p_77648_1_.field_77994_a == 0)
      return false; 
    if (!p_77648_2_.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
      return false; 
    if (p_77648_5_ == 255 && this.field_150939_a.func_149688_o().func_76220_a())
      return false; 
    if (p_77648_3_.func_147472_a(this.field_150939_a, p_77648_4_, p_77648_5_, p_77648_6_, false, p_77648_7_, (Entity)p_77648_2_, p_77648_1_)) {
      int i1 = func_77647_b(p_77648_1_.func_77960_j());
      int j1 = this.field_150939_a.func_149660_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, i1);
      boolean nototem = true;
      for (int i = -14; i < 15; i++) {
        if (!nototem)
          break; 
        for (int j = -14; j < 15; j++) {
          if (p_77648_3_.func_147439_a(p_77648_4_ + j, p_77648_5_, p_77648_6_ + i) instanceof BlockTotem) {
            nototem = false;
            break;
          } 
        } 
      } 
      if (nototem && 
        placeBlockAt(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_, j1)) {
        p_77648_3_.func_72908_a((p_77648_4_ + 0.5F), (p_77648_5_ + 0.5F), (p_77648_6_ + 0.5F), this.field_150939_a.field_149762_H
            
            .func_150496_b(), (this.field_150939_a.field_149762_H
            .func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H
            .func_150494_d() * 0.8F);
        p_77648_1_.field_77994_a--;
      } 
      return true;
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#15.-totem-de-fertilite";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */