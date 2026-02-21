package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.manager.LuckyStatsRewardManager;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.LuckyStatsReward;
import fr.paladium.palamod.modules.luckyblock.monthly.AbstractMonthlyModule;
import fr.paladium.palamod.modules.luckyblock.monthly.SideType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block.BlockAlienPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block.BlockMarchLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block.BlockMarchTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block.BlockMonsterBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block.BlockTelescope;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityBlackHole;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntitySupersonicRocket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntityZombieAstronaut;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityJupiterGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityMarsGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityMoonGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemGalacticPile;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemJupiterGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemMarsGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemMoonGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemStarHelmet;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.ItemSupersonicRocket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemEndiumBattery;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemLaserGun;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemMarsTicket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemMoonBoots;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemPersonalThruster;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemUniversalDollarStone;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats.ItemWarStar;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.listener.JupiterPotionListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.listener.MoonBootsListener;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSSatelliteHandlePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSShootingStarHandlePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs.CSSolarTestHandlePacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCBlackScreenPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenSatelliteUIPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenShootingStarPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenSolarTestUIPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionAlienEffect;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionInvincibility;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionItemMagnet;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionJupiterGravity;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionMarsGravity;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionMoonGravity;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionNoOxygen;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionStar;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionWarStar;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion.PotionZeroGravity;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.entity.npc.impl.EntityPlayerNPC;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommonMarch extends AbstractMonthlyModule {
  private static CommonMarch instance;
  
  public static CommonMarch getInstance() {
    return instance;
  }
  
  public CommonMarch() {
    super(SideType.BOTH, LuckyType.MARCH);
    instance = this;
  }
  
  public void registerEventHandlers() {
    registerEventHandler(new Class[] { MoonBootsListener.class, JupiterPotionListener.class });
  }
  
  public void registerPackets() {
    super.registerPackets();
    registerPacket(SCOpenSolarTestUIPacket.Handler.class, SCOpenSolarTestUIPacket.class, Side.CLIENT);
    registerPacket(SCOpenShootingStarPacket.Handler.class, SCOpenShootingStarPacket.class, Side.CLIENT);
    registerPacket(SCBlackScreenPacket.Handler.class, SCBlackScreenPacket.class, Side.CLIENT);
    registerPacket(SCOpenSatelliteUIPacket.Handler.class, SCOpenSatelliteUIPacket.class, Side.CLIENT);
    registerPacket(CSSatelliteHandlePacket.Handler.class, CSSatelliteHandlePacket.class, Side.SERVER);
    registerPacket(CSShootingStarHandlePacket.Handler.class, CSShootingStarHandlePacket.class, Side.SERVER);
    registerPacket(CSSolarTestHandlePacket.Handler.class, CSSolarTestHandlePacket.class, Side.SERVER);
  }
  
  public void registerItems() {
    super.registerItems();
    RegistryUtils.item(new Item[] { 
          ItemsRegister.STAR_HELMET = (Item)new ItemStarHelmet(), ItemsRegister.SUPERSONIC_ROCKET = (Item)new ItemSupersonicRocket(), ItemsRegister.GALACTIC_PILE = (Item)new ItemGalacticPile(), ItemsRegister.PERSONAL_THRUSTER = (Item)new ItemPersonalThruster(), ItemsRegister.MOON_BOOTS = (Item)new ItemMoonBoots(), ItemsRegister.WAR_STAR = (Item)new ItemWarStar(), ItemsRegister.UNIVERSAL_DOLLAR_STONE = (Item)new ItemUniversalDollarStone(), ItemsRegister.LASER_GUN = (Item)new ItemLaserGun(), ItemsRegister.MARCH_TICKET = (Item)new ItemMarsTicket(), ItemsRegister.ENDIUM_BATTERY = (Item)new ItemEndiumBattery(), 
          ItemsRegister.SPLASH_POTION_JUPITER_GRAVITY = (Item)new ItemJupiterGravitySplashPotion(), ItemsRegister.SPLASH_POTION_MOON_GRAVITY = (Item)new ItemMoonGravitySplashPotion(), ItemsRegister.SPLASH_POTION_MARS_GRAVITY = (Item)new ItemMarsGravitySplashPotion() });
  }
  
  public void registerBlocks() {
    super.registerBlocks();
    RegistryUtils.block(new Block[] { BlocksRegister.ALIEN_PLUSH = (Block)new BlockAlienPlush(), BlocksRegister.MONSTER_BLACK_HOLE = (Block)new BlockMonsterBlackHole(), BlocksRegister.MARCH_TROPHY = (Block)new BlockMarchTrophy(), BlocksRegister.TELESCOPE = (Block)new BlockTelescope(), BlocksRegister.MARCH_LUCKY_BLOCK = (Block)new BlockMarchLuckyBlock() });
  }
  
  public void registerTileEntities() {
    super.registerTileEntities();
    GameRegistry.registerTileEntity(TileEntityMarchLuckyBlock.class, "tileEntityMarchLuckyBlock");
  }
  
  public void registerCrafts() {
    super.registerCrafts();
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.STAR_HELMET), new Object[] { "IXI", "I I", 
          
          Character.valueOf('I'), new ItemStack(Items.field_151042_j), 
          Character.valueOf('X'), new ItemStack(Blocks.field_150426_aN) });
    GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegister.LASER_GUN), new Object[] { new ItemStack(ItemsRegister.LASER_GUN), new ItemStack(ItemsRegister.GALACTIC_PILE) });
  }
  
  public void registerExtendedProperties() {
    super.registerExtendedProperties();
    ExtendedUtils.registerExtended(EntityPlayer.class, MarchPlayer.class, "palamod_MarchPlayer", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
  }
  
  public void registerEntities() {
    super.registerEntities();
    PalaMod modInstance = PalaMod.instance;
    RegistryUtils.entity(EntityZombieAstronaut.class, null, 51, modInstance);
    RegistryUtils.entity(EntitySupersonicRocket.class, null, 51, modInstance);
    RegistryUtils.entity(EntityMarsGravitySplashPotion.class, null, 51, modInstance);
    RegistryUtils.entity(EntityJupiterGravitySplashPotion.class, null, 51, modInstance);
    RegistryUtils.entity(EntityMoonGravitySplashPotion.class, null, 51, modInstance);
    RegistryUtils.entity(EntityBlackHole.class, null, 51, modInstance);
    RegistryUtils.entity(EntityPlayerNPC.class, null, 51, PalaMod.instance);
  }
  
  public void registerPotions() {
    super.registerPotions();
    RegistryUtils.potion(new APotion[] { (APotion)(PotionsRegister.STAR = new PotionStar()), (APotion)(PotionsRegister.NO_OXYGEN = new PotionNoOxygen()), (APotion)(PotionsRegister.STAR_INVINCIBILITY = new PotionInvincibility()), (APotion)(PotionsRegister.WAR_STAR = new PotionWarStar()), (APotion)(PotionsRegister.MARS_GRAVITY = new PotionMarsGravity()), (APotion)(PotionsRegister.JUPITER_GRAVITY = new PotionJupiterGravity()), (APotion)(PotionsRegister.MOON_GRAVITY = new PotionMoonGravity()), (APotion)(PotionsRegister.ALIEN_EFFECT = new PotionAlienEffect()), (APotion)(PotionsRegister.ZERO_GRAVITY = new PotionZeroGravity()), (APotion)(PotionsRegister.ITEM_MAGNET = new PotionItemMagnet()) });
  }
  
  public void registerLuckyStats() {
    super.registerLuckyStats();
    String keyUniqueId = "63873e745b13ee37d92f79db";
    LuckyStatsRewardManager.addReward(LuckyType.MARCH, 0, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(ItemsRegister.PERSONAL_THRUSTER, 1), new LuckyStatsReward(ItemsRegister.MOON_BOOTS, 1) });
    LuckyStatsRewardManager.addReward(LuckyType.MARCH, 1, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 5), new LuckyStatsReward(BlocksRegister.MONSTER_BLACK_HOLE, 1), new LuckyStatsReward(ItemsRegister.WAR_STAR, 1), new LuckyStatsReward(ItemsRegister.WAR_STAR, 1), new LuckyStatsReward(ItemsRegister.WAR_STAR, 1), new LuckyStatsReward("63873e745b13ee37d92f79db", 1) });
    LuckyStatsRewardManager.addReward(LuckyType.MARCH, 2, new LuckyStatsReward[] { new LuckyStatsReward((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 5), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1), new LuckyStatsReward(ItemsRegister.UNIVERSAL_DOLLAR_STONE, 1), new LuckyStatsReward(ItemsRegister.LASER_GUN, 1), new LuckyStatsReward(ItemsRegister.PERSONAL_THRUSTER, 1), new LuckyStatsReward(ItemsRegister.PERSONAL_THRUSTER, 1), new LuckyStatsReward(ItemsRegister.PERSONAL_THRUSTER, 1) });
    LuckyStatsRewardManager.addReward(LuckyType.MARCH, 3, new LuckyStatsReward[] { new LuckyStatsReward(BlocksRegister.MARCH_TROPHY), new LuckyStatsReward((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 3), new LuckyStatsReward(ItemsRegister.LEGENDARYSTONE_RANDOM), new LuckyStatsReward(ItemsRegister.MARCH_TICKET), new LuckyStatsReward("63873e745b13ee37d92f79db", 5) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\CommonMarch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */