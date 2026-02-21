package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockAlchemistPortal;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockBase;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockCauldronCore;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockExtractor;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockGlueball;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockPortalAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockWood;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityWood;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockTankSupport;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumGlueball;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumTank;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockMod {
  static {
    BlocksRegister.EXTRACTOR = (BlockAlchemist)new BlockExtractor();
    BlocksRegister.TANK_GOLD = (BlockAlchemist)new BlockTank("gold_tank", EnumTank.GOLD);
    BlocksRegister.TANK_AMETHYSTE = (BlockAlchemist)new BlockTank("amethyste_tank", EnumTank.AMETHYSTE);
    BlocksRegister.TANK_TITANE = (BlockAlchemist)new BlockTank("titane_tank", EnumTank.TITANE);
    BlocksRegister.TANK_PALADIUM = (BlockAlchemist)new BlockTank("paladium_tank", EnumTank.PALADIUM);
    BlocksRegister.CAULDRON_BLOCK = (BlockAlchemist)new BlockBase("cauldron_block", "cauldron_top", "cauldron_side");
    BlocksRegister.CAULDRON_CORE = (BlockAlchemist)new BlockCauldronCore("cauldron_core");
    BlocksRegister.CAULDRON_TANKSUPPORT = (BlockAlchemist)new BlockTankSupport("cauldron_tanksupport");
    BlocksRegister.GREEN_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.GREEN);
    BlocksRegister.BLUE_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.BLUE);
    BlocksRegister.YELLOW_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.YELLOW);
    BlocksRegister.RED_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.RED);
    BlocksRegister.ORANGE_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.ORANGE);
    BlocksRegister.GRAY_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.GRAY);
    BlocksRegister.PURPLE_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.PURPLE);
    BlocksRegister.GREEN_FLASH_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.GREEN_FLASH);
    BlocksRegister.GREEN_DARK_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.GREEN_DARK);
    BlocksRegister.CYAN_GLUEBALL_BLOCK = (BlockAlchemist)new BlockGlueball(EnumGlueball.CYAN);
    BlocksRegister.SHINY_JUDEECERCIS_WOOD = (BlockAlchemist)(new BlockBase("shiny_judeecercis_block")).setLightLevel(1.0F);
    BlocksRegister.SHINY_JACARANDA_WOOD = (BlockAlchemist)(new BlockBase("shiny_jacaranda_block")).setLightLevel(1.0F);
    BlocksRegister.SHINY_ERABLE_WOOD = (BlockAlchemist)(new BlockBase("shiny_erable_block")).setLightLevel(1.0F);
    BlocksRegister.SHINY_OSTRYA_WOOD = (BlockAlchemist)(new BlockBase("shiny_ostrya_block")).setLightLevel(1.0F);
    BlocksRegister.AMETHYSTE_PORTAL_BLOCK = (BlockAlchemist)new BlockAlchemistPortal("amethyste_portal_block");
    BlocksRegister.TITANE_PORTAL_BLOCK = (BlockAlchemist)new BlockAlchemistPortal("titane_portal_block");
    BlocksRegister.PALADIUM_PORTAL_BLOCK = (BlockAlchemist)new BlockAlchemistPortal("paladium_portal_block");
    BlocksRegister.ENDIUM_PORTAL_BLOCK = (BlockAlchemist)new BlockAlchemistPortal("endium_portal_block");
    BlocksRegister.ENDIUM_HEART_BLOCK = (BlockAlchemist)new BlockBase("endium_heart_block");
    BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK = (BlockAlchemist)new BlockBase("endium_angle_portal_block");
    BlocksRegister.MYSTICAL_BOOKSHELF = (BlockAlchemist)new BlockBase("mystical_bookshelf");
    BlocksRegister.PORTAL_BLOCK = (BlockAlchemist)new BlockPortalAlchemist();
    BlocksRegister.BLOCK_WOOD = (Block)new BlockWood();
  }
  
  private static BlockAlchemist[] REGISTRY = new BlockAlchemist[] { 
      BlocksRegister.EXTRACTOR, BlocksRegister.TANK_GOLD, BlocksRegister.TANK_AMETHYSTE, BlocksRegister.TANK_TITANE, BlocksRegister.TANK_PALADIUM, BlocksRegister.CAULDRON_BLOCK, BlocksRegister.CAULDRON_TANKSUPPORT, BlocksRegister.CAULDRON_CORE, BlocksRegister.GREEN_GLUEBALL_BLOCK, BlocksRegister.BLUE_GLUEBALL_BLOCK, 
      BlocksRegister.YELLOW_GLUEBALL_BLOCK, BlocksRegister.RED_GLUEBALL_BLOCK, BlocksRegister.ORANGE_GLUEBALL_BLOCK, BlocksRegister.GRAY_GLUEBALL_BLOCK, BlocksRegister.PURPLE_GLUEBALL_BLOCK, BlocksRegister.GREEN_FLASH_GLUEBALL_BLOCK, BlocksRegister.GREEN_DARK_GLUEBALL_BLOCK, BlocksRegister.CYAN_GLUEBALL_BLOCK, BlocksRegister.SHINY_JUDEECERCIS_WOOD, BlocksRegister.SHINY_JACARANDA_WOOD, 
      BlocksRegister.SHINY_ERABLE_WOOD, BlocksRegister.SHINY_OSTRYA_WOOD, BlocksRegister.AMETHYSTE_PORTAL_BLOCK, BlocksRegister.TITANE_PORTAL_BLOCK, BlocksRegister.PALADIUM_PORTAL_BLOCK, BlocksRegister.ENDIUM_PORTAL_BLOCK, BlocksRegister.ENDIUM_HEART_BLOCK, BlocksRegister.ENDIUM_ANGLE_PORTAL_BLOCK, BlocksRegister.PORTAL_BLOCK, BlocksRegister.MYSTICAL_BOOKSHELF };
  
  public static void register() {
    for (BlockAlchemist b : REGISTRY) {
      if (b.getCustomStack() != null) {
        GameRegistry.registerBlock((Block)b, b.getCustomStack().getClass(), b.getName());
      } else {
        GameRegistry.registerBlock((Block)b, b.getName());
      } 
      if (b.hasTileEntity(0))
        GameRegistry.registerTileEntity(b.getTileEntity(), "palamod:" + b
            .getName() + "tileentity"); 
    } 
    GameRegistry.registerBlock(BlocksRegister.BLOCK_WOOD, "alchimiste.wood");
    GameRegistry.registerTileEntity(TileEntityWood.class, "palamod:alchimiste.woodtileentity");
    ((BlockBase)BlocksRegister.CAULDRON_TANKSUPPORT)
      .setCustomDrop(new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\BlockMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */