package com.kaiodenic.arcanium;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.Biome;

public class BiomeList {
	private List<Biome> biomes;
	
	public BiomeList() {
		biomes = new ArrayList<Biome>();
	}
	
	public BiomeList(Biome[] biomes) {
		this.biomes = new ArrayList<Biome>();
		
		for (Biome biome : biomes)
			this.biomes.add(biome);
	}
	
	public void addBiome(Biome biome) {
		biomes.add(biome);
	}
	
	public void addBiomes(Biome[] biomes) {
		for (Biome biome : biomes)
			if (!Contains(biome))
				this.biomes.add(biome);
	}
	
	public List<Biome> getBiomes() {
		return biomes;
	}
	
	public boolean Contains(Biome biome) {
		for (Biome b : biomes)
			if (b.getBiomeName().equals(biome.getBiomeName()))
				return true;
		
		return false;
	}
}
