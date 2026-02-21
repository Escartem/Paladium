package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityFakeCorruptedChest;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFakeCorruptedChest extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "fake_corrupted_chest";
  
  private static final String DESCRIPTION = "DESC.TXT : Quand il est posé il fait apparaître ENTITY:CORRUPTED.MIMIC. Quand il subit des dégâts il se ACTION:AWAKEN et ACTION:FIGHT les joueurs aux alentours. S’il est ACTION:KILL un ITEM:CHEST.CORRUPTED apparaît au lieu de sa mort ";
  
  public BlockFakeCorruptedChest() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("fake_corrupted_chest");
    func_149658_d("palamod:fake_corrupted_chest");
    func_149711_c(5.0F);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 22;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityFakeCorruptedChest();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Quand il est posé il fait apparaître ENTITY:CORRUPTED.MIMIC. Quand il subit des dégâts il se ACTION:AWAKEN et ACTION:FIGHT les joueurs aux alentours. S’il est ACTION:KILL un ITEM:CHEST.CORRUPTED apparaît au lieu de sa mort ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockFakeCorruptedChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */