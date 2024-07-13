package dev.nonamecrackers2.anticreativedrift.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import dev.nonamecrackers2.anticreativedrift.config.AntiCreativeDriftConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class AntiCreativeDriftCommands
{
	public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		dispatcher.register(Commands.literal("anticreativedrift")
				.then(Commands.literal("config")
						.then(Commands.literal("set")
								.then(Commands.literal("driftDiminishTicks")
										.then(Commands.argument("value", IntegerArgumentType.integer(0))
												.executes(AntiCreativeDriftCommands::setDriftDiminishTicks)
										)
								)
						)
				)
		);
	}
	
	private static int setDriftDiminishTicks(CommandContext<CommandSourceStack> context)
	{
		CommandSourceStack source = context.getSource();
		int value = IntegerArgumentType.getInteger(context, "value");
		if (AntiCreativeDriftConfig.getConfig().setDriftDiminishTicks(value))
		{
			source.sendSuccess(Component.translatable("command.anticreativedrift.set.config.success", value), true);
			return 1;
		}
		else
		{
			source.sendFailure(Component.translatable("command.anticreativedrift.set.config.fail"));
			return 0;
		}
	}
//	
//	private static int openConfigMenu(CommandContext<CommandSourceStack> context)
//	{
//		Minecraft.getInstance().execute(() -> 
//		{
//			ConfigBuilder builder = ConfigBuilder.create().setTitle(Component.translatable("title.anticreativedrift.config"));
//			ConfigEntryBuilder entryBuilder = builder.entryBuilder();
//			ConfigCategory category = builder.getOrCreateCategory(Component.translatable("category.anticreativedrift.general"));
//			category.addEntry(entryBuilder.startIntField(Component.translatable("option.anticreativedrift.driftDiminishTicks.title"), AntiCreativeDriftConfig.getAndOrReadFromFile().getDriftDiminishTicks())
//					.setDefaultValue(5)
//					.setTooltip(Component.translatable("option.anticreativedrift.driftDiminishTicks.description"))
//					.setSaveConsumer(i -> AntiCreativeDriftConfig.getAndOrReadFromFile().setDriftDiminishTicks(i))
//					.setMin(0)
//					.build());
//			Minecraft.getInstance().setScreen(builder.build());
//		});
//		return 0;
//	}
}
