package io.github.lucklike.luckyclient.api.util;

import com.luckyframework.common.Console;

public class DelayedOutput {

    private static final ThreadLocal<Integer> outputLength = new ThreadLocal<>();

    public static void setOutputLength(int length) {
        outputLength.set(length);
    }

    public static Integer getOutputLength() {
        Integer length = outputLength.get();
        return length == null ? 0 : length;
    }

    public static void clearOutputLength() {
        outputLength.remove();
    }

    public static void output(String output) {
        output(output, 70, 20);
    }

    public static void output(String output, int maxLength, int delayTime)  {
        int length = output.length();
        int outputLength = getOutputLength();
        int rem = outputLength % maxLength;
        setOutputLength(outputLength + output.length());

        int j = maxLength - rem;
        while (j <= length) {
            output = output.substring(0, j) + "\n" + output.substring(j);
            length++;
            j += maxLength;
        }

        for (char c : output.toCharArray()) {
            Console.printMulberry(c);
            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
