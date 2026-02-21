package fr.paladium.palapass.common.quest.misc;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ItemGiveQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.ITEM_GIVE;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, int quantity) {
    if (itemStack == null)
      return; 
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      if (itemStack.func_77969_a(itemStackQuest) || (itemStack.func_77984_f() && itemStack.func_77973_b().equals(itemStackQuest.func_77973_b()))) {
        String supplyItemProgressMessage = PalapassTranslateEnum.SUPPLY_ITEM_PROGRESS.textOrDefault(entityPlayer, "Fournir " + itemStack
            
            .func_82833_r() + " §c{VALUE}/{QUANTITY}", new Object[] { itemStack
              .func_82833_r() });
        quest.questProgress(entityPlayer, quantity, supplyItemProgressMessage);
        if (quantity > 0) {
          EntityPlayerMP player = (EntityPlayerMP)entityPlayer;
          PalapassTranslateEnum.NB_ITEMS_VALIDATE.notificationOrDefault(TTT.format(quest.getText(), new Object[0]), "+ " + quantity + " items validés.", player, new Object[] { Integer.valueOf(quantity) });
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\ItemGiveQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */