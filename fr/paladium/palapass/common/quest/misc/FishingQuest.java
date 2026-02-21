package fr.paladium.palapass.common.quest.misc;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FishingQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.FISHING;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      if (itemStack.func_77969_a(itemStackQuest) || (itemStack.func_77984_f() && itemStack.func_77973_b().equals(itemStackQuest.func_77973_b()))) {
        String fishingItemProgressMessage = PalapassTranslateEnum.FISHING_ITEM_PROGRESS.textOrDefault(entityPlayer, "Pêcher des " + itemStack
            
            .func_82833_r() + " §c{VALUE}/{QUANTITY}", new Object[] { itemStack
              .func_82833_r() });
        quest.questProgress(entityPlayer, quantity, fishingItemProgressMessage);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\FishingQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */