package me.uk.domos.holidaycraft.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.container.ContainerGift;
import me.uk.domos.holidaycraft.tileentity.TileEntityGift;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiGift extends GuiContainer {
	
	public static final ResourceLocation texture = new ResourceLocation(HolidayCraft.modid, "textures/gui/gift.png");
	private GuiTextField giftTo;
	private GuiTextField giftFrom;
	private TileEntityGift teGift;
	private EntityPlayer entityplayer;

	public GuiGift(InventoryPlayer inventoryPlayer, TileEntityGift tileEntity) {
		super(new ContainerGift(inventoryPlayer, tileEntity));
		
		teGift = tileEntity;
		entityplayer = inventoryPlayer.player;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		giftTo = new GuiTextField(fontRenderer, 191,36,62,16);
		giftTo.setFocused(true);
		giftTo.setMaxStringLength(50);
		giftTo.setCanLoseFocus(false);
		giftFrom = new GuiTextField(fontRenderer, 191,63,62,16);
		giftFrom.setFocused(false);
		giftFrom.setMaxStringLength(50);
		giftFrom.setCanLoseFocus(false);
		if (teGift.getRecipient() != null){
			giftTo.setText(teGift.getRecipient());
		}
		if (teGift.getSender() != null){
			giftFrom.setText(teGift.getSender());
		}
		sendStringToServer();
	}
	
	@Override
	public void keyTyped(char c, int i){
		
		if (c != 'e'){
			super.keyTyped(c, i);
		}
		if (this.giftFrom.getText().contentEquals(entityplayer.getEntityName())){
			giftTo.textboxKeyTyped(c, i);
			sendStringToServer();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
	return false;
	}




	@Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		giftTo.drawTextBox();
		giftFrom.drawTextBox();

            //draw text and stuff here
            //the parameters for drawString are: string, x, y, color
            fontRenderer.drawStringWithShadow("Gift", 8, 5, 0xffffff);
            //draws "Inventory" or your regional equivalent
            fontRenderer.drawStringWithShadow(StatCollector.translateToLocal("container.inventory"), 8, 41, 0xffffff);
            fontRenderer.drawString("To:", xSize + 18, 27, 0x000000);
            fontRenderer.drawString("From:", xSize + 18, 54, 0x000000);
    }
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
            //draw your Gui here, only thing you need to change is the path
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.renderEngine.bindTexture(texture);
            this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, 256, ySize);
    }
	
	public void sendStringToServer() 
	{
	   ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
	   DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

	   try 
	   {
	          dataoutputstream.writeInt(teGift.xCoord);
	          dataoutputstream.writeInt(teGift.yCoord);
	          dataoutputstream.writeInt(teGift.zCoord);
	          dataoutputstream.writeUTF(teGift.getSender());
	          dataoutputstream.writeUTF(giftTo.getText());
	   } 
	   catch (Exception e) 
	   {
	          e.printStackTrace();
	   }

	   Packet250CustomPayload packet = new Packet250CustomPayload();
	   packet.channel = "holidaycraft";
	   packet.data = bytearrayoutputstream.toByteArray();
	   packet.length = bytearrayoutputstream.size();
	   PacketDispatcher.sendPacketToServer(packet);
	}

}
