package org.samiul.util;

import org.samiul.model.ASTType;
import org.samiul.model.Method;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {
    public static String getFileContent(String filePath) throws IOException {
        try (
                FileReader fileReader = new FileReader(new File(filePath).getAbsolutePath());
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        }
    }

    public static void writeToFile(String fileName, Set<Method> methods, int mode) throws IOException {
        try (FileWriter fileWriter = new FileWriter("output.txt", mode != 1)) {
            fileWriter.append(fileName)
                    .append(System.lineSeparator())
                    .append("====================")
                    .append(System.lineSeparator());

            Set<String> declaredMethods = new HashSet<>();
            Set<String> invokedMethods = new HashSet<>();

            filterMethodsBasedOnTypes(methods, declaredMethods, invokedMethods);

            writeToFile(fileWriter, declaredMethods, ASTType.DECLARED_METHODS);
            writeToFile(fileWriter, invokedMethods, ASTType.INVOKED_METHODS);
        }
    }

    private static void writeToFile(FileWriter fileWriter,
                                    Set<String> methods,
                                    ASTType astType) throws IOException {
        fileWriter.append(astType.name()).append(System.lineSeparator());
        if (methods.isEmpty()) {
            fileWriter.append("-")
                    .append("No methods found")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
            return;
        }
        for (String methodName : methods) {
            fileWriter.append("-").append(methodName).append(System.lineSeparator());
        }
        fileWriter.append(System.lineSeparator());
    }

    private static void filterMethodsBasedOnTypes(Set<Method> methods,
                                                  Set<String> declaredMethods,
                                                  Set<String> invokedMethods) {
        methods.forEach(method -> {
            if (method.getAstType().equals(ASTType.DECLARED_METHODS)) {
                declaredMethods.add(method.getName());
            } else {
                invokedMethods.add(method.getName());
            }
        });
    }
}
