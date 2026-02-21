package fr.paladium.palamod.modules.spellsv2.utils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.entity.EntityEgg;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFreeze;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import scala.actors.threadpool.Arrays;

public class ServerManager {
  private static Map<Integer, List<UUID>> activeSpells = new HashMap<>();
  
  public static Map<Integer, List<UUID>> getActiveSpells() {
    return activeSpells;
  }
  
  private static Map<UUID, EntityGhost> mentalis = new HashMap<>();
  
  public static Map<UUID, EntityGhost> getMentalis() {
    return mentalis;
  }
  
  public static void setMentalis(Map<UUID, EntityGhost> mentalis) {
    ServerManager.mentalis = mentalis;
  }
  
  private static Map<EntityPlayerMP, List<Integer>> freeze = new HashMap<>();
  
  public static Map<EntityPlayerMP, List<Integer>> getFreeze() {
    return freeze;
  }
  
  public static void setFreeze(Map<EntityPlayerMP, List<Integer>> freeze) {
    ServerManager.freeze = freeze;
  }
  
  private static Map<EntityPlayerMP, EntityEgg> eggs = new HashMap<>();
  
  public static Map<EntityPlayerMP, EntityEgg> getEggs() {
    return eggs;
  }
  
  public static void setEggs(Map<EntityPlayerMP, EntityEgg> eggs) {
    ServerManager.eggs = eggs;
  }
  
  private static List<EntityPlayerMP> labyrinthe = new ArrayList<>();
  
  public static List<EntityPlayerMP> getLabyrinthe() {
    return labyrinthe;
  }
  
  public static void setLabyrinthe(List<EntityPlayerMP> labyrinthe) {
    ServerManager.labyrinthe = labyrinthe;
  }
  
  public static void addActiveSpell(int spell, String uuid) {
    List<UUID> players = new ArrayList<>();
    if (activeSpells.get(Integer.valueOf(spell)) != null)
      players = activeSpells.get(Integer.valueOf(spell)); 
    if (!players.contains(FastUUID.parseUUID(uuid)))
      players.add(FastUUID.parseUUID(uuid)); 
    activeSpells.put(Integer.valueOf(spell), players);
  }
  
  public static void removeActiveSpell(int spell, String uuid) {
    List<UUID> players = new ArrayList<>();
    if (activeSpells.containsKey(Integer.valueOf(spell)))
      players = activeSpells.get(Integer.valueOf(spell)); 
    if (players.contains(FastUUID.parseUUID(uuid)))
      players.remove(FastUUID.parseUUID(uuid)); 
    activeSpells.put(Integer.valueOf(spell), players);
  }
  
  public static void addMentalis(UUID player, EntityGhost entity) {
    mentalis.put(player, entity);
  }
  
  public static void removeMentalis(UUID player) {
    mentalis.remove(player);
  }
  
  public static void addFreeze(EntityPlayerMP player, double posX, double posY, double posZ) {
    freeze.put(player, Arrays.asList((Object[])new Integer[] { Integer.valueOf((int)posX), Integer.valueOf((int)posY), Integer.valueOf((int)posZ) }));
    PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(true), player);
  }
  
  public static void removeFreeze(EntityPlayerMP player) {
    if (freeze.containsKey(player)) {
      freeze.remove(player);
      PSpellsV2.network.sendTo((IMessage)new PacketClientFreeze(false), player);
    } 
  }
  
  public static boolean isSpellActive(int spell, EntityPlayerMP player) {
    if (activeSpells.containsKey(Integer.valueOf(spell)) && (
      (List)activeSpells.get(Integer.valueOf(spell))).contains(player.func_110124_au()))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\ServerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */