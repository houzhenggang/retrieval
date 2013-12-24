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
package framework.retrieval.engine;

import java.io.Serializable;

/**
 * 所有的索引相关枚举类型
 * @author 
 *
 */
public class RetrievalType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995107233722502098L;

	private RetrievalType() {

	}

	/*************************************************************************
	 * 索引分类大类来源 以这个分类来区别几种大的索引内容来源类型
	 * 
	 */
	public enum RIndexSourceType implements Serializable {
		/**
		 * 文本内容索引
		 */
		T,
		/**
		 * 数据库类型索引
		 */
		D,
		/**
		 * 文件类型索引
		 */
		F;
	}

	/*************************************************************************
	 * 索引字段分类 以这几种分类来区分同一个Doc中各个字段属于什么类型的字段， 并根据这些字段来判断要以哪种方式建立索引
	 * 
	 */
	public enum RDocItemType implements Serializable {

		/**
		 * 日期类型字段，以 索引+存储 的方式对这个类型的字段建索引，可以被搜索
		 */
		DATE,

		/**
		 * 数值类型字段，以 索引+存储 的方式对这个类型的字段建索引，可以被搜索
		 */
		NUMBER,
		/**
		 * 关键字字段，以 索引+存储 的方式对这个类型的字段建索引，可以被搜索
		 */
		KEYWORD,

		/**
		 * 以存储的方式对这个类型的字段进行保存,并且不能被搜索
		 */
		STORE_ONLY,

		/**
		 * 属性字段，以 分词+索引+存储 的方式对这个类型的字段建索引，可以被搜索
		 */
		PROPERTY,

		/**
		 * 文本内容字段，以 分词+索引 的方式对这个类型的字段建索引，可以被搜索
		 */
		CONTENT;

	}

	/*************************************************************************
	 * 索引特定字段类型的名称
	 * 
	 */
	public enum RDocItemSpecialName implements Serializable {
		/**
		 * 索引ID字段对应的名称
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IID,

		/**
		 * 索引来源类型字段对应的名称,存放内容如 数据库、文件、文本
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IST,

		/**
		 * 索引信息分类字段对应的名称，存放内容由使用者任意设置，其作用为在同一个索引文件中区分不同业务分类的索引
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IBT,

		/**
		 * 索引全内容字段对应的名称，存放内容为所有字段内容相加组合的字符串
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IAC,

		/**
		 * 索引中所有字段的名称，存放内容为所有字段名称加分隔符组合的字符串
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IAF,

		/**
		 * 该索引被点击的次数对应字段名，存放内容为改条记录的点击次数
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IH,

		/**
		 * 该索引的创建时间对应字段名，存放内容为该条记录的创建时间
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_IC;
	}

	/*************************************************************************
	 * 文件类型索引特定字段类型名称
	 * 
	 */
	public enum RFileDocItemType implements Serializable {
		/**
		 * 文件ID对应字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_FID,

		/**
		 * 文件名称对应字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_FN,

		/**
		 * 文件相对路径对应字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_FP,

		/**
		 * 文件修改时间对应字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_FM,

		/**
		 * 文件内容对应字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_FC;
	}

	/*************************************************************************
	 * 数据库类型索引特定字段类型名称
	 * 
	 */
	public enum RDatabaseDocItemType implements Serializable {

		/**
		 * 数据库表名对应字段名,存放内容为该条记录对应的表名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_DT,

		/**
		 * 数据库主键名称ID字段名,存放内容为该条记录主键字段名
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_DK,

		/**
		 * 数据库主键值对应字段名,存放内容为该条记录主键值
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_DID,

		/**
		 * 数据库记录索引唯一ID对应字段名,存放内容为由 TABLE+RECORDID组成的字符串
		 * 
		 * 		存储类型 		RetrievalType.RDocItemType.KEYWORD
		 */
		_DTID;

	}

	/**
	 * 批量索引操作类型
	 * 
	 */
	public enum RIndexOperatorType implements Serializable {
		/**
		 * 无论数据是否已经在索引中存在，都新增一条索引内容
		 */
		INSERT, 
		
		/**
		 * 如果数据已经在索引中存在，则更新原索引内容，否则新增一条索引内容
		 */
		UPDATE, 
		
		/**
		 * 从索引中删除一条内容
		 */
		DELETE;
	}
	
	/*************************************************************************
	 * 数据库类型，暂时支持MySql/Oracle/SqlServer
	 * 
	 */
	public enum RDatabaseType implements Serializable {
		/**
		 * MySql数据库
		 */
		MYSQL,
		/**
		 * Oracle数据库
		 */
		ORACLE,
		/**
		 * SqlServer数据库
		 */
		SQLSERVER;
	}
}
