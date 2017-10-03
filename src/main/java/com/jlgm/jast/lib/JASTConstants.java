package com.jlgm.jast.lib;

public class JASTConstants{
	//The name "behind the scenes"
	public static final String MODID = "jlgm_jast";
	//The public name
	public static final String NAME = "Just Additional Survival Tools";
	
	//Version parts ( https://mcforge.readthedocs.io/en/latest/conventions/versioning/ )
	public static final String MAJOR = "0";
	public static final String MINOR = "1";
	public static final String PATCH = "0";
	public static final String RELEASETYPE = "";
	
	//The version of the mod
	public static final String VERSION = MAJOR + "." + MINOR + "." + PATCH + RELEASETYPE;
	//The Minecraft verstion this mod is focused to work with
	public static final String ACCEPTEDMINECRAFTVERSIONS = "[1.12,)";
	
	//The package route of the proxys
	public static final String CLIENT_PROXY = "com.jlgm.jast.main.JASTClientProxy";
	public static final String SERVER_PROXY = "com.jlgm.jast.main.JASTServerProxy";
	
	//The URL to the update file
	public static final String UPDATEJSON = "https://raw.githubusercontent.com/JoseluGames/JoseluGamesUpdateCheckers/master/" + MODID + ".json";
}
