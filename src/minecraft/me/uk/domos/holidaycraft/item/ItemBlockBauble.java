package me.uk.domos.holidaycraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockBauble extends ItemBlock {
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		icons = new Icon[16];
		
		for(int i=0; i < icons.length ; i++){
			icons[i] = par1IconRegister.registerIcon("holidaycraft:bauble" + i);
		}
	}
	
	private final static String[] subNames = {
		"black","red","green","brown","blue","purple","cyan","lightGray","gray","pink","lime","yellow","lightBlue","magenta","orange","white"
	};

	public ItemBlockBauble(int par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata (int damageValue) {
		System.out.println("Itemstack Metadata " + damageValue);
		return damageValue;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg) {
        return icons[dmg];
    }

}
