package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityBarrel;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarrel extends Block implements ITooltipInformations {
  public static final String DESCRIPTION = "Peut être rempli avec de la poudre de creeper. Le diamètre de l'explosion dépend de la quantité dans l'objet (maximum 64). Peut être activé avec une flèche ou un courant de redstone ";
  
  public static final String NAME = "powder_barrel";
  
  public static final float DEFAULT_EXPLOSION_RADIUS = 3.0F;
  
  public static final float MAX_EXPLOSION_RADIUS = 12.0F;
  
  public static final int MAX_POWDER_AMOUNT = 64;
  
  private static final String MAX_POWDER_AMOUNT_REACHED = "&cVous ne pouvez pas ajouter plus de poudre à ce baril !";
  
  private IIcon[] icons = new IIcon[3];
  
  public BlockBarrel() {
    super(Material.field_151590_u);
    func_149663_c("powder_barrel");
    func_149658_d("palamod:powder_barrel");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityBarrel))
      return true; 
    TileEntityBarrel barrel = (TileEntityBarrel)tile;
    ItemStack heldItem = player.func_70694_bm();
    if (heldItem != null && heldItem.func_77973_b().equals(Items.field_151016_H)) {
      if (barrel.getPowderAmount() >= 64) {
        MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas ajouter plus de poudre à ce baril !" });
        return true;
      } 
      heldItem.field_77994_a--;
      if (heldItem.field_77994_a <= 0)
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
      barrel.addPowder();
    } 
    MonthlyUtils.sendMessage(player, new String[] { "&eIl y a actuellement &e" + barrel.getPowderAmount() + "/" + '@' + " &epoudres dans ce baril." });
    return true;
  }
  
  public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    int powderAmount = getPowderAmount(world, x, y, z);
    if (powderAmount <= 0) {
      super.onBlockExploded(world, x, y, z, explosion);
      return;
    } 
    explode(world, x, y, z, getExplosionRadius(powderAmount));
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    detectAndExplode(world, x, y, z);
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    super.func_149670_a(world, x, y, z, entity);
    if (world.field_72995_K)
      return; 
    if (!(entity instanceof net.minecraft.entity.projectile.EntityArrow))
      return; 
    int powderAmount = getPowderAmount(world, x, y, z);
    if (powderAmount <= 0)
      return; 
    explode(world, x, y, z, getExplosionRadius(powderAmount));
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    super.func_149695_a(world, x, y, z, block);
    detectAndExplode(world, x, y, z);
  }
  
  public boolean func_149659_a(Explosion explosion) {
    return false;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBarrel();
  }
  
  public void explode(World world, int x, int y, int z, float radius) {
    if (world.field_72995_K)
      return; 
    world.func_147468_f(x, y, z);
    world.func_147475_p(x, y, z);
    world.func_72876_a(null, x, y, z, radius, true);
  }
  
  public float getExplosionRadius(int powderAmount) {
    float radius = 3.0F + powderAmount * 0.15F;
    if (radius > 12.0F)
      return 12.0F; 
    return radius;
  }
  
  public int getPowderAmount(World world, int x, int y, int z) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityBarrel))
      return 0; 
    return ((TileEntityBarrel)tile).getPowderAmount();
  }
  
  private void detectAndExplode(World world, int x, int y, int z) {
    if (!world.func_72864_z(x, y, z))
      return; 
    int powderAmount = getPowderAmount(world, x, y, z);
    if (powderAmount <= 0)
      return; 
    explode(world, x, y, z, getExplosionRadius(powderAmount));
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_front");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[2] = register.func_94245_a(func_149641_N() + "_side");
    this.field_149761_L = this.icons[0];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
    int direction = MathHelper.func_76128_c((living.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    world.func_72921_c(x, y, z, direction, 2);
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    int meta = ba.func_72805_g(x, y, z);
    if (dir == 0)
      return this.icons[2]; 
    if ((dir == 4 && meta == 1) || (dir == 2 && meta == 2) || (dir == 5 && meta == 3) || (dir == 3 && meta == 0))
      return this.icons[0]; 
    if (dir == 1)
      return this.icons[2]; 
    return this.icons[1];
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Peut être rempli avec de la poudre de creeper. Le diamètre de l'explosion dépend de la quantité dans l'objet (maximum 64). Peut être activé avec une flèche ou un courant de redstone ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\blocks\BlockBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */