package me.uk.domos.holidaycraft.item;

import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.item.Item;

public class ItemRibbon extends Item {
	
	public static Item instance;

	public ItemRibbon(int par1) {
		super(par1);
		setMaxStackSize(64);
		setCreativeTab(RegistryHelper.tabHoliday);
		setUnlocalizedName("holidaycraft.ribbon");
		setTextureName("holidaycraft:ribbon");
	}

}
