package eldritchempires.client.particle;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class EntityZPortalFX extends EntityFX
{
	float reddustParticleScale;

	public EntityZPortalFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
	{
		this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
	}

	public EntityZPortalFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10, float par11)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.50000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		
		if (par9 == 0.0F)
		{
			par9 = 1.0F;
		}

		float var12 = (float)Math.random() * 0.4F + 0.6F;
		this.particleRed = 0;
		this.particleGreen = 7 + 2 * (float)Math.random();
		this.particleBlue = 0;
		this.particleScale *= 0.75F;
		this.particleScale *= par8;
		this.reddustParticleScale = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)((float)this.particleMaxAge * par8);
		this.noClip = false;
	}

	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		float var8 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 32.0F;

		if (var8 < 0.0F)
		{
			var8 = 0.0F;
		}

		if (var8 > 1.0F)
		{
			var8 = 1.0F;
		}

		this.particleScale = this.reddustParticleScale * var8;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
	}

	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}

//@SideOnly(Side.CLIENT)
//public class EntityZPortalFX extends EntityFX
//{
//    public EntityZPortalFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
//    {
//        super(par1World, par2, par4, par6, par8, par10, par12);
//        this.particleRed = 1.0F;
//        this.particleGreen = 1.0F;
//        this.particleBlue = 1.0F;
//        this.setParticleTextureIndex(32);
//        this.setSize(0.02F, 0.02F);
//        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
//        this.motionX = par8 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
//        this.motionY = par10 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
//        this.motionZ = par12 * 0.20000000298023224D + (double)((float)(Math.random() * 2.0D - 1.0D) * 0.02F);
//        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
//    }
//
//    /**
//     * Called to update the entity's position/logic.
//     */
//    public void onUpdate()
//    {
//        this.prevPosX = this.posX;
//        this.prevPosY = this.posY;
//        this.prevPosZ = this.posZ;
//        this.motionY += 0.002D;
//        this.moveEntity(this.motionX, this.motionY, this.motionZ);
//        this.motionX *= 0.8500000238418579D;
//        this.motionY *= 0.8500000238418579D;
//        this.motionZ *= 0.8500000238418579D;
//
//        if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) != Material.water)
//        {
//            this.setDead();
//        }
//
//        if (this.particleMaxAge-- <= 0)
//        {
//            this.setDead();
//        }
//    }
//}
