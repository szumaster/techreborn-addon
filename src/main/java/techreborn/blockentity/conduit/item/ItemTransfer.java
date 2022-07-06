/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 TechReborn
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
package techreborn.blockentity.conduit.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import reborncore.api.systems.conduit.BaseConduitTransfer;

public class ItemTransfer extends BaseConduitTransfer<ItemStack> {

	public ItemTransfer(ItemStack stored, int duration, Direction origin, Direction target) {
		super(stored, duration, origin, target);
	}

	public ItemTransfer() {
	}

	public void fromTag(CompoundTag tag) {
		this.fromTagBase(tag);

		this.setStored(ItemStack.fromTag(tag));
	}

	public CompoundTag toTag(CompoundTag tag) {
		toTagBase(tag);

		getStored().toTag(tag);

		return tag;
	}

	@Override
	public boolean isEmpty() {
		return getStored().isEmpty();
	}
}
