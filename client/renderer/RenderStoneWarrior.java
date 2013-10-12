
package eldritchempires.client.renderer;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import eldritchempires.entity.EntityStoneWarrior;
import eldritchempires.entity.EntityZoblin;

public class RenderStoneWarrior extends RenderBiped {
	
	private static final ResourceLocation textureLocation = new ResourceLocation("eldritchempires:textures/models/stoneArcher.png");
	
	public RenderStoneWarrior() {
		super(new ModelBiped(), 0.5F);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		renderStoneArcher((EntityStoneWarrior) entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1)
	{
		renderStoneArcher((EntityStoneWarrior) entityliving, d, d1, d2, f,
				f1);
	}

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	GL11.glScalef(0.8F, 0.8F, 0.8F);
    }

	public void renderStoneArcher(EntityStoneWarrior StoneArcher,
			double d, double d1, double d2, float f, float f1)
	{
		super.doRenderLiving(StoneArcher, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textureLocation;
	}
}