package fr.paladium.palamod.modules.pillage.common.effects;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TNTEffect;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import io.netty.buffer.ByteBuf;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityTNTEffectPrimed extends EntityTNTPrimed implements IEntityAdditionalSpawnData {
  private TNTEffect block;
  
  public TNTEffect getBlock() {
    return this.block;
  }
  
  private String effectName = "";
  
  public EntityTNTEffectPrimed(World world) {
    super(world);
  }
  
  public EntityTNTEffectPrimed(World world, double x, double y, double z, EntityLivingBase placer, TNTEffect blockEffect) {
    super(world, x, y, z, placer);
    this.block = blockEffect;
    this.effectName = blockEffect.effect.getName();
  }
  
  public void func_70071_h_() {
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    this.field_70181_x -= 0.03999999910593033D;
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
    this.field_70159_w *= 0.9800000190734863D;
    this.field_70181_x *= 0.9800000190734863D;
    this.field_70179_y *= 0.9800000190734863D;
    if (this.field_70122_E) {
      this.field_70159_w *= 0.699999988079071D;
      this.field_70179_y *= 0.699999988079071D;
      this.field_70181_x *= -0.5D;
    } 
    if (this.field_70516_a-- <= 0) {
      func_70106_y();
      if (!this.field_70170_p.field_72995_K)
        explode(); 
    } else {
      this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, 0.0D, 0.0D, 0.0D);
    } 
  }
  
  private void explode() {
    if (this.block != null)
      this.block.effect.applyEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0, (func_94083_c() != null) ? (Entity)func_94083_c() : (Entity)this); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeByte(this.field_70516_a);
    ByteBufUtils.writeUTF8String(buffer, this.effectName);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.field_70516_a = additionalData.readByte();
    this.effectName = ByteBufUtils.readUTF8String(additionalData);
    Optional<TNTEffect> optEffect = PPRegisterer.PPBlocks.TNTS.stream().filter(p -> p.effect.getName().equals(this.effectName)).findFirst();
    optEffect.ifPresent(objectEffectBlockEntry -> this.block = objectEffectBlockEntry);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\EntityTNTEffectPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */