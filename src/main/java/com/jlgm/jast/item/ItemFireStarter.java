package com.jlgm.jast.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFireStarter extends Item{
	
	private int fireChance;
	
	public ItemFireStarter(int fireChance) {
		this.fireChance = fireChance;
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		BlockPos originalPos = pos;
		pos = pos.offset(facing);
		ItemStack itemstack = player.getHeldItem(hand);
		
		if(worldIn.isRemote) {
			float x = originalPos.getX() + hitX;
			float y = originalPos.getY() + hitY;
			float z = originalPos.getZ() + hitZ;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
			if(fireChance == 1) {
				if(worldIn.rand.nextBoolean()) {
					worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 1.0D, 0.6D, 0.0D);
					worldIn.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, 1.0D, 0.6D, 0.0D);
				}
			}
		}
		
		if(!worldIn.getBlockState(originalPos).getBlock().isFlammable(worldIn, originalPos, facing)) {
			player.sendStatusMessage(new TextComponentTranslation("item.firestarter.noburnable", new Object[0]), true);
			return EnumActionResult.FAIL;
		}
		
		if(!player.canPlayerEdit(pos, facing, itemstack)) {
			return EnumActionResult.FAIL;
		}
		
		if(!worldIn.isRemote) {
			if(worldIn.rand.nextInt(fireChance) != 0){
				itemstack.damageItem(1, player);
				return EnumActionResult.SUCCESS;
			} else {
				if(worldIn.isAirBlock(pos)) {
					worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
					worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
				}
				
				if(player instanceof EntityPlayerMP) {
					CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
				}
				
				itemstack.damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
			
		}
		return EnumActionResult.SUCCESS;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.translateToLocal("item.firestarter.onlyburnable"));
	}
}
