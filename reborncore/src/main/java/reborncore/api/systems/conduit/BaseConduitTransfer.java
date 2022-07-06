/*
 * This file is part of RebornCore, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2021 TeamReborn
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

package reborncore.api.systems.conduit;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public abstract class BaseConduitTransfer<T> implements IConduitTransfer<T> {
	private T stored;

	private int progress = 0;
	private int duration;

	// Direction which the item came from
	private Direction origin;

	// Used for rendering, not important
	private Direction target = null;

	public BaseConduitTransfer(T stored, int duration, Direction origin, Direction target) {
		this.stored = stored;
		this.duration = duration;
		this.origin = origin;
		this.target = target;
	}

	protected BaseConduitTransfer() {
	}

	@Override
	public void progress() {
		if (!isFinished()) {
			progress++;
		}
	}

	// Helper functions
	@Override
	public boolean isFinished() {
		return progress >= duration;
	}

	@Override
	public void restartProgress() {
		progress = 0;
	}

	@Override
	public float getProgressPercent() {
		return ((float) progress / (float) duration);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}


	// NBT
	protected void fromTagBase(CompoundTag tag) {
		this.progress = tag.getInt("tickProgress");
		this.duration = tag.getInt("tickFinish");

		this.origin = Direction.byId(tag.getInt("fromDirection"));

		if(tag.contains("targetDirection")) {
			this.target = Direction.byId(tag.getInt("targetDirection"));
		}
	}

	protected CompoundTag toTagBase(CompoundTag tag) {
		tag.putInt("tickProgress", progress);
		tag.putInt("tickFinish", duration);

		tag.putInt("fromDirection", origin.getId());

		if(target != null) {
			tag.putInt("targetDirection", target.getId());
		}

		return tag;
	}

	// Getter/Setters
	@Override
	public T getStored() {
		return this.stored;
	}

	@Override
	public void setStored(T stored) {
		this.stored = stored;
	}

	@Override
	public Direction getOriginDirection() {
		return origin;
	}

	@Override
	public void setOriginDirection(Direction origin) {
		this.origin = origin;
	}

	@Override
	public Direction getTargetDirection() {
		return target;
	}

	@Override
	public void setTargetDirection(Direction target) {
		this.target = target;
	}
}
