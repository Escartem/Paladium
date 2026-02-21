package fr.paladium.palamod.modules.luckyblock;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHopperHalloween;
import fr.paladium.palamod.modules.luckyblock.config.ClientHalloweenTradeConfig;
import fr.paladium.palamod.modules.luckyblock.entity.EntityAngryWither;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBalloon;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBee;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBionicSnowball;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBoatFurnace;
import fr.paladium.palamod.modules.luckyblock.entity.EntityDove;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePlayer;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePoweredCreeper;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFunnyWither;
import fr.paladium.palamod.modules.luckyblock.entity.EntityGrappin;
import fr.paladium.palamod.modules.luckyblock.entity.EntityHerobrine;
import fr.paladium.palamod.modules.luckyblock.entity.EntityInvisible;
import fr.paladium.palamod.modules.luckyblock.entity.EntityLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.entity.EntityMineralRabbit;
import fr.paladium.palamod.modules.luckyblock.entity.EntityNervousBat;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPalachat;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPaladiumItemFrame;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPricklySheep;
import fr.paladium.palamod.modules.luckyblock.entity.EntitySkeletonHorse;
import fr.paladium.palamod.modules.luckyblock.entity.EntityZombieHalloween;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityClone;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityMissileMeteo;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityPhantom;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityWaterDrop;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasBall;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasCow;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasSleigh;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasSnowGolem;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaClaus;
import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntitySantaNoescroc;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGiantFish;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGoldenChicken;
import fr.paladium.palamod.modules.luckyblock.entity.fx.EntityFXColoredParticle;
import fr.paladium.palamod.modules.luckyblock.entity.fx.EntityFXRainbow;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityGhostHalloween;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityPumpkinGolem;
import fr.paladium.palamod.modules.luckyblock.entity.june.EntityShark;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityDarkKnight;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.entity.projectile.EntityGhostProjectile;
import fr.paladium.palamod.modules.luckyblock.entity.projectile.EntityWeb;
import fr.paladium.palamod.modules.luckyblock.gui.animations.EventAccessor;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.modules.luckyblock.gui.animations.TexturedIcon;
import fr.paladium.palamod.modules.luckyblock.gui.animations.TexturedIconAccessor;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.entities.RenderLuckyCrab;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.entities.RenderMosquito;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.SuitcaseItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.blocks.AugustLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.blocks.AugustTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.blocks.DolphinPlushRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.blocks.PrimaryCrabEggRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.items.blocks.TreadmillRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles.TileEntityAugustLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles.TileEntityAugustTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles.TileEntityDolphinPlushRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles.TileEntityPrimaryCrabEggRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles.TileEntityTreadmillBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityLuckyCrab;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityMosquito;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityVolleyBall;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityAugustLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityAugustTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityDolphinPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityPrimaryCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityTreadmill;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.blocks.FirePlaceRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.blocks.WreathRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.tiles.TileEntityFirePlaceBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.tiles.TileEntityGiftChestRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.tiles.TileEntityWreathBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityChristmasWreath;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityFirePlace;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityGiftChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.entities.RenderCaptainFlint;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.entities.RenderDutchBoat;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.BlunderblussItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.DutchBoatItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.GunSwordItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.PipeItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.TelescopeItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.WoodenLegItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.blocks.DecryptedComputerRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.blocks.EncryptedComputerRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.blocks.JulyLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.blocks.JulyTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.items.blocks.ParrotPlushRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.tiles.TileEntityDecryptedComputerRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.tiles.TileEntityEncryptedComputerRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.tiles.TileEntityJulyLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.tiles.TileEntityJulyTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.tiles.TileEntityParrotPlushRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityCaptainFlint;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityDecryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityEncryptedComputer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityJulyLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityJulyTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityParrotPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity.RenderAstronautZombie;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity.RenderBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity.SuperSonicGeoRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.inventory.AlienPlushInventoryRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.inventory.MarchLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.inventory.MarchTrophyInventoryRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.inventory.MonsterBlackHoleInventoryRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.inventory.TelescopeInventoryRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.item.RenderLaserGun;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.item.RenderPersonalThruster;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.potion.RenderMarchPotions;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile.TileEntityAlienPlushRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile.TileEntityMarchLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile.TileEntityMarchTrophyRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile.TileEntityMonsterBlackHoleRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile.TileEntityTelescopeRender;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntitySupersonicRocket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityZombieAstronaut;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityJupiterGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityMarsGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityMoonGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityAlienPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMonsterBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityTelescope;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.inventory.TileEntityCreeperPlushRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.inventory.TileEntityGoldenCageRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.inventory.TileEntityRandomStatueRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.inventory.TileEntityTrashRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.tile.TileEntityCreeperPlushRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.tile.TileEntityGoldenCageRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.tile.TileEntityRandomStatueRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.render.tile.TileEntityTrashRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityCreeperPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityGoldenCage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityRandomStatue;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile.TileEntityTrash;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.entities.RenderMimic;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.entities.RenderSlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.items.blocks.CorruptedPlantRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.items.blocks.HunterCorruptedPlantRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.items.blocks.NovemberLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.items.blocks.NovemberTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.items.blocks.PrimarySlayerEggRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityCorruptedPlantRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityFakeCorruptedChestRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityHunterCorruptedPlantRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityNovemberLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityNovemberTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles.TileEntityPrimarySlayerEggRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityCorruptedSplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityMimic;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntitySlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityFakeCorruptedChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityHunterCorruptedPlant;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityNovemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityNovemberTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityPrimarySlayerEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities.RenderBodyGuard;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities.RenderPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities.RenderTaupiko;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities.RenderWorker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.items.PaperAirplaneItemRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.items.blocks.PrimaryTaupikoEggRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.items.blocks.SeptemberLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.items.blocks.SeptemberTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.tiles.TileEntityPrimaryTaupikoEggRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.tiles.TileEntitySeptemberLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.tiles.TileEntitySeptemberTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityBodyGuard;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityTaupiko;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityWorker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntityPrimaryTaupikoEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketRainbowParticle;
import fr.paladium.palamod.modules.luckyblock.renders.AlarmRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.BallBasketRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.BasketRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.ChristmasLuckyBlockInventory;
import fr.paladium.palamod.modules.luckyblock.renders.ChristmasTreeInventory;
import fr.paladium.palamod.modules.luckyblock.renders.HalloweenTradeRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.JawsTrapRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.PetRockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.RenderAngryWither;
import fr.paladium.palamod.modules.luckyblock.renders.RenderBalloon;
import fr.paladium.palamod.modules.luckyblock.renders.RenderBee;
import fr.paladium.palamod.modules.luckyblock.renders.RenderBlackTrophy;
import fr.paladium.palamod.modules.luckyblock.renders.RenderBoatFurnace;
import fr.paladium.palamod.modules.luckyblock.renders.RenderChristmasCow;
import fr.paladium.palamod.modules.luckyblock.renders.RenderClone;
import fr.paladium.palamod.modules.luckyblock.renders.RenderDove;
import fr.paladium.palamod.modules.luckyblock.renders.RenderEntityGhostProjectile;
import fr.paladium.palamod.modules.luckyblock.renders.RenderEntityPlayerNpc;
import fr.paladium.palamod.modules.luckyblock.renders.RenderFakePlayer;
import fr.paladium.palamod.modules.luckyblock.renders.RenderFakePoweredCreeper;
import fr.paladium.palamod.modules.luckyblock.renders.RenderGadgeto;
import fr.paladium.palamod.modules.luckyblock.renders.RenderGhostHalloween;
import fr.paladium.palamod.modules.luckyblock.renders.RenderGrappin;
import fr.paladium.palamod.modules.luckyblock.renders.RenderHerobrine;
import fr.paladium.palamod.modules.luckyblock.renders.RenderInvisible;
import fr.paladium.palamod.modules.luckyblock.renders.RenderLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.renders.RenderMineralRabbit;
import fr.paladium.palamod.modules.luckyblock.renders.RenderMissileMeteo;
import fr.paladium.palamod.modules.luckyblock.renders.RenderNervousBat;
import fr.paladium.palamod.modules.luckyblock.renders.RenderPalachat;
import fr.paladium.palamod.modules.luckyblock.renders.RenderPaladiumItemFrame;
import fr.paladium.palamod.modules.luckyblock.renders.RenderPumpkinGolem;
import fr.paladium.palamod.modules.luckyblock.renders.RenderSantaClaus;
import fr.paladium.palamod.modules.luckyblock.renders.RenderSleigh;
import fr.paladium.palamod.modules.luckyblock.renders.RenderWaterPistol;
import fr.paladium.palamod.modules.luckyblock.renders.StringTrapOffRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.TombeRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.TrophyChristmasRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.TrophyFindiumRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.TrophyHalloweenRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.TrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.christmas.RenderChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.renders.christmas.RenderChristmasSnowGolem;
import fr.paladium.palamod.modules.luckyblock.renders.easter.BunnyPlushRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.easter.EasterLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.easter.EasterTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.easter.EggOfPlentyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.easter.RenderBlackHole;
import fr.paladium.palamod.modules.luckyblock.renders.easter.RenderGiantFish;
import fr.paladium.palamod.modules.luckyblock.renders.easter.RenderGoldenChicken;
import fr.paladium.palamod.modules.luckyblock.renders.june.AlarmClockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.june.JuneLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.june.JuneTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.june.RealJukeboxRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.june.RenderDancer;
import fr.paladium.palamod.modules.luckyblock.renders.june.RenderShark;
import fr.paladium.palamod.modules.luckyblock.renders.june.RenderSitar;
import fr.paladium.palamod.modules.luckyblock.renders.june.RenderSwordGuitarOfTheApocalypse;
import fr.paladium.palamod.modules.luckyblock.renders.june.SharkPlushRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.may.MayLuckyBlockRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.may.MayTrophyRenderInventory;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderDarkKnight;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderDarkKnightHelmet;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderEnergeticBowSword;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderIronSkullHammer;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderLightSaber;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderRunicAxe;
import fr.paladium.palamod.modules.luckyblock.renders.may.RenderSwordTooBig;
import fr.paladium.palamod.modules.luckyblock.renders.models.ModelGhostHalloween;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityAlarm;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityAlarmRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBallBasket;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBallBasketRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBasket;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBasketRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityCatDetector;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityChatDetector;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboardRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDidier;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDidierRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityHalloweenTradeRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityJawsTrap;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityJawsTrapRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityPetRock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityPetRockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySleepingBag;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySleepingBagRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOff;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOffRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOn;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOnRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTombe;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTombeRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophyFindium;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophyFindiumRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityUltraSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntityBlackTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasTree;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasTreeRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityTrophyChristmas;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityTrophyChristmasRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityBunnyPlush;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityBunnyPlushRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterEggRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterGift;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterGiftRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEggOfPlenty;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEggOfPlentyRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEndiumMagicBell;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEndiumMagicBellRender;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityMagicBell;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityMagicBellRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHopperHalloween;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHopperHalloweenRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityTrophyHalloween;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityTrophyHalloweenRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityJuneTrophyRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityRealJukebox;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityRealJukeboxRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntitySharkPlush;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntitySharkPlushRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayLuckyBlockRenderer;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayTrophyRenderer;
import fr.paladium.palamod.modules.paladium.client.render.ItemChestRender;
import fr.paladium.palamod.modules.paladium.client.render.RenderEntityPotion;
import fr.paladium.palawither.client.render.entity.RenderLindwormWither;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenAccessor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class ClientProxy extends CommonProxy {
  public static final ResourceLocation field_152793_a = new ResourceLocation("textures/entity/steve.png");
  
  public static boolean waitingServerResponse = false;
  
  public static int renderTrophy;
  
  public static int renderTrophyFindium;
  
  public static int renderAlarm;
  
  public static int renderPetRock;
  
  public static int renderJawsTrap;
  
  public static int renderTrophyHalloween;
  
  public static int renderTrophyChristmas;
  
  public static int renderHopperHalloween;
  
  public static int renderChristmasTree;
  
  public static int clipboardRenderID;
  
  public static int renderChristmasMockup;
  
  public static int renderDidier;
  
  public static int renderSleepingBed;
  
  public static int renderChristmasLuckyblock;
  
  public static int renderChristmasDeerMockup;
  
  public static int renderChristmasDeerRushingMockup;
  
  public static int renderChristmasDeerWalkingMockup;
  
  public static int renderSantaClausMockup;
  
  public static int renderChristmasSleighMockup;
  
  public static int renderTombe;
  
  public static int renderBallBasket;
  
  public static int renderBasket;
  
  public static int renderStringTrapOn;
  
  public static int renderStringTrapOff;
  
  public static int renderHalloweenTrade;
  
  public static int renderEasterGift;
  
  public static int renderEasterEgg;
  
  public static int renderMagicBell;
  
  public static int renderEasterTrophy;
  
  public static int renderEasterLuckyBlock;
  
  public static int renderMayTrophy;
  
  public static int renderMayLuckyBlock;
  
  public static int renderJuneTrophy;
  
  public static int renderJuneLuckyBlock;
  
  public static int renderJulyLuckyBlock;
  
  public static int renderParrotPlush;
  
  public static int renderDolphinPlush;
  
  public static int renderTreadmill;
  
  public static int renderPrimaryCrabEgg;
  
  public static int renderAugustLuckyBlock;
  
  public static int renderAugustTrophy;
  
  public static int renderSeptemberLuckyBlock;
  
  public static int renderSeptemberTrophy;
  
  public static int renderPrimaryTaupikoEgg;
  
  public static int renderNovemberLuckyBlock;
  
  public static int renderNovemberTrophy;
  
  public static int renderCorruptedPlant;
  
  public static int renderHunterCorruptedPlant;
  
  public static int renderPrimarySlayerEgg;
  
  public static int renderFirePlace;
  
  public static int renderWreath;
  
  public static int renderAlienPlush;
  
  public static int renderMarchTrophy;
  
  public static int renderTelescope;
  
  public static int renderMarchLuckyBlock;
  
  public static int renderMonsterBlackHole;
  
  public static int renderBunnyPlush;
  
  public static int renderEndiumMagicBell;
  
  public static int renderEggOfPlenty;
  
  public static int renderTrash;
  
  public static int renderCreeperPlush;
  
  public static int renderGoldenCage;
  
  public static int renderRandomStatue;
  
  public static int renderEncryptedComputer;
  
  public static int renderDecryptedComputer;
  
  public static int renderJulyTrophy;
  
  public static int renderAlarmClock;
  
  public static int renderSharkPlush;
  
  public static int renderRealJukebox;
  
  public static File cacheSkin;
  
  public static String nameVision = "";
  
  public static boolean hasVisionSkill = false;
  
  public static ItemStack[] inventoryVision = new ItemStack[64];
  
  public static double healthVision = 20.0D;
  
  public static long spooky;
  
  public static boolean calm = false;
  
  public static boolean pumpkinBlur = false;
  
  public static float playerAddedReachDistance = 0.0F;
  
  public static boolean hitmarker;
  
  public static ClientHalloweenTradeConfig configHalloween;
  
  public static boolean isBellRinging = false;
  
  public static long lastBreath = 0L;
  
  public static int blackHolePreRenderSphere;
  
  public static ResourceLocation loadSkin(int skinID, final MinecraftProfileTexture.Type p_152789_2_, final SkinManager.SkinAvailableCallback p_152789_3_) {
    TrustManager[] trustAllCerts = { new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }
          
          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
          
          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception exception) {}
    final ResourceLocation resourcelocation = new ResourceLocation("skinsCustom/" + skinID);
    ITextureObject itextureobject = Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation);
    if (itextureobject != null) {
      if (p_152789_3_ != null)
        p_152789_3_.func_152121_a(p_152789_2_, resourcelocation); 
    } else {
      File file1 = new File(cacheSkin, String.valueOf(skinID));
      File file2 = new File(file1, String.valueOf(skinID));
      final ImageBufferDownload imagebufferdownload = (p_152789_2_ == MinecraftProfileTexture.Type.SKIN) ? new ImageBufferDownload() : null;
      ThreadDownloadImageData threaddownloadimagedata = new ThreadDownloadImageData(file2, "https://cdn2.nicemarkmc.com/skins/64x64/" + skinID + ".png", field_152793_a, new IImageBuffer() {
            public BufferedImage func_78432_a(BufferedImage p_78432_1_) {
              if (imagebufferdownload != null)
                p_78432_1_ = imagebufferdownload.func_78432_a(p_78432_1_); 
              return p_78432_1_;
            }
            
            public void func_152634_a() {
              if (imagebufferdownload != null)
                imagebufferdownload.func_152634_a(); 
              if (p_152789_3_ != null)
                p_152789_3_.func_152121_a(p_152789_2_, resourcelocation); 
            }
          });
      Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, (ITextureObject)threaddownloadimagedata);
    } 
    return resourcelocation;
  }
  
  public static EntityPlayer getClientPlayer() {
    return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
  }
  
  public static void spawnRainbowParticle(PacketRainbowParticle message) {
    (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntityFXRainbow((Minecraft.func_71410_x()).field_71439_g.field_70170_p, message.x, message.y, message.z));
  }
  
  public static void spawnColoredParticle(PacketColoredParticle message) {
    (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntityFXColoredParticle((Minecraft.func_71410_x()).field_71439_g.field_70170_p, message.x, message.y, message.z, message.r, message.g, message.b, message.scale));
  }
  
  public static boolean isSoundPlaying(String soundName) {
    return Minecraft.func_71410_x().func_147118_V().func_147692_c((ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("palamod", soundName)));
  }
  
  public static void playSound(String soundName) {
    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("palamod", soundName)));
  }
  
  public static void stopSound(String soundName) {
    Minecraft.func_71410_x().func_147118_V().func_147683_b((ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("palamod", soundName)));
  }
  
  public static void stopSounds() {
    Minecraft.func_71410_x().func_147118_V().func_147690_c();
  }
  
  public static void playEmoteNoPvp(String emoteName) {}
  
  public static void updateBiome(int x, int z, byte biome) {
    WorldClient world = (Minecraft.func_71410_x()).field_71441_e;
    int relBlockX = x & 0xF;
    int relBlockZ = z & 0xF;
    world.func_72938_d(x, z).func_76605_m()[relBlockZ * 16 + relBlockX] = biome;
    (world.func_72938_d(x, z)).field_76643_l = true;
    world.func_147458_c(x, 0, z, x, 255, z);
  }
  
  public static void openBook(ItemStack book) {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiScreenBook((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, book, false));
  }
  
  public void preInit(FMLPreInitializationEvent e) {
    cacheSkin = new File(e.getModConfigurationDirectory(), "skinsCustom");
  }
  
  public void registerRenders() {
    System.out.println("[LuckyBlock][DEBUG] Renders registering");
    Tween.registerAccessor(Icon.class, (TweenAccessor)new EventAccessor());
    Tween.registerAccessor(TexturedIcon.class, (TweenAccessor)new TexturedIconAccessor());
    RenderMineralRabbit.initTextures();
    RenderingRegistry.registerEntityRenderingHandler(EntityMineralRabbit.class, (Render)new RenderMineralRabbit());
    RenderingRegistry.registerEntityRenderingHandler(EntityFakePoweredCreeper.class, (Render)new RenderFakePoweredCreeper());
    RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonHorse.class, (Render)new RenderHorse((ModelBase)new ModelHorse(), 0.75F));
    RenderingRegistry.registerEntityRenderingHandler(EntityNervousBat.class, (Render)new RenderNervousBat());
    RenderingRegistry.registerEntityRenderingHandler(EntityPricklySheep.class, (Render)new RenderSheep((ModelBase)new ModelSheep1(), (ModelBase)new ModelSheep2(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityGrappin.class, (Render)new RenderGrappin());
    RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class, (Render)new RenderHerobrine());
    RenderingRegistry.registerEntityRenderingHandler(EntityBalloon.class, (Render)new RenderBalloon());
    RenderingRegistry.registerEntityRenderingHandler(EntityPalachat.class, (Render)new RenderPalachat((ModelBase)new ModelOcelot(), 0.6F));
    RenderingRegistry.registerEntityRenderingHandler(EntityAngryWither.class, (Render)new RenderAngryWither());
    RenderingRegistry.registerEntityRenderingHandler(EntityFunnyWither.class, (Render)new RenderLindwormWither(new ResourceLocation("palamod", "models/entities/funny_wither/model.json"), new ResourceLocation("palamod", "models/entities/funny_wither/animation.json"), new ResourceLocation("palamod", "textures/entities/funny_wither.png")));
    RenderingRegistry.registerEntityRenderingHandler(EntityWeb.class, (Render)new RenderSnowball(Items.field_151126_ay));
    RenderingRegistry.registerEntityRenderingHandler(EntityLuckyPainting.class, (Render)new RenderLuckyPainting());
    RenderingRegistry.registerEntityRenderingHandler(EntityInvisible.class, (Render)new RenderInvisible());
    RenderingRegistry.registerEntityRenderingHandler(EntityBionicSnowball.class, (Render)new RenderSnowball(ItemsRegister.BIONIC_SNOWBALL));
    RenderingRegistry.registerEntityRenderingHandler(EntityChristmasBall.class, (Render)new RenderSnowball(ItemsRegister.CHRISTMAS_BALL));
    RenderingRegistry.registerEntityRenderingHandler(EntityGhostProjectile.class, (Render)new RenderEntityGhostProjectile());
    RenderingRegistry.registerEntityRenderingHandler(EntityZombieHalloween.class, (Render)new RenderZombie());
    RenderingRegistry.registerEntityRenderingHandler(EntityGhostHalloween.class, (Render)new RenderGhostHalloween((ModelBiped)new ModelGhostHalloween(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityChristmasCow.class, (Render)new RenderChristmasCow());
    RenderingRegistry.registerEntityRenderingHandler(EntityChristmasSnowGolem.class, (Render)new RenderChristmasSnowGolem());
    RenderingRegistry.registerEntityRenderingHandler(EntitySantaNoescroc.class, (Render)new RenderSantaClaus());
    RenderingRegistry.registerEntityRenderingHandler(EntitySantaClaus.class, (Render)new RenderSantaClaus());
    RenderingRegistry.registerEntityRenderingHandler(EntitySantaClaus.class, (Render)new RenderSantaClaus());
    RenderingRegistry.registerEntityRenderingHandler(EntityChristmasSleigh.class, (Render)new RenderSleigh());
    RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, (Render)new RenderBee());
    RenderingRegistry.registerEntityRenderingHandler(EntityDove.class, (Render)new RenderDove());
    RenderingRegistry.registerEntityRenderingHandler(EntityFakePlayer.class, (Render)new RenderFakePlayer());
    RenderingRegistry.registerEntityRenderingHandler(EntityClone.class, (Render)new RenderClone());
    RenderingRegistry.registerEntityRenderingHandler(EntityMissileMeteo.class, (Render)new RenderMissileMeteo());
    RenderingRegistry.registerEntityRenderingHandler(EntityWaterDrop.class, (Render)new RenderSnowball(ItemsRegister.WATER_DROP));
    RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, (Render)new RenderGhostHalloween(new ModelBiped(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(EntityPaladiumItemFrame.class, (Render)new RenderPaladiumItemFrame());
    RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, (Render)new RenderBoatFurnace());
    GameRegistry.registerTileEntityWithAlternatives(TileEntitySafeChest.class, "palamod:safeChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityMegaSafeChest.class, "palamod:megaSafeChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityUltraSafeChest.class, "palamod:ultraSafeChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityBruteforcer.class, "palamod:bruteforcer", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityCatDetector.class, "palamod:catdetector", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityChatDetector.class, "palamod:chatdetector", new String[0]);
    GameRegistry.registerTileEntity(TileEntityClipboard.class, "biblioClipboardTile");
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophy.class, (TileEntitySpecialRenderer)new TileEntityTrophyRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophyHalloween.class, (TileEntitySpecialRenderer)new TileEntityTrophyHalloweenRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophyChristmas.class, (TileEntitySpecialRenderer)new TileEntityTrophyChristmasRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlarm.class, (TileEntitySpecialRenderer)new TileEntityAlarmRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPetRock.class, (TileEntitySpecialRenderer)new TileEntityPetRockRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJawsTrap.class, (TileEntitySpecialRenderer)new TileEntityJawsTrapRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHopperHalloween.class, (TileEntitySpecialRenderer)new TileEntityHopperHalloweenRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityChristmasLuckyBlockRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasTree.class, (TileEntitySpecialRenderer)new TileEntityChristmasTreeRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophyFindium.class, (TileEntitySpecialRenderer)new TileEntityTrophyFindiumRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDidier.class, (TileEntitySpecialRenderer)new TileEntityDidierRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySleepingBag.class, (TileEntitySpecialRenderer)new TileEntitySleepingBagRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStringTrapOn.class, (TileEntitySpecialRenderer)new TileEntityStringTrapOnRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStringTrapOff.class, (TileEntitySpecialRenderer)new TileEntityStringTrapOffRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTombe.class, (TileEntitySpecialRenderer)new TileEntityTombeRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBallBasket.class, (TileEntitySpecialRenderer)new TileEntityBallBasketRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBasket.class, (TileEntitySpecialRenderer)new TileEntityBasketRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClipboard.class, (TileEntitySpecialRenderer)new TileEntityClipboardRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHalloweenTrade.class, (TileEntitySpecialRenderer)new TileEntityHalloweenTradeRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlackTrophy.class, (TileEntitySpecialRenderer)new RenderBlackTrophy());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasMockup.class, (TileEntitySpecialRenderer)new RenderChristmasMockup());
    RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinGolem.class, (Render)new RenderPumpkinGolem());
    RenderingRegistry.registerEntityRenderingHandler(EntityGoldenChicken.class, (Render)new RenderGoldenChicken());
    blackHolePreRender();
    RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, (Render)new RenderBlackHole());
    RenderingRegistry.registerEntityRenderingHandler(EntityGiantFish.class, (Render)new RenderGiantFish());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEasterGift.class, (TileEntitySpecialRenderer)new TileEntityEasterGiftRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEasterEgg.class, (TileEntitySpecialRenderer)new TileEntityEasterEggRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicBell.class, (TileEntitySpecialRenderer)new TileEntityMagicBellRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEasterTrophy.class, (TileEntitySpecialRenderer)new TileEntityEasterTrophyRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEasterLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityEasterLuckyBlockRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityDarkKnight.class, (Render)new RenderDarkKnight());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.ENERGETIC_BOW_SWORD, (IItemRenderer)new RenderEnergeticBowSword());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SWORD_TOO_BIG, (IItemRenderer)new RenderSwordTooBig());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.IRON_SKULL_HAMMER, (IItemRenderer)new RenderIronSkullHammer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.RUNIC_AXE, (IItemRenderer)new RenderRunicAxe());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.DARK_KNIGHT_HELMET, (IItemRenderer)new RenderDarkKnightHelmet());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.LIGHT_SABER, (IItemRenderer)new RenderLightSaber());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMayTrophy.class, (TileEntitySpecialRenderer)new TileEntityMayTrophyRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMayLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityMayLuckyBlockRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityNPC.class, (Render)new RenderFakePlayer());
    RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, (Render)new RenderShark());
    RenderingRegistry.registerEntityRenderingHandler(EntityDancer.class, (Render)new RenderDancer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SITAR, (IItemRenderer)new RenderSitar());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE, (IItemRenderer)new RenderSwordGuitarOfTheApocalypse());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJuneTrophy.class, (TileEntitySpecialRenderer)new TileEntityJuneTrophyRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJuneLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityJuneLuckyBlockRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRealJukebox.class, (TileEntitySpecialRenderer)new TileEntityRealJukeboxRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlarmClock.class, (TileEntitySpecialRenderer)new TileEntityAlarmClockRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySharkPlush.class, (TileEntitySpecialRenderer)new TileEntitySharkPlushRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.WATER_PISTOL, (IItemRenderer)new RenderWaterPistol());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.GADGETO, (IItemRenderer)new RenderGadgeto());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.BLACK_TROPHY), (IItemRenderer)new RenderBlackTrophy());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.CHRISTMAS_MOCKUP), (IItemRenderer)new RenderChristmasMockup());
    renderTrophy = RenderingRegistry.getNextAvailableRenderId();
    renderTrophyFindium = RenderingRegistry.getNextAvailableRenderId();
    renderAlarm = RenderingRegistry.getNextAvailableRenderId();
    renderPetRock = RenderingRegistry.getNextAvailableRenderId();
    renderJawsTrap = RenderingRegistry.getNextAvailableRenderId();
    renderTrophyHalloween = RenderingRegistry.getNextAvailableRenderId();
    renderTrophyChristmas = RenderingRegistry.getNextAvailableRenderId();
    renderHopperHalloween = RenderingRegistry.getNextAvailableRenderId();
    renderChristmasTree = RenderingRegistry.getNextAvailableRenderId();
    renderChristmasLuckyblock = RenderingRegistry.getNextAvailableRenderId();
    renderDidier = RenderingRegistry.getNextAvailableRenderId();
    renderSleepingBed = RenderingRegistry.getNextAvailableRenderId();
    renderTombe = RenderingRegistry.getNextAvailableRenderId();
    renderBallBasket = RenderingRegistry.getNextAvailableRenderId();
    renderBasket = RenderingRegistry.getNextAvailableRenderId();
    renderStringTrapOn = RenderingRegistry.getNextAvailableRenderId();
    renderStringTrapOff = RenderingRegistry.getNextAvailableRenderId();
    clipboardRenderID = RenderingRegistry.getNextAvailableRenderId();
    renderHalloweenTrade = RenderingRegistry.getNextAvailableRenderId();
    renderChristmasMockup = RenderingRegistry.getNextAvailableRenderId();
    renderEasterGift = RenderingRegistry.getNextAvailableRenderId();
    renderEasterEgg = RenderingRegistry.getNextAvailableRenderId();
    renderMagicBell = RenderingRegistry.getNextAvailableRenderId();
    renderEasterTrophy = RenderingRegistry.getNextAvailableRenderId();
    renderEasterLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    renderMayTrophy = RenderingRegistry.getNextAvailableRenderId();
    renderMayLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    renderJuneTrophy = RenderingRegistry.getNextAvailableRenderId();
    renderJuneLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    renderAlarmClock = RenderingRegistry.getNextAvailableRenderId();
    renderSharkPlush = RenderingRegistry.getNextAvailableRenderId();
    renderRealJukebox = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJulyLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityJulyLuckyBlockRenderer());
    renderJulyLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new JulyLuckyBlockRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParrotPlush.class, (TileEntitySpecialRenderer)new TileEntityParrotPlushRenderer());
    renderParrotPlush = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new ParrotPlushRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJulyTrophy.class, (TileEntitySpecialRenderer)new TileEntityJulyTrophyRenderer());
    renderJulyTrophy = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new JulyTrophyRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEncryptedComputer.class, (TileEntitySpecialRenderer)new TileEntityEncryptedComputerRenderer());
    renderEncryptedComputer = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new EncryptedComputerRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecryptedComputer.class, (TileEntitySpecialRenderer)new TileEntityDecryptedComputerRenderer());
    renderDecryptedComputer = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new DecryptedComputerRenderInventory());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SWORD_PISTOL, (IItemRenderer)new GunSwordItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.BLUNDERBUSS, (IItemRenderer)new BlunderblussItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.DUTCH_BOAT, (IItemRenderer)new DutchBoatItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PIPE, (IItemRenderer)new PipeItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.TELESCOPE, (IItemRenderer)new TelescopeItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.WOODEN_LEG, (IItemRenderer)new WoodenLegItemRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityDutchBoat.class, (Render)new RenderDutchBoat());
    RenderingRegistry.registerEntityRenderingHandler(EntityCaptainFlint.class, (Render)new RenderCaptainFlint());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SUITCASE, (IItemRenderer)new SuitcaseItemRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAugustLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityAugustLuckyBlockRenderer());
    renderAugustLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AugustLuckyBlockRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDolphinPlush.class, (TileEntitySpecialRenderer)new TileEntityDolphinPlushRenderer());
    renderDolphinPlush = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new DolphinPlushRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAugustTrophy.class, (TileEntitySpecialRenderer)new TileEntityAugustTrophyRenderer());
    renderAugustTrophy = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AugustTrophyRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimaryCrabEgg.class, (TileEntitySpecialRenderer)new TileEntityPrimaryCrabEggRenderer());
    renderPrimaryCrabEgg = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new PrimaryCrabEggRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTreadmill.class, (TileEntitySpecialRenderer)new TileEntityTreadmillBlockRenderer());
    renderTreadmill = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TreadmillRenderInventory());
    RenderingRegistry.registerEntityRenderingHandler(EntityVolleyBall.class, (Render)new RenderSnowball(ItemsRegister.VOLLEY_BALL));
    RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, (Render)new RenderMosquito());
    RenderingRegistry.registerEntityRenderingHandler(EntityLuckyCrab.class, (Render)new RenderLuckyCrab());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PAPER_AIRPLANE, (IItemRenderer)new PaperAirplaneItemRenderer());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.BOSS_SUITCASE, (IItemRenderer)new SuitcaseItemRenderer());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimaryTaupikoEgg.class, (TileEntitySpecialRenderer)new TileEntityPrimaryTaupikoEggRenderer());
    renderPrimaryTaupikoEgg = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new PrimaryTaupikoEggRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeptemberLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntitySeptemberLuckyBlockRenderer());
    renderSeptemberLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new SeptemberLuckyBlockRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeptemberTrophy.class, (TileEntitySpecialRenderer)new TileEntitySeptemberTrophyRenderer());
    renderSeptemberTrophy = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new SeptemberTrophyRenderInventory());
    RenderingRegistry.registerEntityRenderingHandler(EntityWorker.class, (Render)new RenderWorker());
    RenderingRegistry.registerEntityRenderingHandler(EntityPaperAirplane.class, (Render)new RenderPaperAirplane());
    RenderingRegistry.registerEntityRenderingHandler(EntityTaupiko.class, (Render)new RenderTaupiko());
    RenderingRegistry.registerEntityRenderingHandler(EntityBodyGuard.class, (Render)new RenderBodyGuard());
    RenderingRegistry.registerEntityRenderingHandler(EntityPaperAirplane.class, (Render)new RenderPaperAirplane());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimarySlayerEgg.class, (TileEntitySpecialRenderer)new TileEntityPrimarySlayerEggRenderer());
    renderPrimarySlayerEgg = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new PrimarySlayerEggRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNovemberLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityNovemberLuckyBlockRenderer());
    renderNovemberLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new NovemberLuckyBlockRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNovemberTrophy.class, (TileEntitySpecialRenderer)new TileEntityNovemberTrophyRenderer());
    renderNovemberTrophy = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new NovemberTrophyRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCorruptedPlant.class, (TileEntitySpecialRenderer)new TileEntityCorruptedPlantRenderer());
    renderCorruptedPlant = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new CorruptedPlantRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHunterCorruptedPlant.class, (TileEntitySpecialRenderer)new TileEntityHunterCorruptedPlantRenderer());
    renderHunterCorruptedPlant = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new HunterCorruptedPlantRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFakeCorruptedChest.class, (TileEntitySpecialRenderer)new TileEntityFakeCorruptedChestRenderer());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.FAKE_CORRUPTED_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityFakeCorruptedChest()));
    RenderingRegistry.registerEntityRenderingHandler(EntitySlayer.class, (Render)new RenderSlayer());
    RenderingRegistry.registerEntityRenderingHandler(EntityMimic.class, (Render)new RenderMimic());
    RenderingRegistry.registerEntityRenderingHandler(EntityCorruptedSplashPotion.class, (Render)new RenderEntityPotion());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFirePlace.class, (TileEntitySpecialRenderer)new TileEntityFirePlaceBlockRenderer());
    renderFirePlace = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new FirePlaceRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasWreath.class, (TileEntitySpecialRenderer)new TileEntityWreathBlockRenderer());
    renderWreath = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new WreathRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGiftChest.class, (TileEntitySpecialRenderer)new TileEntityGiftChestRenderer());
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.GIFT_CHEST), (IItemRenderer)new ItemChestRender((TileEntity)new TileEntityGiftChest()));
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PERSONAL_THRUSTER, (IItemRenderer)new RenderPersonalThruster());
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.LASER_GUN, (IItemRenderer)new RenderLaserGun());
    RenderingRegistry.registerEntityRenderingHandler(EntityJupiterGravitySplashPotion.class, (Render)new RenderMarchPotions());
    RenderingRegistry.registerEntityRenderingHandler(EntityMarsGravitySplashPotion.class, (Render)new RenderMarchPotions());
    RenderingRegistry.registerEntityRenderingHandler(EntityMoonGravitySplashPotion.class, (Render)new RenderMarchPotions());
    RenderingRegistry.registerEntityRenderingHandler(EntitySupersonicRocket.class, (Render)new SuperSonicGeoRenderer(RenderManager.field_78727_a));
    RenderingRegistry.registerEntityRenderingHandler(EntityZombieAstronaut.class, (Render)new RenderAstronautZombie());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlienPlush.class, (TileEntitySpecialRenderer)new TileEntityAlienPlushRender());
    renderAlienPlush = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AlienPlushInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMarchTrophy.class, (TileEntitySpecialRenderer)new TileEntityMarchTrophyRender());
    renderMarchTrophy = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MarchTrophyInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTelescope.class, (TileEntitySpecialRenderer)new TileEntityTelescopeRender());
    renderTelescope = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TelescopeInventoryRender());
    RenderBlackHole.blackHolePreRender();
    RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, (Render)new RenderBlackHole());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMonsterBlackHole.class, (TileEntitySpecialRenderer)new TileEntityMonsterBlackHoleRender());
    renderMonsterBlackHole = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MonsterBlackHoleInventoryRender());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMarchLuckyBlock.class, (TileEntitySpecialRenderer)new TileEntityMarchLuckyBlockRenderer());
    renderMarchLuckyBlock = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MarchLuckyBlockRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrash.class, (TileEntitySpecialRenderer)new TileEntityTrashRenderer());
    renderTrash = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityTrashRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGoldenCage.class, (TileEntitySpecialRenderer)new TileEntityGoldenCageRenderer());
    renderGoldenCage = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityGoldenCageRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCreeperPlush.class, (TileEntitySpecialRenderer)new TileEntityCreeperPlushRenderer());
    renderCreeperPlush = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityCreeperPlushRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRandomStatue.class, (TileEntitySpecialRenderer)new TileEntityRandomStatueRenderer());
    renderRandomStatue = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TileEntityRandomStatueRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEggOfPlenty.class, (TileEntitySpecialRenderer)new TileEntityEggOfPlentyRender());
    renderEggOfPlenty = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new EggOfPlentyRenderInventory());
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEndiumMagicBell.class, (TileEntitySpecialRenderer)new TileEntityEndiumMagicBellRender());
    renderEndiumMagicBell = RenderingRegistry.getNextAvailableRenderId();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBunnyPlush.class, (TileEntitySpecialRenderer)new TileEntityBunnyPlushRender());
    renderBunnyPlush = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BunnyPlushRenderInventory());
    RenderingRegistry.registerEntityRenderingHandler(EntityPlayerNPC.class, (Render)new RenderEntityPlayerNpc());
    System.out.println("RenderingRegistry() BlockPaladiumHopper");
    RenderingRegistry.registerBlockHandler(renderHopperHalloween, new ISimpleBlockRenderingHandler() {
          public boolean shouldRender3DInInventory(int modelId) {
            return false;
          }
          
          public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
            ClientProxy.this.renderBlockPaladiumHopper(renderer, (BlockHopperHalloween)block, x, y, z);
            return true;
          }
          
          public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
          
          public int getRenderId() {
            return ClientProxy.renderHopperHalloween;
          }
        });
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TrophyHalloweenRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TrophyChristmasRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TrophyRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TrophyFindiumRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AlarmRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new PetRockRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new JawsTrapRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new ChristmasLuckyBlockInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new ChristmasTreeInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BallBasketRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BasketRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new TombeRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new StringTrapOffRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new HalloweenTradeRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new EasterTrophyRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new EasterLuckyBlockRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MayTrophyRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MayLuckyBlockRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new JuneLuckyBlockRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new JuneTrophyRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new AlarmClockRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new SharkPlushRenderInventory());
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RealJukeboxRenderInventory());
    System.out.println("[LuckyBlock][DEBUG] Renders registered");
    super.registerRenders();
  }
  
  private void blackHolePreRender() {
    Sphere sphere = new Sphere();
    sphere.setDrawStyle(100012);
    sphere.setNormals(100000);
    ResourceLocation rL = new ResourceLocation("palamod:textures/entity/blackhole.png");
    blackHolePreRenderSphere = GL11.glGenLists(1);
    GL11.glNewList(blackHolePreRenderSphere, 4864);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(rL);
    sphere.draw(0.5F, 32, 32);
    GL11.glEndList();
  }
  
  public boolean renderBlockPaladiumHopper(RenderBlocks renderer, BlockHopperHalloween p_147803_1_, int p_147803_2_, int p_147803_3_, int p_147803_4_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78380_c(p_147803_1_.func_149677_c(renderer.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_));
    int l = p_147803_1_.func_149720_d(renderer.field_147845_a, p_147803_2_, p_147803_3_, p_147803_4_);
    float f = (l >> 16 & 0xFF) / 255.0F;
    float f1 = (l >> 8 & 0xFF) / 255.0F;
    float f2 = (l & 0xFF) / 255.0F;
    if (EntityRenderer.field_78517_a) {
      float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
      float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
      float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
      f = f3;
      f1 = f4;
      f2 = f5;
    } 
    tessellator.func_78386_a(f, f1, f2);
    return renderBlockPaladiumHopperMetadata(renderer, p_147803_1_, p_147803_2_, p_147803_3_, p_147803_4_, renderer.field_147845_a
        .func_72805_g(p_147803_2_, p_147803_3_, p_147803_4_), false);
  }
  
  public boolean renderBlockPaladiumHopperMetadata(RenderBlocks renderer, BlockHopperHalloween p_147799_1_, int p_147799_2_, int p_147799_3_, int p_147799_4_, int p_147799_5_, boolean p_147799_6_) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int i1 = BlockHopper.func_149918_b(p_147799_5_);
    double d0 = 0.625D;
    renderer.func_147782_a(0.0D, 0.625D, 0.0D, 1.0D, 1.0D, 1.0D);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 0, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 1, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 2, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 3, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 4, p_147799_5_));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a((Block)p_147799_1_, 5, p_147799_5_));
      tessellator.func_78381_a();
    } else {
      renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      tessellator.func_78380_c(p_147799_1_.func_149677_c(renderer.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_));
      int j1 = p_147799_1_.func_149720_d(renderer.field_147845_a, p_147799_2_, p_147799_3_, p_147799_4_);
      float f = (j1 >> 16 & 0xFF) / 255.0F;
      float f3 = (j1 >> 8 & 0xFF) / 255.0F;
      float f2 = (j1 & 0xFF) / 255.0F;
      if (EntityRenderer.field_78517_a) {
        float f6 = (f * 30.0F + f3 * 59.0F + f2 * 11.0F) / 100.0F;
        float f4 = (f * 30.0F + f3 * 70.0F) / 100.0F;
        float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
        f = f6;
        f3 = f4;
        f2 = f5;
      } 
      tessellator.func_78386_a(f, f3, f2);
    } 
    IIcon iicon = BlockHopperHalloween.getHopperIcon("hopper_outside");
    IIcon iicon1 = BlockHopperHalloween.getHopperIcon("hopper_inside");
    float f1 = 0.125F;
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, (-1.0F + f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, (1.0F - f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, (-1.0F + f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, (1.0F - f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, -0.375D, 0.0D, iicon1);
      tessellator.func_78381_a();
    } else {
      renderer.func_147764_f((Block)p_147799_1_, (p_147799_2_ - 1.0F + f1), p_147799_3_, p_147799_4_, iicon);
      renderer.func_147798_e((Block)p_147799_1_, (p_147799_2_ + 1.0F - f1), p_147799_3_, p_147799_4_, iicon);
      renderer.func_147734_d((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ - 1.0F + f1), iicon);
      renderer.func_147761_c((Block)p_147799_1_, p_147799_2_, p_147799_3_, (p_147799_4_ + 1.0F - f1), iicon);
      renderer.func_147806_b((Block)p_147799_1_, p_147799_2_, (p_147799_3_ - 1.0F) + 0.625D, p_147799_4_, iicon1);
    } 
    renderer.func_147757_a(iicon);
    double d3 = 0.25D;
    double d4 = 0.25D;
    renderer.func_147782_a(0.25D, 0.25D, 0.25D, 0.75D, 0.623D, 0.75D);
    if (p_147799_6_) {
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a((Block)p_147799_1_, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
    } else {
      renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
    } 
    if (!p_147799_6_) {
      double d1 = 0.375D;
      double d2 = 0.25D;
      renderer.func_147757_a(iicon);
      if (i1 == 0) {
        renderer.func_147782_a(0.375D, 0.0D, 0.375D, 0.625D, 0.25D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 2) {
        renderer.func_147782_a(0.375D, 0.25D, 0.0D, 0.625D, 0.5D, 0.25D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 3) {
        renderer.func_147782_a(0.375D, 0.25D, 0.75D, 0.625D, 0.5D, 1.0D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 4) {
        renderer.func_147782_a(0.0D, 0.25D, 0.375D, 0.25D, 0.5D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
      if (i1 == 5) {
        renderer.func_147782_a(0.75D, 0.25D, 0.375D, 1.0D, 0.5D, 0.625D);
        renderer.func_147784_q((Block)p_147799_1_, p_147799_2_, p_147799_3_, p_147799_4_);
      } 
    } 
    renderer.func_147771_a();
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */