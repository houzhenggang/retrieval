package framework.retrieval.test.query;

import java.util.List;

import framework.retrieval.engine.RetrievalType;
import framework.retrieval.engine.context.ApplicationContext;
import framework.retrieval.engine.context.RetrievalApplicationContext;
import framework.retrieval.engine.query.item.QueryItem;
import framework.retrieval.helper.RetrievalDatabasePage;
import framework.retrieval.helper.RetrievalFilePage;
import framework.retrieval.helper.RetrievalPage;
import framework.retrieval.helper.RetrievalPageQuery;
import framework.retrieval.helper.RetrievalPageQueryHelper;
import framework.retrieval.test.dbindexall.TestDatabaseIndexAll;
import framework.retrieval.test.init.TestInit;

public class TestRetrievalPageQueryHelper {
//	private RetrievalApplicationContext retrievalApplicationContext=TestInit.getApplicationContent();
	private RetrievalApplicationContext retrievalApplicationContext = ApplicationContext.getApplicationContent();
	
	public QueryItem createQueryItem(RetrievalType.RDocItemType docItemType,Object name,String value,Float score){
		QueryItem queryItem=retrievalApplicationContext.getFacade().createQueryItem(docItemType, String.valueOf(name), value, score);
		return queryItem;
	}
	
	public List<RetrievalPage> getRetrievalPage(RetrievalPageQuery retrievalPageQuery,QueryItem queryItem){

		RetrievalPageQueryHelper retrievalPageQueryHelper=new RetrievalPageQueryHelper(retrievalApplicationContext,new String[]{"BS/DB/TEST_WEB"},queryItem);
		
		return retrievalPageQueryHelper.getResults(retrievalPageQuery);
		
	}
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		TestRetrievalPageQueryHelper testInternalRetrievalQueryHelper=new TestRetrievalPageQueryHelper();
		
		RetrievalPageQuery retrievalPageQuery = new RetrievalPageQuery();
		retrievalPageQuery.setTitleFieldName("TITLE");
		retrievalPageQuery.setTitleLength(50);
		retrievalPageQuery.setResumeFieldName("CONTENT");
		retrievalPageQuery.setResumeLength(300);
		retrievalPageQuery.setPageSize(15);
		retrievalPageQuery.setNowStartPage(0);
		retrievalPageQuery.setQueryFields(new String[]{"PAGE_URL"});
		
		QueryItem queryItem0=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"TITLE","城镇化",100f);
//		QueryItem queryItem1=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"TITLE","哦",null);
//		QueryItem queryItem2=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"CONTENT","城镇化健康发展需要新的制度变革",null);
//		QueryItem queryItem3=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"CONTENT","地方");
//		QueryItem queryItem4=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"FIELD3","过节");
//		QueryItem queryItem5=testInternalRetrievalQueryHelper.createQueryItem(RetrievalType.RDocItemType.CONTENT,"FIELD4","台湾");

//		QueryItem queryItem=queryItem0.should(QueryItem.SHOULD,queryItem1).should(queryItem2).should(queryItem3.mustNot(QueryItem.SHOULD,queryItem4)).should(queryItem5);
//		QueryItem queryItem=queryItem0.should(QueryItem.SHOULD,queryItem2);
		List<RetrievalPage> retrievalPageList=testInternalRetrievalQueryHelper.getRetrievalPage(retrievalPageQuery, queryItem0);
//		TestDatabaseIndexAll testDatabaseIndexAll=new TestDatabaseIndexAll();
//		System.out.println("first time :"+retrievalPageList.size());
//		testDatabaseIndexAll.create();
//		List<RetrievalPage> retrievalPageList1=testInternalRetrievalQueryHelper.getRetrievalPage(retrievalPageQuery, queryItem);
//		System.out.println("second time :"+retrievalPageList1.size());
		
		System.out.println("retrievalPageList="+retrievalPageList);
		long endTime = System.currentTimeMillis();
		System.out.println("用时："+(endTime-startTime));
		if(retrievalPageList!=null){
			System.out.println("retrievalPageList.size()="+retrievalPageList.size());
			for(RetrievalPage retrievalPage : retrievalPageList){
				String sourceIndexType=retrievalPage.getSourceIndexType();
				System.out.println("sourceIndexType="+sourceIndexType);
				if(sourceIndexType.equals(String.valueOf(RetrievalType.RIndexSourceType.D))){
					RetrievalDatabasePage retrievalDatabasePage=retrievalPage.getRetrievalDatabasePage();
					System.out.print("tableName:"+retrievalDatabasePage.getTableName()+";");
					System.out.print("recordId:"+retrievalDatabasePage.getRecordId()+";");
					System.out.print("keyFieldName:"+retrievalDatabasePage.getKeyfieldName()+";");
					System.out.print("title:"+retrievalPage.getTitle()+";");
					System.out.print("content:"+retrievalPage.getContent()+";");
					System.out.print("createTime:"+retrievalPage.getCreateTime()+";");
					RetrievalFilePage[] retrievalFilePages=retrievalPage.getRetrievalFilePages();
					if(retrievalFilePages!=null){
						for(RetrievalFilePage retrievalFilePage : retrievalFilePages){
							System.out.println("fileId:"+retrievalFilePage.getFileId()+";");
							System.out.println("fileName:"+retrievalFilePage.getFileName()+";");
							System.out.println("fileRelativePath:"+retrievalFilePage.getFileRelativePath()+";");
							System.out.println("fileModfiyTime:"+retrievalFilePage.getModfiyTime()+";");
							System.out.println("fileContent:"+retrievalFilePage.getFileContent()+";");
						}
					}
				}else if(sourceIndexType.equals(String.valueOf(RetrievalType.RIndexSourceType.F))){
					RetrievalFilePage[] retrievalFilePages=retrievalPage.getRetrievalFilePages();
					if(retrievalFilePages!=null && retrievalFilePages.length>0){
						RetrievalFilePage retrievalFilePage=retrievalFilePages[0];
						System.out.println("fileId:"+retrievalFilePage.getFileId()+";");
						System.out.println("fileName:"+retrievalFilePage.getFileName()+";");
						System.out.println("fileRelativePath:"+retrievalFilePage.getFileRelativePath()+";");
						System.out.println("fileModfiyTime:"+retrievalFilePage.getModfiyTime()+";");
						System.out.println("fileContent:"+retrievalFilePage.getFileContent()+";");
					}
				}
			}
		}
	}
}
