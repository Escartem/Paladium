package fr.paladium.palawither.common.entity;

import fr.paladium.factions.api.type.FactionRelationshipType;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.base.property.WitherProperties;
import fr.paladium.palawither.common.wither.condition.IWitherCondition;
import fr.paladium.palawither.common.wither.condition.impl.FactionPlayerCountWitherCondition;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityThirstyWither extends EntityWitherBase {
  public EntityThirstyWither(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    addProperty(new Object[] { WitherProperties.armored(), WitherProperties.breakBlock(20), WitherProperties.explosion(7, false, true) });
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(450.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
  }
  
  public boolean func_70058_J() {
    return false;
  }
  
  public boolean func_70072_I() {
    return false;
  }
  
  public boolean func_70090_H() {
    return false;
  }
  
  public boolean func_70648_aU() {
    return true;
  }
  
  public boolean func_96092_aw() {
    return false;
  }
  
  public void func_70044_A() {}
  
  public void func_145775_I() {
    int i = MathHelper.func_76128_c(this.field_70121_D.field_72340_a + 0.001D);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b + 0.001D);
    int k = MathHelper.func_76128_c(this.field_70121_D.field_72339_c + 0.001D);
    int l = MathHelper.func_76128_c(this.field_70121_D.field_72336_d - 0.001D);
    int i1 = MathHelper.func_76128_c(this.field_70121_D.field_72337_e - 0.001D);
    int j1 = MathHelper.func_76128_c(this.field_70121_D.field_72334_f - 0.001D);
    if (this.field_70170_p.func_72904_c(i, j, k, l, i1, j1))
      for (int k1 = i; k1 <= l; k1++) {
        for (int l1 = j; l1 <= i1; l1++) {
          for (int i2 = k; i2 <= j1; i2++) {
            Block block = this.field_70170_p.func_147439_a(k1, l1, i2);
            if (!block.func_149688_o().func_76224_d())
              try {
                block.func_149670_a(this.field_70170_p, k1, l1, i2, (Entity)this);
              } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Colliding entity with block");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being collided with");
                CrashReportCategory.func_147153_a(crashreportcategory, k1, l1, i2, block, this.field_70170_p.func_72805_g(k1, l1, i2));
                throw new ReportedException(crashreport);
              }  
          } 
        } 
      }  
  }
  
  public List<IWitherCondition> getConditions() {
    return Arrays.asList(new IWitherCondition[] { (IWitherCondition)FactionPlayerCountWitherCondition.min(FactionRelationshipType.ENEMY, 2) });
  }
  
  public String getBarTexture() {
    return "palawither:textures/overlay/wither/foreground/thirsty.png";
  }
  
  public String getColor() {
    return "#E3C085";
  }
  
  public ResourceLocation getInvokeSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/thirsty_wither/invoke.ogg");
  }
  
  public ResourceLocation getLoopSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/thirsty_wither/loop.ogg");
  }
  
  public ResourceLocation getSpawnSound() {
    return (ResourceLocation)MCResource.of("palawither", "sounds/entities/thirsty_wither/spawn.ogg");
  }
  
  public long getCooldown() {
    return ForgeEnv.isIDE() ? 10L : 300L;
  }
  
  public double getViewDistance() {
    return 240.0D;
  }
  
  public IWither.WitherInvokeAlert getAlert() {
    return IWither.WitherInvokeAlert.SERVER;
  }
  
  public double getMaxDistance() {
    return 1000.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\EntityThirstyWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */