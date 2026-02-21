package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerWinterEvent;
import java.util.Collections;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class SummerWinterDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Questionneur";
  
  private static final String WELCOME_TEXT = "Team été ou team hiver ?";
  
  private static final String FIRST_OPTION_TEXT = "team été";
  
  private static final String SECOND_OPTION_TEXT = "team hiver";
  
  private static final int BLOCK_RADIUS = 5;
  
  public SummerWinterDialogManager() {
    super("Questionneur", Collections.singletonList("Questionneur"));
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    CustomDialog dialog = (new CustomDialog("Questionneur", "Team été ou team hiver ?")).setCloseGui().addOption("team été").addOption("team hiver").setCallback(callback -> {
          switch (callback.intValue()) {
            case 1:
              giveSummerReward(player, entity);
              break;
            case 2:
              giveWinterReward(player, entity);
              break;
          } 
        });
    return dialog;
  }
  
  public boolean sendDialog(EntityPlayerMP player, Entity entity) {
    boolean ret = super.sendDialog(player, entity);
    return ret;
  }
  
  private boolean giveWinterReward(EntityPlayerMP player, Entity entity) {
    if (!(entity instanceof fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC) || entity.field_70128_L)
      return false; 
    smoke((EntityPlayer)player, "magicCrit");
    entity.func_70106_y();
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.CHRISTMAS_TREE, 1));
    SummerWinterEvent.INSTANCE.setBlocks(new Location(entity), (EntityPlayer)player, 5, Blocks.field_150431_aC);
    return true;
  }
  
  private boolean giveSummerReward(EntityPlayerMP player, Entity entity) {
    if (!(entity instanceof fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC) || entity.field_70128_L)
      return false; 
    smoke((EntityPlayer)player, "heart");
    entity.func_70106_y();
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.BLAZE_AND_STEEL, 1));
    SummerWinterEvent.INSTANCE.setBlocks(new Location(entity), (EntityPlayer)player, 5, (Block)Blocks.field_150480_ab);
    return true;
  }
  
  private void smoke(EntityPlayer player, String name) {
    World world = player.field_70170_p;
    if (!(world instanceof WorldServer))
      return; 
    double posX = player.field_70165_t;
    double posY = player.field_70163_u;
    double posZ = player.field_70161_v;
    WorldServer worldServer = (WorldServer)world;
    worldServer.func_147487_a(name, posX, posY, posZ, 2000, 2.2D, 1.0D, 2.2D, 0.1D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\dialog\SummerWinterDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */