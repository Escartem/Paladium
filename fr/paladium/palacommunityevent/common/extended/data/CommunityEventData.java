package fr.paladium.palacommunityevent.common.extended.data;

import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.notification.MinecraftNotification;
import fr.paladium.palacommunityevent.common.pojo.CommonCommunityStep;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.CommunityStep;
import fr.paladium.palacommunityevent.server.api.pojo.EventCommunityData;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import fr.paladium.palaforgeutils.lib.api.callback.IRetrofitCallback;
import fr.paladium.palaforgeutils.lib.api.callback.SilentRetrofitCallback;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommunityEventData {
  public String communityEventId;
  
  public int progress;
  
  public List<String> rewarded = new ArrayList<>();
  
  public CommunityEventData(String communityEventId) {
    this.communityEventId = communityEventId;
  }
  
  public void reset() {
    this.progress = 0;
    this.rewarded.clear();
  }
  
  public void claimCommonRewards(final EntityPlayer p, final String claimedId) {
    PalaCommunityEventManager manager = PalaCommunityEventManager.getInstance();
    final CommunityEvent communityEvent = manager.getCommunityEventById(this.communityEventId);
    if (communityEvent != null) {
      for (CommonCommunityStep commonSteps : communityEvent.commonSteps) {
        if (commonSteps.id.equalsIgnoreCase(claimedId)) {
          int limitedProgress = Math.min(this.progress, communityEvent.totalItemsPerPlayer);
          double currentPlayerPercent = limitedProgress / communityEvent.totalItemsPerPlayer * 100.0D;
          if (this.rewarded.contains(claimedId)) {
            Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§cVous avez déjà récuperé cette récompense.", "paladium"), (EntityPlayerMP)p);
            return;
          } 
          if (currentPlayerPercent >= commonSteps.requiredPlayerPercent) {
            manager.asyncGetEventDataById((EntityPlayerMP)p, communityEvent.id, (IRetrofitCallback)new SilentRetrofitCallback<EventCommunityData>() {
                  public void onSuccess(EventCommunityData value) {
                    int limitedProgress = Math.min(value.progress, communityEvent.totalItems);
                    double currentPercent = limitedProgress / communityEvent.totalItems * 100.0D;
                    if (currentPercent >= commonSteps.percentToReach) {
                      CommunityEventData.this.rewarded.add(claimedId);
                      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§aRécompense débloquée.", "paladium"), (EntityPlayerMP)p);
                      for (ItemStack reward : commonSteps.rewards)
                        InventoryUtils.giveOrDropitems(p, reward.func_77946_l()); 
                    } else {
                      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§eL'étape commune des §c§l" + commonSteps.percentToReach + "% §en'a pas encore été débloquée.", "paladium"), (EntityPlayerMP)p);
                    } 
                  }
                });
          } else {
            Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§eVous n'avez pas complété §c§l" + commonSteps.requiredPlayerPercent + "% §ede votre progression personnelle requise pour cette récompense.", "paladium"), (EntityPlayerMP)p);
          } 
          return;
        } 
      } 
      p.func_146105_b((IChatComponent)new ChatComponentText("§cCette récompense n'existe pas."));
    } 
  }
  
  public void giveItems(EntityPlayer p) {
    PalaCommunityEventManager manager = PalaCommunityEventManager.getInstance();
    CommunityEvent communityEvent = manager.getCommunityEventById(this.communityEventId);
    if (communityEvent != null)
      for (ItemStack targetedIs : communityEvent.targetedItems) {
        int count = InventoryUtils.getItemCount(p, targetedIs);
        if (InventoryUtils.haveRequiredItem(p, targetedIs, count))
          InventoryUtils.removeItems(p, targetedIs, count); 
        progress(count, p);
      }  
  }
  
  public void progress(int i, EntityPlayer p) {
    if (i <= 0)
      return; 
    PalaCommunityEventManager manager = PalaCommunityEventManager.getInstance();
    CommunityEvent communityEvent = manager.getCommunityEventById(this.communityEventId);
    if (communityEvent != null) {
      double oldPercent = Math.min(this.progress * 1.0D, communityEvent.totalItemsPerPlayer) / communityEvent.totalItemsPerPlayer * 100.0D;
      double newPercent = Math.min((this.progress + i) * 1.0D, communityEvent.totalItemsPerPlayer) / communityEvent.totalItemsPerPlayer * 100.0D;
      for (CommunityStep step : communityEvent.playerSteps) {
        if (oldPercent < step.percentToReach && newPercent >= step.percentToReach) {
          Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event-Communautaire", "§6§l" + communityEvent.name + ":§a" + step.percentToReach + "% Atteints", "paladium"), (EntityPlayerMP)p);
          p.func_146105_b((IChatComponent)new ChatComponentText("§6[Event Communautaire ] §a" + step.percentToReach + "% Atteints - Récompenses:"));
          for (ItemStack reward : step.rewards) {
            p.func_146105_b((IChatComponent)new ChatComponentText(" §e- " + reward.func_82833_r() + " x" + reward.field_77994_a));
            InventoryUtils.giveOrDropitems(p, reward.func_77946_l());
          } 
        } 
      } 
      this.progress += i;
      if (newPercent > 100.0D)
        newPercent = 100.0D; 
      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Event Communautaire - Progression", "§6§l" + communityEvent.name + ": §e" + this.progress + "/" + communityEvent.totalItemsPerPlayer, "paladium"), (EntityPlayerMP)p);
      PalaCommunityEventManager.getInstance().asyncProgressEvent((EntityPlayerMP)p, this.communityEventId, i, (IRetrofitCallback)new SilentRetrofitCallback());
    } 
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("communityEventId", this.communityEventId);
    compound.func_74768_a("progress", this.progress);
    compound.func_74768_a("nbRewarded", this.rewarded.size());
    int i = 0;
    for (String c : this.rewarded) {
      compound.func_74778_a("rewarded-" + i, c);
      i++;
    } 
    return compound;
  }
  
  public CommunityEventData readFromNBT(NBTTagCompound compound) {
    this.communityEventId = compound.func_74779_i("communityEventId");
    this.progress = compound.func_74762_e("progress");
    int nbRewarded = compound.func_74762_e("nbRewarded");
    for (int i = 0; i < nbRewarded; i++)
      this.rewarded.add(compound.func_74779_i("rewarded-" + i)); 
    return this;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.communityEventId == null) ? 0 : this.communityEventId.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    CommunityEventData other = (CommunityEventData)obj;
    if (this.communityEventId == null) {
      if (other.communityEventId != null)
        return false; 
    } else if (!this.communityEventId.equals(other.communityEventId)) {
      return false;
    } 
    return true;
  }
  
  public CommunityEventData() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\extended\data\CommunityEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */