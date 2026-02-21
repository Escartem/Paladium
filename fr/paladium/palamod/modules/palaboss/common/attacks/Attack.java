package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public interface Attack {
  String getAttackName();
  
  List<AttackParamEntry> getAttackParams();
  
  JsonObject getJsonObject();
  
  void execute(World paramWorld, EntityBossBase paramEntityBossBase, EntityLivingBase paramEntityLivingBase);
  
  boolean isRunning();
  
  int getMinRange();
  
  int getMaxRange();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\Attack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */