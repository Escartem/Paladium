package fr.paladium.palarpg.module.equipment.common.listener;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palarpg.module.equipment.common.event.PlayerArmorChangedEvent;
import fr.paladium.palarpg.module.equipment.common.event.PlayerHandChangedEvent;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemUtils;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentSetManager;
import fr.paladium.palarpg.module.profile.server.event.RPGExperienceEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class EquipmentPlayerEventListener {
  private final Map<UUID, ItemStack[]> lastArmor = (Map)new HashMap<>();
  
  private final Map<UUID, ItemStack> lastHeldItem = new HashMap<>();
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    EntityPlayer player = event.player;
    UUID playerId = player.func_110124_au();
    ItemStack[] currentArmor = (ItemStack[])player.field_71071_by.field_70460_b.clone();
    if (!this.lastArmor.containsKey(playerId)) {
      this.lastArmor.put(playerId, new ItemStack[4]);
      return;
    } 
    if (!this.lastHeldItem.containsKey(playerId)) {
      this.lastHeldItem.put(playerId, null);
      return;
    } 
    ItemStack[] previousArmor = this.lastArmor.get(playerId);
    for (int i = 0; i < 4; i++) {
      ItemStack oldArmor = previousArmor[i];
      ItemStack newArmor = currentArmor[i];
      if (!ItemUtils.isEquals(oldArmor, newArmor, true, true)) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerArmorChangedEvent(player, i, oldArmor, newArmor));
        this.lastArmor.put(playerId, currentArmor.clone());
      } 
    } 
    ItemStack previousHeldItem = this.lastHeldItem.get(playerId);
    ItemStack currentItem = (player.func_70694_bm() == null) ? null : player.func_70694_bm().func_77946_l();
    if (previousHeldItem == null && currentItem == null)
      return; 
    if (!ItemUtils.isEquals(previousHeldItem, currentItem, true, true)) {
      MinecraftForge.EVENT_BUS.post((Event)new PlayerHandChangedEvent(player, previousHeldItem, player.func_70694_bm()));
      this.lastHeldItem.put(playerId, currentItem);
    } 
  }
  
  @SubscribeEvent
  public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if (this.lastArmor.containsKey(event.player.func_110124_au()))
      this.lastArmor.remove(event.player.func_110124_au()); 
    if (this.lastHeldItem.containsKey(event.player.func_110124_au()))
      this.lastHeldItem.remove(event.player.func_110124_au()); 
    EquipmentSetManager.clear(event.player);
  }
  
  @SubscribeEvent
  public void onPlayerArmorChange(PlayerArmorChangedEvent event) {
    if (FMLCommonHandler.instance().getSide() == Side.SERVER && Server.is(new ServerType[] { ServerType.RPG })) {
      IRPGItem.get(event.getOldArmor()).ifPresent(rpgItem -> rpgItem.removeStats(event.getOldArmor(), (EntityLivingBase)event.getPlayer(), RPGStatApplyType.WEAR));
      IRPGItem.get(event.getNewArmor()).ifPresent(rpgItem -> rpgItem.applyStats(event.getNewArmor(), (EntityLivingBase)event.getPlayer(), RPGStatApplyType.WEAR));
    } 
    try {
      EquipmentSetManager.checkPlayerSet(event.getPlayer());
    } catch (Exception exception) {}
  }
  
  @SubscribeEvent
  public void onPlayerHandChange(PlayerHandChangedEvent event) {
    if (FMLCommonHandler.instance().getSide() == Side.SERVER && Server.is(new ServerType[] { ServerType.RPG })) {
      IRPGItem.get(event.getOldItemStack()).ifPresent(rpgItem -> rpgItem.removeStats(event.getOldItemStack(), (EntityLivingBase)event.getPlayer(), RPGStatApplyType.HELD));
      IRPGItem.get(event.getNewItemStack()).ifPresent(rpgItem -> rpgItem.applyStats(event.getNewItemStack(), (EntityLivingBase)event.getPlayer(), RPGStatApplyType.HELD));
    } 
    try {
      EquipmentSetManager.checkPlayerSet(event.getPlayer());
    } catch (Exception exception) {}
  }
  
  @SubscribeEvent
  @SideOnly(Side.SERVER)
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    for (ItemStack armorStack : event.player.field_71071_by.field_70460_b)
      IRPGItem.get(armorStack).ifPresent(rpgItem -> rpgItem.applyStats(armorStack, (EntityLivingBase)event.player, RPGStatApplyType.WEAR)); 
    ItemStack heldItem = event.player.func_70694_bm();
    IRPGItem.get(heldItem).ifPresent(rpgItem -> rpgItem.applyStats(heldItem, (EntityLivingBase)event.player, RPGStatApplyType.HELD));
    try {
      EquipmentSetManager.checkPlayerSet(event.player);
    } catch (Exception exception) {}
  }
  
  @SubscribeEvent
  @SideOnly(Side.SERVER)
  public void onPlayerLevelUp(RPGExperienceEvent.LevelUpEvent event) {
    for (ItemStack armorStack : (event.getPlayer()).field_71071_by.field_70460_b)
      IRPGItem.get(armorStack).ifPresent(rpgItem -> rpgItem.applyStats(armorStack, (EntityLivingBase)event.getPlayer(), RPGStatApplyType.WEAR)); 
    ItemStack heldItem = event.getPlayer().func_70694_bm();
    IRPGItem.get(heldItem).ifPresent(rpgItem -> rpgItem.applyStats(heldItem, (EntityLivingBase)event.getPlayer(), RPGStatApplyType.HELD));
    try {
      EquipmentSetManager.checkPlayerSet(event.getPlayer());
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\listener\EquipmentPlayerEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */