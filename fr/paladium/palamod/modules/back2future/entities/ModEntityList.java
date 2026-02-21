package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModEntityList {
  public static Item entity_egg;
  
  private static EntityData[] array = new EntityData[15];
  
  private static Map<Integer, Class<? extends Entity>> map = new HashMap<>();
  
  public static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
    registerEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, -1, -1, false);
  }
  
  public static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggColour1, int eggColour2) {
    registerEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggColour1, eggColour2, true);
  }
  
  private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggColour1, int eggColour2, boolean hasEgg) {
    EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    if (id >= array.length) {
      EntityData[] newArray = new EntityData[id + 5];
      for (int i = 0; i < array.length; i++)
        newArray[i] = array[i]; 
      array = newArray;
    } 
    if (array[id] != null)
      throw new IllegalArgumentException("ID " + id + " is already being used! Please report this error!"); 
    array[id] = new EntityData(entityName, id, eggColour1, eggColour2, hasEgg);
    map.put(Integer.valueOf(id), entityClass);
  }
  
  public static String getName(int id) {
    EntityData data = getData(id);
    if (data == null)
      return null; 
    return "palamod." + data.name;
  }
  
  public static EntityData getData(int id) {
    if (id >= array.length)
      return null; 
    return array[id];
  }
  
  public static boolean hasEntitiesWithEggs() {
    for (EntityData data : array) {
      if (data != null && data.hasEgg)
        return true; 
    } 
    return false;
  }
  
  public static Entity createEntityByID(int id, World world) {
    EntityData data = getData(id);
    if (data == null || !data.hasEgg)
      return null; 
    try {
      Class<? extends Entity> cls = map.get(Integer.valueOf(id));
      if (cls != null)
        return cls.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world }); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public static EntityData[] getDatasWithEggs() {
    List<EntityData> list = new LinkedList<>();
    for (Integer id : map.keySet()) {
      EntityData data = getData(id.intValue());
      if (data != null && data.hasEgg)
        list.add(data); 
    } 
    return list.<EntityData>toArray(new EntityData[list.size()]);
  }
  
  public static ItemStack getEggFor(Class<? extends Entity> entityCls) {
    for (Map.Entry<Integer, Class<? extends Entity>> entry : map.entrySet()) {
      if (entry.getValue() == entityCls)
        return new ItemStack(entity_egg, 1, ((Integer)entry.getKey()).intValue()); 
    } 
    return null;
  }
  
  public static class EntityData {
    public final String name;
    
    public final int id;
    
    public final int eggColour1;
    
    public final int eggColour2;
    
    public final boolean hasEgg;
    
    private EntityData(String name, int id, int eggColour1, int eggColour2, boolean hasEgg) {
      this.name = name;
      this.id = id;
      this.eggColour1 = eggColour1;
      this.eggColour2 = eggColour2;
      this.hasEgg = hasEgg;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ModEntityList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */