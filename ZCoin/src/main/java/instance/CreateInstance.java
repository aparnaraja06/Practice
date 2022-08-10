package instance;

import custom.CustomException;
import operation.CoinOperation;

public enum CreateInstance {
	
COINOPERATION;
	
	CoinOperation coin = null;
	
	public CoinOperation getCoinInstance()throws CustomException
	{
		if(coin==null)
		{
			coin = new CoinOperation();
			
			coin.createTable();
			
		}
		
		return coin;
		
	}

}
