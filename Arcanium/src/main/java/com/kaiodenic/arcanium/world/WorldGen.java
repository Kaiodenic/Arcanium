package com.kaiodenic.arcanium.world;

import java.util.Random;

import com.kaiodenic.arcanium.init.Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {
	private static final int chunkSize = 16;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		//generateOre(Blocks.BLOCK_SALT_GREEN.getDefaultState(), world, random, chunkX * chunkSize, chunkZ * chunkSize, 16, 64, random.nextInt(8) + 8, 16 /*56*/);
		generateSalt(world, random, chunkX * chunkSize, chunkZ * chunkSize, 16, 128, random.nextInt(12) + 24, 8 /*56*/);
	}
	
	public void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chance) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chance; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(chunkSize), minY + random.nextInt(deltaY), z + random.nextInt(chunkSize));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size);
			generator.generate(world, random, pos);
		}
	}
	
	public void generateSalt(World world, Random random, int x, int z, int minY, int maxY, int size, int chance) {
		int deltaY = maxY - minY;
		
		for (int i = 0; i < chance; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(chunkSize), minY + random.nextInt(deltaY), z + random.nextInt(chunkSize));
			
			Biome biome = world.getBiomeForCoordsBody(pos);
			WorldGenMinable generator;
			
			//biome.getBiomeName().toLowerCase().contains("ocean") ||
			//biome.getBiomeName().toLowerCase().contains("river") ||
			//biome.getTempCategory().name().toLowerCase().equals("cold")
			
			if (biome.getBaseHeight() > 0.4f) {
				generator = new WorldGenMinable(Blocks.BLOCK_SALT_RED.getDefaultState(), size);
			}
			else if (biome.getBaseHeight() < 0.0f && !biome.getBiomeName().toLowerCase().contains("river")) {
				generator = new WorldGenMinable(Blocks.BLOCK_SALT_GREY.getDefaultState(), size);
			}
			else {
				generator = new WorldGenMinable(Blocks.BLOCK_SALT_GREEN.getDefaultState(), size);
			}
			
			generator.generate(world, random, pos);
		}
	}
}
