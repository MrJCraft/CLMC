package net.mrjay.funmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import java.util.Properties;
import java.util.Vector;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.commands.*;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.arguments.RangeArgument;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.ExecuteCommand;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.phys.Vec3;
import net.mrjay.funmod.events.ModEvents;
import net.mrjay.funmod.lisp.Blip;
import org.armedbear.lisp.*;
import com.mojang.brigadier.context.CommandContext;

import javax.annotation.Nullable;
import java.util.function.Predicate;


public class EvalCommand {
    //try pluging it into executes
    public static void register(CommandDispatcher<CommandSourceStack> p_138410_) {
        p_138410_.register(Commands.literal("eval").then(Commands.argument("sexp", MessageArgument.message()).executes(
                EvalCommand::evalCommand)));
    }
    public static CommandContext<CommandSourceStack> stack;
    private static int evalCommand(CommandContext<CommandSourceStack> par) throws CommandSyntaxException {
        Interpreter interpreter = ModEvents.gi;
        stack = par;
        String line = null;
        StringRange subr = par.getNodes().get(1).getRange();
        String input = par.getInput().substring(subr.getStart(), subr.getEnd());
        try {
            line = interpreter.eval("(format nil \"~s\" (eval" + input + " ))").getStringValue();
            par.getSource().sendSuccess(Component.literal(line), true);
        } catch(ProcessingTerminated e) {
            par.getSource().sendFailure(Component.literal("Something Happened Check console for Error"));
        }
        return 1;
    }

public static CommandContext<CommandSourceStack> gettack() {
        return stack;
}
    public static void playerMotion(double x, double y, double z) throws CommandSyntaxException {
        CommandSourceStack source = gettack().getSource();
        ServerPlayer player = source.getPlayerOrException();
        // player.setPos(x, y, z);
        //player.position();
        //test this out below
        player.lerpMotion(x, y, z);
        //  source.sendSuccess(Component.literal(player.toString()), true);
    }
public static void tpPlayer(double x, double y, double z) throws CommandSyntaxException {
        CommandSourceStack source = gettack().getSource();
        ServerPlayer player = source.getPlayerOrException();
        player.teleportTo(x, y, z);
}

    public static Vec3 getPlayerPos() throws CommandSyntaxException {
        CommandSourceStack source = gettack().getSource();
        ServerPlayer player = source.getPlayerOrException();
       // source.sendSuccess(Component.literal(player.position().toString()), true);
        return player.position();
    }
    public static int addTwoNumbers(int a, int b) {
 return a + b;
}

}




