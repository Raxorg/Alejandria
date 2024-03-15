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

// Made following Kishimisu's introduction to shader art coding tutorial
void main() {
    vec2 pos = gl_FragCoord.xy;
    vec2 reso = u_resolution;
    vec2 uv = (pos * 2.0 - reso) / reso.y;

    float d = length(uv);
    d = sin(d * 8.0 + time) / 8.0;
    d = abs(d);
    d = 0.02 / d;

    vec3 color = vec3(3.0, 2.0, 1.0);
    color *= d;

    // Result
    gl_FragColor = vec4(color, 1.0);
}