package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityGoldenCage;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DistanceUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.may.dialog.GoldenCageDialogManager;
import fr.paladium.pet.common.network.data.PetPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGoldenCage extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "golden_cage";
  
  private final HashMap<String, String> defaultPets;
  
  public HashMap<String, String> getDefaultPets() {
    return this.defaultPets;
  }
  
  private final HashMap<UUID, DoubleLocation> clickedCages = new HashMap<>();
  
  public HashMap<UUID, DoubleLocation> getClickedCages() {
    return this.clickedCages;
  }
  
  public BlockGoldenCage() {
    super(Material.field_151573_f);
    func_149663_c("golden_cage");
    func_149658_d("palamod:golden_cage");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
    this.defaultPets = new HashMap<>();
    this.defaultPets.put("cat", "Félinis");
    this.defaultPets.put("dog", "Cabonchien");
    this.defaultPets.put("rabbit", "Booigny");
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGoldenCage))
      return true; 
    this.clickedCages.put(player.func_110124_au(), new DoubleLocation(x, y, z));
    GoldenCageDialogManager.getInstance().sendDialog((EntityPlayerMP)player, null);
    return true;
  }
  
  public void changeDefaultPet(EntityPlayerMP player, String targetedPet) {
    if (!this.clickedCages.containsKey(player.func_110124_au()))
      return; 
    DoubleLocation location = this.clickedCages.get(player.func_110124_au());
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    if (targetedPet == null || !DistanceUtils.isNearTargetLocation((Entity)player, x, y, z) || !this.defaultPets.containsKey(targetedPet)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous êtes trop loin de la cage." });
      return;
    } 
    TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGoldenCage))
      return; 
    PetPlayer petPlayer = PetPlayer.get((EntityPlayer)player);
    if (petPlayer == null || !petPlayer.has()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous n'avez pas de familier." });
      return;
    } 
    if (petPlayer.getUnlockedSkin().equals(targetedPet)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous possédez déjà ce familier." });
      return;
    } 
    this.clickedCages.remove(player.func_110124_au());
    petPlayer.setUnlockedSkin(targetedPet);
    petPlayer.setCurrentSkin(targetedPet);
    petPlayer.sync();
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez changé l'apparence de votre familier pour " + (String)this.defaultPets.get(targetedPet) + "." });
    player.field_70170_p.func_147468_f(x, y, z);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGoldenCage))
      return; 
    ItemStack stack = new ItemStack(BlocksRegister.GOLDEN_CAGE);
    ItemUtils.spawnItemInWorld(world, x, y, z, stack);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityGoldenCage();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderGoldenCage;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Permet de capturer un familier et de choisir son apparence. Permet aussi de changer l’apparence de base du familier obtenu.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\block\BlockGoldenCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */