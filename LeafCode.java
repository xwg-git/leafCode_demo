package com.chw.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LeafCode {
	
	public List<String> getLeafCode(List<Node> nodeList, String code) {
		
		List<String> leafList = new ArrayList<>();
		
		if(nodeList == null ) return null;
		
		for (Iterator<Node> iterator = nodeList.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			//判断指定节点是否在当前分片后的list中，存在取叶子节点
			if (code.equals(node.getCode())) {
				leafList.add(returnLeafCode(nodeList, node));
			}
		}
		return leafList;
	}
	
	//取最叶子节点code
	private String returnLeafCode(List<Node> list, Node node) {
	
		List<Node> childList = getChildList(list, node);// 得到子节点列表
		
		if (hasChild(list, node)) {// 判断是否有子节点
			Iterator<Node> it = childList.iterator();
			while (it.hasNext()) {
				Node n = (Node) it.next();
				returnLeafCode(list, n);
			}
		} 
		
		return node.getCode();
	}
	
	
	
	// 得到子节点列表
	private List<Node> getChildList(List<Node> list, Node node) {
		List<Node> nodeList = new ArrayList<Node>();
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			Node n = (Node) it.next();
			if (n.getParentCode() == node.getCode()) {
				nodeList.add(n);
			}
		}
		return nodeList;
	}

	// 判断是否有子节点
	private boolean hasChild(List<Node> list, Node node) {
		return getChildList(list, node).size() > 0 ? true : false;
	}
	
	
	public Map<String, List<Node>> splitListCode(List<Node> list, int num) {
		
		HashMap<String, List<Node>> listMap = new HashMap<String, 	List<Node>>();

		List<Node> listStr =  new ArrayList<Node>();
		// 把list进行分片
		for (int i = 0; i < list.size(); i++) {                       
	            	listStr.add(list.get(i));                           
	           	if (((i+1) % num == 0)||(i+1==list.size())) {               
	                		listMap.put("listStr" + i, listStr); 
	                		listStr = new ArrayList<Node>();                
	            	}
	        	}
	        return listMap;
	}
	
	

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		LeafCode lc = new LeafCode();
		List<Node> ln = new ArrayList<>();
		// 数据来源自己造
		
		Map<String, List<Node>> stringListHashMap = lc.splitListCode(ln,10);     //调用方法,将list平均分成10份.
		for(HashMap.Entry<String, List<Node>> entry : stringListHashMap.entrySet()){
			System.out.println(lc.getLeafCode(entry.getValue(), "node2").toString());
        }

		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start) + "ms");
	}
}

/**
 * 节点
 * @author chw
 *
 */
class Node{
	private String code; //当前节点
	private String parentCode; //父节点
	
	public Node(){}
	
	public Node(String code, String parentCode){
		this.code = code;
		this.parentCode = parentCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}