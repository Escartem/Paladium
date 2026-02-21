package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class ItemFaux extends Item {
  public ItemFaux() {
    this.field_77777_bU = 1;
    func_77656_e(-1);
    func_77655_b("faux");
    func_111206_d("palamod:faux");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return true;
  }
  
  public Item.ToolMaterial func_150913_i() {
    return PToolMaterial.paladium;
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    if (p_77659_3_.func_70093_af()) {
      NBTTagCompound compound = new NBTTagCompound();
      if (p_77659_1_.func_77942_o())
        compound = p_77659_1_.func_77978_p(); 
      String soul = "";
      if (compound.func_74764_b("soul"))
        soul = compound.func_74779_i("soul"); 
      String soulUUID = "";
      if (compound.func_74764_b("soulUUID"))
        soul = compound.func_74779_i("soulUUID"); 
      int dura = 15;
      if (compound.func_74764_b("dura"))
        dura = compound.func_74762_e("dura"); 
      if (soul.equals("")) {
        if (!p_77659_2_.field_72995_K)
          return p_77659_1_; 
        p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§cAucune âme n'est contenue dans la faux."));
      } else if (dura == 0) {
        if (!p_77659_2_.field_72995_K)
          return p_77659_1_; 
        p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§cVotre faux n'a plus de durabilité."));
      } else if (dura >= 1 && !soul.equals("")) {
        EntityPlayer player = p_77659_3_;
        NBTTagCompound compoundS = new NBTTagCompound();
        compoundS.func_74778_a("playerUUID", soulUUID);
        ItemStack stack = new ItemStack(BlocksRegister.STATUE, 1);
        stack.func_77982_d(compoundS);
        player.field_71071_by.func_70441_a(stack);
        compound.func_74768_a("dura", dura - 1);
        compound.func_74778_a("soul", "");
        compound.func_74778_a("soulUUID", "");
        p_77659_1_.func_77982_d(compound);
        if (!p_77659_2_.field_72995_K)
          return p_77659_1_; 
        p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§aVous venez de créer la statue de §e" + soul));
      } 
    } else {
      if (p_77659_2_.field_72995_K)
        return p_77659_1_; 
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§eShift + Clique droit:"));
      p_77659_3_.func_146105_b((IChatComponent)new ChatComponentText("§7- Transforme l'âme contenue en statue."));
    } 
    return p_77659_1_;
  }
  
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
    p_77624_3_.add("§eTuer un joueur capture §6son âme§e.");
    p_77624_3_.add("§aShift + Clique droit:");
    p_77624_3_.add("§7- Transforme l'âme en statue.");
    NBTTagCompound compound = new NBTTagCompound();
    if (p_77624_1_.func_77942_o())
      compound = p_77624_1_.func_77978_p(); 
    String soul = "";
    if (compound.func_74764_b("soul"))
      soul = compound.func_74779_i("soul"); 
    int dura = 15;
    if (compound.func_74764_b("dura"))
      dura = compound.func_74762_e("dura"); 
    p_77624_3_.add("§7§m---------------------------.");
    p_77624_3_.add("§cÂme emprisonée: " + (soul.equals("") ? "Aucune" : soul));
    p_77624_3_.add("§aDurabilité: " + dura + "/15");
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemFaux.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */