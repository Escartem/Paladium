package fr.paladium.palaspawner.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.blueprint.common.manager.StructureManager;
import fr.paladium.palaforgeutils.lib.guihandler.CustomGuiHandler;
import fr.paladium.palaforgeutils.lib.guihandler.GHandler;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palaspawner.SpawnerMod;
import fr.paladium.palaspawner.common.block.BlockEmptySpawner;
import fr.paladium.palaspawner.common.block.BlockSpawnerController;
import fr.paladium.palaspawner.common.block.BlockSpawnerTier;
import fr.paladium.palaspawner.common.entity.EntitySpawner;
import fr.paladium.palaspawner.common.handler.SpawnerControllerGuiHandler;
import fr.paladium.palaspawner.common.item.ItemCavernHammer;
import fr.paladium.palaspawner.common.item.ItemEmptySpawner;
import fr.paladium.palaspawner.common.item.upgrade.AItemSpawnerUpgrade;
import fr.paladium.palaspawner.common.listener.AnvilListener;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.network.packet.BBRequestController;
import fr.paladium.palaspawner.common.network.packet.CSChangeSpawnerController;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import fr.paladium.palaspawner.common.registry.SpawnerUpgradeRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.impl.FirstSpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.blueprint.impl.FourthSpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.blueprint.impl.SecondSpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.blueprint.impl.ThirdSpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerBlazeData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerCaveSpiderData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerCreeperData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerEndermanData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerPigData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerSkeletonData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerSlimeData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerSpiderData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerWitchData;
import fr.paladium.palaspawner.common.spawner.data.impl.SpawnerZombieData;
import fr.paladium.palaspawner.common.spawner.upgrade.JobRequirement;
import fr.paladium.palaspawner.common.spawner.upgrade.SpawnerUpgrade;
import fr.paladium.palaspawner.common.tab.SpawnerTabs;
import fr.paladium.palaspawner.common.tile.Tier;
import fr.paladium.palaspawner.common.tile.TileEntityEmptyMobSpawner;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public abstract class SpawnerCommonProxy extends AModProxy {
  private static final String NETWORK_NAME = "palaspawner";
  
  private static SpawnerCommonProxy instance;
  
  private StructureManager structureManager;
  
  private SpawnerManager spawnerManager;
  
  private SpawnerTabs tabs;
  
  private CustomGuiHandler guiHandler;
  
  private int spawnerGuiHandlerId;
  
  public static SpawnerCommonProxy getInstance() {
    return instance;
  }
  
  public StructureManager getStructureManager() {
    return this.structureManager;
  }
  
  public SpawnerManager getSpawnerManager() {
    return this.spawnerManager;
  }
  
  public SpawnerTabs getTabs() {
    return this.tabs;
  }
  
  public CustomGuiHandler getGuiHandler() {
    return this.guiHandler;
  }
  
  public int getSpawnerGuiHandlerId() {
    return this.spawnerGuiHandlerId;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
    this.tabs = new SpawnerTabs();
    SpawnerUpgradeRegistry.register(new SpawnerUpgrade[] { SpawnerUpgradeRegistry.SPEED = SpawnerUpgrade.of("speed").addRequirement(JobRequirement.of(JobType.MINER, 17)), 
          
          SpawnerUpgradeRegistry.MORE = SpawnerUpgrade.of("more").addRequirement(JobRequirement.of(JobType.ALCHEMIST, 18)), 
          
          SpawnerUpgradeRegistry.LOOTING = SpawnerUpgrade.of("looting").addRequirement(JobRequirement.of(JobType.HUNTER, 15)), 
          
          SpawnerUpgradeRegistry.SLIME = SpawnerUpgrade.of("slime").addRequirement(JobRequirement.of(JobType.ALCHEMIST, 15)) });
    RegistryUtils.item(new Item[] { (Item)(SpawnerItemRegistry.CAVERN_HAMMER = new ItemCavernHammer()), (Item)(SpawnerItemRegistry.UPGRADE_LOOTING = new AItemSpawnerUpgrade(SpawnerUpgradeRegistry.LOOTING)), (Item)(SpawnerItemRegistry.UPGRADE_MORE = new AItemSpawnerUpgrade(SpawnerUpgradeRegistry.MORE)), (Item)(SpawnerItemRegistry.UPGRADE_SLIME = new AItemSpawnerUpgrade(SpawnerUpgradeRegistry.SLIME)), (Item)(SpawnerItemRegistry.UPGRADE_SPEED = new AItemSpawnerUpgrade(SpawnerUpgradeRegistry.SPEED)) });
    RegistryUtils.block(new Block[] { (Block)(SpawnerBlockRegistry.SPAWNER_CONTROLLER = new BlockSpawnerController()), (Block)(SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE = new BlockSpawnerTier(Tier.ONE)), (Block)(SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO = new BlockSpawnerTier(Tier.TWO)), (Block)(SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE = new BlockSpawnerTier(Tier.THREE)), (Block)(SpawnerBlockRegistry.SPAWNER_STRUCTURE_FOUR = new BlockSpawnerTier(Tier.FOUR)) });
    GameRegistry.registerBlock(SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER = (Block)new BlockEmptySpawner(true), SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER.func_149739_a());
    GameRegistry.registerBlock(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER = (Block)new BlockEmptySpawner(false), ItemEmptySpawner.class, SpawnerBlockRegistry.EMPTY_MOB_SPAWNER.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityEmptyMobSpawner.class, TileEntityEmptyMobSpawner.class.getSimpleName());
    this.structureManager = StructureManager.getInstance();
    this.spawnerManager = SpawnerManager.getInstance();
    initNetwork("palaspawner");
    SimpleNetworkWrapper network = getNetwork();
    PacketUtils.registerPacket(network, CSChangeSpawnerController.class);
    PacketUtils.registerPacket(network, BBRequestController.class);
    RegistryUtils.entity(EntitySpawner.class, null, 51, SpawnerMod.getInstance());
    NetworkRegistry.INSTANCE.registerGuiHandler("palaspawner", (IGuiHandler)(this.guiHandler = new CustomGuiHandler()));
    this.spawnerGuiHandlerId = this.guiHandler.registerHandler((GHandler)new SpawnerControllerGuiHandler());
    addListener(new Class[] { AnvilListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    this.spawnerManager.registerBluePrint(FourthSpawnerBluePrint.class);
    this.spawnerManager.registerBluePrint(ThirdSpawnerBluePrint.class);
    this.spawnerManager.registerBluePrint(SecondSpawnerBluePrint.class);
    this.spawnerManager.registerBluePrint(FirstSpawnerBluePrint.class);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    this.spawnerManager.registerSpawnerEntity(SpawnerBlazeData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerCaveSpiderData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerCreeperData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerEndermanData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerPigData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerSkeletonData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerSpiderData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerWitchData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerZombieData.class);
    this.spawnerManager.registerSpawnerEntity(SpawnerSlimeData.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\SpawnerCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */