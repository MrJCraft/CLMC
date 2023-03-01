package net.mrjay.funmod.lisp;

import com.mojang.brigadier.context.StringRange;
import net.minecraft.network.chat.Component;
import org.armedbear.lisp.*;

public class Blip {

    public static Interpreter register() {
        Interpreter interpreter = org.armedbear.lisp.Interpreter.getInstance();
        if (interpreter == null) {
            try {
                Blip thisObject = new Blip();
                interpreter = org.armedbear.lisp.Interpreter.createInstance();
                interpreter.eval("(compile-file \"config/Lisp/init\")");
                interpreter.eval("(load \"config/Lisp/init\")");
            } catch(ProcessingTerminated e) {
                System.console().writer().print("Failed to load Lisp init file.");
                e.printStackTrace();
            }
        }
        return interpreter;
    }

    public static void blip() {

    }
    public static int addTwoNumbers(int a, int b)
    {
        return a + b;
    }
}
