package adventcode;

import java.util.*;

public class Day7File {
	
	private String name;
	private int size;
	private Day7File parent;
	private ArrayList<Day7File> subFiles;
	private boolean isDir;
	private int totalSize = -1;
	
	public Day7File (String name, int size, Day7File parent, boolean isDir) {
		this.name = name;
		this.size = size;
		this.parent = parent;
		this.isDir = isDir;
		subFiles = new ArrayList<>();
	}
	
	public Day7File (String name, int size, Day7File parent) {
		this(name,size,parent,false);
	}
	
	public Day7File (String name, Day7File parent) {
		this(name,0,parent,true);
	}
	
	public String getName () {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public void addSubFile (Day7File subFile) {
		subFiles.add(subFile);
	}
	
	public Day7File getParent() {
		return parent;
	}
	
	public Iterator<Day7File> list () {
		return subFiles.iterator();
	}
	
	public Day7File getSubFile (String name) {
		Iterator<Day7File> iter = list();
		while (iter.hasNext()) {
			Day7File subFile = iter.next();
			if (subFile.getName().equals(name)) {
				return subFile;
			}
		}
		return null;
	}
	
	public String toString (String buffer) {
		StringBuilder sb = new StringBuilder();
		if (isDir) {
			sb.append(buffer + "- " + name + " (dir)\n");
			Iterator<Day7File> iter = list();
			while (iter.hasNext()) {
				sb.append(iter.next().toString("  " + buffer));
			}
		}
		else {
			sb.append(buffer + "- " + name + " (file, size=" + size + ")\n");
		}		
		return sb.toString();
	}
	
	public int getTotalSize () {
		if (totalSize == -1) {
			totalSize = size;			
			Iterator<Day7File> iter = list();
			while (iter.hasNext()) {
				totalSize += iter.next().getTotalSize();
			}
		}
		return totalSize;
	}
	
	public int getScore1 () {
		int ret = 0;
		int totalSize = getTotalSize();
		if (totalSize <= 100000 && isDir ) {
			ret = totalSize;
		}
		Iterator<Day7File> iter = list();
		while (iter.hasNext()) {
			ret += iter.next().getScore1();
		}
		return ret;
	}
	
	public Day7File getScore2 (int minumumSize) {
		Day7File ret = null;
		
		if (getTotalSize() >= minumumSize && isDir) {
			ret = this;
		}
		Iterator<Day7File> iter = subFiles.iterator();
		while (iter.hasNext()) {
			Day7File temp = iter.next().getScore2(minumumSize);
			if ((temp != null && ret == null) || (temp != null && temp.getTotalSize() < ret.getTotalSize())) {
				ret = temp;
			}
		}		
		return ret;
	}
}
