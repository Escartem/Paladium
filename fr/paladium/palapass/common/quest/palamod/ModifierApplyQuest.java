package fr.paladium.palapass.common.quest.palamod;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModifierApplyQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.MODIFIER_APPLY;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, ItemStack modifier) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      ItemStack modifierItemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getModifierItemType());
      if (itemStack != null && itemStackQuest != null && modifier != null && modifierItemStackQuest != null && 
        itemStack.func_77969_a(itemStackQuest) && modifier.func_77969_a(modifierItemStackQuest))
        quest.questProgress(entityPlayer, 1, PalapassTranslateEnum.APPLY_MODIFIER_PROGRESS.textOrDefault(entityPlayer, "Appliquer un modifier " + modifierItemStackQuest.func_82833_r() + " sur " + itemStack.func_82833_r() + " §c{VALUE}/{QUANTITY}", new Object[] { modifierItemStackQuest.func_82833_r(), itemStack.func_82833_r() })); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\palamod\ModifierApplyQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */