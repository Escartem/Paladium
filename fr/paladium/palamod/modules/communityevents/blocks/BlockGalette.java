package fr.paladium.palamod.modules.communityevents.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.communityevents.eep.PlayerCakeEEP;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGalette extends BlockCake {
  @SideOnly(Side.CLIENT)
  private IIcon IIcon1;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIcon2;
  
  @SideOnly(Side.CLIENT)
  private IIcon IIcon3;
  
  public BlockGalette() {
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(0.5F);
    func_149672_a(Block.field_149775_l);
    func_149663_c("galette_block");
    func_149658_d("palamod:galette");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    return (side == 1) ? this.IIcon1 : ((side == 0) ? this.IIcon2 : ((metadata > 0 && side == 4) ? this.IIcon3 : this.field_149761_L));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iIconRegister) {
    this.field_149761_L = iIconRegister.func_94245_a(func_149641_N() + "_side");
    this.IIcon3 = iIconRegister.func_94245_a(func_149641_N() + "_inner");
    this.IIcon1 = iIconRegister.func_94245_a(func_149641_N() + "_top");
    this.IIcon2 = iIconRegister.func_94245_a(func_149641_N() + "_bottom");
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    onEat(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_);
    return true;
  }
  
  public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
    onEat(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
  }
  
  private void onEat(World world, int x, int y, int z, EntityPlayer player) {
    if (player.func_71043_e(false)) {
      player.func_71024_bL().func_75122_a(2, 0.1F);
      PlayerCakeEEP property = (PlayerCakeEEP)player.getExtendedProperties("ExtPropPalaGaletteCake");
      if (property.canObtainBlock() && world.field_73012_v.nextDouble() <= 0.005D) {
        property.setLastObtainedBlock(System.currentTimeMillis());
        InventoryUtils.giveOrDropitems(player, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1));
      } 
      int l = world.func_72805_g(x, y, z) + 1;
      if (l >= 6) {
        world.func_147468_f(x, y, z);
      } else {
        world.func_72921_c(x, y, z, l, 2);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\blocks\BlockGalette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */