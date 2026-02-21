package fr.paladium.palarpg.module.dungeon.server.utils;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class EntityTrackerUtils {
  public static void update(EntityPlayer entity) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            try {
              EntityTracker tracker = ((WorldServer)entity.field_70170_p).func_73039_n();
              ConcurrentHashMap<Integer, Object> trackedEntityIDs = (ConcurrentHashMap<Integer, Object>)ObfuscationReflectionHelper.getPrivateValue(EntityTracker.class, tracker, new String[] { "trackedEntityIDs", "field_72794_c" });
              EntityTrackerEntry entry = (EntityTrackerEntry)trackedEntityIDs.get(Integer.valueOf(entity.func_145782_y()));
              for (Object entityPlayerObj : entity.field_70170_p.field_73010_i) {
                if (!(entityPlayerObj instanceof EntityPlayerMP))
                  continue; 
                EntityPlayerMP other = (EntityPlayerMP)entityPlayerObj;
                if (other.func_145782_y() == entity.func_145782_y())
                  continue; 
                if (entry != null && !entry.field_73134_o.contains(other))
                  entry.func_73117_b(other); 
              } 
            } catch (Exception|NoClassDefFoundError exception) {}
          } });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\serve\\utils\EntityTrackerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */