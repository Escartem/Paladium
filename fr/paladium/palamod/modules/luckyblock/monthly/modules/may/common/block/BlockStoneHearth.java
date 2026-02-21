package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityStoneHearth;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class BlockStoneHearth extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "stone_hearth";
  
  public BlockStoneHearth() {
    super(Material.field_151573_f);
    func_149663_c("stone_hearth");
    func_149658_d("palamod:stone_hearth");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityStoneHearth))
      return true; 
    TileEntityStoneHearth hearth = (TileEntityStoneHearth)tile;
    long currentTime = world.func_82737_E();
    long lastHealTime = hearth.getLastHealTimestamp();
    if (currentTime >= lastHealTime + 1200L) {
      player.func_70606_j(player.func_110143_aJ() + 6.0F);
      hearth.setLastHealTimestamp(currentTime);
      hearth.func_70296_d();
      player.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez été soigné 3 coeurs !"));
    } else {
      long timeLeft = (lastHealTime + 1200L - currentTime) / 20L;
      int minutesLeft = (int)(timeLeft / 60L);
      int secondsLeft = (int)(timeLeft % 60L);
      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez attendre encore §e" + minutesLeft + "mn " + secondsLeft + "s§c."));
    } 
    return true;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityStoneHearth))
      return; 
    ItemStack stack = new ItemStack(BlocksRegister.STONE_HEARTH);
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
    return (TileEntity)new TileEntityStoneHearth();
  }
  
  public boolean func_149686_d() {
    return true;
  }
  
  public boolean func_149662_c() {
    return true;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Le joueur qui clique sur ce bloc régénère 3 coeurs instantanément. Cooldown de 60 secondes. Sensible à la dynamite.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\block\BlockStoneHearth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */