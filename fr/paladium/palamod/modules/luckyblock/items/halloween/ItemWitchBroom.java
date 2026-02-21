package fr.paladium.palamod.modules.luckyblock.items.halloween;

import com.google.common.base.Strings;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWitchBroom extends ItemArmor implements ITooltipWiki {
  private static final String NBT_IDENTIFIER_TIMER = "timer";
  
  private static final String NBT_IDENTIFIER_REFILL = "refill";
  
  public ItemWitchBroom() {
    super(ItemArmor.ArmorMaterial.DIAMOND, 0, 2);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("witchbroom");
    func_111206_d("palamod:witchbroom");
    func_77625_d(1);
    func_77656_e(5);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    if (world.field_72995_K)
      return; 
    if (!stack.func_77942_o())
      stack.field_77990_d = new NBTTagCompound(); 
    if (WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, Item.func_150891_b(stack.func_77973_b())))
      return; 
    if (!stack.field_77990_d.func_74767_n("refill") && stack.field_77990_d.func_74762_e("timer") < 864000) {
      if (player.func_70660_b((Potion)PLuckyBlock.SUPER_MAN) == null) {
        player.func_70690_d(new PotionEffect(PLuckyBlock.SUPER_MAN.field_76415_H, 200, 120));
        stack.field_77990_d.func_74768_a("timer", stack.field_77990_d.func_74762_e("timer") + 3600);
        player.field_71075_bZ.field_75101_c = true;
        player.field_71075_bZ.field_75100_b = true;
        player.func_71016_p();
      } 
    } else {
      player.field_71075_bZ.field_75101_c = false;
      player.field_71075_bZ.field_75100_b = false;
      player.func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
      player.func_71016_p();
      stack.field_77990_d.func_74757_a("refill", true);
    } 
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity player, int p_77663_4_, boolean hasInHand) {
    if (!stack.func_77942_o())
      stack.field_77990_d = new NBTTagCompound(); 
    if (((EntityLivingBase)player).func_70660_b((Potion)PLuckyBlock.SUPER_MAN) != null && ((EntityLivingBase)player)
      .func_70660_b((Potion)PLuckyBlock.SUPER_MAN).func_76458_c() == 120 && 
      !(Optional.ofNullable((T)((EntityPlayer)player).func_82169_q(0)).map(ItemStack::func_77973_b).orElse(null) instanceof ItemWitchBroom)) {
      ((EntityPlayer)player).field_71075_bZ.field_75101_c = false;
      ((EntityPlayer)player).field_71075_bZ.field_75100_b = false;
      ((EntityLivingBase)player).func_82170_o(PLuckyBlock.SUPER_MAN.field_76415_H);
      ((EntityPlayer)player).func_71016_p();
    } 
    if (stack.field_77990_d.func_74762_e("timer") > 0)
      stack.field_77990_d.func_74768_a("timer", stack.field_77990_d.func_74762_e("timer") - 1); 
    if (stack.field_77990_d.func_74762_e("timer") <= 0) {
      stack.field_77990_d.func_74757_a("refill", false);
      stack.field_77990_d.func_74768_a("timer", 0);
    } 
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    if (item.func_77942_o()) {
      int delay = item.func_77978_p().func_74762_e("timer");
      list.add("§8[§r" + getProgressBar(delay, 864000, 40, '|', "§c", "§7") + "§8]");
      if (player.func_82169_q(0) != item) {
        list.add(" ");
        if (delay == 0) {
          list.add("§aRechargé");
        } else {
          list.add("§eTemps de rechargement §8" + getFullTime("", "", (delay / 20)));
        } 
      } 
    } 
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getProgressBar(int current, int max, int totalBars, char symbol, String completedColor, String notCompletedColor) {
    float percent = current / max;
    int progressBars = (int)(totalBars * percent);
    return Strings.repeat(completedColor + symbol, progressBars) + 
      Strings.repeat(notCompletedColor + symbol, totalBars - progressBars);
  }
  
  public static String getFullTime(String timeColor, String explainColor, long seconds) {
    int day = (int)TimeUnit.SECONDS.toDays(seconds);
    long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
    long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60L;
    StringBuilder builder = new StringBuilder();
    if (day > 0)
      builder.append(timeColor).append(day).append(explainColor).append(" jour")
        .append((day > 1) ? "s" : "").append(" "); 
    if (hours > 0L)
      builder.append(timeColor).append(hours).append(explainColor).append(" heure")
        .append((hours > 1L) ? "s" : "").append(" "); 
    if (minute > 0L)
      builder.append(timeColor).append(minute).append(explainColor).append(" minute")
        .append((minute > 1L) ? "s" : ""); 
    if (builder.toString().isEmpty())
      if (seconds > 0L) {
        builder.append(timeColor).append(seconds).append(" seconde").append((seconds > 1L) ? "s" : "");
      } else {
        builder.append(timeColor).append("prêt");
      }  
    return builder.toString();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemWitchBroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */