package fr.paladium.palamod.modules.paladium.registerer;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.StepSoundSlime;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.paladium.utils.ItemAnnotationProcessor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class PRegisterer {
  public static PToolMaterial toolMaterial;
  
  public static PArmorMaterial armorMaterial;
  
  public static Block.SoundType SLIME_STEP;
  
  public static void init() {
    toolMaterial = new PToolMaterial();
    armorMaterial = new PArmorMaterial();
    SLIME_STEP = (Block.SoundType)new StepSoundSlime("mob.slime", 1.0F, 1.0F);
    PRegister_Fluids.preInit();
    PRegister_Potions.init();
    PRegister_Blocks.init();
    PRegister_Items.init();
    PRegister_Tiles.init();
    PRegister_Entities.init();
    PRegister_Network.init();
    PRegister_Events.init();
    PRegister_Fluids.init();
    ItemAnnotationProcessor.registerItems(ItemsRegister.class);
    Blocks.field_150343_Z.func_149752_b(100.0F);
    Blocks.field_150477_bB.func_149752_b(100.0F);
    Blocks.field_150467_bQ.func_149752_b(1000.0F);
    BlocksRegister.ANVIL_AMETHYST.func_149752_b(1000.0F);
    BlocksRegister.ANVIL_TITANE.func_149752_b(1000.0F);
    BlocksRegister.ANVIL_PALADIUM.func_149752_b(1000.0F);
    Blocks.field_150381_bn.func_149752_b(100.0F);
  }
  
  public static void initClientOnly() {
    PRegister_Renderer.init();
    PRegister_Overlay.init();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegisterer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */