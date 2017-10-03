package com.jlgm.jast.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class JASTTileEntity{
	public static void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityCraftingTable.class, "craftingtable_tileentity");
		GameRegistry.registerTileEntity(TileEntityJASTFurnace.class, "jastfurnace_tileentity");
	}
}
