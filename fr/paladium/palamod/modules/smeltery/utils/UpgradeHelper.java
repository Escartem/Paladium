package fr.paladium.palamod.modules.smeltery.utils;

import fr.paladium.palamod.modules.miner.item.ItemGodPickaxe;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxeUpgrade;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemBroadsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemFastsword;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class UpgradeHelper {
  public static HashMap<Integer, Integer> upgrades;
  
  public static HashMap<Integer, Integer> upgradeType;
  
  public static final int PHAMMER = 0;
  
  public static final int PSWORD = 1;
  
  public static final int PGAUNTLET = 2;
  
  public static final int SMELT = 8;
  
  public static final int FORTUNE = 1;
  
  public static final int SPEED = 2;
  
  public static final int OBSIDIAN = 6;
  
  public static final int DAMAGE = 3;
  
  public static final int FLAME = 4;
  
  public static final int KNOCKBACK = 5;
  
  public static final int MORE = 7;
  
  public static final int LEGENDARY_STONE_INVISIBILITY = 9;
  
  public static final int LEGENDARY_STONE_FORTUNE = 10;
  
  public static final int LEGENDARY_STONE_CHAOS = 11;
  
  public static final int LEGENDARY_STONE_POWER = 12;
  
  public static final int LEGENDARY_STONE_TELEPORTATION = 13;
  
  public static final int LEGENDARY_STONE_JOBS = 14;
  
  public static final int GOD_PICKAXE_AUTOSMELT = 15;
  
  public static final int GOD_PICKAXE_BIGHOLE = 16;
  
  public static void init() {
    upgrades = new HashMap<>();
    upgradeType = new HashMap<>();
    upgrades.put(Integer.valueOf(1), Integer.valueOf(3));
    upgradeType.put(Integer.valueOf(1), Integer.valueOf(0));
    upgrades.put(Integer.valueOf(8), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(8), Integer.valueOf(0));
    upgrades.put(Integer.valueOf(2), Integer.valueOf(3));
    upgradeType.put(Integer.valueOf(2), Integer.valueOf(0));
    upgrades.put(Integer.valueOf(6), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(6), Integer.valueOf(0));
    upgrades.put(Integer.valueOf(3), Integer.valueOf(3));
    upgradeType.put(Integer.valueOf(3), Integer.valueOf(1));
    upgrades.put(Integer.valueOf(4), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(4), Integer.valueOf(1));
    upgrades.put(Integer.valueOf(5), Integer.valueOf(2));
    upgradeType.put(Integer.valueOf(5), Integer.valueOf(1));
    upgrades.put(Integer.valueOf(7), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(9), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(9), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(11), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(11), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(10), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(10), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(14), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(14), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(12), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(12), Integer.valueOf(2));
    upgrades.put(Integer.valueOf(13), Integer.valueOf(1));
    upgradeType.put(Integer.valueOf(13), Integer.valueOf(2));
  }
  
  public static boolean canApplyUpgrade(ItemStack stack, int type, ItemStack tool) {
    if (tool.func_77973_b() instanceof ItemGodPickaxe && stack
      .func_77973_b() instanceof ItemGodPickaxeUpgrade) {
      ItemGodPickaxe pickaxe = (ItemGodPickaxe)tool.func_77973_b();
      int plvl = pickaxe.getLevel(tool);
      boolean canUpgrade = false;
      ItemGodPickaxeUpgrade upgrade = (ItemGodPickaxeUpgrade)stack.func_77973_b();
      if (upgrade.getType() == null)
        return false; 
      int lvl = 0;
      for (ItemGodPickaxe.Upgrade up : pickaxe.getUpgrades(tool)) {
        if (up.getType() == upgrade.getType())
          lvl = up.getLevel(); 
      } 
      for (int i = 0; i < plvl + 1; i++) {
        if (pickaxe.getUpgradeByLevel().get(i) != null)
          if (((ItemGodPickaxe.Upgrade)pickaxe.getUpgradeByLevel().get(i)).getType() == upgrade.getType() && (
            (ItemGodPickaxe.Upgrade)pickaxe.getUpgradeByLevel().get(i)).getLevel() > lvl)
            canUpgrade = true;  
      } 
      if (!canUpgrade)
        return false; 
      if (lvl < upgrade.getType().getMaxLevel())
        return true; 
      return false;
    } 
    if (getMaxEnchants(tool) <= getEnchants(tool) && type != 7 && 
      !(tool.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEndiumGauntlet))
      return false; 
    int toolType = -1;
    if (tool.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.tools.ItemPaladiumHammer)
      toolType = 0; 
    if (tool.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumBroadsword || tool
      .func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumFastsword)
      toolType = 1; 
    if (tool.func_77973_b() instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEndiumGauntlet)
      toolType = 2; 
    if (upgrades.get(Integer.valueOf(type)) == null || getModifier(tool, type) >= ((Integer)upgrades.get(Integer.valueOf(type))).intValue())
      return false; 
    if (upgradeType.containsKey(Integer.valueOf(type)) && toolType != ((Integer)upgradeType.get(Integer.valueOf(type))).intValue())
      return false; 
    return true;
  }
  
  private static int getEnchants(ItemStack stack) {
    createDefaultNBT(stack);
    NBTTagCompound tag = stack.func_77978_p();
    return tag.func_74762_e("modifiersammount");
  }
  
  public static int getMaxEnchants(ItemStack stack) {
    createDefaultNBT(stack);
    NBTTagCompound tag = stack.func_77978_p();
    return tag.func_74762_e("modifiersmax") + getModifier(stack, 7) * 2;
  }
  
  public static int getModifier(ItemStack stack, int type) {
    createDefaultNBT(stack);
    NBTTagCompound tag = stack.func_77978_p();
    int result = 0;
    if (tag.func_74764_b("upgradearray"))
      for (int i : tag.func_74759_k("upgradearray")) {
        if (i == type)
          result++; 
      }  
    return result;
  }
  
  public static void createDefaultNBT(ItemStack stack) {
    if (!stack.func_77942_o()) {
      int modifiers = 3;
      if (stack.func_77973_b() instanceof fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumBroadsword)
        modifiers = 4; 
      stack.func_77982_d(new NBTTagCompound());
      stack.func_77978_p().func_74768_a("modifiersammount", 0);
      stack.func_77978_p().func_74768_a("modifiersmax", modifiers);
    } 
  }
  
  public static void applyUpgrade(ItemStack stack, int type) {
    createDefaultNBT(stack);
    NBTTagCompound tag = stack.func_77978_p();
    ArrayList<Integer> integerArray = new ArrayList<>();
    if (tag.func_74764_b("upgradearray"))
      for (int i : tag.func_74759_k("upgradearray"))
        integerArray.add(Integer.valueOf(i));  
    integerArray.add(Integer.valueOf(type));
    tag.func_74783_a("upgradearray", convertIntegers(integerArray));
    tag.func_74768_a("modifiersammount", tag.func_74762_e("modifiersammount") + 1);
    if (type == 3) {
      if (stack.func_77973_b() instanceof ItemBroadsword) {
        AttributeModifier attackModifier = new AttributeModifier(FastUUID.parseUUID("CB3F55D3-645C-4F38-A497-9C13A33DB5CF"), "Weapon Upgrade", ((ItemBroadsword)stack.func_77973_b()).getDamages(stack), 0);
        NBTTagCompound modifierNBT = writeAttributeModifierToNBT(SharedMonsterAttributes.field_111264_e, attackModifier);
        NBTTagList list = new NBTTagList();
        list.func_74742_a((NBTBase)modifierNBT);
        tag.func_74782_a("AttributeModifiers", (NBTBase)list);
      } 
      if (stack.func_77973_b() instanceof ItemFastsword) {
        AttributeModifier attackModifier = new AttributeModifier(FastUUID.parseUUID("CB3F55D3-645C-4F38-A497-9C13A33DB5CF"), "Weapon Upgrade", ((ItemFastsword)stack.func_77973_b()).getDamages(stack), 0);
        NBTTagCompound modifierNBT = writeAttributeModifierToNBT(SharedMonsterAttributes.field_111264_e, attackModifier);
        NBTTagList list = new NBTTagList();
        list.func_74742_a((NBTBase)modifierNBT);
        tag.func_74782_a("AttributeModifiers", (NBTBase)list);
      } 
    } 
  }
  
  private static NBTTagCompound writeAttributeModifierToNBT(IAttribute attribute, AttributeModifier modifier) {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    nbttagcompound.func_74778_a("AttributeName", attribute.func_111108_a());
    nbttagcompound.func_74778_a("Name", modifier.func_111166_b());
    nbttagcompound.func_74780_a("Amount", modifier.func_111164_d());
    nbttagcompound.func_74768_a("Operation", modifier.func_111169_c());
    nbttagcompound.func_74772_a("UUIDMost", modifier.func_111167_a().getMostSignificantBits());
    nbttagcompound.func_74772_a("UUIDLeast", modifier.func_111167_a().getLeastSignificantBits());
    return nbttagcompound;
  }
  
  public static String getUpgradeName(int type) {
    if (type == 8)
      return "§3 Smelt"; 
    if (type == 1)
      return "§6 Fortune"; 
    if (type == 2)
      return "§c Speed"; 
    if (type == 3)
      return "§4 Damage"; 
    if (type == 4)
      return "§7 Flame"; 
    if (type == 5)
      return "§2 Knockback"; 
    if (type == 6)
      return "§0 Obsidian"; 
    if (type == 11)
      return "§5§l Chaos"; 
    if (type == 9)
      return "§7§l Invisibility"; 
    if (type == 14)
      return "§a§l Jobs"; 
    if (type == 12)
      return "§c§l Power"; 
    if (type == 13)
      return "§2§l Teleportation"; 
    if (type == 10)
      return "§6§l Fortune"; 
    return "";
  }
  
  public static String getUpgradeColor(int type) {
    if (type == 8)
      return "§3"; 
    if (type == 1)
      return "§6"; 
    if (type == 2)
      return "§c"; 
    if (type == 3)
      return "§4"; 
    if (type == 4)
      return "§7"; 
    if (type == 5)
      return "§2"; 
    if (type == 6)
      return "§0"; 
    return "";
  }
  
  public static int[] convertIntegers(List<Integer> integers) {
    int[] ret = new int[integers.size()];
    for (int i = 0; i < ret.length; i++)
      ret[i] = ((Integer)integers.get(i)).intValue(); 
    return ret;
  }
  
  public static int[] getUpgradeAmmount(ItemStack stack) {
    createDefaultNBT(stack);
    NBTTagCompound tag = stack.func_77978_p();
    if (!tag.func_74764_b("upgradearray")) {
      tag.func_74783_a("upgradearray", new int[68]);
      return null;
    } 
    ArrayList<Integer> integerArray = new ArrayList<>();
    for (int i : tag.func_74759_k("upgradearray")) {
      if (i != 0)
        integerArray.add(Integer.valueOf(i)); 
    } 
    return convertIntegers(integerArray);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\utils\UpgradeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */