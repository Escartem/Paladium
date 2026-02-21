package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Random;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockPumpkinLayer extends BlockSnow {
  public BlockPumpkinLayer() {
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("pumpkinlayer");
    func_149658_d("palamod:pumpkin/pumpkinlayer");
    setHarvestLevel("shovel", 0);
    func_149711_c(0.01F);
    func_149672_a(field_149767_g);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149761_L = p_149651_1_.func_94245_a(func_149641_N());
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return ItemsRegister.PUMPKINCRUSH;
  }
  
  public int quantityDropped(int meta, int fortune, Random random) {
    return (meta & 0x7) + 1;
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 1;
  }
  
  public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
    super.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    p_149636_1_.func_147468_f(p_149636_3_, p_149636_4_, p_149636_5_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockPumpkinLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */