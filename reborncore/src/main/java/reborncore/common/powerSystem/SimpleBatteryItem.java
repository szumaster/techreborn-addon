package reborncore.common.powerSystem;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

public interface SimpleBatteryItem {
	String ENERGY_KEY = "energy";


	double getEnergyCapacity();


	double getEnergyMaxInput();


	double getEnergyMaxOutput();


	default double getStoredEnergy(ItemStack stack) {
		if (stack.getCount() != 1) {
			throw new IllegalArgumentException("Invalid count: " + stack.getCount());
		}

		return getStoredEnergyUnchecked(stack);
	}


	default void setStoredEnergy(ItemStack stack, double newAmount) {
		if (stack.getCount() != 1) {
			throw new IllegalArgumentException("Invalid count: " + stack.getCount());
		}

		setStoredEnergyUnchecked(stack, newAmount);
	}


	default boolean tryUseEnergy(ItemStack stack, double amount) {
		double newAmount = getStoredEnergy(stack) - amount;

		if (newAmount < 0) {
			return false;
		} else {
			setStoredEnergy(stack, newAmount);
			return true;
		}
	}


	static double getStoredEnergyUnchecked(ItemStack stack) {
		return getStoredEnergyUnchecked(stack.getTag());
	}


	static double getStoredEnergyUnchecked(@Nullable CompoundTag tag) {
		return tag != null ? tag.getDouble(ENERGY_KEY) : 0;
	}


	static void setStoredEnergyUnchecked(ItemStack stack, double newAmount) {
		if (newAmount == 0) {
			// Make sure newly crafted energy containers stack with emptied ones.
			stack.removeSubTag(ENERGY_KEY);
		} else {
			stack.getOrCreateTag().putDouble(ENERGY_KEY, newAmount);
		}
	}
}
