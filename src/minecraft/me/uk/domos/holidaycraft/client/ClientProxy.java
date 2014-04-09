package me.uk.domos.holidaycraft.client;

import me.uk.domos.holidaycraft.common.CommonProxy;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityBaubleRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLantern;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLanternRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import me.uk.domos.holidaycraft.tileentity.TileEntityGiftRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityRibbon;
import me.uk.domos.holidaycraft.tileentity.TileEntityRibbonRenderer;
import me.uk.domos.holidaycraft.tileentity.TileEntityWreath;
import me.uk.domos.holidaycraft.tileentity.TileEntityWreathRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	public void registerRenderInformation() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBauble.class, new TileEntityBaubleRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGift.class, new TileEntityGiftRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWreath.class, new TileEntityWreathRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRibbon.class, new TileEntityRibbonRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChineseLantern.class, new TileEntityChineseLanternRenderer());
	}
	
}
