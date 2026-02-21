package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class NoOxygenEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Plus d'oxygène";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 110;
  
  private static final String TEXTURE_PATH = "march/no_oxygen";
  
  private static final String ACTIVATE_MESSAGE = "§eCrée un casque des étoiles et porte le pour ne plus avoir besoin d'oxygène !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PotionsRegister.NO_OXYGEN.getPotionId(), MonthlyUtils.translateSecondsToTicks(30), 0));
    giveItems(player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eCrée un casque des étoiles et porte le pour ne plus avoir besoin d'oxygène !" });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis(30L));
  }
  
  public String getName() {
    return "Plus d'oxygène";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 110;
  }
  
  public String getTexture() {
    return "march/no_oxygen";
  }
  
  private void giveItems(EntityPlayerMP player) {
    ItemStack[] items = { new ItemStack(Blocks.field_150426_aN, 1), new ItemStack(Items.field_151042_j, 4) };
    for (ItemStack stack : items)
      InventoryUtils.giveOrDropitems((EntityPlayer)player, stack); 
    player.field_71071_by.func_70296_d();
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu n'as plus d'oxygène, crée un casque des étoiles pour survivre !" };
  }
  
  public String getAction() {
    return "Temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\NoOxygenEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */