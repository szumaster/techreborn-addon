package techreborn.client.gui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import reborncore.client.RenderUtil;
import reborncore.client.gui.builder.GuiBase;
import reborncore.client.gui.builder.widget.GuiButtonExtended;
import reborncore.client.gui.guibuilder.GuiBuilder;
import reborncore.client.screen.builder.BuiltScreenHandler;
import techreborn.blockentity.machine.multiblock.ChemicalProcessingUnitBlockEntity;

public class GuiChemicalProcessingUnit extends GuiBase<BuiltScreenHandler> {

	private final ChemicalProcessingUnitBlockEntity blockEntity;
	private boolean isValid = false;

	public GuiChemicalProcessingUnit(int syncID, final PlayerEntity player, final ChemicalProcessingUnitBlockEntity blockEntity) {
		super(player, blockEntity, blockEntity.createScreenHandler(syncID, player));
		this.blockEntity = blockEntity;

		if (blockEntity.isMultiblockValid()) {
			isValid = true;
			blockEntity.renderMultiblock = false;
		}
	}

	@Override
	protected void drawBackground(MatrixStack matrixStack, final float f, final int mouseX, final int mouseY) {
		super.drawBackground(matrixStack, f, mouseX, mouseY);
		final Layer layer = Layer.BACKGROUND;

		if (!isValid) {
			return;
		}

		// Battery slot
		drawSlot(matrixStack, 8, 72, layer);

		// Input slots
		drawSlot(matrixStack, 35, 35, layer);
		drawSlot(matrixStack, 35, 55, layer);

		// Four output slots
		drawOutputSlotBar(matrixStack, 78, 41, 4, layer);

		// JEI Button
		builder.drawJEIButton(matrixStack, this, 158, 5, layer);
	}

	@Override
	protected void drawForeground(MatrixStack matrixStack, final int mouseX, final int mouseY) {
		super.drawForeground(matrixStack, mouseX, mouseY);
		final Layer layer = Layer.FOREGROUND;

		if (this.hideGuiElements()) {
			return;
		}

		if (!isValid) {
			builder.drawMultiblockMissingBar(matrixStack, this, layer);
			addHologramButton(76, 56, 212, layer).clickHandler(this::onClick);
			builder.drawHologramButton(matrixStack, this, 76, 56, mouseX, mouseY, layer);
			return;
		}

		builder.drawProgressBar(matrixStack, this, blockEntity.getProgressScaled(100), 100, 55, 49, mouseX, mouseY, GuiBuilder.ProgressDirection.RIGHT, layer);
		builder.drawMultiEnergyBar(matrixStack, this, 9, 19, (int) blockEntity.getEnergy(), (int) blockEntity.getMaxStoredPower(), mouseX, mouseY, 0, layer);
	}

	public void onClick(GuiButtonExtended button, Double x, Double y) {
		blockEntity.renderMultiblock ^= !hideGuiElements();
	}
}
