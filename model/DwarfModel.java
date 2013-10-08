package eldritchempires.model;

import eldritchempires.entity.RabidDwarf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class DwarfModel extends ModelBiped
{
	//fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarm;
    ModelRenderer rightarm;
//    ModelRenderer ears;
    ModelRenderer beard;
    ModelRenderer helmet;
    
  public DwarfModel()
  {
	  textureWidth = 64;
	  textureHeight = 64;
    
    

//      head = new ModelRenderer(this, 0, 0);
//      head.addBox(-4F, -8F, -4F, 8, 8, 8);
//      head.setRotationPoint(0F, 0F, 0F);
//      head.setTextureSize(64, 64);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -6F, -4F, 6, 6, 7);
		head.setRotationPoint(0F, 7F, 0F);
		head.setTextureSize(64, 64);
		setRotation(head, 0F, 0F, 0F);
	    beard = new ModelRenderer(this, 22, 23);
	    beard.addBox(-3F, 0F, -4F, 6, 7, 0);
	    beard.setRotationPoint(0F, 7F, 0F);
	    beard.setTextureSize(64, 64);
	    setRotation(beard, -0.1141593F, 0F, 0F);
	  body = new ModelRenderer(this, 26, 0);
      body.addBox(-4F, 0F, -2F, 8, 9, 8);
      body.setRotationPoint(0F, 7F, -2F);
      body.setTextureSize(64, 64);
      setRotation(body, 0, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 3, 8, 4);
      rightleg.setRotationPoint(-2F, 16F, 0F);
      rightleg.setTextureSize(64, 64);
      setRotation(rightleg, -0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 3, 8, 4);
      leftleg.setRotationPoint(3F, 16F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, -0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 20);
      leftarm.addBox(0F, -2F, -2F, 3, 9, 5);
      leftarm.setRotationPoint(3F, 10F, 0F);
      leftarm.setTextureSize(64, 64);
      setRotation(leftarm, 0F, 0F, -0.2268928F);
      rightarm = new ModelRenderer(this, 40, 20);
      rightarm.addBox(-3F, -2F, -2F, 3, 9, 5);
      rightarm.setRotationPoint(-3F, 10F, 0F);
      rightarm.setTextureSize(64, 64);
      setRotation(rightarm, 0F, 0F, 0.2268928F);
      
      helmet = new ModelRenderer(this, 0, 31);
      helmet.addBox(-3.5F, -6.466667F, -4.533333F, 7, 7, 8);
      helmet.setRotationPoint(0F, 7F, 0F);
      helmet.setTextureSize(64, 64);
            setRotation(helmet, 0F, 0F, 0F);
//      ears = new ModelRenderer(this, 0, 38);
//      ears.addBox(-9F, -7F, 0F, 18, 4, 1);
//      ears.setRotationPoint(0F, 0F, 0F);
//      ears.setTextureSize(64, 64);
//      setRotation(ears, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   // super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    leftarm.render(f5);
    rightarm.render(f5);
    helmet.render(f5);
    beard.render(f5);
    
//    ears.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
//      this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.head.rotateAngleY = par5 / (180F / (float)Math.PI);
      this.helmet.rotateAngleY = head.rotateAngleY;
      this.beard.rotateAngleY = head.rotateAngleY;
      this.head.rotateAngleZ = head.rotateAngleY;
      this.helmet.rotateAngleZ = head.rotateAngleY;
      this.beard.rotateAngleZ = head.rotateAngleY;
//      this.ears.rotateAngleY = head.rotateAngleY;
//      this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
//      this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.helmet.rotateAngleX = head.rotateAngleX;
      this.beard.rotateAngleX = -0.1141593F + head.rotateAngleX;
//      this.ears.rotateAngleX = head.rotateAngleX;
      float rotateright = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
      float rotateleft = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 * 0.5F;
      this.rightleg.rotateAngleX = rotateright;
      this.leftleg.rotateAngleX = rotateleft;
      this.leftarm.rotateAngleX = rotateright;
 //     this.rightarm.rotateAngleX = rotateleft;
      RabidDwarf entity = (RabidDwarf) par7Entity;
      if (entity.getRemovingBlock())
    	  this.rightarm.rotateAngleX = MathHelper.cos(par3 * 0.7F);
 //     this.rightleg.rotateAngleY = 0.0F;
 //     this.leftleg.rotateAngleY = 0.0F;
  }
  
//  public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4)
//  {
//      Zoblin entityzoblin = (Zoblin)par1EntityLiving;
//      int i = entityzoblin.getAttackTimer();
//      if (i > 0)
//      {
//          this.rightarm.rotateAngleX = 0.9F;
//          this.leftarm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a((float)i - par4, 10.0F);
//      }
//  }
  
  private float func_78172_a(float par1, float par2)
  {
      return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}



//package eldritchempires.model;
//
//import net.minecraft.client.model.ModelBiped;
//import net.minecraft.client.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//
//public class DwarfModel extends ModelBiped
//{
//    public ModelRenderer bipedHead;
//    public ModelRenderer bipedHeadwear;
//    public ModelRenderer bipedBody;
//    public ModelRenderer bipedRightArm;
//    public ModelRenderer bipedLeftArm;
//    public ModelRenderer bipedRightLeg;
//    public ModelRenderer bipedLeftLeg;
//    public ModelRenderer beard;
//    
////	    ModelRenderer head;
////	    ModelRenderer beard;
////	    ModelRenderer body;
////	    ModelRenderer rightarm;
////	    ModelRenderer leftarm;
////	    ModelRenderer rightleg;
////	    ModelRenderer leftleg;
////	    ModelRenderer helmet;
//	  
//	  public DwarfModel()
//	  {
//	    textureWidth = 64;
//	    textureHeight = 64;
//	    
//	      this.bipedHead = new ModelRenderer(this, 0, 0);
//	      this.bipedHead.addBox(-3F, -6F, -4F, 6, 6, 7);
//	      this.bipedHead.setRotationPoint(0F, 7F, 0F);
//	      this.bipedHead.setTextureSize(64, 64);
//	      this.bipedHead.mirror = true;
//
//	      beard = new ModelRenderer(this, 22, 23);
//	      beard.addBox(0F, 0F, 0F, 6, 7, 0);
//	      beard.setRotationPoint(-3F, 7F, -4F);
//	      beard.setTextureSize(64, 64);
//	      beard.mirror = true;
//
//	      this.bipedBody = new ModelRenderer(this, 26, 0);
//	      this.bipedBody.addBox(-4F, 0F, -2F, 8, 9, 8);
//	      this.bipedBody.setRotationPoint(0F, 7F, -2F);
//	      this.bipedBody.setTextureSize(64, 64);
//	      this.bipedBody.mirror = true;
//
//	      this.bipedRightArm = new ModelRenderer(this, 40, 20);
//	      this.bipedRightArm.addBox(-3F, -2F, -2F, 3, 9, 5);
//	      this.bipedRightArm.setRotationPoint(-3F, 10F, 0F);
//	      this.bipedRightArm.setTextureSize(64, 64);
//	      this.bipedRightArm.mirror = true;
//
//	      this.bipedLeftArm = new ModelRenderer(this, 40, 20);
//	      this.bipedLeftArm.addBox(0F, -2F, -2F, 3, 9, 5);
//	      this.bipedLeftArm.setRotationPoint(3F, 10F, 0F);
//	      this.bipedLeftArm.setTextureSize(64, 64);
//	      this.bipedLeftArm.mirror = true;
//
//	      this.bipedRightLeg = new ModelRenderer(this, 0, 16);
//	      this.bipedRightLeg.addBox(-2F, 0F, -2F, 3, 8, 4);
//	      this.bipedRightLeg.setRotationPoint(-2F, 16F, 0F);
//	      this.bipedRightLeg.setTextureSize(64, 64);
//	      this.bipedRightLeg.mirror = true;
//
//	      this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
//	      this.bipedLeftLeg.addBox(-2F, 0F, -2F, 3, 8, 4);
//	      this.bipedLeftLeg.setRotationPoint(3F, 16F, 0F);
//	      this.bipedLeftLeg.setTextureSize(64, 64);
//	      this.bipedLeftLeg.mirror = true;
//
//	      this.bipedHeadwear = new ModelRenderer(this, 0, 31);
//	      this.bipedHeadwear.addBox(-3.5F, -6.466667F, -4.533333F, 7, 7, 8);
//	      this.bipedHeadwear.setRotationPoint(0F, 7F, 0F);
//	      this.bipedHeadwear.setTextureSize(64, 64);
//	      this.bipedHeadwear.mirror = true;
//
//	  }
//	  
//	  @Override
//	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
//	  {
//	    super.render(entity, f, f1, f2, f3, f4, f5);
//	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
//	    this.bipedHead.render(f5);
//	    beard.render(f5);
//	    this.bipedBody.render(f5);
//	    this.bipedRightArm.render(f5);
//	    this.bipedLeftArm.render(f5);
//	    this.bipedRightLeg.render(f5);
//	    this.bipedLeftLeg.render(f5);
//	    this.bipedHeadwear.render(f5);
//	  }
//	  
//	  
////	  private void setRotation(ModelRenderer model, float x, float y, float z)
////	  {
////	    model.rotateAngleX = x;
////	    model.rotateAngleY = y;
////	    model.rotateAngleZ = z;
////	  }
//	  
//	  @Override
//	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
//	  {
//	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
//	  }
//}