package adventcode;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public class Day16CaveParser {
	private ArrayList<Day16Cave> caves;
	private int[][] distances;
		
	public Day16CaveParser (LineNumberReader in) throws Exception {
		caves = new ArrayList<>();
		
		String line = in.readLine();
		while (line != null) {
			String name = line.substring(6,8);
			int flowRate = Integer.parseInt(line.substring(line.indexOf("=")+1,line.indexOf(";")));
			String[] tunnelNames =line.substring(line.lastIndexOf("valves")+6).split(",");
			ArrayList<Day16Cave> tunnels = new ArrayList<>();

			for (int i=0;i<tunnelNames.length;i++) {
				Day16Cave tunnel = new Day16Cave (tunnelNames[i].trim());
				if (caves.contains(tunnel)) {
					tunnels.add(caves.get(caves.indexOf(tunnel)));
				}
				else {
					caves.add(tunnel);
					tunnels.add(tunnel);
				}				
			}
			
			Day16Cave cave = new Day16Cave (name, flowRate, tunnels);
			if (caves.contains(cave)) {
				Day16Cave found = caves.get(caves.indexOf(cave));
				found.setFlowRate(flowRate);
				found.setTunnels(tunnels);
			}
			else {
				caves.add(cave);
			}
			
			line = in.readLine();
		}
		distances = new int[caves.size()][caves.size()];
		
		for (int i=0;i<caves.size();i++) {
				distances[i] = getShortestPaths (caves.get(i));
		}
	}
	
	public ArrayList<String> getRelevantCaves () {
		ArrayList<String> ret = new ArrayList<>();
		Iterator<Day16Cave> iter = caves.iterator();
		while (iter.hasNext()) {
			Day16Cave cave = iter.next();
			if (cave.getFlowRate() > 0) {
				ret.add(cave.getName());				
			}
		}
		Collections.sort(ret);
		return ret;
	}
	
	public TreeMap<String,Integer> getDistances() {
		ArrayList<String> list = getRelevantCaves();
		list.add("AA");
		Collections.sort(list);
		
		TreeMap<String,Integer> ret = new TreeMap<>();
		
		for (int i=0;i<list.size();i++) {
			for (int j=i;j<list.size();j++) {
				ret.put(list.get(i) + "" + list.get(j), distances[caves.indexOf(new Day16Cave(list.get(i)))][caves.indexOf(new Day16Cave(list.get(j)))]);
			}			
		}					
		return ret;		
	}
	
	public TreeMap<String,Integer> getFlowRates() {
		TreeMap<String,Integer> ret = new TreeMap<>();
		ret.put("AA", 0);
		Iterator<String> iter = getRelevantCaves().iterator();
		while (iter.hasNext()) {
			Day16Cave cave = caves.get(caves.indexOf(new Day16Cave(iter.next())));
			ret.put(cave.getName(), cave.getFlowRate());
		}
		return ret;
	}
	
	private int[] getShortestPaths (Day16Cave from) {
		int[] pathLength = new int[caves.size()];
		
		for (int i=0; i<pathLength.length;i++) {
			pathLength[i] = Integer.MAX_VALUE;
		}
		
		pathLength[caves.indexOf(from)] = 0;
		
		for (int j=0;j<caves.size();j++) {
			for (int i=0;i<caves.size();i++) {
				int found = Integer.MAX_VALUE;
				Day16Cave check = caves.get(i);
				Iterator<Day16Cave> iter = check.getTunnels().iterator();
				
				while (iter.hasNext()) {
					found = Math.min(found, pathLength[caves.indexOf((iter.next()))]);
				}
				if (pathLength[caves.indexOf(check)] > found ) {
					pathLength[caves.indexOf(check)] = found +1;
				}				
			}
		}
		return pathLength;
	}

}
