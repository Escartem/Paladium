package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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

public class BlockTypeMachine extends BlockContainer implements ITooltipWiki {
  public int typeAngle;
  
  private String name;
  
  public BlockTypeMachine(String name) {
    super(Material.field_151575_d);
    this.name = name;
    func_149711_c(3.0F);
    func_149672_a(Block.field_149766_f);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c(name);
  }
  
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("planks_oak");
  }
  
  public void func_149719_a(IBlockAccess blockAccess, int i, int j, int k) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.92F, 1.0F);
  }
  
  public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
  }
  
  public boolean func_149686_d() {
    return true;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.typemachineID;
  }
  
  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer player, int face, float hitx, float hity, float hitz) {
    int iCheck = (int)(hitx * 2.0F);
    int iCheck2 = (int)(hitx * 3.0F);
    int kCheck = (int)(hitz * 3.0F);
    int kCheck2 = (int)(hitz * 2.0F);
    if (!world.field_72995_K) {
      TileEntityTypeMachine tileType = (TileEntityTypeMachine)world.func_147438_o(i, j, k);
      int angle = tileType.func_145832_p();
      if (tileType != null) {
        if (face == 1) {
          if (angle == 0) {
            if (iCheck == 1 && kCheck == 1) {
              boolean chaseadded = addChase(tileType, player, world);
              if (chaseadded)
                return true; 
              dropSlot(world, i, j, k, 1);
              return true;
            } 
            if (iCheck == 0 && kCheck2 == 0)
              if (player.func_70093_af()) {
                boolean hasReset = plateResetorSaveBook(tileType, world, player);
                if (hasReset) {
                  tileType.booklistset();
                  return true;
                } 
              } else {
                boolean pobadded = addBookorPlate(tileType, player, world);
                if (pobadded)
                  return true; 
                dropSlot(world, i, j, k, 0);
                return true;
              }  
            if (iCheck == 0 && kCheck2 == 1 && 
              tileType.hasNewPlate()) {
              dropSlot(world, i, j, k, 2);
              return true;
            } 
          } 
          if (angle == 1) {
            if (kCheck2 == 1 && iCheck2 == 1) {
              boolean chaseadded = addChase(tileType, player, world);
              if (chaseadded)
                return true; 
              dropSlot(world, i, j, k, 1);
              return true;
            } 
            if (iCheck == 1 && kCheck2 == 0)
              if (player.func_70093_af()) {
                boolean hasReset = plateResetorSaveBook(tileType, world, player);
                if (hasReset) {
                  tileType.booklistset();
                  return true;
                } 
              } else {
                boolean pobadded = addBookorPlate(tileType, player, world);
                if (pobadded)
                  return true; 
                dropSlot(world, i, j, k, 0);
                return true;
              }  
            if (iCheck == 0 && kCheck2 == 0 && 
              tileType.hasNewPlate()) {
              dropSlot(world, i, j, k, 2);
              return true;
            } 
          } 
          if (angle == 2) {
            if (iCheck == 0 && kCheck == 1) {
              boolean chaseadded = addChase(tileType, player, world);
              if (chaseadded)
                return true; 
              dropSlot(world, i, j, k, 1);
              return true;
            } 
            if (iCheck == 1 && kCheck2 == 1)
              if (player.func_70093_af()) {
                boolean hasReset = plateResetorSaveBook(tileType, world, player);
                if (hasReset) {
                  tileType.booklistset();
                  return true;
                } 
              } else {
                boolean pobadded = addBookorPlate(tileType, player, world);
                if (pobadded)
                  return true; 
                dropSlot(world, i, j, k, 0);
                return true;
              }  
            if (iCheck == 1 && kCheck2 == 0 && 
              tileType.hasNewPlate()) {
              dropSlot(world, i, j, k, 2);
              return true;
            } 
          } 
          if (angle == 3) {
            if (iCheck2 == 1 && kCheck2 == 0) {
              boolean chaseadded = addChase(tileType, player, world);
              if (chaseadded)
                return true; 
              dropSlot(world, i, j, k, 1);
              return true;
            } 
            if (iCheck == 0 && kCheck2 == 1)
              if (player.func_70093_af()) {
                boolean hasReset = plateResetorSaveBook(tileType, world, player);
                if (hasReset) {
                  tileType.booklistset();
                  return true;
                } 
              } else {
                boolean pobadded = addBookorPlate(tileType, player, world);
                if (pobadded)
                  return true; 
                dropSlot(world, i, j, k, 0);
                return true;
              }  
            if (iCheck == 1 && kCheck2 == 1 && 
              tileType.hasNewPlate()) {
              dropSlot(world, i, j, k, 2);
              return true;
            } 
          } 
        } 
        tileType.booklistset();
      } 
    } 
    Side side = FMLCommonHandler.instance().getEffectiveSide();
    if (side == Side.CLIENT) {
      TileEntityTypeMachine tileT = (TileEntityTypeMachine)world.func_147438_o(i, j, k);
      if (tileT != null)
        guiZoneCheck(player, tileT, iCheck, iCheck2, kCheck, kCheck2, face, i, j, k); 
    } 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void guiZoneCheck(EntityPlayer player, TileEntityTypeMachine tileType, int iCheck, int iCheck2, int kCheck, int kCheck2, int face, int i, int j, int k) {
    if (face == 1) {
      int angle = tileType.func_145832_p();
      if (angle == 0) {
        if (iCheck == 1 && kCheck == 1)
          return; 
        if (iCheck == 0 && kCheck2 == 0)
          return; 
        if (iCheck == 0 && kCheck2 == 1)
          return; 
      } 
      if (angle == 1) {
        if (kCheck2 == 1 && iCheck2 == 1)
          return; 
        if (iCheck == 1 && kCheck2 == 0)
          return; 
        if (iCheck == 0 && kCheck2 == 0)
          return; 
      } 
      if (angle == 2) {
        if (iCheck == 0 && kCheck == 1)
          return; 
        if (iCheck == 1 && kCheck2 == 1)
          return; 
        if (iCheck == 1 && kCheck2 == 0)
          return; 
      } 
      if (angle == 3) {
        if (iCheck2 == 1 && kCheck2 == 0)
          return; 
        if (iCheck == 0 && kCheck2 == 1)
          return; 
        if (iCheck == 1 && kCheck2 == 1)
          return; 
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void showGui(int i, int j, int k) {}
  
  public boolean addChase(TileEntityTypeMachine tile, EntityPlayer player, World world) {
    boolean validitem = false;
    ItemStack playerhand = player.func_70694_bm();
    if (playerhand != null) {
      Item ctest = playerhand.func_77973_b();
      if (ctest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemChase) {
        validitem = true;
        int chasereturn = tile.addChase(playerhand);
        if (chasereturn != 0) {
          playerhand.field_77994_a = chasereturn;
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, playerhand);
        } else {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        } 
      } 
    } 
    return validitem;
  }
  
  public boolean addBookorPlate(TileEntityTypeMachine tile, EntityPlayer player, World world) {
    ItemStack playerhand = player.func_70694_bm();
    if (playerhand != null) {
      boolean hasAdded = tile.addBookorPlate(playerhand, world);
      if (hasAdded) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean plateResetorSaveBook(TileEntityTypeMachine tile, World world, EntityPlayer player) {
    boolean hasReset = tile.resetPlate();
    boolean hasSavedBook = tile.saveBook(world);
    boolean hasEnchantedBook = tile.enchantPlate(player);
    if (hasReset || hasSavedBook || hasEnchantedBook)
      return true; 
    return false;
  }
  
  public void dropSlot(World world, int i, int j, int k, int slot) {
    TileEntity tileEntity = world.func_147438_o(i, j, k);
    if (!(tileEntity instanceof IInventory))
      return; 
    IInventory inventory = (IInventory)tileEntity;
    TileEntityTypeMachine typeTile = (TileEntityTypeMachine)tileEntity;
    ItemStack stuff = inventory.func_70301_a(slot);
    if (stuff != null && stuff.field_77994_a > 0) {
      EntityItem entityItem = new EntityItem(world, (i + 0.5F), (j + 1.2F), (k + 0.5F), new ItemStack(stuff.func_77973_b(), stuff.field_77994_a, stuff.func_77960_j()));
      if (stuff.func_77942_o())
        entityItem.func_92059_d().func_77982_d((NBTTagCompound)stuff.func_77978_p().func_74737_b()); 
      entityItem.field_70159_w = 0.0D;
      entityItem.field_70181_x = 0.0D;
      entityItem.field_70179_y = 0.0D;
      world.func_72838_d((Entity)entityItem);
      stuff.field_77994_a = 0;
      inventory.func_70299_a(slot, null);
      world.func_147471_g(i, j, k);
    } 
  }
  
  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack itemStack) {
    int angle = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    angle++;
    angle %= 4;
    this.typeAngle = angle;
    world.func_72921_c(i, j, k, this.typeAngle, 0);
    if (!world.field_72995_K) {
      Side side = FMLCommonHandler.instance().getEffectiveSide();
      if (side == Side.SERVER) {
        TileEntityTypeMachine tile = (TileEntityTypeMachine)world.func_147438_o(i, j, k);
        tile.booklistset();
      } 
    } 
  }
  
  public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
    dropItems(world, i, j, k);
    super.func_149749_a(world, i, j, k, par5, par6);
  }
  
  private void dropItems(World world, int i, int j, int k) {
    Random rando = world.field_73012_v;
    TileEntity tileEntity = world.func_147438_o(i, j, k);
    if (!(tileEntity instanceof IInventory))
      return; 
    IInventory inventory = (IInventory)tileEntity;
    for (int x = 0; x < inventory.func_70302_i_(); x++) {
      ItemStack item = inventory.func_70301_a(x);
      if (item != null && item.field_77994_a > 0) {
        float ri = rando.nextFloat() * 0.8F + 0.1F;
        float rj = rando.nextFloat() * 0.8F + 0.1F;
        float rk = rando.nextFloat() * 0.8F + 0.1F;
        EntityItem entityItem = new EntityItem(world, (i + ri), (j + rj), (k + rk), new ItemStack(item.func_77973_b(), item.field_77994_a, item.func_77960_j()));
        if (item.func_77942_o())
          entityItem.func_92059_d().func_77982_d((NBTTagCompound)item.func_77978_p().func_74737_b()); 
        float factor = 0.05F;
        entityItem.field_70159_w = rando.nextGaussian() * factor;
        entityItem.field_70181_x = rando.nextGaussian() * factor + 0.20000000298023224D;
        entityItem.field_70179_y = rando.nextGaussian() * factor;
        world.func_72838_d((Entity)entityItem);
        item.field_77994_a = 0;
      } 
    } 
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    try {
      return new TileEntityTypeMachine();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  public void func_149674_a(World world, int i, int j, int k, Random par5Random) {
    if (!world.field_72995_K && (world.func_72864_z(i, j, k) || world
      .func_72864_z(i, j + 1, k))) {
      TileEntity tileEntity = world.func_147438_o(i, j, k);
      if (tileEntity != null && tileEntity instanceof TileEntityTypeMachine) {
        TileEntityTypeMachine typeTile = (TileEntityTypeMachine)tileEntity;
        typeTile.setPlate();
      } 
    } 
  }
  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    if (!par5.isAir((IBlockAccess)par1World, par2, par3, par4) && par5.func_149744_f()) {
      boolean var6 = (par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4));
      if (var6)
        par1World.func_147464_a(par2, par3, par4, (Block)this, func_149738_a(par1World)); 
    } 
  }
  
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile != null && tile instanceof TileEntityTypeMachine) {
      TileEntityTypeMachine te = (TileEntityTypeMachine)tile;
      ItemStack slot0 = te.func_70301_a(0);
      ItemStack slot1 = te.func_70301_a(1);
      ItemStack slot2 = te.func_70301_a(2);
      int meta = te.field_145847_g;
      int newmeta = 0;
      switch (axis) {
        case DOWN:
          if (meta == 0) {
            newmeta = 3;
          } else {
            newmeta = meta - 1;
          } 
          world.func_72921_c(x, y, z, newmeta, 0);
          te.func_70299_a(0, slot0);
          te.func_70299_a(1, slot1);
          te.func_70299_a(2, slot2);
          world.func_147471_g(x, y, z);
          return true;
        case UP:
          if (meta == 3) {
            newmeta = 0;
          } else {
            newmeta = meta + 1;
          } 
          world.func_72921_c(x, y, z, newmeta, 0);
          te.func_70299_a(0, slot0);
          te.func_70299_a(1, slot1);
          te.func_70299_a(2, slot2);
          world.func_147471_g(x, y, z);
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
  
  public String getName() {
    return this.name;
  }
  
  public Block getObject() {
    return (Block)this;
  }
  
  public Class<? extends TileEntity> getTile() {
    return (Class)TileEntityTypeMachine.class;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#11.-machines-de-bibliocraft";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockTypeMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */