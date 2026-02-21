package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityChatDetector;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChatDetector extends BlockContainer {
  private IIcon[] icons;
  
  public BlockChatDetector() {
    super(Material.field_151576_e);
    func_149663_c("chat_detector");
    func_149658_d("palamod:chatdetector_off");
    func_149713_g(255);
    func_149715_a(0.0F);
    func_149711_c(4.0F);
    func_149752_b(4.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K)
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_CHAT_DETECTOR, true, x, y, z), (EntityPlayerMP)player); 
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityChatDetector();
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a((Block)BlocksRegister.CHAT_DETECTOR);
  }
  
  public void func_149651_a(IIconRegister icon) {
    this.icons = new IIcon[2];
    this.icons[0] = icon.func_94245_a("palamod:chatdetector_off");
    this.icons[1] = icon.func_94245_a("palamod:chatdetector_on");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta];
  }
  
  public boolean func_149744_f() {
    return true;
  }
  
  public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    return true;
  }
  
  public boolean getWeakChanges(IBlockAccess world, int x, int y, int z) {
    return super.getWeakChanges(world, x, y, z);
  }
  
  public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
    World world = (World)p_149709_1_;
    int meta = world.func_72805_g(p_149709_2_, p_149709_3_, p_149709_4_);
    return meta * 15;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockChatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */