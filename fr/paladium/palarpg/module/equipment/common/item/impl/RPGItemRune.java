package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGRuneItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import fr.paladium.palarpg.module.equipment.common.rune.RPGRuneStat;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class RPGItemRune extends Item implements IRPGItem {
  private static final Random RANDOM = new Random();
  
  private final RPGRuneItemData runeItemData;
  
  public RPGItemRune(RPGRuneItemData data) {
    func_77637_a(EquipmentCommonProxy.getRPGBasicTab());
    func_77625_d(data.getMaxStackSize());
    func_77655_b(data.getId());
    func_111206_d("palarpg:rune/" + data.getId());
    this.runeItemData = data;
  }
  
  public void register() {}
  
  public String getId() {
    return this.runeItemData.getId();
  }
  
  public RPGItemRarity getRPGRarity() {
    return this.runeItemData.getRarity();
  }
  
  public String func_77653_i(ItemStack stack) {
    if ((getItemData().getItemName() != null && !getItemData().getItemName().isEmpty()) || (getItemData().getTranslations() != null && !getItemData().getTranslations().isEmpty()))
      return super.func_77653_i(stack); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && "fr_FR".equals(Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a()))
      return "Rune " + (stack.func_77942_o() ? (" de " + EnumStats.fromOrdinal(stack.func_77978_p().func_74775_l("stat_0").func_74762_e("stats")).getFormattedStatName()) : ""); 
    return "Rune" + (stack.func_77942_o() ? (" of " + EnumStats.fromOrdinal(stack.func_77978_p().func_74775_l("stat_0").func_74762_e("stats")).getFormattedStatName()) : "");
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
    super.func_77624_a(stack, player, list, flag);
    List<ItemStatMutator> mutators = getMutators(stack);
    if (mutators.isEmpty()) {
      list.add("");
      list.add("Rune non initialisée");
      return;
    } 
    RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (statData == null)
      return; 
    list.add("");
    mutators.forEach(statMut -> {
          AStatCapability<Object> capa = statData.getCapabilityByName(statMut.getStatName());
          if (capa == null)
            return; 
          list.add(capa.displayMutator(statMut.getMutator("fake")) + " " + statMut.getStatName().getFormattedStatName());
        });
  }
  
  public RPGRuneItemData getItemData() {
    return this.runeItemData;
  }
  
  public IIcon getIcon() {
    return null;
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public void removeStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
    if (!(stack.func_77973_b() instanceof RPGItemRune))
      return; 
    RPGItemRune runeItem = (RPGItemRune)stack.func_77973_b();
    int tier = runeItem.getItemData().getTier();
    boolean hasTwoStats = (tier >= 1 || RANDOM.nextBoolean());
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagCompound rune1NBT = new NBTTagCompound();
    RPGRuneStat rune1 = EquipmentRuneManager.rollStats(tier);
    rune1.write(rune1NBT);
    nbt.func_74782_a("stat_0", (NBTBase)rune1NBT);
    if (hasTwoStats) {
      NBTTagCompound rune2NBT = new NBTTagCompound();
      EquipmentRuneManager.rollStats(tier, rune1.getStats(), rune1.getMutationType()).write(rune2NBT);
      nbt.func_74782_a("stat_1", (NBTBase)rune2NBT);
    } 
    stack.func_77982_d(nbt);
  }
  
  public List<ItemStatMutator> getMutators(ItemStack stack) {
    if (!stack.func_77942_o())
      return new ArrayList<>(); 
    List<ItemStatMutator> mutators = new ArrayList<>();
    NBTTagCompound nbt = stack.func_77978_p();
    if (nbt.func_74764_b("stat_0")) {
      NBTTagCompound rune1NBT = nbt.func_74775_l("stat_0");
      RPGRuneStat rune = RPGRuneStat.create();
      rune.read(rune1NBT);
      mutators.add(rune.getMutator(getItemData().getTier()));
    } 
    if (nbt.func_74764_b("stat_1")) {
      NBTTagCompound rune1NBT = nbt.func_74775_l("stat_1");
      RPGRuneStat rune = RPGRuneStat.create();
      rune.read(rune1NBT);
      mutators.add(rune.getMutator(getItemData().getTier()));
    } 
    return mutators;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemRune.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */