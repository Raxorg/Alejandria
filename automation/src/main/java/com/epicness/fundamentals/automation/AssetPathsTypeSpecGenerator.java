package com.epicness.fundamentals.automation;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import java.util.ArrayList;
import java.util.List;

public class AssetPathsTypeSpecGenerator {

    private static final ClassName assetDescriptor = ClassName.get(AssetDescriptor.class);

    public static TypeSpec generateSpec(String classPrefix, List<AssetDescriptor<?>> descriptors) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(classPrefix + "AssetPaths");
        builder.addModifiers(PUBLIC);
        builder.addField(assetsField());
        builder.addFields(assetFields(descriptors));
        builder.addStaticBlock(initializers(descriptors));
        return builder.build();
    }

    private static FieldSpec assetsField() {
        TypeName wildcard = WildcardTypeName.subtypeOf(Object.class);
        TypeName wildcardDescriptor = ParameterizedTypeName.get(assetDescriptor, wildcard);
        ClassName list = ClassName.get(List.class);
        TypeName descriptorList = ParameterizedTypeName.get(list, wildcardDescriptor);
        return FieldSpec.builder(descriptorList, "ASSETS", STATIC, FINAL).build();
    }

    private static List<FieldSpec> assetFields(List<AssetDescriptor<?>> descriptors) {
        List<FieldSpec> assetFields = new ArrayList<>();
        descriptors.forEach(descriptor -> {
            String identifier = descriptor.file.nameWithoutExtension().replace(".", "_").toUpperCase();
            String name = identifier + "_" + Extension.getTypeName(descriptor.file.extension());
            TypeName assetClass = TypeName.get(Extension.getType(descriptor.file.extension()));
            TypeName classedDescriptor = ParameterizedTypeName.get(assetDescriptor, assetClass);
            FieldSpec.Builder builder = FieldSpec.builder(classedDescriptor, name, PUBLIC, STATIC, FINAL);
            assetFields.add(builder.build());
        });
        return assetFields;
    }

    private static CodeBlock initializers(List<AssetDescriptor<?>> descriptors) {
        CodeBlock.Builder builder = CodeBlock.builder()
            .addStatement("ASSETS = new $T<>()", ArrayList.class);
        descriptors.forEach(descriptor -> {
            String identifier = descriptor.file.nameWithoutExtension().replace(".", "_").toUpperCase();
            String name = identifier + "_" + Extension.getTypeName(descriptor.file.extension());
            String path = descriptor.fileName.replace("assets/", "");
            Class<?> assetClass = Extension.getType(descriptor.file.extension());
            builder.addStatement("ASSETS.add($L = new $T<>($S, $T.class))", name, assetDescriptor, path, assetClass);
        });
        return builder.build();
    }
}