package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.utils.ConfigItemUtils;
import java.util.List;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ItemUseQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.ITEM_USE;
  
  public static void trigger(EntityPlayer entityPlayer, ItemStack itemStack, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      ItemStack itemStackQuest = ConfigItemUtils.getItemStackFromString(quest.getItemStack());
      if (itemStack.func_77969_a(itemStackQuest) || (itemStack.func_77984_f() && itemStack.func_77973_b().equals(itemStackQuest.func_77973_b()))) {
        String useItemProgressMessage = PalapassTranslateEnum.USE_ITEM_PROGRESS.textOrDefault(entityPlayer, "Utiliser " + itemStack
            
            .func_82833_r() + " §c{VALUE}/{QUANTITY}", new Object[] { itemStack
              .func_82833_r() });
        quest.questProgress(entityPlayer, quantity, useItemProgressMessage);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onMilkFillEvent(EntityInteractEvent e) {
    EntityPlayer player = e.entityPlayer;
    if (player == null || player.field_70170_p.field_72995_K || player.func_70694_bm() == null || !(e.target instanceof net.minecraft.entity.passive.EntityCow))
      return; 
    if (player.func_70694_bm().func_77973_b() != Items.field_151133_ar)
      return; 
    trigger(e.entityPlayer, e.entityPlayer.func_70694_bm(), 1);
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onBonemealEvent(BonemealEvent e) {
    EntityPlayer player = e.entityPlayer;
    World world = e.world;
    if (player == null || world.field_72995_K || player.func_70694_bm() == null || !(e.block instanceof IGrowable))
      return; 
    if (e.isCanceled() || (e.hasResult() && Event.Result.DENY.equals(e.getResult())))
      return; 
    IGrowable growable = (IGrowable)e.block;
    if (!growable.func_149851_a(world, e.x, e.y, e.z, world.field_72995_K))
      return; 
    trigger(player, player.func_70694_bm(), 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\ItemUseQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */