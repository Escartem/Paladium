package fr.paladium.palamod.modules.luckyblock.gui.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.Ariane;
import fr.paladium.ariane.lib.dialog.ArianeDialog;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BuskerDialogManager {
  private Item[] rewards = new Item[] { ItemsRegister.SWORD_PISTOL, ItemsRegister.BLUNDERBUSS };
  
  public void startDialog(EntityPlayerMP player) {
    String title = "Kvothe";
    CustomDialog startDialog = null;
    if (hasItem(player)) {
      startDialog = new CustomDialog(title, "ça alors mon p’tit gars, t’as une bien belle arme entre tes p’tites mains. Tu me l’échangerais bien contre une de mes supers armes de pirate non ?");
      startDialog.addOption("Oui");
      startDialog.addOption("Non");
      startDialog.setCallback(ret -> {
            if (ret.intValue() == 1)
              reward(player); 
          });
      startDialog.setCloseGui();
    } else {
      startDialog = new CustomDialog(title, "Je cherche des armes récentes. Je suis prêt à te les échanger contre une super arme pirate. Si tu en obtiens une ce-mois-ci de la part du forgeron, amène la ici !");
      startDialog.setCloseGui();
    } 
    Ariane.getServer().addDialog((EntityPlayer)player, (ArianeDialog)startDialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(startDialog), player);
  }
  
  private boolean hasItem(EntityPlayerMP player) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && (stack
        .func_77973_b() == ItemsRegister.SWORD_TOO_BIG || stack
        .func_77973_b() == ItemsRegister.IRON_SKULL_HAMMER || stack
        .func_77973_b() == ItemsRegister.ENERGETIC_BOW_SWORD || stack
        .func_77973_b() == ItemsRegister.RUNIC_AXE))
        return true; 
    } 
    return false;
  }
  
  private void reward(EntityPlayerMP player) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if ((stack != null && (stack
        .func_77973_b() == ItemsRegister.SWORD_TOO_BIG || stack
        .func_77973_b() == ItemsRegister.IRON_SKULL_HAMMER || stack
        .func_77973_b() == ItemsRegister.ENERGETIC_BOW_SWORD || stack
        .func_77973_b() == ItemsRegister.RUNIC_AXE)) || stack
        .func_77973_b() == ItemsRegister.SITAR || stack
        .func_77973_b() == ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE) {
        player.field_71071_by.func_70299_a(i, new ItemStack(this.rewards[player.field_70170_p.field_73012_v.nextInt(this.rewards.length)]));
        return;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\june\BuskerDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */