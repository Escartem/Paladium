package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.AncientHammerFakePlayerEntity;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEndiumEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.DynamiteNinjaEntity;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityCustomArrow;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntityPotionGun;
import fr.paladium.palamod.modules.paladium.common.entities.projectiles.EntitySplashPotion;
import fr.paladium.palamod.modules.paladium.dummy.EntityDpsFloatingNumber;
import fr.paladium.palamod.modules.paladium.dummy.EntityDummy;
import fr.paladium.palamod.modules.paladium.dummy.EntityFloatingNumber;

public class PRegister_Entities {
  public static void init() {
    EntityRegistry.registerModEntity(EntityCustomArrow.class, "Custom Arrow", 1, PalaMod.instance, 64, 10, true);
    EntityRegistry.registerModEntity(DynamiteEntity.class, "Dynamite", 2, PalaMod.instance, 80, 3, true);
    EntityRegistry.registerModEntity(DynamiteNinjaEntity.class, "DynamiteNinja", 3, PalaMod.instance, 80, 3, true);
    EntityRegistry.registerModEntity(EntitySplashPotion.class, "SplashPotion", 4, PalaMod.instance, 80, 3, true);
    EntityRegistry.registerModEntity(EntityPotionGun.class, "Gun Potion", 5, PalaMod.instance, 64, 10, true);
    EntityRegistry.registerModEntity(DynamiteEndiumEntity.class, "DynamiteEndium", 6, PalaMod.instance, 80, 3, true);
    EntityRegistry.registerModEntity(EntityDummy.class, "Dummy", 7, PalaMod.instance, 32, 10, false);
    EntityRegistry.registerModEntity(EntityFloatingNumber.class, "FloatingNumber", 8, PalaMod.instance, 32, 1, false);
    EntityRegistry.registerModEntity(EntityDpsFloatingNumber.class, "FloatingNumberDPS", 9, PalaMod.instance, 32, 1, false);
    EntityRegistry.registerModEntity(EntityAncientHammerEffect.class, "AncientHammerEffect", 10, PalaMod.instance, 100, 1, false);
    EntityRegistry.registerModEntity(AncientHammerFakePlayerEntity.class, "AncientHammerFakePlayer", 11, PalaMod.instance, 100, 1, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Entities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */