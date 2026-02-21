package fr.paladium.palashop.provider.cosmetic.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.java.clazz.ClassHelper;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.utils.cache.CosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketGetCosmeticData extends ForgePacket {
  public BBPacketGetCosmeticData() {}
  
  private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(0, 5, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
  
  private String factory;
  
  private String id;
  
  private ByteBuf data;
  
  @SideOnly(Side.SERVER)
  public BBPacketGetCosmeticData(ByteBuf data) {
    this.data = data;
  }
  
  @SideOnly(Side.CLIENT)
  public BBPacketGetCosmeticData(String factory, String id) {
    this.factory = factory;
    this.id = id;
  }
  
  public void read(ByteBuf buf) {
    super.read(buf);
    if (buf.readBoolean()) {
      this.data = buf.copy();
    } else {
      this.factory = PacketSerialUtils.readString(buf);
      this.id = PacketSerialUtils.readString(buf);
    } 
  }
  
  public void write(ByteBuf buf) {
    super.write(buf);
    buf.writeBoolean((this.data != null));
    if (this.data != null) {
      buf.writeBytes(this.data);
    } else {
      PacketSerialUtils.writeString(buf, this.factory);
      PacketSerialUtils.writeString(buf, this.id);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.data == null || this.data.readableBytes() <= 0)
      return; 
    EXECUTOR_SERVICE.submit(() -> {
          String id = PacketSerialUtils.readString(this.data);
          try {
            String className = PacketSerialUtils.readString(this.data);
            String json = decode(this.data);
            ICosmetic cosmetic = (ICosmetic)PacketSerialUtils.getGson().fromJson(json, Class.forName(className));
            FMLClientScheduler.getInstance().add(new Runnable[] { () });
          } catch (Exception|NoClassDefFoundError e) {
            e.printStackTrace();
            FMLClientScheduler.getInstance().add(new Runnable[] { () });
          } 
        });
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (this.factory == null || this.id == null) {
      (new BBPacketGetCosmeticData(Unpooled.buffer())).send(player);
      return;
    } 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(this.factory);
    if (!optionalFactory.isPresent()) {
      (new BBPacketGetCosmeticData(Unpooled.buffer())).send(player);
      return;
    } 
    Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(this.id);
    if (!optionalCosmetic.isPresent()) {
      (new BBPacketGetCosmeticData(Unpooled.buffer())).send(player);
      return;
    } 
    EXECUTOR_SERVICE.submit(() -> {
          ByteBuf cosmeticData = Unpooled.buffer();
          ICosmetic cosmetic = optionalCosmetic.get();
          String json = PacketSerialUtils.getGson().toJson(cosmetic);
          PacketSerialUtils.writeString(cosmeticData, this.id);
          PacketSerialUtils.writeString(cosmeticData, ClassHelper.of(cosmetic.getClass()).getName());
          encode(cosmeticData, json);
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        });
  }
  
  private void encode(@NonNull ByteBuf buf, @NonNull String data) {
    if (buf == null)
      throw new NullPointerException("buf is marked non-null but is null"); 
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
      gzipOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
      gzipOutputStream.close();
      byte[] compressed = outputStream.toByteArray();
      outputStream.close();
      PacketSerialUtils.writeInt(buf, compressed.length);
      for (int j = 0; j < compressed.length; j++)
        PacketSerialUtils.writeByte(buf, compressed[j]); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  private String decode(@NonNull ByteBuf buf) {
    if (buf == null)
      throw new NullPointerException("buf is marked non-null but is null"); 
    int nbBytesModel = PacketSerialUtils.readInt(buf);
    byte[] toDecode = new byte[nbBytesModel];
    for (int j = 0; j < toDecode.length; j++)
      toDecode[j] = PacketSerialUtils.readByte(buf); 
    try {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(toDecode);
      GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[toDecode.length];
      int len;
      while ((len = gzipInputStream.read(buffer)) > 0)
        outputStream.write(buffer, 0, len); 
      gzipInputStream.close();
      outputStream.close();
      return outputStream.toString("UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\BBPacketGetCosmeticData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */