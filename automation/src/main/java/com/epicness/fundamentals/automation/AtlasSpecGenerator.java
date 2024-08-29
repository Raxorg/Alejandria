package com.epicness.fundamentals.automation;

import static com.epicness.fundamentals.automation.Extension.ANIM;
import static com.epicness.fundamentals.automation.Extension.PNG;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;

import com.badlogic.gdx.files.FileHandle;
import com.epicness.fundamentals.utils.StringUtils;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AtlasSpecGenerator {

    public static List<FieldSpec> generateFieldSpecs(FileHandle file) {
        List<FieldSpec> fieldSpecs = new ArrayList<>();

        fieldSpecs.add(FieldSpec.builder(PNG.type, file.nameWithoutExtension() + "Atlas", PRIVATE).build());

        try (BufferedReader reader = file.reader(128);) {
            String line;
            Set<String> animationNames = new HashSet<>();
            do {
                line = reader.readLine();
            } while (!line.startsWith("repeat: none"));

            while ((line = reader.readLine()) != null) {
                reader.readLine();  // rotate
                reader.readLine();  // xy
                reader.readLine();  // size
                reader.readLine();  // orig
                reader.readLine();  // offset

                String indexLine = reader.readLine();

                if (!indexLine.equals("  index: -1")) {
                    if (!animationNames.contains(line)) {
                        fieldSpecs.add(FieldSpec.builder(ANIM.type, line + "Frames", PRIVATE).build());
                        animationNames.add(line);
                    }
                    int index = Integer.parseInt(indexLine.split(":")[1].trim());
                    fieldSpecs.add(FieldSpec.builder(PNG.type, line + "_" + index, PRIVATE).build());
                    continue;
                }
                fieldSpecs.add(FieldSpec.builder(PNG.type, line, PRIVATE).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fieldSpecs;
    }

    public static List<CodeBlock> generateInitializerStatements(FileHandle file) {
        List<CodeBlock> initializerStatements = new ArrayList<>();

        String atlasName = file.nameWithoutExtension() + "Atlas";
        String constant = file.nameWithoutExtension().toUpperCase() + "_ATLAS";
        initializerStatements.add(CodeBlock.of("$L = new Sprite(get($L).getTextures().first())", atlasName, constant));

        try (BufferedReader reader = file.reader(128);) {
            String line;
            Set<String> animationNames = new HashSet<>();
            do {
                line = reader.readLine();
            } while (!line.startsWith("repeat: none"));

            while ((line = reader.readLine()) != null) {
                reader.readLine();  // rotate
                reader.readLine();  // xy
                reader.readLine();  // size
                reader.readLine();  // orig
                reader.readLine();  // offset

                String indexLine = reader.readLine();
                String name = line.replace(".", "_");
                String atlasConstant = file.name().replace(".", "_").toUpperCase();

                if (!indexLine.equals("  index: -1")) {
                    if (!animationNames.contains(line)) {
                        initializerStatements.add(CodeBlock.of(
                            "$LFrames = get($L).createSprites($S).toArray()",
                            name, atlasConstant, name
                        ));
                        animationNames.add(line);
                    }
                    int index = Integer.parseInt(indexLine.split(":")[1].trim());
                    initializerStatements.add(CodeBlock.of(
                        "$L_$L = get($L).createSprite($S, $L)",
                        name, index, atlasConstant, name, index
                    ));
                    continue;
                }
                initializerStatements.add(CodeBlock.of("$L = get($L).createSprite($S)", name, atlasConstant, name));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return initializerStatements;
    }

    public static List<MethodSpec> generateGetterMethods(FileHandle file) {
        List<MethodSpec> getterSpecs = new ArrayList<>();

        String atlasName = file.nameWithoutExtension() + "Atlas";
        getterSpecs.add(MethodSpec.methodBuilder("get" + StringUtils.capitalizeFirst(atlasName))
            .addModifiers(PUBLIC)
            .returns(PNG.type)
            .addStatement("return $L", atlasName)
            .build());

        try (BufferedReader reader = file.reader(128);) {
            String line;
            Set<String> animationNames = new HashSet<>();
            do {
                line = reader.readLine();
            } while (!line.startsWith("repeat: none"));

            while ((line = reader.readLine()) != null) {
                reader.readLine();  // rotate
                reader.readLine();  // xy
                reader.readLine();  // size
                reader.readLine();  // orig
                reader.readLine();  // offset

                String indexLine = reader.readLine();
                String name = line.replace(".", "_");

                if (!indexLine.equals("  index: -1")) {
                    if (!animationNames.contains(line)) {
                        animationNames.add(line);
                        getterSpecs.add(MethodSpec.methodBuilder("get" + StringUtils.capitalizeFirst(name + "Frames"))
                            .addModifiers(PUBLIC)
                            .returns(ANIM.type)
                            .addStatement("return $L", name + "Frames")
                            .build());
                    }
                    int index = Integer.parseInt(indexLine.split(":")[1].trim());
                    getterSpecs.add(MethodSpec.methodBuilder("get" + StringUtils.capitalizeFirst(name + "_" + index))
                        .addModifiers(PUBLIC)
                        .returns(PNG.type)
                        .addStatement("return $L", name + "_" + index)
                        .build());
                    continue;
                }
                getterSpecs.add(MethodSpec.methodBuilder("get" + StringUtils.capitalizeFirst(name))
                    .addModifiers(PUBLIC)
                    .returns(PNG.type)
                    .addStatement("return $L", name)
                    .build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return getterSpecs;
    }
}