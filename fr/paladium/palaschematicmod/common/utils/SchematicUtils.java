package fr.paladium.palaschematicmod.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sk89q.jnbt.ByteArrayTag;
import com.sk89q.jnbt.ByteTag;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.jnbt.DoubleTag;
import com.sk89q.jnbt.FloatTag;
import com.sk89q.jnbt.IntArrayTag;
import com.sk89q.jnbt.IntTag;
import com.sk89q.jnbt.ListTag;
import com.sk89q.jnbt.LongTag;
import com.sk89q.jnbt.ShortTag;
import com.sk89q.jnbt.StringTag;
import com.sk89q.jnbt.Tag;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.LocalConfiguration;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.blocks.BaseBlock;
import fr.paladium.palaschematicmod.PalaSchematicMod;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockInfo;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.data.EntityData;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedBlockData;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.server.async.AsyncPalaSchematic;
import fr.paladium.palaschematicmod.server.async.PasteCallback;
import fr.paladium.palaschematicmod.server.manager.PalaSchematicManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SchematicUtils {
  private static final Gson GSON = (new GsonBuilder()).create();
  
  public static void generateSchematicData(World world, Schematic schematic, SchematicCopySession session) {
    int minX = Math.min(session.firstPos.getX(), session.secondPos.getX());
    int maxX = Math.max(session.firstPos.getX(), session.secondPos.getX());
    int minY = Math.min(session.firstPos.getY(), session.secondPos.getY());
    int maxY = Math.max(session.firstPos.getY(), session.secondPos.getY());
    int minZ = Math.min(session.firstPos.getZ(), session.secondPos.getZ());
    int maxZ = Math.max(session.firstPos.getZ(), session.secondPos.getZ());
    int centerX = (minX + maxX) / 2;
    int centerY = (minY + maxY) / 2;
    int centerZ = (minZ + maxZ) / 2;
    generateSchematicData(world, new BlockPos(centerX, centerY, centerZ), schematic, session);
  }
  
  public static void generateSchematicData(World world, BlockPos center, Schematic schematic, SchematicCopySession session) {
    int minX = Math.min(session.firstPos.getX(), session.secondPos.getX());
    int maxX = Math.max(session.firstPos.getX(), session.secondPos.getX());
    int minY = Math.min(session.firstPos.getY(), session.secondPos.getY());
    int maxY = Math.max(session.firstPos.getY(), session.secondPos.getY());
    int minZ = Math.min(session.firstPos.getZ(), session.secondPos.getZ());
    int maxZ = Math.max(session.firstPos.getZ(), session.secondPos.getZ());
    generateSchematicDataFromRegion(world, center, schematic, minX, maxX, minY, maxY, minZ, maxZ);
  }
  
  public static void generateSchematicDataFromRegion(World world, BlockPos center, Schematic schematic, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
    int centerX = center.getX();
    int centerY = center.getY();
    int centerZ = center.getZ();
    for (int x = minX; x <= maxX; x++) {
      for (int y = minY; y <= maxY; y++) {
        for (int z = minZ; z <= maxZ; z++) {
          Block block = world.func_147439_a(x, y, z);
          int metadata = world.func_72805_g(x, y, z);
          BlockPos pos = new BlockPos(x - centerX, y - centerY, z - centerZ);
          BlockInfo info = new BlockInfo(block, (byte)metadata);
          TileEntity te = world.func_147438_o(x, y, z);
          if (te != null) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            te.func_145841_b(nbtTagCompound);
            info.setNbtTagCompound(nbtTagCompound.toString());
          } 
          BlockData blockData = new BlockData(pos, info);
          schematic.addBlock(blockData);
        } 
      } 
    } 
    AxisAlignedBB selection = AxisAlignedBB.func_72330_a(minX, minY, minZ, (maxX + 1), (maxY + 1), (maxZ + 1));
    List<Entity> entities = world.func_72872_a(Entity.class, selection);
    entities.forEach(entity -> schematic.addEntity(entity, center));
  }
  
  public static File getUndoFolder() {
    File directory = new File(PalaSchematicMod.configFolder, "/schematics/sessions/undo/");
    if (!directory.exists())
      directory.mkdirs(); 
    return directory;
  }
  
  public static File getUndoFile(String playerUUID) {
    File directory = getUndoFolder();
    return new File(directory, playerUUID + ".palaschematic_v2");
  }
  
  public static File getUndoSessionFile(String playerUUID) {
    File directory = getUndoFolder();
    return new File(directory, playerUUID + ".session");
  }
  
  public static void saveUndoSession(String playerUUID, BlockPos absoluteOrigin) {
    File file = getUndoSessionFile(playerUUID);
    try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      dos.writeInt(absoluteOrigin.getX());
      dos.writeInt(absoluteOrigin.getY());
      dos.writeInt(absoluteOrigin.getZ());
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static BlockPos loadUndoSession(String playerUUID) {
    File file = getUndoSessionFile(playerUUID);
    if (!file.exists())
      return null; 
    try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int x = dis.readInt();
      int y = dis.readInt();
      int z = dis.readInt();
      return new BlockPos(x, y, z);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static Schematic loadUndoSchematic(String playerUUID) {
    File file = getUndoFile(playerUUID);
    if (!file.exists())
      return null; 
    return loadSchematic(file);
  }
  
  public static void saveUndoSchematic(Schematic schematic, String playerUUID) {
    File file = getUndoFile(playerUUID);
    saveSchematic(schematic, file);
  }
  
  public static void deleteUndoSchematic(String playerUUID) {
    File file = getUndoFile(playerUUID);
    if (file.exists())
      file.delete(); 
    File sessionFile = getUndoSessionFile(playerUUID);
    if (sessionFile.exists())
      sessionFile.delete(); 
  }
  
  public static File getClipboardFolder() {
    File directory = new File(PalaSchematicMod.configFolder, "/schematics/sessions/clipboard/");
    if (!directory.exists())
      directory.mkdirs(); 
    return directory;
  }
  
  public static File getClipboardFile(String playerUUID) {
    File directory = getClipboardFolder();
    return new File(directory, playerUUID + ".palaschematic_v2");
  }
  
  public static Schematic loadClipboard(String playerUUID) {
    File file = getClipboardFile(playerUUID);
    if (!file.exists())
      return null; 
    return loadSchematic(file);
  }
  
  public static void saveClipboard(Schematic schematic, String playerUUID) {
    File file = getClipboardFile(playerUUID);
    saveSchematic(schematic, file);
  }
  
  public static void saveSchematic(Schematic schematic) {
    saveSchematic(schematic, new File(PalaSchematicMod.configFolder, "/schematics/"));
  }
  
  public static void saveSchematic(Schematic schematic, File file) {
    if (file.isDirectory()) {
      if (!file.exists())
        file.mkdirs(); 
      file = new File(file, schematic.getName() + ".palaschematic_v2");
    } 
    if (file.exists())
      file.delete(); 
    try {
      saveSchematicOptimized(schematic, file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  private static void saveSchematicOptimized(Schematic schematic, File file) throws IOException {
    Set<String> allStrings = new HashSet<>();
    Map<BlockInfo, List<BlockPos>> blockGroups = new HashMap<>();
    for (BlockData blockData : schematic.getBlocks()) {
      BlockInfo info = blockData.getInfo();
      allStrings.add(info.getMaterial());
      if (info.getNbtTagCompound() != null && !info.getNbtTagCompound().isEmpty())
        allStrings.add(info.getNbtTagCompound()); 
      ((List<BlockPos>)blockGroups.computeIfAbsent(createBlockInfoKey(info), k -> new ArrayList())).add(blockData.getPos());
    } 
    for (EntityData entityData : schematic.getEntities())
      allStrings.add(entityData.getClazzName()); 
    String[] stringTable = allStrings.<String>toArray(new String[0]);
    Map<String, Integer> stringIndexMap = new HashMap<>();
    for (int i = 0; i < stringTable.length; i++)
      stringIndexMap.put(stringTable[i], Integer.valueOf(i)); 
    if (!file.getParentFile().exists())
      file.getParentFile().mkdirs(); 
    try (DataOutputStream dos = new DataOutputStream(new DeflaterOutputStream(new BufferedOutputStream(new FileOutputStream(file))))) {
      writeString(dos, schematic.getName() + ":" + schematic.getAuthor());
      dos.writeByte(schematic.getVersion());
      dos.writeLong(schematic.getDate());
      dos.writeShort(stringTable.length);
      for (String str : stringTable)
        writeString(dos, str); 
      dos.writeInt(blockGroups.size());
      for (Map.Entry<BlockInfo, List<BlockPos>> entry : blockGroups.entrySet()) {
        writeBlockInfo(dos, entry.getKey(), stringIndexMap);
        writePositionList(dos, entry.getValue());
      } 
      dos.writeInt(schematic.getEntities().size());
      for (EntityData entity : schematic.getEntities())
        writeEntity(dos, entity, stringIndexMap); 
    } 
  }
  
  private static BlockInfo createBlockInfoKey(BlockInfo original) {
    BlockInfo key = new BlockInfo();
    key.setMaterial(original.getMaterial());
    key.setData(original.getData());
    key.setNbtTagCompound(original.getNbtTagCompound());
    return key;
  }
  
  private static void writeString(DataOutputStream dos, String str) throws IOException {
    if (str == null) {
      dos.writeShort(0);
      return;
    } 
    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
    dos.writeShort(bytes.length);
    dos.write(bytes);
  }
  
  private static void writeBlockInfo(DataOutputStream dos, BlockInfo info, Map<String, Integer> stringIndexMap) throws IOException {
    dos.writeShort(((Integer)stringIndexMap.get(info.getMaterial())).intValue());
    dos.writeByte(info.getData());
    if (info.getNbtTagCompound() != null && !info.getNbtTagCompound().isEmpty()) {
      dos.writeBoolean(true);
      dos.writeShort(((Integer)stringIndexMap.get(info.getNbtTagCompound())).intValue());
    } else {
      dos.writeBoolean(false);
    } 
  }
  
  private static void writePositionList(DataOutputStream dos, List<BlockPos> positions) throws IOException {
    dos.writeInt(positions.size());
    if (positions.isEmpty())
      return; 
    positions.sort((a, b) -> {
          int cmp = Integer.compare(a.getX(), b.getX());
          if (cmp != 0)
            return cmp; 
          cmp = Integer.compare(a.getZ(), b.getZ());
          return (cmp != 0) ? cmp : Integer.compare(a.getY(), b.getY());
        });
    BlockPos first = positions.get(0);
    writeVarInt(dos, first.getX());
    writeVarInt(dos, first.getY());
    writeVarInt(dos, first.getZ());
    BlockPos prev = first;
    for (int i = 1; i < positions.size(); i++) {
      BlockPos current = positions.get(i);
      writeVarInt(dos, current.getX() - prev.getX());
      writeVarInt(dos, current.getY() - prev.getY());
      writeVarInt(dos, current.getZ() - prev.getZ());
      prev = current;
    } 
  }
  
  private static void writeVarInt(DataOutputStream dos, int value) throws IOException {
    while ((value & 0xFFFFFF80) != 0) {
      dos.writeByte(value & 0x7F | 0x80);
      value >>>= 7;
    } 
    dos.writeByte(value & 0x7F);
  }
  
  private static void writeEntity(DataOutputStream dos, EntityData entity, Map<String, Integer> stringIndexMap) throws IOException {
    dos.writeShort(((Integer)stringIndexMap.get(entity.getClazzName())).intValue());
    dos.writeDouble(entity.getX());
    dos.writeDouble(entity.getY());
    dos.writeDouble(entity.getZ());
    if (entity.getNbtBytes() != null) {
      dos.writeInt((entity.getNbtBytes()).length);
      dos.write(entity.getNbtBytes());
    } else {
      dos.writeInt(0);
    } 
  }
  
  public static Schematic loadSchematic(String schematicName) {
    return loadSchematic(new File(PalaSchematicMod.configFolder, "/schematics/"), schematicName);
  }
  
  public static Schematic loadSchematic(File directory, String schematicName) {
    if (!directory.exists())
      directory.mkdirs(); 
    File file = new File(directory, schematicName + ".palaschematic_v2");
    if (!file.exists())
      file = new File(directory, schematicName + ".palaschematic"); 
    if (!file.exists())
      return null; 
    return loadSchematic(file);
  }
  
  public static Schematic loadSchematic(File file) {
    switch (file.getName().substring(file.getName().lastIndexOf('.'))) {
      case ".palaschematic":
        return loadSchematic(file, SchematicVersion.V1);
      case ".palaschematic_v2":
        return loadSchematic(file, SchematicVersion.V2);
    } 
    throw new IllegalArgumentException("Format de schématique non pris en charge : " + file.getName());
  }
  
  public static Schematic loadSchematic(File file, SchematicVersion version) {
    switch (version) {
      case V1:
        try(FileInputStream fileInputStream = new FileInputStream(file); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); InflaterInputStream inflaterInputStream = new InflaterInputStream(bufferedInputStream)) {
          return (Schematic)GSON.fromJson(new InputStreamReader(inflaterInputStream, StandardCharsets.UTF_8), Schematic.class);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        } 
      case V2:
        try {
          return loadSchematicOptimized(file);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        } 
    } 
    throw new IllegalArgumentException("Version de schématique non prise en charge : " + version);
  }
  
  private static Schematic loadSchematicOptimized(File file) throws IOException {
    try (DataInputStream dis = new DataInputStream(new InflaterInputStream(new BufferedInputStream(new FileInputStream(file))))) {
      String nameAuthor = readString(dis);
      String[] parts = nameAuthor.split(":", 2);
      String name = (parts.length > 0) ? parts[0] : "unknown";
      String author = (parts.length > 1) ? parts[1] : "unknown";
      int version = dis.readByte();
      long date = dis.readLong();
      Schematic schematic = new Schematic(name, author, version, date);
      int stringTableSize = dis.readShort();
      String[] stringTable = new String[stringTableSize];
      for (int i = 0; i < stringTableSize; i++)
        stringTable[i] = readString(dis); 
      int blockGroupCount = dis.readInt();
      for (int j = 0; j < blockGroupCount; j++) {
        BlockInfo blockInfo = readBlockInfo(dis, stringTable);
        List<BlockPos> positions = readPositionList(dis);
        for (BlockPos pos : positions)
          schematic.addBlock(new BlockData(pos, blockInfo)); 
      } 
      int entityCount = dis.readInt();
      for (int k = 0; k < entityCount; k++) {
        EntityData entity = readEntity(dis, stringTable);
        schematic.getEntities().add(entity);
      } 
      return schematic;
    } 
  }
  
  private static String readString(DataInputStream dis) throws IOException {
    int length = dis.readShort();
    if (length == 0)
      return null; 
    byte[] bytes = new byte[length];
    dis.readFully(bytes);
    return new String(bytes, StandardCharsets.UTF_8);
  }
  
  private static BlockInfo readBlockInfo(DataInputStream dis, String[] stringTable) throws IOException {
    int materialIndex = dis.readShort();
    byte data = dis.readByte();
    boolean hasNbt = dis.readBoolean();
    BlockInfo info = new BlockInfo();
    info.setMaterial(stringTable[materialIndex]);
    info.setData(data);
    if (hasNbt) {
      int nbtIndex = dis.readShort();
      info.setNbtTagCompound(stringTable[nbtIndex]);
    } 
    return info;
  }
  
  private static List<BlockPos> readPositionList(DataInputStream dis) throws IOException {
    int size = dis.readInt();
    List<BlockPos> positions = new ArrayList<>(size);
    if (size == 0)
      return positions; 
    int x = readVarInt(dis);
    int y = readVarInt(dis);
    int z = readVarInt(dis);
    positions.add(new BlockPos(x, y, z));
    for (int i = 1; i < size; i++) {
      x += readVarInt(dis);
      y += readVarInt(dis);
      z += readVarInt(dis);
      positions.add(new BlockPos(x, y, z));
    } 
    return positions;
  }
  
  private static int readVarInt(DataInputStream dis) throws IOException {
    byte b;
    int result = 0;
    int shift = 0;
    do {
      b = dis.readByte();
      result |= (b & Byte.MAX_VALUE) << shift;
      shift += 7;
    } while ((b & 0x80) != 0);
    return result;
  }
  
  private static EntityData readEntity(DataInputStream dis, String[] stringTable) throws IOException {
    int clazzIndex = dis.readShort();
    double x = dis.readDouble();
    double y = dis.readDouble();
    double z = dis.readDouble();
    int nbtLength = dis.readInt();
    byte[] nbtBytes = null;
    if (nbtLength > 0) {
      nbtBytes = new byte[nbtLength];
      dis.readFully(nbtBytes);
    } 
    try {
      Constructor<EntityData> constructor = EntityData.class.getDeclaredConstructor(new Class[0]);
      constructor.setAccessible(true);
      EntityData entity = constructor.newInstance(new Object[0]);
      entity.setClazzName(stringTable[clazzIndex]);
      entity.setX(x);
      entity.setY(y);
      entity.setZ(z);
      entity.setNbtBytes(nbtBytes);
      return entity;
    } catch (Exception e) {
      throw new IOException("Impossible de créer EntityData", e);
    } 
  }
  
  public static File getSchematicFolder() {
    File directory = new File(PalaSchematicMod.configFolder, "/schematics/");
    if (!directory.exists())
      directory.mkdirs(); 
    return directory;
  }
  
  public static File getSchematicFile(String schematicName) {
    File directory = new File(PalaSchematicMod.configFolder, "/schematics/");
    if (!directory.exists())
      directory.mkdirs(); 
    File file = new File(directory, schematicName + ".palaschematic_v2");
    if (!file.exists())
      file = new File(directory, schematicName + ".palaschematic"); 
    return file;
  }
  
  public static void pasteSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, replaceMap, 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, replaceMap, 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), 0L, callback, blockConsumer);
  }
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), 0L, callback, blockConsumer);
  }
  
  public static void pasteSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, boolean pasteData, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, pasteData, replaceMap, 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, boolean pasteData, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, pasteData, replaceMap, 0L, callback, null);
  }
  
  public static void pasteSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, replaceMap, 0L, callback, blockConsumer);
  }
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, replaceMap, 0L, callback, blockConsumer);
  }
  
  public static void pasteTimedSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, long expirationDelay, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), expirationDelay, callback, null);
  }
  
  public static void pasteTimedSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, long expirationDelay, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, new HashMap<>(), expirationDelay, callback, null);
  }
  
  public static void pasteTimedSchematicAsync(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, long expirationDelay, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, null, origin, schematic, pasteAir, pasteEntities, true, replaceMap, expirationDelay, callback, null);
  }
  
  public static void pasteTimedSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, long expirationDelay, PasteCallback callback) {
    AsyncPalaSchematic.pasteSchematicAsync(world, player, origin, schematic, pasteAir, pasteEntities, true, replaceMap, expirationDelay, callback, null);
  }
  
  public static TimedSchematic pasteSchematic(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities) {
    return pasteTimedSchematic(world, origin, schematic, pasteAir, pasteEntities, 0L);
  }
  
  public static TimedSchematic pasteTimedSchematic(World world, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, long expirationDelay) {
    if (schematic == null || world == null)
      return null; 
    TimedSchematic timedSchematic = new TimedSchematic();
    timedSchematic.setName(schematic.getName());
    timedSchematic.setOriginalBlocks(new ArrayList());
    timedSchematic.setExpiration(System.currentTimeMillis() + expirationDelay);
    for (BlockData blockData : schematic.getBlocks()) {
      BlockPos relativePos = blockData.getPos();
      BlockInfo blockInfo = blockData.getInfo();
      if (!pasteAir && "minecraft:air".equalsIgnoreCase(blockInfo.getMaterial()))
        continue; 
      int worldX = origin.getX() + relativePos.getX();
      int worldY = origin.getY() + relativePos.getY();
      int worldZ = origin.getZ() + relativePos.getZ();
      blockData.getPos().setX(worldX);
      blockData.getPos().setY(worldY);
      blockData.getPos().setZ(worldZ);
      Block originalBlock = world.func_147439_a(worldX, worldY, worldZ);
      int originalMetadata = world.func_72805_g(worldX, worldY, worldZ);
      TimedBlockData timedBlockData = new TimedBlockData();
      timedBlockData.setBlock(originalBlock);
      timedBlockData.setMetadata(originalMetadata);
      timedBlockData.setX(worldX);
      timedBlockData.setY(worldY);
      timedBlockData.setZ(worldZ);
      TileEntity te = world.func_147438_o(worldX, worldY, worldZ);
      if (te != null) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        te.func_145841_b(nbtTagCompound);
        timedBlockData.setTileEntityData(nbtTagCompound);
      } 
      timedSchematic.getOriginalBlocks().add(timedBlockData);
      if (blockData != null)
        blockData.apply(world); 
    } 
    if (pasteEntities)
      schematic.getEntities().forEach(entityData -> {
            Entity entity = entityData.getEntity(world);
            if (entity != null) {
              entity.func_70107_b(entityData.getX() + origin.getX(), entityData.getY() + origin.getY(), entityData.getZ() + origin.getZ());
              world.func_72838_d(entity);
            } 
          }); 
    if (expirationDelay > 0L)
      PalaSchematicManager.addTimedSchematic(world, timedSchematic); 
    return timedSchematic;
  }
  
  public static File[] listSchematics() {
    File folder = new File(PalaSchematicMod.configFolder, "/schematics/");
    if (folder.exists() && folder.isDirectory())
      return folder.listFiles(); 
    return null;
  }
  
  public static void convertWorldEditSchematicAsync(String name, Consumer<ConversionResult> callback) {
    (new Thread(() -> {
          try {
            LocalConfiguration config = WorldEdit.getInstance().getConfiguration();
            File weDir = WorldEdit.getInstance().getWorkingDirectoryFile(config.saveDir);
            File weFile = new File(weDir, name.endsWith(".schematic") ? name : (name + ".schematic"));
            if (!weFile.exists()) {
              callback.accept(new ConversionResult(false, "La schematic WorldEdit " + name + " n'existe pas.", null, 0, 0));
              return;
            } 
            callback.accept(new ConversionResult(true, "Chargement de la schematic WorldEdit...", null, 0, 0));
            CuboidClipboard clipboard = CuboidClipboard.loadSchematic(weFile);
            Vector size = clipboard.getSize();
            Vector origin = clipboard.getOffset();
            int totalBlocks = size.getBlockX() * size.getBlockY() * size.getBlockZ();
            callback.accept(new ConversionResult(true, "Conversion de " + totalBlocks + " blocs en cours...", null, 0, totalBlocks));
            Schematic schematic = new Schematic(name, "WorldEdit", 1, System.currentTimeMillis());
            int batchSize = 10000;
            int processedBlocks = 0;
            int notifyInterval = Math.max(1, totalBlocks / 20);
            for (int x = 0; x < size.getBlockX(); x++) {
              for (int y = 0; y < size.getBlockY(); y++) {
                for (int z = 0; z < size.getBlockZ(); z++) {
                  Vector pt = new Vector(x, y, z);
                  BaseBlock block = clipboard.getBlock(pt);
                  int relX = x + origin.getBlockX();
                  int relY = y + origin.getBlockY();
                  int relZ = z + origin.getBlockZ();
                  BlockPos pos = new BlockPos(relX, relY, relZ);
                  BlockInfo info = new BlockInfo();
                  Block mcBlock = Block.func_149729_e(block.getType());
                  if (mcBlock != null) {
                    info.setMaterial(Block.field_149771_c.func_148750_c(mcBlock));
                  } else {
                    info.setMaterial("minecraft:air");
                  } 
                  info.setData((byte)block.getData());
                  if (block.hasNbtData()) {
                    CompoundTag nbtTag = block.getNbtData();
                    NBTTagCompound mcNbt = convertWorldEditNBT(nbtTag);
                    if (mcNbt != null && !mcNbt.func_82582_d())
                      info.setNbtTagCompound(mcNbt.toString()); 
                  } 
                  schematic.addBlock(new BlockData(pos, info));
                  if (++processedBlocks % notifyInterval == 0) {
                    int current = processedBlocks;
                    callback.accept(new ConversionResult(true, "Conversion en cours...", null, current, totalBlocks));
                  } 
                  if (processedBlocks % 10000 == 0) {
                    System.gc();
                    Thread.sleep(10L);
                  } 
                } 
              } 
            } 
            callback.accept(new ConversionResult(true, "Sauvegarde de la schematic (" + schematic.getBlocks().size() + " blocs)...", null, totalBlocks, totalBlocks));
            saveSchematic(schematic);
            schematic.getBlocks().clear();
            schematic.getEntities().clear();
            System.gc();
            callback.accept(new ConversionResult(true, "Conversion terminée.", null, totalBlocks, totalBlocks));
          } catch (Exception e) {
            callback.accept(new ConversionResult(false, "Erreur lors de la conversion: " + e.getMessage(), null, 0, 0));
            e.printStackTrace();
          } 
        }"PalaSchematicMod/Convert-" + name)).start();
  }
  
  private static NBTTagCompound convertWorldEditNBT(CompoundTag weTag) {
    if (weTag == null)
      return null; 
    NBTTagCompound mcTag = new NBTTagCompound();
    for (Map.Entry<String, Tag> entry : (Iterable<Map.Entry<String, Tag>>)weTag.getValue().entrySet()) {
      String key = entry.getKey();
      Tag value = entry.getValue();
      if (value instanceof ByteTag) {
        mcTag.func_74774_a(key, ((ByteTag)value).getValue().byteValue());
        continue;
      } 
      if (value instanceof ShortTag) {
        mcTag.func_74777_a(key, ((ShortTag)value).getValue().shortValue());
        continue;
      } 
      if (value instanceof IntTag) {
        mcTag.func_74768_a(key, ((IntTag)value).getValue().intValue());
        continue;
      } 
      if (value instanceof LongTag) {
        mcTag.func_74772_a(key, ((LongTag)value).getValue().longValue());
        continue;
      } 
      if (value instanceof FloatTag) {
        mcTag.func_74776_a(key, ((FloatTag)value).getValue().floatValue());
        continue;
      } 
      if (value instanceof DoubleTag) {
        mcTag.func_74780_a(key, ((DoubleTag)value).getValue().doubleValue());
        continue;
      } 
      if (value instanceof StringTag) {
        mcTag.func_74778_a(key, ((StringTag)value).getValue());
        continue;
      } 
      if (value instanceof ByteArrayTag) {
        mcTag.func_74773_a(key, ((ByteArrayTag)value).getValue());
        continue;
      } 
      if (value instanceof IntArrayTag) {
        mcTag.func_74783_a(key, ((IntArrayTag)value).getValue());
        continue;
      } 
      if (value instanceof CompoundTag) {
        mcTag.func_74782_a(key, (NBTBase)convertWorldEditNBT((CompoundTag)value));
        continue;
      } 
      if (value instanceof ListTag) {
        ListTag listTag = (ListTag)value;
        NBTTagList mcList = new NBTTagList();
        for (Tag tag : listTag.getValue()) {
          if (tag instanceof CompoundTag)
            mcList.func_74742_a((NBTBase)convertWorldEditNBT((CompoundTag)tag)); 
        } 
        mcTag.func_74782_a(key, (NBTBase)mcList);
      } 
    } 
    return mcTag;
  }
  
  public static File[] listWorldEditSchematics() {
    LocalConfiguration config = WorldEdit.getInstance().getConfiguration();
    File weDir = WorldEdit.getInstance().getWorkingDirectoryFile(config.saveDir);
    if (weDir.exists() && weDir.isDirectory())
      return weDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".schematic")); 
    return null;
  }
  
  public static class ConversionResult {
    private final boolean success;
    
    private final String message;
    
    private final Schematic schematic;
    
    private final int processedBlocks;
    
    private final int totalBlocks;
    
    public boolean isSuccess() {
      return this.success;
    }
    
    public String getMessage() {
      return this.message;
    }
    
    public Schematic getSchematic() {
      return this.schematic;
    }
    
    public int getProcessedBlocks() {
      return this.processedBlocks;
    }
    
    public int getTotalBlocks() {
      return this.totalBlocks;
    }
    
    public ConversionResult(boolean success, String message, Schematic schematic, int processedBlocks, int totalBlocks) {
      this.success = success;
      this.message = message;
      this.schematic = schematic;
      this.processedBlocks = processedBlocks;
      this.totalBlocks = totalBlocks;
    }
  }
  
  public enum SchematicVersion {
    V1, V2;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\commo\\utils\SchematicUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */