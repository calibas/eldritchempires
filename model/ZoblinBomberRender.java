
package eldritchempires.model;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import java.nio.FloatBuffer;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import eldritchempires.entity.ZoblinBomber;

public class ZoblinBomberRender extends RenderBiped {
	
	private static final ResourceLocation textureLocation = new ResourceLocation("eldritchempires:model/texture/zoblinBomber.png");
	
	public ZoblinBomberRender() {
		super(new ZoblinBomberModel(), 0.5F);
	}

//    protected int updateCreeperColorMultiplier(EntityCreeper par1EntityCreeper, float par2, float par3)
//    {
//        float f2 = par1EntityCreeper.getCreeperFlashIntensity(par3);
//
//        if ((int)(f2 * 10.0F) % 2 == 0)
//        {
//            return 0;
//        }
//        else
//        {
//            int i = (int)(f2 * 0.2F * 255.0F);
//
//            if (i < 0)
//            {
//                i = 0;
//            }
//
//            if (i > 255)
//            {
//                i = 255;
//            }
//
//            short short1 = 255;
//            short short2 = 255;
//            short short3 = 255;
//            return i << 24 | short1 << 16 | short2 << 8 | short3;
//        }
//    }
	
	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		renderZoblinBomber((ZoblinBomber) entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1)
	{
		renderZoblinBomber((ZoblinBomber) entityliving, d, d1, d2, f,
				f1);
	}

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	GL11.glScalef(0.8F, 0.8F, 0.8F);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 100.0F);
    }

    protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
    {
        return 16777215;
    }
	
	public void renderZoblinBomber(ZoblinBomber ZoblinBomber,
			double d, double d1, double d2, float f, float f1)
	{
		super.doRenderLiving(ZoblinBomber, d, d1, d2, f, f1);
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textureLocation;
	}
}