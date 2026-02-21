package fr.paladium.palamod.modules.paladium.network.data;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PaladiumPlayerHandler {
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      PaladiumPlayer.get((Entity)e.original).saveNBTData(compound);
      PaladiumPlayer.get((Entity)e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent e) {
    if (!e.world.field_72995_K && e.entity instanceof net.minecraft.entity.player.EntityPlayer) {
      PaladiumPlayer prop = PaladiumPlayer.get(e.entity);
      try {
        prop.setShowHoodHelmet(BukkitUtils.hasPermission(e.entity.func_110124_au(), "palamod.hoodhelmet"));
        prop.setTrixiumRank(BukkitUtils.hasPermission(e.entity.func_110124_au(), "paladium.skin.trixium"));
      } catch (Exception e1) {
        prop.setShowHoodHelmet(false);
        prop.setTrixiumRank(false);
      } 
      if (!prop.isTrixiumRank() && prop.getPaladiumArmorType() > 10)
        prop.setPaladiumArmorType(10); 
      prop.dataChanged(e.entity);
    } 
  }
  
  @SubscribeEvent
  public void entityConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof net.minecraft.entity.player.EntityPlayer && 
      e.entity.getExtendedProperties("palamod_COSMETIQUES") == null)
      e.entity.registerExtendedProperties("palamod_COSMETIQUES", new PaladiumPlayer()); 
  }
  
  @SubscribeEvent
  public void playerStartedTracking(PlayerEvent.StartTracking e) {
    PaladiumPlayer data = PaladiumPlayer.get(e.target);
    if (data != null)
      data.playerStartedTracking(e.entityPlayer); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\data\PaladiumPlayerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */