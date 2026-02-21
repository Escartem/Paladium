package fr.paladium.palapass.common.pojo.quest;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.event.PlayerFinishPassQuestEvent;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassNotification;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;

public class Quest {
  private String questUUID;
  
  private String text;
  
  private String description;
  
  private String icon;
  
  private EnumQuestsType questType;
  
  private EnumQuestsDuration questDuration;
  
  private List<QuestTier> tiers;
  
  private int quantity;
  
  private int earnedPoints;
  
  private String itemStack;
  
  private String world;
  
  private String worldName;
  
  private String entityType;
  
  private String equipmentSetName;
  
  private String slotHelmetItem;
  
  private String slotChestplateItem;
  
  private String slotLeggingsItem;
  
  private String slotBootsItem;
  
  private String regexServer;
  
  private String serverName;
  
  private String modifierItemType;
  
  private String formattedSpellName;
  
  private String spellName;
  
  public void setQuestUUID(String questUUID) {
    this.questUUID = questUUID;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  public void setQuestType(EnumQuestsType questType) {
    this.questType = questType;
  }
  
  public void setQuestDuration(EnumQuestsDuration questDuration) {
    this.questDuration = questDuration;
  }
  
  public void setTiers(List<QuestTier> tiers) {
    this.tiers = tiers;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public void setEarnedPoints(int earnedPoints) {
    this.earnedPoints = earnedPoints;
  }
  
  public void setItemStack(String itemStack) {
    this.itemStack = itemStack;
  }
  
  public void setWorld(String world) {
    this.world = world;
  }
  
  public void setWorldName(String worldName) {
    this.worldName = worldName;
  }
  
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }
  
  public void setEquipmentSetName(String equipmentSetName) {
    this.equipmentSetName = equipmentSetName;
  }
  
  public void setSlotHelmetItem(String slotHelmetItem) {
    this.slotHelmetItem = slotHelmetItem;
  }
  
  public void setSlotChestplateItem(String slotChestplateItem) {
    this.slotChestplateItem = slotChestplateItem;
  }
  
  public void setSlotLeggingsItem(String slotLeggingsItem) {
    this.slotLeggingsItem = slotLeggingsItem;
  }
  
  public void setSlotBootsItem(String slotBootsItem) {
    this.slotBootsItem = slotBootsItem;
  }
  
  public void setRegexServer(String regexServer) {
    this.regexServer = regexServer;
  }
  
  public void setServerName(String serverName) {
    this.serverName = serverName;
  }
  
  public void setModifierItemType(String modifierItemType) {
    this.modifierItemType = modifierItemType;
  }
  
  public void setFormattedSpellName(String formattedSpellName) {
    this.formattedSpellName = formattedSpellName;
  }
  
  public void setSpellName(String spellName) {
    this.spellName = spellName;
  }
  
  public Quest() {}
  
  public String getQuestUUID() {
    return this.questUUID;
  }
  
  public String getText() {
    return this.text;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public EnumQuestsType getQuestType() {
    return this.questType;
  }
  
  public EnumQuestsDuration getQuestDuration() {
    return this.questDuration;
  }
  
  public List<QuestTier> getTiers() {
    return this.tiers;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public int getEarnedPoints() {
    return this.earnedPoints;
  }
  
  public String getItemStack() {
    return this.itemStack;
  }
  
  public String getWorld() {
    return this.world;
  }
  
  public String getWorldName() {
    return this.worldName;
  }
  
  public String getEntityType() {
    return this.entityType;
  }
  
  public String getEquipmentSetName() {
    return this.equipmentSetName;
  }
  
  public String getSlotHelmetItem() {
    return this.slotHelmetItem;
  }
  
  public String getSlotChestplateItem() {
    return this.slotChestplateItem;
  }
  
  public String getSlotLeggingsItem() {
    return this.slotLeggingsItem;
  }
  
  public String getSlotBootsItem() {
    return this.slotBootsItem;
  }
  
  public String getRegexServer() {
    return this.regexServer;
  }
  
  public String getServerName() {
    return this.serverName;
  }
  
  public String getModifierItemType() {
    return this.modifierItemType;
  }
  
  public String getFormattedSpellName() {
    return this.formattedSpellName;
  }
  
  public String getSpellName() {
    return this.spellName;
  }
  
  public Quest(String uuid) {
    this.questUUID = uuid;
  }
  
  private double getMultiplierByPermission(EntityPlayer player) {
    PermissibleEntity entity = PermissibleEntity.from((Entity)player);
    int value = ((Integer)PermissionManager.inst().getPermission(entity, "palapass.points.value.", Integer.class).orElse(Integer.valueOf(0))).intValue();
    int bonus = ((Integer)PermissionManager.inst().getPermission(entity, "palapass.points.bonus.", Integer.class).orElse(Integer.valueOf(0))).intValue();
    int sum = value + bonus;
    if (sum == 0)
      return 0.0D; 
    return sum / 100.0D;
  }
  
  public void questProgress(EntityPlayer player, int amount, String messageSuccess) {
    questProgress(player, amount, messageSuccess, true);
  }
  
  public void questProgress(EntityPlayer player, int amount, String messageSuccess, boolean notification) {
    if (!BukkitUtils.hasPermission(player.func_110124_au(), "palapass.player"))
      return; 
    PalapassPlayer playerData = PalapassPlayer.get(player);
    QuestProgressData questProgress = playerData.getQuestProgress(this);
    if (questProgress == null)
      return; 
    questProgress.setProgress(questProgress.getProgress() + amount);
    if (this.questDuration == EnumQuestsDuration.SEASON) {
      List<Integer> completedTiers = new ArrayList<>();
      for (int tierIndex = 0; tierIndex < this.tiers.size(); tierIndex++) {
        QuestTier tier = this.tiers.get(tierIndex);
        if (questProgress.getProgress() >= tier.getAmount() && questProgress.getProgress() - amount < tier.getAmount())
          completedTiers.add(Integer.valueOf(tierIndex)); 
      } 
      for (Integer integer : completedTiers) {
        QuestTier completedTier = this.tiers.get(integer.intValue());
        int xp = completedTier.getPoints();
        double multiplier = 0.0D;
        if (playerData.isPremium())
          multiplier = 0.1D; 
        multiplier += getMultiplierByPermission(player);
        if (multiplier > 0.0D)
          xp += (int)(xp * multiplier); 
        PalapassTranslateEnum.TIER_COMPLETED.notificationOrDefault("Vous avez complété le palier " + (integer
            .intValue() + 1) + " (+" + xp + ")", (EntityPlayerMP)player, new Object[] { String.valueOf(integer.intValue() + 1), 
              String.valueOf(xp) });
        playerData.addPoints(xp);
      } 
      if (!completedTiers.isEmpty() && ((Integer)completedTiers.get(completedTiers.size() - 1)).intValue() == this.tiers.size() - 1) {
        questProgress.setCompleted(true);
        MinecraftForge.EVENT_BUS.post((Event)new PlayerFinishPassQuestEvent(player, this));
      } else {
        QuestTier questTier = getTiers().get(0);
        for (QuestTier t : getTiers()) {
          if (questProgress.getProgress() < t.getAmount()) {
            questTier = t;
            break;
          } 
        } 
        if (questTier.getAmount() == ((QuestTier)getTiers().get(0)).getAmount() && 
          questProgress.getProgress() >= ((QuestTier)getTiers().get(getTiers().size() - 1)).getAmount())
          questTier = getTiers().get(getTiers().size() - 1); 
        if (notification) {
          String bindedMsg = messageSuccess + " §c" + PalapassTranslateEnum.QUEST_PROGRESS.text(player, new Object[] { Integer.valueOf(questProgress.getProgress()), Integer.valueOf(questTier.getAmount()) });
          (new SCPacketPalapassNotification(Notification.NotificationType.INFO, TTT.format(player, this.text, new Object[0]) + " - " + bindedMsg.replace("{VALUE}", String.valueOf(questProgress.getProgress())).replace("{QUANTITY}", String.valueOf(questTier.getAmount())))).send((EntityPlayerMP)player);
        } 
      } 
    } else if (questProgress.getProgress() >= this.quantity) {
      questProgress.setCompleted(true);
      int xp = this.earnedPoints;
      double multiplier = 0.0D;
      if (playerData.isPremium())
        multiplier = 0.1D; 
      multiplier += getMultiplierByPermission(player);
      if (multiplier > 0.0D)
        xp += (int)(xp * multiplier); 
      PalapassTranslateEnum.QUEST_COMPLETED_STATS.notificationOrDefault("Vous avez terminé la quête (+" + xp + ")", (EntityPlayerMP)player, new Object[] { String.valueOf(xp) });
      playerData.addPoints(xp);
      MinecraftForge.EVENT_BUS.post((Event)new PlayerFinishPassQuestEvent(player, this));
    } else if (notification) {
      String bindedMsg = messageSuccess + " §c" + PalapassTranslateEnum.QUEST_PROGRESS.text(player, new Object[] { Integer.valueOf(questProgress.getProgress()), Integer.valueOf(this.quantity) });
      (new SCPacketPalapassNotification(Notification.NotificationType.INFO, TTT.format(player, this.text, new Object[0]) + " - " + bindedMsg.replace("{VALUE}", String.valueOf(questProgress.getProgress())).replace("{QUANTITY}", String.valueOf(this.quantity)))).send((EntityPlayerMP)player);
    } 
    playerData.sync();
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof Quest))
      return false; 
    Quest quest = (Quest)o;
    if (quest == null || this.questUUID == null)
      return false; 
    return this.questUUID.equals(quest.questUUID);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.questUUID });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\pojo\quest\Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */