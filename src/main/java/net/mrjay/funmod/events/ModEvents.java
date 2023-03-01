package net.mrjay.funmod.events;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.mrjay.funmod.FunMod;
import net.mrjay.funmod.commands.EvalCommand;
import net.mrjay.funmod.lisp.Blip;
import org.armedbear.lisp.Interpreter;

@Mod.EventBusSubscriber(modid = FunMod.MODID)
public class ModEvents {
    public static Interpreter gi = Blip.register();
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {

        EvalCommand.register(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }
}
