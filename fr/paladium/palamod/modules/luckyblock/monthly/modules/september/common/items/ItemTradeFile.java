package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palacore.utils.misc.DateUtils;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemTradeFile extends Item implements ITooltipInformations {
  public static final String NAME = "trade_file";
  
  public static final String DESCRIPTION = "Peut être utilisé en effectuant un clic droit sur un joueur pour obtenir ses statistiques sur le métier ";
  
  public static final String NON_VALID_FILE = "&cCe dossier de métier est imcomplet, il ne peut pas être utilisé.";
  
  public ItemTradeFile() {
    func_77655_b("trade_file");
    func_111206_d("palamod:trade_file");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    NBTTagCompound compound = stack.func_77978_p();
    if (!isValidFile(compound)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe dossier de métier est imcomplet, il ne peut pas être utilisé." });
      return stack;
    } 
    sendData((EntityPlayerMP)player, compound);
    return stack;
  }
  
  private void sendData(EntityPlayerMP player, NBTTagCompound compound) {
    String date = DateUtils.format(compound.func_74763_f("creationMillis"));
    int AlchemistExperience = (int)compound.func_74769_h("alchemistExperience");
    int MinerExperience = (int)compound.func_74769_h("minerExperience");
    int HunterExperience = (int)compound.func_74769_h("hunterExperience");
    int FarmerExperience = (int)compound.func_74769_h("farmerExperience");
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&6Dossier de métier de &c" + compound
          
          .func_74779_i("targetName") + " &6créé le &c" + date, "&7&m----------------------------------------", "&7 - &aAlchimiste&7: &6" + AlchemistExperience + " EXP &7- &eNiveau &c" + 
          
          JobExpUtils.getLevel(AlchemistExperience), "&7 - &aMineur&7: &6" + MinerExperience + " EXP &7- &eNiveau &c" + 
          JobExpUtils.getLevel(MinerExperience), "&7 - &aChasseur&7: &6" + HunterExperience + " EXP &7- &eNiveau &c" + 
          JobExpUtils.getLevel(HunterExperience), "&7 - &aFermier&7: &6" + FarmerExperience + " EXP &7- &eNiveau &c" + 
          JobExpUtils.getLevel(FarmerExperience), "&7&m----------------------------------------" });
  }
  
  private boolean isValidFile(NBTTagCompound compound) {
    if (compound == null)
      return false; 
    return (compound.func_74764_b("targetName") && compound.func_74764_b("creationMillis") && compound
      .func_74764_b("alchemistExperience") && compound.func_74764_b("minerExperience") && compound
      .func_74764_b("hunterExperience") && compound.func_74764_b("farmerExperience"));
  }
  
  public String[] getInformations(ItemStack arg0, EntityPlayer arg1, boolean arg2) {
    return MonthlyUtils.splitDescription("Peut être utilisé en effectuant un clic droit sur un joueur pour obtenir ses statistiques sur le métier ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemTradeFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */