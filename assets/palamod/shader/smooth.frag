#version 330 core
out vec4 FragColor;
in vec2 TexCoords;
uniform sampler2D tex;


void main() {
	float someFactor = 1.0; // <-- value can be played around with a bit
	float mask = texture( tex, TexCoords).r;
	float stepWidth = someFactor * length(vec2(dFdx(mask), dFdy(mask)));
	vec4 res = vec4(1.0,1.0,1.0, 1.0);
	float value = 1.0 - smoothstep(-stepWidth/2.0, stepWidth/2.0, mask);

	if(value == 0.0)
	{
	    res = vec4(0.0, 0.0, 0.0, 0.0);
	}
	else
	{
	    res = vec4(1.0, 1.0, 1.0, value);
	}

	FragColor = res;
}
