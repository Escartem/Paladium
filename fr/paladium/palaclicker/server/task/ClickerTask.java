package fr.paladium.palaclicker.server.task;

import fr.paladium.palaclicker.PalaClickerMod;
import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@Schedule(every = "1s")
public class ClickerTask extends ATask {
  public ClickerTask() {
    super("ClickerTask");
  }
  
  public void run() {
    for (Iterator iterator = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b.iterator(); iterator.hasNext(); ) {
      Object playerObj = iterator.next();
      if (!(playerObj instanceof EntityPlayerMP))
        continue; 
      EntityPlayerMP player = (EntityPlayerMP)playerObj;
      PlayerClickerData data = PlayerClickerData.get((Entity)player);
      if (data == null)
        continue; 
      List<ClickerBuilding> buildingList = PalaClickerMod.getServer().getBuildingConfig().getBuildingList();
      List<ClickerUpgrade> upgradeList = PalaClickerMod.getServer().getUpgradeConfig().getUpgradeList();
      double totalRps = 0.5D;
      for (String buildingId : data.getBuildings().keySet()) {
        Optional<ClickerBuilding> building = buildingList.stream().filter(b -> b.getId().equals(buildingId)).findAny();
        if (!building.isPresent())
          continue; 
        double rps = data.getRPS(building.get(), buildingList, upgradeList);
        data.addBuildingProduction(buildingId, rps);
        totalRps += rps;
      } 
      data.addPoints(totalRps);
      data.setRps(totalRps);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\task\ClickerTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */