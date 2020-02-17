package com.kaiodenic.arcanium.init;

import java.util.ArrayList;
import java.util.List;

import com.kaiodenic.arcanium.items.AItem;
import com.kaiodenic.arcanium.items.ItemCrystalPlacer;
import com.kaiodenic.arcanium.items.ItemWandBasic;

import net.minecraft.item.Item;

public class Items {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final Item CRYSTAL = new AItem("crystal");
	public static final Item ITEM_WAND_BASIC = new ItemWandBasic("item_wand_basic");
	public static final Item ITEM_CRYSTAL_PLACER = new ItemCrystalPlacer("item_crystal_placer");
	
}
