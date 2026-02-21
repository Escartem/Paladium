package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class EquipmentSetQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.EQUIPMENT;
  
  private static final long DELAY = TimeUnit.SECONDS.toMillis(5L);
  
  private long lastCheckMillis = 0L;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
    if (helmet == null || chestplate == null || leggings == null || boots == null)
      return; 
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack helmetItem = ConfigItemUtils.getItemStackFromString(quest.getSlotHelmetItem());
      ItemStack chestplateItem = ConfigItemUtils.getItemStackFromString(quest.getSlotChestplateItem());
      ItemStack leggingsItem = ConfigItemUtils.getItemStackFromString(quest.getSlotLeggingsItem());
      ItemStack bootsItem = ConfigItemUtils.getItemStackFromString(quest.getSlotBootsItem());
      if (helmet.func_77973_b() == helmetItem.func_77973_b() && chestplate
        .func_77973_b() == chestplateItem.func_77973_b() && leggings
        .func_77973_b() == leggingsItem.func_77973_b() && boots
        .func_77973_b() == bootsItem.func_77973_b()) {
        String equipSetProgressMessage = PalapassTranslateEnum.EQUIP_SET_PROGRESS.textOrDefault(entityPlayer, "Equiper un set d'équipement " + quest
            
            .getEquipmentSetName() + " §c{VALUE}/{QUANTITY}", new Object[] { quest
              .getEquipmentSetName() });
        quest.questProgress(entityPlayer, 1, equipSetProgressMessage);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    long now = System.currentTimeMillis();
    if (now - this.lastCheckMillis < DELAY)
      return; 
    this.lastCheckMillis = now;
    for (EntityPlayerMP playerMP : PlayerUtils.getOnlinePlayers())
      trigger((EntityPlayer)playerMP, playerMP.field_71071_by
          .func_70440_f(3), playerMP.field_71071_by
          .func_70440_f(2), playerMP.field_71071_by
          .func_70440_f(1), playerMP.field_71071_by
          .func_70440_f(0)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\EquipmentSetQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */