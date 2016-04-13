package org.functionalkoans.forscala

import org.functionalkoans.forscala.support.Master
import org.scalatest._

class Koans extends Suites(
  new AboutAsserts,
  new AboutValAndVar,
  new AboutLiteralBooleans,
  new AboutLiteralNumbers,
  new AboutLiteralStrings,
  new AboutMethods,
  new AboutClasses,
  new AboutUniformAccessPrinciple,
  new AboutConstructors,
  new AboutParentClasses,
  new AboutOptions,
  new AboutObjects,
  new AboutApply,
  new AboutTuples,
  new AboutHigherOrderFunctions,
  new AboutEmptyValues,
  new AboutLists,
  new AboutMaps,
  new AboutSets,
  new AboutFormatting,
  new AboutStringInterpolation,
  new AboutPatternMatching,
  new AboutCaseClasses,
  new AboutRange,
  new AboutPartiallyAppliedFunctions,
  new AboutPartialFunctions,
  new AboutImplicits,
  new AboutTraits,
  new AboutForExpressions,
  new AboutInfixPrefixAndPostfixOperators,
  new AboutInfixTypes,
  new AboutMutableMaps,
  new AboutMutableSets,
  new AboutSequencesAndArrays,
  new AboutIterables,
  new AboutTraversables,
  new AboutNamedAndDefaultArguments,
  new AboutTypeTags,
  new AboutPreconditions,
  new AboutExtractors,
  new AboutByNameParameter,
  new AboutRepeatedParameters,
  new AboutTypeSignatures,
  new AboutTypeVariance,
  new AboutEnumerations,
  new AboutMacros) {

  override def run(testName: Option[String], args: Args) = {
    super.run(testName, args.copy(stopper = Master))
  }

  override protected def runTest(testName: String, args: Args) = {
    super.runTest(testName, args.copy(stopper = Master))
  }
}
