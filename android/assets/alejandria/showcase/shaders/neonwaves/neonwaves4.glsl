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

// Made following Kishimisu's introduction to shader art coding tutorial
void main() {
    vec2 pos = gl_FragCoord.xy;
    vec2 reso = u_resolution;

    vec2 uv = (pos * 2.0 - u_resolution) / u_resolution.y;
    vec2 originalUV = uv;
    vec3 finalColor = vec3(0.0);

    for (float i = 0.0; i < 3.0; i++) {
        uv = fract(uv * 2.0) - 0.5;

        float distance = length(uv);
        float t = length(originalUV) + time;
        vec3 color = palette(t, a, b, c, d);

        distance = sin(distance * 8.0 + time) / 8.0;
        distance = abs(distance);
        distance = 0.02 / distance;

        finalColor += color * distance;
    }
    // Result
    gl_FragColor = vec4(finalColor, 1.0);
}