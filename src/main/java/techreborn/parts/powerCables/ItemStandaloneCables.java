/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2017 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.parts.powerCables;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import reborncore.RebornCore;
import techreborn.client.TechRebornCreativeTab;
import techreborn.items.ItemTextureBase;
import techreborn.lib.ModInfo;
import techreborn.parts.StandalonePartCompact;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by modmuss50 on 06/03/2016.
 */
public class ItemStandaloneCables extends ItemTextureBase {

	public static Item mcPartCable;

	public ItemStandaloneCables() {
		setCreativeTab(TechRebornCreativeTab.instance);
		setHasSubtypes(true);
		setUnlocalizedName("techreborn.cable");
		setNoRepair();
		RebornCore.jsonDestroyer.registerObject(this);
	}

	public static ItemStack getCableByName(String name, int count) {
		for (int i = 0; i < EnumStandaloneCableType.values().length; i++) {
			if (EnumStandaloneCableType.values()[i].getName().equalsIgnoreCase(name)) {
				return new ItemStack(mcPartCable != null ? mcPartCable : StandalonePartCompact.itemStandaloneCable,
					count, i);
			}
		}
		throw new InvalidParameterException("The cable " + name + " could not be found.");
	}

	public static ItemStack getCableByName(String name) {
		return getCableByName(name, 1);
	}

	@Override
	// gets Unlocalized Name depending on meta data
	public String getUnlocalizedName(ItemStack itemStack) {
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= EnumStandaloneCableType.values().length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + EnumStandaloneCableType.values()[meta];
	}

	// Adds Dusts SubItems To Creative Tab
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
		for (int meta = 0; meta < EnumStandaloneCableType.values().length; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}

	@Override
	public String getTextureName(int damage) {
		return ModInfo.MOD_ID + ":items/cables/" + EnumStandaloneCableType.values()[damage].getName();
	}

	@Override
	public int getMaxMeta() {
		return EnumStandaloneCableType.values().length;
	}

	// @Override
	// public ModelResourceLocation getModel(ItemStack stack, EntityPlayer
	// player, int useRemaining) {
	// return new ModelResourceLocation(ModInfo.MOD_ID + ":" +
	// getUnlocalizedName(stack).substring(5), "inventory");
	// }

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		EnumStandaloneCableType type = EnumStandaloneCableType.values()[stack.getItemDamage()];
		tooltip.add(TextFormatting.GREEN + "EU Transfer: " + TextFormatting.LIGHT_PURPLE + type.transferRate);
		if (type.canKill) {
			tooltip.add(TextFormatting.RED + "Damages entity's!");
		}
		tooltip.add(TextFormatting.RED + "!!INSTALL MCMP TO PLACE CABLES!!");
	}
}
