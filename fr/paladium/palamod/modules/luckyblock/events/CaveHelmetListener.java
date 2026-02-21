package fr.paladium.palamod.modules.luckyblock.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.pathfinding.Vec3i;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CaveHelmetListener {
  private final Map<Vec3i, Block> lastBlocks = new HashMap<>();
  
  private final Map<Vec3i, Long> replacedBlocks = new HashMap<>();
  
  private final Map<UUID, Long> lastTime = new HashMap<>();
  
  @SubscribeEvent
  public void onCaveHelmet(LivingEvent.LivingUpdateEvent e) {
    if (e.entityLiving instanceof EntityPlayerMP) {
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)e.entityLiving;
      if (!entityPlayerMP.field_70170_p.field_72995_K) {
        ItemStack helmet = entityPlayerMP.func_82169_q(3);
        if (helmet != null && helmet.func_77973_b() == ItemsRegister.CAVE_HELMET) {
          long lastUsedTime = ((Long)this.lastTime.getOrDefault(entityPlayerMP.func_110124_au(), Long.valueOf(0L))).longValue();
          long l1 = System.currentTimeMillis();
          if (l1 - lastUsedTime >= 1000L) {
            int newDamage = helmet.func_77960_j() + 1;
            if (newDamage >= helmet.func_77958_k()) {
              entityPlayerMP.func_70062_b(3, null);
            } else {
              helmet.func_77964_b(newDamage);
            } 
            this.lastTime.put(entityPlayerMP.func_110124_au(), Long.valueOf(l1));
          } 
        } 
      } 
    } 
    if (!(e.entityLiving instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entityLiving;
    if (!player.field_70170_p.field_72995_K)
      return; 
    if (player.func_82169_q(3) == null || player
      .func_82169_q(3).func_77973_b() != ItemsRegister.CAVE_HELMET) {
      Iterator<Map.Entry<Vec3i, Block>> iterator1 = this.lastBlocks.entrySet().iterator();
      while (iterator1.hasNext()) {
        Map.Entry<Vec3i, Block> entry = iterator1.next();
        Vec3i vec = entry.getKey();
        if (entry.getValue() != null)
          player.field_70170_p.func_147449_b(vec.getX(), vec.getY(), vec.getZ(), entry.getValue()); 
        iterator1.remove();
      } 
      this.replacedBlocks.clear();
      return;
    } 
    Set<Vec3i> caveVectors = new HashSet<>();
    for (int xRel = -1; xRel <= 1; xRel++) {
      for (int zRel = -1; zRel <= 1; zRel++) {
        int blockX = (int)player.field_70165_t + xRel;
        int blockY = (int)player.field_70163_u - 2;
        int blockZ = (int)player.field_70161_v + zRel;
        Block block = player.field_70170_p.func_147439_a(blockX, blockY, blockZ);
        if (block != BlocksRegister.CAVE_BLOCK && block != Blocks.field_150350_a && block.func_149688_o() != null && 
          !block.func_149688_o().func_76224_d() && block.func_149688_o().func_76220_a())
          caveVectors.add(new Vec3i(blockX, blockY, blockZ)); 
      } 
    } 
    long currentTime = System.currentTimeMillis();
    Iterator<Map.Entry<Vec3i, Long>> iterator = this.replacedBlocks.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Vec3i, Long> entry = iterator.next();
      if (currentTime - ((Long)entry.getValue()).longValue() < 2000L || caveVectors.contains(entry.getKey()))
        continue; 
      Block block = this.lastBlocks.get(entry.getKey());
      if (block != null) {
        Vec3i vec = entry.getKey();
        player.field_70170_p.func_147449_b(vec.getX(), vec.getY(), vec.getZ(), block);
        this.lastBlocks.remove(entry.getKey());
      } 
      iterator.remove();
    } 
    for (Vec3i vec : caveVectors) {
      Block currentBlock = player.field_70170_p.func_147439_a(vec.getX(), vec.getY(), vec.getZ());
      if (currentBlock != BlocksRegister.CAVE_BLOCK) {
        this.lastBlocks.put(vec, currentBlock);
        this.replacedBlocks.put(vec, Long.valueOf(System.currentTimeMillis()));
        player.field_70170_p.func_147449_b(vec.getX(), vec.getY(), vec.getZ(), (Block)BlocksRegister.CAVE_BLOCK);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\CaveHelmetListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */