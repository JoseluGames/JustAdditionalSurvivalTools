package com.jlgm.jast.main;

import com.jlgm.jast.lib.JASTConstants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = JASTConstants.MODID,
	name = JASTConstants.NAME,
	version = JASTConstants.VERSION,
	acceptedMinecraftVersions = JASTConstants.ACCEPTEDMINECRAFTVERSIONS,
	updateJSON = JASTConstants.UPDATEJSON)

public class JASTMain{

	@SidedProxy(clientSide = JASTConstants.CLIENT_PROXY, serverSide = JASTConstants.SERVER_PROXY)
	public static JASTCommonProxy proxy;
	@Mod.Instance(JASTConstants.MODID)
	public static JASTMain instance;

	@Mod.EventHandler
	public static void PreInit(FMLPreInitializationEvent preInitEvent){
		proxy.preInit(preInitEvent);
	}

	@Mod.EventHandler
	public static void Init(FMLInitializationEvent initEvent){
		proxy.init(initEvent);
	}

	@Mod.EventHandler
	public static void PostInit(FMLPostInitializationEvent postInitEvent){
		proxy.postInit(postInitEvent);
	}
}
