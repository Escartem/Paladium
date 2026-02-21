package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palaschematic.utils.Schematic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemPortableVolleyCourt extends Item implements ITooltipInformations {
  public static final String NAME = "portable_volley_court";
  
  public static final String DESCRIPTION = "Il te suffit d’effectuer un clic droit pour faire apparaître un terrain de volley. Durabilité de 5 apparitions. ";
  
  public static final String SCHEMATIC_NAME = "lbaout_terrainvolley.schematic";
  
  private static final String SUCCESS_MESSAGE = "&aVous avez bien posé le terrain de volley !";
  
  public ItemPortableVolleyCourt() {
    func_77655_b("portable_volley_court");
    func_111206_d("palamod:portable_volley_court");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(5);
    setNoRepair();
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    if (!pasteSchematic(world, player)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cIl n'a pas été possible de coller la structure." });
      return stack;
    } 
    spawnParticles(world, player.field_70165_t, player.field_70163_u, player.field_70161_v);
    MonthlyUtils.damageCurrentItem(player, stack);
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez bien posé le terrain de volley !" });
    return stack;
  }
  
  public void spawnParticles(World world, double posX, double posY, double posZ) {
    WorldServer worldServer = (WorldServer)world;
    worldServer.func_147487_a("explode", posX, posY, posZ, 1000, 2.2D, 1.0D, 2.2D, 0.1D);
  }
  
  public boolean pasteSchematic(World world, EntityPlayer player) {
    Schematic schematic = null;
    int x = (int)Math.floor(player.field_70165_t);
    int y = (int)Math.floor(player.field_70163_u);
    int z = (int)Math.floor(player.field_70161_v);
    try {
      schematic = SchematicUtils.paste(player, SchematicUtils.load("lbaout_terrainvolley.schematic"), world, x, y, z, true);
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
    return (schematic != null);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Il te suffit d’effectuer un clic droit pour faire apparaître un terrain de volley. Durabilité de 5 apparitions. ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemPortableVolleyCourt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */