package fr.paladium.palamod.modules.alchimiste.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityAlchemistPortalController;
import fr.paladium.palamod.modules.alchimiste.common.utils.EnumPortalType;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAlchemistPortal extends BlockBase implements ITooltipWiki {
  public BlockAlchemistPortal(String name) {
    super(name);
  }
  
  public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (player.field_71071_by.func_70448_g() == null || player.func_70093_af()) {
      if (((TileEntityAlchemistPortalController)w.func_147438_o(x, y, z)).getStack() != null) {
        EntityItem entityitem = player.func_71019_a(((TileEntityAlchemistPortalController)w.func_147438_o(x, y, z)).getStack(), false);
        if (entityitem != null) {
          entityitem.field_145804_b = 0;
          entityitem.func_145797_a(player.func_70005_c_());
          ((TileEntityAlchemistPortalController)w.func_147438_o(x, y, z)).setStack(null);
        } 
      } 
      return true;
    } 
    ItemStack s = player.field_71071_by.func_70448_g();
    Item i = player.field_71071_by.func_70448_g().func_77973_b();
    if (i == ItemsRegister.AMETHYSTE_PORTAL_KEY || i == ItemsRegister.TITANE_PORTAL_KEY || i == ItemsRegister.PALADIUM_PORTAL_KEY || i == ItemsRegister.ENDIUM_PORTAL_KEY) {
      TileEntityAlchemistPortalController tile = (TileEntityAlchemistPortalController)w.func_147438_o(x, y, z);
      tile.setType((i == ItemsRegister.AMETHYSTE_PORTAL_KEY) ? EnumPortalType.AMETHYSTE.ordinal() : ((i == ItemsRegister.TITANE_PORTAL_KEY) ? EnumPortalType.TITANE
          .ordinal() : ((i == ItemsRegister.PALADIUM_PORTAL_KEY) ? EnumPortalType.PALADIUM
          .ordinal() : ((i == ItemsRegister.ENDIUM_PORTAL_KEY) ? EnumPortalType.ENDIUM
          .ordinal() : 0))));
      ItemStack toCopy = s.func_77946_l();
      toCopy.field_77994_a = 1;
      if (i == ItemsRegister.AMETHYSTE_PORTAL_KEY && this == BlocksRegister.AMETHYSTE_PORTAL_BLOCK) {
        if (tile.getStack() != null)
          return true; 
        s.field_77994_a--;
        tile.setStack(toCopy.func_77946_l());
        UseItemAchievement.performCheck(player, s, "AMETHYSTE_PORTAL_KEY");
        return true;
      } 
      if (i == ItemsRegister.TITANE_PORTAL_KEY && this == BlocksRegister.TITANE_PORTAL_BLOCK) {
        if (tile.getStack() != null)
          return true; 
        s.field_77994_a--;
        tile.setStack(toCopy.func_77946_l());
        UseItemAchievement.performCheck(player, s, "TITANE_PORTAL_KEY");
        return true;
      } 
      if (i == ItemsRegister.PALADIUM_PORTAL_KEY && this == BlocksRegister.PALADIUM_PORTAL_BLOCK) {
        if (tile.getStack() != null)
          return true; 
        s.field_77994_a--;
        tile.setStack(toCopy.func_77946_l());
        UseItemAchievement.performCheck(player, s, "PALADIUM_PORTAL_KEY");
        return true;
      } 
      if (i == ItemsRegister.ENDIUM_PORTAL_KEY && this == BlocksRegister.ENDIUM_PORTAL_BLOCK) {
        if (tile.getStack() != null)
          return true; 
        s.field_77994_a--;
        tile.setStack(toCopy.func_77946_l());
        UseItemAchievement.performCheck(player, s, "ENDIUM_PORTAL_KEY");
        return true;
      } 
    } 
    return true;
  }
  
  public void func_149725_f(World worldObj, int xCoord, int yCoord, int zCoord, int p_149725_5_) {
    if (worldObj.func_147438_o(xCoord, yCoord, zCoord) instanceof TileEntityAlchemistPortalController) {
      TileEntityAlchemistPortalController tile = (TileEntityAlchemistPortalController)worldObj.func_147438_o(xCoord, yCoord, zCoord);
      if (tile.getStack() != null) {
        ItemStack stack = tile.getStack().func_77946_l();
        EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord, zCoord, stack);
        entityitem.field_145804_b = 0;
        worldObj.func_72838_d((Entity)entityitem);
        tile.setStack(null);
      } 
    } 
    if (worldObj.func_147439_a(xCoord - 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord - 1, yCoord, zCoord).func_149664_b(worldObj, xCoord - 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord - 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord + 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord + 1, yCoord, zCoord).func_149664_b(worldObj, xCoord + 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord + 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord - 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord - 1).func_149664_b(worldObj, xCoord, yCoord, zCoord - 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord - 1, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord + 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord + 1).func_149664_b(worldObj, xCoord, yCoord, zCoord + 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord + 1, Blocks.field_150350_a);
    } 
  }
  
  public void func_149719_a(IBlockAccess w, int x, int y, int z) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
    super.func_149670_a(p_149670_1_, p_149670_2_, p_149670_3_, p_149670_4_, p_149670_5_);
  }
  
  public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
    func_149719_a((IBlockAccess)p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    return AxisAlignedBB.func_72330_a(p_149633_2_ + this.field_149759_B, p_149633_3_ + this.field_149760_C, p_149633_4_ + this.field_149754_D, p_149633_2_ + this.field_149755_E, p_149633_3_ + this.field_149756_F, p_149633_4_ + this.field_149757_G);
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return AxisAlignedBB.func_72330_a(p_149668_2_ + this.field_149759_B, p_149668_3_ + this.field_149760_C, p_149668_4_ + this.field_149754_D, p_149668_2_ + this.field_149755_E, p_149668_3_ + this.field_149756_F, p_149668_4_ + this.field_149757_G);
  }
  
  public void func_149664_b(World worldObj, int xCoord, int yCoord, int zCoord, int p_149664_5_) {
    if (worldObj.func_147438_o(xCoord, yCoord, zCoord) instanceof TileEntityAlchemistPortalController) {
      TileEntityAlchemistPortalController tile = (TileEntityAlchemistPortalController)worldObj.func_147438_o(xCoord, yCoord, zCoord);
      if (tile.getStack() != null) {
        ItemStack stack = tile.getStack().func_77946_l();
        EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord, zCoord, stack);
        entityitem.field_145804_b = 0;
        worldObj.func_72838_d((Entity)entityitem);
        tile.setStack(null);
      } 
    } 
    if (worldObj.func_147439_a(xCoord - 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord - 1, yCoord, zCoord).func_149664_b(worldObj, xCoord - 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord - 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord + 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord + 1, yCoord, zCoord).func_149664_b(worldObj, xCoord + 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord + 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord - 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord - 1).func_149664_b(worldObj, xCoord, yCoord, zCoord - 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord - 1, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord + 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord + 1).func_149664_b(worldObj, xCoord, yCoord, zCoord + 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord + 1, Blocks.field_150350_a);
    } 
  }
  
  public void func_149723_a(World worldObj, int xCoord, int yCoord, int zCoord, Explosion p_149723_5_) {
    if (worldObj.func_147438_o(xCoord, yCoord, zCoord) instanceof TileEntityAlchemistPortalController) {
      TileEntityAlchemistPortalController tile = (TileEntityAlchemistPortalController)worldObj.func_147438_o(xCoord, yCoord, zCoord);
      if (tile.getStack() != null) {
        ItemStack stack = tile.getStack().func_77946_l();
        EntityItem entityitem = new EntityItem(worldObj, xCoord, yCoord, zCoord, stack);
        entityitem.field_145804_b = 0;
        worldObj.func_72838_d((Entity)entityitem);
        tile.setStack(null);
      } 
    } 
    if (worldObj.func_147439_a(xCoord - 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord - 1, yCoord, zCoord).func_149664_b(worldObj, xCoord - 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord - 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord + 1, yCoord, zCoord) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord + 1, yCoord, zCoord).func_149664_b(worldObj, xCoord + 1, yCoord, zCoord, 0);
      worldObj.func_147449_b(xCoord + 1, yCoord, zCoord, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord - 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord - 1).func_149664_b(worldObj, xCoord, yCoord, zCoord - 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord - 1, Blocks.field_150350_a);
    } else if (worldObj.func_147439_a(xCoord, yCoord, zCoord + 1) == BlocksRegister.PORTAL_BLOCK) {
      worldObj.func_147439_a(xCoord, yCoord, zCoord + 1).func_149664_b(worldObj, xCoord, yCoord, zCoord + 1, 0);
      worldObj.func_147449_b(xCoord, yCoord, zCoord + 1, Blocks.field_150350_a);
    } 
  }
  
  public boolean a(World w, int x, int y, int z) {
    return (w.func_147439_a(x, y, z) == BlocksRegister.AMETHYSTE_PORTAL_BLOCK);
  }
  
  public boolean b(World w, int x, int y, int z) {
    return (w.func_147439_a(x, y, z) == BlocksRegister.TITANE_PORTAL_BLOCK);
  }
  
  public boolean c(World w, int x, int y, int z) {
    return (w.func_147439_a(x, y, z) == BlocksRegister.PALADIUM_PORTAL_BLOCK);
  }
  
  public boolean d(World w, int x, int y, int z) {
    return (w.func_147439_a(x, y, z) == BlocksRegister.ENDIUM_PORTAL_BLOCK);
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return (Class)TileEntityAlchemistPortalController.class;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.portalController;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityAlchemistPortalController();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#4.-les-portails";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockAlchemistPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */