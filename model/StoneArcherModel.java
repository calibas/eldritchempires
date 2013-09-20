package eldritchempires.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class StoneArcherModel extends ModelBiped
{
	//fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightleg;
    ModelRenderer rightleg2;
    ModelRenderer leftleg;
    ModelRenderer leftleg2;
    ModelRenderer leftarm;
    ModelRenderer leftarm2;
    ModelRenderer rightarm;
    ModelRenderer rightarm2;
    ModelRenderer ears;
    
  public StoneArcherModel()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 64);
      setRotation(body, 0.2617994F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
      rightleg.setRotationPoint(-2F, 11F, 3F);
      rightleg.setTextureSize(64, 64);
      setRotation(rightleg, -0.5585054F, 0F, 0F);
      rightleg2 = new ModelRenderer(this, 40, 16);
      rightleg2.addBox(-2F, 6F, -5F, 4, 7, 4);
      rightleg2.setRotationPoint(-2F, 11F, 3F);
      rightleg2.setTextureSize(64, 64);
      setRotation(rightleg2, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
      leftleg.setRotationPoint(2F, 11F, 3F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, -0.4886922F, 0F, 0F);
      leftleg2 = new ModelRenderer(this, 40, 16);
      leftleg2.addBox(-2F, 6F, -5F, 4, 7, 4);
      leftleg2.setRotationPoint(2F, 11F, 3F);
      leftleg2.setTextureSize(64, 64);
      setRotation(leftleg2, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 27);
      leftarm.addBox(-1F, -2F, -2F, 4, 7, 4);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      setRotation(leftarm, 0.5205006F, 0F, 0F);
      leftarm2 = new ModelRenderer(this, 40, 16);
      leftarm2.addBox(-1F, 0F, 3F, 4, 7, 4);
      leftarm2.setRotationPoint(5F, 2F, 0F);
      leftarm2.setTextureSize(64, 64);
      setRotation(leftarm2, -0.9666439F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 27);
      rightarm.addBox(-3F, -2F, -2F, 4, 7, 4);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      setRotation(rightarm, 0.4833219F, 0F, 0F);
      rightarm2 = new ModelRenderer(this, 40, 16);
      rightarm2.addBox(-3F, 0F, 4F, 4, 7, 4);
      rightarm2.setRotationPoint(-5F, 2F, 0F);
      rightarm2.setTextureSize(64, 64);
      setRotation(rightarm2, -1.041001F, 0F, 0F);
      ears = new ModelRenderer(this, 0, 38);
      ears.addBox(-9F, -7F, 0F, 18, 4, 1);
      ears.setRotationPoint(0F, 0F, 0F);
      ears.setTextureSize(64, 64);
      setRotation(ears, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
   // super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightleg.render(f5);
    rightleg2.render(f5);
    leftleg.render(f5);
    leftleg2.render(f5);
    leftarm.render(f5);
    leftarm2.render(f5);
    rightarm.render(f5);
    rightarm2.render(f5);
    ears.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
      this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
      this.ears.rotateAngleY = head.rotateAngleY;
      this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
      this.ears.rotateAngleX = head.rotateAngleX;
      float rotateright = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
      float rotateleft = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2 * 0.5F;
      this.rightleg.rotateAngleX = -0.5585054F + rotateright;
      this.leftleg.rotateAngleX = -0.4886922F + rotateleft;
      this.rightleg2.rotateAngleX = rotateright;
      this.leftleg2.rotateAngleX = rotateleft;
      this.leftarm.rotateAngleX = 0.5205006F - rotateright;
  //    this.rightarm.rotateAngleX = 0.4833219F - rotateleft;
      this.leftarm2.rotateAngleX = -0.9666439F - rotateright;
 //     this.rightarm2.rotateAngleX = -1.041001F - rotateleft;
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
