package com.epam.mjc;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature ms = new MethodSignature("methodName");

        int indexOpenBracket = signatureString.indexOf('(');

        String withoutParameters = signatureString.substring(0, indexOpenBracket);
        String[] split = withoutParameters.split("\\s+");
        if (split.length == 2) {
            ms.setReturnType(split[0]);
            ms.setMethodName(split[1]);
        } else if (split.length == 3) {
            ms.setAccessModifier(split[0]);
            ms.setReturnType(split[1]);
            ms.setMethodName(split[2]);
        }

        String parameters = signatureString.substring(indexOpenBracket + 1, signatureString.length() - 1);
        if (!parameters.isBlank()) {
            String[] params = parameters.split(", ");

            for (String param : params) {
                String[] pair = param.split(" ");
                ms.getArguments().add(new MethodSignature.Argument(pair[0], pair[1]));
            }
        }

        return ms;
    }
}
