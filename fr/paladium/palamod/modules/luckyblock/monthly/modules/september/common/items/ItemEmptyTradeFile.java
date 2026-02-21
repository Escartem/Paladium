package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemEmptyTradeFile extends Item implements ITooltipInformations {
  public static final String NAME = "empty_trade_file";
  
  public static final String DESCRIPTION = "Peut être utilisé en effectuant un clic droit sur un joueur pour obtenir ses statistiques sur le métier";
  
  public static final String NBT_TARGET_NAME_TAG = "targetName";
  
  public static final String NBT_CREATION_MILLIS_TAG = "creationMillis";
  
  public static final String NBT_ALCHEMIST_EXPERIENCE_TAG = "alchemistExperience";
  
  public static final String NBT_MINER_EXPERIENCE_TAG = "minerExperience";
  
  public static final String NBT_HUNTER_EXPERIENCE_TAG = "hunterExperience";
  
  public static final String NBT_FARMER_EXPERIENCE_TAG = "farmerExperience";
  
  public ItemEmptyTradeFile() {
    func_77655_b("empty_trade_file");
    func_111206_d("palamod:empty_trade_file");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    return stack;
  }
  
  public ItemStack createTradeFile(EntityPlayerMP me, EntityPlayerMP other, ItemStack currentItem) {
    MonthlyUtils.decrementCurrentItem((EntityPlayer)me, currentItem);
    ItemStack created = new ItemStack(ItemsRegister.TRADE_FILE);
    NBTTagCompound tag = new NBTTagCompound();
    created.func_77982_d(tag);
    tag.func_74778_a("targetName", other.getDisplayName());
    tag.func_74772_a("creationMillis", System.currentTimeMillis());
    JobsPlayer targetData = JobsPlayer.get((Entity)other);
    if (targetData == null) {
      tag.func_74780_a("alchemistExperience", 0.0D);
      tag.func_74780_a("minerExperience", 0.0D);
      tag.func_74780_a("hunterExperience", 0.0D);
      tag.func_74780_a("farmerExperience", 0.0D);
      return created;
    } 
    tag.func_74780_a("alchemistExperience", targetData.getExperience(JobType.ALCHEMIST));
    tag.func_74780_a("minerExperience", targetData.getExperience(JobType.MINER));
    tag.func_74780_a("hunterExperience", targetData.getExperience(JobType.HUNTER));
    tag.func_74780_a("farmerExperience", targetData.getExperience(JobType.FARMER));
    return created;
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Peut être utilisé en effectuant un clic droit sur un joueur pour obtenir ses statistiques sur le métier");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemEmptyTradeFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */