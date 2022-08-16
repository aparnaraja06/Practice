package instance;

import checker.Checker;
import custom.CustomException;
import operation.CoinOperation;

public enum CreateInstance {
	
COINOPERATION;
	
	CoinOperation coin = null;
	
	Checker check = null;
	
	public CoinOperation getCoinInstance()throws CustomException
	{
		if(coin==null)
		{
			coin = new CoinOperation();
			
			coin.createTable();
			
		}
		
		return coin;
		
	}
	
	public Checker getCheckInstance()
	{
		if(check==null)
		{
			check = new Checker();
		}
		
		return check;
	}
	
	

}
