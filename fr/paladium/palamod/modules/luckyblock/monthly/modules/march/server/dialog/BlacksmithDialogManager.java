package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.DialogData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BlacksmithDialogManager extends AbstractDialogManager {
  private static BlacksmithDialogManager instance;
  
  private static final String NPC_NAME = "Forgeron";
  
  public static BlacksmithDialogManager getInstance() {
    return instance;
  }
  
  private static final DialogData UPGRADE_DIALOG = DialogData.builder()
    .dialog("Je peux améliorer un de ces objets, lequel souhaites-tu que j'améliore ?")
    .answers(new String[] { "Pile galactique", "Cloche magique", "Pierre coeur", "Aucun de ces objets" }).build();
  
  private static final String NO_GALACTIC_BATTERY_MESSAGE = "Tu n'as pas de pile galactique sur toi. Revient me voir quand tu en auras une que je puisse améliorer !";
  
  private static final String NO_MAGIC_BELL_MESSAGE = "Tu n'as pas de cloche magique sur toi. Revient me voir quand tu en auras une que je puisse améliorer !";
  
  private static final String UPGRADED_MESSAGE = "C'est tout ce que je peux améliorer pour le moment. Reviens me voir si tu souhaites que je t'améliore l'un de ces objets !";
  
  private static final String ALREADY_UPGRADED_MESSAGE = "J'ai déjà amélioré cet objet pour toi. Essaie donc avec un autre objet !";
  
  private static final String NO_STONE_HEARTH_MESSAGE = "Tu n'as pas de coeur pierre sur toi. Revient me voir quand tu en auras une que je puisse améliorer !";
  
  public static final double BLACKSMITH_X = 4885.0D;
  
  public static final double BLACKSMITH_Y = 76.0D;
  
  public static final double BLACKSMITH_Z = 4947.0D;
  
  public static final double UPGRADE_X = 4885.0D;
  
  public static final double UPGRADE_Y = 77.0D;
  
  public static final double UPGRADE_Z = 4941.0D;
  
  public BlacksmithDialogManager() {
    super("Forgeron");
    instance = this;
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    MarchPlayer marchPlayer = MarchPlayer.get((EntityPlayer)player);
    if (marchPlayer == null)
      return null; 
    if (marchPlayer.isHasStoneHearth() && marchPlayer.isHasUpgradedBell() && marchPlayer.isHasUpgradedItems())
      return CustomDialog.of(this, "J'ai déjà amelioré tous tes items, reviens me voir le mois prochain pour que je t'en améliore d'autres !")
        .addOption("OK").setCallback(c -> {
          
          }); 
    return CustomDialog.of(this, UPGRADE_DIALOG.getDialog())
      .addOptions(UPGRADE_DIALOG.getAnswers())
      .setCallback(c -> {
          switch (c.intValue()) {
            case 1:
              if (marchPlayer.isHasUpgradedItems()) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "J'ai déjà amélioré cet objet pour toi. Essaie donc avec un autre objet !" });
                return;
              } 
              if (!hasRequired(player, ItemsRegister.GALACTIC_PILE)) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu n'as pas de pile galactique sur toi. Revient me voir quand tu en auras une que je puisse améliorer !" });
                return;
              } 
              if (upgrade(player, ItemsRegister.MARCH_TICKET, ItemsRegister.GALACTIC_PILE, ItemsRegister.ENDIUM_BATTERY))
                marchPlayer.setHasUpgradedItems(true); 
              break;
            case 2:
              if (marchPlayer.isHasUpgradedBell()) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "J'ai déjà amélioré cet objet pour toi. Essaie donc avec un autre objet !" });
                return;
              } 
              if (!hasRequired(player, Item.func_150898_a(BlocksRegister.MAGIC_BELL))) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu n'as pas de cloche magique sur toi. Revient me voir quand tu en auras une que je puisse améliorer !" });
                return;
              } 
              if (upgrade(player, ItemsRegister.EASTER_TICKET, Item.func_150898_a(BlocksRegister.MAGIC_BELL), Item.func_150898_a(BlocksRegister.ENDIUM_MAGIC_BELL)))
                marchPlayer.setHasUpgradedBell(true); 
              break;
            case 3:
              if (marchPlayer.isHasStoneHearth()) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "J'ai déjà amélioré cet objet pour toi. Essaie donc avec un autre objet !" });
                return;
              } 
              if (!hasRequired(player, Item.func_150898_a(BlocksRegister.STONE_HEARTH))) {
                MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu n'as pas de coeur pierre sur toi. Revient me voir quand tu en auras une que je puisse améliorer !" });
                return;
              } 
              if (upgrade(player, ItemsRegister.MAY_TICKET_BLACKSMITH, Item.func_150898_a(BlocksRegister.STONE_HEARTH), Item.func_150898_a(BlocksRegister.ENDIUM_HEARTH)))
                marchPlayer.setHasStoneHearth(true); 
              break;
            case 4:
              MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "C'est tout ce que je peux améliorer pour le moment. Reviens me voir si tu souhaites que je t'améliore l'un de ces objets !" });
              break;
          } 
        });
  }
  
  private boolean hasRequired(EntityPlayerMP player, Item item) {
    return InventoryUtils.haveRequiredItem((EntityPlayer)player, new ItemStack(item));
  }
  
  private boolean upgrade(EntityPlayerMP player, Item ticket, Item oldItem, Item newItem) {
    if (!hasTicket(player, ticket)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu n'as pas de Ticket, reviens me voir quand tu en auras un !" });
      return false;
    } 
    MonthlyUtils.teleport((EntityPlayer)player, 4885.0D, 76.0D, 4947.0D);
    for (int i = 0; i < 50; i++) {
      double dX = player.field_70165_t - 2.0D + (player.func_70681_au().nextFloat() * 4.0F);
      double dY = player.field_70163_u + (player.func_70681_au().nextFloat() * 3.0F);
      double dZ = player.field_70161_v - 2.0D + (player.func_70681_au().nextFloat() * 4.0F);
      MonthlyUtils.spawnParticle(player.field_70170_p, "portal", dX, dY, dZ, 10, 1.0D);
      MonthlyUtils.spawnParticle(player.field_70170_p, "smoke", dX, dY, dZ, 10, 1.0D);
    } 
    ItemStack endiumBattery = new ItemStack(newItem, 1);
    InventoryUtils.giveOrDropitems((EntityPlayer)player, endiumBattery);
    InventoryUtils.removeItems((EntityPlayer)player, new ItemStack(oldItem), 1);
    removeTicket(player, ticket);
    return true;
  }
  
  private boolean hasTicket(EntityPlayerMP player, Item item) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && stack.func_77973_b() == item) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && tag.func_74779_i("owner").equals(FastUUID.toString((Entity)player)))
          return true; 
      } 
    } 
    return false;
  }
  
  private void removeTicket(EntityPlayerMP player, Item item) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && stack.func_77973_b() == item) {
        player.field_71071_by.func_70299_a(i, null);
        break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\dialog\BlacksmithDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */