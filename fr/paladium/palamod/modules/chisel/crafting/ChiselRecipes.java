package fr.paladium.palamod.modules.chisel.crafting;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.blocks.ConcreteRegistry;
import fr.paladium.palamod.modules.back2future.lib.EnumDyeColor;
import fr.paladium.palamod.modules.chisel.PChisel;
import fr.paladium.palamod.modules.world.PWorld;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class ChiselRecipes {
  private static final ChiselRecipes instance = new ChiselRecipes();
  
  private final List<Group> recipes = new ArrayList<>();
  
  public static ChiselRecipes getInstance() {
    return instance;
  }
  
  public static String _a = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[13];
        this.t = 67472898;
        buf[0] = (byte)(this.t >>> 10);
        this.t = 1703294174;
        buf[1] = (byte)(this.t >>> 18);
        this.t = -624489336;
        buf[2] = (byte)(this.t >>> 17);
        this.t = -1270681493;
        buf[3] = (byte)(this.t >>> 23);
        this.t = -1297046786;
        buf[4] = (byte)(this.t >>> 23);
        this.t = -715601828;
        buf[5] = (byte)(this.t >>> 9);
        this.t = 1858028334;
        buf[6] = (byte)(this.t >>> 8);
        this.t = 1816941441;
        buf[7] = (byte)(this.t >>> 24);
        this.t = 1008274487;
        buf[8] = (byte)(this.t >>> 5);
        this.t = -1178427400;
        buf[9] = (byte)(this.t >>> 23);
        this.t = 1383382478;
        buf[10] = (byte)(this.t >>> 2);
        this.t = 130903242;
        buf[11] = (byte)(this.t >>> 1);
        this.t = -1212569851;
        buf[12] = (byte)(this.t >>> 15);
        return new String(buf);
      }
    }).toString();
  
  private ChiselRecipes() {
    addRecipe(new ItemStack(Blocks.field_150348_b), PChisel.STONE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150417_aV), PChisel.STONE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150417_aV, 1, 1), PChisel.STONE_BLOCK_EXTANDS
        .getGroupID());
    addRecipe(new ItemStack(Blocks.field_150417_aV, 1, 2), PChisel.STONE_BLOCK_EXTANDS
        .getGroupID());
    addRecipe(new ItemStack(Blocks.field_150417_aV, 1, 3), PChisel.STONE_BLOCK_EXTANDS
        .getGroupID());
    addRecipe(new ItemStack(Blocks.field_150347_e), PChisel.COBBLESTONE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150341_Y), PChisel.COBBLESTONE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150364_r), PChisel.LOG_OAK_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150364_r, 1, 1), 3);
    addRecipe(new ItemStack(Blocks.field_150364_r, 1, 2), 4);
    addRecipe(new ItemStack(Blocks.field_150364_r, 1, 3), 5);
    addRecipe(new ItemStack(Blocks.field_150363_s, 1, 0), 6);
    addRecipe(new ItemStack(Blocks.field_150363_s, 1, 1), 7);
    addRecipe(new ItemStack(Blocks.field_150451_bX), 54);
    addRecipe(new ItemStack(Blocks.field_150359_w), 8);
    addRecipe(new ItemStack(ModBlocks.slime), 11);
    addRecipe(new ItemStack(BlocksRegister.SLIMEPAD_BLOCK), 12);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.BLACK)), 13);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.BLUE)), 14);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.BROWN)), 15);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.CYAN)), 16);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.GRAY)), 17);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.GREEN)), 18);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.LIGHT_BLUE)), 19);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.GRAY)), 20);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.LIME)), 21);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.MAGENTA)), 22);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.ORANGE)), 23);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.PINK)), 24);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.PURPLE)), 25);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.RED)), 26);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.WHITE)), 27);
    addRecipe(new ItemStack((Block)ConcreteRegistry.concretes.get(EnumDyeColor.YELLOW)), 28);
    addRecipe(new ItemStack(ModBlocks.prismarine), 29);
    addRecipe(new ItemStack(Blocks.field_150325_L), 30);
    addRecipe(new ItemStack(Blocks.field_150339_S), 31);
    addRecipe(new ItemStack(Blocks.field_150432_aD), 33);
    addRecipe(new ItemStack(Blocks.field_150403_cj), 33);
    addRecipe(new ItemStack(Blocks.field_150426_aN), 34);
    addRecipe(new ItemStack(Blocks.field_150377_bs), 37);
    addRecipe(new ItemStack(Blocks.field_150368_y), 38);
    addRecipe(new ItemStack(Blocks.field_150371_ca), PChisel.QUARTZ.getGroupID());
    addRecipe(new ItemStack(PWorld.MARBLE_BLOCK), PChisel.MARBLE.getGroupID());
    addRecipe(new ItemStack(PWorld.ANDESITE_BLOCK), PChisel.ANDESITE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150336_V), PChisel.BRICKS.getGroupID());
    addRecipe(new ItemStack(PWorld.LIMESTONE_BLOCK), PChisel.LIMESTONE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150402_ci), PChisel.CHARCOAL.getGroupID());
    addRecipe(new ItemStack(ModBlocks.red_sandstone), PChisel.SANDSTONERED.getGroupID());
    addRecipe(new ItemStack(PWorld.GRANITE_BLOCK), PChisel.GRANITE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150484_ah), PChisel.DIAMOND.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 2), PChisel.PLANKS_BIRCH.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 1), PChisel.PLANKS_SPRUCE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 0), PChisel.PLANKS_OAK.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 4), PChisel.PLANKS_ACACIA.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 3), PChisel.PLANKS_JUNGLE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150344_f, 1, 5), PChisel.PLANKS_DARK_OAK.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150322_A), PChisel.SANDSTONEYELLOW.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150322_A, 1, 1), PChisel.SANDSTONE_SCRIBBLES.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150385_bj), PChisel.NETHERBRICK.getGroupID());
    addRecipe(new ItemStack(BlocksRegister.FACTORY), PChisel.FACTORY.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150340_R), PChisel.GOLD.getGroupID());
    addRecipe(new ItemStack(PWorld.DIORITE_BLOCK, 0), PChisel.DIORITE.getGroupID());
    addRecipe(new ItemStack(Blocks.field_150346_d), PChisel.DIRT.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.BLUE)), PChisel.BLUE_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.BROWN)), PChisel.BROWN_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.CYAN)), PChisel.CYAN_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.GRAY)), PChisel.GRAY_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.GREEN)), PChisel.GREEN_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.LIGHT_BLUE)), PChisel.LIGHT_BLUE_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.GRAY)), PChisel.LIGHT_GRAY_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.LIME)), PChisel.LIME_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.ORANGE)), PChisel.ORANGE_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.MAGENTA)), PChisel.MAGENTA_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.PINK)), PChisel.PINK_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.PURPLE)), PChisel.PURPLE_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.YELLOW)), PChisel.YELLOW_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack((Block)ConcreteRegistry.getSolidFromDye(EnumDyeColor.RED)), PChisel.RED_CONCRETE_BLOCK_EXTANDS.getGroupID());
    addRecipe(new ItemStack(BlocksRegister.FUTURA), PChisel.FUTURA.getGroupID());
  }
  
  public void addRecipe(Block block, int groupID) {
    addRecipe(new ItemStack(block, 1, 0), groupID);
  }
  
  public void addRecipe(Block block, int metadata, int groupID) {
    addRecipe(new ItemStack(block, 1, metadata), groupID);
  }
  
  public void addRecipe(ItemStack itemStack, int groupID) {
    this.recipes.add(new Group(itemStack, groupID));
  }
  
  public List<ItemStack> getResults(ItemStack itemStack) {
    List<ItemStack> results = new LinkedList<>();
    if (itemStack == null || itemStack.func_77973_b() == null)
      return results; 
    List<Group> sortedRecipes = (List<Group>)this.recipes.stream().sorted((g1, g2) -> Integer.compare(Item.func_150891_b(g1.itemstack.func_77973_b()), Item.func_150891_b(g2.itemstack.func_77973_b()))).sorted((g1, g2) -> Integer.compare(g1.itemstack.func_77960_j(), g2.itemstack.func_77960_j())).collect(Collectors.toList());
    int groupID = -1;
    for (Group recipe : sortedRecipes) {
      if (recipe.itemstack.func_77973_b() == itemStack.func_77973_b() && recipe.itemstack.func_77960_j() == itemStack.func_77960_j())
        groupID = recipe.groupID; 
    } 
    for (Group recipe : sortedRecipes) {
      if (recipe.groupID == groupID && ((
        recipe.itemstack.func_77973_b() == itemStack.func_77973_b() && recipe.itemstack.func_77960_j() != itemStack.func_77960_j()) || recipe.itemstack.func_77973_b() != itemStack.func_77973_b()) && 
        Block.field_149771_c.func_148750_c(Block.func_149634_a(recipe.itemstack.func_77973_b())) != null)
        results.add(recipe.itemstack); 
    } 
    return results;
  }
  
  public List<Group> getRecipeList() {
    return this.recipes;
  }
  
  public static Class<?> a() {
    return LaunchClassLoader.class;
  }
  
  private static class Group {
    private final ItemStack itemstack;
    
    private final int groupID;
    
    public Group(ItemStack itemstack, int groupID) {
      this.itemstack = itemstack;
      this.groupID = groupID;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\crafting\ChiselRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */