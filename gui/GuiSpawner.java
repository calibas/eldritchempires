// Borrowed largely from MrrGingerNinja's [1.6.2] Advanced Minecraft Forge Modding Tutorial #1 - Interfaces Part 3
// http://www.minecraftforum.net/topic/1931778-162-advanced-minecraft-forge-modding-tutorial-1-interfaces-part-3/

package eldritchempires.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import eldritchempires.EldritchMethods;
import eldritchempires.EldritchWorldData;
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

public class GuiSpawner extends GuiContainer{
	
	public static final ResourceLocation texture = new ResourceLocation("eldritchempires", "textures/gui/spawner.png");
	private TileEntitySpawner spawner;
	private EntityPlayer player;
    protected int xSize = 176;
    protected int ySize = 166;
//    EldritchWorldData data = new EldritchWorldData();
    
	
	public GuiSpawner(InventoryPlayer par1InventoryPlayer, TileEntitySpawner tileEntity) {
		super(new ContainerSpawner(par1InventoryPlayer, tileEntity));
		spawner = (TileEntitySpawner) tileEntity;
		player = par1InventoryPlayer.player;
	}
	
	@Override
    public void initGui()
    {
        super.initGui();
        
        GuiButton attack1 = new GuiButton(0, guiLeft + 35, guiTop + 70, 15, 15, "1");
        GuiButton attack2 = new GuiButton(1, guiLeft + 35, guiTop + 90, 15, 15, "2");
        GuiButton attack3 = new GuiButton(2, guiLeft + 35, guiTop + 110, 15, 15, "3");
        GuiButton health1 = new GuiButton(3, guiLeft + 115, guiTop + 70, 15, 15, "1");
        GuiButton health2 = new GuiButton(4, guiLeft + 115, guiTop + 90, 15, 15, "2");
        GuiButton health3 = new GuiButton(5, guiLeft + 115, guiTop + 110, 15, 15, "3");
//        GuiButton temp1 = new GuiButton(6, guiLeft + 20, guiTop + 103, 15, 15, "1");
//        GuiButton temp2 = new GuiButton(7, guiLeft + 40, guiTop + 103, 15, 15, "2");
//        GuiButton temp3 = new GuiButton(8, guiLeft + 60, guiTop + 103, 15, 15, "3");
//        GuiButton closePortal = new GuiButton(9, guiLeft + 80, guiTop + 140, 70, 20, "Close Portal");
        GuiButton closeGui = new GuiButton(10, guiLeft + 155, guiTop + 10, 15, 15, "X");
        
        attack1.enabled = true;
        attack2.enabled = false;
        attack3.enabled = false;
        health1.enabled = true;
        health2.enabled = false;
        health3.enabled = false;
//        temp1.enabled = false;
//        temp2.enabled = false;
//        temp3.enabled = false;
//        closePortal.enabled = false;
        
//        this.mc.thePlayer.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

//        if (data.isWaveActive())
//        {
//        	closePortal.enabled = true;
//        	zoblin1.enabled = false;
//        }
//
//        if (data.getProgress() >= 1 && !data.isWaveActive())
//        	zoblin2.enabled = true;
//        
//        if (data.getProgress() >= 2 && !data.isWaveActive())
//        	zoblin3.enabled = true;
//        
//        if (data.getProgress() >= 3 && !data.isWaveActive())
//        	dwarf1.enabled = true;
//      
//        if (data.getProgress() >= 4 && !data.isWaveActive())
//        	dwarf2.enabled = true;
//        
//        if (data.getProgress() >= 5 && !data.isWaveActive())
//        	dwarf3.enabled = true;
//        
//        if (data.getProgress() >= 6 && !data.isWaveActive())
//        	temp1.enabled = true;
//        
//        if (data.getProgress() >= 7 && !data.isWaveActive())
//        	temp2.enabled = true;
//        
//        if (data.getProgress() >= 8 && !data.isWaveActive())
//        	temp3.enabled = true;
        
        this.buttonList.add(attack1);
        this.buttonList.add(attack2);
        this.buttonList.add(attack3);
        this.buttonList.add(health1);
        this.buttonList.add(health2);
        this.buttonList.add(health3);
//        this.buttonList.add(temp1);
//        this.buttonList.add(temp2);
//        this.buttonList.add(temp3);
//        this.buttonList.add(closePortal);
        this.buttonList.add(closeGui);
    }
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
		case 0:
			// Attack 1
			sendPacket(1,0);
			System.out.println("set attackLevel 1");

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
			this.mc.thePlayer.closeScreen();
			break;
		case 6:
			this.mc.thePlayer.closeScreen();
			break;
		case 7:
			this.mc.thePlayer.closeScreen();
			break;
		case 8:
			this.mc.thePlayer.closeScreen();
			break;
		case 9:
//			// Close portal
//    		if (data.isWaveActive())
//    		{
////    			this.mc.theWorld.setBlockMetadataWithNotify(data.getCollectorX(), data.getCollectorY(), data.getCollectorZ(), 0, 2);
//    			data.setActiveWave(false);
//    			this.mc.theWorld.perWorldStorage.setData(EldritchWorldData.name, data);
//    			data = (EldritchWorldData) this.mc.theWorld.perWorldStorage.loadData(EldritchWorldData.class, EldritchWorldData.name);
//  //  			EldritchMethods.broadcastMessageLocal("Collector Active", par2, par3, par4, 100, par1World);
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
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawString(fontRenderer, "Current lvl:", this.guiLeft+15, this.guiTop+140, 0xFFFFFF);
        drawString(fontRenderer, "Current lvl:", this.guiLeft+100, this.guiTop+140, 0xFFFFFF);
	}
	
	private void sendPacket(int attackLevel, int healthLevel)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
		        outputStream.writeInt(attackLevel);
		        outputStream.writeInt(healthLevel);
		        outputStream.writeInt(spawner.xCoord);
		        outputStream.writeInt(spawner.yCoord);
		        outputStream.writeInt(spawner.zCoord);
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
