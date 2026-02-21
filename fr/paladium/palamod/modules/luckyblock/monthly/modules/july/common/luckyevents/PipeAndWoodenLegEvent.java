package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs.XavierDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;

public class PipeAndWoodenLegEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Pipe et jambe de bois";
  
  private static final String TEXTURE_PATH = "july/pipe_and_wooden_leg";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = false;
  
  private static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/xavier.png";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(10L);
  
  private static final String SUCCESS_MESSAGE = "Bien joué, tu as réussi à completer la fin de la chanson !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityNPC xavier = new EntityNPC(world, "Marin Xavier", "palamod:textures/entity/npc/xavier.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)xavier);
  }
  
  @SubscribeEvent
  public void onChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    XavierDialogManager manager = XavierDialogManager.getInstance();
    XavierDialogManager.IndexedDialog indexed = manager.getIndex(player);
    if (indexed == null || indexed.isDeclined())
      return; 
    String value = manager.getValueByIndex(indexed.getIndex());
    if (value == null)
      return; 
    String message = event.message;
    if (message.equalsIgnoreCase(value)) {
      indexed.increment();
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Bien joué, tu as réussi à completer la fin de la chanson !" });
    } 
  }
  
  public String getName() {
    return "Pipe et jambe de bois";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/pipe_and_wooden_leg";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\PipeAndWoodenLegEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */