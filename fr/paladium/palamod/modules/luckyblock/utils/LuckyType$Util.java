package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palamod.api.BlocksRegister;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Util {
  private static Map<LuckyType, ItemStack> icons = new HashMap<>();
  
  public static Map<LuckyType, ItemStack> getIcons() {
    return icons;
  }
  
  private static Map<LuckyType, Color> colors = new HashMap<>();
  
  public static Map<LuckyType, Color> getColors() {
    return colors;
  }
  
  private static Map<LuckyType, Long> weights = new HashMap<>();
  
  public static Map<LuckyType, Long> getWeights() {
    return weights;
  }
  
  private static long totalWeight = 0L;
  
  private static void register(LuckyType type, ItemStack item, Color color) {
    register(type, item, color, null);
  }
  
  private static void register(LuckyType type, ItemStack item, Color color, Long weight) {
    icons.put(type, item);
    colors.put(type, color);
    if (weight != null) {
      weights.put(type, weight);
      totalWeight += weight.longValue();
    } 
  }
  
  public static void register() {
    register(LuckyType.PALADIUM, new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK), Color.decode("#D33C3C"), Long.valueOf(4000L));
    register(LuckyType.ENDIUM, new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK), Color.decode("#4765D7"), Long.valueOf(500L));
    register(LuckyType.FINDIUM, new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK), Color.decode("#F1E12C"), Long.valueOf(3500L));
    register(LuckyType.BLACK, new ItemStack((Block)BlocksRegister.BLACK_LUCKY_BLOCK), Color.WHITE, Long.valueOf(300L));
    register(LuckyType.MARCH, new ItemStack(BlocksRegister.MARCH_LUCKY_BLOCK), Color.decode("#AE533F"), Long.valueOf(170L));
    register(LuckyType.EASTER, new ItemStack((Block)BlocksRegister.EASTER_LUCKY_BLOCK), Color.decode("#E57C45"), Long.valueOf(170L));
    register(LuckyType.MAY, new ItemStack((Block)BlocksRegister.MAY_LUCKY_BLOCK), Color.decode("#5D2B34"), Long.valueOf(170L));
    register(LuckyType.JUNE, new ItemStack((Block)BlocksRegister.JUNE_LUCKY_BLOCK), Color.decode("#0F5D70"), Long.valueOf(170L));
    register(LuckyType.JULY, new ItemStack(BlocksRegister.JULY_LUCKY_BLOCK), Color.decode("#DFA270"), Long.valueOf(170L));
    register(LuckyType.AUGUST, new ItemStack(BlocksRegister.AUGUST_LUCKY_BLOCK), Color.decode("#22A5FF"), Long.valueOf(170L));
    register(LuckyType.SEPTEMBER, new ItemStack(BlocksRegister.SEPTEMBER_LUCKY_BLOCK), Color.decode("#F9EA8D"), Long.valueOf(170L));
    register(LuckyType.HALLOWEEN, new ItemStack((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK), Color.decode("#F18E0C"), Long.valueOf(170L));
    register(LuckyType.NOVEMBER, new ItemStack(BlocksRegister.NOVEMBER_LUCKY_BLOCK), Color.decode("#500C6E"), Long.valueOf(170L));
    register(LuckyType.CHRISTMAS, new ItemStack((Block)BlocksRegister.CHRISTMAS_LUCKY_BLOCK), Color.decode("#2BA554"), Long.valueOf(170L));
  }
  
  public static ItemStack getIconFrom(LuckyType type) {
    return icons.getOrDefault(type, null);
  }
  
  public static Color getColorFrom(LuckyType type) {
    return colors.getOrDefault(type, null);
  }
  
  public static Long getWeightFrom(LuckyType type) {
    return weights.getOrDefault(type, null);
  }
  
  public static LuckyType getRandomType() {
    long random = ThreadLocalRandom.current().nextLong(totalWeight);
    long currentWeight = 0L;
    for (Map.Entry<LuckyType, Long> entry : weights.entrySet()) {
      currentWeight += ((Long)entry.getValue()).longValue();
      if (random < currentWeight)
        return entry.getKey(); 
    } 
    return LuckyType.PALADIUM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LuckyType$Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */