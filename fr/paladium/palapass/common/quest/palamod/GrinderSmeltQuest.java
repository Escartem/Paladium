package fr.paladium.palapass.common.quest.palamod;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GrinderSmeltQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.GRINDER_SMELT;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      if (itemStack.func_77969_a(ConfigItemUtils.getItemStackFromString(quest.getItemStack())))
        quest.questProgress(entityPlayer, 1, PalapassTranslateEnum.MELT_IN_GRINDER_PROGRESS.textOrDefault(entityPlayer, "Faire fondre " + itemStack.func_82833_r() + " dans le grinder §c{VALUE}/{QUANTITY}", new Object[] { itemStack.func_82833_r() })); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\palamod\GrinderSmeltQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */