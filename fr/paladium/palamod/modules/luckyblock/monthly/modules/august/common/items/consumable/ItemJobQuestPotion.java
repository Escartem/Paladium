package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.api.event.quest.PlayerFinishQuestEvent;
import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemJobQuestPotion extends ItemFood implements ITooltipInformations {
  public static final String DESCRIPTION = "Valide l’ensemble des quêtes métier journalières de l’utilisateur. Clic droit pour être utilisé";
  
  public static final String NAME = "job_quest_potion";
  
  public ItemJobQuestPotion() {
    super(1, 1.0F, false);
    func_77655_b("job_quest_potion");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:job_quest_potion");
    func_77625_d(1);
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (world.field_72995_K || LuckyBlockUtils.isInCombat(player, true))
      return item; 
    JobsPlayer jobsPlayer = JobsPlayer.get((Entity)player);
    if (jobsPlayer == null)
      return item; 
    finishQuests(player, jobsPlayer);
    MonthlyUtils.decrementCurrentItem(player, item);
    return item;
  }
  
  private void finishQuests(EntityPlayer player, JobsPlayer jobsPlayer) {
    QuestRegistry registry = QuestRegistry.getInstance();
    for (IQuestPlayerData data : jobsPlayer.getDailyQuests()) {
      Optional<AbstractQuest> result = registry.getQuestById(data.getQuestId());
      if (result.isPresent()) {
        AbstractQuest quest = result.get();
        if (data.getQuestId().equals(quest.getId()) && !data.isCompleted()) {
          data.setCompleted(true);
          data.setProgress(quest.getQuantity());
          (new Notification(Notification.NotificationType.SUCCESS, quest.getName() + " - " + TTT.format(player, "notification.jobs.quest", new Object[0]), "jobs")).send((EntityPlayerMP)player);
          jobsPlayer.addXp(quest.getJob(), ObjectiveType.QUEST, quest.getEarnedExperience(), player);
          S29PacketSoundEffect packet = new S29PacketSoundEffect("palajobs:palajobs.quest.complete", player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
          ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)packet);
          jobsPlayer.checkMultiplier();
          PlayerFinishQuestEvent event = new PlayerFinishQuestEvent(player, quest, data);
          MinecraftForge.EVENT_BUS.post((Event)event);
        } 
      } 
    } 
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Valide l’ensemble des quêtes métier journalières de l’utilisateur. Clic droit pour être utilisé");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\consumable\ItemJobQuestPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */