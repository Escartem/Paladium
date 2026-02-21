package fr.paladium.palamod.modules.paladium.common.items.armors.ancient;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorChaosEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorFortuneEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorFullListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorInvisibilityEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorJobsEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorPowerEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.effect.ItemAncientArmorTeleportationEffectListener;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class ItemAncientArmor extends ItemArmor {
  private static final Map<LegendaryStone.Effect, Object> EFFECT_MAP = new HashMap<>();
  
  private static boolean registered;
  
  static {
    EFFECT_MAP.put(LegendaryStone.Effect.JOBS, new ItemAncientArmorJobsEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.CHAOS, new ItemAncientArmorChaosEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.POWER, new ItemAncientArmorPowerEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.FORTUNE, new ItemAncientArmorFortuneEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.INVISIBILITY, new ItemAncientArmorInvisibilityEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.TELEPORTATION, new ItemAncientArmorTeleportationEffectListener());
  }
  
  public ItemAncientArmor(int type, String unlocalizedName) {
    super(PArmorMaterial.ancient, 0, type);
    func_77656_e(0);
    func_77625_d(1);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b(unlocalizedName);
    func_111206_d("palamod:armor/ancient/" + unlocalizedName);
    if (!registered && FMLCommonHandler.instance().getSide() == Side.SERVER) {
      registered = true;
      for (Object object : EFFECT_MAP.values()) {
        MinecraftForge.EVENT_BUS.register(object);
        FMLCommonHandler.instance().bus().register(object);
      } 
      ItemAncientArmorFullListener listener = new ItemAncientArmorFullListener();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
    } 
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack output) {
    return false;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    LegendaryStone.Effect effect = getEffect(item);
    if (effect != null) {
      list.add("§7Effect: §a" + effect.getDisplayName());
    } else {
      list.add("§7Effect: §cNone");
    } 
  }
  
  public static void setEffect(ItemStack item, LegendaryStone.Effect effect) {
    if (effect == LegendaryStone.Effect.RANDOM)
      return; 
    NBTTagCompound tag = item.func_77978_p();
    if (tag == null) {
      tag = new NBTTagCompound();
      item.func_77982_d(tag);
    } 
    tag.func_74778_a("legendaryStoneEffect", effect.name());
  }
  
  public static LegendaryStone.Effect getEffect(ItemStack item) {
    NBTTagCompound tag = item.func_77978_p();
    if (tag != null && tag.func_74764_b("legendaryStoneEffect")) {
      String effectName = tag.func_74779_i("legendaryStoneEffect");
      return LegendaryStone.Effect.valueOf(effectName);
    } 
    return null;
  }
  
  public static boolean isFull(EntityPlayer player) {
    for (int i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
      ItemStack armor = player.field_71071_by.field_70460_b[i];
      if (armor == null || !(armor.func_77973_b() instanceof ItemAncientArmor))
        return false; 
    } 
    return true;
  }
  
  public static boolean hasEffect(EntityPlayer player, LegendaryStone.Effect effect) {
    for (int i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
      ItemStack armor = player.field_71071_by.field_70460_b[i];
      if (armor != null && armor.func_77973_b() instanceof ItemAncientArmor) {
        LegendaryStone.Effect armorEffect = getEffect(armor);
        if (armorEffect == effect)
          return true; 
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\ItemAncientArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */