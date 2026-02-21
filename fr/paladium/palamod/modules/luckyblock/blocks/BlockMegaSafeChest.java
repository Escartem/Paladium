package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMegaSafeChest extends BlockSafeChest {
  public BlockMegaSafeChest(String unlocalizedName) {
    super(unlocalizedName);
    this.drop = false;
    this.texture = "safechest/megasafe_front";
    func_149658_d("palamod:safechest/megasafe_front");
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityMegaSafeChest();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    super.func_149689_a(world, x, y, z, entity, stack);
    TileEntitySafeChest tile = (TileEntitySafeChest)world.func_147438_o(x, y, z);
    if (tile != null && stack.func_77978_p() != null) {
      NBTTagCompound tag = stack.func_77978_p();
      NBTTagList nbttaglist = tag.func_150295_c("itemlist", 10);
      tile.content = new ItemStack[tile.func_70302_i_()];
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
        if (j >= 0 && j < tile.content.length)
          tile.content[j] = ItemStack.func_77949_a(nbttagcompound1); 
      } 
    } 
  }
  
  public void onBlockExploded(World w, int x, int y, int z, Explosion explosion) {
    if (!w.field_72995_K) {
      TileEntitySafeChest te = (TileEntitySafeChest)w.func_147438_o(x, y, z);
      if (te == null)
        return; 
      ItemStack itemStack = new ItemStack((Block)this, 1);
      NBTTagCompound tag = new NBTTagCompound();
      if (te.getCode() != null && !te.getCode().isEmpty())
        tag.func_74778_a("Code", te.getCode()); 
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < te.content.length; i++) {
        if (te.content[i] != null) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.func_74774_a("Slot", (byte)i);
          te.content[i].func_77955_b(nbttagcompound1);
          nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        } 
      } 
      tag.func_74782_a("itemlist", (NBTBase)nbttaglist);
      NBTTagCompound displayCompound = new NBTTagCompound();
      NBTTagList lore = new NBTTagList();
      if (!te.isCodeInitialized()) {
        lore.func_74742_a((NBTBase)new NBTTagString("§cCode secret non initialisé"));
      } else {
        lore.func_74742_a((NBTBase)new NBTTagString("§eCe coffre fort contient :"));
        lore.func_74742_a((NBTBase)new NBTTagString(""));
        Map<String, Long> counts = new HashMap<>();
        for (int j = 0; j < te.content.length; j++) {
          if (te.content[j] != null && te.content[j].func_77973_b() != null) {
            String name = te.content[j].func_77973_b().func_77658_a();
            long count = ((Long)counts.getOrDefault(name, Long.valueOf(0L))).longValue();
            counts.put(name, Long.valueOf(count + (te.content[j]).field_77994_a));
          } 
        } 
        for (Map.Entry<String, Long> entry : counts.entrySet())
          lore.func_74742_a((NBTBase)new NBTTagString("§dx" + entry.getValue() + " §f" + (String)entry.getKey())); 
      } 
      displayCompound.func_74782_a("Lore", (NBTBase)lore);
      tag.func_74782_a("display", (NBTBase)displayCompound);
      itemStack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(w, x, y + 1.0D, z, itemStack);
      entityitem.field_145804_b = 10;
      w.func_72838_d((Entity)entityitem);
    } 
    w.func_147468_f(x, y, z);
    func_149723_a(w, x, y, z, explosion);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntitySafeChest te = (TileEntitySafeChest)world.func_147438_o(x, y, z);
      if (te == null)
        return; 
      ItemStack itemStack = new ItemStack((Block)this, 1, meta);
      NBTTagCompound tag = new NBTTagCompound();
      if (te.getCode() != null && !te.getCode().isEmpty())
        tag.func_74778_a("Code", te.getCode()); 
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < te.content.length; i++) {
        if (te.content[i] != null) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.func_74774_a("Slot", (byte)i);
          te.content[i].func_77955_b(nbttagcompound1);
          nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        } 
      } 
      tag.func_74782_a("itemlist", (NBTBase)nbttaglist);
      NBTTagCompound displayCompound = new NBTTagCompound();
      NBTTagList lore = new NBTTagList();
      if (!te.isCodeInitialized()) {
        lore.func_74742_a((NBTBase)new NBTTagString("§cCode secret non initialisé"));
      } else {
        lore.func_74742_a((NBTBase)new NBTTagString("§eCe coffre fort contient :"));
        lore.func_74742_a((NBTBase)new NBTTagString(""));
        Map<String, Long> counts = new HashMap<>();
        for (int j = 0; j < te.content.length; j++) {
          if (te.content[j] != null && te.content[j].func_77973_b() != null) {
            String name = te.content[j].func_77973_b().func_77658_a();
            long count = ((Long)counts.getOrDefault(name, Long.valueOf(0L))).longValue();
            counts.put(name, Long.valueOf(count + (te.content[j]).field_77994_a));
          } 
        } 
        for (Map.Entry<String, Long> entry : counts.entrySet())
          lore.func_74742_a((NBTBase)new NBTTagString("§dx" + entry.getValue() + " §f" + (String)entry.getKey())); 
      } 
      displayCompound.func_74782_a("Lore", (NBTBase)lore);
      tag.func_74782_a("display", (NBTBase)displayCompound);
      itemStack.func_77982_d(tag);
      EntityItem entityitem = new EntityItem(world, x, y + 1.0D, z, itemStack);
      entityitem.field_145804_b = 10;
      world.func_72838_d((Entity)entityitem);
    } 
    if (player == null);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:safechest/megasafe_front");
    this.icons[1] = register.func_94245_a("palamod:safechest/megasafe_side");
    this.field_149761_L = register.func_94245_a(func_149641_N());
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockMegaSafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */