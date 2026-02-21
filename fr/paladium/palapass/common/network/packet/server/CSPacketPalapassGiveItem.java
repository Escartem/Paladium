package fr.paladium.palapass.common.network.packet.server;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.quest.QuestTier;
import fr.paladium.palapass.common.quest.misc.ItemGiveQuest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class CSPacketPalapassGiveItem extends ForgePacket {
  @PacketData
  private String questUUID;
  
  public CSPacketPalapassGiveItem() {}
  
  public CSPacketPalapassGiveItem(String questUUID) {
    this.questUUID = questUUID;
  }
  
  public void processServer(EntityPlayerMP player) {
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    List<Quest> quests = playerData.getNonCompletedQuestsOfType(EnumQuestsType.ITEM_GIVE);
    for (Quest quest : quests) {
      if (!quest.getQuestUUID().equals(this.questUUID))
        continue; 
      Quest mappedQuest = PalapassManager.getInstance().getQuestFromUUID(quest.getQuestUUID());
      QuestProgressData progress = playerData.getQuestProgress(mappedQuest);
      ItemStack stack = ConfigItemUtils.getItemStackFromString(mappedQuest.getItemStack());
      int count = InventoryUtils.getItemCount((EntityPlayer)player, stack);
      if (count <= 0) {
        PalapassTranslateEnum.DO_NOT_OWN_ITEM.notificationOrDefault("Vous ne possédez pas de §c" + stack.func_82833_r() + ".", player, new Object[] { stack.func_82833_r() });
        return;
      } 
      if (mappedQuest.getQuestDuration() == EnumQuestsDuration.DAILY) {
        int itemsToValidate = Math.min(mappedQuest.getQuantity() - progress.getProgress(), count);
        if (itemsToValidate <= 0)
          return; 
        count = Math.min(count, itemsToValidate);
      } else if (mappedQuest.getQuestDuration() == EnumQuestsDuration.SEASON) {
        int quantity = ((QuestTier)mappedQuest.getTiers().get(2)).getAmount();
        int itemsToValidate = Math.min(quantity - progress.getProgress(), count);
        if (itemsToValidate <= 0)
          return; 
        count = Math.min(count, itemsToValidate);
      } 
      int countSave = count;
      for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
        ItemStack it = player.field_71071_by.field_70462_a[i];
        if (it != null && count > 0 && 
          it.func_77969_a(stack) && ItemStack.func_77970_a(it, stack) && it.func_77960_j() == stack.func_77960_j()) {
          int v = Math.min(Math.min(64, count), it.field_77994_a);
          it.field_77994_a -= v;
          if (it.field_77994_a <= 0) {
            player.field_71071_by.func_70299_a(i, null);
            player.field_71071_by.field_70459_e = true;
          } else {
            player.field_71071_by.func_70299_a(i, it.func_77946_l());
            player.field_71071_by.field_70459_e = true;
          } 
          count -= v;
        } 
      } 
      ItemGiveQuest.trigger((EntityPlayer)player, stack, countSave);
    } 
    reply(Boolean.valueOf(true));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\packet\server\CSPacketPalapassGiveItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */