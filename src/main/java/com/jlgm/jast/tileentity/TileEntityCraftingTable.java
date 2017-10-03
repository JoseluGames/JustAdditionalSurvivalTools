package com.jlgm.jast.tileentity;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCraftingTable extends TileEntity{
	private int durability = 32;
	
	public void decreaseDurability() {
		--this.durability;
		if(durability <= 0) {
			this.getWorld().destroyBlock(this.getPos(), false);
		}
	}
	
	public int getDurability() {
		System.out.println(this.durability);
		return this.durability;
	}
	
	public void setDurability(int newDurability) {
		this.durability = newDurability;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("durability", this.getDurability());
		return tagCompound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.setDurability(tagCompound.getInteger("durability"));
	}
	
	@Override
	public NBTTagCompound getUpdateTag(){
		NBTTagCompound tag = super.getUpdateTag();
		return this.writeToNBT(tag);
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket(){
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), tagCompound);
	}
	
	@Override
	public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt){
		NBTTagCompound tag = pkt.getNbtCompound();
		readFromNBT(tag);
	}
}
