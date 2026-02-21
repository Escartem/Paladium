package fr.paladium.palamod.client.render.shader;

import java.nio.ByteBuffer;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL20;

public abstract class ShaderPart {
  private int shaderID = 0;
  
  protected ShaderPart(ByteBuffer sourceCode, int shaderType) throws Exception {
    this.shaderID = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
    if (this.shaderID == 0)
      throw new Exception("Could not create shader handle - code: " + sourceCode); 
    ARBShaderObjects.glShaderSourceARB(this.shaderID, sourceCode);
    ARBShaderObjects.glCompileShaderARB(this.shaderID);
    int logLength = ARBShaderObjects.glGetObjectParameteriARB(this.shaderID, 35716);
    String log = "";
    if (logLength != 0)
      log = ARBShaderObjects.glGetInfoLogARB(this.shaderID, logLength); 
    if (GL20.glGetShader(this.shaderID, 35713) != 1)
      throw new Exception("Program did not compile. " + log); 
    if (logLength != 0)
      System.out.print("\nCompile log:\n " + log + "\n"); 
  }
  
  int shaderID() {
    return this.shaderID;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\render\shader\ShaderPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */