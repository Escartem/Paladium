package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;

public class MinedTerrain extends ALuckyEvent {
  public static Map<Vec3, Block> blockList = new HashMap<>();
  
  private Block[] blocks = new Block[] { (Block)Blocks.field_150354_m, Blocks.field_150346_d, Blocks.field_150348_b, Blocks.field_150351_n, Blocks.field_150347_e, (Block)Blocks.field_150349_c };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!EventUtils.canInteract((EntityPlayer)player, x, y, z) || player.field_70170_p
      .func_147439_a(x, y, z) == Blocks.field_150357_h)
      return; 
    Block randBlock = this.blocks[player.field_70170_p.field_73012_v.nextInt(this.blocks.length)];
    player.field_70170_p.func_147449_b(x, y, z, randBlock);
    blockList.put(Vec3.func_72443_a(x, y, z), randBlock);
  }
  
  public String getName() {
    return "Terrain miné";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "terrain_mine";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MinedTerrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */