package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.dialog;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import java.util.Collections;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class MaxDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "VendeurMax";
  
  private static final String WELCOME_TEXT = "Tu souhaites adopter un crabe minéral, un Taupiko où un CORRUPTION.SLAYER ?";
  
  private static final String ALREADY_HAVE_CRAB_TEXT = "Tu as déjà un Crabe Minéral. J’espère que tu es heureux avec ton nouvel ami !";
  
  private static final String ALREADY_HAVE_TAUPIKO_TEXT = "Tu as déjà un Taupiko. J’espère que tu es heureux avec ton nouvel ami !";
  
  private static final String ALREADY_HAVE_SLAYER_TEXT = "Tu as déjà un CORRUPTION.SLAYER. J’espère que tu es EMOTION:HAPPY avec ton nouvel ami !";
  
  private static final String GIVE_TEXT = "Voici ton nouvel ENTITY:FRIEND. Occupe toi bien de lui, c’est un ENTITY très FRIENDLY !";
  
  private static final String REFUSE_TEXT = "Aucun souci, c’est une lourde responsabilité que d’adopter un ENTITY:ANIMAL. Reviens me voir si un jour tu te sens prêt !";
  
  private static final String[] ANSWERS = new String[] { "Crabe Minéral", "Taupiko", "CORRUPTION.SLAYER", "Aucun" };
  
  private static final String SECOND_OPTION_TEXT = "Non";
  
  public MaxDialogManager() {
    super("VendeurMax", Collections.singletonList("VendeurMax"));
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return null; 
    return (new CustomDialog("VendeurMax", "Tu souhaites adopter un crabe minéral, un Taupiko où un CORRUPTION.SLAYER ?"))
      .setCloseGui()
      .addOptions(ANSWERS)
      .setCallback(callback -> {
          if (callback.intValue() == 1) {
            if (properties.isOwnCrab()) {
              sendOtherDialog(player, cantGiveCrabDialog(player, entity));
              return;
            } 
            giveCrab(player, properties, entity);
            sendOtherDialog(player, giveDialog(player, properties, entity));
          } else if (callback.intValue() == 2) {
            if (properties.isOwnTaupiko()) {
              sendOtherDialog(player, cantGiveTaupikoDialog(player, entity));
              return;
            } 
            giveTaupiko(player, properties, entity);
            sendOtherDialog(player, giveDialog(player, properties, entity));
          } else if (callback.intValue() == 3) {
            if (properties.isOwnSlayer()) {
              sendOtherDialog(player, cantGiveSlayerDialog(player, entity));
              return;
            } 
            giveSlayer(player, properties, entity);
            sendOtherDialog(player, giveDialog(player, properties, entity));
          } else if (callback.intValue() == 4) {
            sendOtherDialog(player, refuseDialog(player, entity));
          } 
        });
  }
  
  public CustomDialog cantGiveCrabDialog(EntityPlayerMP player, Entity entity) {
    return (new CustomDialog("VendeurMax", "Tu as déjà un Crabe Minéral. J’espère que tu es heureux avec ton nouvel ami !"))
      .setCloseGui();
  }
  
  public CustomDialog cantGiveTaupikoDialog(EntityPlayerMP player, Entity entity) {
    return (new CustomDialog("VendeurMax", "Tu as déjà un Taupiko. J’espère que tu es heureux avec ton nouvel ami !"))
      .setCloseGui();
  }
  
  public CustomDialog cantGiveSlayerDialog(EntityPlayerMP player, Entity entity) {
    return (new CustomDialog("VendeurMax", "Tu as déjà un CORRUPTION.SLAYER. J’espère que tu es EMOTION:HAPPY avec ton nouvel ami !"))
      .setCloseGui();
  }
  
  public CustomDialog giveDialog(EntityPlayerMP player, PlayerLuckyBlockProperties properties, Entity entity) {
    return (new CustomDialog("VendeurMax", "Voici ton nouvel ENTITY:FRIEND. Occupe toi bien de lui, c’est un ENTITY très FRIENDLY !"))
      .setCloseGui();
  }
  
  public CustomDialog refuseDialog(EntityPlayerMP player, Entity entity) {
    return (new CustomDialog("VendeurMax", "Aucun souci, c’est une lourde responsabilité que d’adopter un ENTITY:ANIMAL. Reviens me voir si un jour tu te sens prêt !"))
      .setCloseGui();
  }
  
  private void giveCrab(EntityPlayerMP player, PlayerLuckyBlockProperties properties, Entity entity) {
    if (!(entity instanceof noppes.npcs.entity.EntityNPCInterface) || entity.field_70128_L)
      return; 
    properties.setOwnCrab(true);
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.PRIMARY_CRAB_EGG));
  }
  
  private void giveTaupiko(EntityPlayerMP player, PlayerLuckyBlockProperties properties, Entity entity) {
    if (!(entity instanceof noppes.npcs.entity.EntityNPCInterface) || entity.field_70128_L)
      return; 
    properties.setOwnTaupiko(true);
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.PRIMARY_TAUPIKO_EGG));
  }
  
  private void giveSlayer(EntityPlayerMP player, PlayerLuckyBlockProperties properties, Entity entity) {
    if (!(entity instanceof noppes.npcs.entity.EntityNPCInterface) || entity.field_70128_L)
      return; 
    properties.setOwnSlayer(true);
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.PRIMARY_SLAYER_EGG));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\dialog\MaxDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */