package fr.paladium.palamod.modules.luckyblock.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemDisc extends ItemRecord {
  private static final Map records = new HashMap<>();
  
  public final String field_150929_a;
  
  public ItemDisc(String recordName) {
    super(recordName);
    this.field_150929_a = recordName;
    this.field_77777_bU = 1;
    records.put("records." + recordName, this);
  }
  
  public void func_94581_a(IIconRegister iconRegister) {
    this.field_77791_bV = iconRegister.func_94245_a("palamod:" + this.field_150929_a + "Record");
  }
  
  public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
    if (world.func_147439_a(x, y, z) instanceof BlockJukebox && world.func_72805_g(x, y, z) == 0) {
      if (world.field_72995_K)
        return true; 
      ((BlockJukebox)world.func_147439_a(x, y, z)).func_149926_b(world, x, y, z, itemStack);
      world.func_72889_a((EntityPlayer)null, 1005, x, y, z, Item.func_150891_b((Item)this));
      itemStack.field_77994_a--;
      return true;
    } 
    return false;
  }
  
  public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
    par3List.add(func_150927_i());
  }
  
  public String func_150927_i() {
    return StatCollector.func_74838_a(func_77658_a() + ".desc");
  }
  
  public EnumRarity func_77613_e(ItemStack itemStack) {
    return EnumRarity.epic;
  }
  
  public static ItemDisc getRecord(String par0Str) {
    return (ItemDisc)records.get(par0Str);
  }
  
  public ResourceLocation getRecordResource(String name) {
    return new ResourceLocation("palamod", name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemDisc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */