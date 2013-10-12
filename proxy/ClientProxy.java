package eldritchempires.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import eldritchempires.client.model.DwarfModel;
import eldritchempires.client.model.RabidMinerModel;
import eldritchempires.client.renderer.RenderCollector;
import eldritchempires.client.renderer.RenderDwarf;
import eldritchempires.client.renderer.RenderMagicEssence;
import eldritchempires.client.renderer.RenderRabidMiner;
import eldritchempires.client.renderer.RenderRabidWarrior;
import eldritchempires.client.renderer.RenderIceBolt;
import eldritchempires.client.renderer.RenderNiceArrow;
import eldritchempires.client.renderer.RenderStoneArcher;
import eldritchempires.client.renderer.RenderStoneMage;
import eldritchempires.client.renderer.RenderStoneWarrior;
import eldritchempires.client.renderer.RenderZoblinBomber;
import eldritchempires.client.renderer.RenderZoblinBoss;
import eldritchempires.client.renderer.RenderZoblin;
import eldritchempires.client.renderer.RenderZoblinWarrior;
import eldritchempires.entity.EntityMagicEssence;
import eldritchempires.entity.EntityRabidDwarf;
import eldritchempires.entity.EntityRabidMiner;
import eldritchempires.entity.EntityRabidWarrior;
import eldritchempires.entity.EntityStoneArcher;
import eldritchempires.entity.EntityStoneMage;
import eldritchempires.entity.EntityStoneWarrior;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.EntityZoblin;
import eldritchempires.entity.EntityZoblinBomber;
import eldritchempires.entity.EntityZoblinBoss;
import eldritchempires.entity.EntityZoblinWarrior;
import eldritchempires.entity.projectile.EntityIceBolt;
import eldritchempires.entity.projectile.EntityNiceArrow;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
            // This is for rendering entities and so forth later on
    	RenderingRegistry.registerEntityRenderingHandler(EntityZoblin.class, new RenderZoblin());
    	RenderingRegistry.registerEntityRenderingHandler(EntityZoblinBomber.class, new RenderZoblinBomber());
    	RenderingRegistry.registerEntityRenderingHandler(EntityStoneArcher.class, new RenderStoneArcher());
    	RenderingRegistry.registerEntityRenderingHandler(EntityStoneMage.class, new RenderStoneMage());
    	RenderingRegistry.registerEntityRenderingHandler(EntityStoneWarrior.class, new RenderStoneWarrior());
    	RenderingRegistry.registerEntityRenderingHandler(EntityMagicEssence.class, new RenderMagicEssence());
    	RenderingRegistry.registerEntityRenderingHandler(EntityZoblinBoss.class, new RenderZoblinBoss());
    	RenderingRegistry.registerEntityRenderingHandler(EntityZoblinWarrior.class, new RenderZoblinWarrior());
    	RenderingRegistry.registerEntityRenderingHandler(EntityRabidDwarf.class, new RenderDwarf(new DwarfModel()));
    	RenderingRegistry.registerEntityRenderingHandler(EntityRabidMiner.class, new RenderRabidMiner(new RabidMinerModel()));
    	RenderingRegistry.registerEntityRenderingHandler(EntityRabidWarrior.class, new RenderRabidWarrior(new DwarfModel()));
    	RenderingRegistry.registerEntityRenderingHandler(EntityIceBolt.class, new RenderIceBolt());
    	RenderingRegistry.registerEntityRenderingHandler(EntityNiceArrow.class, new RenderNiceArrow());
//    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNode.class, new NodeRender());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCollector.class, new RenderCollector());

    }
   
}