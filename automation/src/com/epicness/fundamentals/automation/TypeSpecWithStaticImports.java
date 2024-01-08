package com.epicness.fundamentals.automation;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeSpec;

public class TypeSpecWithStaticImports {

    public final TypeSpec spec;
    public final StaticImport[] staticImports;

    public TypeSpecWithStaticImports(TypeSpec spec, StaticImport... staticImports) {
        this.spec = spec;
        this.staticImports = staticImports;
    }

    public static final class StaticImport {
        public ClassName className;
        public String[] names;

        public StaticImport(ClassName className, String... names) {
            this.className = className;
            this.names = names;
        }
    }
}