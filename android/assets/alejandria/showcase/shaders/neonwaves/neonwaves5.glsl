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
uniform vec2 u_position;
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
    vec2 pos = gl_FragCoord.xy - u_position;
    vec2 reso = u_resolution;

    vec2 uv = (pos * 2.0 - u_resolution) / u_resolution.y;
    vec2 originalUV = uv;
    vec3 finalColor = vec3(0.0);

    const float iterations = 3.0;
    for (float i = 0.0; i < iterations; i++) {
        uv = fract(uv * 1.5) - 0.5;

        float dist = length(uv) * exp(-length(originalUV));
        float t = length(originalUV) + i * iterations / 10.0 + time / iterations;
        vec3 color = palette(t, a, b, c, d);

        dist = sin(dist * 8.0 + time) / 8.0;
        dist = abs(dist);
        dist = pow(0.01 / dist, 1.35);

        finalColor += color * dist;
    }
    // Result
    gl_FragColor = vec4(finalColor, 1.0);
}