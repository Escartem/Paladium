package fr.paladium.palamod.modules.trixium.items;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.util.DurationConverter;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemCloud extends ItemBlock {
  public ItemCloud(Block block) {
    super(block);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b("cloud");
    func_111206_d("palamod:cloud");
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (player.func_70093_af()) {
      int duration = getDuration(item);
      switch (duration) {
        case 15:
          setDuration(item, 30);
          return super.func_77659_a(item, world, player);
        case 30:
          setDuration(item, 60);
          return super.func_77659_a(item, world, player);
        case 60:
          setDuration(item, 300);
          return super.func_77659_a(item, world, player);
        case 300:
          setDuration(item, 15);
          return super.func_77659_a(item, world, player);
      } 
      setDuration(item, 15);
    } 
    return super.func_77659_a(item, world, player);
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    Block block = world.func_147439_a(x, y, z);
    if (world.field_72995_K)
      return false; 
    if (PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
      (new Notification(Notification.NotificationType.WARNING, "L'item " + func_77653_i(item) + " est désactivé en possession de l'oeuf", PEggHunt.data.isEndEvent() ? "end" : "egghunt")).send((EntityPlayerMP)player);
      return false;
    } 
    if (block == Blocks.field_150431_aC && (world.func_72805_g(x, y, z) & 0x7) < 1) {
      side = 1;
    } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I && !block.isReplaceable((IBlockAccess)world, x, y, z)) {
      if (side == 0)
        y--; 
      if (side == 1)
        y++; 
      if (side == 2)
        z--; 
      if (side == 3)
        z++; 
      if (side == 4)
        x--; 
      if (side == 5)
        x++; 
    } 
    if (item.field_77994_a == 0)
      return false; 
    if (!player.func_82247_a(x, y, z, side, item) || (y == 255 && this.field_150939_a.func_149688_o().func_76220_a()) || !world.func_147472_a(this.field_150939_a, x, y, z, false, side, (Entity)player, item))
      return false; 
    int i1 = func_77647_b(item.func_77960_j());
    int j1 = this.field_150939_a.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, i1);
    if (placeBlockAt(item, player, world, x, y, z, side, hitX, hitY, hitZ, j1))
      world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), this.field_150939_a.field_149762_H.func_150496_b(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0F) / 2.0F, this.field_150939_a.field_149762_H.func_150494_d() * 0.8F); 
    return true;
  }
  
  public String func_77653_i(ItemStack item) {
    return super.func_77653_i(item) + " §8[§e" + DurationConverter.fromMillisToString(getDuration(item) * 1000L) + "§8]§r";
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    int duration = getDuration(item);
    list.add("§8 » Durée : §e" + DurationConverter.fromMillisToString(duration * 1000L));
    super.func_77624_a(item, player, list, flag);
  }
  
  public int getDuration(ItemStack item) {
    int duration = 15;
    if (item.func_77942_o() && item.func_77978_p().func_74764_b("duration"))
      duration = item.func_77978_p().func_74762_e("duration"); 
    return duration;
  }
  
  private void setDuration(ItemStack item, int duration) {
    NBTTagCompound compound = new NBTTagCompound();
    if (item.func_77942_o())
      compound = item.func_77978_p(); 
    compound.func_74768_a("duration", duration);
    item.func_77982_d(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\items\ItemCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */