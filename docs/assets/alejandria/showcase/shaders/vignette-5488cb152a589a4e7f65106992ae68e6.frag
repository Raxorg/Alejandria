#version 100

#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;

uniform vec2 u_resolution;
uniform vec2 u_position;
uniform sampler2D u_sampler2D;

void main() {
    vec2 pos = gl_FragCoord.xy - u_position;
    vec2 reso = u_resolution;
    vec2 uv = (pos * 2.0 - reso) / reso.y;

    vec4 color = texture2D(u_sampler2D, v_texCoord0) * v_color;
    float len = length(uv);
    float vignette = smoothstep(1.0, 0.8, len);
    color.rgb = mix(color.rgb, color.rgb * vignette, 0.7);
    gl_FragColor = color;
}