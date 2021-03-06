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

package techreborn.blockentity.generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import reborncore.api.IToolDrop;
import reborncore.client.screen.BuiltScreenHandlerProvider;
import reborncore.client.screen.builder.BuiltScreenHandler;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.blocks.BlockMachineBase;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.util.StringUtils;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyTier;
import techreborn.blocks.generator.BlockSolarPanel;
import techreborn.init.TRBlockEntities;
import techreborn.init.TRContent;
import techreborn.init.TRContent.SolarPanels;

import java.util.List;

public class SolarPanelBlockEntity extends PowerAcceptorBlockEntity implements IToolDrop, BuiltScreenHandlerProvider {


	private boolean generating = false;

	private int dayNightRange = 0;


	private SolarPanels panel;

	public SolarPanelBlockEntity() {
		super(TRBlockEntities.SOLAR_PANEL);
	}

	public SolarPanelBlockEntity(SolarPanels panel) {
		super(TRBlockEntities.SOLAR_PANEL);
		this.panel = panel;
	}

	private void updatePanel() {
		if (world == null) {
			return;
		}
		Block panelBlock = world.getBlockState(pos).getBlock();
		if (panelBlock instanceof BlockSolarPanel) {
			BlockSolarPanel solarPanelBlock = (BlockSolarPanel) panelBlock;
			panel = solarPanelBlock.panelType;
		}
		dayNightRange = getPanel().generationRateD - getPanel().generationRateN;
	}

	public boolean isGenerating() { return generating; }
	private void setIsGenerating(boolean isGenerating) {
		if (isGenerating != isGenerating()) {
			world.setBlockState(pos, world.getBlockState(pos).with(BlockMachineBase.ACTIVE, isGenerating));
		}
		this.generating = isGenerating;
	}


	SolarPanels getPanel() {
		if (panel == null) {
			updatePanel();
		}
		return panel;
	}

	private void updateState() {
		if (world == null) {
			return;
		}

		// Generation is only possible if sky is visible above us
		setIsGenerating(world.isSkyVisible(pos.up()));
	}

	public int getGenerationRate() {
		if (!isGenerating()) {
			return 0;
		}

		float skyAngle = world.getSkyAngle(0);

		// Ok, we are actively generating power, but check for a few conditions that would restrict
		// the generation to minimal production...
		if (!world.getDimension().hasSkyLight() || // No light source in dimension (e.g. nether or end)
				(skyAngle > 0.25 && skyAngle < 0.75) || // Light source is below horizon
				(world.isRaining() || world.isThundering())) { // Weather is present
			return getPanel().generationRateN;
		}

		// At this point, we know a light source is present and it's clear weather. We need to determine
		// the level of generation based on % of time through the day, with peak production at noon and
		// a smooth transition to night production as sun rises/sets
		float multiplier = 0.0f;
		if (skyAngle > 0.75) {
			// Morning to noon
			multiplier = (0.25f - (1 - skyAngle)) / 0.25f;
		} else {
			// Noon to sunset
			multiplier = (0.25f - skyAngle) / 0.25f;
		}

		return (int)Math.ceil(getPanel().generationRateN + (dayNightRange * multiplier));
	}



	// Overrides

	@Override
	public void tick() {
		super.tick();
		if (world == null || world.isClient) {
			return;
		}

		if (getPanel() == TRContent.SolarPanels.CREATIVE) {
			checkOverfill = false;
			setEnergy(Integer.MAX_VALUE);
			return;
		}

		// State checking and updating
		if (world.getTime() % 20 == 0) {
			checkOverfill = true;
			updateState();
		}

		// Power generation calculations
		addEnergy(getGenerationRate());
	}

	@Override
	public double getBaseMaxPower() {
		return getPanel().internalCapacity;
	}

	@Override
	protected boolean canAcceptEnergy(EnergySide side) { return false; }

	@Override
	public double getBaseMaxOutput() {
		if (getPanel() == TRContent.SolarPanels.CREATIVE) {
			return EnergyTier.INSANE.getMaxOutput();
		}
		// Solar panel output will only be limited by the cables the users use
		return EnergyTier.EXTREME.getMaxOutput();
	}

	@Override
	public double getBaseMaxInput() {
		return 0;
	}

	@Override
	public boolean canBeUpgraded() {
		return false;
	}

	@Override
	public boolean hasSlotConfig() {
		return false;
	}

	@Override
	public EnergyTier getTier() {
		return getPanel().powerTier;
	}

	@Override
	public void checkTier() {
		// Nope
	}

	@Override
	public void addInfo(List<Text> info, boolean isReal, boolean hasData) {
		if (panel == SolarPanels.CREATIVE) {
			return;
		}

		info.add(
				new TranslatableText("reborncore.tooltip.energy.maxEnergy")
						.formatted(Formatting.GRAY)
						.append(": ")
						.append(
								new LiteralText(PowerSystem.getLocalizedPower(getMaxStoredPower()))
										.formatted(Formatting.GOLD)
						)
		);

		info.add(
				new TranslatableText("techreborn.tooltip.generationRate.day")
						.formatted(Formatting.GRAY)
						.append(": ")
						.append(
								new LiteralText(PowerSystem.getLocalizedPower(panel.generationRateD))
										.formatted(Formatting.GOLD)
						)
		);

		info.add(
				new TranslatableText("techreborn.tooltip.generationRate.night")
						.formatted(Formatting.GRAY)
						.append(": ")
						.append(
								new LiteralText(PowerSystem.getLocalizedPower(panel.generationRateN))
										.formatted(Formatting.GOLD)
						)
		);

		info.add(
				new TranslatableText("reborncore.tooltip.energy.tier")
						.formatted(Formatting.GRAY)
						.append(": ")
						.append(
								new LiteralText(StringUtils.toFirstCapitalAllLowercase(getTier().toString()))
										.formatted(Formatting.GOLD)
						)
		);
	}

	@Override
	public void fromTag(BlockState blockState, CompoundTag tag) {
		if (world == null) {
			// We are in BlockEntity.create method during chunk load.
			this.checkOverfill = false;
		}
		updatePanel();
		super.fromTag(blockState, tag);
	}

	// MachineBaseBlockEntity
	@Override
	public void onLoad() {
		super.onLoad();
		updatePanel();
	}

	// IToolDrop
	@Override
	public ItemStack getToolDrop(final PlayerEntity playerIn) {
		return new ItemStack(getBlockType());
	}

	@Override
	public BuiltScreenHandler createScreenHandler(int syncID, final PlayerEntity player) {
		return new ScreenHandlerBuilder("solar_panel").player(player.inventory).inventory().hotbar().addInventory()
				.blockEntity(this).syncEnergyValue()
				.sync(this::isGenerating, this::setIsGenerating)
				.addInventory().create(this, syncID);
	}
}
