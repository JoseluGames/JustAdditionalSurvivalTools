package com.jlgm.jast.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jlgm.jast.tileentity.TileEntityJASTFurnace;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockJASTFurnace extends BlockFurnace{

	protected BlockJASTFurnace(boolean isBurning) {
		super(isBurning);
		if(!isBurning)
			this.setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if (worldIn.isRemote){
			return true;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityFurnace){
				playerIn.displayGUIChest((TileEntityFurnace)tileentity);
				playerIn.addStat(StatList.FURNACE_INTERACTION);
			}

			return true;
		}
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(JASTBlock.furnace_Block);
	}
	
	/**
	 * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
	 */
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityJASTFurnace){
			TileEntityJASTFurnace tileEntityJAST = (TileEntityJASTFurnace)tileentity;
			
			NBTTagCompound nbt = new NBTTagCompound();
			ItemStack itemStack = new ItemStack(JASTBlock.furnace_Block);
			TileEntity te = worldIn.getTileEntity(pos);
			TileEntityJASTFurnace tileEntity = (TileEntityJASTFurnace) te;
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
		ItemStack itemStack = new ItemStack(JASTBlock.furnace_Block);
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityJASTFurnace) {
			TileEntityJASTFurnace tileEntity = (TileEntityJASTFurnace) te;
			nbt.setInteger("durability", tileEntity.getDurability());
		}
		itemStack.setTagCompound(nbt);
		return itemStack;
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos){
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		keepInventory = true;

		if (active)
		{
			worldIn.setBlockState(pos, JASTBlock.furnaceLit_Block.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, JASTBlock.furnaceLit_Block.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		} else {
			worldIn.setBlockState(pos, JASTBlock.furnace_Block.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
			worldIn.setBlockState(pos, JASTBlock.furnace_Block.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		}

		keepInventory = false;

		if (tileentity != null){
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta){
		return new TileEntityJASTFurnace();
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
