package com.epicness.fundamentals.automation;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssetDescriptorGatherer {

    private static List<AssetDescriptor<?>> descriptors;

    public static List<AssetDescriptor<?>> gatherDescriptors(FileHandle root) {
        descriptors = new ArrayList<>();
        gatherFiles(root.child("animations"));
        gatherFiles(root.child("audios"), "mp3", "ogg", "wav");
        gatherFiles(root.child("fonts"), "png");
        gatherFiles(root.child("images"));
        gatherFiles(root.child("shaders"), "glsl");
        return descriptors;
    }

    private static void gatherFiles(FileHandle directory, String... excludedExtension) {
        for (FileHandle file : directory.list()) {
            if (file.isDirectory()) {
                gatherFiles(file, excludedExtension);
            } else {
                if (Arrays.stream(excludedExtension).toList().contains(file.extension())) continue;
                descriptors.add(new AssetDescriptor<>(file, Extension.getType(file.extension())));
            }
        }
    }
}