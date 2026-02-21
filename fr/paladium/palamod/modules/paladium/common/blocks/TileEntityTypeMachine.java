package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.utils.FileUtil;
import fr.paladium.palamod.modules.paladium.utils.IItem;
import net.minecraft.enchantment.Enchantment;
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
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class TileEntityTypeMachine extends TileEntity implements IInventory {
  private ItemStack[] typeInventory;
  
  public String nameofbook = StatCollector.func_74838_a("typesetting.bookSelect");
  
  public boolean bookIsSaved;
  
  public String listofbooks = "";
  
  public String listofAuthors = "";
  
  public String listofPublicBooks = "";
  
  public boolean showBookname = false;
  
  public boolean showChaseText = false;
  
  public int requiredlevels = 0;
  
  public boolean showLevels = false;
  
  public TileEntityTypeMachine() {
    this.typeInventory = new ItemStack[3];
  }
  
  public boolean canUpdate() {
    return false;
  }
  
  public void setShowBookname(boolean show) {
    this.showBookname = show;
  }
  
  public void setChaseText(boolean show) {
    this.showChaseText = show;
  }
  
  public void setShowLevels(boolean show) {
    this.showLevels = show;
  }
  
  public boolean getShowBookname() {
    return this.showBookname;
  }
  
  public boolean getShowChaseText() {
    return this.showChaseText;
  }
  
  public boolean getShowLevels() {
    return this.showLevels;
  }
  
  public int getLevels() {
    return this.requiredlevels;
  }
  
  public int func_70302_i_() {
    return this.typeInventory.length;
  }
  
  public ItemStack func_70301_a(int slot) {
    return this.typeInventory[slot];
  }
  
  public void func_70299_a(int slot, ItemStack stack) {
    this.typeInventory[slot] = stack;
    if (stack != null && stack.field_77994_a > func_70297_j_())
      stack.field_77994_a = func_70297_j_(); 
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public void booklistset() {
    if (MinecraftServer.func_71276_C().func_71262_S()) {
      FileUtil util = new FileUtil();
      String[] blist = util.scanBookDir(false);
      String[] alist = util.getAuthorList(blist, false);
      boolean[] plist = util.getPublistList(blist, false);
      StringBuilder bookstring = new StringBuilder();
      StringBuilder authorString = new StringBuilder();
      StringBuilder publicsString = new StringBuilder();
      for (int g = 0; g < blist.length; g++) {
        if (g != blist.length - 1) {
          bookstring.append(blist[g]).append("&_");
          authorString.append(alist[g]).append("&_");
          if (plist[g]) {
            publicsString.append("true&_");
          } else {
            publicsString.append("false&_");
          } 
        } else {
          bookstring.append(blist[g]);
          authorString.append(alist[g]);
          if (plist[g]) {
            publicsString.append("true");
          } else {
            publicsString.append("false");
          } 
        } 
      } 
      setBookList(bookstring.toString(), authorString.toString(), publicsString.toString());
    } 
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
    NBTTagList tagList = nbt.func_150295_c("typeInventory", 10);
    this.typeInventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.typeInventory.length)
        this.typeInventory[slot] = ItemStack.func_77949_a(tag); 
    } 
    this.bookIsSaved = nbt.func_74767_n("savedbook");
    this.nameofbook = nbt.func_74779_i("SavedBookName");
    this.listofbooks = nbt.func_74779_i("ListOfBooks");
    this.listofAuthors = nbt.func_74779_i("listofAuthors");
    this.listofPublicBooks = nbt.func_74779_i("listofPublicBooks");
    this.requiredlevels = nbt.func_74762_e("levelreq");
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.typeInventory.length; i++) {
      ItemStack stack = this.typeInventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    nbt.func_74782_a("typeInventory", (NBTBase)itemList);
    nbt.func_74757_a("savedbook", this.bookIsSaved);
    nbt.func_74778_a("SavedBookName", this.nameofbook);
    nbt.func_74778_a("ListOfBooks", this.listofbooks);
    nbt.func_74778_a("listofAuthors", this.listofAuthors);
    nbt.func_74778_a("listofPublicBooks", this.listofPublicBooks);
    nbt.func_74768_a("levelreq", this.requiredlevels);
  }
  
  public String func_145825_b() {
    return "jds.tileentitytypemachine";
  }
  
  public void writePlateNBT(ItemStack stack, String bookName) {
    NBTTagCompound tag = new NBTTagCompound();
    tag.func_74778_a("bookName", bookName);
    stack.func_77982_d(tag);
  }
  
  public boolean signedBookCheck() {
    ItemStack stack = func_70301_a(0);
    if (stack != null) {
      Item booktest = stack.func_77973_b();
      if (booktest instanceof net.minecraft.item.ItemEditableBook)
        return true; 
    } 
    return false;
  }
  
  public boolean enchantedBookCheck() {
    ItemStack stack = func_70301_a(0);
    if (stack != null) {
      Item booktest = stack.func_77973_b();
      if (booktest instanceof net.minecraft.item.ItemEnchantedBook)
        return true; 
    } 
    return false;
  }
  
  public boolean chaseCheck() {
    ItemStack stack = func_70301_a(1);
    if (stack != null)
      return true; 
    return false;
  }
  
  public int getChaseNum() {
    int stacksize = 0;
    if (chaseCheck()) {
      ItemStack stack = func_70301_a(1);
      int sizetest = stack.field_77994_a;
      if (sizetest > 0 && sizetest < 17)
        stacksize = 1; 
      if (sizetest > 16 && sizetest < 33)
        stacksize = 2; 
      if (sizetest > 32 && sizetest < 49)
        stacksize = 3; 
      if (sizetest > 48)
        stacksize = 4; 
    } 
    return stacksize;
  }
  
  public int addChase(ItemStack chaseStack) {
    ItemStack stack = func_70301_a(1);
    if (stack != null) {
      int chaseSize = chaseStack.field_77994_a;
      int sizetest = stack.field_77994_a;
      int totaltest = sizetest + chaseSize;
      if (totaltest < 65) {
        stack.field_77994_a = totaltest;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return 0;
      } 
      stack.field_77994_a = 64;
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return totaltest - 64;
    } 
    func_70299_a(1, chaseStack);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    return 0;
  }
  
  public boolean addBookorPlate(ItemStack playerstack, World world) {
    ItemStack bookstack = func_70301_a(0);
    if (bookstack == null && 
      playerstack != null) {
      Item itemtest = playerstack.func_77973_b();
      boolean testForCustomBooks = false;
      if (itemtest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || itemtest instanceof net.minecraft.item.ItemEditableBook || itemtest instanceof net.minecraft.item.ItemEnchantedBook) {
        FileUtil util = new FileUtil();
        if (util.isBookSaved(playerstack, world)) {
          this.bookIsSaved = true;
        } else {
          this.bookIsSaved = false;
        } 
        if (itemtest instanceof net.minecraft.item.ItemEnchantedBook) {
          NBTTagList enchbookTagList = getEnchantmentTagList(playerstack);
          float levelCalc = 0.0F;
          for (int x = 0; x < enchbookTagList.func_74745_c(); x++) {
            int enchlvl = enchbookTagList.func_150305_b(x).func_74765_d("lvl");
            int enchid = enchbookTagList.func_150305_b(x).func_74765_d("id");
            Enchantment enchantment = Enchantment.field_77331_b[enchid];
            float cost = 2.0F;
            switch (enchantment.func_77324_c()) {
              case 1:
                cost = 8.0F;
                break;
              case 2:
                cost = 4.0F;
                break;
              case 3:
                cost = 10.0F;
                break;
              case 4:
                cost = 0.5F;
                break;
              case 5:
                cost = 2.0F;
                break;
              case 10:
                cost = 1.0F;
                break;
            } 
            cost *= (2 * enchlvl);
            levelCalc += cost;
          } 
          levelCalc += (getNumCost(enchbookTagList.func_74745_c()) + 10);
          this.requiredlevels = Math.min(50, (int)levelCalc);
        } 
        func_70299_a(0, playerstack);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return true;
      } 
    } 
    return false;
  }
  
  private int getNumCost(int n) {
    if (n <= 1)
      return 1; 
    return n + getNumCost(n - 1);
  }
  
  public boolean resetPlate() {
    if (hasOldPlate()) {
      ItemStack newChase = new ItemStack(((IItem)ItemsRegister.CHASE).getObject(), 1, 0);
      ItemStack chaseStack = func_70301_a(1);
      func_70299_a(0, (ItemStack)null);
      if (chaseStack != null) {
        if (chaseStack.field_77994_a < 64) {
          chaseStack.field_77994_a++;
        } else {
          func_70299_a(0, newChase);
        } 
      } else {
        func_70299_a(1, newChase);
      } 
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return true;
    } 
    return false;
  }
  
  public boolean saveBook(World world) {
    ItemStack booktosave = func_70301_a(0);
    if (booktosave != null) {
      Item bookItem = booktosave.func_77973_b();
      if (bookItem instanceof net.minecraft.item.ItemEditableBook) {
        FileUtil util = new FileUtil();
        util.saveBook(booktosave, world);
        this.bookIsSaved = true;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean enchantPlate(EntityPlayer player) {
    ItemStack stack = func_70301_a(0);
    if (stack != null && chaseCheck() && func_70301_a(2) == null) {
      Item enchBook = stack.func_77973_b();
      if (enchBook instanceof net.minecraft.item.ItemEnchantedBook) {
        NBTTagList enchbookTagList = getEnchantmentTagList(stack);
        int enchCount = enchbookTagList.func_74745_c();
        float levelCalc = 0.0F;
        for (int x = 0; x < enchbookTagList.func_74745_c(); x++) {
          int enchlvl = enchbookTagList.func_150305_b(x).func_74765_d("lvl");
          int enchid = enchbookTagList.func_150305_b(x).func_74765_d("id");
          int maxlvl = Enchantment.field_77331_b[enchid].func_77325_b();
          float slidingScale = 1.0F;
          switch (maxlvl) {
            case 1:
              enchlvl += 4;
              break;
            case 2:
              enchlvl += 3;
              break;
            case 3:
              enchlvl += 2;
              break;
            case 4:
              enchlvl++;
              break;
          } 
          if (x > 0)
            slidingScale = 0.5F * 1.0F / x; 
          levelCalc += ((enchlvl * enchlvl) + 15.0F) * slidingScale;
        } 
        levelCalc *= 1.0F;
        if (player.field_71068_ca < this.requiredlevels) {
          player.func_146105_b((IChatComponent)new ChatComponentText("§cVous devez avoir " + this.requiredlevels + " niveaux pour faire cela."));
          return false;
        } 
        ItemStack enchplate = new ItemStack(((IItem)ItemsRegister.PLATE_ENCHANTED).getObject(), 1, 0);
        if (stack.func_77942_o()) {
          enchplate.func_77982_d((NBTTagCompound)stack.func_77978_p().func_74737_b());
          func_70299_a(2, enchplate);
          ItemStack chases = func_70301_a(1);
          chases.field_77994_a--;
          func_70299_a(0, (ItemStack)null);
        } 
        player.func_82242_a(-this.requiredlevels);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return true;
      } 
    } 
    return false;
  }
  
  public ItemStack getEntchantedBook() {
    return func_70301_a(0);
  }
  
  public NBTTagList getEnchantmentTagList(ItemStack stack) {
    return (stack.field_77990_d != null && stack.field_77990_d.func_74764_b("StoredEnchantments")) ? (NBTTagList)stack.field_77990_d
      .func_74781_a("StoredEnchantments") : new NBTTagList();
  }
  
  public void setPlate() {
    if (chaseCheck() && func_70301_a(2) == null) {
      ItemStack stack = func_70301_a(1);
      ItemStack newPlate = new ItemStack(((IItem)ItemsRegister.PLATE).getObject(), 1, 0);
      writePlateNBT(newPlate, getBookname());
      this.typeInventory[2] = newPlate;
      if (stack.field_77994_a != 1) {
        stack.field_77994_a--;
        this.typeInventory[1] = stack;
      } else {
        this.typeInventory[1] = null;
      } 
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } 
  }
  
  public String getBookname() {
    return this.nameofbook;
  }
  
  public void setBookname(String name) {
    this.nameofbook = name;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public boolean hasSavedBook() {
    return this.bookIsSaved;
  }
  
  public boolean hasNewPlate() {
    ItemStack stack = func_70301_a(2);
    if (stack != null)
      return true; 
    return false;
  }
  
  public boolean hasEnchantedPlate() {
    ItemStack stack = func_70301_a(2);
    if (stack != null) {
      Item item = stack.func_77973_b();
      if (item instanceof fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate)
        return true; 
    } 
    return false;
  }
  
  public boolean hasOldPlate() {
    ItemStack stack = func_70301_a(0);
    if (stack != null) {
      Item booktest = stack.func_77973_b();
      if (booktest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate)
        return true; 
    } 
    return false;
  }
  
  public boolean hasLowerChase() {
    ItemStack stack = func_70301_a(0);
    if (stack != null) {
      Item booktest = stack.func_77973_b();
      if (booktest instanceof fr.paladium.palamod.modules.paladium.common.items.ItemChase)
        return true; 
    } 
    return false;
  }
  
  public void setBookList(String books, String authors, String publics) {
    this.listofbooks = books;
    this.listofAuthors = authors;
    this.listofPublicBooks = publics;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  public String getbookListString() {
    return this.listofbooks;
  }
  
  public String getAuthorListString() {
    return this.listofAuthors;
  }
  
  public String getPublicListString() {
    return this.listofPublicBooks;
  }
  
  public String[] getbookList() {
    String[] booklist = this.listofbooks.split("&_");
    return booklist;
  }
  
  public String[] getAuthorList() {
    String[] authorlist = this.listofAuthors.split("&_");
    return authorlist;
  }
  
  public boolean[] getPublicList() {
    String[] publist = this.listofPublicBooks.split("&_");
    boolean[] publiclist = new boolean[publist.length];
    for (int h = 0; h < publist.length; h++) {
      if (publist[h].contains("true")) {
        publiclist[h] = true;
      } else {
        publiclist[h] = false;
      } 
    } 
    return publiclist;
  }
  
  public Packet func_145844_m() {
    NBTTagCompound dataTag = new NBTTagCompound();
    NBTTagList itemList = new NBTTagList();
    for (int i = 0; i < this.typeInventory.length; i++) {
      ItemStack stack = this.typeInventory[i];
      if (stack != null) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("Slot", (byte)i);
        stack.func_77955_b(tag);
        itemList.func_74742_a((NBTBase)tag);
      } 
    } 
    dataTag.func_74782_a("TypeInventory", (NBTBase)itemList);
    dataTag.func_74778_a("SavedBookName", this.nameofbook);
    dataTag.func_74757_a("savedbook", this.bookIsSaved);
    dataTag.func_74778_a("listofbooks", this.listofbooks);
    dataTag.func_74778_a("listofAuthors", this.listofAuthors);
    dataTag.func_74778_a("listofPublicBooks", this.listofPublicBooks);
    dataTag.func_74768_a("levelreq", this.requiredlevels);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, dataTag);
  }
  
  public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
    NBTTagCompound nbtData = packet.func_148857_g();
    NBTTagList tagList = nbtData.func_150295_c("TypeInventory", 10);
    this.typeInventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < tagList.func_74745_c(); i++) {
      NBTTagCompound tag = tagList.func_150305_b(i);
      byte slot = tag.func_74771_c("Slot");
      if (slot >= 0 && slot < this.typeInventory.length)
        this.typeInventory[slot] = ItemStack.func_77949_a(tag); 
    } 
    this.nameofbook = nbtData.func_74779_i("SavedBookName");
    this.bookIsSaved = nbtData.func_74767_n("savedbook");
    this.nameofbook = nbtData.func_74779_i("SavedBookName");
    this.listofbooks = nbtData.func_74779_i("listofbooks");
    this.listofAuthors = nbtData.func_74779_i("listofAuthors");
    this.listofPublicBooks = nbtData.func_74779_i("listofPublicBooks");
    this.requiredlevels = nbtData.func_74762_e("levelreq");
  }
  
  public boolean func_94041_b(int slot, ItemStack itemstack) {
    Item stackitem = itemstack.func_77973_b();
    if (stackitem != null) {
      if (slot == 0 && (
        stackitem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemPlate || stackitem instanceof net.minecraft.item.ItemEditableBook))
        return true; 
      if (slot == 1 && 
        stackitem instanceof fr.paladium.palamod.modules.paladium.common.items.ItemChase)
        return true; 
    } 
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    AxisAlignedBB bb = TileEntity.INFINITE_EXTENT_AABB;
    bb = AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
    return bb;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\TileEntityTypeMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */