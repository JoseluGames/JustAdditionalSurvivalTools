package com.jlgm.jast.block;

import com.jlgm.jast.lib.JASTConstants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;

public class JASTBlock{
	
	public static Block craftingTable_Block;
	public static ItemBlock craftingTable_ItemBlock;
	
	public static Block furnace_Block;
	public static ItemBlock furnace_ItemBlock;
	
	public static Block furnaceLit_Block;
	public static ItemBlock furnaceLit_ItemBlock;
	
	public static void registerBlocks(RegistryEvent.Register<Block> event){
		craftingTable_Block = new BlockCraftingTable().setUnlocalizedName("workbench");
		event.getRegistry().register(craftingTable_Block.setRegistryName("crafting_table"));

		furnace_Block = new BlockJASTFurnace(false).setUnlocalizedName("furnace");
		event.getRegistry().register(furnace_Block.setRegistryName("furnace"));

		furnaceLit_Block = new BlockJASTFurnace(true).setUnlocalizedName("lit_furnace");
		event.getRegistry().register(furnaceLit_Block.setRegistryName("lit_furnace"));
	}

	public static void registerItemBlocks(RegistryEvent.Register<Item> event){
		craftingTable_ItemBlock = new ItemBlock(craftingTable_Block);
		event.getRegistry().register(craftingTable_ItemBlock.setRegistryName(craftingTable_Block.getRegistryName()));

		furnace_ItemBlock = new ItemBlock(furnace_Block);
		event.getRegistry().register(furnace_ItemBlock.setRegistryName(furnace_Block.getRegistryName()));

		furnaceLit_ItemBlock = new ItemBlock(furnaceLit_Block);
		event.getRegistry().register(furnaceLit_ItemBlock.setRegistryName(furnaceLit_Block.getRegistryName()));
	}
	
	public static void renderBlock(){
		ModelLoader.setCustomModelResourceLocation(craftingTable_ItemBlock, 0,
				new ModelResourceLocation("minecraft:crafting_table"));
		
		ModelLoader.setCustomModelResourceLocation(furnace_ItemBlock, 0,
				new ModelResourceLocation("minecraft:furnace"/*, "inventory"*/));

		ModelLoader.setCustomModelResourceLocation(furnaceLit_ItemBlock, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "lit_furnace", "inventory"));
		/*ModelLoader.setCustomModelResourceLocation(furnace_ItemBlock, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "furnace", "inventory"));

		ModelLoader.setCustomModelResourceLocation(furnaceLit_ItemBlock, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "lit_furnace", "inventory"));*/
	}
}
