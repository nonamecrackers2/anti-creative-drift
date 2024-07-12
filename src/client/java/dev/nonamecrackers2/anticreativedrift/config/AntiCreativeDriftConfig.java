package dev.nonamecrackers2.anticreativedrift.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import net.minecraft.client.Minecraft;

public class AntiCreativeDriftConfig
{
	private static @Nullable AntiCreativeDriftConfig instance;
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String DIRECTORY = "anticreativedrift/config.json";
	private int driftDiminishTicks = 5;
	
	private AntiCreativeDriftConfig() {}
	
	public int getDriftDiminishTicks()
	{
		return this.driftDiminishTicks;
	}
	
	public boolean setDriftDiminishTicks(int time)
	{
		time = Math.max(0, time);
		if (this.driftDiminishTicks != time)
		{
			this.driftDiminishTicks = time;
			this.saveFile();
			return true;
		}
		return false;
	}
	
	private static void createDirectory()
	{
		new File(Minecraft.getInstance().gameDirectory, "anticreativedrift").mkdirs();
	}
	
	public static AntiCreativeDriftConfig getConfig()
	{
		if (instance == null)
			throw new IllegalStateException("Config is not loaded");
		return instance;
	}
	
	public static void readFile()
	{
		createDirectory();
		File file = new File(Minecraft.getInstance().gameDirectory, DIRECTORY);
		if (!file.exists())
		{
			LOGGER.info("Config file does not exist, creating a new one");
			instance = new AntiCreativeDriftConfig();
			instance.saveFile();
			return;
		}
		
		CompletableFuture.runAsync(() -> 
		{
			try (var reader = new BufferedReader(new FileReader(file)))
			{
				var config = GSON.fromJson(reader, AntiCreativeDriftConfig.class);
				if (config != null)
					config.driftDiminishTicks = Math.max(0, config.driftDiminishTicks);
				instance = config;
				LOGGER.info("Successfully read config file");
			}
			catch (IOException | JsonSyntaxException e)
			{
				LOGGER.error("Failed to read config file", e);
				instance = new AntiCreativeDriftConfig();
			}
		});
	}
	
	public void saveFile()
	{
		CompletableFuture.runAsync(() -> 
		{
			createDirectory();
			File file = new File(Minecraft.getInstance().gameDirectory, DIRECTORY);
			try (var writer = new FileWriter(file)) {
				GSON.toJson(this, writer);
			} catch (IOException e) {
				LOGGER.error("Failed to write config file", e);
			}
		});
	}
}
