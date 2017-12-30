/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ibtrader.data.model.impl;

import com.ibtrader.data.model.Position;
import com.ibtrader.util.PositionStates;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Position service. Represents a row in the &quot;ibtrader_Position&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.model.Position} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PositionImpl extends PositionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a position model instance should use the {@link com.ibtrader.data.model.Position} interface instead.
	 */
	public PositionImpl() {
	}
	/* SI NO ESTA VENDIDA */
	public boolean IsOpen() {
		
		return !this.getState().equals(PositionStates.status.SELL_OK); 
	}
	/* PENDIENTE DE ENTRAR, NO ESTA COMPRADA, NI VENDIDA    */
	public boolean IsPendingIn() {
		return this.getDate_real_in()==null &&   !this.getState().equals(PositionStates.status.BUY_OK); 
	}
	public boolean IsPendingOut() {
		 return this.getDate_real_out()==null &&   this.getState().equals(PositionStates.status.BUY_OK); 
	}
	public boolean IsClosed() {
		 return this.getState().equals(PositionStates.status.SELL_OK); 	
  }
	
}