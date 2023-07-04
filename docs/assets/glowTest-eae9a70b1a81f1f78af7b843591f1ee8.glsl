#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying LOWP vec4 v_color;
varying vec2 v_texCoord0;
uniform sampler2D u_texture;
uniform float time;

float sdHexagram( in vec2 p, in float r )
{
    const vec4 k = vec4(-0.5,0.8660254038,0.5773502692,1.7320508076);
    p = abs(p);
    p -= 2.0*min(dot(k.xy,p),0.0)*k.xy;
    p -= 2.0*min(dot(k.yx,p),0.0)*k.yx;
    p -= vec2(clamp(p.x,r*k.z,r*k.w),r);
    return length(p)*sign(p.y);
}

vec3 palette( in float t, in vec3 a, in vec3 b, in vec3 c, in vec3 d )
{
    return a + b*cos( 6.28318*(c*t+d) );
}

void main() {
    vec3 finalColor = vec3(0.0);
    //fix coords
    vec2 uv = (v_texCoord0 - 0.5) * 2.0;

    //move everything
    uv += time * 0.01;

    //fractal pattern 4x4
    uv = fract(uv * 2.0) - 0.5;

    //shape and intensity
    float d = sdHexagram(uv, 0.2);
    d = abs(d);
    d = 0.005 / d;

    //animated color
    vec3 col = palette(
    time * 0.01,
    vec3(0.498, 0.640, 0.640),
    vec3(-0.04, 0.5, 0.5),
    vec3(1.0, 1.0, 1.0),
    vec3(0.000, 0.333, 0.667)
    );
    finalColor += d * col;

    //result
    gl_FragColor = vec4(finalColor, 1.0);
}