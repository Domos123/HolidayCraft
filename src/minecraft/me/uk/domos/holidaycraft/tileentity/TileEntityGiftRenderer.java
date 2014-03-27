package me.uk.domos.holidaycraft.tileentity;

import me.uk.domos.holidaycraft.model.ModelGift;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class TileEntityGiftRenderer extends TileEntitySpecialRenderer {
    
    //The model of your block
    private final ModelGift model;
   
    public TileEntityGiftRenderer() {
            this.model = new ModelGift();
    }
   
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glRotatef(meta * (90), 0.0F, 1.0F, 0.0F);
    }
   
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            ResourceLocation textures = (new ResourceLocation("holidaycraft:textures/models/gift.png"));
            Minecraft.getMinecraft().renderEngine.bindTexture(textures);
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
            adjustRotatePivotViaMeta(te.worldObj, te.xCoord, te.yCoord, te.zCoord);
            this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
    }
}