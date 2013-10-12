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
import eldritchempires.client.gui.EldritchGuiHandler;
import eldritchempires.entity.EntityMagicEssence;
import eldritchempires.entity.EntityRabidDwarf;
import eldritchempires.entity.EntityRabidMiner;
import eldritchempires.entity.EntityRabidWarrior;
import eldritchempires.entity.EntityStoneArcher;
import eldritchempires.entity.EntityStoneMage;
import eldritchempires.entity.EntityStoneWarrior;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.TileEntitySpawner;
import eldritchempires.entity.EntityZoblin;
import eldritchempires.entity.EntityZoblinBomber;
import eldritchempires.entity.EntityZoblinBoss;
import eldritchempires.entity.EntityZoblinWarrior;
import eldritchempires.entity.projectile.EntityIceBolt;
import eldritchempires.entity.projectile.EntityNiceArrow;
import eldritchempires.item.ItemCondensedEssence;
import eldritchempires.item.ItemGolemPart;
import eldritchempires.item.ItemIceCrystal;
import eldritchempires.item.ItemInactiveCollector;
import eldritchempires.item.ItemPortal;
import eldritchempires.item.ItemCollector;
import eldritchempires.item.ItemSpawner;

public class Registration {

	public static Block collector;
//	public static Item inactiveCollector;	
	public static Block portal;	
	public static Block spawner;
	public static Item golemPart;
	public static Item condensedEssence;
	public static Item iceCrystal;
	
	public static void registration()
	{
		// Add Blocks
		collector = new BlockCollector(2864, Material.wood).setUnlocalizedName("collector");
		GameRegistry.registerBlock(collector, ItemCollector.class, EldritchEmpires.modid + (collector.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(collector, 1, 0), "Collector");
		LanguageRegistry.addName(new ItemStack(collector, 1, 1), "Active Collector");		
		
		spawner = new BlockSpawner(2865, Material.rock).setUnlocalizedName("spawner");
		GameRegistry.registerBlock(spawner, ItemSpawner.class, EldritchEmpires.modid + (spawner.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(spawner, 1, 0), "Stone Archer Spawner");
		LanguageRegistry.addName(new ItemStack(spawner, 1, 1), "Stone Mage Spawner");
		LanguageRegistry.addName(new ItemStack(spawner, 1, 2), "Stone Warrior Spawner");
	
		portal = new BlockPortal(2866).setUnlocalizedName("portal");
		GameRegistry.registerBlock(portal, ItemPortal.class, EldritchEmpires.modid + (portal.getUnlocalizedName().substring(5)));
		LanguageRegistry.addName(new ItemStack(portal, 1, 0), "Portal Focus");
		LanguageRegistry.addName(new ItemStack(portal, 1, 1), "Placeholder Portal");
		
		// Add Entities
		registerEntity(EntityZoblin.class, "Zoblin");
		registerEntity(EntityZoblinBomber.class, "Zoblin Bomber");
		registerEntity(EntityZoblinBoss.class, "Zoblin Boss");
		registerEntity(EntityZoblinWarrior.class, "Zoblin Warrior");
		registerEntity(EntityRabidDwarf.class, "Rabid Dwarf");
		registerEntity(EntityRabidMiner.class, "Rabid Miner");
		registerEntity(EntityRabidWarrior.class, "Rabid Warrior");
		
		registerEntity(EntityStoneArcher.class, "Stone Archer");
		registerEntity(EntityStoneMage.class, "Stone Mage");
		registerEntity(EntityStoneWarrior.class, "Stone Warrior");
		
		registerEntity(EntityMagicEssence.class, "Magic Essence");
		
		// Projectile Entities
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
	    EntityRegistry.registerGlobalEntityID(EntityIceBolt.class, "Ice Bolt", entityID);
	    EntityRegistry.registerModEntity(EntityIceBolt.class, "Ice Bolt", entityID, EldritchEmpires.instance, 64, 1, true);
	    
		entityID = EntityRegistry.findGlobalUniqueEntityId();
	    EntityRegistry.registerGlobalEntityID(EntityNiceArrow.class, "Nice Arrow", entityID);
	    EntityRegistry.registerModEntity(EntityNiceArrow.class, "Nice Arrow", entityID, EldritchEmpires.instance, 64, 1, true);
		
		// Add TileEntities
		GameRegistry.registerTileEntity(TileEntityCollector.class, "NodeEntity");
		GameRegistry.registerTileEntity(TileEntitySpawner.class, "SpawnerEntity");
		
		// Add Items
//		Items.addItems();
		golemPart = new ItemGolemPart(7620).setUnlocalizedName("golemPart").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(golemPart, "Golem Part");
		LanguageRegistry.addName(golemPart, "Golem Part");
		
		condensedEssence = new ItemCondensedEssence(7621).setUnlocalizedName("condensedEssence").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(condensedEssence, "Condensed Essence");
		LanguageRegistry.addName(condensedEssence, "Condensed Essence");
		
		iceCrystal = new ItemIceCrystal(7622).setUnlocalizedName("iceCrystal").setCreativeTab(EldritchEmpires.tabEldritch);
		GameRegistry.registerItem(iceCrystal, "Ice Crystal");
		LanguageRegistry.addName(iceCrystal, "Ice Crystal");
		
//		inactiveCollector = new ItemInactiveCollector(7622).setUnlocalizedName("inactiveCollector").setCreativeTab(EldritchEmpires.tabEldritch);
//		GameRegistry.registerItem(inactiveCollector, "Inactive Collector");
//		LanguageRegistry.addName(inactiveCollector, "Inactive Collector");
		
		// Add Recipes
		GameRegistry.addRecipe(new ItemStack(collector, 1, 0), new Object[] { "ROR", "ORO", "ROR", 'R', Item.redstone, 'O', new ItemStack(Block.obsidian, 1)});
//		GameRegistry.addRecipe(new ItemStack(portal, 1, 0), new Object[] { "CRC", "RER", "CRC", 'R', Item.redstone, 'E', Item.emerald, 'C', new ItemStack(Block.cobblestone, 1)});
		GameRegistry.addRecipe(new ItemStack(spawner, 1, 0), new Object[] { " E ", "GBG", "GEG", 'G', golemPart, 'B', Item.bow, 'E', condensedEssence});
		GameRegistry.addRecipe(new ItemStack(spawner, 1, 1), new Object[] { " E ", "GIG", "GEG", 'G', golemPart, 'I', iceCrystal, 'E', condensedEssence});
		GameRegistry.addRecipe(new ItemStack(golemPart, 1), new Object[] { "CCC", "III", "CCC", 'C', new ItemStack(Block.cobblestone, 1), 'I', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(iceCrystal, 1), new Object[] { "SSS", "SES", "SSS", 'S', Item.snowball, 'E', condensedEssence});
		GameRegistry.addRecipe(new ItemStack(Item.diamond, 1, 0), new Object[] { "EEE", "EEE", "EEE", 'E', condensedEssence});
//		GameRegistry.addRecipe(new ItemStack(collector, 1, 0), new Object[] { " R ", "RIR", " R ", 'R', Item.redstone, 'I', inactiveCollector});
		
		// Add Event Handler
		MinecraftForge.EVENT_BUS.register(new EldritchEvents());
		
		// Tick Handler
		TickRegistry.registerTickHandler(new EldritchTick(), Side.SERVER);
		
		// GUI Handler
		new EldritchGuiHandler();
		
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
