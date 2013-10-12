package eldritchempires.client.model;

import eldritchempires.entity.EntityRabidMiner;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class RabidMinerModel extends ModelBiped
{
	//fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftarm;
    public ModelRenderer rightarm;
//    ModelRenderer ears;
    ModelRenderer beard;
    ModelRenderer helmet;
    
    private float animationHelper = 0;
    
  public RabidMinerModel()
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
//      RabidMiner entity = (RabidMiner) par7Entity;
//      if (entity.getRemovingBlock())
    	animationHelper = MathHelper.cos(par3 * 0.7F);
 //     this.rightleg.rotateAngleY = 0.0F;
 //     this.leftleg.rotateAngleY = 0.0F;
  }
  
  @Override
  public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
  {
      EntityRabidMiner entity = (EntityRabidMiner)par1EntityLivingBase;
      if (entity.getRemovingBlock()) {
    	  this.rightarm.rotateAngleX = animationHelper;
      }
      else {
    	  this.rightarm.rotateAngleX = this.leftleg.rotateAngleX;
      }
  }
  
  private float func_78172_a(float par1, float par2)
  {
      return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
  }
}