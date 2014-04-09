package me.uk.domos.holidaycraft.block;

import static net.minecraftforge.common.ForgeDirection.*;
import cpw.mods.fml.common.Mod.Metadata;
import me.uk.domos.holidaycraft.tileentity.TileEntityWreath;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWreath extends BlockContainer {
	
	public static BlockContainer instance;
	
	public BlockWreath(int par1) {
		super(par1, Material.cloth);
		setUnlocalizedName("holidaycraft.wreath");
		setHardness(0.5F);
		setResistance(5.0F);
		setStepSound(soundClothFootstep);
		setCreativeTab(RegistryHelper.tabHoliday);
		setTextureName("holidaycraft:wreath");
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
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k){
		int meta = iblockaccess.getBlockTileEntity(i, j, k).getBlockMetadata();
		if (meta==0){
	    	this.setBlockBounds(0.2F, 0.31F, 0.88F, 0.8F, 0.95F, 1.0F);
	    }
	    if (meta==1){
	    	this.setBlockBounds(0.0F, 0.31F, 0.2F, 0.12F, 0.95F, 0.8F);
	    }
	    if (meta==2){
	    	this.setBlockBounds(0.2F, 0.31F, 0.0F, 0.8F, 0.95F, 0.12F);
	    }
	    if (meta==3){
	    	this.setBlockBounds(0.88F, 0.31F, 0.2F, 1.0F, 0.95F, 0.8F);
	    }
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack par6ItemStack) {
	    int dir = MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	    world.setBlockMetadataWithNotify(x, y, z, dir, 0);
	    if (!(((world.isBlockSolidOnSide(x, y, z + 1, NORTH, true) || (world.isBlockFullCube(x, y, z + 1)))  && (dir == 0)) || 
	    		((world.isBlockSolidOnSide(x - 1, y, z, EAST,  true) || (world.isBlockFullCube(x - 1, y, z))) && (dir == 1)) || 
	    		((world.isBlockSolidOnSide(x, y, z - 1, SOUTH, true) || (world.isBlockFullCube(x, y, z - 1))) && (dir == 2)) || 
	    		((world.isBlockSolidOnSide(x + 1, y, z, WEST,  true) || (world.isBlockFullCube(x + 1, y, z))) && (dir == 3))))
        {
	    	if (world.isBlockSolidOnSide(x, y, z + 1, NORTH, true) || world.isBlockFullCube(x, y, z + 1))
	        {
				world.setBlockMetadataWithNotify(x, y, z, 0, 0);
	        }
	    	else if (world.isBlockSolidOnSide(x - 1, y, z, EAST,  true) || world.isBlockFullCube(x - 1, y, z))
	        {
				world.setBlockMetadataWithNotify(x, y, z, 1, 0);
	        }
			else if (world.isBlockSolidOnSide(x, y, z - 1, SOUTH, true) || world.isBlockFullCube(x, y, z - 1))
	        {
				world.setBlockMetadataWithNotify(x, y, z, 2, 0);
	        }
			else if (world.isBlockSolidOnSide(x + 1, y, z, WEST,  true) || world.isBlockFullCube(x + 1, y, z))
	        {
				world.setBlockMetadataWithNotify(x, y, z, 3, 0);
	        }
			else {
				this.dropBlockAsItem(world, x, y, z, 0, 0);
				world.setBlockToAir(x, y, z);
			}
        }
	}
		
	//You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world){
		try{
			return new TileEntityWreath();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
	
	//Can only place below another block
		@Override
		public boolean canPlaceBlockAt(World par1World, int x, int y, int z)
	    {
	        return (par1World.isBlockSolidOnSide(x - 1, y, z, EAST,  true) ||
	                par1World.isBlockSolidOnSide(x + 1, y, z, WEST,  true) ||
	                par1World.isBlockSolidOnSide(x, y, z - 1, SOUTH, true) ||
	                par1World.isBlockSolidOnSide(x, y, z + 1, NORTH, true) ||
	                par1World.isBlockFullCube(x - 1, y, z) ||
	                par1World.isBlockFullCube(x + 1, y, z) ||
	                par1World.isBlockFullCube(x, y, z - 1) ||
	                par1World.isBlockFullCube(x, y, z + 1)
	                );
	    }
		
		//If the block above this is gone, then poof!
		@Override
		public void onNeighborBlockChange(World par1World, int x, int y, int z, int par5)
	    {	
			if (par1World.getBlockTileEntity(x, y, z) instanceof TileEntityWreath){
				int meta = par1World.getBlockMetadata(x,y,z);
				if (((!par1World.isBlockSolidOnSide(x, y, z + 1, NORTH, true) && (!par1World.isBlockFullCube(x, y, z + 1)) && (par1World.getBlockMetadata(x,y,z) == 0))) || 
						((!par1World.isBlockSolidOnSide(x - 1, y, z, EAST,  true) && (!par1World.isBlockFullCube(x - 1, y, z)) && (par1World.getBlockMetadata(x,y,z) == 1))) || 
						((!par1World.isBlockSolidOnSide(x, y, z - 1, SOUTH, true) && (!par1World.isBlockFullCube(x, y, z - 1)) && (par1World.getBlockMetadata(x,y,z) == 2))) || 
						((!par1World.isBlockSolidOnSide(x + 1, y, z, WEST,  true) && (!par1World.isBlockFullCube(x + 1, y, z)) && (par1World.getBlockMetadata(x,y,z) == 3))))
		        {
		            this.dropBlockAsItem(par1World, x, y, z, 0, 0);
		            par1World.setBlockToAir(x, y, z);
		        }
			}
	    }

}
