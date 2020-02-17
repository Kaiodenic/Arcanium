package com.kaiodenic.arcanium.items;

import com.kaiodenic.arcanium.blocks.BlockWayCrystal;
import com.kaiodenic.arcanium.init.EnumSelection;
import com.kaiodenic.arcanium.tilentity.TileEntityBlockWayCrystal;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWandBasic extends AItem {
	TileEntity selectedBlock;
	EnumSelection selectedBlockType;

	public ItemWandBasic(String name) {
		super(name);
		
		initSelection();
	}
	
	private void initSelection() {
		selectedBlock = null;
		selectedBlockType = EnumSelection.NONE;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		if (world.isRemote) {
			System.out.println("selection type :: " + selectedBlockType);
			
			System.out.println("clicked 1");
			if (player.isSneaking()) {
				System.out.println("clicked 2");
				IBlockState blockState = world.getBlockState(pos);
				Block block = blockState.getBlock();
				
				if (block instanceof BlockWayCrystal) {
					System.out.println("clicked 3");
					if (selectedBlockType == EnumSelection.NONE) {
						System.out.println("clicked 4.1");
						TileEntity tileEntity = world.getTileEntity(pos);
						
						if (tileEntity instanceof TileEntityBlockWayCrystal) {
							System.out.println("clicked 5.1");
							selectedBlock = world.getTileEntity(pos);
							selectedBlockType = EnumSelection.BLOCK_WAY_CRYSTAL;
							return EnumActionResult.SUCCESS;
						}
					}
					else if (selectedBlockType == EnumSelection.BLOCK_WAY_CRYSTAL) {
						System.out.println("clicked 4.2");
						TileEntity tileEntity = world.getTileEntity(pos);
						
						if (tileEntity instanceof TileEntityBlockWayCrystal) {
							System.out.println("clicked 5.2");
							((TileEntityBlockWayCrystal)tileEntity).pair((TileEntityBlockWayCrystal)selectedBlock);

							initSelection();
							return EnumActionResult.SUCCESS;
						}
					}
				}
			}
		}
		else {
			if (player.isSneaking()) {
				IBlockState blockState = world.getBlockState(pos);
				Block block = blockState.getBlock();
				
				if (block instanceof BlockWayCrystal) {
					return EnumActionResult.SUCCESS;
				}
			}
		}
		
		return EnumActionResult.PASS;
	}
}
