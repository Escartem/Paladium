package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketClipboardMCBEdit;
import fr.paladium.palamod.modules.luckyblock.network.PacketClipboardUpdateInv;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiClipboard extends GuiScreen {
  public static final ResourceLocation book_png = new ResourceLocation("bibliocraft", "textures/gui/clipboardGUI.png");
  
  private int bookImageWidth = 192;
  
  private int bookImageHeight = 192;
  
  private int button0state = 1;
  
  private int button1state = 0;
  
  private int button2state = 0;
  
  private int button3state = 0;
  
  private int button4state = 0;
  
  private int button5state = 0;
  
  private int button6state = 0;
  
  private int button7state = 0;
  
  private int button8state = 0;
  
  private String button0text = " ";
  
  private String button1text = " ";
  
  private String button2text = " ";
  
  private String button3text = " ";
  
  private String button4text = " ";
  
  private String button5text = " ";
  
  private String button6text = " ";
  
  private String button7text = " ";
  
  private String button8text = " ";
  
  private String titletext = " ";
  
  private GuiBiblioTextField textField0;
  
  private GuiBiblioTextField textField1;
  
  private GuiBiblioTextField textField2;
  
  private GuiBiblioTextField textField3;
  
  private GuiBiblioTextField textField4;
  
  private GuiBiblioTextField textField5;
  
  private GuiBiblioTextField textField6;
  
  private GuiBiblioTextField textField7;
  
  private GuiBiblioTextField textField8;
  
  private GuiBiblioTextField textFieldTitle;
  
  private GuiButtonNextPage buttonNextPage;
  
  private GuiButtonNextPage buttonPreviousPage;
  
  private ItemStack clipStack;
  
  private int totalPages = 1;
  
  private int currentPage = 1;
  
  private int tilex = 0;
  
  private int tiley = 0;
  
  private int tilez = 0;
  
  private int fieldCharLimit = 23;
  
  private boolean inInv;
  
  public GuiClipboard(World world, EntityPlayer player, ItemStack stack, boolean eqippued, int tx, int ty, int tz) {
    this.clipStack = stack;
    this.inInv = eqippued;
    getNBTData();
    if (!this.inInv) {
      this.tilex = tx;
      this.tiley = ty;
      this.tilez = tz;
    } 
  }
  
  public void getNBTData() {
    NBTTagCompound cliptags = this.clipStack.func_77978_p();
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
  
  public void func_73866_w_() {
    super.func_73866_w_();
    this.field_146292_n.clear();
    Keyboard.enableRepeatEvents(true);
    int var5 = (this.field_146294_l - this.bookImageWidth) / 2;
    int sidex = this.field_146294_l / 2 - 64;
    int sidex2 = sidex + 12;
    this.field_146292_n.add(new GuiButtonClipboard(0, sidex, 27, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(1, sidex, 42, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(2, sidex, 57, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(3, sidex, 72, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(4, sidex, 87, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(5, sidex, 102, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(6, sidex, 117, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(7, sidex, 132, 10, 10, "", true));
    this.field_146292_n.add(new GuiButtonClipboard(8, sidex, 147, 10, 10, "", true));
    this.textField0 = new GuiBiblioTextField(this.field_146289_q, sidex2, 29, 115, 10);
    this.textField1 = new GuiBiblioTextField(this.field_146289_q, sidex2, 44, 115, 10);
    this.textField2 = new GuiBiblioTextField(this.field_146289_q, sidex2, 59, 115, 10);
    this.textField3 = new GuiBiblioTextField(this.field_146289_q, sidex2, 74, 115, 10);
    this.textField4 = new GuiBiblioTextField(this.field_146289_q, sidex2, 89, 115, 10);
    this.textField5 = new GuiBiblioTextField(this.field_146289_q, sidex2, 104, 115, 10);
    this.textField6 = new GuiBiblioTextField(this.field_146289_q, sidex2, 119, 115, 10);
    this.textField7 = new GuiBiblioTextField(this.field_146289_q, sidex2, 134, 115, 10);
    this.textField8 = new GuiBiblioTextField(this.field_146289_q, sidex2, 149, 115, 10);
    this.textFieldTitle = new GuiBiblioTextField(this.field_146289_q, sidex2 - 10, 14, 125, 10);
    this.textField0.setEnableBackgroundDrawing(false);
    this.textField1.setEnableBackgroundDrawing(false);
    this.textField2.setEnableBackgroundDrawing(false);
    this.textField3.setEnableBackgroundDrawing(false);
    this.textField4.setEnableBackgroundDrawing(false);
    this.textField5.setEnableBackgroundDrawing(false);
    this.textField6.setEnableBackgroundDrawing(false);
    this.textField7.setEnableBackgroundDrawing(false);
    this.textField8.setEnableBackgroundDrawing(false);
    this.textFieldTitle.setEnableBackgroundDrawing(false);
    this.textField0.setTextColor(4210752);
    this.textField1.setTextColor(4210752);
    this.textField2.setTextColor(4210752);
    this.textField3.setTextColor(4210752);
    this.textField4.setTextColor(4210752);
    this.textField5.setTextColor(4210752);
    this.textField6.setTextColor(4210752);
    this.textField7.setTextColor(4210752);
    this.textField8.setTextColor(4210752);
    this.textFieldTitle.setTextColor(4210752);
    this.textField0.setText(this.button0text);
    this.textField1.setText(this.button1text);
    this.textField2.setText(this.button2text);
    this.textField3.setText(this.button3text);
    this.textField4.setText(this.button4text);
    this.textField5.setText(this.button5text);
    this.textField6.setText(this.button6text);
    this.textField7.setText(this.button7text);
    this.textField8.setText(this.button8text);
    this.textFieldTitle.setText(this.titletext);
    this.textField0.setMaxStringLength(this.fieldCharLimit);
    this.textField1.setMaxStringLength(this.fieldCharLimit);
    this.textField2.setMaxStringLength(this.fieldCharLimit);
    this.textField3.setMaxStringLength(this.fieldCharLimit);
    this.textField4.setMaxStringLength(this.fieldCharLimit);
    this.textField5.setMaxStringLength(this.fieldCharLimit);
    this.textField6.setMaxStringLength(this.fieldCharLimit);
    this.textField7.setMaxStringLength(this.fieldCharLimit);
    this.textField8.setMaxStringLength(this.fieldCharLimit);
    this.textFieldTitle.setMaxStringLength(26);
    int var1 = (this.field_146294_l - this.bookImageWidth) / 2;
    byte var2 = 2;
    this.field_146292_n
      .add(this.buttonNextPage = new GuiButtonNextPage(10, var1 + 120, var2 + 157, true));
    this.field_146292_n
      .add(this.buttonPreviousPage = new GuiButtonNextPage(11, var1 + 38, var2 + 157, false));
  }
  
  public void func_73863_a(int par1, int par2, float par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(book_png);
    int var5 = (this.field_146294_l - this.bookImageWidth) / 2;
    byte var6 = 2;
    func_73729_b(var5, var6, 0, 0, this.bookImageWidth, this.bookImageHeight);
    switch (this.button0state) {
      case 1:
        func_73729_b(var5 + 30, 26, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 25, 72, 194, 16, 15);
        break;
    } 
    switch (this.button1state) {
      case 1:
        func_73729_b(var5 + 30, 41, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 40, 72, 194, 16, 15);
        break;
    } 
    switch (this.button2state) {
      case 1:
        func_73729_b(var5 + 30, 56, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 55, 72, 194, 16, 15);
        break;
    } 
    switch (this.button3state) {
      case 1:
        func_73729_b(var5 + 30, 71, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 70, 72, 194, 16, 15);
        break;
    } 
    switch (this.button4state) {
      case 1:
        func_73729_b(var5 + 30, 86, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 85, 72, 194, 16, 15);
        break;
    } 
    switch (this.button5state) {
      case 1:
        func_73729_b(var5 + 30, 101, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 100, 72, 194, 16, 15);
        break;
    } 
    switch (this.button6state) {
      case 1:
        func_73729_b(var5 + 30, 116, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 115, 72, 194, 16, 15);
        break;
    } 
    switch (this.button7state) {
      case 1:
        func_73729_b(var5 + 30, 131, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 130, 72, 194, 16, 15);
        break;
    } 
    switch (this.button8state) {
      case 1:
        func_73729_b(var5 + 30, 146, 51, 195, 16, 12);
        break;
      case 2:
        func_73729_b(var5 + 29, 145, 72, 194, 16, 15);
        break;
    } 
    String currpage = "" + this.currentPage;
    int pageoffset = var5 + 90;
    if (this.currentPage > 9)
      pageoffset = var5 + 87; 
    this.field_146289_q.func_78276_b(currpage, pageoffset, 162, 4210752);
    if (this.currentPage > 1) {
      this.buttonPreviousPage.field_146124_l = true;
    } else {
      this.buttonPreviousPage.field_146124_l = false;
    } 
    if (this.currentPage > 49) {
      this.buttonNextPage.field_146124_l = false;
    } else {
      this.buttonNextPage.field_146124_l = true;
    } 
    super.func_73863_a(par1, par2, par3);
    this.textField0.drawTextBox();
    this.textField1.drawTextBox();
    this.textField2.drawTextBox();
    this.textField3.drawTextBox();
    this.textField4.drawTextBox();
    this.textField5.drawTextBox();
    this.textField6.drawTextBox();
    this.textField7.drawTextBox();
    this.textField8.drawTextBox();
    this.textFieldTitle.drawTextBox();
    if (this.textField0.isFocused());
  }
  
  public void func_73876_c() {
    super.func_73876_c();
  }
  
  private void nextPage() {
    saveNBT();
    NBTTagCompound cliptags = this.clipStack.func_77978_p();
    if (cliptags != null) {
      this.currentPage = cliptags.func_74762_e("currentPage");
      int nextpage = this.currentPage + 1;
      String pagenum = "page" + nextpage;
      NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
      int[] taskstat = pagetag.func_74759_k("taskStates");
      if (taskstat.length == 9) {
        this.currentPage++;
        cliptags.func_74768_a("currentPage", this.currentPage);
        this.clipStack.func_77982_d(cliptags);
        getNBTData();
        func_73866_w_();
      } else {
        this.currentPage++;
        NBTTagCompound page = new NBTTagCompound();
        NBTTagCompound tasks = new NBTTagCompound();
        int[] taskstate = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        page.func_74783_a("taskStates", taskstate);
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
        String pagename = "page" + this.currentPage;
        cliptags.func_74782_a(pagename, (NBTBase)page);
        cliptags.func_74768_a("currentPage", this.currentPage);
        cliptags.func_74768_a("totalPages", this.totalPages + 1);
        this.clipStack.func_77982_d(cliptags);
        getNBTData();
        func_73866_w_();
      } 
    } 
  }
  
  private void prevPage() {
    saveNBT();
    NBTTagCompound cliptags = this.clipStack.func_77978_p();
    if (cliptags != null) {
      this.currentPage = cliptags.func_74762_e("currentPage");
      int prevpage = this.currentPage - 1;
      String pagenum = "page" + prevpage;
      NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
      if (pagetag != null) {
        this.currentPage--;
        cliptags.func_74768_a("currentPage", this.currentPage);
        this.clipStack.func_77982_d(cliptags);
        getNBTData();
        func_73866_w_();
      } 
    } 
  }
  
  protected void func_146284_a(GuiButton click) {
    if (click.field_146127_k < 9) {
      NBTTagCompound cliptags = this.clipStack.func_77978_p();
      if (cliptags != null) {
        String pagenum = "page" + cliptags.func_74762_e("currentPage");
        NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
        if (pagetag != null) {
          int[] taskstat = pagetag.func_74759_k("taskStates");
          if (taskstat.length == 9) {
            for (int x = 0; x < 9; x++) {
              if (click.field_146127_k == x)
                if (taskstat[x] > 1) {
                  taskstat[x] = 0;
                } else {
                  taskstat[x] = taskstat[x] + 1;
                }  
            } 
            this.button0state = taskstat[0];
            this.button1state = taskstat[1];
            this.button2state = taskstat[2];
            this.button3state = taskstat[3];
            this.button4state = taskstat[4];
            this.button5state = taskstat[5];
            this.button6state = taskstat[6];
            this.button7state = taskstat[7];
            this.button8state = taskstat[8];
            pagetag.func_74783_a("taskStates", taskstat);
            this.clipStack.func_77982_d(cliptags);
          } 
        } 
      } 
    } 
    if (click.field_146127_k == 10)
      nextPage(); 
    if (click.field_146127_k == 11)
      prevPage(); 
  }
  
  public void saveNBT() {
    NBTTagCompound cliptags = this.clipStack.func_77978_p();
    if (cliptags != null) {
      this.currentPage = cliptags.func_74762_e("currentPage");
      String pagenum = "page" + this.currentPage;
      NBTTagCompound pagetag = cliptags.func_74775_l(pagenum);
      if (pagetag != null) {
        NBTTagCompound tasks = pagetag.func_74775_l("tasks");
        tasks.func_74778_a("task1", this.textField0.getText());
        tasks.func_74778_a("task2", this.textField1.getText());
        tasks.func_74778_a("task3", this.textField2.getText());
        tasks.func_74778_a("task4", this.textField3.getText());
        tasks.func_74778_a("task5", this.textField4.getText());
        tasks.func_74778_a("task6", this.textField5.getText());
        tasks.func_74778_a("task7", this.textField6.getText());
        tasks.func_74778_a("task8", this.textField7.getText());
        tasks.func_74778_a("task9", this.textField8.getText());
        pagetag.func_74778_a("title", this.textFieldTitle.getText());
        pagetag.func_74782_a("tasks", (NBTBase)tasks);
        cliptags.func_74782_a(pagenum, (NBTBase)pagetag);
        this.clipStack.func_77982_d(cliptags);
      } 
    } 
  }
  
  public void func_146281_b() {
    Keyboard.enableRepeatEvents(false);
    saveNBT();
    ByteBuf buffer = Unpooled.buffer();
    try {
      if (this.inInv) {
        PacketClipboardUpdateInv packet = new PacketClipboardUpdateInv(this.clipStack);
        PalaMod.getNetwork().sendToServer((IMessage)packet);
      } else {
        PacketClipboardMCBEdit packet = new PacketClipboardMCBEdit(this.clipStack, this.tilex, this.tiley, this.tilez, this.currentPage);
        PalaMod.getNetwork().sendToServer((IMessage)packet);
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }
  
  protected void func_73864_a(int par1, int par2, int par3) {
    super.func_73864_a(par1, par2, par3);
    this.textField0.mouseClicked(par1, par2, par3);
    this.textField1.mouseClicked(par1, par2, par3);
    this.textField2.mouseClicked(par1, par2, par3);
    this.textField3.mouseClicked(par1, par2, par3);
    this.textField4.mouseClicked(par1, par2, par3);
    this.textField5.mouseClicked(par1, par2, par3);
    this.textField6.mouseClicked(par1, par2, par3);
    this.textField7.mouseClicked(par1, par2, par3);
    this.textField8.mouseClicked(par1, par2, par3);
    this.textFieldTitle.mouseClicked(par1, par2, par3);
  }
  
  protected void func_73869_a(char par1, int par2) {
    if (par2 == 28 || par2 == 208)
      if (this.textField0.isFocused()) {
        this.textField0.setFocused(false);
        this.textField1.setFocused(true);
      } else if (this.textField1.isFocused()) {
        this.textField1.setFocused(false);
        this.textField2.setFocused(true);
      } else if (this.textField2.isFocused()) {
        this.textField2.setFocused(false);
        this.textField3.setFocused(true);
      } else if (this.textField3.isFocused()) {
        this.textField3.setFocused(false);
        this.textField4.setFocused(true);
      } else if (this.textField4.isFocused()) {
        this.textField4.setFocused(false);
        this.textField5.setFocused(true);
      } else if (this.textField5.isFocused()) {
        this.textField5.setFocused(false);
        this.textField6.setFocused(true);
      } else if (this.textField6.isFocused()) {
        this.textField6.setFocused(false);
        this.textField7.setFocused(true);
      } else if (this.textField7.isFocused()) {
        this.textField7.setFocused(false);
        this.textField8.setFocused(true);
      }  
    if (par2 == 200)
      if (this.textField1.isFocused()) {
        this.textField1.setFocused(false);
        this.textField0.setFocused(true);
      } else if (this.textField2.isFocused()) {
        this.textField2.setFocused(false);
        this.textField1.setFocused(true);
      } else if (this.textField3.isFocused()) {
        this.textField3.setFocused(false);
        this.textField2.setFocused(true);
      } else if (this.textField4.isFocused()) {
        this.textField4.setFocused(false);
        this.textField3.setFocused(true);
      } else if (this.textField5.isFocused()) {
        this.textField5.setFocused(false);
        this.textField4.setFocused(true);
      } else if (this.textField6.isFocused()) {
        this.textField6.setFocused(false);
        this.textField5.setFocused(true);
      } else if (this.textField7.isFocused()) {
        this.textField7.setFocused(false);
        this.textField6.setFocused(true);
      } else if (this.textField8.isFocused()) {
        this.textField8.setFocused(false);
        this.textField7.setFocused(true);
      }  
    if (!this.textField0.textboxKeyTyped(par1, par2) && 
      !this.textField1.textboxKeyTyped(par1, par2) && 
      !this.textField2.textboxKeyTyped(par1, par2) && 
      !this.textField3.textboxKeyTyped(par1, par2) && 
      !this.textField4.textboxKeyTyped(par1, par2) && 
      !this.textField5.textboxKeyTyped(par1, par2) && 
      !this.textField6.textboxKeyTyped(par1, par2) && 
      !this.textField7.textboxKeyTyped(par1, par2) && 
      !this.textField8.textboxKeyTyped(par1, par2) && 
      !this.textFieldTitle.textboxKeyTyped(par1, par2))
      super.func_73869_a(par1, par2); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */