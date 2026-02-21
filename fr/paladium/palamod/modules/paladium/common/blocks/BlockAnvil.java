package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.localdata.world.LocalWorldData;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAnvil extends BlockAnvil implements ITooltipWiki {
  private final Item.ToolMaterial toolMaterial;
  
  private final int resistance;
  
  private IIcon top;
  
  public Item.ToolMaterial getToolMaterial() {
    return this.toolMaterial;
  }
  
  public int getResistance() {
    return this.resistance;
  }
  
  public IIcon getTop() {
    return this.top;
  }
  
  public BlockAnvil(Item.ToolMaterial material, int resistance) {
    func_149663_c("anvil_" + material.toString().toLowerCase());
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    func_149672_a(Block.field_149788_p);
    func_149711_c(-1.0F);
    this.toolMaterial = material;
    this.resistance = resistance;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (this.field_149833_b == 3 && side == 1)
      return this.top; 
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.field_149761_L = register.func_94245_a("palamod:anvil/anvil_" + this.toolMaterial.toString().toLowerCase() + "_base");
    this.top = register.func_94245_a("palamod:anvil/anvil_" + this.toolMaterial.toString().toLowerCase() + "_top");
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (player != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b().equals(ItemsRegister.MAGICAL_TOOL)) {
      if (world.field_72995_K)
        return false; 
      Block b = world.func_147439_a(x, y, z);
      ItemStack is = player.func_71045_bC();
      if (is.func_77973_b().equals(ItemsRegister.MAGICAL_TOOL)) {
        EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.func_150898_a(b)));
        if (PFactions.instance.getImpl().hasPermission(player, "BUILD_MAGICAL_TOOL", x, z) != Tristate.FALSE) {
          world.func_72838_d((Entity)item);
          BlockUtils.setBlockToAir(world, x, y, z);
        } 
        return false;
      } 
    } else if (!world.field_72995_K && (
      PFactions.instance.getImpl().hasPermission(player, "BUILD_CONTAINER", x, z) == Tristate.TRUE || EventUtils.canInteract(player, x, y, z))) {
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_ANVIL, world, x, y, z);
    } 
    return true;
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    if (!(world instanceof World))
      return super.canEntityDestroy(world, x, y, z, entity); 
    LocalWorldData localWorld = LocalWorldData.get((World)world);
    NBTTagCompound blockData = localWorld.getBlockData(x, y, z);
    int damage = blockData.func_74764_b("anvil_damage") ? blockData.func_74762_e("anvil_damage") : 0;
    if (damage >= this.resistance)
      return super.canEntityDestroy(world, x, y, z, entity); 
    blockData.func_74768_a("anvil_damage", damage + 1);
    localWorld.setBlockData(x, y, z, blockData);
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */