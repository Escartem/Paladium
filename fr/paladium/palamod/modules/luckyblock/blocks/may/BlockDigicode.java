package fr.paladium.palamod.modules.luckyblock.blocks.may;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.util.FastUUID;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDigicode extends Block {
  private IIcon[] icons = new IIcon[10];
  
  public BlockDigicode() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("digicode");
    func_149658_d("palamod:digicode/digicode_0");
    func_149711_c(100.0F);
    func_149752_b(2000.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityDigicode();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack item) {
    super.func_149689_a(world, x, y, z, entityLivingBase, item);
    if (entityLivingBase instanceof EntityPlayer && !world.field_72995_K && EventUtils.canInteract((EntityPlayer)entityLivingBase, x, y, z)) {
      EntityPlayer player = (EntityPlayer)entityLivingBase;
      String playerId = FastUUID.toString((Entity)player);
      if (UsersManager.getDigicodeInit().containsKey(playerId)) {
        ((TileEntityDigicode)UsersManager.getDigicodeInit().get(playerId)).removeNotInit();
        UsersManager.getDigicodeInit().remove(playerId);
      } 
      TileEntityDigicode tileEntityDigicode = (TileEntityDigicode)world.func_147438_o(x, y, z);
      if (tileEntityDigicode != null) {
        tileEntityDigicode.setMaster();
        UsersManager.getDigicodeInit().put(playerId, tileEntityDigicode);
        player.func_145747_a((IChatComponent)new ChatComponentText("Entrez un code entre 1 et 9 pour le digicode :"));
      } 
    } 
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    if (!world.field_72995_K) {
      TileEntityDigicode tileEntityDigicode = (TileEntityDigicode)world.func_147438_o(x, y, z);
      if (tileEntityDigicode != null) {
        tileEntityDigicode.updateDiplayNumber();
        world.func_147459_d(x, y, z, this);
        Block block = world.func_147439_a(x, y, z);
        block.func_149658_d("palamod:digicode/digicode_" + tileEntityDigicode.getDisplayNumber());
        world.func_147471_g(x, y, z);
        return true;
      } 
    } 
    return false;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    world.func_147459_d(x, y, z, this);
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  public void func_149725_f(World world, int x, int y, int z, int meta) {
    super.func_149725_f(world, x, y, z, meta);
    UsersManager.getDigicodeInit().entrySet().removeIf(entry -> (((TileEntityDigicode)entry.getValue()).func_145831_w() == world && ((TileEntityDigicode)entry.getValue()).field_145851_c == x && ((TileEntityDigicode)entry.getValue()).field_145848_d == y && ((TileEntityDigicode)entry.getValue()).field_145849_e == z));
  }
  
  public boolean func_149744_f() {
    return true;
  }
  
  public int func_149709_b(IBlockAccess blockAccess, int x, int y, int z, int side) {
    TileEntityDigicode tileEntityDigicode = (TileEntityDigicode)blockAccess.func_147438_o(x, y, z);
    if (tileEntityDigicode != null && tileEntityDigicode.generatePower())
      return 15; 
    return 0;
  }
  
  public IIcon func_149673_e(IBlockAccess blockAccess, int x, int y, int z, int side) {
    TileEntityDigicode tileEntityDigicode = (TileEntityDigicode)blockAccess.func_147438_o(x, y, z);
    if (tileEntityDigicode != null && tileEntityDigicode.getDisplayNumber() != null)
      return this.icons[tileEntityDigicode.getDisplayNumber().intValue()]; 
    return super.func_149673_e(blockAccess, x, y, z, side);
  }
  
  public void func_149651_a(IIconRegister register) {
    for (int i = 0; i < 10; i++) {
      String index = String.valueOf(i);
      this.icons[i] = register.func_94245_a("palamod:digicode/digicode_" + index);
    } 
    this.field_149761_L = this.icons[0];
    super.func_149651_a(register);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\may\BlockDigicode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */