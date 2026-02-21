package fr.paladium.palaforgeutils.lib.resource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class FileResourcePack implements IResourcePack {
  private final Set<String> domainsSet = (Set<String>)ImmutableSet.of("file", "files");
  
  private final Cache<String, File> fileCache = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  public static void register() {
    FileResourcePack fileResourcePack = new FileResourcePack();
    List<IResourcePack> defaultResourcePacks = (List<IResourcePack>)ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.func_71410_x(), new String[] { "defaultResourcePacks", "field_110449_ao" });
    defaultResourcePacks.add(fileResourcePack);
  }
  
  @NonNull
  public InputStream func_110590_a(@NonNull ResourceLocation resource) throws IOException {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    InputStream inputstream = getResourceStream(resource);
    if (inputstream != null)
      return inputstream; 
    throw new FileNotFoundException(resource.func_110623_a());
  }
  
  private InputStream getResourceStream(@NonNull ResourceLocation resource) throws IOException {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    File file = getResourceFile(resource);
    if (file.exists())
      return new FileInputStream(file); 
    return null;
  }
  
  public boolean func_110589_b(@NonNull ResourceLocation resource) {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    return getResourceFile(resource).exists();
  }
  
  @NonNull
  public Set<String> func_110587_b() {
    return this.domainsSet;
  }
  
  public IMetadataSection func_135058_a(IMetadataSerializer metadataSerializer, String data) throws IOException {
    return null;
  }
  
  public BufferedImage func_110586_a() throws IOException {
    return ImageIO.read(DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.png")).func_110623_a()));
  }
  
  public String func_130077_b() {
    return "File";
  }
  
  @NonNull
  private File getResourceFile(@NonNull ResourceLocation resource) {
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    if (resource instanceof FileResourceLocation)
      return ((FileResourceLocation)resource).getFile(); 
    try {
      return (File)this.fileCache.get(resource.func_110623_a(), () -> new File(resource.func_110623_a()));
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\resource\FileResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */