package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityKillQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.ENTITY_KILL;
  
  public static void trigger(EntityPlayer entityPlayer, Entity entity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      if (EntityList.field_75625_b.containsKey(quest.getEntityType()) && EntityList.field_75625_b.get(quest.getEntityType()).equals(entity.getClass())) {
        String killEntityProgressMessage = PalapassTranslateEnum.KILL_ENTITY_PROGRESS.textOrDefault(entityPlayer, "Tuer des " + quest
            
            .getEntityType() + " §c{VALUE}/{QUANTITY}", new Object[] { quest
              .getEntityType() });
        quest.questProgress(entityPlayer, 1, killEntityProgressMessage);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onEntityKillEvent(LivingDeathEvent e) {
    if (e.entity.field_70170_p.field_72995_K || e.source == null || !(e.source.func_76346_g() instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.source.func_76346_g();
    trigger((EntityPlayer)player, e.entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\EntityKillQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */