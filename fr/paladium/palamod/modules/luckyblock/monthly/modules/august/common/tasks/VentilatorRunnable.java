package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.VentilatorStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityVentilator;
import java.util.TimerTask;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class VentilatorRunnable extends TimerTask {
  private VentilatorStructure structure;
  
  public VentilatorRunnable(VentilatorStructure structure) {
    this.structure = structure;
  }
  
  public void run() {
    EntityPlayer player = this.structure.getPlayer();
    if (player == null || this.structure.isExpired()) {
      stop();
      return;
    } 
    TileEntityVentilator ventilator = this.structure.getVentilator();
    if (ventilator == null) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.VENTILATOR));
      stop();
    } 
  }
  
  private void stop() {
    if (this.structure != null)
      this.structure.onExpire(); 
    cancel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tasks\VentilatorRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */