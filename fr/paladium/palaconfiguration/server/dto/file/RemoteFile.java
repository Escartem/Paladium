package fr.paladium.palaconfiguration.server.dto.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.paladium.palaconfiguration.server.ServerProxy;
import fr.paladium.palaconfiguration.server.dto.file.download.DownloadedRemoteFile;
import fr.paladium.palaconfiguration.server.dto.file.exception.RemoteFileException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import okhttp3.ResponseBody;

public final class RemoteFile extends RemoteObject {
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private static final File DEFAULT_FILE = new File("assets/paladium/");
  
  private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(0, 10, 30L, TimeUnit.SECONDS, new SynchronousQueue<>());
  
  private final String parentPath;
  
  public String getParentPath() {
    return this.parentPath;
  }
  
  public RemoteFile(String path, String name, String sha1, long size, boolean directory, String parentPath) {
    super(path, name, sha1, size, directory);
    this.parentPath = parentPath;
  }
  
  public boolean hasParent() {
    return (this.parentPath != null);
  }
  
  @NonNull
  public CompletableFuture<File> download() {
    return download(4096);
  }
  
  @NonNull
  public CompletableFuture<File> download(int bufferSize) {
    if (!DEFAULT_FILE.exists())
      DEFAULT_FILE.mkdirs(); 
    return download(new File(DEFAULT_FILE, getName()), bufferSize);
  }
  
  @NonNull
  public CompletableFuture<File> download(@NonNull File file) {
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    return download(file, 4096);
  }
  
  @NonNull
  public CompletableFuture<File> download(@NonNull File file, int bufferSize) {
    if (file == null)
      throw new NullPointerException("file is marked non-null but is null"); 
    return CompletableFuture.supplyAsync(() -> {
          try {
            InputStream inputStream = ((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$1101(this)).execute().body()).byteStream();
            if (inputStream == null)
              throw new RemoteFileException(this, "Unable to download file: " + access$1201(this) + " (inputStream is null)"); 
            File targetFile = file;
            if (!targetFile.exists())
              targetFile.createNewFile(); 
            byte[] buffer = new byte[bufferSize];
            try (OutputStream outputStream = new FileOutputStream(targetFile)) {
              int bytesRead;
              while ((bytesRead = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, bytesRead); 
            } 
            return targetFile;
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to download file: " + access$1301(this), e);
          } 
        }EXECUTOR);
  }
  
  @NonNull
  public CompletableFuture<DownloadedRemoteFile> downloadTemp() {
    return downloadTemp(4096);
  }
  
  @NonNull
  public CompletableFuture<DownloadedRemoteFile> downloadTemp(int bufferSize) {
    return CompletableFuture.supplyAsync(() -> {
          try {
            DownloadedRemoteFile downloadedRemoteFile = DownloadedRemoteFile.create(this);
            InputStream inputStream = ((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$801(this)).execute().body()).byteStream();
            if (inputStream == null)
              throw new RemoteFileException(this, "Unable to download file: " + access$901(this) + " (inputStream is null)"); 
            byte[] buffer = new byte[bufferSize];
            try (OutputStream outputStream = new FileOutputStream(downloadedRemoteFile.getFile())) {
              int bytesRead;
              while ((bytesRead = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, bytesRead); 
            } 
            return downloadedRemoteFile;
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to download file: " + access$1001(this), e);
          } 
        }EXECUTOR);
  }
  
  @NonNull
  public CompletableFuture<byte[]> getContent() {
    return (CompletableFuture)CompletableFuture.supplyAsync(() -> {
          try {
            return ((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$601(this)).execute().body()).bytes();
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to get content file: " + access$701(this), e);
          } 
        }EXECUTOR);
  }
  
  @NonNull
  public CompletableFuture<InputStream> getContentStream() {
    return CompletableFuture.supplyAsync(() -> {
          try {
            return ((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$401(this)).execute().body()).byteStream();
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to get content file: " + access$501(this), e);
          } 
        }EXECUTOR);
  }
  
  @NonNull
  public CompletableFuture<String> getContentString() {
    return CompletableFuture.supplyAsync(() -> {
          try {
            return ((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$201(this)).execute().body()).string();
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to get content file: " + access$301(this), e);
          } 
        }EXECUTOR);
  }
  
  @NonNull
  public <T> CompletableFuture<T> getContentObject(@NonNull Class<T> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    return getContentObject(clazz, GSON);
  }
  
  @NonNull
  public <T> CompletableFuture<T> getContentObject(@NonNull Class<T> clazz, @NonNull Gson gson) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (gson == null)
      throw new NullPointerException("gson is marked non-null but is null"); 
    return CompletableFuture.supplyAsync(() -> {
          try {
            return gson.fromJson(((ResponseBody)ServerProxy.getInstance().getFileApi().downloadFile(ServerProxy.getInstance().getConfig().getEnvironment(), access$001(this)).execute().body()).string(), clazz);
          } catch (Exception e) {
            throw new RemoteFileException(this, "Unable to get content file: " + access$101(this), e);
          } 
        }EXECUTOR);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\file\RemoteFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */