package me.uk.domos.holidaycraft.block;

import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGift extends BlockContainer {
	
	public static BlockContainer instance;
	private String player;
	
	public BlockGift(int par1) {
		super(par1, Material.cloth);
		setUnlocalizedName("holidaycraft.gift");
		setHardness(0.5F);
		setResistance(50000.0F);
		setStepSound(soundClothFootstep);
		setCreativeTab(RegistryHelper.tabHoliday);
		setTextureName("holidaycraft:gift");
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F, 0.9F);
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack par6ItemStack) {
	    int dir = MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	    world.setBlockMetadataWithNotify(x, y, z, dir, 0);
	    this.player = player.getEntityName();

	}
		
	//You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world){
		try{
			return new TileEntityGift();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
	
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z){
		TileEntityGift teGift = (TileEntityGift) world.getBlockTileEntity(x, y, z);
		String name = player.getEntityName();
		
		if (teGift.getSender() != null){
			if (name.contentEquals(teGift.getSender())){
				return super.removeBlockByPlayer(world, player, x, y, z);
			}
		}
		if (teGift.getRecipient() != null){
        	if (name.contentEquals(teGift.getRecipient())){
        		return super.removeBlockByPlayer(world, player, x, y, z);
        	}
        }
		if (player.capabilities.isCreativeMode){
    		return super.removeBlockByPlayer(world, player, x, y, z);
		}
        return false;
	}
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t){
		// Just making an instance of the TileEntity that the player clicked on
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		// Checking if the TileEntity is nothing or if the player is sneaking
		if(tile_entity == null || player.isSneaking()){
		// Returns false so it doesn't update anything
		return false;
		}
		
		player.openGui(HolidayCraft.instance, 0, world, x, y, z);
		// Returns true to force an update
		return true;
		}

}
