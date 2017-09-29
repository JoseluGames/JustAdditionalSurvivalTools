package com.jlgm.jast.item;

import com.jlgm.jast.lib.JASTConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
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
		ModelLoader.setCustomModelResourceLocation(firestick_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "firestick", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(firebow_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "firebow", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(magnesium_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "magnesium", "inventory"));
		
		ModelLoader.setCustomModelResourceLocation(knife_Item, 0,
				new ModelResourceLocation(JASTConstants.MODID + ":" + "knife", "inventory"));
		
		for(int i = 0; i < 16; i++) {
			ModelLoader.setCustomModelResourceLocation(sleepbag_Item, i,
					new ModelResourceLocation(JASTConstants.MODID + ":" + "sleepbag_" + EnumDyeColor.byMetadata(i).getUnlocalizedName(), "inventory"));
		}
		
	}
}
