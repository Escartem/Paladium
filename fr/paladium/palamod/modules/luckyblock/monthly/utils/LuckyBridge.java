package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.PracticeMakesPerfectEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.BlackSmithData;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class LuckyBridge {
  public static void performPracticesCraft(EntityPlayer player, ItemStack stack) {
    if (player == null || stack == null)
      return; 
    UUID uniqueId = player.func_110124_au();
    PracticeMakesPerfectEvent instance = PracticeMakesPerfectEvent.INSTANCE;
    BlackSmithData data = instance.get(uniqueId);
    List<UUID> uuids = new ArrayList<>(instance.getDatas().keySet());
    if (data == null)
      return; 
    if (!instance.isCraftingItem(data, stack))
      return; 
    data.onCraft(player, stack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\LuckyBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */