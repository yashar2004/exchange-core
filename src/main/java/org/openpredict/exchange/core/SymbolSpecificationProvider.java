package org.openpredict.exchange.core;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.openpredict.exchange.beans.CoreSymbolSpecification;
import org.openpredict.exchange.beans.cmd.CommandResultCode;

@Slf4j
public final class SymbolSpecificationProvider {

    // symbol->specs
    private final IntObjectHashMap<CoreSymbolSpecification> symbolSpecs = new IntObjectHashMap<>();


    public CommandResultCode addSymbol(final CoreSymbolSpecification symbolSpecification) {
        if (getSymbolSpecification(symbolSpecification.symbolId) != null) {
            return CommandResultCode.SYMBOL_MGMT_SYMBOL_ALREADY_EXISTS;
        } else {
            registerSymbol(symbolSpecification.symbolId, symbolSpecification);
            return CommandResultCode.VALID_FOR_MATCHING_ENGINE;
        }
    }

    /**
     * Get symbol specification
     *
     * @param symbol
     * @return
     */
    public CoreSymbolSpecification getSymbolSpecification(int symbol) {
        return symbolSpecs.get(symbol);
    }

    /**
     * register new symbol specification
     *
     * @param symbol
     * @param spec
     */
    public void registerSymbol(int symbol, CoreSymbolSpecification spec) {
        symbolSpecs.put(symbol, spec);
    }

    /**
     * Reset state
     */
    public void reset() {
        symbolSpecs.clear();
    }
}
