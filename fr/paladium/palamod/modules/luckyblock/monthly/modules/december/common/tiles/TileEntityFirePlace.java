package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles;

import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.CooldownData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palawither.common.utils.WitherUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class TileEntityFirePlace extends TileEntity {
  private static final Map<UUID, Integer> WITHER_MAP = new HashMap<>();
  
  private static final int RESET_TICK = 0;
  
  private static final int MAX_TICK = 20;
  
  private static final int RADIUS = 50;
  
  private static final int FIRE_TIME = 60;
  
  private static final CooldownData COOLDOWN_DATA = CooldownData.builder()
    .duration(TimeUnit.MINUTES.toMillis(60L))
    .name("fire_place")
    .build();
  
  private int tick = 0;
  
  private AxisAlignedBB axisAlignedBB;
  
  private List<EntityLivingBase> getPlayersAround() {
    AxisAlignedBB axisAlignedBB = getAxisAlignedBB();
    List<EntityLivingBase> ret = new ArrayList<>();
    List<EntityLivingBase> livings = this.field_145850_b.func_72872_a(EntityLivingBase.class, axisAlignedBB);
    for (EntityLivingBase living : livings) {
      if (living instanceof EntityPlayerMP) {
        EntityPlayerMP player = (EntityPlayerMP)living;
        ret.add(player);
      } 
      if (WitherUtils.isWither((Entity)living))
        ret.add(living); 
    } 
    return livings;
  }
  
  private AxisAlignedBB getAxisAlignedBB() {
    if (this.axisAlignedBB == null)
      this.axisAlignedBB = AxisAlignedBB.func_72330_a((this.field_145851_c - 50), (this.field_145848_d - 50), (this.field_145849_e - 50), (this.field_145851_c + 50), (this.field_145848_d + 50), (this.field_145849_e + 50)); 
    return this.axisAlignedBB;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    this.tick++;
    if (this.field_145850_b.field_72995_K || this.tick < 20)
      return; 
    this.tick = 0;
    for (EntityLivingBase entity : getPlayersAround()) {
      if (CooldownUtils.isOnCooldown((Entity)entity, COOLDOWN_DATA.getName()) || MonthlyUtils.isWarZone((int)entity.field_70165_t, (int)entity.field_70161_v))
        continue; 
      if (!(entity instanceof EntityPlayerMP)) {
        if (!WITHER_MAP.containsKey(entity.func_110124_au()))
          WITHER_MAP.put(entity.func_110124_au(), Integer.valueOf(0)); 
        entity.func_70097_a(DamageSource.field_76376_m, 2.5F);
        WITHER_MAP.put(entity.func_110124_au(), Integer.valueOf(((Integer)WITHER_MAP.get(entity.func_110124_au())).intValue() + 1));
        int witherCount = ((Integer)WITHER_MAP.get(entity.func_110124_au())).intValue();
        if (witherCount >= 60) {
          CooldownUtils.setCooldown((Entity)entity, COOLDOWN_DATA.getName(), COOLDOWN_DATA.getDuration());
          WITHER_MAP.remove(entity.func_110124_au());
        } 
        continue;
      } 
      entity.func_70015_d(60);
      CooldownUtils.setCooldown((Entity)entity, COOLDOWN_DATA.getName(), COOLDOWN_DATA.getDuration());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\tiles\TileEntityFirePlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */