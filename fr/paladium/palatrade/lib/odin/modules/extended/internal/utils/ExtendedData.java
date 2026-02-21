package fr.paladium.palatrade.lib.odin.modules.extended.internal.utils;

import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedProperty;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;

public class ExtendedData {
  private final String propName;
  
  private final Class<? extends Entity> entityType;
  
  private final List<ExtendedProperty> properties;
  
  private final Class<? extends ExtendedEntityProperties> extended;
  
  public ExtendedData(String propName, Class<? extends Entity> entityType, ExtendedProperty[] properties, Class<? extends ExtendedEntityProperties> extended) {
    this.propName = propName;
    this.entityType = entityType;
    this.properties = Arrays.asList(properties);
    this.extended = extended;
  }
  
  public String getPropName() {
    return this.propName;
  }
  
  public Class<? extends Entity> getEntityType() {
    return this.entityType;
  }
  
  public List<ExtendedProperty> getProperties() {
    return this.properties;
  }
  
  public Class<? extends ExtendedEntityProperties> getExtended() {
    return this.extended;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\interna\\utils\ExtendedData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */