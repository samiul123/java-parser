package org.samiul;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.samiul.util.MethodASTVisitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.samiul.util.FileUtil.getFileContent;
import static org.samiul.util.FileUtil.writeToFile;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        int count = 0;
        if (args.length == 0) {
            logger.log(Level.INFO, "File paths not given");
            return;
        }
        try {
            for (String arg : args) {
                count += 1;
                String[] split = arg.split(System.getProperty("file.separator"));
                String fileName = split[split.length - 1];
                Optional<String> fileContent = Optional.of(getFileContent(arg));
                if (fileContent.get().isEmpty()) {
                    logger.log(Level.INFO, fileName + " is empty");
                    continue;
                }
                ASTParser astParser = ASTParser.newParser(AST.JLS3);
                astParser.setSource(fileContent.get().toCharArray());
                CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);
                MethodASTVisitor methodASTVisitor = MethodASTVisitor.getInstance();
                compilationUnit.accept(methodASTVisitor);
                writeToFile(fileName, methodASTVisitor.getMethods(), count);
                methodASTVisitor.clearMethodNames();
            }
            logger.log(Level.INFO, "Successfully wrote to the file: output.txt");
        } catch (IOException exception) {
            logger.log(Level.SEVERE, Arrays.toString(exception.getStackTrace()));
        }
    }
}