// Borrowed largely from MrrGingerNinja's [1.6.2] Advanced Minecraft Forge Modding Tutorial #1 - Interfaces Part 3
// http://www.minecraftforum.net/topic/1931778-162-advanced-minecraft-forge-modding-tutorial-1-interfaces-part-3/

package eldritchempires.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import eldritchempires.EldritchMethods;
import eldritchempires.entity.TileEntityCollector;
import eldritchempires.entity.TileEntitySpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCollector extends GuiContainer{
	
	public static final ResourceLocation texture = new ResourceLocation("eldritchempires", "textures/gui/collector.png");
	private TileEntityCollector collector;
	private EntityPlayer player;
	final public int packetID = 3;
    protected int xSize = 176;
    protected int ySize = 166;
//    EldritchWorldData data = new EldritchWorldData();
    
	
	public GuiCollector(InventoryPlayer par1InventoryPlayer, TileEntityCollector tileEntity) {
		super(new ContainerCollector(par1InventoryPlayer, tileEntity));
		collector = (TileEntityCollector) tileEntity;
		player = par1InventoryPlayer.player;
	}
	
	@Override
    public void initGui()
    {
        super.initGui();
        
        GuiButton zoblin1 = new GuiButton(0, guiLeft + 20, guiTop + 40, 15, 15, "1");
        GuiButton zoblin2 = new GuiButton(1, guiLeft + 40, guiTop + 40, 15, 15, "2");
        GuiButton zoblin3 = new GuiButton(2, guiLeft + 60, guiTop + 40, 15, 15, "3");
        GuiButton dwarf1 = new GuiButton(3, guiLeft + 20, guiTop + 71, 15, 15, "1");
        GuiButton dwarf2 = new GuiButton(4, guiLeft + 40, guiTop + 71, 15, 15, "2");
        GuiButton dwarf3 = new GuiButton(5, guiLeft + 60, guiTop + 71, 15, 15, "3");
        GuiButton temp1 = new GuiButton(6, guiLeft + 20, guiTop + 103, 15, 15, "1");
        GuiButton temp2 = new GuiButton(7, guiLeft + 40, guiTop + 103, 15, 15, "2");
        GuiButton temp3 = new GuiButton(8, guiLeft + 60, guiTop + 103, 15, 15, "3");
        GuiButton closePortal = new GuiButton(9, guiLeft + 80, guiTop + 140, 70, 20, "Close Portal");
        GuiButton closeGui = new GuiButton(10, guiLeft + 145, guiTop + 10, 15, 15, "X");
        
        zoblin1.enabled = true;
        zoblin2.enabled = false;
        zoblin3.enabled = false;
        dwarf1.enabled = false;
        dwarf2.enabled = false;
        dwarf3.enabled = false;
        temp1.enabled = false;
        temp2.enabled = false;
        temp3.enabled = false;
        closePortal.enabled = false;
        
//        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        if (collector.roundActive)
        {
        	closePortal.enabled = true;
        	zoblin1.enabled = false;
        }

        if (collector.progress >= 2 && !collector.roundActive)
        	zoblin2.enabled = true;
        
        if (collector.progress >= 3 && !collector.roundActive)
        	zoblin3.enabled = true;
        
        if (collector.progress >= 4 && !collector.roundActive)
        	dwarf1.enabled = true;
      
        if (collector.progress >= 5 && !collector.roundActive)
        	dwarf2.enabled = true;
        
        if (collector.progress >= 6 && !collector.roundActive)
        	dwarf3.enabled = true;
        
        if (collector.progress >= 7 && !collector.roundActive)
        	temp1.enabled = true;
        
        if (collector.progress >= 8 && !collector.roundActive)
        	temp2.enabled = true;
        
        if (collector.progress >= 9 && !collector.roundActive)
        	temp3.enabled = true;
        
        this.buttonList.add(zoblin1);
        this.buttonList.add(zoblin2);
        this.buttonList.add(zoblin3);
        this.buttonList.add(dwarf1);
        this.buttonList.add(dwarf2);
        this.buttonList.add(dwarf3);
        this.buttonList.add(temp1);
        this.buttonList.add(temp2);
        this.buttonList.add(temp3);
        this.buttonList.add(closePortal);
        this.buttonList.add(closeGui);
    }
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0:
			// Zoblin 1
			sendPacket(1, 1);
//    		if (!data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 1, 2);
//    			data.setActiveWave(true);
//    			data.setRound(1);
//    			data.resetCollectorHealth();
//    			EldritchMethods.broadcastMessageLocal("A zoblin portal begins to open", data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 100, this.mc.theWorld);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    		}
    		this.mc.thePlayer.closeScreen();
			break;
		case 1:
			// Zoblin 2
			sendPacket(2, 1);
//    		if (!data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 1, 2);
//    			data.setActiveWave(true);
//    			data.setRound(2);
//    			data.resetCollectorHealth();
//    			EldritchMethods.broadcastMessageLocal("A zoblin portal begins to open", data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 100, this.mc.theWorld);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    		}
			this.mc.thePlayer.closeScreen();
			break;
		case 2:
			// Zoblin 3
			sendPacket(3, 1);
//    		if (!data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 1, 2);
//    			data.setActiveWave(true);
//    			data.setRound(3);
//    			data.resetCollectorHealth();
//    			EldritchMethods.broadcastMessageLocal("A zoblin portal begins to open", data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 100, this.mc.theWorld);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    		}
			this.mc.thePlayer.closeScreen();
			break;
		case 3:
			// Dwarf 1
			sendPacket(4, 1);
//    		if (!data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 1, 2);
//    			data.setActiveWave(true);
//    			data.setRound(4);
//    			data.resetCollectorHealth();
//    			EldritchMethods.broadcastMessageLocal("A rabid dwarf portal begins to open", data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 100, this.mc.theWorld);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    		}
			this.mc.thePlayer.closeScreen();
			break;
		case 4:
			// Dwarf 2
			sendPacket(5, 1);
//    		if (!data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 1, 2);
//    			data.setActiveWave(true);
//    			data.setRound(5);
//    			data.resetCollectorHealth();
//    			EldritchMethods.broadcastMessageLocal("A rabid dwarf portal begins to open", data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 100, this.mc.theWorld);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    		}
			this.mc.thePlayer.closeScreen();
			break;
		case 5:
			// Dwarf 3
			this.mc.thePlayer.closeScreen();
			break;
		case 6:
			// Temp 1
			this.mc.thePlayer.closeScreen();
			break;
		case 7:
			// Temp 2
			this.mc.thePlayer.closeScreen();
			break;
		case 8:
			// Temp 3
			this.mc.thePlayer.closeScreen();
			break;
		case 9:
			// Close portal
			sendPacket(0,0);
//    		if (data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 0, 2);
////    			data.setActiveWave(false);
////    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
////    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
//    			EldritchEvents.endRound(this.mc.theWorld);
//    		}
    		this.mc.thePlayer.closeScreen();
			break;
		case 10:
			this.mc.thePlayer.closeScreen();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		this.mc.getTextureManager().bindTexture(texture);
//		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
//		.func_110434_K().func_110577_a(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
//		drawString(fontRenderer, "Zoblins", this.guiLeft+20, this.guiTop+20, 0x000000);
	}
	
//	@Override
//    public void drawScreen(int x, int y, float f)
//    {
//		drawString(fontRenderer, "Zoblins", this.guiLeft+20, this.guiTop+20, 0x000000);
//		super.drawScreen(x,y,f);
//    }
	
	private void sendPacket(int round, int active)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
				outputStream.writeInt(packetID);
		        outputStream.writeInt(round);
		        outputStream.writeInt(active);
		        outputStream.writeInt(collector.xCoord);
		        outputStream.writeInt(collector.yCoord);
		        outputStream.writeInt(collector.zCoord);
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Eldritch";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		EntityClientPlayerMP mpPlayer = (EntityClientPlayerMP) player;
        mpPlayer.sendQueue.addToSendQueue(packet);
	}

}
