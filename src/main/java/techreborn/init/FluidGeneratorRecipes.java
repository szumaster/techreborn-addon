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

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import techreborn.api.generator.EFluidGenerator;
import techreborn.api.generator.GeneratorRecipeHelper;

/**
 * Created by Prospector
 */
public class FluidGeneratorRecipes {
	public static void init() {
		register(EFluidGenerator.DIESEL, ModFluids.NITROFUEL.getFluid(), 24);
		register(EFluidGenerator.DIESEL, ModFluids.NITROCOAL_FUEL.getFluid(), 48);
		register(EFluidGenerator.DIESEL, ModFluids.DIESEL.getFluid(), 128);
		register(EFluidGenerator.DIESEL, ModFluids.NITRO_DIESEL.getFluid(), 400);

		register(EFluidGenerator.SEMIFLUID, ModFluids.SODIUM.getFluid(), 30);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LITHIUM.getFluid(), 60);
		register(EFluidGenerator.SEMIFLUID, ModFluids.OIL.getFluid(), 16);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BIOFUEL.getFluid(), 6);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ACTINIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ALUMINUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.AMERICIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ANTIMONY.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ARSENIC.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ASTATINE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BARIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BERKELIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BERYL.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BERYLLIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BISMUTH.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BOHRIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.BORON.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CADMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CALCIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CALCIUM_CARBONATE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CALIFORNIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CARBON.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CARBON_FIBER.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CERIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CESIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CHLORITE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CHROMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.COBALT.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.COMPRESSED_AIR.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.COPERNICIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.COPPER.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.CURIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.DARMSTADTIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.DEUTERIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.DUBNIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.DYSPROSIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.EINSTEINIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ELECTROLYZED_WATER.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ERBIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.EUROPIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.FERMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.FLEROVIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.FRANCIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.GADOLINIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.GALLIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.GERMANIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.GLYCERYL.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.HAFNIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.HASSIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.HELIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.HELIUM3.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.HOLMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.INDIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.IRIDIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LANTHANUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LAWRENCIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LEAD.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LIQUID_AIR.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LIVERMORIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.LUTETIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MAGNESIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MANGANESE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MEITNERIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MENDELEVIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MERCURY.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MOLYBDENUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.MOSCOVIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NEODYMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NEPTUNIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NICKEL.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NIHONIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NIOBIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NITROGEN.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NITROGEN_DIOXIDE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NITRO_CARBON.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.NOBELIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.OGANESSON.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.OSMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PALLADIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PLATINUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PLUTONIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.POLONIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.POTASSIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PRASEODYMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PROMETHIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.PROTACTINIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RADIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RHENIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RHODIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ROENTGENIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RUBIDIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RUTHENIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.RUTHERFORDIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SAMARIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SCANDIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SEABORGIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SELENIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SILICON.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SILVER.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SODIUM_HYDROXIDE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SODIUM_PERSULFATE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SODIUM_SULFIDE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.STRONTIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SULFUR.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.SULFURIC_ACID.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TANTALUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TECHNETIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TELLURIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TENNESSINE.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TERBIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.THALLIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.THORIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.THULIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TIN.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TITANIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TRITIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.TUNGSTEN.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.URANIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.VANADIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.WOLFRAMIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.YTTERBIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.YTTRIUM.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ZINC.getFluid(), 10);
		register(EFluidGenerator.SEMIFLUID, ModFluids.ZIRCONIUM.getFluid(), 10);

		register(EFluidGenerator.THERMAL, Fluids.LAVA, 60);

		register(EFluidGenerator.GAS, ModFluids.ARGON.getFluid(), 35);
		register(EFluidGenerator.GAS, ModFluids.CHLORINE.getFluid(), 15);
		register(EFluidGenerator.GAS, ModFluids.FLUORINE.getFluid(), 15);
		register(EFluidGenerator.GAS, ModFluids.HYDROGEN.getFluid(), 15);
		register(EFluidGenerator.GAS, ModFluids.KRYPTON.getFluid(), 40);
		register(EFluidGenerator.GAS, ModFluids.METHANE.getFluid(), 45);
		register(EFluidGenerator.GAS, ModFluids.NEON.getFluid(), 20);
		register(EFluidGenerator.GAS, ModFluids.RADON.getFluid(), 25);
		register(EFluidGenerator.GAS, ModFluids.XENON.getFluid(), 20);

		register(EFluidGenerator.PLASMA, ModFluids.HELIUMPLASMA.getFluid(), 8192);
	}

	static void register(EFluidGenerator generator, Fluid fluid, int euPerMB) {
		GeneratorRecipeHelper.registerFluidRecipe(generator, fluid, euPerMB);
	}
}
