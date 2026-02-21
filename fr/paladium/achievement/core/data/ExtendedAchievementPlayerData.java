package fr.paladium.achievement.core.data;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.achievement.AchievementLogger;
import fr.paladium.achievement.AchievementMod;
import fr.paladium.achievement.core.helios.ModuleAchievement;
import fr.paladium.achievement.core.packets.server.SCSyncExtendedAchievementPlayerData;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.utils.FastUUID;
import fr.paladium.achievement.server.config.AchievementRewardConfig;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtendedAchievementPlayerData implements IExtendedEntityProperties {
  public static final String PROP_NAME = "achievement_ACVMT";
  
  private Entity entity;
  
  public void setEntity(Entity entity) {
    this.entity = entity;
  }
  
  public void setAchievementStats(HashMap<String, Integer> achievementStats) {
    this.achievementStats = achievementStats;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public HashMap<String, Integer> achievementStats = new HashMap<>();
  
  public HashMap<String, Integer> getAchievementStats() {
    return this.achievementStats;
  }
  
  public static void register() {
    MinecraftForge.EVENT_BUS.register(new ExtendeAchievementPlayerDataHandler());
  }
  
  public void incrementStats(final Achievement achievement, int nb, EntityPlayer p) {
    if (!achievement.getSubAchievements().isEmpty())
      return; 
    if (!achievement.getSubAchievements().isEmpty() && achievement.getParent() == null)
      return; 
    int newValue = 0;
    boolean validate = false;
    if (getAchievementStats().containsKey(achievement.getId())) {
      int old = ((Integer)getAchievementStats().get(achievement.getId())).intValue();
      if (old >= achievement.getNbToValidate())
        return; 
      newValue = old + nb;
      if (newValue >= achievement.getNbToValidate()) {
        newValue = achievement.getNbToValidate();
        validate = true;
      } 
      getAchievementStats().put(achievement.getId(), Integer.valueOf(newValue));
    } else {
      newValue = nb;
      if (newValue >= achievement.getNbToValidate()) {
        newValue = achievement.getNbToValidate();
        validate = true;
      } 
      getAchievementStats().put(achievement.getId(), Integer.valueOf(newValue));
    } 
    if (achievement.getParent() != null) {
      validate = true;
      for (Achievement achvmts : achievement.getParent().getSubAchievements()) {
        if (getAchievementStats().containsKey(achvmts.getId())) {
          int currentStat = ((Integer)getAchievementStats().get(achvmts.getId())).intValue();
          if (currentStat < achvmts.getNbToValidate()) {
            validate = false;
            break;
          } 
          continue;
        } 
        validate = false;
      } 
    } 
    if (validate) {
      if (achievement.getParent() != null) {
        ModuleAchievement.getInstance().sendAchievementTitle((EntityPlayerMP)this.entity, achievement.getParent().getName(), 5000L);
        giveRewards((EntityPlayer)this.entity, achievement.getParent());
      } else {
        ModuleAchievement.getInstance().sendAchievementTitle((EntityPlayerMP)this.entity, achievement.getName(), 5000L);
        giveRewards((EntityPlayer)this.entity, achievement);
      } 
      AchievementMod.getServer().getApi().completeAchievement(achievement.getId(), FastUUID.toString(this.entity.func_110124_au())).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
              if (!response.isSuccessful())
                System.out.println("Failed to complete achievement " + achievement.getId() + ": " + response.code()); 
            }
            
            public void onFailure(Call<Void> call, Throwable throwable) {
              System.out.println("Failed to complete achievement " + achievement.getId() + ": " + throwable.getMessage());
            }
          });
    } 
    if (achievement instanceof fr.paladium.achievement.core.pojo.types.WalkDistanceAchievement && 
      !validate && newValue % 100 != 0)
      return; 
    dataChanged((Entity)p);
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagList nbtlist = new NBTTagList();
    for (Map.Entry<String, Integer> entry : this.achievementStats.entrySet()) {
      NBTTagCompound stat = new NBTTagCompound();
      stat.func_74778_a("id", entry.getKey());
      stat.func_74768_a("count", ((Integer)entry.getValue()).intValue());
      nbtlist.func_74742_a((NBTBase)stat);
    } 
    nbt.func_74782_a("stats", (NBTBase)nbtlist);
    compound.func_74782_a("achievement", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("achievement")) {
      NBTTagCompound nbt = (NBTTagCompound)compound.func_74781_a("achievement");
      NBTTagList nbtlist = nbt.func_150295_c("stats", 10);
      for (int i = 0; i < nbtlist.func_74745_c(); i++) {
        NBTTagCompound stat = nbtlist.func_150305_b(i);
        String id = stat.func_74779_i("id");
        int count = stat.func_74762_e("count");
        this.achievementStats.put(id, Integer.valueOf(count));
      } 
    } 
  }
  
  public void init(Entity entity, World world) {
    this.entity = entity;
  }
  
  public void dataChanged(Entity e) {
    if (e instanceof EntityPlayerMP)
      dataChanged((EntityPlayerMP)e); 
  }
  
  public void dataChanged(EntityPlayerMP player) {
    if (!player.field_70170_p.field_72995_K) {
      NBTTagCompound nbt = new NBTTagCompound();
      saveNBTData(nbt);
      AchievementMod.getProxy().getNetwork().sendTo((IMessage)new SCSyncExtendedAchievementPlayerData(nbt), player);
    } 
  }
  
  private void giveRewards(EntityPlayer player, Achievement achievement) {
    if (!(player instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP playerMP = (EntityPlayerMP)player;
    AchievementRewardConfig config = AchievementRewardConfig.get();
    if (config == null)
      return; 
    if (!config.giveRewards(playerMP, achievement.getId()))
      AchievementLogger.error("Failed to give rewards for achievement " + achievement.getId() + " to player " + playerMP.func_70005_c_()); 
  }
  
  public static ExtendedAchievementPlayerData get(Entity p) {
    return (ExtendedAchievementPlayerData)p.getExtendedProperties("achievement_ACVMT");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\data\ExtendedAchievementPlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */