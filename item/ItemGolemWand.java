package eldritchempires.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eldritchempires.EldritchEmpires;
import eldritchempires.entity.TileEntitySpawner;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class ItemGolemWand extends Item{

	final public int packetID = 2;
	
	public ItemGolemWand(int par1) {
		super(par1);
	}

	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	if (!par3World.isRemote && par3World.getBlockTileEntity(par4, par5, par6) instanceof TileEntitySpawner)
    	{
    		sendPacket(par4, par5, par6, par2EntityPlayer, par3World);
    		FMLNetworkHandler.openGui(par2EntityPlayer, EldritchEmpires.instance, 1, par3World, par4, par5, par6);
    		return true;
    	}
    	else
    		return false;
}
	
	private void sendPacket(int x, int y, int z, EntityPlayer entityPlayer, World world)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		TileEntitySpawner tileEntity = (TileEntitySpawner)world.getBlockTileEntity(x, y, z);
		int attackLevel = tileEntity.attackLevel;
		int healthLevel = tileEntity.healthLevel;
		try {
				outputStream.writeInt(packetID);
		        outputStream.writeInt(attackLevel);
		        outputStream.writeInt(healthLevel);
		        outputStream.writeInt(x);
		        outputStream.writeInt(y);
		        outputStream.writeInt(z);
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Eldritch";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
        PacketDispatcher.sendPacketToPlayer(packet, (Player)entityPlayer);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("eldritchempires:" + (this.getUnlocalizedName().substring(5)));
    }
}
