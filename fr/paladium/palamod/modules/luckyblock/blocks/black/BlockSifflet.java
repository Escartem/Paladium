package fr.paladium.palamod.modules.luckyblock.blocks.black;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntitySifflet;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSifflet extends Block implements ITooltipWiki {
  public BlockSifflet() {
    super(Material.field_151575_d);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("sifflet");
    func_149658_d("palamod:sifflet");
    func_149675_a(true);
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    boolean flag = p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_, p_149695_4_);
    TileEntitySifflet tileentitynote = (TileEntitySifflet)p_149695_1_.func_147438_o(p_149695_2_, p_149695_3_, p_149695_4_);
    if (tileentitynote != null && tileentitynote.previousRedstoneState != flag) {
      if (flag)
        tileentitynote.triggerNote(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_); 
      tileentitynote.previousRedstoneState = flag;
    } 
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (p_149727_1_.field_72995_K)
      return true; 
    TileEntitySifflet tileentitynote = (TileEntitySifflet)p_149727_1_.func_147438_o(p_149727_2_, p_149727_3_, p_149727_4_);
    if (tileentitynote != null)
      tileentitynote.triggerNote(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_); 
    return true;
  }
  
  public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
    if (!p_149699_1_.field_72995_K) {
      TileEntitySifflet tileentitynote = (TileEntitySifflet)p_149699_1_.func_147438_o(p_149699_2_, p_149699_3_, p_149699_4_);
      if (tileentitynote != null)
        tileentitynote.triggerNote(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_); 
    } 
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntitySifflet();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149696_a(World world, int x, int y, int z, int i, int j) {
    float f = (float)Math.pow(2.0D, (j - 12) / 12.0D);
    world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "palamod:sifflet", 3.0F, f);
    world.func_72869_a("note", x + 0.5D, y + 1.2D, z + 0.5D, j / 24.0D, 0.0D, 0.0D);
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#3.-lucky-block-noir";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\black\BlockSifflet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */