package fr.paladium.palamod.client.render.shader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class ShaderFactory {
  private HashMap<String, Shader> table = new HashMap<>();
  
  ArrayList<String> shaderFileNames;
  
  public void pushShaderFileName(String shaderFileName) {}
  
  public Shader getShader(String shaderName, ResourceLocation[] files) throws IOException, Exception {
    Shader shader = this.table.get(shaderName);
    if (shader != null)
      return shader; 
    ArrayList<ShaderPart> shaderPrograms = new ArrayList<>();
    for (ResourceLocation shaderFileName : files) {
      System.out.print(" - Loading \"" + shaderFileName + "\" ");
      try {
        ByteBuffer shaderbuf;
        String type = shaderFileName.func_110623_a().substring(shaderFileName.func_110623_a().indexOf("."));
        try (InputStream is = Minecraft.func_71410_x().func_110442_L().func_110536_a(shaderFileName).func_110527_b()) {
          byte[] bytes = IOUtils.toByteArray(is);
          shaderbuf = ByteBuffer.allocateDirect(bytes.length);
          shaderbuf.put(bytes);
          shaderbuf.position(0);
        } catch (IOException e) {}
        if (type.compareTo(".vert") == 0) {
          shaderPrograms.add(new VertexShader(shaderbuf));
        } else if (type.compareTo(".frag") == 0) {
          shaderPrograms.add(new FragmentShader(shaderbuf));
        } else {
          System.out.println("Shader type is not recognised: " + type);
        } 
      } catch (Exception e) {
        System.out.println("Fail reading shading code: " + e.getMessage());
        return null;
      } 
    } 
    shader = new Shader(shaderPrograms, shaderName);
    this.table.put(shaderName, shader);
    return shader;
  }
  
  public Shader getShader(String shaderName) throws IOException {
    return this.table.get(shaderName);
  }
  
  public void remove(Shader ref) {
    if (this.table.containsValue(ref))
      this.table.remove(ref.getName()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\render\shader\ShaderFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */