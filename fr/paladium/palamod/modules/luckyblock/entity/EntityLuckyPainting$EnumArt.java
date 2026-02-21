package fr.paladium.palamod.modules.luckyblock.entity;

public enum EnumArt {
  Kebab("Paladium Small", 16, 16, 0, 0),
  Aztec("Snow", 16, 16, 16, 0),
  Alban("Alban", 16, 16, 32, 0),
  Aztec2("Aztec2", 16, 16, 48, 0),
  Bomb("Bomb", 16, 16, 64, 0),
  Plant("Plant", 16, 16, 80, 0),
  Wasteland("Wasteland", 16, 16, 96, 0),
  Pool("Pool", 32, 16, 0, 32),
  Courbet("Courbet", 32, 16, 32, 32),
  Sea("Sea", 32, 16, 64, 32),
  Sunset("Sunset", 32, 16, 96, 32),
  Creebet("Creebet", 32, 16, 128, 32),
  Wanderer("Wanderer", 16, 32, 0, 64),
  Match("Paladium", 32, 32, 0, 128),
  Bust("Bust", 32, 32, 32, 128),
  Stage("Stage", 32, 32, 64, 128),
  Void("Void", 32, 32, 96, 128),
  Fighters("Butterfly", 64, 32, 0, 96),
  Pointer("Pointer", 64, 64, 0, 192),
  Skeleton("Skeleton", 64, 48, 192, 64);
  
  public static final int maxArtTitleLength;
  
  public final String title;
  
  public final int sizeX;
  
  public final int sizeY;
  
  public final int offsetX;
  
  public final int offsetY;
  
  private static final String __OBFID = "CL_00001557";
  
  static {
    maxArtTitleLength = "Paladium Small".length();
  }
  
  EnumArt(String title, int width, int height, int x, int y) {
    this.title = title;
    this.sizeX = width;
    this.sizeY = height;
    this.offsetX = x;
    this.offsetY = y;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityLuckyPainting$EnumArt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */