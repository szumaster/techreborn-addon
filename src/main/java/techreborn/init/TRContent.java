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

package techreborn.init;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import reborncore.api.blockentity.IUpgrade;
import reborncore.api.systems.conduit.block.ConduitBlock;
import reborncore.common.fluid.FluidValue;
import reborncore.common.powerSystem.PowerAcceptorBlockEntity;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyTier;
import techreborn.TechReborn;
import techreborn.blockentity.generator.LightningRodBlockEntity;
import techreborn.blockentity.generator.PlasmaGeneratorBlockEntity;
import techreborn.blockentity.generator.advanced.*;
import techreborn.blockentity.generator.basic.SolidFuelGeneratorBlockEntity;
import techreborn.blockentity.generator.basic.WaterMillBlockEntity;
import techreborn.blockentity.generator.basic.WindMillBlockEntity;
import techreborn.blockentity.machine.misc.ChargeOMatBlockEntity;
import techreborn.blockentity.machine.misc.DrainBlockEntity;
import techreborn.blockentity.machine.multiblock.*;
import techreborn.blockentity.machine.tier1.*;
import techreborn.blockentity.machine.multiblock.CuttingMachineBlockEntity;
import techreborn.blockentity.machine.tier2.GrinderBlockEntity;
import techreborn.blockentity.machine.tier2.NukeProcessingUnitBlockEntity;
import techreborn.blockentity.machine.tier3.ChunkLoaderBlockEntity;
import techreborn.blockentity.machine.tier3.IndustrialCentrifugeBlockEntity;
import techreborn.blockentity.machine.tier3.MatterFabricatorBlockEntity;
import techreborn.blockentity.machine.tier3.MillingCutterBlockEntity;
import techreborn.blockentity.storage.energy.AdjustableSUBlockEntity;
import techreborn.blocks.GenericMachineBlock;
import techreborn.blocks.cable.CableBlock;
import techreborn.blocks.generator.BlockFusionCoil;
import techreborn.blocks.generator.BlockFusionControlComputer;
import techreborn.blocks.generator.BlockSolarPanel;
import techreborn.blocks.generator.GenericGeneratorBlock;
import techreborn.blocks.lighting.LampBlock;
import techreborn.blocks.machine.tier0.IronAlloyFurnaceBlock;
import techreborn.blocks.machine.tier0.IronFurnaceBlock;
import techreborn.blocks.machine.tier1.PlayerDetectorBlock;
import techreborn.blocks.machine.tier1.ResinBasinBlock;
import techreborn.blocks.machine.tier2.QuarryBlock;
import techreborn.blocks.misc.*;
import techreborn.blocks.storage.energy.*;
import techreborn.blocks.storage.fluid.TankUnitBlock;
import techreborn.blocks.storage.item.StorageUnitBlock;
import techreborn.blocks.transformers.*;
import techreborn.client.GuiType;
import techreborn.config.TechRebornConfig;
import techreborn.entities.EntityNukePrimed;
import techreborn.items.DynamicCellItem;
import techreborn.items.IQuarryUpgrade;
import techreborn.items.QuarryUpgradeItem;
import techreborn.items.UpgradeItem;
import techreborn.items.armor.QuantumSuitItem;
import techreborn.items.tool.MiningLevel;
import techreborn.utils.InitUtils;

import org.jetbrains.annotations.Nullable;
import techreborn.world.DataDrivenFeature;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TRContent {

	// Misc Blocks
	public static Block COMPUTER_CUBE;
	public static Block NUKE;
	public static Block REFINED_IRON_FENCE;
	public static Block REINFORCED_GLASS;
	public static Block RUBBER_LEAVES;
	public static Block RUBBER_LOG;
	public static Block RUBBER_PLANK_SLAB;
	public static Block RUBBER_PLANK_STAIR;
	public static Block RUBBER_PLANKS;
	public static Block RUBBER_SAPLING;
	public static Block RUBBER_FENCE;
	public static Block RUBBER_FENCE_GATE;
	public static Block RUBBER_TRAPDOOR;
	public static Block RUBBER_BUTTON;
	public static Block RUBBER_PRESSURE_PLATE;
	public static Block RUBBER_DOOR;
	public static Block RUBBER_LOG_STRIPPED;
	public static Block RUBBER_WOOD;
	public static Block STRIPPED_RUBBER_WOOD;
	public static Block POTTED_RUBBER_SAPLING;
	public static Block DRILL_TUBE = new BlockDrillTube();
	public static ConduitBlock<ItemStack> ITEM_CONDUIT;

	// Armor
	public static Item CLOAKING_DEVICE;
	public static Item LAPOTRONIC_ORBPACK;
	public static Item LITHIUM_ION_BATPACK;

	// Battery
	public static Item ENERGY_CRYSTAL;
	public static Item LAPOTRON_CRYSTAL;
	public static Item LAPOTRONIC_ORB;
	public static Item LITHIUM_ION_BATTERY;
	public static Item RED_CELL_BATTERY;

	public static Item SOLAR_BATTERY;

	// Tools
	public static Item TREE_TAP;
	public static Item WRENCH;
	public static Item PAINTING_TOOL;

	public static Item BASIC_CHAINSAW;
	public static Item BASIC_DRILL;
	public static Item BASIC_JACKHAMMER;
	public static Item ELECTRIC_TREE_TAP;

	public static Item ADVANCED_CHAINSAW;
	public static Item ADVANCED_DRILL;
	public static Item ADVANCED_JACKHAMMER;
	public static Item ROCK_CUTTER;

	public static Item INDUSTRIAL_CHAINSAW;
	public static Item INDUSTRIAL_DRILL;
	public static Item INDUSTRIAL_JACKHAMMER;
	public static Item NANOSABER;
	public static Item OMNI_TOOL;

	public static Item DEBUG_TOOL;

	// Other
	public static Item FREQUENCY_TRANSMITTER;
	public static Item SCRAP_BOX;
	public static Item MANUAL;
	public static DynamicCellItem CELL;

	//Quantum Suit
	public static QuantumSuitItem QUANTUM_HELMET;
	public static QuantumSuitItem QUANTUM_CHESTPLATE;
	public static QuantumSuitItem QUANTUM_LEGGINGS;
	public static QuantumSuitItem QUANTUM_BOOTS;

	// Gem armor & tools
	@Nullable
	public static Item AMETHYST_SWORD;
	@Nullable
	public static Item AMETHYST_PICKAXE;
	@Nullable
	public static Item AMETHYST_SPADE;
	@Nullable
	public static Item AMETHYST_AXE;
	@Nullable
	public static Item AMETHYST_HOE;
	@Nullable
	public static Item AMETHYST_HELMET;
	@Nullable
	public static Item AMETHYST_CHESTPLATE;
	@Nullable
	public static Item AMETHYST_LEGGINGS;
	@Nullable
	public static Item AMETHYST_BOOTS;
	@Nullable
	public static Item BRONZE_SWORD;
	@Nullable
	public static Item BRONZE_PICKAXE;
	@Nullable
	public static Item BRONZE_SPADE;
	@Nullable
	public static Item BRONZE_AXE;
	@Nullable
	public static Item BRONZE_HOE;
	@Nullable
	public static Item BRONZE_HELMET;
	@Nullable
	public static Item BRONZE_CHESTPLATE;
	@Nullable
	public static Item BRONZE_LEGGINGS;
	@Nullable
	public static Item BRONZE_BOOTS;
	@Nullable
	public static Item RUBY_SWORD;
	@Nullable
	public static Item RUBY_PICKAXE;
	@Nullable
	public static Item RUBY_SPADE;
	@Nullable
	public static Item RUBY_AXE;
	@Nullable
	public static Item RUBY_HOE;
	@Nullable
	public static Item RUBY_HELMET;
	@Nullable
	public static Item RUBY_CHESTPLATE;
	@Nullable
	public static Item RUBY_LEGGINGS;
	@Nullable
	public static Item RUBY_BOOTS;
	@Nullable
	public static Item SAPPHIRE_SWORD;
	@Nullable
	public static Item SAPPHIRE_PICKAXE;
	@Nullable
	public static Item SAPPHIRE_SPADE;
	@Nullable
	public static Item SAPPHIRE_AXE;
	@Nullable
	public static Item SAPPHIRE_HOE;
	@Nullable
	public static Item SAPPHIRE_HELMET;
	@Nullable
	public static Item SAPPHIRE_CHESTPLATE;
	@Nullable
	public static Item SAPPHIRE_LEGGINGS;
	@Nullable
	public static Item SAPPHIRE_BOOTS;
	@Nullable
	public static Item PERIDOT_SWORD;
	@Nullable
	public static Item PERIDOT_PICKAXE;
	@Nullable
	public static Item PERIDOT_SPADE;
	@Nullable
	public static Item PERIDOT_AXE;
	@Nullable
	public static Item PERIDOT_HOE;
	@Nullable
	public static Item PERIDOT_HELMET;
	@Nullable
	public static Item PERIDOT_CHESTPLATE;
	@Nullable
	public static Item PERIDOT_LEGGINGS;
	@Nullable
	public static Item PERIDOT_BOOTS;

	public enum SolarParts implements ItemConvertible {
		THIN_FILM_LINK,
		AMORPHOUS_LINK,
		CRYSTALLINE_LINK,
		MONOCRYSTALLINE_LINK,
		POLYCRYSTALLINE_LINK,

		PN_CONNECTOR,
		MC4_CONNECTOR,
		FRESNEL_LENSE,
		ANTI_REFLECTIVE_COATING,
		I2L_DIGITAL_CIRCUIT,

		POLYMER_REAR_BACK_SHEET,
		GLASS_PLATE,
		TEMPERED_GLASS_PLATE,
		PLEXIGLASS_PLATE,
		POLYCARBONATE_PLATE,
		ACRYLIC_PLATE,

		PEROVSKITE_CELL,
		QUANTUM_DOT_CELL,

		MESFE_TRANSISTOR,
		HEM_TRANSISTOR,
		JFE_TRANSISTOR,
		HB_TRANSISTOR,

		INKJET_CATRIDGE,
		PHOTOVOLTAIC_ACCELERATOR,
		DC_CONVERTER,
		JUNCTION_TERMINAL,
		QUANTUM_INFRARED_DETECTOR,

		SOLAR_PANEL_PLATE_MOULD,
		FRAME_MOULD;

		public final String name;
		public final Item item;

		SolarParts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name);
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum SolarPanels implements ItemConvertible {
		BASIC(EnergyTier.MICRO, TechRebornConfig.basicGenerationRateD, TechRebornConfig.basicGenerationRateN),
		ADVANCED(EnergyTier.LOW, TechRebornConfig.advancedGenerationRateD, TechRebornConfig.advancedGenerationRateN),
		INDUSTRIAL(EnergyTier.MEDIUM, TechRebornConfig.industrialGenerationRateD, TechRebornConfig.industrialGenerationRateN),
		ULTIMATE(EnergyTier.HIGH, TechRebornConfig.ultimateGenerationRateD, TechRebornConfig.ultimateGenerationRateN),
		QUANTUM(EnergyTier.EXTREME, TechRebornConfig.quantumGenerationRateD, TechRebornConfig.quantumGenerationRateN),
		CREATIVE(EnergyTier.INFINITE, Integer.MAX_VALUE / 100, Integer.MAX_VALUE / 100);

		public final String name;
		public final Block block;

		// Generation of EU during Day
		public int generationRateD;
		// Generation of EU during Night
		public int generationRateN;
		// Internal EU storage of solar panel
		public int internalCapacity;
		public final EnergyTier powerTier;

		SolarPanels(EnergyTier tier, int generationRateD, int generationRateN) {
			name = this.toString().toLowerCase(Locale.ROOT);
			powerTier = tier;
			block = new BlockSolarPanel(this);
			this.generationRateD = generationRateD;
			this.generationRateN = generationRateN;

			internalCapacity = generationRateD * TechRebornConfig.solarInternalCapacityMultiplier;

			InitUtils.setup(block, name + "_solar_panel");
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum StorageUnit implements ItemConvertible {
		CRUDE(TechRebornConfig.crudeStorageUnitMaxStorage),
		BASIC(TechRebornConfig.basicStorageUnitMaxStorage),
		ADVANCED(TechRebornConfig.advancedStorageUnitMaxStorage),
		INDUSTRIAL(TechRebornConfig.industrialStorageUnitMaxStorage),
		QUANTUM(TechRebornConfig.quantumStorageUnitMaxStorage),
		CREATIVE(Integer.MAX_VALUE);

		public final String name;
		public final Block block;

		public int capacity;


		StorageUnit(int capacity) {
			name = this.toString().toLowerCase(Locale.ROOT);
			block = new StorageUnitBlock(this);
			this.capacity = capacity;

			InitUtils.setup(block, name + "_storage_unit");
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum TankUnit implements ItemConvertible {
		BASIC(TechRebornConfig.basicTankUnitCapacity),
		ADVANCED(TechRebornConfig.advancedTankUnitMaxStorage),
		INDUSTRIAL(TechRebornConfig.industrialTankUnitCapacity),
		QUANTUM(TechRebornConfig.quantumTankUnitCapacity),
		CREATIVE(Integer.MAX_VALUE / 1000);

		public final String name;
		public final Block block;

		// How many blocks it can hold
		public FluidValue capacity;


		TankUnit(int capacity) {
			name = this.toString().toLowerCase(Locale.ROOT);
			block = new TankUnitBlock(this);
			this.capacity = FluidValue.BUCKET.multiply(capacity);

			InitUtils.setup(block, name + "_tank_unit");
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum Cables implements ItemConvertible {
		COPPER(128, 12.0, true, EnergyTier.MEDIUM),
		TIN(32, 12.0, true, EnergyTier.LOW),
		GOLD(512, 12.0, true, EnergyTier.HIGH),
		HV(2048, 12.0, true, EnergyTier.EXTREME),
		GLASSFIBER(8192, 12.0, false, EnergyTier.INSANE),
		INSULATED_COPPER(128, 10.0, false, EnergyTier.MEDIUM),
		INSULATED_GOLD(512, 10.0, false, EnergyTier.HIGH),
		INSULATED_HV(2048, 10.0, false, EnergyTier.EXTREME),
		SUPERCONDUCTOR(Integer.MAX_VALUE / 4, 10.0, false, EnergyTier.INFINITE);


		public final String name;
		public final CableBlock block;

		public int transferRate;
		public int defaultTransferRate;
		public double cableThickness;
		public boolean canKill;
		public boolean defaultCanKill;
		public EnergyTier tier;


		Cables(int transferRate, double cableThickness, boolean canKill, EnergyTier tier) {
			name = this.toString().toLowerCase(Locale.ROOT);
			this.transferRate = transferRate;
			this.defaultTransferRate = transferRate;
			this.cableThickness = cableThickness / 2;
			this.canKill = canKill;
			this.defaultCanKill = canKill;
			this.tier = tier;
			this.block = new CableBlock(this);
			InitUtils.setup(block, name + "_cable");
		}

		public ItemStack getStack() {
			return new ItemStack(block);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}
	public enum Conduit implements ItemConvertible {

		EXPORT_CONDUIT_ITEM,
		IMPORT_CONDUIT_ITEM,
		BLOCK_CONDUIT_ITEM,
		ONE_WAY_CONDUIT_ITEM;

		public final String name;
		public final Item item;

		Conduit() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name);
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}
	public enum Parts implements ItemConvertible {
		CUPRONICKEL_HEATING_COIL,
		KANTHAL_HEATING_COIL,
		NICHROME_HEATING_COIL,
		SUPERCONDUCTOR_COIL,

		CARBON_FIBER,
		CARBON_MESH,

		DATA_STORAGE_CHIP,
		DATA_STORAGE_CORE,
 		BASIC_DISPLAY,
		DIGITAL_DISPLAY,
		ENERGY_FLOW_CHIP,
		ADVANCED_CIRCUIT,
		ELECTRONIC_CIRCUIT,
		INDUSTRIAL_CIRCUIT,

		TUNGSTEN_GRINDING_HEAD,
		DIAMOND_GRINDING_HEAD,
		DIAMOND_SAW_BLADE,


		HELIUM_COOLANT_CELL_60K,
		HELIUM_COOLANT_CELL_180K,
		HELIUM_COOLANT_CELL_360K,

		NAK_COOLANT_CELL_60K,
		NAK_COOLANT_CELL_180K,
		NAK_COOLANT_CELL_360K,

		WATER_COOLANT_CELL_10K,
		WATER_COOLANT_CELL_30K,
		WATER_COOLANT_CELL_60K,
		MACHINE_PARTS,
		NEUTRON_REFLECTOR,
		IRIDIUM_NEUTRON_REFLECTOR,
		THICK_NEUTRON_REFLECTOR,
		PLANTBALL,
		COMPRESSED_PLANTBALL,
		SCRAP,
		SAP,
		RUBBER,
		SPONGE_PIECE,
		SUPERCONDUCTOR,
		UU_MATTER,

		SYNTHETIC_REDSTONE_CRYSTAL;

		public final String name;
		public final Item item;

		Parts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name);
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Ores implements ItemConvertible {
		AMETHYST(6, 3, 10, 250, MiningLevel.DIAMOND),
		BAUXITE(6, 10, 10, 60, MiningLevel.STONE),
		BISMUTH(3, 3, 10, 250, MiningLevel.DIAMOND),
		CINNABAR(6, 3, 10, 126, MiningLevel.IRON),
		COPPER(8, 16, 20, 60, MiningLevel.STONE),
		GALENA(8, 16, 10, 60, MiningLevel.IRON),
		IRIDIUM(3, 3, 5, 60, MiningLevel.DIAMOND),
		LEAD(6, 16, 20, 60, MiningLevel.IRON),
		PERIDOT(6, 3, 10, 250, MiningLevel.DIAMOND),
		PYRITE(6, 3, 10, 126, MiningLevel.DIAMOND),
		RUBY(6, 3, 10, 60, MiningLevel.IRON),
		SAPPHIRE(6, 3, 10, 60, MiningLevel.IRON),
		SHELDONITE(6, 3, 10, 250, MiningLevel.DIAMOND),
		SILVER(6, 16, 20, 60, MiningLevel.IRON),
		SODALITE(6, 3, 10, 250, MiningLevel.DIAMOND),
		SPHALERITE(6, 3, 10, 126, MiningLevel.IRON),
		TIN(8, 16, 20, 60, MiningLevel.STONE),
		TUNGSTEN(6, 3, 10, 250, MiningLevel.DIAMOND);

		public final String name;
		public final Block block;
		public final int veinSize;
		public final int veinsPerChunk;
		public final int minY;
		public final int maxY;

		Ores(int veinSize, int veinsPerChunk, int minY, int maxY, MiningLevel miningLevel) {
			name = this.toString().toLowerCase(Locale.ROOT);
			block = new OreBlock(FabricBlockSettings.of(Material.STONE)
					.breakByTool(FabricToolTags.PICKAXES, miningLevel.intLevel)
					.requiresTool()
					.sounds(BlockSoundGroup.STONE)
					.strength(2f, 2f)
			);
			this.veinSize = veinSize;
			this.veinsPerChunk = veinsPerChunk;
			this.minY = minY;
			this.maxY = maxY;
			InitUtils.setup(block, name + "_ore");
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}

		@SuppressWarnings("deprecation")
		public DataDrivenFeature asNewOres(Identifier identifier, Predicate < BiomeSelectionContext > targetType, RuleTest ruleTest) {
			return new DataDrivenFeature(identifier, targetType, ruleTest, block.getDefaultState(), maxY, veinSize, veinsPerChunk);
		}

	}

	public enum RawOres implements ItemConvertible {
		AMETHYST,
		BAUXITE,
		BISMUTH,
		CINNABAR,
		COPPER,
		GALENA,
		IRIDIUM,
		LEAD,
		PERIDOT,
		PYRITE,
		RUBY,
		SAPPHIRE,
		SHELDONITE,
		SILVER,
		SODALITE,
		SPHALERITE,
		TIN,
		TUNGSTEN;

		public final String name;
		public final Item item;

		RawOres() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_raw_ore");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum StorageBlocks implements ItemConvertible {
		ADVANCED_ALLOY,
		ALUMINUM,
		AMETHYST,
		ANNEALED_COPPER,
		ANNEALED_COPPER_HOT,
		ANTIMONY,
		BATTERY_ALLOY,
		BERYLLIUM,
		BISMUTH,
		BLASTPROOF_ALLOY,
		BRASS,
		BRONZE,
		CADMIUM,
		CUPRONICKEL,
		CHROME,
		COPPER,
		ELECTRUM,
		INVAR,
		IRIDIUM,
		IRIDIUM_REINFORCED_STONE,
		IRIDIUM_REINFORCED_TUNGSTENSTEEL,
		LEAD,
		NICKEL,
		PERIDOT,
		PLATINUM,
		RED_GARNET,
		REFINED_IRON,
		RUBY,
		SAPPHIRE,
		SILVER,
		STEEL,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UNICORN,
		URANIUM,
		YELLOW_GARNET,
		ZINC;

		public final String name;
		public final Block block;
		public final StairsBlock stairsBlock;
		public final SlabBlock slabBlock;
		public final WallBlock wallBlock;

		StorageBlocks() {
			name = this.toString().toLowerCase(Locale.ROOT);
			block = new BlockStorage();
			InitUtils.setup(block, name + "_block");

			stairsBlock = new TechRebornStairsBlock(block.getDefaultState(), FabricBlockSettings.copyOf(block));
			InitUtils.setup(stairsBlock, name + "_block_stairs");

			slabBlock = new SlabBlock(FabricBlockSettings.copyOf(block));
			InitUtils.setup(slabBlock, name + "_block_slab");

			wallBlock = new WallBlock(FabricBlockSettings.copyOf(block));
			InitUtils.setup(wallBlock, name + "_block_wall");
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}

		public static Stream<Block> blockStream() {
			return Arrays.stream(values())
					.map(StorageBlocks::allBlocks)
					.flatMap(Collection::stream);
		}

		private List<Block> allBlocks() {
			return Collections.unmodifiableList(Arrays.asList(
					block, stairsBlock, slabBlock, wallBlock
			));
		}
	}

	public enum MachineBlocks {
		BASIC(510 / 25),
		ADVANCED(1020 / 25),
		INDUSTRIAL(1700 / 25),
		QUANTUM(2380 / 25);

		public final String name;
		public final Block frame;
		public final Block casing;

		MachineBlocks(int casingHeatCapacity) {
			name = this.toString().toLowerCase(Locale.ROOT);
			frame = new BlockMachineFrame();
			InitUtils.setup(frame, name + "_machine_frame");
			casing = new BlockMachineCasing(casingHeatCapacity);
			InitUtils.setup(casing, name + "_machine_casing");
		}

		public Block getFrame() {
			return frame;
		}

		public Block getCasing() {
			return casing;
		}

		public static ItemConvertible[] getCasings() {
			return Arrays.stream(MachineBlocks.values()).map((Function < MachineBlocks, ItemConvertible > ) machineBlocks -> () -> Item.fromBlock(machineBlocks.casing)).toArray(ItemConvertible[]::new);
		}
	}


	public enum Machine implements ItemConvertible {
		ALLOY_SMELTER(new GenericMachineBlock(GuiType.ALLOY_SMELTER, AlloySmelterBlockEntity::new)),
		ASSEMBLY_MACHINE(new GenericMachineBlock(GuiType.ASSEMBLING_MACHINE, AssemblingMachineBlockEntity::new)),
		AUTO_CRAFTING_TABLE(new GenericMachineBlock(GuiType.AUTO_CRAFTING_TABLE, AutoCraftingTableBlockEntity::new)),
		CHEMICAL_REACTOR(new GenericMachineBlock(GuiType.CHEMICAL_REACTOR, ChemicalReactorBlockEntity::new)),
		DISTILLATION_TOWER(new GenericMachineBlock(GuiType.DISTILLATION_TOWER, DistillationTowerBlockEntity::new)),
		EXTRACTOR(new GenericMachineBlock(GuiType.EXTRACTOR, ExtractorBlockEntity::new)),
		RESIN_BASIN(new ResinBasinBlock(ResinBasinBlockEntity::new)),
		FLUID_REPLICATOR(new GenericMachineBlock(GuiType.FLUID_REPLICATOR, FluidReplicatorBlockEntity::new)),
		GRINDER(new GenericMachineBlock(GuiType.GRINDER, GrinderBlockEntity::new)),
		ROLLING_MACHINE(new GenericMachineBlock(GuiType.ROLLING_MACHINE, RollingMachineBlockEntity::new)),
		ELECTRIC_FURNACE(new GenericMachineBlock(GuiType.ELECTRIC_FURNACE, ElectricFurnaceBlockEntity::new)),
		IMPLOSION_COMPRESSOR(new GenericMachineBlock(GuiType.IMPLOSION_COMPRESSOR, ImplosionCompressorBlockEntity::new)),
		INDUSTRIAL_BLAST_FURNACE(new GenericMachineBlock(GuiType.BLAST_FURNACE, IndustrialBlastFurnaceBlockEntity::new)),
		INDUSTRIAL_CENTRIFUGE(new GenericMachineBlock(GuiType.CENTRIFUGE, IndustrialCentrifugeBlockEntity::new)),
		INDUSTRIAL_ELECTROLYZER(new GenericMachineBlock(GuiType.INDUSTRIAL_ELECTROLYZER, IndustrialElectrolyzerBlockEntity::new)),
		INDUSTRIAL_GRINDER(new GenericMachineBlock(GuiType.INDUSTRIAL_GRINDER, IndustrialGrinderBlockEntity::new)),
		INDUSTRIAL_SAWMILL(new GenericMachineBlock(GuiType.SAWMILL, IndustrialSawmillBlockEntity::new)),
		CUTTING_MACHINE(new GenericMachineBlock(GuiType.CUTTING_MACHINE, CuttingMachineBlockEntity::new)),
		MILLING_CUTTER(new GenericMachineBlock(GuiType.MILLING_CUTTER, MillingCutterBlockEntity::new)),
		CHEMICAL_PROCESSING_UNIT(new GenericMachineBlock(GuiType.CHEMICAL_PROCESSING_UNIT, ChemicalProcessingUnitBlockEntity::new)),
		COMPRESSOR(new GenericMachineBlock(GuiType.COMPRESSOR, CompressorBlockEntity::new)),
		IRON_ALLOY_FURNACE(new IronAlloyFurnaceBlock()),
		IRON_FURNACE(new IronFurnaceBlock()),
		MATTER_FABRICATOR(new GenericMachineBlock(GuiType.MATTER_FABRICATOR, MatterFabricatorBlockEntity::new)),
		RECYCLER(new GenericMachineBlock(GuiType.RECYCLER, RecyclerBlockEntity::new)),
		QUARRY(new QuarryBlock()),
		SCRAPBOXINATOR(new GenericMachineBlock(GuiType.SCRAPBOXINATOR, ScrapboxinatorBlockEntity::new)),
		VACUUM_FREEZER(new GenericMachineBlock(GuiType.VACUUM_FREEZER, VacuumFreezerBlockEntity::new)),
		SOLID_CANNING_MACHINE(new GenericMachineBlock(GuiType.SOLID_CANNING_MACHINE, SoildCanningMachineBlockEntity::new)),
		NUKE_PROCESSING_UNIT(new GenericMachineBlock(GuiType.NUKE_PROCESSING_UNIT, NukeProcessingUnitBlockEntity::new)),
		WIRE_MILL(new GenericMachineBlock(GuiType.WIRE_MILL, WireMillBlockEntity::new)),
		GREENHOUSE_CONTROLLER(new GenericMachineBlock(GuiType.GREENHOUSE_CONTROLLER, GreenhouseControllerBlockEntity::new)),
		DIESEL_GENERATOR(new GenericGeneratorBlock(GuiType.DIESEL_GENERATOR, DieselGeneratorBlockEntity::new)),
		DRAGON_EGG_SYPHON(new GenericGeneratorBlock(null, DragonEggSyphonBlockEntity::new)),
		FUSION_COIL(new BlockFusionCoil()),
		FUSION_CONTROL_COMPUTER(new BlockFusionControlComputer()),
		GAS_TURBINE(new GenericGeneratorBlock(GuiType.GAS_TURBINE, GasTurbineBlockEntity::new)),
		LIGHTNING_ROD(new GenericGeneratorBlock(null, LightningRodBlockEntity::new)),
		PLASMA_GENERATOR(new GenericGeneratorBlock(GuiType.PLASMA_GENERATOR, PlasmaGeneratorBlockEntity::new)),
		SEMI_FLUID_GENERATOR(new GenericGeneratorBlock(GuiType.SEMIFLUID_GENERATOR, SemiFluidGeneratorBlockEntity::new)),
		SOLID_FUEL_GENERATOR(new GenericGeneratorBlock(GuiType.GENERATOR, SolidFuelGeneratorBlockEntity::new)),
		THERMAL_GENERATOR(new GenericGeneratorBlock(GuiType.THERMAL_GENERATOR, ThermalGeneratorBlockEntity::new)),
		WATER_MILL(new GenericGeneratorBlock(null, WaterMillBlockEntity::new)),
		WIND_MILL(new GenericGeneratorBlock(null, WindMillBlockEntity::new)),

		DRAIN(new GenericMachineBlock(null, DrainBlockEntity::new)),

		ADJUSTABLE_SU(new AdjustableSUBlock()),
		CHARGE_O_MAT(new GenericMachineBlock(GuiType.CHARGEBENCH, ChargeOMatBlockEntity::new)),
		INTERDIMENSIONAL_SU(new InterdimensionalSUBlock()),
		LAPOTRONIC_SU(new LapotronicSUBlock()),
		LSU_STORAGE(new LSUStorageBlock()),
		LOW_VOLTAGE_SU(new LowVoltageSUBlock()),
		MEDIUM_VOLTAGE_SU(new MediumVoltageSUBlock()),
		HIGH_VOLTAGE_SU(new HighVoltageSUBlock()),
		LV_TRANSFORMER(new BlockLVTransformer()),
		MV_TRANSFORMER(new BlockMVTransformer()),
		HV_TRANSFORMER(new BlockHVTransformer()),
		EV_TRANSFORMER(new BlockEVTransformer()),

		ALARM(new BlockAlarm()),
		LAMP_INCANDESCENT(new LampBlock(4, 10, 8)),
		LAMP_LED(new LampBlock(1, 1, 12)),
		CHUNK_LOADER(new GenericMachineBlock(GuiType.CHUNK_LOADER, ChunkLoaderBlockEntity::new)),
		PLAYER_DETECTOR(new PlayerDetectorBlock());

		public final String name;
		public final Block block;

		<B extends Block> Machine(B block) {
			this.name = this.toString().toLowerCase(Locale.ROOT);
			this.block = block;
			InitUtils.setup(block, name);
		}

		public ItemStack getStack() {
			return new ItemStack(block);
		}

		@Override
		public Item asItem() {
			return block.asItem();
		}
	}

	public enum Gears implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;

		public final String name;
		public final Item item;
		Gears() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_gear");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}
		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Rings implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;
		public final String name;
		public final Item item;

		Rings() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_ring");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum CrushedDusts implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NEODYMIUM,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		KANTHAL,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;

		public final String name;
		public final Item item;

		CrushedDusts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_crushed_dust");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Rods implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NEODYMIUM,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		IRIDIUM,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;

		public final String name;
		public final Item item;

		Rods() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_rod");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Dusts implements ItemConvertible {
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		ASHES,
		BASALT,
		BAUXITE,
		BATTERY_ALLOY,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CARBON,
		CUPRONICKEL,
		CHARCOAL,
		CHROME,
		COBALT,
		CAESIUM,
		CATERIUM,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DARK_ASHES,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		FIRE_CLAY_BRICK,
		FLINT,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM,
		IRON,
		KANTHAL,
		LAZURITE,
		LERASIUM,
		LEAD,
		LITHIUM,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		MIXED_BLASTPROOF,
		NEODYMIUM,
		NETHERRACK,
		NIKOLITE,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		OSMIUM,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PLUTONIUM,
		PYRITE,
		PYROPE,
		RHENIUM,
		QUARTZ,
		RED_GARNET,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SAW,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		SCRITH,
		STEEL,
		SULFUR,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		UVAROVITE,
		YELLOW_GARNET,
		ZINC;

		public final String name;
		public final Item item;

		Dusts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_dust");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum TinyDusts implements ItemConvertible {
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		ASHES,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CARBON,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DARK_ASHES,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		FLINT,
		GALENA,
		GLOWSTONE,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		NEODYMIUM,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REDSTONE,
		RED_GARNET,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SAW,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SULFUR,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		UVAROVITE,
		YELLOW_GARNET,
		ZINC;

		public final String name;
		public final Item item;

		TinyDusts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_tiny_dust");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}
	public enum Gems implements ItemConvertible {
		AMETHYST,
		BISMUTH,
		PERIDOT,
		RED_GARNET,
		RUBY,
		SAPPHIRE,
		YELLOW_GARNET;

		public final String name;
		public final Item item;

		Gems() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_gem");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Ingots implements ItemConvertible {
		ADVANCED_ALLOY,
		ALUMINUM,
		ANNEALED_COPPER,
		ANNEALED_COPPER_HOT,
		ANTIMONY,
		BATTERY_ALLOY,
		BERYLLIUM,
		BISMUTH,
		BLASTPROOF_ALLOY,
		BRASS,
		BRONZE,
		CADMIUM,
		CAESIUM,
		CATERIUM,

		CHROME,
		CHROME_HOT,
		CLAY,
		COBALT,
		COPPER,
		CUPRONICKEL,
		ELECTRUM,
		FIRE_CLAY_BRICK,
		HALIT,
		HE_URANIUM,
		HOT_TUNGSTENSTEEL,
		INVAR,
		IRIDIUM,
		IRIDIUM_ALLOY,
		KANTHAL,
		KANTHAL_HOT,
		LEAD,
		LERASIUM,
		LITHIUM,
		MANGANESE,
		MIXED_BLASTPROOF,
		MIXED_IRIDIUM,
		MIXED_METAL,
		NEODYMIUM,
		NICKEL,
		NIKOLITE,
		OSMIUM,
		PALLADIUM,
		PLATINUM,
		PLATINUM_HOT,
		PLUTONIUM,
		REFINED_IRON,
		SASSOLITE,
		SCRITH,
		SILICON,
		SILVER,
		STAINLESS_STEEL,
		STAINLESS_STEEL_HOT,
		STEEL,
		SUPERCONDUCTOR,
		SUPERCONDUCTOR_HOT,
		SYLWIN,
		TIN,
		TITANIUM,
		TITANIUM_HOT,
		TUNGSTEN,
		TUNGSTENSTEEL,
		URANIUM,
		URANIUM_235,
		URANIUM_238,
		ZINC;

		public final String name;
		public final Item item;

		Ingots() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_ingot");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Bolts implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDSTONE,
		GOLD,
		GRANITE,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		PERIDOT,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;

		public final String name;
		public final Item item;
		Bolts() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_bolt");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}
		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}
	public enum LargePlates implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BLASTPROOF_ALLOY,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;
		public final String name;
		public final Item item;
		LargePlates() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_large_plate");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}
		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum DoubleIngots implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NEODYMIUM,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;
		public final String name;
		public final Item item;

		DoubleIngots() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_double_ingot");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}
		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}
	public enum CurvedPlates implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;
		public final String name;
		public final Item item;
		CurvedPlates() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_curved_plate");
		}
		public ItemStack getStack() {
			return new ItemStack(item);
		}
		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}
		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Blades implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;

		public final String name;
		public final Item item;

		Blades() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_blade");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Rotors implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		ZINC;
		public final String name;
		public final Item item;

		Rotors() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_rotor");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Wires implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CARBON,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		FLINT,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM,
		IRIDIUM_ALLOY,
		IRON,
		LAZURITE,
		LEAD,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NEODYMIUM,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		RED_GARNET,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SULFUR,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		YELLOW_GARNET,
		ZINC;
		public final String name;
		public final Item item;

		Wires() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_wire");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Nuggets implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANNEALED_COPPER_HOT,
		ANTIMONY,
		BASALT,
		BATTERY_ALLOY,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BLASTPROOF_ALLOY,
		BRASS,
		BRONZE,
		CADMIUM,
		CAESIUM,
		CALCITE,
		CARBON,
		CATERIUM,
		CHARCOAL,
		CHROME,
		CHROME_HOT,
		CINNABAR,
		CLAY,
		COAL,
		COBALT,
		COPPER,
		CUPRONICKEL,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		FIRE_CLAY_BRICK,
		FLINT,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		HALIT,
		HE_URANIUM,
		HOT_TUNGSTENSTEEL,
		INVAR,
		IRIDIUM,
		IRIDIUM_ALLOY,
		IRON,
		KANTHAL,
		KANTHAL_HOT,
		LAPIS,
		LAZURITE,
		LEAD,
		LERASIUM,
		LITHIUM,
		MAGNALIUM,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_BLASTPROOF,
		MIXED_IRIDIUM,
		MIXED_METAL,
		NEODYMIUM,
		NETHERITE,
		NETHERRACK,
		NICKEL,
		NIKOLITE,
		OBSIDIAN,
		OLIVINE,
		OSMIUM,
		PALLADIUM,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PLATINUM_HOT,
		PLUTONIUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REDSTONE,
		RED_GARNET,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SASSOLITE,
		SCRITH,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STAINLESS_STEEL,
		STAINLESS_STEEL_HOT,
		STEEL,
		SUPERCONDUCTOR,
		SUPERCONDUCTOR_HOT,
		SYLWIN,
		TIN,
		TITANIUM,
		TITANIUM_HOT,
		TUNGSTEN,
		TUNGSTENSTEEL,
		URANIUM,
		URANIUM_235,
		URANIUM_238,
		UVAROVITE,
		WOOD,
		YELLOW_GARNET,
		ZINC;

		public final String name;
		public final Item item;

		Nuggets() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_nugget");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Plates implements ItemConvertible {
		ADVANCED_ALLOY,
		ALMANDINE,
		ALUMINUM,
		AMETHYST,
		ANDESITE,
		ANDRADITE,
		ANNEALED_COPPER,
		ANTIMONY,
		BASALT,
		BAUXITE,
		BERYLLIUM,
		BISMUTH,
		BLASTPROOF_ALLOY,
		BRASS,
		BRONZE,
		CADMIUM,
		CALCITE,
		CARBON,
		CHARCOAL,
		CHROME,
		CINNABAR,
		CLAY,
		COAL,
		COPPER,
		DIAMOND,
		DIORITE,
		ELECTRUM,
		EMERALD,
		ENDER_EYE,
		ENDER_PEARL,
		ENDSTONE,
		FIRE_CLAY,
		FLINT,
		GALENA,
		GOLD,
		GRANITE,
		GROSSULAR,
		INVAR,
		IRIDIUM,
		IRIDIUM_ALLOY,
		IRON,
		LAPIS,
		LAZURITE,
		LEAD,
		MAGNALIUM,
		MAGNESIUM,
		MANGANESE,
		MARBLE,
		MIXED_METAL,
		NEODYMIUM,
		NETHERRACK,
		NICKEL,
		NETHERITE,
		OBSIDIAN,
		OLIVINE,
		PERIDOT,
		PHOSPHOROUS,
		PLATINUM,
		PYRITE,
		PYROPE,
		QUARTZ,
		REDSTONE,
		RED_GARNET,
		REFINED_IRON,
		RUBY,
		SALTPETER,
		SAPPHIRE,
		SILICON,
		SILVER,
		SODALITE,
		SPESSARTINE,
		SPHALERITE,
		STEEL,
		SUPERCONDUCTOR,
		TIN,
		TITANIUM,
		TUNGSTEN,
		TUNGSTENSTEEL,
		UVAROVITE,
		WOOD,
		YELLOW_GARNET,
		ZINC;

		public final String name;
		public final Item item;

		Plates() {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new Item(new Item.Settings().group(TechReborn.ITEMGROUP));
			InitUtils.setup(item, name + "_plate");
		}

		public ItemStack getStack() {
			return new ItemStack(item);
		}

		public ItemStack getStack(int amount) {
			return new ItemStack(item, amount);
		}

		@Override
		public Item asItem() {
			return item;
		}
	}

	public enum Upgrades implements ItemConvertible {
		OVERCLOCKER((blockEntity, handler, stack) -> {
			PowerAcceptorBlockEntity powerAcceptor = null;
			if (blockEntity instanceof PowerAcceptorBlockEntity) {
				powerAcceptor = (PowerAcceptorBlockEntity) blockEntity;
			}
			handler.addSpeedMulti(TechRebornConfig.overclockSpeed);
			handler.addPowerMulti(TechRebornConfig.overclockPower);
			if (powerAcceptor != null) {
				powerAcceptor.extraPowerInput += powerAcceptor.getMaxInput(EnergySide.UNKNOWN);
				powerAcceptor.extraPowerStorage += powerAcceptor.getBaseMaxPower();
			}
		}),
		TRANSFORMER((blockEntity, handler, stack) -> {
			PowerAcceptorBlockEntity powerAcceptor = null;
			if (blockEntity instanceof PowerAcceptorBlockEntity) {
				powerAcceptor = (PowerAcceptorBlockEntity) blockEntity;
			}
			if (powerAcceptor != null) {
				powerAcceptor.extraTier += 1;
			}
		}),
		ENERGY_STORAGE((blockEntity, handler, stack) -> {
			PowerAcceptorBlockEntity powerAcceptor = null;
			if (blockEntity instanceof PowerAcceptorBlockEntity) {
				powerAcceptor = (PowerAcceptorBlockEntity) blockEntity;
			}
			if (powerAcceptor != null) {
				powerAcceptor.extraPowerStorage += TechRebornConfig.energyStoragePower;
			}
		}),
		SUPERCONDUCTOR((blockEntity, handler, stack) -> {
			AdjustableSUBlockEntity aesu = null;
			if (blockEntity instanceof AdjustableSUBlockEntity) {
				aesu = (AdjustableSUBlockEntity) blockEntity;
			}
			if (aesu != null) {
				aesu.superconductors += TechRebornConfig.superConductorCount;
			}
		});
		public String name;
		public Item item;
		Upgrades(IUpgrade upgrade) {
			name = this.toString().toLowerCase(Locale.ROOT);
			item = new UpgradeItem(name, upgrade);
			InitUtils.setup(item, name + "_upgrade");
		}
		@Override
		public Item asItem() {
			return item;
		}
	}

	/**
	 * <a href="https://github.com/TED-inc/FabricQuarry">Created by TED-INC</a>
	 * **/
	public enum QuarryUpgrades implements ItemConvertible {
		RANGE_EXTENDER_LVL1((quarryBlockEntity, stack) -> {
			quarryBlockEntity.rangeExtenderLevel = Math.max(quarryBlockEntity.rangeExtenderLevel, 1);
		}),
		RANGE_EXTENDER_LVL2((quarryBlockEntity, stack) -> {
			quarryBlockEntity.rangeExtenderLevel = Math.max(quarryBlockEntity.rangeExtenderLevel, 2);
		}),
		RANGE_EXTENDER_LVL3((quarryBlockEntity, stack) -> {
			quarryBlockEntity.rangeExtenderLevel = Math.max(quarryBlockEntity.rangeExtenderLevel, 3);
		}),
		FORTUNE_LVL1((quarryBlockEntity, stack) -> {
			quarryBlockEntity.fortuneLevel = Math.max(quarryBlockEntity.fortuneLevel, 1);
		}),
		FORTUNE_LVL2((quarryBlockEntity, stack) -> {
			quarryBlockEntity.fortuneLevel = Math.max(quarryBlockEntity.fortuneLevel, 2);
		}),
		FORTUNE_LVL3((quarryBlockEntity, stack) -> {
			quarryBlockEntity.fortuneLevel = Math.max(quarryBlockEntity.fortuneLevel, 3);
		}),
		SILKTOUCH((quarryBlockEntity, stack) -> {
			quarryBlockEntity.isSilkTouch |= true;
		});
		public final String name;
		public final Item item;

		QuarryUpgrades(IQuarryUpgrade upgrade) {
			name = this.toString().toLowerCase(Locale.ROOT) + "_upgrade";
			item = new QuarryUpgradeItem(name, upgrade);
			InitUtils.setup(item, name);
		}
		@Override
		public Item asItem() {
			return item;
		}
		public static QuarryUpgrades getFrom(QuarryUpgradeItem item) {
			for (QuarryUpgrades upgrade: values()) {
				if (upgrade.item == item)
					return upgrade;
			}

			throw null;
		}
	}
	public static EntityType < EntityNukePrimed > ENTITY_NUKE;
}
