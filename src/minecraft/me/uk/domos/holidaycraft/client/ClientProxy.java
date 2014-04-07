package me.uk.domos.holidaycraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import me.uk.domos.holidaycraft.common.CommonProxy;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityBaubleRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLantern;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLanternRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import me.uk.domos.holidaycraft.tileentity.TileEntityGiftRenderer;

public class ClientProxy extends CommonProxy {
	
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBauble.class, new TileEntityBaubleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGift.class, new TileEntityGiftRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChineseLantern.class, new TileEntityChineseLanternRenderer());
	}
	
}
