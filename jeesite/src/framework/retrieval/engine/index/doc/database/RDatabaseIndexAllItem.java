/**
 * Copyright 2010 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package framework.retrieval.engine.index.doc.database;

import java.io.Serializable;

import framework.retrieval.engine.RetrievalConstant;
import framework.retrieval.engine.RetrievalType;
import framework.retrieval.engine.facade.IRDocOperatorFacade;
import framework.retrieval.engine.facade.IRQueryFacade;
import framework.retrieval.engine.index.all.database.IIndexAllDatabaseFileIndexOperator;
import framework.retrieval.engine.index.all.database.IIndexAllDatabaseRecordInterceptor;

/**
 * 数据库批量索引对象
 * @author 
 *
 */
public class RDatabaseIndexAllItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 是否同时再索引中生成一个将所有内容组合存放的字段
	 */
	private boolean fullContentFlag=false;
	
	/**
	 * 索引路径类型
	 */
	private String indexPathType=null;
	
	/**
	 * 索引操作类型
	 */
	private RetrievalType.RIndexOperatorType indexOperatorType=null;
	
	/**
	 * 索引信息分类标志
	 * 		如：知识库、信息发布、停电信息
	 */
	private String indexInfoType="";
	
	/**
	 * 默认标题字段名
	 */
	private String defaultTitleFieldName="";
	
	/**
	 * 默认摘要字段名
	 */
	private String defaultResumeFieldName="";
	
	/**
	 * 根据数据库ID，获取相关文件信息
	 */
	private IIndexAllDatabaseFileIndexOperator databaseFileIndexOperator;
	
	/**
	 * 单条数据库记录拦截器
	 */
	private IIndexAllDatabaseRecordInterceptor databaseRecordInterceptor;

	private IRQueryFacade queryFacade=null;
	
	private IRDocOperatorFacade docOperatorFacade=null;
	
	private String tableName;
	
	private String keyField;
	
	private String sql;
	
	private Object[] param;
	
	private int pageSize=5000;
	
	private int maxPageSize=10000;
	
	private long maxIndexFileSize=RetrievalConstant.DEFAULT_INDEX_MAX_FILE_SZIE;
	
	/**
	 * 
	 * @param queryFacade 
	 * @param docOperatorFacade 
	 * @param fullContentFlag 是否同时再索引中生成一个将所有内容组合存放的字段
	 * @param maxPageSize 最大文件分页数量，每页读取的记录数量不允许超过这个最大值
	 */
	public RDatabaseIndexAllItem(IRQueryFacade queryFacade,IRDocOperatorFacade docOperatorFacade,boolean fullContentFlag,int maxPageSize){
		this.queryFacade=queryFacade;
		this.docOperatorFacade=docOperatorFacade;
		this.fullContentFlag=fullContentFlag;
		this.maxPageSize=maxPageSize;
	}
	
	public IRQueryFacade getQueryFacade() {
		return queryFacade;
	}

	public IRDocOperatorFacade getDocOperatorFacade() {
		return docOperatorFacade;
	}

	/**
	 * 是否将所有内容组合成一个字段存放
	 * @return
	 */
	public boolean isFullContentFlag() {
		return fullContentFlag;
	}

	/**
	 * 获取信息分类
	 * @return
	 */
	public String getIndexInfoType() {
		return indexInfoType;
	}

	/**
	 * 设置信息分类
	 * @param indexInfoType
	 */
	public void setIndexInfoType(String indexInfoType) {
		this.indexInfoType = indexInfoType;
	}
	
	/**
	 * 获取默认的标题字段名
	 * @return
	 */
	public String getDefaultTitleFieldName(){
		return defaultTitleFieldName;
	}
	
	/**
	 * 设置默认的标题字段名
	 * @param defaultTitleFieldName
	 */
	public void setDefaultTitleFieldName(String defaultTitleFieldName){
		this.defaultTitleFieldName=defaultTitleFieldName;
	}
	
	/**
	 * 获取默认的摘要字段名
	 * @return
	 */
	public String getDefaultResumeFieldName(){
		return defaultResumeFieldName;
	}
	
	/**
	 * 设置默认的摘要字段名
	 * @param defaultResumeFieldName
	 */
	public void setDefaultResumeFieldName(String defaultResumeFieldName){
		this.defaultResumeFieldName=defaultResumeFieldName;
	}

	/**
	 * 获取索引路径类型
	 * @return
	 */
	public String getIndexPathType() {
		return indexPathType;
	}

	/**
	 * 设置索引路径类型
	 * @param indexPathType
	 */
	public void setIndexPathType(String indexPathType) {
		this.indexPathType = indexPathType.toUpperCase();
	}

	/**
	 * 获取索引操作类型
	 * @return
	 */
	public RetrievalType.RIndexOperatorType getIndexOperatorType() {
		return indexOperatorType;
	}

	/**
	 * 设置索引操作类型
	 * @param indexOperatorType
	 */
	public void setIndexOperatorType(RetrievalType.RIndexOperatorType indexOperatorType) {
		this.indexOperatorType = indexOperatorType;
	}

	/**
	 * 获取根据数据库ID，获取相关文件信息处理对象
	 * @return
	 */
	public IIndexAllDatabaseFileIndexOperator getDatabaseFileIndexOperator() {
		return databaseFileIndexOperator;
	}

	/**
	 * 设置根据数据库ID，获取相关文件信息处理对象
	 * @param databaseFileIndexOperator
	 */
	public void setDatabaseFileIndexOperator(
			IIndexAllDatabaseFileIndexOperator databaseFileIndexOperator) {
		this.databaseFileIndexOperator = databaseFileIndexOperator;
	}

	/**
	 * 获取当条数据加工处理器
	 * @return
	 */
	public IIndexAllDatabaseRecordInterceptor getDatabaseRecordInterceptor() {
		return databaseRecordInterceptor;
	}

	/**
	 * 设置当条数据加工处理器
	 * @param databaseRecordInterceptor
	 */
	public void setDatabaseRecordInterceptor(
			IIndexAllDatabaseRecordInterceptor databaseRecordInterceptor) {
		this.databaseRecordInterceptor = databaseRecordInterceptor;
	}

	/**
	 * 获取数据库表名
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 设置数据库表名
	 * @param tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 获取数据库表关键字段名称
	 * @return
	 */
	public String getKeyField() {
		return keyField;
	}

	/**
	 * 设置数据库表关键字段名称
	 * @param keyField
	 */
	public void setKeyField(String keyField) {
		this.keyField = keyField.toUpperCase();
	}

	/**
	 * 获取SQL
	 * @return
	 */
	public String getSql() {
		return sql;
	}
	
	/**
	 * 设置SQL
	 * @param sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	/**
	 * 获取SQL参数
	 * @return
	 */
	public Object[] getParam() {
		return param;
	}
	
	/**
	 * 设置SQL参数
	 * @param param
	 */
	public void setParam(Object[] param) {
		this.param = param;
	}

	/**
	 * 获取每页大小，默认值为 100
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页大小，默认值为 100
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if(pageSize>maxPageSize){
			pageSize=maxPageSize;
		}
		this.pageSize = pageSize;
	}

	public long getMaxIndexFileSize() {
		return maxIndexFileSize;
	}

	public void setMaxIndexFileSize(long maxIndexFileSize) {
		this.maxIndexFileSize = maxIndexFileSize;
	}

}
