package com.kaiodenic.arcanium.init;

import java.util.ArrayList;
import java.util.List;

import com.kaiodenic.arcanium.blocks.ABlock;
import com.kaiodenic.arcanium.blocks.BlockCrystal;
import com.kaiodenic.arcanium.blocks.BlockWayCrystal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block BLOCK_SALT_GREEN = new ABlock("block_salt_green", Material.PACKED_ICE);
	public static final Block BLOCK_SALT_RED = new ABlock("block_salt_red", Material.PACKED_ICE);
	public static final Block BLOCK_SALT_GREY = new ABlock("block_salt_grey", Material.PACKED_ICE);
	public static final Block BLOCK_SALT_WHITE = new ABlock("block_salt_white", Material.PACKED_ICE);

	public static final Block BLOCK_CRYSTAL = new BlockCrystal("block_crystal", Material.GLASS);
	public static final Block BLOCK_WAY_CRYSTAL = new BlockWayCrystal("block_way_crystal", Material.GLASS);
}
