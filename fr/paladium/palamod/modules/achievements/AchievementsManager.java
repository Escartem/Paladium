package fr.paladium.palamod.modules.achievements;

import com.github.abrarsyed.secretroomsmod.common.SecretRooms;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.pojo.types.BreakBlockAchievement;
import fr.paladium.achievement.core.pojo.types.CraftAchievement;
import fr.paladium.achievement.core.pojo.types.MultiAchievement;
import fr.paladium.achievement.core.pojo.types.PlayerLevelupAchievement;
import fr.paladium.achievement.core.pojo.types.WalkDistanceAchievement;
import fr.paladium.palaforgeutils.lib.extended.ExtendedProperty;
import fr.paladium.palaforgeutils.lib.extended.ExtendedUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.listeners.AchievementsListeners;
import fr.paladium.palamod.modules.achievements.listeners.BlockPlaceListener;
import fr.paladium.palamod.modules.achievements.listeners.BreakItemListener;
import fr.paladium.palamod.modules.achievements.listeners.PetListener;
import fr.paladium.palamod.modules.achievements.listeners.PickupListener;
import fr.paladium.palamod.modules.achievements.listeners.TradeListener;
import fr.paladium.palamod.modules.achievements.listeners.WitherListener;
import fr.paladium.palamod.modules.achievements.network.data.AchievementExtraPlayer;
import fr.paladium.palamod.modules.achievements.tasks.AchievementTask;
import fr.paladium.palamod.modules.achievements.types.ArmorSetEquipAchievement;
import fr.paladium.palamod.modules.achievements.types.BlockPlaceAchievement;
import fr.paladium.palamod.modules.achievements.types.BreakItemAchievement;
import fr.paladium.palamod.modules.achievements.types.BreakSpawnerFinderAchievement;
import fr.paladium.palamod.modules.achievements.types.CaptureMountAchievement;
import fr.paladium.palamod.modules.achievements.types.CobbleBreakerAchievement;
import fr.paladium.palamod.modules.achievements.types.CraftCauldronAchievement;
import fr.paladium.palamod.modules.achievements.types.CresusReachBalanceAchievement;
import fr.paladium.palamod.modules.achievements.types.CresusWithdrawAchievement;
import fr.paladium.palamod.modules.achievements.types.CrusherAchievement;
import fr.paladium.palamod.modules.achievements.types.DimensionMinerActionAchievement;
import fr.paladium.palamod.modules.achievements.types.EggHuntActionAchievement;
import fr.paladium.palamod.modules.achievements.types.EnchantItemAchievement;
import fr.paladium.palamod.modules.achievements.types.EquipBaclavaHelmetAchievement;
import fr.paladium.palamod.modules.achievements.types.ExplodeEndiumTnTAchievement;
import fr.paladium.palamod.modules.achievements.types.ExtractSeveAchievement;
import fr.paladium.palamod.modules.achievements.types.ExtractSeveTypeAchievement;
import fr.paladium.palamod.modules.achievements.types.GrinderCraftAchievement;
import fr.paladium.palamod.modules.achievements.types.HammerDuplicateAchievement;
import fr.paladium.palamod.modules.achievements.types.HurtBossAchievement;
import fr.paladium.palamod.modules.achievements.types.InitCauldronAchievement;
import fr.paladium.palamod.modules.achievements.types.ModifierApplyAchievement;
import fr.paladium.palamod.modules.achievements.types.PetReachLevelAchievement;
import fr.paladium.palamod.modules.achievements.types.PickupItemAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopBuyAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopSellAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopSellCountAchievement;
import fr.paladium.palamod.modules.achievements.types.SpellActionAchievement;
import fr.paladium.palamod.modules.achievements.types.SummonWitherAchievement;
import fr.paladium.palamod.modules.achievements.types.TradeMoneyAchievement;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.achievements.types.UseUnclaimFinderAchievement;
import fr.paladium.palamod.modules.achievements.types.VisitServerAchievement;
import fr.paladium.palamod.modules.achievements.types.WriteMailAchievemnt;
import fr.paladium.palamod.modules.achievements.types.faction.FactionClaimAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.FactionReachEloAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.FactionReachLevelAchievement;
import fr.paladium.palamod.modules.achievements.types.faction.OwnFactionTownAchievement;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Blocks;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palapass.common.achievement.PalapassReachPointsAchievement;
import java.util.Base64;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class AchievementsManager {
  private static AchievementTask achievementTask;
  
  public static void performCraftAchievementCheck(EntityPlayer player, ItemStack itemStack, int quantity) {
    CraftAchievement.performCheck(itemStack, quantity, player);
  }
  
  public static void registerAchievements() {
    CraftAchievement crafttools1 = CraftAchievement.builder().id("palamod.craft.crafttools.1").category(AchievementCategory.HOW_TO_START).name("Craft I").description("Crafter une pioche en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_PICKAXE)).icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==").build();
    CraftAchievement crafttools2 = CraftAchievement.builder().id("palamod.craft.crafttools.2").category(AchievementCategory.HOW_TO_START).name("Craft II").description("Crafter une hache en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_AXE)).icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==").build();
    CraftAchievement crafttools3 = CraftAchievement.builder().id("palamod.craft.crafttools.3").category(AchievementCategory.HOW_TO_START).name("Craft III").description("Crafter une pelle en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_SHOVEL)).icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==").build();
    MultiAchievement.builder().id("palamod.craft.crafttools").category(AchievementCategory.HOW_TO_START)
      .name("Outillage").description("Crafter vos premiers outils en paladium")
      .icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==").build().addSubAchievement((Achievement)crafttools1)
      .addSubAchievement((Achievement)crafttools2).addSubAchievement((Achievement)crafttools3).register();
    CraftAchievement craftarmor1 = CraftAchievement.builder().id("palamod.craft.craftarmor.1").category(AchievementCategory.HOW_TO_START).name("Craft I").description("Crafter un casque en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_HELMET)).build();
    CraftAchievement craftarmor2 = CraftAchievement.builder().id("palamod.craft.craftarmor.2").category(AchievementCategory.HOW_TO_START).name("Craft II").description("Crafter un plastron en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE)).build();
    CraftAchievement craftarmor3 = CraftAchievement.builder().id("palamod.craft.craftarmor.3").category(AchievementCategory.HOW_TO_START).name("Craft III").description("Crafter une jambière en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_LEGGINGS)).build();
    CraftAchievement craftarmor4 = CraftAchievement.builder().id("palamod.craft.craftarmor.4").category(AchievementCategory.HOW_TO_START).name("Craft IV").description("Crafter des bottes en paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_BOOTS)).build();
    MultiAchievement.builder().id("palamod.craft.craftarmor").category(AchievementCategory.HOW_TO_START)
      .name("Bien s'équiper").description("Crafter votre première armure en paladium")
      .icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0uY2hlc3RwbGF0ZTowIw==").build().addSubAchievement((Achievement)craftarmor1)
      .addSubAchievement((Achievement)craftarmor2).addSubAchievement((Achievement)craftarmor3).addSubAchievement((Achievement)craftarmor4)
      .register();
    VisitServerAchievement visitserver1 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.1").category(AchievementCategory.HOW_TO_START).name("I").description("Egopolis").nbToValidate(1).serverName("Egopolis").build();
    VisitServerAchievement visitserver2 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.2").category(AchievementCategory.HOW_TO_START).name("II").description("Aeloria").nbToValidate(1).serverName("Aeloria").build();
    VisitServerAchievement visitserver3 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.3").category(AchievementCategory.HOW_TO_START).name("III").description("Xanoth").nbToValidate(1).serverName("Xanoth").build();
    VisitServerAchievement visitserver4 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Kilmordra").nbToValidate(1).serverName("Kilmordra").build();
    VisitServerAchievement visitserver5 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.5").category(AchievementCategory.HOW_TO_START).name("V").description("Runegard").nbToValidate(1).serverName("Runegard").build();
    VisitServerAchievement visitserver6 = VisitServerAchievement.builder().id("palamod.visitservers.allservers.6").category(AchievementCategory.HOW_TO_START).name("VI").description("Minage").nbToValidate(1).serverName("Minage").build();
    MultiAchievement.builder().id("palamod.visitservers.allservers").category(AchievementCategory.HOW_TO_START)
      .name("Explorateur dimensionnel").description("Explorer tous les serveurs")
      .icon(Base64.getEncoder()
        .encodeToString(ItemStackSerializer.write(new ItemStack(Items.field_151111_aL)).getBytes()).toString())
      .build().addSubAchievement((Achievement)visitserver1).addSubAchievement((Achievement)visitserver2).addSubAchievement((Achievement)visitserver3)
      .addSubAchievement((Achievement)visitserver4).addSubAchievement((Achievement)visitserver5).addSubAchievement((Achievement)visitserver6)
      .register();
    BreakBlockAchievement startrich1 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.1").category(AchievementCategory.HOW_TO_START).name("I").description("Miner 32 minerais de fer").block(Blocks.field_150366_p).nbToValidate(32).build();
    BreakBlockAchievement startrich2 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.2").category(AchievementCategory.HOW_TO_START).name("II").description("Miner 16 minerais d'or").block(Blocks.field_150352_o).nbToValidate(16).build();
    BreakBlockAchievement startrich3 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.3").category(AchievementCategory.HOW_TO_START).name("III").description("Miner 5 minerais de diamant").block(Blocks.field_150482_ag).nbToValidate(5).build();
    BreakBlockAchievement startrich4 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Miner 20 minerais d'améthyste").block(PWorld.AMETHYST_ORE).nbToValidate(20).build();
    BreakBlockAchievement startrich5 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.5").category(AchievementCategory.HOW_TO_START).name("V").description("Miner 12 minerais de titane").block(PWorld.TITANE_ORE).nbToValidate(12).build();
    BreakBlockAchievement startrich6 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.6").category(AchievementCategory.HOW_TO_START).name("VI").description("Miner 6 minerais de paladium").block(PWorld.PALADIUM_ORE).nbToValidate(6).build();
    BreakBlockAchievement startrich7 = BreakBlockAchievement.builder().id("palamod.breakblock.startrich.7").category(AchievementCategory.HOW_TO_START).name("VII").description("Miner 2 minerais de findium").block(PWorld.FINDIUM_ORE).nbToValidate(2).build();
    MultiAchievement.builder().id("palamod.breakblock.startrich").category(AchievementCategory.HOW_TO_START)
      .name("Début de la richesse !").description("Miner les minerais demandés")
      .icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==").build().addSubAchievement((Achievement)startrich1)
      .addSubAchievement((Achievement)startrich2).addSubAchievement((Achievement)startrich3).addSubAchievement((Achievement)startrich4)
      .addSubAchievement((Achievement)startrich5).addSubAchievement((Achievement)startrich6).addSubAchievement((Achievement)startrich7).register();
    WalkDistanceAchievement.builder().id("palamod.walkdistance.30k").category(AchievementCategory.HOW_TO_START)
      .name("Longue marche").description("Marcher sur une distance de 30 000 blocs").nbToValidate(30000)
      .icon("MTpwYWxhbW9kOml0ZW0udHJhdmVsYm9vdHM6MCM=").build().register();
    PlayerLevelupAchievement enchantItem1 = PlayerLevelupAchievement.builder().id("palamod.enchant.firstenchant.1").category(AchievementCategory.HOW_TO_START).name("I").description("Atteindre le niveau 30").level(30).nbToValidate(1).build();
    EnchantItemAchievement enchantItem2 = EnchantItemAchievement.builder().id("palamod.enchant.firstenchant.2").category(AchievementCategory.HOW_TO_START).name("II").description("Enchanter une pioche en paladium").itemStack(new ItemStack(ItemsRegister.PALADIUM_PICKAXE)).nbToValidate(1).build();
    MultiAchievement.builder().id("palamod.enchant.firstenchant").category(AchievementCategory.HOW_TO_START)
      .name("Premier enchantement").description("Réaliser votre premier enchantement")
      .icon(Base64.getEncoder()
        .encodeToString(ItemStackSerializer.write(new ItemStack((Item)Items.field_151134_bR)).getBytes())
        .toString())
      .build().addSubAchievement((Achievement)enchantItem1).addSubAchievement((Achievement)enchantItem2).register();
    CraftAchievement craftgrinder1 = CraftAchievement.builder().id("palamod.craft.craftgrinder.1").category(AchievementCategory.HOW_TO_START).name("I").description("Crafter un grinder bloc").nbToValidate(1).itemStack(new ItemStack((Block)PSRegister_Blocks.GRINDER_BLOCK)).build();
    CraftAchievement craftgrinder2 = CraftAchievement.builder().id("palamod.craft.craftgrinder.2").category(AchievementCategory.HOW_TO_START).name("II").description("Crafter 5 grinder casing").nbToValidate(5).itemStack(new ItemStack((Block)PSRegister_Blocks.GRINDER_CASING_BLOCK)).build();
    CraftAchievement craftgrinder3 = CraftAchievement.builder().id("palamod.craft.craftgrinder.3").category(AchievementCategory.HOW_TO_START).name("III").description("Crafter 20 grinder frame").nbToValidate(20).itemStack(new ItemStack((Block)PSRegister_Blocks.GRINDER_FRAME_BLOCK)).build();
    MultiAchievement.builder().id("palamod.craft.craftgrinder").category(AchievementCategory.HOW_TO_START)
      .name("Etablissement du Grinder")
      .description("Crafter tous les blocs nécessaires pour la création d'un grinder")
      .icon("MTpwYWxhbW9kOnRpbGUuZ3JpbmRlcl9ibG9jazowIw==").build().addSubAchievement((Achievement)craftgrinder1)
      .addSubAchievement((Achievement)craftgrinder2).addSubAchievement((Achievement)craftgrinder3).register();
    CraftAchievement luckyhammer1 = CraftAchievement.builder().id("palamod.craft.luckyhammer.1").category(AchievementCategory.HOW_TO_START).name("I").description("Crafter un hammer pattern").nbToValidate(1).itemStack(new ItemStack((Item)PSRegister_Items.PATERN_HAMMER)).build();
    GrinderCraftAchievement luckyhammer2 = GrinderCraftAchievement.builder().id("palamod.craft.luckyhammer.2").category(AchievementCategory.HOW_TO_START).name("II").description("Crafter un hammer head").nbToValidate(1).itemStack(new ItemStack((Item)PSRegister_Items.HEAD_HAMMER)).build();
    GrinderCraftAchievement luckyhammer3 = GrinderCraftAchievement.builder().id("palamod.craft.luckyhammer.3").category(AchievementCategory.HOW_TO_START).name("III").description("Crafter un hammer en paladium").nbToValidate(1).itemStack(new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM)).build();
    ModifierApplyAchievement luckyhammer4 = ModifierApplyAchievement.builder().id("palamod.craft.luckyhammer.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Appliquer à un hammer en paladium 2 modifieurs de fortune").nbToValidate(2).itemStack(new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM)).modifier(new ItemStack((Item)PSRegister_Items.MODIFIER_FORTUNE)).build();
    ModifierApplyAchievement luckyhammer5 = ModifierApplyAchievement.builder().id("palamod.craft.luckyhammer.5").category(AchievementCategory.HOW_TO_START).name("V").description("Appliquer à un hammer en paladium 1 modifieur de fusion").nbToValidate(1).itemStack(new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM)).modifier(new ItemStack((Item)PSRegister_Items.MODIFIER_SMELT)).build();
    MultiAchievement.builder().id("palamod.craft.luckyhammer").category(AchievementCategory.HOW_TO_START)
      .name("Marteau de fortune").description("Apprendre à dupliquer légalement")
      .icon("MTpwYWxhbW9kOml0ZW0uaGFtbWVyLnBhbGFkaXVtOjAj").build().addSubAchievement((Achievement)luckyhammer1)
      .addSubAchievement((Achievement)luckyhammer2).addSubAchievement((Achievement)luckyhammer3).addSubAchievement((Achievement)luckyhammer4)
      .addSubAchievement((Achievement)luckyhammer5).register();
    WriteMailAchievemnt.builder().id("palamod.mail.write").category(AchievementCategory.HOW_TO_START)
      .name("Ecrivain").description("Ecrire un mail depuis la mailbox").nbToValidate(1)
      .icon(Base64.getEncoder()
        .encodeToString(ItemStackSerializer.write(new ItemStack(Items.field_151099_bA)).getBytes())
        .toString())
      .build().register();
    HammerDuplicateAchievement hammerduplicate1 = HammerDuplicateAchievement.builder().id("palamod.hammer.hammerduplicate.1").category(AchievementCategory.HOW_TO_START).name("I").description("Dupliquer 5 minerais en améthyste").nbToValidate(5).block(PWorld.AMETHYST_ORE).build();
    HammerDuplicateAchievement hammerduplicate2 = HammerDuplicateAchievement.builder().id("palamod.hammer.hammerduplicate.2").category(AchievementCategory.HOW_TO_START).name("II").description("Dupliquer 5 minerais de titane").nbToValidate(5).block(PWorld.TITANE_ORE).build();
    HammerDuplicateAchievement hammerduplicate3 = HammerDuplicateAchievement.builder().id("palamod.hammer.hammerduplicate.3").category(AchievementCategory.HOW_TO_START).name("III").description("Dupliquer 5 minerais de paladium").nbToValidate(5).block(PWorld.PALADIUM_ORE).build();
    MultiAchievement.builder().id("palamod.hammer.hammerduplicate").category(AchievementCategory.HOW_TO_START)
      .name("Duplication en cours")
      .description("Dupliquer avec un hammer fortune et smelt les minerais demandés")
      .icon("MTpwYWxhbW9kOml0ZW0uaGFtbWVyLnBhbGFkaXVtOjAj").build().addSubAchievement((Achievement)hammerduplicate1)
      .addSubAchievement((Achievement)hammerduplicate2).addSubAchievement((Achievement)hammerduplicate3).register();
    ShopSellAchievement shopper1 = ShopSellAchievement.builder().id("palamod.shop.sell.1").category(AchievementCategory.HOW_TO_START).name("I").description("Effectuer une vente au shop").nbToValidate(1).build();
    ShopBuyAchievement shopper2 = ShopBuyAchievement.builder().id("palamod.shop.buy.2").category(AchievementCategory.HOW_TO_START).name("II").description("Effectuer un achat au shop").nbToValidate(1).build();
    MultiAchievement.builder().id("palamod.shop.buysell").category(AchievementCategory.HOW_TO_START)
      .name("Shoppeur").description("Utiliser le shop").icon("MTpjdXN0b21ucGNzOm5wY01vbmV5OjAj").build()
      .addSubAchievement((Achievement)shopper1).addSubAchievement((Achievement)shopper2).register();
    EggHuntActionAchievement egg1 = EggHuntActionAchievement.builder().id("palamod.egg.hurtdragon.1").category(AchievementCategory.HOW_TO_START).name("I").description("Taper le dragon lors du Egg Hunt").nbToValidate(1).action(1).build();
    EggHuntActionAchievement egg2 = EggHuntActionAchievement.builder().id("palamod.egg.dragonroll.2").category(AchievementCategory.HOW_TO_START).name("II").description("Utiliser le parchemin de dragon pendant une chasse").nbToValidate(1).action(2).build();
    EggHuntActionAchievement egg3 = EggHuntActionAchievement.builder().id("palamod.egg.take.3").category(AchievementCategory.HOW_TO_START).name("III").description("Récupérer un oeuf pendant un Egg Hunt").nbToValidate(3).action(3).build();
    MultiAchievement.builder().id("palamod.egg.participate").category(AchievementCategory.HOW_TO_START)
      .name("Une belle omelette").description("Participer à un Egg Hunt").icon("MTpwYWxhbW9kOmVnZzo2Iw==")
      .build().addSubAchievement((Achievement)egg1).addSubAchievement((Achievement)egg2).addSubAchievement((Achievement)egg3).register();
    HurtBossAchievement.builder().id("palamod.egg.hurtboss").category(AchievementCategory.HOW_TO_START)
      .name("C'est qui le boss").description("Combattre un boss dans une arène").nbToValidate(1)
      .icon("MTpwYWxhbW9kOml0ZW0uYnJvYWRzd29yZC5wYWxhZGl1bTowI3ttb2RpZmllcnNtYXg6NCx1cGdyYWRlYXJyYXk6WzAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCxdLG1vZGlmaWVyc2FtbW91bnQ6MH0=")
      .build().register();
    SpellActionAchievement spell1 = SpellActionAchievement.builder().id("palamod.spell.unlock.1").category(AchievementCategory.HOW_TO_START).name("I").description("Dévérrouiller un sort").nbToValidate(1).action(1).build();
    SpellActionAchievement spell2 = SpellActionAchievement.builder().id("palamod.spell.use.1").category(AchievementCategory.HOW_TO_START).name("II").description("Utiliser un sort").nbToValidate(1).action(2).build();
    MultiAchievement.builder().id("palamod.spell.unlockuser").category(AchievementCategory.HOW_TO_START)
      .name("Apprenti sorcier").description("Apprendre un sort et l'utiliser")
      .icon("MTpwYWxhbW9kOmVnZzo2Iw==").build().addSubAchievement((Achievement)spell1).addSubAchievement((Achievement)spell2)
      .register();
    CresusReachBalanceAchievement.builder().id("palamod.cresus.balance.10k").category(AchievementCategory.ECONOMY)
      .name("Le porte-monnaie en sueur").description("Posséder 10 000 $").nbToValidate(1).points(10000)
      .icon("MTpjdXN0b21ucGNzOm5wY01vbmV5OjAj").build().register();
    CresusReachBalanceAchievement.builder().id("palamod.cresus.balance.100k").category(AchievementCategory.ECONOMY)
      .name("Money money money").description("Posséder 100 000 $").nbToValidate(1).points(100000)
      .icon("MTpjdXN0b21ucGNzOm5wY01vbmV5OjAj").build().register();
    CresusReachBalanceAchievement.builder().id("palamod.cresus.balance.500k").category(AchievementCategory.ECONOMY)
      .name("Je possède des thunes").description("Posséder 500 000 $").nbToValidate(1).points(500000)
      .icon("MTpjdXN0b21ucGNzOm5wY01vbmV5OjAj").build().register();
    CresusReachBalanceAchievement.builder().id("palamod.cresus.balance.1m").category(AchievementCategory.ECONOMY)
      .name("Millionnaire").description("Posséder 1 000 000 $").nbToValidate(1).points(1000000)
      .icon("MTpjdXN0b21ucGNzOm5wY01vbmV5OjAj").build().register();
    ShopSellCountAchievement.builder().id("palamod.shop.sell.50k").category(AchievementCategory.ECONOMY)
      .name("Shoppeur expérimenté").description("Vendre pour 50 000 $ de ressources à l'admin shop")
      .nbToValidate(50000).icon("MTpwYWxhbW9kOml0ZW0ubnBjTW9uZXk6MCM=").build().register();
    CraftAchievement.builder().id("palamod.craft.extractor").category(AchievementCategory.JOBS)
      .name("Extraire par ci, par là, partout").description("Crafter 50 extracteurs").nbToValidate(50)
      .icon("MTpwYWxhbW9kOmV4dHJhY3RvcjowIw==")
      .itemStack(new ItemStack((Block)BlocksRegister.EXTRACTOR)).build().register();
    CraftAchievement.builder().id("palamod.craft.flasks").category(AchievementCategory.JOBS).name("Verrier")
      .description("Crafter 1000 fioles d'alchimiste").nbToValidate(1000).icon("MTpwYWxhbW9kOmZsYXNrOjAj")
      .itemStack(new ItemStack((Item)ItemsRegister.FLASK)).build().register();
    ExtractSeveAchievement.builder().id("palamod.seve.count.1m").category(AchievementCategory.JOBS)
      .name("Petite exploitation de sève").description("Extraire 1 000 000 unités de sèves")
      .nbToValidate(1000000).icon("MTpwYWxhbW9kOmZsYXNrOjEj").build().register();
    ExtractSeveAchievement.builder().id("palamod.seve.count.5m").category(AchievementCategory.JOBS)
      .name("Moyenne exploitation de sève").description("Extraire 5 000 000 unités de sèves")
      .nbToValidate(5000000).icon("MTpwYWxhbW9kOmZsYXNrOjMj").build().register();
    ExtractSeveAchievement.builder().id("palamod.seve.count.30m").category(AchievementCategory.JOBS)
      .name("Grande exploitation de sève").description("Extraire 30 000 000 unités de sèves")
      .nbToValidate(30000000).icon("MTpwYWxhbW9kOmZsYXNrOjQj").build().register();
    ExtractSeveAchievement.builder().id("palamod.seve.count.200m").category(AchievementCategory.JOBS)
      .name("Gigantesque exploitation de sève").description("Extraire 200 000 000 unités de sèves")
      .nbToValidate(200000000).icon("MTpwYWxhbW9kOmZsYXNrOjYj").build().register();
    CraftCauldronAchievement glueball1 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.1").category(AchievementCategory.HOW_TO_START).name("I").description("Crafter 10 Green Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.GREEN_GLUEBALL)).build();
    CraftCauldronAchievement glueball2 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.2").category(AchievementCategory.HOW_TO_START).name("II").description("Crafter 10 Blue Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.BLUE_GLUEBALL)).build();
    CraftCauldronAchievement glueball3 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.3").category(AchievementCategory.HOW_TO_START).name("III").description("Crafter 10 Yellow Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.YELLOW_GLUEBALL)).build();
    CraftCauldronAchievement glueball4 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Crafter 10 Red Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.RED_GLUEBALL)).build();
    CraftCauldronAchievement glueball5 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.5").category(AchievementCategory.HOW_TO_START).name("V").description("Crafter 10 Orange Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.ORANGE_GLUEBALL)).build();
    CraftCauldronAchievement glueball6 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.6").category(AchievementCategory.HOW_TO_START).name("VI").description("Crafter 10 Gray Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.GRAY_GLUEBALL)).build();
    CraftCauldronAchievement glueball7 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.7").category(AchievementCategory.HOW_TO_START).name("VII").description("Crafter 10 Purple Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.PURPLE_GLUEBALL)).build();
    CraftCauldronAchievement glueball8 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.8").category(AchievementCategory.HOW_TO_START).name("VIII").description("Crafter 10 Green Flash Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.GREEN_FLASH_GLUEBALL)).build();
    CraftCauldronAchievement glueball9 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.9").category(AchievementCategory.HOW_TO_START).name("IX").description("Crafter 10 Green Dark Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.GREEN_DARK_GLUEBALL)).build();
    CraftCauldronAchievement glueball10 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.glueball.10").category(AchievementCategory.HOW_TO_START).name("X").description("Crafter 10 Cyan Glueball").nbToValidate(10).itemStack(new ItemStack((Item)ItemsRegister.CYAN_GLUEBALL)).build();
    MultiAchievement.builder().id("palamod.craftcauldron.gluballall").category(AchievementCategory.JOBS)
      .name("Chewing gum maison").description("Crafter 10 exemplaires de chaque glueball")
      .icon("MTpwYWxhbW9kOmdsdWViYWxsX3BhdHRlcm46MCM=").build().addSubAchievement((Achievement)glueball1)
      .addSubAchievement((Achievement)glueball2).addSubAchievement((Achievement)glueball3).addSubAchievement((Achievement)glueball4)
      .addSubAchievement((Achievement)glueball5).addSubAchievement((Achievement)glueball6).addSubAchievement((Achievement)glueball7)
      .addSubAchievement((Achievement)glueball8).addSubAchievement((Achievement)glueball9).addSubAchievement((Achievement)glueball10).register();
    DimensionMinerActionAchievement dimminer1 = DimensionMinerActionAchievement.builder().id("palamod.dimensionminer.portal.1").category(AchievementCategory.HOW_TO_START).name("I").description("Activer le portail de la Dimension mineur").nbToValidate(1).action(1).build();
    DimensionMinerActionAchievement dimminer2 = DimensionMinerActionAchievement.builder().id("palamod.dimensionminer.goinportal.2").category(AchievementCategory.HOW_TO_START).name("II").description("Arriver dans la Dimension mineur").nbToValidate(1).action(2).build();
    DimensionMinerActionAchievement dimminer3 = DimensionMinerActionAchievement.builder().id("palamod.dimensionminer.commandimension.3").category(AchievementCategory.HOW_TO_START).name("III").description("Effectuer la commande /dimension").nbToValidate(3).action(3).build();
    MultiAchievement.builder().id("palamod.dimensionminer.all").category(AchievementCategory.HOW_TO_START)
      .name("Transdimensionnelle").description("Rentrer dans la Dimension mineur")
      .icon("MzpwYWxhbW9kOnRpbGUud2l0aGVyZWRfb2JzaWRpYW46MCM=").build().addSubAchievement((Achievement)dimminer1)
      .addSubAchievement((Achievement)dimminer2).addSubAchievement((Achievement)dimminer3).register();
    ExtractSeveTypeAchievement seve1 = ExtractSeveTypeAchievement.builder().id("palamod.seve.extract.1").category(AchievementCategory.JOBS).name("I").description("Récupérer une fiole de sève de jacaranda").nbToValidate(1).seve("Jacaranda").build();
    ExtractSeveTypeAchievement seve2 = ExtractSeveTypeAchievement.builder().id("palamod.seve.extract.2").category(AchievementCategory.JOBS).name("II").description("Récupérer une fiole de sève de judeecercis").nbToValidate(1).seve("Judeecercis").build();
    ExtractSeveTypeAchievement seve3 = ExtractSeveTypeAchievement.builder().id("palamod.seve.extract.3").category(AchievementCategory.JOBS).name("III").description("Récupérer une fiole de sève d'érable").nbToValidate(1).seve("Erable").build();
    ExtractSeveTypeAchievement seve4 = ExtractSeveTypeAchievement.builder().id("palamod.seve.extract.4").category(AchievementCategory.JOBS).name("IV").description("Récupérer une fiole de sève de ostrya").nbToValidate(1).seve("Ostrya").build();
    MultiAchievement.builder().id("palamod.seve.extractall").category(AchievementCategory.JOBS)
      .name("Il y en a pour tous").description("Extraire tous les types de sèves")
      .icon("MTpwYWxhbW9kOmZsYXNrOjYj").build().addSubAchievement((Achievement)seve1).addSubAchievement((Achievement)seve2)
      .addSubAchievement((Achievement)seve3).addSubAchievement((Achievement)seve4).register();
    InitCauldronAchievement.builder().id("palamod.cauldron.init").category(AchievementCategory.JOBS)
      .name("Chaudron de sorcier").description("Créer votre chaudron").nbToValidate(1)
      .icon("MTpwYWxhbW9kOmNhdWxkcm9uX2NvcmU6MCM=").build().register();
    UseItemAchievement portal1 = UseItemAchievement.builder().id("palamod.portal.init.1").category(AchievementCategory.JOBS).name("I").description("Activer un portail en améthyste").nbToValidate(1).itemStack(new ItemStack((Item)ItemsRegister.AMETHYSTE_PORTAL_KEY)).build();
    UseItemAchievement portal2 = UseItemAchievement.builder().id("palamod.portal.init.2").category(AchievementCategory.JOBS).name("II").description("Activer un portail en titane").nbToValidate(1).itemStack(new ItemStack((Item)ItemsRegister.TITANE_PORTAL_KEY)).build();
    UseItemAchievement portal3 = UseItemAchievement.builder().id("palamod.portal.init.3").category(AchievementCategory.JOBS).name("III").description("Activer un portail en paladium").nbToValidate(1).itemStack(new ItemStack((Item)ItemsRegister.PALADIUM_PORTAL_KEY)).build();
    UseItemAchievement portal4 = UseItemAchievement.builder().id("palamod.portal.init.4").category(AchievementCategory.JOBS).name("IV").description("Activer un portail en endium").nbToValidate(1).itemStack(new ItemStack((Item)ItemsRegister.ENDIUM_PORTAL_KEY)).build();
    MultiAchievement.builder().id("palamod.portal.init.all").category(AchievementCategory.JOBS)
      .name("Activateur de portail").description("Activer tous les types de portail")
      .icon("MTpwYWxhbW9kOmVuZGl1bV9wb3J0YWxfa2V5OjAj").build().addSubAchievement((Achievement)portal1)
      .addSubAchievement((Achievement)portal2).addSubAchievement((Achievement)portal3).addSubAchievement((Achievement)portal4).register();
    CraftCauldronAchievement pollen1 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.pollen.1").category(AchievementCategory.HOW_TO_START).name("I").description("Récupérer un pollen d'améthyste").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.AMETHYST_POLLEN)).build();
    CraftCauldronAchievement pollen2 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.pollen.2").category(AchievementCategory.HOW_TO_START).name("II").description("Récupérer un pollen de titane").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.TITANE_POLLEN)).build();
    CraftCauldronAchievement pollen3 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.pollen.3").category(AchievementCategory.HOW_TO_START).name("III").description("Récupérer un pollen de paladium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.PALADIUM_POLLEN)).build();
    CraftCauldronAchievement pollen4 = CraftCauldronAchievement.builder().id("palamod.craftcauldron.pollen.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Récupérer un pollen d'endium").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.ENDIUM_POLLEN)).build();
    MultiAchievement.builder().id("palamod.craftcauldron.pollenall").category(AchievementCategory.JOBS)
      .name("Pollinisation").description("Obtenir tous les types de pollen")
      .icon("NjQ6cGFsYW1vZDppdGVtLmVuZGl1bV9wb2xsZW46MCM=").build().addSubAchievement((Achievement)pollen1)
      .addSubAchievement((Achievement)pollen2).addSubAchievement((Achievement)pollen3).addSubAchievement((Achievement)pollen4).register();
    UseItemAchievement.builder().id("palamod.useitem.cavernhammer").category(AchievementCategory.JOBS)
      .name("Créateur de spawner").description("Récupérer 200 âmes").nbToValidate(200)
      .discriminator("CAVERN_HAMMER_SOUL").icon("MTpwYWxhbW9kOnRpbGUuZW1wdHlfbW9iX3NwYXduZXI6MCM=").build()
      .register();
    UseItemAchievement.builder().id("palamod.useitem.totem.fuel").category(AchievementCategory.JOBS)
      .name("Totémiste").description("Alimenter 1 000 fois un totem en paladium").nbToValidate(1000)
      .discriminator("FUEL_PALADIUM_TOTEM").icon("MTpwYWxhbW9kOnRpbGUudG90ZW06MCM=").build().register();
    CraftAchievement.builder().id("palamod.craft.bread").category(AchievementCategory.JOBS).name("Boulangerie")
      .description("Crafter 2 000 pains").nbToValidate(2000).itemStack(new ItemStack(Items.field_151025_P))
      .icon("MTptaW5lY3JhZnQ6YnJlYWQ6MCM=").build().register();
    BreakBlockAchievement vanilla1 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.1").category(AchievementCategory.JOBS).name("I").description("Casser 10 minerais de charbon").nbToValidate(10).block(Blocks.field_150365_q).build();
    BreakBlockAchievement vanilla2 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.2").category(AchievementCategory.JOBS).name("II").description("Casser 10 minerais de fer").nbToValidate(10).block(Blocks.field_150366_p).build();
    BreakBlockAchievement vanilla3 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.3").category(AchievementCategory.JOBS).name("III").description("Casser 10 minerais de lapis lazuli").nbToValidate(10).block(Blocks.field_150369_x).build();
    BreakBlockAchievement vanilla4 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.4").category(AchievementCategory.JOBS).name("IV").description("Casser 10 minerais de diamant").nbToValidate(10).block(Blocks.field_150482_ag).build();
    BreakBlockAchievement vanilla5 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.5").category(AchievementCategory.JOBS).name("V").description("Casser 10 minerais de redstone").nbToValidate(10).block(Blocks.field_150439_ay).build();
    BreakBlockAchievement vanilla6 = BreakBlockAchievement.builder().id("palamod.breakblock.vanilla.6").category(AchievementCategory.JOBS).name("VI").description("Casser 10 minerais d'émeraude").nbToValidate(10).block(Blocks.field_150412_bA).build();
    MultiAchievement.builder().id("palamod.breakblock.vanilla.all").category(AchievementCategory.JOBS)
      .name("Vanilladdict").description("Miner 10 exemplaires de chaque minerai vanilla")
      .icon("MTptaW5lY3JhZnQ6aXJvbl9vcmU6MCM=").build().addSubAchievement((Achievement)vanilla1)
      .addSubAchievement((Achievement)vanilla2).addSubAchievement((Achievement)vanilla3).addSubAchievement((Achievement)vanilla4)
      .addSubAchievement((Achievement)vanilla5).addSubAchievement((Achievement)vanilla6).register();
    BreakBlockAchievement modded1 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.1").category(AchievementCategory.JOBS).name("I").description("Casser 10 minerais d'améthyste").nbToValidate(10).block(PWorld.AMETHYST_ORE).build();
    BreakBlockAchievement modded2 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.2").category(AchievementCategory.JOBS).name("II").description("Casser 10 minerais de titane").nbToValidate(10).block(PWorld.TITANE_ORE).build();
    BreakBlockAchievement modded3 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.3").category(AchievementCategory.JOBS).name("III").description("Casser 10 minerais de paladium").nbToValidate(10).block(PWorld.PALADIUM_ORE).build();
    BreakBlockAchievement modded4 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.4").category(AchievementCategory.JOBS).name("IV").description("Casser 10 minerais de paladium vert").nbToValidate(10).block(PWorld.PALADIUM_GREEN_ORE).build();
    BreakBlockAchievement modded5 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.5").category(AchievementCategory.JOBS).name("V").description("Casser 10 minerais de trixium").nbToValidate(10).block(PWorld.TRIXIUM_ORE).build();
    BreakBlockAchievement modded6 = BreakBlockAchievement.builder().id("palamod.breakblock.modded.6").category(AchievementCategory.JOBS).name("VI").description("Casser 10 minerais de findium").nbToValidate(10).block(PWorld.FINDIUM_ORE).build();
    MultiAchievement.builder().id("palamod.breakblock.modded.all").category(AchievementCategory.JOBS)
      .name("Minez moddé !").description("Miner 10 exemplaires de chaque minerai moddé")
      .icon("NjQ6cGFsYW1vZDp0aWxlLnBhbGFkaXVtLm9yZTowIw==").build().addSubAchievement((Achievement)modded1)
      .addSubAchievement((Achievement)modded2).addSubAchievement((Achievement)modded3).addSubAchievement((Achievement)modded4)
      .addSubAchievement((Achievement)modded5).addSubAchievement((Achievement)modded6).register();
    CraftAchievement seeds2 = CraftAchievement.builder().id("palamod.breakblock.seeds.2").category(AchievementCategory.JOBS).name("I").description("Crafter 10 graines de citrouilles").nbToValidate(10).itemStack(new ItemStack(Items.field_151080_bb)).build();
    CraftAchievement seeds3 = CraftAchievement.builder().id("palamod.breakblock.seeds.3").category(AchievementCategory.JOBS).name("II").description("Crafter 10 graines de melon").nbToValidate(10).itemStack(new ItemStack(Items.field_151081_bc)).build();
    UseItemAchievement seeds4 = UseItemAchievement.builder().id("palamod.breakblock.seeds.4").category(AchievementCategory.JOBS).name("III").description("Obtenir 10 graines d'eggplant").nbToValidate(10).discriminator("OBTAIN_SEEDS_EGGPLANT").build();
    UseItemAchievement seeds5 = UseItemAchievement.builder().id("palamod.breakblock.seeds.5").category(AchievementCategory.JOBS).name("IV").description("Obtenir 10 graines de chervil").nbToValidate(10).discriminator("OBTAIN_SEEDS_CHERVIL").build();
    UseItemAchievement seeds6 = UseItemAchievement.builder().id("palamod.breakblock.seeds.6").category(AchievementCategory.JOBS).name("V").description("Obtenir 10 graines de kiwano").nbToValidate(10).discriminator("OBTAIN_SEEDS_KIWANO").build();
    UseItemAchievement seeds7 = UseItemAchievement.builder().id("palamod.breakblock.seeds.7").category(AchievementCategory.JOBS).name("VI").description("Obtenir 10 graines d'orangeblue").nbToValidate(10).discriminator("OBTAIN_SEEDS_ORANGEBLUE").build();
    MultiAchievement.builder().id("palamod.breakblock.seeds.all").category(AchievementCategory.JOBS)
      .name("Qui sème les graines...").description("Obtenir 10 exemplaires de chaque graine")
      .icon("MTpwYWxhbW9kOml0ZW0uc2VlZC5vcmFuZ2VibHVlOjAj").build()
      
      .addSubAchievement((Achievement)seeds2).addSubAchievement((Achievement)seeds3).addSubAchievement((Achievement)seeds4).addSubAchievement((Achievement)seeds5)
      .addSubAchievement((Achievement)seeds6).addSubAchievement((Achievement)seeds7).register();
    CrusherAchievement crusher1 = CrusherAchievement.builder().id("palamod.crusher.1").category(AchievementCategory.HOW_TO_START).name("I").description("Crusher des eggplants").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.FRUIT_EGGPLANT)).build();
    CrusherAchievement crusher2 = CrusherAchievement.builder().id("palamod.crusher.2").category(AchievementCategory.HOW_TO_START).name("II").description("Crusher des chervils").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.FRUIT_CHERVIL)).build();
    CrusherAchievement crusher3 = CrusherAchievement.builder().id("palamod.crusher.3").category(AchievementCategory.HOW_TO_START).name("III").description("Crusher des kiwanos").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.FRUIT_KIWANO)).build();
    CrusherAchievement crusher4 = CrusherAchievement.builder().id("palamod.crusher.4").category(AchievementCategory.HOW_TO_START).name("IV").description("Crusher des orangeblue").nbToValidate(1).itemStack(new ItemStack(ItemsRegister.FRUIT_ORANGEBLUE)).build();
    MultiAchievement.builder().id("palamod.crusher.all").category(AchievementCategory.JOBS)
      .name("...crushe les fruits !").description("Broyez des fruits dans le crusher")
      .icon("MTpwYWxhbW9kOnRpbGUuY3J1c2hlcjowIw==").build().addSubAchievement((Achievement)crusher1)
      .addSubAchievement((Achievement)crusher2).addSubAchievement((Achievement)crusher3).addSubAchievement((Achievement)crusher4).register();
    UseItemAchievement.builder().id("palamod.useitem.builderwand").category(AchievementCategory.JOBS)
      .name("Constructeurs de l'extrême !").description("Utiliser 1 000 fois un outil de construction")
      .nbToValidate(1000).discriminator("BUILDER_WAND").icon("MTpwYWxhbW9kOml0ZW0uYnVpbGRlcl93YW5kOjIj")
      .build().register();
    UseItemAchievement.builder().id("palamod.useitem.voidstone").category(AchievementCategory.JOBS)
      .name("Disparition totale").description("Supprimer 1 000 000 de blocs avec une minage voidstone")
      .nbToValidate(1000000).discriminator("VOID_STONE_MINAGE")
      .icon("MTpwYWxhbW9kOml0ZW0udm9pZHN0b25lLm1pbmFnZTowI3tzdG9uZToxMzQsfQ==").build().register();
    CobbleBreakerAchievement.builder().id("palamod.cobblebreaker.particle").category(AchievementCategory.JOBS)
      .name("Trésor dans la pierre").description("Récupérer 5 000 particules").nbToValidate(5000)
      .icon("NjQ6cGFsYW1vZDp0aWxlLmNvYmJsZUJyZWFrZXI6MCM=").build().register();
    UseItemAchievement.builder().id("palamod.useitem.spawnerfinder").category(AchievementCategory.JOBS)
      .name("Chercheur assisté").description("Utiliser 10 fois un spawner finder").nbToValidate(10)
      .discriminator("SPAWNER_FINDER").icon("MTpwYWxhbW9kOml0ZW0uc3Bhd25lcl9maW5kZXI6MCM=").build()
      .register();
    CraftAchievement.builder().id("palamod.craft.sealedxp").category(AchievementCategory.JOBS)
      .name("Mise en bouteille").description("Crafter 500 bouteilles d'XP").nbToValidate(500)
      .itemStack(new ItemStack(ItemsRegister.SEALED_XP_BOTTLE))
      .icon("MTpwYWxhbW9kOml0ZW0uc2VhbGVkX3hwX2JvdHRsZTowIw==").build().register();
    CraftAchievement.builder().id("palamod.craft.amethystbackpack").category(AchievementCategory.JOBS)
      .name("Transport dominical").description("Crafter un backpack en améthyste").nbToValidate(1)
      .itemStack(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 0))
      .icon("MTpwYWxhbW9kOml0ZW0uaHVudGVyX2JhY2twYWNrOjAj").build().register();
    UseItemAchievement.builder().id("palamod.useitem.bamboo.feed").category(AchievementCategory.JOBS)
      .name("Élevage d'éléphants").description("Donner 7 500 bambous à des éléphants").nbToValidate(7500)
      .discriminator("BAMBOO").icon("MTpwYWxhbW9kOnRpbGUuYmFtYm9vX2Jsb2NrOjAj").build().register();
    UseItemAchievement capture1 = UseItemAchievement.builder().id("palamod.capturestone.1").category(AchievementCategory.JOBS).name("I").description("Capturer un zombie").nbToValidate(1).discriminator("CAPTURE_EntityZombie").build();
    UseItemAchievement capture2 = UseItemAchievement.builder().id("palamod.capturestone.2").category(AchievementCategory.JOBS).name("II").description("Capturer un creeper").nbToValidate(1).discriminator("CAPTURE_EntityCreeper").build();
    UseItemAchievement capture3 = UseItemAchievement.builder().id("palamod.capturestone.3").category(AchievementCategory.JOBS).name("III").description("Capturer une tortue").nbToValidate(1).discriminator("CAPTURE_EntityTurtle").build();
    UseItemAchievement capture4 = UseItemAchievement.builder().id("palamod.capturestone.4").category(AchievementCategory.JOBS).name("IV").description("Capturer un crabe").nbToValidate(1).discriminator("CAPTURE_EntityCrab").build();
    UseItemAchievement capture5 = UseItemAchievement.builder().id("palamod.capturestone.5").category(AchievementCategory.JOBS).name("V").description("Capturer un escargot").nbToValidate(1).discriminator("CAPTURE_EntitySnail").build();
    UseItemAchievement capture6 = UseItemAchievement.builder().id("palamod.capturestone.6").category(AchievementCategory.JOBS).name("VI").description("Capturer un dauphin").nbToValidate(1).discriminator("CAPTURE_EntityDolphin").build();
    UseItemAchievement capture7 = UseItemAchievement.builder().id("palamod.capturestone.7").category(AchievementCategory.JOBS).name("VII").description("Capturer un éléphant").nbToValidate(1).discriminator("CAPTURE_EntityElephant").build();
    MultiAchievement.builder().id("palamod.capturestone.all").category(AchievementCategory.JOBS)
      .name("Zoo original").description("Capturer des créatures")
      .icon("MTpwYWxhbW9kOml0ZW0uY2FwdHVyZV9zdG9uZToxIw==").build().addSubAchievement((Achievement)capture1)
      .addSubAchievement((Achievement)capture2).addSubAchievement((Achievement)capture3).addSubAchievement((Achievement)capture4)
      .addSubAchievement((Achievement)capture5).addSubAchievement((Achievement)capture6).addSubAchievement((Achievement)capture7).register();
    UseItemAchievement minibosskill1 = UseItemAchievement.builder().id("palamod.miniboss.kill.1").category(AchievementCategory.JOBS).name("I").description("Vaincre le mini-boss Poulet Fermier").nbToValidate(1).discriminator("MINI_BOSS_KILL_FARMER_CHICKEN").build();
    UseItemAchievement minibosskill2 = UseItemAchievement.builder().id("palamod.miniboss.kill.2").category(AchievementCategory.JOBS).name("II").description("Vaincre le mini-boss Zombie Caverneux").nbToValidate(1).discriminator("MINI_BOSS_KILL_CAVERNOUS_ZOMBIE").build();
    UseItemAchievement minibosskill3 = UseItemAchievement.builder().id("palamod.miniboss.kill.3").category(AchievementCategory.JOBS).name("III").description("Vaincre le mini-boss Méga Creeper").nbToValidate(1).discriminator("MINI_BOSS_KILL_MEGA_CREEPER").build();
    UseItemAchievement minibosskill4 = UseItemAchievement.builder().id("palamod.miniboss.kill.4").category(AchievementCategory.JOBS).name("IV").description("Vaincre le mini-boss Monstre Fleur").nbToValidate(1).discriminator("MINI_BOSS_KILL_FLOWER_MONSTER").build();
    MultiAchievement.builder().id("palamod.miniboss.killall").category(AchievementCategory.JOBS)
      .name("Petite proie, dur combat").description("Vaincre les quatre mini boss")
      .icon("MTpwYWxhbW9kOml0ZW0uaHVudGVyX2FtdWxldDowIw==").build().addSubAchievement((Achievement)minibosskill1)
      .addSubAchievement((Achievement)minibosskill2).addSubAchievement((Achievement)minibosskill3).addSubAchievement((Achievement)minibosskill4)
      .register();
    CraftAchievement.builder().id("palamod.craft.endiumtotem").category(AchievementCategory.JOBS)
      .name("Un sacrifice qui vaut de l'endium").description("Crafter un totem en endium").nbToValidate(1)
      .itemStack(new ItemStack(BlocksRegister.ENDIUM_TOTEM))
      .icon("MTpwYWxhbW9kOnRpbGUuZW5kaXVtX3RvdGVtOjAj").build();
    UseUnclaimFinderAchievement.builder().id("palamod.unclaimfinder.1h")
      .category(AchievementCategory.ATTACK_DEFENSE).name("Chercheur de base débutant")
      .description("Utiliser pendant 60 min un unclaim finder").nbToValidate(3600)
      .icon("MTpwYWxhbW9kOml0ZW0udW5jbGFpbWZpbmRlcjoyMCM=").build().register();
    UseUnclaimFinderAchievement.builder().id("palamod.unclaimfinder.200mn")
      .category(AchievementCategory.ATTACK_DEFENSE).name("Chercheur de base professionnel")
      .description("Utiliser pendant 200 min un unclaim finder").nbToValidate(12000)
      .icon("MTpwYWxhbW9kOml0ZW0udW5jbGFpbWZpbmRlcl9vcmFuZ2U6MjAj").build().register();
    UseUnclaimFinderAchievement.builder().id("palamod.unclaimfinder.800mn")
      .category(AchievementCategory.ATTACK_DEFENSE).name("Chercheur de base expert")
      .description("Utiliser pendant 800 min un unclaim finder").nbToValidate(48000)
      .icon("MTpwYWxhbW9kOml0ZW0udW5jbGFpbWZpbmRlcl9yb3VnZToyMDAj").build().register();
    CraftAchievement craftobsi1 = CraftAchievement.builder().id("palamod.craft.obsi.1").category(AchievementCategory.ATTACK_DEFENSE).name("I").description("Crafter une big obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_BIG)).build();
    CraftAchievement craftobsi2 = CraftAchievement.builder().id("palamod.craft.obsi.2").category(AchievementCategory.ATTACK_DEFENSE).name("II").description("Crafter une lava obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_LAVA)).build();
    CraftAchievement craftobsi3 = CraftAchievement.builder().id("palamod.craft.obsi.3").category(AchievementCategory.ATTACK_DEFENSE).name("III").description("Crafter une sulfuric obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_FAKE)).build();
    CraftAchievement craftobsi4 = CraftAchievement.builder().id("palamod.craft.obsi.4").category(AchievementCategory.ATTACK_DEFENSE).name("IV").description("Crafter une boom obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_BOOM)).build();
    CraftAchievement craftobsi5 = CraftAchievement.builder().id("palamod.craft.obsi.5").category(AchievementCategory.ATTACK_DEFENSE).name("V").description("Crafter une mega boom obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_MEGABOOM)).build();
    CraftAchievement craftobsi6 = CraftAchievement.builder().id("palamod.craft.obsi.6").category(AchievementCategory.ATTACK_DEFENSE).name("VI").description("Crafter une spike (paladium) obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_SPIKE)).build();
    CraftAchievement craftobsi7 = CraftAchievement.builder().id("palamod.craft.obsi.7").category(AchievementCategory.ATTACK_DEFENSE).name("VII").description("Crafter une slime obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_SLIME)).build();
    CraftAchievement craftobsi8 = CraftAchievement.builder().id("palamod.craft.obsi.8").category(AchievementCategory.ATTACK_DEFENSE).name("VIII").description("Crafter une poison obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_POISON)).build();
    CraftAchievement craftobsi9 = CraftAchievement.builder().id("palamod.craft.obsi.9").category(AchievementCategory.ATTACK_DEFENSE).name("IX").description("Crafter une wither obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_WITHER)).build();
    CraftAchievement craftobsi10 = CraftAchievement.builder().id("palamod.craft.obsi.10").category(AchievementCategory.ATTACK_DEFENSE).name("X").description("Crafter une redstone obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_REDSTONE)).build();
    CraftAchievement craftobsi11 = CraftAchievement.builder().id("palamod.craft.obsi.11").category(AchievementCategory.ATTACK_DEFENSE).name("XI").description("Crafter une anti aggro obsidienne").nbToValidate(1).itemStack(new ItemStack(BlocksRegister.OBSI_ANTIAGGRO)).build();
    MultiAchievement.builder().id("palamod.craft.obsiall").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Obsidienne de qualité").description("Crafter tous les types d'obsidiennes demandés")
      .icon("NjQ6cGFsYW1vZDp0aWxlLmJpZ19vYnNpOjAj").build().addSubAchievement((Achievement)craftobsi1)
      .addSubAchievement((Achievement)craftobsi2).addSubAchievement((Achievement)craftobsi3).addSubAchievement((Achievement)craftobsi4)
      .addSubAchievement((Achievement)craftobsi5).addSubAchievement((Achievement)craftobsi6).addSubAchievement((Achievement)craftobsi7)
      .addSubAchievement((Achievement)craftobsi8).addSubAchievement((Achievement)craftobsi9).addSubAchievement((Achievement)craftobsi10)
      .addSubAchievement((Achievement)craftobsi11).register();
    CraftAchievement craftspike1 = CraftAchievement.builder().id("palamod.craft.spike.1").category(AchievementCategory.ATTACK_DEFENSE).name("I").description("Crafter un spike en bois").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_WOOD)).build();
    CraftAchievement craftspike2 = CraftAchievement.builder().id("palamod.craft.spike.2").category(AchievementCategory.ATTACK_DEFENSE).name("II").description("Crafter un spike en fer").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_IRON)).build();
    CraftAchievement craftspike3 = CraftAchievement.builder().id("palamod.craft.spike.3").category(AchievementCategory.ATTACK_DEFENSE).name("III").description("Crafter un spike en or").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_GOLD)).build();
    CraftAchievement craftspike4 = CraftAchievement.builder().id("palamod.craft.spike.4").category(AchievementCategory.ATTACK_DEFENSE).name("IV").description("Crafter un spike en diamant").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND)).build();
    CraftAchievement craftspike5 = CraftAchievement.builder().id("palamod.craft.spike.5").category(AchievementCategory.ATTACK_DEFENSE).name("V").description("Crafter un spike en améthyste").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST)).build();
    CraftAchievement craftspike6 = CraftAchievement.builder().id("palamod.craft.spike.6").category(AchievementCategory.ATTACK_DEFENSE).name("VI").description("Crafter un spike en titane").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_TITANE)).build();
    CraftAchievement craftspike7 = CraftAchievement.builder().id("palamod.craft.spike.7").category(AchievementCategory.ATTACK_DEFENSE).name("VII").description("Crafter un spike en paladium").nbToValidate(1).itemStack(new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM)).build();
    MultiAchievement.builder().id("palamod.craft.spikeall").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Spike de qualité").description("Crafter tous les types de spike demandés")
      .icon("NjQ6cGFsYW1vZDp0aWxlLnNwaWtlcGFsYWRpdW06MCM=").build().addSubAchievement((Achievement)craftspike1)
      .addSubAchievement((Achievement)craftspike2).addSubAchievement((Achievement)craftspike3).addSubAchievement((Achievement)craftspike4)
      .addSubAchievement((Achievement)craftspike5).addSubAchievement((Achievement)craftspike6).addSubAchievement((Achievement)craftspike7)
      .register();
    UseItemAchievement.builder().id("palamod.use.agrostone").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Viens là").description("Attirer l'attention d'un wither avec une pierre d'agro")
      .nbToValidate(1).discriminator("AGRO_STONE").icon("MTpwYWxhbW9kOml0ZW0uYWdyb19zdG9uZTowIw==").build()
      .register();
    UseItemAchievement.builder().id("palamod.use.homeremover.kill").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Extermina-home").description("Tuer 10 fantômes créés par le home remover").nbToValidate(10)
      .discriminator("HOME_REMOVER_GHOST_KILL").icon("MTpwYWxhbW9kOnRpbGUuaG9tZV9maW5kZXI6MCM=").build()
      .register();
    UseItemAchievement.builder().id("palamod.use.chestexplorer").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Explorateur de coffre").description("Utiliser un explorateur de coffre").nbToValidate(1)
      .discriminator("CHEST_EXPLORER").icon("MTpwYWxhbW9kOml0ZW0uY2hlc3RleHBsb3JlcjowIw==").build()
      .register();
    WalkDistanceAchievement.builder().id("palamod.walkdistance.1m").category(AchievementCategory.ATTACK_DEFENSE)
      .name("Marathon").description("Marcher 1 000 000 blocs").nbToValidate(1000000)
      .icon("MTpwYWxhbW9kOml0ZW0udHJhdmVsYm9vdHM6MCM=").build().register();
    UseItemAchievement.builder().id("palamod.useitem.legendarystone").category(AchievementCategory.OTHERS)
      .name("Wouah une petite pierre").description("Utiliser l'une des pierre de légende")
      .icon("MTpwYWxhbW9kOml0ZW0uTEVHRU5EQVJZU1RPTkVfUkFORE9NOjAje0xBU1RfVVNFOjBMLFNFQ1VSSVRZOntQTEFZRVI6e1BPUzp7WDotNC45MjYzMjgzMzM2NzMyODM1ZCxZOjY3LjY1MzgyMTM0NDIzNjM4ZCxaOjI4Ny45Nzk1OTE3NTg3MzFkLFdPUkxEOiIxNzIuMzAuMC4yIix9LFNMT1Q6NSxDTEFTUzoibmV0Lm1pbmVjcmFmdC5lbnRpdHkucGxheWVyLkVudGl0eVBsYXllck1QIixVVUlEOiIyMmU0NjE3Mi1kMjg4LTRjZjMtYTJhYy0yNTMzMmI1NzhjMmYiLEhBTkQ6MGIsfSxJVEVNOntTRUNVX0lURU1fQ1JFQVRFOjE2NzA5MzYxNzE4OTRMLFNFQ1VfSVRFTV9VVUlEOiI2YzNjNTliZi0yMTYxLTQ0YmYtODA3MS1kN2RkMGExODk1ZTYiLH0sVk1fTkFNRToiMTcyLjMwLjAuMiIsfSx9")
      .nbToValidate(1).discriminator("LEGENDARYSTONE").build().register();
    UseItemAchievement legendary1 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.1").category(AchievementCategory.OTHERS).name("I").description("Utiliser une pierre de légende aléatoire").nbToValidate(1).discriminator("LEGENDARYSTONE_RANDOM").build();
    UseItemAchievement legendary2 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.2").category(AchievementCategory.OTHERS).name("II").description("Utiliser une pierre de légende de téléportation").nbToValidate(1).discriminator("LEGENDARYSTONE_TELEPORTATION").build();
    UseItemAchievement legendary3 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.3").category(AchievementCategory.OTHERS).name("III").description("Utiliser une pierre de légende d'invisibilité").nbToValidate(1).discriminator("LEGENDARYSTONE_INVISIBILITY").build();
    UseItemAchievement legendary4 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.4").category(AchievementCategory.OTHERS).name("IV").description("Utiliser une pierre de légende de fortune").nbToValidate(1).discriminator("LEGENDARYSTONE_FORTUNE").build();
    UseItemAchievement legendary5 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.5").category(AchievementCategory.OTHERS).name("V").description("Utiliser une pierre de légende de pouvoir").nbToValidate(1).discriminator("LEGENDARYSTONE_POWER").build();
    UseItemAchievement legendary6 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.6").category(AchievementCategory.OTHERS).name("VI").description("Utiliser une pierre de légende de métiers").nbToValidate(1).discriminator("LEGENDARYSTONE_JOBS").build();
    UseItemAchievement legendary7 = UseItemAchievement.builder().id("palamod.useitem.legendarystone.multi.7").category(AchievementCategory.OTHERS).name("VII").description("Utiliser une pierre de légende de chaos").nbToValidate(1).discriminator("LEGENDARYSTONE_CHAOS").build();
    MultiAchievement.builder().id("palamod.useitem.legendarystone.multi.all").category(AchievementCategory.OTHERS)
      .name("Lithophile").description("Utiliser toutes les pierres de légendes")
      .icon("MTpwYWxhbW9kOml0ZW0uTEVHRU5EQVJZU1RPTkVfUkFORE9NOjAje0xBU1RfVVNFOjBMLFNFQ1VSSVRZOntQTEFZRVI6e1BPUzp7WDotNC45MjYzMjgzMzM2NzMyODM1ZCxZOjY3LjY1MzgyMTM0NDIzNjM4ZCxaOjI4Ny45Nzk1OTE3NTg3MzFkLFdPUkxEOiIxNzIuMzAuMC4yIix9LFNMT1Q6NSxDTEFTUzoibmV0Lm1pbmVjcmFmdC5lbnRpdHkucGxheWVyLkVudGl0eVBsYXllck1QIixVVUlEOiIyMmU0NjE3Mi1kMjg4LTRjZjMtYTJhYy0yNTMzMmI1NzhjMmYiLEhBTkQ6MGIsfSxJVEVNOntTRUNVX0lURU1fQ1JFQVRFOjE2NzA5MzYxNzE4OTRMLFNFQ1VfSVRFTV9VVUlEOiI2YzNjNTliZi0yMTYxLTQ0YmYtODA3MS1kN2RkMGExODk1ZTYiLH0sVk1fTkFNRToiMTcyLjMwLjAuMiIsfSx9")
      .build().addSubAchievement((Achievement)legendary1).addSubAchievement((Achievement)legendary2).addSubAchievement((Achievement)legendary3)
      .addSubAchievement((Achievement)legendary4).addSubAchievement((Achievement)legendary5).addSubAchievement((Achievement)legendary6)
      .addSubAchievement((Achievement)legendary7).register();
    UseItemAchievement.builder().id("palamod.useitem.trixium.deposit.100k").category(AchievementCategory.OTHERS)
      .name("Tout pile le bon nombre").description("Déposer 100 000 trixiums").nbToValidate(100000)
      .discriminator("TRIXIUM_DEPOSIT").icon("MTpwYWxhbW9kOml0ZW0udHJpeGl1bTowIw==").build().register();
    UseItemAchievement.builder().id("palamod.useitem.trixium.deposit.500k").category(AchievementCategory.OTHERS)
      .name("Oula, j'ai beaucoup farm").description("Déposer 500 000 trixiums").nbToValidate(500000)
      .discriminator("TRIXIUM_DEPOSIT").icon("MTpwYWxhbW9kOnRpbGUudHJpeGl1bV9ibG9jazowIw==").build()
      .register();
    PalapassReachPointsAchievement.builder().id("palapass.reach.9k").category(AchievementCategory.OTHERS)
      .name("Le Palapass, c'est fini").description("Atteindre la dernière récompense du Palapass")
      .nbToValidate(1).points(9000).icon("MTptaW5lY3JhZnQ6Y29tcGFzczowIw==").build().register();
    CresusWithdrawAchievement.builder()
      .id("paladium.cresus.withdraw.1")
      .name("Acheteur de passage")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(10000)
      .description("Dépenser 10 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    CresusWithdrawAchievement.builder()
      .id("paladium.cresus.withdraw.2")
      .name("Acheteur modéré")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(100000)
      .description("Dépenser 100 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    CresusWithdrawAchievement.builder()
      .id("paladium.cresus.withdraw.3")
      .name("Acheteur régulier")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(500000)
      .description("Dépenser 500 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    CresusWithdrawAchievement.builder()
      .id("paladium.cresus.withdraw.4")
      .name("Acheteur compulsif")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(1000000)
      .description("Dépenser 1 000 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    TradeMoneyAchievement.builder()
      .id("paladium.trade.money.1")
      .name("Tradeur débutant")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(10000)
      .description("Échanger avec d'autre joueur pour 10 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    TradeMoneyAchievement.builder()
      .id("paladium.trade.money.2")
      .name("Tradeur chevronné")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(100000)
      .description("Échanger avec d'autre joueur pour 100 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    TradeMoneyAchievement.builder()
      .id("paladium.trade.money.3")
      .name("Tradeur vétérans")
      .category(AchievementCategory.ECONOMY)
      .quantityRequired(1000000)
      .description("Échanger avec d'autre joueur pour 1 000 000$")
      .icon("MTpwYWxhbW9kOml0ZW0uYXVndXN0X2Zha2VfbW9uZXk6MCM=")
      .build().register();
    HammerDuplicateAchievement.builder()
      .id("paladium.hammer.duplicate.1")
      .name("Dupliqueur débutant")
      .category(AchievementCategory.OTHERS)
      .nbToValidate(10000)
      .description("Dupliquer 10 000 minerais")
      .icon("MTpwYWxhbW9kOml0ZW0uaGFtbWVyLnBhbGFkaXVtOjAje21vZGlmaWVyc21heDozLHVwZ3JhZGVhcnJheTpbMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLF0sbW9kaWZpZXJzYW1tb3VudDowLH0=")
      .build().register();
    HammerDuplicateAchievement.builder()
      .id("paladium.hammer.duplicate.2")
      .name("Dupliqueur chevronné")
      .category(AchievementCategory.OTHERS)
      .nbToValidate(50000)
      .description("Dupliquer 50 000 minerais")
      .icon("MTpwYWxhbW9kOml0ZW0uaGFtbWVyLnBhbGFkaXVtOjAje21vZGlmaWVyc21heDozLHVwZ3JhZGVhcnJheTpbMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLF0sbW9kaWZpZXJzYW1tb3VudDowLH0=")
      .build().register();
    HammerDuplicateAchievement.builder()
      .id("paladium.hammer.duplicate.3")
      .name("Dupliqueur vétérans")
      .category(AchievementCategory.OTHERS)
      .nbToValidate(100000)
      .description("Dupliquer 100 000 minerais")
      .icon("MTpwYWxhbW9kOml0ZW0uaGFtbWVyLnBhbGFkaXVtOjAje21vZGlmaWVyc21heDozLHVwZ3JhZGVhcnJheTpbMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLDAsMCwwLF0sbW9kaWZpZXJzYW1tb3VudDowLH0=")
      .build().register();
    PetReachLevelAchievement.builder()
      .id("paladium.pet.reach.level.1")
      .name("Le meilleur ami du Paladien")
      .nbToValidate(1)
      .category(AchievementCategory.OTHERS)
      .requiredLevel(100)
      .description("Monter niveau 100 son famillier")
      .icon("MTpwYWxhcGV0OnRpbGUucGV0X2NhZ2U6MCM=")
      .build().register();
    ArmorSetEquipAchievement.builder()
      .id("paladium.armor.equip.full.endium")
      .name("It's over 5000 !")
      .quantityRequired(1)
      .category(AchievementCategory.OTHERS)
      .description("Équiper un full endium")
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLmhlbG1ldDowIw==")
      .helmet(ItemsRegister.ENDIUM_HELMET)
      .chestplate(ItemsRegister.ENDIUM_CHESTPLATE)
      .leggings(ItemsRegister.ENDIUM_LEGGINGS)
      .boots(ItemsRegister.ENDIUM_BOOTS)
      .build().register();
    CaptureMountAchievement.builder()
      .id("paladium.mount.capture.1")
      .name("Dresseur débutant")
      .category(AchievementCategory.OTHERS)
      .quantityRequired(10)
      .description("Capturer 10 montures")
      .icon("MTpwYWxhbW9kOml0ZW0uY2FwdHVyZV9zdG9uZTowIw==")
      .build().register();
    CaptureMountAchievement.builder()
      .id("paladium.mount.capture.2")
      .name("Dresseur chevronné")
      .category(AchievementCategory.OTHERS)
      .quantityRequired(50)
      .description("Capturer 50 montures")
      .icon("MTpwYWxhbW9kOml0ZW0uY2FwdHVyZV9zdG9uZTowIw==")
      .build().register();
    CaptureMountAchievement.builder()
      .id("paladium.mount.capture.3")
      .name("Dresseur vétérans")
      .category(AchievementCategory.OTHERS)
      .quantityRequired(100)
      .description("Capturer 100 montures")
      .icon("MTpwYWxhbW9kOml0ZW0uY2FwdHVyZV9zdG9uZTowIw==")
      .build().register();
    PickupItemAchievement.builder()
      .id("paladium.pickup.endium.nugget")
      .name("Mon précieux !")
      .category(AchievementCategory.OTHERS)
      .quantityRequired(1)
      .description("Récuprer 1 endium nugget")
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLm51Z2dldDowIw==")
      .item(new ItemStack(ItemsRegister.ENDIUM_NUGGET))
      .build().register();
    SummonWitherAchievement.builder()
      .id("paladium.wither.summon.1")
      .name("Destructeur de base")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(10)
      .description("Faire spawn 10 withers")
      .icon("MTptaW5lY3JhZnQ6c2t1bGw6MSM=")
      .build().register();
    SummonWitherAchievement.builder()
      .id("paladium.wither.summon.2")
      .name("Exterminateur de base")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(100)
      .description("Faire spawn 100 withers")
      .icon("MTptaW5lY3JhZnQ6c2t1bGw6MSM=")
      .build().register();
    UseItemAchievement.builder()
      .id("palamod.dynamyite.junior")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .name("Dynamiteur junior")
      .description("Utiliser 1000 dynamites")
      .nbToValidate(1000)
      .discriminator("DYNAMITE_USAGE")
      .icon("MTpwYWxhbW9kOml0ZW0uZHluYW1pdGU6MCM=")
      .build().register();
    UseItemAchievement.builder()
      .id("palamod.dynamyite.confirme")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .name("Dynamiteur confirmé")
      .description("Utiliser 3000 dynamites")
      .nbToValidate(3000)
      .discriminator("DYNAMITE_USAGE")
      .icon("MTpwYWxhbW9kOml0ZW0uZHluYW1pdGVfYmlnOjAj")
      .build().register();
    UseItemAchievement.builder()
      .id("palamod.dynamyite.senior")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .name("Dynamiteur sénior")
      .description("Utiliser 10000 dynamites")
      .nbToValidate(10000)
      .discriminator("DYNAMITE_USAGE")
      .icon("MTpwYWxhbW9kOml0ZW0uZHluYW1pdGVfZW5kaXVtOjAj")
      .build().register();
    BlockPlaceAchievement.builder()
      .id("paladium.block.place.cave.1")
      .name("Chercheur de trésors débutant")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .nbToValidate(100)
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .description("Poser 100 cave block")
      .icon("MTpwYWxhbW9kOnRpbGUuY2F2ZV9ibG9jazowIw==")
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .build().register();
    BlockPlaceAchievement.builder()
      .id("paladium.block.place.cave.2")
      .name("Chercheur de trésors chevronné")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .nbToValidate(500)
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .description("Poser 500 cave block")
      .icon("MTpwYWxhbW9kOnRpbGUuY2F2ZV9ibG9jazowIw==")
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .build().register();
    BlockPlaceAchievement.builder()
      .id("paladium.block.place.cave.3")
      .name("Chercheur de trésors vétérans")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .nbToValidate(1000)
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .description("Poser 1000 cave block")
      .icon("MTpwYWxhbW9kOnRpbGUuY2F2ZV9ibG9jazowIw==")
      .block((Block)BlocksRegister.CAVE_BLOCK)
      .build().register();
    EquipBaclavaHelmetAchievement.builder()
      .id("paladium.equip.balaclava.1")
      .name("Apprentis cambrioleur")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(60)
      .description("Mettre une cagoule pendant 60 minutes")
      .icon("MTpwYWxhbW9kOml0ZW0uYmFsYWNsYXZhX2hlbG1ldDowIw==")
      .build().register();
    EquipBaclavaHelmetAchievement.builder()
      .id("paladium.equip.balaclava.2")
      .name("Cambrioleur expert")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(180)
      .description("Mettre une cagoule pendant 180 minutes")
      .icon("MTpwYWxhbW9kOml0ZW0uYmFsYWNsYXZhX2hlbG1ldDowIw==")
      .build().register();
    EquipBaclavaHelmetAchievement.builder()
      .id("paladium.equip.balaclava.3")
      .name("Roi des cambrioleurs")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(720)
      .description("Mettre une cagoule pendant 720 minutes")
      .icon("MTpwYWxhbW9kOml0ZW0uYmFsYWNsYXZhX2hlbG1ldDowIw==")
      .build().register();
    OwnFactionTownAchievement.builder()
      .id("paladium.faction.own.town")
      .name("Doux chez soi")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .description("Posseder un village de faction")
      .icon("MTptaW5lY3JhZnQ6bmV0aGVyX3N0YXI6MCM=")
      .build().register();
    FactionClaimAchievement.builder()
      .id("paladium.faction.own.claim")
      .name("On est serré un peu non ?")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .description("Posseder une base claim")
      .icon("MTpwYWxhbW9kOnRpbGUubWVnYV9ib29tX29ic2k6MCM=")
      .build().register();
    FactionReachLevelAchievement.builder()
      .id("paladium.faction.level.10")
      .name("Faction mineur")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredLevel(10)
      .description("Etre dans une faction de niveau 10")
      .icon("MTpwYWxhbW9kOnRpbGUuc3Bpa2Vfb2JzaToyIw==")
      .build().register();
    FactionReachLevelAchievement.builder()
      .id("paladium.faction.level.30")
      .name("Faction en développement")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredLevel(30)
      .description("Etre dans une faction de niveau 30")
      .icon("MTpwYWxhbW9kOnRpbGUuc3Bpa2Vfb2JzaTozIw==")
      .build().register();
    FactionReachLevelAchievement.builder()
      .id("paladium.faction.level.50")
      .name("Faction développé")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredLevel(50)
      .description("Etre dans une faction de niveau 50")
      .icon("MTpwYWxhbW9kOnRpbGUuc3Bpa2Vfb2JzaTo0Iw==")
      .build().register();
    FactionReachLevelAchievement.builder()
      .id("paladium.faction.level.70")
      .name("Faction majeur")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredLevel(70)
      .description("Etre dans une faction de niveau 70")
      .icon("MTpwYWxhbW9kOnRpbGUuc3Bpa2Vfb2JzaTo1Iw==")
      .build().register();
    FactionReachLevelAchievement.builder()
      .id("paladium.faction.level.100")
      .name("Faction légendaire")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredLevel(100)
      .description("Etre dans une faction de niveau 100")
      .icon("MTpwYWxhbW9kOnRpbGUuc3Bpa2Vfb2JzaTo2Iw==")
      .build().register();
    FactionReachEloAchievement.builder()
      .id("paladium.faction.elo.3000")
      .name("Gloire éphémère")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredElo(3000)
      .description("Atteindre les 3000 points d'elo")
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLmZyYWdtZW50OjAj")
      .build().register();
    FactionReachEloAchievement.builder()
      .id("paladium.faction.elo.6000")
      .name("Gloire persistante")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredElo(6000)
      .description("Atteindre les 6000 points d'elo")
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLm51Z2dldDowIw==")
      .build().register();
    FactionReachEloAchievement.builder()
      .id("paladium.faction.elo.10000")
      .name("Gloire éternelle")
      .quantityRequired(1)
      .category(AchievementCategory.FACTION)
      .requiredElo(10000)
      .description("Atteindre les 10000 points d'elo")
      .icon("MTpwYWxhbW9kOml0ZW0uZW5kaXVtLmluZ290OjAj")
      .build().register();
    ExplodeEndiumTnTAchievement.builder()
      .id("paladium.explode.endium.tnt")
      .name("Pilleur à tout prix")
      .category(AchievementCategory.ATTACK_DEFENSE)
      .quantityRequired(5)
      .description("Faire exploser 5 endium TNT")
      .icon("MTpwYWxhbW9kOnRpbGUuZW5kaXVtX3RudDowIw==")
      .build().register();
    BreakItemAchievement breakPickaxe = BreakItemAchievement.builder().id("palamod.break.pickaxe.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier I").description("Pioche").nbToValidate(1).item(ItemsRegister.PALADIUM_PICKAXE).build();
    BreakItemAchievement breakShovel = BreakItemAchievement.builder().id("palamod.break.shovel.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier II").description("Pelle").nbToValidate(1).item(ItemsRegister.PALADIUM_SHOVEL).build();
    BreakItemAchievement breakAxe = BreakItemAchievement.builder().id("palamod.break.axe.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier III").description("Hache").nbToValidate(1).item(ItemsRegister.PALADIUM_AXE).build();
    BreakItemAchievement breakSword = BreakItemAchievement.builder().id("palamod.break.sword.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier IV").description("Épée").nbToValidate(1).item(ItemsRegister.PALADIUM_SWORD).build();
    BreakItemAchievement breakHelmet = BreakItemAchievement.builder().id("palamod.break.helmet.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier V").description("Casque").nbToValidate(1).item(ItemsRegister.PALADIUM_HELMET).build();
    BreakItemAchievement breakChestplate = BreakItemAchievement.builder().id("palamod.break.chestplate.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier VI").description("Plastron").nbToValidate(1).item(ItemsRegister.PALADIUM_CHESTPLATE).build();
    BreakItemAchievement breakLeggings = BreakItemAchievement.builder().id("palamod.break.leggings.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier VII").description("Jambières").nbToValidate(1).item(ItemsRegister.PALADIUM_LEGGINGS).build();
    BreakItemAchievement breakBoots = BreakItemAchievement.builder().id("palamod.break.boots.paladium").category(AchievementCategory.OTHERS).name("Utilisateur régulier VIII").description("Bottes").nbToValidate(1).item(ItemsRegister.PALADIUM_BOOTS).build();
    MultiAchievement.builder().id("palamod.break.paladium.multi.all").category(AchievementCategory.OTHERS)
      .name("Utilisateur régulier")
      .description("Casser tous les items en Paladium en existants")
      .icon("MTpwYWxhbW9kOml0ZW0ucGFsYWRpdW0ucGlja2F4ZTowIw==")
      .build()
      .addSubAchievement((Achievement)breakPickaxe)
      .addSubAchievement((Achievement)breakShovel)
      .addSubAchievement((Achievement)breakAxe)
      .addSubAchievement((Achievement)breakSword)
      .addSubAchievement((Achievement)breakHelmet)
      .addSubAchievement((Achievement)breakChestplate)
      .addSubAchievement((Achievement)breakLeggings)
      .addSubAchievement((Achievement)breakBoots)
      .register();
    BreakSpawnerFinderAchievement.builder()
      .id("paladium.break.spawner")
      .name("Chercheur d'âme")
      .category(AchievementCategory.OTHERS)
      .description("Casser 200 spawners en ayant un Spawner Finder dans votre inventaire")
      .nbToValidate(200)
      .icon("MTpwYWxhbW9kOml0ZW0uc3Bhd25lcl9maW5kZXI6MCM=")
      .build().register();
    CraftAchievement.builder()
      .id("paladium.craft.glue")
      .name("Colleur")
      .category(AchievementCategory.OTHERS)
      .description("Crafter 10 000 glues")
      .nbToValidate(10000)
      .icon("MTpwYWxhbW9kOml0ZW0uZ2x1ZTowIw==")
      .itemStack(new ItemStack(PPRegisterer.PPItems.GLUE))
      .build().register();
    PickupItemAchievement pickupFlower1 = PickupItemAchievement.builder().id("paladium.pickup.flower.1").name("Fleuriste curieux I").category(AchievementCategory.OTHERS).description("Lucky Flower").item(new ItemStack(BlocksRegister.FLOWER_LUCKY)).quantityRequired(1).build();
    PickupItemAchievement pickupFlower2 = PickupItemAchievement.builder().id("paladium.pickup.flower.2").name("Fleuriste curieux II").category(AchievementCategory.OTHERS).description("Paladium Flower").item(new ItemStack(BlocksRegister.FLOWER_PALADIUM)).quantityRequired(1).build();
    PickupItemAchievement pickupFlower3 = PickupItemAchievement.builder().id("paladium.pickup.flower.3").name("Fleuriste curieux III").category(AchievementCategory.OTHERS).description("Endium Flower").item(new ItemStack(BlocksRegister.FLOWER_ENDIUM)).quantityRequired(1).build();
    PickupItemAchievement pickupFlower4 = PickupItemAchievement.builder().id("paladium.pickup.flower.4").name("Fleuriste curieux IV").category(AchievementCategory.OTHERS).description("Mineral Flower").item(new ItemStack(BlocksRegister.FLOWER_MINERAL)).quantityRequired(1).build();
    PickupItemAchievement pickupFlower5 = PickupItemAchievement.builder().id("paladium.pickup.flower.5").name("Fleuriste curieux V").category(AchievementCategory.OTHERS).description("Dandelion").item(new ItemStack((Block)Blocks.field_150327_N)).quantityRequired(1).build();
    PickupItemAchievement pickupFlower6 = PickupItemAchievement.builder().id("paladium.pickup.flower.6").name("Fleuriste curieux VI").category(AchievementCategory.OTHERS).description("Poppy").item(new ItemStack((Block)Blocks.field_150328_O)).quantityRequired(1).build();
    ItemStack flowerRed7 = new ItemStack((Block)Blocks.field_150328_O, 1, 1);
    PickupItemAchievement pickupFlower7 = PickupItemAchievement.builder().id("paladium.pickup.flower.7").name("Fleuriste curieux VII").category(AchievementCategory.OTHERS).description("Blue Orchid").item(flowerRed7).quantityRequired(1).build();
    ItemStack flowerRed8 = new ItemStack((Block)Blocks.field_150328_O, 1, 2);
    PickupItemAchievement pickupFlower8 = PickupItemAchievement.builder().id("paladium.pickup.flower.8").name("Fleuriste curieux VIII").category(AchievementCategory.OTHERS).description("Allium").item(flowerRed8).quantityRequired(1).build();
    ItemStack flowerRed9 = new ItemStack((Block)Blocks.field_150328_O, 1, 3);
    PickupItemAchievement pickupFlower9 = PickupItemAchievement.builder().id("paladium.pickup.flower.9").name("Fleuriste curieux IX").category(AchievementCategory.OTHERS).description("Azure Bluet").item(flowerRed9).quantityRequired(1).build();
    ItemStack flowerRed10 = new ItemStack((Block)Blocks.field_150328_O, 1, 4);
    PickupItemAchievement pickupFlower10 = PickupItemAchievement.builder().id("paladium.pickup.flower.10").name("Fleuriste curieux X").category(AchievementCategory.OTHERS).description("Red Tulip").item(flowerRed10).quantityRequired(1).build();
    ItemStack flowerRed11 = new ItemStack((Block)Blocks.field_150328_O, 1, 5);
    PickupItemAchievement pickupFlower11 = PickupItemAchievement.builder().id("paladium.pickup.flower.11").name("Fleuriste curieux XI").category(AchievementCategory.OTHERS).description("Orange Tulip").item(flowerRed11).quantityRequired(1).build();
    ItemStack flowerRed12 = new ItemStack((Block)Blocks.field_150328_O, 1, 6);
    PickupItemAchievement pickupFlower12 = PickupItemAchievement.builder().id("paladium.pickup.flower.12").name("Fleuriste curieux XII").category(AchievementCategory.OTHERS).description("White Tulip").item(flowerRed12).quantityRequired(1).build();
    ItemStack flowerRed13 = new ItemStack((Block)Blocks.field_150328_O, 1, 7);
    PickupItemAchievement pickupFlower13 = PickupItemAchievement.builder().id("paladium.pickup.flower.13").name("Fleuriste curieux XIII").category(AchievementCategory.OTHERS).description("Pink Tulip").item(flowerRed13).quantityRequired(1).build();
    ItemStack flowerRed14 = new ItemStack((Block)Blocks.field_150328_O, 1, 8);
    PickupItemAchievement pickupFlower14 = PickupItemAchievement.builder().id("paladium.pickup.flower.14").name("Fleuriste curieux XIV").category(AchievementCategory.OTHERS).description("Oxeye Daisy").item(flowerRed14).quantityRequired(1).build();
    ItemStack flowerDoublePlant15 = new ItemStack((Block)Blocks.field_150398_cm, 1, 0);
    PickupItemAchievement pickupFlower15 = PickupItemAchievement.builder().id("paladium.pickup.flower.15").name("Fleuriste curieux XV").category(AchievementCategory.OTHERS).description("Sunflower").item(flowerDoublePlant15).quantityRequired(1).build();
    ItemStack flowerDoublePlant16 = new ItemStack((Block)Blocks.field_150398_cm, 1, 4);
    PickupItemAchievement pickupFlower16 = PickupItemAchievement.builder().id("paladium.pickup.flower.16").name("Fleuriste curieux XVI").category(AchievementCategory.OTHERS).description("Rose Bush").item(flowerDoublePlant16).quantityRequired(1).build();
    ItemStack flowerDoublePlant17 = new ItemStack((Block)Blocks.field_150398_cm, 1, 5);
    PickupItemAchievement pickupFlower17 = PickupItemAchievement.builder().id("paladium.pickup.flower.17").name("Fleuriste curieux XVII").category(AchievementCategory.OTHERS).description("Peony").item(flowerDoublePlant17).quantityRequired(1).build();
    MultiAchievement.builder()
      .id("paladium.pickup.flower.multi.all")
      .category(AchievementCategory.OTHERS)
      .name("Fleuriste curieux")
      .description("Récupérer tous les types de fleurs possibles")
      .icon("MTpwYWxhbW9kOnRpbGUuZmxvd2VyX2VuZGl1bTowIw==")
      .build()
      .addSubAchievement((Achievement)pickupFlower1)
      .addSubAchievement((Achievement)pickupFlower2)
      .addSubAchievement((Achievement)pickupFlower3)
      .addSubAchievement((Achievement)pickupFlower4)
      .addSubAchievement((Achievement)pickupFlower5)
      .addSubAchievement((Achievement)pickupFlower6)
      .addSubAchievement((Achievement)pickupFlower7)
      .addSubAchievement((Achievement)pickupFlower8)
      .addSubAchievement((Achievement)pickupFlower9)
      .addSubAchievement((Achievement)pickupFlower10)
      .addSubAchievement((Achievement)pickupFlower11)
      .addSubAchievement((Achievement)pickupFlower12)
      .addSubAchievement((Achievement)pickupFlower13)
      .addSubAchievement((Achievement)pickupFlower14)
      .addSubAchievement((Achievement)pickupFlower15)
      .addSubAchievement((Achievement)pickupFlower16)
      .addSubAchievement((Achievement)pickupFlower17)
      .register();
    ItemStack torchLever = new ItemStack(SecretRooms.torchLever);
    PickupItemAchievement pickupTorchLever = PickupItemAchievement.builder().id("paladium.pickup.torch.lever").name("Expert du camouflage I").category(AchievementCategory.OTHERS).description("Torch Lever").item(torchLever).quantityRequired(1).build();
    ItemStack oneWayGlass = new ItemStack(SecretRooms.oneWay);
    PickupItemAchievement pickupOneWayGlass = PickupItemAchievement.builder().id("paladium.pickup.one.way.glass").name("Expert du camouflage II").category(AchievementCategory.OTHERS).description("One Way Glass").item(oneWayGlass).quantityRequired(1).build();
    ItemStack camoGate = new ItemStack(SecretRooms.camoGate);
    PickupItemAchievement pickupCamoGate = PickupItemAchievement.builder().id("paladium.pickup.camo.gate").name("Expert du camouflage III").category(AchievementCategory.OTHERS).description("1 Camo Gate").item(camoGate).quantityRequired(1).build();
    ItemStack camoDummy = new ItemStack(SecretRooms.camoGateExt);
    PickupItemAchievement pickupCamoDummy = PickupItemAchievement.builder().id("paladium.pickup.camo.dummy").name("Expert du camouflage IV").category(AchievementCategory.OTHERS).description("Camo Dummy").item(camoDummy).quantityRequired(1).build();
    ItemStack secretTrapDoor = new ItemStack(SecretRooms.camoTrapDoor);
    PickupItemAchievement pickupSecretTrapDoor = PickupItemAchievement.builder().id("paladium.pickup.secret.trapdoor").name("Expert du camouflage V").category(AchievementCategory.OTHERS).description("Secret Trap Door").item(secretTrapDoor).quantityRequired(1).build();
    ItemStack secretWoodenDoor = new ItemStack(SecretRooms.camoDoorWood);
    PickupItemAchievement pickupSecretWoodenDoor = PickupItemAchievement.builder().id("paladium.pickup.secret.wooden.door").name("Expert du camouflage VI").category(AchievementCategory.OTHERS).description("Secret Wooden Door").item(secretWoodenDoor).quantityRequired(1).build();
    ItemStack secretIronDoor = new ItemStack(SecretRooms.camoDoorIron);
    PickupItemAchievement pickupSecretIronDoor = PickupItemAchievement.builder().id("paladium.pickup.secret.iron.door").name("Expert du camouflage VII").category(AchievementCategory.OTHERS).description("Secret Iron Door").item(secretIronDoor).quantityRequired(1).build();
    ItemStack ghostBlock = new ItemStack(SecretRooms.camoGhost);
    PickupItemAchievement pickupGhostBlock = PickupItemAchievement.builder().id("paladium.pickup.ghost.block").name("Expert du camouflage VIII").category(AchievementCategory.OTHERS).description("Ghost Block").item(ghostBlock).quantityRequired(1).build();
    ItemStack secretCamoLever = new ItemStack(SecretRooms.camoLever);
    PickupItemAchievement pickupSecretCamoLever = PickupItemAchievement.builder().id("paladium.pickup.secret.camo.lever").name("Expert du camouflage IX").category(AchievementCategory.OTHERS).description("Secret Camo Lever").item(secretCamoLever).quantityRequired(1).build();
    ItemStack secretCamoRedstone = new ItemStack(SecretRooms.camoCurrent);
    PickupItemAchievement pickupSecretCamoRedstone = PickupItemAchievement.builder().id("paladium.pickup.secret.camo.redstone").name("Expert du camouflage X").category(AchievementCategory.OTHERS).description("Secret Camo Redstone").item(secretCamoRedstone).quantityRequired(1).build();
    ItemStack secretCamoButton = new ItemStack(SecretRooms.camoButton);
    PickupItemAchievement pickupSecretCamoButton = PickupItemAchievement.builder().id("paladium.pickup.secret.camo.button").name("Expert du camouflage XI").category(AchievementCategory.OTHERS).description("Secret Camo Button").item(secretCamoButton).quantityRequired(1).build();
    ItemStack secretCamoButton1 = new ItemStack(SecretRooms.camoButton, 1, 1);
    PickupItemAchievement pickupSecretCamoButton1 = PickupItemAchievement.builder().id("paladium.pickup.secret.camo.button.1").name("Expert du camouflage XII").category(AchievementCategory.OTHERS).description("1 Secret Camo Button").item(secretCamoButton1).quantityRequired(1).build();
    ItemStack secretPressurePlate = new ItemStack(SecretRooms.camoPlateAll);
    PickupItemAchievement pickupSecretPressurePlate = PickupItemAchievement.builder().id("paladium.pickup.secret.pressure.plate").name("Expert du camouflage XIII").category(AchievementCategory.OTHERS).description("1 Secret Pressure Plate").item(secretPressurePlate).quantityRequired(1).build();
    ItemStack secretPlayerPlate = new ItemStack(SecretRooms.camoPlatePlayer);
    PickupItemAchievement pickupSecretPlayerPlate = PickupItemAchievement.builder().id("paladium.pickup.secret.player.plate").name("Expert du camouflage XIV").category(AchievementCategory.OTHERS).description("1 Secret Player Plate").item(secretPlayerPlate).quantityRequired(1).build();
    ItemStack secretLightPlate = new ItemStack(SecretRooms.camoPlateLight);
    PickupItemAchievement pickupSecretLightPlate = PickupItemAchievement.builder().id("paladium.pickup.secret.light.plate").name("Expert du camouflage XV").category(AchievementCategory.OTHERS).description("Secret Light Plate").item(secretLightPlate).quantityRequired(1).build();
    ItemStack secretHeavyPlate = new ItemStack(SecretRooms.camoPlateHeavy);
    PickupItemAchievement pickupSecretHeavyPlate = PickupItemAchievement.builder().id("paladium.pickup.secret.heavy.plate").name("Expert du camouflage XVI").category(AchievementCategory.OTHERS).description("1 Secret Heavy Plate").item(secretHeavyPlate).quantityRequired(1).build();
    ItemStack secretStairs = new ItemStack(SecretRooms.camoStairs);
    PickupItemAchievement pickupSecretStairs = PickupItemAchievement.builder().id("paladium.pickup.secret.stairs").name("Expert du camouflage XVII").category(AchievementCategory.OTHERS).item(secretStairs).quantityRequired(1).build();
    ItemStack secretChest = new ItemStack(SecretRooms.camoChest);
    PickupItemAchievement pickupSecretChest = PickupItemAchievement.builder().id("paladium.pickup.secret.chest").name("Expert du camouflage XVIII").category(AchievementCategory.OTHERS).item(secretChest).quantityRequired(1).build();
    ItemStack secretTrappedChest = new ItemStack(SecretRooms.camoTrappedChest);
    PickupItemAchievement pickupSecretTrappedChest = PickupItemAchievement.builder().id("paladium.pickup.secret.trapped.chest").name("Expert du camouflage XIX").category(AchievementCategory.OTHERS).description("Secret Trapped Chest").item(secretTrappedChest).quantityRequired(1).build();
    ItemStack secretLightDetector = new ItemStack(SecretRooms.camoLightDetector);
    PickupItemAchievement pickupSecretLightDetector = PickupItemAchievement.builder().id("paladium.pickup.secret.light.detector").name("Expert du camouflage XX").category(AchievementCategory.OTHERS).description("Secret Light Detector").item(secretLightDetector).quantityRequired(1).build();
    MultiAchievement.builder()
      .id("paladium.pickup.secret.multi.all")
      .category(AchievementCategory.OTHERS)
      .name("Expert du camouflage")
      .description("Récupérer tous les types de ghost blocks")
      .icon("MTpzZWNyZXRyb29tc21vZDpDYW1vZmxhdWdlUGFzdGU6MCM=")
      .build()
      .addSubAchievement((Achievement)pickupTorchLever)
      .addSubAchievement((Achievement)pickupOneWayGlass)
      .addSubAchievement((Achievement)pickupCamoGate)
      .addSubAchievement((Achievement)pickupCamoDummy)
      .addSubAchievement((Achievement)pickupSecretTrapDoor)
      .addSubAchievement((Achievement)pickupSecretWoodenDoor)
      .addSubAchievement((Achievement)pickupSecretIronDoor)
      .addSubAchievement((Achievement)pickupGhostBlock)
      .addSubAchievement((Achievement)pickupSecretCamoLever)
      .addSubAchievement((Achievement)pickupSecretCamoRedstone)
      .addSubAchievement((Achievement)pickupSecretCamoButton)
      .addSubAchievement((Achievement)pickupSecretCamoButton1)
      .addSubAchievement((Achievement)pickupSecretPressurePlate)
      .addSubAchievement((Achievement)pickupSecretPlayerPlate)
      .addSubAchievement((Achievement)pickupSecretLightPlate)
      .addSubAchievement((Achievement)pickupSecretHeavyPlate)
      .addSubAchievement((Achievement)pickupSecretStairs)
      .addSubAchievement((Achievement)pickupSecretChest)
      .addSubAchievement((Achievement)pickupSecretTrappedChest)
      .addSubAchievement((Achievement)pickupSecretLightDetector)
      .register();
  }
  
  public static void register() {
    registerAchievements();
    registerExtendedProperties();
    if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
      registerListeners();
      registerTasks();
    } 
  }
  
  private static void registerTasks() {
    achievementTask = new AchievementTask();
  }
  
  private static void registerListeners() {
    registerListener(new AchievementsListeners());
    registerListener(new TradeListener());
    registerListener(new PetListener());
    registerListener(new PickupListener());
    registerListener(new BlockPlaceListener());
    registerListener(new WitherListener());
    registerListener(new BreakItemListener());
  }
  
  private static void registerExtendedProperties() {
    ExtendedUtils.registerExtended(EntityPlayer.class, AchievementExtraPlayer.class, "palamod_AchievementExtra", new ExtendedProperty[] { ExtendedProperty.SELF_CONSTRUCT, ExtendedProperty.PERSISTANT, ExtendedProperty.SYNCHRONIZED, ExtendedProperty.SYNCHRONIZED_TRACKER });
  }
  
  private static void registerListener(Object obj) {
    MinecraftForge.EVENT_BUS.register(obj);
    FMLCommonHandler.instance().bus().register(obj);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\achievements\AchievementsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */