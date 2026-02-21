package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySuperBeacon;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSuperBeacon extends BlockBase implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[2];
  
  public BlockSuperBeacon() {
    super(Material.field_151576_e);
    func_149663_c("super_beacon");
    func_149658_d("palamod:super_beacon");
    func_149711_c(5.0F);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:super_beacon");
    this.icons[1] = register.func_94245_a("palamod:super_beacon_on");
    super.func_149651_a(register);
  }
  
  public IIcon func_149673_e(IBlockAccess iblockaccess, int x, int y, int z, int side) {
    TileEntity tileEntity = iblockaccess.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntitySuperBeacon))
      return this.icons[0]; 
    TileEntitySuperBeacon superBeacon = (TileEntitySuperBeacon)tileEntity;
    if (superBeacon.getOwner() != null && !superBeacon.getOwner().isEmpty()) {
      EntityPlayer player = superBeacon.func_145831_w().func_72924_a(superBeacon.getOwner());
      if (player != null && 
        superBeacon.isValid() && player.func_70011_f(x, y, z) < 16.0D)
        return this.icons[1]; 
    } 
    return this.icons[0];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntitySuperBeacon tile = (TileEntitySuperBeacon)world.func_147438_o(x, y, z);
    if (entity instanceof EntityPlayer)
      tile.setOwner(entity.func_70005_c_()); 
    super.func_149689_a(world, x, y, z, entity, item);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntitySuperBeacon))
      return super.func_149727_a(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_); 
    TileEntitySuperBeacon superBeacon = (TileEntitySuperBeacon)tileEntity;
    if (player.func_71045_bC() != null && (
      player.func_71045_bC().func_77973_b() == Item.func_150898_a(Blocks.field_150339_S) || player
      .func_71045_bC().func_77973_b() == Item.func_150898_a(Blocks.field_150340_R) || player
      .func_71045_bC().func_77973_b() == 
      Item.func_150898_a(Blocks.field_150484_ah))) {
      int food = 1;
      if (player.func_71045_bC().func_77973_b() == 
        Item.func_150898_a(Blocks.field_150340_R)) {
        food = 2;
      } else if (player.func_71045_bC().func_77973_b() == 
        Item.func_150898_a(Blocks.field_150484_ah)) {
        food = 3;
      } 
      superBeacon.setFood(superBeacon.getFood() + food);
      (player.func_71045_bC()).field_77994_a--;
      return true;
    } 
    return super.func_149727_a(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntitySuperBeacon();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.1.-luckystats-paladium-et-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockSuperBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */