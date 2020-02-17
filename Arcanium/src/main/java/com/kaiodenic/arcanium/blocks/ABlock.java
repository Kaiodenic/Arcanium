package com.kaiodenic.arcanium.blocks;

import com.kaiodenic.arcanium.Main;
import com.kaiodenic.arcanium.init.Blocks;
import com.kaiodenic.arcanium.init.Items;
import com.kaiodenic.arcanium.utils.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ABlock extends Block implements IHasModel{
	public ABlock(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.REDSTONE);
		
		Blocks.BLOCKS.add(this);
		Items.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
