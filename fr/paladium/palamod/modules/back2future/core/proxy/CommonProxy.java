package fr.paladium.palamod.modules.back2future.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.core.handlers.Back2FutureNewBlocksEvents;
import fr.paladium.palamod.modules.back2future.core.handlers.ServerEventHandler;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import fr.paladium.palamod.modules.back2future.entities.EntityEndermite;
import fr.paladium.palamod.modules.back2future.entities.EntityGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityHusk;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringEffect;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringPotion;
import fr.paladium.palamod.modules.back2future.entities.EntityNewSnowGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityPlacedEndCrystal;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.back2future.entities.EntityRespawnedDragon;
import fr.paladium.palamod.modules.back2future.entities.EntityStray;
import fr.paladium.palamod.modules.back2future.entities.EntityTippedArrow;
import fr.paladium.palamod.modules.back2future.entities.EntityZombieVillager;
import fr.paladium.palamod.modules.back2future.entities.ModEntityList;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityBoulder;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityEndRod;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBeacon;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBrewingStand;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
  public void registerEvents() {
    FMLCommonHandler.instance().bus().register(ServerEventHandler.INSTANCE);
    MinecraftForge.EVENT_BUS.register(ServerEventHandler.INSTANCE);
    MinecraftForge.EVENT_BUS.register(new Back2FutureNewBlocksEvents());
  }
  
  public void registerEntities() {
    if (Back2Future.enableBanners)
      GameRegistry.registerTileEntity(TileEntityBanner.class, Utils.getUnlocalisedName("banner")); 
    if (Back2Future.enableArmourStand)
      ModEntityList.registerEntity(EntityArmourStand.class, "wooden_armorstand", 30, PalaMod.instance, 64, 1, true); 
    if (Back2Future.enableEndermite)
      ModEntityList.registerEntity(EntityEndermite.class, "endermite", 31, PalaMod.instance, 64, 1, true, 1447446, 7237230); 
    if (Back2Future.enableChorusFruit)
      GameRegistry.registerTileEntity(TileEntityEndRod.class, Utils.getUnlocalisedName("end_rod")); 
    if (Back2Future.enableTippedArrows)
      ModEntityList.registerEntity(EntityTippedArrow.class, "tipped_arrow", 32, PalaMod.instance, 64, 20, true); 
    if (Back2Future.enableBrewingStands)
      GameRegistry.registerTileEntity(TileEntityNewBrewingStand.class, 
          Utils.getUnlocalisedName("brewing_stand")); 
    if (Back2Future.enableColourfulBeacons)
      GameRegistry.registerTileEntity(TileEntityNewBeacon.class, 
          Utils.getUnlocalisedName("beacon")); 
    if (Back2Future.enableRabbit) {
      ModEntityList.registerEntity(EntityRabbit.class, "rabbit", 33, PalaMod.instance, 80, 3, true, 10051392, 7555121);
      List<BiomeGenBase> biomes = new LinkedList<>();
      for (BiomeGenBase biome : BiomeGenBase.func_150565_n()) {
        if (biome != null)
          for (Object obj : biome.func_76747_a(EnumCreatureType.creature)) {
            if (obj instanceof BiomeGenBase.SpawnListEntry) {
              BiomeGenBase.SpawnListEntry entry = (BiomeGenBase.SpawnListEntry)obj;
              if (entry.field_76300_b == EntityPig.class) {
                biomes.add(biome);
                break;
              } 
            } 
          }  
      } 
      if (!ServerType.FACTION.equals(CommonModule.getInstance().getConfig().getServerType()))
        EntityRegistry.addSpawn(EntityRabbit.class, 10, 3, 3, EnumCreatureType.creature, biomes
            .<BiomeGenBase>toArray(new BiomeGenBase[biomes.size()])); 
    } 
    ModEntityList.registerEntity(EntityHusk.class, "husk", 40, PalaMod.instance, 80, 3, true, 7828833, 14735761);
    EntityRegistry.removeSpawn(EntityZombie.class, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s });
    EntityRegistry.addSpawn(EntityZombie.class, 19, 4, 4, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s });
    EntityRegistry.addSpawn(EntityZombieVillager.class, 1, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s });
    EntityRegistry.addSpawn(EntityHusk.class, 80, 4, 4, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s });
    ModEntityList.registerEntity(EntityStray.class, "stray", 41, PalaMod.instance, 80, 3, true, 6387576, 15133418);
    EntityRegistry.removeSpawn(EntitySkeleton.class, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76774_n, BiomeGenBase.field_76775_o });
    EntityRegistry.addSpawn(EntitySkeleton.class, 20, 4, 4, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76774_n, BiomeGenBase.field_76775_o });
    EntityRegistry.addSpawn(EntityStray.class, 80, 4, 4, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76774_n, BiomeGenBase.field_76775_o });
    EntityRegistry.addSpawn(EntityEnderman.class, 1, 4, 4, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.field_76778_j });
    EntityEnderman.setCarriable(Blocks.field_150424_aL, true);
    if (Back2Future.enableLingeringPotions) {
      ModEntityList.registerEntity(EntityLingeringPotion.class, "lingering_potion", 34, PalaMod.instance, 64, 10, true);
      ModEntityList.registerEntity(EntityLingeringEffect.class, "lingering_effect", 35, PalaMod.instance, 64, 1, true);
    } 
    if (Back2Future.enableVillagerZombies)
      ModEntityList.registerEntity(EntityZombieVillager.class, "villager_zombie", 36, PalaMod.instance, 80, 3, true, 44975, 7969893); 
    if (Back2Future.enableDragonRespawn) {
      ModEntityList.registerEntity(EntityPlacedEndCrystal.class, "end_crystal", 37, PalaMod.instance, 256, 2147483647, false);
      ModEntityList.registerEntity(EntityRespawnedDragon.class, "ender_dragon", 38, PalaMod.instance, 160, 3, true);
    } 
    if (Back2Future.enableShearableGolems)
      ModEntityList.registerEntity(EntityNewSnowGolem.class, "snow_golem", 39, PalaMod.instance, 80, 3, true); 
    ModEntityList.registerEntity(EntityGolem.class, "Golem", 42, PalaMod.instance, 80, 3, true, 8355711, 2500134);
    EntityRegistry.registerModEntity(EntityBoulder.class, "boulder", 43, PalaMod.instance, 64, 10, true);
    EntityRegistry.registerGlobalEntityID(EntityCustomFallingBlock.class, "Falling Block", 
        EntityRegistry.findGlobalUniqueEntityId());
    EntityRegistry.registerModEntity(EntityCustomFallingBlock.class, "Falling Block", 44, PalaMod.instance, 64, 1, true);
  }
  
  public void registerRenderers() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\core\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */