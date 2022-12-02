package org.samiul.model;

public class Method {
    private final ASTType astType;
    private final String name;

    public Method(ASTType astType, String name) {
        this.astType = astType;
        this.name = name;
    }

    public ASTType getAstType() {
        return astType;
    }

    public String getName() {
        return name;
    }
}
