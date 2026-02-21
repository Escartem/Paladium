package fr.paladium.palamod.modules.palaboss.common.boss;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BossRegistry {
  public static final BossRegistry INSTANCE = new BossRegistry();
  
  private final Map<Class<? extends EntityBossBase>, BossAttributes> registeredBosses = new HashMap<>();
  
  public void registerBoss(String bossName, int entityID, Class<? extends EntityBossBase> bossClazz, boolean boss) {
    try {
      if (boss) {
        BossAttributes attributes = loadBossAttributes(bossName, entityID);
        this.registeredBosses.put(bossClazz, attributes);
        EntityRegistry.registerGlobalEntityID(bossClazz, bossName, EntityRegistry.findGlobalUniqueEntityId(), attributes.getAttributeInteger("backgroundEggColor", 1), attributes.getAttributeInteger("foregroundEggColor", 1));
      } 
      EntityRegistry.registerModEntity(bossClazz, bossName, entityID, PalaMod.instance, 40, 1, true);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  protected BossAttributes loadBossAttributes(String bossName, int entityID) throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream("assets/palamod/boss/" + bossName + ".json");
    if (is == null)
      throw new FileNotFoundException("Can't load json boss file: " + getClass().getClassLoader().getResourceAsStream("assets/palamod/boss/" + bossName + ".json").getClass()); 
    InputStreamReader stream = new InputStreamReader(is, "UTF-8");
    return (new BossAttributes(entityID, bossName)).parseAttributes(readStream(stream));
  }
  
  public String readStream(InputStreamReader streamReader) throws IOException {
    BufferedReader reader = new BufferedReader(streamReader);
    StringBuffer builder = new StringBuffer();
    String line;
    while ((line = reader.readLine()) != null)
      builder.append(line); 
    return builder.toString();
  }
  
  public BossAttributes getBossAttributes(Class<? extends EntityBossBase> bossClazz) {
    return this.registeredBosses.get(bossClazz);
  }
  
  public Map<Class<? extends EntityBossBase>, BossAttributes> getRegisteredBosses() {
    return this.registeredBosses;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\boss\BossRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */