package fr.paladium.palamod.modules.luckyblock.entity.black;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityLBVillager extends EntityVillager {
  public EntityLBVillager(World world) {
    super(world);
  }
  
  public EntityLBVillager(World world, EntityPlayer player) {
    super(world);
    MerchantRecipeList list = func_70934_b(player);
    list.clear();
    MerchantRecipe recipe = new MerchantRecipe(new ItemStack(BlocksRegister.BLOCK_PALADIUM, 32), new ItemStack(Blocks.field_150346_d, 1));
    recipe.func_82783_a(-6);
    list.add(recipe);
  }
  
  public void func_70933_a(MerchantRecipe recipe) {
    super.func_70933_a(recipe);
    func_70931_l_().func_71053_j();
    if ((new Random()).nextInt(10) == 0)
      if (func_70931_l_() != null) {
        func_70931_l_().func_146105_b((IChatComponent)new ChatComponentText("Villageois > Tu as vraiment été sympa avec moi, je te donne un petit quelque chose"));
        if (!(func_70931_l_()).field_71071_by.func_70441_a(new ItemStack(ItemsRegister.ENDIUM_FRAGMENT, 2)))
          this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT, 2))); 
      } else {
        this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(ItemsRegister.ENDIUM_FRAGMENT, 2)));
      }  
    func_70106_y();
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70173_aa > 12000)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\black\EntityLBVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */