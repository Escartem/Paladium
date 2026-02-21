package fr.paladium.palamod.modules.luckyblock.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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

public class TileEntityClipboard extends TileEntity implements IInventory {
  private ItemStack[] inventory;
  
  public int angle;
  
  public int button0state = 0;
  
  public int button1state = 0;
  
  public int button2state = 0;
  
  public int button3state = 0;
  
  public int button4state = 0;
  
  public int button5state = 0;
  
  public int button6state = 0;
  
  public int button7state = 0;
  
  public int button8state = 0;
  
  public String button0text = " ";
  
  public String button1text = " ";
  
  public String button2text = " ";
  
  public String button3text = " ";
  
  public String button4text = " ";
  
  public String button5text = " ";
  
  public String button6text = " ";
  
  public String button7text = " ";
  
  public String button8text = " ";
  
  public String titletext = " ";
  
  public int currentPage = 1;
  
  public int totalPages = 1;
  
  public TileEntityClipboard() {
    this.inventory = new ItemStack[1];
  }
  
  public void updateClipboardFromPlayerSelection(int selection) {
    if (selection >= 0 && selection <= 8) {
      checkCheckBox(selection);
    } else if (selection == 10) {
      changePage(false);
    } else if (selection == 11) {
      changePage(true);
    } 
  }
  
  public void checkCheckBox(int box) {
    switch (box) {
      case 0:
        if (this.button0state >= 2) {
          this.button0state = 0;
          break;
        } 
        this.button0state++;
        break;
      case 1:
        if (this.button1state >= 2) {
          this.button1state = 0;
          break;
        } 
        this.button1state++;
        break;
      case 2:
        if (this.button2state >= 2) {
          this.button2state = 0;
          break;
        } 
        this.button2state++;
        break;
      case 3:
        if (this.button3state >= 2) {
          this.button3state = 0;
          break;
        } 
        this.button3state++;
        break;
      case 4:
        if (this.button4state >= 2) {
          this.button4state = 0;
          break;
        } 
        this.button4state++;
        break;
      case 5:
        if (this.button5state >= 2) {
          this.button5state = 0;
          break;
        } 
        this.button5state++;
        break;
      case 6:
        if (this.button6state >= 2) {
          this.button6state = 0;
          break;
        } 
        this.button6state++;
        break;
      case 7:
        if (this.button7state >= 2) {
          this.button7state = 0;
          break;
        } 
        this.button7state++;
        break;
      case 8:
        if (this.button8state >= 2) {
          this.button8state = 0;
          break;
        } 
        this.button8state++;
        break;
    } 
    ItemStack clipStack = func_70301_a(0);
    if (clipStack != null) {
      NBTTagCompound cliptags = clipStack.func_77978_p();
      if (cliptags != null) {
        String pagenum = "page" + cliptags.func_74762_e("currentPage");
        NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
        if (pagetag != null) {
          int[] taskstat = pagetag.func_74759_k("taskStates");
          taskstat[0] = this.button0state;
          taskstat[1] = this.button1state;
          taskstat[2] = this.button2state;
          taskstat[3] = this.button3state;
          taskstat[4] = this.button4state;
          taskstat[5] = this.button5state;
          taskstat[6] = this.button6state;
          taskstat[7] = this.button7state;
          taskstat[8] = this.button8state;
          pagetag.func_74783_a("taskStates", taskstat);
          clipStack.func_77982_d(cliptags);
          func_70299_a(0, clipStack);
          getNBTData();
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } 
      } 
    } 
  }
  
  public void changePage(boolean nextPage) {
    if (nextPage) {
      if (this.currentPage < this.totalPages)
        this.currentPage++; 
    } else if (this.currentPage > 1) {
      this.currentPage--;
    } 
    ItemStack clipStack = func_70301_a(0);
    if (clipStack != null) {
      NBTTagCompound cliptags = clipStack.func_77978_p();
      if (cliptags != null) {
        String pagenum = "page" + this.currentPage;
        NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
        if (pagetag != null) {
          cliptags.func_74768_a("currentPage", this.currentPage);
          clipStack.func_77982_d(cliptags);
          func_70299_a(0, clipStack);
          getNBTData();
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } 
      } 
    } 
  }
  
  public void getNBTData() {
    ItemStack clipStack = func_70301_a(0);
    if (clipStack != null) {
      NBTTagCompound cliptags = clipStack.func_77978_p();
      if (cliptags != null) {
        this.currentPage = cliptags.func_74762_e("currentPage");
        this.totalPages = cliptags.func_74762_e("totalPages");
        String pagenum = "page" + this.currentPage;
        NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
        if (pagetag != null) {
          int[] taskstat = pagetag.func_74759_k("taskStates");
          if (taskstat.length > 0) {
            this.button0state = taskstat[0];
            this.button1state = taskstat[1];
            this.button2state = taskstat[2];
            this.button3state = taskstat[3];
            this.button4state = taskstat[4];
            this.button5state = taskstat[5];
            this.button6state = taskstat[6];
            this.button7state = taskstat[7];
            this.button8state = taskstat[8];
          } 
          NBTTagCompound tasks = pagetag.func_74775_l("tasks");
          this.button0text = tasks.func_74779_i("task1");
          this.button1text = tasks.func_74779_i("task2");
          this.button2text = tasks.func_74779_i("task3");
          this.button3text = tasks.func_74779_i("task4");
          this.button4text = tasks.func_74779_i("task5");
          this.button5text = tasks.func_74779_i("task6");
          this.button6text = tasks.func_74779_i("task7");
          this.button7text = tasks.func_74779_i("task8");
          this.button8text = tasks.func_74779_i("task9");
          this.titletext = pagetag.func_74779_i("title");
        } 
      } 
    } 
  }
  
  public void setAngle(int newAngle) {
    this.angle = newAngle;
  }
  
  public int getAngle() {
    return this.angle;
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  public int func_70302_i_() {
    return this.inventory.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.inventory[slot];
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.inventory[slot] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    if (stack != null)
      getNBTData(); 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public ItemStack func_70298_a(int slot, int amount) {
    ItemStack stack = func_70301_a(slot);
    Item stackSizeTest = stack.func_77973_b();
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
    return 1;
  }
  
  public boolean func_70300_a(EntityPlayer player) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player
      .func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) < 64.0D);
  }
  
  public boolean func_145818_k_() {
    return false;
  }
  
  public String func_145825_b() {
    return "jds.teclipboard";
  }
  
  public void func_70295_k_() {}
  
  public void func_70305_f() {}
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList tagList = nbt.func_150295_c("Inventory", 10);
    this.inventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.inventory.length)
        this.inventory[slot] = ItemStack.func_77949_a(tag); 
    } 
    this.angle = nbt.func_74762_e("angle");
    this.button0state = nbt.func_74762_e("button0state");
    this.button1state = nbt.func_74762_e("button1state");
    this.button2state = nbt.func_74762_e("button2state");
    this.button3state = nbt.func_74762_e("button3state");
    this.button4state = nbt.func_74762_e("button4state");
    this.button5state = nbt.func_74762_e("button5state");
    this.button6state = nbt.func_74762_e("button6state");
    this.button7state = nbt.func_74762_e("button7state");
    this.button8state = nbt.func_74762_e("button8state");
    this.button0text = nbt.func_74779_i("button0text");
    this.button1text = nbt.func_74779_i("button1text");
    this.button2text = nbt.func_74779_i("button2text");
    this.button3text = nbt.func_74779_i("button3text");
    this.button4text = nbt.func_74779_i("button4text");
    this.button5text = nbt.func_74779_i("button5text");
    this.button6text = nbt.func_74779_i("button6text");
    this.button7text = nbt.func_74779_i("button7text");
    this.button8text = nbt.func_74779_i("button8text");
    this.currentPage = nbt.func_74762_e("currentPage");
    this.totalPages = nbt.func_74762_e("totalPages");
    this.titletext = nbt.func_74779_i("titletext");
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {
      ItemStack stack = this.inventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    nbt.func_74782_a("Inventory", (NBTBase)itemList);
    nbt.func_74768_a("angle", this.angle);
    nbt.func_74768_a("button0state", this.button0state);
    nbt.func_74768_a("button1state", this.button1state);
    nbt.func_74768_a("button2state", this.button2state);
    nbt.func_74768_a("button3state", this.button3state);
    nbt.func_74768_a("button4state", this.button4state);
    nbt.func_74768_a("button5state", this.button5state);
    nbt.func_74768_a("button6state", this.button6state);
    nbt.func_74768_a("button7state", this.button7state);
    nbt.func_74768_a("button8state", this.button8state);
    nbt.func_74778_a("button0text", this.button0text);
    nbt.func_74778_a("button1text", this.button1text);
    nbt.func_74778_a("button2text", this.button2text);
    nbt.func_74778_a("button3text", this.button3text);
    nbt.func_74778_a("button4text", this.button4text);
    nbt.func_74778_a("button5text", this.button5text);
    nbt.func_74778_a("button6text", this.button6text);
    nbt.func_74778_a("button7text", this.button7text);
    nbt.func_74778_a("button8text", this.button8text);
    nbt.func_74768_a("currentPage", this.currentPage);
    nbt.func_74768_a("totalPages", this.totalPages);
    nbt.func_74778_a("titletext", this.titletext);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    dataTag.func_74768_a("angle", this.angle);
    dataTag.func_74768_a("button0state", this.button0state);
    dataTag.func_74768_a("button1state", this.button1state);
    dataTag.func_74768_a("button2state", this.button2state);
    dataTag.func_74768_a("button3state", this.button3state);
    dataTag.func_74768_a("button4state", this.button4state);
    dataTag.func_74768_a("button5state", this.button5state);
    dataTag.func_74768_a("button6state", this.button6state);
    dataTag.func_74768_a("button7state", this.button7state);
    dataTag.func_74768_a("button8state", this.button8state);
    dataTag.func_74778_a("button0text", this.button0text);
    dataTag.func_74778_a("button1text", this.button1text);
    dataTag.func_74778_a("button2text", this.button2text);
    dataTag.func_74778_a("button3text", this.button3text);
    dataTag.func_74778_a("button4text", this.button4text);
    dataTag.func_74778_a("button5text", this.button5text);
    dataTag.func_74778_a("button6text", this.button6text);
    dataTag.func_74778_a("button7text", this.button7text);
    dataTag.func_74778_a("button8text", this.button8text);
    dataTag.func_74768_a("currentPage", this.currentPage);
    dataTag.func_74768_a("totalPages", this.totalPages);
    dataTag.func_74778_a("titletext", this.titletext);
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {
      ItemStack stack = this.inventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    this.angle = nbtData.func_74762_e("angle");
    this.button0state = nbtData.func_74762_e("button0state");
    this.button1state = nbtData.func_74762_e("button1state");
    this.button2state = nbtData.func_74762_e("button2state");
    this.button3state = nbtData.func_74762_e("button3state");
    this.button4state = nbtData.func_74762_e("button4state");
    this.button5state = nbtData.func_74762_e("button5state");
    this.button6state = nbtData.func_74762_e("button6state");
    this.button7state = nbtData.func_74762_e("button7state");
    this.button8state = nbtData.func_74762_e("button8state");
    this.button0text = nbtData.func_74779_i("button0text");
    this.button1text = nbtData.func_74779_i("button1text");
    this.button2text = nbtData.func_74779_i("button2text");
    this.button3text = nbtData.func_74779_i("button3text");
    this.button4text = nbtData.func_74779_i("button4text");
    this.button5text = nbtData.func_74779_i("button5text");
    this.button6text = nbtData.func_74779_i("button6text");
    this.button7text = nbtData.func_74779_i("button7text");
    this.button8text = nbtData.func_74779_i("button8text");
    this.currentPage = nbtData.func_74762_e("currentPage");
    this.totalPages = nbtData.func_74762_e("totalPages");
    this.titletext = nbtData.func_74779_i("titletext");
    NBTTagList tagList = nbtData.func_150295_c("BookcaseInv", 10);
    this.inventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.inventory.length)
        this.inventory[slot] = ItemStack.func_77949_a(tag); 
    } 
  }
  
  public boolean func_94041_b(int slot, ItemStack itemstack) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    AxisAlignedBB bb = INFINITE_EXTENT_AABB;
    bb = AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
    return bb;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */