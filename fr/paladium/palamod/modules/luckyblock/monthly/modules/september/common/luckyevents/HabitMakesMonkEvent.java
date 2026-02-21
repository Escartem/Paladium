package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class HabitMakesMonkEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "L'habit fait le moine";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 300;
  
  private static final String TEXTURE_PATH = "september/habit_makes_monk";
  
  public static final String FIRST_SKIN_PATH = "palamod:textures/entity/npc/monk1.png";
  
  public static final String SECOND_SKIN_PATH = "palamod:textures/entity/npc/monk2.png";
  
  public static final String NBT_MONK_FIELD = "monk";
  
  public static final String MONK_CHEST_PLATE_MESSAGE = "&aVotre plastron vous permet désormais de vous transformer en moine !";
  
  public static final String MONK_TRANSFORM_MESSAGE = "&aVous vous êtes transformé en moine !";
  
  private static final long MONK_DURATION = TimeUnit.HOURS.toMillis(6L);
  
  public static HabitMakesMonkEvent INSTANCE;
  
  public HabitMakesMonkEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    this;
    ItemStack chestPlate = getChestPlate((EntityPlayer)player);
    int id = (new Random()).nextBoolean() ? 1 : 0;
    if (chestPlate != null) {
      NBTTagCompound compound = chestPlate.func_77978_p();
      if (compound == null)
        compound = new NBTTagCompound(); 
      compound.func_74768_a("monk", id);
      chestPlate.func_77982_d(compound);
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVotre plastron vous permet désormais de vous transformer en moine !" });
      return;
    } 
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return; 
    properties.setMonkType(id);
    properties.setMonkExpirationMillis(System.currentTimeMillis() + MONK_DURATION);
    addEffect((EntityPlayer)player, id);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous vous êtes transformé en moine !" });
  }
  
  private static ItemStack getChestPlate(EntityPlayer player) {
    return player.func_82169_q(2);
  }
  
  public boolean canAddEffect(PlayerLuckyBlockProperties properties) {
    return (properties != null && properties.getMonkExpirationMillis() > System.currentTimeMillis());
  }
  
  public void addEffect(EntityPlayer player, int id) {
    int potionId = (id == 0) ? PLuckyBlock.MONK.field_76415_H : PLuckyBlock.MONK_SECOND.field_76415_H;
    PotionEffect effect = new PotionEffect(potionId, MonthlyUtils.translateSecondsToTicks(60), 0);
    effect.getCurativeItems().clear();
    player.func_70690_d(effect);
  }
  
  public static ResourceLocation getSkin(EntityPlayer player) {
    ItemStack chestPlate = getChestPlate(player);
    if (chestPlate == null) {
      if (MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.MONK))
        return new ResourceLocation("palamod:textures/entity/npc/monk1.png"); 
      if (MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.MONK_SECOND))
        return new ResourceLocation("palamod:textures/entity/npc/monk2.png"); 
      return null;
    } 
    NBTTagCompound compound = chestPlate.func_77978_p();
    if (compound == null) {
      if (MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.MONK))
        return new ResourceLocation("palamod:textures/entity/npc/monk1.png"); 
      return null;
    } 
    int id = compound.func_74762_e("monk");
    if (!compound.func_74764_b("monk")) {
      if (MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.MONK))
        return new ResourceLocation("palamod:textures/entity/npc/monk1.png"); 
      return null;
    } 
    if (id == 0)
      return new ResourceLocation("palamod:textures/entity/npc/monk1.png"); 
    if (id == 1)
      return new ResourceLocation("palamod:textures/entity/npc/monk2.png"); 
    return null;
  }
  
  public String getName() {
    return "L'habit fait le moine";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "september/habit_makes_monk";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\HabitMakesMonkEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */