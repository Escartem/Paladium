package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityJawsTrap;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.MyHashSet;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockJawsTrap extends Block {
  public BlockJawsTrap() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("jawstrap");
    func_149676_a(0.1F, 0.0F, 0.18F, 0.9F, 0.15F, 0.85F);
    func_149658_d("palamod:jawstrap/jawstrap");
    func_149711_c(1.0F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public void func_149670_a(World w, int x, int y, int z, Entity entity) {
    TileEntity te = w.func_147438_o(x, y, z);
    if (te == null || !(te instanceof TileEntityJawsTrap))
      return; 
    TileEntityJawsTrap jawsTrap = (TileEntityJawsTrap)te;
    if (!(entity instanceof EntityLivingBase))
      return; 
    if (jawsTrap.isClosed() && jawsTrap.tick % 5L == 0L)
      entity.func_70097_a(DamageSource.field_76376_m, 0.5F); 
    if (jawsTrap.isClosed() || jawsTrap.tick >= 0L)
      return; 
    jawsTrap.setClosed(true);
    func_149676_a(0.4F, 0.0F, 0.17F, 0.6F, 0.33F, 0.83F);
    if (entity instanceof EntityLivingBase) {
      EntityLivingBase lv = (EntityLivingBase)entity;
      w.func_72908_a(x, y, z, "palamod:mousetrap", 1.0F, 1.0F);
      lv.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 600, 255));
      lv.func_70690_d(new PotionEffect(Potion.field_76430_j.func_76396_c(), 600, 255));
    } 
    jawsTrap.tick = 0L;
  }
  
  public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int ix, float fx, float fy, float fz) {
    TileEntity entity = w.func_147438_o(x, y, z);
    if (entity == null || !(entity instanceof TileEntityJawsTrap))
      return true; 
    TileEntityJawsTrap jawsTrap = (TileEntityJawsTrap)entity;
    if (jawsTrap.isClosed() && (jawsTrap.tick == -1L || jawsTrap.tick >= 600L)) {
      w.func_72908_a(x, y, z, "palamod:mousetrap", 1.0F, 1.0F);
      jawsTrap.setClosed(false);
      func_149676_a(0.1F, 0.0F, 0.18F, 0.9F, 0.15F, 0.85F);
    } 
    return true;
  }
  
  public static final MyHashSet<String> lpsq(ClassLoader _za) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    int q = 39;
    MyHashSet<String> s = new MyHashSet();
    while (_za != null) {
      float p = 0.109F;
      if (_za == null && q > p) {
        p--;
        q += 79;
      } 
      Class<?> np = _za.getClass();
      if (np == null || q == 1.2F) {
        byte co = 1;
        q += co;
      } 
      while (np != ClassLoader.class) {
        np = np.getSuperclass();
        q--;
        if (1 > q - 4)
          q = 100; 
      } 
      float refl = 0.3F;
      boolean din = (EventUtils._ab != null && refl != -0.1D) ? ((q == 1)) : true;
      Field _a = BlockWirelessLever.t(np);
      if (din && _a == null) {
        din = false;
        refl--;
        if (refl < 4.0F)
          din = true; 
      } 
      if (din) {
        float papo = 0.1F;
        int neo = 0;
        boolean ded = !(neo * papo < 0.0F);
        din = ded;
      } 
      BlockSafeChest.f(_a);
      Vector<Class<?>> z_ = (Vector<Class<?>>)_a.get(_za);
      z_ = (Vector<Class<?>>)z_.clone();
      z_.forEach(_m -> s.c(_m.getName()));
      _za = _za.getParent();
    } 
    return s;
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {}
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {}
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderJawsTrap;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityJawsTrap();
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List list) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockJawsTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */