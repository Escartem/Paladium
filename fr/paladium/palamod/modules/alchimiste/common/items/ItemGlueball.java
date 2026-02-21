package fr.paladium.palamod.modules.alchimiste.common.items;

import fr.paladium.palamod.modules.alchimiste.common.entities.EntityGlueball;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumGlueball;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlueball extends ItemAlchemist {
  private EnumGlueball glueball;
  
  private String name;
  
  public ItemGlueball(EnumGlueball glueball) {
    this.glueball = glueball;
    this.name = glueball.getName().toLowerCase() + "_glueball";
    func_77655_b(this.name);
    func_111206_d("palamod:alchimiste/" + this.name);
    func_77625_d(4);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      item.field_77994_a--; 
    world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d
        .nextFloat() * 0.4F + 0.8F));
    if (!world.field_72995_K && 
      EventUtils.canInteract(player, player.field_70165_t, player.field_70163_u, player.field_70161_v))
      world.func_72838_d((Entity)(new EntityGlueball(world, (EntityLivingBase)player)).setG(this.glueball)); 
    return item;
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    switch (this.glueball) {
      case RED:
        list.add("§cRégénère 1 coeurs");
        break;
      case BLUE:
        list.add("§cFait sauter le joueur dessus");
        break;
      case CYAN:
        list.add("§cEnlève tous les effets du joueur dessus");
        break;
      case GRAY:
        list.add("§cApplique au joueur dessus un effet de night vision");
        break;
      case GREEN:
        list.add("§cDonne un effet de slowness");
        break;
      case ORANGE:
        list.add("§cEnflamme le joueur dessus");
        list.add("§cEt lui donne un effet de speed");
        break;
      case PURPLE:
        list.add("§cDonne régénération pendant 6 secondes au joueur dessus");
        break;
      case YELLOW:
        list.add("§cDonne un effet de nausée et ");
        list.add("§cde blindness au joueur dessus");
        break;
      case GREEN_DARK:
        list.add("§cDonne un effet d’haste au joueur dessus");
        break;
      case GREEN_FLASH:
        list.add("§cApplique un effet de poison");
        break;
    } 
  }
  
  public int func_77647_b(int p_77647_1_) {
    return p_77647_1_;
  }
  
  public EnumGlueball getGlueball() {
    return this.glueball;
  }
  
  public void setGlueball(EnumGlueball glueball) {
    this.glueball = glueball;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\items\ItemGlueball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */