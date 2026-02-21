package fr.paladium.palamod.modules.luckyblock;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.chronos.ChronosMod;
import fr.paladium.helios.Helios;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.packet.PacketUtils;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.adapters.NullOnEmptyConverterFactory;
import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import fr.paladium.palamod.modules.luckyblock.commands.AdminDanseCommand;
import fr.paladium.palamod.modules.luckyblock.commands.EndiumLuckyBlockCommand;
import fr.paladium.palamod.modules.luckyblock.commands.FamilyFirstCommand;
import fr.paladium.palamod.modules.luckyblock.commands.FoghornCommand;
import fr.paladium.palamod.modules.luckyblock.commands.LuckyBlockCommand;
import fr.paladium.palamod.modules.luckyblock.commands.LuckyBlockTestCommand;
import fr.paladium.palamod.modules.luckyblock.commands.LuckyBlockVisionCommand;
import fr.paladium.palamod.modules.luckyblock.commands.SoundTestCommand;
import fr.paladium.palamod.modules.luckyblock.commands.TourDeLaTerreurCommand;
import fr.paladium.palamod.modules.luckyblock.commands.easter.EasterEggSubCommand;
import fr.paladium.palamod.modules.luckyblock.commands.luckypass.LuckyCommand;
import fr.paladium.palamod.modules.luckyblock.commands.luckystats.SupermanCommand;
import fr.paladium.palamod.modules.luckyblock.commands.luckystats.TrophyCommand;
import fr.paladium.palamod.modules.luckyblock.commands.luckystats.TrophyFindiumCommand;
import fr.paladium.palamod.modules.luckyblock.commands.luckystats.TrophyHalloweenCommand;
import fr.paladium.palamod.modules.luckyblock.config.ConfigManager;
import fr.paladium.palamod.modules.luckyblock.config.FamilyFirstConfig;
import fr.paladium.palamod.modules.luckyblock.config.TourDeLaTerreurConfig;
import fr.paladium.palamod.modules.luckyblock.config.WishCardConfig;
import fr.paladium.palamod.modules.luckyblock.dialog.GiantFishDialogManager;
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
import fr.paladium.palamod.modules.luckyblock.entity.EntityHappyNewYearFirework;
import fr.paladium.palamod.modules.luckyblock.entity.EntityHerobrine;
import fr.paladium.palamod.modules.luckyblock.entity.EntityInvisible;
import fr.paladium.palamod.modules.luckyblock.entity.EntityLuckyPainting;
import fr.paladium.palamod.modules.luckyblock.entity.EntityMineralRabbit;
import fr.paladium.palamod.modules.luckyblock.entity.EntityNametagFirework;
import fr.paladium.palamod.modules.luckyblock.entity.EntityNervousBat;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPalachat;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPaladiumItemFrame;
import fr.paladium.palamod.modules.luckyblock.entity.EntityPricklySheep;
import fr.paladium.palamod.modules.luckyblock.entity.EntitySkeletonHorse;
import fr.paladium.palamod.modules.luckyblock.entity.EntityZombieHalloween;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityClone;
import fr.paladium.palamod.modules.luckyblock.entity.black.EntityLBVillager;
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
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityEasterRabbit;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGiantFish;
import fr.paladium.palamod.modules.luckyblock.entity.easter.EntityGoldenChicken;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityGhostHalloween;
import fr.paladium.palamod.modules.luckyblock.entity.halloween.EntityPumpkinGolem;
import fr.paladium.palamod.modules.luckyblock.entity.june.EntityShark;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityCustomCreeper;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityDarkKnight;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityStrengthStand;
import fr.paladium.palamod.modules.luckyblock.entity.projectile.EntityGhostProjectile;
import fr.paladium.palamod.modules.luckyblock.entity.projectile.EntityWeb;
import fr.paladium.palamod.modules.luckyblock.events.CaveHelmetListener;
import fr.paladium.palamod.modules.luckyblock.events.EventsManager;
import fr.paladium.palamod.modules.luckyblock.events.WishCardListener;
import fr.paladium.palamod.modules.luckyblock.events.easter.CursedChocolateEggListener;
import fr.paladium.palamod.modules.luckyblock.events.easter.RainbowSheepListener;
import fr.paladium.palamod.modules.luckyblock.events.easter.ResurrectionStoneListener;
import fr.paladium.palamod.modules.luckyblock.events.halloween.ZombieEventListener;
import fr.paladium.palamod.modules.luckyblock.events.june.DanceEventHandler;
import fr.paladium.palamod.modules.luckyblock.events.june.SoundTestEventHandler;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.hud.vision.ModuleVision;
import fr.paladium.palamod.modules.luckyblock.init.Blocks;
import fr.paladium.palamod.modules.luckyblock.init.Crafts;
import fr.paladium.palamod.modules.luckyblock.init.Items;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PoissonAvril;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework.SoftLikeALambEvent;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.ChampDeFleur;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.HommeInvisible;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.MeilleurAmi;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VaTeLaver;
import fr.paladium.palamod.modules.luckyblock.luckystats.client.ui.home.UIShopLuckyStatsHomePage;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.BBPacketShopLuckyStatsData;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.network.CSPacketLuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.luckystats.server.manager.LuckyStatsDatabaseManager;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.MonthlyManager;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.events.DialogEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.events.StructureEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.SchematicManager;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.StructureManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.entity.EntityCowSuit;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.potion.PotionPink;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.listener.MonstrousSwordListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.enchantments.EnchantmentExperience;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.TickableFeature;
import fr.paladium.palamod.modules.luckyblock.network.CSPacketHalloweenBuyCosmetic;
import fr.paladium.palamod.modules.luckyblock.network.CSSendWishCard;
import fr.paladium.palamod.modules.luckyblock.network.PacketBindSkin;
import fr.paladium.palamod.modules.luckyblock.network.PacketBruteforcerFound;
import fr.paladium.palamod.modules.luckyblock.network.PacketBruteforcerStart;
import fr.paladium.palamod.modules.luckyblock.network.PacketChatDetector;
import fr.paladium.palamod.modules.luckyblock.network.PacketClipboard;
import fr.paladium.palamod.modules.luckyblock.network.PacketClipboardUpdateInv;
import fr.paladium.palamod.modules.luckyblock.network.PacketCrash;
import fr.paladium.palamod.modules.luckyblock.network.PacketGetLuckyEvent;
import fr.paladium.palamod.modules.luckyblock.network.PacketLuckyEventBypass;
import fr.paladium.palamod.modules.luckyblock.network.PacketMobAnimation;
import fr.paladium.palamod.modules.luckyblock.network.PacketOpenMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.network.PacketOpenSafeChest;
import fr.paladium.palamod.modules.luckyblock.network.PacketOpenUltraSafeChest;
import fr.paladium.palamod.modules.luckyblock.network.PacketPotionExplose;
import fr.paladium.palamod.modules.luckyblock.network.PacketPumpkinBlur;
import fr.paladium.palamod.modules.luckyblock.network.PacketRemoveMoney;
import fr.paladium.palamod.modules.luckyblock.network.PacketSeedEventStart;
import fr.paladium.palamod.modules.luckyblock.network.PacketSendChatMessage;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.network.PacketSpookySpook;
import fr.paladium.palamod.modules.luckyblock.network.PacketStringTrap;
import fr.paladium.palamod.modules.luckyblock.network.PacketUpdateBiome;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseInversion;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseJetpack;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketWaterPistol;
import fr.paladium.palamod.modules.luckyblock.network.SCPacketUpdateHalloweenTrade;
import fr.paladium.palamod.modules.luckyblock.network.argus.PacketChannelHandlers;
import fr.paladium.palamod.modules.luckyblock.network.argus.PacketClasslist;
import fr.paladium.palamod.modules.luckyblock.network.argus.PacketOriginSniffer;
import fr.paladium.palamod.modules.luckyblock.network.christmas.PacketChristmasMockup;
import fr.paladium.palamod.modules.luckyblock.network.christmas.PacketChristmasSantaNoescroc;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketColoredParticle;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketEasterTintamarre;
import fr.paladium.palamod.modules.luckyblock.network.easter.PacketRainbowParticle;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketAlarmClock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayEmoteNoPvp;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketSoundDetector;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketGetLuckyPass;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketSetWaitingServer;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketWinLuckyPass;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PacketWinLuckyPassBypass;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassPropertiesSync;
import fr.paladium.palamod.modules.luckyblock.network.may.CSDealInBagPacket;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketBooksellerChoice;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenBookSeller;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenGuiBook;
import fr.paladium.palamod.modules.luckyblock.network.may.SCDealInBagPacket;
import fr.paladium.palamod.modules.luckyblock.potions.CustomPotion;
import fr.paladium.palamod.modules.luckyblock.potions.easter.PotionFishOne;
import fr.paladium.palamod.modules.luckyblock.potions.easter.PotionFishThree;
import fr.paladium.palamod.modules.luckyblock.potions.easter.PotionFishTwo;
import fr.paladium.palamod.modules.luckyblock.profile.ModuleLuckyBlocks;
import fr.paladium.palamod.modules.luckyblock.rabbitmq.config.RabbitConfig;
import fr.paladium.palamod.modules.luckyblock.rabbitmq.listener.FoghornMessageListener;
import fr.paladium.palamod.modules.luckyblock.rabbitmq.listener.SpeakerMessageListener;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityAlarm;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBallBasket;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBasket;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityCatDetector;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDidier;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityHunterPlant;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityJawsTrap;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityPetRock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySleepingBag;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOff;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOn;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySuperCraftingTable;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTombe;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophyFindium;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileLuckyFlower;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntityBlackTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityTrophyChristmas;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterEgg;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterGift;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityEasterTrophy;
import fr.paladium.palamod.modules.luckyblock.tileentity.easter.TileEntityMagicBell;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityHopperHalloween;
import fr.paladium.palamod.modules.luckyblock.tileentity.halloween.TileEntityTrophyHalloween;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.BungeeCordPlugin;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenConfig;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.modules.luckyblock.utils.wishcard.WishCardDataBaseManager;
import fr.paladium.palamod.modules.paladium.utils.PacketAnnotationProcessor;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palamod.util.IWeighted;
import fr.paladium.palamod.util.RandomCollection;
import fr.paladium.palamod.util.rabbitmq.RabbitCredentials;
import fr.paladium.palamod.util.rabbitmq.RabbitListener;
import fr.paladium.palamod.util.rabbitmq.RabbitService;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.profile.common.module.ProfileModules;
import java.awt.Color;
import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@ObjectHolder("palamod")
@Pulse(id = "palamod-luckyblock", description = "Paladium Lucky Block", forced = true)
@DoNotRename
public class PLuckyBlock {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.luckyblock.ClientProxy", serverSide = "fr.paladium.palamod.modules.luckyblock.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-luckyblock")
  public static PLuckyBlock instance;
  
  @SideOnly(Side.SERVER)
  public static BungeeCordPlugin bungeeCordPlugin;
  
  public static CustomPotion COW_PLAYER = new CustomPotion(25, true, Color.RED
      .getRGB(), "cowPlayer");
  
  public static CustomPotion STEVE;
  
  public static CustomPotion GROS_RELOU = new CustomPotion(26, true, Color.RED
      .getRGB(), "grosRelou");
  
  public static CustomPotion FIRE_MAN = new CustomPotion(27, true, Color.RED.getRGB(), "fireMan");
  
  public static CustomPotion BOOM_MOBS = new CustomPotion(28, true, Color.RED.getRGB(), "boomMobs");
  
  public static CustomPotion WALK_IN_MUSIC = new CustomPotion(29, false, Color.BLUE
      .getRGB(), "walkInMusic");
  
  public static CustomPotion SUPER_MAN;
  
  public static CustomPotion RAYMAN;
  
  public static CustomPotion COMMANDO;
  
  public static CustomPotion ROTATE;
  
  public static CustomPotion GHOST;
  
  public static CustomPotion GREEN;
  
  public static CustomPotion FART;
  
  public static CustomPotion CAT;
  
  public static CustomPotion HELLYEAH;
  
  public static CustomPotion SURVIVING;
  
  public static CustomPotion EXTREME_COLD;
  
  public static CustomPotion HIDE_HEAD;
  
  public static CustomPotion CANCEL_JUMP;
  
  public static CustomPotion REMOVE_HEART;
  
  public static CustomPotion MAGNETIC;
  
  public static CustomPotion TREE;
  
  public static CustomPotion FLOU;
  
  public static CustomPotion VIEW_TWO_PERSON;
  
  public static CustomPotion MARIO_MUSHROOM;
  
  public static CustomPotion ICE_GROUND;
  
  public static CustomPotion NO_TEXTURE;
  
  public static CustomPotion LAG;
  
  public static CustomPotion ANVIL_MONEY;
  
  public static CustomPotion STALACTITES;
  
  public static CustomPotion EXPLOSE;
  
  public static CustomPotion TOMBE;
  
  public static CustomPotion BAD_FACE;
  
  public static CustomPotion SOLITAIRE;
  
  public static CustomPotion H4CK3D;
  
  public static CustomPotion FLIP;
  
  public static CustomPotion PINK;
  
  public static CustomPotion CANCEL_FORWARD;
  
  public static CustomPotion QUACK;
  
  public static CustomPotion CINEMA_MUET;
  
  public static CustomPotion GANGNAM_STYLE;
  
  public static CustomPotion FIRE_WALK;
  
  public static CustomPotion POUET_POUET;
  
  public static CustomPotion RADIO;
  
  public static CustomPotion INVULNERABILITY;
  
  public static CustomPotion EYE_PATCH;
  
  public static CustomPotion PARROT;
  
  public static CustomPotion HAY_FEVER;
  
  public static CustomPotion SUNBURN;
  
  public static CustomPotion SOLAR_CREAM;
  
  public static CustomPotion BAKING;
  
  public static CustomPotion SUMMER_BODY;
  
  public static CustomPotion WORK;
  
  public static CustomPotion MONK;
  
  public static CustomPotion MONK_SECOND;
  
  public static CustomPotion[] luckyPotions;
  
  public static Enchantment EXPERIENCE;
  
  private SimpleNetworkWrapper network;
  
  private List<AbstractMonthlyModule> clientModules;
  
  private List<AbstractMonthlyModule> serverModules;
  
  private List<AbstractMonthlyModule> commonModules;
  
  public static RabbitService RABBIT;
  
  public static CreativeTabs TAB = new CreativeTabs("palamod-luckyblock") {
      public Item func_78016_d() {
        return (BlocksRegister.PALADIUM_LUCKY_BLOCK == null) ? Item.func_150898_a(Blocks.field_150348_b) : Item.func_150898_a((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK);
      }
    };
  
  public static double LuckyConst = 1.0D;
  
  public static Map<LuckyType, RandomCollection<ALuckyEvent>> events;
  
  public static Map<LuckyType, RandomCollection<ALuckyEvent>> luckypassEvents;
  
  public static LuckyStatsDatabaseManager dbManager;
  
  public static TourDeLaTerreurConfig tourDeLaTerreurConfig;
  
  public static FamilyFirstConfig familyFirstConfig;
  
  public static WishCardConfig wishCardConfig;
  
  private WishCardDataBaseManager wishCardDataBaseManager;
  
  private HalloweenConfig halloweenConfig;
  
  private Set<AbstractDialogManager> dialogManagers;
  
  public int currentPacketId;
  
  public int currentEntityId;
  
  public WishCardDataBaseManager getWishCardDataBaseManager() {
    return this.wishCardDataBaseManager;
  }
  
  public HalloweenConfig getHalloweenConfig() {
    return this.halloweenConfig;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
    if (e.getSide() == Side.SERVER) {
      try {
        this.halloweenConfig = (HalloweenConfig)JsonUtils.getFileObject(e.getModConfigurationDirectory().getAbsolutePath(), "halloween.json", HalloweenConfig.class);
      } catch (Exception e1) {
        System.err.println("[LuckyBlock] Unable to load " + e.getModConfigurationDirectory().getAbsolutePath() + "/halloween.json");
        if (!ForgeEnv.isDev())
          e1.printStackTrace(); 
      } 
      loadRabbit(e.getModConfigurationDirectory());
    } 
    ConfigManager.init();
    tourDeLaTerreurConfig = new TourDeLaTerreurConfig(e.getModConfigurationDirectory());
    familyFirstConfig = new FamilyFirstConfig(e.getModConfigurationDirectory());
    wishCardConfig = new WishCardConfig(e.getModConfigurationDirectory());
    if (e.getSide() == Side.SERVER && Constants.MOD_ENV != Constants.Environment.DEV) {
      dbManager = new LuckyStatsDatabaseManager();
      this.wishCardDataBaseManager = new WishCardDataBaseManager(wishCardConfig);
    } 
    SUPER_MAN = new CustomPotion(31, false, Color.WHITE.getRGB(), "superMan");
    RAYMAN = new CustomPotion(37, false, Color.YELLOW.getRGB(), "rayman");
    COMMANDO = new CustomPotion(38, false, Color.RED.getRGB(), "commando");
    ROTATE = new CustomPotion(39, false, Color.BLUE.getRGB(), "rotate");
    GHOST = new CustomPotion(40, true, Color.WHITE.getRGB(), "ghost");
    STEVE = new CustomPotion(42, true, Color.RED.getRGB(), "steve");
    GREEN = new CustomPotion(43, true, Color.GREEN.getRGB(), "green_effect");
    FART = new CustomPotion(44, true, Color.GREEN.getRGB(), "fart");
    CAT = new CustomPotion(45, true, Color.YELLOW.getRGB(), "cat");
    HELLYEAH = new CustomPotion(46, true, Color.RED.getRGB(), "hellYeah");
    SURVIVING = new CustomPotion(47, true, Color.RED.getRGB(), "surviving");
    EXTREME_COLD = new CustomPotion(48, true, Color.RED.getRGB(), "extremeCold");
    HIDE_HEAD = new CustomPotion(49, true, Color.RED.getRGB(), "hideHead");
    CANCEL_JUMP = new CustomPotion(50, true, Color.RED.getRGB(), "cancelJump");
    REMOVE_HEART = new CustomPotion(51, true, Color.RED.getRGB(), "removeHeart");
    MAGNETIC = new CustomPotion(52, true, Color.RED.getRGB(), "magnetic");
    TREE = new CustomPotion(53, true, Color.RED.getRGB(), "tree");
    FLOU = new CustomPotion(54, true, Color.RED.getRGB(), "flou");
    VIEW_TWO_PERSON = new CustomPotion(55, true, Color.RED.getRGB(), "veiw_two_person");
    MARIO_MUSHROOM = new CustomPotion(56, true, Color.RED.getRGB(), "mario_mushroom");
    ICE_GROUND = new CustomPotion(57, true, Color.RED.getRGB(), "ice_ground");
    NO_TEXTURE = new CustomPotion(58, true, Color.RED.getRGB(), "notexture");
    LAG = new CustomPotion(59, true, Color.RED.getRGB(), "lag");
    ANVIL_MONEY = new CustomPotion(61, true, Color.RED.getRGB(), "anvil_money");
    STALACTITES = new CustomPotion(62, true, Color.RED.getRGB(), "stalactites");
    EXPLOSE = new CustomPotion(63, true, Color.RED.getRGB(), "explose");
    TOMBE = new CustomPotion(64, true, Color.RED.getRGB(), "tombe");
    BAD_FACE = new CustomPotion(65, true, Color.RED.getRGB(), "bad_face");
    SOLITAIRE = new CustomPotion(66, true, Color.LIGHT_GRAY.getRGB(), "solitaire");
    H4CK3D = new CustomPotion(67, true, Color.GREEN.getRGB(), "h4ck3d");
    FLIP = new CustomPotion(68, true, Color.WHITE.getRGB(), "flip");
    PINK = new CustomPotion(69, true, Color.PINK.getRGB(), "pink");
    CANCEL_FORWARD = new CustomPotion(70, true, Color.GRAY.getRGB(), "cancel_forward");
    QUACK = new CustomPotion(71, true, Color.YELLOW.getRGB(), "quack");
    CINEMA_MUET = new CustomPotion(72, true, Color.GRAY.getRGB(), "cinema_muet");
    GANGNAM_STYLE = new CustomPotion(73, true, 0, "gangnam_style");
    FIRE_WALK = new CustomPotion(74, true, Color.RED.getRGB(), "fire_walk");
    POUET_POUET = new CustomPotion(75, true, Color.ORANGE.getRGB(), "pouet_pouet");
    RADIO = new CustomPotion(76, true, Color.GRAY.getRGB(), "radio");
    INVULNERABILITY = new CustomPotion(77, true, Color.RED.getRGB(), "invulnerability");
    EYE_PATCH = new CustomPotion(78, true, Color.RED.getRGB(), "eye_patch");
    PARROT = new CustomPotion(79, true, Color.RED.getRed(), "parrot");
    HAY_FEVER = new CustomPotion(80, true, Color.RED.getRed(), "hay_fever");
    SUNBURN = new CustomPotion(81, true, Color.RED.getRed(), "sunburn");
    SOLAR_CREAM = new CustomPotion(82, true, Color.RED.getRed(), "solar_cream");
    BAKING = new CustomPotion(83, true, Color.YELLOW.getRed(), "baking");
    SUMMER_BODY = new CustomPotion(84, true, Color.RED.getRed(), "summer_body");
    WORK = new CustomPotion(85, true, Color.RED.getRed(), "work");
    MONK = new CustomPotion(86, true, Color.RED.getRed(), "monk");
    MONK_SECOND = new CustomPotion(87, true, Color.RED.getRed(), "monk_second");
    luckyPotions = new CustomPotion[] { 
        COW_PLAYER, GROS_RELOU, FIRE_MAN, BOOM_MOBS, WALK_IN_MUSIC, SUPER_MAN, RAYMAN, COMMANDO, ROTATE, GHOST, 
        STEVE, FART, CAT, HELLYEAH, SURVIVING, EXTREME_COLD, HIDE_HEAD, CANCEL_JUMP, REMOVE_HEART, MAGNETIC, 
        TREE, FLOU, VIEW_TWO_PERSON, MARIO_MUSHROOM, ICE_GROUND, NO_TEXTURE, LAG, ANVIL_MONEY, STALACTITES, EXPLOSE, 
        TOMBE, BAD_FACE, SOLITAIRE, H4CK3D, FLIP, PINK, CANCEL_FORWARD, QUACK, CINEMA_MUET, GANGNAM_STYLE, 
        FIRE_WALK, POUET_POUET, RADIO, INVULNERABILITY, EYE_PATCH, PARROT, HAY_FEVER, SUNBURN, SOLAR_CREAM, BAKING, 
        SUMMER_BODY, WORK, MONK, MONK_SECOND };
    for (CustomPotion luckyPotion : luckyPotions)
      luckyPotion.setIconIndex(0, 0); 
    EXPERIENCE = (Enchantment)new EnchantmentExperience(201);
    FMLCommonHandler.instance().bus().register(new EventsManager());
    MinecraftForge.EVENT_BUS.register(new EventsManager());
    FMLCommonHandler.instance().bus().register(new CaveHelmetListener());
    MinecraftForge.EVENT_BUS.register(new CaveHelmetListener());
    ZombieEventListener zombieEventListener = new ZombieEventListener();
    FMLCommonHandler.instance().bus().register(zombieEventListener);
    MinecraftForge.EVENT_BUS.register(zombieEventListener);
    if (e.getSide() == Side.SERVER || Constants.MOD_ENV == Constants.Environment.DEV || !ForgeEnv.isDev()) {
      FMLCommonHandler.instance().bus().register(new WishCardListener());
      MinecraftForge.EVENT_BUS.register(new WishCardListener());
    } 
    FMLCommonHandler.instance().bus().register(new MonstrousSwordListener());
    MinecraftForge.EVENT_BUS.register(new MonstrousSwordListener());
    FMLCommonHandler.instance().bus().register(new DanceEventHandler());
    MinecraftForge.EVENT_BUS.register(new DanceEventHandler());
    FMLCommonHandler.instance().bus().register(new SoundTestEventHandler());
    MinecraftForge.EVENT_BUS.register(new SoundTestEventHandler());
    ResurrectionStoneListener stoneListener = new ResurrectionStoneListener();
    FMLCommonHandler.instance().bus().register(stoneListener);
    MinecraftForge.EVENT_BUS.register(stoneListener);
    RainbowSheepListener rainbowSheepListener = new RainbowSheepListener();
    FMLCommonHandler.instance().bus().register(rainbowSheepListener);
    MinecraftForge.EVENT_BUS.register(rainbowSheepListener);
    CursedChocolateEggListener cursedChocolateEggListener = new CursedChocolateEggListener();
    FMLCommonHandler.instance().bus().register(cursedChocolateEggListener);
    MinecraftForge.EVENT_BUS.register(cursedChocolateEggListener);
    this.network = PacketUtils.createNetwork("palamod-luckyblock");
    PalaMod.getNetwork().registerMessage(PacketUseLuckyBlock.Handler.class, PacketUseLuckyBlock.class, 800, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketSendChatMessage.Handler.class, PacketSendChatMessage.class, 801, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketCrash.Handler.class, PacketCrash.class, 802, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketUseJetpack.Handler.class, PacketUseJetpack.class, 806, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketUseInversion.Handler.class, PacketUseInversion.class, 807, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketSetPotionEffect.Handler.class, PacketSetPotionEffect.class, 808, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PlayerLuckyPassPropertiesSync.Handler.class, PlayerLuckyPassPropertiesSync.class, 810, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketSetWaitingServer.Handler.class, PacketSetWaitingServer.class, 811, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(CSPacketLuckyStatsReward.Handler.class, CSPacketLuckyStatsReward.class, 814, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketSeedEventStart.Handler.class, PacketSeedEventStart.class, 816, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketOpenSafeChest.Handler.class, PacketOpenSafeChest.class, 817, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOpenMegaSafeChest.Handler.class, PacketOpenMegaSafeChest.class, 818, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOpenUltraSafeChest.Handler.class, PacketOpenUltraSafeChest.class, 819, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketBruteforcerStart.Handler.class, PacketBruteforcerStart.class, 820, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketBruteforcerFound.Handler.class, PacketBruteforcerFound.class, 821, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketClasslist.Handler.class, PacketClasslist.class, 822, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketChannelHandlers.Handler.class, PacketChannelHandlers.class, 823, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOriginSniffer.Handler.class, PacketOriginSniffer.class, 824, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketSpookySpook.Handler.class, PacketSpookySpook.class, 825, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketMobAnimation.Handler.class, PacketMobAnimation.class, 826, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketWinLuckyPassBypass.Handler.class, PacketWinLuckyPassBypass.class, 827, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketLuckyEventBypass.Handler.class, PacketLuckyEventBypass.class, 828, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketBindSkin.ClientHandler.class, PacketBindSkin.class, 829, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketChatDetector.Handler.class, PacketChatDetector.class, 830, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketRemoveMoney.Handler.class, PacketRemoveMoney.class, 831, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketStringTrap.Handler.class, PacketStringTrap.class, 832, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketClipboard.Handler.class, PacketClipboard.class, 833, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketClipboardUpdateInv.Handler.class, PacketClipboardUpdateInv.class, 834, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketWaterPistol.class, PacketWaterPistol.class, 835, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketChristmasMockup.Handler.class, PacketChristmasMockup.class, 836, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketChristmasMockup.Handler.class, PacketChristmasMockup.class, 837, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketChristmasSantaNoescroc.Handler.class, PacketChristmasSantaNoescroc.class, 838, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketChristmasSantaNoescroc.Handler.class, PacketChristmasSantaNoescroc.class, 839, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketEasterTintamarre.Handler.class, PacketEasterTintamarre.class, 840, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketRainbowParticle.Handler.class, PacketRainbowParticle.class, 841, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketColoredParticle.Handler.class, PacketColoredParticle.class, 842, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketUpdateBiome.Handler.class, PacketUpdateBiome.class, 843, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketPumpkinBlur.Handler.class, PacketPumpkinBlur.class, 844, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketPotionExplose.Handler.class, PacketPotionExplose.class, 845, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOpenGuiBook.Handler.class, PacketOpenGuiBook.class, 846, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketBooksellerChoice.Handler.class, PacketBooksellerChoice.class, 847, Side.SERVER);
    PalaMod.getNetwork().registerMessage(PacketOpenCustomDialogGui.Handler.class, PacketOpenCustomDialogGui.class, 848, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketPlayCustomSound.Handler.class, PacketPlayCustomSound.class, 849, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketPlayEmoteNoPvp.Handler.class, PacketPlayEmoteNoPvp.class, 850, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketSoundDetector.Handler.class, PacketSoundDetector.class, 851, Side.CLIENT);
    PalaMod.getNetwork().registerMessage(PacketAlarmClock.Handler.class, PacketAlarmClock.class, 852, Side.SERVER);
    PacketAnnotationProcessor.registerPacket(PacketGetLuckyEvent.Handler.class, PacketGetLuckyEvent.class, Side.CLIENT);
    PacketAnnotationProcessor.registerPacket(PacketGetLuckyEvent.Handler.class, PacketGetLuckyEvent.class, Side.SERVER);
    PacketAnnotationProcessor.registerPacket(PacketGetLuckyPass.Handler.class, PacketGetLuckyPass.class, Side.CLIENT);
    PacketAnnotationProcessor.registerPacket(PacketGetLuckyPass.Handler.class, PacketGetLuckyPass.class, Side.SERVER);
    PacketAnnotationProcessor.registerPacket(PacketWinLuckyPass.Handler.class, PacketWinLuckyPass.class, Side.SERVER);
    PacketAnnotationProcessor.registerPacket(BBPacketShopLuckyStatsData.Handler.class, BBPacketShopLuckyStatsData.class, Side.CLIENT);
    PacketAnnotationProcessor.registerPacket(BBPacketShopLuckyStatsData.Handler.class, BBPacketShopLuckyStatsData.class, Side.SERVER);
    this.currentPacketId = 853;
    getCommonModules().forEach(AbstractMonthlyModule::registerPackets);
    PacketAnnotationProcessor.registerPacket(CSSendWishCard.class);
    PacketAnnotationProcessor.registerPacket(SCPacketUpdateHalloweenTrade.class);
    PacketAnnotationProcessor.registerPacket(CSPacketHalloweenBuyCosmetic.class);
    registerPacket((Class)PacketOpenBookSeller.Handler.class, PacketOpenBookSeller.class, Side.CLIENT);
    registerPacket((Class)SCDealInBagPacket.Handler.class, SCDealInBagPacket.class, Side.CLIENT);
    registerPacket((Class)CSDealInBagPacket.Handler.class, CSDealInBagPacket.class, Side.SERVER);
    GameRegistry.registerTileEntity(TileLuckyFlower.class, "luckyFlower");
    GameRegistry.registerTileEntity(TileEntityHunterPlant.class, "hunterPlant");
    GameRegistry.registerTileEntity(TileEntityBruteforcer.class, "bruteforcer");
    GameRegistry.registerTileEntity(TileEntityCatDetector.class, "tilecatdetector");
    proxy.preInit(e);
    if (e.getSide() == Side.SERVER)
      if (ForgeEnv.isDev()) {
        System.err.println("[PLuckyBlock] Disabling BungeeCordPlugin in devmode.");
      } else {
        try {
          bungeeCordPlugin = new BungeeCordPlugin();
        } catch (Exception exception) {}
      }  
    ProfileModules.getInstance().registerModule(ModuleLuckyBlocks.class);
    System.out.println("##LuckyBlock preInit");
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    Blocks.register();
    Items.register();
    if (e.getSide() == Side.SERVER && !ForgeEnv.isDev())
      dbManager.init(); 
    if (e.getSide() == Side.CLIENT) {
      Helios.getClient().addModule(ModuleVision.class);
      Helios.getClient().addModule(ModuleLuckyEvent.class);
      ShopNavbarManager.registerTab(UIShopLuckyStatsHomePage.TAB);
    } 
    if (e.getSide() == Side.SERVER) {
      Helios.getServer().addModule(ModuleVision.class);
      Helios.getServer().addModule(ModuleLuckyEvent.class);
    } 
    this.currentEntityId = 581;
    getCommonModules().forEach(module -> {
          module.registerItems();
          module.registerBlocks();
          module.registerTileEntities();
          module.registerExtendedProperties();
          module.registerEventHandlers();
          module.registerEntities();
          module.registerDialogManagers();
          module.registerLuckyStats();
          module.registerPotions();
        });
    instance.getDialogManagers().add(new GiantFishDialogManager());
    proxy.init(e);
    proxy.registerRenders();
    GameRegistry.registerTileEntity(TileEntityTrophy.class, "tileEntityTrophy");
    GameRegistry.registerTileEntity(TileEntityTrophyFindium.class, "tileEntityTrophyFindium");
    GameRegistry.registerTileEntity(TileEntityAlarm.class, "tileEntityAlarm");
    GameRegistry.registerTileEntity(TileEntityPetRock.class, "tileEntityPetRock");
    GameRegistry.registerTileEntity(TileEntityJawsTrap.class, "tileEntityJawsTrap");
    GameRegistry.registerTileEntity(TileEntityTrophyHalloween.class, "tileEntityTrophyHalloween");
    GameRegistry.registerTileEntity(TileEntityHopperHalloween.class, "tileEntityHopperHalloween");
    GameRegistry.registerTileEntity(TileEntityTrophyChristmas.class, "tileEntityTrophyChristmas");
    GameRegistry.registerTileEntity(TileEntityBlackTrophy.class, "tileEntityBlackTrophy");
    GameRegistry.registerTileEntity(TileEntityDidier.class, "tileEntityDidier");
    GameRegistry.registerTileEntity(TileEntitySleepingBag.class, "tileEntitySleepingBed");
    GameRegistry.registerTileEntity(TileEntityStringTrapOn.class, "tilestringtrapeon");
    GameRegistry.registerTileEntity(TileEntityStringTrapOff.class, "tilestringtrapoff");
    GameRegistry.registerTileEntity(TileEntityTombe.class, "tileEntityTombe");
    GameRegistry.registerTileEntity(TileEntityBasket.class, "tileEntityBasket");
    GameRegistry.registerTileEntity(TileEntityBallBasket.class, "tileEntityBallBasket");
    GameRegistry.registerTileEntity(TileEntitySuperCraftingTable.class, "tileEntitySuperCraftingTable");
    EntityRegistry.registerGlobalEntityID(EntityMineralRabbit.class, "entityMineralRabbit", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 255, 255)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityMineralRabbit.class, "entityMineralRabbit", 430, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityFakePoweredCreeper.class, "entityFakePoweredCreeper", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70))
        .getRGB(), (new Color(100, 100, 100)).getRGB());
    EntityRegistry.registerModEntity(EntityFakePoweredCreeper.class, "entityFakePoweredCreeper", 431, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntitySkeletonHorse.class, "entitySkeletonHorse", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntitySkeletonHorse.class, "entitySkeletonHorse", 432, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityNervousBat.class, "entityNervousBat", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityNervousBat.class, "entityNervousBat", 433, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityPricklySheep.class, "entityPricklySheep", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityPricklySheep.class, "entityPricklySheep", 434, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityGrappin.class, "entityGrappin", 435, PalaMod.instance, 0, 1, true);
    EntityRegistry.registerModEntity(EntityNametagFirework.class, "entityNametagFirework", 436, PalaMod.instance, 0, 1, true);
    EntityRegistry.registerModEntity(EntityBalloon.class, "entityBalloon", 437, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityPalachat.class, "entityPalachat", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 200, 15)).getRGB(), (new Color(255, 55, 10))
        .getRGB());
    EntityRegistry.registerModEntity(EntityPalachat.class, "entityPalachat", 438, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityAngryWither.class, "entityCustomWither", 439, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityLuckyPainting.class, "entityLuckyPainting", 440, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityBionicSnowball.class, "entityBionicSnowball", 441, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityInvisible.class, "entityInvisible", 442, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityHerobrine.class, "entityHerobrine", 443, PalaMod.instance, 80, 1, true);
    EntityRegistry.registerModEntity(EntityWeb.class, "web", 444, PalaMod.instance, 64, 10, true);
    EntityRegistry.registerGlobalEntityID(EntityPumpkinGolem.class, "entityPumpkinGolem", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityPumpkinGolem.class, "entityPumpkinGolem", 445, PalaMod.instance, 40, 3, true);
    EntityRegistry.registerModEntity(EntityGhostProjectile.class, "ghostprojectil", 446, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityZombieHalloween.class, "zombiehalloween", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityZombieHalloween.class, "zombiehalloween", 447, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityGhostHalloween.class, "ghosthalloween", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(100, 255, 70)).getRGB(), (new Color(100, 100, 100))
        .getRGB());
    EntityRegistry.registerModEntity(EntityGhostHalloween.class, "ghosthalloween", 448, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityChristmasCow.class, "entityChristmasCow", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 70, 70)).getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntityChristmasCow.class, "entityChristmasCow", 449, PalaMod.instance, 64, 1, true);
    int bufferEntityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntityChristmasSnowGolem.class, "entityChristmasSnowGolem", bufferEntityID, (new Color(200, 200, 200))
        .getRGB(), (new Color(255, 255, 255)).getRGB());
    EntityRegistry.registerModEntity(EntityChristmasCow.class, "entityChristmasSnowGolem", bufferEntityID, PalaMod.instance, 64, 1, true);
    bufferEntityID = EntityRegistry.findGlobalUniqueEntityId();
    EntityRegistry.registerGlobalEntityID(EntitySantaNoescroc.class, "entitySantaNoescroc", bufferEntityID, (new Color(150, 0, 0))
        .getRGB(), (new Color(255, 0, 0)).getRGB());
    EntityRegistry.registerModEntity(EntitySantaNoescroc.class, "entitySantaNoescroc", bufferEntityID, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerModEntity(EntityChristmasBall.class, "entityChristmasBall", 500, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerGlobalEntityID(EntitySantaClaus.class, "entitySantaClaus", 
        EntityRegistry.findGlobalUniqueEntityId(), (new Color(255, 70, 70)).getRGB(), (new Color(255, 255, 255))
        .getRGB());
    EntityRegistry.registerModEntity(EntitySantaClaus.class, "entitySantaClaus", 501, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerModEntity(EntityChristmasSleigh.class, "entityChristmasSleigh", 502, PalaMod.instance, 40, 1, true);
    RegistryUtils.entity(EntityFunnyWither.class, null, 100, PalaMod.instance);
    EntityRegistry.registerModEntity(EntityDove.class, "entityDove", 560, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityHappyNewYearFirework.class, "entityHappyNewYearFirework", 561, PalaMod.instance, 0, 1, true);
    EntityRegistry.registerGlobalEntityID(EntityBee.class, "entityBee", 
        EntityRegistry.findGlobalUniqueEntityId(), Color.ORANGE.getRGB(), Color.CYAN.getRGB());
    EntityRegistry.registerModEntity(EntityBee.class, "entityBee", 562, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityFakePlayer.class, "fakeplayerluckyblock", 563, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityBoatFurnace.class, "entityBoatFurnace", 564, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityMissileMeteo.class, "missileMeteoLuckyBlock", 567, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityClone.class, "cloneLuckyBlock", 566, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityPaladiumItemFrame.class, "entityPaladiumItemFrame", 565, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityPhantom.class, "phantomLuckyBlock", 568, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityLBVillager.class, "villagerLuckyBlock", 569, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityWaterDrop.class, "waterDropLuckyBlock", 570, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityEasterRabbit.class, "entityEasterRabbit", 571, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityGoldenChicken.class, "EntityGoldenChicken", 572, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityEasterEgg.class, "entityEasterEgg", 573, PalaMod.instance, 64, 1, true);
    EntityRegistry.registerModEntity(EntityBlackHole.class, "entityBlackHole", 574, PalaMod.instance, 64, 1, true);
    RegistryUtils.entity(EntityGiantFish.class, null, 52, PalaMod.instance);
    GameRegistry.registerTileEntity(TileEntityEasterGift.class, "tileEntityEasterGift");
    GameRegistry.registerTileEntity(TileEntityEasterEgg.class, "tileEntityEasterEgg");
    GameRegistry.registerTileEntity(TileEntityMagicBell.class, "tileEntityMagicBell");
    GameRegistry.registerTileEntity(TileEntityEasterTrophy.class, "tileEntityEasterTrophy");
    GameRegistry.registerTileEntity(TileEntityEasterLuckyBlock.class, "tileEntityEasterLuckyBlock");
    EntityRegistry.registerModEntity(EntityCustomCreeper.class, "entityCustomCreeper", 575, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityStrengthStand.class, "entityStrengthStand", 576, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityDarkKnight.class, "entityDarkKnight", 577, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityNPC.class, "entityNPC", 578, PalaMod.instance, 40, 1, true);
    RegistryUtils.entity(EntityCowSuit.class, Color.red, 24, PalaMod.instance);
    EntityRegistry.registerModEntity(EntityShark.class, "entityShark", 579, PalaMod.instance, 40, 1, true);
    EntityRegistry.registerModEntity(EntityDancer.class, "entityDancer", 580, PalaMod.instance, 40, 1, true);
    for (CustomPotion luckyPotion : luckyPotions) {
      luckyPotion.loadEffects();
      luckyPotion.register();
    } 
    RegistryUtils.potion(new APotion[] { PotionsRegister.FISH_ONE = (APotion)new PotionFishOne(), PotionsRegister.FISH_TWO = (APotion)new PotionFishTwo(), PotionsRegister.FISH_THREE = (APotion)new PotionFishThree(), PotionsRegister.PINK_EFFECT = (APotion)new PotionPink() });
    events = new HashMap<>();
    luckypassEvents = new HashMap<>();
    for (LuckyType type : LuckyType.values()) {
      events.put(type, new RandomCollection(new SecureRandom()));
      ((RandomCollection)events.get(type)).addAll(LuckyEvents.valuesEvents(type));
      luckypassEvents.put(type, new RandomCollection(new SecureRandom()));
      for (ALuckyEvent event : LuckyEvents.valuesEvents(type)) {
        if (event.isBad()) {
          ((RandomCollection)luckypassEvents.get(type)).add((IWeighted)event);
          continue;
        } 
        ((RandomCollection)luckypassEvents.get(type)).add((IWeighted)new ALuckyEvent() {
              public void perform(EntityPlayerMP player, int x, int y, int z) {
                event.perform(player, x, y, z);
              }
              
              public String getName() {
                return event.getName();
              }
              
              public boolean isBad() {
                return event.isBad();
              }
              
              public int getRarity() {
                return event.getRarity();
              }
              
              public String getTexture() {
                return event.getTexture();
              }
              
              public double getWeight() {
                return event.getWeight() * 1.25D;
              }
            });
      } 
    } 
    new LuckyBlockManager();
    if (e.getSide() == Side.CLIENT) {
      getClientModules().forEach(AbstractMonthlyModule::registerEventHandlers);
    } else {
      getServerModules().forEach(module -> {
            module.registerEventHandlers();
            module.registerTasks();
            module.registerTickables();
            module.registerDialogManagers();
          });
      TickableFeature.getInstance().getTickables().add(SoftLikeALambEvent.getInstance());
      TickableFeature.getInstance().getTickables().add(PoissonAvril.getInstance());
      TickableFeature.getInstance().getTickables().add(MeilleurAmi.getInstance());
      TickableFeature.getInstance().getTickables().add(VaTeLaver.getInstance());
      TickableFeature.getInstance().getTickables().add(HommeInvisible.getInstance());
      TickableFeature.getInstance().getTickables().add(ChampDeFleur.getInstance());
      Object dialogEvent = new DialogEventHandler();
      FMLCommonHandler.instance().bus().register(dialogEvent);
      MinecraftForge.EVENT_BUS.register(dialogEvent);
      Object structureEvent = new StructureEventHandler();
      FMLCommonHandler.instance().bus().register(structureEvent);
      MinecraftForge.EVENT_BUS.register(structureEvent);
      StructureManager structureManager = StructureManager.getInstance();
      structureManager.startTask();
      SchematicManager schematicManager = SchematicManager.getInstance();
      schematicManager.startTask();
      SubCommandBuilder.create("easteregg")
        .sender(new SenderType[] { SenderType.PLAYER }).string()
        .register(EasterEggSubCommand.class);
    } 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    LuckyType.Util.register();
    LuckyStatsRewardManager.register();
    Crafts.register();
    getCommonModules().forEach(AbstractMonthlyModule::registerCrafts);
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new LuckyBlockCommand());
    e.registerServerCommand((ICommand)new TourDeLaTerreurCommand());
    e.registerServerCommand((ICommand)new FamilyFirstCommand());
    e.registerServerCommand((ICommand)new EndiumLuckyBlockCommand());
    e.registerServerCommand((ICommand)new LuckyBlockVisionCommand());
    e.registerServerCommand((ICommand)new AdminDanseCommand());
    e.registerServerCommand((ICommand)new FoghornCommand());
    e.registerServerCommand((ICommand)new LuckyCommand());
    e.registerServerCommand((ICommand)new SupermanCommand());
    e.registerServerCommand((ICommand)new TrophyCommand());
    e.registerServerCommand((ICommand)new TrophyFindiumCommand());
    e.registerServerCommand((ICommand)new TrophyHalloweenCommand());
    if (e.getSide() == Side.SERVER) {
      e.registerServerCommand((ICommand)new LuckyBlockTestCommand());
      e.registerServerCommand((ICommand)new SoundTestCommand());
      getServerModules().forEach(module -> module.registerCommands(e));
    } 
  }
  
  @Handler
  public void serverStarted(FMLServerStartedEvent e) {
    if (ForgeEnv.isDev()) {
      System.err.println("[PLuckyBlock] Chronos Halloween is disabled in devmode.");
    } else {
      Retrofit eventRetrofit = (new Retrofit.Builder()).baseUrl(ChronosMod.getInstance().getApiURL() + "/events/").addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).addConverterFactory((Converter.Factory)new NullOnEmptyConverterFactory()).build();
      ApiServices.Http.setHalloweenService((PaladiumServices.HALLOWEEN)eventRetrofit.create(PaladiumServices.HALLOWEEN.class));
    } 
  }
  
  private void loadRabbit(File configDir) {
    try {
      RabbitConfig config = (RabbitConfig)JsonConfigLoader.loadConfig(new File(configDir, "luckyblockRabbitConfig.json"), RabbitConfig.class);
      RabbitCredentials rabbitCredentials = new RabbitCredentials(config.getHost(), config.getPort(), config.getUsername(), config.getPassword(), "/", 16);
      if (!ForgeEnv.isDev()) {
        RABBIT = new RabbitService(rabbitCredentials);
        RABBIT.registerListener((RabbitListener)new SpeakerMessageListener(RABBIT));
        RABBIT.registerListener((RabbitListener)new FoghornMessageListener(RABBIT));
      } else {
        System.err.println("[PLuckyBlock] RabbitMQ is disabled in dev mode.");
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public <REQ extends cpw.mods.fml.common.network.simpleimpl.IMessage, REPLY extends cpw.mods.fml.common.network.simpleimpl.IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> handler, Class<REQ> packet, Side side) {
    PalaMod.getNetwork().registerMessage(handler, packet, this.currentPacketId, side);
    this.currentPacketId++;
  }
  
  public void registerEntity(Class<? extends Entity> entityClass, String name, int range, int freq, boolean velUpdate) {
    EntityRegistry.registerModEntity(entityClass, name, this.currentEntityId, PalaMod.instance, range, freq, velUpdate);
    this.currentEntityId++;
  }
  
  public List<AbstractMonthlyModule> getModules(SideType type) {
    switch (type) {
      case CLIENT:
        return getClientModules();
      case SERVER:
        return getServerModules();
      case BOTH:
        return getCommonModules();
    } 
    return null;
  }
  
  public List<AbstractMonthlyModule> getClientModules() {
    if (this.clientModules == null)
      this.clientModules = MonthlyManager.getInstance().getModules(SideType.CLIENT); 
    return this.clientModules;
  }
  
  private List<AbstractMonthlyModule> getServerModules() {
    if (this.serverModules == null)
      this.serverModules = MonthlyManager.getInstance().getModules(SideType.SERVER); 
    return this.serverModules;
  }
  
  private List<AbstractMonthlyModule> getCommonModules() {
    if (this.commonModules == null)
      this.commonModules = MonthlyManager.getInstance().getModules(SideType.BOTH); 
    return this.commonModules;
  }
  
  public Set<AbstractDialogManager> getDialogManagers() {
    if (this.dialogManagers == null)
      this.dialogManagers = new HashSet<>(); 
    return this.dialogManagers;
  }
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\PLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */