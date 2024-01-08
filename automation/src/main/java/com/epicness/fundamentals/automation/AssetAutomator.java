package com.epicness.fundamentals.automation;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.epicness.fundamentals.automation.TypeSpecWithStaticImports.StaticImport;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.JavaFile.Builder;
import com.squareup.javapoet.TypeSpec;

import java.nio.file.Paths;
import java.util.List;

public class AssetAutomator {

    private static String assetsPackage, assetsPath;

    public static void automate(String classPrefix, String... packagePath) {
        String packageDotPath = String.join(".", packagePath);
        String packageSlashPath = String.join("/", packagePath);

        assetsPackage = "com.epicness." + packageDotPath + ".assets";
        assetsPath = "core/src/main/java/com/epicness/" + packageSlashPath + "/assets/";
        FileHandle rootHandle = new FileHandle(Paths.get("assets/" + packageSlashPath).toFile());

        List<AssetDescriptor<?>> assetDescriptors = AssetDescriptorGatherer.gatherDescriptors(rootHandle);

        generateClass(AssetPathsTypeSpecGenerator.generateSpec(classPrefix, assetDescriptors));
        generateClass(AssetsTypeSpecGenerator.generateSpec(assetsPackage, classPrefix, assetDescriptors));
    }

    private static void generateClass(TypeSpecWithStaticImports typeSpec) {
        Builder builder = JavaFile.builder(assetsPackage, typeSpec.spec).indent("    ");
        builder.skipJavaLangImports(true);
        for (StaticImport staticImport : typeSpec.staticImports) {
            builder.addStaticImport(staticImport.className, staticImport.names);
        }
        JavaFile javaFile = builder.build();

        FileHandle target = new FileHandle(Paths.get(assetsPath + typeSpec.spec.name + ".java").toFile());
        String fileString = javaFile.toString().stripTrailing();
        target.writeString(fileString, false);
    }

    private static void generateClass(TypeSpec typeSpec) {
        generateClass(new TypeSpecWithStaticImports(typeSpec));
    }
}