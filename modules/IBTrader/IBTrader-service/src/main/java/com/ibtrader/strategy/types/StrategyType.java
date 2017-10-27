package com.ibtrader.strategy.types;

import com.ibtrader.data.model.Strategy;

public interface  StrategyType {
		public void verify(Strategy strategy);
		public void execute(Strategy strategy);

	}

