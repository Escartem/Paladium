package fr.paladium.achievement.core.data;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExtendeAchievementPlayerDataHandler {
  @SubscribeEvent
  public void onClonePlayer(PlayerEvent.Clone e) {
    if (e.wasDeath) {
      NBTTagCompound compound = new NBTTagCompound();
      ExtendedAchievementPlayerData.get((Entity)e.original).saveNBTData(compound);
      ExtendedAchievementPlayerData.get((Entity)e.entityPlayer).loadNBTData(compound);
    } 
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof net.minecraft.entity.player.EntityPlayerMP) {
      ExtendedAchievementPlayerData data = ExtendedAchievementPlayerData.get(event.entity);
      data.dataChanged(event.entity);
    } 
  }
  
  @SubscribeEvent
  public void entityConstruct(EntityEvent.EntityConstructing e) {
    if (e.entity instanceof net.minecraft.entity.player.EntityPlayer && 
      e.entity.getExtendedProperties("achievement_ACVMT") == null)
      e.entity.registerExtendedProperties("achievement_ACVMT", new ExtendedAchievementPlayerData()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\data\ExtendeAchievementPlayerDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */