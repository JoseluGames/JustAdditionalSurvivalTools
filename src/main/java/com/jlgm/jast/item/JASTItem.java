package com.jlgm.jast.item;

import com.jlgm.jast.lib.JASTConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class JASTItem {
	
	public static Item firestick_Item;
	public static Item firebow_Item;
	public static Item magnesium_Item;
	public static Item knife_Item;
	public static Item sleepbag_Item;
	
	public static void registerItems(RegistryEvent.Register<Item> event){
		firestick_Item = new ItemFireStarter(12).setUnlocalizedName("firestick").setMaxDamage(16);
		event.getRegistry().register(firestick_Item.setRegistryName("firestick"));
		
		firebow_Item = new ItemFireStarter(8).setUnlocalizedName("firebow").setMaxDamage(48);
		event.getRegistry().register(firebow_Item.setRegistryName("firebow"));
		
		magnesium_Item = new ItemFireStarter(1).setUnlocalizedName("magnesium").setMaxDamage(64);
		event.getRegistry().register(magnesium_Item.setRegistryName("magnesium"));
		
		knife_Item = new ItemKnife().setUnlocalizedName("knife");
		event.getRegistry().register(knife_Item.setRegistryName("knife"));
		
		sleepbag_Item = new ItemSleepbag().setUnlocalizedName("sleepbag");
		event.getRegistry().register(sleepbag_Item.setRegistryName("sleepbag"));
	}
	
	public static void renderItem(){
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
		renderItem.getItemModelMesher().register(firestick_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "firestick", "inventory"));
		
		renderItem.getItemModelMesher().register(firebow_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "firebow", "inventory"));
		
		renderItem.getItemModelMesher().register(magnesium_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "magnesium", "inventory"));
		
		renderItem.getItemModelMesher().register(knife_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "knife", "inventory"));
		
		renderItem.getItemModelMesher().register(sleepbag_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "sleepbag", "inventory"));
		
	}
}
