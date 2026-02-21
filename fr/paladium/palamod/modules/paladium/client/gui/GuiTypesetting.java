package fr.paladium.palamod.modules.paladium.client.gui;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import fr.paladium.palamod.modules.hunter.proxies.CommonProxy;
import fr.paladium.palamod.modules.paladium.PPaladium;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityTypeMachine;
import fr.paladium.palamod.modules.paladium.utils.FileUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiTypesetting extends GuiScreen {
  public TileEntityTypeMachine typetile;
  
  public String bookName = "Select a book";
  
  public String savedname = "Loading";
  
  public int i;
  
  public int j;
  
  public int k;
  
  public String[] booklist;
  
  public boolean[] isPublic;
  
  public String[] authorList;
  
  public boolean[] bookButtonRender = new boolean[] { false, false, false, false, false, false, false, false };
  
  private String title = StatCollector.func_74838_a("gui.typesetting.guiTitle");
  
  private int pageCurr = 1;
  
  private int pageTotal = 0;
  
  private GuiButton[] select;
  
  private GuiButton[] publicToggle;
  
  private GuiButton[] delete;
  
  private GuiButton nextPage;
  
  private GuiButton prevPage;
  
  private GuiButton exit;
  
  private GuiButton deleteToggle;
  
  private boolean deleteable = false;
  
  private String playerName;
  
  private boolean creativeMode;
  
  private int[] heights;
  
  private int listStart = 0;
  
  private int listEnd = 8;
  
  private boolean isServerSide = false;
  
  public GuiTypesetting(EntityPlayer player, TileEntityTypeMachine tile) {
    this.typetile = tile;
    this.i = this.typetile.field_145851_c;
    this.j = this.typetile.field_145848_d;
    this.k = this.typetile.field_145849_e;
    this.playerName = player.getDisplayName();
    if (player.field_71075_bZ.field_75098_d) {
      this.creativeMode = true;
    } else {
      this.creativeMode = false;
    } 
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    this.field_146292_n.clear();
    World world = this.typetile.func_145831_w();
    int widthRender = this.field_146294_l / 2;
    int heightRender = this.field_146295_m / 2;
    this.heights = new int[] { heightRender - 94, heightRender - 69, heightRender - 44, heightRender - 19, heightRender + 6, heightRender + 31, heightRender + 56, heightRender + 81 };
    this
      
      .select = new GuiButton[] { new GuiButton(1, widthRender - 160, heightRender - 100, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(2, widthRender - 160, heightRender - 75, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(3, widthRender - 160, heightRender - 50, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(4, widthRender - 160, heightRender - 25, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(5, widthRender - 160, heightRender, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(6, widthRender - 160, heightRender + 25, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(7, widthRender - 160, heightRender + 50, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")), new GuiButton(8, widthRender - 160, heightRender + 75, 40, 20, StatCollector.func_74838_a("gui.typesetting.select")) };
    this
      
      .publicToggle = new GuiButton[] { new GuiButton(9, widthRender + 75, heightRender - 100, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(10, widthRender + 75, heightRender - 75, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(11, widthRender + 75, heightRender - 50, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(12, widthRender + 75, heightRender - 25, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(13, widthRender + 75, heightRender, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(14, widthRender + 75, heightRender + 25, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(15, widthRender + 75, heightRender + 50, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")), new GuiButton(16, widthRender + 75, heightRender + 75, 42, 20, StatCollector.func_74838_a("gui.typesetting.public")) };
    this
      
      .delete = new GuiButton[] { new GuiButton(17, widthRender + 120, heightRender - 100, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(18, widthRender + 120, heightRender - 75, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(19, widthRender + 120, heightRender - 50, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(20, widthRender + 120, heightRender - 25, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(21, widthRender + 120, heightRender, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(22, widthRender + 120, heightRender + 25, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(23, widthRender + 120, heightRender + 50, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")), new GuiButton(24, widthRender + 120, heightRender + 75, 40, 20, StatCollector.func_74838_a("gui.typesetting.delete")) };
    this
      .nextPage = new GuiButton(25, widthRender + 10, heightRender + 100, 60, 20, StatCollector.func_74838_a("gui.typesetting.nextPage"));
    this
      .prevPage = new GuiButton(26, widthRender - 115, heightRender + 100, 60, 20, StatCollector.func_74838_a("gui.typesetting.prevPage"));
    this
      .exit = new GuiButton(0, widthRender + 95, heightRender + 100, 40, 20, StatCollector.func_74838_a("gui.typesetting.exit"));
    this
      .deleteToggle = new GuiButton(27, widthRender + 114, heightRender - 120, 46, 20, StatCollector.func_74838_a("gui.typesetting.enableDiscard"));
    for (int i = 0; i < 8; i++) {
      this.field_146292_n.add(this.select[i]);
      this.field_146292_n.add(this.publicToggle[i]);
      this.field_146292_n.add(this.delete[i]);
    } 
    this.field_146292_n.add(this.nextPage);
    this.field_146292_n.add(this.prevPage);
    this.field_146292_n.add(this.exit);
    this.field_146292_n.add(this.deleteToggle);
    boolean issp = this.field_146297_k.func_71356_B();
    FileUtil util = new FileUtil();
    if (!issp) {
      this.isServerSide = true;
      this.booklist = this.typetile.getbookList();
      if (this.booklist != null) {
        this.authorList = this.typetile.getAuthorList();
        this.isPublic = this.typetile.getPublicList();
      } 
    } else {
      this.isServerSide = false;
      this.booklist = util.scanBookDir(world);
      if (this.booklist != null) {
        this.authorList = util.getAuthorList(this.booklist, true);
        this.isPublic = util.getPublistList(this.booklist, true);
      } 
    } 
    if (this.booklist != null)
      removePrivatesFromList(); 
    if (this.booklist != null) {
      this.pageTotal = this.booklist.length / 8 + 1;
      this.pageCurr = 1;
      setBookListEnds();
    } 
  }
  
  public void func_73863_a(int x, int y, float f) {
    func_146276_q_();
    int widthRender = this.field_146294_l / 2;
    int heightRender = this.field_146295_m / 2;
    this.field_146297_k.func_110434_K().func_110577_a(CommonProxy.TYPEMACHINEGUI_L_PNG);
    func_73729_b(widthRender - 170, heightRender - 128, 0, 0, 256, 256);
    this.field_146297_k.func_110434_K().func_110577_a(CommonProxy.TYPEMACHINEGUI_R_PNG);
    func_73729_b(widthRender + 86, heightRender - 128, 172, 0, 84, 256);
    this.field_146289_q.func_78276_b(this.title, widthRender - this.title.length() * 3, heightRender - 119, 4210752);
    String pages = this.pageCurr + " " + StatCollector.func_74838_a("gui.typesetting.pageOfpages") + " " + this.pageTotal;
    this.field_146289_q.func_78276_b(pages, widthRender - 19 - pages.length() * 3, heightRender + 106, 4210752);
    if (this.booklist != null) {
      for (int h = 0; h < 8; h++) {
        if (this.bookButtonRender[h]) {
          (this.select[h]).field_146125_m = true;
          (this.publicToggle[h]).field_146125_m = true;
        } else {
          (this.select[h]).field_146125_m = false;
          (this.publicToggle[h]).field_146125_m = false;
        } 
        (this.delete[h]).field_146125_m = false;
      } 
      int b = this.listStart;
      for (int i = 0; b < this.listEnd; i++) {
        this.field_146289_q.func_78276_b(this.booklist[b], widthRender - 115, this.heights[i], 4210752);
        if (this.isPublic[b]) {
          (this.publicToggle[i])
            .field_146126_j = StatCollector.func_74838_a("gui.typesetting.public");
        } else {
          (this.publicToggle[i])
            .field_146126_j = StatCollector.func_74838_a("gui.typesetting.private");
        } 
        if ((this.creativeMode || this.authorList[b].contains(this.playerName)) && 
          this.deleteable)
          (this.delete[i]).field_146125_m = true; 
        b++;
      } 
      if (this.pageCurr == 1) {
        if (this.prevPage.field_146125_m == true)
          this.prevPage.field_146125_m = false; 
      } else if (!this.prevPage.field_146125_m) {
        this.prevPage.field_146125_m = true;
      } 
      if (this.pageCurr == this.pageTotal) {
        if (this.nextPage.field_146125_m == true)
          this.nextPage.field_146125_m = false; 
      } else if (!this.nextPage.field_146125_m) {
        this.nextPage.field_146125_m = true;
      } 
    } else {
      this.prevPage.field_146125_m = false;
      this.nextPage.field_146125_m = false;
      for (int h = 0; h < 8; h++) {
        (this.select[h]).field_146125_m = false;
        (this.publicToggle[h]).field_146125_m = false;
        (this.delete[h]).field_146125_m = false;
      } 
    } 
    super.func_73863_a(x, y, f);
  }
  
  private void setBookListEnds() {
    this.listStart = this.pageCurr * 8 - 8;
    if (this.pageCurr == this.pageTotal) {
      this.listEnd = this.booklist.length;
    } else {
      this.listEnd = this.pageCurr * 8;
    } 
    int i;
    for (i = 0; i < 8; i++)
      this.bookButtonRender[i] = false; 
    for (i = 0; i < this.listEnd - this.listStart; i++)
      this.bookButtonRender[i] = true; 
  }
  
  protected void func_146284_a(GuiButton click) {
    boolean setExit = false;
    boolean sendBookname = false;
    String bookFlagTitle = "";
    boolean setFlagUpdate = false;
    String deleteBookTitle = "";
    int flag = 0;
    boolean setDelete = false;
    switch (click.field_146127_k) {
      case 0:
        setExit = true;
        break;
      case 1:
        this.bookName = this.booklist[this.listStart];
        setExit = true;
        break;
      case 2:
        this.bookName = this.booklist[this.listStart + 1];
        setExit = true;
        break;
      case 3:
        this.bookName = this.booklist[this.listStart + 2];
        setExit = true;
        break;
      case 4:
        this.bookName = this.booklist[this.listStart + 3];
        setExit = true;
        break;
      case 5:
        this.bookName = this.booklist[this.listStart + 4];
        setExit = true;
        break;
      case 6:
        this.bookName = this.booklist[this.listStart + 5];
        setExit = true;
        break;
      case 7:
        this.bookName = this.booklist[this.listStart + 6];
        setExit = true;
        break;
      case 8:
        this.bookName = this.booklist[this.listStart + 7];
        setExit = true;
        break;
      case 9:
        bookFlagTitle = this.booklist[this.listStart];
        setFlagUpdate = true;
        flag = this.listStart;
        break;
      case 10:
        bookFlagTitle = this.booklist[this.listStart + 1];
        setFlagUpdate = true;
        flag = this.listStart + 1;
        break;
      case 11:
        bookFlagTitle = this.booklist[this.listStart + 2];
        setFlagUpdate = true;
        flag = this.listStart + 2;
        break;
      case 12:
        bookFlagTitle = this.booklist[this.listStart + 3];
        setFlagUpdate = true;
        flag = this.listStart + 3;
        break;
      case 13:
        bookFlagTitle = this.booklist[this.listStart + 4];
        setFlagUpdate = true;
        flag = this.listStart + 4;
        break;
      case 14:
        bookFlagTitle = this.booklist[this.listStart + 5];
        setFlagUpdate = true;
        flag = this.listStart + 5;
        break;
      case 15:
        bookFlagTitle = this.booklist[this.listStart + 6];
        setFlagUpdate = true;
        flag = this.listStart + 6;
        break;
      case 16:
        bookFlagTitle = this.booklist[this.listStart + 7];
        setFlagUpdate = true;
        flag = this.listStart + 7;
        break;
      case 17:
        deleteBookTitle = this.booklist[this.listStart];
        setDelete = true;
        flag = this.listStart;
        break;
      case 18:
        deleteBookTitle = this.booklist[this.listStart + 1];
        setDelete = true;
        flag = this.listStart + 1;
        break;
      case 19:
        deleteBookTitle = this.booklist[this.listStart + 2];
        setDelete = true;
        flag = this.listStart + 2;
        break;
      case 20:
        deleteBookTitle = this.booklist[this.listStart + 3];
        setDelete = true;
        flag = this.listStart + 3;
        break;
      case 21:
        deleteBookTitle = this.booklist[this.listStart + 4];
        setDelete = true;
        flag = this.listStart + 4;
        break;
      case 22:
        deleteBookTitle = this.booklist[this.listStart + 5];
        setDelete = true;
        flag = this.listStart + 5;
        break;
      case 23:
        deleteBookTitle = this.booklist[this.listStart + 6];
        setDelete = true;
        flag = this.listStart + 6;
        break;
      case 24:
        deleteBookTitle = this.booklist[this.listStart + 7];
        setDelete = true;
        flag = this.listStart + 7;
        break;
      case 27:
        this.deleteable = !this.deleteable;
        break;
    } 
    if (click.field_146127_k == 25 && 
      this.pageCurr < this.pageTotal) {
      this.pageCurr++;
      setBookListEnds();
    } 
    if (click.field_146127_k == 26 && 
      this.pageCurr > 1) {
      this.pageCurr--;
      setBookListEnds();
    } 
    if (click.field_146127_k >= 1 && click.field_146127_k <= 8) {
      ByteBuf buffer = Unpooled.buffer();
      ByteBufUtils.writeUTF8String(buffer, this.bookName);
      buffer.writeInt(this.i);
      buffer.writeInt(this.j);
      buffer.writeInt(this.k);
      PPaladium.ch_Type.sendToServer(new FMLProxyPacket(buffer, "DecodiumType"));
    } 
    if (setFlagUpdate && (
      this.authorList[flag].contains(this.playerName) || this.creativeMode)) {
      ByteBuf buffer = Unpooled.buffer();
      ByteBufUtils.writeUTF8String(buffer, bookFlagTitle);
      buffer.writeBoolean(!this.isPublic[flag]);
      buffer.writeBoolean(this.isServerSide);
      PPaladium.ch_TypeFlag.sendToServer(new FMLProxyPacket(buffer, "DecodiumTypeFlag"));
      this.isPublic[flag] = !this.isPublic[flag];
    } 
    if (setDelete) {
      ByteBuf buffer = Unpooled.buffer();
      ByteBufUtils.writeUTF8String(buffer, deleteBookTitle);
      buffer.writeBoolean(this.isServerSide);
      PPaladium.ch_TypeDelete.sendToServer(new FMLProxyPacket(buffer, "DecodiumTypeDelete"));
      deleteBookFromLists(flag);
    } 
    if (setExit)
      this.field_146297_k.field_71439_g.func_71053_j(); 
  }
  
  private void deleteBookFromLists(int flag) {
    String[] nBooks = new String[this.booklist.length - 1];
    String[] nAuthors = new String[this.authorList.length - 1];
    boolean[] nPublics = new boolean[this.isPublic.length - 1];
    int r = 0;
    for (int b = 0; b < this.booklist.length; b++) {
      if (b != flag) {
        nBooks[r] = this.booklist[b];
        nAuthors[r] = this.authorList[b];
        nPublics[r] = this.isPublic[b];
        r++;
      } 
    } 
    this.booklist = nBooks;
    this.authorList = nAuthors;
    this.isPublic = nPublics;
    this.pageTotal = this.booklist.length / 8 + 1;
    if (this.pageCurr > this.pageTotal && this.pageTotal > 1)
      this.pageCurr--; 
    setBookListEnds();
  }
  
  protected void func_73864_a(int left, int top, int par3) {
    super.func_73864_a(left, top, par3);
  }
  
  protected void func_73869_a(char par1, int par2) {
    if (par2 == 1 || par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i())
      this.field_146297_k.field_71439_g.func_71053_j(); 
  }
  
  public void func_146281_b() {
    if (this.isServerSide) {
      ByteBuf buffer = Unpooled.buffer();
      buffer.writeInt(this.i);
      buffer.writeInt(this.j);
      buffer.writeInt(this.k);
      PPaladium.ch_TypeUpdate.sendToServer(new FMLProxyPacket(buffer, "DecodiumTypeUpdate"));
    } 
  }
  
  public void setBookList(String[] books) {
    this.booklist = books;
  }
  
  private void removePrivatesFromList() {
    if (!this.creativeMode) {
      ArrayList<String> books = new ArrayList();
      ArrayList<String> authors = new ArrayList();
      ArrayList<Integer> publics = new ArrayList();
      int b;
      for (b = 0; b < this.booklist.length; b++) {
        if (this.authorList[b].contains(this.playerName) || this.isPublic[b]) {
          books.add(this.booklist[b]);
          authors.add(this.authorList[b]);
          if (this.isPublic[b]) {
            publics.add(Integer.valueOf(1));
          } else {
            publics.add(Integer.valueOf(0));
          } 
        } 
      } 
      if (books.size() > 0) {
        this.booklist = new String[books.size()];
        this.authorList = new String[authors.size()];
        this.isPublic = new boolean[publics.size()];
        for (b = 0; b < books.size(); b++) {
          this.booklist[b] = books.get(b);
          this.authorList[b] = authors.get(b);
          if (((Integer)publics.get(b)).intValue() == 1) {
            this.isPublic[b] = true;
          } else {
            this.isPublic[b] = false;
          } 
        } 
      } else {
        this.booklist = null;
        this.pageTotal = 1;
        this.bookButtonRender = new boolean[] { false, false, false, false, false, false, false, false };
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\GuiTypesetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */