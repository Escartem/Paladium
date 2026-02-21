package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.items.block.ItemBlockConcrete;
import fr.paladium.palamod.modules.back2future.lib.EnumDyeColor;
import java.util.HashMap;
import net.minecraft.block.Block;

public class ConcreteRegistry {
  public static HashMap<EnumDyeColor, BlockConcretePowder> concretePowders = new HashMap<>();
  
  public static HashMap<EnumDyeColor, BlockConcrete> concretes = new HashMap<>();
  
  public static void init() {
    for (EnumDyeColor color : EnumDyeColor.values()) {
      concretePowders.put(color, new BlockConcretePowder(color));
      GameRegistry.registerBlock((Block)concretePowders.get(color), ItemBlockConcrete.class, "concrete_powder_" + color
          .getUnlocalizedName());
      concretes.put(color, new BlockConcrete(color));
      GameRegistry.registerBlock(concretes.get(color), ItemBlockConcrete.class, "concrete_" + color
          .getUnlocalizedName());
    } 
  }
  
  public static BlockConcretePowder getPowderFromDye(EnumDyeColor dye) {
    return concretePowders.get(dye);
  }
  
  public static BlockConcrete getSolidFromDye(EnumDyeColor dye) {
    return concretes.get(dye);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\ConcreteRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */