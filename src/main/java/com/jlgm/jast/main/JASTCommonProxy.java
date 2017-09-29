package com.jlgm.jast.main;

import com.jlgm.jast.item.JASTItem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class JASTCommonProxy{

	public void preInit(FMLPreInitializationEvent preInitEvent){
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event){
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){
		JASTItem.registerItems(event);
	}
	

	public void init(FMLInitializationEvent initEvent){
	}

	public void postInit(FMLPostInitializationEvent postInitEvent){

	}
}
