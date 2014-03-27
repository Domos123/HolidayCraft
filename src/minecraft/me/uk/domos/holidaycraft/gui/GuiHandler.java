package me.uk.domos.holidaycraft.gui;

import me.uk.domos.holidaycraft.container.ContainerGift;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	
	@Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityGift){
                    return new ContainerGift(player.inventory, (TileEntityGift) tileEntity);
            }
            return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityGift){
            	TileEntityGift teGift = (TileEntityGift) tileEntity;
            	if(teGift.getSender().isEmpty()){
            		teGift.setSender(player.getEntityName());
            	}
            	return new GuiGift(player.inventory, (TileEntityGift) tileEntity);
            }
            return null;

    }

}
