package me.uk.domos.holidaycraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGift extends ModelBase
{
//fields
 ModelRenderer Shape1;
 ModelRenderer Shape2;
 ModelRenderer Shape3;
 ModelRenderer Shape4;
 ModelRenderer Shape5;
 ModelRenderer Shape6;
 ModelRenderer Shape7;
 ModelRenderer Shape8;
 ModelRenderer Shape9;
 ModelRenderer Shape10;

public ModelGift()
{
 textureWidth = 128;
 textureHeight = 32;
 
   Shape1 = new ModelRenderer(this, 0, 0);
   Shape1.addBox(0F, 0F, 0F, 12, 6, 12);
   Shape1.setRotationPoint(-6F, 18F, -6F);
   Shape1.setTextureSize(128, 32);
   Shape1.mirror = true;
   setRotation(Shape1, 0F, 0F, 0F);
   Shape2 = new ModelRenderer(this, 0, 20);
   Shape2.addBox(0F, 0F, 0F, 13, 6, 2);
   Shape2.setRotationPoint(-6.5F, 17.5F, -1F);
   Shape2.setTextureSize(128, 32);
   Shape2.mirror = true;
   setRotation(Shape2, 0F, 0F, 0F);
   Shape3 = new ModelRenderer(this, 50, 0);
   Shape3.addBox(0F, 0F, 0F, 13, 1, 2);
   Shape3.setRotationPoint(-6.5F, 23F, -1F);
   Shape3.setTextureSize(128, 32);
   Shape3.mirror = true;
   setRotation(Shape3, 0F, 0F, 0F);
   Shape4 = new ModelRenderer(this, 82, 0);
   Shape4.addBox(0F, 0F, 0F, 2, 6, 13);
   Shape4.setRotationPoint(-1F, 17.5F, -6.5F);
   Shape4.setTextureSize(128, 32);
   Shape4.mirror = true;
   setRotation(Shape4, 0F, 0F, 0F);
   Shape5 = new ModelRenderer(this, 50, 5);
   Shape5.addBox(0F, 0F, 0F, 2, 1, 13);
   Shape5.setRotationPoint(-1F, 23F, -6.5F);
   Shape5.setTextureSize(128, 32);
   Shape5.mirror = true;
   setRotation(Shape5, 0F, 0F, 0F);
   Shape6 = new ModelRenderer(this, 32, 23);
   Shape6.addBox(0F, 0F, 0F, 9, 1, 1);
   Shape6.setRotationPoint(-3F, 17F, 2F);
   Shape6.setTextureSize(128, 32);
   Shape6.mirror = true;
   setRotation(Shape6, 0F, 0.8726646F, 0F);
   Shape7 = new ModelRenderer(this, 32, 26);
   Shape7.addBox(0F, 0F, 0F, 9, 1, 1);
   Shape7.setRotationPoint(2F, 17F, 2.6F);
   Shape7.setTextureSize(128, 32);
   Shape7.mirror = true;
   setRotation(Shape7, 0F, 2.268928F, 0F);
   Shape8 = new ModelRenderer(this, 32, 20);
   Shape8.addBox(0F, 0F, 0F, 9, 1, 1);
   Shape8.setRotationPoint(-4.5F, 17F, -2.4F);
   Shape8.setTextureSize(128, 32);
   Shape8.mirror = true;
   setRotation(Shape8, 0F, 0F, 0F);
   Shape9 = new ModelRenderer(this, 54, 20);
   Shape9.addBox(0F, -3F, 0F, 3, 1, 1);
   Shape9.setRotationPoint(-2.55F, 20F, -4.2F);
   Shape9.setTextureSize(128, 32);
   Shape9.mirror = true;
   setRotation(Shape9, 0F, 4.328416F, 0F);
   Shape10 = new ModelRenderer(this, 54, 23);
   Shape10.addBox(0F, 0F, 0F, 3, 1, 1);
   Shape10.setRotationPoint(3.7F, 17F, -1.4F);
   Shape10.setTextureSize(128, 32);
   Shape10.mirror = true;
   setRotation(Shape10, 0F, 2.007129F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
 super.render(entity, f, f1, f2, f3, f4, f5);
 setRotationAngles(f, f1, f2, f3, f4, f5, entity);
 Shape1.render(f5);
 Shape2.render(f5);
 Shape3.render(f5);
 Shape4.render(f5);
 Shape5.render(f5);
 Shape6.render(f5);
 Shape7.render(f5);
 Shape8.render(f5);
 Shape9.render(f5);
 Shape10.render(f5);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
 model.rotateAngleX = x;
 model.rotateAngleY = y;
 model.rotateAngleZ = z;
}

public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
{
 super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
}

}
