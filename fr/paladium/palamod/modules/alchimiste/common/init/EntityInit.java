package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.alchimiste.common.entities.EntityGlueball;

public class EntityInit {
  public static void init() {
    EntityRegistry.registerGlobalEntityID(EntityGlueball.class, "entityGlueball", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntityGlueball.class, "entityGlueball", 420, PalaMod.instance, 40, 1, true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\EntityInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */