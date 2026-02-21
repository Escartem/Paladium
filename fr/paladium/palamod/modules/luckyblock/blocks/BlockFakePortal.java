package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.back2future.entities.ai.Vec3i;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckyevents.NetherEnabled;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockFakePortal extends BlockPortal {
  public BlockFakePortal() {
    func_149663_c("fakeportal");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {}
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {}
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (world.field_72995_K)
      return; 
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      player.func_70606_j(0.0F);
      player.func_70106_y();
      world.func_147449_b(x, y, z, Blocks.field_150350_a);
      Iterator<Map.Entry<Vec3i, Block>> iterator = NetherEnabled.blocks.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<Vec3i, Block> entry = iterator.next();
        if (((Vec3i)entry.getKey()).distanceManhattan(x, y, z) < 10.0D) {
          world.func_147449_b(((Vec3i)entry.getKey()).getX(), ((Vec3i)entry.getKey()).getY(), ((Vec3i)entry.getKey()).getZ(), Blocks.field_150350_a);
          iterator.remove();
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakePortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */