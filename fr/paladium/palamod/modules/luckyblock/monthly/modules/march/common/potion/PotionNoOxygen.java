package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palamod.api.ItemsRegister;
import java.awt.Color;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PotionNoOxygen extends APotion {
  private static final float DAMAGE = 2.0F;
  
  private static final long DAMAGE_DELAY = 2000L;
  
  private static final HashMap<UUID, Long> DAMAGE_DELAY_MAP = new HashMap<>();
  
  public PotionNoOxygen() {
    super("no_oxygen", true, Color.BLUE);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    if (!canDamage(player.func_110124_au()))
      return; 
    ItemStack helmet = player.func_82169_q(3);
    if (helmet != null && helmet.func_77973_b().equals(ItemsRegister.STAR_HELMET))
      return; 
    float health = player.func_110143_aJ();
    if (health <= 2.0F)
      return; 
    player.func_70097_a(DamageSource.field_76376_m, 2.0F);
  }
  
  private boolean canDamage(UUID uuid) {
    long now = System.currentTimeMillis();
    Long lastDamage = DAMAGE_DELAY_MAP.get(uuid);
    if (lastDamage == null) {
      DAMAGE_DELAY_MAP.put(uuid, Long.valueOf(now));
      return true;
    } 
    boolean canDamage = (now - lastDamage.longValue() >= 2000L);
    if (canDamage)
      DAMAGE_DELAY_MAP.put(uuid, Long.valueOf(now)); 
    return canDamage;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionNoOxygen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */