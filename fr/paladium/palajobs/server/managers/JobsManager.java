package fr.paladium.palajobs.server.managers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.chronos.ChronosMod;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palajobs.PalaJobsLogger;
import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.BlackListedItem;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.boss.BossData;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import fr.paladium.palajobs.core.utils.ItemStackSerializer;
import fr.paladium.palajobs.server.api.IBossAPI;
import fr.paladium.palajobs.server.runnable.BossRunnable;
import fr.paladium.palajobs.utils.JobsUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import org.bukkit.Bukkit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JobsManager {
  private static JobsManager instance;
  
  private final List<IBlackListedItem> blackListedCrafts;
  
  private final List<IBlackListedItem> blackListedUsages;
  
  private BossData bossData;
  
  public List<IBlackListedItem> getBlackListedCrafts() {
    return this.blackListedCrafts;
  }
  
  public List<IBlackListedItem> getBlackListedUsages() {
    return this.blackListedUsages;
  }
  
  private final List<LvlTokenReward> lvlTokenReward = new ArrayList<>();
  
  private IBossAPI api;
  
  public List<LvlTokenReward> getLvlTokenReward() {
    return this.lvlTokenReward;
  }
  
  public IBossAPI getApi() {
    return this.api;
  }
  
  public JobsManager() {
    instance = this;
    this.blackListedCrafts = new ArrayList<>();
    this.blackListedUsages = new ArrayList<>();
    if (ForgeEnv.isDev()) {
      PalaJobsLogger.error("[JobsManager] BossAPI is disabled in devmode.");
    } else if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
      Retrofit retrofit = (new Retrofit.Builder()).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).baseUrl(ChronosMod.getInstance().getApiURL()).build();
      this.api = (IBossAPI)retrofit.create(IBossAPI.class);
      new BossRunnable();
    } 
  }
  
  public List<AbstractQuest> getQuests(QuestType type) {
    return (List<AbstractQuest>)QuestRegistry.getInstance().getQuests().stream().filter(q -> (q != null && type.equals(q.getType())))
      .collect(Collectors.toList());
  }
  
  public Optional<AbstractQuest> getQuest(String id) {
    return QuestRegistry.getInstance().getQuests().stream()
      .filter(quest -> (quest != null && quest.getId().equals(id))).findFirst();
  }
  
  public List<AbstractObjective<?>> getObjectives(ObjectiveType type) {
    List<AbstractObjective<?>> objectives = new ArrayList<>();
    for (AbstractJob job : JobRegistry.getInstance().getJobs())
      job.getObjectives().stream().filter(objective -> type.equals(objective.getType())).forEach(objectives::add); 
    return objectives;
  }
  
  public Optional<AbstractJob> getJobByType(JobType type) {
    return JobRegistry.getInstance().getJobs().stream().filter(j -> (j != null && type.equals(j.getType())))
      .findFirst();
  }
  
  public Optional<AbstractJob> getJobByName(String name) {
    return JobRegistry.getInstance().getJobs().stream().filter(j -> (j != null && name.equals(j.getType().getName())))
      .findFirst();
  }
  
  public IBlackListedItem canUseCraft(EntityPlayer player, JobsPlayer data, ItemStack itemStack) {
    return canUse(player, data, itemStack, true);
  }
  
  public IBlackListedItem canUseCraftEnchantment(EntityPlayer player, JobsPlayer data, ItemStack itemStack) {
    if (data == null || itemStack == null || itemStack.func_77973_b() != Items.field_151134_bR)
      return null; 
    for (IBlackListedItem blacklisted : this.blackListedCrafts) {
      if (!JobsUtils.isItemEqual(blacklisted.getStack(), itemStack))
        continue; 
      for (Enchantment enchantment : getEnchantments(itemStack)) {
        ItemStack enchantedBook = new ItemStack((Item)Items.field_151134_bR);
        ((ItemEnchantedBook)enchantedBook.func_77973_b()).func_92115_a(enchantedBook, new EnchantmentData(enchantment, enchantment
              .func_77319_d()));
        if (!ItemStack.func_77970_a(blacklisted.getStack(), enchantedBook))
          continue; 
        int level = JobExpUtils.getLevel(data.getExperience(blacklisted.getType()));
        if (level < blacklisted.getRequiredLevel())
          return blacklisted; 
      } 
    } 
    return null;
  }
  
  public IBlackListedItem canUseUseItem(EntityPlayer player, JobsPlayer data, ItemStack itemStack) {
    return canUse(player, data, itemStack, false);
  }
  
  public IBlackListedItem canUse(EntityPlayer player, JobsPlayer data, ItemStack itemStack, boolean craft) {
    if (data == null || itemStack == null)
      return null; 
    List<IBlackListedItem> listedItems = craft ? this.blackListedCrafts : this.blackListedUsages;
    for (IBlackListedItem blacklisted : listedItems) {
      if (!blacklisted.canUse(player, (IJobsPlayer)data, itemStack))
        return blacklisted; 
    } 
    return null;
  }
  
  public void registerBlackListedCraft(BlackListedItem item) {
    if (this.blackListedCrafts.contains(item))
      throw new IllegalArgumentException("This item is already blacklisted"); 
    this.blackListedCrafts.add(item);
  }
  
  public void registerBlackListedUsage(BlackListedItem item) {
    if (this.blackListedUsages.contains(item))
      throw new IllegalArgumentException("This item is already blacklisted"); 
    this.blackListedUsages.add(item);
  }
  
  public void setDoubleXpUntil(EntityPlayer player, long expiration) {
    JobsPlayer eep = JobsPlayer.get((Entity)player);
    if (eep == null)
      return; 
    eep.setDoubleXpUntil(expiration);
  }
  
  public void checkDailyQuests(EntityPlayerMP player) {
    JobsPlayer eep = JobsPlayer.get((Entity)player);
    if (eep == null)
      return; 
    eep.checkDailyQuests();
  }
  
  public BossData getBossData() {
    if (this.bossData == null)
      fetchBossData(); 
    return this.bossData;
  }
  
  public void fetchBossData() {
    try {
      this.bossData = (BossData)this.api.getBossData().execute().body();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void fetchBossDataAsync() {
    this.api.getBossData().enqueue(new Callback<BossData>() {
          public void onResponse(Call<BossData> call, Response<BossData> response) {
            JobsManager.this.bossData = (BossData)response.body();
          }
          
          public void onFailure(Call<BossData> call, Throwable error) {
            error.printStackTrace();
          }
        });
  }
  
  public static ItemStack getItemStackFromString(String str) {
    return ItemStackSerializer.read(new String(Base64.getDecoder().decode(str)));
  }
  
  public List<Enchantment> getEnchantments(ItemStack item) {
    if (item == null || item.func_77973_b() != Items.field_151134_bR)
      return new ArrayList<>(); 
    List<Enchantment> enchantments = new ArrayList<>();
    ItemEnchantedBook enchantedBook = (ItemEnchantedBook)item.func_77973_b();
    NBTTagList nbttaglist = enchantedBook.func_92110_g(item);
    if (nbttaglist != null)
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        short short1 = nbttaglist.func_150305_b(i).func_74765_d("id");
        if (Enchantment.field_77331_b[short1] != null)
          enchantments.add(Enchantment.field_77331_b[short1]); 
      }  
    return enchantments;
  }
  
  public static boolean hasBukkitPermission(UUID uuid, String permission) {
    try {
      return Bukkit.getPlayer(uuid).hasPermission(permission);
    } catch (Exception e) {
      return true;
    } 
  }
  
  public static JobsManager getInstance() {
    if (instance == null)
      new JobsManager(); 
    return instance;
  }
  
  public void generateLvlTokenRewards(LvlToken lvlToken) {
    lvlToken.getPendingRewards().clear();
    List<LvlTokenReward> rewards = LvlTokenRegistry.generateRewards(lvlToken.getLvl(), lvlToken.getJobType());
    for (LvlTokenReward reward : rewards)
      lvlToken.getPendingRewards().add(reward.id); 
  }
  
  public void giveLvlTokenRewards(final EntityPlayerMP playerEntity, String rewardId) {
    int idx = LvlTokenRegistry.lvlTokenRewards.indexOf(new LvlTokenReward(rewardId));
    if (idx != -1) {
      JobsPlayer eep;
      final LvlTokenReward reward = LvlTokenRegistry.lvlTokenRewards.get(idx);
      switch (reward.category) {
        case ITEM:
          if (reward.itemStack != null)
            InventoryUtils.addItem((EntityPlayer)playerEntity, reward.itemStack, reward.quantity); 
          break;
        case MONEY:
          CresusManager.getInstance().depositPlayerAsync(playerEntity.func_110124_au(), reward.quantity, "[Jobs Token Reward] " + reward.quantity, new CresusCallback<CresusResponse>() {
                public void onSuccess(CresusResponse arg0) {
                  (new Notification(Notification.NotificationType.SUCCESS, "+ " + reward.quantity + "$", "paladium")).send(playerEntity);
                }
                
                public void onFail(CresusResponse arg0, Throwable arg1) {}
              });
          break;
        case XP:
          playerEntity.func_71023_q(reward.quantity);
          break;
        case XP_JOBS:
          eep = JobsPlayer.get((Entity)playerEntity);
          if (eep != null)
            eep.addXp(reward.job, ObjectiveType.FISH, reward.quantity, (EntityPlayer)playerEntity); 
          break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\managers\JobsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */