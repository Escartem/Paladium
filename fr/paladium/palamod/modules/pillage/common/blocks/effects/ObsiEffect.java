package fr.paladium.palamod.modules.pillage.common.blocks.effects;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.factions.dto.leveling.Tristate;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.common.items.ItemBlockMultipleTypes;
import fr.paladium.palamod.util.BlockUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ObsiEffect extends BlockObsidian implements ITooltipWiki {
  public ObjectEffect objectEffect;
  
  @SideOnly(Side.CLIENT)
  private IIcon top;
  
  @SideOnly(Side.CLIENT)
  private IIcon bottom;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] front;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] spikeIcons;
  
  public boolean shouldApplyEffect = true;
  
  public boolean isSpike;
  
  public boolean isDirectional;
  
  public boolean hasMultiFaces;
  
  public ObsiEffect(ObjectEffect effect) {
    func_149663_c(effect.getName());
    func_149658_d("palamod:pillage/effects/" + effect.getName());
    func_149711_c(-1.0F);
    func_149752_b(100.0F);
    func_149672_a(field_149780_i);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    this.objectEffect = effect;
    this.isDirectional = this.objectEffect.getTypeEffect().equals(TypeEffects.SLIME_OBSIDIAN);
    this.isSpike = this.objectEffect.getTypeEffect().equals(TypeEffects.SPIKE_OBSIDIAN);
    this
      
      .hasMultiFaces = (this.objectEffect.getTypeEffect().equals(TypeEffects.REDSTONE_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.BOOM_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.MEGA_BOOM_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.SPIKE_OBSIDIAN));
  }
  
  public ObsiEffect(ObjectEffect effect, boolean noRegister) {
    func_149663_c(effect.getName());
    func_149658_d("palamod:pillage/effects/" + effect.getName());
    func_149711_c(-1.0F);
    func_149752_b(100.0F);
    func_149672_a(field_149780_i);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    this.objectEffect = effect;
    this.isDirectional = this.objectEffect.getTypeEffect().equals(TypeEffects.SLIME_OBSIDIAN);
    this.isSpike = this.objectEffect.getTypeEffect().equals(TypeEffects.SPIKE_OBSIDIAN);
    this
      
      .hasMultiFaces = (this.objectEffect.getTypeEffect().equals(TypeEffects.REDSTONE_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.BOOM_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.MEGA_BOOM_OBSIDIAN) || this.objectEffect.getTypeEffect().equals(TypeEffects.SPIKE_OBSIDIAN));
    if (!noRegister)
      GameRegistry.registerBlock((Block)this, ItemBlockMultipleTypes.class, func_149739_a()); 
  }
  
  public void func_149651_a(IIconRegister iconRegister) {
    if (this.isSpike) {
      this.spikeIcons = new IIcon[7];
      for (int i = 0; i < 7; i++) {
        String suffix = (i > 0) ? ("_" + i) : "";
        this.spikeIcons[i] = iconRegister.func_94245_a(func_149641_N() + suffix);
      } 
    } else if (this.hasMultiFaces) {
      this.field_149761_L = iconRegister.func_94245_a(func_149641_N() + "_side");
      this.top = iconRegister.func_94245_a(func_149641_N() + "_top");
      this.bottom = iconRegister.func_94245_a(func_149641_N() + "_bottom");
    } else {
      this.field_149761_L = iconRegister.func_94245_a(func_149641_N());
    } 
    if (this.isDirectional) {
      this.front = new IIcon[this.isSpike ? 7 : 1];
      if (this.isSpike) {
        for (int i = 0; i < 7; i++) {
          String suffix = (i > 0) ? ("_" + i) : "_front";
          this.front[i] = iconRegister.func_94245_a(func_149641_N() + suffix);
        } 
      } else {
        this.front[0] = iconRegister.func_94245_a(func_149641_N() + "_front");
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (this.isDirectional)
      if (this.isSpike) {
        if ((side == 3 && meta % 7 + 1 == 0) || (side == 4 && meta % 7 + 1 == 1) || (side == 2 && meta % 7 + 1 == 2) || (side == 5 && meta % 7 + 1 == 3))
          return this.front[meta % 7]; 
      } else if ((side == 3 && meta == 0) || (side == 4 && meta == 1) || (side == 2 && meta == 2) || (side == 5 && meta == 3) || (side == 0 && meta == 4) || (side == 1 && meta == 5)) {
        return this.front[0];
      }  
    if (this.isSpike)
      return this.spikeIcons[meta % 7]; 
    if (this.hasMultiFaces)
      return (side == 0) ? this.bottom : ((side == 1) ? this.top : this.field_149761_L); 
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> subBlocks) {
    if (this.isSpike) {
      for (int i = 0; i < 7; i++)
        subBlocks.add(new ItemStack(item, 1, i)); 
    } else {
      subBlocks.add(new ItemStack(item, 1, 0));
    } 
  }
  
  public int quantityDropped(int meta, int fortune, Random random) {
    return this.shouldApplyEffect ? 0 : super.quantityDropped(meta, fortune, random);
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    if (this.isDirectional) {
      if (world.func_147439_a(x + 1, y, z) == this)
        return world.func_72805_g(x + 1, y, z); 
      if (world.func_147439_a(x - 1, y, z) == this)
        return world.func_72805_g(x - 1, y, z); 
      if (world.func_147439_a(x, y, z + 1) == this)
        return world.func_72805_g(x, y, z + 1); 
      if (world.func_147439_a(x, y, z - 1) == this)
        return world.func_72805_g(x, y, z - 1); 
      if (world.func_147439_a(x, y + 1, z) == this)
        return world.func_72805_g(x, y + 1, z); 
      if (world.func_147439_a(x, y - 1, z) == this)
        return world.func_72805_g(x, y - 1, z); 
    } 
    return super.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, meta);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    if (!this.isDirectional || !item.func_77942_o() || !item.func_77978_p().func_74764_b("obsiMeta"))
      return; 
    int meta = item.func_77978_p().func_74762_e("obsiMeta");
    world.func_72921_c(x, y, z, meta, 2);
  }
  
  public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
    if (this.shouldApplyEffect)
      this.objectEffect.applyEffect(world, x, y, z, meta, (Entity)player); 
    super.func_149636_a(world, player, x, y, z, meta);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> items = new ArrayList<>();
    if (!this.shouldApplyEffect)
      items.add(new ItemStack((Block)this, 1, metadata)); 
    return items;
  }
  
  public int func_149656_h() {
    return 2;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
      return false; 
    Block b = world.func_147439_a(x, y, z);
    if (player != null && b != null && player.func_71045_bC() != null) {
      ItemStack is = player.func_71045_bC();
      if (is.func_77973_b().equals(ItemsRegister.MAGICAL_TOOL)) {
        EntityItem item = new EntityItem(world, x, y, z, new ItemStack(Item.func_150898_a(b), 1, world.func_72805_g(x, y, z)));
        if (PFactions.instance.getImpl().hasPermission(player, "BUILD_MAGICAL_TOOL", x, z) != Tristate.FALSE) {
          world.func_72838_d((Entity)item);
          BlockUtils.setBlockToAir(world, x, y, z);
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#1.-les-objets-a-base-dobsidienne";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\effects\ObsiEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */