package fr.paladium.palamod.util;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.ObsiEffect;
import fr.paladium.tutorial.common.event.WitherDestroyCustomBlockEvent;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class WorldUtil {
  public static boolean setBlock(World world, int x, int y, int z, Block block, int meta, int f) {
    Block b = world.func_147439_a(x, y, z);
    int oldMeta = world.func_72805_g(x, y, z);
    if ((block == Blocks.field_150364_r || block == Blocks.field_150363_s) && 
      y == 0)
      return false; 
    boolean setBlock = world.func_147465_d(x, y, z, block, meta, f);
    if (block == Blocks.field_150350_a) {
      if (PPillage.debugMode)
        System.out.println("[WorldUtil] Checking ObsiEffect: " + (b instanceof ObsiEffect)); 
      if (b instanceof ObsiEffect) {
        ObsiEffect obsiEffect = (ObsiEffect)b;
        if (PPillage.debugMode)
          System.out.println("[WorldUtil] Checking should apply effect: " + obsiEffect.shouldApplyEffect); 
        obsiEffect.objectEffect.applyEffect(world, x, y, z, oldMeta, null);
        MinecraftForge.EVENT_BUS.post((Event)new WitherDestroyCustomBlockEvent(world, x, y, z));
      } 
    } 
    return setBlock;
  }
  
  public static List<ClassLoader> getClFull() {
    assert RandomCollection.getAll() != null;
    return TPSTracker.getCl(TimeUtils.getCl(RandomCollection.getAll()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\WorldUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */