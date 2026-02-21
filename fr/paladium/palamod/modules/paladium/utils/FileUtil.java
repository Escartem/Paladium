package fr.paladium.palamod.modules.paladium.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class FileUtil {
  public boolean isBookSaved(ItemStack stack, World world) {
    if (stack.func_77942_o()) {
      NBTTagCompound pageTags = stack.func_77978_p();
      NBTTagString titleTag = (NBTTagString)pageTags.func_74781_a("title");
      NBTTagString authorTag = (NBTTagString)pageTags.func_74781_a("author");
      if (titleTag != null && authorTag != null) {
        String title = titleTag.toString();
        String author = authorTag.toString();
        title = title.replace("\"", "");
        author = author.replace("\"", "");
        NBTTagList page = pageTags.func_150295_c("pages", 8);
        File book = new File(getSaveDir(world), author + ", " + title + "");
        if (book.exists())
          return true; 
      } 
    } 
    return false;
  }
  
  public void saveBook(ItemStack stack, World world) {
    if (stack.func_77942_o()) {
      NBTTagCompound pageTags = stack.func_77978_p();
      NBTTagString titleTag = (NBTTagString)pageTags.func_74781_a("title");
      NBTTagString authorTag = (NBTTagString)pageTags.func_74781_a("author");
      if (titleTag != null && authorTag != null) {
        String title = titleTag.toString();
        String author = authorTag.toString();
        title = title.replace("\"", "");
        author = author.replace("\"", "");
        NBTTagList page = pageTags.func_150295_c("pages", 8);
        File book = new File(getSaveDir(world), author + ", " + title + "");
        if (!book.exists())
          try {
            FileWriter writer = new FileWriter(book);
            writer.write(title);
            writer.write("\n");
            writer.write(author);
            writer.write("\n");
            writer.write("public");
            writer.write("\n");
            for (int i = 0; i < page.func_74745_c(); i++) {
              writer.write("#pgx" + i);
              writer.write("\n");
              writer.write(page.func_150307_f(i));
              writer.write("\n");
            } 
            writer.close();
          } catch (IOException e) {
            e.printStackTrace();
          }  
      } 
    } 
  }
  
  public int getBookNumber(World world, String author, String title) {
    String[] booksArray = scanBookDir(world);
    int bookint = -1;
    if (booksArray != null)
      for (int x = 0; x < booksArray.length; x++) {
        if (booksArray[x].contains(author) && booksArray[x].contains(title))
          bookint = x; 
      }  
    return bookint;
  }
  
  public int getBookNumber(World world, String authortitle) {
    String[] booksArray = scanBookDir(world);
    int bookint = -1;
    if (booksArray != null)
      for (int x = 0; x < booksArray.length; x++) {
        if (booksArray[x].contains(authortitle))
          bookint = x; 
      }  
    return bookint;
  }
  
  public int getBookNumber(boolean isclient, String authortitle) {
    String[] booksArray = scanBookDir(isclient);
    int bookint = -1;
    if (booksArray != null)
      for (int x = 0; x < booksArray.length; x++) {
        if (booksArray[x].contains(authortitle))
          bookint = x; 
      }  
    return bookint;
  }
  
  public ItemStack loadBook(World world, ItemStack stack, int bookint) {
    if (stack.func_77973_b() != Items.field_151122_aG) {
      System.out.println("Cannot print to these type of book");
      return null;
    } 
    String[] booksArray = scanBookDir(world);
    if (booksArray != null) {
      if (booksArray.length < bookint)
        return null; 
      stack = new ItemStack(Items.field_151164_bB);
      File book = new File(getSaveDir(world), booksArray[bookint]);
      NBTTagCompound bookinfo = new NBTTagCompound();
      try {
        FileReader reader = new FileReader(book);
        BufferedReader breader = new BufferedReader(reader);
        String line = breader.readLine();
        if (line != null) {
          bookinfo.func_74782_a("title", (NBTBase)new NBTTagString(line));
          line = breader.readLine();
          bookinfo.func_74782_a("author", (NBTBase)new NBTTagString(line));
          line = breader.readLine();
        } 
        line = breader.readLine();
        NBTTagList bookTagList = new NBTTagList();
        while (line != null) {
          if (!line.contains("#pgx") && line != null) {
            String pagetag = line;
            line = breader.readLine();
            if (line != null) {
              if (line.isEmpty())
                line = " "; 
              while (!line.contains("#pgx") && line != null) {
                pagetag = pagetag + "\n" + line;
                line = breader.readLine();
                if (line == null)
                  break; 
                if (line.isEmpty())
                  line = " "; 
              } 
            } 
            NBTTagString nbtpage = new NBTTagString(pagetag);
            bookTagList.func_74742_a((NBTBase)nbtpage);
            bookinfo.func_74782_a("pages", (NBTBase)bookTagList);
            continue;
          } 
          line = breader.readLine();
        } 
        breader.close();
        stack.func_77982_d(bookinfo);
        return stack;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } else {
      return null;
    } 
    return null;
  }
  
  public String[] scanBookDir(World world) {
    File bookLoc = getSaveDir(world);
    File[] bookList = bookLoc.listFiles();
    if (bookList != null) {
      String[] bookArray = new String[bookList.length];
      for (int x = 0; x < bookList.length; x++) {
        String bookName = bookList[x].getName();
        bookArray[x] = bookList[x].getName();
      } 
      ArrayList<String> bookSortList = new ArrayList();
      for (int n = 0; n < bookArray.length; n++) {
        if (!bookArray[n].contains(".dat"))
          bookSortList.add(bookArray[n]); 
      } 
      String[] newBookArray = new String[bookSortList.size()];
      for (int i = 0; i < newBookArray.length; i++)
        newBookArray[i] = bookSortList.get(i); 
      bookArray = newBookArray;
      return bookArray;
    } 
    return null;
  }
  
  public File getSaveDir(World world) {
    File storage;
    if (!MinecraftServer.func_71276_C().func_71262_S()) {
      storage = new File((Minecraft.func_71410_x()).field_71412_D, "config");
    } else {
      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
      storage = new File(server.func_71270_I());
    } 
    File biblioDir = new File(storage, "books");
    if (biblioDir.mkdir());
    return biblioDir;
  }
  
  public String[] scanBookDir(boolean isclient) {
    File bookLoc = getSaveDir(isclient);
    File[] bookList = bookLoc.listFiles();
    if (bookList != null) {
      String[] bookArray = new String[bookList.length];
      for (int x = 0; x < bookList.length; x++) {
        String bookName = bookList[x].getName();
        bookArray[x] = bookList[x].getName();
      } 
      ArrayList<String> bookSortList = new ArrayList();
      int n;
      for (n = 0; n < bookArray.length; n++)
        bookSortList.add(bookArray[n]); 
      for (n = 0; n < bookSortList.size(); n++) {
        String bname = bookSortList.get(n);
        if (bname.contains(".dat"))
          bookSortList.remove(n); 
      } 
      String[] newBookArray = new String[bookSortList.size()];
      for (int i = 0; i < newBookArray.length; i++)
        newBookArray[i] = bookSortList.get(i); 
      bookArray = newBookArray;
      return bookArray;
    } 
    return null;
  }
  
  public String getBookName(World world, int booknum) {
    String[] booklist = scanBookDir(world);
    if (booklist.length > booknum)
      return booklist[booknum]; 
    return null;
  }
  
  public String[] getAuthorList(String[] books, boolean isClient) {
    File bookLoc = getSaveDir(isClient);
    String[] authorList = new String[books.length];
    for (int i = 0; i < books.length; i++) {
      try {
        File book = new File(bookLoc, books[i]);
        FileReader reader = new FileReader(book);
        BufferedReader breader = new BufferedReader(reader);
        String line = breader.readLine();
        line = breader.readLine();
        if (line != null)
          authorList[i] = line; 
        breader.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    return authorList;
  }
  
  public boolean[] getPublistList(String[] books, boolean isClient) {
    File bookLoc = getSaveDir(isClient);
    boolean[] isPublicList = new boolean[books.length];
    for (int i = 0; i < books.length; i++) {
      try {
        File book = new File(bookLoc, books[i]);
        FileReader reader = new FileReader(book);
        BufferedReader breader = new BufferedReader(reader);
        String line = breader.readLine();
        if (line != null)
          line = breader.readLine(); 
        if (line != null)
          line = breader.readLine(); 
        if (line != null)
          if (line.contains("private")) {
            isPublicList[i] = false;
          } else if (line.contains("public")) {
            isPublicList[i] = true;
          } else {
            addPublicPrivateFieldToBook(bookLoc, books[i]);
            isPublicList[i] = true;
          }  
        breader.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    return isPublicList;
  }
  
  public void addPublicPrivateFieldToBook(File dir, String bookFile) {
    ArrayList<String> bookText = new ArrayList();
    try {
      File book = new File(dir, bookFile);
      FileReader reader = new FileReader(book);
      BufferedReader breader = new BufferedReader(reader);
      String line = breader.readLine();
      if (line != null)
        bookText.add(line); 
      line = breader.readLine();
      if (line != null)
        bookText.add(line); 
      line = breader.readLine();
      if (line != null)
        if (line.contains("private") || line.contains("public")) {
          bookText.add(line);
        } else {
          bookText.add("public");
        }  
      line = breader.readLine();
      while (line != null) {
        bookText.add(line);
        line = breader.readLine();
      } 
      breader.close();
      FileWriter writer = new FileWriter(book);
      for (int i = 0; i < bookText.size(); i++) {
        writer.write(bookText.get(i));
        writer.write("\n");
      } 
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public File getSaveDir(boolean isclient) {
    File storage;
    if (isclient) {
      storage = new File((Minecraft.func_71410_x()).field_71412_D, "config");
    } else {
      MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
      storage = new File(server.func_71270_I());
    } 
    File biblioDir = new File(storage, "books");
    if (biblioDir.mkdir());
    return biblioDir;
  }
  
  public boolean deleteBook(boolean isClient, String bookname) {
    File book = new File(getSaveDir(isClient), bookname);
    int bookCheck = getBookType(isClient, getBookNumber(isClient, bookname));
    if (bookCheck == -1) {
      if (book.delete())
        return true; 
    } else {
      File bookDat = new File(getSaveDir(isClient), bookname + ".dat");
      if (book.delete() && bookDat.delete())
        return true; 
    } 
    return false;
  }
  
  public boolean updatePublicFlag(boolean isClient, String bookname, boolean flag) {
    ArrayList<String> bookText = new ArrayList();
    try {
      File book = new File(getSaveDir(isClient), bookname);
      FileReader reader = new FileReader(book);
      BufferedReader breader = new BufferedReader(reader);
      String line = breader.readLine();
      if (line != null)
        bookText.add(line); 
      line = breader.readLine();
      if (line != null)
        bookText.add(line); 
      line = breader.readLine();
      if (line != null)
        if (flag) {
          bookText.add("public");
        } else {
          bookText.add("private");
        }  
      line = breader.readLine();
      while (line != null) {
        bookText.add(line);
        line = breader.readLine();
      } 
      breader.close();
      FileWriter writer = new FileWriter(book);
      for (int i = 0; i < bookText.size(); i++) {
        writer.write(bookText.get(i));
        writer.write("\n");
      } 
      writer.close();
      return true;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return false;
  }
  
  public boolean saveNBTtoFile(ItemStack book, World world, int type) {
    if (book != null) {
      NBTTagCompound nbt = book.func_77978_p();
      if (nbt != null) {
        String filename = "";
        String title = book.func_82833_r();
        title = title.replace("ï¿½f", "");
        boolean savedMeta = false;
        if (type == 0) {
          String author = nbt.func_74779_i("author");
          filename = author + ", " + title;
          savedMeta = saveBookMeta(world, filename, title, author, "Big");
        } else if (type == 1) {
          title = title.replace(":", ";");
          filename = title;
          savedMeta = saveBookMeta(world, filename, title, "NoAuthor", "Recipe");
        } else if (type == 2) {
          title = title.replace(":", ";");
          filename = title;
          savedMeta = saveBookMeta(world, filename, title, "NoAuthor", "Stockroom");
        } else {
          return false;
        } 
        if (savedMeta) {
          File bookFile = new File(getSaveDir(world), filename + ".dat");
          try {
            CompressedStreamTools.func_74795_b(nbt, bookFile);
            return true;
          } catch (IOException e) {
            e.printStackTrace();
          } 
        } 
      } 
    } 
    return false;
  }
  
  public boolean saveBookMeta(World world, String filename, String title, String author, String type) {
    File bookFile = new File(getSaveDir(world), filename);
    if (!bookFile.exists())
      try {
        FileWriter writer = new FileWriter(bookFile);
        writer.write(title);
        writer.write("\n");
        writer.write(author);
        writer.write("\n");
        writer.write("public");
        writer.write("\n");
        writer.write("Booktype&=" + type);
        writer.write("\n");
        writer.write(filename);
        writer.close();
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      }  
    return false;
  }
  
  public int getBookType(World world, int bookNum) {
    String[] booksArray = scanBookDir(world);
    try {
      File book = new File(getSaveDir(world), booksArray[bookNum]);
      FileReader reader = new FileReader(book);
      BufferedReader breader = new BufferedReader(reader);
      String line = breader.readLine();
      line = breader.readLine();
      line = breader.readLine();
      line = breader.readLine();
      if (line != null && 
        line.contains("Booktype="))
        line = line.replace("Booktype=", ""); 
      breader.close();
      if (line.contains("Big"))
        return 0; 
      if (line.contains("Recipe"))
        return 1; 
      if (line.contains("Stockroom"))
        return 2; 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return -1;
  }
  
  public int getBookType(boolean isClient, int bookNum) {
    String[] booksArray = scanBookDir(isClient);
    try {
      File book = new File(getSaveDir(isClient), booksArray[bookNum]);
      FileReader reader = new FileReader(book);
      BufferedReader breader = new BufferedReader(reader);
      String line = breader.readLine();
      line = breader.readLine();
      line = breader.readLine();
      line = breader.readLine();
      if (line != null && 
        line.contains("Booktype="))
        line = line.replace("Booktype=", ""); 
      breader.close();
      if (line.contains("Big"))
        return 0; 
      if (line.contains("Recipe"))
        return 1; 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return -1;
  }
  
  public ItemStack loadBookNBT(World world, ItemStack bookstack, int booknum) {
    String[] booklist = scanBookDir(world);
    File book = new File(getSaveDir(world), booklist[booknum] + ".dat");
    NBTTagCompound nbt = null;
    if (book.exists()) {
      try {
        nbt = CompressedStreamTools.func_74797_a(book);
      } catch (IOException e) {
        e.printStackTrace();
      } 
      if (nbt != null)
        bookstack.func_77982_d(nbt); 
    } 
    return bookstack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\FileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */