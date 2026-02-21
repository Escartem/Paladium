package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.JulyBiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityDecryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDecryptedComputer extends Block implements ITooltipInformations {
  public static final String DESCRIPTION = "Ordinateur contenant les données top secrètes de Paladium. Espérons qu'il reste entre de bonnes mains !";
  
  public static final String NAME = "decrypted_computer";
  
  private static final String CODE_MESSAGE = "&eCode secret du mois de juillet : &c%s";
  
  private static final String[] SECRET_MESSAGE = new String[] { "Paladium secret défense :", "Les prochains lucky block seront autour du thème de l'été. Chaleur, glaces et vacances seront au rendez-vous.", "Lucky stat 100% : nous cessons de travailler avec le forgeron. Une nouvelle mécanique unique permettra de rencontrer un nouvel ami.", "Document réservé au personnel autorisé" };
  
  public BlockDecryptedComputer() {
    super(Material.field_76233_E);
    func_149663_c("decrypted_computer");
    func_149658_d("palamod:decrypted_computer");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    if (!world.field_72995_K)
      return true; 
    MonthlyUtils.sendMessage(player, SECRET_MESSAGE);
    MonthlyUtils.sendMessage(player, new String[] { String.format("&eCode secret du mois de juillet : &c%s", new Object[] { Arrays.toString(JulyBiicodeStructure.CODES) }) });
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void openUI() {
    Minecraft mc = Minecraft.func_71410_x();
    mc.func_147108_a((GuiScreen)new GuiStats(mc.field_71462_r, mc.field_71439_g.func_146107_m()));
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderDecryptedComputer;
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
  
  private void setDefaultDirection(World world, int x, int y, int z) {
    if (world.field_72995_K)
      return; 
    Block block = world.func_147439_a(x, y, z - 1);
    Block block1 = world.func_147439_a(x, y, z + 1);
    Block block2 = world.func_147439_a(x - 1, y, z);
    Block block3 = world.func_147439_a(x + 1, y, z);
    byte b0 = 3;
    if (block.func_149730_j() && !block1.func_149730_j())
      b0 = 3; 
    if (block1.func_149730_j() && !block.func_149730_j())
      b0 = 2; 
    if (block2.func_149730_j() && !block3.func_149730_j())
      b0 = 5; 
    if (block3.func_149730_j() && !block2.func_149730_j())
      b0 = 4; 
    world.func_72921_c(x, y, z, b0, 2);
  }
  
  public TileEntity createTileEntity(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityDecryptedComputer();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Ordinateur contenant les données top secrètes de Paladium. Espérons qu'il reste entre de bonnes mains !");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\blocks\BlockDecryptedComputer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */