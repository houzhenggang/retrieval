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
package framework.base.snoic.base.log;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import framework.base.snoic.base.util.StringClass;
import framework.base.snoic.base.util.file.FileHelper;

/**
 * 
 * @author 
 *
 */
public class SystemOut {
	private String filename=null;
	private boolean appendFlag=false;
	private static PrintStream printStreamOut=null;
	private static PrintStream printStreamErr=null;
	
	private FileHelper snoicsFile=new FileHelper();
    
	/**
	 * 获取是否保留原来的内容
	 * @return Returns the appendFlag.
	 */
	public boolean isAppendFlag() {
		return appendFlag;
	}

	/**
	 * 设置是否保留原来的内容
	 * @param appendFlag The appendFlag to set.
	 */
	public void setAppendFlag(boolean appendFlag) {
		this.appendFlag = appendFlag;
	}

	/**
	 * 获取输出文件
	 * @return Returns the filename.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * 设置输出文件
	 * @param filename The filename to set.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return Returns the printStreamErr.
	 */
	public static PrintStream getPrintStreamErr() {
		return printStreamErr;
	}

	/**
	 * @return Returns the printStreamOut.
	 */
	public static PrintStream getPrintStreamOut() {
		return printStreamOut;
	}
	
	/**
	 * 初始化，并且进行System.out的重写
	 *
	 */
	public void changeOut() {
		if(printStreamOut!=null) {
			printStreamOut.flush();
			printStreamOut.close();
			printStreamOut=null;
		}
		if(printStreamErr!=null) {
			printStreamErr.flush();
			printStreamErr.close();
			printStreamErr=null;
		}
		
		OutputStream outputStream=null;
		//String content=null;
		filename=StringClass.getString(filename);
		if(!filename.equals("")) {
			if(!snoicsFile.isFile(filename)) {
				snoicsFile.createFile(filename);
			}//else {
				//if(appendFlag) {
					//content=snoicsFile.fileToString(filename);
				//}
			//}
			outputStream=snoicsFile.fileToOutputStream(filename);
		}
		
		OverrideOutputStream snoicsOutputStreamOut=new OverrideOutputStream();
		if(outputStream!=null) {
			snoicsOutputStreamOut.setFileOutputStream((FileOutputStream)outputStream);
		}

		FileOutputStream fdOut = new FileOutputStream(FileDescriptor.out);
		snoicsOutputStreamOut.setFdOut(fdOut);
		printStreamOut = new PrintStream(snoicsOutputStreamOut);
		System.setOut(printStreamOut);
	    
		OverrideOutputStream snoicsOutputStreamErr=new OverrideOutputStream();
		if(outputStream!=null) {
			snoicsOutputStreamErr.setFileOutputStream((FileOutputStream)outputStream);
		}

		FileOutputStream fdErr = new FileOutputStream(FileDescriptor.err);
		snoicsOutputStreamErr.setFdErr(fdErr);
		printStreamErr = new PrintStream(snoicsOutputStreamErr);
		System.setErr(printStreamErr);
		
		//if(content!=null) {
		//	snoicsFile.stringToFile(content,filename);
		//}
	}
	/*
	public static void main(String[] args) {
		SystemOut systemOut=new SystemOut();
		systemOut.setFilename("c:/test.log");
		systemOut.setAppendFlag(true);
		systemOut.changeOut();
		System.out.println("chage System.out.println");
		System.err.println("chage System.err.println");
	}
	*/
}
