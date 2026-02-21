package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.EnglishResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.EnglishTestData;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ServerChatEvent;

public class EnglishTestEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Test d'anglais";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "september/english_test";
  
  public static final int CUBOID_RADIUS = 5;
  
  public static final String SCHEMATIC_NAME = "lb09_anglais.schematic";
  
  public static final long SCHEMATIC_DURATION = TimeUnit.MINUTES.toMillis(30L);
  
  public static final String CANT_LEFT_MESSAGE = "&cVous ne pouvez pas quitter la zone de test d'anglais ! &6Répondez aux questions où écrivez un message pour quitter le test.";
  
  public static final String START_MESSAGE = "&aLe test d’anglais va commencer !";
  
  public static final String WIN_MESSAGE = "&aBravo, tu as réussi le test d’Anglais. Heureusement car il était super facile ;)";
  
  public static final String FAIL_MESSAGE = "&cVu que tu ne parles pas bien Anglais tu vas apprendre directement en jeu !";
  
  public static EnglishTestEvent INSTANCE;
  
  private List<EnglishResponse> responses;
  
  private List<EnglishTestData> datas;
  
  private Cuboid classCuboid;
  
  private Location classLocation;
  
  public List<EnglishResponse> getResponses() {
    return this.responses;
  }
  
  public List<EnglishTestData> getDatas() {
    return this.datas;
  }
  
  public EnglishTestEvent() {
    INSTANCE = this;
    this.datas = new ArrayList<>();
    this.responses = Arrays.asList(new EnglishResponse[] { EnglishResponse.builder()
          .question("Conjugue le verbe être à la troisième personne : he...")
          .responses(Collections.singletonList("is"))
          .build(), 
          EnglishResponse.builder()
          .question("Traduis le mot 'chat': ...")
          .responses(Collections.singletonList("cat"))
          .build(), 
          EnglishResponse.builder()
          .question("Traduis le mot 'trois': ...")
          .responses(Collections.singletonList("three"))
          .build(), 
          EnglishResponse.builder()
          .question("Traduis en Anglais la phrase suivante: 'Je vis en France': ...")
          .responses(Collections.singletonList("I live in France"))
          .build(), 
          EnglishResponse.builder()
          .question("Traduis en Français la phrase suivante: 'I'm ten years old': ...")
          .responses(Arrays.asList(new String[] { "J'ai dix ans", "J'ai 10 ans" })).build() });
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EnglishTestData data = new EnglishTestData(player);
    this.datas.add(data);
  }
  
  @SubscribeEvent
  public void onPlayerChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    EnglishTestData data = get(player);
    if (data == null || data.hasFinished())
      return; 
    event.setCanceled(true);
    data.handleResponse(player, event.message);
  }
  
  public EnglishTestData get(EntityPlayerMP player) {
    return this.datas.stream()
      .filter(data -> data.getPlayerUniqueId().equals(player.func_110124_au()))
      .findFirst()
      .orElse(null);
  }
  
  public String getName() {
    return "Test d'anglais";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "september/english_test";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\EnglishTestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */