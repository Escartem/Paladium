package fr.paladium.palamod.modules.luckyblock.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.GuiClipboard;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemClipboard extends Item {
  public ItemStack clipboardstack;
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister par1IconRegister) {
    this.field_77791_bV = par1IconRegister.func_94245_a("bibliocraft:cboard");
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int face, float hitX, float hitY, float hitZ) {
    if (player.func_70093_af() && !world.field_72995_K) {
      switch (face) {
        case 2:
          setClipboardBlock(i, j, k - 1, 1, world, player, stack);
          break;
        case 3:
          setClipboardBlock(i, j, k + 1, 3, world, player, stack);
          break;
        case 4:
          setClipboardBlock(i - 1, j, k, 0, world, player, stack);
          break;
        case 5:
          setClipboardBlock(i + 1, j, k, 2, world, player, stack);
          break;
      } 
      return true;
    } 
    return false;
  }
  
  public void setClipboardBlock(int x, int y, int z, int angle, World world, EntityPlayer player, ItemStack stack) {
    Block testBlock = world.func_147439_a(x, y, z);
    if (testBlock.isAir((IBlockAccess)world, x, y, z)) {
      world.func_147465_d(x, y, z, BlocksRegister.clipboard, 0, 2);
      TileEntityClipboard clipboard = (TileEntityClipboard)world.func_147438_o(x, y, z);
      if (clipboard != null) {
        clipboard.setAngle(angle);
        clipboard.func_70299_a(0, stack);
        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
      } 
    } 
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    NBTTagCompound test = stack.func_77978_p();
    if (test == null) {
      NBTTagCompound clipboard = new NBTTagCompound();
      NBTTagCompound page = new NBTTagCompound();
      NBTTagCompound tasks = new NBTTagCompound();
      int[] taskstates = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
      page.func_74783_a("taskStates", taskstates);
      tasks.func_74778_a("task1", "");
      tasks.func_74778_a("task2", "");
      tasks.func_74778_a("task3", "");
      tasks.func_74778_a("task4", "");
      tasks.func_74778_a("task5", "");
      tasks.func_74778_a("task6", "");
      tasks.func_74778_a("task7", "");
      tasks.func_74778_a("task8", "");
      tasks.func_74778_a("task9", "");
      page.func_74782_a("tasks", (NBTBase)tasks);
      page.func_74778_a("title", "");
      clipboard.func_74782_a("page1", (NBTBase)page);
      clipboard.func_74768_a("currentPage", 1);
      clipboard.func_74768_a("totalPages", 1);
      stack.func_77982_d(clipboard);
    } 
    this.clipboardstack = stack;
    if (!player.func_70093_af() && 
      world.field_72995_K)
      openWritingGUI(world, player, stack, true); 
    return stack;
  }
  
  @SideOnly(Side.CLIENT)
  public void openWritingGUI(World world, EntityPlayer player, ItemStack stack, boolean inInv) {
    Minecraft.func_71410_x()
      .func_147108_a((GuiScreen)new GuiClipboard(world, player, stack, inInv, 0, 0, 0));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean whatsthis) {
    String title = "";
    NBTTagCompound clipboard = stack.func_77978_p();
    if (clipboard != null) {
      int currpage = clipboard.func_74762_e("currentPage");
      if (currpage > 0) {
        String pagenum = "page" + currpage;
        NBTTagCompound page = clipboard.func_74775_l(pagenum);
        if (page != null) {
          title = page.func_74779_i("title");
          title = title.trim();
          list.add(title);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */