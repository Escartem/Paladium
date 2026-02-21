package fr.paladium.palapass.common.constants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palapass.common.utils.TranslateUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public enum PalapassTranslateEnum {
  NOTIFICATION_TYPE("palapass.notification.type"),
  NOTIFICATION_TITLE("palapass.notification.type"),
  MESSAGE_PREFIX("palapass.message.prefix"),
  FREE("palapass.ui.common.free"),
  PREMIUM("palapass.ui.common.premium"),
  UI_REWARD_CONTAINER_NOT_ENOUGH_POINTS("palapass.ui.reward.container.not_enough_points"),
  UI_REWARD_CONTAINER_NOT_TO_GET_REWARD("palapass.ui.reward.container.to_get_reward"),
  UI_REWARD_CONTAINER_NOT_POINTS_MISSING("palapass.ui.reward.container.points_missing"),
  UI_REWARD_CONTAINER_HOVER_CLICK_GET_REWARD("palapass.ui.reward.container.hover_click_get_reward"),
  UI_PROMPT_BUY_PALAPASS("palapass.ui.prompt.buy_palapass"),
  UI_PROMPT_UNLOCK_REWARDS("palapass.ui.prompt.unlock_rewards"),
  UI_PROMPT_DEBITS_CURRENT_BALANCE("palapass.ui.prompt.debits_current_balance"),
  QUEST_COMPLETED("palapass.quest.completed"),
  QUEST_PROGRESS("palapass.quest.progress"),
  TIER_I("palapass.tier.i"),
  TIER_II("palapass.tier.ii"),
  TIER_III("palapass.tier.iii"),
  ERROR_LOADING_PROGRESS_TITLE("palapass.error.loading_progress_title"),
  ERROR_LOADING_PROGRESS_CONTENT("palapass.error.loading_progress_content"),
  QUEST_DURATION_DAILY("palapass.quest.duration.daily"),
  QUEST_DURATION_SEASONAL("palapass.quest.duration.seasonal"),
  PROGRESSION("palapass.progression"),
  VALIDATE("palapass.validate"),
  GAIN("palapass.gain"),
  DEPOSIT_ITEMS("palapass.deposit_items"),
  PALAPASS("palapass.general"),
  UNLOCK_LEVEL_PROMPT("palapass.unlock.level.prompt"),
  UNLOCK_REWARD_PROMPT("palapass.unlock.reward.prompt"),
  DEBITS_FROM_CURRENT_BALANCE("palapass.debits.from.current.balance"),
  CANCEL("palapass.cancel"),
  CONFIRM("palapass.confirm"),
  SEASON_INFO("palapass.season.info"),
  REMAINING_TIME("palapass.remaining.time"),
  POINTS_INFO("palapass.points.info"),
  VIEW_QUESTS("palapass.view.quests"),
  NEXT_LEVEL("palapass.next.level"),
  UNLOCK("palapass.unlock"),
  BUY_PREMIUM("palapass.buy.premium"),
  BUY("palapass.buy"),
  TEXT_SAVED_CLIPBOARD("palapass.text.saved.clipboard"),
  REWARD_FIRST_MONTH("palapass.reward.first_month"),
  REWARD_TOO_EARLY("palapass.reward.too_early"),
  REWARD_NOT_EXIST("palapass.reward.not_exist"),
  REWARD_ALREADY_RETRIEVED("palapass.reward.already_retrieved"),
  TIER_ALREADY_UNLOCKED("palapass.tier.already_unlocked"),
  CANNOT_UNLOCK_TIER("palapass.cannot.unlock.tier"),
  TIER_UNLOCKED("palapass.tier.unlocked"),
  NOT_ENOUGH_PBS("palapass.not.enough.pbs"),
  ALREADY_OWN_PREMIUM("palapass.already_own_premium"),
  UNLOCKED_PREMIUM_PASS("palapass.unlocked_premium_pass"),
  NOT_ENOUGH_POINTS_FOR_TIER("palapass.not_enough_points_for_tier"),
  TIER_ONLY_FOR_PREMIUM("palapass.tier_only_for_premium"),
  INVENTORY_FULL("palapass.inventory.full"),
  REWARD_RETRIEVED("palapass.reward.retrieved"),
  DO_NOT_OWN_ITEM("palapass.do_not_own_item"),
  TIER_COMPLETED("palapass.tier_completed"),
  QUEST_COMPLETED_STATS("palapass.quest_completed"),
  FACTION_ENTER_CLAIM_PROGRESS("palapass.faction_enter_claim.progress"),
  PVP_AREA_KILL_PROGRESS("palapass.pvp_area_kill.progress"),
  BUY_ITEMS_MARKET_PROGRESS("palapass.buy_items_market.progress"),
  SELL_ITEMS_MARKET_PROGRESS("palapass.sell_items_market.progress"),
  MINING_ITEM_PROGRESS("palapass.mining_item.progress"),
  VISIT_WORLD_PROGRESS("palapass.visit_world.progress"),
  KILL_ENTITY_PROGRESS("palapass.kill_entity.progress"),
  EQUIP_SET_PROGRESS("palapass.equip_set.progress"),
  FISHING_ITEM_PROGRESS("palapass.fishing_item.progress"),
  NB_ITEMS_VALIDATE("palapass.notification.nb.items.validate"),
  COOK_ITEM_PROGRESS("palapass.cook_item.progress"),
  CRAFT_ITEM_PROGRESS("palapass.craft_item.progress"),
  ENCHANT_ITEM_PROGRESS("palapass.enchant_item.progress"),
  SUPPLY_ITEM_PROGRESS("palapass.supply_item.progress"),
  USE_ITEM_PROGRESS("palapass.use_item.progress"),
  VISIT_SERVER_PROGRESS("palapass.visit_server.progress"),
  CRAFT_IN_GRINDER_PROGRESS("palapass.craft_in_grinder.progress"),
  MELT_IN_GRINDER_PROGRESS("palapass.melt_in_grinder.progress"),
  APPLY_MODIFIER_PROGRESS("palapass.apply_modifier.progress"),
  DUPLICATE_BOOKS_PROGRESS("palapass.duplicate_books.progress"),
  REPAIR_WITH_RING_PROGRESS("palapass.repair_with_ring.progress"),
  USE_SPELL_PROGRESS("palapass.use_spell.progress"),
  BUY_ITEM_SHOP_PROGRESS("palapass.buy_item_shop.progress"),
  SELL_ITEM_SHOP_PROGRESS("palapass.sell_item_shop.progress"),
  PALAMACHINE_CRAFT_PROGRESS("palapass.palamachine_craft.progress"),
  TRAVEL_DISTANCE_PROGRESS("palapass.travel_distance.progress");
  
  private final String id;
  
  public String getId() {
    return this.id;
  }
  
  PalapassTranslateEnum(String id) {
    this.id = id;
  }
  
  public String text() {
    return TTT.format(this.id, new Object[0]);
  }
  
  public String text(Object... args) {
    return TTT.format(this.id, args);
  }
  
  public String textOrDefault(String defaultText) {
    String translattedText = TTT.format(this.id, new Object[0]);
    return translattedText;
  }
  
  public String textOrDefault(String defaultText, Object... args) {
    String translattedText = TTT.format(this.id, args);
    return translattedText;
  }
  
  public String textOrDefault(EntityPlayer player, String defaultText) {
    String translattedText = TTT.format(player, this.id, new Object[0]);
    return translattedText;
  }
  
  public String textOrDefault(EntityPlayer player, String defaultText, Object... args) {
    String translattedText = TTT.format(player, this.id, args);
    return translattedText;
  }
  
  public String text(EntityPlayer player, Object... args) {
    return TTT.format(player, this.id, args);
  }
  
  public String text(EntityPlayer player) {
    return TTT.format(player, this.id, new Object[0]);
  }
  
  public void message(ICommandSender sender, Object... args) {
    if (sender instanceof EntityPlayer) {
      TranslateUtils.sendPrefixedMessage(sender, new String[] { TTT.format((EntityPlayer)sender, this.id, args) });
      return;
    } 
    TranslateUtils.sendPrefixedMessage(sender, new String[] { TTT.format(this.id, args) });
  }
  
  public void message(ICommandSender sender) {
    if (sender instanceof EntityPlayer) {
      TranslateUtils.sendPrefixedMessage(sender, new String[] { TTT.format((EntityPlayer)sender, this.id, new Object[0]) });
      return;
    } 
    TranslateUtils.sendPrefixedMessage(sender, new String[] { TTT.format(this.id, new Object[0]) });
  }
  
  public void messageOrDefault(String defaultText, ICommandSender sender, Object... args) {
    String translattedText = TTT.format((EntityPlayer)sender, this.id, args);
    TranslateUtils.sendPrefixedMessage(sender, new String[] { translattedText });
  }
  
  public void messageOrDefault(String defaultText, ICommandSender sender) {
    String translattedText = TTT.format((EntityPlayer)sender, this.id, new Object[0]);
    TranslateUtils.sendPrefixedMessage(sender, new String[] { translattedText });
  }
  
  @SideOnly(Side.CLIENT)
  public void notification(Object... args) {
    (new Notification(Notification.NotificationType.INFO, TTT.format(this.id, args), TTT.format(NOTIFICATION_TYPE.getId(), new Object[0]))).send();
  }
  
  @SideOnly(Side.CLIENT)
  public void notification() {
    (new Notification(Notification.NotificationType.INFO, TTT.format(this.id, new Object[0]), TTT.format(NOTIFICATION_TYPE.getId(), new Object[0]))).send();
  }
  
  @SideOnly(Side.SERVER)
  public void notification(EntityPlayerMP player, Object... args) {
    (new Notification(Notification.NotificationType.INFO, TTT.format((EntityPlayer)player, this.id, args), TTT.format((EntityPlayer)player, NOTIFICATION_TYPE.getId(), new Object[0]))).send(player);
  }
  
  @SideOnly(Side.SERVER)
  public void notification(EntityPlayerMP player) {
    (new Notification(Notification.NotificationType.INFO, TTT.format((EntityPlayer)player, this.id, new Object[0]), TTT.format((EntityPlayer)player, NOTIFICATION_TYPE.getId(), new Object[0]))).send(player);
  }
  
  @SideOnly(Side.SERVER)
  public void notificationOrDefault(String defaultText, EntityPlayerMP player, Object... args) {
    String translattedText = TTT.format((EntityPlayer)player, this.id, args);
    if (translattedText.equalsIgnoreCase(this.id))
      translattedText = "[MS] " + defaultText; 
    (new Notification(Notification.NotificationType.INFO, TTT.format((EntityPlayer)player, translattedText, args), TTT.format((EntityPlayer)player, NOTIFICATION_TYPE.getId(), new Object[0]))).send(player);
  }
  
  @SideOnly(Side.SERVER)
  public void notificationOrDefault(String title, String defaultText, EntityPlayerMP player, Object... args) {
    String translattedText = TTT.format((EntityPlayer)player, this.id, args);
    if (translattedText.equalsIgnoreCase(this.id))
      translattedText = "[MS] " + defaultText; 
    (new Notification(Notification.NotificationType.INFO, TTT.format((EntityPlayer)player, translattedText, args), TTT.format((EntityPlayer)player, NOTIFICATION_TYPE.getId(), new Object[0]))).send(player);
  }
  
  @SideOnly(Side.SERVER)
  public void notificationOrDefault(String defaultText, EntityPlayerMP player) {
    String translattedText = TTT.format((EntityPlayer)player, this.id, new Object[0]);
    if (translattedText.equalsIgnoreCase(this.id))
      translattedText = "[MS] " + defaultText; 
    (new Notification(Notification.NotificationType.INFO, TTT.format((EntityPlayer)player, translattedText, new Object[0]), TTT.format((EntityPlayer)player, NOTIFICATION_TYPE.getId(), new Object[0]))).send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\constants\PalapassTranslateEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */