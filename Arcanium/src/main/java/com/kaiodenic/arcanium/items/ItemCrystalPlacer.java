package com.kaiodenic.arcanium.items;

import com.kaiodenic.arcanium.init.Blocks;
import com.kaiodenic.arcanium.init.EnumSelection;
import com.kaiodenic.arcanium.tilentity.TileEntityBlockWayCrystal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCrystalPlacer extends AItem {
	TileEntity selectedBlock;
	EnumSelection selectedBlockType;

	public ItemCrystalPlacer(String name) {
		super(name);
		
		initSelection();
	}
	
	private void initSelection() {
		selectedBlock = null;
		selectedBlockType = EnumSelection.NONE;
	}
	
	@Override
	
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos inPos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
		BlockPos pos = inPos.offset(facing);
		
		IBlockState blockState = world.getBlockState(pos);
		
		if (blockState.getBlock().isReplaceable(world, pos)) {
			System.out.println("----- Is Replaceable");
			world.setBlockState(pos, Blocks.BLOCK_WAY_CRYSTAL.getStateForPlacement(world, pos, facing, x, y, z, 0, player, hand));
			
			TileEntity tileEntity = world.getTileEntity(pos);

			if (tileEntity instanceof TileEntityBlockWayCrystal) {
				if (selectedBlockType == EnumSelection.NONE) {
					selectedBlock = world.getTileEntity(pos);
					selectedBlockType = EnumSelection.BLOCK_WAY_CRYSTAL;
					
					return EnumActionResult.SUCCESS;
				}
				else if (selectedBlockType == EnumSelection.BLOCK_WAY_CRYSTAL){
					((TileEntityBlockWayCrystal)tileEntity).pair((TileEntityBlockWayCrystal)selectedBlock);
					selectedBlock = world.getTileEntity(pos);
					selectedBlockType = EnumSelection.BLOCK_WAY_CRYSTAL;

					return EnumActionResult.SUCCESS;
				}
			}
			
			return EnumActionResult.PASS;
		}
		
		return EnumActionResult.PASS;
	}
}
