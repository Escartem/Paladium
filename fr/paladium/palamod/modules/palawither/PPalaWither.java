package fr.paladium.palamod.modules.palawither;

import com.allatori.annotations.DoNotRename;
import com.github.abrarsyed.secretroomsmod.common.SecretRooms;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import fr.paladium.palawither.api.BlocksRegister;
import fr.paladium.palawither.api.ItemsRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Pulse(id = "palamod-palawither", description = "PalaWither crafts and items", forced = true)
@DoNotRename
public class PPalaWither {
  @Instance("palamod-palawither")
  public static PPalaWither instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    instance = this;
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    GameRegistry.addRecipe(new ItemStack(Items.field_151156_bN), new Object[] { "XXX", "YYY", "XXX", Character.valueOf('X'), new ItemStack(ItemsRegister.WITHER_SKULLFRAGMENT), Character.valueOf('Y'), new ItemStack(Items.field_151144_bL, 1, 1) });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.WITHER_RECEPTACLE), new Object[] { " X ", "XYX", " Z ", Character.valueOf('X'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), Character.valueOf('Y'), new ItemStack(Items.field_151144_bL, 1, 1), Character.valueOf('Z'), new ItemStack(Blocks.field_150344_f) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BABY_WITHER_GEM), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(Items.field_151110_aK), Character.valueOf('Y'), new ItemStack(Items.field_151144_bL, 1, 1) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD), new Object[] { " Y ", "YXY", " Y ", Character.valueOf('Y'), new ItemStack(Items.field_151144_bL, 1, 1), Character.valueOf('X'), new ItemStack(Items.field_151156_bN) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PASSIVE_WITHER_GEM), new Object[] { "XXX", "YYY", " Y ", Character.valueOf('X'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD), Character.valueOf('Y'), new ItemStack(Blocks.field_150425_aM) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.THIRSTY_WITHER_HEAD), new Object[] { "XYX", "YZY", "XYX", Character.valueOf('X'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), Character.valueOf('Y'), new ItemStack(ItemsRegister.EXTRAPOLATED_BUCKET), Character.valueOf('Z'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.THIRSTY_WITHER_GEM), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.THIRSTY_WITHER_HEAD), Character.valueOf('Y'), new ItemStack(ItemsRegister.COMPRESSED_ENDIUM) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PREDATOR_WITHER_HEAD), new Object[] { "XXX", "AZA", "XXX", Character.valueOf('X'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), Character.valueOf('Z'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD), Character.valueOf('A'), new ItemStack(ItemsRegister.COMPRESSED_ENDIUM) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PREDATOR_WITHER_GEM), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.PREDATOR_WITHER_HEAD), Character.valueOf('Y'), new ItemStack(ItemsRegister.ENDIUM_NUGGET) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.SUPREME_WITHER_GEM), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.SUPREME_WITHER_HEAD), Character.valueOf('Y'), new ItemStack(ItemsRegister.ENDIUM_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.FURTIVE_WITHER_UPGRADE), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(SecretRooms.camoPaste), Character.valueOf('Y'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.RESISTANCE_WITHER_UPGRADE), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), Character.valueOf('Y'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD) });
    GameRegistry.addRecipe(new ItemStack((Item)ItemsRegister.SLIMY_WITHER_UPGRADE), new Object[] { " X ", "XYX", " X ", Character.valueOf('X'), new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), Character.valueOf('Y'), new ItemStack(ItemsRegister.PASSIVE_WITHER_HEAD) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.WITHER_TARGET_LASER_SYSTEM), new Object[] { " XY", "YZY", " Y ", Character.valueOf('X'), new ItemStack(Blocks.field_150429_aA), Character.valueOf('Y'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), Character.valueOf('Z'), new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 2) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palawither\PPalaWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */