package com.kaiodenic.arcanium.items;

import com.kaiodenic.arcanium.Main;
import com.kaiodenic.arcanium.init.Items;
import com.kaiodenic.arcanium.utils.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AItem extends Item implements IHasModel {
	
	public AItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.REDSTONE);
		
		Items.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
