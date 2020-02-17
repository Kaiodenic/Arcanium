package com.kaiodenic.arcanium;

import com.kaiodenic.arcanium.proxy.CommonProxy;
import com.kaiodenic.arcanium.tilentity.TileEntityBlockWayCrystal;
import com.kaiodenic.arcanium.tilentity.TileEntityCrystal;
import com.kaiodenic.arcanium.utils.Refs;
import com.kaiodenic.arcanium.world.WorldGen;

import net.minecraft.init.Biomes;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Refs.MOD_ID, name = Refs.NAME, version = Refs.VERSION)
public class Main {
	
	@Instance
	public static Main _instance;
	
	@SidedProxy(clientSide = Refs.CLIENT_PROXY_CLASS, serverSide = Refs.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		OBJLoader.INSTANCE.addDomain(Refs.MOD_ID);
		
		GameRegistry.registerWorldGenerator(new WorldGen(), 3);
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "tileEntityCrystal");
		GameRegistry.registerTileEntity(TileEntityBlockWayCrystal.class, "tileEntityBlockWayCrystal");
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		System.out.println("Variation [00][BEACH] :: " + Biomes.BEACH.getBaseHeight());
		System.out.println("Variation [01][BIRCH_FOREST] :: " + Biomes.BIRCH_FOREST.getBaseHeight());
		System.out.println("Variation [02][BIRCH_FOREST_HILLS] :: " + Biomes.BIRCH_FOREST_HILLS.getBaseHeight());
		System.out.println("Variation [04][COLD_BEACH] :: " + Biomes.COLD_BEACH.getBaseHeight());
		System.out.println("Variation [05][COLD_TAIGA] :: " + Biomes.COLD_TAIGA.getBaseHeight());
		System.out.println("Variation [06][COLD_TAIGA_HILLS] :: " + Biomes.COLD_TAIGA_HILLS.getBaseHeight());
		System.out.println("Variation [07][DEEP_OCEAN] :: " + Biomes.DEEP_OCEAN.getBaseHeight());
		System.out.println("Variation [08][DEFAULT] :: " + Biomes.DEFAULT.getBaseHeight());
		System.out.println("Variation [09][DESERT] :: " + Biomes.DESERT.getBaseHeight());
		System.out.println("Variation [10][DESERT_HILLS] :: " + Biomes.DESERT_HILLS.getBaseHeight());
		System.out.println("Variation [11][EXTREME_HILLS] :: " + Biomes.EXTREME_HILLS.getBaseHeight());
		System.out.println("Variation [12][EXTREME_HILLS_EDGE] :: " + Biomes.EXTREME_HILLS_EDGE.getBaseHeight());
		System.out.println("Variation [13][EXTREME_HILLS_WITH_TREES] :: " + Biomes.EXTREME_HILLS_WITH_TREES.getBaseHeight());
		System.out.println("Variation [14][FOREST] :: " + Biomes.FOREST.getBaseHeight());
		System.out.println("Variation [15][FOREST_HILLS] :: " + Biomes.FOREST_HILLS.getBaseHeight());
		System.out.println("Variation [16][FROZEN_OCEAN] :: " + Biomes.FROZEN_OCEAN.getBaseHeight());
		System.out.println("Variation [17][FROZEN_RIVER] :: " + Biomes.FROZEN_RIVER.getBaseHeight());
		System.out.println("Variation [18][HELL] :: " + Biomes.HELL.getBaseHeight());
		System.out.println("Variation [19][ICE_MOUNTAINS] :: " + Biomes.ICE_MOUNTAINS.getBaseHeight());
		System.out.println("Variation [20][ICE_PLAINS] :: " + Biomes.ICE_PLAINS.getBaseHeight());
		System.out.println("Variation [21][JUNGLE] :: " + Biomes.JUNGLE.getBaseHeight());
		System.out.println("Variation [22][JUNGLE_EDGE] :: " + Biomes.JUNGLE_EDGE.getBaseHeight());
		System.out.println("Variation [23][JUNGLE_HILLS] :: " + Biomes.JUNGLE_HILLS.getBaseHeight());
		System.out.println("Variation [24][MESA] :: " + Biomes.MESA.getBaseHeight());
		System.out.println("Variation [25][MESA_CLEAR_ROCK] :: " + Biomes.MESA_CLEAR_ROCK.getBaseHeight());
		System.out.println("Variation [26][MESA_ROCK] :: " + Biomes.MESA_ROCK.getBaseHeight());
		System.out.println("Variation [27][MUSHROOM_ISLAND] :: " + Biomes.MUSHROOM_ISLAND.getBaseHeight());
		System.out.println("Variation [28][MUSHROOM_ISLAND_SHORE] :: " + Biomes.MUSHROOM_ISLAND_SHORE.getBaseHeight());
		/*
		System.out.println("Variation [29][] :: " + Biomes.MUTATED_BIRCH_FOREST.getHeightVariation());
		System.out.println("Variation [30][] :: " + Biomes.MUTATED_BIRCH_FOREST_HILLS.getHeightVariation());
		System.out.println("Variation [31][] :: " + Biomes.MUTATED_DESERT.getHeightVariation());
		System.out.println("Variation [32][] :: " + Biomes.MUTATED_EXTREME_HILLS.getHeightVariation());
		System.out.println("Variation [33][] :: " + Biomes.MUTATED_EXTREME_HILLS_WITH_TREES.getHeightVariation());
		System.out.println("Variation [34][] :: " + Biomes.MUTATED_FOREST.getHeightVariation());
		System.out.println("Variation [35][] :: " + Biomes.MUTATED_ICE_FLATS.getHeightVariation());
		System.out.println("Variation [36][] :: " + Biomes.MUTATED_JUNGLE.getHeightVariation());
		System.out.println("Variation [37][] :: " + Biomes.MUTATED_JUNGLE_EDGE.getHeightVariation());
		System.out.println("Variation [38][] :: " + Biomes.MUTATED_MESA.getHeightVariation());
		System.out.println("Variation [39][] :: " + Biomes.MUTATED_MESA_CLEAR_ROCK.getHeightVariation());
		System.out.println("Variation [40][] :: " + Biomes.MUTATED_MESA_ROCK.getHeightVariation());
		System.out.println("Variation [41][] :: " + Biomes.MUTATED_PLAINS.getHeightVariation());
		System.out.println("Variation [42][] :: " + Biomes.MUTATED_REDWOOD_TAIGA.getHeightVariation());
		System.out.println("Variation [43][] :: " + Biomes.MUTATED_REDWOOD_TAIGA_HILLS.getHeightVariation());
		System.out.println("Variation [44][] :: " + Biomes.MUTATED_ROOFED_FOREST.getHeightVariation());
		System.out.println("Variation [45][] :: " + Biomes.MUTATED_SAVANNA.getHeightVariation());
		System.out.println("Variation [46][] :: " + Biomes.MUTATED_SAVANNA_ROCK.getHeightVariation());
		System.out.println("Variation [47][] :: " + Biomes.MUTATED_SWAMPLAND.getHeightVariation());
		System.out.println("Variation [48][] :: " + Biomes.MUTATED_TAIGA.getHeightVariation());
		System.out.println("Variation [49][] :: " + Biomes.MUTATED_TAIGA_COLD.getHeightVariation());
		*/
		System.out.println("Variation [50][OCEAN] :: " + Biomes.OCEAN.getBaseHeight());
		System.out.println("Variation [51][PLAINS] :: " + Biomes.PLAINS.getBaseHeight());
		System.out.println("Variation [52][REDWOOD_TAIGA] :: " + Biomes.REDWOOD_TAIGA.getBaseHeight());
		System.out.println("Variation [53][REDWOOD_TAIGA_HILLS] :: " + Biomes.REDWOOD_TAIGA_HILLS.getBaseHeight());
		System.out.println("Variation [54][RIVER] :: " + Biomes.RIVER.getBaseHeight());
		System.out.println("Variation [55][ROOFED_FOREST] :: " + Biomes.ROOFED_FOREST.getBaseHeight());
		System.out.println("Variation [56][SAVANNA] :: " + Biomes.SAVANNA.getBaseHeight());
		System.out.println("Variation [57][SAVANNA_PLATEAU] :: " + Biomes.SAVANNA_PLATEAU.getBaseHeight());
		System.out.println("Variation [58][SKY] :: " + Biomes.SKY.getBaseHeight());
		System.out.println("Variation [59][STONE_BEACH] :: " + Biomes.STONE_BEACH.getBaseHeight());
		System.out.println("Variation [60][SWAMPLAND] :: " + Biomes.SWAMPLAND.getBaseHeight());
		System.out.println("Variation [61][TAIGA] :: " + Biomes.TAIGA.getBaseHeight());
		System.out.println("Variation [62][TAIGA_HILLS] :: " + Biomes.TAIGA_HILLS.getBaseHeight());
		System.out.println("Variation [63][VOID] :: " + Biomes.VOID.getBaseHeight());
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		
	}
	
	
}
