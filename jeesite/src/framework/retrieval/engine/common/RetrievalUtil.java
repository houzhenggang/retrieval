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
package framework.retrieval.engine.common;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.util.Version;

import framework.base.snoic.base.util.RandomSeed;
import framework.base.snoic.base.util.StringClass;
import framework.base.snoic.base.util.file.SnoicsFileHelper;
import framework.retrieval.engine.RetrievalConstant;
import framework.retrieval.engine.RetrievalException;

/**
 * 公用方法
 *
 * @author 
 *
 *
 */

public class RetrievalUtil {
    private static SnoicsFileHelper fileHelper=new SnoicsFileHelper();

	private RetrievalUtil(){
		
	}
	
	@SuppressWarnings("unchecked")
	public static Log getLog(Class clazz){
		Log log=LogFactory.getLog(clazz);
		return log;
	}
	
	public static void debugLog(Log log,Object msg){
		if(log.isDebugEnabled()){
			log.debug(msg);
		}
	}
	
	public static void infoLog(Log log,Object msg){
		if(log.isInfoEnabled()){
			log.info(msg);
		}
	}
	
	public static void errorLog(Log log,String msg){
		if(log.isErrorEnabled()){
			log.error(msg);
		}
	}
	
	public static void errorLog(Log log,Throwable e){
		if(log.isErrorEnabled()){
			log.error(e);
		}
	}
	
	public static void errorLog(Log log,String msg,Throwable e){
		if(log.isErrorEnabled()){
			log.error(msg,e);
		}
	}
	
	/**
	 * 获取某种类型的索引路径
	 * @param baseIndexPath
	 * @param indexPathType
	 * @return
	 */
	public static String getIndexPath(String baseIndexPath,String indexPathType){
		if(indexPathType==null){
			throw new RetrievalException("索引类型路径不允许为空!!!");
		}
		String path=StringClass.getFormatPath(baseIndexPath+"/"+String.valueOf(indexPathType).toUpperCase());
		fileHelper.createDir(path);
		return path;
	}
	
	/**
	 * 获取随机生成的唯一索引ID
	 * @return
	 */
	public static String getIndexId(){
		return RandomSeed.getSeed(32);
	}
	
	/**
	 * 获取随机生成的唯一索引ID
	 * @return
	 */
	public static String getFileName(){
		return RandomSeed.getSeed(16);
	}
	
	/**
	 * 从HTML中解析出内容
	 * @param htmlContent
	 * @param charsetName
	 * @return
	 */
	public static String parseHTML(String htmlContent, String charsetName) {
		if (null == htmlContent || "".equals(htmlContent.trim())) {
			return htmlContent;
		}

		StringBuffer txt=new StringBuffer();
		Pattern pattern = Pattern.compile("<[^<|^>]*>",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(htmlContent);
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}

		matcher.appendTail(txt);
		String str=txt.toString();
		
		return str;
	}
	
	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName){
		if(fileName.indexOf(".")<0){
			return "";
		}
		
		int extendIndex=0;
		extendIndex=fileName.lastIndexOf(".");
		String theExtend="";
        theExtend=fileName.substring(extendIndex+1,fileName.length()).toUpperCase();
        
        return theExtend;
	}
	
	/**
	 * 生成所有字段名称列表
	 * @param fieldNames
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getAllFields(String fieldNames){
		fieldNames=StringClass.getString(fieldNames);
		if(fieldNames.equals("")){
			return null;
		}
		List<String> list=StringClass.getInterString(RetrievalConstant.DEFAULT_INDEX_FIELD_NAME_SPLIT, fieldNames);
		return list;
	}
	
	/**
	 * 获取版本号
	 * @param versionType
	 * @return
	 */
	public static Version getVersion(String versionType){
		versionType=StringClass.getString(versionType).toUpperCase();
		if(versionType.equals("")){
			throw new RetrievalException("版本号为空!!!!");
		}else{
			if(versionType.equals("LUCENE_20")){
				return Version.LUCENE_20;
			}else if(versionType.equals("LUCENE_21")){
				return Version.LUCENE_21;
			}else if(versionType.equals("LUCENE_22")){
				return Version.LUCENE_22;
			}else if(versionType.equals("LUCENE_23")){
				return Version.LUCENE_23;
			}else if(versionType.equals("LUCENE_24")){
				return Version.LUCENE_24;
			}else if(versionType.equals("LUCENE_29")){
				return Version.LUCENE_29;
			}else if(versionType.equals("LUCENE_30")){
				return Version.LUCENE_30;
			}else if(versionType.equals("LUCENE_36")){
				return Version.LUCENE_36;
			}
			throw new RetrievalException(versionType+" 找不到对应的Lucene版本号!!!");
		}
	}
}
