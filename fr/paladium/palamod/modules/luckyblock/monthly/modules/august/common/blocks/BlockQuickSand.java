package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.QuickSandData;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockQuickSand extends BlockFalling {
  public static final String DESCRIPTION = "desc";
  
  public static final String NAME = "quick_sand";
  
  private final Map<UUID, QuickSandData> dataMap;
  
  public BlockQuickSand() {
    super(Material.field_151595_p);
    func_149663_c("quick_sand");
    func_149658_d("palamod:quick_sand");
    func_149647_a(PLuckyBlock.TAB);
    this.dataMap = new HashMap<>();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  private QuickSandData getData(EntityPlayer player, int x, int y, int z) {
    UUID uuid = player.func_110124_au();
    QuickSandData data = this.dataMap.get(uuid);
    if (data == null) {
      data = new QuickSandData(player);
      this.dataMap.put(uuid, data);
      return data;
    } 
    DoubleLocation location = data.getLocation();
    if (location.getBlockX() != x || location.getBlockY() != y || location.getBlockZ() != z)
      data.update(player); 
    return data;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (!(entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)entity;
    player.field_70159_w = 0.0D;
    player.field_70181_x = 0.07D;
    player.field_70179_y = 0.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\blocks\BlockQuickSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */