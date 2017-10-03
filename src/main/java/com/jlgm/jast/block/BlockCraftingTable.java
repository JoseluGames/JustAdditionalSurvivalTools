package com.jlgm.jast.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jlgm.jast.client.gui.JASTGuiHandler;
import com.jlgm.jast.main.JASTMain;
import com.jlgm.jast.tileentity.TileEntityCraftingTable;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCraftingTable extends BlockWorkbench implements ITileEntityProvider{
	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		playerIn.openGui(JASTMain.instance, JASTGuiHandler.CRAFTING_GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		
		if (worldIn.isRemote){
			return true;
		} else {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te != null && te instanceof TileEntityCraftingTable) {
				TileEntityCraftingTable tileEntity = (TileEntityCraftingTable) te;
				tileEntity.decreaseDurability();
			}
			
			playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
			return true;
		}
	}
	
	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(JASTBlock.craftingTable_Block);
	}
	
	/**
	 * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
	 */
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityCraftingTable){
			TileEntityCraftingTable tileEntityJAST = (TileEntityCraftingTable)tileentity;
			
			NBTTagCompound nbt = new NBTTagCompound();
			ItemStack itemStack = new ItemStack(JASTBlock.craftingTable_Block);
			TileEntity te = worldIn.getTileEntity(pos);
			TileEntityCraftingTable tileEntity = (TileEntityCraftingTable) te;
			nbt.setInteger("durability", tileEntity.getDurability());
			itemStack.setTagCompound(nbt);
			spawnAsEntity(worldIn, pos, itemStack);
		}

		super.breakBlock(worldIn, pos, state);
	}
	
	/**
	 * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
	 * Block.removedByPlayer
	 */
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack){}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state){
		NBTTagCompound nbt = new NBTTagCompound();
		ItemStack itemStack = new ItemStack(JASTBlock.craftingTable_Block);
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityCraftingTable) {
			TileEntityCraftingTable tileEntity = (TileEntityCraftingTable) te;
			nbt.setInteger("durability", tileEntity.getDurability());
		}
		itemStack.setTagCompound(nbt);
		return itemStack;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCraftingTable();
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced){
		super.addInformation(stack, player, tooltip, advanced);
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
			if(nbt.hasKey("durability")) {
				tooltip.add("Uses left: " + nbt.getInteger("durability"));
			}
		}
	}
}
