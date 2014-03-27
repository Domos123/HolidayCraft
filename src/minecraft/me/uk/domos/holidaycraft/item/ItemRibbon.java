package me.uk.domos.holidaycraft.item;

import me.uk.domos.holidaycraft.common.HolidayCraft;
import net.minecraft.item.Item;

public class ItemRibbon extends Item {
	
	public static Item instance;

	public ItemRibbon(int par1) {
		super(par1);
		setMaxStackSize(64);
		setCreativeTab(HolidayCraft.tabHoliday);
		setUnlocalizedName("Ribbon");
		setTextureName("holidaycraft:ribbon");
	}

}
