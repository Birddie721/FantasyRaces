package birddie.fantasyraces.proxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config{

	//private static Configuration latestConfig;
	
	public static boolean isDwarfEnabled = true;
	public static boolean isElfEnabled = true;
	public static boolean isHalflingEnabled = true;
	
	private Config() {}
	/*
	public static void loadAndSetup(File file) {
		latestConfig = new Configuration(file);
		latestConfig.load();
		loadData();
		latestConfig.save();
		cachedConfigs.put(fantasyraces.MODID, latestConfig);
		
		MinecraftForge.EVENT_BUS.register(new Config());
	}
	
	@SubscribeEvent
	public void onCfgChange(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(fantasyraces.MODID.equals(event.getModID())) {
			Configuration cfg = cachedConfigs.get(event.getConfigID());
			if(cfg != null) {
				cfg.save();
				
				loadData();
				loadConfigRegestries(ConfigDataAdapter.LoadPhase.PRE_INIT);
				loadConfigRegestries(ConfigDataAdapter.LoadPhase.INIT);
				loadConfigRegestries(ConfigDataAdapter.LoadPhase.POST_INIT);
			}
		}
	}*/
	
	public static void syncConfig(Configuration Config) {
		try{
			Config.load();
			
			Property isDwarfEnabledProp = Config.get(Configuration.CATEGORY_GENERAL,
					"isDwarfEnabled",
					"true",
					"Are Dwarves enabled?");
			Property isElfEnabledProp = Config.get(Configuration.CATEGORY_GENERAL,
					"isElfEnabled",
					"true",
					"Are Elves enabled?");
			Property isHalflingEnabledProp = Config.get(Configuration.CATEGORY_GENERAL,
					"isHalflingEnabled",
					"true",
					"Are Halflings enabled?");
			
			isDwarfEnabled = isDwarfEnabledProp.getBoolean();
			isElfEnabled = isElfEnabledProp.getBoolean();
			isHalflingEnabled = isHalflingEnabledProp.getBoolean();
		}catch(Exception e) {
			
		}finally {
			if(Config.hasChanged()) Config.save();
		}
	}
	
}
