package eldritchempires;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eldritchempires.proxy.CommonProxy;

@Mod(modid="EldritchEmpires", name="Eldritch Empires", version="0.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class EldritchEmpires {
	
	public static final String modid = "EldritchEmpires";
	public static int modelID;
	
    @Instance("EldritchEmpires")
    public static EldritchEmpires instance;
   
    @SidedProxy(clientSide="eldritchempires.proxy.ClientProxy", serverSide="eldritchempires.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tabEldritch = new CreativeTabsEldritch(CreativeTabs.getNextID(), "Eldritch Empires");
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
    }
   
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	Registration.registration();
        proxy.registerRenderers();
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
}
