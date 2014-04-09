package me.uk.domos.holidaycraft;

import me.uk.domos.holidaycraft.common.CommonProxy;
import me.uk.domos.holidaycraft.util.PacketHandler;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "holidaycraft", name = "HolidayCraft", version = "0.1")
@NetworkMod(channels = "holidaycraft", packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = false)

public class HolidayCraft {
	
	public static final String modid = "holidaycraft";
    @Instance(modid)
    public static HolidayCraft instance;
	
	
	@SidedProxy(clientSide = "me.uk.domos.holidaycraft.client.ClientProxy", serverSide = "me.uk.domos.holidaycraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		RegistryHelper.registerGUI();
		RegistryHelper.registerContent();
		RegistryHelper.registerNames();
		RegistryHelper.registerRecipes();
		proxy.registerRenderInformation();
	}

}
