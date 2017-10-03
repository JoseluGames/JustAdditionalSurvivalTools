package com.jlgm.jast.main;

import com.jlgm.jast.block.JASTBlock;
import com.jlgm.jast.client.gui.JASTGuiHandler;
import com.jlgm.jast.item.JASTItem;
import com.jlgm.jast.tileentity.JASTTileEntity;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class JASTCommonProxy{

	public void preInit(FMLPreInitializationEvent preInitEvent){
		JASTTileEntity.registerTileEntity();
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event){
		JASTBlock.registerBlocks(event);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){
		JASTBlock.registerItemBlocks(event);
		JASTItem.registerItems(event);
	}
	
	public void init(FMLInitializationEvent initEvent){
		NetworkRegistry.INSTANCE.registerGuiHandler(JASTMain.instance, new JASTGuiHandler());
	}

	public void postInit(FMLPostInitializationEvent postInitEvent){

	}
}
