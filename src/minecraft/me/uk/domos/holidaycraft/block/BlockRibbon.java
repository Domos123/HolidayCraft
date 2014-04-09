package me.uk.domos.holidaycraft.block;

import me.uk.domos.holidaycraft.tileentity.TileEntityRibbon;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRibbon extends Block {
	
	public static Block instance;
	
	@Override
	public boolean hasTileEntity(int meta){
		return true;
	}
	
	public BlockRibbon(int par1) {
		super(par1, Material.cloth);
		setUnlocalizedName("holidaycraft.ribbon");
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundClothFootstep);
		setCreativeTab(RegistryHelper.tabHoliday);
		setTextureName("holidaycraft:ribbon");
		setLightValue(1.0F);
		setCreativeTab(RegistryHelper.tabHoliday);
	}
	
	/*@Override //Selection box in correct place --- Too fiddly
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k){
		if (iblockaccess.getBlockTileEntity(i, j, k) instanceof TileEntityRibbon){
			TileEntityRibbon te = (TileEntityRibbon)iblockaccess.getBlockTileEntity(i, j, k);
			int dip = te.getDip();
			float maxy = (1.0f - (dip/16f));
			float miny = ((15f/16f) - (dip/16f));
			this.setBlockBounds(0.0F, miny, 0.0F, 1.0F, maxy, 1.0F);
		}
	}*/
	
	@Override //Prevent derpy collisions
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
		return (AxisAlignedBB) null;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	    return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
	    return false;
	}
	
	
	//You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
	
	@Override
	public TileEntity createTileEntity(World world, int meta){
		try{
			return new TileEntityRibbon();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
}
