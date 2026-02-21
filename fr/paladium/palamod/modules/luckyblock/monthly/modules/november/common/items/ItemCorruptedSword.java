package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.CooldownData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemCorruptedSword extends ItemSword implements ITooltipInformations {
  public static final String NAME = "corrupted_sword";
  
  private static final String DESCRIPTION = "DESC.TXT : Permet de donner un EFFECT:GOOD aléatoire au ENTITY:PLAYER touché par l’épée";
  
  public static final int EFFECT_DURATION = MonthlyUtils.translateSecondsToTicks(60);
  
  private static final int DURABILITY = 16;
  
  private static final CooldownData COOLDOWN_DATA = CooldownData.builder()
    .name("corrupted_sword")
    .duration(TimeUnit.SECONDS.toMillis(60L))
    .build();
  
  public static final List<PotionEffect> EFFECTS = Arrays.asList(new PotionEffect[] { 
        new PotionEffect(Potion.field_76444_x.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76429_m.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76422_e.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76426_n.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76432_h.field_76415_H, EFFECT_DURATION, 1), new PotionEffect(Potion.field_76434_w.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76420_g.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76430_j.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76439_r.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76428_l.field_76415_H, EFFECT_DURATION, 0), 
        new PotionEffect(Potion.field_76443_y.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76424_c.field_76415_H, EFFECT_DURATION, 0), new PotionEffect(Potion.field_76427_o.field_76415_H, EFFECT_DURATION, 0) });
  
  public static final String ON_COOLDOWN_MESSAGE = "§cVous devez attendre %s avant de pouvoir réutiliser cette épée.";
  
  private final Random random;
  
  public ItemCorruptedSword() {
    super(PToolMaterial.luckyCorrupted);
    func_77655_b("corrupted_sword");
    func_111206_d("palamod:corrupted_sword");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(16);
    this.random = new Random();
  }
  
  public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
    if (attacker.field_70170_p.field_72995_K)
      return false; 
    if (!(attacker instanceof EntityPlayer) || !(target instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)attacker;
    if (CooldownUtils.isOnCooldown((Entity)player, COOLDOWN_DATA.getName())) {
      long now = System.currentTimeMillis();
      long cooldown = CooldownUtils.getCooldown((Entity)attacker, COOLDOWN_DATA.getName());
      MonthlyUtils.sendMessage(player, new String[] { String.format("§cVous devez attendre %s avant de pouvoir réutiliser cette épée.", new Object[] { DurationConverter.fromMillisToString(cooldown - now) }) });
      return false;
    } 
    MonthlyUtils.damageCurrentItem(player, stack, 1);
    target.func_70690_d(getRandomEffect());
    CooldownUtils.setCooldown((Entity)player, COOLDOWN_DATA.getName(), COOLDOWN_DATA.getDuration());
    return true;
  }
  
  private PotionEffect getRandomEffect() {
    PotionEffect ret = EFFECTS.get(this.random.nextInt(EFFECTS.size()));
    return new PotionEffect(ret.func_76456_a(), ret.func_76459_b(), ret.func_76458_c());
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Permet de donner un EFFECT:GOOD aléatoire au ENTITY:PLAYER touché par l’épée");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemCorruptedSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */