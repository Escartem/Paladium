package fr.paladium.palamod.modules.ablueprint;

import fr.paladium.blueprint.common.manager.StructureManager;
import fr.paladium.palamod.modules.ablueprint.blueprints.cauldron.CauldronCoreBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.flower.FlowerFarmBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.golem.GolemBoxBlueprint;
import fr.paladium.palamod.modules.ablueprint.blueprints.grinder.GrinderBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.mount.MountFiveBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.mount.MountFourBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.mount.MountOneBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.mount.MountThreeBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.mount.MountTwoBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.HomeRemoverFiveBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.HomeRemoverFourBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.HomeRemoverOneBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.HomeRemoverThreeBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.HomeRemoverTwoBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.remover.TitanePortalBluePrint;
import fr.paladium.palamod.modules.ablueprint.blueprints.ritual.EndiumRitualBluePrint;

public class BluePrintRegistry {
  public static EndiumRitualBluePrint ENDIUM_RITUAL;
  
  public static GrinderBluePrint GRINDER;
  
  public static GolemBoxBlueprint GOLEM_BOX;
  
  public static CauldronCoreBluePrint CAULDRON_CORE;
  
  public static HomeRemoverOneBluePrint HOME_REMOVER_ONE;
  
  public static HomeRemoverTwoBluePrint HOME_REMOVER_TWO;
  
  public static HomeRemoverThreeBluePrint HOME_REMOVER_THREE;
  
  public static HomeRemoverFourBluePrint HOME_REMOVER_FOUR;
  
  public static HomeRemoverFiveBluePrint HOME_REMOVER_FIVE;
  
  public static MountOneBluePrint MOUNT_ONE;
  
  public static MountTwoBluePrint MOUNT_TWO;
  
  public static MountThreeBluePrint MOUNT_THREE;
  
  public static MountFourBluePrint MOUNT_FOUR;
  
  public static MountFiveBluePrint MOUNT_FIVE;
  
  public static FlowerFarmBluePrint FLOWER_FARM;
  
  public static TitanePortalBluePrint TITANE_PORTAL;
  
  public static void init() {
    StructureManager manager = StructureManager.getInstance();
    ENDIUM_RITUAL = (EndiumRitualBluePrint)manager.registerBluePrint(EndiumRitualBluePrint.class);
    GRINDER = (GrinderBluePrint)manager.registerBluePrint(GrinderBluePrint.class);
    GOLEM_BOX = (GolemBoxBlueprint)manager.registerBluePrint(GolemBoxBlueprint.class);
    CAULDRON_CORE = (CauldronCoreBluePrint)manager.registerBluePrint(CauldronCoreBluePrint.class);
    HOME_REMOVER_ONE = (HomeRemoverOneBluePrint)manager.registerBluePrint(HomeRemoverOneBluePrint.class);
    HOME_REMOVER_TWO = (HomeRemoverTwoBluePrint)manager.registerBluePrint(HomeRemoverTwoBluePrint.class);
    HOME_REMOVER_THREE = (HomeRemoverThreeBluePrint)manager.registerBluePrint(HomeRemoverThreeBluePrint.class);
    HOME_REMOVER_FOUR = (HomeRemoverFourBluePrint)manager.registerBluePrint(HomeRemoverFourBluePrint.class);
    HOME_REMOVER_FIVE = (HomeRemoverFiveBluePrint)manager.registerBluePrint(HomeRemoverFiveBluePrint.class);
    MOUNT_ONE = (MountOneBluePrint)manager.registerBluePrint(MountOneBluePrint.class);
    MOUNT_TWO = (MountTwoBluePrint)manager.registerBluePrint(MountTwoBluePrint.class);
    MOUNT_THREE = (MountThreeBluePrint)manager.registerBluePrint(MountThreeBluePrint.class);
    MOUNT_FOUR = (MountFourBluePrint)manager.registerBluePrint(MountFourBluePrint.class);
    MOUNT_FIVE = (MountFiveBluePrint)manager.registerBluePrint(MountFiveBluePrint.class);
    FLOWER_FARM = (FlowerFarmBluePrint)manager.registerBluePrint(FlowerFarmBluePrint.class);
    TITANE_PORTAL = (TitanePortalBluePrint)manager.registerBluePrint(TitanePortalBluePrint.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ablueprint\BluePrintRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */