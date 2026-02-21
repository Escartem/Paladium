package fr.paladium.pet.common.constant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public enum PetTranslateEnum {
  NOTIFICATION_TYPE("pet.notification.type"),
  NOTIFICATION_TITLE("pet.notification.title"),
  NOTIFICATION_LEVEL_UP("pet.notification.level.up"),
  NOTIFICATION_ASSIGNMENT_FINISH("pet.notification.assignment.finish"),
  NOTIFICATION_SLOT_UNLOCKED("pet.notification.slot.unlocked"),
  MESSAGE_PREFIX("pet.message.prefix"),
  MESSAGE_NO_FAMILIAR("pet.message.no.familiar"),
  MESSAGE_EARN_HAPPINESS("pet.message.earn.happiness"),
  MESSAGE_NO_ACTIVE_SKILL_AT_SLOT("pet.message.no.active.skill.at.slot"),
  MESSAGE_SKILL_ON_COOLDOWN("pet.message.skill.on.cooldown"),
  MESSAGE_CANT_USE_SKILL("pet.message.cant.use.skill"),
  MESSAGE_SKILL_USED("pet.message.skill.used"),
  MESSAGE_NO_SLOT("pet.message.no.slot"),
  MESSAGE_NO_SKILL("pet.message.no.skill"),
  MESSAGE_CHANGE_SKILL_COOLDOWN("pet.message.change.skill.cooldown"),
  MESSAGE_CHANGE_SKILL("pet.message.change.skill"),
  MESSAGE_MAX_ACTIVE_SKILLS("pet.message.max.active.skills"),
  MESSAGE_SKILL_REQUIRED_LEVEL("pet.message.skill.required.level"),
  MESSAGE_REVEALING_SWORD("pet.message.revealing.sword"),
  MESSAGE_BLESSED_EXPLOSION("pet.message.blessed.explosion"),
  MESSAGE_BLESSED_EXPLOSION_SUCCESS("pet.message.blessed.explosion.success"),
  MESSAGE_BLESSED_EXPLOSION_FAILED("pet.message.blessed.explosion.failed"),
  MESSAGE_LUCKY_MINER_ACTIVATED("pet.message.lucky.miner.activated"),
  MESSAGE_LUCKY_MINER_FAILED("pet.message.lucky.miner.failed"),
  MESSAGE_LUCKY_MINER_SUCCESS("pet.message.lucky.miner.success"),
  MESSAGE_CAGE_NOT_FILLED("pet.message.cage.not.filled"),
  MESSAGE_CAGE_FILLED("pet.message.cage.filled"),
  MESSAGE_WAITING_FOR_FAMILIAR("pet.message.waiting.for.familiar"),
  MESSAGE_NOT_OWNER("pet.message.not.owner"),
  MESSAGE_ALREADY_HAS_FAMILIAR("pet.message.already.has.familiar"),
  MESSAGE_PET_NAME_TOO_LONG("pet.message.pet.name.too.long"),
  MESSAGE_PET_NAME_CHANGED("pet.message.pet.name.changed"),
  MESSAGE_FAST_CHANGE_ENABLED("pet.message.fast.change.enabled"),
  MESSAGE_FAST_CHANGE_SUCCESS("pet.message.fast.change.success"),
  MESSAGE_TARGET_NO_PET("pet.message.target.no.pet"),
  MESSAGE_HAPPINESS_SET("pet.message.happiness.set"),
  MESSAGE_HAPPINESS_SET_TARGET("pet.message.happiness.set.target"),
  MESSAGE_ENCHANTED_ENABLED("pet.message.enchanted.enabled"),
  MESSAGE_ENCHANTED_SUCCESS("pet.message.enchanted.success"),
  MESSAGE_PET_REPAIR("pet.message.pet.repair"),
  MESSAGE_PET_REPAIR_SUCCESS("pet.message.pet.repair.success"),
  MESSAGE_PET_REPAIR_FAILED("pet.message.pet.repair.failed"),
  MESSAGE_UNBREAKABLE_PICKAXE("pet.message.unbreakable.pickaxe"),
  MESSAGE_VETERINARY_USED("pet.message.veterinary.used"),
  MESSAGE_VETERINARY_SUCCESS("pet.message.veterinary.success"),
  MESSAGE_SKILL_ALREADY_SELECTED("pet.message.skill.already.selected"),
  MESSAGE_SKILL_NOT_SELECTED("pet.message.skill.not.selected"),
  MESSAGE_SKILL_ROLL_SET_SUCCESS("pet.message.skill.roll.set.success"),
  MESSAGE_SKILL_ROLL_REMOVED("pet.message.skill.roll.removed"),
  MESSAGE_PET_SKIN_CHANGED("pet.message.pet.skin.changed"),
  MESSAGE_TRAPPED_SUCCESSFULLY("pet.message.trapped.successfully"),
  MESSAGE_FAMILIAR_ESCAPED("pet.message.familiar.escaped"),
  MESSAGE_CURRENT_SCORE("pet.message.current.score"),
  MESSAGE_WAIT("pet.message.wait"),
  MESSAGE_CAN_REPLAY("pet.message.can.replay"),
  MESSAGE_STEP_SUCCESSFULLY("pet.message.step.successfully"),
  MESSAGE_WIN("pet.message.win"),
  MESSAGE_DERANK("pet.message.derank"),
  MESSAGE_NO_FAMILIAR_IN_CAGE("pet.message.no.familiar.in.cage"),
  MESSAGE_BYPASS_REMOVE("pet.message.bypass.remove"),
  MESSAGE_BYPASS_ADD("pet.message.bypass.add"),
  MESSAGE_LEVEL_SET("pet.message.level.set"),
  MESSAGE_LEVEL_SET_TARGET("pet.message.level.set.target"),
  MESSAGE_EXPERIENCE_SET("pet.message.experience.set"),
  MESSAGE_EXPERIENCE_SET_TARGET("pet.message.experience.set.target"),
  MESSAGE_EXPERIENCE_ADD("pet.message.experience.add"),
  MESSAGE_EXPERIENCE_ADD_TARGET("pet.message.experience.add.target"),
  MESSAGE_MISSION_NOT_FOUND("pet.message.mission.not.found"),
  MESSAGE_SKILL_NO_FOUND("pet.message.skill.no.found"),
  MESSAGE_LIGHTNING_POTION_WIN("pet.message.lightning.potion.win"),
  GUI_CAPTURE_TITLE("pet.gui.capture.title"),
  GUI_HOME_TITLE("pet.gui.home.title"),
  GUI_SKILL_ROLL_TITLE("pet.gui.skill.roll.title"),
  GUI_SKILL_ROLL_SUBTITLE("pet.gui.skill.roll.subtitle"),
  GUI_SKILL_ROLL_DESCRIPTION("pet.gui.skill.roll.description"),
  GUI_CHANGE_SKIN_TITLE("pet.gui.change.skin.title"),
  GUI_CHANGE_SKIN_SUBTITLE("pet.gui.change.skin.subtitle"),
  GUI_CHANGE_SKIN_VALIDATE_TEXT("pet.gui.change.skin.validate.text"),
  GUI_ASSIGN_SKILL_TITLE("pet.gui.assign.skill.title"),
  GUI_ASSIGN_SKILL_SUBTITLE("pet.gui.assign.skill.subtitle"),
  GUI_NODE_ASSIGNMENT_TITLE("pet.gui.node.assignment.title"),
  GUI_NODE_ASSIGNMENT_REMAINING("pet.gui.node.assignment.remaining"),
  GUI_NODE_SKILL_TITLE("pet.gui.node.skill.title"),
  GUI_NODE_SKILL_BUTTON_TITLE("pet.gui.node.skill.button.title"),
  GUI_NODE_SKILL_ACTIVE_SKILL("pet.gui.node.skill.active.skill"),
  GUI_NODE_SKILL_PASSIVE_SKILL("pet.gui.node.skill.passive.skill"),
  GUI_NODE_SKILL_ACTIVE("pet.gui.node.skill.active"),
  GUI_NODE_SKILL_PASSIVE("pet.gui.node.skill.passive"),
  GUI_NODE_STATS_HAPPINESS("pet.gui.node.stats.happiness"),
  GUI_NODE_STATS_EXPERIENCE("pet.gui.node.stats.experience"),
  GUI_NODE_FEED_TITLE("pet.gui.node.feed.title"),
  GUI_NODE_FEED_SUBTITLE("pet.gui.node.feed.subtitle"),
  GUI_SLOT_NOT_CONFIGURED("pet.gui.slot.not.configured"),
  GUI_SLOT_CLICK_TO_SELECT("pet.gui.slot.click.to.select"),
  GUI_SLOT_LOCKED("pet.gui.slot.locked"),
  GUI_SLOT_LOCKED_LEVEL("pet.gui.slot.locked.level"),
  GUI_CAPTURE_CLICK_SPACE("pet.gui.capture.click.space"),
  TEXT_CAPTURE_STEP_1("pet.text.capture.step.1"),
  TEXT_CAPTURE_STEP_2("pet.text.capture.step.2"),
  TEXT_CAPTURE_STEP_3("pet.text.capture.step.3");
  
  private final String id;
  
  public String getId() {
    return this.id;
  }
  
  PetTranslateEnum(String id) {
    this.id = id;
  }
  
  public String text() {
    return TTT.format(this.id, new Object[0]);
  }
  
  public String text(Object... args) {
    return TTT.format(this.id, args);
  }
  
  public String text(EntityPlayer player, Object... args) {
    return TTT.format(player, this.id, args);
  }
  
  public String text(EntityPlayer player) {
    return TTT.format(player, this.id, new Object[0]);
  }
  
  public void message(ICommandSender sender, Object... args) {
    if (sender instanceof EntityPlayer) {
      PetUtils.sendPrefixedMessage(sender, new String[] { TTT.format((EntityPlayer)sender, this.id, args) });
      return;
    } 
    PetUtils.sendPrefixedMessage(sender, new String[] { TTT.format(this.id, args) });
  }
  
  public void message(ICommandSender sender) {
    if (sender instanceof EntityPlayer) {
      PetUtils.sendPrefixedMessage(sender, new String[] { TTT.format((EntityPlayer)sender, this.id, new Object[0]) });
      return;
    } 
    PetUtils.sendPrefixedMessage(sender, new String[] { TTT.format(this.id, new Object[0]) });
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
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\constant\PetTranslateEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */