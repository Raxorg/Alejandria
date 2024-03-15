varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform sampler2D u_noise;
uniform float u_time;

void main() {
    vec2 frag = vec2(gl_FragCoord.x / 1280.0, gl_FragCoord.y / 720.0);
    vec2 displacedTexCoord = v_texCoords + vec2(
    texture2D(u_noise, frag + vec2(sin(u_time * 25.0) / 20.0, 0)).z - 0.5,
    texture2D(u_noise, frag + vec2(0, cos(u_time * 25.0) / 20.0)).z - 0.5
    ) / 250.0;
    gl_FragColor = v_color * texture2D(u_texture, displacedTexCoord);
}