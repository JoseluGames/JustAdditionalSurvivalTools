package com.jlgm.jast.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

public class ItemKnife extends ItemShears {
	
	public ItemKnife() {
		this.setMaxStackSize(1);
		this.setMaxDamage(128);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	 */
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand)
	{
		if (entity.world.isRemote)
		{
			return false;
		}
		if (entity instanceof net.minecraftforge.common.IShearable)
		{
			net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable)entity;
			BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
			if (target.isShearable(itemstack, entity.world, pos))
			{
				java.util.List<ItemStack> drops = target.onSheared(itemstack, entity.world, pos,
						net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, itemstack));

				java.util.Random rand = new java.util.Random();
				for(ItemStack stack : drops)
				{
					net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(stack, 1.0F);
					ent.motionY += rand.nextFloat() * 0.05F;
					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 0.5f);
				itemstack.damageItem(1, entity);
			}
			return true;
		}
		return false;
	}
}
