package com.jlgm.jast.client.gui;

import com.jlgm.jast.inventory.ContainerCraftingTable;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class JASTGuiHandler implements IGuiHandler{
	
	public static final int CRAFTING_GUI_ID = 0;
	public static final int FURNACE_GUI_ID = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(tileEntity != null) {
			if(ID == CRAFTING_GUI_ID){
				return new ContainerCraftingTable(player.inventory, world, new BlockPos(x, y, z));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(tileEntity != null) {
			if(ID == CRAFTING_GUI_ID){
				return new GuiCraftingTable(player.inventory, world, new BlockPos(x, y, z));
			}
		}
		return null;
	}
}
