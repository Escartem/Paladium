#version 120

varying vec2 TexCoord;

void main() {
	gl_Position = ftransform();
	TexCoord = vec2(gl_MultiTexCoord0);
}
