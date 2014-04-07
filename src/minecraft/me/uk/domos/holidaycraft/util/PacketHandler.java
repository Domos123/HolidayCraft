package me.uk.domos.holidaycraft.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
	   DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

	   EntityPlayer entityPlayer = (EntityPlayer) player;
	   EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;

	   if (packet.channel.equals("holidaycraft")) 
	   {
		   int x = 0;
		   int y = 0;
		   int z = 0;
		   String text = "";
		   String text2 = "";
			try {
				x = inputStream.readInt();
		        y = inputStream.readInt();
		        z = inputStream.readInt();
		        text = inputStream.readUTF();
		        text2 = inputStream.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}

	          TileEntity tile_entity = entityPlayer.worldObj.getBlockTileEntity(x, y, z);
	          if(tile_entity instanceof TileEntityGift)
	          {
	                 TileEntityGift tee = (TileEntityGift) tile_entity;
	                 tee.setSender(text);
	                 tee.setRecipient(text2);

	                 PacketDispatcher.sendPacketToAllAround(x, y, z, 100, entityPlayerMP.dimension, tee.getDescriptionPacket());
	          }
	   }
	}

}
