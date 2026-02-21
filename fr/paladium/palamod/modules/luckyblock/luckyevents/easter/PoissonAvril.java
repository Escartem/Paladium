package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PoissonAvril extends ATickable<Object> {
  public static final String FIRST_SKIN_PATH = "palamod:textures/entity/npc/fish_1.png";
  
  public static final String SECOND_SKIN_PATH = "palamod:textures/entity/npc/fish_2.png";
  
  public static final String THIRD_SKIN_PATH = "palamod:textures/entity/npc/fish_3.png";
  
  private static PoissonAvril instance;
  
  public static PoissonAvril getInstance() {
    return instance;
  }
  
  public PoissonAvril() {
    super(null, 1000L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.APRIL_FISH, 2));
  }
  
  public String getName() {
    return "Poisson d’avril";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "easter/poisson_avril";
  }
  
  protected void onTick(long now) {
    MonthlyUtils.getOnlinePlayers().forEach(player -> {
          MarchPlayer eep = MarchPlayer.get((EntityPlayer)player);
          if (eep == null)
            return; 
          if (now > eep.getFishExpirationMillis())
            return; 
          pickupRandomEffect((EntityPlayer)player);
        });
  }
  
  public static ResourceLocation getSkin(EntityPlayer player) {
    if (player == null)
      return null; 
    if (PotionsRegister.FISH_ONE != null && player.func_82165_m(PotionsRegister.FISH_ONE.getPotionId()))
      return new ResourceLocation("palamod:textures/entity/npc/fish_1.png"); 
    if (PotionsRegister.FISH_TWO != null && player.func_82165_m(PotionsRegister.FISH_TWO.getPotionId()))
      return new ResourceLocation("palamod:textures/entity/npc/fish_2.png"); 
    if (PotionsRegister.FISH_THREE != null && player.func_82165_m(PotionsRegister.FISH_THREE.getPotionId()))
      return new ResourceLocation("palamod:textures/entity/npc/fish_3.png"); 
    return null;
  }
  
  public static void pickupRandomEffect(EntityPlayer player) {
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    if (player.func_82165_m(PotionsRegister.FISH_ONE.getPotionId()) || player
      .func_82165_m(PotionsRegister.FISH_TWO.getPotionId()) || player
      .func_82165_m(PotionsRegister.FISH_THREE.getPotionId()))
      return; 
    List<PotionEffect> effects = Arrays.asList(new PotionEffect[] { new PotionEffect(PotionsRegister.FISH_ONE
            .getPotionId(), MonthlyUtils.translateSecondsToTicks(120), 0), new PotionEffect(PotionsRegister.FISH_TWO
            .getPotionId(), MonthlyUtils.translateSecondsToTicks(120), 0), new PotionEffect(PotionsRegister.FISH_THREE
            .getPotionId(), MonthlyUtils.translateSecondsToTicks(120), 0) });
    effects.forEach(effect -> effect.setCurativeItems(new ArrayList()));
    PotionEffect effect = effects.get(world.field_73012_v.nextInt(effects.size()));
    player.func_70690_d(effect);
  }
  
  public static void applyPotionEffect(EntityPlayer player) {
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    MarchPlayer eep = MarchPlayer.get(player);
    if (eep == null)
      return; 
    long minInterval = TimeUnit.MINUTES.toMillis(30L);
    long now = System.currentTimeMillis();
    if (now - eep.getLastFishMillis() < minInterval)
      return; 
    eep.setLastFishMillis(now);
    EntitySilverfish fish = new EntitySilverfish(world);
    fish.func_70107_b(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    world.func_72838_d((Entity)fish);
    MonthlyUtils.sendMessage(player, new String[] { "§eUn poisson d'avril est apparu dans votre dos !" });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\PoissonAvril.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */