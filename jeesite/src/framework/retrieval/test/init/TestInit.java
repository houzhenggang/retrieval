package framework.retrieval.test.init;

import framework.retrieval.engine.context.RetrievalApplicationContext;

public class TestInit {
	private static RetrievalApplicationContext retrievalApplicationContext=new RetrievalApplicationContext("D:\\workspace\\index");
	
	public static RetrievalApplicationContext getApplicationContent(){
		return retrievalApplicationContext;
	}
	
	public static void initIndex(){
		retrievalApplicationContext.getFacade().initIndex(new String[]{"BS/DB"});
	}
	
	public static void main(String[] args) {
		TestInit.initIndex();
	}
	
}
