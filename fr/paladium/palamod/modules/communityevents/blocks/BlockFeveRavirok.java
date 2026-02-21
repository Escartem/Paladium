package fr.paladium.palamod.modules.communityevents.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.communityevents.ClientProxy;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveRavirok;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFeveRavirok extends Block {
  public BlockFeveRavirok() {
    super(Material.field_151576_e);
    func_149663_c("feve_ravirok");
    func_149658_d("palamod:feve_ravirok");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
    func_149676_a(0.0F, 0.0F, 0.3F, 1.0F, 1.0F, 0.9F);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderFeveRavirok;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityFeveRavirok();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    int meta = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    world.func_72921_c(x, y, z, meta, 2);
  }
  
  public void func_149719_a(IBlockAccess iblockaccess, int x, int y, int z) {
    int metadata = iblockaccess.func_72805_g(x, y, z);
    switch (metadata) {
      case 0:
        func_149676_a(0.0F, 0.0F, 0.3F, 1.0F, 1.0F, 0.9F);
        break;
      case 1:
        func_149676_a(0.1F, 0.0F, 0.0F, 0.7F, 1.0F, 1.0F);
        break;
      case 2:
        func_149676_a(0.0F, 0.0F, 0.1F, 1.0F, 1.0F, 0.7F);
        break;
      case 3:
        func_149676_a(0.3F, 0.0F, 0.0F, 0.9F, 1.0F, 1.0F);
        break;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\blocks\BlockFeveRavirok.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */