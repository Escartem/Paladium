package fr.paladium.palamod.modules.hunter.init;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.hunter.entites.EntityCavernousZombie;
import fr.paladium.palamod.modules.hunter.entites.EntityCrab;
import fr.paladium.palamod.modules.hunter.entites.EntityDolphin;
import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.hunter.entites.EntityFarmerChicken;
import fr.paladium.palamod.modules.hunter.entites.EntityFlowerMonster;
import fr.paladium.palamod.modules.hunter.entites.EntityGoat;
import fr.paladium.palamod.modules.hunter.entites.EntityJellyFish;
import fr.paladium.palamod.modules.hunter.entites.EntityMegaCreeper;
import fr.paladium.palamod.modules.hunter.entites.EntityPanda;
import fr.paladium.palamod.modules.hunter.entites.EntityParrot;
import fr.paladium.palamod.modules.hunter.entites.EntitySnail;
import fr.paladium.palamod.modules.hunter.entites.EntitySnake;
import fr.paladium.palamod.modules.hunter.entites.EntityTurtle;
import java.awt.Color;

public class Entities {
  public static void register() {
    EntityRegistry.registerGlobalEntityID(EntityFarmerChicken.class, "entityFarmerChicken", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(0, 0, 255)).getRGB(), (new Color(255, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityFarmerChicken.class, "entityFarmerChicken", 540, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityCavernousZombie.class, "entityCavernousZombie", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(0, 0, 255)).getRGB(), (new Color(255, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityCavernousZombie.class, "entityCavernousZombie", 541, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityFlowerMonster.class, "entityFlowerMonster", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(0, 0, 255)).getRGB(), (new Color(255, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityFlowerMonster.class, "entityFlowerMonster", 542, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityMegaCreeper.class, "entityMegaCreeper", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(0, 0, 255)).getRGB(), (new Color(255, 0, 0))
        .getRGB());
    EntityRegistry.registerModEntity(EntityMegaCreeper.class, "entityMegaCreeper", 543, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityCrab.class, "entityCrab", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 134, 35)).getRGB(), (new Color(255, 134, 35))
        .getRGB());
    EntityRegistry.registerModEntity(EntityCrab.class, "entityCrab", 544, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntitySnail.class, "entitySnail", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 174, 190)).getRGB(), (new Color(255, 174, 190))
        .getRGB());
    EntityRegistry.registerModEntity(EntitySnail.class, "entitySnail", 545, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityJellyFish.class, "entityJellyFish", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(50, 30, 255)).getRGB(), (new Color(50, 30, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityJellyFish.class, "entityJellyFish", 546, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntitySnake.class, "entitySnake", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.GREEN.getRGB(), Color.GREEN.getRGB());
    EntityRegistry.registerModEntity(EntitySnake.class, "entitySnake", 547, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityTurtle.class, "entityTurtle", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.BLUE.getRGB(), Color.CYAN.getRGB());
    EntityRegistry.registerModEntity(EntityTurtle.class, "entityTurtle", 548, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityParrot.class, "entityParrot", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.ORANGE.getRGB(), Color.CYAN.getRGB());
    EntityRegistry.registerModEntity(EntityParrot.class, "entityParrot", 549, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityPanda.class, "entityPanda", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.WHITE.getRGB(), Color.BLACK.getRGB());
    EntityRegistry.registerModEntity(EntityPanda.class, "entityPanda", 550, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityGoat.class, "entityGoat", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.WHITE.getRGB(), Color.GRAY.getRGB());
    EntityRegistry.registerModEntity(EntityGoat.class, "entityGoat", 551, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityDolphin.class, "entityDolphin", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.BLUE.getRGB(), Color.MAGENTA.getRGB());
    EntityRegistry.registerModEntity(EntityDolphin.class, "entityDolphin", 552, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityElephant.class, "entityElephant", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.GRAY.getRGB(), Color.LIGHT_GRAY.getRGB());
    EntityRegistry.registerModEntity(EntityElephant.class, "entityElephant", 553, PalaMod.instance, 40, 1, true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\init\Entities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */