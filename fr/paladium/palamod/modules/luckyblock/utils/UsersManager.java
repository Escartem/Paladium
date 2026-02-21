package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class UsersManager {
  private static Map<EntityPlayerMP, List<EntityIronGolem>> bodyGuard = new HashMap<>();
  
  public static Map<EntityPlayerMP, List<EntityIronGolem>> getBodyGuard() {
    return bodyGuard;
  }
  
  public static void setBodyGuard(Map<EntityPlayerMP, List<EntityIronGolem>> bodyGuard) {
    UsersManager.bodyGuard = bodyGuard;
  }
  
  private static Map<EntityPlayerMP, EntityPig> piggyRodeo = new HashMap<>();
  
  public static Map<EntityPlayerMP, EntityPig> getPiggyRodeo() {
    return piggyRodeo;
  }
  
  public static void setPiggyRodeo(Map<EntityPlayerMP, EntityPig> piggyRodeo) {
    UsersManager.piggyRodeo = piggyRodeo;
  }
  
  private static List<EntityPlayer> invincibles = new ArrayList<>();
  
  public static List<EntityPlayer> getInvincibles() {
    return invincibles;
  }
  
  public static void setInvincibles(List<EntityPlayer> invincibles) {
    UsersManager.invincibles = invincibles;
  }
  
  private static List<int[]> traps = (List)new ArrayList<>();
  
  public static List<int[]> getTraps() {
    return traps;
  }
  
  public static void setTraps(List<int[]> traps) {
    UsersManager.traps = traps;
  }
  
  private static List<EntityPlayerMP> killOrClear = new ArrayList<>();
  
  public static List<EntityPlayerMP> getKillOrClear() {
    return killOrClear;
  }
  
  public static void setKillOrClear(List<EntityPlayerMP> killOrClear) {
    UsersManager.killOrClear = killOrClear;
  }
  
  private static List<EntityPlayerMP> bol = new ArrayList<>();
  
  public static List<EntityPlayerMP> getBol() {
    return bol;
  }
  
  public static void setBol(List<EntityPlayerMP> bol) {
    UsersManager.bol = bol;
  }
  
  private static List<String> keepPumpkinBlur = new ArrayList<>();
  
  public static List<String> getKeepPumpkinBlur() {
    return keepPumpkinBlur;
  }
  
  public static void setKeepPumpkinBlur(List<String> keepPumpkinBlur) {
    UsersManager.keepPumpkinBlur = keepPumpkinBlur;
  }
  
  private static List<EntityPlayer> spacefood = new ArrayList<>();
  
  public static List<EntityPlayer> getSpacefood() {
    return spacefood;
  }
  
  public static void setSpacefood(List<EntityPlayer> spacefood) {
    UsersManager.spacefood = spacefood;
  }
  
  private static List<EntityPlayer> consumerCredit = new ArrayList<>();
  
  public static List<EntityPlayer> getConsumerCredit() {
    return consumerCredit;
  }
  
  public static void setConsumerCredit(List<EntityPlayer> consumerCredit) {
    UsersManager.consumerCredit = consumerCredit;
  }
  
  private static Map<String, Integer> chocolateEggEaten = new HashMap<>();
  
  public static Map<String, Integer> getChocolateEggEaten() {
    return chocolateEggEaten;
  }
  
  public static void setChocolateEggEaten(Map<String, Integer> chocolateEggEaten) {
    UsersManager.chocolateEggEaten = chocolateEggEaten;
  }
  
  private static Map<String, Integer> blueEasterEggCaught = new HashMap<>();
  
  public static Map<String, Integer> getBlueEasterEggCaught() {
    return blueEasterEggCaught;
  }
  
  public static void setBlueEasterEggCaught(Map<String, Integer> blueEasterEggCaught) {
    UsersManager.blueEasterEggCaught = blueEasterEggCaught;
  }
  
  private static List<String> almostCancelFallDamagePlayer = new ArrayList<>();
  
  public static List<String> getAlmostCancelFallDamagePlayer() {
    return almostCancelFallDamagePlayer;
  }
  
  private static Map<String, TileEntityDigicode> digicodeInit = new HashMap<>();
  
  public static Map<String, TileEntityDigicode> getDigicodeInit() {
    return digicodeInit;
  }
  
  public static void setDigicodeInit(Map<String, TileEntityDigicode> digicodeInit) {
    UsersManager.digicodeInit = digicodeInit;
  }
  
  private static List<String> cancelFallDamagePlayer = new ArrayList<>();
  
  public static List<String> getCancelFallDamagePlayer() {
    return cancelFallDamagePlayer;
  }
  
  private static Map<String, BungeeCordPlugin.BungeeCordCommandTPServerCoords> foghornAllowlist = new HashMap<>();
  
  public static Map<String, BungeeCordPlugin.BungeeCordCommandTPServerCoords> getFoghornAllowlist() {
    return foghornAllowlist;
  }
  
  public static void addBodyGuard(EntityPlayerMP player, EntityIronGolem golem) {
    List<EntityIronGolem> golems = new ArrayList<>();
    if (bodyGuard.containsKey(player))
      golems = bodyGuard.get(player); 
    golems.add(golem);
    bodyGuard.put(player, golems);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\UsersManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */