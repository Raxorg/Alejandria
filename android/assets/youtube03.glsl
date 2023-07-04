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

const vec3 a = vec3(0.5, 0.5, 0.5);
const vec3 b = vec3(0.5, 0.5, 0.5);
const vec3 c = vec3(1.0, 1.0, 1.0);
const vec3 d = vec3(0.263, 0.416, 0.557);

vec3 palette(in float t, in vec3 a, in vec3 b, in vec3 c, in vec3 d) {
    return a + b * cos(6.28318 * (c * t + d));
}

void main() {
    vec2 pos = gl_FragCoord.xy;
    vec2 reso = u_resolution;
    vec2 uv = (pos * 2.0 - u_resolution) / u_resolution.y;

    float distance = length(uv);
    vec3 color = palette(distance + time, a, b, c, d);

    distance = sin(distance * 8.0 + time) / 8.0;
    distance = abs(distance);
    distance = 0.02 / distance;

    color *= distance;
    // Result
    gl_FragColor = vec4(color, 1.0);
}