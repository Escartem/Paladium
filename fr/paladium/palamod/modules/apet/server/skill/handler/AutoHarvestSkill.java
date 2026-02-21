package fr.paladium.palamod.modules.apet.server.skill.handler;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.common.blocks.BaseBlockCrops;
import fr.paladium.palamod.modules.apet.server.skill.handler.data.ItemMeta;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class AutoHarvestSkill extends ASkillHandler {
  public static final String ID = "auto_harvest";
  
  public static final int VANILLA_MAX_STAGE = 7;
  
  public AutoHarvestSkill() {
    super("auto_harvest");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    int intValue = (int)Math.floor(value);
    return harvest(player, intValue);
  }
  
  private Cuboid getCuboid(EntityPlayerMP player, int radius) {
    return new Cuboid(player.field_70165_t - radius, player.field_70163_u - radius, player.field_70161_v - radius, player.field_70165_t + radius, player.field_70163_u + radius, player.field_70161_v + radius);
  }
  
  public boolean harvest(EntityPlayerMP player, int radius) {
    Cuboid cuboid = getCuboid(player, radius);
    if (cuboid == null || cuboid.getLocations().isEmpty())
      return false; 
    World world = player.field_70170_p;
    Map<ItemMeta, Integer> itemCounts = new HashMap<>();
    Iterator<Location> iterator = cuboid.getLocations().iterator();
    (new Thread(() -> {
          while (iterator.hasNext()) {
            FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
            try {
              Thread.sleep(100L);
            } catch (Exception exception) {}
          } 
          FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
        }"PalaPet/AutoHarvestSkill-" + player.func_70005_c_())).start();
    return true;
  }
  
  private void extractItems(World world, Map<ItemMeta, Integer> itemCounts, int blockX, int blockY, int blockZ, Block block, int meta) {
    List<ItemStack> blockDrops = block.getDrops(world, blockX, blockY, blockZ, meta, 0);
    if (blockDrops != null)
      for (ItemStack drop : blockDrops) {
        ItemMeta key = ItemMeta.of(drop);
        int value = ((Integer)itemCounts.getOrDefault(key, Integer.valueOf(0))).intValue();
        itemCounts.put(key, Integer.valueOf(value + drop.field_77994_a));
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\skill\handler\AutoHarvestSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */