package com.epicness.fundamentals.constants;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.TextureCoordinates;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Constants3D {

    // Material
    public static final String MATERIAL_ID = "material";
    // Attributes
    public static final long LIGHTED_TEXTURED_ATTRIBUTES = Position | Normal | TextureCoordinates;
    public static final long LIGHTLESS_TEXTURED_ATTRIBUTES = Position | TextureCoordinates;
    public static final long LIGHTED_TEXTURELESS_ATTRIBUTES = Position | Normal;
    public static final long LIGHTLESS_TEXTURELESS_ATTRIBUTES = Position;
    // Cube
    public static final Vector3 STARTING_CUBE_MIN = new Vector3(-0.5f, -0.5f, -0.5f);
    public static final Vector3 STARTING_CUBE_MAX = new Vector3(0.5f, 0.5f, 0.5f);
    public static final BoundingBox STARTING_CUBE_BOUNDS = new BoundingBox(STARTING_CUBE_MIN, STARTING_CUBE_MAX);
}