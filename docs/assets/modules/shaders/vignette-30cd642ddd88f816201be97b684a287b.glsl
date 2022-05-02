#version 100

#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord0;

uniform vec2 u_resolution;
uniform sampler2D u_sampler2D;

void main() {
    vec4 color = texture2D(u_sampler2D, v_texCoord0) * v_color;
    vec2 relativePosition = (gl_FragCoord.xy / u_resolution - 0.5) * 2.0;
    relativePosition.x *= u_resolution.x / u_resolution.y;
    float len = length(relativePosition);
    float vignette = smoothstep(1.0, 0.8, len);
    color.rgb = mix(color.rgb, color.rgb * vignette, 0.7);
    gl_FragColor = color;
}