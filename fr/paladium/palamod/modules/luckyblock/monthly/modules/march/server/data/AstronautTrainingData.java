package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.AstronautTrainingEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class AstronautTrainingData {
  private UUID playerUniqueId;
  
  private int jumpCount;
  
  private long timer;
  
  private int step;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public int getJumpCount() {
    return this.jumpCount;
  }
  
  public long getTimer() {
    return this.timer;
  }
  
  public int getStep() {
    return this.step;
  }
  
  public AstronautTrainingData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.jumpCount = 0;
    this.timer = 0L;
    this.step = -1;
  }
  
  private boolean canPerform(long now) {
    return (this.step < 4);
  }
  
  public void perform(EntityPlayerMP player, long now) {
    if (player == null) {
      this.step = 4;
      return;
    } 
    if (this.step == -1) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eTon entrainement va commencer...", "§eEntraîne toi à résister à la gravité martienne ", "§een sautant 10 fois pour obtenir une récompense !" });
      this.step = 1;
      player.func_70690_d(new PotionEffect(PotionsRegister.MARS_GRAVITY.getPotionId(), MonthlyUtils.translateSecondsToTicks(30), 0));
      this.timer = now + 30000L;
    } 
    if (this.step == 1) {
      if (now > this.timer || !player.func_82165_m(PotionsRegister.MARS_GRAVITY.getPotionId())) {
        stopHelios(player);
        MonthlyUtils.playSound(player, "soft_fail");
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi l'entrainement." });
        InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY));
        InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MOON_GRAVITY));
        InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MARS_GRAVITY));
        this.step = 4;
      } 
      if (player.field_70181_x > 0.2D) {
        this.jumpCount++;
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eSauts confirmés: " + this.jumpCount + "/10" });
      } 
      if (this.jumpCount >= 10) {
        this.jumpCount = 0;
        this.step++;
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eTu as réussi la première étape de l'entraînement.", " ", "§eEntraîne toi à résister à la gravité lunaire ", "§een sautant 10 fois pour obtenir une récompense !" });
        this.step = 2;
        player.func_70690_d(new PotionEffect(PotionsRegister.MOON_GRAVITY.getPotionId(), MonthlyUtils.translateSecondsToTicks(30), 0));
        this.timer = now + 30000L;
      } 
    } 
    if (this.step == 2) {
      if (now > this.timer || !player.func_82165_m(PotionsRegister.MOON_GRAVITY.getPotionId())) {
        stopHelios(player);
        MonthlyUtils.playSound(player, "soft_fail");
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi l'entrainement." });
        for (int i = 0; i < 2; i++) {
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MOON_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MARS_GRAVITY));
        } 
        this.step = 4;
      } 
      if (player.field_70181_x > 0.2D) {
        this.jumpCount++;
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eSauts confirmés: " + this.jumpCount + "/10" });
      } 
      if (this.jumpCount >= 10) {
        this.jumpCount = 0;
        this.step++;
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eTu as réussi la seconde étape de l'entraînement.", " ", "§eEntraîne toi à résister à la gravité de Jupiter..." });
        this.step = 3;
        player.func_70690_d(new PotionEffect(PotionsRegister.JUPITER_GRAVITY.getPotionId(), MonthlyUtils.translateSecondsToTicks(30), 0));
        this.timer = now + 30000L;
      } 
    } 
    if (this.step == 3)
      if (now > this.timer) {
        stopHelios(player);
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eTu as réussi la dernière étape de l'entraînement." });
        for (int i = 0; i < 3; i++) {
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MOON_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MARS_GRAVITY));
        } 
        this.step = 4;
      } else if (!player.func_82165_m(PotionsRegister.JUPITER_GRAVITY.getPotionId())) {
        stopHelios(player);
        MonthlyUtils.playSound(player, "soft_fail");
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu n'as pas réussi l'entrainement." });
        for (int i = 0; i < 2; i++) {
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MOON_GRAVITY));
          InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SPLASH_POTION_MARS_GRAVITY));
        } 
        this.step = 4;
      }  
  }
  
  private void stopHelios(EntityPlayerMP player) {
    if (player == null)
      return; 
    MonthlyUtils.stopHeliosEvent(player, AstronautTrainingEvent.class);
  }
  
  public boolean isExpired() {
    return (this.step == 4);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\AstronautTrainingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */