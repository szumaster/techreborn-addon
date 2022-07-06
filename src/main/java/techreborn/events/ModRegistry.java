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

package techreborn.events;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import reborncore.RebornRegistry;
import reborncore.api.systems.conduit.block.ConduitBlock;
import team.reborn.energy.EnergyTier;
import techreborn.TechReborn;
import techreborn.blockentity.conduit.item.ItemConduitBlockEntity;
import techreborn.blocks.misc.*;
import techreborn.config.TechRebornConfig;
import techreborn.init.*;
import techreborn.init.TRContent.*;
import techreborn.items.*;
import techreborn.items.armor.BatpackItem;
import techreborn.items.armor.CloakingDeviceItem;
import techreborn.items.armor.QuantumSuitItem;
import techreborn.items.armor.TRArmourItem;
import techreborn.items.tool.*;
import techreborn.items.tool.advanced.AdvancedJackhammerItem;
import techreborn.items.tool.advanced.RockCutterItem;
import techreborn.items.tool.basic.ElectricTreetapItem;
import techreborn.items.tool.industrial.*;
import techreborn.items.tool.vanilla.*;
import techreborn.utils.InitUtils;

import java.util.Arrays;

/**
 * @author drcrazy
 */

public class ModRegistry {

	public static void RegistrySetup() {
		registerBlocks();
		registerItems();
		registerFluids();
		registerSounds();
	}

	private static void registerBlocks() {
		Settings itemGroup = new Item.Settings().group(TechReborn.ITEMGROUP);
		Arrays.stream(RawOres.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Ores.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		StorageBlocks.blockStream().forEach(block -> RebornRegistry.registerBlock(block, itemGroup));
		Arrays.stream(MachineBlocks.values()).forEach(value -> {
			RebornRegistry.registerBlock(value.frame, itemGroup);
			RebornRegistry.registerBlock(value.casing, itemGroup);
		});

		Arrays.stream(SolarPanels.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(StorageUnit.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(TankUnit.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		Arrays.stream(Cables.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));
		RebornRegistry.registerBlock(TRContent.ITEM_CONDUIT = InitUtils.setup(new ConduitBlock<>(ItemConduitBlockEntity::new, ItemConduitBlockEntity.class), "item_conduit"), itemGroup);
		// exception (Conduit -> Item registry)
		Arrays.stream(Conduit.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Machine.values()).forEach(value -> RebornRegistry.registerBlock(value.block, itemGroup));

		// Misc. blocks
		RebornRegistry.registerBlock(TRContent.COMPUTER_CUBE = InitUtils.setup(new BlockComputerCube(), "computer_cube"), itemGroup);
		RebornRegistry.registerBlock(TRContent.NUKE = InitUtils.setup(new BlockNuke(), "nuke"), itemGroup);
		RebornRegistry.registerBlock(TRContent.DRILL_TUBE = InitUtils.setup(new BlockDrillTube(), "drill_tube"), itemGroup);
		RebornRegistry.registerBlock(TRContent.REFINED_IRON_FENCE = InitUtils.setup(new BlockRefinedIronFence(), "refined_iron_fence"), itemGroup);
		RebornRegistry.registerBlock(TRContent.REINFORCED_GLASS = InitUtils.setup(new BlockReinforcedGlass(), "reinforced_glass"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_LEAVES = InitUtils.setup(new BlockRubberLeaves(), "rubber_leaves"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_LOG = InitUtils.setup(new BlockRubberLog(), "rubber_log"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_LOG_STRIPPED = InitUtils.setup(new PillarBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "rubber_log_stripped"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_WOOD = InitUtils.setup(new PillarBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "rubber_wood"), itemGroup);
		RebornRegistry.registerBlock(TRContent.STRIPPED_RUBBER_WOOD = InitUtils.setup(new PillarBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "stripped_rubber_wood"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANKS = InitUtils.setup(new BlockRubberPlank(), "rubber_planks"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_SAPLING = InitUtils.setup(new BlockRubberSapling(), "rubber_sapling"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANK_SLAB = InitUtils.setup(new SlabBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "rubber_plank_slab"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_FENCE = InitUtils.setup(new FenceBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "rubber_fence"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_FENCE_GATE = InitUtils.setup(new FenceGateBlock(InitUtils.setupRubberBlockSettings(2.0F, 15.0F)), "rubber_fence_gate"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_PLANK_STAIR = InitUtils.setup(new BlockRubberPlankStair(), "rubber_plank_stair"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_TRAPDOOR = InitUtils.setup(new RubberTrapdoorBlock(), "rubber_trapdoor"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_BUTTON = InitUtils.setup(new RubberButtonBlock(), "rubber_button"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_PRESSURE_PLATE = InitUtils.setup(new RubberPressurePlateBlock(), "rubber_pressure_plate"), itemGroup);
		RebornRegistry.registerBlock(TRContent.RUBBER_DOOR = InitUtils.setup(new RubberDoorBlock(), "rubber_door"), itemGroup);
		RebornRegistry.registerBlockNoItem(TRContent.POTTED_RUBBER_SAPLING = InitUtils.setup(new FlowerPotBlock(TRContent.RUBBER_SAPLING, AbstractBlock.Settings.of(Material.SUPPORTED).breakInstantly().nonOpaque()), "potted_rubber_sapling"));
		TechReborn.LOGGER.debug("TechReborns Blocks Loaded");
	}

	private static void registerItems() {
		Arrays.stream(Parts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Upgrades.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(QuarryUpgrades.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Ingots.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(DoubleIngots.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Gems.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Nuggets.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Dusts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(TinyDusts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(CrushedDusts.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Plates.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(LargePlates.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(CurvedPlates.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		Arrays.stream(Gears.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Blades.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Rods.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Bolts.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Rings.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Rotors.values()).forEach(value -> RebornRegistry.registerItem(value.item));
		Arrays.stream(Wires.values()).forEach(value -> RebornRegistry.registerItem(value.item));

		RebornRegistry.registerItem(TRContent.QUANTUM_HELMET = InitUtils.setup(new QuantumSuitItem(TRArmorMaterials.QUANTUM, EquipmentSlot.HEAD), "quantum_helmet"));
		RebornRegistry.registerItem(TRContent.QUANTUM_CHESTPLATE = InitUtils.setup(new QuantumSuitItem(TRArmorMaterials.QUANTUM, EquipmentSlot.CHEST), "quantum_chestplate"));
		RebornRegistry.registerItem(TRContent.QUANTUM_LEGGINGS = InitUtils.setup(new QuantumSuitItem(TRArmorMaterials.QUANTUM, EquipmentSlot.LEGS), "quantum_leggings"));
		RebornRegistry.registerItem(TRContent.QUANTUM_BOOTS = InitUtils.setup(new QuantumSuitItem(TRArmorMaterials.QUANTUM, EquipmentSlot.FEET), "quantum_boots"));

		// Gem armor & tools
		RebornRegistry.registerItem(TRContent.AMETHYST_SWORD = InitUtils.setup(new TRSwordItem(TRToolTier.AMETHYST), "amethyst_sword"));
		RebornRegistry.registerItem(TRContent.AMETHYST_PICKAXE = InitUtils.setup(new TRPickaxeItem(TRToolTier.AMETHYST), "amethyst_pickaxe"));
		RebornRegistry.registerItem(TRContent.AMETHYST_SPADE = InitUtils.setup(new TRSpadeItem(TRToolTier.AMETHYST), "amethyst_spade"));
		RebornRegistry.registerItem(TRContent.AMETHYST_AXE = InitUtils.setup(new TRAxeItem(TRToolTier.AMETHYST), "amethyst_axe"));
		RebornRegistry.registerItem(TRContent.AMETHYST_HOE = InitUtils.setup(new TRHoeItem(TRToolTier.AMETHYST), "amethyst_hoe"));

		RebornRegistry.registerItem(TRContent.AMETHYST_HELMET = InitUtils.setup(new TRArmourItem(TRArmorMaterials.AMETHYST, EquipmentSlot.HEAD), "amethyst_helmet"));
		RebornRegistry.registerItem(TRContent.AMETHYST_CHESTPLATE = InitUtils.setup(new TRArmourItem(TRArmorMaterials.AMETHYST, EquipmentSlot.CHEST), "amethyst_chestplate"));
		RebornRegistry.registerItem(TRContent.AMETHYST_LEGGINGS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.AMETHYST, EquipmentSlot.LEGS), "amethyst_leggings"));
		RebornRegistry.registerItem(TRContent.AMETHYST_BOOTS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.AMETHYST, EquipmentSlot.FEET), "amethyst_boots"));

		RebornRegistry.registerItem(TRContent.BRONZE_SWORD = InitUtils.setup(new TRSwordItem(TRToolTier.BRONZE), "bronze_sword"));
		RebornRegistry.registerItem(TRContent.BRONZE_PICKAXE = InitUtils.setup(new TRPickaxeItem(TRToolTier.BRONZE), "bronze_pickaxe"));
		RebornRegistry.registerItem(TRContent.BRONZE_SPADE = InitUtils.setup(new TRSpadeItem(TRToolTier.BRONZE), "bronze_spade"));
		RebornRegistry.registerItem(TRContent.BRONZE_AXE = InitUtils.setup(new TRAxeItem(TRToolTier.BRONZE), "bronze_axe"));
		RebornRegistry.registerItem(TRContent.BRONZE_HOE = InitUtils.setup(new TRHoeItem(TRToolTier.BRONZE), "bronze_hoe"));

		RebornRegistry.registerItem(TRContent.BRONZE_HELMET = InitUtils.setup(new TRArmourItem(TRArmorMaterials.BRONZE, EquipmentSlot.HEAD), "bronze_helmet"));
		RebornRegistry.registerItem(TRContent.BRONZE_CHESTPLATE = InitUtils.setup(new TRArmourItem(TRArmorMaterials.BRONZE, EquipmentSlot.CHEST), "bronze_chestplate"));
		RebornRegistry.registerItem(TRContent.BRONZE_LEGGINGS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.BRONZE, EquipmentSlot.LEGS), "bronze_leggings"));
		RebornRegistry.registerItem(TRContent.BRONZE_BOOTS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.BRONZE, EquipmentSlot.FEET), "bronze_boots"));

		RebornRegistry.registerItem(TRContent.RUBY_SWORD = InitUtils.setup(new TRSwordItem(TRToolTier.RUBY), "ruby_sword"));
		RebornRegistry.registerItem(TRContent.RUBY_PICKAXE = InitUtils.setup(new TRPickaxeItem(TRToolTier.RUBY), "ruby_pickaxe"));
		RebornRegistry.registerItem(TRContent.RUBY_SPADE = InitUtils.setup(new TRSpadeItem(TRToolTier.RUBY), "ruby_spade"));
		RebornRegistry.registerItem(TRContent.RUBY_AXE = InitUtils.setup(new TRAxeItem(TRToolTier.RUBY), "ruby_axe"));
		RebornRegistry.registerItem(TRContent.RUBY_HOE = InitUtils.setup(new TRHoeItem(TRToolTier.RUBY), "ruby_hoe"));

		RebornRegistry.registerItem(TRContent.RUBY_HELMET = InitUtils.setup(new TRArmourItem(TRArmorMaterials.RUBY, EquipmentSlot.HEAD), "ruby_helmet"));
		RebornRegistry.registerItem(TRContent.RUBY_CHESTPLATE = InitUtils.setup(new TRArmourItem(TRArmorMaterials.RUBY, EquipmentSlot.CHEST), "ruby_chestplate"));
		RebornRegistry.registerItem(TRContent.RUBY_LEGGINGS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.RUBY, EquipmentSlot.LEGS), "ruby_leggings"));
		RebornRegistry.registerItem(TRContent.RUBY_BOOTS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.RUBY, EquipmentSlot.FEET), "ruby_boots"));

		RebornRegistry.registerItem(TRContent.SAPPHIRE_SWORD = InitUtils.setup(new TRSwordItem(TRToolTier.SAPPHIRE), "sapphire_sword"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_PICKAXE = InitUtils.setup(new TRPickaxeItem(TRToolTier.SAPPHIRE), "sapphire_pickaxe"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_SPADE = InitUtils.setup(new TRSpadeItem(TRToolTier.SAPPHIRE), "sapphire_spade"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_AXE = InitUtils.setup(new TRAxeItem(TRToolTier.SAPPHIRE), "sapphire_axe"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_HOE = InitUtils.setup(new TRHoeItem(TRToolTier.SAPPHIRE), "sapphire_hoe"));

		RebornRegistry.registerItem(TRContent.SAPPHIRE_HELMET = InitUtils.setup(new TRArmourItem(TRArmorMaterials.SAPPHIRE, EquipmentSlot.HEAD), "sapphire_helmet"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_CHESTPLATE = InitUtils.setup(new TRArmourItem(TRArmorMaterials.SAPPHIRE, EquipmentSlot.CHEST), "sapphire_chestplate"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_LEGGINGS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.SAPPHIRE, EquipmentSlot.LEGS), "sapphire_leggings"));
		RebornRegistry.registerItem(TRContent.SAPPHIRE_BOOTS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.SAPPHIRE, EquipmentSlot.FEET), "sapphire_boots"));

		RebornRegistry.registerItem(TRContent.PERIDOT_SWORD = InitUtils.setup(new TRSwordItem(TRToolTier.PERIDOT), "peridot_sword"));
		RebornRegistry.registerItem(TRContent.PERIDOT_PICKAXE = InitUtils.setup(new TRPickaxeItem(TRToolTier.PERIDOT), "peridot_pickaxe"));
		RebornRegistry.registerItem(TRContent.PERIDOT_SPADE = InitUtils.setup(new TRSpadeItem(TRToolTier.PERIDOT), "peridot_spade"));
		RebornRegistry.registerItem(TRContent.PERIDOT_AXE = InitUtils.setup(new TRAxeItem(TRToolTier.PERIDOT), "peridot_axe"));
		RebornRegistry.registerItem(TRContent.PERIDOT_HOE = InitUtils.setup(new TRHoeItem(TRToolTier.PERIDOT), "peridot_hoe"));

		RebornRegistry.registerItem(TRContent.PERIDOT_HELMET = InitUtils.setup(new TRArmourItem(TRArmorMaterials.PERIDOT, EquipmentSlot.HEAD), "peridot_helmet"));
		RebornRegistry.registerItem(TRContent.PERIDOT_CHESTPLATE = InitUtils.setup(new TRArmourItem(TRArmorMaterials.PERIDOT, EquipmentSlot.CHEST), "peridot_chestplate"));
		RebornRegistry.registerItem(TRContent.PERIDOT_LEGGINGS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.PERIDOT, EquipmentSlot.LEGS), "peridot_leggings"));
		RebornRegistry.registerItem(TRContent.PERIDOT_BOOTS = InitUtils.setup(new TRArmourItem(TRArmorMaterials.PERIDOT, EquipmentSlot.FEET), "peridot_boots"));

		// Battery
		RebornRegistry.registerItem(TRContent.RED_CELL_BATTERY = InitUtils.setup(new BatteryItem(TechRebornConfig.redCellBatteryMaxCharge, EnergyTier.LOW), "red_cell_battery"));
		RebornRegistry.registerItem(TRContent.LITHIUM_ION_BATTERY = InitUtils.setup(new BatteryItem(TechRebornConfig.lithiumIonBatteryMaxCharge, EnergyTier.MEDIUM), "lithium_ion_battery"));
		RebornRegistry.registerItem(TRContent.LITHIUM_ION_BATPACK = InitUtils.setup(new BatpackItem(TechRebornConfig.lithiumBatpackCharge, TRArmorMaterials.LITHIUM_BATPACK, EnergyTier.MEDIUM), "lithium_ion_batpack"));
		RebornRegistry.registerItem(TRContent.ENERGY_CRYSTAL = InitUtils.setup(new BatteryItem(TechRebornConfig.energyCrystalMaxCharge, EnergyTier.HIGH), "energy_crystal"));
		RebornRegistry.registerItem(TRContent.LAPOTRON_CRYSTAL = InitUtils.setup(new BatteryItem(TechRebornConfig.lapotronCrystalMaxCharge, EnergyTier.EXTREME), "lapotron_crystal"));
		RebornRegistry.registerItem(TRContent.LAPOTRONIC_ORB = InitUtils.setup(new BatteryItem(TechRebornConfig.lapotronicOrbMaxCharge, EnergyTier.INSANE), "lapotronic_orb"));
		RebornRegistry.registerItem(TRContent.LAPOTRONIC_ORBPACK = InitUtils.setup(new BatpackItem(TechRebornConfig.lapotronPackCharge, TRArmorMaterials.LAPOTRONIC_ORBPACK, EnergyTier.INSANE), "lapotronic_orbpack"));

		// Tools
		RebornRegistry.registerItem(TRContent.TREE_TAP = InitUtils.setup(new TreeTapItem(), "treetap"));
		RebornRegistry.registerItem(TRContent.WRENCH = InitUtils.setup(new WrenchItem(), "wrench"));
		RebornRegistry.registerItem(TRContent.PAINTING_TOOL = InitUtils.setup(new PaintingToolItem(), "painting_tool"));

		RebornRegistry.registerItem(TRContent.BASIC_DRILL = InitUtils.setup(new DrillItem(ToolMaterials.IRON, TechRebornConfig.basicDrillCharge, EnergyTier.MEDIUM, TechRebornConfig.basicDrillCost, 6F, -.5F, MiningLevel.IRON), "basic_drill"));
		RebornRegistry.registerItem(TRContent.BASIC_CHAINSAW = InitUtils.setup(new ChainsawItem(ToolMaterials.IRON, TechRebornConfig.basicChainsawCharge, EnergyTier.MEDIUM, TechRebornConfig.basicChainsawCost, 20F, 0.5F, Items.IRON_AXE), "basic_chainsaw"));
		RebornRegistry.registerItem(TRContent.BASIC_JACKHAMMER = InitUtils.setup(new JackhammerItem(TechRebornConfig.basicJackhammerCharge, EnergyTier.MEDIUM, TechRebornConfig.basicJackhammerCost), "basic_jackhammer"));
		RebornRegistry.registerItem(TRContent.ELECTRIC_TREE_TAP = InitUtils.setup(new ElectricTreetapItem(), "electric_treetap"));

		RebornRegistry.registerItem(TRContent.ADVANCED_DRILL = InitUtils.setup(new DrillItem(ToolMaterials.DIAMOND, TechRebornConfig.advancedDrillCharge, EnergyTier.EXTREME, TechRebornConfig.advancedDrillCost, 12F, 0.2F, MiningLevel.DIAMOND), "advanced_drill"));
		RebornRegistry.registerItem(TRContent.ADVANCED_CHAINSAW = InitUtils.setup(new ChainsawItem(ToolMaterials.DIAMOND, TechRebornConfig.advancedChainsawCharge, EnergyTier.EXTREME, TechRebornConfig.advancedChainsawCost, 20F, 1.0F, Items.DIAMOND_AXE), "advanced_chainsaw"));
		RebornRegistry.registerItem(TRContent.ADVANCED_JACKHAMMER = InitUtils.setup(new AdvancedJackhammerItem(), "advanced_jackhammer"));
		RebornRegistry.registerItem(TRContent.ROCK_CUTTER = InitUtils.setup(new RockCutterItem(), "rock_cutter"));

		RebornRegistry.registerItem(TRContent.INDUSTRIAL_DRILL = InitUtils.setup(new IndustrialDrillItem(), "industrial_drill"));
		RebornRegistry.registerItem(TRContent.INDUSTRIAL_CHAINSAW = InitUtils.setup(new IndustrialChainsawItem(), "industrial_chainsaw"));
		RebornRegistry.registerItem(TRContent.INDUSTRIAL_JACKHAMMER = InitUtils.setup(new IndustrialJackhammerItem(), "industrial_jackhammer"));
		RebornRegistry.registerItem(TRContent.NANOSABER = InitUtils.setup(new NanosaberItem(), "nanosaber"));
		RebornRegistry.registerItem(TRContent.OMNI_TOOL = InitUtils.setup(new OmniToolItem(), "omni_tool"));

		// Armor
		RebornRegistry.registerItem(TRContent.CLOAKING_DEVICE = InitUtils.setup(new CloakingDeviceItem(), "cloaking_device"));

		// Other
		RebornRegistry.registerItem(TRContent.FREQUENCY_TRANSMITTER = InitUtils.setup(new FrequencyTransmitterItem(), "frequency_transmitter"));
		RebornRegistry.registerItem(TRContent.SCRAP_BOX = InitUtils.setup(new ScrapBoxItem(), "scrap_box"));
		RebornRegistry.registerItem(TRContent.MANUAL = InitUtils.setup(new ManualItem(), "manual"));
		RebornRegistry.registerItem(TRContent.DEBUG_TOOL = InitUtils.setup(new DebugToolItem(), "debug_tool"));
		RebornRegistry.registerItem(TRContent.CELL = InitUtils.setup(new DynamicCellItem(), "cell"));

		TechReborn.LOGGER.debug("TechReborns Items Loaded");
	}

	private static void registerFluids() {
		Arrays.stream(ModFluids.values()).forEach(ModFluids::register);
	}

	private static void registerSounds() {
		ModSounds.ALARM = InitUtils.setup("alarm");
		ModSounds.ALARM_2 = InitUtils.setup("alarm_2");
		ModSounds.ALARM_3 = InitUtils.setup("alarm_3");
		ModSounds.AUTO_CRAFTING = InitUtils.setup("auto_crafting");
		ModSounds.BLOCK_DISMANTLE = InitUtils.setup("block_dismantle");
		ModSounds.CABLE_SHOCK = InitUtils.setup("cable_shock");
		ModSounds.MACHINE_RUN = InitUtils.setup("machine_run");
		ModSounds.MACHINE_START = InitUtils.setup("machine_start");
		ModSounds.SAP_EXTRACT = InitUtils.setup("sap_extract");
	}
}
