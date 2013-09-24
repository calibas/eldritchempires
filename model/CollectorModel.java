package eldritchempires.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CollectorModel extends ModelBase
{
//fields
 ModelRenderer mainbox;
 ModelRenderer leftbox;
 ModelRenderer frontbox;
 ModelRenderer rightbox;
 ModelRenderer backbox;

public CollectorModel()
{
 textureWidth = 64;
 textureHeight = 32;
 
   mainbox = new ModelRenderer(this, 0, 0);
   mainbox.addBox(0F, 0F, 0F, 8, 8, 8);
   mainbox.setRotationPoint(-4F, 16F, -4F);
   mainbox.setTextureSize(64, 32);
   mainbox.mirror = true;
   setRotation(mainbox, 0F, 0F, 0F);
   leftbox = new ModelRenderer(this, 0, 16);
   leftbox.addBox(0F, 0F, 0F, 4, 4, 4);
   leftbox.setRotationPoint(4F, 20F, -2F);
   leftbox.setTextureSize(64, 32);
   leftbox.mirror = true;
   setRotation(leftbox, 0F, 0F, 0F);
   frontbox = new ModelRenderer(this, 0, 24);
   frontbox.addBox(0F, 0F, 0F, 4, 4, 4);
   frontbox.setRotationPoint(-2F, 20F, -8F);
   frontbox.setTextureSize(64, 32);
   frontbox.mirror = true;
   setRotation(frontbox, 0F, 0F, 0F);
   rightbox = new ModelRenderer(this, 0, 16);
   rightbox.addBox(0F, 0F, 0F, 4, 4, 4);
   rightbox.setRotationPoint(-8F, 20F, -2F);
   rightbox.setTextureSize(64, 32);
   rightbox.mirror = true;
   setRotation(rightbox, 0F, 0F, 0F);
   backbox = new ModelRenderer(this, 0, 24);
   backbox.addBox(0F, 0F, 0F, 4, 4, 4);
   backbox.setRotationPoint(-2F, 20F, 4F);
   backbox.setTextureSize(64, 32);
   backbox.mirror = true;
   setRotation(backbox, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
// super.render(entity, f, f1, f2, f3, f4, f5);
// setRotationAngles(f, f1, f2, f3, f4, f5, entity);
// mainbox.render(f5);
// leftbox.render(f5);
// frontbox.render(f5);
// rightbox.render(f5);
// backbox.render(f5);
}

public void renderCollector(float f5)
{
	 mainbox.render(f5);
	 leftbox.render(f5);
	 frontbox.render(f5);
	 rightbox.render(f5);
	 backbox.render(f5);
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