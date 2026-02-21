package fr.paladium.palapass.common.quest.misc;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BlockBreakQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.BLOCK_BREAK;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, int quantity) {
    if (itemStack == null)
      return; 
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      if (quest == null || quest.getItemStack() == null)
        continue; 
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      if (itemStack.func_77969_a(itemStackQuest)) {
        String miningProgressMessage = PalapassTranslateEnum.MINING_ITEM_PROGRESS.textOrDefault(entityPlayer, "Miner " + itemStack
            
            .func_82833_r() + " §c{VALUE}/{QUANTITY}", new Object[] { itemStack
              .func_82833_r() });
        quest.questProgress(entityPlayer, quantity, miningProgressMessage);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\BlockBreakQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */