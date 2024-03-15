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

float sdSphere(vec3 p, float s) {
    return length(p) - s;
}

float sdBox(vec3 p, vec3 b) {
    vec3 q = abs(p) - b;
    return length(max(q, 0.0)) + min(max(q.x, max(q.y, q.z)), 0.0);
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
    vec3 spherePos = vec3(sin(time) * 3.0, 0.0, 0.0);   // sphere position
    float sphere = sdSphere(p - spherePos, 1.0);        // sphere SDF

    vec3 boxP = p;                          // input copy
    boxP.xy *= rot2D(time);                 // rotate around the z axis
    float box = sdBox(boxP, vec3(0.75));    // cube SDF

    float ground = p.y + 0.75;              // ground

    // Closest distance to the scene
    return smin(ground, smin(sphere, box, 2.0), 1.0);
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

    // Raymarching
    for (float i = 0.0; i < 80.0; i++) {
        vec3 p = ro + rd * t;   // position along the ray
        float d = map(p);       // current distance to the scene
        t += d;                 // "march" the ray
        if (d < 0.001 || t > 100.0) break;  // early stop if close enough or too far
    }

    // Coloring
    col = vec3(t * 0.2);        // color based on distance

    // Result
    gl_FragColor = vec4(col, 1.0);
}