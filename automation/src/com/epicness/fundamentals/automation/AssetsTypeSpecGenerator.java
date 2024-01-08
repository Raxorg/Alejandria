package com.epicness.fundamentals.automation;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.epicness.fundamentals.assets.Assets;
import com.epicness.fundamentals.automation.TypeSpecWithStaticImports.StaticImport;
import com.epicness.fundamentals.utils.StringUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.stream.Collectors;

public class AssetsTypeSpecGenerator {

    public static TypeSpecWithStaticImports generateSpec(String assetsPackage, String classPrefix,
                                                         List<AssetDescriptor<?>> assetDescriptors) {
        ClassName assetPaths = ClassName.get(assetsPackage, classPrefix + "AssetPaths");
        StaticImport staticImport = new StaticImport(assetPaths, "*");
        return new TypeSpecWithStaticImports(assetsSpec(classPrefix, assetDescriptors), staticImport);
    }

    private static TypeSpec assetsSpec(String classPrefix, List<AssetDescriptor<?>> descriptors) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(classPrefix + "Assets")
            .addModifiers(PUBLIC)
            .superclass(Assets.class);

        builder.addMethod(constructor());
        builder.addFields(fieldSpecs(descriptors));
        builder.addMethod(initializeAssetsSpec(descriptors));
        builder.addMethods(getterSpecs(descriptors));
        return builder.build();
    }

    private static MethodSpec constructor() {
        return MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addStatement("super(ASSETS)")
            .build();
    }

    private static List<FieldSpec> fieldSpecs(List<AssetDescriptor<?>> descriptors) {
        return descriptors.stream()
            .map(descriptor -> {
                String name = descriptor.file.nameWithoutExtension().replace(".", "_");
                return FieldSpec.builder(descriptor.type, name, PRIVATE).build();
            })
            .collect(Collectors.toList());
    }

    private static MethodSpec initializeAssetsSpec(List<AssetDescriptor<?>> descriptors) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("initializeAssets")
            .addAnnotation(Override.class)
            .addModifiers(PUBLIC);

        descriptors.forEach(descriptor -> {
            String name = descriptor.file.nameWithoutExtension().replace(".", "_");
            String descriptorConstant = name.toUpperCase() + "_" + Extension.getTypeName(descriptor.file.extension());
            builder.addStatement("$L = get($L)", name, descriptorConstant);
        });
        return builder.build();
    }

    private static List<MethodSpec> getterSpecs(List<AssetDescriptor<?>> descriptors) {
        return descriptors.stream()
            .map(descriptor -> descriptor.file)
            .map(file -> {
                String name = file.nameWithoutExtension().replace(".", "_");
                return MethodSpec.methodBuilder("get" + StringUtils.capitalizeFirst(name))
                    .addModifiers(PUBLIC)
                    .returns(Extension.getType(file.extension()))
                    .addStatement("return $L", name)
                    .build();
            })
            .collect(Collectors.toList());
    }
}