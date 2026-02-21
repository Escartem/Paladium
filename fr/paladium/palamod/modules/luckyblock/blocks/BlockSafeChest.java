package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.blocks.BaseBlockContainer;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSafeChest extends BaseBlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  protected IIcon[] icons = new IIcon[2];
  
  public BlockSafeChest(String unlocalizedName) {
    super(Material.field_151573_f, 12.0F, "safechest/safe_front");
    this.field_149770_b = unlocalizedName;
    func_149752_b(0.0F);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c(this.field_149770_b);
    func_149658_d("palamod:safechest/safe_front");
    func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    setHarvestLevel("pickaxe", 2);
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
    if (meta == 4)
      return (dir == ForgeDirection.EAST) ? this.icons[0] : this.icons[1]; 
    if (meta == 2)
      return (dir == ForgeDirection.SOUTH) ? this.icons[0] : this.icons[1]; 
    if (meta == 5)
      return (dir == ForgeDirection.WEST) ? this.icons[0] : this.icons[1]; 
    return (dir == ForgeDirection.NORTH) ? this.icons[0] : this.icons[1];
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntitySafeChest();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    byte b0 = 0;
    int l = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      b0 = 2; 
    if (l == 1)
      b0 = 5; 
    if (l == 2)
      b0 = 3; 
    if (l == 3)
      b0 = 4; 
    world.func_72921_c(x, y, z, b0, 2);
    TileEntitySafeChest tile = (TileEntitySafeChest)world.func_147438_o(x, y, z);
    if (tile != null && stack.func_77978_p() != null && stack.func_77978_p().func_74764_b("Code"))
      tile.setCode(stack.func_77978_p().func_74779_i("Code")); 
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    return ret;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && !world.func_147439_a(x, y + 1, z).func_149721_r())
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_SAFE_CHEST_LOCKED, world, x, y, z); 
    return true;
  }
  
  public void onBlockExploded(World w, int x, int y, int z, Explosion explosion) {
    if (!w.field_72995_K) {
      TileEntitySafeChest te = (TileEntitySafeChest)w.func_147438_o(x, y, z);
      if (te == null)
        return; 
      ItemStack itemStack = new ItemStack((Block)this, 1);
      NBTTagCompound tag = new NBTTagCompound();
      if (te.getCode() != null && !te.getCode().isEmpty())
        tag.func_74778_a("Code", te.getCode()); 
      itemStack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(w, x, y + 1.0D, z, itemStack);
      entityitem.field_145804_b = 10;
      w.func_72838_d((Entity)entityitem);
    } 
    super.onBlockExploded(w, x, y, z, explosion);
  }
  
  public static void f(Field f) {
    f.setAccessible(true);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntitySafeChest te = (TileEntitySafeChest)world.func_147438_o(x, y, z);
      if (te == null)
        return; 
      ItemStack itemStack = new ItemStack((Block)this);
      NBTTagCompound tag = new NBTTagCompound();
      if (te.getCode() != null && !te.getCode().isEmpty())
        tag.func_74778_a("Code", te.getCode()); 
      itemStack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(world, x, y + 1.0D, z, itemStack);
      entityitem.field_145804_b = 10;
      world.func_72838_d((Entity)entityitem);
    } 
    if (player == null)
      return; 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:safechest/safe_front");
    this.icons[1] = register.func_94245_a("palamod:safechest/safe_side");
    super.func_149651_a(register);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockSafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */