package fr.paladium.achievement.core;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.AchievementLogger;
import fr.paladium.achievement.AchievementMod;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.pojo.types.CommandAchievement;
import fr.paladium.achievement.core.pojo.types.MultiAchievement;
import fr.paladium.achievement.core.pojo.types.WalkDistanceAchievement;
import fr.paladium.achievement.core.proxy.CommonProxy;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchievementRegistry {
  public static AchievementRegistry instance;
  
  private final List<Achievement> achievements = Lists.newArrayList();
  
  public List<Achievement> getAchievements() {
    return this.achievements;
  }
  
  public void registerAchievements() {
    if (!ForgeEnv.isDev())
      return; 
    WalkDistanceAchievement.builder()
      .id("core.command.tellrawdaad")
      .category(AchievementCategory.HOW_TO_START)
      .name("Tellraw I")
      .description("Créer un home avec /tellraw")
      .nbToValidate(10000)
      .icon(CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151015_O)))
      .build()
      .register();
    CommandAchievement.builder()
      .id("core.command.home")
      .category(AchievementCategory.HOW_TO_START)
      .name("Home II")
      .description("Se téléporter à son home avec /home")
      .nbToValidate(1)
      .command("home")
      .icon(CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151135_aq)))
      .build()
      .register();
    CommandAchievement.builder()
      .id("core.command.achievement")
      .category(AchievementCategory.HOW_TO_START)
      .name("Achievements I")
      .description("Consulter ses achievements avec /achievements")
      .nbToValidate(1)
      .command("achievement")
      .icon(CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151059_bz)))
      .build()
      .register();
    CommandAchievement.builder()
      .id("core.command.achievement")
      .category(AchievementCategory.HOW_TO_START)
      .name("Achievements I")
      .description("Consulter ses achievements avec /achievements")
      .nbToValidate(1)
      .command("achievement")
      .icon(CommonProxy.getBase64FromItemStack(new ItemStack(Items.field_151049_t)))
      .build()
      .register();
    CommandAchievement defaultCommand1 = CommandAchievement.builder().id("core.command.seed").category(AchievementCategory.HOW_TO_START).name("Default I").description("Consulter ses seed avec /seed").nbToValidate(1).command("seed").build();
    CommandAchievement defaultCommand2 = CommandAchievement.builder().id("core.command.me2").category(AchievementCategory.HOW_TO_START).name("Default II").description("Consulter ses me avec /me").nbToValidate(1).command("me").build();
    CommandAchievement defaultCommand3 = CommandAchievement.builder().id("core.command.say").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /say").nbToValidate(1).command("say").build();
    CommandAchievement defaultCommand4 = CommandAchievement.builder().id("core.command.tellraw").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /tellraw").nbToValidate(1).command("tellraw").build();
    CommandAchievement defaultCommand11 = CommandAchievement.builder().id("core.command.seed1111").category(AchievementCategory.HOW_TO_START).name("Default I").description("Consulter ses seed avec /seed").nbToValidate(1).command("seed").build();
    CommandAchievement defaultCommand2111 = CommandAchievement.builder().id("core.command.me2").category(AchievementCategory.HOW_TO_START).name("Default II").description("Consulter ses me avec /me").nbToValidate(1).command("me").build();
    CommandAchievement defaultCommand1113 = CommandAchievement.builder().id("core.command.say").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /say").nbToValidate(1).command("say").build();
    CommandAchievement defaultCommand41111 = CommandAchievement.builder().id("core.command.tellraw").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /tellraw").nbToValidate(1).command("tellraw").build();
    CommandAchievement defaultCommanda = CommandAchievement.builder().id("core.command.tellraw").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /tellraw").nbToValidate(1).command("tellraw").build();
    CommandAchievement.builder()
      .id("core.command.tellrawaaa")
      .category(AchievementCategory.HOW_TO_START)
      .name("Default III")
      .description("Consulter ses say avec /tellraw")
      .nbToValidate(1000000)
      .command("tellraw")
      .build().register();
    CommandAchievement.builder()
      .id("core.command.tellrawddqsd")
      .category(AchievementCategory.HOW_TO_START)
      .name("Default III")
      .description("Consulter ses say avec /tellraw")
      .nbToValidate(1000000000)
      .command("tellraw")
      .build().register();
    CommandAchievement defaultCommandd = CommandAchievement.builder().id("core.command.tellraw").category(AchievementCategory.HOW_TO_START).name("Default III").description("Consulter ses say avec /tellraw").nbToValidate(1).command("tellraw").build();
    MultiAchievement.builder()
      .id("core.command.default")
      .category(AchievementCategory.HOW_TO_START)
      .name("Commands I")
      .description("Faire les commandes: /seed /me /say")
      .build()
      .addSubAchievement((Achievement)defaultCommand1)
      .addSubAchievement((Achievement)defaultCommand2)
      .addSubAchievement((Achievement)defaultCommand3)
      .addSubAchievement((Achievement)defaultCommand4)
      .addSubAchievement((Achievement)defaultCommand11)
      .addSubAchievement((Achievement)defaultCommand2111)
      .addSubAchievement((Achievement)defaultCommand1113)
      .addSubAchievement((Achievement)defaultCommand41111)
      .addSubAchievement((Achievement)defaultCommanda)
      .addSubAchievement((Achievement)defaultCommandd)
      .register();
    MultiAchievement.builder()
      .id("core.command.defaultAA")
      .category(AchievementCategory.HOW_TO_START)
      .name("Commands I")
      .description("Faire les commandes: /seed /me /say")
      .build()
      .addSubAchievement((Achievement)defaultCommand1)
      .addSubAchievement((Achievement)defaultCommand2)
      .addSubAchievement((Achievement)defaultCommand3)
      .addSubAchievement((Achievement)defaultCommand4)
      .addSubAchievement((Achievement)defaultCommand11)
      .addSubAchievement((Achievement)defaultCommand2111)
      .register();
  }
  
  public static void register(final Achievement achievement) {
    if (instance.achievements.contains(achievement)) {
      AchievementLogger.error("The Achievement with id " + achievement.getId() + " is already registered.");
      return;
    } 
    instance.achievements.add(achievement);
    if (FMLCommonHandler.instance().getSide() == Side.SERVER && !ForgeEnv.isDev() && AchievementMod.getServer().getApi() != null)
      AchievementMod.getServer().getApi().publishAchievement(achievement).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
              if (!response.isSuccessful())
                AchievementLogger.error("Failed to publish achievement " + achievement.getId() + ": " + response.code()); 
            }
            
            public void onFailure(Call<Void> call, Throwable throwable) {
              AchievementLogger.error("Failed to publish achievement " + achievement.getId());
              throwable.printStackTrace();
            }
          }); 
  }
  
  public static AchievementRegistry getInstance() {
    if (instance == null)
      instance = new AchievementRegistry(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\AchievementRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */