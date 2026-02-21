package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.PirateTalker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ServerChatEvent;

public class TalkLikePirateEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Parler comme un pirate, ce n’est pas fait pour tout le monde !";
  
  private static final String TEXTURE_PATH = "july/talk_like_pirate";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = true;
  
  private static final int MINIMUM_MESSAGE_SENT = 5;
  
  private static final List<String> REPLACEMENT_LIST = Arrays.asList(new String[] { "Je suis un véritable Bachi-bouzouk !", "Vu mon expérience, je peux dire que je suis un vrai marin d'eau douce !", "Je suis un délicieux gibier de potence !", "On dit de moi que suis un vrai gredin, quel joli compliment !", "Selon mon capitaine je suis le meilleur laveur de pont qu’il ait vu ! Par contre niveau piraterie…", "Dès que je vois un ennemi je baisse pavillon, comme un vrai pirate !" });
  
  private static final Map<UUID, PirateTalker> PIRATE_TALKER_MAP = new HashMap<>();
  
  private static final long MALEDICTION_DURATION_MILLIS = TimeUnit.MINUTES.toMillis(10L);
  
  private static final String MALEDICTION_MESSAGE = "Vous avez été maudit par un pirate, essayez donc d'insérer un message dans le chat !";
  
  private static final String HEALED_MESSAGE = "Vous avez été guéri de votre malédiction !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PIRATE_TALKER_MAP.put(player.func_110124_au(), new PirateTalker(MALEDICTION_DURATION_MILLIS));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez été maudit par un pirate, essayez donc d'insérer un message dans le chat !" });
  }
  
  @SubscribeEvent
  public void onPlayerChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    if (player.field_70170_p.field_72995_K)
      return; 
    PirateTalker talker = getPirateTalker(player);
    if (talker == null)
      return; 
    if (talker.getMessageSent() < 5)
      try {
        Field messageField = ServerChatEvent.class.getDeclaredField("message");
        messageField.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(messageField, messageField.getModifiers() & 0xFFFFFFEF);
        messageField.set(event, pickRandomReplacement());
        talker.increment();
      } catch (NoSuchFieldException|IllegalAccessException noSuchFieldException) {} 
  }
  
  public String getName() {
    return "Parler comme un pirate, ce n’est pas fait pour tout le monde !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/talk_like_pirate";
  }
  
  private String pickRandomReplacement() {
    return REPLACEMENT_LIST.get((new Random()).nextInt(REPLACEMENT_LIST.size()));
  }
  
  private PirateTalker getPirateTalker(EntityPlayerMP player) {
    PirateTalker talker = PIRATE_TALKER_MAP.get(player.func_110124_au());
    if (talker == null)
      return null; 
    if (isExpired(talker)) {
      PIRATE_TALKER_MAP.remove(player.func_110124_au());
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez été guéri de votre malédiction !" });
      return null;
    } 
    if (talker.getMessageSent() >= 5) {
      PIRATE_TALKER_MAP.remove(player.func_110124_au());
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez été guéri de votre malédiction !" });
      return null;
    } 
    return talker;
  }
  
  private boolean isExpired(PirateTalker talker) {
    return (talker.getExpirationMillis() <= System.currentTimeMillis());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\TalkLikePirateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */