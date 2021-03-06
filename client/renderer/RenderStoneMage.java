
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

import eldritchempires.entity.EntityStoneMage;
import eldritchempires.entity.EntityZoblin;

public class RenderStoneMage extends RenderBiped {
	
	private static final ResourceLocation textureLocation = new ResourceLocation("eldritchempires:textures/models/stoneMage.png");
	
	public RenderStoneMage() {
		super(new ModelBiped(), 0.5F);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		renderStoneArcher((EntityStoneMage) entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1)
	{
		renderStoneArcher((EntityStoneMage) entityliving, d, d1, d2, f,
				f1);
	}

    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
    	GL11.glScalef(0.8F, 0.8F, 0.8F);
    }

	
//	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
//    {
//        float f1 = 1.0F;
//        GL11.glColor3f(f1, f1, f1);
//        super.renderEquippedItems(par1EntityLiving, par2);
//        ItemStack itemstack = par1EntityLiving.getHeldItem();
//        ItemStack itemstack1 = par1EntityLiving.getCurrentArmor(3);
//        float f2;
//
//        if (itemstack1 != null)
//        {
//            GL11.glPushMatrix();
//            this.modelZoblinMain.head.postRender(0.0625F);
//
//            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
//            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
//
//            if (itemstack1.getItem() instanceof ItemBlock)
//            {
//                if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType()))
//                {
//                    f2 = 0.625F;
//                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
//                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
//                    GL11.glScalef(f2, -f2, -f2);
//                }
//
//                this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack1, 0);
//            }
//            else if (itemstack1.getItem().itemID == Item.skull.itemID)
//            {
//                f2 = 1.0625F;
//                GL11.glScalef(f2, -f2, -f2);
//                String s = "";
//
//                if (itemstack1.hasTagCompound() && itemstack1.getTagCompound().hasKey("SkullOwner"))
//                {
//                    s = itemstack1.getTagCompound().getString("SkullOwner");
//                }
//
//                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack1.getItemDamage(), s);
//            }
//
//            GL11.glPopMatrix();
//        }
//
//        if (itemstack != null)
//        {
//            GL11.glPushMatrix();
//
//            if (this.mainModel.isChild)
//            {
//                f2 = 0.5F;
//                GL11.glTranslatef(0.0F, 0.625F, 0.0F);
//                GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
//                GL11.glScalef(f2, f2, f2);
//            }
//
//            this.modelZoblinMain.rightarm2.postRender(0.0625F);
//            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
//
//            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
//            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));
//
//            if (itemstack.getItem() instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
//            {
//                f2 = 0.5F;
//                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
//                f2 *= 0.75F;
//                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glScalef(-f2, -f2, f2);
//            }
//            else if (itemstack.itemID == Item.bow.itemID)
//            {
//                f2 = 0.625F;
//                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
//                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
//                GL11.glScalef(f2, -f2, f2);
//                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
//            }
//            else if (Item.itemsList[itemstack.itemID].isFull3D())
//            {
//                f2 = 0.625F;
//
//                if (Item.itemsList[itemstack.itemID].shouldRotateAroundWhenRendering())
//                {
//                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
//                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
//                }
//
//                this.func_82422_c();
//                GL11.glScalef(f2, -f2, f2);
//                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
//            }
//            else
//            {
//                f2 = 0.375F;
//                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
//                GL11.glScalef(f2, f2, f2);
//                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
//                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
//            }
//
//            this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, 0);
//
//            if (itemstack.getItem().requiresMultipleRenderPasses())
//            {
//                for (int x = 1; x < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); x++)
//                {
//                    this.renderManager.itemRenderer.renderItem(par1EntityLiving, itemstack, x);
//                }
//            }
//
//            GL11.glPopMatrix();
//        }
//    }
//	
//    protected void func_82422_c()
//    {
//        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
//    }

	public void renderStoneArcher(EntityStoneMage stoneMage,
			double d, double d1, double d2, float f, float f1)
	{
		super.doRenderLiving(stoneMage, d, d1, d2, f, f1);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return textureLocation;
	}
}