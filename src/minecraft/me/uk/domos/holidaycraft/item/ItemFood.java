package me.uk.domos.holidaycraft.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemFood extends net.minecraft.item.ItemFood {
	
	public static Item instance;
	
	private final static String[] subNames = {
		"christmasCookie", "easterEgg", "pancake", "candyCane"
	};
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemFood(int par1,int par2, float f, boolean par4) {
		super(par1,par2,f,par4);
		setMaxStackSize(64);
		setCreativeTab(RegistryHelper.tabHoliday);
		setUnlocalizedName("holidaycraft.food");
		setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		icons = new Icon[4];
		
		for(int i=0; i < icons.length ; i++){
			icons[i] = par1IconRegister.registerIcon("holidaycraft:food" + subNames[i]);
		}
	}
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg) {
        return icons[dmg];
    }
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

}
