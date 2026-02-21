package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.common.utils.forge.Cuboid;
import fr.paladium.pet.common.utils.forge.Location;
import fr.paladium.pet.server.skill.data.ItemMeta;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import fr.paladium.pet.server.skill.handler.impl.active.data.BlockTickable;
import fr.paladium.pet.server.skill.listener.tickable.TickableListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GravitationalAxeSkill extends ASkillHandler {
  public static final String ID = "gravitational_axe";
  
  public GravitationalAxeSkill() {
    super("gravitational_axe");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 1.0D)
      return false; 
    GravitationalAxeSkillTask task = new GravitationalAxeSkillTask(player, value);
    task.start();
    return true;
  }
  
  @Schedule(async = true, repeat = 1)
  public static class GravitationalAxeSkillTask extends ATask {
    private final EntityPlayerMP player;
    
    private final double value;
    
    public GravitationalAxeSkillTask(EntityPlayerMP player, double value) {
      super("Gravitational-" + UUID.randomUUID());
      this.player = player;
      this.value = value;
    }
    
    private Cuboid getCuboid(EntityPlayerMP player, World world, double value) {
      return new Cuboid(world, player.field_70165_t - value, player.field_70163_u - value, player.field_70161_v - value, player.field_70165_t + value, player.field_70163_u + value, player.field_70161_v + value);
    }
    
    private void axe(EntityPlayerMP player, World world, double value) {
      Cuboid cuboid = getCuboid(player, world, value);
      HashMap<ItemMeta, Integer> itemCounts = new HashMap<>();
      long now = System.currentTimeMillis();
      int increment = 0;
      int currentBlocks = 0;
      for (Location location : cuboid.getLocations()) {
        int blockX = location.getBlockX();
        int blockY = location.getBlockY();
        int blockZ = location.getBlockZ();
        if (!world.func_72899_e(blockX, blockY, blockZ))
          continue; 
        Block block = world.func_147439_a(blockX, blockY, blockZ);
        if (!(block instanceof net.minecraft.block.BlockLog) || !PetUtils.canInteract((EntityPlayer)player, block, blockX, blockY, blockZ))
          continue; 
        List<ItemStack> drops = block.getDrops(world, blockX, blockY, blockZ, world.func_72805_g(blockX, blockY, blockZ), 0);
        if (drops == null)
          continue; 
        for (ItemStack stack : drops) {
          if (stack == null)
            continue; 
          ItemMeta key = ItemMeta.of(stack);
          int count = ((Integer)itemCounts.getOrDefault(key, Integer.valueOf(0))).intValue();
          itemCounts.put(key, Integer.valueOf(count + stack.field_77994_a));
        } 
        TickableListener.AXE_TICKABLES.add(BlockTickable.of(blockX, blockY, blockZ, Blocks.field_150350_a, now + 2L * increment));
        currentBlocks++;
        if (currentBlocks >= 10) {
          increment++;
          currentBlocks = 0;
        } 
      } 
      FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
              for (Map.Entry<ItemMeta, Integer> entry : (Iterable<Map.Entry<ItemMeta, Integer>>)itemCounts.entrySet()) {
                int remaining = ((Integer)entry.getValue()).intValue();
                ItemMeta item = entry.getKey();
                while (remaining > 0) {
                  int stackSize = Math.min(remaining, 64);
                  ItemStack stack = new ItemStack(item.getItem(), stackSize, item.getMetadata());
                  InventoryUtils.giveOrDropitems((EntityPlayer)player, stack);
                  remaining -= stackSize;
                } 
              } 
            } });
    }
    
    public void run() {
      if (this.player == null || this.player.field_70128_L)
        return; 
      axe(this.player, this.player.field_70170_p, this.value);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\GravitationalAxeSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */