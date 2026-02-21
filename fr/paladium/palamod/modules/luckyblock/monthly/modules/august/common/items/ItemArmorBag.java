package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemArmorBag extends Item implements ITooltipInformations {
  public static final String NAME = "armor_bag";
  
  public static final String DESCRIPTION = "Peut contenir 1 casque, 1 plastron, une paire de jambière et une paire de bottes. Il suffit d’effectuer un clic droit sur l’objet pour que l’armure équipée aille dans le sac à armure et que l’armure enregistrée soit instantanément équipée. Inutilisable en combat";
  
  private static final String EMPTY_BAG_AND_INVENTORY_MESSAGE = "&cCe sac est vide et vous n'avez pas d'armure sur vous !";
  
  private static final String BAG_EXCHANGE_SUCCESS_MESSAGE = "&aVous avez équipé votre sac d'armure !";
  
  private static final String BAG_STORING_SUCCESS_MESSAGE = "&aVous avez stocké votre armure dans votre sac !";
  
  private static final String NO_EQUIPPED_ARMOR_MESSAGE = "&cVous n'avez pas d'armure équipée !";
  
  public ItemArmorBag() {
    func_77655_b("armor_bag");
    func_111206_d("palamod:armor_bag");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    NBTTagCompound compound = itemStack.func_77978_p();
    List<ItemStack> armors = getArmors(player);
    List<ItemStack> armorsFromNBT = getArmorsFromNBT(compound);
    boolean isNBTFullArmor = isFullArmor(armorsFromNBT);
    if (!isFullArmor(armors) && !isNBTFullArmor) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe sac est vide et vous n'avez pas d'armure sur vous !" });
      return itemStack;
    } 
    if (armors.size() == 0) {
      if (!isNBTFullArmor) {
        MonthlyUtils.sendMessage(player, new String[] { "&cCe sac est vide et vous n'avez pas d'armure sur vous !" });
        return itemStack;
      } 
      for (int j = 0; j < player.field_71071_by.field_70460_b.length; j++)
        player.field_71071_by.field_70460_b[j] = armorsFromNBT.get(j); 
      player.field_71069_bz.func_75142_b();
      MonthlyUtils.sendMessage(player, new String[] { "&aVous avez équipé votre sac d'armure !" });
      itemStack.func_77982_d(null);
      return itemStack;
    } 
    if (!isFullArmor(armors)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous n'avez pas d'armure équipée !" });
      return itemStack;
    } 
    for (int i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
      player.field_71071_by.field_70460_b[i] = null;
      if (!armorsFromNBT.isEmpty())
        player.field_71071_by.field_70460_b[i] = armorsFromNBT.get(i); 
    } 
    player.field_71069_bz.func_75142_b();
    setArmorsToNBT(compound, itemStack, armors);
    if (armorsFromNBT.isEmpty()) {
      MonthlyUtils.sendMessage(player, new String[] { "&aVous avez stocké votre armure dans votre sac !" });
    } else {
      MonthlyUtils.sendMessage(player, new String[] { "&aVous avez équipé votre sac d'armure !" });
    } 
    return itemStack;
  }
  
  private void setArmorsToNBT(NBTTagCompound compound, ItemStack stack, List<ItemStack> armors) {
    if (compound == null)
      compound = new NBTTagCompound(); 
    NBTTagList tagList = new NBTTagList();
    for (int i = 0; i < armors.size(); i++) {
      NBTTagCompound tag = new NBTTagCompound();
      tag.func_74774_a("Slot", (byte)i);
      ((ItemStack)armors.get(i)).func_77955_b(tag);
      tagList.func_74742_a((NBTBase)tag);
    } 
    compound.func_74782_a("Items", (NBTBase)tagList);
    stack.func_77982_d(compound);
  }
  
  private List<ItemStack> getArmorsFromNBT(NBTTagCompound compound) {
    List<ItemStack> armors = new ArrayList<>();
    if (compound == null)
      return armors; 
    NBTTagList tagList = compound.func_150295_c("Items", 10);
    if (tagList == null || tagList.func_74745_c() == 0)
      return armors; 
    if (tagList.func_74745_c() < 4) {
      compound.func_82580_o("Items");
      return armors;
    } 
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      int j = tag.func_74771_c("Slot") & 0xFF;
      if (j >= 0 && j < 4) {
        ItemStack stack = ItemStack.func_77949_a(tag);
        if (stack != null)
          armors.add(stack); 
      } 
    } 
    return armors;
  }
  
  private boolean isFullArmor(List<ItemStack> armors) {
    return (armors.size() == 4);
  }
  
  private List<ItemStack> getArmors(EntityPlayer player) {
    List<ItemStack> armors = new ArrayList<>();
    for (ItemStack armor : player.field_71071_by.field_70460_b) {
      if (armor != null && armor.func_77973_b() instanceof net.minecraft.item.ItemArmor)
        armors.add(armor); 
    } 
    return armors;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Peut contenir 1 casque, 1 plastron, une paire de jambière et une paire de bottes. Il suffit d’effectuer un clic droit sur l’objet pour que l’armure équipée aille dans le sac à armure et que l’armure enregistrée soit instantanément équipée. Inutilisable en combat");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemArmorBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */