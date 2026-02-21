package fr.paladium.palamod.modules.paladium.common.tileentities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.FileUtil;
import fr.paladium.palapass.common.quest.palamod.PrintBookQuest;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityPrintPress extends TileEntity implements ISidedInventory, IEntityAdditionalSpawnData {
  public ItemStack[] pressInventory;
  
  public int pressAngle;
  
  public int furnaceCookTime = 0;
  
  public int furnaceBurnTime = 0;
  
  public int currentItemBurnTime = 0;
  
  public boolean pressani = true;
  
  private float move1 = 0.0F;
  
  private boolean movetest1 = false;
  
  private float move2 = 0.0F;
  
  private boolean movetest2 = false;
  
  private boolean plateturn = false;
  
  private boolean rollerturn = true;
  
  private boolean showPlateText = false;
  
  private boolean showInkText = false;
  
  private boolean showEmptyBookText = false;
  
  private boolean showNewBookText = false;
  
  private EntityPlayer player;
  
  public TileEntityPrintPress() {
    this.pressInventory = new ItemStack[4];
  }
  
  public int func_70302_i_() {
    return this.pressInventory.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.pressInventory[slot];
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.pressInventory[slot] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public ItemStack func_70298_a(int slot, int amount) {
    ItemStack stack = func_70301_a(slot);
    if (stack != null)
      if (stack.field_77994_a <= amount) {
        func_70299_a(slot, (ItemStack)null);
      } else {
        stack = stack.func_77979_a(amount);
        if (stack.field_77994_a == 0)
          func_70299_a(slot, (ItemStack)null); 
      }  
    return stack;
  }
  
  public ItemStack func_70304_b(int slot) {
    ItemStack stack = func_70301_a(slot);
    if (stack != null)
      func_70299_a(slot, (ItemStack)null); 
    return stack;
  }
  
  public int func_70297_j_() {
    return 64;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player
      .func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) < 64.0D);
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList tagList = nbt.func_150295_c("pressInventory", 10);
    this.pressInventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.pressInventory.length)
        this.pressInventory[slot] = ItemStack.func_77949_a(tag); 
    } 
    this.furnaceBurnTime = nbt.func_74765_d("BurnTime");
    this.furnaceCookTime = nbt.func_74765_d("CookTime");
    this.currentItemBurnTime = getItemBurnTime(this.pressInventory[0]);
    this.pressani = nbt.func_74767_n("pressani");
    this.movetest1 = nbt.func_74767_n("movetest1");
    this.movetest2 = nbt.func_74767_n("movetest2");
    this.plateturn = nbt.func_74767_n("plateturn");
    this.rollerturn = nbt.func_74767_n("rollerturn");
    this.move1 = nbt.func_74760_g("move1");
    this.move2 = nbt.func_74760_g("move2");
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.pressInventory.length; i++) {
      ItemStack stack = this.pressInventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    nbt.func_74782_a("pressInventory", (NBTBase)itemList);
    nbt.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
    nbt.func_74777_a("CookTime", (short)this.furnaceCookTime);
    nbt.func_74757_a("pressani", this.pressani);
    nbt.func_74757_a("movetest1", this.movetest1);
    nbt.func_74757_a("movetest2", this.movetest2);
    nbt.func_74757_a("plateturn", this.plateturn);
    nbt.func_74757_a("rollerturn", this.rollerturn);
    nbt.func_74776_a("move1", this.move1);
    nbt.func_74776_a("move2", this.move2);
  }
  
  public void writeSpawnData(ByteBuf additionalData) {
    for (int i = 0; i < func_70302_i_(); i++) {
      if (this.pressInventory[i] != null) {
        additionalData.writeInt(i);
        ByteBufUtils.writeItemStack(additionalData, this.pressInventory[i]);
      } 
    } 
  }
  
  public void readSpawnData(ByteBuf buffer) {
    this.pressInventory = new ItemStack[func_70302_i_()];
    if (buffer.isReadable())
      this.pressInventory[buffer.readInt()] = ByteBufUtils.readItemStack(buffer); 
  }
  
  public void setShowPlateText(boolean show) {
    this.showPlateText = show;
  }
  
  public void setShowInkText(boolean show) {
    this.showInkText = show;
  }
  
  public void setShowEmptyBookText(boolean show) {
    this.showEmptyBookText = show;
  }
  
  public void setShowNewBookText(boolean show) {
    this.showNewBookText = show;
  }
  
  public boolean getShowPlateText() {
    return this.showPlateText;
  }
  
  public boolean getShowInkText() {
    return this.showInkText;
  }
  
  public boolean getShowEmptyBookText() {
    return this.showEmptyBookText;
  }
  
  public boolean getShowNewBookText() {
    return this.showNewBookText;
  }
  
  public String func_145825_b() {
    return "jds.tileentityprintpress";
  }
  
  public boolean hasPlate() {
    ItemStack stack = func_70301_a(1);
    if (stack != null) {
      Item isplate = stack.func_77973_b();
      if (isplate instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || isplate instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate)
        return true; 
    } 
    return false;
  }
  
  public boolean hasEnchantedPlate() {
    ItemStack stack = func_70301_a(1);
    if (stack != null) {
      Item isplate = stack.func_77973_b();
      if (isplate instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate) {
        NBTTagList taglist = getEnchantmentTagList(stack);
        int tagscount = 0;
        if (taglist != null) {
          tagscount = taglist.func_74745_c();
          if (tagscount > 0)
            return true; 
        } 
      } 
    } 
    return false;
  }
  
  public boolean hasEnchantedBook() {
    ItemStack stack = func_70301_a(3);
    if (stack != null) {
      Item isEnchBook = stack.func_77973_b();
      if (isEnchBook instanceof net.minecraft.item.ItemEnchantedBook)
        return true; 
    } 
    return false;
  }
  
  public boolean hasBlank() {
    ItemStack stack = func_70301_a(2);
    if (stack != null)
      return true; 
    return false;
  }
  
  public int numberBlanks() {
    if (hasBlank()) {
      ItemStack stack = func_70301_a(2);
      int stackSize = stack.field_77994_a;
      if (stackSize > 0 && stackSize < 17)
        return 1; 
      if (stackSize > 16 && stackSize < 33)
        return 2; 
      if (stackSize > 32 && stackSize < 49)
        return 3; 
      if (stackSize > 48)
        return 4; 
    } 
    return 0;
  }
  
  public boolean hasSigned() {
    ItemStack stack = func_70301_a(3);
    if (stack != null)
      return true; 
    return false;
  }
  
  public boolean hasInk() {
    ItemStack stack = func_70301_a(0);
    if (stack != null)
      return true; 
    return false;
  }
  
  public String plateName() {
    String bookname = "";
    if (hasPlate()) {
      ItemStack stack = func_70301_a(1);
      NBTTagCompound tag = stack.func_77978_p();
      if (tag != null)
        bookname = tag.func_74779_i("bookName"); 
      if (hasEnchantedPlate()) {
        NBTTagList taglist = getEnchantmentTagList(stack);
        int tagscount = 0;
        if (taglist != null)
          tagscount = taglist.func_74745_c(); 
        if (tagscount > 0) {
          short var7 = taglist.func_150305_b(0).func_74765_d("id");
          short var8 = taglist.func_150305_b(0).func_74765_d("lvl");
          if (Enchantment.field_77331_b[var7] != null)
            bookname = Enchantment.field_77331_b[var7].func_77316_c(var8); 
        } 
      } 
    } else {
      bookname = "none";
    } 
    return bookname;
  }
  
  public NBTTagList getEnchantmentTagList(ItemStack stack) {
    return (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("StoredEnchantments")) ? (NBTTagList)stack.field_77990_d
      .func_74781_a("StoredEnchantments") : new NBTTagList();
  }
  
  public boolean addPlate(ItemStack plate) {
    if (!hasPlate()) {
      Item test = plate.func_77973_b();
      if (test instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || test instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate) {
        func_70299_a(1, plate);
        return true;
      } 
    } 
    return false;
  }
  
  public int addInk(ItemStack ink) {
    if (ink != null) {
      int inkmeta = ink.func_77960_j();
      if (inkmeta != 0)
        return -1; 
      ItemStack pressInk = func_70301_a(0);
      if (pressInk == null) {
        func_70299_a(0, ink);
        return 0;
      } 
      int inksize = ink.field_77994_a;
      int totalsize = inksize + pressInk.field_77994_a;
      if (totalsize < 65) {
        pressInk.field_77994_a = totalsize;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return 0;
      } 
      if (totalsize > 64) {
        pressInk.field_77994_a = 64;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return totalsize - 64;
      } 
    } 
    return -1;
  }
  
  public int addBlank(ItemStack books) {
    if (books != null) {
      ItemStack pressBooks = func_70301_a(2);
      if (pressBooks == null) {
        func_70299_a(2, books);
        return 0;
      } 
      int booksize = books.field_77994_a;
      int totalsize = booksize + pressBooks.field_77994_a;
      if (totalsize < 65) {
        pressBooks.field_77994_a = totalsize;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return 0;
      } 
      if (totalsize > 64) {
        pressBooks.field_77994_a = 64;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return totalsize - 64;
      } 
    } else {
      func_70299_a(2, books);
      return 0;
    } 
    return -1;
  }
  
  public void setAngle(int angle) {
    this.pressAngle = angle;
  }
  
  public int getAngle() {
    return this.pressAngle;
  }
  
  public int getinkStackSize() {
    ItemStack ink = func_70301_a(0);
    if (ink != null) {
      int size = ink.field_77994_a;
      if (size > 0 && size < 8)
        return 1; 
      if (size > 7 && size < 16)
        return 2; 
      if (size > 15 && size < 24)
        return 3; 
      if (size > 23 && size < 32)
        return 4; 
      if (size > 31 && size < 40)
        return 5; 
      if (size > 39 && size < 48)
        return 6; 
      if (size > 47 && size < 56)
        return 7; 
      if (size > 55)
        return 8; 
      return 0;
    } 
    return 0;
  }
  
  public boolean ismovetest1() {
    return this.movetest1;
  }
  
  public void setmovetest1(boolean set) {
    this.movetest1 = set;
  }
  
  public boolean ismovetest2() {
    return this.movetest2;
  }
  
  public void setmovetest2(boolean set) {
    this.movetest2 = set;
  }
  
  public boolean isplateturn() {
    return this.plateturn;
  }
  
  public void setplateturn(boolean set) {
    this.plateturn = set;
  }
  
  public boolean isrollerturn() {
    return this.rollerturn;
  }
  
  public void setrollerturn(boolean set) {
    this.rollerturn = set;
  }
  
  public float getmove1() {
    return this.move1;
  }
  
  public void setMove1(float set) {
    this.move1 = set;
  }
  
  public float getmove2() {
    return this.move2;
  }
  
  public void setMove2(float set) {
    this.move2 = set;
  }
  
  public int getBurnTimeRemainingScaled(int par1) {
    if (this.currentItemBurnTime == 0)
      this.currentItemBurnTime = 200; 
    return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
  }
  
  public boolean isBurning() {
    return (this.furnaceBurnTime > 0);
  }
  
  public boolean canUpdate() {
    return true;
  }
  
  public void func_145845_h() {
    boolean var1 = (this.furnaceBurnTime > 0);
    boolean var2 = false;
    if (this.furnaceBurnTime > 0)
      this.furnaceBurnTime--; 
    if (!this.field_145850_b.field_72995_K) {
      if (this.furnaceBurnTime == 0 && canSmelt()) {
        this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.pressInventory[0]);
        if (this.furnaceBurnTime > 0) {
          var2 = true;
          if (this.pressInventory[0] != null) {
            setPressAnimation(false);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            (this.pressInventory[0]).field_77994_a--;
            if ((this.pressInventory[0]).field_77994_a <= 0) {
              this.pressInventory[0] = this.pressInventory[0].func_77973_b()
                .getContainerItem(this.pressInventory[0]);
              func_70299_a(0, (ItemStack)null);
            } 
          } 
        } 
      } 
      if (isBurning() && canSmelt()) {
        this.furnaceCookTime++;
        if (this.furnaceCookTime == 200) {
          this.furnaceCookTime = 0;
          smeltItem();
          var2 = true;
        } 
      } else {
        this.furnaceCookTime = 0;
      } 
      if (var1 != ((this.furnaceBurnTime > 0)))
        var2 = true; 
    } 
    if (var2)
      func_70296_d(); 
  }
  
  private boolean canSmelt() {
    if (this.pressInventory[3] == null && hasPlate() && this.pressInventory[2] != null && this.pressInventory[0] != null)
      if ((this.pressInventory[0]).field_77994_a >= 2)
        return true;  
    return false;
  }
  
  public void smeltItem() {
    if (canSmelt()) {
      FileUtil util = new FileUtil();
      int booknum = util.getBookNumber(this.field_145850_b, plateName());
      if (booknum == -1 && !hasEnchantedPlate()) {
        System.out.println("This is not a valid book name!");
        setPressAnimation(true);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return;
      } 
      ItemStack newstack = null;
      if (booknum == -1 && hasEnchantedPlate()) {
        ItemStack plate = func_70301_a(1);
        if (plate.func_77942_o()) {
          ItemStack blankenchbook = new ItemStack((Item)Items.field_151134_bR, 1, 0);
          blankenchbook.func_77982_d((NBTTagCompound)plate.func_77978_p().func_74737_b());
          newstack = blankenchbook.func_77946_l();
          int damage = plate.func_77960_j();
          plate.func_77964_b(damage + 1);
          if (plate.func_77960_j() > 2)
            func_70299_a(1, (ItemStack)null); 
        } else {
          return;
        } 
      } 
      if (booknum != -1) {
        System.out.println("Checking the book type");
        int checkBookType = util.getBookType(this.field_145850_b, booknum);
        if (checkBookType != 0) {
          ItemStack blankbook = new ItemStack(Items.field_151122_aG, 1, 0);
          newstack = util.loadBook(this.field_145850_b, blankbook, booknum);
        } 
      } 
      if (this.pressInventory[3] == null) {
        this.pressInventory[3] = newstack.func_77946_l();
        if (this.player instanceof net.minecraft.entity.player.EntityPlayerMP)
          PrintBookQuest.trigger(this.player, (newstack.func_77946_l()).field_77994_a); 
      } 
      (this.pressInventory[2]).field_77994_a--;
      if ((this.pressInventory[2]).field_77994_a <= 0)
        this.pressInventory[2] = null; 
      setPressAnimation(true);
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } 
  }
  
  public static int getItemBurnTime(ItemStack par0ItemStack) {
    if (par0ItemStack == null)
      return 0; 
    Item var2 = par0ItemStack.func_77973_b();
    if (var2 instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumInk)
      return 200; 
    return 0;
  }
  
  public void setPressAnimation(boolean ison) {
    this.pressani = ison;
  }
  
  public boolean getPressAntimation() {
    return this.pressani;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.pressInventory.length; i++) {
      ItemStack stack = this.pressInventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    dataTag.func_74782_a("pressInventory", (NBTBase)itemList);
    dataTag.func_74757_a("pressani", this.pressani);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    NBTTagList tagList = nbtData.func_150295_c("pressInventory", 10);
    this.pressInventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.pressInventory.length)
        this.pressInventory[slot] = ItemStack.func_77949_a(tag); 
    } 
    this.pressani = nbtData.func_74767_n("pressani");
  }
  
  public boolean func_94041_b(int slot, ItemStack itemstack) {
    Item stackitem = itemstack.func_77973_b();
    if (stackitem != null) {
      if (slot == 0 && stackitem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumInk) {
        int metacheck = itemstack.func_77960_j();
        if (metacheck == 0)
          return true; 
      } 
      if (slot == 1 && 
        stackitem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate)
        return true; 
      if (slot == 2 && 
        stackitem instanceof net.minecraft.item.ItemBook)
        return true; 
    } 
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    AxisAlignedBB bb = TileEntity.INFINITE_EXTENT_AABB;
    bb = AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1));
    return bb;
  }
  
  public int[] func_94128_d(int side) {
    if (side == 0) {
      int[] arrayOfInt = new int[1];
      arrayOfInt[0] = 3;
      return arrayOfInt;
    } 
    int[] sides = new int[3];
    sides[0] = 0;
    sides[1] = 1;
    sides[2] = 2;
    return sides;
  }
  
  public boolean func_102007_a(int slot, ItemStack itemstack, int side) {
    if (side >= 1 && side <= 5 && 
      itemstack != null) {
      Item stackItem = itemstack.func_77973_b();
      if (stackItem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumInk && itemstack.func_77960_j() == 0 && slot == 0)
        return true; 
      if (stackItem instanceof net.minecraft.item.ItemBook && slot == 2)
        return true; 
      if ((stackItem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || stackItem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate) && slot == 1)
        return true; 
    } 
    return false;
  }
  
  public boolean func_102008_b(int slot, ItemStack itemstack, int side) {
    return (side == 0 && slot == 3);
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\tileentities\TileEntityPrintPress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */