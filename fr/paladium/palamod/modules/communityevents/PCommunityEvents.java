package fr.paladium.palamod.modules.communityevents;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palacommunityevent.common.pojo.CommonCommunityStep;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import fr.paladium.palacommunityevent.common.pojo.CommunityStep;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.communityevents.init.AdventCalendar;
import fr.paladium.palamod.modules.communityevents.init.Blocks;
import fr.paladium.palamod.modules.communityevents.init.Crafts;
import fr.paladium.palamod.modules.communityevents.init.Items;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@ObjectHolder("palamod")
@Pulse(id = "palamod-comuevents", description = "Paladium Community Events", forced = true)
@DoNotRename
public class PCommunityEvents {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.communityevents.ClientProxy", serverSide = "fr.paladium.palamod.modules.communityevents.CommonProxy")
  public static CommonProxy proxy;
  
  @Instance("palamod-comuevents")
  public static PCommunityEvents instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent e) {
    instance = this;
    proxy.preInit(e);
  }
  
  @Handler
  public void init(FMLInitializationEvent e) {
    Blocks.register();
    Items.register();
    AdventCalendar.register();
    proxy.registerRenders();
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent e) {
    Crafts.register();
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {}
  
  @Handler
  public void serverStarted(FMLServerStartedEvent e) {}
  
  @Handler
  public void serverPostInit(FMLPostInitializationEvent e) {
    CommunityEvent christmasEvent = CommunityEvent.init("community-event-christmas", "Christmas", "Déposez vos boules de neige", "Snowman", 20000000, 40000, "christmas", "F7921E", "B14622");
    christmasEvent.addTargetedItems(new ItemStack(Items.field_151126_ay));
    CommunityStep christmas25playerStep = new CommunityStep(25);
    christmas25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 16));
    christmas25playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 1));
    CommunityStep christmas50playerStep = new CommunityStep(50);
    ItemStack christmas50item1 = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    Map<Integer, Integer> christmasMap = EnchantmentHelper.func_82781_a(christmas50item1);
    christmasMap.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
    christmasMap.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    EnchantmentHelper.func_82782_a(christmasMap, christmas50item1);
    ItemStack christmas50item2 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    EnchantmentHelper.func_82782_a(christmasMap, christmas50item2);
    ItemStack christmas50item3 = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    EnchantmentHelper.func_82782_a(christmasMap, christmas50item3);
    ItemStack christmas50item4 = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    EnchantmentHelper.func_82782_a(christmasMap, christmas50item4);
    ItemStack christmas50item5 = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    Map<Integer, Integer> christmasMap2 = EnchantmentHelper.func_82781_a(christmas50item5);
    christmasMap2.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
    christmasMap2.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    christmasMap2.put(Integer.valueOf(Enchantment.field_77334_n.field_77352_x), Integer.valueOf(2));
    EnchantmentHelper.func_82782_a(christmasMap2, christmas50item5);
    christmas50playerStep.addReward(christmas50item1);
    christmas50playerStep.addReward(christmas50item2);
    christmas50playerStep.addReward(christmas50item3);
    christmas50playerStep.addReward(christmas50item4);
    christmas50playerStep.addReward(christmas50item5);
    christmas50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 32));
    christmas50playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 2));
    CommunityStep christmas75playerStep = new CommunityStep(75);
    christmas75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    christmas75playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 3));
    christmas75playerStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 1));
    christmas75playerStep.addReward(new ItemStack(ItemsRegister.RANDOM_BEAN, 1));
    christmas75playerStep.addReward(new ItemStack(ItemsRegister.LUCKY_BEAN, 1));
    CommunityStep christmas100playerStep = new CommunityStep(100);
    christmas100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    christmas100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    christmas100playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 4));
    christmas100playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    christmas100playerStep.addReward(new ItemStack((Block)BlocksRegister.CHRISTMAS_LUCKY_BLOCK, 1));
    christmas100playerStep.addReward(new ItemStack(ItemsRegister.PRESENT_BAG, 1));
    christmasEvent.addPlayerStep(christmas25playerStep);
    christmasEvent.addPlayerStep(christmas50playerStep);
    christmasEvent.addPlayerStep(christmas75playerStep);
    christmasEvent.addPlayerStep(christmas100playerStep);
    CommonCommunityStep christmas50commonStep = new CommonCommunityStep("christmas-common-50", 25, 50);
    christmas50commonStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 1));
    christmas50commonStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 2));
    CommonCommunityStep christmas100commonStep = new CommonCommunityStep("christmas-common-100", 50, 100);
    christmas100commonStep.addReward(new ItemStack(ItemsRegister.PRESENT, 2));
    christmas100commonStep.addReward(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 2));
    christmas100commonStep.addReward(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1));
    christmasEvent.addCommonStep(christmas50commonStep);
    christmasEvent.addCommonStep(christmas100commonStep);
    PalaCommunityEventManager.getInstance().registerCommunityEvent(christmasEvent);
    CommunityEvent communityEventHalloween = CommunityEvent.init("community-event-halloween", "Halloween", "Déposez vos citrouilles", "Jack O'Lantern", 40000, 4000, "halloween", "F7921E", "B14622");
    communityEventHalloween.addTargetedItems(new ItemStack(Blocks.field_150423_aK));
    CommunityStep halloween25playerStep = new CommunityStep(25);
    halloween25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 16));
    halloween25playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 1));
    CommunityStep halloween50playerStep = new CommunityStep(50);
    ItemStack halloween50item1 = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    Map<Integer, Integer> halloweenMap = EnchantmentHelper.func_82781_a(halloween50item1);
    halloweenMap.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
    halloweenMap.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    EnchantmentHelper.func_82782_a(halloweenMap, halloween50item1);
    ItemStack halloween50item2 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    EnchantmentHelper.func_82782_a(halloweenMap, halloween50item2);
    ItemStack halloween50item3 = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    EnchantmentHelper.func_82782_a(halloweenMap, halloween50item3);
    ItemStack halloween50item4 = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    EnchantmentHelper.func_82782_a(halloweenMap, halloween50item4);
    ItemStack halloween50item5 = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    Map<Integer, Integer> halloweenMap2 = EnchantmentHelper.func_82781_a(halloween50item5);
    halloweenMap2.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
    halloweenMap2.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    halloweenMap2.put(Integer.valueOf(Enchantment.field_77334_n.field_77352_x), Integer.valueOf(2));
    EnchantmentHelper.func_82782_a(halloweenMap2, halloween50item5);
    halloween50playerStep.addReward(new ItemStack((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK, 1));
    halloween50playerStep.addReward(halloween50item1);
    halloween50playerStep.addReward(halloween50item2);
    halloween50playerStep.addReward(halloween50item3);
    halloween50playerStep.addReward(halloween50item4);
    halloween50playerStep.addReward(halloween50item5);
    halloween50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 32));
    halloween50playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 2));
    CommunityStep halloween75playerStep = new CommunityStep(75);
    halloween75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    halloween75playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 3));
    halloween75playerStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 2));
    halloween75playerStep.addReward(new ItemStack(ItemsRegister.RANDOM_BEAN, 1));
    halloween75playerStep.addReward(new ItemStack(ItemsRegister.LUCKY_BEAN, 1));
    CommunityStep halloween100playerStep = new CommunityStep(100);
    halloween100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 128));
    halloween100playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 4));
    halloween100playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    halloween100playerStep.addReward(new ItemStack((Block)BlocksRegister.HALLOWEEN_LUCKY_BLOCK, 1));
    halloween100playerStep.addReward(new ItemStack(ItemsRegister.LUCKY_BEAN, 3));
    communityEventHalloween.addPlayerStep(halloween25playerStep);
    communityEventHalloween.addPlayerStep(halloween50playerStep);
    communityEventHalloween.addPlayerStep(halloween75playerStep);
    communityEventHalloween.addPlayerStep(halloween100playerStep);
    CommonCommunityStep halloween50commonStep = new CommonCommunityStep("halloween-common-50", 25, 50);
    halloween50commonStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 1));
    halloween50commonStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3));
    CommonCommunityStep halloween100commonStep = new CommonCommunityStep("halloween-common-100", 25, 100);
    halloween100commonStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    halloween100commonStep.addReward(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 3));
    halloween100commonStep.addReward(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1));
    communityEventHalloween.addCommonStep(halloween50commonStep);
    communityEventHalloween.addCommonStep(halloween100commonStep);
    CommunityEvent communityEventJanuary = CommunityEvent.init("community-event-galette", "Galettes des rois", "Déposez vos galettes", "Glouton", 40000, 4000, "galette", "DB5D22", "7C1902");
    communityEventJanuary.addTargetedItems(new ItemStack(ItemsRegister.GALETTE));
    CommunityStep galette25playerStep = new CommunityStep(25);
    galette25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 16));
    galette25playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 1));
    CommunityStep galette50playerStep = new CommunityStep(50);
    ItemStack galette50item1 = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    Map<Integer, Integer> galetteMap = EnchantmentHelper.func_82781_a(galette50item1);
    galetteMap.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
    galetteMap.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    EnchantmentHelper.func_82782_a(galetteMap, galette50item1);
    ItemStack galette50item2 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    EnchantmentHelper.func_82782_a(galetteMap, galette50item2);
    ItemStack galette50item3 = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    EnchantmentHelper.func_82782_a(galetteMap, galette50item3);
    ItemStack galette50item4 = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    EnchantmentHelper.func_82782_a(galetteMap, galette50item4);
    ItemStack galette50item5 = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    Map<Integer, Integer> galetteMap2 = EnchantmentHelper.func_82781_a(galette50item5);
    galetteMap2.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
    galetteMap2.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    galetteMap2.put(Integer.valueOf(Enchantment.field_77334_n.field_77352_x), Integer.valueOf(2));
    EnchantmentHelper.func_82782_a(galetteMap2, galette50item5);
    galette50playerStep.addReward(new ItemStack(ItemsRegister.PIRATE_CHEST, 2));
    galette50playerStep.addReward(galette50item1);
    galette50playerStep.addReward(galette50item2);
    galette50playerStep.addReward(galette50item3);
    galette50playerStep.addReward(galette50item4);
    galette50playerStep.addReward(galette50item5);
    galette50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 32));
    galette50playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 2));
    CommunityStep galette75playerStep = new CommunityStep(75);
    galette75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    galette75playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 3));
    galette75playerStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 2));
    galette75playerStep.addReward(new ItemStack(ItemsRegister.RANDOM_BEAN, 1));
    galette75playerStep.addReward(new ItemStack(ItemsRegister.LUCKY_BEAN, 1));
    CommunityStep galette100playerStep = new CommunityStep(100);
    galette100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 128));
    galette100playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 4));
    galette100playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    galette100playerStep.addReward(new ItemStack(ItemsRegister.RANDOM_BEAN, 1));
    galette100playerStep.addReward(new ItemStack(ItemsRegister.LUCKY_BEAN, 3));
    communityEventJanuary.addPlayerStep(galette25playerStep);
    communityEventJanuary.addPlayerStep(galette50playerStep);
    communityEventJanuary.addPlayerStep(galette75playerStep);
    communityEventJanuary.addPlayerStep(galette100playerStep);
    CommonCommunityStep galette50commonStep = new CommonCommunityStep("galette-common-50", 25, 50);
    galette50commonStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 1));
    galette50commonStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 3));
    CommonCommunityStep galette100commonStep = new CommonCommunityStep("galette-common-100", 25, 100);
    galette100commonStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    galette100commonStep.addReward(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 3));
    galette100commonStep.addReward(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1));
    communityEventJanuary.addCommonStep(galette50commonStep);
    communityEventJanuary.addCommonStep(galette100commonStep);
    CommunityEvent communityEventPirate = CommunityEvent.init("community-event-pirate", "Pirate", "Déposez vos fûts", "Vallo Willy", 6000000, 30000, "pirate", "EF9F26", "B9550C");
    communityEventPirate.addTargetedItems(new ItemStack(BlocksRegister.BARREL_WOOD));
    CommunityStep pirate25playerStep = new CommunityStep(25);
    pirate25playerStep.addReward(new ItemStack(ItemsRegister.PIRATE_CHEST, 2));
    pirate25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 16));
    pirate25playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 1));
    CommunityStep pirate50playerStep = new CommunityStep(50);
    ItemStack pirate50item1 = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(pirate50item1);
    map.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
    map.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    EnchantmentHelper.func_82782_a(map, pirate50item1);
    ItemStack pirate50item2 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    EnchantmentHelper.func_82782_a(map, pirate50item2);
    ItemStack pirate50item3 = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    EnchantmentHelper.func_82782_a(map, pirate50item3);
    ItemStack pirate50item4 = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    EnchantmentHelper.func_82782_a(map, pirate50item4);
    ItemStack pirate50item5 = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    Map<Integer, Integer> map2 = EnchantmentHelper.func_82781_a(pirate50item5);
    map2.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
    map2.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    map2.put(Integer.valueOf(Enchantment.field_77334_n.field_77352_x), Integer.valueOf(2));
    EnchantmentHelper.func_82782_a(map2, pirate50item5);
    pirate50playerStep.addReward(pirate50item1);
    pirate50playerStep.addReward(pirate50item2);
    pirate50playerStep.addReward(pirate50item3);
    pirate50playerStep.addReward(pirate50item4);
    pirate50playerStep.addReward(pirate50item5);
    pirate50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 32));
    pirate50playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 2));
    pirate50playerStep.addReward(new ItemStack(ItemsRegister.PIRATE_CHEST, 2));
    CommunityStep pirate75playerStep = new CommunityStep(75);
    pirate75playerStep.addReward(new ItemStack(BlocksRegister.JULY_LUCKY_BLOCK, 1));
    pirate75playerStep.addReward(new ItemStack(BlocksRegister.RAINBOW_SKULL, 1));
    pirate75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 48));
    pirate75playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 3));
    pirate75playerStep.addReward(new ItemStack(ItemsRegister.PIRATE_CHEST, 3));
    CommunityStep pirate100playerStep = new CommunityStep(100);
    pirate100playerStep.addReward(new ItemStack(BlocksRegister.JULY_LUCKY_BLOCK, 2));
    pirate100playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 3));
    pirate100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    pirate100playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 4));
    pirate100playerStep.addReward(new ItemStack(ItemsRegister.PIRATE_CHEST, 4));
    communityEventPirate.addPlayerStep(pirate25playerStep);
    communityEventPirate.addPlayerStep(pirate50playerStep);
    communityEventPirate.addPlayerStep(pirate75playerStep);
    communityEventPirate.addPlayerStep(pirate100playerStep);
    CommonCommunityStep pirate50commonStep = new CommonCommunityStep("pirate-common-50", 25, 50);
    pirate50commonStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 2));
    pirate50commonStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 4));
    CommonCommunityStep pirate100commonStep = new CommonCommunityStep("pirate-common-100", 50, 100);
    pirate100commonStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 2));
    pirate100commonStep.addReward(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 4));
    pirate100commonStep.addReward(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 1));
    communityEventPirate.addCommonStep(pirate50commonStep);
    communityEventPirate.addCommonStep(pirate100commonStep);
    CommunityEvent communityEventAugust = CommunityEvent.init("community-event-august", "LINGOTS", "Déposez vos lingots", "Ingénieur Goldo", 10000000, 2000, "august", "ef3926", "6c1007");
    communityEventAugust.addTargetedItems(new ItemStack(ItemsRegister.PALADIUM_INGOT));
    CommunityStep august25playerStep = new CommunityStep(25);
    august25playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 2, 1));
    august25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 16));
    august25playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 1));
    CommunityStep august50playerStep = new CommunityStep(50);
    ItemStack august50item1 = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    map = EnchantmentHelper.func_82781_a(august50item1);
    map.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
    map.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    EnchantmentHelper.func_82782_a(map, august50item1);
    ItemStack august50item2 = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    EnchantmentHelper.func_82782_a(map, august50item2);
    ItemStack august50item3 = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    EnchantmentHelper.func_82782_a(map, august50item3);
    ItemStack august50item4 = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    EnchantmentHelper.func_82782_a(map, august50item4);
    ItemStack august50item5 = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    map2 = EnchantmentHelper.func_82781_a(august50item5);
    map2.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
    map2.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
    map2.put(Integer.valueOf(Enchantment.field_77334_n.field_77352_x), Integer.valueOf(2));
    EnchantmentHelper.func_82782_a(map2, august50item5);
    august50playerStep.addReward(august50item1);
    august50playerStep.addReward(august50item2);
    august50playerStep.addReward(august50item3);
    august50playerStep.addReward(august50item4);
    august50playerStep.addReward(august50item5);
    august50playerStep.addReward(new ItemStack(BlocksRegister.FLOWER_LUCKY, 2));
    august50playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 2));
    august50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 32));
    CommunityStep august75playerStep = new CommunityStep(75);
    august75playerStep.addReward(new ItemStack(ItemsRegister.CHUNK_ANALYSER, 1));
    august75playerStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 1));
    august75playerStep.addReward(new ItemStack(BlocksRegister.AUGUST_LUCKY_BLOCK, 1));
    august75playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 3));
    august75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 48));
    CommunityStep august100playerStep = new CommunityStep(100);
    august100playerStep.addReward(new ItemStack(ItemsRegister.FUNNY_WITHER_GEM, 1));
    august100playerStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 2, 3));
    august100playerStep.addReward(new ItemStack(BlocksRegister.AUGUST_LUCKY_BLOCK, 2));
    august100playerStep.addReward(new ItemStack(ItemsRegister.ORE_PRESENT, 4));
    august100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    communityEventAugust.addPlayerStep(august25playerStep);
    communityEventAugust.addPlayerStep(august50playerStep);
    communityEventAugust.addPlayerStep(august75playerStep);
    communityEventAugust.addPlayerStep(august100playerStep);
    CommonCommunityStep august50commonStep = new CommonCommunityStep("august-common-50", 25, 50);
    august50commonStep.addReward(new ItemStack(ItemsRegister.JETPACK, 1));
    august50commonStep.addReward(new ItemStack((Item)ItemsRegister.POTION_MINER_FOU, 2, 1));
    august50commonStep.addReward(new ItemStack((Block)BlocksRegister.PALADIUM_LUCKY_BLOCK, 4));
    CommonCommunityStep august100commonStep = new CommonCommunityStep("august-common-100", 50, 100);
    august100commonStep.addReward(new ItemStack(ItemsRegister.WATER_PISTOL, 1));
    august100commonStep.addReward(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 3));
    august100commonStep.addReward(new ItemStack((Block)BlocksRegister.FINDIUM_LUCKY_BLOCK, 6));
    august100commonStep.addReward(new ItemStack((Block)BlocksRegister.ENDIUM_LUCKY_BLOCK, 2));
    communityEventAugust.addCommonStep(august50commonStep);
    communityEventAugust.addCommonStep(august100commonStep);
    CommunityEvent communityEventTrix = CommunityEvent.init("community-event-trixium-race", "Course au Trixium", "Déposez de l'améthyste", "Collectionneur de Trixium", 8000000, 20000, "trixium_race", "A8DEF0", "22A0C8").setTitleColor("A8DEF0").setTitleShadowColor("22A0C8");
    communityEventTrix.addTargetedItems(new ItemStack(ItemsRegister.AMETHYST_INGOT));
    CommunityStep trixium25playerStep = new CommunityStep(25);
    trixium25playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    CommunityStep trixium50playerStep = new CommunityStep(50);
    trixium50playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    CommunityStep trixium75playerStep = new CommunityStep(75);
    trixium75playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    CommunityStep trixium100playerStep = new CommunityStep(100);
    trixium100playerStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    communityEventTrix.addPlayerStep(trixium25playerStep);
    communityEventTrix.addPlayerStep(trixium50playerStep);
    communityEventTrix.addPlayerStep(trixium75playerStep);
    communityEventTrix.addPlayerStep(trixium100playerStep);
    CommonCommunityStep trixium25commonStep = new CommonCommunityStep("trixium-race-common-25", 25, 25);
    trixium25commonStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    CommonCommunityStep trixium100commonStep = new CommonCommunityStep("summer-rush-common-100", 50, 100);
    trixium100commonStep.addReward(new ItemStack(BlocksRegister.BLOCK_TRIXIUM, 64));
    communityEventTrix.addCommonStep(trixium25commonStep);
    communityEventTrix.addCommonStep(trixium100commonStep);
    PalaCommunityEventManager.getInstance().registerCommunityEvent(communityEventTrix);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\PCommunityEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */