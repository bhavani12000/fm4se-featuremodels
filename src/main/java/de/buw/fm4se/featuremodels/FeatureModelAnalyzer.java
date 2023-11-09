package de.buw.fm4se.featuremodels;

import java.util.ArrayList;
import java.util.List;

import de.buw.fm4se.featuremodels.exec.LimbooleExecutor;
import de.buw.fm4se.featuremodels.fm.FeatureModel;

public class FeatureModelAnalyzer {

    
    public static boolean checkAllProductsPreserved(FeatureModel fm1, FeatureModel fm2) {
        
        String formulaFM1 = FeatureModelTranslator.translateToFormula(fm1);
        String formulaFM2 = FeatureModelTranslator.translateToFormula(fm2);

        
        return checkProductsPreserved(formulaFM1, formulaFM2);
    }

    private static boolean checkProductsPreserved(String formulaFM1, String formulaFM2) {
        
        for (Product product : fm1.getAllProducts()) {
            
            boolean isProductPreserved = isProductPreserved(product, formulaFM1, formulaFM2);

            
            if (!isProductPreserved) {
                return false;
            }
        }

        // All products of fm1 are preserved in fm2
        return true;
    }

    private static boolean isProductPreserved(Product product, String formulaFM1, String formulaFM2) {
      
        String substitutedFormulaFM1 = substituteFeatureValues(formulaFM1, product);

        
        return checkSatisfiability(substitutedFormulaFM1, formulaFM2);
    }

    private static String substituteFeatureValues(String formula, Product product) {
     
        return formula;
    }

    private static boolean checkSatisfiability(String formulaFM1, String formulaFM2) {
        
        String implicationFormula = String.format("(%s -> %s)", formulaFM1, formulaFM2);

       
        try {
            String result = LimbooleExecutor.runLimboole(implicationFormula, true);
            return !result.contains("UNSATISFIABLE");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

  

}
