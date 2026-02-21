#version 120


varying vec2 TexCoord;

uniform sampler2D tex;
uniform float u_colorFactor;

void main()
{
	vec4 sample =  texture2D(tex, TexCoord);
	float grey = 0.299 * sample.r + 0.587 * sample.g + 0.114 * sample.b;
	gl_FragColor = vec4(sample.r * u_colorFactor + grey * (1.0 - u_colorFactor),
			sample.g * u_colorFactor + grey * (1.0 - u_colorFactor),
			sample.b * u_colorFactor + grey * (1.0 - u_colorFactor),
			sample.a);
}
