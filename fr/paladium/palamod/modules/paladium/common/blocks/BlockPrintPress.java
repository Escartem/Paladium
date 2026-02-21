package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import fr.paladium.palamod.modules.paladium.common.tileentities.TileEntityPrintPress;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPrintPress extends BlockContainer implements ITooltipWiki {
  public int pressAngle;
  
  private String name;
  
  public BlockPrintPress(String name) {
    super(Material.field_151573_f);
    this.name = name;
    func_149711_c(3.0F);
    func_149672_a(Block.field_149788_p);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c(name);
  }
  
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("anvil_base");
  }
  
  public boolean func_149686_d() {
    return true;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.printpressID;
  }
  
  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer player, int face, float hitx, float hity, float hitz) {
    if (!world.field_72995_K) {
      TileEntityPrintPress pressTile = (TileEntityPrintPress)world.func_147438_o(i, j, k);
      pressTile.setPlayer(player);
      int angle = pressTile.func_145832_p();
      int iCheck = (int)(hitx * 3.0F);
      int iCheck2 = (int)(hitx * 2.0F);
      int kCheck = (int)(hitz * 3.0F);
      int kCheck2 = (int)(hitz * 2.0F);
      if (face == 1) {
        if (angle == 0) {
          if (iCheck == 2 && kCheck == 1) {
            boolean inkAdded = placeInk(player, pressTile);
            if (inkAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 0);
            return true;
          } 
          if (iCheck == 1 && kCheck == 1) {
            boolean plateAdded = placePlate(player, pressTile);
            if (plateAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 1);
            return true;
          } 
          if (iCheck == 0 && kCheck2 == 0) {
            boolean bookAdded = placeBooks(player, pressTile);
            if (bookAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 2);
            return true;
          } 
          if (iCheck == 0 && kCheck2 == 1) {
            dropSlot(world, i, j, k, 3);
            return true;
          } 
        } 
        if (angle == 1) {
          if (iCheck == 1 && kCheck == 2) {
            boolean inkAdded = placeInk(player, pressTile);
            if (inkAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 0);
            return true;
          } 
          if (iCheck == 1 && kCheck == 1) {
            boolean plateAdded = placePlate(player, pressTile);
            if (plateAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 1);
            return true;
          } 
          if (iCheck2 == 1 && kCheck == 0) {
            boolean bookAdded = placeBooks(player, pressTile);
            if (bookAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 2);
            return true;
          } 
          if (iCheck2 == 0 && kCheck == 0) {
            dropSlot(world, i, j, k, 3);
            return true;
          } 
        } 
        if (angle == 2) {
          if (iCheck == 0 && kCheck == 1) {
            boolean inkAdded = placeInk(player, pressTile);
            if (inkAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 0);
            return true;
          } 
          if (iCheck == 1 && kCheck == 1) {
            boolean plateAdded = placePlate(player, pressTile);
            if (plateAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 1);
            return true;
          } 
          if (iCheck == 2 && kCheck2 == 1) {
            boolean bookAdded = placeBooks(player, pressTile);
            if (bookAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 2);
            return true;
          } 
          if (iCheck == 2 && kCheck2 == 0) {
            dropSlot(world, i, j, k, 3);
            return true;
          } 
        } 
        if (angle == 3) {
          if (iCheck == 1 && kCheck == 0) {
            boolean inkAdded = placeInk(player, pressTile);
            if (inkAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 0);
            return true;
          } 
          if (iCheck == 1 && kCheck == 1) {
            boolean plateAdded = placePlate(player, pressTile);
            if (plateAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 1);
            return true;
          } 
          if (iCheck2 == 0 && kCheck == 2) {
            boolean bookAdded = placeBooks(player, pressTile);
            if (bookAdded)
              return true; 
            if (!pressTile.getPressAntimation())
              pressTile.setPressAnimation(true); 
            dropSlot(world, i, j, k, 2);
            return true;
          } 
          if (iCheck2 == 1 && kCheck == 2) {
            dropSlot(world, i, j, k, 3);
            return true;
          } 
        } 
      } 
    } 
    return true;
  }
  
  public boolean placePlate(EntityPlayer player, TileEntityPrintPress tile) {
    ItemStack plate = player.func_70694_bm();
    if (plate != null) {
      Item plateTest = plate.func_77973_b();
      if ((plateTest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || plateTest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate) && 
        tile.addPlate(plate)) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean placeInk(EntityPlayer player, TileEntityPrintPress tile) {
    ItemStack ink = player.func_70694_bm();
    if (ink != null && 
      ink.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumInk) {
      int inkReturn = tile.addInk(ink);
      if (inkReturn == 0) {
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        return true;
      } 
      if (inkReturn > 0) {
        ink.field_77994_a = inkReturn;
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ink);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean placeBooks(EntityPlayer player, TileEntityPrintPress tile) {
    ItemStack book = player.func_70694_bm();
    if (book != null) {
      Item test = book.func_77973_b();
      if (test instanceof net.minecraft.item.ItemBook) {
        int bookReturn = tile.addBlank(book);
        if (bookReturn == 0) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
          return true;
        } 
        if (bookReturn > 0) {
          book.field_77994_a = bookReturn;
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, book);
          return true;
        } 
      } 
    } 
    return false;
  }
  
  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack itemStack) {
    int angle = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    angle++;
    angle %= 4;
    this.pressAngle = angle;
    world.func_72921_c(i, j, k, this.pressAngle, 0);
  }
  
  public void func_149749_a(World world, int i, int j, int k, Block par5, int par6) {
    dropItems(world, i, j, k);
    super.func_149749_a(world, i, j, k, par5, par6);
  }
  
  public void dropSlot(World world, int i, int j, int k, int slot) {
    TileEntity tileEntity = world.func_147438_o(i, j, k);
    if (!(tileEntity instanceof IInventory))
      return; 
    IInventory inventory = (IInventory)tileEntity;
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
      return (TileEntity)new TileEntityPrintPress();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } 
  }
  
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile != null && tile instanceof TileEntityPrintPress) {
      TileEntityPrintPress te = (TileEntityPrintPress)tile;
      ItemStack slot0 = te.func_70301_a(0);
      ItemStack slot1 = te.func_70301_a(1);
      ItemStack slot2 = te.func_70301_a(2);
      ItemStack slot3 = te.func_70301_a(3);
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
          te.func_70299_a(3, slot3);
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
          te.func_70299_a(3, slot3);
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
    return (Class)TileEntityPrintPress.class;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#11.-machines-de-bibliocraft";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockPrintPress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */