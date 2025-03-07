# [Alejandria](https://raxorg.github.io/Alejandria/)

This desktop+web app showcases a variety of libGDX features/capabilities/techniques/aspects with code organized into bite-sized
"modules", each consisting of its logic and graphic parts

## Current modules

### Animations
- Sprite Animation
- Sprite Rotation Animation

### Audio
- Sound Pitch

### Bullets
- Bullet Spawning

### Collisions
- Pixel Perfect Collision

### Cursor
- Beam Aiming
- Point At Cursor

### Fun
- Beeping Balls
- Falling Sand
- Lasers
- Reactive Grid

### Grids
- Cross Chunk Selection
- Hexagon Radial Selection

### Kinematics
- Forward Kinematics
- Inverse Kinematics

### Lights
- Simple Lights

### Masking
- Alpha Masking
- Clipping
- Layered Masking
- Shape Drawer Masking
- Shape Renderer Masking

### Optimization
- Quad Tree

### Pathfinding
- A Star (A*)
- Alternative A*

### Patterns
- Cantor Gasket
- Dragon Curve
- Phyllotaxis
- Spiral
- Spirograph

### Physics
- Ball Physics

### Procedural
- Lightning
- Pixmap Manipulation
- Procedural Square

### Rendering
- Frame Buffer Example
- Manual Screen Clear
- Orthographic Example
- Shape Rendering

### Rendering 3D
- Decal Visualization
- Textured Cube Visualization

### Shaders
- Invert Shader
- Raymarching Shader (based on [kishimisu's tutorial](https://www.youtube.com/watch?v=khblXafu7iA))
- Shake Shader
- Vignette Shader

### UI
- Drag And Drop
- Text Manipulation

### Viewports
- Advanced Split Screen
- Wide Viewport

The project also features standalone demos you can run directly from the 
[StandaloneLauncher](/lwjgl3/src/main/java/com/epicness/alejandria/lwjgl3/Lwjgl3Launcher.java) in the lwjgl3 module, check the
[standalone package](/core/src/main/java/com/epicness/standalone) in the core module

## Platform specific modules

### HTML (Web only)
- Alert

### libGDX in a fragment (Android only)
- Check the android module, create a run configuration that uses the [FragmentLauncher](/android/src/main/java/com/epicness/alejandria/android/FragmentLauncher.java)
