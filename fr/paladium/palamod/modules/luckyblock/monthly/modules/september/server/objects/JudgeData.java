package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.SchematicManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.DevilsAdvocateEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JudgeData {
  public void setDevilStep(int devilStep) {
    this.devilStep = devilStep;
  }
  
  public void setJudgeStep(int judgeStep) {
    this.judgeStep = judgeStep;
  }
  
  public void setWin(boolean win) {
    this.win = win;
  }
  
  public static final long LOSE_EXPIRATION_DELAY = TimeUnit.SECONDS.toMillis(30L);
  
  private final UUID schematicUniqueId;
  
  private final UUID judgeUniqueId;
  
  private final UUID devilUniqueId;
  
  private int devilStep;
  
  private int judgeStep;
  
  private boolean win;
  
  public UUID getSchematicUniqueId() {
    return this.schematicUniqueId;
  }
  
  public UUID getJudgeUniqueId() {
    return this.judgeUniqueId;
  }
  
  public UUID getDevilUniqueId() {
    return this.devilUniqueId;
  }
  
  public int getDevilStep() {
    return this.devilStep;
  }
  
  public int getJudgeStep() {
    return this.judgeStep;
  }
  
  public boolean isWin() {
    return this.win;
  }
  
  public JudgeData(Entity judge, Entity devil, UUID schematicUniqueId) {
    this.judgeUniqueId = judge.func_110124_au();
    this.devilUniqueId = devil.func_110124_au();
    this.schematicUniqueId = schematicUniqueId;
    this.devilStep = 0;
    this.judgeStep = 0;
    this.win = false;
  }
  
  public boolean isValid(Entity entity) {
    return (this.judgeUniqueId.equals(entity.func_110124_au()) || this.devilUniqueId.equals(entity.func_110124_au()));
  }
  
  public int incrementDevilStep() {
    this.devilStep++;
    return this.devilStep;
  }
  
  public int incrementJudgeStep() {
    this.judgeStep++;
    return this.judgeStep;
  }
  
  public void onWin(EntityPlayerMP player) {
    if (this.win)
      return; 
    this.win = true;
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.BLAZE_AND_STEEL));
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.DAEMON_AMULET));
    onLose(player);
    MonthlyUtils.playSound(player, "spooky");
  }
  
  public void onLose(EntityPlayerMP player) {
    Optional<TimedSchematic> result = SchematicManager.getInstance().getSchematic(this.schematicUniqueId);
    if (result.isPresent()) {
      TimedSchematic timed = result.get();
      timed.updateExpiration(LOSE_EXPIRATION_DELAY);
    } 
    World world = player.field_70170_p;
    EntityLivingBase devil = MonthlyUtils.getLivingEntityByUniqueId(world, this.devilUniqueId);
    EntityLivingBase judge = MonthlyUtils.getLivingEntityByUniqueId(world, this.judgeUniqueId);
    if (devil != null && !devil.field_70128_L)
      devil.func_70106_y(); 
    if (judge != null && !judge.field_70128_L)
      judge.func_70106_y(); 
    DevilsAdvocateEvent.INSTANCE.remove(player.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\JudgeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */