package me.uk.domos.holidaycraft.block;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLantern;
import me.uk.domos.holidaycraft.tileentity.TileEntityRibbon;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChineseLantern extends Block {
	
	public static Block instance;
	
	@Override
	public boolean hasTileEntity(int meta){
		return true;
	}
	
	public BlockChineseLantern(int par1) {
		super(par1, Material.cloth);
		setUnlocalizedName("holidaycraft.chineseLantern");
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundClothFootstep);
		setCreativeTab(RegistryHelper.tabHoliday);
		setTextureName("holidaycraft:chineseLantern");
		setBlockBounds(0.15625F, 0.0625F, 0.15625F, 0.84375F, 1.0F, 0.84375F);
		setLightValue(1.0F);
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
	
	//Can only place below another block
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		return par1World.isBlockFullCube(par2, par3 + 1, par4) || 
				par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN) || 
				(par1World.getBlockTileEntity(par2, par3 + 1, par4) instanceof TileEntityRibbon) || 
				(par1World.getBlockTileEntity(par2, par3 + 1, par4) instanceof TileEntityChineseLantern);
    }
	
	//If the block above this is gone, then poof!
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
		if (!par1World.isBlockFullCube(par2, par3 + 1, par4) && 
				!(par1World.getBlockTileEntity(par2, par3 + 1, par4) instanceof TileEntityRibbon)&& 
				!(par1World.getBlockTileEntity(par2, par3 + 1, par4) instanceof TileEntityChineseLantern) && 
				!par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
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
			return new TileEntityChineseLantern();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
}
