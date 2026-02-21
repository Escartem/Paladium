package fr.paladium.palamod.modules.ajobs.common.manager;

import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type.AlchemistRegisterer;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type.FarmerRegisterer;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type.HunterRegisterer;
import fr.paladium.palamod.modules.ajobs.common.registerer.jobs.type.MinerRegisterer;
import fr.paladium.palamod.modules.ajobs.common.registerer.quest.QuestRegisterer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class JobsManager {
  private static JobsManager instance;
  
  public JobsManager() {
    instance = this;
  }
  
  public void register() {
    clearOldBlacklists();
    registerJobs();
    registerQuests();
  }
  
  public void registerQuests() {
    QuestRegistry.getInstance().getQuests().clear();
    QuestRegisterer.getInstance().registerQuests();
  }
  
  public void registerJobs() {
    JobRegistry registry = JobRegistry.getInstance();
    (new FarmerRegisterer(registry.getFarmerJob())).register();
    (new HunterRegisterer(registry.getHunterJob())).register();
    (new AlchemistRegisterer(registry.getAlchemistJob())).register();
    (new MinerRegisterer(registry.getMinerJob())).register();
    List<String> hover2 = new ArrayList<>();
    hover2.add("§6Utilisation de la commande");
    hover2.add("§a/nether");
    ItemStack netherCommandItem = new ItemStack(Items.field_151008_G);
    netherCommandItem.func_151001_c("§6Commande Nether");
    registry.getMinerJob().addReward(Integer.valueOf(3), new IReward[] { (IReward)Reward.builder().type(RewardType.USAGE).level(3).logo(netherCommandItem).hover(hover2).build() });
    List<String> hover3 = new ArrayList<>();
    hover3.add("§6Dimension du mineur");
    hover3.add("§aDébloque l'accès à la dimension du mineur");
    ItemStack minerDimensionItem = new ItemStack(Items.field_151008_G);
    minerDimensionItem.field_77990_d = new NBTTagCompound();
    minerDimensionItem.field_77990_d.func_74778_a("url", "https://pictures.paladium-pvp.fr/job/miner/miner_dimension.png");
    registry.getMinerJob().addReward(Integer.valueOf(18), new IReward[] { (IReward)Reward.builder().type(RewardType.USAGE).level(18).logo(minerDimensionItem).hover(hover3).build() });
    List<String> hover1 = new ArrayList<>();
    hover1.add("§5Enchanteur omniscient");
    hover1.add("§7Capacité de connaître les enchantements à l'avance.");
    ItemStack itemstack1 = new ItemStack((Item)Items.field_151134_bR);
    itemstack1.field_77990_d = new NBTTagCompound();
    itemstack1.field_77990_d.func_74778_a("url", "https://i.imgur.com/OMV1DnL.png");
    registry.getAlchemistJob().addReward(Integer.valueOf(15), new IReward[] { (IReward)Reward.builder().type(RewardType.USAGE).level(15).logo(itemstack1).hover(hover1).build() });
  }
  
  public void clearOldBlacklists() {
    fr.paladium.palajobs.server.managers.JobsManager manager = fr.paladium.palajobs.server.managers.JobsManager.getInstance();
    manager.getBlackListedCrafts().clear();
    manager.getBlackListedUsages().clear();
  }
  
  public static JobsManager getInstance() {
    if (instance == null)
      instance = new JobsManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\manager\JobsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */