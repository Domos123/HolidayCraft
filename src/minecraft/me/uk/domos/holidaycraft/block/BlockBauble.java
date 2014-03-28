package me.uk.domos.holidaycraft.block;

import java.util.List;

import javax.swing.ImageIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBauble extends Block {
	
	public static Block instance;
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister){
		icons = new Icon[16];
		
		for(int i=0; i < icons.length ; i++){
			icons[i] = par1IconRegister.registerIcon("holidaycraft:bauble" + i);
		}
	}
	
	@Override
	public boolean hasTileEntity(int meta){
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2){
		System.out.println("getting icon for " + par2);
		return icons[par2];
	}
	
	public BlockBauble(int par1) {
		super(par1, Material.glass);
		setUnlocalizedName("Bauble");
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundGlassFootstep);
		setCreativeTab(HolidayCraft.tabHoliday);
		setBlockBounds(0.29F, 0.5F, 0.29F, 0.71F, 0.95F, 0.71F);
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	    return false;
	}

	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	public int damageDropped(int metadata){
		return metadata;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 16; i++) {
			subItems.add(new ItemStack(par1, 1, i));
		}
	}
	
	//Can only place below another block
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockFullCube(par2, par3 + 1, par4);
    }
	
	//If the block above this is gone, then poof!
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isBlockFullCube(par2, par3 + 1, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
	
	
	//You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
	
	@Override
	public TileEntity createTileEntity(World world, int meta){
		try{
			return new TileEntityBauble();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
}
