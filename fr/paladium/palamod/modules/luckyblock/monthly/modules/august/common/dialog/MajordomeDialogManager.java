package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SandCastleLifeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.MajordomeData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.majordome.MajordomeRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class MajordomeDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Majordome";
  
  private static final String REWARD_HEAD_USERNAME = "Elysio__";
  
  public static final String FAKE_NAME = "Majordome_FAKE";
  
  private static final String WELCOME_TEXT = "Bonjour. Quel service souhaitez-vous que je rende aujourd’hui?";
  
  private static final String FIRST_OPTION_TEXT = "Je souhaiterais obtenir une tête rare";
  
  private static final String SECOND_OPTION_TEXT = "Je souhaiterais obtenir 8 blocs de Paladium";
  
  private static final String THIRD_OPTION_TEXT = "Je souhaiterais obtenir 2 lucky block de juin";
  
  private static final String FOURTH_OPTION_TEXT = "Je souhaiterais obtenir 2 lucky block de juillet";
  
  public MajordomeDialogManager() {
    super("Majordome", Collections.singletonList("Majordome"));
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = (new CustomDialog("Majordome", "Bonjour. Quel service souhaitez-vous que je rende aujourd’hui?")).setCloseGui().addOption("Je souhaiterais obtenir une tête rare").addOption("Je souhaiterais obtenir 8 blocs de Paladium").addOption("Je souhaiterais obtenir 2 lucky block de juin").addOption("Je souhaiterais obtenir 2 lucky block de juillet").setCallback(callback -> {
          switch (callback.intValue()) {
            case 1:
              giveReward(player, entity, MonthlyUtils.getHead("Elysio__"));
              break;
            case 2:
              giveReward(player, entity, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 8));
              break;
            case 3:
              giveReward(player, entity, new ItemStack((Block)BlocksRegister.JUNE_LUCKY_BLOCK, 2));
              break;
            case 4:
              giveReward(player, entity, new ItemStack(BlocksRegister.JULY_LUCKY_BLOCK, 2));
              break;
          } 
        });
    return dialog;
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity entity) {
    boolean ret = super.sendDialog(player, entity);
    return ret;
  }
  
  private boolean giveReward(EntityPlayerMP player, Entity entity, ItemStack stack) {
    if (!(entity instanceof fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC) || entity.field_70128_L)
      return false; 
    smoke((EntityPlayer)player);
    entity.func_70106_y();
    Timer timer = new Timer();
    MajordomeData data = SandCastleLifeEvent.INSTANCE.get(entity.func_110124_au());
    timer.schedule((TimerTask)new MajordomeRunnable(entity.func_110124_au(), stack, data.getLocation()), 5000L);
    return true;
  }
  
  private void smoke(EntityPlayer player) {
    World world = player.field_70170_p;
    if (!(world instanceof WorldServer))
      return; 
    double posX = player.field_70165_t;
    double posY = player.field_70163_u;
    double posZ = player.field_70161_v;
    WorldServer worldServer = (WorldServer)world;
    worldServer.func_147487_a("explode", posX, posY, posZ, 2000, 2.2D, 1.0D, 2.2D, 0.1D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\dialog\MajordomeDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */