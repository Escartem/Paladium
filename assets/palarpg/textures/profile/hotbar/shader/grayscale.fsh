#version 120

uniform float progress;
uniform sampler2D texture;

varying vec2 vTexCoord;
varying vec2 vPosition;
varying vec4 vColor;

void main() {
    vec4 color = texture2D(texture, vTexCoord);
    float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    vec3 mixedColor = mix(color.rgb, vec3(gray), progress);
    gl_FragColor = vec4(mixedColor, color.a) * vColor;
}