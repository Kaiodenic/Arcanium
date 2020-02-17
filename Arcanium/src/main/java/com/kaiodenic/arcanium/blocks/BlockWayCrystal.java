package com.kaiodenic.arcanium.blocks;

import com.kaiodenic.arcanium.tilentity.TileEntityBlockWayCrystal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockWayCrystal extends BlockCrystal implements ITileEntityProvider {
	
	BlockPos lastWaypoint;

	public BlockWayCrystal(String name, Material material) {
		super(name, material);
		lastWaypoint = new BlockPos(0.0f, 0.0f, 0.0f);
	}
	
	public BlockWayCrystal(String name, Material material, BlockPos waypoint) {
		super(name, material);
		lastWaypoint = waypoint;
	}
	
	public void onBlockBreak(World world, BlockPos pos) {
		TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntityBlockWayCrystal) {
        	((TileEntityBlockWayCrystal)tileentity).onBlockBreak();
        }
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		onBlockBreak(world, pos);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		onBlockBreak(world, pos);
	}

	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		onBlockBreak(world, pos);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		onBlockBreak(world, pos);
	}
	
	/*
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        else {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityBlockWayCrystal) {
            	((TileEntityBlockWayCrystal)tileentity).printTestStatement(" ------------------------------- Clicked From (" + playerIn.getPosition().getX() + ", " + playerIn.getPosition().getY() + ", " + playerIn.getPosition().getZ() + ")");
            }
            else {
            	System.out.println("---------------- Incorrect");
            }

            return true;
        }
    }*/
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBlockWayCrystal();
    }
}
