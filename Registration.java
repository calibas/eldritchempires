package eldritchempires;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import eldritchempires.block.BlockPortal;
import eldritchempires.block.BlockCollector;
import eldritchempires.block.BlockSpawner;
import eldritchempires.entity.MagicEssence;
import eldritchempires.entity.StoneArcher;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.TileEntitySpawner;
import eldritchempires.entity.Zoblin;
import eldritchempires.entity.ZoblinBomber;
import eldritchempires.entity.ZoblinBoss;
import eldritchempires.item.ItemCondensedEssence;
import eldritchempires.item.ItemGolemPart;
import eldritchempires.item.ItemInactiveCollector;
import eldritchempires.item.ItemPortal;
import eldritchempires.item.ItemCollector;
import eldritchempires.item.ItemSpawner;

public class Registration {

	public static Block collector;
	public static Block spawner;
	public static Block portal;
	public static Item golemPart;
	public static Item condensedEssence;
	public static Item inactiveCollector;
	
	public static void registration()
	{
		// Add Blocks
		collector = new BlockCollector(252, Material.wood).setUnlocalizedName("collector");
		GameRegistry.registerBlock(collector, ItemCollector.class, EldritchEmpires.modid + (collector.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(collector, 1, 0), "Active Collector");
		LanguageRegistry.addName(new ItemStack(collector, 1, 1), "Inactive Collector");		
		
		spawner = new BlockSpawner(253, Material.rock).setUnlocalizedName("spawner");
		GameRegistry.registerBlock(spawner, ItemSpawner.class, EldritchEmpires.modid + (spawner.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Stone Archer Spawner");
		LanguageRegistry.addName(new ItemStack(spawner, 1, 1), "Placeholder Spawner");
	
		portal = new BlockPortal(254).setUnlocalizedName("portal");
		GameRegistry.registerBlock(portal, ItemPortal.class, EldritchEmpires.modid + (portal.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(portal, 1, 0), "Zoblin Portal");
		LanguageRegistry.addName(new ItemStack(portal, 1, 1), "Placeholder Portal");
		
		// Add Entities
		registerEntity(Zoblin.class, "Zoblin");
		registerEntity(ZoblinBomber.class, "ZoblinBomber");
		registerEntity(StoneArcher.class, "StoneArcher");
		registerEntity(MagicEssence.class, "MagicEssence");
		registerEntity(ZoblinBoss.class, "ZoblinBoss");
		
		// Add TileEntities
		GameRegistry.registerTileEntity(TileEntityCollector.class, "NodeEntity");
		GameRegistry.registerTileEntity(TileEntitySpawner.class, "SpawnerEntity");
		
		// Add Items
//		Items.addItems();
		golemPart = new ItemGolemPart(5008).setUnlocalizedName("golemPart").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(golemPart, "Golem Part");
		LanguageRegistry.addName(golemPart, "Golem Part");
		
		condensedEssence = new ItemCondensedEssence(5009).setUnlocalizedName("condensedEssence").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(condensedEssence, "Condensed Essence");
		LanguageRegistry.addName(condensedEssence, "Condensed Essence");
		
		inactiveCollector = new ItemInactiveCollector(5010).setUnlocalizedName("inactiveCollector").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(inactiveCollector, "Inactive Collector");
		LanguageRegistry.addName(inactiveCollector, "Inactive Collector");
		
		// Add Recipes
		GameRegistry.addRecipe(new ItemStack(collector, 1, 0), new Object[] { "ROR", "ORO", "ROR", 'R', Item.redstone, 'O', new ItemStack(Block.obsidian, 1)});
		GameRegistry.addRecipe(new ItemStack(portal, 1, 0), new Object[] { "CRC", "RER", "CRC", 'R', Item.redstone, 'E', Item.emerald, 'C', new ItemStack(Block.cobblestone, 1)});
		GameRegistry.addRecipe(new ItemStack(spawner, 1, 0), new Object[] { " G ", "GBG", "GGG", 'G', golemPart, 'B', Item.bow});
		GameRegistry.addRecipe(new ItemStack(golemPart, 1), new Object[] { "CCC", "III", "CCC", 'C', new ItemStack(Block.cobblestone, 1), 'I', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(Item.diamond, 1, 0), new Object[] { "EEE", "EEE", "EEE", 'E', condensedEssence});
		GameRegistry.addRecipe(new ItemStack(collector, 1, 0), new Object[] { " R ", "RIR", " R ", 'R', Item.redstone, 'I', inactiveCollector});
		
		// Add Event Handler
		MinecraftForge.EVENT_BUS.register(new EldritchEvents());
		
		// Tick Handler
		TickRegistry.registerTickHandler(new EldritchTick(), Side.SERVER);
		
		// Add Spawns
//		EntityRegistry.addSpawn(HoneyBee.class, 15, 1, 1, EnumCreatureType.monster, new BiomeGenBase[] { BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.taiga });
		

	}

	public static void registerEntity(Class entityClass, String entityName)
	{
	    int entityID = EntityRegistry.findGlobalUniqueEntityId();
	    LanguageRegistry.instance().addStringLocalization("entity." + entityName + ".name", "en_US", entityName);

	    EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
	    EntityRegistry.registerModEntity(entityClass, entityName, entityID, EldritchEmpires.instance, 64, 1, true);
	    EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityEggInfo(entityID, (16777215 - (10 * entityID)), 0));
	}
}
