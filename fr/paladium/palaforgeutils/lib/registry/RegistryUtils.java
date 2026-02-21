package fr.paladium.palaforgeutils.lib.registry;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.potion.PotionUtils;
import java.awt.Color;
import java.lang.reflect.Method;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class RegistryUtils {
  private static Method entityRegistryValidateAndClaimIdMethod;
  
  public static void block(Block... blocks) {
    for (Block block : blocks) {
      GameRegistry.registerBlock(block, block.func_149739_a());
      if (block instanceof ITileEntityProvider) {
        ITileEntityProvider tileEntityProvider = (ITileEntityProvider)block;
        Class<? extends TileEntity> clazz = (Class)tileEntityProvider.func_149915_a(null, 0).getClass();
        try {
          GameRegistry.registerTileEntity(clazz, clazz.getSimpleName());
        } catch (IllegalArgumentException illegalArgumentException) {}
      } 
    } 
  }
  
  public static void item(Item... items) {
    for (Item item : items)
      GameRegistry.registerItem(item, item.func_77658_a()); 
  }
  
  public static void potion(APotion... potions) {
    for (APotion potion : potions)
      PotionUtils.registerPotionEffect(potion); 
  }
  
  public static void entity(Class<? extends Entity> entityClass, Color eggColor, int trackingRange) {
    entity(entityClass, eggColor, trackingRange, PalaForgeUtilsMod.getInstance());
  }
  
  public static void entity(Class<? extends Entity> entityClass, Color eggColor, int trackingRange, Object modInstance) {
    int entityID = EntityRegistry.findGlobalUniqueEntityId();
    String entityName = entityClass.getSimpleName();
    if (eggColor != null) {
      EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID, eggColor
          
          .getRGB(), eggColor
          .getRGB());
    } else {
      validateAndClaimId(entityID);
    } 
    EntityRegistry.registerModEntity(entityClass, entityName, entityID, modInstance, trackingRange, 1, true);
  }
  
  private static void validateAndClaimId(int entityId) {
    if (entityRegistryValidateAndClaimIdMethod == null)
      try {
        entityRegistryValidateAndClaimIdMethod = EntityRegistry.class.getDeclaredMethod("validateAndClaimId", new Class[] { int.class });
        entityRegistryValidateAndClaimIdMethod.setAccessible(true);
      } catch (NoSuchMethodException|SecurityException e) {
        e.printStackTrace();
        return;
      }  
    try {
      entityRegistryValidateAndClaimIdMethod.invoke(EntityRegistry.instance(), new Object[] { Integer.valueOf(entityId) });
    } catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\registry\RegistryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */