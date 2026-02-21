package fr.paladium.palacommunityevent.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.Apollon;
import fr.paladium.lib.apollon.notification.ANotification;
import fr.paladium.lib.apollon.notification.MinecraftNotification;
import fr.paladium.palacommunityevent.common.extended.PalaCommunityEventEEP;
import fr.paladium.palacommunityevent.common.manager.AdventCalendarManager;
import fr.paladium.palacommunityevent.common.pojo.advent.AdventCalendarType;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class BBPacketAdventCalendar extends ForgePacket {
  @PacketData
  private int year;
  
  @PacketData
  private int month;
  
  @PacketData
  private int day;
  
  public BBPacketAdventCalendar() {}
  
  public BBPacketAdventCalendar(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (!AdventCalendarManager.isValid(this.year))
      return; 
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    int diff = Math.abs(this.day - now.getDayOfMonth());
    if (diff > 1) {
      Apollon.instance().getNotificationManager().sendNotification((ANotification)new MinecraftNotification("Santa Claus", "L'heure de votre ordinateur n'est pas à jour", "paladium"), player);
      return;
    } 
    if (this.month != 12 || this.day > 24)
      return; 
    PalaCommunityEventEEP eep = PalaCommunityEventEEP.get((Entity)player);
    if (eep == null)
      return; 
    if (eep.getAdventCalendarType(this.year, this.day) == AdventCalendarType.PICKED_UP)
      return; 
    ItemStack[] items = AdventCalendarManager.getItems(this.year, this.day);
    if (items == null || items.length == 0)
      return; 
    int availableSlotCount = 0;
    for (ItemStack item : player.field_71071_by.field_70462_a) {
      if (item == null)
        availableSlotCount++; 
    } 
    if (availableSlotCount < items.length) {
      Apollon.instance().getNotificationManager().addNotification((ANotification)new MinecraftNotification("Santa Claus", "Vous n'avez pas assez de place dans votre inventaire", "paladium"));
      reply(Result.NOT_ENOUGH_SPACE);
      return;
    } 
    for (ItemStack is : items)
      InventoryUtils.giveOrDropitems((EntityPlayer)player, is.func_77946_l()); 
    eep.getAdventCalendarData(this.year)[this.day - 1] = AdventCalendarType.PICKED_UP;
    eep.sync();
    reply(Result.SUCCESS);
  }
  
  public enum Result {
    NOT_ENOUGH_SPACE, SUCCESS;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\network\BBPacketAdventCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */