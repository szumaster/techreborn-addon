package techreborn.blockentity.machine.multiblock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.blockentity.MultiblockWriter;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.util.RebornInventory;
import techreborn.blockentity.machine.GenericMachineBlockEntity;
import techreborn.config.TechRebornConfig;
import techreborn.init.ModRecipes;
import techreborn.init.TRBlockEntities;
import techreborn.init.TRContent;

public class ChemicalProcessingUnitBlockEntity extends GenericMachineBlockEntity implements BuiltScreenHandlerProvider {

	public ChemicalProcessingUnitBlockEntity() {
		super(TRBlockEntities.CHEMICAL_PROCESSING_UNIT, "ChemicalProcessingUnit", TechRebornConfig.chemicalProcessorMaxInput, TechRebornConfig.chemicalProcessorMaxEnergy, TRContent.Machine.CHEMICAL_PROCESSING_UNIT.block, 6);
		final int[] inputs = new int[]{0, 1};
		final int[] outputs = new int[]{2, 3, 4, 5};
		this.inventory = new RebornInventory<>(7, "ChemicalProcessingUnitBlockEntity", 64, this);
		this.crafter = new RecipeCrafter(ModRecipes.CHEMICAL_PROCESSING_UNIT, this, 2, 4, this.inventory, inputs, outputs);
	}

	@Override
	public void writeMultiblock(MultiblockWriter writer) {
		writer.translate(1, 0, -1)
				.fill(0, 0, 0, 3, 1, 3, TRContent.MachineBlocks.INDUSTRIAL.getCasing().getDefaultState())
				.ringWithAir(Direction.Axis.Y, 3, 1, 3, TRContent.MachineBlocks.ADVANCED.getCasing().getDefaultState())
				.ringWithAir(Direction.Axis.Y, 3, 2, 3, TRContent.MachineBlocks.ADVANCED.getCasing().getDefaultState())
				.ringWithAir(Direction.Axis.Y, 3, 3, 3, TRContent.MachineBlocks.ADVANCED.getCasing().getDefaultState())
				.fill(0, 4, 0, 3, 5, 3, TRContent.MachineBlocks.INDUSTRIAL.getCasing().getDefaultState());
	}

	// IContainerProvider
	@Override
	public BuiltScreenHandler createScreenHandler(int syncID, final PlayerEntity player) {
		ScreenHandlerBuilder builder = new ScreenHandlerBuilder("chemicalprocessing").player(player.inventory).inventory().hotbar().addInventory();
		if (isMultiblockValid()) {
			builder.blockEntity(this)
					.slot(0, 35, 35)
					.slot(1, 35, 55)
					.outputSlot(2, 79, 41)
					.outputSlot(3, 99, 41)
					.outputSlot(4, 119, 41)
					.outputSlot(5, 139, 41)
					.energySlot(6, 8, 72)
					.syncEnergyValue()
					.syncCrafterValue()
					.addInventory();
		}
		return builder.create(this, syncID);
	}

	@Override
	public boolean canCraft(RebornRecipe rebornRecipe) {
		return isMultiblockValid();
	}
}