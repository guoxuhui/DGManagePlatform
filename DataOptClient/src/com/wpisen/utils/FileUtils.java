package com.wpisen.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileUtils {

	/**
	 * 获取文件中行数
	 * @param file
	 * @return
	 * @throws Exception
	 */
    public static int getFileLineNumber(File file) throws Exception{  
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));  
        lineNumberReader.skip(Long.MAX_VALUE);  
        int lineNumber = lineNumberReader.getLineNumber();  
        lineNumber++;  
        lineNumberReader.close();  
        return lineNumber;  
    } 
	
	/**
	 * 获取文件第二行数据
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readFrist2Line(File file, String charset) throws IOException {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();
			if (len == 0L) {
				return "";
			} else {
				long pos = 0;
				int cs = 0;
				while (pos < len) {
					pos++;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						cs = cs + 1;
						if(cs==2)
						break;
					}
				}
				raf.seek(0);
				byte[] bytes = new byte[(int) (pos)];
				raf.read(bytes);
				if (charset == null) {
					String twostr = new String(bytes);
					String[] twos = twostr.split("\\n");
					return twos[1];
				} else {
					String twostr = new String(bytes, charset);
					String[] twos = twostr.split("\\n");
					return twos[1];
				}
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (Exception e2) {
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 获取文件第一行数据
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readFristLine(File file, String charset) throws IOException {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();
			if (len == 0L) {
				return "";
			} else {
				long pos = 0;
				while (pos < len) {
					pos++;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						break;
					}
				}
				raf.seek(0);
				byte[] bytes = new byte[(int) (pos)];
				raf.read(bytes);
				if (charset == null) {
					return new String(bytes);
				} else {
					return new String(bytes, charset);
				}
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (Exception e2) {
				}
			}
		}
		return null;
	}

	/**
	 * 获取文件最后一行数据
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readLastLine(File file, String charset) throws IOException {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();
			if (len == 0L) {
				return "";
			} else {
				long pos = len - 1;
				while (pos > 0) {
					pos--;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						break;
					}
				}
				if (pos == 0) {
					raf.seek(0);
				}
				byte[] bytes = new byte[(int) (len - pos)];
				raf.read(bytes);
				if (charset == null) {
					return new String(bytes);
				} else {
					return new String(bytes, charset);
				}
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (Exception e2) {
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取文件倒数第二行数据
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readLast2Line(File file, String charset) throws IOException {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();
			if (len == 0L) {
				return "";
			} else {
				long pos = len - 1;
				int cs = 0;
				while (pos > 0) {
					pos--;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						cs = cs + 1;
						if(cs==2)
						break;
					}
				}
				if (pos == 0) {
					raf.seek(0);
				}
				byte[] bytes = new byte[(int) (len - pos)];
				raf.read(bytes);
				if (charset == null) {
					String twostr = new String(bytes);
					String[] twos = twostr.split("\\n");
					return twos[0];
				} else {
					String twostr = new String(bytes, charset);
					String[] twos = twostr.split("\\n");
					return twos[0];
				}
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (Exception e2) {
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取文件夹中最近修改的文件
	 * 如果获取为空返回null
	 * @param path
	 * @return
	 */
	public static File getLastModifiedFile(String path){
		List<File> files = getFileSort(path);
		if(files != null && files.size() > 0) {
			return files.get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * 获取目录下所有文件(按时间排序)
	 * 
	 * @param path
	 * @return
	 */
	public static List<File> getFileSort(String path) {
		List<File> list = getFiles(path, new ArrayList<File>());
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<File>() {
				public int compare(File file, File newFile) {
					if (file.lastModified() < newFile.lastModified()) {
						return 1;
					} else if (file.lastModified() == newFile.lastModified()) {
						return 0;
					} else {
						return -1;
					}
				}
			});
		}
		return list;
	}

	/**
	 * 获取目录下所有文件
	 * 
	 * @param realpath
	 * @param files
	 * @return
	 */
	public static List<File> getFiles(String realpath, List<File> files) {

		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				} else {
					files.add(file);
				}
			}
		}
		return files;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("/Users/apple/Desktop/EnzanCsv-2017-08-21.csv"); // 400M
		while(true) {
			long start = System.currentTimeMillis();
			String lastLine = FileUtils.readFrist2Line(file, "gbk");
			int num = FileUtils.getFileLineNumber(file);
			long delt = System.currentTimeMillis() - start;
			System.out.println(lastLine);
			System.out.println("文件共计"+num+"行");
			System.out.println("读取时间(毫秒):" + delt);
			Thread.sleep(2000);
		}
	}

}