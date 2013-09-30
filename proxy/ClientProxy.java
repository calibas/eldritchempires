package eldritchempires.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import eldritchempires.entity.MagicEssence;
import eldritchempires.entity.StoneArcher;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.Zoblin;
import eldritchempires.entity.ZoblinBomber;
import eldritchempires.entity.ZoblinBoss;
import eldritchempires.entity.ZoblinWarrior;
import eldritchempires.model.CollectorRender;
import eldritchempires.model.MagicEssenceRender;
import eldritchempires.model.NodeRender;
import eldritchempires.model.StoneArcherRender;
import eldritchempires.model.ZoblinBomberRender;
import eldritchempires.model.ZoblinBossRender;
import eldritchempires.model.ZoblinRender;
import eldritchempires.model.ZoblinWarriorRender;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
            // This is for rendering entities and so forth later on
    	RenderingRegistry.registerEntityRenderingHandler(Zoblin.class, new ZoblinRender());
    	RenderingRegistry.registerEntityRenderingHandler(ZoblinBomber.class, new ZoblinBomberRender());
    	RenderingRegistry.registerEntityRenderingHandler(StoneArcher.class, new StoneArcherRender());
    	RenderingRegistry.registerEntityRenderingHandler(MagicEssence.class, new MagicEssenceRender());
    	RenderingRegistry.registerEntityRenderingHandler(ZoblinBoss.class, new ZoblinBossRender());
    	RenderingRegistry.registerEntityRenderingHandler(ZoblinWarrior.class, new ZoblinWarriorRender());
//    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNode.class, new NodeRender());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCollector.class, new CollectorRender());

    }
   
}