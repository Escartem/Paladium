package fr.paladium.palamod.modules.alchimiste.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.common.blocks.itemblocks.ItemBlockTank;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityTank;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.BlockAlchemist;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumTank;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTank extends BlockAlchemist implements ITooltipWiki {
  public static String[] authorizedItems = new String[] { 
      "item.sulfuricwater", "item.angelicwater", "item.bucketWater", "item.bucketLava", "item.flask0", "item.flask1", "item.flask2", "item.flask3", "item.flask4", "item.flask5", 
      "item.flask6", "item.flask7" };
  
  public String name;
  
  public EnumTank tank;
  
  public BlockTank(String name, EnumTank tank) {
    super(Material.field_151573_f);
    this.name = name;
    this.tank = tank;
    func_149711_c(4.0F);
    func_149752_b(2.0F);
    func_149663_c(name);
    func_149658_d("palamod:tanks/" + this.name);
  }
  
  public EnumTank getTank() {
    return this.tank;
  }
  
  public Class<? extends TileEntity> getTileEntity() {
    return (Class)TileEntityTank.class;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.tanksRenderId;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K)
      if (player.field_71071_by.func_70448_g() != null && (player.field_71071_by
        .func_70448_g().func_77973_b() instanceof net.minecraft.item.ItemBucket || player.field_71071_by
        .func_70448_g().func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.items.ItemFlask)) {
        TileEntityTank tank = (TileEntityTank)world.func_147438_o(x, y, z);
        ItemStack current = player.func_71045_bC();
        if (current.func_77973_b().equals(ItemsRegister.BUCKET_SULFURIC)) {
          if (tank.getLiquid().trim().length() == 0) {
            tank.setLiquid("item.sulfuricwater");
            tank.setLiquidLevel(1000);
            if (!player.field_71075_bZ.field_75098_d)
              current.func_150996_a(Items.field_151133_ar); 
          } else if (tank.getLiquid().equals("item.sulfuricwater")) {
            if (tank.getLiquidLevel() <= tank.getTank().getMaxSeve() - 1000) {
              tank.setLiquidLevel(tank.getLiquidLevel() + 1000);
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } else if (tank.getLiquidLevel() + 1000 > tank.getTank().getMaxSeve() && tank
              .getLiquidLevel() < tank.getTank().getMaxSeve()) {
              tank.setLiquidLevel(tank.getTank().getMaxSeve());
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } 
          } 
        } else if (current.func_77973_b().equals(Items.field_151129_at)) {
          if (tank.getLiquid().trim().length() == 0) {
            tank.setLiquid(Blocks.field_150353_l.func_149739_a());
            tank.setLiquidLevel(1000);
            if (!player.field_71075_bZ.field_75098_d)
              current.func_150996_a(Items.field_151133_ar); 
          } else if (tank.getLiquid().equals("tile.lava")) {
            if (tank.getLiquidLevel() <= tank.getTank().getMaxSeve() - 1000) {
              tank.setLiquidLevel(tank.getLiquidLevel() + 1000);
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } else if (tank.getLiquidLevel() + 1000 > tank.getTank().getMaxSeve() && tank
              .getLiquidLevel() < tank.getTank().getMaxSeve()) {
              tank.setLiquidLevel(tank.getTank().getMaxSeve());
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } 
          } 
        } else if (current.func_77973_b().equals(Items.field_151131_as)) {
          if (tank.getLiquid().trim().length() == 0) {
            tank.setLiquid(Blocks.field_150355_j.func_149739_a());
            tank.setLiquidLevel(1000);
            if (!player.field_71075_bZ.field_75098_d)
              current.func_150996_a(Items.field_151133_ar); 
          } else if (tank.getLiquid().equals("tile.water")) {
            if (tank.getLiquidLevel() <= tank.getTank().getMaxSeve() - 1000) {
              tank.setLiquidLevel(tank.getLiquidLevel() + 1000);
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } else if (tank.getLiquidLevel() + 1000 > tank.getTank().getMaxSeve() && tank
              .getLiquidLevel() < tank.getTank().getMaxSeve()) {
              tank.setLiquidLevel(tank.getTank().getMaxSeve());
              if (!player.field_71075_bZ.field_75098_d)
                current.func_150996_a(Items.field_151133_ar); 
            } 
          } 
        } else if (current.func_77973_b().equals(Items.field_151133_ar)) {
          if (tank.getLiquid().trim().length() > 3 && tank.getLiquidLevel() >= 1000) {
            ItemStack toSend;
            switch (tank.getLiquid()) {
              case "tile.water":
                toSend = new ItemStack(Items.field_151131_as, 1);
                break;
              case "tile.lava":
                toSend = new ItemStack(Items.field_151129_at, 1);
                break;
              case "item.sulfuricwater":
                toSend = new ItemStack((Item)ItemsRegister.BUCKET_SULFURIC, 1);
                break;
              default:
                return false;
            } 
            tank.setLiquidLevel(tank.getLiquidLevel() - 1000);
            if (tank.getLiquidLevel() == 0)
              tank.setLiquid(""); 
            if (!player.field_71075_bZ.field_75098_d)
              current.field_77994_a--; 
            EntityItem entityitem = player.func_71019_a(toSend, false);
            entityitem.field_145804_b = 0;
            entityitem.func_145797_a(player.func_70005_c_());
          } 
        } else if (current.func_77973_b().equals(ItemsRegister.FLASK)) {
          int stackSize = current.field_77994_a;
          for (int i = 0; i < stackSize; i++) {
            int seveLevel = current.func_77960_j();
            seveLevel = (int)(seveLevel / 6.0D * 100.0D);
            int mbToAdd = seveLevel * 10;
            if (tank.getLiquid().startsWith("treeseve-")) {
              String seveType = tank.getLiquid().split("-")[1];
              if (mbToAdd == 0)
                return false; 
              if (!current.func_77942_o()) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.func_74778_a("seveType", seveType);
                current.func_77982_d(compound);
              } else if (!current.func_77978_p().func_74764_b("seveType")) {
                current.func_77978_p().func_74778_a("seveType", seveType);
              } 
              if (current.func_77960_j() == 0)
                current.func_77978_p().func_74778_a("seveType", seveType); 
              if (!current.func_77978_p().func_74779_i("seveType").equals(seveType))
                return false; 
              if (tank.getLiquidLevel() + mbToAdd <= tank.getTank().getMaxSeve()) {
                if (current.func_77960_j() == 0)
                  return false; 
                if (current.field_77994_a-- <= 0)
                  player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
                ItemStack fl = new ItemStack((Item)ItemsRegister.FLASK, 1, current.func_77960_j() - seveLevel);
                NBTTagCompound tag = new NBTTagCompound();
                tag.func_74778_a("seveType", seveType);
                fl.func_77982_d(tag);
                EntityItem entityitem = player.func_71019_a(fl, false);
                entityitem.field_145804_b = 0;
                entityitem.func_145797_a(player.func_70005_c_());
                tank.setLiquidLevel(tank.getLiquidLevel() + mbToAdd);
              } 
            } else if (tank.getLiquid().trim().length() < 4 && 
              current.func_77942_o() && current.func_77978_p().func_74764_b("seveType")) {
              tank.setLiquid("treeseve-" + current.func_77978_p().func_74779_i("seveType"));
              if (current.func_77960_j() == 0)
                return false; 
              if (current.field_77994_a-- <= 0)
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
              ItemStack fl = new ItemStack((Item)ItemsRegister.FLASK, 1, current.func_77960_j() - seveLevel);
              NBTTagCompound tag = new NBTTagCompound();
              tag.func_74778_a("seveType", current.func_77978_p().func_74779_i("seveType"));
              fl.func_77982_d(tag);
              EntityItem entityitem = player.func_71019_a(fl, false);
              entityitem.field_145804_b = 0;
              entityitem.func_145797_a(player.func_70005_c_());
              tank.setLiquidLevel(mbToAdd);
            } 
          } 
        } 
      }  
    return true;
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    if (!world.field_72995_K)
      if (player.field_71071_by.func_70448_g() != null && (player.field_71071_by
        .func_70448_g().func_77973_b() instanceof net.minecraft.item.ItemBucket || player.field_71071_by
        .func_70448_g().func_77973_b() instanceof fr.paladium.palamod.modules.alchimiste.common.items.ItemFlask)) {
        TileEntityTank tank = (TileEntityTank)world.func_147438_o(x, y, z);
        ItemStack current = player.func_71045_bC();
        if (current.func_77973_b().equals(ItemsRegister.FLASK) && current.func_77960_j() < 6) {
          int seveLevel = current.func_77960_j() + 1;
          seveLevel = (int)(seveLevel / 6.0D * 100.0D) - (int)((seveLevel - 1) / 6.0D * 100.0D);
          seveLevel *= current.field_77994_a;
          int mbToAdd = seveLevel * 10;
          if (tank.getLiquid().startsWith("treeseve-")) {
            String seveType = tank.getLiquid().split("-")[1];
            if (tank.getLiquidLevel() >= mbToAdd) {
              tank.setLiquidLevel(tank.getLiquidLevel() - mbToAdd);
              if (tank.getLiquidLevel() == 0)
                tank.setLiquid(""); 
              ItemStack fl = new ItemStack((Item)ItemsRegister.FLASK, current.field_77994_a, current.func_77960_j() + 1);
              NBTTagCompound tag = new NBTTagCompound();
              tag.func_74778_a("seveType", seveType);
              fl.func_77982_d(tag);
              player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, fl);
              player.field_71071_by.func_70296_d();
            } 
          } 
        } 
      }  
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      BlockAlchemist blockAlchemist;
      TileEntityTank tank = (TileEntityTank)world.func_147438_o(x, y, z);
      String liquid = tank.getLiquid();
      int liquidLevel = tank.getLiquidLevel();
      Block b = Blocks.field_150348_b;
      switch (getTank()) {
        case GOLD:
          blockAlchemist = BlocksRegister.TANK_GOLD;
          break;
        case TITANE:
          blockAlchemist = BlocksRegister.TANK_TITANE;
          break;
        case PALADIUM:
          blockAlchemist = BlocksRegister.TANK_PALADIUM;
          break;
        case AMETHYSTE:
          blockAlchemist = BlocksRegister.TANK_AMETHYSTE;
          break;
      } 
      ItemStack stack = new ItemStack((Block)blockAlchemist, 1);
      NBTTagCompound tag = new NBTTagCompound();
      tag.func_74778_a("liquid", liquid);
      tag.func_74768_a("liquidLevel", liquidLevel);
      stack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(world, x, y, z);
      entityitem.field_145804_b = 0;
      entityitem.func_92058_a(stack);
      world.func_72838_d((Entity)entityitem);
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  protected void func_149642_a(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_) {}
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
    if (!world.field_72995_K && 
      stack.func_77942_o() && stack.func_77978_p().func_74764_b("liquid") && stack
      .func_77978_p().func_74764_b("liquidLevel")) {
      TileEntityTank tank = (TileEntityTank)world.func_147438_o(x, y, z);
      tank.setLiquid(stack.func_77978_p().func_74779_i("liquid"));
      tank.setLiquidLevel(stack.func_77978_p().func_74762_e("liquidLevel"));
    } 
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityTank(this.tank);
  }
  
  public static String getLiquid(ItemStack is) {
    if (is.func_77942_o() && is.func_77978_p().func_74764_b("liquid"))
      return is.field_77990_d.func_74779_i("liquid"); 
    return "";
  }
  
  public static int getLiquidLevel(ItemStack is) {
    if (is.func_77942_o() && is.func_77978_p().func_74764_b("liquidLevel"))
      return is.field_77990_d.func_74762_e("liquidLevel"); 
    return 0;
  }
  
  public static void setLiquid(ItemStack is, String liquid) {
    NBTTagCompound tag = new NBTTagCompound();
    if (is.func_77942_o())
      tag = is.field_77990_d; 
    if (!tag.func_74764_b("liquidLevel"))
      tag.func_74768_a("liquidLevel", 0); 
    tag.func_74778_a("liquid", liquid);
    is.func_77982_d(tag);
  }
  
  public static void setLiquidLevel(ItemStack is, int level) {
    NBTTagCompound tag = new NBTTagCompound();
    if (is.func_77942_o())
      tag = is.field_77990_d; 
    if (!tag.func_74764_b("liquid"))
      tag.func_74778_a("liquid", ""); 
    tag.func_74768_a("liquidLevel", level);
    is.func_77982_d(tag);
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public ItemBlock getCustomStack() {
    return (ItemBlock)new ItemBlockTank((Block)this);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#3.-les-tanks";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockTank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */