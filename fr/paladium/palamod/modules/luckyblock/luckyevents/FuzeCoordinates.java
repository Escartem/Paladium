package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class FuzeCoordinates extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack tomeStack = new ItemStack(Items.field_151164_bB);
    NBTTagList bookPages = new NBTTagList();
    bookPages.func_74742_a((NBTBase)new NBTTagString("Voilà les coordonnées de Fuze : https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
    tomeStack.func_77983_a("pages", (NBTBase)bookPages);
    tomeStack.func_77983_a("author", (NBTBase)new NBTTagString("bigh4ck3r"));
    tomeStack.func_77983_a("title", (NBTBase)new NBTTagString("Coordonnées  de Fuze"));
    tomeStack.func_151001_c("Coordonnées de Fuze");
    player.field_71071_by.func_70441_a(tomeStack);
  }
  
  public String getName() {
    return "Coordonnées de Fuze";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "coordonnes_de_fuze";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FuzeCoordinates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */