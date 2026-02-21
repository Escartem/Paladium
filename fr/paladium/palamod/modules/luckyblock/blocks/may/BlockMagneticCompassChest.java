package fr.paladium.palamod.modules.luckyblock.blocks.may;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMagneticCompassChest;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockMagneticCompassChest extends Block {
  private IIcon top;
  
  private IIcon front;
  
  public BlockMagneticCompassChest() {
    super(Material.field_151573_f);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("magnetic_compass_chest");
  }
  
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:magnetic_compass_chest/iron_side");
    this.top = iiconRegister.func_94245_a("palamod:magnetic_compass_chest/iron_top");
    this.front = iiconRegister.func_94245_a("palamod:magnetic_compass_chest/iron_front");
  }
  
  public IIcon func_149691_a(int side, int metadata) {
    if (side == metadata)
      return this.front; 
    if (side == 1)
      return this.top; 
    return this.field_149761_L;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityMagneticCompassChest();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    if (!world.field_72995_K)
      if (player.field_71071_by.func_146028_b(ItemsRegister.MAGNETIC_COMPASS)) {
        TileEntityMagneticCompassChest tileEntityMagneticCompassChest = (TileEntityMagneticCompassChest)world.func_147438_o(x, y, z);
        if (tileEntityMagneticCompassChest != null) {
          if (tileEntityMagneticCompassChest.use(FastUUID.toString((Entity)player))) {
            PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.STRANGE_METAL_PIECE, 2));
          } else {
            PlayerUtils.sendMessage(player, "Ce coffre est vide.");
          } 
          return true;
        } 
      } else {
        PlayerUtils.sendMessage(player, "Il vous faut une " + StatCollector.func_74838_a(ItemsRegister.MAGNETIC_COMPASS.func_77658_a() + ".name") + ".");
      }  
    return false;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
    int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\may\BlockMagneticCompassChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */