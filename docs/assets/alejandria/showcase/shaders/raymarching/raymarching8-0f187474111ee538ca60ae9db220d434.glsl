#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying LOWP vec4 v_color;
varying vec2 v_texCoord0;

uniform vec2 u_resolution;
uniform vec2 u_position;
uniform float time;

vec3 palette(float t) {
    vec3 a = vec3(0.5, 0.5, 0.5);
    vec3 b = vec3(0.5, 0.5, 0.5);
    vec3 c = vec3(1.0, 1.0, 1.0);
    vec3 d = vec3(0.263, 0.416, 0.557);
    return a + b * cos(6.28318 * (c * t + d));
}

float sdSphere(vec3 p, float s) {
    return length(p) - s;
}

float sdBox(vec3 p, vec3 b) {
    vec3 q = abs(p) - b;
    return length(max(q, 0.0)) + min(max(q.x, max(q.y, q.z)), 0.0);
}

float sdOctahedron(vec3 p, float s) {
    p = abs(p);
    return (p.x + p.y + p.z - s) * 0.57735027;
}

mat2 rot2D(float angle) {
    float s = sin(angle);
    float c = cos(angle);
    return mat2(c, -s, s, c);
}

// Smooth minimum
float smin(float a, float b, float k) {
    float h = max(k - abs(a - b), 0.0) / k;
    return min(a, b) - h * h * h * k * (1.0 / 6.0);
}

// Distance to the scene
float map(vec3 p) {
    p.z += time * 0.4;                  // forward movement

    // Space repetition
    p.xy = fract(p.xy) - 0.5;           // spacing: 1
    p.z = mod(p.z, 0.25) - 0.125;       // spacing: 0.25

    float box = sdOctahedron(p, 0.15);  // octahedron SDF

    // Closest distance to the scene
    return box;
}

// Made following Kishimisu's introduction to raymarching tutorial
void main() {
    vec2 pos = gl_FragCoord.xy - u_position;
    vec2 reso = u_resolution;
    vec2 uv = (pos * 2.0 - reso) / reso.y;

    // Initialization
    vec3 ro = vec3(0.0, 0.0, -3.0);     // ray origin
    vec3 rd = normalize(vec3(uv, 1.0)); // ray direction
    vec3 col = vec3(0.0);               // final pixel color
    float t = 0.0;                      // total distance travelled

    // Circular motion simulating mouse movement
    vec2 m = vec2(cos(time * 0.2), sin(time * 0.2));

    // Raymarching
    float i;
    for (i = 0.0; i < 80.0; i++) {
        vec3 p = ro + rd * t;                       // position along the ray
        p.xy *= rot2D(t * 0.2 * m.x);               // rotate ray around z axis
        p.y += sin(t * (m.y + 1.0) * 0.5) * 0.35;   // wiggle ray
        float d = map(p);                           // current distance to the scene
        t += d;                                     // "march" the ray
        if (d < 0.001 || t > 100.0) break;          // early stop if close enough or too far
    }

    // Coloring
    col = palette(t * 0.04 + i * 0.005);    // color based on distance

    // Result
    gl_FragColor = vec4(col, 1.0);
}