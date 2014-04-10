package me.uk.domos.holidaycraft.tileentity;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityRibbon extends TileEntity 
{
	
	private int dip = 15;
	
	public int calculateDip()
	{
		
		World world = this.getWorldObj();
		int finalDip = 0;
		
		TileEntity te1 = world.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
		TileEntity te2 = world.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
		TileEntity te3 = world.getBlockTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
		TileEntity te4 = world.getBlockTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
		
		if(world.isBlockSolidOnSide(this.xCoord, this.yCoord, this.zCoord - 1, SOUTH) 
		|| world.isBlockSolidOnSide(this.xCoord, this.yCoord, this.zCoord + 1, NORTH) 
		|| world.isBlockSolidOnSide(this.xCoord - 1, this.yCoord, this.zCoord, WEST) 
		|| world.isBlockSolidOnSide(this.xCoord + 1, this.yCoord, this.zCoord, EAST))
		{
			
			return 1;
			
		}
		else if(te1 instanceof TileEntityRibbon 
		|| te2 instanceof TileEntityRibbon 
		|| te3 instanceof TileEntityRibbon 
		|| te4 instanceof TileEntityRibbon)
		{
			
			TileEntityRibbon ter1 = null;
			TileEntityRibbon ter2 = null;
			TileEntityRibbon ter3 = null;
			TileEntityRibbon ter4 = null;
			
			if (te1 instanceof TileEntityRibbon){ter1 = (TileEntityRibbon) te1;}
			if (te2 instanceof TileEntityRibbon){ter2 = (TileEntityRibbon) te2;}
			if (te3 instanceof TileEntityRibbon){ter3 = (TileEntityRibbon) te3;}
			if (te4 instanceof TileEntityRibbon){ter4 = (TileEntityRibbon) te4;}
			
			if (ter1 == null)
			{
				
				if (ter2 == null)
				{
					
					if (ter3 == null)
					{
						
						if (ter4 != null)
						{
							
							finalDip = ter4.getDip() + 1;
							
						}
						
					}
					else if (ter4 == null)
					{
						
						finalDip = ter3.getDip() + 1;
						
					}
					else 
					{
						
						finalDip = Math.min(ter3.getDip(), ter4.getDip()) + 1;
						
					}
					
				}
				else if (ter3 == null)
				{
					
					if (ter4 == null)
					{
						
						finalDip = ter2.getDip() + 1;
						
					}
					else 
					{
						
						finalDip = Math.min(ter2.getDip(), ter4.getDip()) + 1;
						
					}
					
				}
				else if (ter4 == null)
				{
					
					finalDip = ter2.getDip() + 1;
					
				}
				else 
				{
					
					int dip2 = Math.min(ter3.getDip(), ter4.getDip());
					finalDip = Math.min(ter2.getDip(),  dip2) + 1;
					
				}
				
			}
			else if (ter2 == null)
			{
				
				if (ter3 == null)
				{
					
					if (ter4 == null)
					{
						
						finalDip = ter1.getDip() + 1;
						
					}
					else
					{
						
						finalDip = Math.min(ter1.getDip(), ter4.getDip()) + 1;
						
					}
					
				}
				else if (ter4 == null)
				{
					
					finalDip = Math.min(ter1.getDip(), ter3.getDip()) + 1;
					
				}
				else 
				{
					
					int dip1 = Math.min(ter3.getDip(), ter4.getDip());
					finalDip = Math.min(dip1, ter1.getDip()) + 1;
					
				}
				
			}
			else if (ter3 == null)
			{
				
				if (ter4 == null)
				{
					
					finalDip = Math.min(ter1.getDip(), ter2.getDip()) + 1;
					
				}
				else 
				{
					
					int dip1 = Math.min(ter1.getDip(), ter2.getDip());
					finalDip = Math.min(dip1, ter4.getDip()) + 1;
					
				}
				
			}
			else if (ter4 == null)
			{
				
				int dip1 = Math.min(ter1.getDip(), ter2.getDip());
				finalDip = Math.min(dip1, ter3.getDip()) + 1;
				
			}
			else 
			{
				
				int dip1 = Math.min(ter1.getDip(), ter2.getDip());
				int dip2 = Math.min(ter3.getDip(), ter4.getDip());
				finalDip = Math.min(dip1,  dip2) + 1;
				
			}
		}
		
		if (finalDip > 15)
		{
			
			finalDip = 15;
			
		}
		
		return finalDip;
		
	}
	
	public int getDip()
	{
		
		return this.dip;
		
	}
	
	public void setDip(int dip)
	{
		
		this.dip = dip;
		
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound) 
    {
    	
            super.readFromNBT(tagCompound);
            this.dip = tagCompound.getInteger("dip");
            
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) 
    {
    	
            super.writeToNBT(tagCompound);
            tagCompound.setInteger("dip", this.dip);
            
    }
    
}
