package fr.paladium.palamod.modules.luckyblock.blocks.june;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.impl.FirstSoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.impl.SecondSoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.impl.ThirdSoundStructure;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSoundTest extends Block implements ITooltipInformations {
  public static final String LINE = "§7§m-------------------";
  
  public static final String UNDEFINED = "§cNon défini";
  
  private final IIcon[] icons = new IIcon[3];
  
  public BlockSoundTest() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("sound_test_block");
    func_149658_d("palamod:sound_test_block_1");
    func_149711_c(1.0F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {}
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta % this.icons.length];
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a("palamod:sound_test_block_1");
    this.icons[1] = register.func_94245_a("palamod:sound_test_block_2");
    this.icons[2] = register.func_94245_a("palamod:sound_test_block_3");
    this.field_149761_L = this.icons[0];
    super.func_149651_a(register);
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < 3; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public AbstractStructure getStructure(ItemStack stack, Location location, EntityPlayer player) {
    int metadata = stack.func_77960_j();
    int maxScore = 0;
    if (stack.func_77978_p() != null && stack.func_77978_p().func_74764_b("score"))
      maxScore = stack.func_77978_p().func_74762_e("score"); 
    if (metadata == 0)
      return (AbstractStructure)new FirstSoundStructure(maxScore, location, player); 
    if (metadata == 1)
      return (AbstractStructure)new SecondSoundStructure(maxScore, location, player); 
    if (metadata == 2)
      return (AbstractStructure)new ThirdSoundStructure(maxScore, location, player); 
    return (AbstractStructure)new FirstSoundStructure(maxScore, location, player);
  }
  
  public String[] getInformations(ItemStack stack, EntityPlayer entityPlayer, boolean b) {
    List<String> list = new ArrayList<>();
    NBTTagCompound nbt = stack.func_77978_p();
    String score = "§eScore: §c";
    String maximalScore = "§eScore maximal: §c";
    if (nbt == null) {
      score = score + "§cNon défini";
      maximalScore = maximalScore + "§cNon défini";
    } else {
      if (nbt.func_74764_b("score")) {
        score = score + nbt.func_74762_e("score");
      } else {
        score = score + "§cNon défini";
      } 
      if (nbt.func_74764_b("maximalScore")) {
        maximalScore = maximalScore + nbt.func_74762_e("maximalScore");
      } else {
        maximalScore = maximalScore + "§cNon défini";
      } 
    } 
    list.add("Quand il est posé ce bloc génère un Test du bloc sonore level " + (stack.func_77960_j() + 1) + ".");
    list.add("§7§m-------------------");
    list.add(score);
    list.add(maximalScore);
    list.add("§7§m-------------------");
    String[] array = new String[list.size()];
    list.toArray(array);
    return array;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\june\BlockSoundTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */