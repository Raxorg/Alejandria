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

// Distance to the scene
float map(vec3 p) {
    return length(p) - 1.0;     // distance to a sphere of radius 1
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