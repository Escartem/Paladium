package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.common.items.BaseItemSeed;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SeedPlanterBase extends BaseItem {
  public int r = 0;
  
  public int lvlmin = 0;
  
  public ArrayList<String> addInformation;
  
  public SeedPlanterBase(String unlocalizedName, int radius, int lvlmin, ArrayList<String> information) {
    super("seedplanter/" + unlocalizedName);
    this.r = radius;
    this.lvlmin = lvlmin;
    this.addInformation = information;
    func_77655_b(unlocalizedName);
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    if (!p_77659_2_.field_72995_K) {
      ItemStack itemStack = p_77659_3_.field_71071_by.field_70462_a[p_77659_3_.field_71071_by.field_70461_c + 1];
      Block crops = Blocks.field_150464_aj;
      if (itemStack != null && itemStack.func_77973_b() != null) {
        if (itemStack.func_77973_b() instanceof ItemSeeds) {
          ItemSeeds itemSeeds = (ItemSeeds)itemStack.func_77973_b();
          crops = itemSeeds.getPlant(null, 0, 0, 0);
        } else if (itemStack.func_77973_b() instanceof BaseItemSeed) {
          BaseItemSeed itemSeeds = (BaseItemSeed)itemStack.func_77973_b();
          crops = itemSeeds.getPlant(null, 0, 0, 0);
        } else {
          return super.func_77659_a(p_77659_1_, p_77659_2_, p_77659_3_);
        } 
      } else {
        return super.func_77659_a(p_77659_1_, p_77659_2_, p_77659_3_);
      } 
      ItemStack itemStack1 = p_77659_3_.field_71071_by.field_70462_a[p_77659_3_.field_71071_by.field_70461_c + 1];
      int rf = this.r / 2;
      for (int x = rf * -1; x < rf; x++) {
        for (int z = rf * -1; z < rf; z++) {
          int posX = (int)p_77659_3_.field_70165_t + x;
          int posY = (int)p_77659_3_.field_70163_u;
          int posZ = (int)p_77659_3_.field_70161_v + z;
          if (p_77659_2_.func_147437_c(posX, posY, posZ) && p_77659_2_
            .func_147439_a(posX, posY - 1, posZ) != null && (p_77659_2_
            .func_147439_a(posX, posY - 1, posZ) instanceof net.minecraft.block.BlockFarmland || p_77659_2_
            .func_147439_a(posX, posY - 1, posZ) instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockConnectedDirt) && itemStack1.field_77994_a > 0) {
            p_77659_2_.func_147449_b(posX, posY, posZ, crops);
            itemStack1.field_77994_a--;
          } 
        } 
      } 
      p_77659_3_.field_71071_by.func_70299_a(p_77659_3_.field_71071_by.field_70461_c + 1, itemStack1);
      p_77659_3_.field_71069_bz.func_75142_b();
    } 
    return super.func_77659_a(p_77659_1_, p_77659_2_, p_77659_3_);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    super.func_77624_a(stack, player, list, b);
    list.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a("tooltip.show"));
    if (GuiScreen.func_146272_n() && 
      this.addInformation != null)
      for (String value : this.addInformation)
        list.add(value);  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\SeedPlanterBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */