package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.data;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SandStormData {
  private static final int MAX_COUNT = 30;
  
  private static final int BLOCKS_PER_TICK = 3;
  
  private final UUID playerUniqueId;
  
  private int count;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public SandStormData(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
    this.count = 1;
  }
  
  public void perform(EntityPlayerMP playerMP) {
    this.count++;
    if (playerMP == null)
      return; 
    World world = playerMP.field_70170_p;
    int currentY = MathHelper.func_76128_c(playerMP.field_70163_u);
    int currentX = MathHelper.func_76128_c(playerMP.field_70165_t);
    int currentZ = MathHelper.func_76128_c(playerMP.field_70161_v);
    int[] yCoords = getYCoords(currentY);
    for (int y : yCoords) {
      Block block = world.func_147439_a(currentX, y, currentZ);
      if (block != null && block.equals(Blocks.field_150350_a) && EventUtils.canInteract((EntityPlayer)playerMP, currentX, y, currentZ))
        world.func_147449_b(currentX, y, currentZ, (Block)Blocks.field_150354_m); 
    } 
  }
  
  public int[] getYCoords(int currentY) {
    int[] coords = new int[3];
    for (int i = 0; i < 3; i++)
      coords[i] = currentY + i + 2; 
    return coords;
  }
  
  public boolean isExpired() {
    return (this.count >= 30);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\data\SandStormData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */