package me.uk.domos.holidaycraft.tileentity;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGift extends TileEntity implements ISidedInventory {
	
	private ItemStack[] inv;
	public String recipient;
	public String sender;
	
	public TileEntityGift(){
		inv = new ItemStack[5];
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
                if (stack.stackSize <= amt) {
                        setInventorySlotContents(slot, null);
                } else {
                        stack = stack.splitStack(amt);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(slot, null);
                        }
                }
        }
        return stack;
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    setInventorySlotContents(slot, null);
            }
            return stack;
    }

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
                itemstack.stackSize = getInventoryStackLimit();
        } 
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return true;
	}

	@Override
	public void openChest() {		
	}

	@Override
	public void closeChest() {
		
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
	   NBTTagCompound var1 = new NBTTagCompound();
	   this.writeToNBT(var1);
	   return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 2, var1);
	}

	@Override
	public void onDataPacket(INetworkManager netManager, Packet132TileEntityData packet)
	{
	   readFromNBT(packet.data);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
           
            NBTTagList tagList = tagCompound.getTagList("Inventory");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inv.length) {
                            inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
            this.recipient = tagCompound.getString("Recipient");
            this.sender = tagCompound.getString("Sender");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
                           
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inv.length; i++) {
                    ItemStack stack = inv[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            tagCompound.setTag("Inventory", itemList);
            if (this.recipient != null){
            	tagCompound.setString("Recipient", this.recipient);
            }
            if (this.sender != null){
            	tagCompound.setString("Sender", this.sender);
            }
    }

    @Override
    public String getInvName() {
    	return "holidaycraft.tileentitygift";
    }
    
    public String getSender(){
    	return this.sender;
    }
    
    public void setSender(String sender){
    	this.sender = sender;
    }
    
    public String getRecipient(){
    	return this.recipient;
    }
    
    public void setRecipient(String recipient){
    	this.recipient = recipient;
    }
    
    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
    	return this.isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return false;
    }

	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[]{0,1,2,3,4};
	}
	
}
