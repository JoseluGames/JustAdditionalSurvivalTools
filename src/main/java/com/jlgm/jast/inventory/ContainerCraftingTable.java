package com.jlgm.jast.inventory;

import com.jlgm.jast.block.JASTBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCraftingTable extends ContainerWorkbench{
	
    private final World world;
    /** Position of the workbench */
    private final BlockPos pos;
    private final EntityPlayer player;

	public ContainerCraftingTable(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
		super(playerInventory, worldIn, posIn);
        this.world = worldIn;
        this.pos = posIn;
        this.player = playerInventory.player;
	}
	
    /**
     * Determines whether supplied player can use this container
     */
	@Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        if (this.world.getBlockState(this.pos).getBlock() != JASTBlock.craftingTable_Block)
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
}
