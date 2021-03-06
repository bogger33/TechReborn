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

package techreborn.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import techreborn.api.ScrapboxList;

public class BehaviorDispenseScrapbox extends BehaviorDefaultDispenseItem {

	@Override
	protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
		int random = source.getWorld().rand.nextInt(ScrapboxList.stacks.size());
		ItemStack out = ScrapboxList.stacks.get(random).copy();
		float xOffset = source.getWorld().rand.nextFloat() * 0.8F + 0.1F;
		float yOffset = source.getWorld().rand.nextFloat() * 0.8F + 0.1F;
		float zOffset = source.getWorld().rand.nextFloat() * 0.8F + 0.1F;
		stack.splitStack(1);

		TileEntityDispenser tile = source.getBlockTileEntity();
		EnumFacing enumfacing = tile.getWorld().getBlockState(new BlockPos(source.getX(), source.getY(), source.getZ())).getValue(BlockDispenser.FACING);
		IPosition iposition = BlockDispenser.getDispensePosition(source);
		doDispense(source.getWorld(), out, 6, enumfacing, iposition);
		return stack;
	}

}
