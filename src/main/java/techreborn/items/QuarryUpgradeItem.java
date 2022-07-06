package techreborn.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import techreborn.TechReborn;
import techreborn.blockentity.machine.tier2.QuarryBlockEntity;

/**
 * <a href="https://github.com/TED-inc/FabricQuarry">Created by TED-INC</a>
 * **/
public class QuarryUpgradeItem extends Item implements IQuarryUpgrade {

    public final String name;
	public final IQuarryUpgrade behavior;

	public QuarryUpgradeItem(String name, IQuarryUpgrade process) {
		super(new Item.Settings().group(TechReborn.ITEMGROUP).maxCount(16));
		this.name = name;
		this.behavior = process;
	}

	@Override
	public void process(
			@NotNull QuarryBlockEntity quarryBlockEntity,
			@NotNull ItemStack stack) {
		behavior.process(quarryBlockEntity, stack);
	}
}
