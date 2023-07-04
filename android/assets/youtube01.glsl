#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying LOWP vec4 v_color;
varying vec2 v_texCoord0;

uniform sampler2D u_texture;
uniform vec2 u_resolution;
uniform float time;

void main() {
    vec2 pos = gl_FragCoord;
    vec2 reso = u_resolution;
    vec2 uv = (pos * 2.0 - u_resolution) / u_resolution.y;

    float d = length(uv);
    d = sin(d * 8.0 + time) / 8.0;
    d = abs(d);
    d = smoothstep(0.0, 0.1, d);

    // Result
    gl_FragColor = vec4(d, d, d, 1.0);
}