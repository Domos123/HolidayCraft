package me.uk.domos.holidaycraft.tileentity;

import me.uk.domos.holidaycraft.model.ModelRibbon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import org.lwjgl.opengl.GL11;

public class TileEntityRibbonRenderer extends TileEntitySpecialRenderer {
    
    //The model of your block
    private ModelRibbon model;
   
    public TileEntityRibbonRenderer() {
    	this.model = new ModelRibbon();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    		TileEntityRibbon teRibbon = (TileEntityRibbon) te;
    		teRibbon.setDip(teRibbon.calculateDip());
    		
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            ResourceLocation textures = (new ResourceLocation("holidaycraft:textures/models/ribbon.png"));
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);            
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            World world = te.getWorldObj();
            boolean north = world.isBlockSolidOnSide(te.xCoord, te.yCoord, te.zCoord - 1, SOUTH) || world.getBlockTileEntity(te.xCoord, te.yCoord, te.zCoord - 1) instanceof TileEntityRibbon;
            boolean south = world.isBlockSolidOnSide(te.xCoord, te.yCoord, te.zCoord + 1, NORTH) || world.getBlockTileEntity(te.xCoord, te.yCoord, te.zCoord + 1) instanceof TileEntityRibbon;
            boolean west = world.isBlockSolidOnSide(te.xCoord - 1, te.yCoord, te.zCoord, WEST) || world.getBlockTileEntity(te.xCoord - 1, te.yCoord, te.zCoord) instanceof TileEntityRibbon;
            boolean east = world.isBlockSolidOnSide(te.xCoord + 1, te.yCoord, te.zCoord, EAST) || world.getBlockTileEntity(te.xCoord + 1, te.yCoord, te.zCoord) instanceof TileEntityRibbon;
            this.model.render(teRibbon.getDip(), north, south, east, west, (Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }
}