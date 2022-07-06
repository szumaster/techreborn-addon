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

package reborncore.api.systems.functionalface;

import net.minecraft.util.math.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionalFaceStorage<F extends FunctionalFace> {

	private final Map<Direction, F> functionMap = new HashMap<>();

	public void addFunctionalFace(Direction direction, F function) {
		functionMap.put(direction, function);
	}

	public boolean canAddFunctionality(Direction direction, F function) {
		if (functionMap.containsKey(direction)) return false;

		int count = (int) functionMap.values().stream().filter(mapMode -> mapMode == function).count();

		return count < function.getMaxCount();
	}

	public boolean hasFunctionality(Direction face) {
		return functionMap.containsKey(face);
	}

	public boolean hasFunctionality(F function) {
		return functionMap.containsValue(function);
	}

	public Direction getFunctionalityFace(F function) {
		for(Map.Entry<Direction, F> entry : functionMap.entrySet()){
			if(entry.getValue().equals(function)){
				return entry.getKey();
			}
		}

		return null;
	}

	public void clearFunctionaries() {
		functionMap.clear();
	}

	public F getFunctionality(Direction face) {
		return functionMap.get(face);
	}

	public void removeFunctionality(Direction face) {
		functionMap.remove(face);
	}

	public boolean isEmpty() {
		return functionMap.isEmpty();
	}

	public int getSize() {
		return functionMap.size();
	}

	public Set<Map.Entry<Direction, F>> getEntrySet() {
		return functionMap.entrySet();
	}
}
