package fr.paladium.palamod.modules.paladium.common.items.sword.ancient;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect.AncientHammerFortuneEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect.AncientHammerPlayerCamListener;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect.AncientHammerPlayerInvisibilityListener;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.effect.AncientHammerPowerEffectListener;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager.AncientHammerEffectCamManager;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemAncientHammer extends ItemSword {
  public static final Map<LegendaryStone.Effect, Object> EFFECT_MAP = new HashMap<>();
  
  private static boolean registered;
  
  static {
    EFFECT_MAP.put(LegendaryStone.Effect.CHAOS, null);
    EFFECT_MAP.put(LegendaryStone.Effect.TELEPORTATION, null);
    EFFECT_MAP.put(LegendaryStone.Effect.INVISIBILITY, new AncientHammerPlayerInvisibilityListener());
    EFFECT_MAP.put(LegendaryStone.Effect.POWER, new AncientHammerPowerEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.FORTUNE, new AncientHammerFortuneEffectListener());
    EFFECT_MAP.put(LegendaryStone.Effect.JOBS, null);
  }
  
  public ItemAncientHammer() {
    super(PToolMaterial.endium);
    func_77656_e(0);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b("ancient_hammer");
    func_111206_d("palamod:ancient_hammer");
    if (!registered) {
      registered = true;
      if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
        AncientHammerPlayerCamListener listener = new AncientHammerPlayerCamListener();
        MinecraftForge.EVENT_BUS.register(listener);
        FMLCommonHandler.instance().bus().register(listener);
      } 
      EFFECT_MAP.values().forEach(effectObj -> {
            if (effectObj != null) {
              MinecraftForge.EVENT_BUS.register(effectObj);
              FMLCommonHandler.instance().bus().register(effectObj);
            } 
          });
    } 
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K || getEffect(stack) == null || WorldGuardUtils.isItemEffectBlocked((Entity)player, stack))
      return super.func_77659_a(stack, world, player); 
    LegendaryStone.Effect effect = getEffect(stack);
    if (effect == null)
      return super.func_77659_a(stack, world, player); 
    ItemAncientHammerPlayerData pData = ItemAncientHammerPlayerData.get(player);
    if (pData == null)
      return super.func_77659_a(stack, world, player); 
    if (!player.field_71075_bZ.field_75098_d && pData.isOnCooldown(effect)) {
      if (effect == LegendaryStone.Effect.POWER && pData.isPowerEffectActive() && !pData.isPowerEffectPlayed()) {
        pData.setPowerEffectPlayed(true);
        EntityAncientHammerEffect entityAncientHammerEffect = new EntityAncientHammerEffect(world, effect, player, player.field_70177_z);
        world.func_72838_d((Entity)entityAncientHammerEffect);
        AncientHammerEffectCamManager.start((EntityPlayerMP)player, entityAncientHammerEffect);
        return super.func_77659_a(stack, world, player);
      } 
      long totalSecondsLeft = Math.max(0L, (pData.getLastUse(effect) - UniversalTimeUtils.now()) / 1000L);
      int hoursLeft = (int)(totalSecondsLeft / 3600L);
      int minutesLeft = (int)(totalSecondsLeft % 3600L / 60L);
      String cdMessage = (hoursLeft > 0) ? (hoursLeft + " heures et " + minutesLeft + " minutes") : (minutesLeft + " minutes");
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §rVous devez attendre " + cdMessage + " avant de pouvoir réutiliser l'effet de votre Marteau Antique."));
      return super.func_77659_a(stack, world, player);
    } 
    if (effect == LegendaryStone.Effect.POWER && !pData.isPowerEffectActive()) {
      pData.setLastUse(effect);
      world.func_72956_a((Entity)player, SoundUtils.GHAST_FIREBALL.getSoundName(), 1.0F, 1.0F);
      return super.func_77659_a(stack, world, player);
    } 
    EntityAncientHammerEffect entity = new EntityAncientHammerEffect(world, effect, player, player.field_70177_z);
    world.func_72838_d((Entity)entity);
    AncientHammerEffectCamManager.start((EntityPlayerMP)player, entity);
    pData.setLastUse(effect);
    return super.func_77659_a(stack, world, player);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    LegendaryStone.Effect effect = ItemAncientArmor.getEffect(item);
    if (effect != null) {
      list.add("§7Effect: §a" + effect.getDisplayName());
    } else {
      list.add("§7Effect: §cNone");
    } 
  }
  
  public static void setEffect(ItemStack item, LegendaryStone.Effect effect) {
    if (!isApplicableEffect(effect))
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
  
  public static boolean isApplicableEffect(LegendaryStone.Effect effect) {
    return EFFECT_MAP.containsKey(effect);
  }
  
  public static class AncientHammerEffectData {
    public final long cooldown;
    
    public final String description;
    
    private AncientHammerEffectData(long cooldown, String description) {
      this.cooldown = cooldown;
      this.description = description;
    }
    
    public static AncientHammerEffectData of(long cooldown, String description) {
      return new AncientHammerEffectData(cooldown, description);
    }
    
    public long getCooldown() {
      return this.cooldown;
    }
    
    public String getDescription() {
      return this.description;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\ItemAncientHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */