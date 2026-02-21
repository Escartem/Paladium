package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.blocks;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityGiftChest;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.utils.RandomItemStackPicker;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.util.DurationConverter;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class BlockGiftChest extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "gift_chest";
  
  private static final String DESCRIPTION = "";
  
  private static final String MESSAGE_ON_COOLDOWN = "&eVous devez encore attendre &c%s &epour une nouvelle utilisation.";
  
  private RandomItemStackPicker picker;
  
  public BlockGiftChest() {
    super(Material.field_151575_d);
    func_149663_c("gift_chest");
    func_149658_d("palamod:gift_chest");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
    this.picker = null;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    if (!EventUtils.canInteract(player, x, y, z)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas interagir ici."));
      return false;
    } 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGiftChest))
      return true; 
    TileEntityGiftChest chest = (TileEntityGiftChest)tile;
    long now = System.currentTimeMillis();
    if (chest.isOnCooldown()) {
      MonthlyUtils.sendMessage(player, new String[] { String.format("&eVous devez encore attendre &c%s &epour une nouvelle utilisation.", new Object[] { DurationConverter.fromMillisToString(chest.getNextUseMillis() - now) }) });
      return true;
    } 
    ItemStack reward = getReward();
    if (reward == null)
      return true; 
    chest.use(now);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, getReward());
    return true;
  }
  
  private ItemStack getReward() {
    initPicker();
    return this.picker.pick();
  }
  
  private void initPicker() {
    if (this.picker != null)
      return; 
    this.picker = new RandomItemStackPicker(Arrays.asList(new RandomItemStackPicker.RandomItemStack[] { 
            RandomItemStackPicker.RandomItemStack.builder().chance(0.25D).stack(new ItemStack(ItemsRegister.LEGENDARYSTONE_RANDOM, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(0.75D).stack(new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(1.25D).stack(new ItemStack(ItemsRegister.JOB_CANDY, 5, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(2.75D).stack(new ItemStack((Item)ItemsRegister.DOUBLE_XP_POTION, 1, 1)).build(), 
            
            RandomItemStackPicker.RandomItemStack.builder().chance(2.75D).stack(new ItemStack(ItemsRegister.SWORD_TOO_BIG, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(4.25D).stack(new ItemStack(ItemsRegister.IRON_SKULL_HAMMER, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(4.75D).stack(new ItemStack(ItemsRegister.RUNIC_AXE, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(6.25D).stack(new ItemStack(ItemsRegister.ENERGETIC_BOW_SWORD, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(6.75D).stack(new ItemStack(ItemsRegister.SITAR, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(8.25D).stack(new ItemStack(ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(8.75D).stack(new ItemStack(ItemsRegister.BLUNDERBUSS, 1)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(10.75D).stack(new ItemStack(ItemsRegister.SWORD_PISTOL, 1)).build(), 
            
            RandomItemStackPicker.RandomItemStack.builder().chance(10.75D).stack(new ItemStack(PWorld.TRIXIUM_ORE, 16)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(20.25D).stack(new ItemStack(PWorld.PALADIUM_GREEN_ORE, 8)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(20.75D).stack(new ItemStack(PWorld.FINDIUM_ORE, 4)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(20.75D).stack(new ItemStack(PWorld.PALADIUM_ORE, 16)).build(), 
            
            RandomItemStackPicker.RandomItemStack.builder().chance(52.25D).stack(new ItemStack(PWorld.PALADIUM_GREEN_ORE, 4)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(56.75D).stack(new ItemStack(PWorld.PALADIUM_ORE, 8)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(76.25D).stack(new ItemStack(PWorld.TRIXIUM_ORE, 4)).build(), 
            RandomItemStackPicker.RandomItemStack.builder().chance(82.75D).stack(new ItemStack(PWorld.PALADIUM_ORE, 4)).build() }));
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
    NBTTagCompound compound = item.func_77978_p();
    TileEntity tile = world.func_147438_o(x, y, z);
    if (compound == null || !(tile instanceof TileEntityGiftChest))
      return; 
    TileEntityGiftChest chest = (TileEntityGiftChest)tile;
    if (compound.func_74764_b("nextUseMillis"))
      chest.setNextUseMillis(compound.func_74763_f("nextUseMillis")); 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityGiftChest))
      return; 
    TileEntityGiftChest chest = (TileEntityGiftChest)tile;
    ItemStack stack = new ItemStack(BlocksRegister.GIFT_CHEST);
    NBTTagCompound tag = new NBTTagCompound();
    tag.func_74772_a("nextUseMillis", chest.getNextUseMillis());
    stack.func_77982_d(tag);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityGiftChest();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 22;
  }
  
  public boolean func_149662_c() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\blocks\BlockGiftChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */