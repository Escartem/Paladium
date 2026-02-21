package fr.paladium.palapass.common.quest.shop;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ShopBuyQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.SHOP_BUY;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      if (itemStack.func_77969_a(itemStackQuest) || (itemStack.func_77984_f() && itemStack.func_77973_b().equals(itemStackQuest.func_77973_b())))
        quest.questProgress(entityPlayer, quantity, PalapassTranslateEnum.BUY_ITEM_SHOP_PROGRESS.textOrDefault(entityPlayer, "Acheter " + itemStack.func_82833_r() + " au shop §c{VALUE}/{QUANTITY}", new Object[] { itemStack.func_82833_r() })); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\shop\ShopBuyQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */