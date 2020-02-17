package com.kaiodenic.arcanium.blocks;

import net.minecraft.block.material.Material;

public class BlockSaltGreen extends ABlock {
	public static final int maxDropped = 4;
	public static final int minDropped = 2;

	public BlockSaltGreen(String name, Material material) {
		super(name, material);
		setHardness(1.5f);
		setHarvestLevel("pickaxe", 1);
	}
	
	/*
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.CRYSTAL;
	}
	
	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(1 + maxDropped - minDropped) + minDropped;
	}*/
}
