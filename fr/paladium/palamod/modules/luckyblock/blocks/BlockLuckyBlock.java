package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class BlockLuckyBlock extends Block {
  private LuckyType type;
  
  public void setType(LuckyType type) {
    this.type = type;
  }
  
  public LuckyType getType() {
    return this.type;
  }
  
  public BlockLuckyBlock(Material material, LuckyType type, String name, String texture) {
    super(material);
    this.type = type;
    func_149663_c(name);
    func_149658_d(texture);
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
    func_149752_b(1000.0F);
    func_149675_a(true);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(x, y, z);
    if (te == null)
      return; 
    te.func_145841_b(new NBTTagCompound());
    if (item.func_77942_o()) {
      if (item.func_77978_p().func_74764_b("event"))
        te.setEvent(LuckyEvents.values()[item.func_77978_p().func_74762_e("event")]); 
      if (item.func_77978_p().func_74764_b("version"))
        te.setVersion(item.func_77978_p().func_74762_e("version")); 
    } 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(x, y, z);
    if (te == null)
      return; 
    if (te.getEvent() != null) {
      PacketUseLuckyBlock.perform((EntityPlayerMP)player, x, y, z);
      return;
    } 
    PlayerUtils.dropItemStack((Entity)player, x, y, z, ((ItemStack)LuckyType.Util.getIcons().get(this.type)).func_77946_l());
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
    TileEntityLuckyBlock tile = (TileEntityLuckyBlock)world.func_147438_o(x, y, z);
    if (tile == null)
      return true; 
    if (tile.isOpen()) {
      if (!world.field_72995_K)
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCe Lucky Block est déjà ouvert.")); 
      return true;
    } 
    tile.setOpen(true);
    if (!world.field_72995_K) {
      if (!EventUtils.canInteract(player, x, y, z)) {
        tile.setOpen(false);
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas ouvrir ce LuckyBlock ici."));
        return false;
      } 
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_LUCKY_BLOCK, true, x, y, z), (EntityPlayerMP)player);
    } 
    return true;
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityLuckyBlock(this.type);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random random) {
    if (!world.func_72899_e(x, y, z))
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityLuckyBlock))
      return; 
    TileEntityLuckyBlock luckyBlock = (TileEntityLuckyBlock)tile;
    if (luckyBlock.getEvent() == null)
      return; 
    Color color = LuckyType.Util.getColorFrom(luckyBlock.getType());
    world.func_72869_a("reddust", x, (y + 1), z, color.r, color.g, color.b);
    world.func_72869_a("reddust", (x + 1), (y + 1), z, color.r, color.g, color.b);
    world.func_72869_a("reddust", x, (y + 1), (z + 1), color.r, color.g, color.b);
    world.func_72869_a("reddust", (x + 1), (y + 1), (z + 1), color.r, color.g, color.b);
    world.func_72869_a("reddust", x, y, z, color.r, color.g, color.b);
    world.func_72869_a("reddust", (x + 1), y, z, color.r, color.g, color.b);
    world.func_72869_a("reddust", x, y, (z + 1), color.r, color.g, color.b);
    world.func_72869_a("reddust", (x + 1), y, (z + 1), color.r, color.g, color.b);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */