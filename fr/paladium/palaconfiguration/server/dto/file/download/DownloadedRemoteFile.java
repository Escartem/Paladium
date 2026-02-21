package fr.paladium.palaconfiguration.server.dto.file.download;

import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import lombok.NonNull;
import org.apache.commons.io.FileUtils;

public class DownloadedRemoteFile implements Closeable {
  private final RemoteFile remoteFile;
  
  private final File file;
  
  public DownloadedRemoteFile(RemoteFile remoteFile, File file) {
    this.remoteFile = remoteFile;
    this.file = file;
  }
  
  public RemoteFile getRemoteFile() {
    return this.remoteFile;
  }
  
  public File getFile() {
    return this.file;
  }
  
  @NonNull
  public static DownloadedRemoteFile create(@NonNull RemoteFile remoteFile) throws IOException {
    if (remoteFile == null)
      throw new NullPointerException("remoteFile is marked non-null but is null"); 
    return new DownloadedRemoteFile(remoteFile, File.createTempFile("paladium_remote_file", ".palafile"));
  }
  
  public void close() throws IOException {
    if (this.file == null || !this.file.exists())
      return; 
    if (!this.file.delete())
      FileUtils.forceDelete(this.file); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\file\download\DownloadedRemoteFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */