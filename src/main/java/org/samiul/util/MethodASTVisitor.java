package org.samiul.util;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.samiul.model.ASTType;
import org.samiul.model.Method;

import java.util.HashSet;
import java.util.Set;

public class MethodASTVisitor extends ASTVisitor {
    private static MethodASTVisitor methodASTVisitor;
    private final Set<Method> methods;

    private MethodASTVisitor() {
        this.methods = new HashSet<>();
    }

    public static MethodASTVisitor getInstance() {
        if (methodASTVisitor == null)
            methodASTVisitor = new MethodASTVisitor();
        return methodASTVisitor;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        methods.add(new Method(ASTType.DECLARED_METHODS, node.getName().toString()));
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        methods.add(new Method(ASTType.INVOKED_METHODS, node.getName().toString()));
        return super.visit(node);
    }

    public Set<Method> getMethods() {
        return methods;
    }

    public void clearMethodNames() {
        methods.clear();
    }
}
