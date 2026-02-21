package fr.paladium.palamod.modules.luckyblock.items.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.VieilleHistoire;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenGuiBook;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemOldStory extends ItemWritableBook implements ITooltipInformations {
  private IIcon[] icons = new IIcon[3];
  
  public ItemOldStory() {
    func_77655_b("old_story");
    func_77627_a(true);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public IIcon func_77617_a(int metadata) {
    return this.icons[metadata];
  }
  
  public void func_94581_a(IIconRegister iconRegister) {
    this.icons[0] = iconRegister.func_94245_a("palamod:old_story_0");
    this.icons[1] = iconRegister.func_94245_a("palamod:old_story_1");
    this.icons[2] = iconRegister.func_94245_a("palamod:old_story_2");
  }
  
  public String func_77667_c(ItemStack itemStack) {
    int metadata = itemStack.func_77960_j();
    return func_77658_a() + "_" + metadata;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      NBTTagCompound tagCompound = stack.func_77978_p();
      if (tagCompound == null)
        tagCompound = new NBTTagCompound(); 
      NBTTagList pagesNBT = new NBTTagList();
      int storyId = stack.func_77960_j();
      if (VieilleHistoire.stories.length > storyId) {
        List<String> pages = VieilleHistoire.splitStory(storyId);
        for (String page : pages)
          pagesNBT.func_74742_a((NBTBase)new NBTTagString(page)); 
        pagesNBT.func_74742_a((NBTBase)new NBTTagString(VieilleHistoire.answers[storyId][VieilleHistoire.solutions[storyId]]));
      } 
      tagCompound.func_74782_a("pages", (NBTBase)pagesNBT);
      stack.func_77982_d(tagCompound);
      PalaMod.getNetwork().sendTo((IMessage)new PacketOpenGuiBook(stack), (EntityPlayerMP)player);
    } 
    return stack;
  }
  
  public boolean func_77651_p() {
    return true;
  }
  
  public static boolean func_150930_a(NBTTagCompound p_150930_0_) {
    return true;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Donne ce livre ainsi que les deux autres", "livres d’histoire au libraire.", "Il te donnera sûrement une récompense." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\may\ItemOldStory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */