package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.network.PacketClipboard;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockClipboard extends BlockContainer {
  public int clipboardAngle;
  
  public BlockClipboard() {
    super(Material.field_151575_d);
    func_149672_a(Block.field_149766_f);
  }
  
  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer player, int face, float hitx, float hity, float hitz) {
    if (player.func_70093_af()) {
      if (!world.field_72995_K) {
        dropItems(world, i, j, k);
        world.func_147468_f(i, j, k);
        return true;
      } 
    } else if (world.field_72995_K) {
      int selection = getSelectionPointFromFace(face, hitx, hity, hitz);
      PacketClipboard packet = new PacketClipboard(i, j, k, selection);
      PalaMod.getNetwork().sendToServer((IMessage)packet);
    } 
    return false;
  }
  
  private int getSelectionPointFromFace(int face, float hitx, float hity, float hitz) {
    switch (face) {
      case 2:
        return getSelectionPoint(1.0F - hitx, 1.0F - hity);
      case 3:
        return getSelectionPoint(hitx, 1.0F - hity);
      case 4:
        return getSelectionPoint(hitz, 1.0F - hity);
      case 5:
        return getSelectionPoint(1.0F - hitz, 1.0F - hity);
    } 
    return -1;
  }
  
  private int getSelectionPoint(float x, float y) {
    if (x > 0.21F && x < 0.272F) {
      float spacing = 0.0655F;
      for (int i = 0; i < 9; i++) {
        if (y > 0.23D + (i * spacing) && y < 0.285F + i * spacing)
          return i; 
      } 
    } 
    if (y > 0.83D && y < 0.868F) {
      if (x > 0.296F && x < 0.387F)
        return 10; 
      if (x > 0.599F && x < 0.843F)
        return 11; 
    } 
    return -1;
  }
  
  public Item func_149650_a(int meta, Random rando, int par3) {
    return Item.func_150898_a(Blocks.field_150350_a);
  }
  
  public float func_149712_f(World world, int i, int j, int k) {
    return 3.0F;
  }
  
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("planks_spruce");
  }
  
  public void func_149719_a(IBlockAccess blockAccess, int i, int j, int k) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    TileEntity tile = blockAccess.func_147438_o(i, j, k);
    if (tile != null && tile instanceof TileEntityClipboard) {
      TileEntityClipboard clipboard = (TileEntityClipboard)tile;
      switch (clipboard.getAngle()) {
        case 0:
          func_149676_a(0.97F, 0.08F, 0.15F, 1.0F, 0.92F, 0.85F);
          break;
        case 1:
          func_149676_a(0.15F, 0.08F, 0.97F, 0.85F, 0.92F, 1.0F);
          break;
        case 2:
          func_149676_a(0.0F, 0.08F, 0.15F, 0.03F, 0.92F, 0.85F);
          break;
        case 3:
          func_149676_a(0.15F, 0.08F, 0.0F, 0.85F, 0.92F, 0.03F);
          break;
      } 
    } 
  }
  
  public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public int func_149645_b() {
    return ClientProxy.clipboardRenderID;
  }
  
  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack itemStack) {
    int angle = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    angle++;
    angle %= 4;
    this.clipboardAngle = angle;
    TileEntityClipboard tile = (TileEntityClipboard)world.func_147438_o(i, j, k);
    tile.setAngle(this.clipboardAngle);
  }
  
  public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
    dropItems(world, i, j, k);
    super.func_149749_a(world, i, j, k, par5, par6);
  }
  
  private void dropItems(World world, int i, int j, int k) {
    Random rando = new Random();
    TileEntity tileEntity = world.func_147438_o(i, j, k);
    if (!(tileEntity instanceof IInventory))
      return; 
    IInventory inventory = (IInventory)tileEntity;
    for (int x = 0; x < inventory.func_70302_i_(); x++) {
      ItemStack item = inventory.func_70301_a(x);
      if (item != null && item.field_77994_a > 0) {
        EntityItem entityItem = new EntityItem(world, i + 0.5D, j + 0.5D, k + 0.5D, new ItemStack(item.func_77973_b(), item.field_77994_a, item.func_77960_j()));
        if (item.func_77942_o())
          entityItem.func_92059_d().func_77982_d((NBTTagCompound)item.func_77978_p().func_74737_b()); 
        entityItem.field_70159_w = 0.0D;
        entityItem.field_70181_x = 0.0D;
        entityItem.field_70179_y = 0.0D;
        world.func_72838_d((Entity)entityItem);
        item.field_77994_a = 0;
      } 
    } 
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int var2) {
    try {
      return (TileEntity)new TileEntityClipboard();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile != null && tile instanceof TileEntityClipboard) {
      TileEntityClipboard te = (TileEntityClipboard)tile;
      int angle = te.getAngle();
      switch (axis) {
        case DOWN:
          if (angle <= 0) {
            te.setAngle(3);
          } else {
            te.setAngle(angle - 1);
          } 
          return true;
        case UP:
          if (angle >= 3) {
            te.setAngle(0);
          } else {
            te.setAngle(angle + 1);
          } 
          return true;
        case UNKNOWN:
          return false;
      } 
      return false;
    } 
    return false;
  }
  
  public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {
    ForgeDirection[] axises = { ForgeDirection.UP, ForgeDirection.DOWN };
    return axises;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */