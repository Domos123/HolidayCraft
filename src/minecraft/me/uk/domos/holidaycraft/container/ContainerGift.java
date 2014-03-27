package me.uk.domos.holidaycraft.container;

import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGift extends Container{
	
	public TileEntityGift tileEntity;
	
	public ContainerGift(InventoryPlayer inventoryPlayer, TileEntityGift te) {
        tileEntity = te;
        

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        for (int i = 0; i < 5; i++) {
            addSlotToContainer(new Slot(tileEntity, i, 44 + i * 18, 20));
        }
        
        for (int i = 0; i < 3; i++){
        	for (int j = 0; j < 9; j++){
        		this.addSlotToContainer(new Slot(inventoryPlayer,9 + j + i * 9, 8 + j * 18, 51 + i * 18));
        	}
        }
        
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 109));
        }

        //commonly used vanilla code that adds the player's inventory
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		String name = entityplayer.getEntityName();
		if (tileEntity.getSender() != null){
			if (name.contentEquals(tileEntity.getSender())){
				return true;
			}
		}
        if (tileEntity.getRecipient() != null){
        	if (name.contentEquals(tileEntity.getRecipient())){
        		return true;
        	}
        }
        return false;
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
            ItemStack stack = null;
            Slot slotObject = (Slot) inventorySlots.get(slot);

            //null checks and checks if the item can be stacked (maxStackSize > 1)
            if (slotObject != null && slotObject.getHasStack()) {
                    ItemStack stackInSlot = slotObject.getStack();
                    stack = stackInSlot.copy();

                    //merges the item into player inventory since its in the tileEntity
                    if (slot < 9) {
                            if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
                                    return null;
                            }
                    }
                    //places it into the tileEntity is possible since its in the player inventory
                    else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
                            return null;
                    }

                    if (stackInSlot.stackSize == 0) {
                            slotObject.putStack(null);
                    } else {
                            slotObject.onSlotChanged();
                    }

                    if (stackInSlot.stackSize == stack.stackSize) {
                            return null;
                    }
                    slotObject.onPickupFromSlot(player, stackInSlot);
            }
            return stack;
    }
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		
	}

}
