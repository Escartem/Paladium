package fr.paladium.palamod.modules.aspawner.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.aspawner.common.blueprint.FirstSpawnerBluePrint;
import fr.paladium.palamod.modules.aspawner.common.blueprint.FourthSpawnerBluePrint;
import fr.paladium.palamod.modules.aspawner.common.blueprint.SecondSpawnerBluePrint;
import fr.paladium.palamod.modules.aspawner.common.blueprint.ThirdSpawnerBluePrint;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class PPalaSpawnerCommonProxy extends AModProxy {
  private static PPalaSpawnerCommonProxy instance;
  
  public static PPalaSpawnerCommonProxy getInstance() {
    return instance;
  }
  
  public PPalaSpawnerCommonProxy() {
    instance = this;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    registerCrafts();
    registerBlueprints();
  }
  
  private void registerCrafts() {
    GameRegistry.addRecipe(new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE), new Object[] { "MOM", "OBO", "MOM", 
          
          Character.valueOf('M'), Items.field_151045_i, 
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO), new Object[] { "MOM", "OBO", "MOM", 
          
          Character.valueOf('M'), ItemsRegister.AMETHYST_INGOT, 
          Character.valueOf('O'), SpawnerBlockRegistry.SPAWNER_STRUCTURE_ONE, 
          Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE), new Object[] { "MOM", "OBO", "MOM", 
          
          Character.valueOf('M'), ItemsRegister.TITANE_INGOT, 
          Character.valueOf('O'), SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO, 
          Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_FOUR), new Object[] { "MOM", "OBO", "MOM", 
          
          Character.valueOf('M'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('O'), SpawnerBlockRegistry.SPAWNER_STRUCTURE_THREE, 
          Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER), new Object[] { "OOO", "PSP", "OOO", 
          
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('P'), ItemsRegister.COMPRESSED_PALADIUM, 
          Character.valueOf('S'), SpawnerBlockRegistry.EMPTY_MOB_SPAWNER });
    GameRegistry.addRecipe(new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SPEED), new Object[] { "OOO", "PSP", "OOO", 
          
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('S'), Items.field_151102_aT, 
          Character.valueOf('P'), ItemsRegister.COMPRESSED_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)SpawnerItemRegistry.UPGRADE_SLIME), new Object[] { "OOO", "PSP", "OOO", 
          
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('S'), BlocksRegister.SLIMEPAD_BLOCK, 
          Character.valueOf('P'), ItemsRegister.COMPRESSED_PALADIUM });
    GameRegistry.addRecipe(new ItemStack((Item)SpawnerItemRegistry.UPGRADE_MORE), new Object[] { 
          "OWO", "PSP", "OBO", 
          
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('W'), new ItemStack(Items.field_151144_bL, 1, 1), 
          Character.valueOf('S'), PSRegister_Items.MODIFIER_MOREUPGRADE, 
          Character.valueOf('P'), 
          ItemsRegister.ENDIUM_NUGGET, 
          Character.valueOf('B'), SpawnerBlockRegistry.EMPTY_MOB_SPAWNER });
    GameRegistry.addRecipe(new ItemStack((Item)SpawnerItemRegistry.UPGRADE_LOOTING), new Object[] { "OOO", "PSP", "OOO", 
          
          Character.valueOf('O'), Blocks.field_150343_Z, 
          Character.valueOf('S'), ItemsRegister.PALADIUM_SWORD, 
          Character.valueOf('P'), ItemsRegister.COMPRESSED_PALADIUM });
  }
  
  private void registerBlueprints() {
    SpawnerManager manager = SpawnerManager.getInstance();
    manager.getBluePrints().clear();
    manager.registerBluePrint(FourthSpawnerBluePrint.class);
    manager.registerBluePrint(ThirdSpawnerBluePrint.class);
    manager.registerBluePrint(SecondSpawnerBluePrint.class);
    manager.registerBluePrint(FirstSpawnerBluePrint.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\common\PPalaSpawnerCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */