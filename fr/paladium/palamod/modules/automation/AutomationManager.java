package fr.paladium.palamod.modules.automation;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaautomation.common.registry.AutomationBlockRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AutomationManager {
  private static AutomationManager instance;
  
  private AutomationManager() {
    instance = this;
  }
  
  public static AutomationManager getInstance() {
    if (instance == null)
      instance = new AutomationManager(); 
    return instance;
  }
  
  public void registerCrafts() {
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.CRAFTER), new Object[] { "QQQ", "ICI", "QQQ", 
          
          Character.valueOf('Q'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          Character.valueOf('I'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT), 
          Character.valueOf('C'), new ItemStack(Blocks.field_150462_ai) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.RECEIVER), new Object[] { "QQQ", "QCQ", "QQQ", 
          
          Character.valueOf('Q'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          Character.valueOf('C'), new ItemStack(Items.field_151132_bS) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.GIVER), new Object[] { 
          "QOQ", "RCD", "QOQ", 
          
          Character.valueOf('Q'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          Character.valueOf('O'), new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO), 
          Character.valueOf('R'), new ItemStack(Blocks.field_150451_bX), 
          Character.valueOf('C'), 
          new ItemStack((Block)Blocks.field_150486_ae), 
          Character.valueOf('D'), new ItemStack(Blocks.field_150367_z) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.INJECTOR), new Object[] { "QQQ", "OIO", "QQQ", 
          
          Character.valueOf('Q'), new ItemStack(PPRegisterer.PPBlocks.EMPOWERED_QUARTZ), 
          Character.valueOf('O'), new ItemStack((Block)SpawnerBlockRegistry.SPAWNER_STRUCTURE_TWO), 
          Character.valueOf('I'), new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.PIPE_PALADIUM), new Object[] { "HP ", "PDP", " PH", 
          
          Character.valueOf('P'), new ItemStack(ItemsRegister.PALADIUM_INGOT), 
          Character.valueOf('H'), new ItemStack((Block)Blocks.field_150438_bZ), 
          Character.valueOf('D'), new ItemStack(Blocks.field_150367_z) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.PIPE_PALADIUM_GREEN), new Object[] { "   ", "PTP", "   ", 
          
          Character.valueOf('P'), new ItemStack(ItemsRegister.PALADIUM_GREEN_INGOT), 
          Character.valueOf('T'), new ItemStack(AutomationBlockRegistry.PIPE_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack(AutomationBlockRegistry.PIPE_PALADIUM_ENDIUM), new Object[] { "   ", "PTP", "   ", 
          
          Character.valueOf('P'), new ItemStack(ItemsRegister.MIXED_ENDIUM_INGOT), 
          Character.valueOf('T'), new ItemStack(AutomationBlockRegistry.PIPE_PALADIUM_GREEN) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\automation\AutomationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */