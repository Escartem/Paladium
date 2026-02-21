package fr.paladium.palaspawner.common.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaspawner.common.block.BlockEmptySpawner;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.tile.Tier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;

public class AnvilListener {
  @SubscribeEvent
  public void onAnvilUpdate(AnvilUpdateEvent event) {
    ItemStack left = event.left;
    ItemStack right = event.right;
    if (left == null || right == null || left.func_77973_b() != Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER) || right.func_77973_b() != Item.func_150898_a(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER))
      return; 
    NBTTagCompound leftTag = left.func_77978_p();
    NBTTagCompound rightTag = right.func_77978_p();
    if (leftTag == null || rightTag == null)
      return; 
    String leftId = leftTag.func_74779_i("entityId");
    String rightId = rightTag.func_74779_i("entityId");
    if (leftId == null || rightId == null || !leftId.equals(rightId))
      return; 
    int leftSouls = leftTag.func_74762_e("souls");
    int rightSouls = rightTag.func_74762_e("souls");
    int souls = Math.min(leftSouls + rightSouls, Tier.FOUR.getRequiredSouls());
    event.output = BlockEmptySpawner.createSpawnerItemStack(leftId, souls);
    event.cost = (int)Math.ceil((souls / 10));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\listener\AnvilListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */