package fr.paladium.palamod.modules.miner.utils;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoCrafterManager {
  public static Map<String, AutoCrafterRecipe> getRecipes() {
    return recipes;
  }
  
  private static Map<String, AutoCrafterRecipe> recipes = new LinkedHashMap<>();
  
  public static void init() {
    register(new AutoCrafterRecipe("angelicwater", new ItemStack((Item)ItemsRegister.BUCKET_ANGELIC), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151131_as)), new SubAutoCrafterRecipe(new ItemStack((Item)Items.field_151068_bn, 1, 8193)) }));
    register(new AutoCrafterRecipe("sulfuricwater", new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151131_as)), new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.STICK_PALADIUM)) }));
    register(new AutoCrafterRecipe("iron", new ItemStack((Block)BlocksRegister.SPIKE_IRON), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151042_j, 4)), new SubAutoCrafterRecipe(new ItemStack(Items.field_151041_m, 3), new ItemStack[] { new ItemStack(Blocks.field_150344_f, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("gold", new ItemStack((Block)BlocksRegister.SPIKE_GOLD), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151043_k, 4)), new SubAutoCrafterRecipe(new ItemStack(Items.field_151040_l, 3), new ItemStack[] { new ItemStack(Items.field_151042_j, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("diamond", new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151045_i, 4)), new SubAutoCrafterRecipe(new ItemStack(Items.field_151010_B, 3), new ItemStack[] { new ItemStack(Items.field_151043_k, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("amethyst", new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.AMETHYST_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(Items.field_151048_u, 3), new ItemStack[] { new ItemStack(Items.field_151045_i, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("titane", new ItemStack((Block)BlocksRegister.SPIKE_TITANE), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.TITANE_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.AMETHYST_SWORD, 3), new ItemStack[] { new ItemStack(ItemsRegister.AMETHYST_INGOT, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("paladium", new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.PALADIUM_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.TITANE_SWORD, 3), new ItemStack[] { new ItemStack(ItemsRegister.TITANE_INGOT, 6), new ItemStack(Items.field_151055_y, 3) }) }));
    register(new AutoCrafterRecipe("bonemeal", new ItemStack(Items.field_151100_aR, 3, 15), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151103_aS)) }));
    register(new AutoCrafterRecipe("dynamite", new ItemStack((Item)ItemsRegister.DYNAMITE, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.DIAMOND_STRING)), new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150335_W, 2), new ItemStack[] { new ItemStack(Items.field_151016_H, 8), new ItemStack((Block)Blocks.field_150354_m, 8) }) }));
    register(new AutoCrafterRecipe("tnt", new ItemStack(Blocks.field_150335_W), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151016_H, 4)), new SubAutoCrafterRecipe(new ItemStack((Block)Blocks.field_150354_m, 4)) }));
    register(new AutoCrafterRecipe("anvil", new ItemStack(Blocks.field_150467_bQ), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Items.field_151042_j, 4)), new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150339_S, 3), new ItemStack[] { new ItemStack(Items.field_151042_j, 27) }) }));
    register(new AutoCrafterRecipe("amethystanvil", new ItemStack(BlocksRegister.ANVIL_AMETHYST), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.AMETHYST_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(BlocksRegister.BLOCK_AMETHYST, 3), new ItemStack[] { new ItemStack(ItemsRegister.AMETHYST_INGOT, 27) }) }));
    register(new AutoCrafterRecipe("titaneanvil", new ItemStack(BlocksRegister.ANVIL_TITANE), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.TITANE_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(BlocksRegister.BLOCK_TITANE, 3), new ItemStack[] { new ItemStack(ItemsRegister.TITANE_INGOT, 27) }) }));
    register(new AutoCrafterRecipe("paladiumanvil", new ItemStack(BlocksRegister.ANVIL_PALADIUM), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.PALADIUM_INGOT, 4)), new SubAutoCrafterRecipe(new ItemStack(BlocksRegister.BLOCK_PALADIUM, 3), new ItemStack[] { new ItemStack(ItemsRegister.PALADIUM_INGOT, 27) }) }));
    register(new AutoCrafterRecipe("hardenedobsidian", new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 4)) }));
    register(new AutoCrafterRecipe("bigobsi", new ItemStack(BlocksRegister.OBSI_BIG, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 8), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 8) }), new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.PALADIUM_CORE), new ItemStack[] { new ItemStack(ItemsRegister.PALADIUM_INGOT, 5), new ItemStack(ItemsRegister.TITANE_INGOT, 4) }) }));
    register(new AutoCrafterRecipe("lavaobsi", new ItemStack(BlocksRegister.OBSI_LAVA), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack(Items.field_151129_at)) }));
    register(new AutoCrafterRecipe("fakeobsi", new ItemStack(BlocksRegister.OBSI_FAKE), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC)) }));
    register(new AutoCrafterRecipe("boomobsi", new ItemStack(BlocksRegister.OBSI_BOOM), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack(Items.field_151016_H)) }));
    register(new AutoCrafterRecipe("megaboomobsi", new ItemStack(BlocksRegister.OBSI_MEGABOOM), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150335_W, 3), new ItemStack[] { new ItemStack(Items.field_151016_H, 12), new ItemStack((Block)Blocks.field_150354_m, 12) }), new SubAutoCrafterRecipe(new ItemStack(Items.field_151016_H)) }));
    register(new AutoCrafterRecipe("woodspikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 0), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_WOOD)) }));
    register(new AutoCrafterRecipe("ironspikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 1), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_IRON)) }));
    register(new AutoCrafterRecipe("diamondspikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 2), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_DIAMOND)) }));
    register(new AutoCrafterRecipe("amethystspikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_AMETHYST)) }));
    register(new AutoCrafterRecipe("titanespikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 5), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_TITANE)) }));
    register(new AutoCrafterRecipe("paladiumspikeobsi", new ItemStack(BlocksRegister.OBSI_SPIKE, 1, 6), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack((Block)BlocksRegister.SPIKE_PALADIUM)) }));
    register(new AutoCrafterRecipe("slimeobsi", new ItemStack(BlocksRegister.OBSI_SLIME, 2), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 6), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 6) }), new SubAutoCrafterRecipe(new ItemStack(BlocksRegister.SLIMEPAD_BLOCK, 3)) }));
    register(new AutoCrafterRecipe("redstoneobsi", new ItemStack(BlocksRegister.OBSI_REDSTONE), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 1), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 1) }), new SubAutoCrafterRecipe(new ItemStack(Items.field_151137_ax)) }));
    register(new AutoCrafterRecipe("antiaggroobsi", new ItemStack(BlocksRegister.OBSI_ANTIAGGRO, 8), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 8), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 8) }), new SubAutoCrafterRecipe(new ItemStack(Items.field_151156_bN)) }));
    register(new AutoCrafterRecipe("frozenobsi", new ItemStack(BlocksRegister.OBSI_FROZEN, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 4), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 4) }), new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150403_cj, 5)) }));
    register(new AutoCrafterRecipe("knockbackobsi", new ItemStack(BlocksRegister.OBSI_KNOCKBACK, 4), new SubAutoCrafterRecipe[] { new SubAutoCrafterRecipe(new ItemStack(Blocks.field_150343_Z, 8), new ItemStack[] { new ItemStack((Block)BlocksRegister.HARDENED_OBSIDIAN, 8) }), new SubAutoCrafterRecipe(new ItemStack(ItemsRegister.ORB_KNOCKBACK)) }));
  }
  
  private static void register(AutoCrafterRecipe recipe) {
    recipes.put(recipe.getId(), recipe);
  }
  
  public static List<AutoCrafterRecipe> getAllRecipies() {
    return (List<AutoCrafterRecipe>)recipes.values().stream().collect(Collectors.toCollection(java.util.LinkedList::new));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\utils\AutoCrafterManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */