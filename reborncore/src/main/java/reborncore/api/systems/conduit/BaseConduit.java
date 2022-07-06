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

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import reborncore.api.systems.conduit.functionalfaces.ConduitFunction;
import reborncore.api.systems.conduit.functionalfaces.ConduitFunctionalFace;
import reborncore.api.systems.conduit.functionalfaces.IConduitItemProvider;
import reborncore.api.systems.functionalface.FunctionalFaceStorage;
import reborncore.common.network.ClientBoundPackets;
import reborncore.common.network.NetworkManager;
import reborncore.common.util.IDebuggable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseConduit<T> extends BlockEntity implements Tickable, IDebuggable, IConduit<T> {

	// Thing we move
	protected IConduitTransfer<T> stored = null;
	final Class<? extends IConduitTransfer<T>> transferClass;

	// Neighbouring conduits which are we are connected to
	protected final Map<Direction, IConduit<T>> conduits = new HashMap<>();
	final Class<? extends IConduit<T>> conduitEntityClass;

	// Speciality faces
	protected final FunctionalFaceStorage<ConduitFunctionalFace> functionalFaces = new FunctionalFaceStorage<>();
	private final IConduitItemProvider conduitItemProvider;

	private int ticktime = 0;

	// Round robin variable
	private int outputIndex = 0;

	public BaseConduit(BlockEntityType<?> type, Class<? extends IConduit<T>> conduitEntityClass, Class<? extends IConduitTransfer<T>> transferClass, IConduitItemProvider conduitItemProvider) {
		super(type);
		this.transferClass = transferClass;
		this.conduitEntityClass = conduitEntityClass;
		this.conduitItemProvider = conduitItemProvider;
	}

	// Abstract functions
	protected abstract void importFace(Direction face);

	protected abstract void exportFace(Direction face);

	protected void onServerLoad() {
		// Conduits populating if read from NBT, note will be conduit type, checked elsewhere
		for (Direction direction : new ArrayList<>(conduits.keySet())) {
			if (world == null) return;

			BlockEntity entity = world.getBlockEntity(this.pos.offset(direction));

			if (conduitEntityClass.isInstance(entity)) {
				conduits.put(direction, conduitEntityClass.cast(entity));
			} else {
				conduits.remove(direction);
			}
		}
	}

	protected void clientTick() {

	}

	protected void serverTick() {
		if (ticktime == 0) {
			onServerLoad();
		}
		ticktime++;

		// Progress item if we have one
		if (stored != null) {
			stored.progress();
		}

		// Loop through each mode and perform action
		for (Map.Entry<Direction, ConduitFunctionalFace> entry : functionalFaces.getEntrySet()) {

			switch ((entry.getValue().conduitFunction)) {
				case ONE_WAY:
					if (stored == null || !stored.isFinished()) break;
					if (!tryTransferPayload(entry.getKey())) {
						// Might be storage
						exportFace(entry.getKey());
					}
					break;
				case IMPORT:
					if (stored != null) break;
					importFace(entry.getKey());
					break;
				case EXPORT:
					if (stored == null) break;
					exportFace(entry.getKey());
					break;
				default:
					// No functionality for rest
					break;
			}
		}

		// Item logic after operations
		if (stored != null) {

			// If finished, find another conduit to move to
			if (stored.isFinished()) {

				Direction destination = getDestinationDirection(stored.getOriginDirection());

				if (destination != null) {
					// Giving the opposite of the TO direction which is the direction which the new conduit will be facing this entity.
					tryTransferPayload(destination);
				}
			}
		}

		sync();

	}

	@Override
	public void tick() {
		if (world == null) {
			return;
		}

		if (world.isClient()) {
			clientTick();
		} else {
			serverTick();
		}
	}

	public void addConduit(Direction direction, IConduit<T> conduit) {
		conduits.put(direction, conduit);
	}

	public void removeConduit(Direction direction) {
		conduits.remove(direction);
	}

	public boolean tryTransferPayload(Direction direction) {
		if (stored == null) {
			return false;
		}

		IConduit<T> conduit = conduits.get(direction);

		if (conduit == null) {
			BlockEntity entity = world.getBlockEntity(this.pos.offset(direction));

			if (conduitEntityClass.isInstance(entity)) {
				conduit = conduitEntityClass.cast(entity);
			} else {
				return false;
			}
		}

		boolean didTransfer = conduit.transferPayload(stored, direction.getOpposite());

		if (didTransfer) {
			this.stored = null;
			return true;
		}

		return false;
	}


	// Called by other conduits
	public boolean transferPayload(IConduitTransfer<T> transfer, Direction origin) {
		if (stored == null) {
			transfer.restartProgress();
			transfer.setOriginDirection(origin);
			transfer.setTargetDirection(getDestinationDirection(origin));

			this.stored = transfer;

			return true;
		}

		return false;
	}

	// Returns a direction which is next up for transfer
	protected Direction getDestinationDirection(Direction from) {

		// Always return one-way direction if present
		if(functionalFaces.hasFunctionality(ConduitFunctionalFace.fromFunction(ConduitFunction.ONE_WAY, conduitItemProvider))){
			return functionalFaces.getFunctionalityFace(ConduitFunctionalFace.fromFunction(ConduitFunction.ONE_WAY, conduitItemProvider));
		}

		// If we have export, then export
		if(functionalFaces.hasFunctionality(ConduitFunctionalFace.fromFunction(ConduitFunction.EXPORT, conduitItemProvider))){
			return functionalFaces.getFunctionalityFace(ConduitFunctionalFace.fromFunction(ConduitFunction.EXPORT, conduitItemProvider));
		}

		// Else pick a direction with valid conduit and remove from
		HashMap<Direction, IConduit<T>> tempConduit = new HashMap<>(conduits);

		// Don't send to where we've received.
		tempConduit.remove(from);

		// If pipe's changed or round robin round is finished, reset index
		if (outputIndex >= tempConduit.size()) {
			outputIndex = 0;
		}

		// Round robin crap
		int position = 0;

		for (Map.Entry<Direction, IConduit<T>> entry : tempConduit.entrySet()) {
			if (position == outputIndex) {
				outputIndex++;
				return entry.getKey();
			}

			// Increment if not right index
			position++;
		}

		return null;
	}

	// Returns true if can connect to that direction from this entity
	public boolean canConnect(Direction direction, IConduit<T> otherConduit) {

		// Can't connect to direction which has a IO/Block mode
		return !functionalFaces.hasFunctionality(direction);
	}

	// Add functionality to side
	public boolean addFunctionality(Direction face, ItemStack playerHolding) {
		Item holdingItem = playerHolding.getItem();

		ConduitFunction conduitFunction = conduitItemProvider.fromConduitFunction(holdingItem);

		if (conduitFunction == null) return false;

		// Only one_way can connect
		if (conduitFunction == ConduitFunction.ONE_WAY && functionalFaces.hasFunctionality(ConduitFunctionalFace.fromFunction(ConduitFunction.ONE_WAY, conduitItemProvider))) {
			return false;
		}

		ConduitFunctionalFace conduitFunctionalFace = ConduitFunctionalFace.fromFunction(conduitFunction, conduitItemProvider);

		if (functionalFaces.canAddFunctionality(face, conduitFunctionalFace)) {
			functionalFaces.addFunctionalFace(face, conduitFunctionalFace);
			playerHolding.decrement(1);
			return true;
		}

		return false;
	}


	// Add functionality to side
	public ItemStack removeFunctionality(Direction face) {
		ConduitFunctionalFace functionality = functionalFaces.getFunctionality(face);

		if (functionality == null) {
			return ItemStack.EMPTY;
		}

		functionalFaces.removeFunctionality(face);
		return new ItemStack(functionality.getItem());
	}

	public void neighbourChange(){

		// Update stored target
		if(this.stored != null){
			Direction destination = getDestinationDirection(stored.getOriginDirection());

			if(destination != null){
				this.stored.setTargetDirection(destination);
			}
		}
	}

	@Override
	public boolean isOneWayFace(Direction direction) {
		return functionalFaces.getFunctionality(direction).conduitFunction == ConduitFunction.ONE_WAY;
	}

	public IConduitTransfer<T> getStored() {
		return stored;
	}


	// Returns a map containing faces which are functional (IO/specials)
	public FunctionalFaceStorage<ConduitFunctionalFace> getFunctionalFaces() {
		return functionalFaces;
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag = super.toTag(tag);

		if (!functionalFaces.isEmpty()) {
			ListTag functionalFacesList = new ListTag();

			int index = 0;

			for (Map.Entry<Direction, ConduitFunctionalFace> entry : functionalFaces.getEntrySet()) {
				CompoundTag sidedFunction = new CompoundTag();

				sidedFunction.putInt("direction", entry.getKey().getId());
				sidedFunction.putInt("function", entry.getValue().conduitFunction.ordinal());

				functionalFacesList.add(index, sidedFunction);

				index++;
			}

			tag.put("functionalFace", functionalFacesList);
		}

		if (stored != null) {
			CompoundTag storedTag = new CompoundTag();
			storedTag = stored.toTag(storedTag);
			tag.put("stored", storedTag);
		}

		if (!conduits.isEmpty()) {
			ListTag conduitFacesList = new ListTag();

			int index = 0;
			for (Map.Entry<Direction, IConduit<T>> entry : conduits.entrySet()) {
				CompoundTag sidedConduit = new CompoundTag();
				sidedConduit.putInt("direction", entry.getKey().getId());

				conduitFacesList.add(index, sidedConduit);

				index++;
			}

			tag.put("conduit", conduitFacesList);
		}

		return tag;
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		super.fromTag(state, tag);

		stored = null;
		conduits.clear();

		if (tag.contains("stored")) {
			IConduitTransfer<T> transfer = null;
			try {
				transfer = transferClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (transfer != null) {
				transfer.fromTag(tag.getCompound("stored"));
				this.stored = transfer;
			}

		}

		//  Load functionalities
		functionalFaces.clearFunctionaries();

		if (tag.contains("functionalFace")) {
			ListTag functionList = tag.getList("functionalFace", NbtType.COMPOUND);

			for (int i = 0; i < functionList.size(); i++) {
				CompoundTag compoundTag = functionList.getCompound(i);

				Direction direction = Direction.byId(compoundTag.getInt("direction"));
				ConduitFunctionalFace function = new ConduitFunctionalFace(ConduitFunction.values()[compoundTag.getInt("function")], conduitItemProvider);

				functionalFaces.addFunctionalFace(direction, function);
			}
		}


		if (tag.contains("conduit")) {
			ListTag conduitList = tag.getList("conduit", NbtType.COMPOUND);

			for (int i = 0; i < conduitList.size(); i++) {
				CompoundTag compoundTag = conduitList.getCompound(i);
				conduits.put(Direction.byId(compoundTag.getInt("direction")), null);

			}
		}
	}

	@Override
	public String getDebugText() {
		String s = "";
		s += IDebuggable.propertyFormat("Conduit count: ", conduits.size() + "\n");
		s += IDebuggable.propertyFormat("Functional faces count", functionalFaces.getSize() + "\n");
		s += IDebuggable.propertyFormat("OutputIndex: ", String.valueOf(outputIndex)) + "\n";

		return s;
	}

	protected void sync() {
		NetworkManager.sendToTracking(ClientBoundPackets.createCustomDescriptionPacket(this), this);
	}
}
