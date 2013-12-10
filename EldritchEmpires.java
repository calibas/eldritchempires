package eldritchempires;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
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
@NetworkMod(clientSideRequired=true, serverSideRequired=false,  channels={"Eldritch"}, packetHandler = EldritchPacketHandler.class)
public class EldritchEmpires {
	
	public static final String modid = "EldritchEmpires";
	public static int modelID;
	public static int collectorBlockID;
	public static int spawnerBlockID;
	public static int portalBlockID;
	public static int ironTrapDoorBlockID;
	public static int protectedRedstoneBlockID;
	public static int golemPartItemID;
	public static int condensedEssenceItemID;
	public static int iceCrystalItemID;
	public static int golemWandItemID;
	
    @Instance("EldritchEmpires")
    public static EldritchEmpires instance;
   
    @SidedProxy(clientSide="eldritchempires.proxy.ClientProxy", serverSide="eldritchempires.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tabEldritch = new CreativeTabsEldritch(CreativeTabs.getNextID(), "Eldritch Empires");
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	collectorBlockID = config.getBlock("BlockCollector", 2864).getInt();
    	spawnerBlockID = config.getBlock("BlockSpawner", 2865).getInt();
    	portalBlockID = config.getBlock("BlockPortal", 2866).getInt();
    	ironTrapDoorBlockID = config.getBlock("BlockIronTrapDoor", 2867).getInt();
    	protectedRedstoneBlockID = config.getBlock("BlockProtectedRedstone", 2868).getInt();
    	golemPartItemID = config.getItem("ItemGolemPart", 7620).getInt();
    	condensedEssenceItemID = config.getItem("ItemCondensedEssence", 7621).getInt();;
    	iceCrystalItemID = config.getItem("ItemIceCrystal", 7622).getInt();;
    	golemWandItemID = config.getItem("ItemGolemWand", 7623).getInt();;
    	config.save();       
    }
   
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	Registration.registration();
        proxy.registerRenderers();
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
